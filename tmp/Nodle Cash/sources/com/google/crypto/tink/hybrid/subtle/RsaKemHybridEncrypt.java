package com.google.crypto.tink.hybrid.subtle;

import com.google.crypto.tink.HybridEncrypt;
import com.google.crypto.tink.aead.subtle.AeadFactory;
import com.google.crypto.tink.subtle.Hkdf;
import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import java.security.interfaces.RSAPublicKey;
import javax.crypto.Cipher;

public final class RsaKemHybridEncrypt implements HybridEncrypt {
    private final AeadFactory aeadFactory;
    private final String hkdfHmacAlgo;
    private final byte[] hkdfSalt;
    private final RSAPublicKey recipientPublicKey;

    public RsaKemHybridEncrypt(RSAPublicKey rSAPublicKey, String str, byte[] bArr, AeadFactory aeadFactory2) throws GeneralSecurityException {
        RsaKem.validateRsaModulus(rSAPublicKey.getModulus());
        this.recipientPublicKey = rSAPublicKey;
        this.hkdfHmacAlgo = str;
        this.hkdfSalt = bArr;
        this.aeadFactory = aeadFactory2;
    }

    public byte[] encrypt(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        byte[] generateSecret = RsaKem.generateSecret(this.recipientPublicKey.getModulus());
        Cipher instance = Cipher.getInstance("RSA/ECB/NoPadding");
        instance.init(1, this.recipientPublicKey);
        byte[] doFinal = instance.doFinal(generateSecret);
        byte[] encrypt = this.aeadFactory.createAead(Hkdf.computeHkdf(this.hkdfHmacAlgo, generateSecret, this.hkdfSalt, bArr2, this.aeadFactory.getKeySizeInBytes())).encrypt(bArr, RsaKem.EMPTY_AAD);
        return ByteBuffer.allocate(doFinal.length + encrypt.length).put(doFinal).put(encrypt).array();
    }
}
