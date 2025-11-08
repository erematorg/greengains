package com.google.android.gms.internal.measurement;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zzdv;

final class zzew extends zzdv.zzb {
    private final /* synthetic */ String zzc;
    private final /* synthetic */ zzdi zzd;
    private final /* synthetic */ zzdv zze;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public zzew(zzdv zzdv, String str, zzdi zzdi) {
        super(zzdv);
        this.zzc = str;
        this.zzd = zzdi;
        this.zze = zzdv;
    }

    public final void zza() throws RemoteException {
        ((zzdk) Preconditions.checkNotNull(this.zze.zzj)).getMaxUserProperties(this.zzc, this.zzd);
    }

    public final void zzb() {
        this.zzd.zza((Bundle) null);
    }
}
