package com.google.android.gms.internal.p002firebaseauthapi;

import androidx.annotation.Nullable;
import com.google.firebase.auth.internal.zzal;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzzm  reason: invalid package */
final class zzzm implements zzadm<zzafm> {
    final /* synthetic */ zzacf zza;
    final /* synthetic */ zzyl zzb;
    private final /* synthetic */ String zzc;
    private final /* synthetic */ String zzd;

    public zzzm(zzyl zzyl, String str, String str2, zzacf zzacf) {
        this.zzc = str;
        this.zzd = str2;
        this.zza = zzacf;
        this.zzb = zzyl;
    }

    public final void zza(@Nullable String str) {
        this.zza.zza(zzal.zza(str));
    }

    public final /* synthetic */ void zza(Object obj) {
        this.zzb.zza.zza(new zzahd(((zzafm) obj).zzc(), this.zzc, this.zzd), (zzadm<zzahc>) new zzzl(this));
    }
}
