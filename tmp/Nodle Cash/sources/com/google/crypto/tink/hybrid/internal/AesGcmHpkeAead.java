package com.google.crypto.tink.hybrid.internal;

import A.a;
import com.google.crypto.tink.aead.internal.InsecureNonceAesGcmJce;
import com.google.errorprone.annotations.Immutable;
import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;

@Immutable
final class AesGcmHpkeAead implements HpkeAead {
    private final int keyLength;

    public AesGcmHpkeAead(int i3) throws InvalidAlgorithmParameterException {
        if (i3 == 16 || i3 == 32) {
            this.keyLength = i3;
            return;
        }
        throw new InvalidAlgorithmParameterException(a.k("Unsupported key length: ", i3));
    }

    public byte[] getAeadId() throws GeneralSecurityException {
        int i3 = this.keyLength;
        if (i3 == 16) {
            return HpkeUtil.AES_128_GCM_AEAD_ID;
        }
        if (i3 == 32) {
            return HpkeUtil.AES_256_GCM_AEAD_ID;
        }
        throw new GeneralSecurityException("Could not determine HPKE AEAD ID");
    }

    public int getKeyLength() {
        return this.keyLength;
    }

    public int getNonceLength() {
        return 12;
    }

    public byte[] open(byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4) throws GeneralSecurityException {
        if (bArr.length == this.keyLength) {
            return new InsecureNonceAesGcmJce(bArr, false).decrypt(bArr2, bArr3, bArr4);
        }
        throw new InvalidAlgorithmParameterException("Unexpected key length: " + bArr.length);
    }

    public byte[] seal(byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4) throws GeneralSecurityException {
        if (bArr.length == this.keyLength) {
            return new InsecureNonceAesGcmJce(bArr, false).encrypt(bArr2, bArr3, bArr4);
        }
        throw new InvalidAlgorithmParameterException("Unexpected key length: " + bArr.length);
    }
}
