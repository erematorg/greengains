package com.google.common.util.concurrent;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.common.base.Preconditions;
import com.google.common.primitives.Longs;
import com.google.errorprone.annotations.concurrent.GuardedBy;
import com.google.j2objc.annotations.Weak;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import javax.annotation.CheckForNull;

@GwtIncompatible
@ElementTypesAreNonnullByDefault
@J2ktIncompatible
public final class Monitor {
    @CheckForNull
    @GuardedBy("lock")
    private Guard activeGuards;
    private final boolean fair;
    /* access modifiers changed from: private */
    public final ReentrantLock lock;

    public static abstract class Guard {
        final Condition condition;
        @Weak
        final Monitor monitor;
        @CheckForNull
        @GuardedBy("monitor.lock")
        Guard next;
        @GuardedBy("monitor.lock")
        int waiterCount = 0;

        public Guard(Monitor monitor2) {
            this.monitor = (Monitor) Preconditions.checkNotNull(monitor2, "monitor");
            this.condition = monitor2.lock.newCondition();
        }

        public abstract boolean isSatisfied();
    }

    public Monitor() {
        this(false);
    }

    @GuardedBy("lock")
    private void await(Guard guard, boolean z2) throws InterruptedException {
        if (z2) {
            signalNextWaiter();
        }
        beginWaitingFor(guard);
        do {
            try {
                guard.condition.await();
            } finally {
                endWaitingFor(guard);
            }
        } while (!guard.isSatisfied());
    }

    @GuardedBy("lock")
    private boolean awaitNanos(Guard guard, long j2, boolean z2) throws InterruptedException {
        boolean z3 = true;
        while (j2 > 0) {
            if (z3) {
                if (z2) {
                    try {
                        signalNextWaiter();
                    } catch (Throwable th) {
                        if (!z3) {
                            endWaitingFor(guard);
                        }
                        throw th;
                    }
                }
                beginWaitingFor(guard);
                z3 = false;
            }
            j2 = guard.condition.awaitNanos(j2);
            if (guard.isSatisfied()) {
                if (!z3) {
                    endWaitingFor(guard);
                }
                return true;
            }
        }
        if (!z3) {
            endWaitingFor(guard);
        }
        return false;
    }

    @GuardedBy("lock")
    private void awaitUninterruptibly(Guard guard, boolean z2) {
        if (z2) {
            signalNextWaiter();
        }
        beginWaitingFor(guard);
        do {
            try {
                guard.condition.awaitUninterruptibly();
            } finally {
                endWaitingFor(guard);
            }
        } while (!guard.isSatisfied());
    }

    @GuardedBy("lock")
    private void beginWaitingFor(Guard guard) {
        int i3 = guard.waiterCount;
        guard.waiterCount = i3 + 1;
        if (i3 == 0) {
            guard.next = this.activeGuards;
            this.activeGuards = guard;
        }
    }

    @GuardedBy("lock")
    private void endWaitingFor(Guard guard) {
        int i3 = guard.waiterCount - 1;
        guard.waiterCount = i3;
        if (i3 == 0) {
            Guard guard2 = this.activeGuards;
            Guard guard3 = null;
            while (guard2 != guard) {
                guard3 = guard2;
                guard2 = guard2.next;
            }
            if (guard3 == null) {
                this.activeGuards = guard2.next;
            } else {
                guard3.next = guard2.next;
            }
            guard2.next = null;
        }
    }

    private static long initNanoTime(long j2) {
        if (j2 <= 0) {
            return 0;
        }
        long nanoTime = System.nanoTime();
        if (nanoTime == 0) {
            return 1;
        }
        return nanoTime;
    }

    @GuardedBy("lock")
    private boolean isSatisfied(Guard guard) {
        try {
            return guard.isSatisfied();
        } catch (Error | RuntimeException e3) {
            signalAllWaiters();
            throw e3;
        }
    }

    private static long remainingNanos(long j2, long j3) {
        if (j3 <= 0) {
            return 0;
        }
        return j3 - (System.nanoTime() - j2);
    }

    @GuardedBy("lock")
    private void signalAllWaiters() {
        for (Guard guard = this.activeGuards; guard != null; guard = guard.next) {
            guard.condition.signalAll();
        }
    }

    @GuardedBy("lock")
    private void signalNextWaiter() {
        for (Guard guard = this.activeGuards; guard != null; guard = guard.next) {
            if (isSatisfied(guard)) {
                guard.condition.signal();
                return;
            }
        }
    }

    private static long toSafeNanos(long j2, TimeUnit timeUnit) {
        return Longs.constrainToRange(timeUnit.toNanos(j2), 0, 6917529027641081853L);
    }

