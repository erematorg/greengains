package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzig;
import com.google.android.gms.internal.p002firebaseauthapi.zzuw;
import java.security.GeneralSecurityException;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzes  reason: invalid package */
public final class zzes {
    private static final zzoe<zzfa, zzbh> zza;
    private static final zzbt<zzbh> zzb;
    private static final zznk<zzez> zzc = new zzet();

    static {
        Class<zzbh> cls = zzbh.class;
        zza = zzoe.zza(new zzeu(), zzfa.class, cls);
        zzb = zzmy.zza("type.googleapis.com/google.crypto.tink.KmsAeadKey", cls, zzuw.zzb.REMOTE, zzvk.zze());
    }

    public static void zza(boolean z2) throws GeneralSecurityException {
        if (zzig.zza.ALGORITHM_NOT_FIPS.zza()) {
            zzfb.zza();
            zznp.zza().zza(zza);
            zzni.zza().zza(zzc, zzez.class);
            zzmn.zza().zza(zzb, true);
            return;
        }
        throw new GeneralSecurityException("Registering KMS AEAD is not supported in FIPS mode");
    }
}
