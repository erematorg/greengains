package com.google.android.gms.internal.p002firebaseauthapi;

import A.a;
import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzku  reason: invalid package */
final class zzku implements zzle {
    private final int zza;

    public zzku(int i3) throws InvalidAlgorithmParameterException {
        if (i3 == 16 || i3 == 32) {
            this.zza = i3;
            return;
        }
        throw new InvalidAlgorithmParameterException(a.k("Unsupported key length: ", i3));
    }

    public final int zza() {
        return this.zza;
    }

    public final int zzb() {
        return 12;
    }

    public final byte[] zzc() throws GeneralSecurityException {
        int i3 = this.zza;
        if (i3 == 16) {
            return zzln.zzi;
        }
        if (i3 == 32) {
            return zzln.zzj;
        }
        throw new GeneralSecurityException("Could not determine HPKE AEAD ID");
    }

    public final byte[] zza(byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4) throws GeneralSecurityException {
        if (bArr.length == this.zza) {
            return new zzhk(bArr).zza(bArr2, bArr3, bArr4);
        }
        throw new InvalidAlgorithmParameterException(a.k("Unexpected key length: ", bArr.length));
    }
}
