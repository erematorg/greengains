package com.google.android.gms.internal.p002firebaseauthapi;

import androidx.annotation.VisibleForTesting;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.auth.internal.zzi;

@VisibleForTesting
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzacc  reason: invalid package */
final class zzacc extends zzacz<Void, zzi> {
    private final UserProfileChangeRequest zzy;

    public zzacc(UserProfileChangeRequest userProfileChangeRequest) {
        super(2);
        this.zzy = (UserProfileChangeRequest) Preconditions.checkNotNull(userProfileChangeRequest, "request cannot be null");
    }

    public final String zza() {
        return "updateProfile";
    }

    public final void zzb() {
        ((zzi) this.zze).zza(this.zzj, zzaak.zza(this.zzc, this.zzk));
        zzb(null);
    }

    public final void zza(TaskCompletionSource taskCompletionSource, zzaci zzaci) {
        this.zzg = new zzadg(this, taskCompletionSource);
        zzaci.zza(this.zzd.zze(), this.zzy, (zzacg) this.zzb);
    }
}
