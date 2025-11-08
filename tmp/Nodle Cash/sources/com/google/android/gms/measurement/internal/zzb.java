package com.google.android.gms.measurement.internal;

final class zzb implements Runnable {
    private final /* synthetic */ String zza;
    private final /* synthetic */ long zzb;
    private final /* synthetic */ zza zzc;

    public zzb(zza zza2, String str, long j2) {
        this.zza = str;
        this.zzb = j2;
        this.zzc = zza2;
    }

    public final void run() {
        zza.zzb(this.zzc, this.zza, this.zzb);
    }
}
