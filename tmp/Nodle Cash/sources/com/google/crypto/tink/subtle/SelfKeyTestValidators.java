package com.google.crypto.tink.subtle;

import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.subtle.EllipticCurves;
import com.google.crypto.tink.subtle.Enums;
import java.security.GeneralSecurityException;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.security.interfaces.RSAPrivateCrtKey;
import java.security.interfaces.RSAPublicKey;

public final class SelfKeyTestValidators {
    private static final ByteString TEST_MESSAGE = ByteString.copyFromUtf8("Tink and Wycheproof.");

    private SelfKeyTestValidators() {
    }

    public static final void validateEcdsa(ECPrivateKey eCPrivateKey, ECPublicKey eCPublicKey, Enums.HashType hashType, EllipticCurves.EcdsaEncoding ecdsaEncoding) throws GeneralSecurityException {
        EcdsaSignJce ecdsaSignJce = new EcdsaSignJce(eCPrivateKey, hashType, ecdsaEncoding);
        EcdsaVerifyJce ecdsaVerifyJce = new EcdsaVerifyJce(eCPublicKey, hashType, ecdsaEncoding);
        try {
            ByteString byteString = TEST_MESSAGE;
            ecdsaVerifyJce.verify(ecdsaSignJce.sign(byteString.toByteArray()), byteString.toByteArray());
        } catch (GeneralSecurityException e3) {
            throw new GeneralSecurityException("ECDSA signing with private key followed by verifying with public key failed. The key may be corrupted.", e3);
        }
    }

    public static final void validateRsaSsaPkcs1(RSAPrivateCrtKey rSAPrivateCrtKey, RSAPublicKey rSAPublicKey, Enums.HashType hashType) throws GeneralSecurityException {
        RsaSsaPkcs1SignJce rsaSsaPkcs1SignJce = new RsaSsaPkcs1SignJce(rSAPrivateCrtKey, hashType);
        RsaSsaPkcs1VerifyJce rsaSsaPkcs1VerifyJce = new RsaSsaPkcs1VerifyJce(rSAPublicKey, hashType);
        try {
            ByteString byteString = TEST_MESSAGE;
            rsaSsaPkcs1VerifyJce.verify(rsaSsaPkcs1SignJce.sign(byteString.toByteArray()), byteString.toByteArray());
        } catch (GeneralSecurityException e3) {
            throw new GeneralSecurityException("RSA PKCS1 signing with private key followed by verifying with public key failed. The key may be corrupted.", e3);
        }
    }

    public static final void validateRsaSsaPss(RSAPrivateCrtKey rSAPrivateCrtKey, RSAPublicKey rSAPublicKey, Enums.HashType hashType, Enums.HashType hashType2, int i3) throws GeneralSecurityException {
        RsaSsaPssSignJce rsaSsaPssSignJce = new RsaSsaPssSignJce(rSAPrivateCrtKey, hashType, hashType2, i3);
        RsaSsaPssVerifyJce rsaSsaPssVerifyJce = new RsaSsaPssVerifyJce(rSAPublicKey, hashType, hashType2, i3);
        try {
            ByteString byteString = TEST_MESSAGE;
            rsaSsaPssVerifyJce.verify(rsaSsaPssSignJce.sign(byteString.toByteArray()), byteString.toByteArray());
        } catch (GeneralSecurityException e3) {
            throw new GeneralSecurityException("RSA PSS signing with private key followed by verifying with public key failed. The key may be corrupted.", e3);
        }
    }
}
