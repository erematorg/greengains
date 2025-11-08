package com.google.android.gms.internal.measurement;

import android.app.Activity;
import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.internal.measurement.zzdv;

final class zzfi extends zzdv.zzb {
    private final /* synthetic */ Activity zzc;
    private final /* synthetic */ zzdv.zzc zzd;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public zzfi(zzdv.zzc zzc2, Activity activity) {
        super(zzdv.this);
        this.zzc = activity;
        this.zzd = zzc2;
    }

    public final void zza() throws RemoteException {
        ((zzdk) Preconditions.checkNotNull(zzdv.this.zzj)).onActivityResumed(ObjectWrapper.wrap(this.zzc), this.zzb);
    }
}
