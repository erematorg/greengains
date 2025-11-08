package com.google.android.gms.internal.p002firebaseauthapi;

import androidx.annotation.Nullable;
import com.google.firebase.auth.internal.zzal;
import com.google.firebase.auth.zzf;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzyq  reason: invalid package */
final class zzyq implements zzadm<zzagy> {
    private final /* synthetic */ zzacf zza;
    private final /* synthetic */ zzyl zzb;

    public zzyq(zzyl zzyl, zzacf zzacf) {
        this.zza = zzacf;
        this.zzb = zzyl;
    }

    public final void zza(@Nullable String str) {
        this.zza.zza(zzal.zza(str));
    }

    public final /* synthetic */ void zza(Object obj) {
        zzagy zzagy = (zzagy) obj;
        if (zzagy.zzf()) {
            this.zza.zza(new zzym(zzagy.zzc(), zzagy.zze(), (zzf) null));
            return;
        }
        this.zzb.zza(new zzafm(zzagy.zzd(), zzagy.zzb(), Long.valueOf(zzagy.zza()), "Bearer"), (String) null, (String) null, Boolean.FALSE, (zzf) null, this.zza, this);
    }
}