    public void enter() {
        this.lock.lock();
    }

    public boolean enterIf(Guard guard) {
        if (guard.monitor == this) {
            ReentrantLock reentrantLock = this.lock;
            reentrantLock.lock();
            try {
                boolean isSatisfied = guard.isSatisfied();
                if (!isSatisfied) {
                }
                return isSatisfied;
            } finally {
                reentrantLock.unlock();
            }
        } else {
            throw new IllegalMonitorStateException();
        }
    }

    public boolean enterIfInterruptibly(Guard guard) throws InterruptedException {
        if (guard.monitor == this) {
            ReentrantLock reentrantLock = this.lock;
            reentrantLock.lockInterruptibly();
            try {
                boolean isSatisfied = guard.isSatisfied();
                if (!isSatisfied) {
                }
                return isSatisfied;
            } finally {
                reentrantLock.unlock();
            }
        } else {
            throw new IllegalMonitorStateException();
        }
    }

    public void enterInterruptibly() throws InterruptedException {
        this.lock.lockInterruptibly();
    }

    public void enterWhen(Guard guard) throws InterruptedException {
        if (guard.monitor == this) {
            ReentrantLock reentrantLock = this.lock;
            boolean isHeldByCurrentThread = reentrantLock.isHeldByCurrentThread();
            reentrantLock.lockInterruptibly();
            try {
                if (!guard.isSatisfied()) {
                    await(guard, isHeldByCurrentThread);
                }
            } catch (Throwable th) {
                leave();
                throw th;
            }
        } else {
            throw new IllegalMonitorStateException();
        }
    }

    public void enterWhenUninterruptibly(Guard guard) {
        if (guard.monitor == this) {
            ReentrantLock reentrantLock = this.lock;
            boolean isHeldByCurrentThread = reentrantLock.isHeldByCurrentThread();
            reentrantLock.lock();
            try {
                if (!guard.isSatisfied()) {
                    awaitUninterruptibly(guard, isHeldByCurrentThread);
                }
            } catch (Throwable th) {
                leave();
                throw th;
            }
        } else {
            throw new IllegalMonitorStateException();
        }
    }

    public int getOccupiedDepth() {
        return this.lock.getHoldCount();
    }

    public int getQueueLength() {
        return this.lock.getQueueLength();
    }

    public int getWaitQueueLength(Guard guard) {
        if (guard.monitor == this) {
            this.lock.lock();
            try {
                return guard.waiterCount;
            } finally {
                this.lock.unlock();
            }
        } else {
            throw new IllegalMonitorStateException();
        }
    }

    public boolean hasQueuedThread(Thread thread) {
        return this.lock.hasQueuedThread(thread);
    }

    public boolean hasQueuedThreads() {
        return this.lock.hasQueuedThreads();
    }

    public boolean hasWaiters(Guard guard) {
        return getWaitQueueLength(guard) > 0;
    }

    public boolean isFair() {
        return this.fair;
    }

    public boolean isOccupied() {
        return this.lock.isLocked();
    }

    public boolean isOccupiedByCurrentThread() {
        return this.lock.isHeldByCurrentThread();
    }

    public void leave() {
        ReentrantLock reentrantLock = this.lock;
        try {
            if (reentrantLock.getHoldCount() == 1) {
                signalNextWaiter();
            }
        } finally {
            reentrantLock.unlock();
        }
    }

    public boolean tryEnter() {
        return this.lock.tryLock();
    }

    public boolean tryEnterIf(Guard guard) {
        if (guard.monitor == this) {
            ReentrantLock reentrantLock = this.lock;
            if (!reentrantLock.tryLock()) {
                return false;
            }
            try {
                boolean isSatisfied = guard.isSatisfied();
                if (!isSatisfied) {
                }
                return isSatisfied;
            } finally {
                reentrantLock.unlock();
            }
        } else {
            throw new IllegalMonitorStateException();
        }
    }

    public void waitFor(Guard guard) throws InterruptedException {
        if (guard.monitor != this || !this.lock.isHeldByCurrentThread()) {
            throw new IllegalMonitorStateException();
        } else if (!guard.isSatisfied()) {
            await(guard, true);
        }
    }

    public void waitForUninterruptibly(Guard guard) {
        if (guard.monitor != this || !this.lock.isHeldByCurrentThread()) {
            throw new IllegalMonitorStateException();
        } else if (!guard.isSatisfied()) {
            awaitUninterruptibly(guard, true);
        }
    }

