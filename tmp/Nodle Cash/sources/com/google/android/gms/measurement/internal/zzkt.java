package com.google.android.gms.measurement.internal;

import java.util.concurrent.atomic.AtomicReference;

final class zzkt implements Runnable {
    private final /* synthetic */ AtomicReference zza;
    private final /* synthetic */ zzjk zzb;

    public zzkt(zzjk zzjk, AtomicReference atomicReference) {
        this.zza = atomicReference;
        this.zzb = zzjk;
    }

    public final void run() {
        synchronized (this.zza) {
            try {
                this.zza.set(Double.valueOf(this.zzb.zze().zza(this.zzb.zzg().zzad(), zzbj.zzaq)));
                this.zza.notify();
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
