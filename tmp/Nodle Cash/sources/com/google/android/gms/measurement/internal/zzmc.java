package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;

final class zzmc implements Runnable {
    private final /* synthetic */ zzp zza;
    private final /* synthetic */ Bundle zzb;
    private final /* synthetic */ zzlp zzc;

    public zzmc(zzlp zzlp, zzp zzp, Bundle bundle) {
        this.zza = zzp;
        this.zzb = bundle;
        this.zzc = zzlp;
    }

    public final void run() {
        zzgb zza2 = this.zzc.zzb;
        if (zza2 == null) {
            this.zzc.zzj().zzg().zza("Failed to send default event parameters to service");
            return;
        }
        try {
            Preconditions.checkNotNull(this.zza);
            zza2.zza(this.zzb, this.zza);
        } catch (RemoteException e3) {
            this.zzc.zzj().zzg().zza("Failed to send default event parameters to service", e3);
        }
    }
}
