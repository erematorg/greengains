package com.google.android.gms.measurement.internal;

import android.content.ComponentName;

final class zzmo implements Runnable {
    private final /* synthetic */ ComponentName zza;
    private final /* synthetic */ zzmm zzb;

    public zzmo(zzmm zzmm, ComponentName componentName) {
        this.zza = componentName;
        this.zzb = zzmm;
    }

    public final void run() {
        zzlp.zza(this.zzb.zza, this.zza);
    }
}
