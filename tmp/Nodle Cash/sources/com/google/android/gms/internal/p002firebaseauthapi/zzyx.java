package com.google.android.gms.internal.p002firebaseauthapi;

import androidx.annotation.Nullable;
import com.google.firebase.auth.internal.zzal;
import com.google.firebase.auth.zzf;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzyx  reason: invalid package */
final class zzyx implements zzadm<zzagw> {
    private final /* synthetic */ zzacf zza;
    private final /* synthetic */ zzyl zzb;

    public zzyx(zzyl zzyl, zzacf zzacf) {
        this.zza = zzacf;
        this.zzb = zzyl;
    }

    public final void zza(@Nullable String str) {
        this.zza.zza(zzal.zza(str));
    }

    public final /* synthetic */ void zza(Object obj) {
        zzagw zzagw = (zzagw) obj;
        this.zzb.zza(new zzafm(zzagw.zzc(), zzagw.zzb(), Long.valueOf(zzagw.zza()), "Bearer"), (String) null, (String) null, Boolean.valueOf(zzagw.zzd()), (zzf) null, this.zza, this);
    }
}
