package com.google.android.gms.internal.measurement;

import android.app.Activity;
import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.internal.measurement.zzdv;

final class zzfk extends zzdv.zzb {
    private final /* synthetic */ Activity zzc;
    private final /* synthetic */ zzdv.zzc zzd;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public zzfk(zzdv.zzc zzc2, Activity activity) {
        super(zzdv.this);
        this.zzc = activity;
        this.zzd = zzc2;
    }

    public final void zza() throws RemoteException {
        ((zzdk) Preconditions.checkNotNull(zzdv.this.zzj)).onActivityStopped(ObjectWrapper.wrap(this.zzc), this.zzb);
    }
}
