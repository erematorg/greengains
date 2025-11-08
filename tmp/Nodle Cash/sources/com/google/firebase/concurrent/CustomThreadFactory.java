package com.google.firebase.concurrent;

import android.os.Process;
import android.os.StrictMode;
import java.util.Locale;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicLong;
import javax.annotation.Nullable;

class CustomThreadFactory implements ThreadFactory {
    private static final ThreadFactory DEFAULT = Executors.defaultThreadFactory();
    private final String namePrefix;
    private final StrictMode.ThreadPolicy policy;
    private final int priority;
    private final AtomicLong threadCount = new AtomicLong();

    public CustomThreadFactory(String str, int i3, @Nullable StrictMode.ThreadPolicy threadPolicy) {
        this.namePrefix = str;
        this.priority = i3;
        this.policy = threadPolicy;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$newThread$0(Runnable runnable) {
        Process.setThreadPriority(this.priority);
        StrictMode.ThreadPolicy threadPolicy = this.policy;
        if (threadPolicy != null) {
            StrictMode.setThreadPolicy(threadPolicy);
        }
        runnable.run();
    }

    public Thread newThread(Runnable runnable) {
        Thread newThread = DEFAULT.newThread(new a(this, runnable, 0));
        Locale locale = Locale.ROOT;
        String str = this.namePrefix;
        long andIncrement = this.threadCount.getAndIncrement();
        newThread.setName(str + " Thread #" + andIncrement);
        return newThread;
    }
}
