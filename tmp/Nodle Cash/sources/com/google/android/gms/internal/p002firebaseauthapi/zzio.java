package com.google.android.gms.internal.p002firebaseauthapi;

import androidx.camera.camera2.internal.C0118y;
import com.google.android.gms.internal.p002firebaseauthapi.zzig;
import com.google.android.gms.internal.p002firebaseauthapi.zzis;
import com.google.android.gms.internal.p002firebaseauthapi.zzuw;
import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.util.Collections;
import java.util.HashMap;
import javax.annotation.Nullable;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzio  reason: invalid package */
public final class zzio {
    private static final zzoe<zzil, zzbq> zza;
    private static final zzbt<zzbq> zzb;
    private static final zznm<zzis> zzc = new zziq();
    private static final zznk<zzis> zzd = new zzit();

    static {
        Class<zzbq> cls = zzbq.class;
        zza = zzoe.zza(new zzir(), zzil.class, cls);
        zzb = zzmy.zza("type.googleapis.com/google.crypto.tink.AesSivKey", cls, zzuw.zzb.SYMMETRIC, zzta.zze());
    }

    public static zzil zza(zzis zzis, @Nullable Integer num) throws GeneralSecurityException {
        zza(zzis);
        return zzil.zzb().zza(zzis).zza(num).zza(zzxw.zza(zzis.zzb())).zza();
    }

    public static void zza(boolean z2) throws GeneralSecurityException {
        if (zzig.zza.ALGORITHM_NOT_FIPS.zza()) {
            zzjf.zza();
            zznp.zza().zza(zza);
            zznq zza2 = zznq.zza();
            HashMap hashMap = new HashMap();
            hashMap.put("AES256_SIV", zzjd.zza);
            hashMap.put("AES256_SIV_RAW", zzis.zzc().zza(64).zza(zzis.zzb.zzc).zza());
            zza2.zza(Collections.unmodifiableMap(hashMap));
            Class<zzis> cls = zzis.class;
            zznj.zza().zza(zzc, cls);
            zzni.zza().zza(zzd, cls);
            zzmn.zza().zza(zzb, true);
            return;
        }
        throw new GeneralSecurityException("Registering AES SIV is not supported in FIPS mode");
    }

    /* access modifiers changed from: private */
    public static void zza(zzis zzis) throws GeneralSecurityException {
        if (zzis.zzb() != 64) {
            throw new InvalidAlgorithmParameterException(C0118y.c(zzis.zzb(), "invalid key size: ", ". Valid keys must have 64 bytes."));
        }
    }
}
