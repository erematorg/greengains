package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzlu  reason: invalid package */
final class zzlu implements zzlh {
    private final zzwq zza;
    private final zzlb zzb;

    private zzlu(zzlb zzlb, zzwq zzwq) {
        this.zzb = zzlb;
        this.zza = zzwq;
    }

    public static zzlu zza(zzwq zzwq) throws GeneralSecurityException {
        int i3 = zzlt.zza[zzwq.ordinal()];
        if (i3 == 1) {
            return new zzlu(new zzlb("HmacSha256"), zzwq.NIST_P256);
        }
        if (i3 == 2) {
            return new zzlu(new zzlb("HmacSha384"), zzwq.NIST_P384);
        }
        if (i3 == 3) {
            return new zzlu(new zzlb("HmacSha512"), zzwq.NIST_P521);
        }
        throw new GeneralSecurityException("invalid curve type: ".concat(String.valueOf(zzwq)));
    }

    public final byte[] zza(byte[] bArr, zzlk zzlk) throws GeneralSecurityException {
        byte[] zza2 = zzwr.zza(zzwr.zza(this.zza, zzlk.zza().zzb()), zzwr.zza(this.zza, zzwt.UNCOMPRESSED, bArr));
        byte[] zza3 = zzwh.zza(bArr, zzlk.zzb().zzb());
        byte[] zza4 = zzln.zza(zza());
        zzlb zzlb = this.zzb;
        return zzlb.zza((byte[]) null, zza2, "eae_prk", zza3, "shared_secret", zza4, zzlb.zza());
    }

    public final byte[] zza() throws GeneralSecurityException {
        int i3 = zzlt.zza[this.zza.ordinal()];
        if (i3 == 1) {
            return zzln.zzc;
        }
        if (i3 == 2) {
            return zzln.zzd;
        }
        if (i3 == 3) {
            return zzln.zze;
        }
        throw new GeneralSecurityException("Could not determine HPKE KEM ID");
    }
}
