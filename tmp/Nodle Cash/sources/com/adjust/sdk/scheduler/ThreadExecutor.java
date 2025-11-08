package com.adjust.sdk.scheduler;

public interface ThreadExecutor {
    void submit(Runnable runnable);

    void teardown();
}
