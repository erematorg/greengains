package com.google.android.gms.internal.p002firebaseauthapi;

import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.auth.ActionCodeResult;
import com.google.firebase.auth.internal.zzi;
import com.google.firebase.auth.internal.zzr;

@VisibleForTesting
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzaam  reason: invalid package */
final class zzaam extends zzacz<ActionCodeResult, zzi> {
    private final String zzy;
    @Nullable
    private final String zzz;

    public zzaam(String str, @Nullable String str2) {
        super(4);
        Preconditions.checkNotEmpty(str, "code cannot be null or empty");
        this.zzy = str;
        this.zzz = str2;
    }

    public final String zza() {
        return "checkActionCode";
    }

    public final void zzb() {
        zzb(new zzr(this.zzm));
    }

    public final void zza(TaskCompletionSource taskCompletionSource, zzaci zzaci) {
        this.zzg = new zzadg(this, taskCompletionSource);
        zzaci.zzd(this.zzy, this.zzz, this.zzb);
    }
}
