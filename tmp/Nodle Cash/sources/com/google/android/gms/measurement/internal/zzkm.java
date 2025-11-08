package com.google.android.gms.measurement.internal;

import java.util.concurrent.atomic.AtomicReference;

final class zzkm implements Runnable {
    private final /* synthetic */ AtomicReference zza;
    private final /* synthetic */ String zzb = null;
    private final /* synthetic */ String zzc;
    private final /* synthetic */ String zzd;
    private final /* synthetic */ boolean zze;
    private final /* synthetic */ zzjk zzf;

    public zzkm(zzjk zzjk, AtomicReference atomicReference, String str, String str2, String str3, boolean z2) {
        this.zza = atomicReference;
        this.zzc = str2;
        this.zzd = str3;
        this.zze = z2;
        this.zzf = zzjk;
    }

    public final void run() {
        this.zzf.zzu.zzr().zza(this.zza, (String) null, this.zzc, this.zzd, this.zze);
    }
}
