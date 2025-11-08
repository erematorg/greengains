package com.google.android.gms.internal.p002firebaseauthapi;

import A.a;
import androidx.annotation.Nullable;
import com.google.firebase.auth.internal.zzal;
import com.reown.foundation.util.jwt.JwtUtilsKt;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzzn  reason: invalid package */
final class zzzn implements zzadm<zzagi> {
    private final /* synthetic */ zzagj zza;
    private final /* synthetic */ zzacf zzb;

    public zzzn(zzyl zzyl, zzagj zzagj, zzacf zzacf) {
        this.zza = zzagj;
        this.zzb = zzacf;
    }

    public final void zza(@Nullable String str) {
        this.zzb.zza(zzal.zza(str));
    }

    public final /* synthetic */ void zza(Object obj) {
        zzagi zzagi = (zzagi) obj;
        zzagj zzagj = this.zza;
        if (zzagj instanceof zzagn) {
            this.zzb.zzb(zzagi.zza());
        } else if (zzagj instanceof zzagp) {
            this.zzb.zza(zzagi);
        } else {
            throw new IllegalArgumentException(a.l("startMfaEnrollmentRequest must be an instance of either StartPhoneMfaEnrollmentRequest or StartTotpMfaEnrollmentRequest but was ", this.zza.getClass().getName(), JwtUtilsKt.JWT_DELIMITER));
        }
    }
}
