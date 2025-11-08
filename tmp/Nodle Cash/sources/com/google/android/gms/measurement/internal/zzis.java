package com.google.android.gms.measurement.internal;

final class zzis implements Runnable {
    private final /* synthetic */ zzbh zza;
    private final /* synthetic */ zzp zzb;
    private final /* synthetic */ zzia zzc;

    public zzis(zzia zzia, zzbh zzbh, zzp zzp) {
        this.zza = zzbh;
        this.zzb = zzp;
        this.zzc = zzia;
    }

    public final void run() {
        this.zzc.zzc(this.zzc.zzb(this.zza, this.zzb), this.zzb);
    }
}
