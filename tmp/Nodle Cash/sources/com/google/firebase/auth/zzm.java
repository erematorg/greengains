package com.google.firebase.auth;

import android.util.Log;
import com.google.android.gms.internal.p002firebaseauthapi.zzach;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthProvider;

final class zzm extends PhoneAuthProvider.OnVerificationStateChangedCallbacks {
    private final /* synthetic */ PhoneAuthOptions zza;
    private final /* synthetic */ PhoneAuthProvider.OnVerificationStateChangedCallbacks zzb;
    private final /* synthetic */ FirebaseAuth zzc;

    public zzm(FirebaseAuth firebaseAuth, PhoneAuthOptions phoneAuthOptions, PhoneAuthProvider.OnVerificationStateChangedCallbacks onVerificationStateChangedCallbacks) {
        this.zza = phoneAuthOptions;
        this.zzb = onVerificationStateChangedCallbacks;
        this.zzc = firebaseAuth;
    }

    public final void onCodeAutoRetrievalTimeOut(String str) {
        this.zzb.onCodeAutoRetrievalTimeOut(str);
    }

    public final void onCodeSent(String str, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
        this.zzb.onCodeSent(str, forceResendingToken);
    }

    public final void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
        this.zzb.onVerificationCompleted(phoneAuthCredential);
    }

    public final void onVerificationFailed(FirebaseException firebaseException) {
        if (zzach.zza((Exception) firebaseException)) {
            this.zza.zza(true);
            String zzh = this.zza.zzh();
            Log.d("FirebaseAuth", "Re-triggering phone verification with Recaptcha flow forced for phone number " + zzh);
            FirebaseAuth.zza(this.zza);
            return;
        }
        String zzh2 = this.zza.zzh();
        String message = firebaseException.getMessage();
        Log.d("FirebaseAuth", "Invoking original failure callbacks after phone verification failure for " + zzh2 + ", error - " + message);
        this.zzb.onVerificationFailed(firebaseException);
    }
}
