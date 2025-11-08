package com.google.zxing.common;

import com.google.zxing.Binarizer;
import com.google.zxing.LuminanceSource;
import com.google.zxing.NotFoundException;

public class GlobalHistogramBinarizer extends Binarizer {
    private static final byte[] EMPTY = new byte[0];
    private static final int LUMINANCE_BITS = 5;
    private static final int LUMINANCE_BUCKETS = 32;
    private static final int LUMINANCE_SHIFT = 3;
    private final int[] buckets = new int[32];
    private byte[] luminances = EMPTY;

    public GlobalHistogramBinarizer(LuminanceSource luminanceSource) {
        super(luminanceSource);
    }

    private static int estimateBlackPoint(int[] iArr) throws NotFoundException {
        int length = iArr.length;
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        for (int i6 = 0; i6 < length; i6++) {
            int i7 = iArr[i6];
            if (i7 > i3) {
                i5 = i6;
                i3 = i7;
            }
            if (i7 > i4) {
                i4 = i7;
            }
        }
        int i8 = 0;
        int i9 = 0;
        for (int i10 = 0; i10 < length; i10++) {
            int i11 = i10 - i5;
            int i12 = iArr[i10] * i11 * i11;
            if (i12 > i9) {
                i8 = i10;
                i9 = i12;
            }
        }
        if (i5 <= i8) {
            int i13 = i5;
            i5 = i8;
            i8 = i13;
        }
        if (i5 - i8 > length / 16) {
            int i14 = i5 - 1;
            int i15 = -1;
            int i16 = i14;
            while (i14 > i8) {
                int i17 = i14 - i8;
                int i18 = (i4 - iArr[i14]) * (i5 - i14) * i17 * i17;
                if (i18 > i15) {
                    i16 = i14;
                    i15 = i18;
                }
                i14--;
            }
            return i16 << 3;
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private void initArrays(int i3) {
        if (this.luminances.length < i3) {
            this.luminances = new byte[i3];
        }
        for (int i4 = 0; i4 < 32; i4++) {
            this.buckets[i4] = 0;
        }
    }

    public Binarizer createBinarizer(LuminanceSource luminanceSource) {
        return new GlobalHistogramBinarizer(luminanceSource);
    }

    public BitMatrix getBlackMatrix() throws NotFoundException {
        LuminanceSource luminanceSource = getLuminanceSource();
        int width = luminanceSource.getWidth();
        int height = luminanceSource.getHeight();
        BitMatrix bitMatrix = new BitMatrix(width, height);
        initArrays(width);
        int[] iArr = this.buckets;
        for (int i3 = 1; i3 < 5; i3++) {
            byte[] row = luminanceSource.getRow((height * i3) / 5, this.luminances);
            int i4 = (width * 4) / 5;
            for (int i5 = width / 5; i5 < i4; i5++) {
                int i6 = (row[i5] & 255) >> 3;
                iArr[i6] = iArr[i6] + 1;
            }
        }
        int estimateBlackPoint = estimateBlackPoint(iArr);
        byte[] matrix = luminanceSource.getMatrix();
        for (int i7 = 0; i7 < height; i7++) {
            int i8 = i7 * width;
            for (int i9 = 0; i9 < width; i9++) {
                if ((matrix[i8 + i9] & 255) < estimateBlackPoint) {
                    bitMatrix.set(i9, i7);
                }
            }
        }
        return bitMatrix;
    }

    public BitArray getBlackRow(int i3, BitArray bitArray) throws NotFoundException {
        LuminanceSource luminanceSource = getLuminanceSource();
        int width = luminanceSource.getWidth();
        if (bitArray == null || bitArray.getSize() < width) {
            bitArray = new BitArray(width);
        } else {
            bitArray.clear();
        }
        initArrays(width);
        byte[] row = luminanceSource.getRow(i3, this.luminances);
        int[] iArr = this.buckets;
        for (int i4 = 0; i4 < width; i4++) {
            int i5 = (row[i4] & 255) >> 3;
            iArr[i5] = iArr[i5] + 1;
        }
        int estimateBlackPoint = estimateBlackPoint(iArr);
        if (width < 3) {
            for (int i6 = 0; i6 < width; i6++) {
                if ((row[i6] & 255) < estimateBlackPoint) {
                    bitArray.set(i6);
                }
            }
        } else {
            int i7 = 1;
            byte b3 = row[1] & 255;
            byte b4 = row[0] & 255;
            byte b5 = b3;
            while (i7 < width - 1) {
                int i8 = i7 + 1;
                byte b6 = row[i8] & 255;
                if ((((b5 * 4) - b4) - b6) / 2 < estimateBlackPoint) {
                    bitArray.set(i7);
                }
                b4 = b5;
                i7 = i8;
                b5 = b6;
            }
        }
        return bitArray;
    }
}
