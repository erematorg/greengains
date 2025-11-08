package com.google.crypto.tink.subtle;

import com.google.crypto.tink.PublicKeySign;
import com.google.crypto.tink.config.internal.TinkFipsUtil;
import com.google.crypto.tink.subtle.EngineWrapper;
import com.google.crypto.tink.subtle.Enums;
import com.google.errorprone.annotations.Immutable;
import java.security.GeneralSecurityException;
import java.security.Signature;
import java.security.interfaces.RSAPrivateCrtKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.RSAPublicKeySpec;

@Immutable
public final class RsaSsaPkcs1SignJce implements PublicKeySign {
    public static final TinkFipsUtil.AlgorithmFipsCompatibility FIPS = TinkFipsUtil.AlgorithmFipsCompatibility.ALGORITHM_REQUIRES_BORINGCRYPTO;
    private final RSAPrivateCrtKey privateKey;
    private final RSAPublicKey publicKey;
    private final String signatureAlgorithm;

    public RsaSsaPkcs1SignJce(RSAPrivateCrtKey rSAPrivateCrtKey, Enums.HashType hashType) throws GeneralSecurityException {
        if (FIPS.isCompatible()) {
            Validators.validateSignatureHash(hashType);
            Validators.validateRsaModulusSize(rSAPrivateCrtKey.getModulus().bitLength());
            Validators.validateRsaPublicExponent(rSAPrivateCrtKey.getPublicExponent());
            this.privateKey = rSAPrivateCrtKey;
            this.signatureAlgorithm = SubtleUtil.toRsaSsaPkcs1Algo(hashType);
            this.publicKey = (RSAPublicKey) EngineFactory.KEY_FACTORY.getInstance("RSA").generatePublic(new RSAPublicKeySpec(rSAPrivateCrtKey.getModulus(), rSAPrivateCrtKey.getPublicExponent()));
            return;
        }
        throw new GeneralSecurityException("Can not use RSA PKCS1.5 in FIPS-mode, as BoringCrypto module is not available.");
    }

    public byte[] sign(byte[] bArr) throws GeneralSecurityException {
        EngineFactory<EngineWrapper.TSignature, Signature> engineFactory = EngineFactory.SIGNATURE;
        Signature instance = engineFactory.getInstance(this.signatureAlgorithm);
        instance.initSign(this.privateKey);
        instance.update(bArr);
        byte[] sign = instance.sign();
        Signature instance2 = engineFactory.getInstance(this.signatureAlgorithm);
        instance2.initVerify(this.publicKey);
        instance2.update(bArr);
        if (instance2.verify(sign)) {
            return sign;
        }
        throw new RuntimeException("Security bug: RSA signature computation error");
    }
}
