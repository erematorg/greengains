package com.google.android.gms.measurement.internal;

final class zzig implements Runnable {
    private final /* synthetic */ zzp zza;
    private final /* synthetic */ zzia zzb;

    public zzig(zzia zzia, zzp zzp) {
        this.zza = zzp;
        this.zzb = zzia;
    }

    public final void run() {
        this.zzb.zza.zzr();
        this.zzb.zza.zzd(this.zza);
    }
}
