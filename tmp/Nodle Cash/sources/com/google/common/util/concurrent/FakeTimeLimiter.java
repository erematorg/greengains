package com.google.common.util.concurrent;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@ElementTypesAreNonnullByDefault
@GwtIncompatible
@J2ktIncompatible
public final class FakeTimeLimiter implements TimeLimiter {
    @CanIgnoreReturnValue
    @ParametricNullness
    public <T> T callUninterruptiblyWithTimeout(Callable<T> callable, long j2, TimeUnit timeUnit) throws ExecutionException {
        return callWithTimeout(callable, j2, timeUnit);
    }

    @CanIgnoreReturnValue
    @ParametricNullness
    public <T> T callWithTimeout(Callable<T> callable, long j2, TimeUnit timeUnit) throws ExecutionException {
        Preconditions.checkNotNull(callable);
        Preconditions.checkNotNull(timeUnit);
        try {
            return callable.call();
        } catch (RuntimeException e3) {
            throw new UncheckedExecutionException((Throwable) e3);
        } catch (Exception e4) {
            Platform.restoreInterruptIfIsInterruptedException(e4);
            throw new ExecutionException(e4);
        } catch (Error e5) {
            throw new ExecutionError(e5);
        }
    }

    @CanIgnoreReturnValue
    public <T> T newProxy(T t2, Class<T> cls, long j2, TimeUnit timeUnit) {
        Preconditions.checkNotNull(t2);
        Preconditions.checkNotNull(cls);
        Preconditions.checkNotNull(timeUnit);
        return t2;
    }

    public void runUninterruptiblyWithTimeout(Runnable runnable, long j2, TimeUnit timeUnit) {
        runWithTimeout(runnable, j2, timeUnit);
    }

    public void runWithTimeout(Runnable runnable, long j2, TimeUnit timeUnit) {
        Preconditions.checkNotNull(runnable);
        Preconditions.checkNotNull(timeUnit);
        try {
            runnable.run();
        } catch (RuntimeException e3) {
            throw new UncheckedExecutionException((Throwable) e3);
        } catch (Error e4) {
            throw new ExecutionError(e4);
        }
    }
}
