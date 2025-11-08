package com.google.mlkit.common.sdkinternal;

import com.google.android.gms.tasks.CancellationToken;
import com.google.android.gms.tasks.CancellationTokenSource;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.concurrent.Callable;

public final /* synthetic */ class zzn implements Runnable {
    public final /* synthetic */ ModelResource zza;
    public final /* synthetic */ CancellationToken zzb;
    public final /* synthetic */ CancellationTokenSource zzc;
    public final /* synthetic */ Callable zzd;
    public final /* synthetic */ TaskCompletionSource zze;

    public /* synthetic */ zzn(ModelResource modelResource, CancellationToken cancellationToken, CancellationTokenSource cancellationTokenSource, Callable callable, TaskCompletionSource taskCompletionSource) {
        this.zza = modelResource;
        this.zzb = cancellationToken;
        this.zzc = cancellationTokenSource;
        this.zzd = callable;
        this.zze = taskCompletionSource;
    }

    public final void run() {
        this.zza.zza(this.zzb, this.zzc, this.zzd, this.zze);
    }
}
