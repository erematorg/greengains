package com.google.android.gms.measurement.internal;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

final class zzkh implements Runnable {
    private final /* synthetic */ AtomicReference zza;
    private final /* synthetic */ boolean zzb;
    private final /* synthetic */ zzjk zzc;

    public zzkh(zzjk zzjk, AtomicReference atomicReference, boolean z2) {
        this.zza = atomicReference;
        this.zzb = z2;
        this.zzc = zzjk;
    }

    public final void run() {
        this.zzc.zzo().zza((AtomicReference<List<zzok>>) this.zza, this.zzb);
    }
}
