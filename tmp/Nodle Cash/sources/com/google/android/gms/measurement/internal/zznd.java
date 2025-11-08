package com.google.android.gms.measurement.internal;

final class zznd implements Runnable {
    private final /* synthetic */ long zza;
    private final /* synthetic */ zznb zzb;

    public zznd(zznb zznb, long j2) {
        this.zza = j2;
        this.zzb = zznb;
    }

    public final void run() {
        zznb.zza(this.zzb, this.zza);
    }
}
