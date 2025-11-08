package com.google.android.gms.measurement.internal;

final class zzih implements Runnable {
    private final /* synthetic */ zzaf zza;
    private final /* synthetic */ zzp zzb;
    private final /* synthetic */ zzia zzc;

    public zzih(zzia zzia, zzaf zzaf, zzp zzp) {
        this.zza = zzaf;
        this.zzb = zzp;
        this.zzc = zzia;
    }

    public final void run() {
        this.zzc.zza.zzr();
        if (this.zza.zzc.zza() == null) {
            this.zzc.zza.zza(this.zza, this.zzb);
        } else {
            this.zzc.zza.zzb(this.zza, this.zzb);
        }
    }
}
