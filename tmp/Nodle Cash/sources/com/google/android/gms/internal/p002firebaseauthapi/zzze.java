package com.google.android.gms.internal.p002firebaseauthapi;

import androidx.annotation.Nullable;
import com.google.firebase.auth.zzf;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzze  reason: invalid package */
final class zzze implements zzadm<zzagg> {
    private final /* synthetic */ zzacf zza;
    private final /* synthetic */ zzadn zzb;
    private final /* synthetic */ zzyl zzc;

    public zzze(zzyl zzyl, zzacf zzacf, zzadn zzadn) {
        this.zza = zzacf;
        this.zzb = zzadn;
        this.zzc = zzyl;
    }

    public final void zza(@Nullable String str) {
        this.zzb.zza(str);
    }

    public final /* synthetic */ void zza(Object obj) {
        zzagg zzagg = (zzagg) obj;
        this.zzc.zza(new zzafm(zzagg.zzc(), zzagg.zzb(), Long.valueOf(zzagg.zza()), "Bearer"), (String) null, "password", Boolean.FALSE, (zzf) null, this.zza, this);
    }
}
