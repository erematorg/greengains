package com.google.android.gms.internal.measurement;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zzdv;

final class zzes extends zzdv.zzb {
    private final /* synthetic */ String zzc;
    private final /* synthetic */ String zzd;
    private final /* synthetic */ boolean zze;
    private final /* synthetic */ zzdi zzf;
    private final /* synthetic */ zzdv zzg;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public zzes(zzdv zzdv, String str, String str2, boolean z2, zzdi zzdi) {
        super(zzdv);
        this.zzc = str;
        this.zzd = str2;
        this.zze = z2;
        this.zzf = zzdi;
        this.zzg = zzdv;
    }

    public final void zza() throws RemoteException {
        ((zzdk) Preconditions.checkNotNull(this.zzg.zzj)).getUserProperties(this.zzc, this.zzd, this.zze, this.zzf);
    }

    public final void zzb() {
        this.zzf.zza((Bundle) null);
    }
}
