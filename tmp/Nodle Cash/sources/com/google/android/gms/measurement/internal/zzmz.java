package com.google.android.gms.measurement.internal;

final class zzmz implements Runnable {
    private final /* synthetic */ zznv zza;
    private final /* synthetic */ Runnable zzb;

    public zzmz(zzmu zzmu, zznv zznv, Runnable runnable) {
        this.zza = zznv;
        this.zzb = runnable;
    }

    public final void run() {
        this.zza.zzr();
        this.zza.zza(this.zzb);
        this.zza.zzw();
    }
}
