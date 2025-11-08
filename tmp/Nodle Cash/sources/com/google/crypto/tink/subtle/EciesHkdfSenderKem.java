package com.google.crypto.tink.subtle;

import com.google.crypto.tink.subtle.EllipticCurves;
import com.google.crypto.tink.util.Bytes;
import java.security.GeneralSecurityException;
import java.security.KeyPair;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;

public final class EciesHkdfSenderKem {
    private final ECPublicKey recipientPublicKey;

    public static final class KemKey {
        private final Bytes kemBytes;
        private final Bytes symmetricKey;

        public KemKey(byte[] bArr, byte[] bArr2) {
            if (bArr == null) {
                throw new NullPointerException("KemBytes must be non-null");
            } else if (bArr2 != null) {
                this.kemBytes = Bytes.copyFrom(bArr);
                this.symmetricKey = Bytes.copyFrom(bArr2);
            } else {
                throw new NullPointerException("symmetricKey must be non-null");
            }
        }

        public byte[] getKemBytes() {
            return this.kemBytes.toByteArray();
        }

        public byte[] getSymmetricKey() {
            return this.symmetricKey.toByteArray();
        }
    }

    public EciesHkdfSenderKem(ECPublicKey eCPublicKey) {
        this.recipientPublicKey = eCPublicKey;
    }

    public KemKey generateKey(String str, byte[] bArr, byte[] bArr2, int i3, EllipticCurves.PointFormatType pointFormatType) throws GeneralSecurityException {
        KeyPair generateKeyPair = EllipticCurves.generateKeyPair(this.recipientPublicKey.getParams());
        ECPublicKey eCPublicKey = (ECPublicKey) generateKeyPair.getPublic();
        byte[] computeSharedSecret = EllipticCurves.computeSharedSecret((ECPrivateKey) generateKeyPair.getPrivate(), this.recipientPublicKey);
        byte[] pointEncode = EllipticCurves.pointEncode(eCPublicKey.getParams().getCurve(), pointFormatType, eCPublicKey.getW());
        return new KemKey(pointEncode, Hkdf.computeEciesHkdfSymmetricKey(pointEncode, computeSharedSecret, str, bArr, bArr2, i3));
    }
}
