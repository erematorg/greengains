package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzdq;
import com.google.android.gms.internal.p002firebaseauthapi.zzig;
import com.google.android.gms.internal.p002firebaseauthapi.zzuw;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.HashMap;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzdm  reason: invalid package */
public final class zzdm {
    private static final zzoe<zzdj, zzbh> zza;
    private static final zzbt<zzbh> zzb;
    private static final zznk<zzdq> zzc = new zzdo();

    static {
        Class<zzbh> cls = zzbh.class;
        zza = zzoe.zza(new zzdp(), zzdj.class, cls);
        zzb = zzmy.zza("type.googleapis.com/google.crypto.tink.AesEaxKey", cls, zzuw.zzb.SYMMETRIC, zzsn.zzf());
    }

    public static /* synthetic */ zzdj zza(zzdq zzdq, Integer num) {
        if (zzdq.zzc() != 24) {
            return zzdj.zzb().zza(zzdq).zza(num).zza(zzxw.zza(zzdq.zzc())).zza();
        }
        throw new GeneralSecurityException("192 bit AES GCM Parameters are not valid");
    }

    public static String zza() {
        return "type.googleapis.com/google.crypto.tink.AesEaxKey";
    }

    public static void zza(boolean z2) throws GeneralSecurityException {
        if (zzig.zza.ALGORITHM_NOT_FIPS.zza()) {
            zzgl.zza();
            zznp.zza().zza(zza);
            zznq zza2 = zznq.zza();
            HashMap hashMap = new HashMap();
            hashMap.put("AES128_EAX", zzfs.zzc);
            zzdq.zza zzc2 = zzdq.zze().zza(16).zzb(16).zzc(16);
            zzdq.zzb zzb2 = zzdq.zzb.zzc;
            hashMap.put("AES128_EAX_RAW", zzc2.zza(zzb2).zza());
            hashMap.put("AES256_EAX", zzfs.zzd);
            hashMap.put("AES256_EAX_RAW", zzdq.zze().zza(16).zzb(32).zzc(16).zza(zzb2).zza());
            zza2.zza(Collections.unmodifiableMap(hashMap));
            zzni.zza().zza(zzc, zzdq.class);
            zzmn.zza().zza(zzb, true);
            return;
        }
        throw new GeneralSecurityException("Registering AES EAX is not supported in FIPS mode");
    }
}
