package com.google.android.gms.internal.p002firebaseauthapi;

import androidx.annotation.Nullable;
import com.google.firebase.auth.internal.zzal;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzzf  reason: invalid package */
final class zzzf implements zzadm<zzafm> {
    final /* synthetic */ zzacf zza;
    final /* synthetic */ zzyl zzb;
    private final /* synthetic */ zzags zzc;

    public zzzf(zzyl zzyl, zzags zzags, zzacf zzacf) {
        this.zzc = zzags;
        this.zza = zzacf;
        this.zzb = zzyl;
    }

    public final void zza(@Nullable String str) {
        this.zza.zza(zzal.zza(str));
    }

    public final /* synthetic */ void zza(Object obj) {
        this.zzc.zzb(true);
        this.zzc.zza(((zzafm) obj).zzc());
        this.zzb.zza.zza(this.zzc, (zzadm<zzagu>) new zzzi(this, this));
    }
}
