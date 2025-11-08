package com.google.zxing;

import A.a;
import androidx.core.view.ViewCompat;

public final class PlanarYUVLuminanceSource extends LuminanceSource {
    private static final int THUMBNAIL_SCALE_FACTOR = 2;
    private final int dataHeight;
    private final int dataWidth;
    private final int left;
    private final int top;
    private final byte[] yuvData;

    public PlanarYUVLuminanceSource(byte[] bArr, int i3, int i4, int i5, int i6, int i7, int i8, boolean z2) {
        super(i7, i8);
        if (i5 + i7 > i3 || i6 + i8 > i4) {
            throw new IllegalArgumentException("Crop rectangle does not fit within image data.");
        }
        this.yuvData = bArr;
        this.dataWidth = i3;
        this.dataHeight = i4;
        this.left = i5;
        this.top = i6;
        if (z2) {
            reverseHorizontal(i7, i8);
        }
    }

    private void reverseHorizontal(int i3, int i4) {
        byte[] bArr = this.yuvData;
        int i5 = (this.top * this.dataWidth) + this.left;
        int i6 = 0;
        while (i6 < i4) {
            int i7 = (i3 / 2) + i5;
            int i8 = (i5 + i3) - 1;
            int i9 = i5;
            while (i9 < i7) {
                byte b3 = bArr[i9];
                bArr[i9] = bArr[i8];
                bArr[i8] = b3;
                i9++;
                i8--;
            }
            i6++;
            i5 += this.dataWidth;
        }
    }

    public LuminanceSource crop(int i3, int i4, int i5, int i6) {
        return new PlanarYUVLuminanceSource(this.yuvData, this.dataWidth, this.dataHeight, this.left + i3, this.top + i4, i5, i6, false);
    }

    public byte[] getMatrix() {
        int width = getWidth();
        int height = getHeight();
        int i3 = this.dataWidth;
        if (width == i3 && height == this.dataHeight) {
            return this.yuvData;
        }
        int i4 = width * height;
        byte[] bArr = new byte[i4];
        int i5 = (this.top * i3) + this.left;
        if (width == i3) {
            System.arraycopy(this.yuvData, i5, bArr, 0, i4);
            return bArr;
        }
        for (int i6 = 0; i6 < height; i6++) {
            System.arraycopy(this.yuvData, i5, bArr, i6 * width, width);
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
        System.arraycopy(this.yuvData, ((i3 + this.top) * this.dataWidth) + this.left, bArr, 0, width);
        return bArr;
    }

    public int getThumbnailHeight() {
        return getHeight() / 2;
    }

    public int getThumbnailWidth() {
        return getWidth() / 2;
    }

    public boolean isCropSupported() {
        return true;
    }

    public int[] renderThumbnail() {
        int width = getWidth() / 2;
        int height = getHeight() / 2;
        int[] iArr = new int[(width * height)];
        byte[] bArr = this.yuvData;
        int i3 = (this.top * this.dataWidth) + this.left;
        for (int i4 = 0; i4 < height; i4++) {
            int i5 = i4 * width;
            for (int i6 = 0; i6 < width; i6++) {
                iArr[i5 + i6] = ((bArr[(i6 * 2) + i3] & 255) * 65793) | ViewCompat.MEASURED_STATE_MASK;
            }
            i3 += this.dataWidth * 2;
        }
        return iArr;
    }
}
