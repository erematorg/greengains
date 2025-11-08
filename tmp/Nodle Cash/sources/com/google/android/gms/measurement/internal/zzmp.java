package com.google.android.gms.measurement.internal;

final class zzmp implements Runnable {
    private final /* synthetic */ zzgb zza;
    private final /* synthetic */ zzmm zzb;

    public zzmp(zzmm zzmm, zzgb zzgb) {
        this.zza = zzgb;
        this.zzb = zzmm;
    }

    public final void run() {
        synchronized (this.zzb) {
            try {
                this.zzb.zzb = false;
                if (!this.zzb.zza.zzal()) {
                    this.zzb.zza.zzj().zzp().zza("Connected to service");
                    this.zzb.zza.zza(this.zza);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
