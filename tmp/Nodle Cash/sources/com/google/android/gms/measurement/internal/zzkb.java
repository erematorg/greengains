package com.google.android.gms.measurement.internal;

final class zzkb implements Runnable {
    private final /* synthetic */ boolean zza;
    private final /* synthetic */ zzjk zzb;

    public zzkb(zzjk zzjk, boolean z2) {
        this.zza = z2;
        this.zzb = zzjk;
    }

    public final void run() {
        boolean zzac = this.zzb.zzu.zzac();
        boolean zzab = this.zzb.zzu.zzab();
        this.zzb.zzu.zza(this.zza);
        if (zzab == this.zza) {
            this.zzb.zzu.zzj().zzp().zza("Default data collection state already set to", Boolean.valueOf(this.zza));
        }
        if (this.zzb.zzu.zzac() == zzac || this.zzb.zzu.zzac() != this.zzb.zzu.zzab()) {
            this.zzb.zzu.zzj().zzv().zza("Default data collection is different than actual status", Boolean.valueOf(this.zza), Boolean.valueOf(zzac));
        }
        this.zzb.zzav();
    }
}
