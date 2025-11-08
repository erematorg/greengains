package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzkt  reason: invalid package */
final class zzkt implements zzle {
    public final int zza() {
        return 32;
    }

    public final int zzb() {
        return 12;
    }

    public final byte[] zzc() {
        return zzln.zzk;
    }

    public final byte[] zza(byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4) throws GeneralSecurityException {
        if (bArr.length != 32) {
            throw new InvalidAlgorithmParameterException("Unexpected key length: 32");
        } else if (zzhq.zza()) {
            return zzhq.zza(bArr).zza(bArr2, bArr3, bArr4);
        } else {
            return new zzho(bArr).zza(bArr2, bArr3, bArr4);
        }
    }
}
