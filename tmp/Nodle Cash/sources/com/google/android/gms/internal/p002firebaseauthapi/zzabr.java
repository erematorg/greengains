package com.google.android.gms.internal.p002firebaseauthapi;

import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.auth.internal.zzaj;
import com.google.firebase.auth.internal.zzi;

@VisibleForTesting
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzabr  reason: invalid package */
final class zzabr extends zzacz<Void, zzi> {
    @Nullable
    private final String zzaa;
    private final long zzab;
    private final boolean zzac;
    private final boolean zzad;
    @Nullable
    private final String zzae;
    @Nullable
    private final String zzaf;
    private final boolean zzag;
    private final String zzy;
    private final String zzz;

    public zzabr(zzaj zzaj, String str, @Nullable String str2, long j2, boolean z2, boolean z3, @Nullable String str3, @Nullable String str4, boolean z4) {
        super(8);
        Preconditions.checkNotNull(zzaj);
        Preconditions.checkNotEmpty(str);
        this.zzy = Preconditions.checkNotEmpty(zzaj.zzb());
        this.zzz = str;
        this.zzaa = str2;
        this.zzab = j2;
        this.zzac = z2;
        this.zzad = z3;
        this.zzae = str3;
        this.zzaf = str4;
        this.zzag = z4;
    }

    public final String zza() {
        return "startMfaEnrollment";
    }

    public final void zzb() {
    }

    public final void zza(TaskCompletionSource taskCompletionSource, zzaci zzaci) {
        this.zzg = new zzadg(this, taskCompletionSource);
        zzaci.zza(this.zzy, this.zzz, this.zzaa, this.zzab, this.zzac, this.zzad, this.zzae, this.zzaf, this.zzag, this.zzb);
    }
}
