package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzig;
import com.google.android.gms.internal.p002firebaseauthapi.zzuw;
import java.security.GeneralSecurityException;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzev  reason: invalid package */
public final class zzev {
    private static final zzbt<zzbh> zza;
    private static final zznk<zzfi> zzb = new zzey();
    private static final zzoe<zzfg, zzbh> zzc;

    static {
        Class<zzbh> cls = zzbh.class;
        zza = zzmy.zza("type.googleapis.com/google.crypto.tink.KmsEnvelopeAeadKey", cls, zzuw.zzb.SYMMETRIC, zzvo.zze());
        zzc = zzoe.zza(new zzex(), zzfg.class, cls);
    }

    public static void zza(boolean z2) throws GeneralSecurityException {
        if (zzig.zza.ALGORITHM_NOT_FIPS.zza()) {
            zzfm.zza();
            zzni.zza().zza(zzb, zzfi.class);
            zznp.zza().zza(zzc);
            zzmn.zza().zza(zza, true);
            return;
        }
        throw new GeneralSecurityException("Registering KMS Envelope AEAD is not supported in FIPS mode");
    }
}
