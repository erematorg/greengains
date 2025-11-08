package com.google.android.gms.measurement.internal;

final class zzke implements Runnable {
    private final /* synthetic */ String zza;
    private final /* synthetic */ String zzb;
    private final /* synthetic */ Object zzc;
    private final /* synthetic */ long zzd;
    private final /* synthetic */ zzjk zze;

    public zzke(zzjk zzjk, String str, String str2, Object obj, long j2) {
        this.zza = str;
        this.zzb = str2;
        this.zzc = obj;
        this.zzd = j2;
        this.zze = zzjk;
    }

    public final void run() {
        this.zze.zza(this.zza, this.zzb, this.zzc, this.zzd);
    }
}
