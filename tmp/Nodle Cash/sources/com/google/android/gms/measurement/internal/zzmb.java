package com.google.android.gms.measurement.internal;

import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;

final class zzmb implements Runnable {
    private final /* synthetic */ zzp zza;
    private final /* synthetic */ zzlp zzb;

    public zzmb(zzlp zzlp, zzp zzp) {
        this.zza = zzp;
        this.zzb = zzlp;
    }

    public final void run() {
        zzgb zza2 = this.zzb.zzb;
        if (zza2 == null) {
            this.zzb.zzj().zzu().zza("Failed to send app backgrounded");
            return;
        }
        try {
            Preconditions.checkNotNull(this.zza);
            zza2.zzc(this.zza);
            this.zzb.zzar();
        } catch (RemoteException e3) {
            this.zzb.zzj().zzg().zza("Failed to send app backgrounded to the service", e3);
        }
    }
}
