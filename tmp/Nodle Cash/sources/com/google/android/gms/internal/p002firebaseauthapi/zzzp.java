package com.google.android.gms.internal.p002firebaseauthapi;

import androidx.annotation.Nullable;
import com.google.firebase.auth.internal.zzal;
import com.google.firebase.auth.zzf;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzzp  reason: invalid package */
final class zzzp implements zzadm<zzaet> {
    private final /* synthetic */ zzzq zza;

    public zzzp(zzzq zzzq) {
        this.zza = zzzq;
    }

    public final void zza(@Nullable String str) {
        this.zza.zza.zza(zzal.zza(str));
    }

    public final /* synthetic */ void zza(Object obj) {
        zzaet zzaet = (zzaet) obj;
        zzafm zzafm = new zzafm(zzaet.zzb(), zzaet.zza(), Long.valueOf(zzafo.zza(zzaet.zza())), "Bearer");
        zzzq zzzq = this.zza;
        zzzq.zzb.zza(zzafm, (String) null, (String) null, Boolean.FALSE, (zzf) null, zzzq.zza, this);
    }
}
