package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzcr  reason: invalid package */
public final class zzcr {
    private static final String zza = "type.googleapis.com/google.crypto.tink.AesCtrHmacAeadKey";
    private static final String zzb = "type.googleapis.com/google.crypto.tink.AesGcmKey";
    private static final String zzc = "type.googleapis.com/google.crypto.tink.AesGcmSivKey";
    private static final String zzd = "type.googleapis.com/google.crypto.tink.AesEaxKey";
    private static final String zze = "type.googleapis.com/google.crypto.tink.KmsAeadKey";
    private static final String zzf = "type.googleapis.com/google.crypto.tink.KmsEnvelopeAeadKey";
    private static final String zzg = "type.googleapis.com/google.crypto.tink.ChaCha20Poly1305Key";
    private static final String zzh = "type.googleapis.com/google.crypto.tink.XChaCha20Poly1305Key";
    @Deprecated
    private static final zzvu zzi;
    @Deprecated
    private static final zzvu zzj;
    @Deprecated
    private static final zzvu zzk;

    static {
        zzvu zzb2 = zzvu.zzb();
        zzi = zzb2;
        zzj = zzb2;
        zzk = zzb2;
        try {
            zza();
        } catch (GeneralSecurityException e3) {
            throw new ExceptionInInitializerError(e3);
        }
    }

    public static void zza() throws GeneralSecurityException {
        zzcv.zzc();
        zzqj.zza();
        zzdc.zza(true);
        zzdv.zza(true);
        if (!zzig.zzb()) {
            zzdm.zza(true);
            zzeh.zza(true);
            zzep.zza(true);
            zzes.zza(true);
            zzev.zza(true);
            zzga.zza(true);
        }
    }
}
