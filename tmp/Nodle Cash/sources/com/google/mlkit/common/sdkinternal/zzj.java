package com.google.mlkit.common.sdkinternal;

import java.util.concurrent.ThreadFactory;

public final /* synthetic */ class zzj implements ThreadFactory {
    public final /* synthetic */ ThreadFactory zza;

    public /* synthetic */ zzj(ThreadFactory threadFactory) {
        this.zza = threadFactory;
    }

    public final Thread newThread(Runnable runnable) {
        return this.zza.newThread(new zzk(runnable));
    }
}
