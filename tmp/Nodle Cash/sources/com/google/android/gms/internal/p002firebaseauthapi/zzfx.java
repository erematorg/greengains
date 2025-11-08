package com.google.android.gms.internal.p002firebaseauthapi;

import A.a;
import com.google.android.gms.internal.p002firebaseauthapi.zzgd;
import java.security.GeneralSecurityException;
import javax.annotation.Nullable;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzfx  reason: invalid package */
public final class zzfx extends zzcu {
    private final zzgd zza;
    private final zzxw zzb;
    private final zzxv zzc;
    @Nullable
    private final Integer zzd;

    private zzfx(zzgd zzgd, zzxw zzxw, zzxv zzxv, @Nullable Integer num) {
        this.zza = zzgd;
        this.zzb = zzxw;
        this.zzc = zzxv;
        this.zzd = num;
    }

    public static zzfx zza(zzgd.zza zza2, zzxw zzxw, @Nullable Integer num) throws GeneralSecurityException {
        zzxv zzxv;
        zzgd.zza zza3 = zzgd.zza.zzc;
        if (zza2 != zza3 && num == null) {
            throw new GeneralSecurityException(a.l("For given Variant ", String.valueOf(zza2), " the value of idRequirement must be non-null"));
        } else if (zza2 == zza3 && num != null) {
            throw new GeneralSecurityException("For given Variant NO_PREFIX the value of idRequirement must be null");
        } else if (zzxw.zza() == 32) {
            zzgd zza4 = zzgd.zza(zza2);
            if (zza4.zzb() == zza3) {
                zzxv = zznt.zza;
            } else if (zza4.zzb() == zzgd.zza.zzb) {
                zzxv = zznt.zza(num.intValue());
            } else if (zza4.zzb() == zzgd.zza.zza) {
                zzxv = zznt.zzb(num.intValue());
            } else {
                throw new IllegalStateException("Unknown Variant: ".concat(String.valueOf(zza4.zzb())));
            }
            return new zzfx(zza4, zzxw, zzxv, num);
        } else {
            throw new GeneralSecurityException(a.k("XChaCha20Poly1305 key must be constructed with key of length 32 bytes, not ", zzxw.zza()));
        }
    }

    public final zzgd zzb() {
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
