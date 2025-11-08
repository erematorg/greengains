package com.google.android.gms.measurement.internal;

import java.util.concurrent.atomic.AtomicReference;

final class zzjx implements Runnable {
    private final /* synthetic */ AtomicReference zza;
    private final /* synthetic */ zzjk zzb;

    public zzjx(zzjk zzjk, AtomicReference atomicReference) {
        this.zza = atomicReference;
        this.zzb = zzjk;
    }

    public final void run() {
        synchronized (this.zza) {
            try {
                this.zza.set(Boolean.valueOf(this.zzb.zze().zzi(this.zzb.zzg().zzad())));
                this.zza.notify();
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
