package com.appsamurai.storyly.exoplayer2.extractor.audio;

import androidx.annotation.Nullable;
import androidx.constraintlayout.core.state.b;
import com.appsamurai.storyly.exoplayer2.common.util.MimeTypes;

public final class MpegAudioUtil {
    /* access modifiers changed from: private */
    public static final int[] BITRATE_V1_L1 = {32000, 64000, 96000, 128000, 160000, DtsUtil.DTS_MAX_RATE_BYTES_PER_SECOND, 224000, AacUtil.AAC_XHE_MAX_RATE_BYTES_PER_SECOND, 288000, 320000, 352000, 384000, 416000, 448000};
    /* access modifiers changed from: private */
    public static final int[] BITRATE_V1_L2 = {32000, OpusUtil.SAMPLE_RATE, 56000, 64000, Ac3Util.AC3_MAX_RATE_BYTES_PER_SECOND, 96000, 112000, 128000, 160000, DtsUtil.DTS_MAX_RATE_BYTES_PER_SECOND, 224000, AacUtil.AAC_XHE_MAX_RATE_BYTES_PER_SECOND, 320000, 384000};
    /* access modifiers changed from: private */
    public static final int[] BITRATE_V1_L3 = {32000, MAX_RATE_BYTES_PER_SECOND, OpusUtil.SAMPLE_RATE, 56000, 64000, Ac3Util.AC3_MAX_RATE_BYTES_PER_SECOND, 96000, 112000, 128000, 160000, DtsUtil.DTS_MAX_RATE_BYTES_PER_SECOND, 224000, AacUtil.AAC_XHE_MAX_RATE_BYTES_PER_SECOND, 320000};
    /* access modifiers changed from: private */
    public static final int[] BITRATE_V2 = {8000, AacUtil.AAC_HE_V1_MAX_RATE_BYTES_PER_SECOND, 24000, 32000, MAX_RATE_BYTES_PER_SECOND, OpusUtil.SAMPLE_RATE, 56000, 64000, Ac3Util.AC3_MAX_RATE_BYTES_PER_SECOND, 96000, 112000, 128000, 144000, 160000};
    /* access modifiers changed from: private */
    public static final int[] BITRATE_V2_L1 = {32000, OpusUtil.SAMPLE_RATE, 56000, 64000, Ac3Util.AC3_MAX_RATE_BYTES_PER_SECOND, 96000, 112000, 128000, 144000, 160000, 176000, DtsUtil.DTS_MAX_RATE_BYTES_PER_SECOND, 224000, AacUtil.AAC_XHE_MAX_RATE_BYTES_PER_SECOND};
    public static final int MAX_FRAME_SIZE_BYTES = 4096;
    public static final int MAX_RATE_BYTES_PER_SECOND = 40000;
    /* access modifiers changed from: private */
    public static final String[] MIME_TYPE_BY_LAYER = {MimeTypes.AUDIO_MPEG_L1, MimeTypes.AUDIO_MPEG_L2, MimeTypes.AUDIO_MPEG};
    private static final int SAMPLES_PER_FRAME_L1 = 384;
    private static final int SAMPLES_PER_FRAME_L2 = 1152;
    private static final int SAMPLES_PER_FRAME_L3_V1 = 1152;
    private static final int SAMPLES_PER_FRAME_L3_V2 = 576;
    /* access modifiers changed from: private */
    public static final int[] SAMPLING_RATE_V1 = {44100, OpusUtil.SAMPLE_RATE, 32000};

    public static final class Header {
        public int bitrate;
        public int channels;
        public int frameSize;
        @Nullable
        public String mimeType;
        public int sampleRate;
        public int samplesPerFrame;
        public int version;

