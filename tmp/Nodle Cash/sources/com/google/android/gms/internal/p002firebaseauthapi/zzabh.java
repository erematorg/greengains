package com.google.android.gms.internal.p002firebaseauthapi;

import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.google.android.gms.tasks.TaskCompletionSource;

@VisibleForTesting
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzabh  reason: invalid package */
final class zzabh extends zzacz<Void, Void> {
    private final zzafy zzy;

    public zzabh(String str, String str2, String str3, @Nullable String str4) {
        super(15);
        this.zzy = zzafy.zzg().zzd(str).zza(str2).zzc(str4).zzb(str3).zza(zzafb.ACCESS_TOKEN).zza();
    }

    public final String zza() {
        return "revokeAccessToken";
    }

    public final void zzb() {
        zzb(null);
    }

    public final void zza(TaskCompletionSource<Void> taskCompletionSource, zzaci zzaci) {
        this.zzg = new zzadg(this, taskCompletionSource);
        zzaci.zza(this.zzy, (zzacg) this.zzb);
    }
}
