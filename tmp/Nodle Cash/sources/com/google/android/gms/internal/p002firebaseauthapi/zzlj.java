package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzig;
import com.google.android.gms.internal.p002firebaseauthapi.zzjv;
import com.google.android.gms.internal.p002firebaseauthapi.zzuw;
import java.security.GeneralSecurityException;
import java.security.KeyPair;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.security.spec.ECPoint;
import java.security.spec.EllipticCurve;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import okio.Utf8;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzlj  reason: invalid package */
public final class zzlj {
    private static final zzoe<zzka, zzbp> zza;
    private static final zzoe<zzki, zzbs> zzb;
    private static final zzcj<zzbp> zzc;
    private static final zzbt<zzbs> zzd;
    private static final zznk<zzjv> zze = new zzlo();

    static {
        Class<zzbp> cls = zzbp.class;
        zza = zzoe.zza(new zzlm(), zzka.class, cls);
        Class<zzbs> cls2 = zzbs.class;
        zzb = zzoe.zza(new zzll(), zzki.class, cls2);
        zzc = zzmy.zza("type.googleapis.com/google.crypto.tink.HpkePrivateKey", cls, zzus.zzf());
        zzd = zzmy.zza("type.googleapis.com/google.crypto.tink.HpkePublicKey", cls2, zzuw.zzb.ASYMMETRIC_PUBLIC, zzuv.zzg());
    }

    public static /* synthetic */ zzka zza(zzjv zzjv, Integer num) {
        zzxw zzxw;
        zzxv zzxv;
        byte[] bArr;
        if (zzjv.zze().equals(zzjv.zzd.zzd)) {
            byte[] zza2 = zzow.zza(32);
            zza2[0] = (byte) (zza2[0] | 7);
            byte b3 = (byte) (zza2[31] & Utf8.REPLACEMENT_BYTE);
            zza2[31] = b3;
            zza2[31] = (byte) (b3 | 128);
            zzxw = zzxw.zza(zza2, zzbr.zza());
            zzxv = zzxv.zza(zzxt.zza(zza2));
        } else if (zzjv.zze().equals(zzjv.zzd.zza) || zzjv.zze().equals(zzjv.zzd.zzb) || zzjv.zze().equals(zzjv.zzd.zzc)) {
            zzwq zzc2 = zzln.zzc(zzjv.zze());
            KeyPair zza3 = zzwr.zza(zzwr.zza(zzc2));
            zzwt zzwt = zzwt.UNCOMPRESSED;
            ECPoint w2 = ((ECPublicKey) zza3.getPublic()).getW();
            EllipticCurve curve = zzwr.zza(zzc2).getCurve();
            zzmf.zza(w2, curve);
            int zza4 = zzwr.zza(curve);
            int ordinal = zzwt.ordinal();
            if (ordinal != 0) {
                int i3 = 2;
                if (ordinal == 1) {
                    int i4 = zza4 + 1;
                    bArr = new byte[i4];
                    byte[] zza5 = zzmd.zza(w2.getAffineX());
                    System.arraycopy(zza5, 0, bArr, i4 - zza5.length, zza5.length);
                    if (w2.getAffineY().testBit(0)) {
                        i3 = 3;
                    }
                    bArr[0] = (byte) i3;
                } else if (ordinal == 2) {
                    int i5 = zza4 * 2;
                    bArr = new byte[i5];
                    byte[] zza6 = zzmd.zza(w2.getAffineX());
                    if (zza6.length > zza4) {
                        zza6 = Arrays.copyOfRange(zza6, zza6.length - zza4, zza6.length);
                    }
                    byte[] zza7 = zzmd.zza(w2.getAffineY());
                    if (zza7.length > zza4) {
                        zza7 = Arrays.copyOfRange(zza7, zza7.length - zza4, zza7.length);
                    }
                    System.arraycopy(zza7, 0, bArr, i5 - zza7.length, zza7.length);
                    System.arraycopy(zza6, 0, bArr, zza4 - zza6.length, zza6.length);
                } else {
                    throw new GeneralSecurityException("invalid format:".concat(String.valueOf(zzwt)));
                }
            } else {
                int i6 = (zza4 * 2) + 1;
                bArr = new byte[i6];
                byte[] zza8 = zzmd.zza(w2.getAffineX());
                byte[] zza9 = zzmd.zza(w2.getAffineY());
                System.arraycopy(zza9, 0, bArr, i6 - zza9.length, zza9.length);
                System.arraycopy(zza8, 0, bArr, (zza4 + 1) - zza8.length, zza8.length);
                bArr[0] = 4;
            }
            zzxv = zzxv.zza(bArr);
            zzxw = zzxw.zza(zzmd.zza(((ECPrivateKey) zza3.getPrivate()).getS(), zzln.zza(zzjv.zze())), zzbr.zza());
        } else {
            throw new GeneralSecurityException("Unknown KEM ID");
        }
        return zzka.zza(zzki.zza(zzjv, zzxv, num), zzxw);
    }

