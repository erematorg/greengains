package com.google.android.gms.measurement.internal;

import android.os.RemoteException;
import com.google.android.gms.internal.measurement.zzdl;

final class zzme implements Runnable {
    private final /* synthetic */ zzbh zza;
    private final /* synthetic */ String zzb;
    private final /* synthetic */ zzdl zzc;
    private final /* synthetic */ zzlp zzd;

    public zzme(zzlp zzlp, zzbh zzbh, String str, zzdl zzdl) {
        this.zza = zzbh;
        this.zzb = str;
        this.zzc = zzdl;
        this.zzd = zzlp;
    }

    public final void run() {
        byte[] bArr = null;
        try {
            zzgb zza2 = this.zzd.zzb;
            if (zza2 == null) {
                this.zzd.zzj().zzg().zza("Discarding data. Failed to send event to service to bundle");
                return;
            }
            bArr = zza2.zza(this.zza, this.zzb);
            this.zzd.zzar();
            this.zzd.zzq().zza(this.zzc, bArr);
        } catch (RemoteException e3) {
            this.zzd.zzj().zzg().zza("Failed to send event to the service to bundle", e3);
        } finally {
            this.zzd.zzq().zza(this.zzc, bArr);
        }
    }
}
