package com.google.android.gms.internal.p002firebaseauthapi;

import androidx.annotation.Nullable;
import com.google.firebase.auth.internal.zzal;
import com.google.firebase.auth.zzf;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzyn  reason: invalid package */
final class zzyn implements zzadm<zzagg> {
    private final /* synthetic */ zzacf zza;
    private final /* synthetic */ zzyl zzb;

    public zzyn(zzyl zzyl, zzacf zzacf) {
        this.zza = zzacf;
        this.zzb = zzyl;
    }

    public final void zza(@Nullable String str) {
        this.zza.zza(zzal.zza(str));
    }

    public final /* synthetic */ void zza(Object obj) {
        zzagg zzagg = (zzagg) obj;
        this.zzb.zza(new zzafm(zzagg.zzc(), zzagg.zzb(), Long.valueOf(zzagg.zza()), "Bearer"), (String) null, (String) null, Boolean.TRUE, (zzf) null, this.zza, this);
    }
}
