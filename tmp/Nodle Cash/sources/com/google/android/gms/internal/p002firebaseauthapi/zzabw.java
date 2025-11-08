package com.google.android.gms.internal.p002firebaseauthapi;

import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.auth.internal.zzi;

@VisibleForTesting
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzabw  reason: invalid package */
final class zzabw extends zzacz<Void, zzi> {
    @Nullable
    private final String zzaa;
    private final String zzy;
    private final String zzz;

    public zzabw(String str, String str2, @Nullable String str3) {
        super(2);
        this.zzy = Preconditions.checkNotEmpty(str);
        this.zzz = Preconditions.checkNotEmpty(str2);
        this.zzaa = str3;
    }

    public final String zza() {
        return "unenrollMfa";
    }

    public final void zzb() {
        ((zzi) this.zze).zza(this.zzj, zzaak.zza(this.zzc, this.zzk));
        zzb(null);
    }

    public final void zza(TaskCompletionSource taskCompletionSource, zzaci zzaci) {
        this.zzg = new zzadg(this, taskCompletionSource);
        zzaci.zza(this.zzy, this.zzz, this.zzaa, (zzacg) this.zzb);
    }
}
