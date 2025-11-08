package com.google.android.gms.internal.measurement;

import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zzdv;

final class zzeu extends zzdv.zzb {
    private final /* synthetic */ zzdv.zza zzc;
    private final /* synthetic */ zzdv zzd;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public zzeu(zzdv zzdv, zzdv.zza zza) {
        super(zzdv);
        this.zzc = zza;
        this.zzd = zzdv;
    }

    public final void zza() throws RemoteException {
        ((zzdk) Preconditions.checkNotNull(this.zzd.zzj)).setEventInterceptor(this.zzc);
    }
}
