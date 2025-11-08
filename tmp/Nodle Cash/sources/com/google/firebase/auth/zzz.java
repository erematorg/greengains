package com.google.firebase.auth;

import com.google.firebase.auth.FirebaseAuth;

final class zzz implements Runnable {
    private final /* synthetic */ FirebaseAuth zza;

    public zzz(FirebaseAuth firebaseAuth) {
        this.zza = firebaseAuth;
    }

    public final void run() {
        for (FirebaseAuth.AuthStateListener onAuthStateChanged : this.zza.zzd) {
            onAuthStateChanged.onAuthStateChanged(this.zza);
        }
    }
}
