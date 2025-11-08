package com.appsamurai.storyly.exoplayer2.core.mediacodec;

import android.annotation.TargetApi;
import android.media.MediaCodec;
import android.media.MediaCrypto;
import android.media.MediaCryptoException;
import android.media.MediaFormat;
import android.media.metrics.LogSessionId;
import android.os.Bundle;
import android.os.SystemClock;
import androidx.annotation.CallSuper;
import androidx.annotation.CheckResult;
import androidx.annotation.DoNotInline;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import com.adjust.sdk.Constants;
import com.appsamurai.storyly.exoplayer2.common.C;
import com.appsamurai.storyly.exoplayer2.common.Format;
import com.appsamurai.storyly.exoplayer2.common.PlaybackException;
import com.appsamurai.storyly.exoplayer2.common.util.Assertions;
import com.appsamurai.storyly.exoplayer2.common.util.Log;
import com.appsamurai.storyly.exoplayer2.common.util.MimeTypes;
import com.appsamurai.storyly.exoplayer2.common.util.TimedValueQueue;
import com.appsamurai.storyly.exoplayer2.common.util.TraceUtil;
import com.appsamurai.storyly.exoplayer2.common.util.Util;
import com.appsamurai.storyly.exoplayer2.core.BaseRenderer;
import com.appsamurai.storyly.exoplayer2.core.ExoPlaybackException;
import com.appsamurai.storyly.exoplayer2.core.FormatHolder;
import com.appsamurai.storyly.exoplayer2.core.analytics.PlayerId;
import com.appsamurai.storyly.exoplayer2.core.decoder.DecoderCounters;
import com.appsamurai.storyly.exoplayer2.core.decoder.DecoderReuseEvaluation;
import com.appsamurai.storyly.exoplayer2.core.drm.DrmSession;
import com.appsamurai.storyly.exoplayer2.core.drm.FrameworkCryptoConfig;
import com.appsamurai.storyly.exoplayer2.core.mediacodec.MediaCodecAdapter;
import com.appsamurai.storyly.exoplayer2.core.mediacodec.MediaCodecUtil;
import com.appsamurai.storyly.exoplayer2.decoder.CryptoConfig;
import com.appsamurai.storyly.exoplayer2.decoder.DecoderInputBuffer;
import com.appsamurai.storyly.exoplayer2.extractor.util.NalUnitUtil;
import com.fasterxml.jackson.dataformat.cbor.CBORConstants;
import com.google.common.base.Ascii;
import com.reown.foundation.util.jwt.JwtUtilsKt;
import io.nodle.cash.substrate.SubstrateHelper;
import io.zksync.transaction.type.Transaction712;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.msgpack.core.MessagePack;

public abstract class MediaCodecRenderer extends BaseRenderer {
    private static final byte[] ADAPTATION_WORKAROUND_BUFFER = {0, 0, 1, 103, 66, MessagePack.Code.NIL, Ascii.VT, MessagePack.Code.STR16, SubstrateHelper.NODLE_SUBSTRATE_ID, MessagePack.Code.FIXARRAY_PREFIX, 0, 0, 1, 104, MessagePack.Code.UINT32, Ascii.SI, 19, 32, 0, 0, 1, 101, -120, -124, Ascii.CR, MessagePack.Code.UINT32, Transaction712.EIP_712_TX_TYPE, Ascii.CAN, MessagePack.Code.FIXSTR_PREFIX, 0, 47, -65, Ascii.FS, 49, -61, 39, 93, CBORConstants.BYTE_STRING_1BYTE_LEN};
    private static final int ADAPTATION_WORKAROUND_MODE_ALWAYS = 2;
    private static final int ADAPTATION_WORKAROUND_MODE_NEVER = 0;
    private static final int ADAPTATION_WORKAROUND_MODE_SAME_RESOLUTION = 1;
    private static final int ADAPTATION_WORKAROUND_SLICE_WIDTH_HEIGHT = 32;
    protected static final float CODEC_OPERATING_RATE_UNSET = -1.0f;
    private static final int DRAIN_ACTION_FLUSH = 1;
    private static final int DRAIN_ACTION_FLUSH_AND_UPDATE_DRM_SESSION = 2;
    private static final int DRAIN_ACTION_NONE = 0;
    private static final int DRAIN_ACTION_REINITIALIZE = 3;
    private static final int DRAIN_STATE_NONE = 0;
    private static final int DRAIN_STATE_SIGNAL_END_OF_STREAM = 1;
    private static final int DRAIN_STATE_WAIT_END_OF_STREAM = 2;
    private static final long MAX_CODEC_HOTSWAP_TIME_MS = 1000;
    private static final int MAX_PENDING_OUTPUT_STREAM_OFFSET_COUNT = 10;
    private static final int RECONFIGURATION_STATE_NONE = 0;
    private static final int RECONFIGURATION_STATE_QUEUE_PENDING = 2;
    private static final int RECONFIGURATION_STATE_WRITE_PENDING = 1;
    private static final String TAG = "MediaCodecRenderer";
    private final float assumedMinimumCodecOperatingRate;
    @Nullable
    private ArrayDeque<MediaCodecInfo> availableCodecInfos;
    private final DecoderInputBuffer buffer = new DecoderInputBuffer(0);
    private final BatchBuffer bypassBatchBuffer;
    private boolean bypassDrainAndReinitialize;
    private boolean bypassEnabled;
    private final DecoderInputBuffer bypassSampleBuffer = new DecoderInputBuffer(2);
    private boolean bypassSampleBufferPending;
    @Nullable
    private C2Mp3TimestampTracker c2Mp3TimestampTracker;
    @Nullable
    private MediaCodecAdapter codec;
    private int codecAdaptationWorkaroundMode;
    private final MediaCodecAdapter.Factory codecAdapterFactory;
    private int codecDrainAction;
    private int codecDrainState;
    @Nullable
    private DrmSession codecDrmSession;
    private boolean codecHasOutputMediaFormat;
    private long codecHotswapDeadlineMs;
    @Nullable
    private MediaCodecInfo codecInfo;
    @Nullable
    private Format codecInputFormat;
    private boolean codecNeedsAdaptationWorkaroundBuffer;
    private boolean codecNeedsDiscardToSpsWorkaround;
    private boolean codecNeedsEosBufferTimestampWorkaround;
    private boolean codecNeedsEosFlushWorkaround;
    private boolean codecNeedsEosOutputExceptionWorkaround;
    private boolean codecNeedsEosPropagation;
    private boolean codecNeedsFlushWorkaround;
    private boolean codecNeedsMonoChannelCountWorkaround;
    private boolean codecNeedsSosFlushWorkaround;
    private float codecOperatingRate;
    @Nullable
    private MediaFormat codecOutputMediaFormat;
    private boolean codecOutputMediaFormatChanged;
    private boolean codecReceivedBuffers;
    private boolean codecReceivedEos;
    private int codecReconfigurationState;
    private boolean codecReconfigured;
    private float currentPlaybackSpeed;
    private final ArrayList<Long> decodeOnlyPresentationTimestamps;
    protected DecoderCounters decoderCounters;
    private final boolean enableDecoderFallback;
    private final TimedValueQueue<Format> formatQueue;
    @Nullable
    private Format inputFormat;
    private int inputIndex;
    private boolean inputStreamEnded;
    private boolean isDecodeOnlyOutputBuffer;
    private boolean isLastOutputBuffer;
    private long largestQueuedPresentationTimeUs;
    private long lastBufferInStreamPresentationTimeUs;
    private final MediaCodecSelector mediaCodecSelector;
    @Nullable
    private MediaCrypto mediaCrypto;
    private boolean mediaCryptoRequiresSecureDecoder;
    private final DecoderInputBuffer noDataBuffer = DecoderInputBuffer.newNoDataInstance();
    @Nullable
    private ByteBuffer outputBuffer;
    private final MediaCodec.BufferInfo outputBufferInfo;
    @Nullable
    private Format outputFormat;
    private int outputIndex;
    private boolean outputStreamEnded;
    private long outputStreamOffsetUs;
    private long outputStreamStartPositionUs;
    private boolean pendingOutputEndOfStream;
    private int pendingOutputStreamOffsetCount;
    private final long[] pendingOutputStreamOffsetsUs;
    private final long[] pendingOutputStreamStartPositionsUs;
    private final long[] pendingOutputStreamSwitchTimesUs;
    @Nullable
    private ExoPlaybackException pendingPlaybackException;
    @Nullable
    private DecoderInitializationException preferredDecoderInitializationException;
    private long renderTimeLimitMs;
    private boolean shouldSkipAdaptationWorkaroundOutputBuffer;
    @Nullable
    private DrmSession sourceDrmSession;
    private float targetPlaybackSpeed;
    private boolean waitingForFirstSampleInFormat;

    @RequiresApi(31)
    public static final class Api31 {
        private Api31() {
        }

        @DoNotInline
        public static void setLogSessionIdToMediaCodecFormat(MediaCodecAdapter.Configuration configuration, PlayerId playerId) {
            LogSessionId logSessionId = playerId.getLogSessionId();
            if (!logSessionId.equals(LogSessionId.LOG_SESSION_ID_NONE)) {
                configuration.mediaFormat.setString("log-session-id", logSessionId.getStringId());
            }
        }
    }

