package com.google.android.gms.internal.p002firebaseauthapi;

import androidx.annotation.Nullable;
import com.google.firebase.auth.internal.zzal;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzzk  reason: invalid package */
final class zzzk implements zzadm<zzafm> {
    final /* synthetic */ String zza;
    final /* synthetic */ zzacf zzb;
    final /* synthetic */ zzyl zzc;

    public zzzk(zzyl zzyl, String str, zzacf zzacf) {
        this.zza = str;
        this.zzb = zzacf;
        this.zzc = zzyl;
    }

    public final void zza(@Nullable String str) {
        this.zzb.zza(zzal.zza(str));
    }

    public final /* synthetic */ void zza(Object obj) {
        zzafm zzafm = (zzafm) obj;
        this.zzc.zza.zza(new zzafd(zzafm.zzc()), (zzadm<zzafc>) new zzzj(this, this, zzafm));
    }
}
