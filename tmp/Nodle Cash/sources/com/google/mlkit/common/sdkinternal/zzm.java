package com.google.mlkit.common.sdkinternal;

import com.google.android.gms.tasks.CancellationToken;
import com.google.android.gms.tasks.CancellationTokenSource;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.concurrent.Executor;

public final /* synthetic */ class zzm implements Executor {
    public final /* synthetic */ Executor zza;
    public final /* synthetic */ CancellationToken zzb;
    public final /* synthetic */ CancellationTokenSource zzc;
    public final /* synthetic */ TaskCompletionSource zzd;

    public /* synthetic */ zzm(Executor executor, CancellationToken cancellationToken, CancellationTokenSource cancellationTokenSource, TaskCompletionSource taskCompletionSource) {
        this.zza = executor;
        this.zzb = cancellationToken;
        this.zzc = cancellationTokenSource;
        this.zzd = taskCompletionSource;
    }

    public final void execute(Runnable runnable) {
        try {
            this.zza.execute(runnable);
        } catch (RuntimeException e3) {
            if (this.zzb.isCancellationRequested()) {
                this.zzc.cancel();
            } else {
                this.zzd.setException(e3);
            }
            throw e3;
        }
    }
}
