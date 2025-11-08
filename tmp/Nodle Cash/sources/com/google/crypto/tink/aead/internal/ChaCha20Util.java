package com.google.crypto.tink.aead.internal;

import com.fasterxml.jackson.dataformat.cbor.CBORConstants;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;

final class ChaCha20Util {
    static final int BLOCK_SIZE_IN_BYTES = 64;
    static final int BLOCK_SIZE_IN_INTS = 16;
    static final int KEY_SIZE_IN_BYTES = 32;
    static final int KEY_SIZE_IN_INTS = 8;
    private static final int[] SIGMA = toIntArray(new byte[]{101, CBORConstants.BYTE_STRING_1BYTE_LEN, 112, 97, 110, 100, 32, 51, 50, 45, 98, CBORConstants.BYTE_STRING_2BYTE_LEN, 116, 101, 32, 107});

    private ChaCha20Util() {
    }

    public static void quarterRound(int[] iArr, int i3, int i4, int i5, int i6) {
        int i7 = iArr[i3] + iArr[i4];
        iArr[i3] = i7;
        int rotateLeft = rotateLeft(i7 ^ iArr[i6], 16);
        iArr[i6] = rotateLeft;
        int i8 = iArr[i5] + rotateLeft;
        iArr[i5] = i8;
        int rotateLeft2 = rotateLeft(iArr[i4] ^ i8, 12);
        iArr[i4] = rotateLeft2;
        int i9 = iArr[i3] + rotateLeft2;
        iArr[i3] = i9;
        int rotateLeft3 = rotateLeft(iArr[i6] ^ i9, 8);
        iArr[i6] = rotateLeft3;
        int i10 = iArr[i5] + rotateLeft3;
        iArr[i5] = i10;
        iArr[i4] = rotateLeft(iArr[i4] ^ i10, 7);
    }

    private static int rotateLeft(int i3, int i4) {
        return (i3 >>> (-i4)) | (i3 << i4);
    }

    public static void setSigmaAndKey(int[] iArr, int[] iArr2) {
        int[] iArr3 = SIGMA;
        System.arraycopy(iArr3, 0, iArr, 0, iArr3.length);
        System.arraycopy(iArr2, 0, iArr, iArr3.length, 8);
    }

    public static void shuffleState(int[] iArr) {
        int[] iArr2 = iArr;
        for (int i3 = 0; i3 < 10; i3++) {
            quarterRound(iArr2, 0, 4, 8, 12);
            quarterRound(iArr2, 1, 5, 9, 13);
            quarterRound(iArr2, 2, 6, 10, 14);
            quarterRound(iArr2, 3, 7, 11, 15);
            quarterRound(iArr2, 0, 5, 10, 15);
            quarterRound(iArr2, 1, 6, 11, 12);
            quarterRound(iArr2, 2, 7, 8, 13);
            quarterRound(iArr2, 3, 4, 9, 14);
        }
    }

    public static int[] toIntArray(byte[] bArr) {
        IntBuffer asIntBuffer = ByteBuffer.wrap(bArr).order(ByteOrder.LITTLE_ENDIAN).asIntBuffer();
        int[] iArr = new int[asIntBuffer.remaining()];
        asIntBuffer.get(iArr);
        return iArr;
    }
}
