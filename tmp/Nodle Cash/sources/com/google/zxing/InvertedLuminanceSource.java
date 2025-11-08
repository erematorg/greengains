package com.google.zxing;

public final class InvertedLuminanceSource extends LuminanceSource {
    private final LuminanceSource delegate;

    public InvertedLuminanceSource(LuminanceSource luminanceSource) {
        super(luminanceSource.getWidth(), luminanceSource.getHeight());
        this.delegate = luminanceSource;
    }

    public LuminanceSource crop(int i3, int i4, int i5, int i6) {
        return new InvertedLuminanceSource(this.delegate.crop(i3, i4, i5, i6));
    }

    public byte[] getMatrix() {
        byte[] matrix = this.delegate.getMatrix();
        int height = getHeight() * getWidth();
        byte[] bArr = new byte[height];
        for (int i3 = 0; i3 < height; i3++) {
            bArr[i3] = (byte) (255 - (matrix[i3] & 255));
        }
        return bArr;
    }

    public byte[] getRow(int i3, byte[] bArr) {
        byte[] row = this.delegate.getRow(i3, bArr);
        int width = getWidth();
        for (int i4 = 0; i4 < width; i4++) {
            row[i4] = (byte) (255 - (row[i4] & 255));
        }
        return row;
    }

    public LuminanceSource invert() {
        return this.delegate;
    }

    public boolean isCropSupported() {
        return this.delegate.isCropSupported();
    }

    public boolean isRotateSupported() {
        return this.delegate.isRotateSupported();
    }

    public LuminanceSource rotateCounterClockwise() {
        return new InvertedLuminanceSource(this.delegate.rotateCounterClockwise());
    }

    public LuminanceSource rotateCounterClockwise45() {
        return new InvertedLuminanceSource(this.delegate.rotateCounterClockwise45());
    }
}
