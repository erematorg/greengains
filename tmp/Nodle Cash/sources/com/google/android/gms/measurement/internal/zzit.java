package com.google.android.gms.measurement.internal;

final class zzit implements Runnable {
    private final /* synthetic */ zzok zza;
    private final /* synthetic */ zzp zzb;
    private final /* synthetic */ zzia zzc;

    public zzit(zzia zzia, zzok zzok, zzp zzp) {
        this.zza = zzok;
        this.zzb = zzp;
        this.zzc = zzia;
    }

    public final void run() {
        this.zzc.zza.zzr();
        if (this.zza.zza() == null) {
            this.zzc.zza.zza(this.zza.zza, this.zzb);
        } else {
            this.zzc.zza.zza(this.zza, this.zzb);
        }
    }
}
