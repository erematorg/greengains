package com.google.android.gms.measurement.internal;

final class zzkv implements Runnable {
    private final /* synthetic */ zzaz zza;
    private final /* synthetic */ zzjk zzb;

    public zzkv(zzjk zzjk, zzaz zzaz) {
        this.zza = zzaz;
        this.zzb = zzjk;
    }

    public final void run() {
        if (this.zzb.zzk().zza(this.zza)) {
            this.zzb.zzj().zzp().zza("Setting DMA consent(FE)", this.zza);
            if (this.zzb.zzo().zzao()) {
                this.zzb.zzo().zzaj();
            } else {
                this.zzb.zzo().zza(false);
            }
        } else {
            this.zzb.zzj().zzn().zza("Lower precedence consent source ignored, proposed source", Integer.valueOf(this.zza.zza()));
        }
    }
}