        public boolean setForHeaderData(int i3) {
            int i4;
            int i5;
            int i6;
            int i7;
            if (!MpegAudioUtil.isMagicPresent(i3) || (i4 = (i3 >>> 19) & 3) == 1 || (i5 = (i3 >>> 17) & 3) == 0 || (i6 = (i3 >>> 12) & 15) == 0 || i6 == 15 || (i7 = (i3 >>> 10) & 3) == 3) {
                return false;
            }
            this.version = i4;
            this.mimeType = MpegAudioUtil.MIME_TYPE_BY_LAYER[3 - i5];
            int i8 = MpegAudioUtil.SAMPLING_RATE_V1[i7];
            this.sampleRate = i8;
            int i9 = 2;
            if (i4 == 2) {
                this.sampleRate = i8 / 2;
            } else if (i4 == 0) {
                this.sampleRate = i8 / 4;
            }
            int i10 = (i3 >>> 9) & 1;
            this.samplesPerFrame = MpegAudioUtil.getFrameSizeInSamples(i4, i5);
            if (i5 == 3) {
                int i11 = i4 == 3 ? MpegAudioUtil.BITRATE_V1_L1[i6 - 1] : MpegAudioUtil.BITRATE_V2_L1[i6 - 1];
                this.bitrate = i11;
                this.frameSize = (((i11 * 12) / this.sampleRate) + i10) * 4;
            } else {
                int i12 = 144;
                if (i4 == 3) {
                    int i13 = i5 == 2 ? MpegAudioUtil.BITRATE_V1_L2[i6 - 1] : MpegAudioUtil.BITRATE_V1_L3[i6 - 1];
                    this.bitrate = i13;
                    this.frameSize = ((i13 * 144) / this.sampleRate) + i10;
                } else {
                    int i14 = MpegAudioUtil.BITRATE_V2[i6 - 1];
                    this.bitrate = i14;
                    if (i5 == 1) {
                        i12 = 72;
                    }
                    this.frameSize = ((i12 * i14) / this.sampleRate) + i10;
                }
            }
            if (((i3 >> 6) & 3) == 3) {
                i9 = 1;
            }
            this.channels = i9;
            return true;
        }
    }

    private MpegAudioUtil() {
    }

    public static int getFrameSize(int i3) {
        int i4;
        int i5;
        int i6;
        int i7;
        if (!isMagicPresent(i3) || (i4 = (i3 >>> 19) & 3) == 1 || (i5 = (i3 >>> 17) & 3) == 0 || (i6 = (i3 >>> 12) & 15) == 0 || i6 == 15 || (i7 = (i3 >>> 10) & 3) == 3) {
            return -1;
        }
        int i8 = SAMPLING_RATE_V1[i7];
        if (i4 == 2) {
            i8 /= 2;
        } else if (i4 == 0) {
            i8 /= 4;
        }
        int i9 = (i3 >>> 9) & 1;
        if (i5 == 3) {
            return ((((i4 == 3 ? BITRATE_V1_L1[i6 - 1] : BITRATE_V2_L1[i6 - 1]) * 12) / i8) + i9) * 4;
        }
        int i10 = i4 == 3 ? i5 == 2 ? BITRATE_V1_L2[i6 - 1] : BITRATE_V1_L3[i6 - 1] : BITRATE_V2[i6 - 1];
        int i11 = 144;
        if (i4 == 3) {
            return b.b(i10, 144, i8, i9);
        }
        if (i5 == 1) {
            i11 = 72;
        }
        return b.b(i11, i10, i8, i9);
    }

    /* access modifiers changed from: private */
    public static int getFrameSizeInSamples(int i3, int i4) {
        if (i4 != 1) {
            if (i4 == 2) {
                return 1152;
            }
            if (i4 == 3) {
                return 384;
            }
            throw new IllegalArgumentException();
        } else if (i3 == 3) {
            return 1152;
        } else {
            return SAMPLES_PER_FRAME_L3_V2;
        }
    }

    /* access modifiers changed from: private */
    public static boolean isMagicPresent(int i3) {
        return (i3 & -2097152) == -2097152;
    }

    public static int parseMpegAudioFrameSampleCount(int i3) {
        int i4;
        int i5;
        if (!isMagicPresent(i3) || (i4 = (i3 >>> 19) & 3) == 1 || (i5 = (i3 >>> 17) & 3) == 0) {
            return -1;
        }
        int i6 = (i3 >>> 12) & 15;
        int i7 = (i3 >>> 10) & 3;
        if (i6 == 0 || i6 == 15 || i7 == 3) {
            return -1;
        }
        return getFrameSizeInSamples(i4, i5);
    }
}
