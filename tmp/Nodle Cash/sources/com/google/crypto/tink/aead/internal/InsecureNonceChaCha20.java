package com.google.crypto.tink.aead.internal;

import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;

public class InsecureNonceChaCha20 extends InsecureNonceChaCha20Base {
    public InsecureNonceChaCha20(byte[] bArr, int i3) throws InvalidKeyException {
        super(bArr, i3);
    }

    public int[] createInitialState(int[] iArr, int i3) {
        if (iArr.length == nonceSizeInBytes() / 4) {
            int[] iArr2 = new int[16];
            ChaCha20Util.setSigmaAndKey(iArr2, this.key);
            iArr2[12] = i3;
            System.arraycopy(iArr, 0, iArr2, 13, iArr.length);
            return iArr2;
        }
        throw new IllegalArgumentException(String.format("ChaCha20 uses 96-bit nonces, but got a %d-bit nonce", new Object[]{Integer.valueOf(iArr.length * 32)}));
    }

    public /* bridge */ /* synthetic */ byte[] decrypt(byte[] bArr, ByteBuffer byteBuffer) throws GeneralSecurityException {
        return super.decrypt(bArr, byteBuffer);
    }

    public /* bridge */ /* synthetic */ void encrypt(ByteBuffer byteBuffer, byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        super.encrypt(byteBuffer, bArr, bArr2);
    }

    public int nonceSizeInBytes() {
        return 12;
    }

    public /* bridge */ /* synthetic */ byte[] decrypt(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        return super.decrypt(bArr, bArr2);
    }

    public /* bridge */ /* synthetic */ byte[] encrypt(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        return super.encrypt(bArr, bArr2);
    }
}
