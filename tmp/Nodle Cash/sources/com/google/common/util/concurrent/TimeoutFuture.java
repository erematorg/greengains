package com.google.common.util.concurrent;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.FluentFuture;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import javax.annotation.CheckForNull;

@GwtIncompatible
@ElementTypesAreNonnullByDefault
@J2ktIncompatible
final class TimeoutFuture<V> extends FluentFuture.TrustedFuture<V> {
    /* access modifiers changed from: private */
    @CheckForNull
    public ListenableFuture<V> delegateRef;
    /* access modifiers changed from: private */
    @CheckForNull
    public ScheduledFuture<?> timer;

    public static final class Fire<V> implements Runnable {
        @CheckForNull
        TimeoutFuture<V> timeoutFutureRef;

        public Fire(TimeoutFuture<V> timeoutFuture) {
            this.timeoutFutureRef = timeoutFuture;
        }

        public void run() {
            ListenableFuture access$000;
            String str;
            TimeoutFuture<V> timeoutFuture = this.timeoutFutureRef;
            if (timeoutFuture != null && (access$000 = timeoutFuture.delegateRef) != null) {
                this.timeoutFutureRef = null;
                if (access$000.isDone()) {
                    timeoutFuture.setFuture(access$000);
                    return;
                }
                try {
                    ScheduledFuture access$100 = timeoutFuture.timer;
                    ScheduledFuture unused = timeoutFuture.timer = null;
                    str = "Timed out";
                    if (access$100 != null) {
                        long abs = Math.abs(access$100.getDelay(TimeUnit.MILLISECONDS));
                        if (abs > 10) {
                            str = "Timed out (timeout delayed by " + abs + " ms after scheduled time)";
                        }
                    }
                    timeoutFuture.setException(new TimeoutFutureException(str + ": " + access$000));
                    access$000.cancel(true);
                } catch (Throwable th) {
                    access$000.cancel(true);
                    throw th;
                }
            }
        }
    }

    public static final class TimeoutFutureException extends TimeoutException {
        public synchronized Throwable fillInStackTrace() {
            setStackTrace(new StackTraceElement[0]);
            return this;
        }

        private TimeoutFutureException(String str) {
            super(str);
        }
    }

    private TimeoutFuture(ListenableFuture<V> listenableFuture) {
        this.delegateRef = (ListenableFuture) Preconditions.checkNotNull(listenableFuture);
    }

    public static <V> ListenableFuture<V> create(ListenableFuture<V> listenableFuture, long j2, TimeUnit timeUnit, ScheduledExecutorService scheduledExecutorService) {
        TimeoutFuture timeoutFuture = new TimeoutFuture(listenableFuture);
        Fire fire = new Fire(timeoutFuture);
        timeoutFuture.timer = scheduledExecutorService.schedule(fire, j2, timeUnit);
        listenableFuture.addListener(fire, MoreExecutors.directExecutor());
        return timeoutFuture;
    }

    public void afterDone() {
        maybePropagateCancellationTo(this.delegateRef);
        ScheduledFuture<?> scheduledFuture = this.timer;
        if (scheduledFuture != null) {
            scheduledFuture.cancel(false);
        }
        this.delegateRef = null;
        this.timer = null;
    }

    @CheckForNull
    public String pendingToString() {
        ListenableFuture<V> listenableFuture = this.delegateRef;
        ScheduledFuture<?> scheduledFuture = this.timer;
        if (listenableFuture == null) {
            return null;
        }
        String str = "inputFuture=[" + listenableFuture + "]";
        if (scheduledFuture == null) {
            return str;
        }
        long delay = scheduledFuture.getDelay(TimeUnit.MILLISECONDS);
        if (delay <= 0) {
            return str;
        }
        return str + ", remaining delay=[" + delay + " ms]";
    }
}
