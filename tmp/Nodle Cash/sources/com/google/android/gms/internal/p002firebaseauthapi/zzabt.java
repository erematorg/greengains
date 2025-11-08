package com.google.android.gms.internal.p002firebaseauthapi;

import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.auth.PhoneMultiFactorInfo;

@VisibleForTesting
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzabt  reason: invalid package */
final class zzabt extends zzacz<Void, PhoneAuthProvider.OnVerificationStateChangedCallbacks> {
    private final zzyh zzy;

    public zzabt(PhoneMultiFactorInfo phoneMultiFactorInfo, String str, @Nullable String str2, long j2, boolean z2, boolean z3, @Nullable String str3, @Nullable String str4, boolean z4) {
        super(8);
        Preconditions.checkNotNull(phoneMultiFactorInfo);
        Preconditions.checkNotEmpty(str);
        this.zzy = new zzyh(phoneMultiFactorInfo, str, str2, j2, z2, z3, str3, str4, z4);
    }

    public final String zza() {
        return "startMfaSignInWithPhoneNumber";
    }

    public final void zzb() {
    }

    public final void zza(TaskCompletionSource taskCompletionSource, zzaci zzaci) {
        this.zzg = new zzadg(this, taskCompletionSource);
        zzaci.zza(this.zzy, (zzacg) this.zzb);
    }
}
