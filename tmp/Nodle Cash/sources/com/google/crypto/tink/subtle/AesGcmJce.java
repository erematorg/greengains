package com.google.crypto.tink.subtle;

import com.google.crypto.tink.Aead;
import com.google.crypto.tink.aead.internal.InsecureNonceAesGcmJce;
import com.google.crypto.tink.config.internal.TinkFipsUtil;
import java.security.GeneralSecurityException;
import java.util.Arrays;

public final class AesGcmJce implements Aead {
    public static final TinkFipsUtil.AlgorithmFipsCompatibility FIPS = TinkFipsUtil.AlgorithmFipsCompatibility.ALGORITHM_REQUIRES_BORINGCRYPTO;
    private final InsecureNonceAesGcmJce insecureNonceAesGcmJce;

    public AesGcmJce(byte[] bArr) throws GeneralSecurityException {
        if (FIPS.isCompatible()) {
            this.insecureNonceAesGcmJce = new InsecureNonceAesGcmJce(bArr, true);
            return;
        }
        throw new GeneralSecurityException("Can not use AES-GCM in FIPS-mode, as BoringCrypto module is not available.");
    }

    public byte[] decrypt(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        return this.insecureNonceAesGcmJce.decrypt(Arrays.copyOf(bArr, 12), bArr, bArr2);
    }

    public byte[] encrypt(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        return this.insecureNonceAesGcmJce.encrypt(Random.randBytes(12), bArr, bArr2);
    }
}
