package com.google.android.gms.measurement.internal;

import android.os.RemoteException;

final class zzma implements Runnable {
    private final /* synthetic */ zzlh zza;
    private final /* synthetic */ zzlp zzb;

    public zzma(zzlp zzlp, zzlh zzlh) {
        this.zza = zzlh;
        this.zzb = zzlp;
    }

    public final void run() {
        zzgb zza2 = this.zzb.zzb;
        if (zza2 == null) {
            this.zzb.zzj().zzg().zza("Failed to send current screen to service");
            return;
        }
        try {
            zzlh zzlh = this.zza;
            if (zzlh == null) {
                zza2.zza(0, (String) null, (String) null, this.zzb.zza().getPackageName());
            } else {
                zza2.zza(zzlh.zzc, zzlh.zza, zzlh.zzb, this.zzb.zza().getPackageName());
            }
            this.zzb.zzar();
        } catch (RemoteException e3) {
            this.zzb.zzj().zzg().zza("Failed to send current screen to the service", e3);
        }
    }
}
