package com.google.android.gms.measurement.internal;

import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;

final class zzmg implements Runnable {
    private final /* synthetic */ zzp zza;
    private final /* synthetic */ zzlp zzb;

    public zzmg(zzlp zzlp, zzp zzp) {
        this.zza = zzp;
        this.zzb = zzlp;
    }

    public final void run() {
        zzgb zza2 = this.zzb.zzb;
        if (zza2 == null) {
            this.zzb.zzj().zzg().zza("Failed to send consent settings to service");
            return;
        }
        try {
            Preconditions.checkNotNull(this.zza);
            zza2.zzf(this.zza);
            this.zzb.zzar();
        } catch (RemoteException e3) {
            this.zzb.zzj().zzg().zza("Failed to send consent settings to the service", e3);
        }
    }
}
