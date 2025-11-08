package com.appsamurai.storyly.exoplayer2.common.util;

import com.google.common.base.Charsets;
import java.nio.charset.Charset;

public final class ParsableBitArray {
    private int bitOffset;
    private int byteLimit;
    private int byteOffset;
    public byte[] data;

    public ParsableBitArray() {
        this.data = Util.EMPTY_BYTE_ARRAY;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0004, code lost:
        r1 = r2.byteLimit;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void assertValidOffset() {
        /*
            r2 = this;
            int r0 = r2.byteOffset
            if (r0 < 0) goto L_0x0010
            int r1 = r2.byteLimit
            if (r0 < r1) goto L_0x000e
            if (r0 != r1) goto L_0x0010
            int r2 = r2.bitOffset
            if (r2 != 0) goto L_0x0010
        L_0x000e:
            r2 = 1
            goto L_0x0011
        L_0x0010:
            r2 = 0
        L_0x0011:
            com.appsamurai.storyly.exoplayer2.common.util.Assertions.checkState(r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.exoplayer2.common.util.ParsableBitArray.assertValidOffset():void");
    }

    public int bitsLeft() {
        return ((this.byteLimit - this.byteOffset) * 8) - this.bitOffset;
    }

    public void byteAlign() {
        if (this.bitOffset != 0) {
            this.bitOffset = 0;
            this.byteOffset++;
            assertValidOffset();
        }
    }

    public int getBytePosition() {
        Assertions.checkState(this.bitOffset == 0);
        return this.byteOffset;
    }

    public int getPosition() {
        return (this.byteOffset * 8) + this.bitOffset;
    }

    public void putInt(int i3, int i4) {
        if (i4 < 32) {
            i3 &= (1 << i4) - 1;
        }
        int min = Math.min(8 - this.bitOffset, i4);
        int i5 = this.bitOffset;
        int i6 = (8 - i5) - min;
        byte[] bArr = this.data;
        int i7 = this.byteOffset;
        byte b3 = (byte) (((65280 >> i5) | ((1 << i6) - 1)) & bArr[i7]);
        bArr[i7] = b3;
        int i8 = i4 - min;
        bArr[i7] = (byte) (b3 | ((i3 >>> i8) << i6));
        int i9 = i7 + 1;
        while (i8 > 8) {
            this.data[i9] = (byte) (i3 >>> (i8 - 8));
            i8 -= 8;
            i9++;
        }
        int i10 = 8 - i8;
        byte[] bArr2 = this.data;
        byte b4 = (byte) (bArr2[i9] & ((1 << i10) - 1));
        bArr2[i9] = b4;
        bArr2[i9] = (byte) (((i3 & ((1 << i8) - 1)) << i10) | b4);
        skipBits(i4);
        assertValidOffset();
    }

    public boolean readBit() {
        boolean z2 = (this.data[this.byteOffset] & (128 >> this.bitOffset)) != 0;
        skipBit();
        return z2;
    }

    public int readBits(int i3) {
        int i4;
        if (i3 == 0) {
            return 0;
        }
        this.bitOffset += i3;
        int i5 = 0;
        while (true) {
            i4 = this.bitOffset;
            if (i4 <= 8) {
                break;
            }
            int i6 = i4 - 8;
            this.bitOffset = i6;
            byte[] bArr = this.data;
            int i7 = this.byteOffset;
            this.byteOffset = i7 + 1;
            i5 |= (bArr[i7] & 255) << i6;
        }
        byte[] bArr2 = this.data;
        int i8 = this.byteOffset;
        int i9 = (-1 >>> (32 - i3)) & (i5 | ((bArr2[i8] & 255) >> (8 - i4)));
        if (i4 == 8) {
            this.bitOffset = 0;
            this.byteOffset = i8 + 1;
        }
        assertValidOffset();
        return i9;
    }

    public long readBitsToLong(int i3) {
        return i3 <= 32 ? Util.toUnsignedLong(readBits(i3)) : Util.toLong(readBits(i3 - 32), readBits(32));
    }

    public void readBytes(byte[] bArr, int i3, int i4) {
        Assertions.checkState(this.bitOffset == 0);
        System.arraycopy(this.data, this.byteOffset, bArr, i3, i4);
        this.byteOffset += i4;
        assertValidOffset();
    }

    public String readBytesAsString(int i3) {
        return readBytesAsString(i3, Charsets.UTF_8);
    }

    public void reset(byte[] bArr) {
        reset(bArr, bArr.length);
    }

    public void setPosition(int i3) {
        int i4 = i3 / 8;
        this.byteOffset = i4;
        this.bitOffset = i3 - (i4 * 8);
        assertValidOffset();
    }

    public void skipBit() {
        int i3 = this.bitOffset + 1;
        this.bitOffset = i3;
        if (i3 == 8) {
            this.bitOffset = 0;
            this.byteOffset++;
        }
        assertValidOffset();
    }

    public void skipBits(int i3) {
        int i4 = i3 / 8;
        int i5 = this.byteOffset + i4;
        this.byteOffset = i5;
        int i6 = (i3 - (i4 * 8)) + this.bitOffset;
        this.bitOffset = i6;
        if (i6 > 7) {
            this.byteOffset = i5 + 1;
            this.bitOffset = i6 - 8;
        }
        assertValidOffset();
    }

    public void skipBytes(int i3) {
        Assertions.checkState(this.bitOffset == 0);
        this.byteOffset += i3;
        assertValidOffset();
    }

    public String readBytesAsString(int i3, Charset charset) {
        byte[] bArr = new byte[i3];
        readBytes(bArr, 0, i3);
        return new String(bArr, charset);
    }

    public void reset(ParsableByteArray parsableByteArray) {
        reset(parsableByteArray.getData(), parsableByteArray.limit());
        setPosition(parsableByteArray.getPosition() * 8);
    }

    public ParsableBitArray(byte[] bArr) {
        this(bArr, bArr.length);
    }

    public ParsableBitArray(byte[] bArr, int i3) {
        this.data = bArr;
        this.byteLimit = i3;
    }

    public void reset(byte[] bArr, int i3) {
        this.data = bArr;
        this.byteOffset = 0;
        this.bitOffset = 0;
        this.byteLimit = i3;
    }

    public void readBits(byte[] bArr, int i3, int i4) {
        int i5 = (i4 >> 3) + i3;
        while (i3 < i5) {
            byte[] bArr2 = this.data;
            int i6 = this.byteOffset;
            int i7 = i6 + 1;
            this.byteOffset = i7;
            byte b3 = bArr2[i6];
            int i8 = this.bitOffset;
            byte b4 = (byte) (b3 << i8);
            bArr[i3] = b4;
            bArr[i3] = (byte) (((255 & bArr2[i7]) >> (8 - i8)) | b4);
            i3++;
        }
        int i9 = i4 & 7;
        if (i9 != 0) {
            byte b5 = (byte) (bArr[i5] & (255 >> i9));
            bArr[i5] = b5;
            int i10 = this.bitOffset;
            if (i10 + i9 > 8) {
                byte[] bArr3 = this.data;
                int i11 = this.byteOffset;
                this.byteOffset = i11 + 1;
                bArr[i5] = (byte) (b5 | ((bArr3[i11] & 255) << i10));
                this.bitOffset = i10 - 8;
            }
            int i12 = this.bitOffset + i9;
            this.bitOffset = i12;
            byte[] bArr4 = this.data;
            int i13 = this.byteOffset;
            bArr[i5] = (byte) (((byte) (((255 & bArr4[i13]) >> (8 - i12)) << (8 - i9))) | bArr[i5]);
            if (i12 == 8) {
                this.bitOffset = 0;
                this.byteOffset = i13 + 1;
            }
            assertValidOffset();
        }
    }
}
