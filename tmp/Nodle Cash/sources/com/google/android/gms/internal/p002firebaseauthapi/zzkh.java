package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzkh  reason: invalid package */
public final class zzkh {
    private static final String zza = "type.googleapis.com/google.crypto.tink.EciesAeadHkdfPublicKey";
    private static final String zzb = "type.googleapis.com/google.crypto.tink.EciesAeadHkdfPrivateKey";
    @Deprecated
    private static final zzvu zzc = zzvu.zzb();
    @Deprecated
    private static final zzvu zzd = zzvu.zzb();
    @Deprecated
    private static final zzvu zze = zzvu.zzb();

    static {
        try {
            zza();
        } catch (GeneralSecurityException e3) {
            throw new ExceptionInInitializerError(e3);
        }
    }

    public static void zza() throws GeneralSecurityException {
        zzkk.zzc();
        zzkl.zzc();
        zzcr.zza();
        zzix.zza();
        if (!zzig.zzb()) {
            zzjk.zza(true);
            zzlj.zza(true);
        }
    }
}
