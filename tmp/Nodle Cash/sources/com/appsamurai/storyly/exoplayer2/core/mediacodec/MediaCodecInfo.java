package com.appsamurai.storyly.exoplayer2.core.mediacodec;

import android.graphics.Point;
import android.media.MediaCodecInfo;
import android.support.v4.media.session.a;
import android.util.Pair;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.VisibleForTesting;
import androidx.camera.camera2.internal.C0118y;
import com.appsamurai.storyly.exoplayer2.common.Format;
import com.appsamurai.storyly.exoplayer2.common.util.Assertions;
import com.appsamurai.storyly.exoplayer2.common.util.Log;
import com.appsamurai.storyly.exoplayer2.common.util.MimeTypes;
import com.appsamurai.storyly.exoplayer2.common.util.Util;
import com.appsamurai.storyly.exoplayer2.core.decoder.DecoderReuseEvaluation;
import com.appsamurai.storyly.exoplayer2.core.mediacodec.MediaCodecUtil;

public final class MediaCodecInfo {
    public static final int MAX_SUPPORTED_INSTANCES_UNKNOWN = -1;
    public static final String TAG = "MediaCodecInfo";
    public final boolean adaptive;
    @Nullable
    public final MediaCodecInfo.CodecCapabilities capabilities;
    public final String codecMimeType;
    public final boolean hardwareAccelerated;
    private final boolean isVideo;
    public final String mimeType;
    public final String name;
    public final boolean secure;
    public final boolean softwareOnly;
    public final boolean tunneling;
    public final boolean vendor;

    @VisibleForTesting
    public MediaCodecInfo(String str, String str2, String str3, @Nullable MediaCodecInfo.CodecCapabilities codecCapabilities, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7) {
        this.name = (String) Assertions.checkNotNull(str);
        this.mimeType = str2;
        this.codecMimeType = str3;
        this.capabilities = codecCapabilities;
        this.hardwareAccelerated = z2;
        this.softwareOnly = z3;
        this.vendor = z4;
        this.adaptive = z5;
        this.tunneling = z6;
        this.secure = z7;
        this.isVideo = MimeTypes.isVideo(str2);
    }

    private static int adjustMaxInputChannelCount(String str, String str2, int i3) {
        if (i3 > 1 || ((Util.SDK_INT >= 26 && i3 > 0) || MimeTypes.AUDIO_MPEG.equals(str2) || MimeTypes.AUDIO_AMR_NB.equals(str2) || MimeTypes.AUDIO_AMR_WB.equals(str2) || MimeTypes.AUDIO_AAC.equals(str2) || MimeTypes.AUDIO_VORBIS.equals(str2) || MimeTypes.AUDIO_OPUS.equals(str2) || MimeTypes.AUDIO_RAW.equals(str2) || MimeTypes.AUDIO_FLAC.equals(str2) || MimeTypes.AUDIO_ALAW.equals(str2) || MimeTypes.AUDIO_MLAW.equals(str2) || MimeTypes.AUDIO_MSGSM.equals(str2))) {
            return i3;
        }
        int i4 = MimeTypes.AUDIO_AC3.equals(str2) ? 6 : MimeTypes.AUDIO_E_AC3.equals(str2) ? 16 : 30;
        StringBuilder x2 = a.x("AssumedMaxChannelAdjustment: ", str, ", [", i3, " to ");
        x2.append(i4);
        x2.append("]");
        Log.w(TAG, x2.toString());
        return i4;
    }

