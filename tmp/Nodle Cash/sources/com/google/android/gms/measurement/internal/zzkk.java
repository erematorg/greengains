package com.google.android.gms.measurement.internal;

import android.os.Bundle;

final class zzkk implements Runnable {
    private final /* synthetic */ Bundle zza;
    private final /* synthetic */ zzjk zzb;

    public zzkk(zzjk zzjk, Bundle bundle) {
        this.zza = bundle;
        this.zzb = zzjk;
    }

    public final void run() {
        zzjk.zza(this.zzb, this.zza);
    }
}
