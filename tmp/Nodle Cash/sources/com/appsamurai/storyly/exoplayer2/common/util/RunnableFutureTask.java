package com.appsamurai.storyly.exoplayer2.common.util;

import androidx.annotation.Nullable;
import java.lang.Exception;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public abstract class RunnableFutureTask<R, E extends Exception> implements RunnableFuture<R> {
    private final Object cancelLock = new Object();
    private boolean canceled;
    @Nullable
    private Exception exception;
    private final ConditionVariable finished = new ConditionVariable();
    @Nullable
    private R result;
    private final ConditionVariable started = new ConditionVariable();
    @Nullable
    private Thread workThread;

    @UnknownNull
    private R getResult() throws ExecutionException {
        if (this.canceled) {
            throw new CancellationException();
        } else if (this.exception == null) {
            return this.result;
        } else {
            throw new ExecutionException(this.exception);
        }
    }

    public final void blockUntilFinished() {
        this.finished.blockUninterruptible();
    }

    public final void blockUntilStarted() {
        this.started.blockUninterruptible();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x002d, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x002f, code lost:
        return false;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean cancel(boolean r4) {
        /*
            r3 = this;
            java.lang.Object r0 = r3.cancelLock
            monitor-enter(r0)
            boolean r1 = r3.canceled     // Catch:{ all -> 0x0020 }
            if (r1 != 0) goto L_0x002e
            com.appsamurai.storyly.exoplayer2.common.util.ConditionVariable r1 = r3.finished     // Catch:{ all -> 0x0020 }
            boolean r1 = r1.isOpen()     // Catch:{ all -> 0x0020 }
            if (r1 == 0) goto L_0x0010
            goto L_0x002e
        L_0x0010:
            r1 = 1
            r3.canceled = r1     // Catch:{ all -> 0x0020 }
            r3.cancelWork()     // Catch:{ all -> 0x0020 }
            java.lang.Thread r2 = r3.workThread     // Catch:{ all -> 0x0020 }
            if (r2 == 0) goto L_0x0022
            if (r4 == 0) goto L_0x002c
            r2.interrupt()     // Catch:{ all -> 0x0020 }
            goto L_0x002c
        L_0x0020:
            r3 = move-exception
            goto L_0x0031
        L_0x0022:
            com.appsamurai.storyly.exoplayer2.common.util.ConditionVariable r4 = r3.started     // Catch:{ all -> 0x0020 }
            r4.open()     // Catch:{ all -> 0x0020 }
            com.appsamurai.storyly.exoplayer2.common.util.ConditionVariable r3 = r3.finished     // Catch:{ all -> 0x0020 }
            r3.open()     // Catch:{ all -> 0x0020 }
        L_0x002c:
            monitor-exit(r0)     // Catch:{ all -> 0x0020 }
            return r1
        L_0x002e:
            monitor-exit(r0)     // Catch:{ all -> 0x0020 }
            r3 = 0
            return r3
        L_0x0031:
            monitor-exit(r0)     // Catch:{ all -> 0x0020 }
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.exoplayer2.common.util.RunnableFutureTask.cancel(boolean):boolean");
    }

    public void cancelWork() {
    }

    @UnknownNull
    public abstract R doWork() throws Exception;

    @UnknownNull
    public final R get() throws ExecutionException, InterruptedException {
        this.finished.block();
        return getResult();
    }

    public final boolean isCancelled() {
        return this.canceled;
    }

    public final boolean isDone() {
        return this.finished.isOpen();
    }

    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void run() {
        /*
            r4 = this;
            java.lang.Object r0 = r4.cancelLock
            monitor-enter(r0)
            boolean r1 = r4.canceled     // Catch:{ all -> 0x0009 }
            if (r1 == 0) goto L_0x000b
            monitor-exit(r0)     // Catch:{ all -> 0x0009 }
            return
        L_0x0009:
            r4 = move-exception
            goto L_0x0059
        L_0x000b:
            java.lang.Thread r1 = java.lang.Thread.currentThread()     // Catch:{ all -> 0x0009 }
            r4.workThread = r1     // Catch:{ all -> 0x0009 }
            monitor-exit(r0)     // Catch:{ all -> 0x0009 }
            com.appsamurai.storyly.exoplayer2.common.util.ConditionVariable r0 = r4.started
            r0.open()
            r0 = 0
            java.lang.Object r1 = r4.doWork()     // Catch:{ Exception -> 0x0032 }
            r4.result = r1     // Catch:{ Exception -> 0x0032 }
            java.lang.Object r1 = r4.cancelLock
            monitor-enter(r1)
            com.appsamurai.storyly.exoplayer2.common.util.ConditionVariable r2 = r4.finished     // Catch:{ all -> 0x002d }
            r2.open()     // Catch:{ all -> 0x002d }
            r4.workThread = r0     // Catch:{ all -> 0x002d }
            java.lang.Thread.interrupted()     // Catch:{ all -> 0x002d }
            monitor-exit(r1)     // Catch:{ all -> 0x002d }
            goto L_0x0043
        L_0x002d:
            r4 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x002d }
            throw r4
        L_0x0030:
            r1 = move-exception
            goto L_0x0047
        L_0x0032:
            r1 = move-exception
            r4.exception = r1     // Catch:{ all -> 0x0030 }
            java.lang.Object r1 = r4.cancelLock
            monitor-enter(r1)
            com.appsamurai.storyly.exoplayer2.common.util.ConditionVariable r2 = r4.finished     // Catch:{ all -> 0x0044 }
            r2.open()     // Catch:{ all -> 0x0044 }
            r4.workThread = r0     // Catch:{ all -> 0x0044 }
            java.lang.Thread.interrupted()     // Catch:{ all -> 0x0044 }
            monitor-exit(r1)     // Catch:{ all -> 0x0044 }
        L_0x0043:
            return
        L_0x0044:
            r4 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x0044 }
            throw r4
        L_0x0047:
            java.lang.Object r2 = r4.cancelLock
            monitor-enter(r2)
            com.appsamurai.storyly.exoplayer2.common.util.ConditionVariable r3 = r4.finished     // Catch:{ all -> 0x0056 }
            r3.open()     // Catch:{ all -> 0x0056 }
            r4.workThread = r0     // Catch:{ all -> 0x0056 }
            java.lang.Thread.interrupted()     // Catch:{ all -> 0x0056 }
            monitor-exit(r2)     // Catch:{ all -> 0x0056 }
            throw r1
        L_0x0056:
            r4 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x0056 }
            throw r4
        L_0x0059:
            monitor-exit(r0)     // Catch:{ all -> 0x0009 }
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.exoplayer2.common.util.RunnableFutureTask.run():void");
    }

    @UnknownNull
    public final R get(long j2, TimeUnit timeUnit) throws ExecutionException, InterruptedException, TimeoutException {
        if (this.finished.block(TimeUnit.MILLISECONDS.convert(j2, timeUnit))) {
            return getResult();
        }
        throw new TimeoutException();
    }
}
