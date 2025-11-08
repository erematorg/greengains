package com.google.android.gms.internal.measurement;

import java.util.concurrent.Callable;

public final /* synthetic */ class zzd implements Callable {
    private /* synthetic */ zzb zza;

    public /* synthetic */ zzd(zzb zzb) {
        this.zza = zzb;
    }

    public final Object call() {
        return new zzk(this.zza.zza);
    }
}
