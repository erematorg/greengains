package com.google.android.gms.auth.api.signin.internal;

import android.os.RemoteException;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.api.Status;

final class zbf extends zba {
    final /* synthetic */ zbg zba;

    public zbf(zbg zbg) {
        this.zba = zbg;
    }

    public final void zbd(GoogleSignInAccount googleSignInAccount, Status status) throws RemoteException {
        if (googleSignInAccount != null) {
            zbg zbg = this.zba;
            zbn.zbc(zbg.zba).zbe(zbg.zbb, googleSignInAccount);
        }
        this.zba.setResult(new GoogleSignInResult(googleSignInAccount, status));
    }
}
