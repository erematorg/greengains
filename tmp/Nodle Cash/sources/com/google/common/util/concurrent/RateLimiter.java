package com.google.common.util.concurrent;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.common.base.Stopwatch;
import com.google.common.util.concurrent.SmoothRateLimiter;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import javax.annotation.CheckForNull;

@GwtIncompatible
@ElementTypesAreNonnullByDefault
@Beta
@J2ktIncompatible
public abstract class RateLimiter {
    @CheckForNull
    private volatile Object mutexDoNotUseDirectly;
    private final SleepingStopwatch stopwatch;

    public static abstract class SleepingStopwatch {
        public static SleepingStopwatch createFromSystemTimer() {
            return new SleepingStopwatch() {
                final Stopwatch stopwatch = Stopwatch.createStarted();

                public long readMicros() {
                    return this.stopwatch.elapsed(TimeUnit.MICROSECONDS);
                }

                public void sleepMicrosUninterruptibly(long j2) {
                    if (j2 > 0) {
                        Uninterruptibles.sleepUninterruptibly(j2, TimeUnit.MICROSECONDS);
                    }
                }
            };
        }

        public abstract long readMicros();

        public abstract void sleepMicrosUninterruptibly(long j2);
    }

    public RateLimiter(SleepingStopwatch sleepingStopwatch) {
        this.stopwatch = (SleepingStopwatch) Preconditions.checkNotNull(sleepingStopwatch);
    }

    private boolean canAcquire(long j2, long j3) {
        return queryEarliestAvailable(j2) - j3 <= j2;
    }

    private static void checkPermits(int i3) {
        Preconditions.checkArgument(i3 > 0, "Requested permits (%s) must be positive", i3);
    }

    public static RateLimiter create(double d2) {
        return create(d2, SleepingStopwatch.createFromSystemTimer());
    }

    private Object mutex() {
        Object obj = this.mutexDoNotUseDirectly;
        if (obj == null) {
            synchronized (this) {
                try {
                    obj = this.mutexDoNotUseDirectly;
                    if (obj == null) {
                        obj = new Object();
                        this.mutexDoNotUseDirectly = obj;
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        return obj;
    }

    @CanIgnoreReturnValue
    public double acquire() {
        return acquire(1);
    }

    public abstract double doGetRate();

    public abstract void doSetRate(double d2, long j2);

    public final double getRate() {
        double doGetRate;
        synchronized (mutex()) {
            doGetRate = doGetRate();
        }
        return doGetRate;
    }

    public abstract long queryEarliestAvailable(long j2);

    public final long reserve(int i3) {
        long reserveAndGetWaitLength;
        checkPermits(i3);
        synchronized (mutex()) {
            reserveAndGetWaitLength = reserveAndGetWaitLength(i3, this.stopwatch.readMicros());
        }
        return reserveAndGetWaitLength;
    }

    public final long reserveAndGetWaitLength(int i3, long j2) {
        return Math.max(reserveEarliestAvailable(i3, j2) - j2, 0);
    }

    public abstract long reserveEarliestAvailable(int i3, long j2);

    public final void setRate(double d2) {
        Preconditions.checkArgument(d2 > 0.0d && !Double.isNaN(d2), "rate must be positive");
        synchronized (mutex()) {
            doSetRate(d2, this.stopwatch.readMicros());
        }
    }

    public String toString() {
        return String.format(Locale.ROOT, "RateLimiter[stableRate=%3.1fqps]", new Object[]{Double.valueOf(getRate())});
    }

    public boolean tryAcquire(long j2, TimeUnit timeUnit) {
        return tryAcquire(1, j2, timeUnit);
    }

    @VisibleForTesting
    public static RateLimiter create(double d2, SleepingStopwatch sleepingStopwatch) {
        SmoothRateLimiter.SmoothBursty smoothBursty = new SmoothRateLimiter.SmoothBursty(sleepingStopwatch, 1.0d);
        smoothBursty.setRate(d2);
        return smoothBursty;
    }

    @CanIgnoreReturnValue
    public double acquire(int i3) {
        long reserve = reserve(i3);
        this.stopwatch.sleepMicrosUninterruptibly(reserve);
        return (((double) reserve) * 1.0d) / ((double) TimeUnit.SECONDS.toMicros(1));
    }

    public boolean tryAcquire(int i3) {
        return tryAcquire(i3, 0, TimeUnit.MICROSECONDS);
    }

    public boolean tryAcquire() {
        return tryAcquire(1, 0, TimeUnit.MICROSECONDS);
    }

    public static RateLimiter create(double d2, long j2, TimeUnit timeUnit) {
        Preconditions.checkArgument(j2 >= 0, "warmupPeriod must not be negative: %s", j2);
        return create(d2, j2, timeUnit, 3.0d, SleepingStopwatch.createFromSystemTimer());
    }

    public boolean tryAcquire(int i3, long j2, TimeUnit timeUnit) {
        long max = Math.max(timeUnit.toMicros(j2), 0);
        checkPermits(i3);
        synchronized (mutex()) {
            try {
                long readMicros = this.stopwatch.readMicros();
                if (!canAcquire(readMicros, max)) {
                    return false;
                }
                long reserveAndGetWaitLength = reserveAndGetWaitLength(i3, readMicros);
                this.stopwatch.sleepMicrosUninterruptibly(reserveAndGetWaitLength);
                return true;
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
    }

    @VisibleForTesting
    public static RateLimiter create(double d2, long j2, TimeUnit timeUnit, double d3, SleepingStopwatch sleepingStopwatch) {
        SmoothRateLimiter.SmoothWarmingUp smoothWarmingUp = new SmoothRateLimiter.SmoothWarmingUp(sleepingStopwatch, j2, timeUnit, d3);
        smoothWarmingUp.setRate(d2);
        return smoothWarmingUp;
    }
}
