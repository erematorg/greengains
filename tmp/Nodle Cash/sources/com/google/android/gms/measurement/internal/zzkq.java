package com.google.android.gms.measurement.internal;

import java.util.concurrent.atomic.AtomicReference;

final class zzkq implements Runnable {
    private final /* synthetic */ AtomicReference zza;
    private final /* synthetic */ zzjk zzb;

    public zzkq(zzjk zzjk, AtomicReference atomicReference) {
        this.zza = atomicReference;
        this.zzb = zzjk;
    }

    public final void run() {
        synchronized (this.zza) {
            try {
                this.zza.set(Integer.valueOf(this.zzb.zze().zzb(this.zzb.zzg().zzad(), zzbj.zzap)));
                this.zza.notify();
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