    public Monitor(boolean z2) {
        this.activeGuards = null;
        this.fair = z2;
        this.lock = new ReentrantLock(z2);
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(3:16|17|18) */
    /* JADX WARNING: Code restructure failed: missing block: B:17:?, code lost:
        r3 = remainingNanos(java.lang.System.nanoTime(), r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0034, code lost:
        r7 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0037, code lost:
        java.lang.Thread.currentThread().interrupt();
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:16:0x002e */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0037  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean enter(long r7, java.util.concurrent.TimeUnit r9) {
        /*
            r6 = this;
            long r7 = toSafeNanos(r7, r9)
            java.util.concurrent.locks.ReentrantLock r9 = r6.lock
            boolean r6 = r6.fair
            r0 = 1
            if (r6 != 0) goto L_0x0012
            boolean r6 = r9.tryLock()
            if (r6 == 0) goto L_0x0012
            return r0
        L_0x0012:
            boolean r6 = java.lang.Thread.interrupted()
            long r1 = java.lang.System.nanoTime()     // Catch:{ all -> 0x002b }
            r3 = r7
        L_0x001b:
            java.util.concurrent.TimeUnit r5 = java.util.concurrent.TimeUnit.NANOSECONDS     // Catch:{ InterruptedException -> 0x002e }
            boolean r7 = r9.tryLock(r3, r5)     // Catch:{ InterruptedException -> 0x002e }
            if (r6 == 0) goto L_0x002a
            java.lang.Thread r6 = java.lang.Thread.currentThread()
            r6.interrupt()
        L_0x002a:
            return r7
        L_0x002b:
            r7 = move-exception
            r0 = r6
            goto L_0x0035
        L_0x002e:
            long r3 = remainingNanos(r1, r7)     // Catch:{ all -> 0x0034 }
            r6 = r0
            goto L_0x001b
        L_0x0034:
            r7 = move-exception
        L_0x0035:
            if (r0 == 0) goto L_0x003e
            java.lang.Thread r6 = java.lang.Thread.currentThread()
            r6.interrupt()
        L_0x003e:
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.util.concurrent.Monitor.enter(long, java.util.concurrent.TimeUnit):boolean");
    }

    public boolean enterInterruptibly(long j2, TimeUnit timeUnit) throws InterruptedException {
        return this.lock.tryLock(j2, timeUnit);
    }

    public boolean waitFor(Guard guard, long j2, TimeUnit timeUnit) throws InterruptedException {
        long safeNanos = toSafeNanos(j2, timeUnit);
        if (guard.monitor != this || !this.lock.isHeldByCurrentThread()) {
            throw new IllegalMonitorStateException();
        } else if (guard.isSatisfied()) {
            return true;
        } else {
            if (!Thread.interrupted()) {
                return awaitNanos(guard, safeNanos, true);
            }
            throw new InterruptedException();
        }
    }

    /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0033 */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x004b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean waitForUninterruptibly(com.google.common.util.concurrent.Monitor.Guard r7, long r8, java.util.concurrent.TimeUnit r10) {
        /*
            r6 = this;
            long r8 = toSafeNanos(r8, r10)
            com.google.common.util.concurrent.Monitor r10 = r7.monitor
            if (r10 != r6) goto L_0x0053
            java.util.concurrent.locks.ReentrantLock r10 = r6.lock
            boolean r10 = r10.isHeldByCurrentThread()
            if (r10 == 0) goto L_0x0053
            boolean r10 = r7.isSatisfied()
            r0 = 1
            if (r10 == 0) goto L_0x0018
            return r0
        L_0x0018:
            long r1 = initNanoTime(r8)
            boolean r10 = java.lang.Thread.interrupted()
            r3 = r8
            r5 = r0
        L_0x0022:
            boolean r6 = r6.awaitNanos(r7, r3, r5)     // Catch:{ InterruptedException -> 0x0033, all -> 0x0030 }
            if (r10 == 0) goto L_0x002f
            java.lang.Thread r7 = java.lang.Thread.currentThread()
            r7.interrupt()
        L_0x002f:
            return r6
        L_0x0030:
            r6 = move-exception
            r0 = r10
            goto L_0x0049
        L_0x0033:
            boolean r10 = r7.isSatisfied()     // Catch:{ all -> 0x0048 }
            if (r10 == 0) goto L_0x0041
            java.lang.Thread r6 = java.lang.Thread.currentThread()
            r6.interrupt()
            return r0
        L_0x0041:
            long r3 = remainingNanos(r1, r8)     // Catch:{ all -> 0x0048 }
            r5 = 0
            r10 = r0
            goto L_0x0022
        L_0x0048:
            r6 = move-exception
        L_0x0049:
            if (r0 == 0) goto L_0x0052
            java.lang.Thread r7 = java.lang.Thread.currentThread()
            r7.interrupt()
        L_0x0052:
            throw r6
        L_0x0053:
            java.lang.IllegalMonitorStateException r6 = new java.lang.IllegalMonitorStateException
            r6.<init>()
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.util.concurrent.Monitor.waitForUninterruptibly(com.google.common.util.concurrent.Monitor$Guard, long, java.util.concurrent.TimeUnit):boolean");
    }

    public boolean enterIf(Guard guard, long j2, TimeUnit timeUnit) {
        if (guard.monitor != this) {
            throw new IllegalMonitorStateException();
        } else if (!enter(j2, timeUnit)) {
            return false;
        } else {
            try {
                boolean isSatisfied = guard.isSatisfied();
                if (!isSatisfied) {
                }
                return isSatisfied;
            } finally {
                this.lock.unlock();
            }
        }
    }

    public boolean enterIfInterruptibly(Guard guard, long j2, TimeUnit timeUnit) throws InterruptedException {
        if (guard.monitor == this) {
            ReentrantLock reentrantLock = this.lock;
            if (!reentrantLock.tryLock(j2, timeUnit)) {
                return false;
            }
            try {
                boolean isSatisfied = guard.isSatisfied();
                if (!isSatisfied) {
                }
                return isSatisfied;
            } finally {
                reentrantLock.unlock();
            }
        } else {
            throw new IllegalMonitorStateException();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0047, code lost:
        if (awaitNanos(r11, r0, r3) != false) goto L_0x004c;
     */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x003a A[Catch:{ all -> 0x004a, all -> 0x0059 }] */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x004f A[DONT_GENERATE] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean enterWhen(com.google.common.util.concurrent.Monitor.Guard r11, long r12, java.util.concurrent.TimeUnit r14) throws java.lang.InterruptedException {
        /*
            r10 = this;
            long r0 = toSafeNanos(r12, r14)
            com.google.common.util.concurrent.Monitor r2 = r11.monitor
            if (r2 != r10) goto L_0x0062
            java.util.concurrent.locks.ReentrantLock r2 = r10.lock
            boolean r3 = r2.isHeldByCurrentThread()
            boolean r4 = r10.fair
            r5 = 0
            r6 = 0
            if (r4 != 0) goto L_0x0029
            boolean r4 = java.lang.Thread.interrupted()
            if (r4 != 0) goto L_0x0023
            boolean r4 = r2.tryLock()
            if (r4 == 0) goto L_0x0029
            r8 = r6
            goto L_0x0034
        L_0x0023:
            java.lang.InterruptedException r10 = new java.lang.InterruptedException
            r10.<init>()
            throw r10
        L_0x0029:
            long r8 = initNanoTime(r0)
            boolean r12 = r2.tryLock(r12, r14)
            if (r12 != 0) goto L_0x0034
            return r5
        L_0x0034:
            boolean r12 = r11.isSatisfied()     // Catch:{ all -> 0x004a }
            if (r12 != 0) goto L_0x004c
            int r12 = (r8 > r6 ? 1 : (r8 == r6 ? 0 : -1))
            if (r12 != 0) goto L_0x003f
            goto L_0x0043
        L_0x003f:
            long r0 = remainingNanos(r8, r0)     // Catch:{ all -> 0x004a }
        L_0x0043:
            boolean r10 = r10.awaitNanos(r11, r0, r3)     // Catch:{ all -> 0x004a }
            if (r10 == 0) goto L_0x004d
            goto L_0x004c
        L_0x004a:
            r11 = move-exception
            goto L_0x0053
        L_0x004c:
            r5 = 1
        L_0x004d:
            if (r5 != 0) goto L_0x0052
            r2.unlock()
        L_0x0052:
            return r5
        L_0x0053:
            if (r3 != 0) goto L_0x005e
            r10.signalNextWaiter()     // Catch:{ all -> 0x0059 }
            goto L_0x005e
        L_0x0059:
            r10 = move-exception
            r2.unlock()
            throw r10
        L_0x005e:
            r2.unlock()
            throw r11
        L_0x0062:
            java.lang.IllegalMonitorStateException r10 = new java.lang.IllegalMonitorStateException
            r10.<init>()
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.util.concurrent.Monitor.enterWhen(com.google.common.util.concurrent.Monitor$Guard, long, java.util.concurrent.TimeUnit):boolean");
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(3:42|43|44) */
    /* JADX WARNING: Code restructure failed: missing block: B:43:?, code lost:
        r9 = remainingNanos(r7, r13);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x0073, code lost:
        r11 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x0074, code lost:
        r1 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x0077, code lost:
        java.lang.Thread.currentThread().interrupt();
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:42:0x006d */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0038 A[Catch:{ InterruptedException -> 0x0060, all -> 0x0043, all -> 0x0023 }] */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0039 A[Catch:{ InterruptedException -> 0x0060, all -> 0x0043, all -> 0x0023 }] */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x004f A[SYNTHETIC, Splitter:B:29:0x004f] */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0054  */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x0077  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean enterWhenUninterruptibly(com.google.common.util.concurrent.Monitor.Guard r12, long r13, java.util.concurrent.TimeUnit r15) {
        /*
            r11 = this;
            long r13 = toSafeNanos(r13, r15)
            com.google.common.util.concurrent.Monitor r15 = r12.monitor
            if (r15 != r11) goto L_0x007f
            java.util.concurrent.locks.ReentrantLock r15 = r11.lock
            boolean r0 = r15.isHeldByCurrentThread()
            boolean r1 = java.lang.Thread.interrupted()
            boolean r2 = r11.fair     // Catch:{ all -> 0x0023 }
            r3 = 0
            r4 = 0
            r6 = 1
            if (r2 != 0) goto L_0x0025
            boolean r2 = r15.tryLock()     // Catch:{ all -> 0x0023 }
            if (r2 != 0) goto L_0x0021
            goto L_0x0025
        L_0x0021:
            r7 = r4
            goto L_0x0032
        L_0x0023:
            r11 = move-exception
            goto L_0x0075
        L_0x0025:
            long r7 = initNanoTime(r13)     // Catch:{ all -> 0x0023 }
            r9 = r13
        L_0x002a:
            java.util.concurrent.TimeUnit r2 = java.util.concurrent.TimeUnit.NANOSECONDS     // Catch:{ InterruptedException -> 0x006d }
            boolean r2 = r15.tryLock(r9, r2)     // Catch:{ InterruptedException -> 0x006d }
            if (r2 == 0) goto L_0x0063
        L_0x0032:
            boolean r2 = r12.isSatisfied()     // Catch:{ InterruptedException -> 0x0060, all -> 0x0043 }
            if (r2 == 0) goto L_0x0039
            goto L_0x004d
        L_0x0039:
            int r2 = (r7 > r4 ? 1 : (r7 == r4 ? 0 : -1))
            if (r2 != 0) goto L_0x0045
            long r7 = initNanoTime(r13)     // Catch:{ InterruptedException -> 0x0060, all -> 0x0043 }
            r9 = r13
            goto L_0x0049
        L_0x0043:
            r11 = move-exception
            goto L_0x005c
        L_0x0045:
            long r9 = remainingNanos(r7, r13)     // Catch:{ InterruptedException -> 0x0060, all -> 0x0043 }
        L_0x0049:
            boolean r6 = r11.awaitNanos(r12, r9, r0)     // Catch:{ InterruptedException -> 0x0060, all -> 0x0043 }
        L_0x004d:
            if (r6 != 0) goto L_0x0052
            r15.unlock()     // Catch:{ all -> 0x0023 }
        L_0x0052:
            if (r1 == 0) goto L_0x005b
            java.lang.Thread r11 = java.lang.Thread.currentThread()
            r11.interrupt()
        L_0x005b:
            return r6
        L_0x005c:
            r15.unlock()     // Catch:{ all -> 0x0023 }
            throw r11     // Catch:{ all -> 0x0023 }
        L_0x0060:
            r0 = r3
            r1 = r6
            goto L_0x0032
        L_0x0063:
            if (r1 == 0) goto L_0x006c
            java.lang.Thread r11 = java.lang.Thread.currentThread()
            r11.interrupt()
        L_0x006c:
            return r3
        L_0x006d:
            long r9 = remainingNanos(r7, r13)     // Catch:{ all -> 0x0073 }
            r1 = r6
            goto L_0x002a
        L_0x0073:
            r11 = move-exception
            r1 = r6
        L_0x0075:
            if (r1 == 0) goto L_0x007e
            java.lang.Thread r12 = java.lang.Thread.currentThread()
            r12.interrupt()
        L_0x007e:
            throw r11
        L_0x007f:
            java.lang.IllegalMonitorStateException r11 = new java.lang.IllegalMonitorStateException
            r11.<init>()
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.util.concurrent.Monitor.enterWhenUninterruptibly(com.google.common.util.concurrent.Monitor$Guard, long, java.util.concurrent.TimeUnit):boolean");
    }
}
