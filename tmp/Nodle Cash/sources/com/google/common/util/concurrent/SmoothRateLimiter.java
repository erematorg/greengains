package com.google.common.util.concurrent;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.common.math.LongMath;
import com.google.common.util.concurrent.RateLimiter;
import java.util.concurrent.TimeUnit;

@GwtIncompatible
@ElementTypesAreNonnullByDefault
@J2ktIncompatible
abstract class SmoothRateLimiter extends RateLimiter {
    double maxPermits;
    private long nextFreeTicketMicros;
    double stableIntervalMicros;
    double storedPermits;

    public static final class SmoothBursty extends SmoothRateLimiter {
        final double maxBurstSeconds;

        public SmoothBursty(RateLimiter.SleepingStopwatch sleepingStopwatch, double d2) {
            super(sleepingStopwatch);
            this.maxBurstSeconds = d2;
        }

        public double coolDownIntervalMicros() {
            return this.stableIntervalMicros;
        }

        public void doSetRate(double d2, double d3) {
            double d4 = this.maxPermits;
            double d5 = this.maxBurstSeconds * d2;
            this.maxPermits = d5;
            if (d4 == Double.POSITIVE_INFINITY) {
                this.storedPermits = d5;
                return;
            }
            double d6 = 0.0d;
            if (d4 != 0.0d) {
                d6 = (this.storedPermits * d5) / d4;
            }
            this.storedPermits = d6;
        }

        public long storedPermitsToWaitTime(double d2, double d3) {
            return 0;
        }
    }

    public static final class SmoothWarmingUp extends SmoothRateLimiter {
        private double coldFactor;
        private double slope;
        private double thresholdPermits;
        private final long warmupPeriodMicros;

        public SmoothWarmingUp(RateLimiter.SleepingStopwatch sleepingStopwatch, long j2, TimeUnit timeUnit, double d2) {
            super(sleepingStopwatch);
            this.warmupPeriodMicros = timeUnit.toMicros(j2);
            this.coldFactor = d2;
        }

        private double permitsToTime(double d2) {
            return (d2 * this.slope) + this.stableIntervalMicros;
        }

        public double coolDownIntervalMicros() {
            return ((double) this.warmupPeriodMicros) / this.maxPermits;
        }

        public void doSetRate(double d2, double d3) {
            double d4 = this.maxPermits;
            double d5 = this.coldFactor * d3;
            long j2 = this.warmupPeriodMicros;
            double d6 = (((double) j2) * 0.5d) / d3;
            this.thresholdPermits = d6;
            double d7 = ((((double) j2) * 2.0d) / (d3 + d5)) + d6;
            this.maxPermits = d7;
            this.slope = (d5 - d3) / (d7 - d6);
            if (d4 == Double.POSITIVE_INFINITY) {
                this.storedPermits = 0.0d;
                return;
            }
            if (d4 != 0.0d) {
                d7 = (this.storedPermits * d7) / d4;
            }
            this.storedPermits = d7;
        }

        public long storedPermitsToWaitTime(double d2, double d3) {
            long j2;
            double d4 = d2 - this.thresholdPermits;
            if (d4 > 0.0d) {
                double min = Math.min(d4, d3);
                j2 = (long) (((permitsToTime(d4) + permitsToTime(d4 - min)) * min) / 2.0d);
                d3 -= min;
            } else {
                j2 = 0;
            }
            return j2 + ((long) (this.stableIntervalMicros * d3));
        }
    }

    public abstract double coolDownIntervalMicros();

    public final double doGetRate() {
        return ((double) TimeUnit.SECONDS.toMicros(1)) / this.stableIntervalMicros;
    }

    public abstract void doSetRate(double d2, double d3);

    public final void doSetRate(double d2, long j2) {
        resync(j2);
        double micros = ((double) TimeUnit.SECONDS.toMicros(1)) / d2;
        this.stableIntervalMicros = micros;
        doSetRate(d2, micros);
    }

    public final long queryEarliestAvailable(long j2) {
        return this.nextFreeTicketMicros;
    }

    public final long reserveEarliestAvailable(int i3, long j2) {
        resync(j2);
        long j3 = this.nextFreeTicketMicros;
        double d2 = (double) i3;
        double min = Math.min(d2, this.storedPermits);
        this.nextFreeTicketMicros = LongMath.saturatedAdd(this.nextFreeTicketMicros, storedPermitsToWaitTime(this.storedPermits, min) + ((long) ((d2 - min) * this.stableIntervalMicros)));
        this.storedPermits -= min;
        return j3;
    }

    public void resync(long j2) {
        long j3 = this.nextFreeTicketMicros;
        if (j2 > j3) {
            this.storedPermits = Math.min(this.maxPermits, this.storedPermits + (((double) (j2 - j3)) / coolDownIntervalMicros()));
            this.nextFreeTicketMicros = j2;
        }
    }

    public abstract long storedPermitsToWaitTime(double d2, double d3);

    private SmoothRateLimiter(RateLimiter.SleepingStopwatch sleepingStopwatch) {
        super(sleepingStopwatch);
        this.nextFreeTicketMicros = 0;
    }
}