    @RequiresApi(21)
    private static boolean areSizeAndRateSupportedV21(MediaCodecInfo.VideoCapabilities videoCapabilities, int i3, int i4, double d2) {
        Point alignVideoSizeV21 = alignVideoSizeV21(videoCapabilities, i3, i4);
        int i5 = alignVideoSizeV21.x;
        int i6 = alignVideoSizeV21.y;
        return (d2 == -1.0d || d2 < 1.0d) ? videoCapabilities.isSizeSupported(i5, i6) : videoCapabilities.areSizeAndRateSupported(i5, i6, Math.floor(d2));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:1:0x0002, code lost:
        r2 = r2.getVideoCapabilities();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static android.media.MediaCodecInfo.CodecProfileLevel[] estimateLegacyVp9ProfileLevels(@androidx.annotation.Nullable android.media.MediaCodecInfo.CodecCapabilities r2) {
        /*
            if (r2 == 0) goto L_0x0017
            android.media.MediaCodecInfo$VideoCapabilities r2 = r2.getVideoCapabilities()
            if (r2 == 0) goto L_0x0017
            android.util.Range r2 = r2.getBitrateRange()
            java.lang.Comparable r2 = r2.getUpper()
            java.lang.Integer r2 = (java.lang.Integer) r2
            int r2 = r2.intValue()
            goto L_0x0018
        L_0x0017:
            r2 = 0
        L_0x0018:
            r0 = 180000000(0xaba9500, float:1.7967196E-32)
            r1 = 1
            if (r2 < r0) goto L_0x0021
            r2 = 1024(0x400, float:1.435E-42)
            goto L_0x0068
        L_0x0021:
            r0 = 120000000(0x7270e00, float:1.2567798E-34)
            if (r2 < r0) goto L_0x0029
            r2 = 512(0x200, float:7.175E-43)
            goto L_0x0068
        L_0x0029:
            r0 = 60000000(0x3938700, float:8.670878E-37)
            if (r2 < r0) goto L_0x0031
            r2 = 256(0x100, float:3.59E-43)
            goto L_0x0068
        L_0x0031:
            r0 = 30000000(0x1c9c380, float:7.411627E-38)
            if (r2 < r0) goto L_0x0039
            r2 = 128(0x80, float:1.794E-43)
            goto L_0x0068
        L_0x0039:
            r0 = 18000000(0x112a880, float:2.6936858E-38)
            if (r2 < r0) goto L_0x0041
            r2 = 64
            goto L_0x0068
        L_0x0041:
            r0 = 12000000(0xb71b00, float:1.6815582E-38)
            if (r2 < r0) goto L_0x0049
            r2 = 32
            goto L_0x0068
        L_0x0049:
            r0 = 7200000(0x6ddd00, float:1.0089349E-38)
            if (r2 < r0) goto L_0x0051
            r2 = 16
            goto L_0x0068
        L_0x0051:
            r0 = 3600000(0x36ee80, float:5.044674E-39)
            if (r2 < r0) goto L_0x0059
            r2 = 8
            goto L_0x0068
        L_0x0059:
            r0 = 1800000(0x1b7740, float:2.522337E-39)
            if (r2 < r0) goto L_0x0060
            r2 = 4
            goto L_0x0068
        L_0x0060:
            r0 = 800000(0xc3500, float:1.121039E-39)
            if (r2 < r0) goto L_0x0067
            r2 = 2
            goto L_0x0068
        L_0x0067:
            r2 = r1
        L_0x0068:
            android.media.MediaCodecInfo$CodecProfileLevel r0 = new android.media.MediaCodecInfo$CodecProfileLevel
            r0.<init>()
            r0.profile = r1
            r0.level = r2
            android.media.MediaCodecInfo$CodecProfileLevel[] r2 = new android.media.MediaCodecInfo.CodecProfileLevel[]{r0}
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.exoplayer2.core.mediacodec.MediaCodecInfo.estimateLegacyVp9ProfileLevels(android.media.MediaCodecInfo$CodecCapabilities):android.media.MediaCodecInfo$CodecProfileLevel[]");
    }

    @RequiresApi(23)
    private static int getMaxSupportedInstancesV23(MediaCodecInfo.CodecCapabilities codecCapabilities) {
        return codecCapabilities.getMaxSupportedInstances();
    }

    private static boolean isAdaptive(MediaCodecInfo.CodecCapabilities codecCapabilities) {
        return Util.SDK_INT >= 19 && isAdaptiveV19(codecCapabilities);
    }

    @RequiresApi(19)
    private static boolean isAdaptiveV19(MediaCodecInfo.CodecCapabilities codecCapabilities) {
        return codecCapabilities.isFeatureSupported("adaptive-playback");
    }

    private boolean isCodecProfileAndLevelSupported(Format format) {
        Pair<Integer, Integer> codecProfileAndLevel;
        if (format.codecs == null || (codecProfileAndLevel = MediaCodecUtil.getCodecProfileAndLevel(format)) == null) {
            return true;
        }
        int intValue = ((Integer) codecProfileAndLevel.first).intValue();
        int intValue2 = ((Integer) codecProfileAndLevel.second).intValue();
        if (MimeTypes.VIDEO_DOLBY_VISION.equals(format.sampleMimeType)) {
            if (MimeTypes.VIDEO_H264.equals(this.mimeType)) {
                intValue = 8;
            } else if (MimeTypes.VIDEO_H265.equals(this.mimeType)) {
                intValue = 2;
            }
            intValue2 = 0;
        }
        if (!this.isVideo && intValue != 42) {
            return true;
        }
        MediaCodecInfo.CodecProfileLevel[] profileLevels = getProfileLevels();
        if (Util.SDK_INT <= 23 && MimeTypes.VIDEO_VP9.equals(this.mimeType) && profileLevels.length == 0) {
            profileLevels = estimateLegacyVp9ProfileLevels(this.capabilities);
        }
        for (MediaCodecInfo.CodecProfileLevel codecProfileLevel : profileLevels) {
            if (codecProfileLevel.profile == intValue && codecProfileLevel.level >= intValue2) {
                return true;
            }
        }
        logNoSupport("codec.profileLevel, " + format.codecs + ", " + this.codecMimeType);
        return false;
    }

    private boolean isSampleMimeTypeSupported(Format format) {
        return this.mimeType.equals(format.sampleMimeType) || this.mimeType.equals(MediaCodecUtil.getAlternativeCodecMimeType(format));
    }

    private static boolean isSecure(MediaCodecInfo.CodecCapabilities codecCapabilities) {
        return Util.SDK_INT >= 21 && isSecureV21(codecCapabilities);
    }

    @RequiresApi(21)
    private static boolean isSecureV21(MediaCodecInfo.CodecCapabilities codecCapabilities) {
        return codecCapabilities.isFeatureSupported("secure-playback");
    }

    private static boolean isTunneling(MediaCodecInfo.CodecCapabilities codecCapabilities) {
        return Util.SDK_INT >= 21 && isTunnelingV21(codecCapabilities);
    }

    @RequiresApi(21)
    private static boolean isTunnelingV21(MediaCodecInfo.CodecCapabilities codecCapabilities) {
        return codecCapabilities.isFeatureSupported("tunneled-playback");
    }

    private void logAssumedSupport(String str) {
        StringBuilder w2 = a.w("AssumedSupport [", str, "] [");
        w2.append(this.name);
        w2.append(", ");
        w2.append(this.mimeType);
        w2.append("] [");
        w2.append(Util.DEVICE_DEBUG_INFO);
        w2.append("]");
        Log.d(TAG, w2.toString());
    }

    private void logNoSupport(String str) {
        StringBuilder w2 = a.w("NoSupport [", str, "] [");
        w2.append(this.name);
        w2.append(", ");
        w2.append(this.mimeType);
        w2.append("] [");
        w2.append(Util.DEVICE_DEBUG_INFO);
        w2.append("]");
        Log.d(TAG, w2.toString());
    }

    private static boolean needsAdaptationFlushWorkaround(String str) {
        return MimeTypes.AUDIO_OPUS.equals(str);
    }

    private static boolean needsAdaptationReconfigureWorkaround(String str) {
        return Util.MODEL.startsWith("SM-T230") && "OMX.MARVELL.VIDEO.HW.CODA7542DECODER".equals(str);
    }

    private static boolean needsDisableAdaptationWorkaround(String str) {
        if (Util.SDK_INT <= 22) {
            String str2 = Util.MODEL;
            if (("ODROID-XU3".equals(str2) || "Nexus 10".equals(str2)) && ("OMX.Exynos.AVC.Decoder".equals(str) || "OMX.Exynos.AVC.Decoder.secure".equals(str))) {
                return true;
            }
        }
        return false;
    }

    private static final boolean needsRotatedVerticalResolutionWorkaround(String str) {
        return !"OMX.MTK.VIDEO.DECODER.HEVC".equals(str) || !"mcv5a".equals(Util.DEVICE);
    }

    public static MediaCodecInfo newInstance(String str, String str2, String str3, @Nullable MediaCodecInfo.CodecCapabilities codecCapabilities, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6) {
        return new MediaCodecInfo(str, str2, str3, codecCapabilities, z2, z3, z4, !z5 && codecCapabilities != null && isAdaptive(codecCapabilities) && !needsDisableAdaptationWorkaround(str), codecCapabilities != null && isTunneling(codecCapabilities), z6 || (codecCapabilities != null && isSecure(codecCapabilities)));
    }

    @RequiresApi(21)
    @Nullable
    public Point alignVideoSizeV21(int i3, int i4) {
        MediaCodecInfo.VideoCapabilities videoCapabilities;
        MediaCodecInfo.CodecCapabilities codecCapabilities = this.capabilities;
        if (codecCapabilities == null || (videoCapabilities = codecCapabilities.getVideoCapabilities()) == null) {
            return null;
        }
        return alignVideoSizeV21(videoCapabilities, i3, i4);
    }

    public DecoderReuseEvaluation canReuseCodec(Format format, Format format2) {
        int i3 = !Util.areEqual(format.sampleMimeType, format2.sampleMimeType) ? 8 : 0;
        if (this.isVideo) {
            if (format.rotationDegrees != format2.rotationDegrees) {
                i3 |= 1024;
            }
            if (!this.adaptive && !(format.width == format2.width && format.height == format2.height)) {
                i3 |= 512;
            }
            if (!Util.areEqual(format.colorInfo, format2.colorInfo)) {
                i3 |= 2048;
            }
            if (needsAdaptationReconfigureWorkaround(this.name) && !format.initializationDataEquals(format2)) {
                i3 |= 2;
            }
            if (i3 == 0) {
                return new DecoderReuseEvaluation(this.name, format, format2, format.initializationDataEquals(format2) ? 3 : 2, 0);
            }
        } else {
            if (format.channelCount != format2.channelCount) {
                i3 |= 4096;
            }
            if (format.sampleRate != format2.sampleRate) {
                i3 |= 8192;
            }
            if (format.pcmEncoding != format2.pcmEncoding) {
                i3 |= 16384;
            }
            if (i3 == 0 && MimeTypes.AUDIO_AAC.equals(this.mimeType)) {
                Pair<Integer, Integer> codecProfileAndLevel = MediaCodecUtil.getCodecProfileAndLevel(format);
                Pair<Integer, Integer> codecProfileAndLevel2 = MediaCodecUtil.getCodecProfileAndLevel(format2);
                if (!(codecProfileAndLevel == null || codecProfileAndLevel2 == null)) {
                    int intValue = ((Integer) codecProfileAndLevel.first).intValue();
                    int intValue2 = ((Integer) codecProfileAndLevel2.first).intValue();
                    if (intValue == 42 && intValue2 == 42) {
                        return new DecoderReuseEvaluation(this.name, format, format2, 3, 0);
                    }
                }
            }
            if (!format.initializationDataEquals(format2)) {
                i3 |= 32;
            }
            if (needsAdaptationFlushWorkaround(this.mimeType)) {
                i3 |= 2;
            }
            if (i3 == 0) {
                return new DecoderReuseEvaluation(this.name, format, format2, 1, 0);
            }
        }
        return new DecoderReuseEvaluation(this.name, format, format2, 0, i3);
    }

    public int getMaxSupportedInstances() {
        MediaCodecInfo.CodecCapabilities codecCapabilities;
        if (Util.SDK_INT < 23 || (codecCapabilities = this.capabilities) == null) {
            return -1;
        }
        return getMaxSupportedInstancesV23(codecCapabilities);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0004, code lost:
        r0 = r0.profileLevels;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.media.MediaCodecInfo.CodecProfileLevel[] getProfileLevels() {
        /*
            r0 = this;
            android.media.MediaCodecInfo$CodecCapabilities r0 = r0.capabilities
            if (r0 == 0) goto L_0x0008
            android.media.MediaCodecInfo$CodecProfileLevel[] r0 = r0.profileLevels
            if (r0 != 0) goto L_0x000b
        L_0x0008:
            r0 = 0
            android.media.MediaCodecInfo$CodecProfileLevel[] r0 = new android.media.MediaCodecInfo.CodecProfileLevel[r0]
        L_0x000b:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.exoplayer2.core.mediacodec.MediaCodecInfo.getProfileLevels():android.media.MediaCodecInfo$CodecProfileLevel[]");
    }

    @RequiresApi(21)
    public boolean isAudioChannelCountSupportedV21(int i3) {
        MediaCodecInfo.CodecCapabilities codecCapabilities = this.capabilities;
        if (codecCapabilities == null) {
            logNoSupport("channelCount.caps");
            return false;
        }
        MediaCodecInfo.AudioCapabilities audioCapabilities = codecCapabilities.getAudioCapabilities();
        if (audioCapabilities == null) {
            logNoSupport("channelCount.aCaps");
            return false;
        } else if (adjustMaxInputChannelCount(this.name, this.mimeType, audioCapabilities.getMaxInputChannelCount()) >= i3) {
            return true;
        } else {
            logNoSupport(A.a.k("channelCount.support, ", i3));
            return false;
        }
    }

    @RequiresApi(21)
    public boolean isAudioSampleRateSupportedV21(int i3) {
        MediaCodecInfo.CodecCapabilities codecCapabilities = this.capabilities;
        if (codecCapabilities == null) {
            logNoSupport("sampleRate.caps");
            return false;
        }
        MediaCodecInfo.AudioCapabilities audioCapabilities = codecCapabilities.getAudioCapabilities();
        if (audioCapabilities == null) {
            logNoSupport("sampleRate.aCaps");
            return false;
        } else if (audioCapabilities.isSampleRateSupported(i3)) {
            return true;
        } else {
            logNoSupport(A.a.k("sampleRate.support, ", i3));
            return false;
        }
    }

    public boolean isFormatSupported(Format format) throws MediaCodecUtil.DecoderQueryException {
        int i3;
        boolean z2 = false;
        if (!isSampleMimeTypeSupported(format) || !isCodecProfileAndLevelSupported(format)) {
            return false;
        }
        if (this.isVideo) {
            int i4 = format.width;
            if (i4 <= 0 || (i3 = format.height) <= 0) {
                return true;
            }
            if (Util.SDK_INT >= 21) {
                return isVideoSizeAndRateSupportedV21(i4, i3, (double) format.frameRate);
            }
            if (i4 * i3 <= MediaCodecUtil.maxH264DecodableFrameSize()) {
                z2 = true;
            }
            if (!z2) {
                logNoSupport("legacyFrameSize, " + format.width + "x" + format.height);
            }
            return z2;
        }
        if (Util.SDK_INT >= 21) {
            int i5 = format.sampleRate;
            if (i5 != -1 && !isAudioSampleRateSupportedV21(i5)) {
                return false;
            }
            int i6 = format.channelCount;
            if (i6 != -1 && !isAudioChannelCountSupportedV21(i6)) {
                return false;
            }
        }
        return true;
    }

    public boolean isHdr10PlusOutOfBandMetadataSupported() {
        if (Util.SDK_INT >= 29 && MimeTypes.VIDEO_VP9.equals(this.mimeType)) {
            for (MediaCodecInfo.CodecProfileLevel codecProfileLevel : getProfileLevels()) {
                if (codecProfileLevel.profile == 16384) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isSeamlessAdaptationSupported(Format format) {
        if (this.isVideo) {
            return this.adaptive;
        }
        Pair<Integer, Integer> codecProfileAndLevel = MediaCodecUtil.getCodecProfileAndLevel(format);
        return codecProfileAndLevel != null && ((Integer) codecProfileAndLevel.first).intValue() == 42;
    }

    @RequiresApi(21)
    public boolean isVideoSizeAndRateSupportedV21(int i3, int i4, double d2) {
        MediaCodecInfo.CodecCapabilities codecCapabilities = this.capabilities;
        if (codecCapabilities == null) {
            logNoSupport("sizeAndRate.caps");
            return false;
        }
        MediaCodecInfo.VideoCapabilities videoCapabilities = codecCapabilities.getVideoCapabilities();
        if (videoCapabilities == null) {
            logNoSupport("sizeAndRate.vCaps");
            return false;
        } else if (areSizeAndRateSupportedV21(videoCapabilities, i3, i4, d2)) {
            return true;
        } else {
            if (i3 >= i4 || !needsRotatedVerticalResolutionWorkaround(this.name) || !areSizeAndRateSupportedV21(videoCapabilities, i4, i3, d2)) {
                StringBuilder k2 = C0118y.k(i3, i4, "sizeAndRate.support, ", "x", "x");
                k2.append(d2);
                logNoSupport(k2.toString());
                return false;
            }
            StringBuilder k3 = C0118y.k(i3, i4, "sizeAndRate.rotated, ", "x", "x");
            k3.append(d2);
            logAssumedSupport(k3.toString());
            return true;
        }
    }

    public String toString() {
        return this.name;
    }

    @RequiresApi(21)
    private static Point alignVideoSizeV21(MediaCodecInfo.VideoCapabilities videoCapabilities, int i3, int i4) {
        int widthAlignment = videoCapabilities.getWidthAlignment();
        int heightAlignment = videoCapabilities.getHeightAlignment();
        return new Point(Util.ceilDivide(i3, widthAlignment) * widthAlignment, Util.ceilDivide(i4, heightAlignment) * heightAlignment);
    }

    @Deprecated
    public boolean isSeamlessAdaptationSupported(Format format, Format format2, boolean z2) {
        if (!z2 && format.colorInfo != null && format2.colorInfo == null) {
            format2 = format2.buildUpon().setColorInfo(format.colorInfo).build();
        }
        int i3 = canReuseCodec(format, format2).result;
        return i3 == 2 || i3 == 3;
    }
}
