package com.google.android.gms.measurement.internal;

import android.os.Bundle;

public final /* synthetic */ class zzjr implements Runnable {
    private /* synthetic */ zzjk zza;
    private /* synthetic */ Bundle zzb;

    public /* synthetic */ zzjr(zzjk zzjk, Bundle bundle) {
        this.zza = zzjk;
        this.zzb = bundle;
    }

    public final void run() {
        this.zza.zza(this.zzb);
    }
}
