package com.google.android.gms.internal.p002firebaseauthapi;

import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.auth.internal.zzaj;

@VisibleForTesting
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzabu  reason: invalid package */
final class zzabu extends zzacz<zzagi, Void> {
    private final zzagp zzy;

    public zzabu(zzaj zzaj, @Nullable String str) {
        super(12);
        Preconditions.checkNotNull(zzaj);
        this.zzy = zzagp.zza(Preconditions.checkNotEmpty(zzaj.zzb()), str);
    }

    public final String zza() {
        return "startMfaEnrollment";
    }

    public final void zzb() {
        zzb(this.zzv);
    }

    public final void zza(TaskCompletionSource taskCompletionSource, zzaci zzaci) {
        this.zzg = new zzadg(this, taskCompletionSource);
        zzaci.zza(this.zzy, (zzacg) this.zzb);
    }
}
