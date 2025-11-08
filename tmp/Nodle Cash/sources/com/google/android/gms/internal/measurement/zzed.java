package com.google.android.gms.internal.measurement;

import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zzdv;

final class zzed extends zzdv.zzb {
    private final /* synthetic */ Boolean zzc;
    private final /* synthetic */ zzdv zzd;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public zzed(zzdv zzdv, Boolean bool) {
        super(zzdv);
        this.zzc = bool;
        this.zzd = zzdv;
    }

    public final void zza() throws RemoteException {
        if (this.zzc != null) {
            ((zzdk) Preconditions.checkNotNull(this.zzd.zzj)).setMeasurementEnabled(this.zzc.booleanValue(), this.zza);
        } else {
            ((zzdk) Preconditions.checkNotNull(this.zzd.zzj)).clearMeasurementEnabled(this.zza);
        }
    }
}
