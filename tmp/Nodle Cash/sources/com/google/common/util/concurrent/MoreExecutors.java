package com.google.common.util.concurrent;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.common.base.Supplier;
import com.google.common.base.Throwables;
import com.google.common.util.concurrent.AbstractFuture;
import com.google.common.util.concurrent.ForwardingListenableFuture;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.concurrent.GuardedBy;
import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.Delayed;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@GwtCompatible(emulated = true)
@ElementTypesAreNonnullByDefault
public final class MoreExecutors {

    @GwtIncompatible
    @J2ktIncompatible
    public static final class DirectExecutorService extends AbstractListeningExecutorService {
        private final Object lock;
        @GuardedBy("lock")
        private int runningTasks;
        @GuardedBy("lock")
        private boolean shutdown;

        private DirectExecutorService() {
            this.lock = new Object();
            this.runningTasks = 0;
            this.shutdown = false;
        }

        private void endTask() {
            synchronized (this.lock) {
                try {
                    int i3 = this.runningTasks - 1;
                    this.runningTasks = i3;
                    if (i3 == 0) {
                        this.lock.notifyAll();
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }

        private void startTask() {
            synchronized (this.lock) {
                try {
                    if (!this.shutdown) {
                        this.runningTasks++;
                    } else {
                        throw new RejectedExecutionException("Executor already shutdown");
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }

        public boolean awaitTermination(long j2, TimeUnit timeUnit) throws InterruptedException {
            long nanos = timeUnit.toNanos(j2);
            synchronized (this.lock) {
                while (true) {
                    try {
                        if (this.shutdown && this.runningTasks == 0) {
                            return true;
                        }
                        if (nanos <= 0) {
                            return false;
                        }
                        long nanoTime = System.nanoTime();
                        TimeUnit.NANOSECONDS.timedWait(this.lock, nanos);
                        nanos -= System.nanoTime() - nanoTime;
                    } finally {
                    }
                }
            }
        }

        public void execute(Runnable runnable) {
            startTask();
            try {
                runnable.run();
            } finally {
                endTask();
            }
        }

        public boolean isShutdown() {
            boolean z2;
            synchronized (this.lock) {
                z2 = this.shutdown;
            }
            return z2;
        }

        public boolean isTerminated() {
            boolean z2;
            synchronized (this.lock) {
                try {
                    z2 = this.shutdown && this.runningTasks == 0;
                } catch (Throwable th) {
                    throw th;
                }
            }
            return z2;
        }

        public void shutdown() {
            synchronized (this.lock) {
                try {
                    this.shutdown = true;
                    if (this.runningTasks == 0) {
                        this.lock.notifyAll();
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }

        public List<Runnable> shutdownNow() {
            shutdown();
            return Collections.emptyList();
        }
    }

    @GwtIncompatible
    @J2ktIncompatible
    public static class ListeningDecorator extends AbstractListeningExecutorService {
        private final ExecutorService delegate;

        public ListeningDecorator(ExecutorService executorService) {
            this.delegate = (ExecutorService) Preconditions.checkNotNull(executorService);
        }

        public final boolean awaitTermination(long j2, TimeUnit timeUnit) throws InterruptedException {
            return this.delegate.awaitTermination(j2, timeUnit);
        }

        public final void execute(Runnable runnable) {
            this.delegate.execute(runnable);
        }

        public final boolean isShutdown() {
            return this.delegate.isShutdown();
        }

        public final boolean isTerminated() {
            return this.delegate.isTerminated();
        }

        public final void shutdown() {
            this.delegate.shutdown();
        }

        public final List<Runnable> shutdownNow() {
            return this.delegate.shutdownNow();
        }

        public final String toString() {
            return super.toString() + "[" + this.delegate + "]";
        }
    }

    @GwtIncompatible
    @J2ktIncompatible
    public static final class ScheduledListeningDecorator extends ListeningDecorator implements ListeningScheduledExecutorService {
        final ScheduledExecutorService delegate;

        public static final class ListenableScheduledTask<V> extends ForwardingListenableFuture.SimpleForwardingListenableFuture<V> implements ListenableScheduledFuture<V> {
            private final ScheduledFuture<?> scheduledDelegate;

            public ListenableScheduledTask(ListenableFuture<V> listenableFuture, ScheduledFuture<?> scheduledFuture) {
                super(listenableFuture);
                this.scheduledDelegate = scheduledFuture;
            }

            public boolean cancel(boolean z2) {
                boolean cancel = super.cancel(z2);
                if (cancel) {
                    this.scheduledDelegate.cancel(z2);
                }
                return cancel;
            }

            public long getDelay(TimeUnit timeUnit) {
                return this.scheduledDelegate.getDelay(timeUnit);
            }

            public int compareTo(Delayed delayed) {
                return this.scheduledDelegate.compareTo(delayed);
            }
        }

        @GwtIncompatible
        @J2ktIncompatible
        public static final class NeverSuccessfulListenableFutureTask extends AbstractFuture.TrustedFuture<Void> implements Runnable {
            private final Runnable delegate;

            public NeverSuccessfulListenableFutureTask(Runnable runnable) {
                this.delegate = (Runnable) Preconditions.checkNotNull(runnable);
            }

            public String pendingToString() {
                return "task=[" + this.delegate + "]";
            }

            public void run() {
                try {
                    this.delegate.run();
                } catch (Error | RuntimeException e3) {
                    setException(e3);
                    throw e3;
                }
            }
        }

        public ScheduledListeningDecorator(ScheduledExecutorService scheduledExecutorService) {
            super(scheduledExecutorService);
            this.delegate = (ScheduledExecutorService) Preconditions.checkNotNull(scheduledExecutorService);
        }

        public ListenableScheduledFuture<?> scheduleAtFixedRate(Runnable runnable, long j2, long j3, TimeUnit timeUnit) {
            NeverSuccessfulListenableFutureTask neverSuccessfulListenableFutureTask = new NeverSuccessfulListenableFutureTask(runnable);
            return new ListenableScheduledTask(neverSuccessfulListenableFutureTask, this.delegate.scheduleAtFixedRate(neverSuccessfulListenableFutureTask, j2, j3, timeUnit));
        }

        public ListenableScheduledFuture<?> scheduleWithFixedDelay(Runnable runnable, long j2, long j3, TimeUnit timeUnit) {
            NeverSuccessfulListenableFutureTask neverSuccessfulListenableFutureTask = new NeverSuccessfulListenableFutureTask(runnable);
            return new ListenableScheduledTask(neverSuccessfulListenableFutureTask, this.delegate.scheduleWithFixedDelay(neverSuccessfulListenableFutureTask, j2, j3, timeUnit));
        }

        public ListenableScheduledFuture<?> schedule(Runnable runnable, long j2, TimeUnit timeUnit) {
            TrustedListenableFutureTask create = TrustedListenableFutureTask.create(runnable, null);
            return new ListenableScheduledTask(create, this.delegate.schedule(create, j2, timeUnit));
        }

        public <V> ListenableScheduledFuture<V> schedule(Callable<V> callable, long j2, TimeUnit timeUnit) {
            TrustedListenableFutureTask<V> create = TrustedListenableFutureTask.create(callable);
            return new ListenableScheduledTask(create, this.delegate.schedule(create, j2, timeUnit));
        }
    }

    private MoreExecutors() {
    }

    @GwtIncompatible
    @J2ktIncompatible
    public static void addDelayedShutdownHook(ExecutorService executorService, long j2, TimeUnit timeUnit) {
        new Application().addDelayedShutdownHook(executorService, j2, timeUnit);
    }

    public static Executor directExecutor() {
        return DirectExecutor.INSTANCE;
    }

    @GwtIncompatible
    @J2ktIncompatible
    public static ExecutorService getExitingExecutorService(ThreadPoolExecutor threadPoolExecutor, long j2, TimeUnit timeUnit) {
        return new Application().getExitingExecutorService(threadPoolExecutor, j2, timeUnit);
    }

    @GwtIncompatible
    @J2ktIncompatible
    public static ScheduledExecutorService getExitingScheduledExecutorService(ScheduledThreadPoolExecutor scheduledThreadPoolExecutor, long j2, TimeUnit timeUnit) {
        return new Application().getExitingScheduledExecutorService(scheduledThreadPoolExecutor, j2, timeUnit);
    }

    /* JADX WARNING: Removed duplicated region for block: B:30:0x0093  */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x00b8 A[SYNTHETIC] */
    @com.google.common.annotations.GwtIncompatible
    @com.google.common.util.concurrent.ParametricNullness
    @com.google.common.annotations.J2ktIncompatible
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static <T> T invokeAnyImpl(com.google.common.util.concurrent.ListeningExecutorService r16, java.util.Collection<? extends java.util.concurrent.Callable<T>> r17, boolean r18, long r19, java.util.concurrent.TimeUnit r21) throws java.lang.InterruptedException, java.util.concurrent.ExecutionException, java.util.concurrent.TimeoutException {
        /*
            r1 = r16
            com.google.common.base.Preconditions.checkNotNull(r16)
            com.google.common.base.Preconditions.checkNotNull(r21)
            int r0 = r17.size()
            r2 = 1
            if (r0 <= 0) goto L_0x0011
            r3 = r2
            goto L_0x0012
        L_0x0011:
            r3 = 0
        L_0x0012:
            com.google.common.base.Preconditions.checkArgument(r3)
            java.util.ArrayList r3 = com.google.common.collect.Lists.newArrayListWithCapacity(r0)
            java.util.concurrent.LinkedBlockingQueue r4 = com.google.common.collect.Queues.newLinkedBlockingQueue()
            r5 = r19
            r7 = r21
            long r5 = r7.toNanos(r5)
            if (r18 == 0) goto L_0x002f
            long r7 = java.lang.System.nanoTime()     // Catch:{ all -> 0x002c }
            goto L_0x0031
        L_0x002c:
            r0 = move-exception
            goto L_0x00bc
        L_0x002f:
            r7 = 0
        L_0x0031:
            java.util.Iterator r9 = r17.iterator()     // Catch:{ all -> 0x002c }
            java.lang.Object r10 = r9.next()     // Catch:{ all -> 0x002c }
            java.util.concurrent.Callable r10 = (java.util.concurrent.Callable) r10     // Catch:{ all -> 0x002c }
            com.google.common.util.concurrent.ListenableFuture r10 = submitAndAddQueueListener(r1, r10, r4)     // Catch:{ all -> 0x002c }
            r3.add(r10)     // Catch:{ all -> 0x002c }
            int r0 = r0 + -1
            r10 = 0
            r11 = r2
            r12 = r10
        L_0x0047:
            java.lang.Object r13 = r4.poll()     // Catch:{ all -> 0x002c }
            java.util.concurrent.Future r13 = (java.util.concurrent.Future) r13     // Catch:{ all -> 0x002c }
            if (r13 != 0) goto L_0x0062
            if (r0 <= 0) goto L_0x0066
            int r0 = r0 + -1
            java.lang.Object r14 = r9.next()     // Catch:{ all -> 0x002c }
            java.util.concurrent.Callable r14 = (java.util.concurrent.Callable) r14     // Catch:{ all -> 0x002c }
            com.google.common.util.concurrent.ListenableFuture r14 = submitAndAddQueueListener(r1, r14, r4)     // Catch:{ all -> 0x002c }
            r3.add(r14)     // Catch:{ all -> 0x002c }
            int r11 = r11 + 1
        L_0x0062:
            r14 = r7
        L_0x0063:
            r6 = r5
            r5 = r0
            goto L_0x0091
        L_0x0066:
            if (r11 != 0) goto L_0x0070
            if (r12 != 0) goto L_0x006f
            java.util.concurrent.ExecutionException r12 = new java.util.concurrent.ExecutionException     // Catch:{ all -> 0x002c }
            r12.<init>(r10)     // Catch:{ all -> 0x002c }
        L_0x006f:
            throw r12     // Catch:{ all -> 0x002c }
        L_0x0070:
            if (r18 == 0) goto L_0x008a
            java.util.concurrent.TimeUnit r13 = java.util.concurrent.TimeUnit.NANOSECONDS     // Catch:{ all -> 0x002c }
            java.lang.Object r13 = r4.poll(r5, r13)     // Catch:{ all -> 0x002c }
            java.util.concurrent.Future r13 = (java.util.concurrent.Future) r13     // Catch:{ all -> 0x002c }
            if (r13 == 0) goto L_0x0084
            long r14 = java.lang.System.nanoTime()     // Catch:{ all -> 0x002c }
            long r7 = r14 - r7
            long r5 = r5 - r7
            goto L_0x0063
        L_0x0084:
            java.util.concurrent.TimeoutException r0 = new java.util.concurrent.TimeoutException     // Catch:{ all -> 0x002c }
            r0.<init>()     // Catch:{ all -> 0x002c }
            throw r0     // Catch:{ all -> 0x002c }
        L_0x008a:
            java.lang.Object r13 = r4.take()     // Catch:{ all -> 0x002c }
            java.util.concurrent.Future r13 = (java.util.concurrent.Future) r13     // Catch:{ all -> 0x002c }
            goto L_0x0062
        L_0x0091:
            if (r13 == 0) goto L_0x00b8
            int r11 = r11 + -1
            java.lang.Object r0 = r13.get()     // Catch:{ ExecutionException -> 0x00b6, RuntimeException -> 0x00ae }
            java.util.Iterator r1 = r3.iterator()
        L_0x009d:
            boolean r3 = r1.hasNext()
            if (r3 == 0) goto L_0x00ad
            java.lang.Object r3 = r1.next()
            java.util.concurrent.Future r3 = (java.util.concurrent.Future) r3
            r3.cancel(r2)
            goto L_0x009d
        L_0x00ad:
            return r0
        L_0x00ae:
            r0 = move-exception
            r8 = r0
            java.util.concurrent.ExecutionException r12 = new java.util.concurrent.ExecutionException     // Catch:{ all -> 0x002c }
            r12.<init>(r8)     // Catch:{ all -> 0x002c }
            goto L_0x00b8
        L_0x00b6:
            r0 = move-exception
            r12 = r0
        L_0x00b8:
            r0 = r5
            r5 = r6
            r7 = r14
            goto L_0x0047
        L_0x00bc:
            java.util.Iterator r1 = r3.iterator()
        L_0x00c0:
            boolean r3 = r1.hasNext()
            if (r3 == 0) goto L_0x00d0
            java.lang.Object r3 = r1.next()
            java.util.concurrent.Future r3 = (java.util.concurrent.Future) r3
            r3.cancel(r2)
            goto L_0x00c0
        L_0x00d0:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.util.concurrent.MoreExecutors.invokeAnyImpl(com.google.common.util.concurrent.ListeningExecutorService, java.util.Collection, boolean, long, java.util.concurrent.TimeUnit):java.lang.Object");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:?, code lost:
        return false;
     */
    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* JADX WARNING: Removed duplicated region for block: B:11:? A[ExcHandler: ClassNotFoundException (unused java.lang.ClassNotFoundException), SYNTHETIC, Splitter:B:7:0x0011] */
    @com.google.common.annotations.GwtIncompatible
    @com.google.common.annotations.J2ktIncompatible
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static boolean isAppEngineWithApiClasses() {
        /*
            java.lang.String r0 = "com.google.appengine.runtime.environment"
            java.lang.String r0 = java.lang.System.getProperty(r0)
            r1 = 0
            if (r0 != 0) goto L_0x000a
            return r1
        L_0x000a:
            java.lang.String r0 = "com.google.appengine.api.utils.SystemProperty"
            java.lang.Class.forName(r0)     // Catch:{  }
            java.lang.String r0 = "com.google.apphosting.api.ApiProxy"
            java.lang.Class r0 = java.lang.Class.forName(r0)     // Catch:{ ClassNotFoundException -> 0x0023, ClassNotFoundException -> 0x0023, ClassNotFoundException -> 0x0023 }
            java.lang.String r2 = "getCurrentEnvironment"
            r3 = 0
            java.lang.reflect.Method r0 = r0.getMethod(r2, r3)     // Catch:{ ClassNotFoundException -> 0x0023, ClassNotFoundException -> 0x0023, ClassNotFoundException -> 0x0023 }
            java.lang.Object r0 = r0.invoke(r3, r3)     // Catch:{ ClassNotFoundException -> 0x0023, ClassNotFoundException -> 0x0023, ClassNotFoundException -> 0x0023 }
            if (r0 == 0) goto L_0x0023
            r1 = 1
        L_0x0023:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.util.concurrent.MoreExecutors.isAppEngineWithApiClasses():boolean");
    }

    @GwtIncompatible
    @J2ktIncompatible
    public static ListeningExecutorService listeningDecorator(ExecutorService executorService) {
        ListeningExecutorService listeningDecorator;
        if (executorService instanceof ListeningExecutorService) {
            return (ListeningExecutorService) executorService;
        }
        if (executorService instanceof ScheduledExecutorService) {
            listeningDecorator = new ScheduledListeningDecorator((ScheduledExecutorService) executorService);
        } else {
            listeningDecorator = new ListeningDecorator(executorService);
        }
        return listeningDecorator;
    }

    @GwtIncompatible
    @J2ktIncompatible
    public static ListeningExecutorService newDirectExecutorService() {
        return new DirectExecutorService();
    }

    @GwtIncompatible
    @J2ktIncompatible
    public static Executor newSequentialExecutor(Executor executor) {
        return new SequentialExecutor(executor);
    }

    @GwtIncompatible
    @J2ktIncompatible
    public static Thread newThread(String str, Runnable runnable) {
        Preconditions.checkNotNull(str);
        Preconditions.checkNotNull(runnable);
        Thread newThread = platformThreadFactory().newThread(runnable);
        Objects.requireNonNull(newThread);
        try {
            newThread.setName(str);
        } catch (SecurityException unused) {
        }
        return newThread;
    }

    @GwtIncompatible
    @J2ktIncompatible
    public static ThreadFactory platformThreadFactory() {
        if (!isAppEngineWithApiClasses()) {
            return Executors.defaultThreadFactory();
        }
        try {
            return (ThreadFactory) Class.forName("com.google.appengine.api.ThreadManager").getMethod("currentRequestThreadFactory", (Class[]) null).invoke((Object) null, (Object[]) null);
        } catch (IllegalAccessException e3) {
            throw new RuntimeException("Couldn't invoke ThreadManager.currentRequestThreadFactory", e3);
        } catch (ClassNotFoundException e4) {
            throw new RuntimeException("Couldn't invoke ThreadManager.currentRequestThreadFactory", e4);
        } catch (NoSuchMethodException e5) {
            throw new RuntimeException("Couldn't invoke ThreadManager.currentRequestThreadFactory", e5);
        } catch (InvocationTargetException e6) {
            throw Throwables.propagate(e6.getCause());
        }
    }

    public static Executor rejectionPropagatingExecutor(final Executor executor, final AbstractFuture<?> abstractFuture) {
        Preconditions.checkNotNull(executor);
        Preconditions.checkNotNull(abstractFuture);
        return executor == directExecutor() ? executor : new Executor() {
            public void execute(Runnable runnable) {
                try {
                    executor.execute(runnable);
                } catch (RejectedExecutionException e3) {
                    abstractFuture.setException(e3);
                }
            }
        };
    }

    @GwtIncompatible
    @J2ktIncompatible
    public static Executor renamingDecorator(final Executor executor, final Supplier<String> supplier) {
        Preconditions.checkNotNull(executor);
        Preconditions.checkNotNull(supplier);
        return new Executor() {
            public void execute(Runnable runnable) {
                executor.execute(Callables.threadRenaming(runnable, (Supplier<String>) supplier));
            }
        };
    }

    @GwtIncompatible
    @CanIgnoreReturnValue
    @J2ktIncompatible
    public static boolean shutdownAndAwaitTermination(ExecutorService executorService, long j2, TimeUnit timeUnit) {
        long nanos = timeUnit.toNanos(j2) / 2;
        executorService.shutdown();
        try {
            TimeUnit timeUnit2 = TimeUnit.NANOSECONDS;
            if (!executorService.awaitTermination(nanos, timeUnit2)) {
                executorService.shutdownNow();
                executorService.awaitTermination(nanos, timeUnit2);
            }
        } catch (InterruptedException unused) {
            Thread.currentThread().interrupt();
            executorService.shutdownNow();
        }
        return executorService.isTerminated();
    }

    @GwtIncompatible
    @J2ktIncompatible
    private static <T> ListenableFuture<T> submitAndAddQueueListener(ListeningExecutorService listeningExecutorService, Callable<T> callable, final BlockingQueue<Future<T>> blockingQueue) {
        final ListenableFuture<T> submit = listeningExecutorService.submit(callable);
        submit.addListener(new Runnable() {
            public void run() {
                blockingQueue.add(submit);
            }
        }, directExecutor());
        return submit;
    }

    /* access modifiers changed from: private */
    @GwtIncompatible
    @J2ktIncompatible
    public static void useDaemonThreadFactory(ThreadPoolExecutor threadPoolExecutor) {
        threadPoolExecutor.setThreadFactory(new ThreadFactoryBuilder().setDaemon(true).setThreadFactory(threadPoolExecutor.getThreadFactory()).build());
    }

    @GwtIncompatible
    @J2ktIncompatible
    public static ExecutorService getExitingExecutorService(ThreadPoolExecutor threadPoolExecutor) {
        return new Application().getExitingExecutorService(threadPoolExecutor);
    }

    @GwtIncompatible
    @VisibleForTesting
    @J2ktIncompatible
    public static class Application {
        public final void addDelayedShutdownHook(ExecutorService executorService, long j2, TimeUnit timeUnit) {
            Preconditions.checkNotNull(executorService);
            Preconditions.checkNotNull(timeUnit);
            final ExecutorService executorService2 = executorService;
            final long j3 = j2;
            final TimeUnit timeUnit2 = timeUnit;
            addShutdownHook(MoreExecutors.newThread("DelayedShutdownHook-for-" + executorService, new Runnable(this) {
                public void run() {
                    try {
                        executorService2.shutdown();
                        executorService2.awaitTermination(j3, timeUnit2);
                    } catch (InterruptedException unused) {
                    }
                }
            }));
        }

        @VisibleForTesting
        public void addShutdownHook(Thread thread) {
            Runtime.getRuntime().addShutdownHook(thread);
        }

        public final ExecutorService getExitingExecutorService(ThreadPoolExecutor threadPoolExecutor, long j2, TimeUnit timeUnit) {
            MoreExecutors.useDaemonThreadFactory(threadPoolExecutor);
            ExecutorService unconfigurableExecutorService = Executors.unconfigurableExecutorService(threadPoolExecutor);
            addDelayedShutdownHook(threadPoolExecutor, j2, timeUnit);
            return unconfigurableExecutorService;
        }

        public final ScheduledExecutorService getExitingScheduledExecutorService(ScheduledThreadPoolExecutor scheduledThreadPoolExecutor, long j2, TimeUnit timeUnit) {
            MoreExecutors.useDaemonThreadFactory(scheduledThreadPoolExecutor);
            ScheduledExecutorService unconfigurableScheduledExecutorService = Executors.unconfigurableScheduledExecutorService(scheduledThreadPoolExecutor);
            addDelayedShutdownHook(scheduledThreadPoolExecutor, j2, timeUnit);
            return unconfigurableScheduledExecutorService;
        }

        public final ExecutorService getExitingExecutorService(ThreadPoolExecutor threadPoolExecutor) {
            return getExitingExecutorService(threadPoolExecutor, 120, TimeUnit.SECONDS);
        }

        public final ScheduledExecutorService getExitingScheduledExecutorService(ScheduledThreadPoolExecutor scheduledThreadPoolExecutor) {
            return getExitingScheduledExecutorService(scheduledThreadPoolExecutor, 120, TimeUnit.SECONDS);
        }
    }

    @GwtIncompatible
    @J2ktIncompatible
    public static ScheduledExecutorService getExitingScheduledExecutorService(ScheduledThreadPoolExecutor scheduledThreadPoolExecutor) {
        return new Application().getExitingScheduledExecutorService(scheduledThreadPoolExecutor);
    }

    @GwtIncompatible
    @J2ktIncompatible
    public static ExecutorService renamingDecorator(ExecutorService executorService, final Supplier<String> supplier) {
        Preconditions.checkNotNull(executorService);
        Preconditions.checkNotNull(supplier);
        return new WrappingExecutorService(executorService) {
            public <T> Callable<T> wrapTask(Callable<T> callable) {
                return Callables.threadRenaming(callable, (Supplier<String>) supplier);
            }

            public Runnable wrapTask(Runnable runnable) {
                return Callables.threadRenaming(runnable, (Supplier<String>) supplier);
            }
        };
    }

    @GwtIncompatible
    @J2ktIncompatible
    public static ListeningScheduledExecutorService listeningDecorator(ScheduledExecutorService scheduledExecutorService) {
        if (scheduledExecutorService instanceof ListeningScheduledExecutorService) {
            return (ListeningScheduledExecutorService) scheduledExecutorService;
        }
        return new ScheduledListeningDecorator(scheduledExecutorService);
    }

    @GwtIncompatible
    @J2ktIncompatible
    public static ScheduledExecutorService renamingDecorator(ScheduledExecutorService scheduledExecutorService, final Supplier<String> supplier) {
        Preconditions.checkNotNull(scheduledExecutorService);
        Preconditions.checkNotNull(supplier);
        return new WrappingScheduledExecutorService(scheduledExecutorService) {
            public <T> Callable<T> wrapTask(Callable<T> callable) {
                return Callables.threadRenaming(callable, (Supplier<String>) supplier);
            }

            public Runnable wrapTask(Runnable runnable) {
                return Callables.threadRenaming(runnable, (Supplier<String>) supplier);
            }
        };
    }
}
