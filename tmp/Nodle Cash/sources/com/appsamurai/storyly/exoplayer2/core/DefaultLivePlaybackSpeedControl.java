package com.appsamurai.storyly.exoplayer2.core;

import android.os.SystemClock;
import com.appsamurai.storyly.exoplayer2.common.C;
import com.appsamurai.storyly.exoplayer2.common.MediaItem;
import com.appsamurai.storyly.exoplayer2.common.util.Assertions;
import com.appsamurai.storyly.exoplayer2.common.util.Util;
import com.google.common.primitives.Longs;

public final class DefaultLivePlaybackSpeedControl implements LivePlaybackSpeedControl {
    public static final float DEFAULT_FALLBACK_MAX_PLAYBACK_SPEED = 1.03f;
    public static final float DEFAULT_FALLBACK_MIN_PLAYBACK_SPEED = 0.97f;
    public static final long DEFAULT_MAX_LIVE_OFFSET_ERROR_MS_FOR_UNIT_SPEED = 20;
    public static final float DEFAULT_MIN_POSSIBLE_LIVE_OFFSET_SMOOTHING_FACTOR = 0.999f;
    public static final long DEFAULT_MIN_UPDATE_INTERVAL_MS = 1000;
    public static final float DEFAULT_PROPORTIONAL_CONTROL_FACTOR = 0.1f;
    public static final long DEFAULT_TARGET_LIVE_OFFSET_INCREMENT_ON_REBUFFER_MS = 500;
    private float adjustedPlaybackSpeed;
    private long currentTargetLiveOffsetUs;
    private final float fallbackMaxPlaybackSpeed;
    private final float fallbackMinPlaybackSpeed;
    private long idealTargetLiveOffsetUs;
    private long lastPlaybackSpeedUpdateMs;
    private final long maxLiveOffsetErrorUsForUnitSpeed;
    private float maxPlaybackSpeed;
    private long maxTargetLiveOffsetUs;
    private long mediaConfigurationTargetLiveOffsetUs;
    private float minPlaybackSpeed;
    private final float minPossibleLiveOffsetSmoothingFactor;
    private long minTargetLiveOffsetUs;
    private final long minUpdateIntervalMs;
    private final float proportionalControlFactor;
    private long smoothedMinPossibleLiveOffsetDeviationUs;
    private long smoothedMinPossibleLiveOffsetUs;
    private long targetLiveOffsetOverrideUs;
    private final long targetLiveOffsetRebufferDeltaUs;

    public static final class Builder {
        private float fallbackMaxPlaybackSpeed = 1.03f;
        private float fallbackMinPlaybackSpeed = 0.97f;
        private long maxLiveOffsetErrorUsForUnitSpeed = Util.msToUs(20);
        private float minPossibleLiveOffsetSmoothingFactor = 0.999f;
        private long minUpdateIntervalMs = 1000;
        private float proportionalControlFactorUs = 1.0E-7f;
        private long targetLiveOffsetIncrementOnRebufferUs = Util.msToUs(500);

        public DefaultLivePlaybackSpeedControl build() {
            return new DefaultLivePlaybackSpeedControl(this.fallbackMinPlaybackSpeed, this.fallbackMaxPlaybackSpeed, this.minUpdateIntervalMs, this.proportionalControlFactorUs, this.maxLiveOffsetErrorUsForUnitSpeed, this.targetLiveOffsetIncrementOnRebufferUs, this.minPossibleLiveOffsetSmoothingFactor);
        }

        public Builder setFallbackMaxPlaybackSpeed(float f2) {
            Assertions.checkArgument(f2 >= 1.0f);
            this.fallbackMaxPlaybackSpeed = f2;
            return this;
        }

        public Builder setFallbackMinPlaybackSpeed(float f2) {
            Assertions.checkArgument(0.0f < f2 && f2 <= 1.0f);
            this.fallbackMinPlaybackSpeed = f2;
            return this;
        }

        public Builder setMaxLiveOffsetErrorMsForUnitSpeed(long j2) {
            Assertions.checkArgument(j2 > 0);
            this.maxLiveOffsetErrorUsForUnitSpeed = Util.msToUs(j2);
            return this;
        }

        public Builder setMinPossibleLiveOffsetSmoothingFactor(float f2) {
            Assertions.checkArgument(f2 >= 0.0f && f2 < 1.0f);
            this.minPossibleLiveOffsetSmoothingFactor = f2;
            return this;
        }

