package com.google.common.util.concurrent;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.concurrent.GuardedBy;
import com.google.j2objc.annotations.RetainedWith;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.Executor;
import java.util.logging.Logger;
import javax.annotation.CheckForNull;
import org.apache.commons.text.StringSubstitutor;

@GwtIncompatible
@ElementTypesAreNonnullByDefault
@J2ktIncompatible
final class SequentialExecutor implements Executor {
    /* access modifiers changed from: private */
    public static final Logger log = Logger.getLogger(SequentialExecutor.class.getName());
    private final Executor executor;
    /* access modifiers changed from: private */
    @GuardedBy("queue")
    public final Deque<Runnable> queue = new ArrayDeque();
    @RetainedWith
    private final QueueWorker worker = new QueueWorker();
    @GuardedBy("queue")
    private long workerRunCount = 0;
    /* access modifiers changed from: private */
    @GuardedBy("queue")
    public WorkerRunningState workerRunningState = WorkerRunningState.IDLE;

    public final class QueueWorker implements Runnable {
        @CheckForNull
        Runnable task;

        private QueueWorker() {
        }

        /* JADX WARNING: Code restructure failed: missing block: B:10:0x0018, code lost:
            java.lang.Thread.currentThread().interrupt();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:18:0x0045, code lost:
            if (r1 == false) goto L_?;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:19:0x0047, code lost:
            java.lang.Thread.currentThread().interrupt();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:24:0x0054, code lost:
            r1 = r1 | java.lang.Thread.interrupted();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:26:?, code lost:
            r8.task.run();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:31:0x0062, code lost:
            r3 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:33:?, code lost:
            com.google.common.util.concurrent.SequentialExecutor.access$400().log(java.util.logging.Level.SEVERE, "Exception while executing runnable " + r8.task, r3);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:49:?, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:50:?, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:51:?, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:52:?, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:9:0x0016, code lost:
            if (r1 == false) goto L_?;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private void workOnQueue() {
            /*
                r8 = this;
                r0 = 0
                r1 = r0
            L_0x0002:
                com.google.common.util.concurrent.SequentialExecutor r2 = com.google.common.util.concurrent.SequentialExecutor.this     // Catch:{ all -> 0x005e }
                java.util.Deque r2 = r2.queue     // Catch:{ all -> 0x005e }
                monitor-enter(r2)     // Catch:{ all -> 0x005e }
                if (r0 != 0) goto L_0x002d
                com.google.common.util.concurrent.SequentialExecutor r0 = com.google.common.util.concurrent.SequentialExecutor.this     // Catch:{ all -> 0x0020 }
                com.google.common.util.concurrent.SequentialExecutor$WorkerRunningState r0 = r0.workerRunningState     // Catch:{ all -> 0x0020 }
                com.google.common.util.concurrent.SequentialExecutor$WorkerRunningState r3 = com.google.common.util.concurrent.SequentialExecutor.WorkerRunningState.RUNNING     // Catch:{ all -> 0x0020 }
                if (r0 != r3) goto L_0x0022
                monitor-exit(r2)     // Catch:{ all -> 0x0020 }
                if (r1 == 0) goto L_0x001f
                java.lang.Thread r8 = java.lang.Thread.currentThread()
                r8.interrupt()
            L_0x001f:
                return
            L_0x0020:
                r8 = move-exception
                goto L_0x0083
            L_0x0022:
                com.google.common.util.concurrent.SequentialExecutor r0 = com.google.common.util.concurrent.SequentialExecutor.this     // Catch:{ all -> 0x0020 }
                com.google.common.util.concurrent.SequentialExecutor.access$308(r0)     // Catch:{ all -> 0x0020 }
                com.google.common.util.concurrent.SequentialExecutor r0 = com.google.common.util.concurrent.SequentialExecutor.this     // Catch:{ all -> 0x0020 }
                com.google.common.util.concurrent.SequentialExecutor.WorkerRunningState unused = r0.workerRunningState = r3     // Catch:{ all -> 0x0020 }
                r0 = 1
            L_0x002d:
                com.google.common.util.concurrent.SequentialExecutor r3 = com.google.common.util.concurrent.SequentialExecutor.this     // Catch:{ all -> 0x0020 }
                java.util.Deque r3 = r3.queue     // Catch:{ all -> 0x0020 }
                java.lang.Object r3 = r3.poll()     // Catch:{ all -> 0x0020 }
                java.lang.Runnable r3 = (java.lang.Runnable) r3     // Catch:{ all -> 0x0020 }
                r8.task = r3     // Catch:{ all -> 0x0020 }
                if (r3 != 0) goto L_0x004f
                com.google.common.util.concurrent.SequentialExecutor r8 = com.google.common.util.concurrent.SequentialExecutor.this     // Catch:{ all -> 0x0020 }
                com.google.common.util.concurrent.SequentialExecutor$WorkerRunningState r0 = com.google.common.util.concurrent.SequentialExecutor.WorkerRunningState.IDLE     // Catch:{ all -> 0x0020 }
                com.google.common.util.concurrent.SequentialExecutor.WorkerRunningState unused = r8.workerRunningState = r0     // Catch:{ all -> 0x0020 }
                monitor-exit(r2)     // Catch:{ all -> 0x0020 }
                if (r1 == 0) goto L_0x004e
                java.lang.Thread r8 = java.lang.Thread.currentThread()
                r8.interrupt()
            L_0x004e:
                return
            L_0x004f:
                monitor-exit(r2)     // Catch:{ all -> 0x0020 }
                boolean r2 = java.lang.Thread.interrupted()     // Catch:{ all -> 0x005e }
                r1 = r1 | r2
                r2 = 0
                java.lang.Runnable r3 = r8.task     // Catch:{ RuntimeException -> 0x0062 }
                r3.run()     // Catch:{ RuntimeException -> 0x0062 }
            L_0x005b:
                r8.task = r2     // Catch:{ all -> 0x005e }
                goto L_0x0002
            L_0x005e:
                r8 = move-exception
                goto L_0x0085
            L_0x0060:
                r0 = move-exception
                goto L_0x0080
            L_0x0062:
                r3 = move-exception
                java.util.logging.Logger r4 = com.google.common.util.concurrent.SequentialExecutor.log     // Catch:{ all -> 0x0060 }
                java.util.logging.Level r5 = java.util.logging.Level.SEVERE     // Catch:{ all -> 0x0060 }
                java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ all -> 0x0060 }
                r6.<init>()     // Catch:{ all -> 0x0060 }
                java.lang.String r7 = "Exception while executing runnable "
                r6.append(r7)     // Catch:{ all -> 0x0060 }
                java.lang.Runnable r7 = r8.task     // Catch:{ all -> 0x0060 }
                r6.append(r7)     // Catch:{ all -> 0x0060 }
                java.lang.String r6 = r6.toString()     // Catch:{ all -> 0x0060 }
                r4.log(r5, r6, r3)     // Catch:{ all -> 0x0060 }
                goto L_0x005b
            L_0x0080:
                r8.task = r2     // Catch:{ all -> 0x005e }
                throw r0     // Catch:{ all -> 0x005e }
            L_0x0083:
                monitor-exit(r2)     // Catch:{ all -> 0x0020 }
                throw r8     // Catch:{ all -> 0x005e }
            L_0x0085:
                if (r1 == 0) goto L_0x008e
                java.lang.Thread r0 = java.lang.Thread.currentThread()
                r0.interrupt()
            L_0x008e:
                throw r8
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.common.util.concurrent.SequentialExecutor.QueueWorker.workOnQueue():void");
        }

        public void run() {
            try {
                workOnQueue();
            } catch (Error e3) {
                synchronized (SequentialExecutor.this.queue) {
                    WorkerRunningState unused = SequentialExecutor.this.workerRunningState = WorkerRunningState.IDLE;
                    throw e3;
                }
            }
        }

        public String toString() {
            Runnable runnable = this.task;
            if (runnable != null) {
                return "SequentialExecutorWorker{running=" + runnable + StringSubstitutor.DEFAULT_VAR_END;
            }
            return "SequentialExecutorWorker{state=" + SequentialExecutor.this.workerRunningState + StringSubstitutor.DEFAULT_VAR_END;
        }
    }

    public enum WorkerRunningState {
        IDLE,
        QUEUING,
        QUEUED,
        RUNNING
    }

    public SequentialExecutor(Executor executor2) {
        this.executor = (Executor) Preconditions.checkNotNull(executor2);
    }

    public static /* synthetic */ long access$308(SequentialExecutor sequentialExecutor) {
        long j2 = sequentialExecutor.workerRunCount;
        sequentialExecutor.workerRunCount = 1 + j2;
        return j2;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:?, code lost:
        r7.executor.execute(r7.worker);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x002b, code lost:
        if (r7.workerRunningState == r8) goto L_0x002e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x002d, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x002e, code lost:
        r0 = r7.queue;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0030, code lost:
        monitor-enter(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0035, code lost:
        if (r7.workerRunCount != r3) goto L_0x0040;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0039, code lost:
        if (r7.workerRunningState != r8) goto L_0x0040;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x003b, code lost:
        r7.workerRunningState = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x003e, code lost:
        r7 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0040, code lost:
        monitor-exit(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0041, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0043, code lost:
        throw r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0044, code lost:
        r8 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0047, code lost:
        monitor-enter(r7.queue);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:?, code lost:
        r0 = r7.workerRunningState;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x004c, code lost:
        if (r0 != com.google.common.util.concurrent.SequentialExecutor.WorkerRunningState.IDLE) goto L_0x004e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0050, code lost:
        if (r0 == com.google.common.util.concurrent.SequentialExecutor.WorkerRunningState.QUEUING) goto L_0x0052;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x0053, code lost:
        r7 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x005b, code lost:
        if (r7.queue.removeLastOccurrence(r1) != false) goto L_0x005d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x005d, code lost:
        r7 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x005f, code lost:
        r7 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x0062, code lost:
        if ((r8 instanceof java.util.concurrent.RejectedExecutionException) != false) goto L_0x0064;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x0067, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x0068, code lost:
        throw r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x006a, code lost:
        throw r7;
     */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x0064 A[ADDED_TO_REGION, Catch:{ Error | RuntimeException -> 0x0044, all -> 0x0053 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void execute(final java.lang.Runnable r8) {
        /*
            r7 = this;
            com.google.common.base.Preconditions.checkNotNull(r8)
            java.util.Deque<java.lang.Runnable> r0 = r7.queue
            monitor-enter(r0)
            com.google.common.util.concurrent.SequentialExecutor$WorkerRunningState r1 = r7.workerRunningState     // Catch:{ all -> 0x006b }
            com.google.common.util.concurrent.SequentialExecutor$WorkerRunningState r2 = com.google.common.util.concurrent.SequentialExecutor.WorkerRunningState.RUNNING     // Catch:{ all -> 0x006b }
            if (r1 == r2) goto L_0x006d
            com.google.common.util.concurrent.SequentialExecutor$WorkerRunningState r2 = com.google.common.util.concurrent.SequentialExecutor.WorkerRunningState.QUEUED     // Catch:{ all -> 0x006b }
            if (r1 != r2) goto L_0x0011
            goto L_0x006d
        L_0x0011:
            long r3 = r7.workerRunCount     // Catch:{ all -> 0x006b }
            com.google.common.util.concurrent.SequentialExecutor$1 r1 = new com.google.common.util.concurrent.SequentialExecutor$1     // Catch:{ all -> 0x006b }
            r1.<init>(r7, r8)     // Catch:{ all -> 0x006b }
            java.util.Deque<java.lang.Runnable> r8 = r7.queue     // Catch:{ all -> 0x006b }
            r8.add(r1)     // Catch:{ all -> 0x006b }
            com.google.common.util.concurrent.SequentialExecutor$WorkerRunningState r8 = com.google.common.util.concurrent.SequentialExecutor.WorkerRunningState.QUEUING     // Catch:{ all -> 0x006b }
            r7.workerRunningState = r8     // Catch:{ all -> 0x006b }
            monitor-exit(r0)     // Catch:{ all -> 0x006b }
            java.util.concurrent.Executor r0 = r7.executor     // Catch:{ Error | RuntimeException -> 0x0044 }
            com.google.common.util.concurrent.SequentialExecutor$QueueWorker r5 = r7.worker     // Catch:{ Error | RuntimeException -> 0x0044 }
            r0.execute(r5)     // Catch:{ Error | RuntimeException -> 0x0044 }
            com.google.common.util.concurrent.SequentialExecutor$WorkerRunningState r0 = r7.workerRunningState
            if (r0 == r8) goto L_0x002e
            return
        L_0x002e:
            java.util.Deque<java.lang.Runnable> r0 = r7.queue
            monitor-enter(r0)
            long r5 = r7.workerRunCount     // Catch:{ all -> 0x003e }
            int r1 = (r5 > r3 ? 1 : (r5 == r3 ? 0 : -1))
            if (r1 != 0) goto L_0x0040
            com.google.common.util.concurrent.SequentialExecutor$WorkerRunningState r1 = r7.workerRunningState     // Catch:{ all -> 0x003e }
            if (r1 != r8) goto L_0x0040
            r7.workerRunningState = r2     // Catch:{ all -> 0x003e }
            goto L_0x0040
        L_0x003e:
            r7 = move-exception
            goto L_0x0042
        L_0x0040:
            monitor-exit(r0)     // Catch:{ all -> 0x003e }
            return
        L_0x0042:
            monitor-exit(r0)     // Catch:{ all -> 0x003e }
            throw r7
        L_0x0044:
            r8 = move-exception
            java.util.Deque<java.lang.Runnable> r2 = r7.queue
            monitor-enter(r2)
            com.google.common.util.concurrent.SequentialExecutor$WorkerRunningState r0 = r7.workerRunningState     // Catch:{ all -> 0x0053 }
            com.google.common.util.concurrent.SequentialExecutor$WorkerRunningState r3 = com.google.common.util.concurrent.SequentialExecutor.WorkerRunningState.IDLE     // Catch:{ all -> 0x0053 }
            if (r0 == r3) goto L_0x0055
            com.google.common.util.concurrent.SequentialExecutor$WorkerRunningState r3 = com.google.common.util.concurrent.SequentialExecutor.WorkerRunningState.QUEUING     // Catch:{ all -> 0x0053 }
            if (r0 != r3) goto L_0x005f
            goto L_0x0055
        L_0x0053:
            r7 = move-exception
            goto L_0x0069
        L_0x0055:
            java.util.Deque<java.lang.Runnable> r7 = r7.queue     // Catch:{ all -> 0x0053 }
            boolean r7 = r7.removeLastOccurrence(r1)     // Catch:{ all -> 0x0053 }
            if (r7 == 0) goto L_0x005f
            r7 = 1
            goto L_0x0060
        L_0x005f:
            r7 = 0
        L_0x0060:
            boolean r0 = r8 instanceof java.util.concurrent.RejectedExecutionException     // Catch:{ all -> 0x0053 }
            if (r0 == 0) goto L_0x0068
            if (r7 != 0) goto L_0x0068
            monitor-exit(r2)     // Catch:{ all -> 0x0053 }
            return
        L_0x0068:
            throw r8     // Catch:{ all -> 0x0053 }
        L_0x0069:
            monitor-exit(r2)     // Catch:{ all -> 0x0053 }
            throw r7
        L_0x006b:
            r7 = move-exception
            goto L_0x0074
        L_0x006d:
            java.util.Deque<java.lang.Runnable> r7 = r7.queue     // Catch:{ all -> 0x006b }
            r7.add(r8)     // Catch:{ all -> 0x006b }
            monitor-exit(r0)     // Catch:{ all -> 0x006b }
            return
        L_0x0074:
            monitor-exit(r0)     // Catch:{ all -> 0x006b }
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.util.concurrent.SequentialExecutor.execute(java.lang.Runnable):void");
    }

    public String toString() {
        return "SequentialExecutor@" + System.identityHashCode(this) + "{" + this.executor + StringSubstitutor.DEFAULT_VAR_END;
    }
}
