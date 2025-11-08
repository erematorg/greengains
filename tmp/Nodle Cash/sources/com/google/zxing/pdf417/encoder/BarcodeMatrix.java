package com.google.zxing.pdf417.encoder;

import java.lang.reflect.Array;

public final class BarcodeMatrix {
    private int currentRow;
    private final int height;
    private final BarcodeRow[] matrix;
    private final int width;

    public BarcodeMatrix(int i3, int i4) {
        BarcodeRow[] barcodeRowArr = new BarcodeRow[i3];
        this.matrix = barcodeRowArr;
        int length = barcodeRowArr.length;
        for (int i5 = 0; i5 < length; i5++) {
            this.matrix[i5] = new BarcodeRow(((i4 + 4) * 17) + 1);
        }
        this.width = i4 * 17;
        this.height = i3;
        this.currentRow = -1;
    }

    public BarcodeRow getCurrentRow() {
        return this.matrix[this.currentRow];
    }

    public byte[][] getMatrix() {
        return getScaledMatrix(1, 1);
    }

    public byte[][] getScaledMatrix(int i3, int i4) {
        int[] iArr = new int[2];
        iArr[1] = this.width * i3;
        iArr[0] = this.height * i4;
        byte[][] bArr = (byte[][]) Array.newInstance(Byte.TYPE, iArr);
        int i5 = this.height * i4;
        for (int i6 = 0; i6 < i5; i6++) {
            bArr[(i5 - i6) - 1] = this.matrix[i6 / i4].getScaledRow(i3);
        }
        return bArr;
    }

    public void set(int i3, int i4, byte b3) {
        this.matrix[i4].set(i3, b3);
    }

    public void startRow() {
        this.currentRow++;
    }
}
