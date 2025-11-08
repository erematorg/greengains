package com.google.crypto.tink.aead.internal;

import com.google.crypto.tink.subtle.Bytes;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;

abstract class InsecureNonceChaCha20Base {
    private final int initialCounter;
    int[] key;

    public InsecureNonceChaCha20Base(byte[] bArr, int i3) throws InvalidKeyException {
        if (bArr.length == 32) {
            this.key = ChaCha20Util.toIntArray(bArr);
            this.initialCounter = i3;
            return;
        }
        throw new InvalidKeyException("The key length in bytes must be 32.");
    }

    private void process(byte[] bArr, ByteBuffer byteBuffer, ByteBuffer byteBuffer2) throws GeneralSecurityException {
        if (bArr.length == nonceSizeInBytes()) {
            int remaining = byteBuffer2.remaining();
            int i3 = remaining / 64;
            int i4 = i3 + 1;
            for (int i5 = 0; i5 < i4; i5++) {
                ByteBuffer chacha20Block = chacha20Block(bArr, this.initialCounter + i5);
                if (i5 == i3) {
                    Bytes.xor(byteBuffer, byteBuffer2, chacha20Block, remaining % 64);
                } else {
                    Bytes.xor(byteBuffer, byteBuffer2, chacha20Block, 64);
                }
            }
            return;
        }
        throw new GeneralSecurityException("The nonce length (in bytes) must be " + nonceSizeInBytes());
    }

    public ByteBuffer chacha20Block(byte[] bArr, int i3) {
        int[] createInitialState = createInitialState(ChaCha20Util.toIntArray(bArr), i3);
        int[] iArr = (int[]) createInitialState.clone();
        ChaCha20Util.shuffleState(iArr);
        for (int i4 = 0; i4 < createInitialState.length; i4++) {
            createInitialState[i4] = createInitialState[i4] + iArr[i4];
        }
        ByteBuffer order = ByteBuffer.allocate(64).order(ByteOrder.LITTLE_ENDIAN);
        order.asIntBuffer().put(createInitialState, 0, 16);
        return order;
    }

    public abstract int[] createInitialState(int[] iArr, int i3);

    public byte[] decrypt(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        return decrypt(bArr, ByteBuffer.wrap(bArr2));
    }

    public byte[] encrypt(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        ByteBuffer allocate = ByteBuffer.allocate(bArr2.length);
        encrypt(allocate, bArr, bArr2);
        return allocate.array();
    }

    public abstract int nonceSizeInBytes();

    public byte[] decrypt(byte[] bArr, ByteBuffer byteBuffer) throws GeneralSecurityException {
        ByteBuffer allocate = ByteBuffer.allocate(byteBuffer.remaining());
        process(bArr, allocate, byteBuffer);
        return allocate.array();
    }

    public void encrypt(ByteBuffer byteBuffer, byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        if (byteBuffer.remaining() >= bArr2.length) {
            process(bArr, byteBuffer, ByteBuffer.wrap(bArr2));
            return;
        }
        throw new IllegalArgumentException("Given ByteBuffer output is too small");
    }
}
