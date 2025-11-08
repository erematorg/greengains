package com.google.android.gms.internal.p002firebaseauthapi;

import androidx.annotation.VisibleForTesting;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.auth.internal.zzas;

@VisibleForTesting
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzaan  reason: invalid package */
final class zzaan extends zzacz<Void, zzas> {
    public zzaan() {
        super(5);
    }

    public final String zza() {
        return "delete";
    }

    public final void zzb() {
        ((zzas) this.zze).zza();
        zzb(null);
    }

    public final void zza(TaskCompletionSource taskCompletionSource, zzaci zzaci) {
        this.zzg = new zzadg(this, taskCompletionSource);
        zzaci.zza(this.zzd.zze(), (zzacg) this.zzb);
    }
}
