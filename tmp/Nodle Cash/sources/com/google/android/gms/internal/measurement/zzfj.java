package com.google.android.gms.internal.measurement;

import android.app.Activity;
import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.internal.measurement.zzdv;

final class zzfj extends zzdv.zzb {
    private final /* synthetic */ Activity zzc;
    private final /* synthetic */ zzdi zzd;
    private final /* synthetic */ zzdv.zzc zze;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public zzfj(zzdv.zzc zzc2, Activity activity, zzdi zzdi) {
        super(zzdv.this);
        this.zzc = activity;
        this.zzd = zzdi;
        this.zze = zzc2;
    }

    public final void zza() throws RemoteException {
        ((zzdk) Preconditions.checkNotNull(zzdv.this.zzj)).onActivitySaveInstanceState(ObjectWrapper.wrap(this.zzc), this.zzd, this.zzb);
    }
}
