package com.google.firebase.auth;

import com.google.firebase.auth.FirebaseAuth;

final class zzv implements Runnable {
    private final /* synthetic */ FirebaseAuth.AuthStateListener zza;
    private final /* synthetic */ FirebaseAuth zzb;

    public zzv(FirebaseAuth firebaseAuth, FirebaseAuth.AuthStateListener authStateListener) {
        this.zza = authStateListener;
        this.zzb = firebaseAuth;
    }

    public final void run() {
        this.zza.onAuthStateChanged(this.zzb);
    }
}
