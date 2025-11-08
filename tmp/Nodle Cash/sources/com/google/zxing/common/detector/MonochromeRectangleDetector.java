package com.google.zxing.common.detector;

import com.google.zxing.NotFoundException;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitMatrix;

@Deprecated
public final class MonochromeRectangleDetector {
    private static final int MAX_MODULES = 32;
    private final BitMatrix image;

    public MonochromeRectangleDetector(BitMatrix bitMatrix) {
        this.image = bitMatrix;
    }

    private int[] blackWhiteRange(int i3, int i4, int i5, int i6, boolean z2) {
        int i7 = (i5 + i6) / 2;
        int i8 = i7;
        while (i8 >= i5) {
            BitMatrix bitMatrix = this.image;
            if (!z2 ? !bitMatrix.get(i3, i8) : !bitMatrix.get(i8, i3)) {
                int i9 = i8;
                while (true) {
                    i9--;
                    if (i9 < i5) {
                        break;
                    }
                    BitMatrix bitMatrix2 = this.image;
                    if (z2) {
                        if (bitMatrix2.get(i9, i3)) {
                            break;
                        }
                    } else if (bitMatrix2.get(i3, i9)) {
                        break;
                    }
                }
                int i10 = i8 - i9;
                if (i9 < i5 || i10 > i4) {
                    break;
                }
                i8 = i9;
            } else {
                i8--;
            }
        }
        int i11 = i8 + 1;
        while (i7 < i6) {
            BitMatrix bitMatrix3 = this.image;
            if (!z2 ? !bitMatrix3.get(i3, i7) : !bitMatrix3.get(i7, i3)) {
                int i12 = i7;
                while (true) {
                    i12++;
                    if (i12 >= i6) {
                        break;
                    }
                    BitMatrix bitMatrix4 = this.image;
                    if (z2) {
                        if (bitMatrix4.get(i12, i3)) {
                            break;
                        }
                    } else if (bitMatrix4.get(i3, i12)) {
                        break;
                    }
                }
                int i13 = i12 - i7;
                if (i12 >= i6 || i13 > i4) {
                    break;
                }
                i7 = i12;
            } else {
                i7++;
            }
        }
        int i14 = i7 - 1;
        if (i14 <= i11) {
            return null;
        }
        return new int[]{i11, i14};
    }

    private ResultPoint findCornerFromCenter(int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11) throws NotFoundException {
        int i12 = i3;
        int i13 = i7;
        int[] iArr = null;
        int i14 = i10;
        int i15 = i12;
        int i16 = i13;
        while (i16 < i14 && i16 >= i9 && i15 < i6 && i15 >= i5) {
            int[] blackWhiteRange = i4 == 0 ? blackWhiteRange(i16, i11, i5, i6, true) : blackWhiteRange(i15, i11, i9, i10, false);
            if (blackWhiteRange != null) {
                i16 += i8;
                i15 += i4;
                iArr = blackWhiteRange;
            } else if (iArr != null) {
                char c3 = 0;
                if (i4 == 0) {
                    int i17 = i16 - i8;
                    int i18 = iArr[0];
                    if (i18 >= i12) {
                        return new ResultPoint((float) iArr[1], (float) i17);
                    }
                    if (iArr[1] <= i12) {
                        return new ResultPoint((float) i18, (float) i17);
                    }
                    if (i8 <= 0) {
                        c3 = 1;
                    }
                    return new ResultPoint((float) iArr[c3], (float) i17);
                }
                int i19 = i15 - i4;
                int i20 = iArr[0];
                if (i20 >= i13) {
                    return new ResultPoint((float) i19, (float) iArr[1]);
                }
                if (iArr[1] <= i13) {
                    return new ResultPoint((float) i19, (float) i20);
                }
                float f2 = (float) i19;
                if (i4 >= 0) {
                    c3 = 1;
                }
                return new ResultPoint(f2, (float) iArr[c3]);
            } else {
                throw NotFoundException.getNotFoundInstance();
            }
        }
        throw NotFoundException.getNotFoundInstance();
    }

    public ResultPoint[] detect() throws NotFoundException {
        int height = this.image.getHeight();
        int width = this.image.getWidth();
        int i3 = height / 2;
        int i4 = width / 2;
        int max = Math.max(1, height / 256);
        int i5 = -max;
        int i6 = i4 / 2;
        int i7 = i4;
        int i8 = width;
        int i9 = i3;
        int i10 = i5;
        int i11 = height;
        int i12 = max;
        int max2 = Math.max(1, width / 256);
        int i13 = i3 / 2;
        int y2 = ((int) findCornerFromCenter(i7, 0, 0, i8, i9, i5, 0, i11, i6).getY()) - 1;
        int i14 = max2;
        ResultPoint findCornerFromCenter = findCornerFromCenter(i7, -max2, 0, i8, i9, 0, y2, i11, i13);
        int x2 = ((int) findCornerFromCenter.getX()) - 1;
        ResultPoint resultPoint = findCornerFromCenter;
        ResultPoint findCornerFromCenter2 = findCornerFromCenter(i7, i14, x2, i8, i9, 0, y2, i11, i13);
        int x3 = ((int) findCornerFromCenter2.getX()) + 1;
        ResultPoint resultPoint2 = findCornerFromCenter2;
        ResultPoint findCornerFromCenter3 = findCornerFromCenter(i7, 0, x2, x3, i9, i12, y2, i11, i6);
        return new ResultPoint[]{findCornerFromCenter(i7, 0, x2, x3, i9, i10, y2, ((int) findCornerFromCenter3.getY()) + 1, i4 / 4), resultPoint, resultPoint2, findCornerFromCenter3};
    }
}
