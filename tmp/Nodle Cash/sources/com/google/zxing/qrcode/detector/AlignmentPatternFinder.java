package com.google.zxing.qrcode.detector;

import com.google.zxing.NotFoundException;
import com.google.zxing.ResultPointCallback;
import com.google.zxing.common.BitMatrix;
import java.util.ArrayList;
import java.util.List;

final class AlignmentPatternFinder {
    private final int[] crossCheckStateCount;
    private final int height;
    private final BitMatrix image;
    private final float moduleSize;
    private final List<AlignmentPattern> possibleCenters = new ArrayList(5);
    private final ResultPointCallback resultPointCallback;
    private final int startX;
    private final int startY;
    private final int width;

    public AlignmentPatternFinder(BitMatrix bitMatrix, int i3, int i4, int i5, int i6, float f2, ResultPointCallback resultPointCallback2) {
        this.image = bitMatrix;
        this.startX = i3;
        this.startY = i4;
        this.width = i5;
        this.height = i6;
        this.moduleSize = f2;
        this.crossCheckStateCount = new int[3];
        this.resultPointCallback = resultPointCallback2;
    }

    private static float centerFromEnd(int[] iArr, int i3) {
        return ((float) (i3 - iArr[2])) - (((float) iArr[1]) / 2.0f);
    }

    private float crossCheckVertical(int i3, int i4, int i5, int i6) {
        BitMatrix bitMatrix = this.image;
        int height2 = bitMatrix.getHeight();
        int[] iArr = this.crossCheckStateCount;
        iArr[0] = 0;
        iArr[1] = 0;
        iArr[2] = 0;
        int i7 = i3;
        while (i7 >= 0 && bitMatrix.get(i4, i7)) {
            int i8 = iArr[1];
            if (i8 > i5) {
                break;
            }
            iArr[1] = i8 + 1;
            i7--;
        }
        if (i7 < 0 || iArr[1] > i5) {
            return Float.NaN;
        }
        while (i7 >= 0 && !bitMatrix.get(i4, i7)) {
            int i9 = iArr[0];
            if (i9 > i5) {
                break;
            }
            iArr[0] = i9 + 1;
            i7--;
        }
        if (iArr[0] > i5) {
            return Float.NaN;
        }
        int i10 = i3 + 1;
        while (i10 < height2 && bitMatrix.get(i4, i10)) {
            int i11 = iArr[1];
            if (i11 > i5) {
                break;
            }
            iArr[1] = i11 + 1;
            i10++;
        }
        if (i10 == height2 || iArr[1] > i5) {
            return Float.NaN;
        }
        while (i10 < height2 && !bitMatrix.get(i4, i10)) {
            int i12 = iArr[2];
            if (i12 > i5) {
                break;
            }
            iArr[2] = i12 + 1;
            i10++;
        }
        int i13 = iArr[2];
        if (i13 <= i5 && Math.abs(((iArr[0] + iArr[1]) + i13) - i6) * 5 < i6 * 2 && foundPatternCross(iArr)) {
            return centerFromEnd(iArr, i10);
        }
        return Float.NaN;
    }

    private boolean foundPatternCross(int[] iArr) {
        float f2 = this.moduleSize;
        float f3 = f2 / 2.0f;
        for (int i3 = 0; i3 < 3; i3++) {
            if (Math.abs(f2 - ((float) iArr[i3])) >= f3) {
                return false;
            }
        }
        return true;
    }

    private AlignmentPattern handlePossibleCenter(int[] iArr, int i3, int i4) {
        int i5 = iArr[0] + iArr[1] + iArr[2];
        float centerFromEnd = centerFromEnd(iArr, i4);
        float crossCheckVertical = crossCheckVertical(i3, (int) centerFromEnd, iArr[1] * 2, i5);
        if (Float.isNaN(crossCheckVertical)) {
            return null;
        }
        float f2 = ((float) ((iArr[0] + iArr[1]) + iArr[2])) / 3.0f;
        for (AlignmentPattern next : this.possibleCenters) {
            if (next.aboutEquals(f2, crossCheckVertical, centerFromEnd)) {
                return next.combineEstimate(crossCheckVertical, centerFromEnd, f2);
            }
        }
        AlignmentPattern alignmentPattern = new AlignmentPattern(centerFromEnd, crossCheckVertical, f2);
        this.possibleCenters.add(alignmentPattern);
        ResultPointCallback resultPointCallback2 = this.resultPointCallback;
        if (resultPointCallback2 == null) {
            return null;
        }
        resultPointCallback2.foundPossibleResultPoint(alignmentPattern);
        return null;
    }

    public AlignmentPattern find() throws NotFoundException {
        AlignmentPattern handlePossibleCenter;
        AlignmentPattern handlePossibleCenter2;
        int i3 = this.startX;
        int i4 = this.height;
        int i5 = this.width + i3;
        int i6 = (i4 / 2) + this.startY;
        int[] iArr = new int[3];
        for (int i7 = 0; i7 < i4; i7++) {
            int i8 = ((i7 & 1) == 0 ? (i7 + 1) / 2 : -((i7 + 1) / 2)) + i6;
            iArr[0] = 0;
            iArr[1] = 0;
            iArr[2] = 0;
            int i9 = i3;
            while (i9 < i5 && !this.image.get(i9, i8)) {
                i9++;
            }
            int i10 = 0;
            while (i9 < i5) {
                if (!this.image.get(i9, i8)) {
                    if (i10 == 1) {
                        i10++;
                    }
                    iArr[i10] = iArr[i10] + 1;
                } else if (i10 == 1) {
                    iArr[1] = iArr[1] + 1;
                } else if (i10 != 2) {
                    i10++;
                    iArr[i10] = iArr[i10] + 1;
                } else if (foundPatternCross(iArr) && (handlePossibleCenter2 = handlePossibleCenter(iArr, i8, i9)) != null) {
                    return handlePossibleCenter2;
                } else {
                    iArr[0] = iArr[2];
                    iArr[1] = 1;
                    iArr[2] = 0;
                    i10 = 1;
                }
                i9++;
            }
            if (foundPatternCross(iArr) && (handlePossibleCenter = handlePossibleCenter(iArr, i8, i5)) != null) {
                return handlePossibleCenter;
            }
        }
        if (!this.possibleCenters.isEmpty()) {
            return this.possibleCenters.get(0);
        }
        throw NotFoundException.getNotFoundInstance();
    }
}
