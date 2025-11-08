package com.google.android.gms.internal.measurement;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zzdv;

final class zzeq extends zzdv.zzb {
    private final /* synthetic */ zzdi zzc;
    private final /* synthetic */ zzdv zzd;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public zzeq(zzdv zzdv, zzdi zzdi) {
        super(zzdv);
        this.zzc = zzdi;
        this.zzd = zzdv;
    }

    public final void zza() throws RemoteException {
        ((zzdk) Preconditions.checkNotNull(this.zzd.zzj)).getCurrentScreenName(this.zzc);
    }

    public final void zzb() {
        this.zzc.zza((Bundle) null);
    }
}
