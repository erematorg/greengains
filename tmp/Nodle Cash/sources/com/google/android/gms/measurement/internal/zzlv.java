package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
import java.util.concurrent.atomic.AtomicReference;

final class zzlv implements Runnable {
    private final /* synthetic */ AtomicReference zza;
    private final /* synthetic */ zzp zzb;
    private final /* synthetic */ Bundle zzc;
    private final /* synthetic */ zzlp zzd;

    public zzlv(zzlp zzlp, AtomicReference atomicReference, zzp zzp, Bundle bundle) {
        this.zza = atomicReference;
        this.zzb = zzp;
        this.zzc = bundle;
        this.zzd = zzlp;
    }

    public final void run() {
        synchronized (this.zza) {
            try {
                zzgb zza2 = this.zzd.zzb;
                if (zza2 == null) {
                    this.zzd.zzj().zzg().zza("Failed to get trigger URIs; not connected to service");
                    this.zza.notify();
                    return;
                }
                Preconditions.checkNotNull(this.zzb);
                this.zza.set(zza2.zza(this.zzb, this.zzc));
                this.zzd.zzar();
                this.zza.notify();
            } catch (RemoteException e3) {
                try {
                    this.zzd.zzj().zzg().zza("Failed to get trigger URIs; remote exception", e3);
                    this.zza.notify();
                } catch (Throwable th) {
                    this.zza.notify();
                    throw th;
                }
            }
        }
    }
}
