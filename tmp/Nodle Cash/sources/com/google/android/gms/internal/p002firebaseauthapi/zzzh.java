package com.google.android.gms.internal.p002firebaseauthapi;

import androidx.annotation.Nullable;
import com.google.firebase.auth.internal.zzal;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzzh  reason: invalid package */
final class zzzh implements zzadm<zzafm> {
    private final /* synthetic */ zzacf zza;
    private final /* synthetic */ zzyl zzb;

    public zzzh(zzyl zzyl, zzacf zzacf) {
        this.zza = zzacf;
        this.zzb = zzyl;
    }

    public final void zza(@Nullable String str) {
        this.zza.zza(zzal.zza(str));
    }

    public final /* synthetic */ void zza(Object obj) {
        zzafm zzafm = (zzafm) obj;
        zzagf zzagf = new zzagf();
        zzagf.zzd(zzafm.zzc()).zzc((String) null).zzf((String) null);
        zzyl.zza(this.zzb, this.zza, zzafm, zzagf, (zzadn) this);
    }
}
