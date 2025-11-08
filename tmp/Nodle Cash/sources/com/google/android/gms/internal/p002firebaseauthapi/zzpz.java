package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzig;
import com.google.android.gms.internal.p002firebaseauthapi.zzqf;
import com.google.android.gms.internal.p002firebaseauthapi.zzuw;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.HashMap;
import javax.annotation.Nullable;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzpz  reason: invalid package */
public final class zzpz {
    private static final zzoe<zzpw, zzps> zza;
    private static final zzoe<zzpw, zzcf> zzb;
    private static final zzbt<zzcf> zzc;
    private static final zznm<zzqf> zzd = new zzqe();
    private static final zznk<zzqf> zze = new zzqd();
    private static final zzig.zza zzf = zzig.zza.ALGORITHM_REQUIRES_BORINGCRYPTO;

    static {
        Class<zzpw> cls = zzpw.class;
        zza = zzoe.zza(new zzqc(), cls, zzps.class);
        Class<zzcf> cls2 = zzcf.class;
        zzb = zzoe.zza(new zzqb(), cls, cls2);
        zzc = zzmy.zza("type.googleapis.com/google.crypto.tink.HmacKey", cls2, zzuw.zzb.SYMMETRIC, zzud.zzg());
    }

    public static zzpw zza(zzqf zzqf, @Nullable Integer num) throws GeneralSecurityException {
        return zzpw.zzb().zza(zzqf).zza(zzxw.zza(zzqf.zzc())).zza(num).zza();
    }

    public static void zza(boolean z2) throws GeneralSecurityException {
        zzig.zza zza2 = zzf;
        if (zza2.zza()) {
            zzrf.zza();
            zznp.zza().zza(zza);
            zznp.zza().zza(zzb);
            zznq zza3 = zznq.zza();
            HashMap hashMap = new HashMap();
            hashMap.put("HMAC_SHA256_128BITTAG", zzqo.zza);
            zzqf.zza zzb2 = zzqf.zzd().zza(32).zzb(16);
            zzqf.zzc zzc2 = zzqf.zzc.zzd;
            zzqf.zza zza4 = zzb2.zza(zzc2);
            zzqf.zzb zzb3 = zzqf.zzb.zzc;
            hashMap.put("HMAC_SHA256_128BITTAG_RAW", zza4.zza(zzb3).zza());
            zzqf.zza zzb4 = zzqf.zzd().zza(32).zzb(32);
            zzqf.zzc zzc3 = zzqf.zzc.zza;
            hashMap.put("HMAC_SHA256_256BITTAG", zzb4.zza(zzc3).zza(zzb3).zza());
            hashMap.put("HMAC_SHA256_256BITTAG_RAW", zzqf.zzd().zza(32).zzb(32).zza(zzc2).zza(zzb3).zza());
            zzqf.zza zza5 = zzqf.zzd().zza(64).zzb(16).zza(zzc3);
            zzqf.zzb zzb5 = zzqf.zzb.zze;
            hashMap.put("HMAC_SHA512_128BITTAG", zza5.zza(zzb5).zza());
            hashMap.put("HMAC_SHA512_128BITTAG_RAW", zzqf.zzd().zza(64).zzb(16).zza(zzc2).zza(zzb5).zza());
            hashMap.put("HMAC_SHA512_256BITTAG", zzqf.zzd().zza(64).zzb(32).zza(zzc3).zza(zzb5).zza());
            hashMap.put("HMAC_SHA512_256BITTAG_RAW", zzqf.zzd().zza(64).zzb(32).zza(zzc2).zza(zzb5).zza());
            hashMap.put("HMAC_SHA512_512BITTAG", zzqo.zzb);
            hashMap.put("HMAC_SHA512_512BITTAG_RAW", zzqf.zzd().zza(64).zzb(64).zza(zzc2).zza(zzb5).zza());
            zza3.zza(Collections.unmodifiableMap(hashMap));
            Class<zzqf> cls = zzqf.class;
            zzni.zza().zza(zze, cls);
            zznj.zza().zza(zzd, cls);
            zzmn.zza().zza(zzc, zza2, true);
            return;
        }
        throw new GeneralSecurityException("Can not use HMAC in FIPS-mode, as BoringCrypto module is not available.");
    }
}
