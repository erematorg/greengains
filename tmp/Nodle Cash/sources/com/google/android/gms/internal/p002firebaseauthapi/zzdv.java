package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzdz;
import com.google.android.gms.internal.p002firebaseauthapi.zzig;
import com.google.android.gms.internal.p002firebaseauthapi.zzuw;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.HashMap;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzdv  reason: invalid package */
public final class zzdv {
    private static final zzoe<zzds, zzbh> zza;
    private static final zzbt<zzbh> zzb;
    private static final zznm<zzdz> zzc = new zzdx();
    private static final zznk<zzdz> zzd = new zzea();
    private static final zzig.zza zze = zzig.zza.ALGORITHM_REQUIRES_BORINGCRYPTO;

    static {
        Class<zzbh> cls = zzbh.class;
        zza = zzoe.zza(new zzdy(), zzds.class, cls);
        zzb = zzmy.zza("type.googleapis.com/google.crypto.tink.AesGcmKey", cls, zzuw.zzb.SYMMETRIC, zzss.zze());
    }

    public static /* synthetic */ zzds zza(zzdz zzdz, Integer num) {
        if (zzdz.zzc() != 24) {
            return zzds.zzb().zza(zzdz).zza(num).zza(zzxw.zza(zzdz.zzc())).zza();
        }
        throw new GeneralSecurityException("192 bit AES GCM Parameters are not valid");
    }

    public static String zza() {
        return "type.googleapis.com/google.crypto.tink.AesGcmKey";
    }

    public static void zza(boolean z2) throws GeneralSecurityException {
        zzig.zza zza2 = zze;
        if (zza2.zza()) {
            zzgr.zza();
            zznp.zza().zza(zza);
            zznq zza3 = zznq.zza();
            HashMap hashMap = new HashMap();
            hashMap.put("AES128_GCM", zzfs.zza);
            zzdz.zza zzc2 = zzdz.zze().zza(12).zzb(16).zzc(16);
            zzdz.zzb zzb2 = zzdz.zzb.zzc;
            hashMap.put("AES128_GCM_RAW", zzc2.zza(zzb2).zza());
            hashMap.put("AES256_GCM", zzfs.zzb);
            hashMap.put("AES256_GCM_RAW", zzdz.zze().zza(12).zzb(32).zzc(16).zza(zzb2).zza());
            zza3.zza(Collections.unmodifiableMap(hashMap));
            Class<zzdz> cls = zzdz.class;
            zznj.zza().zza(zzc, cls);
            zzni.zza().zza(zzd, cls);
            zzmn.zza().zza(zzb, zza2, true);
            return;
        }
        throw new GeneralSecurityException("Can not use AES-GCM in FIPS-mode, as BoringCrypto module is not available.");
    }
}
