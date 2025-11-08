package com.google.android.gms.internal.measurement;

import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.internal.measurement.zzdv;

final class zzdx extends zzdv.zzb {
    private final /* synthetic */ String zzc;
    private final /* synthetic */ String zzd;
    private final /* synthetic */ Object zze;
    private final /* synthetic */ boolean zzf;
    private final /* synthetic */ zzdv zzg;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public zzdx(zzdv zzdv, String str, String str2, Object obj, boolean z2) {
        super(zzdv);
        this.zzc = str;
        this.zzd = str2;
        this.zze = obj;
        this.zzf = z2;
        this.zzg = zzdv;
    }

    public final void zza() throws RemoteException {
        ((zzdk) Preconditions.checkNotNull(this.zzg.zzj)).setUserProperty(this.zzc, this.zzd, ObjectWrapper.wrap(this.zze), this.zzf, this.zza);
    }
}
