package com.google.android.gms.measurement.internal;

import java.util.concurrent.atomic.AtomicReference;

public final /* synthetic */ class zzjp implements Runnable {
    private /* synthetic */ zzjk zza;
    private /* synthetic */ AtomicReference zzb;

    public /* synthetic */ zzjp(zzjk zzjk, AtomicReference atomicReference) {
        this.zza = zzjk;
        this.zzb = atomicReference;
    }

    public final void run() {
        this.zza.zza(this.zzb);
    }
}
