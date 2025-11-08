package com.google.android.gms.measurement.internal;

import android.os.Bundle;

final class zzkl implements Runnable {
    private final /* synthetic */ Bundle zza;
    private final /* synthetic */ zzjk zzb;

    public zzkl(zzjk zzjk, Bundle bundle) {
        this.zza = bundle;
        this.zzb = zzjk;
    }

    public final void run() {
        zzjk.zzb(this.zzb, this.zza);
    }
}
