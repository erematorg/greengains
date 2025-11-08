package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzeq;
import com.google.android.gms.internal.p002firebaseauthapi.zzig;
import com.google.android.gms.internal.p002firebaseauthapi.zzuw;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.HashMap;
import javax.annotation.Nullable;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzep  reason: invalid package */
public final class zzep {
    private static final zzoe<zzem, zzbh> zza;
    private static final zznk<zzeq> zzb = new zzer();
    private static final zzbt<zzbh> zzc;

    static {
        Class<zzbh> cls = zzbh.class;
        zza = zzoe.zza(new zzeo(), zzem.class, cls);
        zzc = zzmy.zza("type.googleapis.com/google.crypto.tink.ChaCha20Poly1305Key", cls, zzuw.zzb.SYMMETRIC, zzte.zze());
    }

    public static /* synthetic */ zzbh zza(zzem zzem) {
        if (zzhd.zzb()) {
            return zzhd.zza(zzem);
        }
        return zzwg.zza(zzem);
    }

    public static zzem zza(zzeq zzeq, @Nullable Integer num) throws GeneralSecurityException {
        return zzem.zza(zzeq.zzb(), zzxw.zza(32), num);
    }

    public static String zza() {
        return "type.googleapis.com/google.crypto.tink.ChaCha20Poly1305Key";
    }

    public static void zza(boolean z2) throws GeneralSecurityException {
        if (zzig.zza.ALGORITHM_NOT_FIPS.zza()) {
            zzhf.zza();
            zznp.zza().zza(zza);
            zzni.zza().zza(zzb, zzeq.class);
            zznq zza2 = zznq.zza();
            HashMap hashMap = new HashMap();
            hashMap.put("CHACHA20_POLY1305", zzeq.zza(zzeq.zza.zza));
            hashMap.put("CHACHA20_POLY1305_RAW", zzeq.zza(zzeq.zza.zzc));
            zza2.zza(Collections.unmodifiableMap(hashMap));
            zzmn.zza().zza(zzc, true);
            return;
        }
        throw new GeneralSecurityException("Registering ChaCha20Poly1305 is not supported in FIPS mode");
    }
}
