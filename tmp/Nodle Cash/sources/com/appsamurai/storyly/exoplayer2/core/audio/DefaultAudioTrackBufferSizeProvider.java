package com.appsamurai.storyly.exoplayer2.core.audio;

import com.appsamurai.storyly.exoplayer2.common.util.Util;
import com.appsamurai.storyly.exoplayer2.core.audio.DefaultAudioSink;
import com.appsamurai.storyly.exoplayer2.extractor.audio.AacUtil;
import com.appsamurai.storyly.exoplayer2.extractor.audio.Ac3Util;
import com.appsamurai.storyly.exoplayer2.extractor.audio.Ac4Util;
import com.appsamurai.storyly.exoplayer2.extractor.audio.DtsUtil;
import com.appsamurai.storyly.exoplayer2.extractor.audio.MpegAudioUtil;
import com.google.common.primitives.Ints;

public class DefaultAudioTrackBufferSizeProvider implements DefaultAudioSink.AudioTrackBufferSizeProvider {
    private static final int AC3_BUFFER_MULTIPLICATION_FACTOR = 2;
    private static final int MAX_PCM_BUFFER_DURATION_US = 750000;
    private static final int MIN_PCM_BUFFER_DURATION_US = 250000;
    private static final int OFFLOAD_BUFFER_DURATION_US = 50000000;
    private static final int PASSTHROUGH_BUFFER_DURATION_US = 250000;
    private static final int PCM_BUFFER_MULTIPLICATION_FACTOR = 4;
    public final int ac3BufferMultiplicationFactor;
    protected final int maxPcmBufferDurationUs;
    protected final int minPcmBufferDurationUs;
    protected final int offloadBufferDurationUs;
    protected final int passthroughBufferDurationUs;
    protected final int pcmBufferMultiplicationFactor;

    public static class Builder {
        /* access modifiers changed from: private */
        public int ac3BufferMultiplicationFactor = 2;
        /* access modifiers changed from: private */
        public int maxPcmBufferDurationUs = DefaultAudioTrackBufferSizeProvider.MAX_PCM_BUFFER_DURATION_US;
        /* access modifiers changed from: private */
        public int minPcmBufferDurationUs = 250000;
        /* access modifiers changed from: private */
        public int offloadBufferDurationUs = DefaultAudioTrackBufferSizeProvider.OFFLOAD_BUFFER_DURATION_US;
        /* access modifiers changed from: private */
        public int passthroughBufferDurationUs = 250000;
        /* access modifiers changed from: private */
        public int pcmBufferMultiplicationFactor = 4;

        public DefaultAudioTrackBufferSizeProvider build() {
            return new DefaultAudioTrackBufferSizeProvider(this);
        }

        public Builder setAc3BufferMultiplicationFactor(int i3) {
            this.ac3BufferMultiplicationFactor = i3;
            return this;
        }

        public Builder setMaxPcmBufferDurationUs(int i3) {
            this.maxPcmBufferDurationUs = i3;
            return this;
        }

        public Builder setMinPcmBufferDurationUs(int i3) {
            this.minPcmBufferDurationUs = i3;
            return this;
        }

        public Builder setOffloadBufferDurationUs(int i3) {
            this.offloadBufferDurationUs = i3;
            return this;
        }

        public Builder setPassthroughBufferDurationUs(int i3) {
            this.passthroughBufferDurationUs = i3;
            return this;
        }

        public Builder setPcmBufferMultiplicationFactor(int i3) {
            this.pcmBufferMultiplicationFactor = i3;
            return this;
        }
    }

    public DefaultAudioTrackBufferSizeProvider(Builder builder) {
        this.minPcmBufferDurationUs = builder.minPcmBufferDurationUs;
        this.maxPcmBufferDurationUs = builder.maxPcmBufferDurationUs;
        this.pcmBufferMultiplicationFactor = builder.pcmBufferMultiplicationFactor;
        this.passthroughBufferDurationUs = builder.passthroughBufferDurationUs;
        this.offloadBufferDurationUs = builder.offloadBufferDurationUs;
        this.ac3BufferMultiplicationFactor = builder.ac3BufferMultiplicationFactor;
    }

    public static int durationUsToBytes(int i3, int i4, int i5) {
        return Ints.checkedCast(((((long) i3) * ((long) i4)) * ((long) i5)) / 1000000);
    }

    public static int getMaximumEncodedRateBytesPerSecond(int i3) {
        switch (i3) {
            case 5:
                return Ac3Util.AC3_MAX_RATE_BYTES_PER_SECOND;
            case 6:
            case 18:
                return Ac3Util.E_AC3_MAX_RATE_BYTES_PER_SECOND;
            case 7:
                return DtsUtil.DTS_MAX_RATE_BYTES_PER_SECOND;
            case 8:
                return DtsUtil.DTS_HD_MAX_RATE_BYTES_PER_SECOND;
            case 9:
                return MpegAudioUtil.MAX_RATE_BYTES_PER_SECOND;
            case 10:
                return 100000;
            case 11:
                return AacUtil.AAC_HE_V1_MAX_RATE_BYTES_PER_SECOND;
            case 12:
                return AacUtil.AAC_HE_V2_MAX_RATE_BYTES_PER_SECOND;
            case 14:
                return Ac3Util.TRUEHD_MAX_RATE_BYTES_PER_SECOND;
            case 15:
                return 8000;
            case 16:
                return AacUtil.AAC_XHE_MAX_RATE_BYTES_PER_SECOND;
            case 17:
                return Ac4Util.MAX_RATE_BYTES_PER_SECOND;
            default:
                throw new IllegalArgumentException();
        }
    }

    public int get1xBufferSizeInBytes(int i3, int i4, int i5, int i6, int i7) {
        if (i5 == 0) {
            return getPcmBufferSizeInBytes(i3, i7, i6);
        }
        if (i5 == 1) {
            return getOffloadBufferSizeInBytes(i4);
        }
        if (i5 == 2) {
            return getPassthroughBufferSizeInBytes(i4);
        }
        throw new IllegalArgumentException();
    }

    public int getBufferSizeInBytes(int i3, int i4, int i5, int i6, int i7, double d2) {
        return (((Math.max(i3, (int) (((double) get1xBufferSizeInBytes(i3, i4, i5, i6, i7)) * d2)) + i6) - 1) / i6) * i6;
    }

    public int getOffloadBufferSizeInBytes(int i3) {
        return Ints.checkedCast((((long) this.offloadBufferDurationUs) * ((long) getMaximumEncodedRateBytesPerSecond(i3))) / 1000000);
    }

    public int getPassthroughBufferSizeInBytes(int i3) {
        int i4 = this.passthroughBufferDurationUs;
        if (i3 == 5) {
            i4 *= this.ac3BufferMultiplicationFactor;
        }
        return Ints.checkedCast((((long) i4) * ((long) getMaximumEncodedRateBytesPerSecond(i3))) / 1000000);
    }

    public int getPcmBufferSizeInBytes(int i3, int i4, int i5) {
        return Util.constrainValue(i3 * this.pcmBufferMultiplicationFactor, durationUsToBytes(this.minPcmBufferDurationUs, i4, i5), durationUsToBytes(this.maxPcmBufferDurationUs, i4, i5));
    }
}