    public MediaCodecRenderer(int i3, MediaCodecAdapter.Factory factory, MediaCodecSelector mediaCodecSelector2, boolean z2, float f2) {
        super(i3);
        this.codecAdapterFactory = factory;
        this.mediaCodecSelector = (MediaCodecSelector) Assertions.checkNotNull(mediaCodecSelector2);
        this.enableDecoderFallback = z2;
        this.assumedMinimumCodecOperatingRate = f2;
        BatchBuffer batchBuffer = new BatchBuffer();
        this.bypassBatchBuffer = batchBuffer;
        this.formatQueue = new TimedValueQueue<>();
        this.decodeOnlyPresentationTimestamps = new ArrayList<>();
        this.outputBufferInfo = new MediaCodec.BufferInfo();
        this.currentPlaybackSpeed = 1.0f;
        this.targetPlaybackSpeed = 1.0f;
        this.renderTimeLimitMs = C.TIME_UNSET;
        this.pendingOutputStreamStartPositionsUs = new long[10];
        this.pendingOutputStreamOffsetsUs = new long[10];
        this.pendingOutputStreamSwitchTimesUs = new long[10];
        this.outputStreamStartPositionUs = C.TIME_UNSET;
        this.outputStreamOffsetUs = C.TIME_UNSET;
        batchBuffer.ensureSpaceForWrite(0);
        batchBuffer.data.order(ByteOrder.nativeOrder());
        this.codecOperatingRate = -1.0f;
        this.codecAdaptationWorkaroundMode = 0;
        this.codecReconfigurationState = 0;
        this.inputIndex = -1;
        this.outputIndex = -1;
        this.codecHotswapDeadlineMs = C.TIME_UNSET;
        this.largestQueuedPresentationTimeUs = C.TIME_UNSET;
        this.lastBufferInStreamPresentationTimeUs = C.TIME_UNSET;
        this.codecDrainState = 0;
        this.codecDrainAction = 0;
    }

    private void bypassRead() throws ExoPlaybackException {
        Assertions.checkState(!this.inputStreamEnded);
        FormatHolder formatHolder = getFormatHolder();
        this.bypassSampleBuffer.clear();
        do {
            this.bypassSampleBuffer.clear();
            int readSource = readSource(formatHolder, this.bypassSampleBuffer, 0);
            if (readSource == -5) {
                onInputFormatChanged(formatHolder);
                return;
            } else if (readSource != -4) {
                if (readSource != -3) {
                    throw new IllegalStateException();
                }
                return;
            } else if (this.bypassSampleBuffer.isEndOfStream()) {
                this.inputStreamEnded = true;
                return;
            } else {
                if (this.waitingForFirstSampleInFormat) {
                    Format format = (Format) Assertions.checkNotNull(this.inputFormat);
                    this.outputFormat = format;
                    onOutputFormatChanged(format, (MediaFormat) null);
                    this.waitingForFirstSampleInFormat = false;
                }
                this.bypassSampleBuffer.flip();
            }
        } while (this.bypassBatchBuffer.append(this.bypassSampleBuffer));
        this.bypassSampleBufferPending = true;
    }

    private boolean bypassRender(long j2, long j3) throws ExoPlaybackException {
        boolean z2;
        Assertions.checkState(!this.outputStreamEnded);
        if (this.bypassBatchBuffer.hasSamples()) {
            BatchBuffer batchBuffer = this.bypassBatchBuffer;
            if (!processOutputBuffer(j2, j3, (MediaCodecAdapter) null, batchBuffer.data, this.outputIndex, 0, batchBuffer.getSampleCount(), this.bypassBatchBuffer.getFirstSampleTimeUs(), this.bypassBatchBuffer.isDecodeOnly(), this.bypassBatchBuffer.isEndOfStream(), this.outputFormat)) {
                return false;
            }
            onProcessedOutputBuffer(this.bypassBatchBuffer.getLastSampleTimeUs());
            this.bypassBatchBuffer.clear();
            z2 = false;
        } else {
            z2 = false;
        }
        if (this.inputStreamEnded) {
            this.outputStreamEnded = true;
            return z2;
        }
        if (this.bypassSampleBufferPending) {
            Assertions.checkState(this.bypassBatchBuffer.append(this.bypassSampleBuffer));
            this.bypassSampleBufferPending = z2;
        }
        if (this.bypassDrainAndReinitialize) {
            if (this.bypassBatchBuffer.hasSamples()) {
                return true;
            }
            disableBypass();
            this.bypassDrainAndReinitialize = z2;
            maybeInitCodecOrBypass();
            if (!this.bypassEnabled) {
                return z2;
            }
        }
        bypassRead();
        if (this.bypassBatchBuffer.hasSamples()) {
            this.bypassBatchBuffer.flip();
        }
        if (this.bypassBatchBuffer.hasSamples() || this.inputStreamEnded || this.bypassDrainAndReinitialize) {
            return true;
        }
        return z2;
    }

    private int codecAdaptationWorkaroundMode(String str) {
        int i3 = Util.SDK_INT;
        if (i3 <= 25 && "OMX.Exynos.avc.dec.secure".equals(str)) {
            String str2 = Util.MODEL;
            if (str2.startsWith("SM-T585") || str2.startsWith("SM-A510") || str2.startsWith("SM-A520") || str2.startsWith("SM-J700")) {
                return 2;
            }
        }
        if (i3 >= 24) {
            return 0;
        }
        if (!"OMX.Nvidia.h264.decode".equals(str) && !"OMX.Nvidia.h264.decode.secure".equals(str)) {
            return 0;
        }
        String str3 = Util.DEVICE;
        return ("flounder".equals(str3) || "flounder_lte".equals(str3) || "grouper".equals(str3) || "tilapia".equals(str3)) ? 1 : 0;
    }

    private static boolean codecNeedsDiscardToSpsWorkaround(String str, Format format) {
        return Util.SDK_INT < 21 && format.initializationData.isEmpty() && "OMX.MTK.VIDEO.DECODER.AVC".equals(str);
    }

    private static boolean codecNeedsEosBufferTimestampWorkaround(String str) {
        if (Util.SDK_INT < 21 && "OMX.SEC.mp3.dec".equals(str) && Constants.REFERRER_API_SAMSUNG.equals(Util.MANUFACTURER)) {
            String str2 = Util.DEVICE;
            if (str2.startsWith("baffin") || str2.startsWith("grand") || str2.startsWith("fortuna") || str2.startsWith("gprimelte") || str2.startsWith("j2y18lte") || str2.startsWith("ms01")) {
                return true;
            }
        }
        return false;
    }

