package com.google.android.gms.measurement.internal;

import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;

final class zzlx implements Runnable {
    private final /* synthetic */ zzp zza;
    private final /* synthetic */ zzlp zzb;

    public zzlx(zzlp zzlp, zzp zzp) {
        this.zza = zzp;
        this.zzb = zzlp;
    }

    public final void run() {
        zzgb zza2 = this.zzb.zzb;
        if (zza2 == null) {
            this.zzb.zzj().zzg().zza("Failed to reset data on the service: not connected to service");
            return;
        }
        try {
            Preconditions.checkNotNull(this.zza);
            zza2.zze(this.zza);
        } catch (RemoteException e3) {
            this.zzb.zzj().zzg().zza("Failed to reset data on the service: remote exception", e3);
        }
        this.zzb.zzar();
    }
}
