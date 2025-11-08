package com.google.crypto.tink.subtle;

import com.google.crypto.tink.PublicKeyVerify;
import com.google.crypto.tink.config.internal.TinkFipsUtil;
import com.google.crypto.tink.util.Bytes;
import java.security.GeneralSecurityException;

public final class Ed25519Verify implements PublicKeyVerify {
    public static final TinkFipsUtil.AlgorithmFipsCompatibility FIPS = TinkFipsUtil.AlgorithmFipsCompatibility.ALGORITHM_NOT_FIPS;
    public static final int PUBLIC_KEY_LEN = 32;
    public static final int SIGNATURE_LEN = 64;
    private final Bytes publicKey;

    public Ed25519Verify(byte[] bArr) {
        if (!FIPS.isCompatible()) {
            throw new IllegalStateException(new GeneralSecurityException("Can not use Ed25519 in FIPS-mode."));
        } else if (bArr.length == 32) {
            this.publicKey = Bytes.copyFrom(bArr);
        } else {
            throw new IllegalArgumentException("Given public key's length is not 32.");
        }
    }

    public void verify(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        if (bArr.length != 64) {
            throw new GeneralSecurityException("The length of the signature is not 64.");
        } else if (!Ed25519.verify(bArr2, bArr, this.publicKey.toByteArray())) {
            throw new GeneralSecurityException("Signature check failed.");
        }
    }
}
