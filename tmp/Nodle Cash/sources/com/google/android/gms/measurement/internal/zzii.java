package com.google.android.gms.measurement.internal;

final class zzii implements Runnable {
    private final /* synthetic */ String zza;
    private final /* synthetic */ String zzb;
    private final /* synthetic */ String zzc;
    private final /* synthetic */ long zzd;
    private final /* synthetic */ zzia zze;

    public zzii(zzia zzia, String str, String str2, String str3, long j2) {
        this.zza = str;
        this.zzb = str2;
        this.zzc = str3;
        this.zzd = j2;
        this.zze = zzia;
    }

    public final void run() {
        String str = this.zza;
        if (str == null) {
            this.zze.zza.zza(this.zzb, (zzlh) null);
            return;
        }
        this.zze.zza.zza(this.zzb, new zzlh(this.zzc, str, this.zzd));
    }
}
