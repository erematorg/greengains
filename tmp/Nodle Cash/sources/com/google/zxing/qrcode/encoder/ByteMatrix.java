package com.google.zxing.qrcode.encoder;

import java.lang.reflect.Array;
import java.util.Arrays;

public final class ByteMatrix {
    private final byte[][] bytes;
    private final int height;
    private final int width;

    public ByteMatrix(int i3, int i4) {
        int[] iArr = new int[2];
        iArr[1] = i3;
        iArr[0] = i4;
        this.bytes = (byte[][]) Array.newInstance(Byte.TYPE, iArr);
        this.width = i3;
        this.height = i4;
    }

    public void clear(byte b3) {
        for (byte[] fill : this.bytes) {
            Arrays.fill(fill, b3);
        }
    }

    public byte get(int i3, int i4) {
        return this.bytes[i4][i3];
    }

    public byte[][] getArray() {
        return this.bytes;
    }

    public int getHeight() {
        return this.height;
    }

    public int getWidth() {
        return this.width;
    }

    public void set(int i3, int i4, byte b3) {
        this.bytes[i4][i3] = b3;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder((this.width * 2 * this.height) + 2);
        for (int i3 = 0; i3 < this.height; i3++) {
            byte[] bArr = this.bytes[i3];
            for (int i4 = 0; i4 < this.width; i4++) {
                byte b3 = bArr[i4];
                if (b3 == 0) {
                    sb.append(" 0");
                } else if (b3 != 1) {
                    sb.append("  ");
                } else {
                    sb.append(" 1");
                }
            }
            sb.append(10);
        }
        return sb.toString();
    }

    public void set(int i3, int i4, int i5) {
        this.bytes[i4][i3] = (byte) i5;
    }

    public void set(int i3, int i4, boolean z2) {
        this.bytes[i4][i3] = z2 ? (byte) 1 : 0;
    }
}
