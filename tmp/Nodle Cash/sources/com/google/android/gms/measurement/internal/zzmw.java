package com.google.android.gms.measurement.internal;

import android.content.Intent;

public final /* synthetic */ class zzmw implements Runnable {
    private /* synthetic */ zzmu zza;
    private /* synthetic */ int zzb;
    private /* synthetic */ zzgi zzc;
    private /* synthetic */ Intent zzd;

    public /* synthetic */ zzmw(zzmu zzmu, int i3, zzgi zzgi, Intent intent) {
        this.zza = zzmu;
        this.zzb = i3;
        this.zzc = zzgi;
        this.zzd = intent;
    }

    public final void run() {
        this.zza.zza(this.zzb, this.zzc, this.zzd);
    }
}
