package com.adjust.sdk.scheduler;

import com.adjust.sdk.AdjustFactory;
import com.adjust.sdk.Constants;
import java.lang.Thread;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class ThreadFactoryWrapper implements ThreadFactory {
    private String source;

    public class a implements Thread.UncaughtExceptionHandler {
        public final void uncaughtException(Thread thread, Throwable th) {
            AdjustFactory.getLogger().error("Thread [%s] with error [%s]", thread.getName(), th.getMessage());
        }
    }

    public ThreadFactoryWrapper(String str) {
        this.source = str;
    }

    public Thread newThread(Runnable runnable) {
        Thread newThread = Executors.defaultThreadFactory().newThread(runnable);
        newThread.setPriority(9);
        newThread.setName(Constants.THREAD_PREFIX + newThread.getName() + "-" + this.source);
        newThread.setDaemon(true);
        newThread.setUncaughtExceptionHandler(new a());
        return newThread;
    }
}
