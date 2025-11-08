package com.google.android.gms.internal.base;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

final class zas implements zaq {
    private zas() {
    }

    public final ExecutorService zaa(ThreadFactory threadFactory, int i3) {
        return zac(1, threadFactory, 1);
    }

    public final ExecutorService zab(int i3, int i4) {
        return zac(4, Executors.defaultThreadFactory(), 2);
    }

    public final ExecutorService zac(int i3, ThreadFactory threadFactory, int i4) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(i3, i3, 60, TimeUnit.SECONDS, new LinkedBlockingQueue(), threadFactory);
        threadPoolExecutor.allowCoreThreadTimeOut(true);
        return Executors.unconfigurableExecutorService(threadPoolExecutor);
    }

    public /* synthetic */ zas(zar zar) {
    }
}
