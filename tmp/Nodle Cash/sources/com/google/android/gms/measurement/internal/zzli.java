package com.google.android.gms.measurement.internal;

import android.os.Bundle;

final class zzli implements Runnable {
    private final /* synthetic */ zzlh zza;
    private final /* synthetic */ zzlh zzb;
    private final /* synthetic */ long zzc;
    private final /* synthetic */ boolean zzd;
    private final /* synthetic */ zzlg zze;

    public zzli(zzlg zzlg, zzlh zzlh, zzlh zzlh2, long j2, boolean z2) {
        this.zza = zzlh;
        this.zzb = zzlh2;
        this.zzc = j2;
        this.zzd = z2;
        this.zze = zzlg;
    }

    public final void run() {
        this.zze.zza(this.zza, this.zzb, this.zzc, this.zzd, (Bundle) null);
    }
}
