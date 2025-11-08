package com.google.android.gms.internal.measurement;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zzdv;

final class zzex extends zzdv.zzb {
    private final /* synthetic */ zzdi zzc;
    private final /* synthetic */ int zzd;
    private final /* synthetic */ zzdv zze;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public zzex(zzdv zzdv, zzdi zzdi, int i3) {
        super(zzdv);
        this.zzc = zzdi;
        this.zzd = i3;
        this.zze = zzdv;
    }

    public final void zza() throws RemoteException {
        ((zzdk) Preconditions.checkNotNull(this.zze.zzj)).getTestFlag(this.zzc, this.zzd);
    }

    public final void zzb() {
        this.zzc.zza((Bundle) null);
    }
}
