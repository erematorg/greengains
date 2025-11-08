package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zzdl;

final class zzlt implements Runnable {
    private final /* synthetic */ String zza;
    private final /* synthetic */ String zzb;
    private final /* synthetic */ zzp zzc;
    private final /* synthetic */ boolean zzd;
    private final /* synthetic */ zzdl zze;
    private final /* synthetic */ zzlp zzf;

    public zzlt(zzlp zzlp, String str, String str2, zzp zzp, boolean z2, zzdl zzdl) {
        this.zza = str;
        this.zzb = str2;
        this.zzc = zzp;
        this.zzd = z2;
        this.zze = zzdl;
        this.zzf = zzlp;
    }

    public final void run() {
        Bundle bundle = new Bundle();
        try {
            zzgb zza2 = this.zzf.zzb;
            if (zza2 == null) {
                this.zzf.zzj().zzg().zza("Failed to get user properties; not connected to service", this.zza, this.zzb);
                return;
            }
            Preconditions.checkNotNull(this.zzc);
            bundle = zzop.zza(zza2.zza(this.zza, this.zzb, this.zzd, this.zzc));
            this.zzf.zzar();
            this.zzf.zzq().zza(this.zze, bundle);
        } catch (RemoteException e3) {
            this.zzf.zzj().zzg().zza("Failed to get user properties; remote exception", this.zza, e3);
        } finally {
            this.zzf.zzq().zza(this.zze, bundle);
        }
    }
}
