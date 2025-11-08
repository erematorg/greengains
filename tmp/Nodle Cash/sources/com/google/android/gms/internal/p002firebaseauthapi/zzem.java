package com.google.android.gms.internal.p002firebaseauthapi;

import A.a;
import com.google.android.gms.internal.p002firebaseauthapi.zzeq;
import java.security.GeneralSecurityException;
import javax.annotation.Nullable;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzem  reason: invalid package */
public final class zzem extends zzcu {
    private final zzeq zza;
    private final zzxw zzb;
    private final zzxv zzc;
    @Nullable
    private final Integer zzd;

    private zzem(zzeq zzeq, zzxw zzxw, zzxv zzxv, @Nullable Integer num) {
        this.zza = zzeq;
        this.zzb = zzxw;
        this.zzc = zzxv;
        this.zzd = num;
    }

    public static zzem zza(zzeq.zza zza2, zzxw zzxw, @Nullable Integer num) throws GeneralSecurityException {
        zzxv zzxv;
        zzeq.zza zza3 = zzeq.zza.zzc;
        if (zza2 != zza3 && num == null) {
            throw new GeneralSecurityException(a.l("For given Variant ", String.valueOf(zza2), " the value of idRequirement must be non-null"));
        } else if (zza2 == zza3 && num != null) {
            throw new GeneralSecurityException("For given Variant NO_PREFIX the value of idRequirement must be null");
        } else if (zzxw.zza() == 32) {
            zzeq zza4 = zzeq.zza(zza2);
            if (zza4.zzb() == zza3) {
                zzxv = zznt.zza;
            } else if (zza4.zzb() == zzeq.zza.zzb) {
                zzxv = zznt.zza(num.intValue());
            } else if (zza4.zzb() == zzeq.zza.zza) {
                zzxv = zznt.zzb(num.intValue());
            } else {
                throw new IllegalStateException("Unknown Variant: ".concat(String.valueOf(zza4.zzb())));
            }
            return new zzem(zza4, zzxw, zzxv, num);
        } else {
            throw new GeneralSecurityException(a.k("ChaCha20Poly1305 key must be constructed with key of length 32 bytes, not ", zzxw.zza()));
        }
    }

    public final zzeq zzb() {
        return this.zza;
    }

    public final zzxv zzc() {
        return this.zzc;
    }

    public final zzxw zzd() {
        return this.zzb;
    }

    @Nullable
    public final Integer zza() {
        return this.zzd;
    }
}
