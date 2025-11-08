package com.google.android.gms.measurement.internal;

final class zzln implements Runnable {
    private final /* synthetic */ zzlh zza;
    private final /* synthetic */ long zzb;
    private final /* synthetic */ zzlg zzc;

    public zzln(zzlg zzlg, zzlh zzlh, long j2) {
        this.zza = zzlh;
        this.zzb = j2;
        this.zzc = zzlg;
    }

    public final void run() {
        this.zzc.zza(this.zza, false, this.zzb);
        zzlg zzlg = this.zzc;
        zzlg.zza = null;
        zzlg.zzo().zza((zzlh) null);
    }
}
