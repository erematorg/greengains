package com.google.android.gms.measurement.internal;

final class zzhx implements Runnable {
    private final /* synthetic */ zzji zza;
    private final /* synthetic */ zzhw zzb;

    public zzhx(zzhw zzhw, zzji zzji) {
        this.zza = zzji;
        this.zzb = zzhw;
    }

    public final void run() {
        zzhw.zza(this.zzb, this.zza);
        this.zzb.zza(this.zza.zzg);
    }
}
