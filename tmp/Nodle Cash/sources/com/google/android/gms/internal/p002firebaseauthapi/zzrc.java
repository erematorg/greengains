package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzig;
import java.security.GeneralSecurityException;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzrc  reason: invalid package */
public final class zzrc implements zzps {
    private static final zzig.zza zza = zzig.zza.ALGORITHM_REQUIRES_BORINGCRYPTO;
    private final zzpw zzb;

    public zzrc(zzpw zzpw) throws GeneralSecurityException {
        if (zza.zza()) {
            this.zzb = zzpw;
            return;
        }
        throw new GeneralSecurityException("Can not use HMAC in FIPS-mode, as BoringCrypto module is not available.");
    }
}