    public static void zza(boolean z2) throws GeneralSecurityException {
        if (zzig.zza.ALGORITHM_NOT_FIPS.zza()) {
            zzjz.zza();
            zznq zza2 = zznq.zza();
            HashMap hashMap = new HashMap();
            zzjv.zzc zzc2 = zzjv.zzc();
            zzjv.zzf zzf = zzjv.zzf.zza;
            zzjv.zzc zza3 = zzc2.zza(zzf);
            zzjv.zzd zzd2 = zzjv.zzd.zzd;
            zzjv.zzc zza4 = zza3.zza(zzd2);
            zzjv.zze zze2 = zzjv.zze.zza;
            zzjv.zzc zza5 = zza4.zza(zze2);
            zzjv.zza zza6 = zzjv.zza.zza;
            hashMap.put("DHKEM_X25519_HKDF_SHA256_HKDF_SHA256_AES_128_GCM", zza5.zza(zza6).zza());
            zzjv.zzc zzc3 = zzjv.zzc();
            zzjv.zzf zzf2 = zzjv.zzf.zzc;
            hashMap.put("DHKEM_X25519_HKDF_SHA256_HKDF_SHA256_AES_128_GCM_RAW", zzc3.zza(zzf2).zza(zzd2).zza(zze2).zza(zza6).zza());
            zzjv.zzc zza7 = zzjv.zzc().zza(zzf).zza(zzd2).zza(zze2);
            zzjv.zza zza8 = zzjv.zza.zzb;
            hashMap.put("DHKEM_X25519_HKDF_SHA256_HKDF_SHA256_AES_256_GCM", zza7.zza(zza8).zza());
            hashMap.put("DHKEM_X25519_HKDF_SHA256_HKDF_SHA256_AES_256_GCM_RAW", zzjv.zzc().zza(zzf2).zza(zzd2).zza(zze2).zza(zza8).zza());
            zzjv.zzc zza9 = zzjv.zzc().zza(zzf).zza(zzd2).zza(zze2);
            zzjv.zza zza10 = zzjv.zza.zzc;
            hashMap.put("DHKEM_X25519_HKDF_SHA256_HKDF_SHA256_CHACHA20_POLY1305", zza9.zza(zza10).zza());
            hashMap.put("DHKEM_X25519_HKDF_SHA256_HKDF_SHA256_CHACHA20_POLY1305_RAW", zzjv.zzc().zza(zzf2).zza(zzd2).zza(zze2).zza(zza10).zza());
            zzjv.zzc zza11 = zzjv.zzc().zza(zzf);
            zzjv.zzd zzd3 = zzjv.zzd.zza;
            hashMap.put("DHKEM_P256_HKDF_SHA256_HKDF_SHA256_AES_128_GCM", zza11.zza(zzd3).zza(zze2).zza(zza6).zza());
            hashMap.put("DHKEM_P256_HKDF_SHA256_HKDF_SHA256_AES_128_GCM_RAW", zzjv.zzc().zza(zzf2).zza(zzd3).zza(zze2).zza(zza6).zza());
            hashMap.put("DHKEM_P256_HKDF_SHA256_HKDF_SHA256_AES_256_GCM", zzjv.zzc().zza(zzf).zza(zzd3).zza(zze2).zza(zza8).zza());
            hashMap.put("DHKEM_P256_HKDF_SHA256_HKDF_SHA256_AES_256_GCM_RAW", zzjv.zzc().zza(zzf2).zza(zzd3).zza(zze2).zza(zza8).zza());
            zzjv.zzc zza12 = zzjv.zzc().zza(zzf);
            zzjv.zzd zzd4 = zzjv.zzd.zzb;
            zzjv.zzc zza13 = zza12.zza(zzd4);
            zzjv.zze zze3 = zzjv.zze.zzb;
            hashMap.put("DHKEM_P384_HKDF_SHA384_HKDF_SHA384_AES_128_GCM", zza13.zza(zze3).zza(zza6).zza());
            hashMap.put("DHKEM_P384_HKDF_SHA384_HKDF_SHA384_AES_128_GCM_RAW", zzjv.zzc().zza(zzf2).zza(zzd4).zza(zze3).zza(zza6).zza());
            hashMap.put("DHKEM_P384_HKDF_SHA384_HKDF_SHA384_AES_256_GCM", zzjv.zzc().zza(zzf).zza(zzd4).zza(zze3).zza(zza8).zza());
            hashMap.put("DHKEM_P384_HKDF_SHA384_HKDF_SHA384_AES_256_GCM_RAW", zzjv.zzc().zza(zzf2).zza(zzd4).zza(zze3).zza(zza8).zza());
            zzjv.zzc zza14 = zzjv.zzc().zza(zzf);
            zzjv.zzd zzd5 = zzjv.zzd.zzc;
            zzjv.zzc zza15 = zza14.zza(zzd5);
            zzjv.zze zze4 = zzjv.zze.zzc;
            hashMap.put("DHKEM_P521_HKDF_SHA512_HKDF_SHA512_AES_128_GCM", zza15.zza(zze4).zza(zza6).zza());
            hashMap.put("DHKEM_P521_HKDF_SHA512_HKDF_SHA512_AES_128_GCM_RAW", zzjv.zzc().zza(zzf2).zza(zzd5).zza(zze4).zza(zza6).zza());
            hashMap.put("DHKEM_P521_HKDF_SHA512_HKDF_SHA512_AES_256_GCM", zzjv.zzc().zza(zzf).zza(zzd5).zza(zze4).zza(zza8).zza());
            hashMap.put("DHKEM_P521_HKDF_SHA512_HKDF_SHA512_AES_256_GCM_RAW", zzjv.zzc().zza(zzf2).zza(zzd5).zza(zze4).zza(zza8).zza());
            zza2.zza(Collections.unmodifiableMap(hashMap));
            zznp.zza().zza(zza);
            zznp.zza().zza(zzb);
            zzni.zza().zza(zze, zzjv.class);
            zzmn.zza().zza(zzc, true);
            zzmn.zza().zza(zzd, false);
            return;
        }
        throw new GeneralSecurityException("Registering HPKE Hybrid Encryption is not supported in FIPS mode");
    }
}
