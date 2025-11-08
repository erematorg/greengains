package com.google.crypto.tink.hybrid.subtle;

import com.google.crypto.tink.Aead;
import com.google.crypto.tink.HybridDecrypt;
import com.google.crypto.tink.aead.subtle.AeadFactory;
import com.google.crypto.tink.subtle.Hkdf;
import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import java.security.interfaces.RSAPrivateKey;
import javax.crypto.Cipher;

public final class RsaKemHybridDecrypt implements HybridDecrypt {
    private final AeadFactory aeadFactory;
    private final String hkdfHmacAlgo;
    private final byte[] hkdfSalt;
    private final RSAPrivateKey recipientPrivateKey;

    public RsaKemHybridDecrypt(RSAPrivateKey rSAPrivateKey, String str, byte[] bArr, AeadFactory aeadFactory2) throws GeneralSecurityException {
        RsaKem.validateRsaModulus(rSAPrivateKey.getModulus());
        this.recipientPrivateKey = rSAPrivateKey;
        this.hkdfSalt = bArr;
        this.hkdfHmacAlgo = str;
        this.aeadFactory = aeadFactory2;
    }

    public byte[] decrypt(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        int bigIntSizeInBytes = RsaKem.bigIntSizeInBytes(this.recipientPrivateKey.getModulus());
        if (bArr.length >= bigIntSizeInBytes) {
            ByteBuffer wrap = ByteBuffer.wrap(bArr);
            byte[] bArr3 = new byte[bigIntSizeInBytes];
            wrap.get(bArr3);
            Cipher instance = Cipher.getInstance("RSA/ECB/NoPadding");
            instance.init(2, this.recipientPrivateKey);
            Aead createAead = this.aeadFactory.createAead(Hkdf.computeHkdf(this.hkdfHmacAlgo, instance.doFinal(bArr3), this.hkdfSalt, bArr2, this.aeadFactory.getKeySizeInBytes()));
            byte[] bArr4 = new byte[wrap.remaining()];
            wrap.get(bArr4);
            return createAead.decrypt(bArr4, RsaKem.EMPTY_AAD);
        }
        throw new GeneralSecurityException(String.format("Ciphertext must be of at least size %d bytes, but got %d", new Object[]{Integer.valueOf(bigIntSizeInBytes), Integer.valueOf(bArr.length)}));
    }
}