    private static boolean codecNeedsEosFlushWorkaround(String str) {
        int i3 = Util.SDK_INT;
        if (i3 > 23 || !"OMX.google.vorbis.decoder".equals(str)) {
            if (i3 <= 19) {
                String str2 = Util.DEVICE;
                if (("hb2000".equals(str2) || "stvm8".equals(str2)) && ("OMX.amlogic.avc.decoder.awesome".equals(str) || "OMX.amlogic.avc.decoder.awesome.secure".equals(str))) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    private static boolean codecNeedsEosOutputExceptionWorkaround(String str) {
        return Util.SDK_INT == 21 && "OMX.google.aac.decoder".equals(str);
    }

    private static boolean codecNeedsEosPropagationWorkaround(MediaCodecInfo mediaCodecInfo) {
        String str = mediaCodecInfo.name;
        int i3 = Util.SDK_INT;
        return (i3 <= 25 && "OMX.rk.video_decoder.avc".equals(str)) || (i3 <= 17 && "OMX.allwinner.video.decoder.avc".equals(str)) || ((i3 <= 29 && ("OMX.broadcom.video_decoder.tunnel".equals(str) || "OMX.broadcom.video_decoder.tunnel.secure".equals(str))) || ("Amazon".equals(Util.MANUFACTURER) && "AFTS".equals(Util.MODEL) && mediaCodecInfo.secure));
    }

    private static boolean codecNeedsFlushWorkaround(String str) {
        int i3 = Util.SDK_INT;
        return i3 < 18 || (i3 == 18 && ("OMX.SEC.avc.dec".equals(str) || "OMX.SEC.avc.dec.secure".equals(str))) || (i3 == 19 && Util.MODEL.startsWith("SM-G800") && ("OMX.Exynos.avc.dec".equals(str) || "OMX.Exynos.avc.dec.secure".equals(str)));
    }

    private static boolean codecNeedsMonoChannelCountWorkaround(String str, Format format) {
        return Util.SDK_INT <= 18 && format.channelCount == 1 && "OMX.MTK.AUDIO.DECODER.MP3".equals(str);
    }

    private static boolean codecNeedsSosFlushWorkaround(String str) {
        return Util.SDK_INT == 29 && "c2.android.aac.decoder".equals(str);
    }

    private void disableBypass() {
        this.bypassDrainAndReinitialize = false;
        this.bypassBatchBuffer.clear();
        this.bypassSampleBuffer.clear();
        this.bypassSampleBufferPending = false;
        this.bypassEnabled = false;
    }

    private boolean drainAndFlushCodec() {
        if (this.codecReceivedBuffers) {
            this.codecDrainState = 1;
            if (this.codecNeedsFlushWorkaround || this.codecNeedsEosFlushWorkaround) {
                this.codecDrainAction = 3;
                return false;
            }
            this.codecDrainAction = 1;
        }
        return true;
    }

    private void drainAndReinitializeCodec() throws ExoPlaybackException {
        if (this.codecReceivedBuffers) {
            this.codecDrainState = 1;
            this.codecDrainAction = 3;
            return;
        }
        reinitializeCodec();
    }

    @TargetApi(23)
    private boolean drainAndUpdateCodecDrmSessionV23() throws ExoPlaybackException {
        if (this.codecReceivedBuffers) {
            this.codecDrainState = 1;
            if (this.codecNeedsFlushWorkaround || this.codecNeedsEosFlushWorkaround) {
                this.codecDrainAction = 3;
                return false;
            }
            this.codecDrainAction = 2;
        } else {
            updateDrmSessionV23();
        }
        return true;
    }

    /* JADX WARNING: Removed duplicated region for block: B:68:0x00fa  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean drainOutputBuffer(long r20, long r22) throws com.appsamurai.storyly.exoplayer2.core.ExoPlaybackException {
        /*
            r19 = this;
            r15 = r19
            boolean r0 = r19.hasOutputBuffer()
            r16 = 1
            r14 = 0
            if (r0 != 0) goto L_0x00c2
            boolean r0 = r15.codecNeedsEosOutputExceptionWorkaround
            if (r0 == 0) goto L_0x0027
            boolean r0 = r15.codecReceivedEos
            if (r0 == 0) goto L_0x0027
            com.appsamurai.storyly.exoplayer2.core.mediacodec.MediaCodecAdapter r0 = r15.codec     // Catch:{ IllegalStateException -> 0x001c }
            android.media.MediaCodec$BufferInfo r1 = r15.outputBufferInfo     // Catch:{ IllegalStateException -> 0x001c }
            int r0 = r0.dequeueOutputBufferIndex(r1)     // Catch:{ IllegalStateException -> 0x001c }
            goto L_0x002f
        L_0x001c:
            r19.processEndOfStream()
            boolean r0 = r15.outputStreamEnded
            if (r0 == 0) goto L_0x0026
            r19.releaseCodec()
        L_0x0026:
            return r14
        L_0x0027:
            com.appsamurai.storyly.exoplayer2.core.mediacodec.MediaCodecAdapter r0 = r15.codec
            android.media.MediaCodec$BufferInfo r1 = r15.outputBufferInfo
            int r0 = r0.dequeueOutputBufferIndex(r1)
        L_0x002f:
            if (r0 >= 0) goto L_0x0049
            r1 = -2
            if (r0 != r1) goto L_0x0038
            r19.processOutputMediaFormatChanged()
            return r16
        L_0x0038:
            boolean r0 = r15.codecNeedsEosPropagation
            if (r0 == 0) goto L_0x0048
            boolean r0 = r15.inputStreamEnded
            if (r0 != 0) goto L_0x0045
            int r0 = r15.codecDrainState
            r1 = 2
            if (r0 != r1) goto L_0x0048
        L_0x0045:
            r19.processEndOfStream()
        L_0x0048:
            return r14
        L_0x0049:
            boolean r1 = r15.shouldSkipAdaptationWorkaroundOutputBuffer
            if (r1 == 0) goto L_0x0055
            r15.shouldSkipAdaptationWorkaroundOutputBuffer = r14
            com.appsamurai.storyly.exoplayer2.core.mediacodec.MediaCodecAdapter r1 = r15.codec
            r1.releaseOutputBuffer((int) r0, (boolean) r14)
            return r16
        L_0x0055:
            android.media.MediaCodec$BufferInfo r1 = r15.outputBufferInfo
            int r2 = r1.size
            if (r2 != 0) goto L_0x0065
            int r1 = r1.flags
            r1 = r1 & 4
            if (r1 == 0) goto L_0x0065
            r19.processEndOfStream()
            return r14
        L_0x0065:
            r15.outputIndex = r0
            com.appsamurai.storyly.exoplayer2.core.mediacodec.MediaCodecAdapter r1 = r15.codec
            java.nio.ByteBuffer r0 = r1.getOutputBuffer(r0)
            r15.outputBuffer = r0
            if (r0 == 0) goto L_0x0084
            android.media.MediaCodec$BufferInfo r1 = r15.outputBufferInfo
            int r1 = r1.offset
            r0.position(r1)
            java.nio.ByteBuffer r0 = r15.outputBuffer
            android.media.MediaCodec$BufferInfo r1 = r15.outputBufferInfo
            int r2 = r1.offset
            int r1 = r1.size
            int r2 = r2 + r1
            r0.limit(r2)
        L_0x0084:
            boolean r0 = r15.codecNeedsEosBufferTimestampWorkaround
            if (r0 == 0) goto L_0x00a5
            android.media.MediaCodec$BufferInfo r0 = r15.outputBufferInfo
            long r1 = r0.presentationTimeUs
            r3 = 0
            int r1 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r1 != 0) goto L_0x00a5
            int r1 = r0.flags
            r1 = r1 & 4
            if (r1 == 0) goto L_0x00a5
            long r1 = r15.largestQueuedPresentationTimeUs
            r3 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            int r3 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r3 == 0) goto L_0x00a5
            r0.presentationTimeUs = r1
        L_0x00a5:
            android.media.MediaCodec$BufferInfo r0 = r15.outputBufferInfo
            long r0 = r0.presentationTimeUs
            boolean r0 = r15.isDecodeOnlyBuffer(r0)
            r15.isDecodeOnlyOutputBuffer = r0
            long r0 = r15.lastBufferInStreamPresentationTimeUs
            android.media.MediaCodec$BufferInfo r2 = r15.outputBufferInfo
            long r2 = r2.presentationTimeUs
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r0 != 0) goto L_0x00bc
            r0 = r16
            goto L_0x00bd
        L_0x00bc:
            r0 = r14
        L_0x00bd:
            r15.isLastOutputBuffer = r0
            r15.updateOutputFormatForTime(r2)
        L_0x00c2:
            boolean r0 = r15.codecNeedsEosOutputExceptionWorkaround
            if (r0 == 0) goto L_0x00fe
            boolean r0 = r15.codecReceivedEos
            if (r0 == 0) goto L_0x00fe
            com.appsamurai.storyly.exoplayer2.core.mediacodec.MediaCodecAdapter r5 = r15.codec     // Catch:{ IllegalStateException -> 0x00f1 }
            java.nio.ByteBuffer r6 = r15.outputBuffer     // Catch:{ IllegalStateException -> 0x00f1 }
            int r7 = r15.outputIndex     // Catch:{ IllegalStateException -> 0x00f1 }
            android.media.MediaCodec$BufferInfo r0 = r15.outputBufferInfo     // Catch:{ IllegalStateException -> 0x00f1 }
            int r8 = r0.flags     // Catch:{ IllegalStateException -> 0x00f1 }
            long r10 = r0.presentationTimeUs     // Catch:{ IllegalStateException -> 0x00f1 }
            boolean r12 = r15.isDecodeOnlyOutputBuffer     // Catch:{ IllegalStateException -> 0x00f1 }
            boolean r13 = r15.isLastOutputBuffer     // Catch:{ IllegalStateException -> 0x00f1 }
            com.appsamurai.storyly.exoplayer2.common.Format r9 = r15.outputFormat     // Catch:{ IllegalStateException -> 0x00f1 }
            r17 = 1
            r0 = r19
            r1 = r20
            r3 = r22
            r18 = r9
            r9 = r17
            r17 = r14
            r14 = r18
            boolean r0 = r0.processOutputBuffer(r1, r3, r5, r6, r7, r8, r9, r10, r12, r13, r14)     // Catch:{ IllegalStateException -> 0x00f3 }
            goto L_0x011d
        L_0x00f1:
            r17 = r14
        L_0x00f3:
            r19.processEndOfStream()
            boolean r0 = r15.outputStreamEnded
            if (r0 == 0) goto L_0x00fd
            r19.releaseCodec()
        L_0x00fd:
            return r17
        L_0x00fe:
            r17 = r14
            com.appsamurai.storyly.exoplayer2.core.mediacodec.MediaCodecAdapter r5 = r15.codec
            java.nio.ByteBuffer r6 = r15.outputBuffer
            int r7 = r15.outputIndex
            android.media.MediaCodec$BufferInfo r0 = r15.outputBufferInfo
            int r8 = r0.flags
            long r10 = r0.presentationTimeUs
            boolean r12 = r15.isDecodeOnlyOutputBuffer
            boolean r13 = r15.isLastOutputBuffer
            com.appsamurai.storyly.exoplayer2.common.Format r14 = r15.outputFormat
            r9 = 1
            r0 = r19
            r1 = r20
            r3 = r22
            boolean r0 = r0.processOutputBuffer(r1, r3, r5, r6, r7, r8, r9, r10, r12, r13, r14)
        L_0x011d:
            if (r0 == 0) goto L_0x013c
            android.media.MediaCodec$BufferInfo r0 = r15.outputBufferInfo
            long r0 = r0.presentationTimeUs
            r15.onProcessedOutputBuffer(r0)
            android.media.MediaCodec$BufferInfo r0 = r15.outputBufferInfo
            int r0 = r0.flags
            r0 = r0 & 4
            if (r0 == 0) goto L_0x0131
            r14 = r16
            goto L_0x0133
        L_0x0131:
            r14 = r17
        L_0x0133:
            r19.resetOutputBuffer()
            if (r14 != 0) goto L_0x0139
            return r16
        L_0x0139:
            r19.processEndOfStream()
        L_0x013c:
            return r17
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.exoplayer2.core.mediacodec.MediaCodecRenderer.drainOutputBuffer(long, long):boolean");
    }

    private boolean drmNeedsCodecReinitialization(MediaCodecInfo mediaCodecInfo, Format format, @Nullable DrmSession drmSession, @Nullable DrmSession drmSession2) throws ExoPlaybackException {
        FrameworkCryptoConfig frameworkCryptoConfig;
        if (drmSession == drmSession2) {
            return false;
        }
        if (drmSession2 == null || drmSession == null || Util.SDK_INT < 23) {
            return true;
        }
        UUID uuid = C.PLAYREADY_UUID;
        if (uuid.equals(drmSession.getSchemeUuid()) || uuid.equals(drmSession2.getSchemeUuid()) || (frameworkCryptoConfig = getFrameworkCryptoConfig(drmSession2)) == null) {
            return true;
        }
        return !mediaCodecInfo.secure && (frameworkCryptoConfig.forceAllowInsecureDecoderComponents ? false : drmSession2.requiresSecureDecoder(format.sampleMimeType));
    }

    private boolean feedInputBuffer() throws ExoPlaybackException {
        int i3;
        if (this.codec == null || (i3 = this.codecDrainState) == 2 || this.inputStreamEnded) {
            return false;
        }
        if (i3 == 0 && shouldReinitCodec()) {
            drainAndReinitializeCodec();
        }
        if (this.inputIndex < 0) {
            int dequeueInputBufferIndex = this.codec.dequeueInputBufferIndex();
            this.inputIndex = dequeueInputBufferIndex;
            if (dequeueInputBufferIndex < 0) {
                return false;
            }
            this.buffer.data = this.codec.getInputBuffer(dequeueInputBufferIndex);
            this.buffer.clear();
        }
        if (this.codecDrainState == 1) {
            if (!this.codecNeedsEosPropagation) {
                this.codecReceivedEos = true;
                this.codec.queueInputBuffer(this.inputIndex, 0, 0, 0, 4);
                resetInputBuffer();
            }
            this.codecDrainState = 2;
            return false;
        } else if (this.codecNeedsAdaptationWorkaroundBuffer) {
            this.codecNeedsAdaptationWorkaroundBuffer = false;
            ByteBuffer byteBuffer = this.buffer.data;
            byte[] bArr = ADAPTATION_WORKAROUND_BUFFER;
            byteBuffer.put(bArr);
            this.codec.queueInputBuffer(this.inputIndex, 0, bArr.length, 0, 0);
            resetInputBuffer();
            this.codecReceivedBuffers = true;
            return true;
        } else {
            if (this.codecReconfigurationState == 1) {
                for (int i4 = 0; i4 < this.codecInputFormat.initializationData.size(); i4++) {
                    this.buffer.data.put(this.codecInputFormat.initializationData.get(i4));
                }
                this.codecReconfigurationState = 2;
            }
            int position = this.buffer.data.position();
            FormatHolder formatHolder = getFormatHolder();
            try {
                int readSource = readSource(formatHolder, this.buffer, 0);
                if (hasReadStreamToEnd()) {
                    this.lastBufferInStreamPresentationTimeUs = this.largestQueuedPresentationTimeUs;
                }
                if (readSource == -3) {
                    return false;
                }
                if (readSource == -5) {
                    if (this.codecReconfigurationState == 2) {
                        this.buffer.clear();
                        this.codecReconfigurationState = 1;
                    }
                    onInputFormatChanged(formatHolder);
                    return true;
                } else if (this.buffer.isEndOfStream()) {
                    if (this.codecReconfigurationState == 2) {
                        this.buffer.clear();
                        this.codecReconfigurationState = 1;
                    }
                    this.inputStreamEnded = true;
                    if (!this.codecReceivedBuffers) {
                        processEndOfStream();
                        return false;
                    }
                    try {
                        if (!this.codecNeedsEosPropagation) {
                            this.codecReceivedEos = true;
                            this.codec.queueInputBuffer(this.inputIndex, 0, 0, 0, 4);
                            resetInputBuffer();
                        }
                        return false;
                    } catch (MediaCodec.CryptoException e3) {
                        throw createRendererException(e3, this.inputFormat, Util.getErrorCodeForMediaDrmErrorCode(e3.getErrorCode()));
                    }
                } else if (this.codecReceivedBuffers || this.buffer.isKeyFrame()) {
                    boolean isEncrypted = this.buffer.isEncrypted();
                    if (isEncrypted) {
                        this.buffer.cryptoInfo.increaseClearDataFirstSubSampleBy(position);
                    }
                    if (this.codecNeedsDiscardToSpsWorkaround && !isEncrypted) {
                        NalUnitUtil.discardToSps(this.buffer.data);
                        if (this.buffer.data.position() == 0) {
                            return true;
                        }
                        this.codecNeedsDiscardToSpsWorkaround = false;
                    }
                    DecoderInputBuffer decoderInputBuffer = this.buffer;
                    long j2 = decoderInputBuffer.timeUs;
                    C2Mp3TimestampTracker c2Mp3TimestampTracker2 = this.c2Mp3TimestampTracker;
                    if (c2Mp3TimestampTracker2 != null) {
                        j2 = c2Mp3TimestampTracker2.updateAndGetPresentationTimeUs(this.inputFormat, decoderInputBuffer);
                        this.largestQueuedPresentationTimeUs = Math.max(this.largestQueuedPresentationTimeUs, this.c2Mp3TimestampTracker.getLastOutputBufferPresentationTimeUs(this.inputFormat));
                    }
                    long j3 = j2;
                    if (this.buffer.isDecodeOnly()) {
                        this.decodeOnlyPresentationTimestamps.add(Long.valueOf(j3));
                    }
                    if (this.waitingForFirstSampleInFormat) {
                        this.formatQueue.add(j3, this.inputFormat);
                        this.waitingForFirstSampleInFormat = false;
                    }
                    this.largestQueuedPresentationTimeUs = Math.max(this.largestQueuedPresentationTimeUs, j3);
                    this.buffer.flip();
                    if (this.buffer.hasSupplementalData()) {
                        handleInputBufferSupplementalData(this.buffer);
                    }
                    onQueueInputBuffer(this.buffer);
                    if (isEncrypted) {
                        try {
                            this.codec.queueSecureInputBuffer(this.inputIndex, 0, this.buffer.cryptoInfo, j3, 0);
                        } catch (MediaCodec.CryptoException e4) {
                            throw createRendererException(e4, this.inputFormat, Util.getErrorCodeForMediaDrmErrorCode(e4.getErrorCode()));
                        }
                    } else {
                        this.codec.queueInputBuffer(this.inputIndex, 0, this.buffer.data.limit(), j3, 0);
                    }
                    resetInputBuffer();
                    this.codecReceivedBuffers = true;
                    this.codecReconfigurationState = 0;
                    this.decoderCounters.queuedInputBufferCount++;
                    return true;
                } else {
                    this.buffer.clear();
                    if (this.codecReconfigurationState == 2) {
                        this.codecReconfigurationState = 1;
                    }
                    return true;
                }
            } catch (DecoderInputBuffer.InsufficientCapacityException e5) {
                onCodecError(e5);
                readSourceOmittingSampleData(0);
                flushCodec();
                return true;
            }
        }
    }

    private void flushCodec() {
        try {
            this.codec.flush();
        } finally {
            resetCodecStateForFlush();
        }
    }

    private List<MediaCodecInfo> getAvailableCodecInfos(boolean z2) throws MediaCodecUtil.DecoderQueryException {
        List<MediaCodecInfo> decoderInfos = getDecoderInfos(this.mediaCodecSelector, this.inputFormat, z2);
        if (decoderInfos.isEmpty() && z2) {
            decoderInfos = getDecoderInfos(this.mediaCodecSelector, this.inputFormat, false);
            if (!decoderInfos.isEmpty()) {
                Log.w(TAG, "Drm session requires secure decoder for " + this.inputFormat.sampleMimeType + ", but no secure decoder available. Trying to proceed with " + decoderInfos + JwtUtilsKt.JWT_DELIMITER);
            }
        }
        return decoderInfos;
    }

    @Nullable
    private FrameworkCryptoConfig getFrameworkCryptoConfig(DrmSession drmSession) throws ExoPlaybackException {
        CryptoConfig cryptoConfig = drmSession.getCryptoConfig();
        if (cryptoConfig == null || (cryptoConfig instanceof FrameworkCryptoConfig)) {
            return (FrameworkCryptoConfig) cryptoConfig;
        }
        throw createRendererException(new IllegalArgumentException("Expecting FrameworkCryptoConfig but found: " + cryptoConfig), this.inputFormat, PlaybackException.ERROR_CODE_DRM_SCHEME_UNSUPPORTED);
    }

    private boolean hasOutputBuffer() {
        return this.outputIndex >= 0;
    }

    private void initBypass(Format format) {
        disableBypass();
        String str = format.sampleMimeType;
        if (MimeTypes.AUDIO_AAC.equals(str) || MimeTypes.AUDIO_MPEG.equals(str) || MimeTypes.AUDIO_OPUS.equals(str)) {
            this.bypassBatchBuffer.setMaxSampleCount(32);
        } else {
            this.bypassBatchBuffer.setMaxSampleCount(1);
        }
        this.bypassEnabled = true;
    }

    /* JADX INFO: finally extract failed */
    private void initCodec(MediaCodecInfo mediaCodecInfo, MediaCrypto mediaCrypto2) throws Exception {
        String str = mediaCodecInfo.name;
        int i3 = Util.SDK_INT;
        float f2 = -1.0f;
        float codecOperatingRateV23 = i3 < 23 ? -1.0f : getCodecOperatingRateV23(this.targetPlaybackSpeed, this.inputFormat, getStreamFormats());
        if (codecOperatingRateV23 > this.assumedMinimumCodecOperatingRate) {
            f2 = codecOperatingRateV23;
        }
        long elapsedRealtime = SystemClock.elapsedRealtime();
        MediaCodecAdapter.Configuration mediaCodecConfiguration = getMediaCodecConfiguration(mediaCodecInfo, this.inputFormat, mediaCrypto2, f2);
        if (i3 >= 31) {
            Api31.setLogSessionIdToMediaCodecFormat(mediaCodecConfiguration, getPlayerId());
        }
        try {
            TraceUtil.beginSection("createCodec:" + str);
            this.codec = this.codecAdapterFactory.createAdapter(mediaCodecConfiguration);
            TraceUtil.endSection();
            long elapsedRealtime2 = SystemClock.elapsedRealtime();
            this.codecInfo = mediaCodecInfo;
            this.codecOperatingRate = f2;
            this.codecInputFormat = this.inputFormat;
            this.codecAdaptationWorkaroundMode = codecAdaptationWorkaroundMode(str);
            this.codecNeedsDiscardToSpsWorkaround = codecNeedsDiscardToSpsWorkaround(str, this.codecInputFormat);
            this.codecNeedsFlushWorkaround = codecNeedsFlushWorkaround(str);
            this.codecNeedsSosFlushWorkaround = codecNeedsSosFlushWorkaround(str);
            this.codecNeedsEosFlushWorkaround = codecNeedsEosFlushWorkaround(str);
            this.codecNeedsEosOutputExceptionWorkaround = codecNeedsEosOutputExceptionWorkaround(str);
            this.codecNeedsEosBufferTimestampWorkaround = codecNeedsEosBufferTimestampWorkaround(str);
            this.codecNeedsMonoChannelCountWorkaround = codecNeedsMonoChannelCountWorkaround(str, this.codecInputFormat);
            boolean z2 = false;
            this.codecNeedsEosPropagation = codecNeedsEosPropagationWorkaround(mediaCodecInfo) || getCodecNeedsEosPropagation();
            if (this.codec.needsReconfiguration()) {
                this.codecReconfigured = true;
                this.codecReconfigurationState = 1;
                if (this.codecAdaptationWorkaroundMode != 0) {
                    z2 = true;
                }
                this.codecNeedsAdaptationWorkaroundBuffer = z2;
            }
            if ("c2.android.mp3.decoder".equals(mediaCodecInfo.name)) {
                this.c2Mp3TimestampTracker = new C2Mp3TimestampTracker();
            }
            if (getState() == 2) {
                this.codecHotswapDeadlineMs = SystemClock.elapsedRealtime() + 1000;
            }
            this.decoderCounters.decoderInitCount++;
            onCodecInitialized(str, mediaCodecConfiguration, elapsedRealtime2, elapsedRealtime2 - elapsedRealtime);
        } catch (Throwable th) {
            TraceUtil.endSection();
            throw th;
        }
    }

    private boolean isDecodeOnlyBuffer(long j2) {
        int size = this.decodeOnlyPresentationTimestamps.size();
        for (int i3 = 0; i3 < size; i3++) {
            if (this.decodeOnlyPresentationTimestamps.get(i3).longValue() == j2) {
                this.decodeOnlyPresentationTimestamps.remove(i3);
                return true;
            }
        }
        return false;
    }

    private static boolean isMediaCodecException(IllegalStateException illegalStateException) {
        if (Util.SDK_INT >= 21 && isMediaCodecExceptionV21(illegalStateException)) {
            return true;
        }
        StackTraceElement[] stackTrace = illegalStateException.getStackTrace();
        return stackTrace.length > 0 && stackTrace[0].getClassName().equals("android.media.MediaCodec");
    }

    @RequiresApi(21)
    private static boolean isMediaCodecExceptionV21(IllegalStateException illegalStateException) {
        return illegalStateException instanceof MediaCodec.CodecException;
    }

    @RequiresApi(21)
    private static boolean isRecoverableMediaCodecExceptionV21(IllegalStateException illegalStateException) {
        if (illegalStateException instanceof MediaCodec.CodecException) {
            return ((MediaCodec.CodecException) illegalStateException).isRecoverable();
        }
        return false;
    }

    private void maybeInitCodecWithFallback(MediaCrypto mediaCrypto2, boolean z2) throws DecoderInitializationException {
        if (this.availableCodecInfos == null) {
            try {
                List<MediaCodecInfo> availableCodecInfos2 = getAvailableCodecInfos(z2);
                ArrayDeque<MediaCodecInfo> arrayDeque = new ArrayDeque<>();
                this.availableCodecInfos = arrayDeque;
                if (this.enableDecoderFallback) {
                    arrayDeque.addAll(availableCodecInfos2);
                } else if (!availableCodecInfos2.isEmpty()) {
                    this.availableCodecInfos.add(availableCodecInfos2.get(0));
                }
                this.preferredDecoderInitializationException = null;
            } catch (MediaCodecUtil.DecoderQueryException e3) {
                throw new DecoderInitializationException(this.inputFormat, (Throwable) e3, z2, -49998);
            }
        }
        if (!this.availableCodecInfos.isEmpty()) {
            MediaCodecInfo peekFirst = this.availableCodecInfos.peekFirst();
            while (this.codec == null) {
                MediaCodecInfo peekFirst2 = this.availableCodecInfos.peekFirst();
                if (shouldInitCodec(peekFirst2)) {
                    try {
                        initCodec(peekFirst2, mediaCrypto2);
                    } catch (Exception e4) {
                        if (peekFirst2 == peekFirst) {
                            Log.w(TAG, "Preferred decoder instantiation failed. Sleeping for 50ms then retrying.");
                            Thread.sleep(50);
                            initCodec(peekFirst2, mediaCrypto2);
                        } else {
                            throw e4;
                        }
                    } catch (Exception e5) {
                        Log.w(TAG, "Failed to initialize decoder: " + peekFirst2, e5);
                        this.availableCodecInfos.removeFirst();
                        DecoderInitializationException decoderInitializationException = new DecoderInitializationException(this.inputFormat, (Throwable) e5, z2, peekFirst2);
                        onCodecError(decoderInitializationException);
                        DecoderInitializationException decoderInitializationException2 = this.preferredDecoderInitializationException;
                        if (decoderInitializationException2 == null) {
                            this.preferredDecoderInitializationException = decoderInitializationException;
                        } else {
                            this.preferredDecoderInitializationException = decoderInitializationException2.copyWithFallbackException(decoderInitializationException);
                        }
                        if (this.availableCodecInfos.isEmpty()) {
                            throw this.preferredDecoderInitializationException;
                        }
                    }
                } else {
                    return;
                }
            }
            this.availableCodecInfos = null;
            return;
        }
        throw new DecoderInitializationException(this.inputFormat, (Throwable) null, z2, -49999);
    }

    @TargetApi(23)
    private void processEndOfStream() throws ExoPlaybackException {
        int i3 = this.codecDrainAction;
        if (i3 == 1) {
            flushCodec();
        } else if (i3 == 2) {
            flushCodec();
            updateDrmSessionV23();
        } else if (i3 != 3) {
            this.outputStreamEnded = true;
            renderToEndOfStream();
        } else {
            reinitializeCodec();
        }
    }

    private void processOutputMediaFormatChanged() {
        this.codecHasOutputMediaFormat = true;
        MediaFormat outputFormat2 = this.codec.getOutputFormat();
        if (this.codecAdaptationWorkaroundMode != 0 && outputFormat2.getInteger("width") == 32 && outputFormat2.getInteger("height") == 32) {
            this.shouldSkipAdaptationWorkaroundOutputBuffer = true;
            return;
        }
        if (this.codecNeedsMonoChannelCountWorkaround) {
            outputFormat2.setInteger("channel-count", 1);
        }
        this.codecOutputMediaFormat = outputFormat2;
        this.codecOutputMediaFormatChanged = true;
    }

    private boolean readSourceOmittingSampleData(int i3) throws ExoPlaybackException {
        FormatHolder formatHolder = getFormatHolder();
        this.noDataBuffer.clear();
        int readSource = readSource(formatHolder, this.noDataBuffer, i3 | 4);
        if (readSource == -5) {
            onInputFormatChanged(formatHolder);
            return true;
        } else if (readSource != -4 || !this.noDataBuffer.isEndOfStream()) {
            return false;
        } else {
            this.inputStreamEnded = true;
            processEndOfStream();
            return false;
        }
    }

    private void reinitializeCodec() throws ExoPlaybackException {
        releaseCodec();
        maybeInitCodecOrBypass();
    }

    private void resetInputBuffer() {
        this.inputIndex = -1;
        this.buffer.data = null;
    }

    private void resetOutputBuffer() {
        this.outputIndex = -1;
        this.outputBuffer = null;
    }

    private void setCodecDrmSession(@Nullable DrmSession drmSession) {
        DrmSession.replaceSession(this.codecDrmSession, drmSession);
        this.codecDrmSession = drmSession;
    }

    private void setSourceDrmSession(@Nullable DrmSession drmSession) {
        DrmSession.replaceSession(this.sourceDrmSession, drmSession);
        this.sourceDrmSession = drmSession;
    }

    private boolean shouldContinueRendering(long j2) {
        return this.renderTimeLimitMs == C.TIME_UNSET || SystemClock.elapsedRealtime() - j2 < this.renderTimeLimitMs;
    }

    public static boolean supportsFormatDrm(Format format) {
        int i3 = format.cryptoType;
        return i3 == 0 || i3 == 2;
    }

    @RequiresApi(23)
    private void updateDrmSessionV23() throws ExoPlaybackException {
        try {
            this.mediaCrypto.setMediaDrmSession(getFrameworkCryptoConfig(this.sourceDrmSession).sessionId);
            setCodecDrmSession(this.sourceDrmSession);
            this.codecDrainState = 0;
            this.codecDrainAction = 0;
        } catch (MediaCryptoException e3) {
            throw createRendererException(e3, this.inputFormat, PlaybackException.ERROR_CODE_DRM_SYSTEM_ERROR);
        }
    }

    public DecoderReuseEvaluation canReuseCodec(MediaCodecInfo mediaCodecInfo, Format format, Format format2) {
        return new DecoderReuseEvaluation(mediaCodecInfo.name, format, format2, 0, 1);
    }

    public MediaCodecDecoderException createDecoderException(Throwable th, @Nullable MediaCodecInfo mediaCodecInfo) {
        return new MediaCodecDecoderException(th, mediaCodecInfo);
    }

    public final boolean flushOrReinitializeCodec() throws ExoPlaybackException {
        boolean flushOrReleaseCodec = flushOrReleaseCodec();
        if (flushOrReleaseCodec) {
            maybeInitCodecOrBypass();
        }
        return flushOrReleaseCodec;
    }

    public boolean flushOrReleaseCodec() {
        if (this.codec == null) {
            return false;
        }
        int i3 = this.codecDrainAction;
        if (i3 == 3 || this.codecNeedsFlushWorkaround || ((this.codecNeedsSosFlushWorkaround && !this.codecHasOutputMediaFormat) || (this.codecNeedsEosFlushWorkaround && this.codecReceivedEos))) {
            releaseCodec();
            return true;
        }
        if (i3 == 2) {
            int i4 = Util.SDK_INT;
            Assertions.checkState(i4 >= 23);
            if (i4 >= 23) {
                try {
                    updateDrmSessionV23();
                } catch (ExoPlaybackException e3) {
                    Log.w(TAG, "Failed to update the DRM session, releasing the codec instead.", e3);
                    releaseCodec();
                    return true;
                }
            }
        }
        flushCodec();
        return false;
    }

    @Nullable
    public final MediaCodecAdapter getCodec() {
        return this.codec;
    }

    @Nullable
    public final MediaCodecInfo getCodecInfo() {
        return this.codecInfo;
    }

    public boolean getCodecNeedsEosPropagation() {
        return false;
    }

    public float getCodecOperatingRate() {
        return this.codecOperatingRate;
    }

    public float getCodecOperatingRateV23(float f2, Format format, Format[] formatArr) {
        return -1.0f;
    }

    @Nullable
    public final MediaFormat getCodecOutputMediaFormat() {
        return this.codecOutputMediaFormat;
    }

    public abstract List<MediaCodecInfo> getDecoderInfos(MediaCodecSelector mediaCodecSelector2, Format format, boolean z2) throws MediaCodecUtil.DecoderQueryException;

    public abstract MediaCodecAdapter.Configuration getMediaCodecConfiguration(MediaCodecInfo mediaCodecInfo, Format format, @Nullable MediaCrypto mediaCrypto2, float f2);

    public final long getOutputStreamOffsetUs() {
        return this.outputStreamOffsetUs;
    }

    public float getPlaybackSpeed() {
        return this.currentPlaybackSpeed;
    }

    public void handleInputBufferSupplementalData(DecoderInputBuffer decoderInputBuffer) throws ExoPlaybackException {
    }

    public boolean isEnded() {
        return this.outputStreamEnded;
    }

    public boolean isReady() {
        return this.inputFormat != null && (isSourceReady() || hasOutputBuffer() || (this.codecHotswapDeadlineMs != C.TIME_UNSET && SystemClock.elapsedRealtime() < this.codecHotswapDeadlineMs));
    }

    public final void maybeInitCodecOrBypass() throws ExoPlaybackException {
        Format format;
        if (this.codec == null && !this.bypassEnabled && (format = this.inputFormat) != null) {
            if (this.sourceDrmSession != null || !shouldUseBypass(format)) {
                setCodecDrmSession(this.sourceDrmSession);
                String str = this.inputFormat.sampleMimeType;
                DrmSession drmSession = this.codecDrmSession;
                if (drmSession != null) {
                    if (this.mediaCrypto == null) {
                        FrameworkCryptoConfig frameworkCryptoConfig = getFrameworkCryptoConfig(drmSession);
                        if (frameworkCryptoConfig != null) {
                            try {
                                MediaCrypto mediaCrypto2 = new MediaCrypto(frameworkCryptoConfig.uuid, frameworkCryptoConfig.sessionId);
                                this.mediaCrypto = mediaCrypto2;
                                this.mediaCryptoRequiresSecureDecoder = !frameworkCryptoConfig.forceAllowInsecureDecoderComponents && mediaCrypto2.requiresSecureDecoderComponent(str);
                            } catch (MediaCryptoException e3) {
                                throw createRendererException(e3, this.inputFormat, PlaybackException.ERROR_CODE_DRM_SYSTEM_ERROR);
                            }
                        } else if (this.codecDrmSession.getError() == null) {
                            return;
                        }
                    }
                    if (FrameworkCryptoConfig.WORKAROUND_DEVICE_NEEDS_KEYS_TO_CONFIGURE_CODEC) {
                        int state = this.codecDrmSession.getState();
                        if (state == 1) {
                            DrmSession.DrmSessionException drmSessionException = (DrmSession.DrmSessionException) Assertions.checkNotNull(this.codecDrmSession.getError());
                            throw createRendererException(drmSessionException, this.inputFormat, drmSessionException.errorCode);
                        } else if (state != 4) {
                            return;
                        }
                    }
                }
                try {
                    maybeInitCodecWithFallback(this.mediaCrypto, this.mediaCryptoRequiresSecureDecoder);
                } catch (DecoderInitializationException e4) {
                    throw createRendererException(e4, this.inputFormat, PlaybackException.ERROR_CODE_DECODER_INIT_FAILED);
                }
            } else {
                initBypass(this.inputFormat);
            }
        }
    }

    public void onCodecError(Exception exc) {
    }

    public void onCodecInitialized(String str, MediaCodecAdapter.Configuration configuration, long j2, long j3) {
    }

    public void onCodecReleased(String str) {
    }

    public void onDisabled() {
        this.inputFormat = null;
        this.outputStreamStartPositionUs = C.TIME_UNSET;
        this.outputStreamOffsetUs = C.TIME_UNSET;
        this.pendingOutputStreamOffsetCount = 0;
        flushOrReleaseCodec();
    }

    public void onEnabled(boolean z2, boolean z3) throws ExoPlaybackException {
        this.decoderCounters = new DecoderCounters();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0081, code lost:
        if (drainAndUpdateCodecDrmSessionV23() == false) goto L_0x0083;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0083, code lost:
        r7 = 2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x00b4, code lost:
        if (drainAndUpdateCodecDrmSessionV23() == false) goto L_0x0083;
     */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x00e0  */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x00ea A[RETURN] */
    @androidx.annotation.CallSuper
    @androidx.annotation.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.appsamurai.storyly.exoplayer2.core.decoder.DecoderReuseEvaluation onInputFormatChanged(com.appsamurai.storyly.exoplayer2.core.FormatHolder r12) throws com.appsamurai.storyly.exoplayer2.core.ExoPlaybackException {
        /*
            r11 = this;
            r0 = 1
            r11.waitingForFirstSampleInFormat = r0
            com.appsamurai.storyly.exoplayer2.common.Format r1 = r12.format
            java.lang.Object r1 = com.appsamurai.storyly.exoplayer2.common.util.Assertions.checkNotNull(r1)
            r5 = r1
            com.appsamurai.storyly.exoplayer2.common.Format r5 = (com.appsamurai.storyly.exoplayer2.common.Format) r5
            java.lang.String r1 = r5.sampleMimeType
            if (r1 == 0) goto L_0x00eb
            com.appsamurai.storyly.exoplayer2.core.drm.DrmSession r12 = r12.drmSession
            r11.setSourceDrmSession(r12)
            r11.inputFormat = r5
            boolean r12 = r11.bypassEnabled
            r1 = 0
            if (r12 == 0) goto L_0x001f
            r11.bypassDrainAndReinitialize = r0
            return r1
        L_0x001f:
            com.appsamurai.storyly.exoplayer2.core.mediacodec.MediaCodecAdapter r12 = r11.codec
            if (r12 != 0) goto L_0x0029
            r11.availableCodecInfos = r1
            r11.maybeInitCodecOrBypass()
            return r1
        L_0x0029:
            com.appsamurai.storyly.exoplayer2.core.mediacodec.MediaCodecInfo r1 = r11.codecInfo
            com.appsamurai.storyly.exoplayer2.common.Format r4 = r11.codecInputFormat
            com.appsamurai.storyly.exoplayer2.core.drm.DrmSession r2 = r11.codecDrmSession
            com.appsamurai.storyly.exoplayer2.core.drm.DrmSession r3 = r11.sourceDrmSession
            boolean r2 = r11.drmNeedsCodecReinitialization(r1, r5, r2, r3)
            if (r2 == 0) goto L_0x0046
            r11.drainAndReinitializeCodec()
            com.appsamurai.storyly.exoplayer2.core.decoder.DecoderReuseEvaluation r11 = new com.appsamurai.storyly.exoplayer2.core.decoder.DecoderReuseEvaluation
            java.lang.String r3 = r1.name
            r6 = 0
            r7 = 128(0x80, float:1.794E-43)
            r2 = r11
            r2.<init>(r3, r4, r5, r6, r7)
            return r11
        L_0x0046:
            com.appsamurai.storyly.exoplayer2.core.drm.DrmSession r2 = r11.sourceDrmSession
            com.appsamurai.storyly.exoplayer2.core.drm.DrmSession r3 = r11.codecDrmSession
            r6 = 0
            if (r2 == r3) goto L_0x004f
            r2 = r0
            goto L_0x0050
        L_0x004f:
            r2 = r6
        L_0x0050:
            if (r2 == 0) goto L_0x005b
            int r3 = com.appsamurai.storyly.exoplayer2.common.util.Util.SDK_INT
            r7 = 23
            if (r3 < r7) goto L_0x0059
            goto L_0x005b
        L_0x0059:
            r3 = r6
            goto L_0x005c
        L_0x005b:
            r3 = r0
        L_0x005c:
            com.appsamurai.storyly.exoplayer2.common.util.Assertions.checkState(r3)
            com.appsamurai.storyly.exoplayer2.core.decoder.DecoderReuseEvaluation r3 = r11.canReuseCodec(r1, r4, r5)
            int r7 = r3.result
            r8 = 3
            if (r7 == 0) goto L_0x00d0
            r9 = 16
            r10 = 2
            if (r7 == r0) goto L_0x00b7
            if (r7 == r10) goto L_0x008b
            if (r7 != r8) goto L_0x0085
            boolean r0 = r11.updateCodecOperatingRate(r5)
            if (r0 != 0) goto L_0x0079
        L_0x0077:
            r7 = r9
            goto L_0x00d4
        L_0x0079:
            r11.codecInputFormat = r5
            if (r2 == 0) goto L_0x00d3
            boolean r0 = r11.drainAndUpdateCodecDrmSessionV23()
            if (r0 != 0) goto L_0x00d3
        L_0x0083:
            r7 = r10
            goto L_0x00d4
        L_0x0085:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            r11.<init>()
            throw r11
        L_0x008b:
            boolean r7 = r11.updateCodecOperatingRate(r5)
            if (r7 != 0) goto L_0x0092
            goto L_0x0077
        L_0x0092:
            r11.codecReconfigured = r0
            r11.codecReconfigurationState = r0
            int r7 = r11.codecAdaptationWorkaroundMode
            if (r7 == r10) goto L_0x00aa
            if (r7 != r0) goto L_0x00a9
            int r7 = r5.width
            int r9 = r4.width
            if (r7 != r9) goto L_0x00a9
            int r7 = r5.height
            int r9 = r4.height
            if (r7 != r9) goto L_0x00a9
            goto L_0x00aa
        L_0x00a9:
            r0 = r6
        L_0x00aa:
            r11.codecNeedsAdaptationWorkaroundBuffer = r0
            r11.codecInputFormat = r5
            if (r2 == 0) goto L_0x00d3
            boolean r0 = r11.drainAndUpdateCodecDrmSessionV23()
            if (r0 != 0) goto L_0x00d3
            goto L_0x0083
        L_0x00b7:
            boolean r0 = r11.updateCodecOperatingRate(r5)
            if (r0 != 0) goto L_0x00be
            goto L_0x0077
        L_0x00be:
            r11.codecInputFormat = r5
            if (r2 == 0) goto L_0x00c9
            boolean r0 = r11.drainAndUpdateCodecDrmSessionV23()
            if (r0 != 0) goto L_0x00d3
            goto L_0x0083
        L_0x00c9:
            boolean r0 = r11.drainAndFlushCodec()
            if (r0 != 0) goto L_0x00d3
            goto L_0x0083
        L_0x00d0:
            r11.drainAndReinitializeCodec()
        L_0x00d3:
            r7 = r6
        L_0x00d4:
            int r0 = r3.result
            if (r0 == 0) goto L_0x00ea
            com.appsamurai.storyly.exoplayer2.core.mediacodec.MediaCodecAdapter r0 = r11.codec
            if (r0 != r12) goto L_0x00e0
            int r11 = r11.codecDrainAction
            if (r11 != r8) goto L_0x00ea
        L_0x00e0:
            com.appsamurai.storyly.exoplayer2.core.decoder.DecoderReuseEvaluation r11 = new com.appsamurai.storyly.exoplayer2.core.decoder.DecoderReuseEvaluation
            java.lang.String r3 = r1.name
            r6 = 0
            r2 = r11
            r2.<init>(r3, r4, r5, r6, r7)
            return r11
        L_0x00ea:
            return r3
        L_0x00eb:
            java.lang.IllegalArgumentException r12 = new java.lang.IllegalArgumentException
            r12.<init>()
            r0 = 4005(0xfa5, float:5.612E-42)
            com.appsamurai.storyly.exoplayer2.core.ExoPlaybackException r11 = r11.createRendererException(r12, r5, r0)
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.exoplayer2.core.mediacodec.MediaCodecRenderer.onInputFormatChanged(com.appsamurai.storyly.exoplayer2.core.FormatHolder):com.appsamurai.storyly.exoplayer2.core.decoder.DecoderReuseEvaluation");
    }

    public void onOutputFormatChanged(Format format, @Nullable MediaFormat mediaFormat) throws ExoPlaybackException {
    }

    public void onPositionReset(long j2, boolean z2) throws ExoPlaybackException {
        this.inputStreamEnded = false;
        this.outputStreamEnded = false;
        this.pendingOutputEndOfStream = false;
        if (this.bypassEnabled) {
            this.bypassBatchBuffer.clear();
            this.bypassSampleBuffer.clear();
            this.bypassSampleBufferPending = false;
        } else {
            flushOrReinitializeCodec();
        }
        if (this.formatQueue.size() > 0) {
            this.waitingForFirstSampleInFormat = true;
        }
        this.formatQueue.clear();
        int i3 = this.pendingOutputStreamOffsetCount;
        if (i3 != 0) {
            this.outputStreamOffsetUs = this.pendingOutputStreamOffsetsUs[i3 - 1];
            this.outputStreamStartPositionUs = this.pendingOutputStreamStartPositionsUs[i3 - 1];
            this.pendingOutputStreamOffsetCount = 0;
        }
    }

    @CallSuper
    public void onProcessedOutputBuffer(long j2) {
        while (true) {
            int i3 = this.pendingOutputStreamOffsetCount;
            if (i3 != 0 && j2 >= this.pendingOutputStreamSwitchTimesUs[0]) {
                long[] jArr = this.pendingOutputStreamStartPositionsUs;
                this.outputStreamStartPositionUs = jArr[0];
                this.outputStreamOffsetUs = this.pendingOutputStreamOffsetsUs[0];
                int i4 = i3 - 1;
                this.pendingOutputStreamOffsetCount = i4;
                System.arraycopy(jArr, 1, jArr, 0, i4);
                long[] jArr2 = this.pendingOutputStreamOffsetsUs;
                System.arraycopy(jArr2, 1, jArr2, 0, this.pendingOutputStreamOffsetCount);
                long[] jArr3 = this.pendingOutputStreamSwitchTimesUs;
                System.arraycopy(jArr3, 1, jArr3, 0, this.pendingOutputStreamOffsetCount);
                onProcessedStreamChange();
            } else {
                return;
            }
        }
    }

    public void onProcessedStreamChange() {
    }

    public void onQueueInputBuffer(DecoderInputBuffer decoderInputBuffer) throws ExoPlaybackException {
    }

    public void onReset() {
        try {
            disableBypass();
            releaseCodec();
        } finally {
            setSourceDrmSession((DrmSession) null);
        }
    }

    public void onStarted() {
    }

    public void onStopped() {
    }

    public void onStreamChanged(Format[] formatArr, long j2, long j3) throws ExoPlaybackException {
        boolean z2 = true;
        if (this.outputStreamOffsetUs == C.TIME_UNSET) {
            if (this.outputStreamStartPositionUs != C.TIME_UNSET) {
                z2 = false;
            }
            Assertions.checkState(z2);
            this.outputStreamStartPositionUs = j2;
            this.outputStreamOffsetUs = j3;
            return;
        }
        int i3 = this.pendingOutputStreamOffsetCount;
        if (i3 == this.pendingOutputStreamOffsetsUs.length) {
            Log.w(TAG, "Too many stream changes, so dropping offset: " + this.pendingOutputStreamOffsetsUs[this.pendingOutputStreamOffsetCount - 1]);
        } else {
            this.pendingOutputStreamOffsetCount = i3 + 1;
        }
        long[] jArr = this.pendingOutputStreamStartPositionsUs;
        int i4 = this.pendingOutputStreamOffsetCount;
        jArr[i4 - 1] = j2;
        this.pendingOutputStreamOffsetsUs[i4 - 1] = j3;
        this.pendingOutputStreamSwitchTimesUs[i4 - 1] = this.largestQueuedPresentationTimeUs;
    }

    public abstract boolean processOutputBuffer(long j2, long j3, @Nullable MediaCodecAdapter mediaCodecAdapter, @Nullable ByteBuffer byteBuffer, int i3, int i4, int i5, long j4, boolean z2, boolean z3, Format format) throws ExoPlaybackException;

    public void releaseCodec() {
        try {
            MediaCodecAdapter mediaCodecAdapter = this.codec;
            if (mediaCodecAdapter != null) {
                mediaCodecAdapter.release();
                this.decoderCounters.decoderReleaseCount++;
                onCodecReleased(this.codecInfo.name);
            }
            this.codec = null;
            try {
                MediaCrypto mediaCrypto2 = this.mediaCrypto;
                if (mediaCrypto2 != null) {
                    mediaCrypto2.release();
                }
            } finally {
                this.mediaCrypto = null;
                setCodecDrmSession((DrmSession) null);
                resetCodecStateForRelease();
            }
        } catch (Throwable th) {
            this.codec = null;
            MediaCrypto mediaCrypto3 = this.mediaCrypto;
            if (mediaCrypto3 != null) {
                mediaCrypto3.release();
            }
            throw th;
        } finally {
            this.mediaCrypto = null;
            setCodecDrmSession((DrmSession) null);
            resetCodecStateForRelease();
        }
    }

    public void render(long j2, long j3) throws ExoPlaybackException {
        boolean z2 = false;
        if (this.pendingOutputEndOfStream) {
            this.pendingOutputEndOfStream = false;
            processEndOfStream();
        }
        ExoPlaybackException exoPlaybackException = this.pendingPlaybackException;
        if (exoPlaybackException == null) {
            try {
                if (this.outputStreamEnded) {
                    renderToEndOfStream();
                } else if (this.inputFormat != null || readSourceOmittingSampleData(2)) {
                    maybeInitCodecOrBypass();
                    if (this.bypassEnabled) {
                        TraceUtil.beginSection("bypassRender");
                        while (bypassRender(j2, j3)) {
                        }
                        TraceUtil.endSection();
                    } else if (this.codec != null) {
                        long elapsedRealtime = SystemClock.elapsedRealtime();
                        TraceUtil.beginSection("drainAndFeed");
                        while (drainOutputBuffer(j2, j3) && shouldContinueRendering(elapsedRealtime)) {
                        }
                        while (feedInputBuffer() && shouldContinueRendering(elapsedRealtime)) {
                        }
                        TraceUtil.endSection();
                    } else {
                        this.decoderCounters.skippedInputBufferCount += skipSource(j2);
                        readSourceOmittingSampleData(1);
                    }
                    this.decoderCounters.ensureUpdated();
                }
            } catch (IllegalStateException e3) {
                if (isMediaCodecException(e3)) {
                    onCodecError(e3);
                    if (Util.SDK_INT >= 21 && isRecoverableMediaCodecExceptionV21(e3)) {
                        z2 = true;
                    }
                    if (z2) {
                        releaseCodec();
                    }
                    throw createRendererException(createDecoderException(e3, getCodecInfo()), this.inputFormat, z2, PlaybackException.ERROR_CODE_DECODING_FAILED);
                }
                throw e3;
            }
        } else {
            this.pendingPlaybackException = null;
            throw exoPlaybackException;
        }
    }

    public void renderToEndOfStream() throws ExoPlaybackException {
    }

    @CallSuper
    public void resetCodecStateForFlush() {
        resetInputBuffer();
        resetOutputBuffer();
        this.codecHotswapDeadlineMs = C.TIME_UNSET;
        this.codecReceivedEos = false;
        this.codecReceivedBuffers = false;
        this.codecNeedsAdaptationWorkaroundBuffer = false;
        this.shouldSkipAdaptationWorkaroundOutputBuffer = false;
        this.isDecodeOnlyOutputBuffer = false;
        this.isLastOutputBuffer = false;
        this.decodeOnlyPresentationTimestamps.clear();
        this.largestQueuedPresentationTimeUs = C.TIME_UNSET;
        this.lastBufferInStreamPresentationTimeUs = C.TIME_UNSET;
        C2Mp3TimestampTracker c2Mp3TimestampTracker2 = this.c2Mp3TimestampTracker;
        if (c2Mp3TimestampTracker2 != null) {
            c2Mp3TimestampTracker2.reset();
        }
        this.codecDrainState = 0;
        this.codecDrainAction = 0;
        this.codecReconfigurationState = this.codecReconfigured ? 1 : 0;
    }

    @CallSuper
    public void resetCodecStateForRelease() {
        resetCodecStateForFlush();
        this.pendingPlaybackException = null;
        this.c2Mp3TimestampTracker = null;
        this.availableCodecInfos = null;
        this.codecInfo = null;
        this.codecInputFormat = null;
        this.codecOutputMediaFormat = null;
        this.codecOutputMediaFormatChanged = false;
        this.codecHasOutputMediaFormat = false;
        this.codecOperatingRate = -1.0f;
        this.codecAdaptationWorkaroundMode = 0;
        this.codecNeedsDiscardToSpsWorkaround = false;
        this.codecNeedsFlushWorkaround = false;
        this.codecNeedsSosFlushWorkaround = false;
        this.codecNeedsEosFlushWorkaround = false;
        this.codecNeedsEosOutputExceptionWorkaround = false;
        this.codecNeedsEosBufferTimestampWorkaround = false;
        this.codecNeedsMonoChannelCountWorkaround = false;
        this.codecNeedsEosPropagation = false;
        this.codecReconfigured = false;
        this.codecReconfigurationState = 0;
        this.mediaCryptoRequiresSecureDecoder = false;
    }

    public final void setPendingOutputEndOfStream() {
        this.pendingOutputEndOfStream = true;
    }

    public final void setPendingPlaybackException(ExoPlaybackException exoPlaybackException) {
        this.pendingPlaybackException = exoPlaybackException;
    }

    public void setPlaybackSpeed(float f2, float f3) throws ExoPlaybackException {
        this.currentPlaybackSpeed = f2;
        this.targetPlaybackSpeed = f3;
        updateCodecOperatingRate(this.codecInputFormat);
    }

    public void setRenderTimeLimitMs(long j2) {
        this.renderTimeLimitMs = j2;
    }

    public boolean shouldInitCodec(MediaCodecInfo mediaCodecInfo) {
        return true;
    }

    public boolean shouldReinitCodec() {
        return false;
    }

    public boolean shouldUseBypass(Format format) {
        return false;
    }

    public final int supportsFormat(Format format) throws ExoPlaybackException {
        try {
            return supportsFormat(this.mediaCodecSelector, format);
        } catch (MediaCodecUtil.DecoderQueryException e3) {
            throw createRendererException(e3, format, PlaybackException.ERROR_CODE_DECODER_QUERY_FAILED);
        }
    }

    public abstract int supportsFormat(MediaCodecSelector mediaCodecSelector2, Format format) throws MediaCodecUtil.DecoderQueryException;

    public final int supportsMixedMimeTypeAdaptation() {
        return 8;
    }

    public final boolean updateCodecOperatingRate() throws ExoPlaybackException {
        return updateCodecOperatingRate(this.codecInputFormat);
    }

    public final void updateOutputFormatForTime(long j2) throws ExoPlaybackException {
        Format pollFloor = this.formatQueue.pollFloor(j2);
        if (pollFloor == null && this.codecOutputMediaFormatChanged) {
            pollFloor = this.formatQueue.pollFirst();
        }
        if (pollFloor != null) {
            this.outputFormat = pollFloor;
        } else if (!this.codecOutputMediaFormatChanged || this.outputFormat == null) {
            return;
        }
        onOutputFormatChanged(this.outputFormat, this.codecOutputMediaFormat);
        this.codecOutputMediaFormatChanged = false;
    }

    private boolean updateCodecOperatingRate(Format format) throws ExoPlaybackException {
        if (!(Util.SDK_INT < 23 || this.codec == null || this.codecDrainAction == 3 || getState() == 0)) {
            float codecOperatingRateV23 = getCodecOperatingRateV23(this.targetPlaybackSpeed, format, getStreamFormats());
            float f2 = this.codecOperatingRate;
            if (f2 == codecOperatingRateV23) {
                return true;
            }
            if (codecOperatingRateV23 == -1.0f) {
                drainAndReinitializeCodec();
                return false;
            } else if (f2 == -1.0f && codecOperatingRateV23 <= this.assumedMinimumCodecOperatingRate) {
                return true;
            } else {
                Bundle bundle = new Bundle();
                bundle.putFloat("operating-rate", codecOperatingRateV23);
                this.codec.setParameters(bundle);
                this.codecOperatingRate = codecOperatingRateV23;
            }
        }
        return true;
    }

    public static class DecoderInitializationException extends Exception {
        private static final int CUSTOM_ERROR_CODE_BASE = -50000;
        private static final int DECODER_QUERY_ERROR = -49998;
        private static final int NO_SUITABLE_DECODER_ERROR = -49999;
        @Nullable
        public final MediaCodecInfo codecInfo;
        @Nullable
        public final String diagnosticInfo;
        @Nullable
        public final DecoderInitializationException fallbackDecoderInitializationException;
        public final String mimeType;
        public final boolean secureDecoderRequired;

        public DecoderInitializationException(Format format, @Nullable Throwable th, boolean z2, int i3) {
            this("Decoder init failed: [" + i3 + "], " + format, th, format.sampleMimeType, z2, (MediaCodecInfo) null, buildCustomDiagnosticInfo(i3), (DecoderInitializationException) null);
        }

        private static String buildCustomDiagnosticInfo(int i3) {
            String str = i3 < 0 ? "neg_" : "";
            return "com.google.android.exoplayer2.mediacodec.MediaCodecRenderer_" + str + Math.abs(i3);
        }

        /* access modifiers changed from: private */
        @CheckResult
        public DecoderInitializationException copyWithFallbackException(DecoderInitializationException decoderInitializationException) {
            return new DecoderInitializationException(getMessage(), getCause(), this.mimeType, this.secureDecoderRequired, this.codecInfo, this.diagnosticInfo, decoderInitializationException);
        }

        @RequiresApi(21)
        @Nullable
        private static String getDiagnosticInfoV21(@Nullable Throwable th) {
            if (th instanceof MediaCodec.CodecException) {
                return ((MediaCodec.CodecException) th).getDiagnosticInfo();
            }
            return null;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public DecoderInitializationException(Format format, @Nullable Throwable th, boolean z2, MediaCodecInfo mediaCodecInfo) {
            this("Decoder init failed: " + mediaCodecInfo.name + ", " + format, th, format.sampleMimeType, z2, mediaCodecInfo, Util.SDK_INT >= 21 ? getDiagnosticInfoV21(th) : null, (DecoderInitializationException) null);
        }

        private DecoderInitializationException(String str, @Nullable Throwable th, String str2, boolean z2, @Nullable MediaCodecInfo mediaCodecInfo, @Nullable String str3, @Nullable DecoderInitializationException decoderInitializationException) {
            super(str, th);
            this.mimeType = str2;
            this.secureDecoderRequired = z2;
            this.codecInfo = mediaCodecInfo;
            this.diagnosticInfo = str3;
            this.fallbackDecoderInitializationException = decoderInitializationException;
        }
    }
}
