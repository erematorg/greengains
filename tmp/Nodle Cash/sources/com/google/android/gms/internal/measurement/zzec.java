package com.google.android.gms.internal.measurement;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zzdv;

final class zzec extends zzdv.zzb {
    private final /* synthetic */ String zzc;
    private final /* synthetic */ String zzd;
    private final /* synthetic */ zzdi zze;
    private final /* synthetic */ zzdv zzf;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public zzec(zzdv zzdv, String str, String str2, zzdi zzdi) {
        super(zzdv);
        this.zzc = str;
        this.zzd = str2;
        this.zze = zzdi;
        this.zzf = zzdv;
    }

    public final void zza() throws RemoteException {
        ((zzdk) Preconditions.checkNotNull(this.zzf.zzj)).getConditionalUserProperties(this.zzc, this.zzd, this.zze);
    }

    public final void zzb() {
        this.zze.zza((Bundle) null);
    }
}
