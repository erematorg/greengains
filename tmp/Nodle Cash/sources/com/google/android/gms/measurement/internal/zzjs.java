package com.google.android.gms.measurement.internal;

import android.os.Bundle;

public final /* synthetic */ class zzjs implements Runnable {
    private /* synthetic */ zzjk zza;
    private /* synthetic */ Bundle zzb;

    public /* synthetic */ zzjs(zzjk zzjk, Bundle bundle) {
        this.zza = zzjk;
        this.zzb = bundle;
    }

    public final void run() {
        this.zza.zzb(this.zzb);
    }
}
