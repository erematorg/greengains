package com.google.android.gms.measurement.internal;

final class zznf implements Runnable {
    long zza;
    long zzb;
    final /* synthetic */ zznc zzc;

    public zznf(zznc zznc, long j2, long j3) {
        this.zzc = zznc;
        this.zza = j2;
        this.zzb = j3;
    }

    public final void run() {
        this.zzc.zza.zzl().zzb((Runnable) new zzne(this));
    }
}
