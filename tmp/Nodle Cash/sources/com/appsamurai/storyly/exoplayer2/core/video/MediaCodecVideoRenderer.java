package com.appsamurai.storyly.exoplayer2.core.video;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Point;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.util.Pair;
import android.view.Surface;
import androidx.annotation.CallSuper;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import com.appsamurai.storyly.exoplayer2.common.C;
import com.appsamurai.storyly.exoplayer2.common.Format;
import com.appsamurai.storyly.exoplayer2.common.util.Assertions;
import com.appsamurai.storyly.exoplayer2.common.util.Log;
import com.appsamurai.storyly.exoplayer2.common.util.MediaFormatUtil;
import com.appsamurai.storyly.exoplayer2.common.util.MimeTypes;
import com.appsamurai.storyly.exoplayer2.common.util.TraceUtil;
import com.appsamurai.storyly.exoplayer2.common.util.Util;
import com.appsamurai.storyly.exoplayer2.common.video.VideoSize;
import com.appsamurai.storyly.exoplayer2.core.ExoPlaybackException;
import com.appsamurai.storyly.exoplayer2.core.FormatHolder;
import com.appsamurai.storyly.exoplayer2.core.RendererCapabilities;
import com.appsamurai.storyly.exoplayer2.core.decoder.DecoderCounters;
import com.appsamurai.storyly.exoplayer2.core.decoder.DecoderReuseEvaluation;
import com.appsamurai.storyly.exoplayer2.core.mediacodec.MediaCodecAdapter;
import com.appsamurai.storyly.exoplayer2.core.mediacodec.MediaCodecDecoderException;
import com.appsamurai.storyly.exoplayer2.core.mediacodec.MediaCodecInfo;
import com.appsamurai.storyly.exoplayer2.core.mediacodec.MediaCodecRenderer;
import com.appsamurai.storyly.exoplayer2.core.mediacodec.MediaCodecSelector;
import com.appsamurai.storyly.exoplayer2.core.mediacodec.MediaCodecUtil;
import com.appsamurai.storyly.exoplayer2.core.video.VideoRendererEventListener;
import com.appsamurai.storyly.exoplayer2.decoder.DecoderInputBuffer;
import com.google.android.gms.common.Scopes;
import com.google.common.collect.ImmutableList;
import java.nio.ByteBuffer;
import java.util.List;

public class MediaCodecVideoRenderer extends MediaCodecRenderer {
    private static final float INITIAL_FORMAT_MAX_INPUT_SIZE_SCALE_FACTOR = 1.5f;
    private static final String KEY_CROP_BOTTOM = "crop-bottom";
    private static final String KEY_CROP_LEFT = "crop-left";
    private static final String KEY_CROP_RIGHT = "crop-right";
    private static final String KEY_CROP_TOP = "crop-top";
    private static final int[] STANDARD_LONG_EDGE_VIDEO_PX = {1920, 1600, 1440, 1280, 960, 854, 640, 540, 480};
    private static final String TAG = "MediaCodecVideoRenderer";
    private static final long TUNNELING_EOS_PRESENTATION_TIME_US = Long.MAX_VALUE;
    private static boolean deviceNeedsSetOutputSurfaceWorkaround;
    private static boolean evaluatedDeviceNeedsSetOutputSurfaceWorkaround;
    private final long allowedJoiningTimeMs;
    private int buffersInCodecCount;
    private boolean codecHandlesHdr10PlusOutOfBandMetadata;
    private CodecMaxValues codecMaxValues;
    private boolean codecNeedsSetOutputSurfaceWorkaround;
    private int consecutiveDroppedFrameCount;
    private final Context context;
    private int currentHeight;
    private float currentPixelWidthHeightRatio;
    private int currentUnappliedRotationDegrees;
    private int currentWidth;
    private final boolean deviceNeedsNoPostProcessWorkaround;
    private long droppedFrameAccumulationStartTimeMs;
    private int droppedFrames;
    private final VideoRendererEventListener.EventDispatcher eventDispatcher;
    @Nullable
    private VideoFrameMetadataListener frameMetadataListener;
    private final VideoFrameReleaseHelper frameReleaseHelper;
    private boolean haveReportedFirstFrameRenderedForCurrentSurface;
    private long initialPositionUs;
    private long joiningDeadlineMs;
    private long lastBufferPresentationTimeUs;
    private long lastRenderRealtimeUs;
    private final int maxDroppedFramesToNotify;
    private boolean mayRenderFirstFrameAfterEnableIfNotStarted;
    @Nullable
    private PlaceholderSurface placeholderSurface;
    private boolean renderedFirstFrameAfterEnable;
    private boolean renderedFirstFrameAfterReset;
    @Nullable
    private VideoSize reportedVideoSize;
    private int scalingMode;
    @Nullable
    private Surface surface;
    private long totalVideoFrameProcessingOffsetUs;
    private boolean tunneling;
    private int tunnelingAudioSessionId;
    @Nullable
    OnFrameRenderedListenerV23 tunnelingOnFrameRenderedListener;
    private int videoFrameProcessingOffsetCount;

    public static final class CodecMaxValues {
        public final int height;
        public final int inputSize;
        public final int width;

        public CodecMaxValues(int i3, int i4, int i5) {
            this.width = i3;
            this.height = i4;
            this.inputSize = i5;
        }
    }

    @RequiresApi(23)
    public final class OnFrameRenderedListenerV23 implements MediaCodecAdapter.OnFrameRenderedListener, Handler.Callback {
        private static final int HANDLE_FRAME_RENDERED = 0;
        private final Handler handler;

        public OnFrameRenderedListenerV23(MediaCodecAdapter mediaCodecAdapter) {
            Handler createHandlerForCurrentLooper = Util.createHandlerForCurrentLooper(this);
            this.handler = createHandlerForCurrentLooper;
            mediaCodecAdapter.setOnFrameRenderedListener(this, createHandlerForCurrentLooper);
        }

        private void handleFrameRendered(long j2) {
            MediaCodecVideoRenderer mediaCodecVideoRenderer = MediaCodecVideoRenderer.this;
            if (this == mediaCodecVideoRenderer.tunnelingOnFrameRenderedListener) {
                if (j2 == Long.MAX_VALUE) {
                    mediaCodecVideoRenderer.onProcessedTunneledEndOfStream();
                    return;
                }
                try {
                    mediaCodecVideoRenderer.onProcessedTunneledBuffer(j2);
                } catch (ExoPlaybackException e3) {
                    MediaCodecVideoRenderer.this.setPendingPlaybackException(e3);
                }
            }
        }

        public boolean handleMessage(Message message) {
            if (message.what != 0) {
                return false;
            }
            handleFrameRendered(Util.toLong(message.arg1, message.arg2));
            return true;
        }

        public void onFrameRendered(MediaCodecAdapter mediaCodecAdapter, long j2, long j3) {
            if (Util.SDK_INT < 30) {
                this.handler.sendMessageAtFrontOfQueue(Message.obtain(this.handler, 0, (int) (j2 >> 32), (int) j2));
                return;
            }
            handleFrameRendered(j2);
        }
    }

    public MediaCodecVideoRenderer(Context context2, MediaCodecSelector mediaCodecSelector) {
        this(context2, mediaCodecSelector, 0);
    }

    private void clearRenderedFirstFrame() {
        MediaCodecAdapter codec;
        this.renderedFirstFrameAfterReset = false;
        if (Util.SDK_INT >= 23 && this.tunneling && (codec = getCodec()) != null) {
            this.tunnelingOnFrameRenderedListener = new OnFrameRenderedListenerV23(codec);
        }
    }

    private void clearReportedVideoSize() {
        this.reportedVideoSize = null;
    }

    @RequiresApi(21)
    private static void configureTunnelingV21(MediaFormat mediaFormat, int i3) {
        mediaFormat.setFeatureEnabled("tunneled-playback", true);
        mediaFormat.setInteger("audio-session-id", i3);
    }

