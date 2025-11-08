package com.google.android.gms.internal.measurement;

import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zzdv;

final class zzei extends zzdv.zzb {
    private final /* synthetic */ zzdv zzc;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public zzei(zzdv zzdv) {
        super(zzdv);
        this.zzc = zzdv;
    }

    public final void zza() throws RemoteException {
        ((zzdk) Preconditions.checkNotNull(this.zzc.zzj)).resetAnalyticsData(this.zza);
    }
}
