package com.adjust.sdk.scheduler;

public interface ThreadScheduler extends ThreadExecutor {
    void schedule(Runnable runnable, long j2);
}
