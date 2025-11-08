package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzix  reason: invalid package */
public final class zzix {
    private static final String zza = "type.googleapis.com/google.crypto.tink.AesSivKey";
    @Deprecated
    private static final zzvu zzb = zzvu.zzb();
    @Deprecated
    private static final zzvu zzc = zzvu.zzb();

    static {
        try {
            zza();
        } catch (GeneralSecurityException e3) {
            throw new ExceptionInInitializerError(e3);
        }
    }

    public static void zza() throws GeneralSecurityException {
        zziy.zzc();
        if (!zzig.zzb()) {
            zzio.zza(true);
        }
    }
}
