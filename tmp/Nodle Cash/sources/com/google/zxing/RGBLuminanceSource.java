package com.google.zxing;

import A.a;
import androidx.constraintlayout.core.motion.utils.TypedValues;

public final class RGBLuminanceSource extends LuminanceSource {
    private final int dataHeight;
    private final int dataWidth;
    private final int left;
    private final byte[] luminances;
    private final int top;

    public RGBLuminanceSource(int i3, int i4, int[] iArr) {
        super(i3, i4);
        this.dataWidth = i3;
        this.dataHeight = i4;
        this.left = 0;
        this.top = 0;
        int i5 = i3 * i4;
        this.luminances = new byte[i5];
        for (int i6 = 0; i6 < i5; i6++) {
            int i7 = iArr[i6];
            this.luminances[i6] = (byte) (((((i7 >> 16) & 255) + ((i7 >> 7) & TypedValues.PositionType.TYPE_POSITION_TYPE)) + (i7 & 255)) / 4);
        }
    }

    public LuminanceSource crop(int i3, int i4, int i5, int i6) {
        return new RGBLuminanceSource(this.luminances, this.dataWidth, this.dataHeight, this.left + i3, this.top + i4, i5, i6);
    }

    public byte[] getMatrix() {
        int width = getWidth();
        int height = getHeight();
        int i3 = this.dataWidth;
        if (width == i3 && height == this.dataHeight) {
            return this.luminances;
        }
        int i4 = width * height;
        byte[] bArr = new byte[i4];
        int i5 = (this.top * i3) + this.left;
        if (width == i3) {
            System.arraycopy(this.luminances, i5, bArr, 0, i4);
            return bArr;
        }
        for (int i6 = 0; i6 < height; i6++) {
            System.arraycopy(this.luminances, i5, bArr, i6 * width, width);
            i5 += this.dataWidth;
        }
        return bArr;
    }

    public byte[] getRow(int i3, byte[] bArr) {
        if (i3 < 0 || i3 >= getHeight()) {
            throw new IllegalArgumentException(a.k("Requested row is outside the image: ", i3));
        }
        int width = getWidth();
        if (bArr == null || bArr.length < width) {
            bArr = new byte[width];
        }
        System.arraycopy(this.luminances, ((i3 + this.top) * this.dataWidth) + this.left, bArr, 0, width);
        return bArr;
    }

    public boolean isCropSupported() {
        return true;
    }

    private RGBLuminanceSource(byte[] bArr, int i3, int i4, int i5, int i6, int i7, int i8) {
        super(i7, i8);
        if (i7 + i5 > i3 || i8 + i6 > i4) {
            throw new IllegalArgumentException("Crop rectangle does not fit within image data.");
        }
        this.luminances = bArr;
        this.dataWidth = i3;
        this.dataHeight = i4;
        this.left = i5;
        this.top = i6;
    }
}
