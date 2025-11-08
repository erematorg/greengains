package com.google.mlkit.common.internal.model;

import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.mlkit.common.model.CustomRemoteModel;

public final /* synthetic */ class zzb implements Runnable {
    public final /* synthetic */ zzg zza;
    public final /* synthetic */ CustomRemoteModel zzb;
    public final /* synthetic */ TaskCompletionSource zzc;

    public /* synthetic */ zzb(zzg zzg, CustomRemoteModel customRemoteModel, TaskCompletionSource taskCompletionSource) {
        this.zza = zzg;
        this.zzb = customRemoteModel;
        this.zzc = taskCompletionSource;
    }

    public final void run() {
        this.zza.zzb(this.zzb, this.zzc);
    }
}
