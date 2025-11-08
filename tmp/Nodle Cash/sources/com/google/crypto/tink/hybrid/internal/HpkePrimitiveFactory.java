package com.google.crypto.tink.hybrid.internal;

import com.google.crypto.tink.proto.HpkeAead;
import com.google.crypto.tink.proto.HpkeKdf;
import com.google.crypto.tink.proto.HpkeKem;
import com.google.crypto.tink.proto.HpkeParams;
import com.google.crypto.tink.subtle.EllipticCurves;
import java.security.GeneralSecurityException;
import java.util.Arrays;

final class HpkePrimitiveFactory {
    private HpkePrimitiveFactory() {
    }

    public static HpkeAead createAead(byte[] bArr) throws GeneralSecurityException {
        if (Arrays.equals(bArr, HpkeUtil.AES_128_GCM_AEAD_ID)) {
            return new AesGcmHpkeAead(16);
        }
        if (Arrays.equals(bArr, HpkeUtil.AES_256_GCM_AEAD_ID)) {
            return new AesGcmHpkeAead(32);
        }
        if (Arrays.equals(bArr, HpkeUtil.CHACHA20_POLY1305_AEAD_ID)) {
            return new ChaCha20Poly1305HpkeAead();
        }
        throw new IllegalArgumentException("Unrecognized HPKE AEAD identifier");
    }

    public static HpkeKdf createKdf(byte[] bArr) {
        if (Arrays.equals(bArr, HpkeUtil.HKDF_SHA256_KDF_ID)) {
            return new HkdfHpkeKdf("HmacSha256");
        }
        if (Arrays.equals(bArr, HpkeUtil.HKDF_SHA384_KDF_ID)) {
            return new HkdfHpkeKdf("HmacSha384");
        }
        if (Arrays.equals(bArr, HpkeUtil.HKDF_SHA512_KDF_ID)) {
            return new HkdfHpkeKdf("HmacSha512");
        }
        throw new IllegalArgumentException("Unrecognized HPKE KDF identifier");
    }

    public static HpkeKem createKem(byte[] bArr) throws GeneralSecurityException {
        if (Arrays.equals(bArr, HpkeUtil.X25519_HKDF_SHA256_KEM_ID)) {
            return new X25519HpkeKem(new HkdfHpkeKdf("HmacSha256"));
        }
        if (Arrays.equals(bArr, HpkeUtil.P256_HKDF_SHA256_KEM_ID)) {
            return NistCurvesHpkeKem.fromCurve(EllipticCurves.CurveType.NIST_P256);
        }
        if (Arrays.equals(bArr, HpkeUtil.P384_HKDF_SHA384_KEM_ID)) {
            return NistCurvesHpkeKem.fromCurve(EllipticCurves.CurveType.NIST_P384);
        }
        if (Arrays.equals(bArr, HpkeUtil.P521_HKDF_SHA512_KEM_ID)) {
            return NistCurvesHpkeKem.fromCurve(EllipticCurves.CurveType.NIST_P521);
        }
        throw new IllegalArgumentException("Unrecognized HPKE KEM identifier");
    }

    public static HpkeAead createAead(HpkeParams hpkeParams) throws GeneralSecurityException {
        if (hpkeParams.getAead() == HpkeAead.AES_128_GCM) {
            return new AesGcmHpkeAead(16);
        }
        if (hpkeParams.getAead() == HpkeAead.AES_256_GCM) {
            return new AesGcmHpkeAead(32);
        }
        if (hpkeParams.getAead() == HpkeAead.CHACHA20_POLY1305) {
            return new ChaCha20Poly1305HpkeAead();
        }
        throw new IllegalArgumentException("Unrecognized HPKE AEAD identifier");
    }

    public static HpkeKdf createKdf(HpkeParams hpkeParams) {
        if (hpkeParams.getKdf() == HpkeKdf.HKDF_SHA256) {
            return new HkdfHpkeKdf("HmacSha256");
        }
        if (hpkeParams.getKdf() == HpkeKdf.HKDF_SHA384) {
            return new HkdfHpkeKdf("HmacSha384");
        }
        if (hpkeParams.getKdf() == HpkeKdf.HKDF_SHA512) {
            return new HkdfHpkeKdf("HmacSha512");
        }
        throw new IllegalArgumentException("Unrecognized HPKE KDF identifier");
    }

    public static HpkeKem createKem(HpkeParams hpkeParams) throws GeneralSecurityException {
        if (hpkeParams.getKem() == HpkeKem.DHKEM_X25519_HKDF_SHA256) {
            return new X25519HpkeKem(new HkdfHpkeKdf("HmacSha256"));
        }
        if (hpkeParams.getKem() == HpkeKem.DHKEM_P256_HKDF_SHA256) {
            return NistCurvesHpkeKem.fromCurve(EllipticCurves.CurveType.NIST_P256);
        }
        if (hpkeParams.getKem() == HpkeKem.DHKEM_P384_HKDF_SHA384) {
            return NistCurvesHpkeKem.fromCurve(EllipticCurves.CurveType.NIST_P384);
        }
        if (hpkeParams.getKem() == HpkeKem.DHKEM_P521_HKDF_SHA512) {
            return NistCurvesHpkeKem.fromCurve(EllipticCurves.CurveType.NIST_P521);
        }
        throw new IllegalArgumentException("Unrecognized HPKE KEM identifier");
    }
}
