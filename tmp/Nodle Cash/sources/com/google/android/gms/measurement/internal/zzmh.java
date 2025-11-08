package com.google.android.gms.measurement.internal;

import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;

final class zzmh implements Runnable {
    private final /* synthetic */ zzp zza;
    private final /* synthetic */ zzlp zzb;

    public zzmh(zzlp zzlp, zzp zzp) {
        this.zza = zzp;
        this.zzb = zzlp;
    }

    public final void run() {
        zzgb zza2 = this.zzb.zzb;
        if (zza2 == null) {
            this.zzb.zzj().zzg().zza("Failed to send measurementEnabled to service");
            return;
        }
        try {
            Preconditions.checkNotNull(this.zza);
            zza2.zzh(this.zza);
            this.zzb.zzar();
        } catch (RemoteException e3) {
            this.zzb.zzj().zzg().zza("Failed to send measurementEnabled to the service", e3);
        }
    }
}
