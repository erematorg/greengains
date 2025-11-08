package com.google.android.gms.internal.p002firebaseauthapi;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.auth.ActionCodeSettings;
import com.google.firebase.auth.internal.zzi;

@VisibleForTesting
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzabj  reason: invalid package */
final class zzabj extends zzacz<Void, zzi> {
    @NonNull
    private final zzye zzy;
    private final String zzz;

    public zzabj(String str, ActionCodeSettings actionCodeSettings, @Nullable String str2, @Nullable String str3, String str4) {
        super(4);
        Preconditions.checkNotEmpty(str, "email cannot be null or empty");
        this.zzy = new zzye(str, actionCodeSettings, str2, str3);
        this.zzz = str4;
    }

    public final String zza() {
        return this.zzz;
    }

    public final void zzb() {
        zzb(null);
    }

    public final void zza(TaskCompletionSource taskCompletionSource, zzaci zzaci) {
        this.zzg = new zzadg(this, taskCompletionSource);
        zzaci.zza(this.zzy, (zzacg) this.zzb);
    }
}
