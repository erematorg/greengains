package com.google.crypto.tink.hybrid.internal;

import com.google.crypto.tink.subtle.Bytes;
import com.google.crypto.tink.subtle.X25519;
import com.google.errorprone.annotations.Immutable;
import java.security.GeneralSecurityException;
import java.util.Arrays;

@Immutable
final class X25519HpkeKem implements HpkeKem {
    private final HkdfHpkeKdf hkdf;

    public X25519HpkeKem(HkdfHpkeKdf hkdfHpkeKdf) {
        this.hkdf = hkdfHpkeKdf;
    }

    private byte[] deriveKemSharedSecret(byte[] bArr, byte[] bArr2, byte[] bArr3) throws GeneralSecurityException {
        byte[] concat = Bytes.concat(bArr2, bArr3);
        byte[] kemSuiteId = HpkeUtil.kemSuiteId(HpkeUtil.X25519_HKDF_SHA256_KEM_ID);
        HkdfHpkeKdf hkdfHpkeKdf = this.hkdf;
        return hkdfHpkeKdf.extractAndExpand((byte[]) null, bArr, "eae_prk", concat, "shared_secret", kemSuiteId, hkdfHpkeKdf.getMacLength());
    }

    public byte[] decapsulate(byte[] bArr, HpkeKemPrivateKey hpkeKemPrivateKey) throws GeneralSecurityException {
        return deriveKemSharedSecret(X25519.computeSharedSecret(hpkeKemPrivateKey.getSerializedPrivate().toByteArray(), bArr), bArr, hpkeKemPrivateKey.getSerializedPublic().toByteArray());
    }

    public HpkeKemEncapOutput encapsulate(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        byte[] computeSharedSecret = X25519.computeSharedSecret(bArr2, bArr);
        byte[] publicFromPrivate = X25519.publicFromPrivate(bArr2);
        return new HpkeKemEncapOutput(deriveKemSharedSecret(computeSharedSecret, publicFromPrivate, bArr), publicFromPrivate);
    }

    public byte[] getKemId() throws GeneralSecurityException {
        if (Arrays.equals(this.hkdf.getKdfId(), HpkeUtil.HKDF_SHA256_KDF_ID)) {
            return HpkeUtil.X25519_HKDF_SHA256_KEM_ID;
        }
        throw new GeneralSecurityException("Could not determine HPKE KEM ID");
    }

    public HpkeKemEncapOutput encapsulate(byte[] bArr) throws GeneralSecurityException {
        return encapsulate(bArr, X25519.generatePrivateKey());
    }
}
