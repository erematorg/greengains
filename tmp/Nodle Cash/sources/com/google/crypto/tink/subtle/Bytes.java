package com.google.crypto.tink.subtle;

import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.util.Arrays;

public final class Bytes {
    private Bytes() {
    }

    public static int byteArrayToInt(byte[] bArr) {
        return byteArrayToInt(bArr, bArr.length);
    }

    public static byte[] concat(byte[]... bArr) throws GeneralSecurityException {
        int length = bArr.length;
        int i3 = 0;
        int i4 = 0;
        while (i3 < length) {
            byte[] bArr2 = bArr[i3];
            if (i4 <= Integer.MAX_VALUE - bArr2.length) {
                i4 += bArr2.length;
                i3++;
            } else {
                throw new GeneralSecurityException("exceeded size limit");
            }
        }
        byte[] bArr3 = new byte[i4];
        int i5 = 0;
        for (byte[] bArr4 : bArr) {
            System.arraycopy(bArr4, 0, bArr3, i5, bArr4.length);
            i5 += bArr4.length;
        }
        return bArr3;
    }

    public static final boolean equal(byte[] bArr, byte[] bArr2) {
        return MessageDigest.isEqual(bArr, bArr2);
    }

    public static byte[] intToByteArray(int i3, int i4) {
        byte[] bArr = new byte[i3];
        for (int i5 = 0; i5 < i3; i5++) {
            bArr[i5] = (byte) ((i4 >> (i5 * 8)) & 255);
        }
        return bArr;
    }

    public static final byte[] xor(byte[] bArr, int i3, byte[] bArr2, int i4, int i5) {
        if (i5 < 0 || bArr.length - i5 < i3 || bArr2.length - i5 < i4) {
            throw new IllegalArgumentException("That combination of buffers, offsets and length to xor result in out-of-bond accesses.");
        }
        byte[] bArr3 = new byte[i5];
        for (int i6 = 0; i6 < i5; i6++) {
            bArr3[i6] = (byte) (bArr[i6 + i3] ^ bArr2[i6 + i4]);
        }
        return bArr3;
    }

    public static final byte[] xorEnd(byte[] bArr, byte[] bArr2) {
        if (bArr.length >= bArr2.length) {
            int length = bArr.length - bArr2.length;
            byte[] copyOf = Arrays.copyOf(bArr, bArr.length);
            for (int i3 = 0; i3 < bArr2.length; i3++) {
                int i4 = length + i3;
                copyOf[i4] = (byte) (copyOf[i4] ^ bArr2[i3]);
            }
            return copyOf;
        }
        throw new IllegalArgumentException("xorEnd requires a.length >= b.length");
    }

    public static int byteArrayToInt(byte[] bArr, int i3) {
        return byteArrayToInt(bArr, 0, i3);
    }

    public static int byteArrayToInt(byte[] bArr, int i3, int i4) {
        int i5 = 0;
        for (int i6 = 0; i6 < i4; i6++) {
            i5 += (bArr[i6 + i3] & 255) << (i6 * 8);
        }
        return i5;
    }

    public static final void xor(ByteBuffer byteBuffer, ByteBuffer byteBuffer2, ByteBuffer byteBuffer3, int i3) {
        if (i3 < 0 || byteBuffer2.remaining() < i3 || byteBuffer3.remaining() < i3 || byteBuffer.remaining() < i3) {
            throw new IllegalArgumentException("That combination of buffers, offsets and length to xor result in out-of-bond accesses.");
        }
        for (int i4 = 0; i4 < i3; i4++) {
            byteBuffer.put((byte) (byteBuffer2.get() ^ byteBuffer3.get()));
        }
    }

    public static final byte[] xor(byte[] bArr, byte[] bArr2) {
        if (bArr.length == bArr2.length) {
            return xor(bArr, 0, bArr2, 0, bArr.length);
        }
        throw new IllegalArgumentException("The lengths of x and y should match.");
    }
}
