package com.appsamurai.storyly.exoplayer2.extractor.extractor;

public final class VorbisBitArray {
    private int bitOffset;
    private final int byteLimit;
    private int byteOffset;
    private final byte[] data;

    public VorbisBitArray(byte[] bArr) {
        this.data = bArr;
        this.byteLimit = bArr.length;
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
        throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.exoplayer2.extractor.extractor.VorbisBitArray.assertValidOffset():void");
    }

    public int bitsLeft() {
        return ((this.byteLimit - this.byteOffset) * 8) - this.bitOffset;
    }

    public int getPosition() {
        return (this.byteOffset * 8) + this.bitOffset;
    }

    public boolean readBit() {
        boolean z2 = (((this.data[this.byteOffset] & 255) >> this.bitOffset) & 1) == 1;
        skipBits(1);
        return z2;
    }

    public int readBits(int i3) {
        int i4 = this.byteOffset;
        int min = Math.min(i3, 8 - this.bitOffset);
        int i5 = i4 + 1;
        int i6 = ((this.data[i4] & 255) >> this.bitOffset) & (255 >> (8 - min));
        while (min < i3) {
            i6 |= (this.data[i5] & 255) << min;
            min += 8;
            i5++;
        }
        int i7 = i6 & (-1 >>> (32 - i3));
        skipBits(i3);
        return i7;
    }

    public void reset() {
        this.byteOffset = 0;
        this.bitOffset = 0;
    }

    public void setPosition(int i3) {
        int i4 = i3 / 8;
        this.byteOffset = i4;
        this.bitOffset = i3 - (i4 * 8);
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
}
