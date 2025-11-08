package com.google.android.gms.measurement.internal;

import android.os.Bundle;

final class zzlj implements Runnable {
    private final /* synthetic */ Bundle zza;
    private final /* synthetic */ zzlh zzb;
    private final /* synthetic */ zzlh zzc;
    private final /* synthetic */ long zzd;
    private final /* synthetic */ zzlg zze;

    public zzlj(zzlg zzlg, Bundle bundle, zzlh zzlh, zzlh zzlh2, long j2) {
        this.zza = bundle;
        this.zzb = zzlh;
        this.zzc = zzlh2;
        this.zzd = j2;
        this.zze = zzlg;
    }

    public final void run() {
        zzlg.zza(this.zze, this.zza, this.zzb, this.zzc, this.zzd);
    }
}
