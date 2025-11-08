package com.google.android.gms.measurement.internal;

final class zzaw implements Runnable {
    private final /* synthetic */ zzja zza;
    private final /* synthetic */ zzax zzb;

    public zzaw(zzax zzax, zzja zzja) {
        this.zza = zzja;
        this.zzb = zzax;
    }

    public final void run() {
        this.zza.zzd();
        if (zzac.zza()) {
            this.zza.zzl().zzb((Runnable) this);
            return;
        }
        boolean zzc = this.zzb.zzc();
        this.zzb.zzd = 0;
        if (zzc) {
            this.zzb.zzb();
        }
    }
}
