package com.google.android.gms.internal.p002firebaseauthapi;

import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.internal.zzac;
import com.google.firebase.auth.internal.zzi;
import com.google.firebase.auth.internal.zzw;

@VisibleForTesting
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzabl  reason: invalid package */
final class zzabl extends zzacz<AuthResult, zzi> {
    @Nullable
    private final String zzy;

    public zzabl(@Nullable String str) {
        super(2);
        this.zzy = str;
    }

    public final String zza() {
        return "signInAnonymously";
    }

    public final void zzb() {
        zzac zza = zzaak.zza(this.zzc, this.zzk);
        ((zzi) this.zze).zza(this.zzj, zza);
        zzb(new zzw(zza));
    }

    public final void zza(TaskCompletionSource taskCompletionSource, zzaci zzaci) {
        this.zzg = new zzadg(this, taskCompletionSource);
        zzaci.zzd(this.zzy, this.zzb);
    }
}
