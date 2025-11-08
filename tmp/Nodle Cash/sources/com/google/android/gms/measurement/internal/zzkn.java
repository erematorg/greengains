package com.google.android.gms.measurement.internal;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

final class zzkn implements Runnable {
    private final /* synthetic */ AtomicReference zza;
    private final /* synthetic */ String zzb = null;
    private final /* synthetic */ String zzc;
    private final /* synthetic */ String zzd;
    private final /* synthetic */ zzjk zze;

    public zzkn(zzjk zzjk, AtomicReference atomicReference, String str, String str2, String str3) {
        this.zza = atomicReference;
        this.zzc = str2;
        this.zzd = str3;
        this.zze = zzjk;
    }

    public final void run() {
        this.zze.zzu.zzr().zza((AtomicReference<List<zzaf>>) this.zza, (String) null, this.zzc, this.zzd);
    }
}
