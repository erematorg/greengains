package com.google.android.gms.internal.p002firebaseauthapi;

import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.auth.MultiFactorAssertion;
import com.google.firebase.auth.internal.zzi;

@VisibleForTesting
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzaap  reason: invalid package */
final class zzaap extends zzacz<Void, zzi> {
    @Nullable
    private final String zzaa;
    @Nullable
    private final String zzab;
    private final MultiFactorAssertion zzy;
    private final String zzz;

    public zzaap(MultiFactorAssertion multiFactorAssertion, String str, @Nullable String str2, @Nullable String str3) {
        super(2);
        this.zzy = (MultiFactorAssertion) Preconditions.checkNotNull(multiFactorAssertion);
        this.zzz = Preconditions.checkNotEmpty(str);
        this.zzaa = str2;
        this.zzab = str3;
    }

    public final String zza() {
        return "finalizeMfaEnrollment";
    }

    public final void zzb() {
        ((zzi) this.zze).zza(this.zzj, zzaak.zza(this.zzc, this.zzk));
        zzb(null);
    }

    public final void zza(TaskCompletionSource taskCompletionSource, zzaci zzaci) {
        this.zzg = new zzadg(this, taskCompletionSource);
        zzaci.zza(this.zzy, this.zzz, this.zzaa, this.zzab, (zzacg) this.zzb);
    }
}
