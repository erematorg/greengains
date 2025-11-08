package com.google.android.gms.measurement.internal;

public final /* synthetic */ class zzne implements Runnable {
    private /* synthetic */ zznf zza;

    public /* synthetic */ zzne(zznf zznf) {
        this.zza = zznf;
    }

    public final void run() {
        zznf zznf = this.zza;
        zznc zznc = zznf.zzc;
        long j2 = zznf.zza;
        long j3 = zznf.zzb;
        zznc.zza.zzt();
        zznc.zza.zzj().zzc().zza("Application going to the background");
        zznc.zza.zzk().zzn.zza(true);
        zznc.zza.zza(true);
        if (!zznc.zza.zze().zzy()) {
            if (zznc.zza.zze().zza(zzbj.zzco)) {
                zznc.zza.zza(false, false, j3);
                zznc.zza.zzb.zzb(j3);
            } else {
                zznc.zza.zzb.zzb(j3);
                zznc.zza.zza(false, false, j3);
            }
        }
        zznc.zza.zzj().zzn().zza("Application backgrounded at: timestamp_millis", Long.valueOf(j2));
        if (zznc.zza.zze().zza(zzbj.zzdh)) {
            zznc.zza.zzm().zzam();
        }
    }
}
