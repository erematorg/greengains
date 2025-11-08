package com.google.android.gms.internal.p002firebaseauthapi;

import android.text.TextUtils;
import androidx.annotation.Nullable;
import com.google.firebase.auth.internal.zzal;
import com.google.firebase.auth.zzf;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzzl  reason: invalid package */
final class zzzl implements zzadm<zzahc> {
    private final /* synthetic */ zzzm zza;

    public zzzl(zzzm zzzm) {
        this.zza = zzzm;
    }

    public final void zza(@Nullable String str) {
        this.zza.zza.zza(zzal.zza(str));
    }

    public final /* synthetic */ void zza(Object obj) {
        zzahc zzahc = (zzahc) obj;
        if (TextUtils.isEmpty(zzahc.zza()) || TextUtils.isEmpty(zzahc.zzb())) {
            this.zza.zza.zza(zzal.zza("INTERNAL_SUCCESS_SIGN_OUT"));
            return;
        }
        zzafm zzafm = new zzafm(zzahc.zzb(), zzahc.zza(), Long.valueOf(zzafo.zza(zzahc.zza())), "Bearer");
        zzzm zzzm = this.zza;
        zzzm.zzb.zza(zzafm, (String) null, (String) null, Boolean.FALSE, (zzf) null, zzzm.zza, this);
    }
}
