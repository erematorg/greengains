package com.google.android.gms.internal.p002firebaseauthapi;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.FirebaseError;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.internal.zzac;
import com.google.firebase.auth.internal.zzi;
import com.google.firebase.auth.internal.zzw;

@VisibleForTesting
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzabd  reason: invalid package */
final class zzabd extends zzacz<AuthResult, zzi> {
    @Nullable
    private final String zzaa;
    @Nullable
    private final String zzab;
    @NonNull
    private final String zzy;
    @NonNull
    private final String zzz;

    public zzabd(String str, String str2, @Nullable String str3, @Nullable String str4) {
        super(2);
        Preconditions.checkNotEmpty(str, "email cannot be null or empty");
        Preconditions.checkNotEmpty(str2, "password cannot be null or empty");
        this.zzy = str;
        this.zzz = str2;
        this.zzaa = str3;
        this.zzab = str4;
    }

    public final String zza() {
        return "reauthenticateWithEmailPasswordWithData";
    }

    public final void zzb() {
        zzac zza = zzaak.zza(this.zzc, this.zzk);
        if (this.zzd.getUid().equalsIgnoreCase(zza.getUid())) {
            ((zzi) this.zze).zza(this.zzj, zza);
            zzb(new zzw(zza));
            return;
        }
        zza(new Status(FirebaseError.ERROR_USER_MISMATCH));
    }

    public final void zza(TaskCompletionSource taskCompletionSource, zzaci zzaci) {
        this.zzg = new zzadg(this, taskCompletionSource);
        zzaci.zzb(this.zzy, this.zzz, this.zzaa, this.zzab, this.zzb);
    }
}
