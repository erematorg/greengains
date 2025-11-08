package com.google.crypto.tink.aead.subtle;

import androidx.compose.animation.core.a;
import com.google.crypto.tink.Aead;
import com.google.crypto.tink.subtle.AesGcmJce;
import com.google.errorprone.annotations.Immutable;
import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;

@Immutable
public final class AesGcmFactory implements AeadFactory {
    private final int keySizeInBytes;

    public AesGcmFactory(int i3) throws GeneralSecurityException {
        this.keySizeInBytes = validateAesKeySize(i3);
    }

    private static int validateAesKeySize(int i3) throws InvalidAlgorithmParameterException {
        if (i3 == 16 || i3 == 32) {
            return i3;
        }
        throw new InvalidAlgorithmParameterException(String.format("Invalid AES key size, expected 16 or 32, but got %d", new Object[]{Integer.valueOf(i3)}));
    }

    public Aead createAead(byte[] bArr) throws GeneralSecurityException {
        if (bArr.length == getKeySizeInBytes()) {
            return new AesGcmJce(bArr);
        }
        throw new GeneralSecurityException(a.r("Symmetric key has incorrect length; expected ", getKeySizeInBytes(), ", but got ", bArr.length));
    }

    public int getKeySizeInBytes() {
        return this.keySizeInBytes;
    }
}
