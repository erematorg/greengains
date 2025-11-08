package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;
import java.util.Arrays;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzlv  reason: invalid package */
final class zzlv implements zzlh {
    private final zzlb zza;

    public zzlv(zzlb zzlb) {
        this.zza = zzlb;
    }

    public final byte[] zza(byte[] bArr, zzlk zzlk) throws GeneralSecurityException {
        byte[] zza2 = zzxt.zza(zzlk.zza().zzb(), bArr);
        byte[] zza3 = zzwh.zza(bArr, zzlk.zzb().zzb());
        byte[] zza4 = zzln.zza(zzln.zzb);
        zzlb zzlb = this.zza;
        return zzlb.zza((byte[]) null, zza2, "eae_prk", zza3, "shared_secret", zza4, zzlb.zza());
    }

    public final byte[] zza() throws GeneralSecurityException {
        if (Arrays.equals(this.zza.zzb(), zzln.zzf)) {
            return zzln.zzb;
        }
        throw new GeneralSecurityException("Could not determine HPKE KEM ID");
    }
}
