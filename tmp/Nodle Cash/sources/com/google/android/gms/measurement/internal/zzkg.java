package com.google.android.gms.measurement.internal;

import java.util.concurrent.atomic.AtomicReference;

final class zzkg implements Runnable {
    private final /* synthetic */ AtomicReference zza;
    private final /* synthetic */ zzjk zzb;

    public zzkg(zzjk zzjk, AtomicReference atomicReference) {
        this.zza = atomicReference;
        this.zzb = zzjk;
    }

    public final void run() {
        synchronized (this.zza) {
            try {
                this.zza.set(this.zzb.zze().zzf(this.zzb.zzg().zzad()));
                this.zza.notify();
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
