package com.google.android.gms.measurement.internal;

import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zzdl;

final class zzlz implements Runnable {
    private final /* synthetic */ zzp zza;
    private final /* synthetic */ zzdl zzb;
    private final /* synthetic */ zzlp zzc;

    public zzlz(zzlp zzlp, zzp zzp, zzdl zzdl) {
        this.zza = zzp;
        this.zzb = zzdl;
        this.zzc = zzlp;
    }

    public final void run() {
        try {
            if (!this.zzc.zzk().zzn().zzj()) {
                this.zzc.zzj().zzv().zza("Analytics storage consent denied; will not get app instance id");
                this.zzc.zzm().zzc((String) null);
                this.zzc.zzk().zze.zza((String) null);
                return;
            }
            zzgb zza2 = this.zzc.zzb;
            if (zza2 == null) {
                this.zzc.zzj().zzg().zza("Failed to get app instance id");
                this.zzc.zzq().zza(this.zzb, (String) null);
                return;
            }
            Preconditions.checkNotNull(this.zza);
            String zzb2 = zza2.zzb(this.zza);
            if (zzb2 != null) {
                this.zzc.zzm().zzc(zzb2);
                this.zzc.zzk().zze.zza(zzb2);
            }
            this.zzc.zzar();
            this.zzc.zzq().zza(this.zzb, zzb2);
        } catch (RemoteException e3) {
            this.zzc.zzj().zzg().zza("Failed to get app instance id", e3);
        } finally {
            this.zzc.zzq().zza(this.zzb, (String) null);
        }
    }
}
