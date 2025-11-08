package com.adjust.sdk.scheduler;

import java.util.concurrent.Callable;
import java.util.concurrent.ScheduledFuture;

public interface FutureScheduler {
    ScheduledFuture<?> scheduleFuture(Runnable runnable, long j2);

    ScheduledFuture<?> scheduleFutureWithFixedDelay(Runnable runnable, long j2, long j3);

    <V> ScheduledFuture<V> scheduleFutureWithReturn(Callable<V> callable, long j2);

    void teardown();
}
