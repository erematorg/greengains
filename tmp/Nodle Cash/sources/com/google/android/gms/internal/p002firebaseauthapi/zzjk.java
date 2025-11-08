package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzdg;
import com.google.android.gms.internal.p002firebaseauthapi.zzdz;
import com.google.android.gms.internal.p002firebaseauthapi.zzig;
import com.google.android.gms.internal.p002firebaseauthapi.zzjo;
import com.google.android.gms.internal.p002firebaseauthapi.zzuw;
import java.security.GeneralSecurityException;
import java.security.KeyPair;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.security.spec.ECParameterSpec;
import java.util.Collections;
import java.util.HashMap;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzjk  reason: invalid package */
public final class zzjk {
    private static final zzoe<zzju, zzbp> zza;
    private static final zzoe<zzjt, zzbs> zzb;
    private static final zzcj<zzbp> zzc;
    private static final zzbt<zzbs> zzd;
    private static final zznk<zzjo> zze = new zzjp();

    static {
        Class<zzbp> cls = zzbp.class;
        zza = zzoe.zza(new zzjn(), zzju.class, cls);
        Class<zzbs> cls2 = zzbs.class;
        zzb = zzoe.zza(new zzjm(), zzjt.class, cls2);
        zzc = zzmy.zza("type.googleapis.com/google.crypto.tink.EciesAeadHkdfPrivateKey", cls, zztr.zzf());
        zzd = zzmy.zza("type.googleapis.com/google.crypto.tink.EciesAeadHkdfPublicKey", cls2, zzuw.zzb.ASYMMETRIC_PUBLIC, zzts.b_());
    }

    public static /* synthetic */ zzju zza(zzjo zzjo, Integer num) {
        ECParameterSpec eCParameterSpec;
        zzjo.zzb zzd2 = zzjo.zzd();
        if (zzd2 == zzjo.zzb.zza) {
            eCParameterSpec = zzmf.zza;
        } else if (zzd2 == zzjo.zzb.zzb) {
            eCParameterSpec = zzmf.zzb;
        } else if (zzd2 == zzjo.zzb.zzc) {
            eCParameterSpec = zzmf.zzc;
        } else {
            throw new GeneralSecurityException("Unsupported curve type: ".concat(String.valueOf(zzd2)));
        }
        KeyPair zza2 = zzwr.zza(eCParameterSpec);
        return zzju.zza(zzjt.zza(zzjo, ((ECPublicKey) zza2.getPublic()).getW(), num), zzxu.zza(((ECPrivateKey) zza2.getPrivate()).getS(), zzbr.zza()));
    }

    public static String zza() {
        return "type.googleapis.com/google.crypto.tink.EciesAeadHkdfPrivateKey";
    }

