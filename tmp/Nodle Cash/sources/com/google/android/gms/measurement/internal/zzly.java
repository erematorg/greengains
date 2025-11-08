package com.google.android.gms.measurement.internal;

import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

final class zzly implements Runnable {
    private final /* synthetic */ zzp zza;
    private final /* synthetic */ zzlp zzb;

    public zzly(zzlp zzlp, zzp zzp) {
        this.zza = zzp;
        this.zzb = zzlp;
    }

    public final void run() {
        zzgb zza2 = this.zzb.zzb;
        if (zza2 == null) {
            this.zzb.zzj().zzg().zza("Discarding data. Failed to send app launch");
            return;
        }
        try {
            Preconditions.checkNotNull(this.zza);
            zza2.zzd(this.zza);
            this.zzb.zzh().zzac();
            this.zzb.zza(zza2, (AbstractSafeParcelable) null, this.zza);
            this.zzb.zzar();
        } catch (RemoteException e3) {
            this.zzb.zzj().zzg().zza("Failed to send app launch to the service", e3);
        }
    }
}