        public Builder setMinUpdateIntervalMs(long j2) {
            Assertions.checkArgument(j2 > 0);
            this.minUpdateIntervalMs = j2;
            return this;
        }

        public Builder setProportionalControlFactor(float f2) {
            Assertions.checkArgument(f2 > 0.0f);
            this.proportionalControlFactorUs = f2 / 1000000.0f;
            return this;
        }

        public Builder setTargetLiveOffsetIncrementOnRebufferMs(long j2) {
            Assertions.checkArgument(j2 >= 0);
            this.targetLiveOffsetIncrementOnRebufferUs = Util.msToUs(j2);
            return this;
        }
    }

    private void adjustTargetLiveOffsetUs(long j2) {
        long j3 = (this.smoothedMinPossibleLiveOffsetDeviationUs * 3) + this.smoothedMinPossibleLiveOffsetUs;
        if (this.currentTargetLiveOffsetUs > j3) {
            float msToUs = (float) Util.msToUs(this.minUpdateIntervalMs);
            long j4 = ((long) ((this.adjustedPlaybackSpeed - 1.0f) * msToUs)) + ((long) ((this.maxPlaybackSpeed - 1.0f) * msToUs));
            this.currentTargetLiveOffsetUs = Longs.max(j3, this.idealTargetLiveOffsetUs, this.currentTargetLiveOffsetUs - j4);
            return;
        }
        long constrainValue = Util.constrainValue(j2 - ((long) (Math.max(0.0f, this.adjustedPlaybackSpeed - 1.0f) / this.proportionalControlFactor)), this.currentTargetLiveOffsetUs, j3);
        this.currentTargetLiveOffsetUs = constrainValue;
        long j5 = this.maxTargetLiveOffsetUs;
        if (j5 != C.TIME_UNSET && constrainValue > j5) {
            this.currentTargetLiveOffsetUs = j5;
        }
    }

    private void maybeResetTargetLiveOffsetUs() {
        long j2 = this.mediaConfigurationTargetLiveOffsetUs;
        if (j2 != C.TIME_UNSET) {
            long j3 = this.targetLiveOffsetOverrideUs;
            if (j3 != C.TIME_UNSET) {
                j2 = j3;
            }
            long j4 = this.minTargetLiveOffsetUs;
            if (j4 != C.TIME_UNSET && j2 < j4) {
                j2 = j4;
            }
            long j5 = this.maxTargetLiveOffsetUs;
            if (j5 != C.TIME_UNSET && j2 > j5) {
                j2 = j5;
            }
        } else {
            j2 = -9223372036854775807L;
        }
        if (this.idealTargetLiveOffsetUs != j2) {
            this.idealTargetLiveOffsetUs = j2;
            this.currentTargetLiveOffsetUs = j2;
            this.smoothedMinPossibleLiveOffsetUs = C.TIME_UNSET;
            this.smoothedMinPossibleLiveOffsetDeviationUs = C.TIME_UNSET;
            this.lastPlaybackSpeedUpdateMs = C.TIME_UNSET;
        }
    }

    private static long smooth(long j2, long j3, float f2) {
        return (long) (((1.0f - f2) * ((float) j3)) + (((float) j2) * f2));
    }

    private void updateSmoothedMinPossibleLiveOffsetUs(long j2, long j3) {
        long j4 = j2 - j3;
        long j5 = this.smoothedMinPossibleLiveOffsetUs;
        if (j5 == C.TIME_UNSET) {
            this.smoothedMinPossibleLiveOffsetUs = j4;
            this.smoothedMinPossibleLiveOffsetDeviationUs = 0;
            return;
        }
        long max = Math.max(j4, smooth(j5, j4, this.minPossibleLiveOffsetSmoothingFactor));
        this.smoothedMinPossibleLiveOffsetUs = max;
        this.smoothedMinPossibleLiveOffsetDeviationUs = smooth(this.smoothedMinPossibleLiveOffsetDeviationUs, Math.abs(j4 - max), this.minPossibleLiveOffsetSmoothingFactor);
    }

