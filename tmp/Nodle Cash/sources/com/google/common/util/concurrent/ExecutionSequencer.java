package com.google.common.util.concurrent;

import com.google.common.annotations.J2ktIncompatible;
import com.google.common.base.Preconditions;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicReference;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
@J2ktIncompatible
public final class ExecutionSequencer {
    /* access modifiers changed from: private */
    public ThreadConfinedTaskQueue latestTaskQueue = new ThreadConfinedTaskQueue();
    private final AtomicReference<ListenableFuture<Void>> ref = new AtomicReference<>(Futures.immediateVoidFuture());

    public enum RunningState {
        NOT_RUN,
        CANCELLED,
        STARTED
    }

    public static final class TaskNonReentrantExecutor extends AtomicReference<RunningState> implements Executor, Runnable {
        @CheckForNull
        Executor delegate;
        @CheckForNull
        ExecutionSequencer sequencer;
        @CheckForNull
        Thread submitting;
        @CheckForNull
        Runnable task;

        /* access modifiers changed from: private */
        public boolean trySetCancelled() {
            return compareAndSet(RunningState.NOT_RUN, RunningState.CANCELLED);
        }

        /* access modifiers changed from: private */
        public boolean trySetStarted() {
            return compareAndSet(RunningState.NOT_RUN, RunningState.STARTED);
        }

        public void execute(Runnable runnable) {
            if (get() == RunningState.CANCELLED) {
                this.delegate = null;
                this.sequencer = null;
                return;
            }
            this.submitting = Thread.currentThread();
            try {
                ExecutionSequencer executionSequencer = this.sequencer;
                Objects.requireNonNull(executionSequencer);
                ThreadConfinedTaskQueue access$300 = executionSequencer.latestTaskQueue;
                if (access$300.thread == this.submitting) {
                    this.sequencer = null;
                    Preconditions.checkState(access$300.nextTask == null);
                    access$300.nextTask = runnable;
                    Executor executor = this.delegate;
                    Objects.requireNonNull(executor);
                    access$300.nextExecutor = executor;
                    this.delegate = null;
                } else {
                    Executor executor2 = this.delegate;
                    Objects.requireNonNull(executor2);
                    this.delegate = null;
                    this.task = runnable;
                    executor2.execute(this);
                }
            } finally {
                this.submitting = null;
            }
        }

        public void run() {
            Executor executor;
            Thread currentThread = Thread.currentThread();
            if (currentThread != this.submitting) {
                Runnable runnable = this.task;
                Objects.requireNonNull(runnable);
                this.task = null;
                runnable.run();
                return;
            }
            ThreadConfinedTaskQueue threadConfinedTaskQueue = new ThreadConfinedTaskQueue();
            threadConfinedTaskQueue.thread = currentThread;
            ExecutionSequencer executionSequencer = this.sequencer;
            Objects.requireNonNull(executionSequencer);
            ThreadConfinedTaskQueue unused = executionSequencer.latestTaskQueue = threadConfinedTaskQueue;
            this.sequencer = null;
            try {
                Runnable runnable2 = this.task;
                Objects.requireNonNull(runnable2);
                this.task = null;
                runnable2.run();
                while (true) {
                    Runnable runnable3 = threadConfinedTaskQueue.nextTask;
                    if (runnable3 != null && (executor = threadConfinedTaskQueue.nextExecutor) != null) {
                        threadConfinedTaskQueue.nextTask = null;
                        threadConfinedTaskQueue.nextExecutor = null;
                        executor.execute(runnable3);
                    }
                }
            } finally {
                threadConfinedTaskQueue.thread = null;
            }
        }

        private TaskNonReentrantExecutor(Executor executor, ExecutionSequencer executionSequencer) {
            super(RunningState.NOT_RUN);
            this.delegate = executor;
            this.sequencer = executionSequencer;
        }
    }

    public static final class ThreadConfinedTaskQueue {
        @CheckForNull
        Executor nextExecutor;
        @CheckForNull
        Runnable nextTask;
        @CheckForNull
        Thread thread;

        private ThreadConfinedTaskQueue() {
        }
    }

    private ExecutionSequencer() {
    }

    public static ExecutionSequencer create() {
        return new ExecutionSequencer();
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$submitAsync$0(TrustedListenableFutureTask trustedListenableFutureTask, SettableFuture settableFuture, ListenableFuture listenableFuture, ListenableFuture listenableFuture2, TaskNonReentrantExecutor taskNonReentrantExecutor) {
        if (trustedListenableFutureTask.isDone()) {
            settableFuture.setFuture(listenableFuture);
        } else if (listenableFuture2.isCancelled() && taskNonReentrantExecutor.trySetCancelled()) {
            trustedListenableFutureTask.cancel(false);
        }
    }

    public <T> ListenableFuture<T> submit(final Callable<T> callable, Executor executor) {
        Preconditions.checkNotNull(callable);
        Preconditions.checkNotNull(executor);
        return submitAsync(new AsyncCallable<T>(this) {
            public ListenableFuture<T> call() throws Exception {
                return Futures.immediateFuture(callable.call());
            }

            public String toString() {
                return callable.toString();
            }
        }, executor);
    }

    public <T> ListenableFuture<T> submitAsync(final AsyncCallable<T> asyncCallable, Executor executor) {
        Preconditions.checkNotNull(asyncCallable);
        Preconditions.checkNotNull(executor);
        final TaskNonReentrantExecutor taskNonReentrantExecutor = new TaskNonReentrantExecutor(executor, this);
        AnonymousClass2 r8 = new AsyncCallable<T>(this) {
            public ListenableFuture<T> call() throws Exception {
                return !taskNonReentrantExecutor.trySetStarted() ? Futures.immediateCancelledFuture() : asyncCallable.call();
            }

            public String toString() {
                return asyncCallable.toString();
            }
        };
        SettableFuture create = SettableFuture.create();
        ListenableFuture andSet = this.ref.getAndSet(create);
        TrustedListenableFutureTask create2 = TrustedListenableFutureTask.create(r8);
        andSet.addListener(create2, taskNonReentrantExecutor);
        ListenableFuture<T> nonCancellationPropagating = Futures.nonCancellationPropagating(create2);
        l lVar = new l(create2, create, andSet, nonCancellationPropagating, taskNonReentrantExecutor);
        nonCancellationPropagating.addListener(lVar, MoreExecutors.directExecutor());
        create2.addListener(lVar, MoreExecutors.directExecutor());
        return nonCancellationPropagating;
    }
}
