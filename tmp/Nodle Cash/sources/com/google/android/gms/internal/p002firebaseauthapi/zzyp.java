package com.google.android.gms.internal.p002firebaseauthapi;

import androidx.annotation.Nullable;
import com.google.firebase.auth.EmailAuthCredential;
import com.google.firebase.auth.internal.zzal;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzyp  reason: invalid package */
final class zzyp implements zzadm<zzafm> {
    private final /* synthetic */ EmailAuthCredential zza;
    private final /* synthetic */ String zzb;
    private final /* synthetic */ zzacf zzc;
    private final /* synthetic */ zzyl zzd;

    public zzyp(zzyl zzyl, EmailAuthCredential emailAuthCredential, String str, zzacf zzacf) {
        this.zza = emailAuthCredential;
        this.zzb = str;
        this.zzc = zzacf;
        this.zzd = zzyl;
    }

    public final void zza(@Nullable String str) {
        this.zzc.zza(zzal.zza(str));
    }

    public final /* synthetic */ void zza(Object obj) {
        this.zzd.zza(new zzaeo(this.zza, ((zzafm) obj).zzc(), this.zzb), this.zzc);
    }
}
