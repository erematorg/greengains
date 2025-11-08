package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzdg;
import com.google.android.gms.internal.p002firebaseauthapi.zzig;
import com.google.android.gms.internal.p002firebaseauthapi.zzuw;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.HashMap;
import javax.annotation.Nullable;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzdc  reason: invalid package */
public final class zzdc {
    private static final zzoe<zzcz, zzbh> zza;
    private static final zzbt<zzbh> zzb;
    private static final zznm<zzdg> zzc = new zzde();
    private static final zznk<zzdg> zzd = new zzdh();
    private static final zzig.zza zze = zzig.zza.ALGORITHM_REQUIRES_BORINGCRYPTO;

    static {
        Class<zzbh> cls = zzbh.class;
        zza = zzoe.zza(new zzdf(), zzcz.class, cls);
        zzb = zzmy.zza("type.googleapis.com/google.crypto.tink.AesCtrHmacAeadKey", cls, zzuw.zzb.SYMMETRIC, zzsc.zzf());
    }

    public static zzcz zza(zzdg zzdg, @Nullable Integer num) throws GeneralSecurityException {
        if (zzdg.zzb() == 16 || zzdg.zzb() == 32) {
            return zzcz.zzb().zza(zzdg).zza(num).zza(zzxw.zza(zzdg.zzb())).zzb(zzxw.zza(zzdg.zzc())).zza();
        }
        throw new GeneralSecurityException("AES key size must be 16 or 32 bytes");
    }

    public static String zza() {
        return "type.googleapis.com/google.crypto.tink.AesCtrHmacAeadKey";
    }

    public static void zza(boolean z2) throws GeneralSecurityException {
        zzig.zza zza2 = zze;
        if (zza2.zza()) {
            zzgf.zza();
            zznp.zza().zza(zza);
            zznq zza3 = zznq.zza();
            HashMap hashMap = new HashMap();
            hashMap.put("AES128_CTR_HMAC_SHA256", zzfs.zze);
            zzdg.zza zzc2 = zzdg.zzf().zza(16).zzb(32).zzd(16).zzc(16);
            zzdg.zzc zzc3 = zzdg.zzc.zzc;
            zzdg.zza zza4 = zzc2.zza(zzc3);
            zzdg.zzb zzb2 = zzdg.zzb.zzc;
            hashMap.put("AES128_CTR_HMAC_SHA256_RAW", zza4.zza(zzb2).zza());
            hashMap.put("AES256_CTR_HMAC_SHA256", zzfs.zzf);
            hashMap.put("AES256_CTR_HMAC_SHA256_RAW", zzdg.zzf().zza(32).zzb(32).zzd(32).zzc(16).zza(zzc3).zza(zzb2).zza());
            zza3.zza(Collections.unmodifiableMap(hashMap));
            Class<zzdg> cls = zzdg.class;
            zznj.zza().zza(zzc, cls);
            zzni.zza().zza(zzd, cls);
            zzmn.zza().zza(zzb, zza2, true);
            return;
        }
        throw new GeneralSecurityException("Can not use AES-CTR-HMAC in FIPS-mode, as BoringCrypto module is not available.");
    }
}
