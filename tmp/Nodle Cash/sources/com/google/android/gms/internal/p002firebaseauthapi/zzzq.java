package com.google.android.gms.internal.p002firebaseauthapi;

import androidx.annotation.Nullable;
import com.google.firebase.auth.internal.zzal;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzzq  reason: invalid package */
final class zzzq implements zzadm<zzafm> {
    final /* synthetic */ zzacf zza;
    final /* synthetic */ zzyl zzb;
    private final /* synthetic */ zzaeq zzc;

    public zzzq(zzyl zzyl, zzaeq zzaeq, zzacf zzacf) {
        this.zzc = zzaeq;
        this.zza = zzacf;
        this.zzb = zzyl;
    }

    public final void zza(@Nullable String str) {
        this.zza.zza(zzal.zza(str));
    }

    public final /* synthetic */ void zza(Object obj) {
        this.zzc.zza(((zzafm) obj).zzc());
        this.zzb.zza.zza(this.zzc, (zzadm<zzaet>) new zzzp(this));
    }
}
