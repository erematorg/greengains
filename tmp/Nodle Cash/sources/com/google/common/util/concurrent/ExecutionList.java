package com.google.common.util.concurrent;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.concurrent.GuardedBy;
import java.util.concurrent.Executor;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.CheckForNull;

@GwtIncompatible
@ElementTypesAreNonnullByDefault
@J2ktIncompatible
public final class ExecutionList {
    private static final Logger log = Logger.getLogger(ExecutionList.class.getName());
    @GuardedBy("this")
    private boolean executed;
    @CheckForNull
    @GuardedBy("this")
    private RunnableExecutorPair runnables;

    public static final class RunnableExecutorPair {
        final Executor executor;
        @CheckForNull
        RunnableExecutorPair next;
        final Runnable runnable;

        public RunnableExecutorPair(Runnable runnable2, Executor executor2, @CheckForNull RunnableExecutorPair runnableExecutorPair) {
            this.runnable = runnable2;
            this.executor = executor2;
            this.next = runnableExecutorPair;
        }
    }

    private static void executeListener(Runnable runnable, Executor executor) {
        try {
            executor.execute(runnable);
        } catch (RuntimeException e3) {
            Logger logger = log;
            Level level = Level.SEVERE;
            logger.log(level, "RuntimeException while executing runnable " + runnable + " with executor " + executor, e3);
        }
    }

    public void add(Runnable runnable, Executor executor) {
        Preconditions.checkNotNull(runnable, "Runnable was null.");
        Preconditions.checkNotNull(executor, "Executor was null.");
        synchronized (this) {
            try {
                if (!this.executed) {
                    this.runnables = new RunnableExecutorPair(runnable, executor, this.runnables);
                } else {
                    executeListener(runnable, executor);
                }
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0012, code lost:
        if (r0 == null) goto L_0x001b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0014, code lost:
        r2 = r0.next;
        r0.next = r1;
        r1 = r0;
        r0 = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001b, code lost:
        if (r1 == null) goto L_0x0027;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x001d, code lost:
        executeListener(r1.runnable, r1.executor);
        r1 = r1.next;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0027, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void execute() {
        /*
            r2 = this;
            monitor-enter(r2)
            boolean r0 = r2.executed     // Catch:{ all -> 0x0007 }
            if (r0 == 0) goto L_0x0009
            monitor-exit(r2)     // Catch:{ all -> 0x0007 }
            return
        L_0x0007:
            r0 = move-exception
            goto L_0x0028
        L_0x0009:
            r0 = 1
            r2.executed = r0     // Catch:{ all -> 0x0007 }
            com.google.common.util.concurrent.ExecutionList$RunnableExecutorPair r0 = r2.runnables     // Catch:{ all -> 0x0007 }
            r1 = 0
            r2.runnables = r1     // Catch:{ all -> 0x0007 }
            monitor-exit(r2)     // Catch:{ all -> 0x0007 }
        L_0x0012:
            if (r0 == 0) goto L_0x001b
            com.google.common.util.concurrent.ExecutionList$RunnableExecutorPair r2 = r0.next
            r0.next = r1
            r1 = r0
            r0 = r2
            goto L_0x0012
        L_0x001b:
            if (r1 == 0) goto L_0x0027
            java.lang.Runnable r2 = r1.runnable
            java.util.concurrent.Executor r0 = r1.executor
            executeListener(r2, r0)
            com.google.common.util.concurrent.ExecutionList$RunnableExecutorPair r1 = r1.next
            goto L_0x001b
        L_0x0027:
            return
        L_0x0028:
            monitor-exit(r2)     // Catch:{ all -> 0x0007 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.util.concurrent.ExecutionList.execute():void");
    }
}
