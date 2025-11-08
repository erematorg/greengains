package com.google.android.gms.internal.measurement;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zzdv;

final class zzdz extends zzdv.zzb {
    private final /* synthetic */ String zzc;
    private final /* synthetic */ String zzd;
    private final /* synthetic */ Bundle zze;
    private final /* synthetic */ zzdv zzf;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public zzdz(zzdv zzdv, String str, String str2, Bundle bundle) {
        super(zzdv);
        this.zzc = str;
        this.zzd = str2;
        this.zze = bundle;
        this.zzf = zzdv;
    }

    public final void zza() throws RemoteException {
        ((zzdk) Preconditions.checkNotNull(this.zzf.zzj)).clearConditionalUserProperty(this.zzc, this.zzd, this.zze);
    }
}
