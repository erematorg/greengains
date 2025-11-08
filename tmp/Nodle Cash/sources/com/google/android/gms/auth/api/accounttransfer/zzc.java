package com.google.android.gms.auth.api.accounttransfer;

import android.os.RemoteException;
import com.google.android.gms.internal.auth.zzau;
import com.google.android.gms.internal.auth.zzaz;

final class zzc extends zzn {
    final /* synthetic */ zzaz zza;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public zzc(AccountTransferClient accountTransferClient, int i3, zzaz zzaz) {
        super(1606);
        this.zza = zzaz;
    }

    public final void zza(zzau zzau) throws RemoteException {
        zzau.zzh(this.zzc, this.zza);
    }
}
