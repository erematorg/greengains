package com.google.crypto.tink.aead.internal;

import com.google.crypto.tink.config.internal.TinkFipsUtil;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import javax.crypto.AEADBadTagException;

abstract class InsecureNonceChaCha20Poly1305Base {
    public static final TinkFipsUtil.AlgorithmFipsCompatibility FIPS = TinkFipsUtil.AlgorithmFipsCompatibility.ALGORITHM_NOT_FIPS;
    private final InsecureNonceChaCha20Base chacha20;
    private final InsecureNonceChaCha20Base macKeyChaCha20;

    public InsecureNonceChaCha20Poly1305Base(byte[] bArr) throws GeneralSecurityException {
        if (FIPS.isCompatible()) {
            this.chacha20 = newChaCha20Instance(bArr, 1);
            this.macKeyChaCha20 = newChaCha20Instance(bArr, 0);
            return;
        }
        throw new GeneralSecurityException("Can not use ChaCha20Poly1305 in FIPS-mode.");
    }

    private byte[] getMacKey(byte[] bArr) throws GeneralSecurityException {
        ByteBuffer chacha20Block = this.macKeyChaCha20.chacha20Block(bArr, 0);
        byte[] bArr2 = new byte[32];
        chacha20Block.get(bArr2);
        return bArr2;
    }

    private static byte[] macDataRfc8439(byte[] bArr, ByteBuffer byteBuffer) {
        int length = bArr.length % 16 == 0 ? bArr.length : (bArr.length + 16) - (bArr.length % 16);
        int remaining = byteBuffer.remaining();
        int i3 = remaining % 16;
        int i4 = (i3 == 0 ? remaining : (remaining + 16) - i3) + length;
        ByteBuffer order = ByteBuffer.allocate(i4 + 16).order(ByteOrder.LITTLE_ENDIAN);
        order.put(bArr);
        order.position(length);
        order.put(byteBuffer);
        order.position(i4);
        order.putLong((long) bArr.length);
        order.putLong((long) remaining);
        return order.array();
    }

    public byte[] decrypt(byte[] bArr, byte[] bArr2, byte[] bArr3) throws GeneralSecurityException {
        return decrypt(ByteBuffer.wrap(bArr2), bArr, bArr3);
    }

    public byte[] encrypt(byte[] bArr, byte[] bArr2, byte[] bArr3) throws GeneralSecurityException {
        if (bArr2.length <= 2147483631) {
            ByteBuffer allocate = ByteBuffer.allocate(bArr2.length + 16);
            encrypt(allocate, bArr, bArr2, bArr3);
            return allocate.array();
        }
        throw new GeneralSecurityException("plaintext too long");
    }

    public abstract InsecureNonceChaCha20Base newChaCha20Instance(byte[] bArr, int i3) throws InvalidKeyException;

    public byte[] decrypt(ByteBuffer byteBuffer, byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        if (byteBuffer.remaining() >= 16) {
            int position = byteBuffer.position();
            byte[] bArr3 = new byte[16];
            byteBuffer.position(byteBuffer.limit() - 16);
            byteBuffer.get(bArr3);
            byteBuffer.position(position);
            byteBuffer.limit(byteBuffer.limit() - 16);
            if (bArr2 == null) {
                bArr2 = new byte[0];
            }
            try {
                Poly1305.verifyMac(getMacKey(bArr), macDataRfc8439(bArr2, byteBuffer), bArr3);
                byteBuffer.position(position);
                return this.chacha20.decrypt(bArr, byteBuffer);
            } catch (GeneralSecurityException e3) {
                throw new AEADBadTagException(e3.toString());
            }
        } else {
            throw new GeneralSecurityException("ciphertext too short");
        }
    }

    public void encrypt(ByteBuffer byteBuffer, byte[] bArr, byte[] bArr2, byte[] bArr3) throws GeneralSecurityException {
        if (byteBuffer.remaining() >= bArr2.length + 16) {
            int position = byteBuffer.position();
            this.chacha20.encrypt(byteBuffer, bArr, bArr2);
            byteBuffer.position(position);
            byteBuffer.limit(byteBuffer.limit() - 16);
            if (bArr3 == null) {
                bArr3 = new byte[0];
            }
            byte[] computeMac = Poly1305.computeMac(getMacKey(bArr), macDataRfc8439(bArr3, byteBuffer));
            byteBuffer.limit(byteBuffer.limit() + 16);
            byteBuffer.put(computeMac);
            return;
        }
        throw new IllegalArgumentException("Given ByteBuffer output is too small");
    }
}
