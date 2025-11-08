package com.google.zxing.common;

import com.google.zxing.NotFoundException;

public final class DefaultGridSampler extends GridSampler {
    public BitMatrix sampleGrid(BitMatrix bitMatrix, int i3, int i4, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10, float f11, float f12, float f13, float f14, float f15, float f16, float f17) throws NotFoundException {
        BitMatrix bitMatrix2 = bitMatrix;
        int i5 = i3;
        int i6 = i4;
        return sampleGrid(bitMatrix, i3, i4, PerspectiveTransform.quadrilateralToQuadrilateral(f2, f3, f4, f5, f6, f7, f8, f9, f10, f11, f12, f13, f14, f15, f16, f17));
    }

    public BitMatrix sampleGrid(BitMatrix bitMatrix, int i3, int i4, PerspectiveTransform perspectiveTransform) throws NotFoundException {
        if (i3 <= 0 || i4 <= 0) {
            throw NotFoundException.getNotFoundInstance();
        }
        BitMatrix bitMatrix2 = new BitMatrix(i3, i4);
        int i5 = i3 * 2;
        float[] fArr = new float[i5];
        for (int i6 = 0; i6 < i4; i6++) {
            float f2 = ((float) i6) + 0.5f;
            for (int i7 = 0; i7 < i5; i7 += 2) {
                fArr[i7] = ((float) (i7 / 2)) + 0.5f;
                fArr[i7 + 1] = f2;
            }
            perspectiveTransform.transformPoints(fArr);
            GridSampler.checkAndNudgePoints(bitMatrix, fArr);
            int i8 = 0;
            while (i8 < i5) {
                try {
                    if (bitMatrix.get((int) fArr[i8], (int) fArr[i8 + 1])) {
                        bitMatrix2.set(i8 / 2, i6);
                    }
                    i8 += 2;
                } catch (ArrayIndexOutOfBoundsException unused) {
                    throw NotFoundException.getNotFoundInstance();
                }
            }
        }
        return bitMatrix2;
    }
}
