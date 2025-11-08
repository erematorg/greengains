package com.google.android.gms.measurement.internal;

import java.util.concurrent.atomic.AtomicReference;

final class zzkr implements Runnable {
    private final /* synthetic */ AtomicReference zza;
    private final /* synthetic */ zzjk zzb;

    public zzkr(zzjk zzjk, AtomicReference atomicReference) {
        this.zza = atomicReference;
        this.zzb = zzjk;
    }

    public final void run() {
        synchronized (this.zza) {
            try {
                this.zza.set(Long.valueOf(this.zzb.zze().zzc(this.zzb.zzg().zzad(), zzbj.zzao)));
                this.zza.notify();
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
