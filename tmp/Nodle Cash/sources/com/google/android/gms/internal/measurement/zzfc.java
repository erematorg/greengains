package com.google.android.gms.internal.measurement;

import android.content.Intent;
import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zzdv;

final class zzfc extends zzdv.zzb {
    private final /* synthetic */ Intent zzc;
    private final /* synthetic */ zzdv zzd;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public zzfc(zzdv zzdv, Intent intent) {
        super(zzdv);
        this.zzc = intent;
        this.zzd = zzdv;
    }

    public final void zza() throws RemoteException {
        ((zzdk) Preconditions.checkNotNull(this.zzd.zzj)).setSgtmDebugInfo(this.zzc);
    }
}
