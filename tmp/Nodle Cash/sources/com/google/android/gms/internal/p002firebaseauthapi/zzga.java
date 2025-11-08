package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzgd;
import com.google.android.gms.internal.p002firebaseauthapi.zzig;
import com.google.android.gms.internal.p002firebaseauthapi.zzuw;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.HashMap;
import javax.annotation.Nullable;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzga  reason: invalid package */
public final class zzga {
    private static final zzoe<zzfx, zzbh> zza;
    private static final zzbt<zzbh> zzb;
    private static final zznm<zzgd> zzc = new zzgc();
    private static final zznk<zzgd> zzd = new zzgb();

    static {
        Class<zzbh> cls = zzbh.class;
        zza = zzoe.zza(new zzfz(), zzfx.class, cls);
        zzb = zzmy.zza("type.googleapis.com/google.crypto.tink.XChaCha20Poly1305Key", cls, zzuw.zzb.SYMMETRIC, zzvv.zze());
    }

    public static /* synthetic */ zzbh zza(zzfx zzfx) {
        if (zzhw.zza()) {
            return zzhw.zza(zzfx);
        }
        return zzxs.zza(zzfx);
    }

    public static zzfx zza(zzgd zzgd, @Nullable Integer num) throws GeneralSecurityException {
        return zzfx.zza(zzgd.zzb(), zzxw.zza(32), num);
    }

    public static String zza() {
        return "type.googleapis.com/google.crypto.tink.XChaCha20Poly1305Key";
    }

    public static void zza(boolean z2) throws GeneralSecurityException {
        if (zzig.zza.ALGORITHM_NOT_FIPS.zza()) {
            zzhz.zza();
            zznp.zza().zza(zza);
            zznq zza2 = zznq.zza();
            HashMap hashMap = new HashMap();
            hashMap.put("XCHACHA20_POLY1305", zzgd.zza(zzgd.zza.zza));
            hashMap.put("XCHACHA20_POLY1305_RAW", zzgd.zza(zzgd.zza.zzc));
            zza2.zza(Collections.unmodifiableMap(hashMap));
            Class<zzgd> cls = zzgd.class;
            zzni.zza().zza(zzd, cls);
            zznj.zza().zza(zzc, cls);
            zzmn.zza().zza(zzb, true);
            return;
        }
        throw new GeneralSecurityException("Registering XChaCha20Poly1305 is not supported in FIPS mode");
    }
}
