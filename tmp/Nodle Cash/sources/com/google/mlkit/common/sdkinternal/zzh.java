package com.google.mlkit.common.sdkinternal;

import androidx.annotation.NonNull;
import java.util.concurrent.Executor;

enum zzh implements Executor {
    INSTANCE;

    public final void execute(@NonNull Runnable runnable) {
        MLTaskExecutor.getInstance().zzc.post(runnable);
    }
}
