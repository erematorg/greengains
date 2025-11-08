package com.google.android.gms.measurement.internal;

final class zzir implements Runnable {
    private final /* synthetic */ zzbh zza;
    private final /* synthetic */ String zzb;
    private final /* synthetic */ zzia zzc;

    public zzir(zzia zzia, zzbh zzbh, String str) {
        this.zza = zzbh;
        this.zzb = str;
        this.zzc = zzia;
    }

    public final void run() {
        this.zzc.zza.zzr();
        this.zzc.zza.zza(this.zza, this.zzb);
    }
}
