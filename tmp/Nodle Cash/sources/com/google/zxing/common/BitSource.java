package com.google.zxing.common;

public final class BitSource {
    private int bitOffset;
    private int byteOffset;
    private final byte[] bytes;

    public BitSource(byte[] bArr) {
        this.bytes = bArr;
    }

    public int available() {
        return ((this.bytes.length - this.byteOffset) * 8) - this.bitOffset;
    }

    public int getBitOffset() {
        return this.bitOffset;
    }

    public int getByteOffset() {
        return this.byteOffset;
    }

    public int readBits(int i3) {
        if (i3 < 1 || i3 > 32 || i3 > available()) {
            throw new IllegalArgumentException(String.valueOf(i3));
        }
        int i4 = this.bitOffset;
        byte b3 = 0;
        if (i4 > 0) {
            int i5 = 8 - i4;
            int min = Math.min(i3, i5);
            int i6 = i5 - min;
            byte[] bArr = this.bytes;
            int i7 = this.byteOffset;
            int i8 = (((255 >> (8 - min)) << i6) & bArr[i7]) >> i6;
            i3 -= min;
            int i9 = this.bitOffset + min;
            this.bitOffset = i9;
            if (i9 == 8) {
                this.bitOffset = 0;
                this.byteOffset = i7 + 1;
            }
            b3 = i8;
        }
        if (i3 <= 0) {
            return b3;
        }
        while (i3 >= 8) {
            int i10 = b3 << 8;
            byte[] bArr2 = this.bytes;
            int i11 = this.byteOffset;
            b3 = (bArr2[i11] & 255) | i10;
            this.byteOffset = i11 + 1;
            i3 -= 8;
        }
        if (i3 <= 0) {
            return b3;
        }
        int i12 = 8 - i3;
        int i13 = (b3 << i3) | ((((255 >> i12) << i12) & this.bytes[this.byteOffset]) >> i12);
        this.bitOffset += i3;
        return i13;
    }
}
