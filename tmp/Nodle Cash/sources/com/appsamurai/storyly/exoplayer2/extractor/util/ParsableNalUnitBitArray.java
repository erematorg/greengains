package com.appsamurai.storyly.exoplayer2.extractor.util;

public final class ParsableNalUnitBitArray {
    private int bitOffset;
    private int byteLimit;
    private int byteOffset;
    private byte[] data;

    public ParsableNalUnitBitArray(byte[] bArr, int i3, int i4) {
        reset(bArr, i3, i4);
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
        throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.exoplayer2.extractor.util.ParsableNalUnitBitArray.assertValidOffset():void");
    }

    private int readExpGolombCodeNum() {
        int i3 = 0;
        int i4 = 0;
        while (!readBit()) {
            i4++;
        }
        int i5 = (1 << i4) - 1;
        if (i4 > 0) {
            i3 = readBits(i4);
        }
        return i5 + i3;
    }

    private boolean shouldSkipByte(int i3) {
        if (2 <= i3 && i3 < this.byteLimit) {
            byte[] bArr = this.data;
            return bArr[i3] == 3 && bArr[i3 + -2] == 0 && bArr[i3 - 1] == 0;
        }
    }

    public boolean canReadBits(int i3) {
        int i4 = this.byteOffset;
        int i5 = i3 / 8;
        int i6 = i4 + i5;
        int i7 = (this.bitOffset + i3) - (i5 * 8);
        if (i7 > 7) {
            i6++;
            i7 -= 8;
        }
        while (true) {
            i4++;
            if (i4 > i6 || i6 >= this.byteLimit) {
                int i8 = this.byteLimit;
            } else if (shouldSkipByte(i4)) {
                i6++;
                i4 += 2;
            }
        }
        int i82 = this.byteLimit;
        if (i6 >= i82) {
            return i6 == i82 && i7 == 0;
        }
        return true;
    }

    public boolean canReadExpGolombCodedNum() {
        int i3 = this.byteOffset;
        int i4 = this.bitOffset;
        int i5 = 0;
        while (this.byteOffset < this.byteLimit && !readBit()) {
            i5++;
        }
        boolean z2 = this.byteOffset == this.byteLimit;
        this.byteOffset = i3;
        this.bitOffset = i4;
        return !z2 && canReadBits((i5 * 2) + 1);
    }

    public boolean readBit() {
        boolean z2 = (this.data[this.byteOffset] & (128 >> this.bitOffset)) != 0;
        skipBit();
        return z2;
    }

    public int readBits(int i3) {
        int i4;
        int i5;
        this.bitOffset += i3;
        int i6 = 0;
        while (true) {
            i4 = this.bitOffset;
            i5 = 2;
            if (i4 <= 8) {
                break;
            }
            int i7 = i4 - 8;
            this.bitOffset = i7;
            byte[] bArr = this.data;
            int i8 = this.byteOffset;
            i6 |= (bArr[i8] & 255) << i7;
            if (!shouldSkipByte(i8 + 1)) {
                i5 = 1;
            }
            this.byteOffset = i8 + i5;
        }
        byte[] bArr2 = this.data;
        int i9 = this.byteOffset;
        int i10 = (-1 >>> (32 - i3)) & (i6 | ((bArr2[i9] & 255) >> (8 - i4)));
        if (i4 == 8) {
            this.bitOffset = 0;
            if (!shouldSkipByte(i9 + 1)) {
                i5 = 1;
            }
            this.byteOffset = i9 + i5;
        }
        assertValidOffset();
        return i10;
    }

    public int readSignedExpGolombCodedInt() {
        int readExpGolombCodeNum = readExpGolombCodeNum();
        return ((readExpGolombCodeNum + 1) / 2) * (readExpGolombCodeNum % 2 == 0 ? -1 : 1);
    }

    public int readUnsignedExpGolombCodedInt() {
        return readExpGolombCodeNum();
    }

    public void reset(byte[] bArr, int i3, int i4) {
        this.data = bArr;
        this.byteOffset = i3;
        this.byteLimit = i4;
        this.bitOffset = 0;
        assertValidOffset();
    }

    public void skipBit() {
        int i3 = 1;
        int i4 = this.bitOffset + 1;
        this.bitOffset = i4;
        if (i4 == 8) {
            this.bitOffset = 0;
            int i5 = this.byteOffset;
            if (shouldSkipByte(i5 + 1)) {
                i3 = 2;
            }
            this.byteOffset = i5 + i3;
        }
        assertValidOffset();
    }

    public void skipBits(int i3) {
        int i4 = this.byteOffset;
        int i5 = i3 / 8;
        int i6 = i4 + i5;
        this.byteOffset = i6;
        int i7 = (i3 - (i5 * 8)) + this.bitOffset;
        this.bitOffset = i7;
        if (i7 > 7) {
            this.byteOffset = i6 + 1;
            this.bitOffset = i7 - 8;
        }
        while (true) {
            i4++;
            if (i4 > this.byteOffset) {
                assertValidOffset();
                return;
            } else if (shouldSkipByte(i4)) {
                this.byteOffset++;
                i4 += 2;
            }
        }
    }
}
