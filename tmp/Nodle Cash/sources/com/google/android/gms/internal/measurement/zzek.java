package com.google.android.gms.internal.measurement;

import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zzdv;

final class zzek extends zzdv.zzb {
    private final /* synthetic */ long zzc;
    private final /* synthetic */ zzdv zzd;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public zzek(zzdv zzdv, long j2) {
        super(zzdv);
        this.zzc = j2;
        this.zzd = zzdv;
    }

    public final void zza() throws RemoteException {
        ((zzdk) Preconditions.checkNotNull(this.zzd.zzj)).setSessionTimeoutDuration(this.zzc);
    }
}
