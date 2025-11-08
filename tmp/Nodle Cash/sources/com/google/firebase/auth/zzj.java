package com.google.firebase.auth;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthProvider;

public final /* synthetic */ class zzj implements Runnable {
    private /* synthetic */ PhoneAuthProvider.OnVerificationStateChangedCallbacks zza;
    private /* synthetic */ FirebaseException zzb;

    public /* synthetic */ zzj(PhoneAuthProvider.OnVerificationStateChangedCallbacks onVerificationStateChangedCallbacks, FirebaseException firebaseException) {
        this.zza = onVerificationStateChangedCallbacks;
        this.zzb = firebaseException;
    }

    public final void run() {
        this.zza.onVerificationFailed(this.zzb);
    }
}
