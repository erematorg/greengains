package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzek;
import com.google.android.gms.internal.p002firebaseauthapi.zzig;
import com.google.android.gms.internal.p002firebaseauthapi.zzuw;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;
import java.util.HashMap;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzeh  reason: invalid package */
public final class zzeh {
    private static final zzoe<zzed, zzbh> zza;
    private static final zznk<zzek> zzb = new zzej();
    private static final zznm<zzek> zzc = new zzei();
    private static final zzbt<zzbh> zzd;

    static {
        Class<zzbh> cls = zzbh.class;
        zza = zzoe.zza(new zzeg(), zzed.class, cls);
        zzd = zzmy.zza("type.googleapis.com/google.crypto.tink.AesGcmSivKey", cls, zzuw.zzb.SYMMETRIC, zzsw.zze());
    }

    public static void zza(boolean z2) throws GeneralSecurityException {
        if (zzig.zza.ALGORITHM_NOT_FIPS.zza()) {
            zzgx.zza();
            if (zza()) {
                zznp.zza().zza(zza);
                zznq zza2 = zznq.zza();
                HashMap hashMap = new HashMap();
                zzek.zza zza3 = zzek.zzc().zza(16);
                zzek.zzb zzb2 = zzek.zzb.zza;
                hashMap.put("AES128_GCM_SIV", zza3.zza(zzb2).zza());
                zzek.zza zza4 = zzek.zzc().zza(16);
                zzek.zzb zzb3 = zzek.zzb.zzc;
                hashMap.put("AES128_GCM_SIV_RAW", zza4.zza(zzb3).zza());
                hashMap.put("AES256_GCM_SIV", zzek.zzc().zza(32).zza(zzb2).zza());
                hashMap.put("AES256_GCM_SIV_RAW", zzek.zzc().zza(32).zza(zzb3).zza());
                zza2.zza(Collections.unmodifiableMap(hashMap));
                Class<zzek> cls = zzek.class;
                zznj.zza().zza(zzc, cls);
                zzni.zza().zza(zzb, cls);
                zzmn.zza().zza(zzd, true);
                return;
            }
            return;
        }
        throw new GeneralSecurityException("Registering AES GCM SIV is not supported in FIPS mode");
    }

    private static boolean zza() {
        try {
            Cipher.getInstance("AES/GCM-SIV/NoPadding");
            return true;
        } catch (NoSuchAlgorithmException | NoSuchPaddingException unused) {
            return false;
        }
    }
}
