package com.google.android.gms.measurement.internal;

import com.google.android.gms.internal.measurement.zzpd;

final class zzku implements Runnable {
    private final /* synthetic */ zzjc zza;
    private final /* synthetic */ long zzb;
    private final /* synthetic */ long zzc;
    private final /* synthetic */ boolean zzd;
    private final /* synthetic */ zzjc zze;
    private final /* synthetic */ zzjk zzf;

    public zzku(zzjk zzjk, zzjc zzjc, long j2, long j3, boolean z2, zzjc zzjc2) {
        this.zza = zzjc;
        this.zzb = j2;
        this.zzc = j3;
        this.zzd = z2;
        this.zze = zzjc2;
        this.zzf = zzjk;
    }

    public final void run() {
        this.zzf.zza(this.zza);
        if (!zzpd.zza() || !this.zzf.zze().zza(zzbj.zzdb)) {
            this.zzf.zza(this.zzb, false);
        }
        zzjk.zza(this.zzf, this.zza, this.zzc, true, this.zzd);
        zzjk.zza(this.zzf, this.zza, this.zze);
    }
}
