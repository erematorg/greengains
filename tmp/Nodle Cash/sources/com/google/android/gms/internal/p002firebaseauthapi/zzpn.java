package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzig;
import com.google.android.gms.internal.p002firebaseauthapi.zzpq;
import com.google.android.gms.internal.p002firebaseauthapi.zzuw;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.HashMap;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzpn  reason: invalid package */
public final class zzpn {
    private static final zznk<zzpq> zza = new zzpm();
    private static final zzoe<zzpj, zzps> zzb;
    private static final zzoe<zzpj, zzcf> zzc;
    private static final zzbt<zzcf> zzd;

    static {
        Class<zzpj> cls = zzpj.class;
        zzb = zzoe.zza(new zzpp(), cls, zzps.class);
        Class<zzcf> cls2 = zzcf.class;
        zzc = zzoe.zza(new zzpo(), cls, cls2);
        zzd = zzmy.zza("type.googleapis.com/google.crypto.tink.AesCmacKey", cls2, zzuw.zzb.SYMMETRIC, zzrx.zzf());
    }

    public static void zza(boolean z2) throws GeneralSecurityException {
        if (zzig.zza.ALGORITHM_NOT_FIPS.zza()) {
            zzqu.zza();
            zzni.zza().zza(zza, zzpq.class);
            zznp.zza().zza(zzb);
            zznp.zza().zza(zzc);
            zznq zza2 = zznq.zza();
            HashMap hashMap = new HashMap();
            zzpq zzpq = zzqo.zzc;
            hashMap.put("AES_CMAC", zzpq);
            hashMap.put("AES256_CMAC", zzpq);
            hashMap.put("AES256_CMAC_RAW", zzpq.zzd().zza(32).zzb(16).zza(zzpq.zzb.zzd).zza());
            zza2.zza(Collections.unmodifiableMap(hashMap));
            zzmn.zza().zza(zzd, true);
            return;
        }
        throw new GeneralSecurityException("Registering AES CMAC is not supported in FIPS mode");
    }

    /* access modifiers changed from: private */
    public static void zza(zzpq zzpq) throws GeneralSecurityException {
        if (zzpq.zzc() != 32) {
            throw new GeneralSecurityException("AesCmacKey size wrong, must be 32 bytes");
        }
    }
}
