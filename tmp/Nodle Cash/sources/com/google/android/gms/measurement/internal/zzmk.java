package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zzdl;
import java.util.ArrayList;

final class zzmk implements Runnable {
    private final /* synthetic */ String zza;
    private final /* synthetic */ String zzb;
    private final /* synthetic */ zzp zzc;
    private final /* synthetic */ zzdl zzd;
    private final /* synthetic */ zzlp zze;

    public zzmk(zzlp zzlp, String str, String str2, zzp zzp, zzdl zzdl) {
        this.zza = str;
        this.zzb = str2;
        this.zzc = zzp;
        this.zzd = zzdl;
        this.zze = zzlp;
    }

    public final void run() {
        ArrayList<Bundle> arrayList = new ArrayList<>();
        try {
            zzgb zza2 = this.zze.zzb;
            if (zza2 == null) {
                this.zze.zzj().zzg().zza("Failed to get conditional properties; not connected to service", this.zza, this.zzb);
                return;
            }
            Preconditions.checkNotNull(this.zzc);
            arrayList = zzop.zzb(zza2.zza(this.zza, this.zzb, this.zzc));
            this.zze.zzar();
            this.zze.zzq().zza(this.zzd, arrayList);
        } catch (RemoteException e3) {
            this.zze.zzj().zzg().zza("Failed to get conditional properties; remote exception", this.zza, this.zzb, e3);
        } finally {
            this.zze.zzq().zza(this.zzd, arrayList);
        }
    }
}
