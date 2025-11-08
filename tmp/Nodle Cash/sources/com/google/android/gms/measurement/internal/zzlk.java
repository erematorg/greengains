package com.google.android.gms.measurement.internal;

final class zzlk implements Runnable {
    private final /* synthetic */ long zza;
    private final /* synthetic */ zzlg zzb;

    public zzlk(zzlg zzlg, long j2) {
        this.zza = j2;
        this.zzb = zzlg;
    }

    public final void run() {
        this.zzb.zzc().zza(this.zza);
        this.zzb.zza = null;
    }
}