    public static void zza(boolean z2) throws GeneralSecurityException {
        if (zzig.zza.ALGORITHM_NOT_FIPS.zza()) {
            zzkw.zza();
            zznq zza2 = zznq.zza();
            HashMap hashMap = new HashMap();
            zzjo.zza zzc2 = zzjo.zzc();
            zzjo.zzb zzb2 = zzjo.zzb.zza;
            zzjo.zza zza3 = zzc2.zza(zzb2);
            zzjo.zzd zzd2 = zzjo.zzd.zzc;
            zzjo.zza zza4 = zza3.zza(zzd2);
            zzjo.zzc zzc3 = zzjo.zzc.zzb;
            zzjo.zza zza5 = zza4.zza(zzc3);
            zzjo.zze zze2 = zzjo.zze.zza;
            zzjo.zza zza6 = zza5.zza(zze2);
            zzdz.zza zzc4 = zzdz.zze().zza(12).zzb(16).zzc(16);
            zzdz.zzb zzb3 = zzdz.zzb.zzc;
            hashMap.put("ECIES_P256_HKDF_HMAC_SHA256_AES128_GCM", zza6.zza((zzci) zzc4.zza(zzb3).zza()).zza());
            zzjo.zza zza7 = zzjo.zzc().zza(zzb2).zza(zzd2).zza(zzc3);
            zzjo.zze zze3 = zzjo.zze.zzc;
            hashMap.put("ECIES_P256_HKDF_HMAC_SHA256_AES128_GCM_RAW", zza7.zza(zze3).zza((zzci) zzdz.zze().zza(12).zzb(16).zzc(16).zza(zzb3).zza()).zza());
            zzjo.zza zza8 = zzjo.zzc().zza(zzb2).zza(zzd2);
            zzjo.zzc zzc5 = zzjo.zzc.zza;
            hashMap.put("ECIES_P256_COMPRESSED_HKDF_HMAC_SHA256_AES128_GCM", zza8.zza(zzc5).zza(zze2).zza((zzci) zzdz.zze().zza(12).zzb(16).zzc(16).zza(zzb3).zza()).zza());
            hashMap.put("ECIES_P256_COMPRESSED_HKDF_HMAC_SHA256_AES128_GCM_RAW", zzjo.zzc().zza(zzb2).zza(zzd2).zza(zzc5).zza(zze3).zza((zzci) zzdz.zze().zza(12).zzb(16).zzc(16).zza(zzb3).zza()).zza());
            hashMap.put("ECIES_P256_HKDF_HMAC_SHA256_AES128_GCM_COMPRESSED_WITHOUT_PREFIX", zzjo.zzc().zza(zzb2).zza(zzd2).zza(zzc5).zza(zze3).zza((zzci) zzdz.zze().zza(12).zzb(16).zzc(16).zza(zzb3).zza()).zza());
            zzjo.zza zza9 = zzjo.zzc().zza(zzb2).zza(zzd2).zza(zzc3).zza(zze2);
            zzdg.zza zzc6 = zzdg.zzf().zza(16).zzb(32).zzd(16).zzc(16);
            zzdg.zzc zzc7 = zzdg.zzc.zzc;
            zzdg.zza zza10 = zzc6.zza(zzc7);
            zzdg.zzb zzb4 = zzdg.zzb.zzc;
            hashMap.put("ECIES_P256_HKDF_HMAC_SHA256_AES128_CTR_HMAC_SHA256", zza9.zza((zzci) zza10.zza(zzb4).zza()).zza());
            hashMap.put("ECIES_P256_HKDF_HMAC_SHA256_AES128_CTR_HMAC_SHA256_RAW", zzjo.zzc().zza(zzb2).zza(zzd2).zza(zzc3).zza(zze3).zza((zzci) zzdg.zzf().zza(16).zzb(32).zzd(16).zzc(16).zza(zzc7).zza(zzb4).zza()).zza());
            hashMap.put("ECIES_P256_COMPRESSED_HKDF_HMAC_SHA256_AES128_CTR_HMAC_SHA256", zzjo.zzc().zza(zzb2).zza(zzd2).zza(zzc5).zza(zze2).zza((zzci) zzdg.zzf().zza(16).zzb(32).zzd(16).zzc(16).zza(zzc7).zza(zzb4).zza()).zza());
            hashMap.put("ECIES_P256_COMPRESSED_HKDF_HMAC_SHA256_AES128_CTR_HMAC_SHA256_RAW", zzjo.zzc().zza(zzb2).zza(zzd2).zza(zzc5).zza(zze3).zza((zzci) zzdg.zzf().zza(16).zzb(32).zzd(16).zzc(16).zza(zzc7).zza(zzb4).zza()).zza());
            zza2.zza(Collections.unmodifiableMap(hashMap));
            zznp.zza().zza(zza);
            zznp.zza().zza(zzb);
            zzni.zza().zza(zze, zzjo.class);
            zzmn.zza().zza(zzc, true);
            zzmn.zza().zza(zzd, false);
            return;
        }
        throw new GeneralSecurityException("Registering ECIES Hybrid Encryption is not supported in FIPS mode");
    }
}
