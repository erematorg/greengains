package com.google.zxing.pdf417.decoder;

import androidx.emoji2.emojipicker.StickyVariantProvider;

final class Codeword {
    private static final int BARCODE_ROW_UNKNOWN = -1;
    private final int bucket;
    private final int endX;
    private int rowNumber = -1;
    private final int startX;
    private final int value;

    public Codeword(int i3, int i4, int i5, int i6) {
        this.startX = i3;
        this.endX = i4;
        this.bucket = i5;
        this.value = i6;
    }

    public int getBucket() {
        return this.bucket;
    }

    public int getEndX() {
        return this.endX;
    }

    public int getRowNumber() {
        return this.rowNumber;
    }

    public int getStartX() {
        return this.startX;
    }

    public int getValue() {
        return this.value;
    }

    public int getWidth() {
        return this.endX - this.startX;
    }

    public boolean hasValidRowNumber() {
        return isValidRowNumber(this.rowNumber);
    }

    public boolean isValidRowNumber(int i3) {
        return i3 != -1 && this.bucket == (i3 % 3) * 3;
    }

    public void setRowNumber(int i3) {
        this.rowNumber = i3;
    }

    public void setRowNumberAsRowIndicatorColumn() {
        this.rowNumber = (this.bucket / 3) + ((this.value / 30) * 3);
    }

    public String toString() {
        return this.rowNumber + StickyVariantProvider.ENTRY_DELIMITER + this.value;
    }
}
