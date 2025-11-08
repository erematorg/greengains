package com.google.zxing.pdf417.detector;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.NotFoundException;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitMatrix;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public final class Detector {
    private static final int BARCODE_MIN_HEIGHT = 10;
    private static final int[] INDEXES_START_PATTERN = {0, 4, 1, 5};
    private static final int[] INDEXES_STOP_PATTERN = {6, 2, 7, 3};
    private static final float MAX_AVG_VARIANCE = 0.42f;
    private static final float MAX_INDIVIDUAL_VARIANCE = 0.8f;
    private static final int MAX_PATTERN_DRIFT = 5;
    private static final int MAX_PIXEL_DRIFT = 3;
    private static final float MAX_STOP_PATTERN_HEIGHT_VARIANCE = 0.5f;
    private static final int[] ROTATIONS = {0, 180, 270, 90};
    private static final int ROW_STEP = 5;
    private static final int SKIPPED_ROW_COUNT_MAX = 25;
    private static final int[] START_PATTERN = {8, 1, 1, 1, 1, 1, 1, 3};
    private static final int[] STOP_PATTERN = {7, 1, 1, 3, 1, 1, 1, 2, 1};

    private Detector() {
    }

    private static BitMatrix applyRotation(BitMatrix bitMatrix, int i3) {
        if (i3 % 360 == 0) {
            return bitMatrix;
        }
        BitMatrix clone = bitMatrix.clone();
        clone.rotate(i3);
        return clone;
    }

    private static void copyToResult(ResultPoint[] resultPointArr, ResultPoint[] resultPointArr2, int[] iArr) {
        for (int i3 = 0; i3 < iArr.length; i3++) {
            resultPointArr[iArr[i3]] = resultPointArr2[i3];
        }
    }

    public static PDF417DetectorResult detect(BinaryBitmap binaryBitmap, Map<DecodeHintType, ?> map, boolean z2) throws NotFoundException {
        BitMatrix blackMatrix = binaryBitmap.getBlackMatrix();
        for (int i3 : ROTATIONS) {
            BitMatrix applyRotation = applyRotation(blackMatrix, i3);
            List<ResultPoint[]> detect = detect(z2, applyRotation);
            if (!detect.isEmpty()) {
                return new PDF417DetectorResult(applyRotation, detect, i3);
            }
        }
        return new PDF417DetectorResult(blackMatrix, new ArrayList(), 0);
    }

    private static int[] findGuardPattern(BitMatrix bitMatrix, int i3, int i4, int i5, int[] iArr, int[] iArr2) {
        Arrays.fill(iArr2, 0, iArr2.length, 0);
        int i6 = 0;
        while (bitMatrix.get(i3, i4) && i3 > 0) {
            int i7 = i6 + 1;
            if (i6 >= 3) {
                break;
            }
            i3--;
            i6 = i7;
        }
        int length = iArr.length;
        int i8 = i3;
        int i9 = 0;
        boolean z2 = false;
        while (i3 < i5) {
            if (bitMatrix.get(i3, i4) != z2) {
                iArr2[i9] = iArr2[i9] + 1;
            } else {
                if (i9 != length - 1) {
                    i9++;
                } else if (patternMatchVariance(iArr2, iArr) < 0.42f) {
                    return new int[]{i8, i3};
                } else {
                    i8 += iArr2[0] + iArr2[1];
                    int i10 = i9 - 1;
                    System.arraycopy(iArr2, 2, iArr2, 0, i10);
                    iArr2[i10] = 0;
                    iArr2[i9] = 0;
                    i9--;
                }
                iArr2[i9] = 1;
                z2 = !z2;
            }
            i3++;
        }
        if (i9 != length - 1 || patternMatchVariance(iArr2, iArr) >= 0.42f) {
            return null;
        }
        return new int[]{i8, i3 - 1};
    }

    private static ResultPoint[] findRowsWithPattern(BitMatrix bitMatrix, int i3, int i4, int i5, int i6, int i7, int[] iArr) {
        boolean z2;
        int i8;
        int i9 = i3;
        ResultPoint[] resultPointArr = new ResultPoint[4];
        int[] iArr2 = new int[iArr.length];
        int i10 = i5;
        while (true) {
            if (i10 >= i9) {
                z2 = false;
                break;
            }
            int[] findGuardPattern = findGuardPattern(bitMatrix, i6, i10, i4, iArr, iArr2);
            if (findGuardPattern != null) {
                int i11 = i10;
                int[] iArr3 = findGuardPattern;
                while (i11 > 0) {
                    int i12 = i11 - 1;
                    int[] findGuardPattern2 = findGuardPattern(bitMatrix, i6, i12, i4, iArr, iArr2);
                    if (findGuardPattern2 == null) {
                        break;
                    }
                    iArr3 = findGuardPattern2;
                    i11 = i12;
                }
                float f2 = (float) i11;
                resultPointArr[0] = new ResultPoint((float) iArr3[0], f2);
                resultPointArr[1] = new ResultPoint((float) iArr3[1], f2);
                z2 = true;
                i10 = i11;
            } else {
                i10 += 5;
            }
        }
        int i13 = i10 + 1;
        if (z2) {
            int[] iArr4 = {(int) resultPointArr[0].getX(), (int) resultPointArr[1].getX()};
            int i14 = i13;
            int i15 = 0;
            while (true) {
                if (i14 >= i9) {
                    i8 = i15;
                    break;
                }
                i8 = i15;
                int[] findGuardPattern3 = findGuardPattern(bitMatrix, iArr4[0], i14, i4, iArr, iArr2);
                if (findGuardPattern3 != null && Math.abs(iArr4[0] - findGuardPattern3[0]) < 5 && Math.abs(iArr4[1] - findGuardPattern3[1]) < 5) {
                    iArr4 = findGuardPattern3;
                    i15 = 0;
                } else if (i8 > 25) {
                    break;
                } else {
                    i15 = i8 + 1;
                }
                i14++;
            }
            i13 = i14 - (i8 + 1);
            float f3 = (float) i13;
            resultPointArr[2] = new ResultPoint((float) iArr4[0], f3);
            resultPointArr[3] = new ResultPoint((float) iArr4[1], f3);
        }
        if (i13 - i10 < i7) {
            Arrays.fill(resultPointArr, (Object) null);
        }
        return resultPointArr;
    }

    private static ResultPoint[] findVertices(BitMatrix bitMatrix, int i3, int i4) {
        int i5;
        int i6;
        int i7;
        int height = bitMatrix.getHeight();
        int width = bitMatrix.getWidth();
        ResultPoint[] resultPointArr = new ResultPoint[8];
        copyToResult(resultPointArr, findRowsWithPattern(bitMatrix, height, width, i3, i4, 10, START_PATTERN), INDEXES_START_PATTERN);
        ResultPoint resultPoint = resultPointArr[4];
        if (resultPoint != null) {
            i4 = (int) resultPoint.getX();
            i3 = (int) resultPointArr[4].getY();
            ResultPoint resultPoint2 = resultPointArr[5];
            if (resultPoint2 != null) {
                i7 = i3;
                i6 = i4;
                i5 = (int) Math.max(((float) (((int) resultPoint2.getY()) - i3)) * 0.5f, 10.0f);
                copyToResult(resultPointArr, findRowsWithPattern(bitMatrix, height, width, i7, i6, i5, STOP_PATTERN), INDEXES_STOP_PATTERN);
                return resultPointArr;
            }
        }
        i7 = i3;
        i6 = i4;
        i5 = 10;
        copyToResult(resultPointArr, findRowsWithPattern(bitMatrix, height, width, i7, i6, i5, STOP_PATTERN), INDEXES_STOP_PATTERN);
        return resultPointArr;
    }

    private static float patternMatchVariance(int[] iArr, int[] iArr2) {
        int length = iArr.length;
        int i3 = 0;
        int i4 = 0;
        for (int i5 = 0; i5 < length; i5++) {
            i3 += iArr[i5];
            i4 += iArr2[i5];
        }
        if (i3 < i4) {
            return Float.POSITIVE_INFINITY;
        }
        float f2 = (float) i3;
        float f3 = f2 / ((float) i4);
        float f4 = MAX_INDIVIDUAL_VARIANCE * f3;
        float f5 = 0.0f;
        for (int i6 = 0; i6 < length; i6++) {
            int i7 = iArr[i6];
            float f6 = ((float) iArr2[i6]) * f3;
            float f7 = (float) i7;
            float f8 = f7 > f6 ? f7 - f6 : f6 - f7;
            if (f8 > f4) {
                return Float.POSITIVE_INFINITY;
            }
            f5 += f8;
        }
        return f5 / f2;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0028, code lost:
        if (r3.hasNext() == false) goto L_0x004c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x002a, code lost:
        r4 = (com.google.zxing.ResultPoint[]) r3.next();
        r7 = r4[1];
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0032, code lost:
        if (r7 == null) goto L_0x003e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0034, code lost:
        r2 = (int) java.lang.Math.max((float) r2, r7.getY());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x003e, code lost:
        r4 = r4[3];
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0040, code lost:
        if (r4 == null) goto L_0x0024;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0042, code lost:
        r2 = java.lang.Math.max(r2, (int) r4.getY());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x001d, code lost:
        if (r4 != 0) goto L_0x0020;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0020, code lost:
        r3 = r0.iterator();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.util.List<com.google.zxing.ResultPoint[]> detect(boolean r8, com.google.zxing.common.BitMatrix r9) {
        /*
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            r1 = 0
            r2 = r1
            r3 = r2
        L_0x0008:
            r4 = r3
        L_0x0009:
            int r5 = r9.getHeight()
            if (r2 >= r5) goto L_0x007a
            com.google.zxing.ResultPoint[] r3 = findVertices(r9, r2, r3)
            r5 = r3[r1]
            r6 = 1
            if (r5 != 0) goto L_0x0050
            r5 = 3
            r7 = r3[r5]
            if (r7 != 0) goto L_0x0050
            if (r4 != 0) goto L_0x0020
            goto L_0x007a
        L_0x0020:
            java.util.Iterator r3 = r0.iterator()
        L_0x0024:
            boolean r4 = r3.hasNext()
            if (r4 == 0) goto L_0x004c
            java.lang.Object r4 = r3.next()
            com.google.zxing.ResultPoint[] r4 = (com.google.zxing.ResultPoint[]) r4
            r7 = r4[r6]
            if (r7 == 0) goto L_0x003e
            float r2 = (float) r2
            float r7 = r7.getY()
            float r2 = java.lang.Math.max(r2, r7)
            int r2 = (int) r2
        L_0x003e:
            r4 = r4[r5]
            if (r4 == 0) goto L_0x0024
            float r4 = r4.getY()
            int r4 = (int) r4
            int r2 = java.lang.Math.max(r2, r4)
            goto L_0x0024
        L_0x004c:
            int r2 = r2 + 5
            r3 = r1
            goto L_0x0008
        L_0x0050:
            r0.add(r3)
            if (r8 != 0) goto L_0x0056
            goto L_0x007a
        L_0x0056:
            r2 = 2
            r4 = r3[r2]
            if (r4 == 0) goto L_0x0069
            float r4 = r4.getX()
            int r4 = (int) r4
            r2 = r3[r2]
            float r2 = r2.getY()
        L_0x0066:
            int r2 = (int) r2
            r3 = r4
            goto L_0x0078
        L_0x0069:
            r2 = 4
            r4 = r3[r2]
            float r4 = r4.getX()
            int r4 = (int) r4
            r2 = r3[r2]
            float r2 = r2.getY()
            goto L_0x0066
        L_0x0078:
            r4 = r6
            goto L_0x0009
        L_0x007a:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.pdf417.detector.Detector.detect(boolean, com.google.zxing.common.BitMatrix):java.util.List");
    }
}
