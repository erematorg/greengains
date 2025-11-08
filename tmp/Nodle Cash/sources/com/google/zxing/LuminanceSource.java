package com.google.zxing;

import org.apache.commons.lang3.ClassUtils;
import org.objectweb.asm.signature.SignatureVisitor;

public abstract class LuminanceSource {
    private final int height;
    private final int width;

    public LuminanceSource(int i3, int i4) {
        this.width = i3;
        this.height = i4;
    }

    public LuminanceSource crop(int i3, int i4, int i5, int i6) {
        throw new UnsupportedOperationException("This luminance source does not support cropping.");
    }

    public final int getHeight() {
        return this.height;
    }

    public abstract byte[] getMatrix();

    public abstract byte[] getRow(int i3, byte[] bArr);

    public final int getWidth() {
        return this.width;
    }

    public LuminanceSource invert() {
        return new InvertedLuminanceSource(this);
    }

    public boolean isCropSupported() {
        return false;
    }

    public boolean isRotateSupported() {
        return false;
    }

    public LuminanceSource rotateCounterClockwise() {
        throw new UnsupportedOperationException("This luminance source does not support rotation by 90 degrees.");
    }

    public LuminanceSource rotateCounterClockwise45() {
        throw new UnsupportedOperationException("This luminance source does not support rotation by 45 degrees.");
    }

    public final String toString() {
        int i3 = this.width;
        byte[] bArr = new byte[i3];
        StringBuilder sb = new StringBuilder((i3 + 1) * this.height);
        for (int i4 = 0; i4 < this.height; i4++) {
            bArr = getRow(i4, bArr);
            for (int i5 = 0; i5 < this.width; i5++) {
                byte b3 = bArr[i5] & 255;
                sb.append(b3 < 64 ? '#' : b3 < 128 ? SignatureVisitor.EXTENDS : b3 < 192 ? ClassUtils.PACKAGE_SEPARATOR_CHAR : ' ');
            }
            sb.append(10);
        }
        return sb.toString();
    }
}
