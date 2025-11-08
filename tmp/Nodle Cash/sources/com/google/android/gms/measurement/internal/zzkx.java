package com.google.android.gms.measurement.internal;

final class zzkx implements Runnable {
    private final /* synthetic */ zzjc zza;
    private final /* synthetic */ long zzb;
    private final /* synthetic */ boolean zzc;
    private final /* synthetic */ zzjc zzd;
    private final /* synthetic */ zzjk zze;

    public zzkx(zzjk zzjk, zzjc zzjc, long j2, boolean z2, zzjc zzjc2) {
        this.zza = zzjc;
        this.zzb = j2;
        this.zzc = z2;
        this.zzd = zzjc2;
        this.zze = zzjk;
    }

    public final void run() {
        this.zze.zza(this.zza);
        zzjk.zza(this.zze, this.zza, this.zzb, false, this.zzc);
        zzjk.zza(this.zze, this.zza, this.zzd);
    }
}
