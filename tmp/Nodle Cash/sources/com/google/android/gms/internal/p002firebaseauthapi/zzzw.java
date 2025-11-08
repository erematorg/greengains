package com.google.android.gms.internal.p002firebaseauthapi;

import androidx.annotation.Nullable;
import com.google.firebase.auth.internal.zzal;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzzw  reason: invalid package */
final class zzzw implements zzadm<zzafm> {
    final /* synthetic */ zzacf zza;
    private final /* synthetic */ zzyl zzb;

    public zzzw(zzyl zzyl, zzacf zzacf) {
        this.zza = zzacf;
        this.zzb = zzyl;
    }

    public final void zza(@Nullable String str) {
        this.zza.zza(zzal.zza(str));
    }

    public final /* synthetic */ void zza(Object obj) {
        zzafm zzafm = (zzafm) obj;
        this.zzb.zza.zza(new zzafd(zzafm.zzc()), (zzadm<zzafc>) new zzzv(this, this, zzafm));
    }
}
