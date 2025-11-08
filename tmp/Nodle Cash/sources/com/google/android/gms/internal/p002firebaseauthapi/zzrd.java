package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzig;
import java.security.GeneralSecurityException;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzrd  reason: invalid package */
public final class zzrd implements zzps {
    private static final zzig.zza zza = zzig.zza.ALGORITHM_NOT_FIPS;
    private final zzpj zzb;

    public zzrd(zzpj zzpj) throws GeneralSecurityException {
        if (zza.zza()) {
            this.zzb = zzpj;
            return;
        }
        throw new GeneralSecurityException("Can not use AES-CMAC in FIPS-mode.");
    }
}
