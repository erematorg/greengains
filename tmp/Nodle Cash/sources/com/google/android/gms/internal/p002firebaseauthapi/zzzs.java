package com.google.android.gms.internal.p002firebaseauthapi;

import androidx.annotation.Nullable;
import com.google.firebase.auth.internal.zzal;
import com.google.firebase.auth.zzf;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzzs  reason: invalid package */
final class zzzs implements zzadm<zzaev> {
    private final /* synthetic */ zzacf zza;
    private final /* synthetic */ zzyl zzb;

    public zzzs(zzyl zzyl, zzacf zzacf) {
        this.zza = zzacf;
        this.zzb = zzyl;
    }

    public final void zza(@Nullable String str) {
        this.zza.zza(zzal.zza(str));
    }

    public final /* synthetic */ void zza(Object obj) {
        zzaev zzaev = (zzaev) obj;
        this.zzb.zza(new zzafm(zzaev.zzb(), zzaev.zza(), Long.valueOf(zzafo.zza(zzaev.zza())), "Bearer"), (String) null, (String) null, Boolean.FALSE, (zzf) null, this.zza, this);
    }
}
