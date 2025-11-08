package com.google.android.gms.measurement.internal;

final class zzna implements Runnable {
    private final /* synthetic */ long zza;
    private final /* synthetic */ zznb zzb;

    public zzna(zznb zznb, long j2) {
        this.zza = j2;
        this.zzb = zznb;
    }

    public final void run() {
        zznb.zzb(this.zzb, this.zza);
    }
}
