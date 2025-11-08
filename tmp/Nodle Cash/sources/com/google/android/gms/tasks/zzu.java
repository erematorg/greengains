package com.google.android.gms.tasks;

import android.os.Handler;
import android.os.Looper;
import androidx.annotation.NonNull;
import com.google.android.gms.internal.tasks.zza;
import java.util.concurrent.Executor;

final class zzu implements Executor {
    private final Handler zza = new zza(Looper.getMainLooper());

    public final void execute(@NonNull Runnable runnable) {
        this.zza.post(runnable);
    }
}
