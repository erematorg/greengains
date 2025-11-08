package com.google.android.gms.measurement.internal;

import android.os.Bundle;

public final /* synthetic */ class zzjq implements Runnable {
    private /* synthetic */ zzjk zza;
    private /* synthetic */ Bundle zzb;
    private /* synthetic */ long zzc;

    public /* synthetic */ zzjq(zzjk zzjk, Bundle bundle, long j2) {
        this.zza = zzjk;
        this.zzb = bundle;
        this.zzc = j2;
    }

    public final void run() {
        this.zza.zza(this.zzb, this.zzc);
    }
}