    private static boolean deviceNeedsNoPostProcessWorkaround() {
        return "NVIDIA".equals(Util.MANUFACTURER);
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:379:0x06cb, code lost:
        if (r10.equals("A10-70L") == false) goto L_0x0099;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:463:0x0842, code lost:
        if (r0.equals("AFTN") == false) goto L_0x083a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:471:0x085e, code lost:
        return true;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static boolean evaluateDeviceNeedsSetOutputSurfaceWorkaround() {
        /*
            r0 = 26
            r1 = 27
            r2 = 7
            r3 = 6
            r4 = 5
            r5 = 4
            r6 = 3
            r7 = 2
            r8 = -1
            r9 = 0
            int r10 = com.appsamurai.storyly.exoplayer2.common.util.Util.SDK_INT
            r11 = 28
            r12 = 1
            if (r10 > r11) goto L_0x007e
            java.lang.String r13 = com.appsamurai.storyly.exoplayer2.common.util.Util.DEVICE
            r13.getClass()
            int r14 = r13.hashCode()
            switch(r14) {
                case -1339091551: goto L_0x006f;
                case -1220081023: goto L_0x0064;
                case -1220066608: goto L_0x0059;
                case -1012436106: goto L_0x004e;
                case -760312546: goto L_0x0043;
                case -64886864: goto L_0x0038;
                case 3415681: goto L_0x002d;
                case 825323514: goto L_0x0022;
                default: goto L_0x001f;
            }
        L_0x001f:
            r13 = r8
            goto L_0x0079
        L_0x0022:
            java.lang.String r14 = "machuca"
            boolean r13 = r13.equals(r14)
            if (r13 != 0) goto L_0x002b
            goto L_0x001f
        L_0x002b:
            r13 = r2
            goto L_0x0079
        L_0x002d:
            java.lang.String r14 = "once"
            boolean r13 = r13.equals(r14)
            if (r13 != 0) goto L_0x0036
            goto L_0x001f
        L_0x0036:
            r13 = r3
            goto L_0x0079
        L_0x0038:
            java.lang.String r14 = "magnolia"
            boolean r13 = r13.equals(r14)
            if (r13 != 0) goto L_0x0041
            goto L_0x001f
        L_0x0041:
            r13 = r4
            goto L_0x0079
        L_0x0043:
            java.lang.String r14 = "aquaman"
            boolean r13 = r13.equals(r14)
            if (r13 != 0) goto L_0x004c
            goto L_0x001f
        L_0x004c:
            r13 = r5
            goto L_0x0079
        L_0x004e:
            java.lang.String r14 = "oneday"
            boolean r13 = r13.equals(r14)
            if (r13 != 0) goto L_0x0057
            goto L_0x001f
        L_0x0057:
            r13 = r6
            goto L_0x0079
        L_0x0059:
            java.lang.String r14 = "dangalUHD"
            boolean r13 = r13.equals(r14)
            if (r13 != 0) goto L_0x0062
            goto L_0x001f
        L_0x0062:
            r13 = r7
            goto L_0x0079
        L_0x0064:
            java.lang.String r14 = "dangalFHD"
            boolean r13 = r13.equals(r14)
            if (r13 != 0) goto L_0x006d
            goto L_0x001f
        L_0x006d:
            r13 = r12
            goto L_0x0079
        L_0x006f:
            java.lang.String r14 = "dangal"
            boolean r13 = r13.equals(r14)
            if (r13 != 0) goto L_0x0078
            goto L_0x001f
        L_0x0078:
            r13 = r9
        L_0x0079:
            switch(r13) {
                case 0: goto L_0x007d;
                case 1: goto L_0x007d;
                case 2: goto L_0x007d;
                case 3: goto L_0x007d;
                case 4: goto L_0x007d;
                case 5: goto L_0x007d;
                case 6: goto L_0x007d;
                case 7: goto L_0x007d;
                default: goto L_0x007c;
            }
        L_0x007c:
            goto L_0x007e
        L_0x007d:
            return r12
        L_0x007e:
            if (r10 > r1) goto L_0x008b
            java.lang.String r13 = "HWEML"
            java.lang.String r14 = com.appsamurai.storyly.exoplayer2.common.util.Util.DEVICE
            boolean r13 = r13.equals(r14)
            if (r13 == 0) goto L_0x008b
            return r12
        L_0x008b:
            if (r10 > r0) goto L_0x085f
            java.lang.String r10 = com.appsamurai.storyly.exoplayer2.common.util.Util.DEVICE
            r10.getClass()
            int r13 = r10.hashCode()
            switch(r13) {
                case -2144781245: goto L_0x0820;
                case -2144781185: goto L_0x0814;
                case -2144781160: goto L_0x0808;
                case -2097309513: goto L_0x07fc;
                case -2022874474: goto L_0x07f0;
                case -1978993182: goto L_0x07e4;
                case -1978990237: goto L_0x07d8;
                case -1936688988: goto L_0x07cc;
                case -1936688066: goto L_0x07be;
                case -1936688065: goto L_0x07b0;
                case -1931988508: goto L_0x07a2;
                case -1885099851: goto L_0x0794;
                case -1696512866: goto L_0x0786;
                case -1680025915: goto L_0x0778;
                case -1615810839: goto L_0x076a;
                case -1600724499: goto L_0x075c;
                case -1554255044: goto L_0x074d;
                case -1481772737: goto L_0x073f;
                case -1481772730: goto L_0x0731;
                case -1481772729: goto L_0x0723;
                case -1320080169: goto L_0x0715;
                case -1217592143: goto L_0x0707;
                case -1180384755: goto L_0x06f9;
                case -1139198265: goto L_0x06eb;
                case -1052835013: goto L_0x06dd;
                case -993250464: goto L_0x06cf;
                case -993250458: goto L_0x06c5;
                case -965403638: goto L_0x06b8;
                case -958336948: goto L_0x06ab;
                case -879245230: goto L_0x069c;
                case -842500323: goto L_0x068e;
                case -821392978: goto L_0x0680;
                case -797483286: goto L_0x0672;
                case -794946968: goto L_0x0663;
                case -788334647: goto L_0x0654;
                case -782144577: goto L_0x0646;
                case -575125681: goto L_0x0638;
                case -521118391: goto L_0x062a;
                case -430914369: goto L_0x061c;
                case -290434366: goto L_0x060d;
                case -282781963: goto L_0x05ff;
                case -277133239: goto L_0x05f1;
                case -173639913: goto L_0x05e3;
                case -56598463: goto L_0x05d4;
                case 2126: goto L_0x05c6;
                case 2564: goto L_0x05b8;
                case 2715: goto L_0x05aa;
                case 2719: goto L_0x059c;
                case 3091: goto L_0x058e;
                case 3483: goto L_0x0580;
                case 73405: goto L_0x0572;
                case 75537: goto L_0x0564;
                case 75739: goto L_0x0556;
                case 76779: goto L_0x0548;
                case 78669: goto L_0x053a;
                case 79305: goto L_0x052c;
                case 80618: goto L_0x051e;
                case 88274: goto L_0x0510;
                case 98846: goto L_0x0502;
                case 98848: goto L_0x04f4;
                case 99329: goto L_0x04e6;
                case 101481: goto L_0x04d8;
                case 1513190: goto L_0x04ca;
                case 1514184: goto L_0x04bc;
                case 1514185: goto L_0x04ae;
                case 2133089: goto L_0x04a0;
                case 2133091: goto L_0x0492;
                case 2133120: goto L_0x0484;
                case 2133151: goto L_0x0476;
                case 2133182: goto L_0x0468;
                case 2133184: goto L_0x045a;
                case 2436959: goto L_0x044c;
                case 2463773: goto L_0x043e;
                case 2464648: goto L_0x0430;
                case 2689555: goto L_0x0422;
                case 3154429: goto L_0x0414;
                case 3284551: goto L_0x0406;
                case 3351335: goto L_0x03f8;
                case 3386211: goto L_0x03ea;
                case 41325051: goto L_0x03dc;
                case 51349633: goto L_0x03ce;
                case 51350594: goto L_0x03c0;
                case 55178625: goto L_0x03b2;
                case 61542055: goto L_0x03a4;
                case 65355429: goto L_0x0396;
                case 66214468: goto L_0x0388;
                case 66214470: goto L_0x037a;
                case 66214473: goto L_0x036c;
                case 66215429: goto L_0x035e;
                case 66215431: goto L_0x0350;
                case 66215433: goto L_0x0342;
                case 66216390: goto L_0x0334;
                case 76402249: goto L_0x0326;
                case 76404105: goto L_0x0318;
                case 76404911: goto L_0x030a;
                case 80963634: goto L_0x02fc;
                case 82882791: goto L_0x02ee;
                case 98715550: goto L_0x02e0;
                case 101370885: goto L_0x02d2;
                case 102844228: goto L_0x02c4;
                case 165221241: goto L_0x02b6;
                case 182191441: goto L_0x02a8;
                case 245388979: goto L_0x029a;
                case 287431619: goto L_0x028c;
                case 307593612: goto L_0x027e;
                case 308517133: goto L_0x0270;
                case 316215098: goto L_0x0262;
                case 316215116: goto L_0x0254;
                case 316246811: goto L_0x0246;
                case 316246818: goto L_0x0238;
                case 407160593: goto L_0x022a;
                case 507412548: goto L_0x021c;
                case 793982701: goto L_0x020e;
                case 794038622: goto L_0x0200;
                case 794040393: goto L_0x01f2;
                case 835649806: goto L_0x01e4;
                case 917340916: goto L_0x01d6;
                case 958008161: goto L_0x01c8;
                case 1060579533: goto L_0x01ba;
                case 1150207623: goto L_0x01ac;
                case 1176899427: goto L_0x019e;
                case 1280332038: goto L_0x0190;
                case 1306947716: goto L_0x0182;
                case 1349174697: goto L_0x0174;
                case 1522194893: goto L_0x0165;
                case 1691543273: goto L_0x0157;
                case 1691544261: goto L_0x0149;
                case 1709443163: goto L_0x013b;
                case 1865889110: goto L_0x012d;
                case 1906253259: goto L_0x011f;
                case 1977196784: goto L_0x0111;
                case 2006372676: goto L_0x0104;
                case 2019281702: goto L_0x00f7;
                case 2029784656: goto L_0x00ea;
                case 2030379515: goto L_0x00dd;
                case 2033393791: goto L_0x00d0;
                case 2047190025: goto L_0x00c3;
                case 2047252157: goto L_0x00b6;
                case 2048319463: goto L_0x00a9;
                case 2048855701: goto L_0x009c;
                default: goto L_0x0099;
            }
        L_0x0099:
            r0 = r8
            goto L_0x082b
        L_0x009c:
            java.lang.String r0 = "HWWAS-H"
            boolean r0 = r10.equals(r0)
            if (r0 != 0) goto L_0x00a5
            goto L_0x0099
        L_0x00a5:
            r0 = 139(0x8b, float:1.95E-43)
            goto L_0x082b
        L_0x00a9:
            java.lang.String r0 = "HWVNS-H"
            boolean r0 = r10.equals(r0)
            if (r0 != 0) goto L_0x00b2
            goto L_0x0099
        L_0x00b2:
            r0 = 138(0x8a, float:1.93E-43)
            goto L_0x082b
        L_0x00b6:
            java.lang.String r0 = "ELUGA_Prim"
            boolean r0 = r10.equals(r0)
            if (r0 != 0) goto L_0x00bf
            goto L_0x0099
        L_0x00bf:
            r0 = 137(0x89, float:1.92E-43)
            goto L_0x082b
        L_0x00c3:
            java.lang.String r0 = "ELUGA_Note"
            boolean r0 = r10.equals(r0)
            if (r0 != 0) goto L_0x00cc
            goto L_0x0099
        L_0x00cc:
            r0 = 136(0x88, float:1.9E-43)
            goto L_0x082b
        L_0x00d0:
            java.lang.String r0 = "ASUS_X00AD_2"
            boolean r0 = r10.equals(r0)
            if (r0 != 0) goto L_0x00d9
            goto L_0x0099
        L_0x00d9:
            r0 = 135(0x87, float:1.89E-43)
            goto L_0x082b
        L_0x00dd:
            java.lang.String r0 = "HWCAM-H"
            boolean r0 = r10.equals(r0)
            if (r0 != 0) goto L_0x00e6
            goto L_0x0099
        L_0x00e6:
            r0 = 134(0x86, float:1.88E-43)
            goto L_0x082b
        L_0x00ea:
            java.lang.String r0 = "HWBLN-H"
            boolean r0 = r10.equals(r0)
            if (r0 != 0) goto L_0x00f3
            goto L_0x0099
        L_0x00f3:
            r0 = 133(0x85, float:1.86E-43)
            goto L_0x082b
        L_0x00f7:
            java.lang.String r0 = "DM-01K"
            boolean r0 = r10.equals(r0)
            if (r0 != 0) goto L_0x0100
            goto L_0x0099
        L_0x0100:
            r0 = 132(0x84, float:1.85E-43)
            goto L_0x082b
        L_0x0104:
            java.lang.String r0 = "BRAVIA_ATV3_4K"
            boolean r0 = r10.equals(r0)
            if (r0 != 0) goto L_0x010d
            goto L_0x0099
        L_0x010d:
            r0 = 131(0x83, float:1.84E-43)
            goto L_0x082b
        L_0x0111:
            java.lang.String r0 = "Infinix-X572"
            boolean r0 = r10.equals(r0)
            if (r0 != 0) goto L_0x011b
            goto L_0x0099
        L_0x011b:
            r0 = 130(0x82, float:1.82E-43)
            goto L_0x082b
        L_0x011f:
            java.lang.String r0 = "PB2-670M"
            boolean r0 = r10.equals(r0)
            if (r0 != 0) goto L_0x0129
            goto L_0x0099
        L_0x0129:
            r0 = 129(0x81, float:1.81E-43)
            goto L_0x082b
        L_0x012d:
            java.lang.String r0 = "santoni"
            boolean r0 = r10.equals(r0)
            if (r0 != 0) goto L_0x0137
            goto L_0x0099
        L_0x0137:
            r0 = 128(0x80, float:1.794E-43)
            goto L_0x082b
        L_0x013b:
            java.lang.String r0 = "iball8735_9806"
            boolean r0 = r10.equals(r0)
            if (r0 != 0) goto L_0x0145
            goto L_0x0099
        L_0x0145:
            r0 = 127(0x7f, float:1.78E-43)
            goto L_0x082b
        L_0x0149:
            java.lang.String r0 = "CPH1715"
            boolean r0 = r10.equals(r0)
            if (r0 != 0) goto L_0x0153
            goto L_0x0099
        L_0x0153:
            r0 = 126(0x7e, float:1.77E-43)
            goto L_0x082b
        L_0x0157:
            java.lang.String r0 = "CPH1609"
            boolean r0 = r10.equals(r0)
            if (r0 != 0) goto L_0x0161
            goto L_0x0099
        L_0x0161:
            r0 = 125(0x7d, float:1.75E-43)
            goto L_0x082b
        L_0x0165:
            java.lang.String r0 = "woods_f"
            boolean r0 = r10.equals(r0)
            if (r0 != 0) goto L_0x0170
            goto L_0x0099
        L_0x0170:
            r0 = 124(0x7c, float:1.74E-43)
            goto L_0x082b
        L_0x0174:
            java.lang.String r0 = "htc_e56ml_dtul"
            boolean r0 = r10.equals(r0)
            if (r0 != 0) goto L_0x017e
            goto L_0x0099
        L_0x017e:
            r0 = 123(0x7b, float:1.72E-43)
            goto L_0x082b
        L_0x0182:
            java.lang.String r0 = "EverStar_S"
            boolean r0 = r10.equals(r0)
            if (r0 != 0) goto L_0x018c
            goto L_0x0099
        L_0x018c:
            r0 = 122(0x7a, float:1.71E-43)
            goto L_0x082b
        L_0x0190:
            java.lang.String r0 = "hwALE-H"
            boolean r0 = r10.equals(r0)
            if (r0 != 0) goto L_0x019a
            goto L_0x0099
        L_0x019a:
            r0 = 121(0x79, float:1.7E-43)
            goto L_0x082b
        L_0x019e:
            java.lang.String r0 = "itel_S41"
            boolean r0 = r10.equals(r0)
            if (r0 != 0) goto L_0x01a8
            goto L_0x0099
        L_0x01a8:
            r0 = 120(0x78, float:1.68E-43)
            goto L_0x082b
        L_0x01ac:
            java.lang.String r0 = "LS-5017"
            boolean r0 = r10.equals(r0)
            if (r0 != 0) goto L_0x01b6
            goto L_0x0099
        L_0x01b6:
            r0 = 119(0x77, float:1.67E-43)
            goto L_0x082b
        L_0x01ba:
            java.lang.String r0 = "panell_d"
            boolean r0 = r10.equals(r0)
            if (r0 != 0) goto L_0x01c4
            goto L_0x0099
        L_0x01c4:
            r0 = 118(0x76, float:1.65E-43)
            goto L_0x082b
        L_0x01c8:
            java.lang.String r0 = "j2xlteins"
            boolean r0 = r10.equals(r0)
            if (r0 != 0) goto L_0x01d2
            goto L_0x0099
        L_0x01d2:
            r0 = 117(0x75, float:1.64E-43)
            goto L_0x082b
        L_0x01d6:
            java.lang.String r0 = "A7000plus"
            boolean r0 = r10.equals(r0)
            if (r0 != 0) goto L_0x01e0
            goto L_0x0099
        L_0x01e0:
            r0 = 116(0x74, float:1.63E-43)
            goto L_0x082b
        L_0x01e4:
            java.lang.String r0 = "manning"
            boolean r0 = r10.equals(r0)
            if (r0 != 0) goto L_0x01ee
            goto L_0x0099
        L_0x01ee:
            r0 = 115(0x73, float:1.61E-43)
            goto L_0x082b
        L_0x01f2:
            java.lang.String r0 = "GIONEE_WBL7519"
            boolean r0 = r10.equals(r0)
            if (r0 != 0) goto L_0x01fc
            goto L_0x0099
        L_0x01fc:
            r0 = 114(0x72, float:1.6E-43)
            goto L_0x082b
        L_0x0200:
            java.lang.String r0 = "GIONEE_WBL7365"
            boolean r0 = r10.equals(r0)
            if (r0 != 0) goto L_0x020a
            goto L_0x0099
        L_0x020a:
            r0 = 113(0x71, float:1.58E-43)
            goto L_0x082b
        L_0x020e:
            java.lang.String r0 = "GIONEE_WBL5708"
            boolean r0 = r10.equals(r0)
            if (r0 != 0) goto L_0x0218
            goto L_0x0099
        L_0x0218:
            r0 = 112(0x70, float:1.57E-43)
            goto L_0x082b
        L_0x021c:
            java.lang.String r0 = "QM16XE_U"
            boolean r0 = r10.equals(r0)
            if (r0 != 0) goto L_0x0226
            goto L_0x0099
        L_0x0226:
            r0 = 111(0x6f, float:1.56E-43)
            goto L_0x082b
        L_0x022a:
            java.lang.String r0 = "Pixi5-10_4G"
            boolean r0 = r10.equals(r0)
            if (r0 != 0) goto L_0x0234
            goto L_0x0099
        L_0x0234:
            r0 = 110(0x6e, float:1.54E-43)
            goto L_0x082b
        L_0x0238:
            java.lang.String r0 = "TB3-850M"
            boolean r0 = r10.equals(r0)
            if (r0 != 0) goto L_0x0242
            goto L_0x0099
        L_0x0242:
            r0 = 109(0x6d, float:1.53E-43)
            goto L_0x082b
        L_0x0246:
            java.lang.String r0 = "TB3-850F"
            boolean r0 = r10.equals(r0)
            if (r0 != 0) goto L_0x0250
            goto L_0x0099
        L_0x0250:
            r0 = 108(0x6c, float:1.51E-43)
            goto L_0x082b
        L_0x0254:
            java.lang.String r0 = "TB3-730X"
            boolean r0 = r10.equals(r0)
            if (r0 != 0) goto L_0x025e
            goto L_0x0099
        L_0x025e:
            r0 = 107(0x6b, float:1.5E-43)
            goto L_0x082b
        L_0x0262:
            java.lang.String r0 = "TB3-730F"
            boolean r0 = r10.equals(r0)
            if (r0 != 0) goto L_0x026c
            goto L_0x0099
        L_0x026c:
            r0 = 106(0x6a, float:1.49E-43)
            goto L_0x082b
        L_0x0270:
            java.lang.String r0 = "A7020a48"
            boolean r0 = r10.equals(r0)
            if (r0 != 0) goto L_0x027a
            goto L_0x0099
        L_0x027a:
            r0 = 105(0x69, float:1.47E-43)
            goto L_0x082b
        L_0x027e:
            java.lang.String r0 = "A7010a48"
            boolean r0 = r10.equals(r0)
            if (r0 != 0) goto L_0x0288
            goto L_0x0099
        L_0x0288:
            r0 = 104(0x68, float:1.46E-43)
            goto L_0x082b
        L_0x028c:
            java.lang.String r0 = "griffin"
            boolean r0 = r10.equals(r0)
            if (r0 != 0) goto L_0x0296
            goto L_0x0099
        L_0x0296:
            r0 = 103(0x67, float:1.44E-43)
            goto L_0x082b
        L_0x029a:
            java.lang.String r0 = "marino_f"
            boolean r0 = r10.equals(r0)
            if (r0 != 0) goto L_0x02a4
            goto L_0x0099
        L_0x02a4:
            r0 = 102(0x66, float:1.43E-43)
            goto L_0x082b
        L_0x02a8:
            java.lang.String r0 = "CPY83_I00"
            boolean r0 = r10.equals(r0)
            if (r0 != 0) goto L_0x02b2
            goto L_0x0099
        L_0x02b2:
            r0 = 101(0x65, float:1.42E-43)
            goto L_0x082b
        L_0x02b6:
            java.lang.String r0 = "A2016a40"
            boolean r0 = r10.equals(r0)
            if (r0 != 0) goto L_0x02c0
            goto L_0x0099
        L_0x02c0:
            r0 = 100
            goto L_0x082b
        L_0x02c4:
            java.lang.String r0 = "le_x6"
            boolean r0 = r10.equals(r0)
            if (r0 != 0) goto L_0x02ce
            goto L_0x0099
        L_0x02ce:
            r0 = 99
            goto L_0x082b
        L_0x02d2:
            java.lang.String r0 = "l5460"
            boolean r0 = r10.equals(r0)
            if (r0 != 0) goto L_0x02dc
            goto L_0x0099
        L_0x02dc:
            r0 = 98
            goto L_0x082b
        L_0x02e0:
            java.lang.String r0 = "i9031"
            boolean r0 = r10.equals(r0)
            if (r0 != 0) goto L_0x02ea
            goto L_0x0099
        L_0x02ea:
            r0 = 97
            goto L_0x082b
        L_0x02ee:
            java.lang.String r0 = "X3_HK"
            boolean r0 = r10.equals(r0)
            if (r0 != 0) goto L_0x02f8
            goto L_0x0099
        L_0x02f8:
            r0 = 96
            goto L_0x082b
        L_0x02fc:
            java.lang.String r0 = "V23GB"
            boolean r0 = r10.equals(r0)
            if (r0 != 0) goto L_0x0306
            goto L_0x0099
        L_0x0306:
            r0 = 95
            goto L_0x082b
        L_0x030a:
            java.lang.String r0 = "Q4310"
            boolean r0 = r10.equals(r0)
            if (r0 != 0) goto L_0x0314
            goto L_0x0099
        L_0x0314:
            r0 = 94
            goto L_0x082b
        L_0x0318:
            java.lang.String r0 = "Q4260"
            boolean r0 = r10.equals(r0)
            if (r0 != 0) goto L_0x0322
            goto L_0x0099
        L_0x0322:
            r0 = 93
            goto L_0x082b
        L_0x0326:
            java.lang.String r0 = "PRO7S"
            boolean r0 = r10.equals(r0)
            if (r0 != 0) goto L_0x0330
            goto L_0x0099
        L_0x0330:
            r0 = 92
            goto L_0x082b
        L_0x0334:
            java.lang.String r0 = "F3311"
            boolean r0 = r10.equals(r0)
            if (r0 != 0) goto L_0x033e
            goto L_0x0099
        L_0x033e:
            r0 = 91
            goto L_0x082b
        L_0x0342:
            java.lang.String r0 = "F3215"
            boolean r0 = r10.equals(r0)
            if (r0 != 0) goto L_0x034c
            goto L_0x0099
        L_0x034c:
            r0 = 90
            goto L_0x082b
        L_0x0350:
            java.lang.String r0 = "F3213"
            boolean r0 = r10.equals(r0)
            if (r0 != 0) goto L_0x035a
            goto L_0x0099
        L_0x035a:
            r0 = 89
            goto L_0x082b
        L_0x035e:
            java.lang.String r0 = "F3211"
            boolean r0 = r10.equals(r0)
            if (r0 != 0) goto L_0x0368
            goto L_0x0099
        L_0x0368:
            r0 = 88
            goto L_0x082b
        L_0x036c:
            java.lang.String r0 = "F3116"
            boolean r0 = r10.equals(r0)
            if (r0 != 0) goto L_0x0376
            goto L_0x0099
        L_0x0376:
            r0 = 87
            goto L_0x082b
        L_0x037a:
            java.lang.String r0 = "F3113"
            boolean r0 = r10.equals(r0)
            if (r0 != 0) goto L_0x0384
            goto L_0x0099
        L_0x0384:
            r0 = 86
            goto L_0x082b
        L_0x0388:
            java.lang.String r0 = "F3111"
            boolean r0 = r10.equals(r0)
            if (r0 != 0) goto L_0x0392
            goto L_0x0099
        L_0x0392:
            r0 = 85
            goto L_0x082b
        L_0x0396:
            java.lang.String r0 = "E5643"
            boolean r0 = r10.equals(r0)
            if (r0 != 0) goto L_0x03a0
            goto L_0x0099
        L_0x03a0:
            r0 = 84
            goto L_0x082b
        L_0x03a4:
            java.lang.String r0 = "A1601"
            boolean r0 = r10.equals(r0)
            if (r0 != 0) goto L_0x03ae
            goto L_0x0099
        L_0x03ae:
            r0 = 83
            goto L_0x082b
        L_0x03b2:
            java.lang.String r0 = "Aura_Note_2"
            boolean r0 = r10.equals(r0)
            if (r0 != 0) goto L_0x03bc
            goto L_0x0099
        L_0x03bc:
            r0 = 82
            goto L_0x082b
        L_0x03c0:
            java.lang.String r0 = "602LV"
            boolean r0 = r10.equals(r0)
            if (r0 != 0) goto L_0x03ca
            goto L_0x0099
        L_0x03ca:
            r0 = 81
            goto L_0x082b
        L_0x03ce:
            java.lang.String r0 = "601LV"
            boolean r0 = r10.equals(r0)
            if (r0 != 0) goto L_0x03d8
            goto L_0x0099
        L_0x03d8:
            r0 = 80
            goto L_0x082b
        L_0x03dc:
            java.lang.String r0 = "MEIZU_M5"
            boolean r0 = r10.equals(r0)
            if (r0 != 0) goto L_0x03e6
            goto L_0x0099
        L_0x03e6:
            r0 = 79
            goto L_0x082b
        L_0x03ea:
            java.lang.String r0 = "p212"
            boolean r0 = r10.equals(r0)
            if (r0 != 0) goto L_0x03f4
            goto L_0x0099
        L_0x03f4:
            r0 = 78
            goto L_0x082b
        L_0x03f8:
            java.lang.String r0 = "mido"
            boolean r0 = r10.equals(r0)
            if (r0 != 0) goto L_0x0402
            goto L_0x0099
        L_0x0402:
            r0 = 77
            goto L_0x082b
        L_0x0406:
            java.lang.String r0 = "kate"
            boolean r0 = r10.equals(r0)
            if (r0 != 0) goto L_0x0410
            goto L_0x0099
        L_0x0410:
            r0 = 76
            goto L_0x082b
        L_0x0414:
            java.lang.String r0 = "fugu"
            boolean r0 = r10.equals(r0)
            if (r0 != 0) goto L_0x041e
            goto L_0x0099
        L_0x041e:
            r0 = 75
            goto L_0x082b
        L_0x0422:
            java.lang.String r0 = "XE2X"
            boolean r0 = r10.equals(r0)
            if (r0 != 0) goto L_0x042c
            goto L_0x0099
        L_0x042c:
            r0 = 74
            goto L_0x082b
        L_0x0430:
            java.lang.String r0 = "Q427"
            boolean r0 = r10.equals(r0)
            if (r0 != 0) goto L_0x043a
            goto L_0x0099
        L_0x043a:
            r0 = 73
            goto L_0x082b
        L_0x043e:
            java.lang.String r0 = "Q350"
            boolean r0 = r10.equals(r0)
            if (r0 != 0) goto L_0x0448
            goto L_0x0099
        L_0x0448:
            r0 = 72
            goto L_0x082b
        L_0x044c:
            java.lang.String r0 = "P681"
            boolean r0 = r10.equals(r0)
            if (r0 != 0) goto L_0x0456
            goto L_0x0099
        L_0x0456:
            r0 = 71
            goto L_0x082b
        L_0x045a:
            java.lang.String r0 = "F04J"
            boolean r0 = r10.equals(r0)
            if (r0 != 0) goto L_0x0464
            goto L_0x0099
        L_0x0464:
            r0 = 70
            goto L_0x082b
        L_0x0468:
            java.lang.String r0 = "F04H"
            boolean r0 = r10.equals(r0)
            if (r0 != 0) goto L_0x0472
            goto L_0x0099
        L_0x0472:
            r0 = 69
            goto L_0x082b
        L_0x0476:
            java.lang.String r0 = "F03H"
            boolean r0 = r10.equals(r0)
            if (r0 != 0) goto L_0x0480
            goto L_0x0099
        L_0x0480:
            r0 = 68
            goto L_0x082b
        L_0x0484:
            java.lang.String r0 = "F02H"
            boolean r0 = r10.equals(r0)
            if (r0 != 0) goto L_0x048e
            goto L_0x0099
        L_0x048e:
            r0 = 67
            goto L_0x082b
        L_0x0492:
            java.lang.String r0 = "F01J"
            boolean r0 = r10.equals(r0)
            if (r0 != 0) goto L_0x049c
            goto L_0x0099
        L_0x049c:
            r0 = 66
            goto L_0x082b
        L_0x04a0:
            java.lang.String r0 = "F01H"
            boolean r0 = r10.equals(r0)
            if (r0 != 0) goto L_0x04aa
            goto L_0x0099
        L_0x04aa:
            r0 = 65
            goto L_0x082b
        L_0x04ae:
            java.lang.String r0 = "1714"
            boolean r0 = r10.equals(r0)
            if (r0 != 0) goto L_0x04b8
            goto L_0x0099
        L_0x04b8:
            r0 = 64
            goto L_0x082b
        L_0x04bc:
            java.lang.String r0 = "1713"
            boolean r0 = r10.equals(r0)
            if (r0 != 0) goto L_0x04c6
            goto L_0x0099
        L_0x04c6:
            r0 = 63
            goto L_0x082b
        L_0x04ca:
            java.lang.String r0 = "1601"
            boolean r0 = r10.equals(r0)
            if (r0 != 0) goto L_0x04d4
            goto L_0x0099
        L_0x04d4:
            r0 = 62
            goto L_0x082b
        L_0x04d8:
            java.lang.String r0 = "flo"
            boolean r0 = r10.equals(r0)
            if (r0 != 0) goto L_0x04e2
            goto L_0x0099
        L_0x04e2:
            r0 = 61
            goto L_0x082b
        L_0x04e6:
            java.lang.String r0 = "deb"
            boolean r0 = r10.equals(r0)
            if (r0 != 0) goto L_0x04f0
            goto L_0x0099
        L_0x04f0:
            r0 = 60
            goto L_0x082b
        L_0x04f4:
            java.lang.String r0 = "cv3"
            boolean r0 = r10.equals(r0)
            if (r0 != 0) goto L_0x04fe
            goto L_0x0099
        L_0x04fe:
            r0 = 59
            goto L_0x082b
        L_0x0502:
            java.lang.String r0 = "cv1"
            boolean r0 = r10.equals(r0)
            if (r0 != 0) goto L_0x050c
            goto L_0x0099
        L_0x050c:
            r0 = 58
            goto L_0x082b
        L_0x0510:
            java.lang.String r0 = "Z80"
            boolean r0 = r10.equals(r0)
            if (r0 != 0) goto L_0x051a
            goto L_0x0099
        L_0x051a:
            r0 = 57
            goto L_0x082b
        L_0x051e:
            java.lang.String r0 = "QX1"
            boolean r0 = r10.equals(r0)
            if (r0 != 0) goto L_0x0528
            goto L_0x0099
        L_0x0528:
            r0 = 56
            goto L_0x082b
        L_0x052c:
            java.lang.String r0 = "PLE"
            boolean r0 = r10.equals(r0)
            if (r0 != 0) goto L_0x0536
            goto L_0x0099
        L_0x0536:
            r0 = 55
            goto L_0x082b
        L_0x053a:
            java.lang.String r0 = "P85"
            boolean r0 = r10.equals(r0)
            if (r0 != 0) goto L_0x0544
            goto L_0x0099
        L_0x0544:
            r0 = 54
            goto L_0x082b
        L_0x0548:
            java.lang.String r0 = "MX6"
            boolean r0 = r10.equals(r0)
            if (r0 != 0) goto L_0x0552
            goto L_0x0099
        L_0x0552:
            r0 = 53
            goto L_0x082b
        L_0x0556:
            java.lang.String r0 = "M5c"
            boolean r0 = r10.equals(r0)
            if (r0 != 0) goto L_0x0560
            goto L_0x0099
        L_0x0560:
            r0 = 52
            goto L_0x082b
        L_0x0564:
            java.lang.String r0 = "M04"
            boolean r0 = r10.equals(r0)
            if (r0 != 0) goto L_0x056e
            goto L_0x0099
        L_0x056e:
            r0 = 51
            goto L_0x082b
        L_0x0572:
            java.lang.String r0 = "JGZ"
            boolean r0 = r10.equals(r0)
            if (r0 != 0) goto L_0x057c
            goto L_0x0099
        L_0x057c:
            r0 = 50
            goto L_0x082b
        L_0x0580:
            java.lang.String r0 = "mh"
            boolean r0 = r10.equals(r0)
            if (r0 != 0) goto L_0x058a
            goto L_0x0099
        L_0x058a:
            r0 = 49
            goto L_0x082b
        L_0x058e:
            java.lang.String r0 = "b5"
            boolean r0 = r10.equals(r0)
            if (r0 != 0) goto L_0x0598
            goto L_0x0099
        L_0x0598:
            r0 = 48
            goto L_0x082b
        L_0x059c:
            java.lang.String r0 = "V5"
            boolean r0 = r10.equals(r0)
            if (r0 != 0) goto L_0x05a6
            goto L_0x0099
        L_0x05a6:
            r0 = 47
            goto L_0x082b
        L_0x05aa:
            java.lang.String r0 = "V1"
            boolean r0 = r10.equals(r0)
            if (r0 != 0) goto L_0x05b4
            goto L_0x0099
        L_0x05b4:
            r0 = 46
            goto L_0x082b
        L_0x05b8:
            java.lang.String r0 = "Q5"
            boolean r0 = r10.equals(r0)
            if (r0 != 0) goto L_0x05c2
            goto L_0x0099
        L_0x05c2:
            r0 = 45
            goto L_0x082b
        L_0x05c6:
            java.lang.String r0 = "C1"
            boolean r0 = r10.equals(r0)
            if (r0 != 0) goto L_0x05d0
            goto L_0x0099
        L_0x05d0:
            r0 = 44
            goto L_0x082b
        L_0x05d4:
            java.lang.String r0 = "woods_fn"
            boolean r0 = r10.equals(r0)
            if (r0 != 0) goto L_0x05df
            goto L_0x0099
        L_0x05df:
            r0 = 43
            goto L_0x082b
        L_0x05e3:
            java.lang.String r0 = "ELUGA_A3_Pro"
            boolean r0 = r10.equals(r0)
            if (r0 != 0) goto L_0x05ed
            goto L_0x0099
        L_0x05ed:
            r0 = 42
            goto L_0x082b
        L_0x05f1:
            java.lang.String r0 = "Z12_PRO"
            boolean r0 = r10.equals(r0)
            if (r0 != 0) goto L_0x05fb
            goto L_0x0099
        L_0x05fb:
            r0 = 41
            goto L_0x082b
        L_0x05ff:
            java.lang.String r0 = "BLACK-1X"
            boolean r0 = r10.equals(r0)
            if (r0 != 0) goto L_0x0609
            goto L_0x0099
        L_0x0609:
            r0 = 40
            goto L_0x082b
        L_0x060d:
            java.lang.String r0 = "taido_row"
            boolean r0 = r10.equals(r0)
            if (r0 != 0) goto L_0x0618
            goto L_0x0099
        L_0x0618:
            r0 = 39
            goto L_0x082b
        L_0x061c:
            java.lang.String r0 = "Pixi4-7_3G"
            boolean r0 = r10.equals(r0)
            if (r0 != 0) goto L_0x0626
            goto L_0x0099
        L_0x0626:
            r0 = 38
            goto L_0x082b
        L_0x062a:
            java.lang.String r0 = "GIONEE_GBL7360"
            boolean r0 = r10.equals(r0)
            if (r0 != 0) goto L_0x0634
            goto L_0x0099
        L_0x0634:
            r0 = 37
            goto L_0x082b
        L_0x0638:
            java.lang.String r0 = "GiONEE_CBL7513"
            boolean r0 = r10.equals(r0)
            if (r0 != 0) goto L_0x0642
            goto L_0x0099
        L_0x0642:
            r0 = 36
            goto L_0x082b
        L_0x0646:
            java.lang.String r0 = "OnePlus5T"
            boolean r0 = r10.equals(r0)
            if (r0 != 0) goto L_0x0650
            goto L_0x0099
        L_0x0650:
            r0 = 35
            goto L_0x082b
        L_0x0654:
            java.lang.String r0 = "whyred"
            boolean r0 = r10.equals(r0)
            if (r0 != 0) goto L_0x065f
            goto L_0x0099
        L_0x065f:
            r0 = 34
            goto L_0x082b
        L_0x0663:
            java.lang.String r0 = "watson"
            boolean r0 = r10.equals(r0)
            if (r0 != 0) goto L_0x066e
            goto L_0x0099
        L_0x066e:
            r0 = 33
            goto L_0x082b
        L_0x0672:
            java.lang.String r0 = "SVP-DTV15"
            boolean r0 = r10.equals(r0)
            if (r0 != 0) goto L_0x067c
            goto L_0x0099
        L_0x067c:
            r0 = 32
            goto L_0x082b
        L_0x0680:
            java.lang.String r0 = "A7000-a"
            boolean r0 = r10.equals(r0)
            if (r0 != 0) goto L_0x068a
            goto L_0x0099
        L_0x068a:
            r0 = 31
            goto L_0x082b
        L_0x068e:
            java.lang.String r0 = "nicklaus_f"
            boolean r0 = r10.equals(r0)
            if (r0 != 0) goto L_0x0698
            goto L_0x0099
        L_0x0698:
            r0 = 30
            goto L_0x082b
        L_0x069c:
            java.lang.String r0 = "tcl_eu"
            boolean r0 = r10.equals(r0)
            if (r0 != 0) goto L_0x06a7
            goto L_0x0099
        L_0x06a7:
            r0 = 29
            goto L_0x082b
        L_0x06ab:
            java.lang.String r0 = "ELUGA_Ray_X"
            boolean r0 = r10.equals(r0)
            if (r0 != 0) goto L_0x06b5
            goto L_0x0099
        L_0x06b5:
            r0 = r11
            goto L_0x082b
        L_0x06b8:
            java.lang.String r0 = "s905x018"
            boolean r0 = r10.equals(r0)
            if (r0 != 0) goto L_0x06c2
            goto L_0x0099
        L_0x06c2:
            r0 = r1
            goto L_0x082b
        L_0x06c5:
            java.lang.String r1 = "A10-70L"
            boolean r1 = r10.equals(r1)
            if (r1 != 0) goto L_0x082b
            goto L_0x0099
        L_0x06cf:
            java.lang.String r0 = "A10-70F"
            boolean r0 = r10.equals(r0)
            if (r0 != 0) goto L_0x06d9
            goto L_0x0099
        L_0x06d9:
            r0 = 25
            goto L_0x082b
        L_0x06dd:
            java.lang.String r0 = "namath"
            boolean r0 = r10.equals(r0)
            if (r0 != 0) goto L_0x06e7
            goto L_0x0099
        L_0x06e7:
            r0 = 24
            goto L_0x082b
        L_0x06eb:
            java.lang.String r0 = "Slate_Pro"
            boolean r0 = r10.equals(r0)
            if (r0 != 0) goto L_0x06f5
            goto L_0x0099
        L_0x06f5:
            r0 = 23
            goto L_0x082b
        L_0x06f9:
            java.lang.String r0 = "iris60"
            boolean r0 = r10.equals(r0)
            if (r0 != 0) goto L_0x0703
            goto L_0x0099
        L_0x0703:
            r0 = 22
            goto L_0x082b
        L_0x0707:
            java.lang.String r0 = "BRAVIA_ATV2"
            boolean r0 = r10.equals(r0)
            if (r0 != 0) goto L_0x0711
            goto L_0x0099
        L_0x0711:
            r0 = 21
            goto L_0x082b
        L_0x0715:
            java.lang.String r0 = "GiONEE_GBL7319"
            boolean r0 = r10.equals(r0)
            if (r0 != 0) goto L_0x071f
            goto L_0x0099
        L_0x071f:
            r0 = 20
            goto L_0x082b
        L_0x0723:
            java.lang.String r0 = "panell_dt"
            boolean r0 = r10.equals(r0)
            if (r0 != 0) goto L_0x072d
            goto L_0x0099
        L_0x072d:
            r0 = 19
            goto L_0x082b
        L_0x0731:
            java.lang.String r0 = "panell_ds"
            boolean r0 = r10.equals(r0)
            if (r0 != 0) goto L_0x073b
            goto L_0x0099
        L_0x073b:
            r0 = 18
            goto L_0x082b
        L_0x073f:
            java.lang.String r0 = "panell_dl"
            boolean r0 = r10.equals(r0)
            if (r0 != 0) goto L_0x0749
            goto L_0x0099
        L_0x0749:
            r0 = 17
            goto L_0x082b
        L_0x074d:
            java.lang.String r0 = "vernee_M5"
            boolean r0 = r10.equals(r0)
            if (r0 != 0) goto L_0x0758
            goto L_0x0099
        L_0x0758:
            r0 = 16
            goto L_0x082b
        L_0x075c:
            java.lang.String r0 = "pacificrim"
            boolean r0 = r10.equals(r0)
            if (r0 != 0) goto L_0x0766
            goto L_0x0099
        L_0x0766:
            r0 = 15
            goto L_0x082b
        L_0x076a:
            java.lang.String r0 = "Phantom6"
            boolean r0 = r10.equals(r0)
            if (r0 != 0) goto L_0x0774
            goto L_0x0099
        L_0x0774:
            r0 = 14
            goto L_0x082b
        L_0x0778:
            java.lang.String r0 = "ComioS1"
            boolean r0 = r10.equals(r0)
            if (r0 != 0) goto L_0x0782
            goto L_0x0099
        L_0x0782:
            r0 = 13
            goto L_0x082b
        L_0x0786:
            java.lang.String r0 = "XT1663"
            boolean r0 = r10.equals(r0)
            if (r0 != 0) goto L_0x0790
            goto L_0x0099
        L_0x0790:
            r0 = 12
            goto L_0x082b
        L_0x0794:
            java.lang.String r0 = "RAIJIN"
            boolean r0 = r10.equals(r0)
            if (r0 != 0) goto L_0x079e
            goto L_0x0099
        L_0x079e:
            r0 = 11
            goto L_0x082b
        L_0x07a2:
            java.lang.String r0 = "AquaPowerM"
            boolean r0 = r10.equals(r0)
            if (r0 != 0) goto L_0x07ac
            goto L_0x0099
        L_0x07ac:
            r0 = 10
            goto L_0x082b
        L_0x07b0:
            java.lang.String r0 = "PGN611"
            boolean r0 = r10.equals(r0)
            if (r0 != 0) goto L_0x07ba
            goto L_0x0099
        L_0x07ba:
            r0 = 9
            goto L_0x082b
        L_0x07be:
            java.lang.String r0 = "PGN610"
            boolean r0 = r10.equals(r0)
            if (r0 != 0) goto L_0x07c8
            goto L_0x0099
        L_0x07c8:
            r0 = 8
            goto L_0x082b
        L_0x07cc:
            java.lang.String r0 = "PGN528"
            boolean r0 = r10.equals(r0)
            if (r0 != 0) goto L_0x07d6
            goto L_0x0099
        L_0x07d6:
            r0 = r2
            goto L_0x082b
        L_0x07d8:
            java.lang.String r0 = "NX573J"
            boolean r0 = r10.equals(r0)
            if (r0 != 0) goto L_0x07e2
            goto L_0x0099
        L_0x07e2:
            r0 = r3
            goto L_0x082b
        L_0x07e4:
            java.lang.String r0 = "NX541J"
            boolean r0 = r10.equals(r0)
            if (r0 != 0) goto L_0x07ee
            goto L_0x0099
        L_0x07ee:
            r0 = r4
            goto L_0x082b
        L_0x07f0:
            java.lang.String r0 = "CP8676_I02"
            boolean r0 = r10.equals(r0)
            if (r0 != 0) goto L_0x07fa
            goto L_0x0099
        L_0x07fa:
            r0 = r5
            goto L_0x082b
        L_0x07fc:
            java.lang.String r0 = "K50a40"
            boolean r0 = r10.equals(r0)
            if (r0 != 0) goto L_0x0806
            goto L_0x0099
        L_0x0806:
            r0 = r6
            goto L_0x082b
        L_0x0808:
            java.lang.String r0 = "GIONEE_SWW1631"
            boolean r0 = r10.equals(r0)
            if (r0 != 0) goto L_0x0812
            goto L_0x0099
        L_0x0812:
            r0 = r7
            goto L_0x082b
        L_0x0814:
            java.lang.String r0 = "GIONEE_SWW1627"
            boolean r0 = r10.equals(r0)
            if (r0 != 0) goto L_0x081e
            goto L_0x0099
        L_0x081e:
            r0 = r12
            goto L_0x082b
        L_0x0820:
            java.lang.String r0 = "GIONEE_SWW1609"
            boolean r0 = r10.equals(r0)
            if (r0 != 0) goto L_0x082a
            goto L_0x0099
        L_0x082a:
            r0 = r9
        L_0x082b:
            switch(r0) {
                case 0: goto L_0x085e;
                case 1: goto L_0x085e;
                case 2: goto L_0x085e;
                case 3: goto L_0x085e;
                case 4: goto L_0x085e;
                case 5: goto L_0x085e;
                case 6: goto L_0x085e;
                case 7: goto L_0x085e;
                case 8: goto L_0x085e;
                case 9: goto L_0x085e;
                case 10: goto L_0x085e;
                case 11: goto L_0x085e;
                case 12: goto L_0x085e;
                case 13: goto L_0x085e;
                case 14: goto L_0x085e;
                case 15: goto L_0x085e;
                case 16: goto L_0x085e;
                case 17: goto L_0x085e;
                case 18: goto L_0x085e;
                case 19: goto L_0x085e;
                case 20: goto L_0x085e;
                case 21: goto L_0x085e;
                case 22: goto L_0x085e;
                case 23: goto L_0x085e;
                case 24: goto L_0x085e;
                case 25: goto L_0x085e;
                case 26: goto L_0x085e;
                case 27: goto L_0x085e;
                case 28: goto L_0x085e;
                case 29: goto L_0x085e;
                case 30: goto L_0x085e;
                case 31: goto L_0x085e;
                case 32: goto L_0x085e;
                case 33: goto L_0x085e;
                case 34: goto L_0x085e;
                case 35: goto L_0x085e;
                case 36: goto L_0x085e;
                case 37: goto L_0x085e;
                case 38: goto L_0x085e;
                case 39: goto L_0x085e;
                case 40: goto L_0x085e;
                case 41: goto L_0x085e;
                case 42: goto L_0x085e;
                case 43: goto L_0x085e;
                case 44: goto L_0x085e;
                case 45: goto L_0x085e;
                case 46: goto L_0x085e;
                case 47: goto L_0x085e;
                case 48: goto L_0x085e;
                case 49: goto L_0x085e;
                case 50: goto L_0x085e;
                case 51: goto L_0x085e;
                case 52: goto L_0x085e;
                case 53: goto L_0x085e;
                case 54: goto L_0x085e;
                case 55: goto L_0x085e;
                case 56: goto L_0x085e;
                case 57: goto L_0x085e;
                case 58: goto L_0x085e;
                case 59: goto L_0x085e;
                case 60: goto L_0x085e;
                case 61: goto L_0x085e;
                case 62: goto L_0x085e;
                case 63: goto L_0x085e;
                case 64: goto L_0x085e;
                case 65: goto L_0x085e;
                case 66: goto L_0x085e;
                case 67: goto L_0x085e;
                case 68: goto L_0x085e;
                case 69: goto L_0x085e;
                case 70: goto L_0x085e;
                case 71: goto L_0x085e;
                case 72: goto L_0x085e;
                case 73: goto L_0x085e;
                case 74: goto L_0x085e;
                case 75: goto L_0x085e;
                case 76: goto L_0x085e;
                case 77: goto L_0x085e;
                case 78: goto L_0x085e;
                case 79: goto L_0x085e;
                case 80: goto L_0x085e;
                case 81: goto L_0x085e;
                case 82: goto L_0x085e;
                case 83: goto L_0x085e;
                case 84: goto L_0x085e;
                case 85: goto L_0x085e;
                case 86: goto L_0x085e;
                case 87: goto L_0x085e;
                case 88: goto L_0x085e;
                case 89: goto L_0x085e;
                case 90: goto L_0x085e;
                case 91: goto L_0x085e;
                case 92: goto L_0x085e;
                case 93: goto L_0x085e;
                case 94: goto L_0x085e;
                case 95: goto L_0x085e;
                case 96: goto L_0x085e;
                case 97: goto L_0x085e;
                case 98: goto L_0x085e;
                case 99: goto L_0x085e;
                case 100: goto L_0x085e;
                case 101: goto L_0x085e;
                case 102: goto L_0x085e;
                case 103: goto L_0x085e;
                case 104: goto L_0x085e;
                case 105: goto L_0x085e;
                case 106: goto L_0x085e;
                case 107: goto L_0x085e;
                case 108: goto L_0x085e;
                case 109: goto L_0x085e;
                case 110: goto L_0x085e;
                case 111: goto L_0x085e;
                case 112: goto L_0x085e;
                case 113: goto L_0x085e;
                case 114: goto L_0x085e;
                case 115: goto L_0x085e;
                case 116: goto L_0x085e;
                case 117: goto L_0x085e;
                case 118: goto L_0x085e;
                case 119: goto L_0x085e;
                case 120: goto L_0x085e;
                case 121: goto L_0x085e;
                case 122: goto L_0x085e;
                case 123: goto L_0x085e;
                case 124: goto L_0x085e;
                case 125: goto L_0x085e;
                case 126: goto L_0x085e;
                case 127: goto L_0x085e;
                case 128: goto L_0x085e;
                case 129: goto L_0x085e;
                case 130: goto L_0x085e;
                case 131: goto L_0x085e;
                case 132: goto L_0x085e;
                case 133: goto L_0x085e;
                case 134: goto L_0x085e;
                case 135: goto L_0x085e;
                case 136: goto L_0x085e;
                case 137: goto L_0x085e;
                case 138: goto L_0x085e;
                case 139: goto L_0x085e;
                default: goto L_0x082e;
            }
        L_0x082e:
            java.lang.String r0 = com.appsamurai.storyly.exoplayer2.common.util.Util.MODEL
            r0.getClass()
            int r1 = r0.hashCode()
            switch(r1) {
                case -594534941: goto L_0x0850;
                case 2006354: goto L_0x0845;
                case 2006367: goto L_0x083c;
                default: goto L_0x083a;
            }
        L_0x083a:
            r7 = r8
            goto L_0x085a
        L_0x083c:
            java.lang.String r1 = "AFTN"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x085a
            goto L_0x083a
        L_0x0845:
            java.lang.String r1 = "AFTA"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x084e
            goto L_0x083a
        L_0x084e:
            r7 = r12
            goto L_0x085a
        L_0x0850:
            java.lang.String r1 = "JSN-L21"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0859
            goto L_0x083a
        L_0x0859:
            r7 = r9
        L_0x085a:
            switch(r7) {
                case 0: goto L_0x085e;
                case 1: goto L_0x085e;
                case 2: goto L_0x085e;
                default: goto L_0x085d;
            }
        L_0x085d:
            goto L_0x085f
        L_0x085e:
            return r12
        L_0x085f:
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.exoplayer2.core.video.MediaCodecVideoRenderer.evaluateDeviceNeedsSetOutputSurfaceWorkaround():boolean");
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0082, code lost:
        if (r9.equals(com.appsamurai.storyly.exoplayer2.common.util.MimeTypes.VIDEO_AV1) == false) goto L_0x0043;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0026, code lost:
        r12 = ((java.lang.Integer) r12.first).intValue();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int getCodecMaxInputSize(com.appsamurai.storyly.exoplayer2.core.mediacodec.MediaCodecInfo r11, com.appsamurai.storyly.exoplayer2.common.Format r12) {
        /*
            r0 = 4
            r1 = 3
            java.lang.String r2 = "video/hevc"
            java.lang.String r3 = "video/avc"
            r4 = 1
            r5 = 2
            int r6 = r12.width
            int r7 = r12.height
            r8 = -1
            if (r6 == r8) goto L_0x00d7
            if (r7 != r8) goto L_0x0015
            goto L_0x00d7
        L_0x0015:
            java.lang.String r9 = r12.sampleMimeType
            java.lang.String r10 = "video/dolby-vision"
            boolean r10 = r10.equals(r9)
            if (r10 == 0) goto L_0x0039
            android.util.Pair r12 = com.appsamurai.storyly.exoplayer2.core.mediacodec.MediaCodecUtil.getCodecProfileAndLevel(r12)
            if (r12 == 0) goto L_0x0038
            java.lang.Object r12 = r12.first
            java.lang.Integer r12 = (java.lang.Integer) r12
            int r12 = r12.intValue()
            r9 = 512(0x200, float:7.175E-43)
            if (r12 == r9) goto L_0x0036
            if (r12 == r4) goto L_0x0036
            if (r12 != r5) goto L_0x0038
        L_0x0036:
            r9 = r3
            goto L_0x0039
        L_0x0038:
            r9 = r2
        L_0x0039:
            r9.getClass()
            int r12 = r9.hashCode()
            switch(r12) {
                case -1664118616: goto L_0x0085;
                case -1662735862: goto L_0x007b;
                case -1662541442: goto L_0x0072;
                case 1187890754: goto L_0x0066;
                case 1331836730: goto L_0x005d;
                case 1599127256: goto L_0x0051;
                case 1599127257: goto L_0x0045;
                default: goto L_0x0043;
            }
        L_0x0043:
            r4 = r8
            goto L_0x0090
        L_0x0045:
            java.lang.String r12 = "video/x-vnd.on2.vp9"
            boolean r12 = r9.equals(r12)
            if (r12 != 0) goto L_0x004f
            goto L_0x0043
        L_0x004f:
            r4 = 6
            goto L_0x0090
        L_0x0051:
            java.lang.String r12 = "video/x-vnd.on2.vp8"
            boolean r12 = r9.equals(r12)
            if (r12 != 0) goto L_0x005b
            goto L_0x0043
        L_0x005b:
            r4 = 5
            goto L_0x0090
        L_0x005d:
            boolean r12 = r9.equals(r3)
            if (r12 != 0) goto L_0x0064
            goto L_0x0043
        L_0x0064:
            r4 = r0
            goto L_0x0090
        L_0x0066:
            java.lang.String r12 = "video/mp4v-es"
            boolean r12 = r9.equals(r12)
            if (r12 != 0) goto L_0x0070
            goto L_0x0043
        L_0x0070:
            r4 = r1
            goto L_0x0090
        L_0x0072:
            boolean r12 = r9.equals(r2)
            if (r12 != 0) goto L_0x0079
            goto L_0x0043
        L_0x0079:
            r4 = r5
            goto L_0x0090
        L_0x007b:
            java.lang.String r12 = "video/av01"
            boolean r12 = r9.equals(r12)
            if (r12 != 0) goto L_0x0090
            goto L_0x0043
        L_0x0085:
            java.lang.String r12 = "video/3gpp"
            boolean r12 = r9.equals(r12)
            if (r12 != 0) goto L_0x008f
            goto L_0x0043
        L_0x008f:
            r4 = 0
        L_0x0090:
            switch(r4) {
                case 0: goto L_0x00d0;
                case 1: goto L_0x00d0;
                case 2: goto L_0x00cd;
                case 3: goto L_0x00d0;
                case 4: goto L_0x0094;
                case 5: goto L_0x00d0;
                case 6: goto L_0x00cd;
                default: goto L_0x0093;
            }
        L_0x0093:
            return r8
        L_0x0094:
            java.lang.String r12 = com.appsamurai.storyly.exoplayer2.common.util.Util.MODEL
            java.lang.String r0 = "BRAVIA 4K 2015"
            boolean r0 = r0.equals(r12)
            if (r0 != 0) goto L_0x00cc
            java.lang.String r0 = "Amazon"
            java.lang.String r2 = com.appsamurai.storyly.exoplayer2.common.util.Util.MANUFACTURER
            boolean r0 = r0.equals(r2)
            if (r0 == 0) goto L_0x00bd
            java.lang.String r0 = "KFSOWI"
            boolean r0 = r0.equals(r12)
            if (r0 != 0) goto L_0x00cc
            java.lang.String r0 = "AFTS"
            boolean r12 = r0.equals(r12)
            if (r12 == 0) goto L_0x00bd
            boolean r11 = r11.secure
            if (r11 == 0) goto L_0x00bd
            goto L_0x00cc
        L_0x00bd:
            r11 = 16
            int r12 = com.appsamurai.storyly.exoplayer2.common.util.Util.ceilDivide((int) r6, (int) r11)
            int r11 = com.appsamurai.storyly.exoplayer2.common.util.Util.ceilDivide((int) r7, (int) r11)
            int r11 = r11 * r12
            int r11 = r11 * 256
        L_0x00ca:
            r0 = r5
            goto L_0x00d3
        L_0x00cc:
            return r8
        L_0x00cd:
            int r11 = r6 * r7
            goto L_0x00d3
        L_0x00d0:
            int r11 = r6 * r7
            goto L_0x00ca
        L_0x00d3:
            int r11 = r11 * r1
            int r0 = r0 * r5
            int r11 = r11 / r0
            return r11
        L_0x00d7:
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.exoplayer2.core.video.MediaCodecVideoRenderer.getCodecMaxInputSize(com.appsamurai.storyly.exoplayer2.core.mediacodec.MediaCodecInfo, com.appsamurai.storyly.exoplayer2.common.Format):int");
    }

    private static Point getCodecMaxSize(MediaCodecInfo mediaCodecInfo, Format format) {
        int i3 = format.height;
        int i4 = format.width;
        boolean z2 = i3 > i4;
        int i5 = z2 ? i3 : i4;
        if (z2) {
            i3 = i4;
        }
        float f2 = ((float) i3) / ((float) i5);
        for (int i6 : STANDARD_LONG_EDGE_VIDEO_PX) {
            int i7 = (int) (((float) i6) * f2);
            if (i6 <= i5 || i7 <= i3) {
                break;
            }
            if (Util.SDK_INT >= 21) {
                int i8 = z2 ? i7 : i6;
                if (!z2) {
                    i6 = i7;
                }
                Point alignVideoSizeV21 = mediaCodecInfo.alignVideoSizeV21(i8, i6);
                if (mediaCodecInfo.isVideoSizeAndRateSupportedV21(alignVideoSizeV21.x, alignVideoSizeV21.y, (double) format.frameRate)) {
                    return alignVideoSizeV21;
                }
            } else {
                try {
                    int ceilDivide = Util.ceilDivide(i6, 16) * 16;
                    int ceilDivide2 = Util.ceilDivide(i7, 16) * 16;
                    if (ceilDivide * ceilDivide2 <= MediaCodecUtil.maxH264DecodableFrameSize()) {
                        int i9 = z2 ? ceilDivide2 : ceilDivide;
                        if (!z2) {
                            ceilDivide = ceilDivide2;
                        }
                        return new Point(i9, ceilDivide);
                    }
                } catch (MediaCodecUtil.DecoderQueryException unused) {
                }
            }
        }
        return null;
    }

    public static int getMaxInputSize(MediaCodecInfo mediaCodecInfo, Format format) {
        if (format.maxInputSize == -1) {
            return getCodecMaxInputSize(mediaCodecInfo, format);
        }
        int size = format.initializationData.size();
        int i3 = 0;
        for (int i4 = 0; i4 < size; i4++) {
            i3 += format.initializationData.get(i4).length;
        }
        return format.maxInputSize + i3;
    }

    private static boolean isBufferLate(long j2) {
        return j2 < -30000;
    }

    private static boolean isBufferVeryLate(long j2) {
        return j2 < -500000;
    }

    private void maybeNotifyDroppedFrames() {
        if (this.droppedFrames > 0) {
            long elapsedRealtime = SystemClock.elapsedRealtime();
            this.eventDispatcher.droppedFrames(this.droppedFrames, elapsedRealtime - this.droppedFrameAccumulationStartTimeMs);
            this.droppedFrames = 0;
            this.droppedFrameAccumulationStartTimeMs = elapsedRealtime;
        }
    }

    private void maybeNotifyVideoFrameProcessingOffset() {
        int i3 = this.videoFrameProcessingOffsetCount;
        if (i3 != 0) {
            this.eventDispatcher.reportVideoFrameProcessingOffset(this.totalVideoFrameProcessingOffsetUs, i3);
            this.totalVideoFrameProcessingOffsetUs = 0;
            this.videoFrameProcessingOffsetCount = 0;
        }
    }

    private void maybeNotifyVideoSizeChanged() {
        int i3 = this.currentWidth;
        if (i3 != -1 || this.currentHeight != -1) {
            VideoSize videoSize = this.reportedVideoSize;
            if (videoSize == null || videoSize.width != i3 || videoSize.height != this.currentHeight || videoSize.unappliedRotationDegrees != this.currentUnappliedRotationDegrees || videoSize.pixelWidthHeightRatio != this.currentPixelWidthHeightRatio) {
                VideoSize videoSize2 = new VideoSize(this.currentWidth, this.currentHeight, this.currentUnappliedRotationDegrees, this.currentPixelWidthHeightRatio);
                this.reportedVideoSize = videoSize2;
                this.eventDispatcher.videoSizeChanged(videoSize2);
            }
        }
    }

    private void maybeRenotifyRenderedFirstFrame() {
        if (this.haveReportedFirstFrameRenderedForCurrentSurface) {
            this.eventDispatcher.renderedFirstFrame(this.surface);
        }
    }

    private void maybeRenotifyVideoSizeChanged() {
        VideoSize videoSize = this.reportedVideoSize;
        if (videoSize != null) {
            this.eventDispatcher.videoSizeChanged(videoSize);
        }
    }

    private void notifyFrameMetadataListener(long j2, long j3, Format format) {
        VideoFrameMetadataListener videoFrameMetadataListener = this.frameMetadataListener;
        if (videoFrameMetadataListener != null) {
            videoFrameMetadataListener.onVideoFrameAboutToBeRendered(j2, j3, format, getCodecOutputMediaFormat());
        }
    }

    /* access modifiers changed from: private */
    public void onProcessedTunneledEndOfStream() {
        setPendingOutputEndOfStream();
    }

    @RequiresApi(17)
    private void releasePlaceholderSurface() {
        Surface surface2 = this.surface;
        PlaceholderSurface placeholderSurface2 = this.placeholderSurface;
        if (surface2 == placeholderSurface2) {
            this.surface = null;
        }
        placeholderSurface2.release();
        this.placeholderSurface = null;
    }

    @RequiresApi(29)
    private static void setHdr10PlusInfoV29(MediaCodecAdapter mediaCodecAdapter, byte[] bArr) {
        Bundle bundle = new Bundle();
        bundle.putByteArray("hdr10-plus-info", bArr);
        mediaCodecAdapter.setParameters(bundle);
    }

    private void setJoiningDeadlineMs() {
        this.joiningDeadlineMs = this.allowedJoiningTimeMs > 0 ? SystemClock.elapsedRealtime() + this.allowedJoiningTimeMs : C.TIME_UNSET;
    }

    /* JADX WARNING: Failed to insert additional move for type inference */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void setOutput(@androidx.annotation.Nullable java.lang.Object r5) throws com.appsamurai.storyly.exoplayer2.core.ExoPlaybackException {
        /*
            r4 = this;
            boolean r0 = r5 instanceof android.view.Surface
            if (r0 == 0) goto L_0x0007
            android.view.Surface r5 = (android.view.Surface) r5
            goto L_0x0008
        L_0x0007:
            r5 = 0
        L_0x0008:
            if (r5 != 0) goto L_0x0026
            com.appsamurai.storyly.exoplayer2.core.video.PlaceholderSurface r0 = r4.placeholderSurface
            if (r0 == 0) goto L_0x0010
            r5 = r0
            goto L_0x0026
        L_0x0010:
            com.appsamurai.storyly.exoplayer2.core.mediacodec.MediaCodecInfo r0 = r4.getCodecInfo()
            if (r0 == 0) goto L_0x0026
            boolean r1 = r4.shouldUseDummySurface(r0)
            if (r1 == 0) goto L_0x0026
            android.content.Context r5 = r4.context
            boolean r0 = r0.secure
            com.appsamurai.storyly.exoplayer2.core.video.PlaceholderSurface r5 = com.appsamurai.storyly.exoplayer2.core.video.PlaceholderSurface.newInstanceV17(r5, r0)
            r4.placeholderSurface = r5
        L_0x0026:
            android.view.Surface r0 = r4.surface
            if (r0 == r5) goto L_0x006e
            r4.surface = r5
            com.appsamurai.storyly.exoplayer2.core.video.VideoFrameReleaseHelper r0 = r4.frameReleaseHelper
            r0.onSurfaceChanged(r5)
            r0 = 0
            r4.haveReportedFirstFrameRenderedForCurrentSurface = r0
            int r0 = r4.getState()
            com.appsamurai.storyly.exoplayer2.core.mediacodec.MediaCodecAdapter r1 = r4.getCodec()
            if (r1 == 0) goto L_0x0054
            int r2 = com.appsamurai.storyly.exoplayer2.common.util.Util.SDK_INT
            r3 = 23
            if (r2 < r3) goto L_0x004e
            if (r5 == 0) goto L_0x004e
            boolean r2 = r4.codecNeedsSetOutputSurfaceWorkaround
            if (r2 != 0) goto L_0x004e
            r4.setOutputSurfaceV23(r1, r5)
            goto L_0x0054
        L_0x004e:
            r4.releaseCodec()
            r4.maybeInitCodecOrBypass()
        L_0x0054:
            if (r5 == 0) goto L_0x0067
            com.appsamurai.storyly.exoplayer2.core.video.PlaceholderSurface r1 = r4.placeholderSurface
            if (r5 == r1) goto L_0x0067
            r4.maybeRenotifyVideoSizeChanged()
            r4.clearRenderedFirstFrame()
            r5 = 2
            if (r0 != r5) goto L_0x007a
            r4.setJoiningDeadlineMs()
            goto L_0x007a
        L_0x0067:
            r4.clearReportedVideoSize()
            r4.clearRenderedFirstFrame()
            goto L_0x007a
        L_0x006e:
            if (r5 == 0) goto L_0x007a
            com.appsamurai.storyly.exoplayer2.core.video.PlaceholderSurface r0 = r4.placeholderSurface
            if (r5 == r0) goto L_0x007a
            r4.maybeRenotifyVideoSizeChanged()
            r4.maybeRenotifyRenderedFirstFrame()
        L_0x007a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.exoplayer2.core.video.MediaCodecVideoRenderer.setOutput(java.lang.Object):void");
    }

    private boolean shouldUseDummySurface(MediaCodecInfo mediaCodecInfo) {
        return Util.SDK_INT >= 23 && !this.tunneling && !codecNeedsSetOutputSurfaceWorkaround(mediaCodecInfo.name) && (!mediaCodecInfo.secure || PlaceholderSurface.isSecureSupported(this.context));
    }

    public DecoderReuseEvaluation canReuseCodec(MediaCodecInfo mediaCodecInfo, Format format, Format format2) {
        DecoderReuseEvaluation canReuseCodec = mediaCodecInfo.canReuseCodec(format, format2);
        int i3 = canReuseCodec.discardReasons;
        int i4 = format2.width;
        CodecMaxValues codecMaxValues2 = this.codecMaxValues;
        if (i4 > codecMaxValues2.width || format2.height > codecMaxValues2.height) {
            i3 |= 256;
        }
        if (getMaxInputSize(mediaCodecInfo, format2) > this.codecMaxValues.inputSize) {
            i3 |= 64;
        }
        int i5 = i3;
        return new DecoderReuseEvaluation(mediaCodecInfo.name, format, format2, i5 != 0 ? 0 : canReuseCodec.result, i5);
    }

    public boolean codecNeedsSetOutputSurfaceWorkaround(String str) {
        if (str.startsWith("OMX.google")) {
            return false;
        }
        synchronized (MediaCodecVideoRenderer.class) {
            try {
                if (!evaluatedDeviceNeedsSetOutputSurfaceWorkaround) {
                    deviceNeedsSetOutputSurfaceWorkaround = evaluateDeviceNeedsSetOutputSurfaceWorkaround();
                    evaluatedDeviceNeedsSetOutputSurfaceWorkaround = true;
                }
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
        return deviceNeedsSetOutputSurfaceWorkaround;
    }

    public MediaCodecDecoderException createDecoderException(Throwable th, @Nullable MediaCodecInfo mediaCodecInfo) {
        return new MediaCodecVideoDecoderException(th, mediaCodecInfo, this.surface);
    }

    public void dropOutputBuffer(MediaCodecAdapter mediaCodecAdapter, int i3, long j2) {
        TraceUtil.beginSection("dropVideoBuffer");
        mediaCodecAdapter.releaseOutputBuffer(i3, false);
        TraceUtil.endSection();
        updateDroppedBufferCounters(0, 1);
    }

    public CodecMaxValues getCodecMaxValues(MediaCodecInfo mediaCodecInfo, Format format, Format[] formatArr) {
        int codecMaxInputSize;
        int i3 = format.width;
        int i4 = format.height;
        int maxInputSize = getMaxInputSize(mediaCodecInfo, format);
        if (formatArr.length == 1) {
            if (!(maxInputSize == -1 || (codecMaxInputSize = getCodecMaxInputSize(mediaCodecInfo, format)) == -1)) {
                maxInputSize = Math.min((int) (((float) maxInputSize) * INITIAL_FORMAT_MAX_INPUT_SIZE_SCALE_FACTOR), codecMaxInputSize);
            }
            return new CodecMaxValues(i3, i4, maxInputSize);
        }
        int length = formatArr.length;
        boolean z2 = false;
        for (int i5 = 0; i5 < length; i5++) {
            Format format2 = formatArr[i5];
            if (format.colorInfo != null && format2.colorInfo == null) {
                format2 = format2.buildUpon().setColorInfo(format.colorInfo).build();
            }
            if (mediaCodecInfo.canReuseCodec(format, format2).result != 0) {
                int i6 = format2.width;
                z2 |= i6 == -1 || format2.height == -1;
                i3 = Math.max(i3, i6);
                i4 = Math.max(i4, format2.height);
                maxInputSize = Math.max(maxInputSize, getMaxInputSize(mediaCodecInfo, format2));
            }
        }
        if (z2) {
            Log.w(TAG, "Resolutions unknown. Codec max resolution: " + i3 + "x" + i4);
            Point codecMaxSize = getCodecMaxSize(mediaCodecInfo, format);
            if (codecMaxSize != null) {
                i3 = Math.max(i3, codecMaxSize.x);
                i4 = Math.max(i4, codecMaxSize.y);
                maxInputSize = Math.max(maxInputSize, getCodecMaxInputSize(mediaCodecInfo, format.buildUpon().setWidth(i3).setHeight(i4).build()));
                Log.w(TAG, "Codec max resolution adjusted to: " + i3 + "x" + i4);
            }
        }
        return new CodecMaxValues(i3, i4, maxInputSize);
    }

    public boolean getCodecNeedsEosPropagation() {
        return this.tunneling && Util.SDK_INT < 23;
    }

    public float getCodecOperatingRateV23(float f2, Format format, Format[] formatArr) {
        float f3 = -1.0f;
        for (Format format2 : formatArr) {
            float f4 = format2.frameRate;
            if (f4 != -1.0f) {
                f3 = Math.max(f3, f4);
            }
        }
        if (f3 == -1.0f) {
            return -1.0f;
        }
        return f3 * f2;
    }

    public List<MediaCodecInfo> getDecoderInfos(MediaCodecSelector mediaCodecSelector, Format format, boolean z2) throws MediaCodecUtil.DecoderQueryException {
        return MediaCodecUtil.getDecoderInfosSortedByFormatSupport(getDecoderInfos(mediaCodecSelector, format, z2, this.tunneling), format);
    }

    @TargetApi(17)
    public MediaCodecAdapter.Configuration getMediaCodecConfiguration(MediaCodecInfo mediaCodecInfo, Format format, @Nullable MediaCrypto mediaCrypto, float f2) {
        PlaceholderSurface placeholderSurface2 = this.placeholderSurface;
        if (!(placeholderSurface2 == null || placeholderSurface2.secure == mediaCodecInfo.secure)) {
            releasePlaceholderSurface();
        }
        String str = mediaCodecInfo.codecMimeType;
        CodecMaxValues codecMaxValues2 = getCodecMaxValues(mediaCodecInfo, format, getStreamFormats());
        this.codecMaxValues = codecMaxValues2;
        MediaFormat mediaFormat = getMediaFormat(format, str, codecMaxValues2, f2, this.deviceNeedsNoPostProcessWorkaround, this.tunneling ? this.tunnelingAudioSessionId : 0);
        if (this.surface == null) {
            if (shouldUseDummySurface(mediaCodecInfo)) {
                if (this.placeholderSurface == null) {
                    this.placeholderSurface = PlaceholderSurface.newInstanceV17(this.context, mediaCodecInfo.secure);
                }
                this.surface = this.placeholderSurface;
            } else {
                throw new IllegalStateException();
            }
        }
        return MediaCodecAdapter.Configuration.createForVideoDecoding(mediaCodecInfo, mediaFormat, format, this.surface, mediaCrypto);
    }

    @SuppressLint({"InlinedApi"})
    @TargetApi(21)
    public MediaFormat getMediaFormat(Format format, String str, CodecMaxValues codecMaxValues2, float f2, boolean z2, int i3) {
        Pair<Integer, Integer> codecProfileAndLevel;
        MediaFormat mediaFormat = new MediaFormat();
        mediaFormat.setString("mime", str);
        mediaFormat.setInteger("width", format.width);
        mediaFormat.setInteger("height", format.height);
        MediaFormatUtil.setCsdBuffers(mediaFormat, format.initializationData);
        MediaFormatUtil.maybeSetFloat(mediaFormat, "frame-rate", format.frameRate);
        MediaFormatUtil.maybeSetInteger(mediaFormat, "rotation-degrees", format.rotationDegrees);
        MediaFormatUtil.maybeSetColorInfo(mediaFormat, format.colorInfo);
        if (MimeTypes.VIDEO_DOLBY_VISION.equals(format.sampleMimeType) && (codecProfileAndLevel = MediaCodecUtil.getCodecProfileAndLevel(format)) != null) {
            MediaFormatUtil.maybeSetInteger(mediaFormat, Scopes.PROFILE, ((Integer) codecProfileAndLevel.first).intValue());
        }
        mediaFormat.setInteger("max-width", codecMaxValues2.width);
        mediaFormat.setInteger("max-height", codecMaxValues2.height);
        MediaFormatUtil.maybeSetInteger(mediaFormat, "max-input-size", codecMaxValues2.inputSize);
        if (Util.SDK_INT >= 23) {
            mediaFormat.setInteger("priority", 0);
            if (f2 != -1.0f) {
                mediaFormat.setFloat("operating-rate", f2);
            }
        }
        if (z2) {
            mediaFormat.setInteger("no-post-process", 1);
            mediaFormat.setInteger("auto-frc", 0);
        }
        if (i3 != 0) {
            configureTunnelingV21(mediaFormat, i3);
        }
        return mediaFormat;
    }

    public String getName() {
        return TAG;
    }

    public Surface getSurface() {
        return this.surface;
    }

    @TargetApi(29)
    public void handleInputBufferSupplementalData(DecoderInputBuffer decoderInputBuffer) throws ExoPlaybackException {
        if (this.codecHandlesHdr10PlusOutOfBandMetadata) {
            ByteBuffer byteBuffer = (ByteBuffer) Assertions.checkNotNull(decoderInputBuffer.supplementalData);
            if (byteBuffer.remaining() >= 7) {
                byte b3 = byteBuffer.get();
                short s3 = byteBuffer.getShort();
                short s4 = byteBuffer.getShort();
                byte b4 = byteBuffer.get();
                byte b5 = byteBuffer.get();
                byteBuffer.position(0);
                if (b3 == -75 && s3 == 60 && s4 == 1 && b4 == 4 && b5 == 0) {
                    byte[] bArr = new byte[byteBuffer.remaining()];
                    byteBuffer.get(bArr);
                    byteBuffer.position(0);
                    setHdr10PlusInfoV29(getCodec(), bArr);
                }
            }
        }
    }

    public void handleMessage(int i3, @Nullable Object obj) throws ExoPlaybackException {
        if (i3 == 1) {
            setOutput(obj);
        } else if (i3 == 7) {
            this.frameMetadataListener = (VideoFrameMetadataListener) obj;
        } else if (i3 == 10) {
            int intValue = ((Integer) obj).intValue();
            if (this.tunnelingAudioSessionId != intValue) {
                this.tunnelingAudioSessionId = intValue;
                if (this.tunneling) {
                    releaseCodec();
                }
            }
        } else if (i3 == 4) {
            this.scalingMode = ((Integer) obj).intValue();
            MediaCodecAdapter codec = getCodec();
            if (codec != null) {
                codec.setVideoScalingMode(this.scalingMode);
            }
        } else if (i3 != 5) {
            super.handleMessage(i3, obj);
        } else {
            this.frameReleaseHelper.setChangeFrameRateStrategy(((Integer) obj).intValue());
        }
    }

    public boolean isReady() {
        PlaceholderSurface placeholderSurface2;
        if (super.isReady() && (this.renderedFirstFrameAfterReset || (((placeholderSurface2 = this.placeholderSurface) != null && this.surface == placeholderSurface2) || getCodec() == null || this.tunneling))) {
            this.joiningDeadlineMs = C.TIME_UNSET;
            return true;
        } else if (this.joiningDeadlineMs == C.TIME_UNSET) {
            return false;
        } else {
            if (SystemClock.elapsedRealtime() < this.joiningDeadlineMs) {
                return true;
            }
            this.joiningDeadlineMs = C.TIME_UNSET;
            return false;
        }
    }

    public boolean maybeDropBuffersToKeyframe(long j2, boolean z2) throws ExoPlaybackException {
        int skipSource = skipSource(j2);
        if (skipSource == 0) {
            return false;
        }
        if (z2) {
            DecoderCounters decoderCounters = this.decoderCounters;
            decoderCounters.skippedInputBufferCount += skipSource;
            decoderCounters.skippedOutputBufferCount += this.buffersInCodecCount;
        } else {
            this.decoderCounters.droppedToKeyframeCount++;
            updateDroppedBufferCounters(skipSource, this.buffersInCodecCount);
        }
        flushOrReinitializeCodec();
        return true;
    }

    public void maybeNotifyRenderedFirstFrame() {
        this.renderedFirstFrameAfterEnable = true;
        if (!this.renderedFirstFrameAfterReset) {
            this.renderedFirstFrameAfterReset = true;
            this.eventDispatcher.renderedFirstFrame(this.surface);
            this.haveReportedFirstFrameRenderedForCurrentSurface = true;
        }
    }

    public void onCodecError(Exception exc) {
        Log.e(TAG, "Video codec error", exc);
        this.eventDispatcher.videoCodecError(exc);
    }

    public void onCodecInitialized(String str, MediaCodecAdapter.Configuration configuration, long j2, long j3) {
        this.eventDispatcher.decoderInitialized(str, j2, j3);
        this.codecNeedsSetOutputSurfaceWorkaround = codecNeedsSetOutputSurfaceWorkaround(str);
        this.codecHandlesHdr10PlusOutOfBandMetadata = ((MediaCodecInfo) Assertions.checkNotNull(getCodecInfo())).isHdr10PlusOutOfBandMetadataSupported();
        if (Util.SDK_INT >= 23 && this.tunneling) {
            this.tunnelingOnFrameRenderedListener = new OnFrameRenderedListenerV23((MediaCodecAdapter) Assertions.checkNotNull(getCodec()));
        }
    }

    public void onCodecReleased(String str) {
        this.eventDispatcher.decoderReleased(str);
    }

    public void onDisabled() {
        clearReportedVideoSize();
        clearRenderedFirstFrame();
        this.haveReportedFirstFrameRenderedForCurrentSurface = false;
        this.tunnelingOnFrameRenderedListener = null;
        try {
            super.onDisabled();
        } finally {
            this.eventDispatcher.disabled(this.decoderCounters);
        }
    }

    public void onEnabled(boolean z2, boolean z3) throws ExoPlaybackException {
        super.onEnabled(z2, z3);
        boolean z4 = getConfiguration().tunneling;
        Assertions.checkState(!z4 || this.tunnelingAudioSessionId != 0);
        if (this.tunneling != z4) {
            this.tunneling = z4;
            releaseCodec();
        }
        this.eventDispatcher.enabled(this.decoderCounters);
        this.mayRenderFirstFrameAfterEnableIfNotStarted = z3;
        this.renderedFirstFrameAfterEnable = false;
    }

    @Nullable
    public DecoderReuseEvaluation onInputFormatChanged(FormatHolder formatHolder) throws ExoPlaybackException {
        DecoderReuseEvaluation onInputFormatChanged = super.onInputFormatChanged(formatHolder);
        this.eventDispatcher.inputFormatChanged(formatHolder.format, onInputFormatChanged);
        return onInputFormatChanged;
    }

    public void onOutputFormatChanged(Format format, @Nullable MediaFormat mediaFormat) {
        MediaCodecAdapter codec = getCodec();
        if (codec != null) {
            codec.setVideoScalingMode(this.scalingMode);
        }
        if (this.tunneling) {
            this.currentWidth = format.width;
            this.currentHeight = format.height;
        } else {
            Assertions.checkNotNull(mediaFormat);
            boolean z2 = mediaFormat.containsKey(KEY_CROP_RIGHT) && mediaFormat.containsKey(KEY_CROP_LEFT) && mediaFormat.containsKey(KEY_CROP_BOTTOM) && mediaFormat.containsKey(KEY_CROP_TOP);
            this.currentWidth = z2 ? (mediaFormat.getInteger(KEY_CROP_RIGHT) - mediaFormat.getInteger(KEY_CROP_LEFT)) + 1 : mediaFormat.getInteger("width");
            this.currentHeight = z2 ? (mediaFormat.getInteger(KEY_CROP_BOTTOM) - mediaFormat.getInteger(KEY_CROP_TOP)) + 1 : mediaFormat.getInteger("height");
        }
        float f2 = format.pixelWidthHeightRatio;
        this.currentPixelWidthHeightRatio = f2;
        if (Util.SDK_INT >= 21) {
            int i3 = format.rotationDegrees;
            if (i3 == 90 || i3 == 270) {
                int i4 = this.currentWidth;
                this.currentWidth = this.currentHeight;
                this.currentHeight = i4;
                this.currentPixelWidthHeightRatio = 1.0f / f2;
            }
        } else {
            this.currentUnappliedRotationDegrees = format.rotationDegrees;
        }
        this.frameReleaseHelper.onFormatChanged(format.frameRate);
    }

    public void onPositionReset(long j2, boolean z2) throws ExoPlaybackException {
        super.onPositionReset(j2, z2);
        clearRenderedFirstFrame();
        this.frameReleaseHelper.onPositionReset();
        this.lastBufferPresentationTimeUs = C.TIME_UNSET;
        this.initialPositionUs = C.TIME_UNSET;
        this.consecutiveDroppedFrameCount = 0;
        if (z2) {
            setJoiningDeadlineMs();
        } else {
            this.joiningDeadlineMs = C.TIME_UNSET;
        }
    }

    @CallSuper
    public void onProcessedOutputBuffer(long j2) {
        super.onProcessedOutputBuffer(j2);
        if (!this.tunneling) {
            this.buffersInCodecCount--;
        }
    }

    public void onProcessedStreamChange() {
        super.onProcessedStreamChange();
        clearRenderedFirstFrame();
    }

    public void onProcessedTunneledBuffer(long j2) throws ExoPlaybackException {
        updateOutputFormatForTime(j2);
        maybeNotifyVideoSizeChanged();
        this.decoderCounters.renderedOutputBufferCount++;
        maybeNotifyRenderedFirstFrame();
        onProcessedOutputBuffer(j2);
    }

    @CallSuper
    public void onQueueInputBuffer(DecoderInputBuffer decoderInputBuffer) throws ExoPlaybackException {
        boolean z2 = this.tunneling;
        if (!z2) {
            this.buffersInCodecCount++;
        }
        if (Util.SDK_INT < 23 && z2) {
            onProcessedTunneledBuffer(decoderInputBuffer.timeUs);
        }
    }

    @TargetApi(17)
    public void onReset() {
        try {
            super.onReset();
        } finally {
            if (this.placeholderSurface != null) {
                releasePlaceholderSurface();
            }
        }
    }

    public void onStarted() {
        super.onStarted();
        this.droppedFrames = 0;
        this.droppedFrameAccumulationStartTimeMs = SystemClock.elapsedRealtime();
        this.lastRenderRealtimeUs = SystemClock.elapsedRealtime() * 1000;
        this.totalVideoFrameProcessingOffsetUs = 0;
        this.videoFrameProcessingOffsetCount = 0;
        this.frameReleaseHelper.onStarted();
    }

    public void onStopped() {
        this.joiningDeadlineMs = C.TIME_UNSET;
        maybeNotifyDroppedFrames();
        maybeNotifyVideoFrameProcessingOffset();
        this.frameReleaseHelper.onStopped();
        super.onStopped();
    }

    public boolean processOutputBuffer(long j2, long j3, @Nullable MediaCodecAdapter mediaCodecAdapter, @Nullable ByteBuffer byteBuffer, int i3, int i4, int i5, long j4, boolean z2, boolean z3, Format format) throws ExoPlaybackException {
        long j5;
        boolean z4;
        long j6 = j2;
        MediaCodecAdapter mediaCodecAdapter2 = mediaCodecAdapter;
        int i6 = i3;
        long j7 = j4;
        Assertions.checkNotNull(mediaCodecAdapter);
        if (this.initialPositionUs == C.TIME_UNSET) {
            this.initialPositionUs = j6;
        }
        if (j7 != this.lastBufferPresentationTimeUs) {
            this.frameReleaseHelper.onNextFrame(j7);
            this.lastBufferPresentationTimeUs = j7;
        }
        long outputStreamOffsetUs = getOutputStreamOffsetUs();
        long j8 = j7 - outputStreamOffsetUs;
        if (!z2 || z3) {
            double playbackSpeed = (double) getPlaybackSpeed();
            boolean z5 = getState() == 2;
            long elapsedRealtime = SystemClock.elapsedRealtime() * 1000;
            long j9 = (long) (((double) (j7 - j6)) / playbackSpeed);
            if (z5) {
                j9 -= elapsedRealtime - j3;
            }
            if (this.surface != this.placeholderSurface) {
                long j10 = elapsedRealtime - this.lastRenderRealtimeUs;
                if (this.renderedFirstFrameAfterEnable ? this.renderedFirstFrameAfterReset : !z5 && !this.mayRenderFirstFrameAfterEnableIfNotStarted) {
                    j5 = j10;
                    z4 = false;
                } else {
                    z4 = true;
                    j5 = j10;
                }
                if (this.joiningDeadlineMs != C.TIME_UNSET || j6 < outputStreamOffsetUs || (!z4 && (!z5 || !shouldForceRenderOutputBuffer(j9, j5)))) {
                    if (z5 && j6 != this.initialPositionUs) {
                        long nanoTime = System.nanoTime();
                        long adjustReleaseTime = this.frameReleaseHelper.adjustReleaseTime((j9 * 1000) + nanoTime);
                        long j11 = (adjustReleaseTime - nanoTime) / 1000;
                        boolean z6 = this.joiningDeadlineMs != C.TIME_UNSET;
                        long j12 = j11;
                        if (shouldDropBuffersToKeyframe(j11, j3, z3) && maybeDropBuffersToKeyframe(j6, z6)) {
                            return false;
                        }
                        if (shouldDropOutputBuffer(j12, j3, z3)) {
                            if (z6) {
                                skipOutputBuffer(mediaCodecAdapter2, i6, j8);
                            } else {
                                dropOutputBuffer(mediaCodecAdapter2, i6, j8);
                            }
                            updateVideoFrameProcessingOffsetCounters(j12);
                            return true;
                        }
                        long j13 = j12;
                        if (Util.SDK_INT >= 21) {
                            if (j13 < 50000) {
                                notifyFrameMetadataListener(j8, adjustReleaseTime, format);
                                renderOutputBufferV21(mediaCodecAdapter, i3, j8, adjustReleaseTime);
                                updateVideoFrameProcessingOffsetCounters(j13);
                                return true;
                            }
                        } else if (j13 < 30000) {
                            if (j13 > 11000) {
                                try {
                                    Thread.sleep((j13 - 10000) / 1000);
                                } catch (InterruptedException unused) {
                                    Thread.currentThread().interrupt();
                                    return false;
                                }
                            }
                            notifyFrameMetadataListener(j8, adjustReleaseTime, format);
                            renderOutputBuffer(mediaCodecAdapter2, i6, j8);
                            updateVideoFrameProcessingOffsetCounters(j13);
                            return true;
                        }
                    }
                    return false;
                }
                long nanoTime2 = System.nanoTime();
                notifyFrameMetadataListener(j8, nanoTime2, format);
                if (Util.SDK_INT >= 21) {
                    renderOutputBufferV21(mediaCodecAdapter, i3, j8, nanoTime2);
                } else {
                    renderOutputBuffer(mediaCodecAdapter2, i6, j8);
                }
                updateVideoFrameProcessingOffsetCounters(j9);
                return true;
            } else if (!isBufferLate(j9)) {
                return false;
            } else {
                skipOutputBuffer(mediaCodecAdapter2, i6, j8);
                updateVideoFrameProcessingOffsetCounters(j9);
                return true;
            }
        } else {
            skipOutputBuffer(mediaCodecAdapter2, i6, j8);
            return true;
        }
    }

    public void renderOutputBuffer(MediaCodecAdapter mediaCodecAdapter, int i3, long j2) {
        maybeNotifyVideoSizeChanged();
        TraceUtil.beginSection("releaseOutputBuffer");
        mediaCodecAdapter.releaseOutputBuffer(i3, true);
        TraceUtil.endSection();
        this.lastRenderRealtimeUs = SystemClock.elapsedRealtime() * 1000;
        this.decoderCounters.renderedOutputBufferCount++;
        this.consecutiveDroppedFrameCount = 0;
        maybeNotifyRenderedFirstFrame();
    }

    @RequiresApi(21)
    public void renderOutputBufferV21(MediaCodecAdapter mediaCodecAdapter, int i3, long j2, long j3) {
        maybeNotifyVideoSizeChanged();
        TraceUtil.beginSection("releaseOutputBuffer");
        mediaCodecAdapter.releaseOutputBuffer(i3, j3);
        TraceUtil.endSection();
        this.lastRenderRealtimeUs = SystemClock.elapsedRealtime() * 1000;
        this.decoderCounters.renderedOutputBufferCount++;
        this.consecutiveDroppedFrameCount = 0;
        maybeNotifyRenderedFirstFrame();
    }

    @CallSuper
    public void resetCodecStateForFlush() {
        super.resetCodecStateForFlush();
        this.buffersInCodecCount = 0;
    }

    @RequiresApi(23)
    public void setOutputSurfaceV23(MediaCodecAdapter mediaCodecAdapter, Surface surface2) {
        mediaCodecAdapter.setOutputSurface(surface2);
    }

    public void setPlaybackSpeed(float f2, float f3) throws ExoPlaybackException {
        super.setPlaybackSpeed(f2, f3);
        this.frameReleaseHelper.onPlaybackSpeed(f2);
    }

    public boolean shouldDropBuffersToKeyframe(long j2, long j3, boolean z2) {
        return isBufferVeryLate(j2) && !z2;
    }

    public boolean shouldDropOutputBuffer(long j2, long j3, boolean z2) {
        return isBufferLate(j2) && !z2;
    }

    public boolean shouldForceRenderOutputBuffer(long j2, long j3) {
        return isBufferLate(j2) && j3 > 100000;
    }

    public boolean shouldInitCodec(MediaCodecInfo mediaCodecInfo) {
        return this.surface != null || shouldUseDummySurface(mediaCodecInfo);
    }

    public void skipOutputBuffer(MediaCodecAdapter mediaCodecAdapter, int i3, long j2) {
        TraceUtil.beginSection("skipVideoBuffer");
        mediaCodecAdapter.releaseOutputBuffer(i3, false);
        TraceUtil.endSection();
        this.decoderCounters.skippedOutputBufferCount++;
    }

    public int supportsFormat(MediaCodecSelector mediaCodecSelector, Format format) throws MediaCodecUtil.DecoderQueryException {
        boolean z2;
        int i3 = 0;
        if (!MimeTypes.isVideo(format.sampleMimeType)) {
            return RendererCapabilities.create(0);
        }
        boolean z3 = format.drmInitData != null;
        List<MediaCodecInfo> decoderInfos = getDecoderInfos(mediaCodecSelector, format, z3, false);
        if (z3 && decoderInfos.isEmpty()) {
            decoderInfos = getDecoderInfos(mediaCodecSelector, format, false, false);
        }
        if (decoderInfos.isEmpty()) {
            return RendererCapabilities.create(1);
        }
        if (!MediaCodecRenderer.supportsFormatDrm(format)) {
            return RendererCapabilities.create(2);
        }
        MediaCodecInfo mediaCodecInfo = decoderInfos.get(0);
        boolean isFormatSupported = mediaCodecInfo.isFormatSupported(format);
        if (!isFormatSupported) {
            int i4 = 1;
            while (true) {
                if (i4 >= decoderInfos.size()) {
                    break;
                }
                MediaCodecInfo mediaCodecInfo2 = decoderInfos.get(i4);
                if (mediaCodecInfo2.isFormatSupported(format)) {
                    z2 = false;
                    isFormatSupported = true;
                    mediaCodecInfo = mediaCodecInfo2;
                    break;
                }
                i4++;
            }
        }
        z2 = true;
        int i5 = isFormatSupported ? 4 : 3;
        int i6 = mediaCodecInfo.isSeamlessAdaptationSupported(format) ? 16 : 8;
        int i7 = mediaCodecInfo.hardwareAccelerated ? 64 : 0;
        int i8 = z2 ? 128 : 0;
        if (isFormatSupported) {
            List<MediaCodecInfo> decoderInfos2 = getDecoderInfos(mediaCodecSelector, format, z3, true);
            if (!decoderInfos2.isEmpty()) {
                MediaCodecInfo mediaCodecInfo3 = MediaCodecUtil.getDecoderInfosSortedByFormatSupport(decoderInfos2, format).get(0);
                if (mediaCodecInfo3.isFormatSupported(format) && mediaCodecInfo3.isSeamlessAdaptationSupported(format)) {
                    i3 = 32;
                }
            }
        }
        return RendererCapabilities.create(i5, i6, i3, i7, i8);
    }

    public void updateDroppedBufferCounters(int i3, int i4) {
        DecoderCounters decoderCounters = this.decoderCounters;
        decoderCounters.droppedInputBufferCount += i3;
        int i5 = i3 + i4;
        decoderCounters.droppedBufferCount += i5;
        this.droppedFrames += i5;
        int i6 = this.consecutiveDroppedFrameCount + i5;
        this.consecutiveDroppedFrameCount = i6;
        decoderCounters.maxConsecutiveDroppedBufferCount = Math.max(i6, decoderCounters.maxConsecutiveDroppedBufferCount);
        int i7 = this.maxDroppedFramesToNotify;
        if (i7 > 0 && this.droppedFrames >= i7) {
            maybeNotifyDroppedFrames();
        }
    }

    public void updateVideoFrameProcessingOffsetCounters(long j2) {
        this.decoderCounters.addVideoFrameProcessingOffset(j2);
        this.totalVideoFrameProcessingOffsetUs += j2;
        this.videoFrameProcessingOffsetCount++;
    }

    public MediaCodecVideoRenderer(Context context2, MediaCodecSelector mediaCodecSelector, long j2) {
        this(context2, mediaCodecSelector, j2, (Handler) null, (VideoRendererEventListener) null, 0);
    }

    public MediaCodecVideoRenderer(Context context2, MediaCodecSelector mediaCodecSelector, long j2, @Nullable Handler handler, @Nullable VideoRendererEventListener videoRendererEventListener, int i3) {
        this(context2, MediaCodecAdapter.Factory.DEFAULT, mediaCodecSelector, j2, false, handler, videoRendererEventListener, i3, 30.0f);
    }

    public MediaCodecVideoRenderer(Context context2, MediaCodecSelector mediaCodecSelector, long j2, boolean z2, @Nullable Handler handler, @Nullable VideoRendererEventListener videoRendererEventListener, int i3) {
        this(context2, MediaCodecAdapter.Factory.DEFAULT, mediaCodecSelector, j2, z2, handler, videoRendererEventListener, i3, 30.0f);
    }

    private static List<MediaCodecInfo> getDecoderInfos(MediaCodecSelector mediaCodecSelector, Format format, boolean z2, boolean z3) throws MediaCodecUtil.DecoderQueryException {
        String str = format.sampleMimeType;
        if (str == null) {
            return ImmutableList.of();
        }
        List<MediaCodecInfo> decoderInfos = mediaCodecSelector.getDecoderInfos(str, z2, z3);
        String alternativeCodecMimeType = MediaCodecUtil.getAlternativeCodecMimeType(format);
        if (alternativeCodecMimeType == null) {
            return ImmutableList.copyOf(decoderInfos);
        }
        return ImmutableList.builder().addAll((Iterable) decoderInfos).addAll((Iterable) mediaCodecSelector.getDecoderInfos(alternativeCodecMimeType, z2, z3)).build();
    }

    public MediaCodecVideoRenderer(Context context2, MediaCodecAdapter.Factory factory, MediaCodecSelector mediaCodecSelector, long j2, boolean z2, @Nullable Handler handler, @Nullable VideoRendererEventListener videoRendererEventListener, int i3) {
        this(context2, factory, mediaCodecSelector, j2, z2, handler, videoRendererEventListener, i3, 30.0f);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public MediaCodecVideoRenderer(Context context2, MediaCodecAdapter.Factory factory, MediaCodecSelector mediaCodecSelector, long j2, boolean z2, @Nullable Handler handler, @Nullable VideoRendererEventListener videoRendererEventListener, int i3, float f2) {
        super(2, factory, mediaCodecSelector, z2, f2);
        this.allowedJoiningTimeMs = j2;
        this.maxDroppedFramesToNotify = i3;
        Context applicationContext = context2.getApplicationContext();
        this.context = applicationContext;
        this.frameReleaseHelper = new VideoFrameReleaseHelper(applicationContext);
        Handler handler2 = handler;
        VideoRendererEventListener videoRendererEventListener2 = videoRendererEventListener;
        this.eventDispatcher = new VideoRendererEventListener.EventDispatcher(handler, videoRendererEventListener);
        this.deviceNeedsNoPostProcessWorkaround = deviceNeedsNoPostProcessWorkaround();
        this.joiningDeadlineMs = C.TIME_UNSET;
        this.currentWidth = -1;
        this.currentHeight = -1;
        this.currentPixelWidthHeightRatio = -1.0f;
        this.scalingMode = 1;
        this.tunnelingAudioSessionId = 0;
        clearReportedVideoSize();
    }
}
