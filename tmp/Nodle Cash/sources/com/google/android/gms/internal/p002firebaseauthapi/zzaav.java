package com.google.android.gms.internal.p002firebaseauthapi;

import androidx.annotation.VisibleForTesting;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.internal.zzac;
import com.google.firebase.auth.internal.zzh;
import com.google.firebase.auth.internal.zzi;
import com.google.firebase.auth.internal.zzw;

@VisibleForTesting
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzaav  reason: invalid package */
final class zzaav extends zzacz<AuthResult, zzi> {
    private final zzags zzy;

    public zzaav(AuthCredential authCredential) {
        super(2);
        Preconditions.checkNotNull(authCredential, "credential cannot be null");
        this.zzy = zzh.zza(authCredential, (String) null);
    }

    public final String zza() {
        return "linkFederatedCredential";
    }

    public final void zzb() {
        zzac zza = zzaak.zza(this.zzc, this.zzk);
        ((zzi) this.zze).zza(this.zzj, zza);
        zzb(new zzw(zza));
    }

    public final void zza(TaskCompletionSource taskCompletionSource, zzaci zzaci) {
        this.zzg = new zzadg(this, taskCompletionSource);
        zzaci.zza(this.zzd.zze(), this.zzy, (zzacg) this.zzb);
    }
}
