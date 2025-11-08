package com.google.firebase.auth;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.p002firebaseauthapi.zzafm;
import com.google.firebase.auth.internal.zzar;
import com.google.firebase.auth.internal.zzi;

final class zzy implements zzar, zzi {
    private final /* synthetic */ FirebaseAuth zza;

    public zzy(FirebaseAuth firebaseAuth) {
        this.zza = firebaseAuth;
    }

    public final void zza(zzafm zzafm, FirebaseUser firebaseUser) {
        this.zza.zza(firebaseUser, zzafm, true, true);
    }

    public final void zza(Status status) {
        int statusCode = status.getStatusCode();
        if (statusCode == 17011 || statusCode == 17021 || statusCode == 17005) {
            this.zza.signOut();
        }
    }
}
