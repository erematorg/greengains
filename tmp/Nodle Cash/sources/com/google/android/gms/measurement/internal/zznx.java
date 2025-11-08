package com.google.android.gms.measurement.internal;

final class zznx implements Runnable {
    private final /* synthetic */ zzoh zza;
    private final /* synthetic */ zznv zzb;

    public zznx(zznv zznv, zzoh zzoh) {
        this.zza = zzoh;
        this.zzb = zznv;
    }

    public final void run() {
        zznv.zza(this.zzb, this.zza);
        this.zzb.zzv();
    }
}
