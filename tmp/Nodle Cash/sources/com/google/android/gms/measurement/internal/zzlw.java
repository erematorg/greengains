package com.google.android.gms.measurement.internal;

import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
import java.util.concurrent.atomic.AtomicReference;

final class zzlw implements Runnable {
    private final /* synthetic */ AtomicReference zza;
    private final /* synthetic */ zzp zzb;
    private final /* synthetic */ zzlp zzc;

    public zzlw(zzlp zzlp, AtomicReference atomicReference, zzp zzp) {
        this.zza = atomicReference;
        this.zzb = zzp;
        this.zzc = zzlp;
    }

    public final void run() {
        synchronized (this.zza) {
            try {
                if (!this.zzc.zzk().zzn().zzj()) {
                    this.zzc.zzj().zzv().zza("Analytics storage consent denied; will not get app instance id");
                    this.zzc.zzm().zzc((String) null);
                    this.zzc.zzk().zze.zza((String) null);
                    this.zza.set((Object) null);
                    this.zza.notify();
                    return;
                }
                zzgb zza2 = this.zzc.zzb;
                if (zza2 == null) {
                    this.zzc.zzj().zzg().zza("Failed to get app instance id");
                    this.zza.notify();
                    return;
                }
                Preconditions.checkNotNull(this.zzb);
                this.zza.set(zza2.zzb(this.zzb));
                String str = (String) this.zza.get();
                if (str != null) {
                    this.zzc.zzm().zzc(str);
                    this.zzc.zzk().zze.zza(str);
                }
                this.zzc.zzar();
                this.zza.notify();
            } catch (RemoteException e3) {
                try {
                    this.zzc.zzj().zzg().zza("Failed to get app instance id", e3);
                    this.zza.notify();
                } catch (Throwable th) {
                    this.zza.notify();
                    throw th;
                }
            }
        }
    }
}
