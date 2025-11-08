package com.google.android.gms.measurement.internal;

import java.util.List;

public final /* synthetic */ class zzjo implements Runnable {
    private /* synthetic */ zzjk zza;
    private /* synthetic */ List zzb;

    public /* synthetic */ zzjo(zzjk zzjk, List list) {
        this.zza = zzjk;
        this.zzb = list;
    }

    public final void run() {
        this.zza.zza(this.zzb);
    }
}