    public float getAdjustedPlaybackSpeed(long j2, long j3) {
        if (this.mediaConfigurationTargetLiveOffsetUs == C.TIME_UNSET) {
            return 1.0f;
        }
        updateSmoothedMinPossibleLiveOffsetUs(j2, j3);
        if (this.lastPlaybackSpeedUpdateMs != C.TIME_UNSET && SystemClock.elapsedRealtime() - this.lastPlaybackSpeedUpdateMs < this.minUpdateIntervalMs) {
            return this.adjustedPlaybackSpeed;
        }
        this.lastPlaybackSpeedUpdateMs = SystemClock.elapsedRealtime();
        adjustTargetLiveOffsetUs(j2);
        long j4 = j2 - this.currentTargetLiveOffsetUs;
        if (Math.abs(j4) < this.maxLiveOffsetErrorUsForUnitSpeed) {
            this.adjustedPlaybackSpeed = 1.0f;
        } else {
            this.adjustedPlaybackSpeed = Util.constrainValue((this.proportionalControlFactor * ((float) j4)) + 1.0f, this.minPlaybackSpeed, this.maxPlaybackSpeed);
        }
        return this.adjustedPlaybackSpeed;
    }

    public long getTargetLiveOffsetUs() {
        return this.currentTargetLiveOffsetUs;
    }

    public void notifyRebuffer() {
        long j2 = this.currentTargetLiveOffsetUs;
        if (j2 != C.TIME_UNSET) {
            long j3 = j2 + this.targetLiveOffsetRebufferDeltaUs;
            this.currentTargetLiveOffsetUs = j3;
            long j4 = this.maxTargetLiveOffsetUs;
            if (j4 != C.TIME_UNSET && j3 > j4) {
                this.currentTargetLiveOffsetUs = j4;
            }
            this.lastPlaybackSpeedUpdateMs = C.TIME_UNSET;
        }
    }

    public void setLiveConfiguration(MediaItem.LiveConfiguration liveConfiguration) {
        this.mediaConfigurationTargetLiveOffsetUs = Util.msToUs(liveConfiguration.targetOffsetMs);
        this.minTargetLiveOffsetUs = Util.msToUs(liveConfiguration.minOffsetMs);
        this.maxTargetLiveOffsetUs = Util.msToUs(liveConfiguration.maxOffsetMs);
        float f2 = liveConfiguration.minPlaybackSpeed;
        if (f2 == -3.4028235E38f) {
            f2 = this.fallbackMinPlaybackSpeed;
        }
        this.minPlaybackSpeed = f2;
        float f3 = liveConfiguration.maxPlaybackSpeed;
        if (f3 == -3.4028235E38f) {
            f3 = this.fallbackMaxPlaybackSpeed;
        }
        this.maxPlaybackSpeed = f3;
        if (f2 == 1.0f && f3 == 1.0f) {
            this.mediaConfigurationTargetLiveOffsetUs = C.TIME_UNSET;
        }
        maybeResetTargetLiveOffsetUs();
    }

    public void setTargetLiveOffsetOverrideUs(long j2) {
        this.targetLiveOffsetOverrideUs = j2;
        maybeResetTargetLiveOffsetUs();
    }

    private DefaultLivePlaybackSpeedControl(float f2, float f3, long j2, float f4, long j3, long j4, float f5) {
        this.fallbackMinPlaybackSpeed = f2;
        this.fallbackMaxPlaybackSpeed = f3;
        this.minUpdateIntervalMs = j2;
        this.proportionalControlFactor = f4;
        this.maxLiveOffsetErrorUsForUnitSpeed = j3;
        this.targetLiveOffsetRebufferDeltaUs = j4;
        this.minPossibleLiveOffsetSmoothingFactor = f5;
        this.mediaConfigurationTargetLiveOffsetUs = C.TIME_UNSET;
        this.targetLiveOffsetOverrideUs = C.TIME_UNSET;
        this.minTargetLiveOffsetUs = C.TIME_UNSET;
        this.maxTargetLiveOffsetUs = C.TIME_UNSET;
        this.minPlaybackSpeed = f2;
        this.maxPlaybackSpeed = f3;
        this.adjustedPlaybackSpeed = 1.0f;
        this.lastPlaybackSpeedUpdateMs = C.TIME_UNSET;
        this.idealTargetLiveOffsetUs = C.TIME_UNSET;
        this.currentTargetLiveOffsetUs = C.TIME_UNSET;
        this.smoothedMinPossibleLiveOffsetUs = C.TIME_UNSET;
        this.smoothedMinPossibleLiveOffsetDeviationUs = C.TIME_UNSET;
    }
}
