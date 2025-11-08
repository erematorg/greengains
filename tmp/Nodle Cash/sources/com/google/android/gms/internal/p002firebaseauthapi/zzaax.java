package com.google.android.gms.internal.p002firebaseauthapi;

import androidx.annotation.VisibleForTesting;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.EmailAuthCredential;
import com.google.firebase.auth.internal.zzac;
import com.google.firebase.auth.internal.zzi;
import com.google.firebase.auth.internal.zzw;

@VisibleForTesting
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzaax  reason: invalid package */
final class zzaax extends zzacz<AuthResult, zzi> {
    private final EmailAuthCredential zzy;

    public zzaax(EmailAuthCredential emailAuthCredential) {
        super(2);
        this.zzy = (EmailAuthCredential) Preconditions.checkNotNull(emailAuthCredential, "credential cannot be null");
    }

    public final String zza() {
        return "linkEmailAuthCredential";
    }

    public final void zzb() {
        zzac zza = zzaak.zza(this.zzc, this.zzk);
        ((zzi) this.zze).zza(this.zzj, zza);
        zzb(new zzw(zza));
    }

    public final void zza(TaskCompletionSource taskCompletionSource, zzaci zzaci) {
        this.zzg = new zzadg(this, taskCompletionSource);
        zzaci.zza(new zzyf(this.zzy.zza(this.zzd), (String) null), (zzacg) this.zzb);
    }
}
