package com.google.android.gms.internal.measurement;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zzdv;

final class zzeg extends zzdv.zzb {
    private final /* synthetic */ Bundle zzc;
    private final /* synthetic */ zzdv zzd;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public zzeg(zzdv zzdv, Bundle bundle) {
        super(zzdv);
        this.zzc = bundle;
        this.zzd = zzdv;
    }

    public final void zza() throws RemoteException {
        ((zzdk) Preconditions.checkNotNull(this.zzd.zzj)).setConsent(this.zzc, this.zza);
    }
}
