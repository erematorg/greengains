package com.google.mlkit.common.sdkinternal;

import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.mlkit.common.MlKitException;
import java.util.concurrent.Callable;

public final /* synthetic */ class zzf implements Runnable {
    public final /* synthetic */ Callable zza;
    public final /* synthetic */ TaskCompletionSource zzb;

    public /* synthetic */ zzf(Callable callable, TaskCompletionSource taskCompletionSource) {
        this.zza = callable;
        this.zzb = taskCompletionSource;
    }

    public final void run() {
        Callable callable = this.zza;
        TaskCompletionSource taskCompletionSource = this.zzb;
        try {
            taskCompletionSource.setResult(callable.call());
        } catch (MlKitException e3) {
            taskCompletionSource.setException(e3);
        } catch (Exception e4) {
            taskCompletionSource.setException(new MlKitException("Internal error has occurred when executing ML Kit tasks", 13, e4));
        }
    }
}
