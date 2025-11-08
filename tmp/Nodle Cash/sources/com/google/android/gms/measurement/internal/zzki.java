package com.google.android.gms.measurement.internal;

import java.util.concurrent.atomic.AtomicReference;

final class zzki implements Runnable {
    private final /* synthetic */ long zza;
    private final /* synthetic */ zzjk zzb;

    public zzki(zzjk zzjk, long j2) {
        this.zza = j2;
        this.zzb = zzjk;
    }

    public final void run() {
        this.zzb.zzb(this.zza);
        this.zzb.zzo().zza((AtomicReference<String>) new AtomicReference());
    }
}
