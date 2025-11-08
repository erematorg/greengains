package com.google.zxing.qrcode.detector;

import com.google.zxing.DecodeHintType;
import com.google.zxing.NotFoundException;
import com.google.zxing.ResultPoint;
import com.google.zxing.ResultPointCallback;
import com.google.zxing.common.BitMatrix;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class FinderPatternFinder {
    private static final int CENTER_QUORUM = 2;
    protected static final int MAX_MODULES = 97;
    protected static final int MIN_SKIP = 3;
    private static final EstimatedModuleComparator moduleComparator = new EstimatedModuleComparator();
    private final int[] crossCheckStateCount;
    private boolean hasSkipped;
    private final BitMatrix image;
    private final List<FinderPattern> possibleCenters;
    private final ResultPointCallback resultPointCallback;

    public static final class EstimatedModuleComparator implements Comparator<FinderPattern>, Serializable {
        private EstimatedModuleComparator() {
        }

        public int compare(FinderPattern finderPattern, FinderPattern finderPattern2) {
            return Float.compare(finderPattern.getEstimatedModuleSize(), finderPattern2.getEstimatedModuleSize());
        }
    }

    public FinderPatternFinder(BitMatrix bitMatrix) {
        this(bitMatrix, (ResultPointCallback) null);
    }

    private static float centerFromEnd(int[] iArr, int i3) {
        return ((float) ((i3 - iArr[4]) - iArr[3])) - (((float) iArr[2]) / 2.0f);
    }

    private boolean crossCheckDiagonal(int i3, int i4) {
        int i5;
        int i6;
        int i7;
        int[] crossCheckStateCount2 = getCrossCheckStateCount();
        int i8 = 0;
        while (i3 >= i8 && i4 >= i8 && this.image.get(i4 - i8, i3 - i8)) {
            crossCheckStateCount2[2] = crossCheckStateCount2[2] + 1;
            i8++;
        }
        if (crossCheckStateCount2[2] == 0) {
            return false;
        }
        while (i3 >= i8 && i4 >= i8 && !this.image.get(i4 - i8, i3 - i8)) {
            crossCheckStateCount2[1] = crossCheckStateCount2[1] + 1;
            i8++;
        }
        if (crossCheckStateCount2[1] == 0) {
            return false;
        }
        while (i3 >= i8 && i4 >= i8 && this.image.get(i4 - i8, i3 - i8)) {
            crossCheckStateCount2[0] = crossCheckStateCount2[0] + 1;
            i8++;
        }
        if (crossCheckStateCount2[0] == 0) {
            return false;
        }
        int height = this.image.getHeight();
        int width = this.image.getWidth();
        int i9 = 1;
        while (true) {
            int i10 = i3 + i9;
            if (i10 < height && (i7 = i4 + i9) < width && this.image.get(i7, i10)) {
                crossCheckStateCount2[2] = crossCheckStateCount2[2] + 1;
                i9++;
            }
        }
        while (true) {
            int i11 = i3 + i9;
            if (i11 < height && (i6 = i4 + i9) < width && !this.image.get(i6, i11)) {
                crossCheckStateCount2[3] = crossCheckStateCount2[3] + 1;
                i9++;
            }
        }
        if (crossCheckStateCount2[3] == 0) {
            return false;
        }
        while (true) {
            int i12 = i3 + i9;
            if (i12 < height && (i5 = i4 + i9) < width && this.image.get(i5, i12)) {
                crossCheckStateCount2[4] = crossCheckStateCount2[4] + 1;
                i9++;
            }
        }
        if (crossCheckStateCount2[4] == 0) {
            return false;
        }
        return foundPatternDiagonal(crossCheckStateCount2);
    }

    private float crossCheckHorizontal(int i3, int i4, int i5, int i6) {
        int i7;
        int i8;
        int i9;
        BitMatrix bitMatrix = this.image;
        int width = bitMatrix.getWidth();
        int[] crossCheckStateCount2 = getCrossCheckStateCount();
        int i10 = i3;
        while (i10 >= 0 && bitMatrix.get(i10, i4)) {
            crossCheckStateCount2[2] = crossCheckStateCount2[2] + 1;
            i10--;
        }
        if (i10 < 0) {
            return Float.NaN;
        }
        while (i10 >= 0 && !bitMatrix.get(i10, i4)) {
            int i11 = crossCheckStateCount2[1];
            if (i11 > i5) {
                break;
            }
            crossCheckStateCount2[1] = i11 + 1;
            i10--;
        }
        if (i10 < 0 || crossCheckStateCount2[1] > i5) {
            return Float.NaN;
        }
        while (i10 >= 0 && bitMatrix.get(i10, i4) && (i9 = crossCheckStateCount2[0]) <= i5) {
            crossCheckStateCount2[0] = i9 + 1;
            i10--;
        }
        if (crossCheckStateCount2[0] > i5) {
            return Float.NaN;
        }
        int i12 = i3 + 1;
        while (i12 < width && bitMatrix.get(i12, i4)) {
            crossCheckStateCount2[2] = crossCheckStateCount2[2] + 1;
            i12++;
        }
        if (i12 == width) {
            return Float.NaN;
        }
        while (i12 < width && !bitMatrix.get(i12, i4) && (i8 = crossCheckStateCount2[3]) < i5) {
            crossCheckStateCount2[3] = i8 + 1;
            i12++;
        }
        if (i12 == width || crossCheckStateCount2[3] >= i5) {
            return Float.NaN;
        }
        while (i12 < width && bitMatrix.get(i12, i4) && (i7 = crossCheckStateCount2[4]) < i5) {
            crossCheckStateCount2[4] = i7 + 1;
            i12++;
        }
        int i13 = crossCheckStateCount2[4];
        if (i13 < i5 && Math.abs(((((crossCheckStateCount2[0] + crossCheckStateCount2[1]) + crossCheckStateCount2[2]) + crossCheckStateCount2[3]) + i13) - i6) * 5 < i6 && foundPatternCross(crossCheckStateCount2)) {
            return centerFromEnd(crossCheckStateCount2, i12);
        }
        return Float.NaN;
    }

    private float crossCheckVertical(int i3, int i4, int i5, int i6) {
        int i7;
        int i8;
        int i9;
        BitMatrix bitMatrix = this.image;
        int height = bitMatrix.getHeight();
        int[] crossCheckStateCount2 = getCrossCheckStateCount();
        int i10 = i3;
        while (i10 >= 0 && bitMatrix.get(i4, i10)) {
            crossCheckStateCount2[2] = crossCheckStateCount2[2] + 1;
            i10--;
        }
        if (i10 < 0) {
            return Float.NaN;
        }
        while (i10 >= 0 && !bitMatrix.get(i4, i10)) {
            int i11 = crossCheckStateCount2[1];
            if (i11 > i5) {
                break;
            }
            crossCheckStateCount2[1] = i11 + 1;
            i10--;
        }
        if (i10 < 0 || crossCheckStateCount2[1] > i5) {
            return Float.NaN;
        }
        while (i10 >= 0 && bitMatrix.get(i4, i10) && (i9 = crossCheckStateCount2[0]) <= i5) {
            crossCheckStateCount2[0] = i9 + 1;
            i10--;
        }
        if (crossCheckStateCount2[0] > i5) {
            return Float.NaN;
        }
        int i12 = i3 + 1;
        while (i12 < height && bitMatrix.get(i4, i12)) {
            crossCheckStateCount2[2] = crossCheckStateCount2[2] + 1;
            i12++;
        }
        if (i12 == height) {
            return Float.NaN;
        }
        while (i12 < height && !bitMatrix.get(i4, i12) && (i8 = crossCheckStateCount2[3]) < i5) {
            crossCheckStateCount2[3] = i8 + 1;
            i12++;
        }
        if (i12 == height || crossCheckStateCount2[3] >= i5) {
            return Float.NaN;
        }
        while (i12 < height && bitMatrix.get(i4, i12) && (i7 = crossCheckStateCount2[4]) < i5) {
            crossCheckStateCount2[4] = i7 + 1;
            i12++;
        }
        int i13 = crossCheckStateCount2[4];
        if (i13 < i5 && Math.abs(((((crossCheckStateCount2[0] + crossCheckStateCount2[1]) + crossCheckStateCount2[2]) + crossCheckStateCount2[3]) + i13) - i6) * 5 < i6 * 2 && foundPatternCross(crossCheckStateCount2)) {
            return centerFromEnd(crossCheckStateCount2, i12);
        }
        return Float.NaN;
    }

    public static void doClearCounts(int[] iArr) {
        Arrays.fill(iArr, 0);
    }

    public static void doShiftCounts2(int[] iArr) {
        iArr[0] = iArr[2];
        iArr[1] = iArr[3];
        iArr[2] = iArr[4];
        iArr[3] = 1;
        iArr[4] = 0;
    }

    private int findRowSkip() {
        if (this.possibleCenters.size() <= 1) {
            return 0;
        }
        FinderPattern finderPattern = null;
        for (FinderPattern next : this.possibleCenters) {
            if (next.getCount() >= 2) {
                if (finderPattern == null) {
                    finderPattern = next;
                } else {
                    this.hasSkipped = true;
                    return ((int) (Math.abs(finderPattern.getX() - next.getX()) - Math.abs(finderPattern.getY() - next.getY()))) / 2;
                }
            }
        }
        return 0;
    }

    public static boolean foundPatternCross(int[] iArr) {
        int i3 = 0;
        for (int i4 = 0; i4 < 5; i4++) {
            int i5 = iArr[i4];
            if (i5 == 0) {
                return false;
            }
            i3 += i5;
        }
        if (i3 < 7) {
            return false;
        }
        float f2 = ((float) i3) / 7.0f;
        float f3 = f2 / 2.0f;
        return Math.abs(f2 - ((float) iArr[0])) < f3 && Math.abs(f2 - ((float) iArr[1])) < f3 && Math.abs((f2 * 3.0f) - ((float) iArr[2])) < 3.0f * f3 && Math.abs(f2 - ((float) iArr[3])) < f3 && Math.abs(f2 - ((float) iArr[4])) < f3;
    }

    public static boolean foundPatternDiagonal(int[] iArr) {
        int i3 = 0;
        for (int i4 = 0; i4 < 5; i4++) {
            int i5 = iArr[i4];
            if (i5 == 0) {
                return false;
            }
            i3 += i5;
        }
        if (i3 < 7) {
            return false;
        }
        float f2 = ((float) i3) / 7.0f;
        float f3 = f2 / 1.333f;
        return Math.abs(f2 - ((float) iArr[0])) < f3 && Math.abs(f2 - ((float) iArr[1])) < f3 && Math.abs((f2 * 3.0f) - ((float) iArr[2])) < 3.0f * f3 && Math.abs(f2 - ((float) iArr[3])) < f3 && Math.abs(f2 - ((float) iArr[4])) < f3;
    }

    private int[] getCrossCheckStateCount() {
        doClearCounts(this.crossCheckStateCount);
        return this.crossCheckStateCount;
    }

    private boolean haveMultiplyConfirmedCenters() {
        int size = this.possibleCenters.size();
        float f2 = 0.0f;
        int i3 = 0;
        float f3 = 0.0f;
        for (FinderPattern next : this.possibleCenters) {
            if (next.getCount() >= 2) {
                i3++;
                f3 += next.getEstimatedModuleSize();
            }
        }
        if (i3 < 3) {
            return false;
        }
        float f4 = f3 / ((float) size);
        for (FinderPattern estimatedModuleSize : this.possibleCenters) {
            f2 += Math.abs(estimatedModuleSize.getEstimatedModuleSize() - f4);
        }
        return f2 <= f3 * 0.05f;
    }

    /* JADX WARNING: Removed duplicated region for block: B:39:0x00db  */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x00e3 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.google.zxing.qrcode.detector.FinderPattern[] selectBestPatterns() throws com.google.zxing.NotFoundException {
        /*
            r26 = this;
            r0 = r26
            java.util.List<com.google.zxing.qrcode.detector.FinderPattern> r1 = r0.possibleCenters
            int r1 = r1.size()
            r2 = 3
            if (r1 < r2) goto L_0x00f5
            java.util.List<com.google.zxing.qrcode.detector.FinderPattern> r1 = r0.possibleCenters
            java.util.Iterator r1 = r1.iterator()
        L_0x0011:
            boolean r3 = r1.hasNext()
            r4 = 2
            if (r3 == 0) goto L_0x0028
            java.lang.Object r3 = r1.next()
            com.google.zxing.qrcode.detector.FinderPattern r3 = (com.google.zxing.qrcode.detector.FinderPattern) r3
            int r3 = r3.getCount()
            if (r3 >= r4) goto L_0x0011
            r1.remove()
            goto L_0x0011
        L_0x0028:
            java.util.List<com.google.zxing.qrcode.detector.FinderPattern> r1 = r0.possibleCenters
            com.google.zxing.qrcode.detector.FinderPatternFinder$EstimatedModuleComparator r3 = moduleComparator
            java.util.Collections.sort(r1, r3)
            com.google.zxing.qrcode.detector.FinderPattern[] r1 = new com.google.zxing.qrcode.detector.FinderPattern[r2]
            r2 = 0
            r3 = r2
            r7 = 9218868437227405311(0x7fefffffffffffff, double:1.7976931348623157E308)
        L_0x0038:
            java.util.List<com.google.zxing.qrcode.detector.FinderPattern> r9 = r0.possibleCenters
            int r9 = r9.size()
            int r9 = r9 - r4
            if (r3 >= r9) goto L_0x00e6
            java.util.List<com.google.zxing.qrcode.detector.FinderPattern> r9 = r0.possibleCenters
            java.lang.Object r9 = r9.get(r3)
            com.google.zxing.qrcode.detector.FinderPattern r9 = (com.google.zxing.qrcode.detector.FinderPattern) r9
            float r10 = r9.getEstimatedModuleSize()
            int r3 = r3 + 1
            r11 = r3
        L_0x0050:
            java.util.List<com.google.zxing.qrcode.detector.FinderPattern> r12 = r0.possibleCenters
            int r12 = r12.size()
            r13 = 1
            int r12 = r12 - r13
            if (r11 >= r12) goto L_0x0038
            java.util.List<com.google.zxing.qrcode.detector.FinderPattern> r12 = r0.possibleCenters
            java.lang.Object r12 = r12.get(r11)
            com.google.zxing.qrcode.detector.FinderPattern r12 = (com.google.zxing.qrcode.detector.FinderPattern) r12
            double r14 = squaredDistance(r9, r12)
            int r11 = r11 + 1
            r5 = r11
        L_0x0069:
            java.util.List<com.google.zxing.qrcode.detector.FinderPattern> r6 = r0.possibleCenters
            int r6 = r6.size()
            if (r5 >= r6) goto L_0x0050
            java.util.List<com.google.zxing.qrcode.detector.FinderPattern> r6 = r0.possibleCenters
            java.lang.Object r6 = r6.get(r5)
            com.google.zxing.qrcode.detector.FinderPattern r6 = (com.google.zxing.qrcode.detector.FinderPattern) r6
            float r16 = r6.getEstimatedModuleSize()
            r17 = 1068708659(0x3fb33333, float:1.4)
            float r17 = r17 * r10
            int r16 = (r16 > r17 ? 1 : (r16 == r17 ? 0 : -1))
            if (r16 <= 0) goto L_0x0087
            goto L_0x00e3
        L_0x0087:
            double r16 = squaredDistance(r12, r6)
            double r18 = squaredDistance(r9, r6)
            int r20 = (r14 > r16 ? 1 : (r14 == r16 ? 0 : -1))
            if (r20 >= 0) goto L_0x00ac
            int r20 = (r16 > r18 ? 1 : (r16 == r18 ? 0 : -1))
            if (r20 <= 0) goto L_0x00a3
            int r20 = (r14 > r18 ? 1 : (r14 == r18 ? 0 : -1))
            if (r20 >= 0) goto L_0x009e
            r20 = r14
            goto L_0x00c3
        L_0x009e:
            r20 = r18
        L_0x00a0:
            r18 = r14
            goto L_0x00c3
        L_0x00a3:
            r20 = r14
            r24 = r16
            r16 = r18
            r18 = r24
            goto L_0x00c3
        L_0x00ac:
            int r20 = (r16 > r18 ? 1 : (r16 == r18 ? 0 : -1))
            if (r20 >= 0) goto L_0x00be
            int r20 = (r14 > r18 ? 1 : (r14 == r18 ? 0 : -1))
            if (r20 >= 0) goto L_0x00b9
            r20 = r16
            r16 = r18
            goto L_0x00a0
        L_0x00b9:
            r20 = r16
        L_0x00bb:
            r16 = r14
            goto L_0x00c3
        L_0x00be:
            r20 = r18
            r18 = r16
            goto L_0x00bb
        L_0x00c3:
            r22 = 4611686018427387904(0x4000000000000000, double:2.0)
            double r18 = r18 * r22
            double r18 = r16 - r18
            double r18 = java.lang.Math.abs(r18)
            double r20 = r20 * r22
            double r16 = r16 - r20
            double r16 = java.lang.Math.abs(r16)
            double r16 = r16 + r18
            int r18 = (r16 > r7 ? 1 : (r16 == r7 ? 0 : -1))
            if (r18 >= 0) goto L_0x00e3
            r1[r2] = r9
            r1[r13] = r12
            r1[r4] = r6
            r7 = r16
        L_0x00e3:
            int r5 = r5 + 1
            goto L_0x0069
        L_0x00e6:
            r5 = 9218868437227405311(0x7fefffffffffffff, double:1.7976931348623157E308)
            int r0 = (r7 > r5 ? 1 : (r7 == r5 ? 0 : -1))
            if (r0 == 0) goto L_0x00f0
            return r1
        L_0x00f0:
            com.google.zxing.NotFoundException r0 = com.google.zxing.NotFoundException.getNotFoundInstance()
            throw r0
        L_0x00f5:
            com.google.zxing.NotFoundException r0 = com.google.zxing.NotFoundException.getNotFoundInstance()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.qrcode.detector.FinderPatternFinder.selectBestPatterns():com.google.zxing.qrcode.detector.FinderPattern[]");
    }

    private static double squaredDistance(FinderPattern finderPattern, FinderPattern finderPattern2) {
        double x2 = (double) (finderPattern.getX() - finderPattern2.getX());
        double y2 = (double) (finderPattern.getY() - finderPattern2.getY());
        return (y2 * y2) + (x2 * x2);
    }

    @Deprecated
    public final void clearCounts(int[] iArr) {
        doClearCounts(iArr);
    }

    public final FinderPatternInfo find(Map<DecodeHintType, ?> map) throws NotFoundException {
        boolean z2 = map != null && map.containsKey(DecodeHintType.TRY_HARDER);
        int height = this.image.getHeight();
        int width = this.image.getWidth();
        int i3 = (height * 3) / 388;
        if (i3 < 3 || z2) {
            i3 = 3;
        }
        int[] iArr = new int[5];
        int i4 = i3 - 1;
        boolean z3 = false;
        while (i4 < height && !z3) {
            doClearCounts(iArr);
            int i5 = 0;
            int i6 = 0;
            while (i5 < width) {
                if (this.image.get(i5, i4)) {
                    if ((i6 & 1) == 1) {
                        i6++;
                    }
                    iArr[i6] = iArr[i6] + 1;
                } else if ((i6 & 1) != 0) {
                    iArr[i6] = iArr[i6] + 1;
                } else if (i6 == 4) {
                    if (!foundPatternCross(iArr)) {
                        doShiftCounts2(iArr);
                    } else if (handlePossibleCenter(iArr, i4, i5)) {
                        if (this.hasSkipped) {
                            z3 = haveMultiplyConfirmedCenters();
                        } else {
                            int findRowSkip = findRowSkip();
                            int i7 = iArr[2];
                            if (findRowSkip > i7) {
                                i4 += (findRowSkip - i7) - 2;
                                i5 = width - 1;
                            }
                        }
                        doClearCounts(iArr);
                        i3 = 2;
                        i6 = 0;
                    } else {
                        doShiftCounts2(iArr);
                    }
                    i6 = 3;
                } else {
                    i6++;
                    iArr[i6] = iArr[i6] + 1;
                }
                i5++;
            }
            if (foundPatternCross(iArr) && handlePossibleCenter(iArr, i4, width)) {
                i3 = iArr[0];
                if (this.hasSkipped) {
                    z3 = haveMultiplyConfirmedCenters();
                }
            }
            i4 += i3;
        }
        FinderPattern[] selectBestPatterns = selectBestPatterns();
        ResultPoint.orderBestPatterns(selectBestPatterns);
        return new FinderPatternInfo(selectBestPatterns);
    }

    public final BitMatrix getImage() {
        return this.image;
    }

    public final List<FinderPattern> getPossibleCenters() {
        return this.possibleCenters;
    }

    @Deprecated
    public final boolean handlePossibleCenter(int[] iArr, int i3, int i4, boolean z2) {
        return handlePossibleCenter(iArr, i3, i4);
    }

    @Deprecated
    public final void shiftCounts2(int[] iArr) {
        doShiftCounts2(iArr);
    }

    public FinderPatternFinder(BitMatrix bitMatrix, ResultPointCallback resultPointCallback2) {
        this.image = bitMatrix;
        this.possibleCenters = new ArrayList();
        this.crossCheckStateCount = new int[5];
        this.resultPointCallback = resultPointCallback2;
    }

    public final boolean handlePossibleCenter(int[] iArr, int i3, int i4) {
        int i5 = 0;
        int i6 = iArr[0] + iArr[1] + iArr[2] + iArr[3] + iArr[4];
        int centerFromEnd = (int) centerFromEnd(iArr, i4);
        float crossCheckVertical = crossCheckVertical(i3, centerFromEnd, iArr[2], i6);
        if (!Float.isNaN(crossCheckVertical)) {
            int i7 = (int) crossCheckVertical;
            float crossCheckHorizontal = crossCheckHorizontal(centerFromEnd, i7, iArr[2], i6);
            if (!Float.isNaN(crossCheckHorizontal) && crossCheckDiagonal(i7, (int) crossCheckHorizontal)) {
                float f2 = ((float) i6) / 7.0f;
                while (true) {
                    if (i5 < this.possibleCenters.size()) {
                        FinderPattern finderPattern = this.possibleCenters.get(i5);
                        if (finderPattern.aboutEquals(f2, crossCheckVertical, crossCheckHorizontal)) {
                            this.possibleCenters.set(i5, finderPattern.combineEstimate(crossCheckVertical, crossCheckHorizontal, f2));
                            break;
                        }
                        i5++;
                    } else {
                        FinderPattern finderPattern2 = new FinderPattern(crossCheckHorizontal, crossCheckVertical, f2);
                        this.possibleCenters.add(finderPattern2);
                        ResultPointCallback resultPointCallback2 = this.resultPointCallback;
                        if (resultPointCallback2 != null) {
                            resultPointCallback2.foundPossibleResultPoint(finderPattern2);
                        }
                    }
                }
                return true;
            }
        }
        return false;
    }
}
