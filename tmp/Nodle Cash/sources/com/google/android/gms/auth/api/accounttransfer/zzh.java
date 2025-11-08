package com.google.android.gms.auth.api.accounttransfer;

import android.os.RemoteException;
import com.google.android.gms.internal.auth.zzau;
import com.google.android.gms.internal.auth.zzbb;

final class zzh extends zzn {
    final /* synthetic */ zzbb zza;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public zzh(AccountTransferClient accountTransferClient, int i3, zzbb zzbb) {
        super(1609);
        this.zza = zzbb;
    }

    public final void zza(zzau zzau) throws RemoteException {
        zzau.zze(this.zzc, this.zza);
    }
}
