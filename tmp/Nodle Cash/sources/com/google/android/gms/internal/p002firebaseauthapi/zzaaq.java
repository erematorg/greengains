package com.google.android.gms.internal.p002firebaseauthapi;

import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.auth.SignInMethodQueryResult;
import com.google.firebase.auth.internal.zzao;
import com.google.firebase.auth.internal.zzi;
import java.util.List;

@VisibleForTesting
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzaaq  reason: invalid package */
final class zzaaq extends zzacz<SignInMethodQueryResult, zzi> {
    private final String zzy;
    @Nullable
    private final String zzz;

    public zzaaq(String str, @Nullable String str2) {
        super(3);
        Preconditions.checkNotEmpty(str, "email cannot be null or empty");
        this.zzy = str;
        this.zzz = str2;
    }

    public final String zza() {
        return "fetchSignInMethodsForEmail";
    }

    public final void zzb() {
        zzb(new zzao(this.zzl.zza() == null ? zzaq.zzh() : (List) Preconditions.checkNotNull(this.zzl.zza())));
    }

    public final void zza(TaskCompletionSource taskCompletionSource, zzaci zzaci) {
        this.zzg = new zzadg(this, taskCompletionSource);
        zzaci.zze(this.zzy, this.zzz, this.zzb);
    }
}
