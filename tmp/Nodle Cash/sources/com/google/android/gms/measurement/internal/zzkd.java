package com.google.android.gms.measurement.internal;

final class zzkd implements Runnable {
    private final /* synthetic */ long zza;
    private final /* synthetic */ zzjk zzb;

    public zzkd(zzjk zzjk, long j2) {
        this.zza = j2;
        this.zzb = zzjk;
    }

    public final void run() {
        this.zzb.zzk().zzf.zza(this.zza);
        this.zzb.zzj().zzc().zza("Session timeout duration set", Long.valueOf(this.zza));
    }
}
