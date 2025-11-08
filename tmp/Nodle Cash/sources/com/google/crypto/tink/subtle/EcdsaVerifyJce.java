package com.google.crypto.tink.subtle;

import com.google.android.gms.security.ProviderInstaller;
import com.google.crypto.tink.PublicKeyVerify;
import com.google.crypto.tink.config.internal.TinkFipsUtil;
import com.google.crypto.tink.subtle.EllipticCurves;
import com.google.crypto.tink.subtle.Enums;
import com.google.errorprone.annotations.Immutable;
import java.security.GeneralSecurityException;
import java.security.Signature;
import java.security.interfaces.ECPublicKey;

@Immutable
public final class EcdsaVerifyJce implements PublicKeyVerify {
    public static final TinkFipsUtil.AlgorithmFipsCompatibility FIPS = TinkFipsUtil.AlgorithmFipsCompatibility.ALGORITHM_REQUIRES_BORINGCRYPTO;
    private final EllipticCurves.EcdsaEncoding encoding;
    private final ECPublicKey publicKey;
    private final String signatureAlgorithm;

    public EcdsaVerifyJce(ECPublicKey eCPublicKey, Enums.HashType hashType, EllipticCurves.EcdsaEncoding ecdsaEncoding) throws GeneralSecurityException {
        if (FIPS.isCompatible()) {
            EllipticCurves.checkPublicKey(eCPublicKey);
            this.signatureAlgorithm = SubtleUtil.toEcdsaAlgo(hashType);
            this.publicKey = eCPublicKey;
            this.encoding = ecdsaEncoding;
            return;
        }
        throw new GeneralSecurityException("Can not use ECDSA in FIPS-mode, as BoringCrypto is not available.");
    }

    public void verify(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        boolean z2;
        if (this.encoding == EllipticCurves.EcdsaEncoding.IEEE_P1363) {
            if (bArr.length == EllipticCurves.fieldSizeInBytes(this.publicKey.getParams().getCurve()) * 2) {
                bArr = EllipticCurves.ecdsaIeee2Der(bArr);
            } else {
                throw new GeneralSecurityException("Invalid signature");
            }
        }
        if (EllipticCurves.isValidDerEncoding(bArr)) {
            Signature instance = EngineFactory.SIGNATURE.getInstance(this.signatureAlgorithm, EngineFactory.toProviderList(ProviderInstaller.PROVIDER_NAME, "AndroidOpenSSL", "Conscrypt"));
            instance.initVerify(this.publicKey);
            instance.update(bArr2);
            try {
                z2 = instance.verify(bArr);
            } catch (RuntimeException unused) {
                z2 = false;
            }
            if (!z2) {
                throw new GeneralSecurityException("Invalid signature");
            }
            return;
        }
        throw new GeneralSecurityException("Invalid signature");
    }
}
