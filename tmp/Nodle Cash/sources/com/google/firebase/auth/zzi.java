package com.google.firebase.auth;

import com.google.firebase.auth.FirebaseAuth;

final class zzi implements Runnable {
    private final /* synthetic */ FirebaseAuth.IdTokenListener zza;
    private final /* synthetic */ FirebaseAuth zzb;

    public zzi(FirebaseAuth firebaseAuth, FirebaseAuth.IdTokenListener idTokenListener) {
        this.zza = idTokenListener;
        this.zzb = firebaseAuth;
    }

    public final void run() {
        this.zza.onIdTokenChanged(this.zzb);
    }
}
