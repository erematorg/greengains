package com.adjust.sdk.scheduler;

import com.adjust.sdk.AdjustFactory;
import java.util.concurrent.Callable;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class SingleThreadFutureScheduler implements FutureScheduler {
    private ScheduledThreadPoolExecutor scheduledThreadPoolExecutor;

    public class a implements RejectedExecutionHandler {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ String f3337a;

        public a(String str) {
            this.f3337a = str;
        }

        public final void rejectedExecution(Runnable runnable, ThreadPoolExecutor threadPoolExecutor) {
            AdjustFactory.getLogger().warn("Runnable [%s] rejected from [%s] ", runnable.toString(), this.f3337a);
        }
    }

    public class b implements Callable<V> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Callable f3338a;

        public b(Callable callable) {
            this.f3338a = callable;
        }

        public final V call() {
            try {
                return this.f3338a.call();
            } catch (Throwable th) {
                AdjustFactory.getLogger().error("Callable error [%s] of type [%s]", th.getMessage(), th.getClass().getCanonicalName());
                return null;
            }
        }
    }

    public SingleThreadFutureScheduler(String str, boolean z2) {
        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor2 = new ScheduledThreadPoolExecutor(1, new ThreadFactoryWrapper(str), new a(str));
        this.scheduledThreadPoolExecutor = scheduledThreadPoolExecutor2;
        if (!z2) {
            scheduledThreadPoolExecutor2.setKeepAliveTime(10, TimeUnit.MILLISECONDS);
            this.scheduledThreadPoolExecutor.allowCoreThreadTimeOut(true);
        }
    }

    public ScheduledFuture<?> scheduleFuture(Runnable runnable, long j2) {
        return this.scheduledThreadPoolExecutor.schedule(new RunnableWrapper(runnable), j2, TimeUnit.MILLISECONDS);
    }

    public ScheduledFuture<?> scheduleFutureWithFixedDelay(Runnable runnable, long j2, long j3) {
        return this.scheduledThreadPoolExecutor.scheduleWithFixedDelay(new RunnableWrapper(runnable), j2, j3, TimeUnit.MILLISECONDS);
    }

    public <V> ScheduledFuture<V> scheduleFutureWithReturn(Callable<V> callable, long j2) {
        return this.scheduledThreadPoolExecutor.schedule(new b(callable), j2, TimeUnit.MILLISECONDS);
    }

    public void teardown() {
        this.scheduledThreadPoolExecutor.shutdownNow();
    }
}
