package com.google.android.gms.internal.p002firebaseauthapi;

import androidx.annotation.VisibleForTesting;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.auth.PhoneAuthProvider;

@VisibleForTesting
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzacd  reason: invalid package */
final class zzacd extends zzacz<Void, PhoneAuthProvider.OnVerificationStateChangedCallbacks> {
    private final zzyd zzy;

    public zzacd(zzagd zzagd) {
        super(8);
        Preconditions.checkNotNull(zzagd);
        this.zzy = new zzyd(zzagd);
    }

    public final String zza() {
        return "verifyPhoneNumber";
    }

    public final void zzb() {
    }

    public final void zza(TaskCompletionSource taskCompletionSource, zzaci zzaci) {
        this.zzg = new zzadg(this, taskCompletionSource);
        zzaci.zza(this.zzy, (zzacg) this.zzb);
    }
}
