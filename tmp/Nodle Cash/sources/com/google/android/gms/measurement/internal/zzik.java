package com.google.android.gms.measurement.internal;

final class zzik implements Runnable {
    private final /* synthetic */ zzaf zza;
    private final /* synthetic */ zzia zzb;

    public zzik(zzia zzia, zzaf zzaf) {
        this.zza = zzaf;
        this.zzb = zzia;
    }

    public final void run() {
        this.zzb.zza.zzr();
        if (this.zza.zzc.zza() == null) {
            this.zzb.zza.zza(this.zza);
        } else {
            this.zzb.zza.zzb(this.zza);
        }
    }
}
