package com.google.zxing.qrcode.detector;

import com.google.zxing.DecodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.ResultPoint;
import com.google.zxing.ResultPointCallback;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.DetectorResult;
import com.google.zxing.common.GridSampler;
import com.google.zxing.common.PerspectiveTransform;
import com.google.zxing.common.detector.MathUtils;
import java.util.Map;

public class Detector {
    private final BitMatrix image;
    private ResultPointCallback resultPointCallback;

    public Detector(BitMatrix bitMatrix) {
        this.image = bitMatrix;
    }

    private float calculateModuleSizeOneWay(ResultPoint resultPoint, ResultPoint resultPoint2) {
        float sizeOfBlackWhiteBlackRunBothWays = sizeOfBlackWhiteBlackRunBothWays((int) resultPoint.getX(), (int) resultPoint.getY(), (int) resultPoint2.getX(), (int) resultPoint2.getY());
        float sizeOfBlackWhiteBlackRunBothWays2 = sizeOfBlackWhiteBlackRunBothWays((int) resultPoint2.getX(), (int) resultPoint2.getY(), (int) resultPoint.getX(), (int) resultPoint.getY());
        return Float.isNaN(sizeOfBlackWhiteBlackRunBothWays) ? sizeOfBlackWhiteBlackRunBothWays2 / 7.0f : Float.isNaN(sizeOfBlackWhiteBlackRunBothWays2) ? sizeOfBlackWhiteBlackRunBothWays / 7.0f : (sizeOfBlackWhiteBlackRunBothWays + sizeOfBlackWhiteBlackRunBothWays2) / 14.0f;
    }

    private static int computeDimension(ResultPoint resultPoint, ResultPoint resultPoint2, ResultPoint resultPoint3, float f2) throws NotFoundException {
        int round = (MathUtils.round(ResultPoint.distance(resultPoint, resultPoint3) / f2) + MathUtils.round(ResultPoint.distance(resultPoint, resultPoint2) / f2)) / 2;
        int i3 = round + 7;
        int i4 = i3 & 3;
        if (i4 == 0) {
            return round + 8;
        }
        if (i4 == 2) {
            return round + 6;
        }
        if (i4 != 3) {
            return i3;
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private static PerspectiveTransform createTransform(ResultPoint resultPoint, ResultPoint resultPoint2, ResultPoint resultPoint3, ResultPoint resultPoint4, int i3) {
        float f2;
        float f3;
        float f4;
        float f5 = ((float) i3) - 3.5f;
        if (resultPoint4 != null) {
            f3 = resultPoint4.getX();
            f2 = resultPoint4.getY();
            f4 = f5 - 3.0f;
        } else {
            float x2 = resultPoint3.getX() + (resultPoint2.getX() - resultPoint.getX());
            f3 = x2;
            f2 = resultPoint3.getY() + (resultPoint2.getY() - resultPoint.getY());
            f4 = f5;
        }
        return PerspectiveTransform.quadrilateralToQuadrilateral(3.5f, 3.5f, f5, 3.5f, f4, f4, 3.5f, f5, resultPoint.getX(), resultPoint.getY(), resultPoint2.getX(), resultPoint2.getY(), f3, f2, resultPoint3.getX(), resultPoint3.getY());
    }

    private static BitMatrix sampleGrid(BitMatrix bitMatrix, PerspectiveTransform perspectiveTransform, int i3) throws NotFoundException {
        return GridSampler.getInstance().sampleGrid(bitMatrix, i3, i3, perspectiveTransform);
    }

    private float sizeOfBlackWhiteBlackRun(int i3, int i4, int i5, int i6) {
        int i7;
        int i8;
        int i9;
        int i10;
        int i11;
        int i12;
        boolean z2;
        Detector detector;
        boolean z3;
        boolean z4 = true;
        boolean z5 = Math.abs(i6 - i4) > Math.abs(i5 - i3);
        if (z5) {
            i9 = i3;
            i10 = i4;
            i7 = i5;
            i8 = i6;
        } else {
            i10 = i3;
            i9 = i4;
            i8 = i5;
            i7 = i6;
        }
        int abs = Math.abs(i8 - i10);
        int abs2 = Math.abs(i7 - i9);
        int i13 = 2;
        int i14 = (-abs) / 2;
        int i15 = -1;
        int i16 = i10 < i8 ? 1 : -1;
        if (i9 < i7) {
            i15 = 1;
        }
        int i17 = i8 + i16;
        int i18 = i10;
        int i19 = i9;
        int i20 = 0;
        while (true) {
            if (i18 == i17) {
                i11 = i17;
                i12 = i13;
                break;
            }
            int i21 = z5 ? i19 : i18;
            int i22 = z5 ? i18 : i19;
            if (i20 == z4) {
                z2 = z5;
                z3 = z4;
                i11 = i17;
                detector = this;
            } else {
                detector = this;
                z2 = z5;
                i11 = i17;
                z3 = false;
            }
            if (z3 == detector.image.get(i21, i22)) {
                if (i20 == 2) {
                    return MathUtils.distance(i18, i19, i10, i9);
                }
                i20++;
            }
            i14 += abs2;
            if (i14 > 0) {
                if (i19 == i7) {
                    i12 = 2;
                    break;
                }
                i19 += i15;
                i14 -= abs;
            }
            i18 += i16;
            i17 = i11;
            z5 = z2;
            z4 = true;
            i13 = 2;
        }
        if (i20 == i12) {
            return MathUtils.distance(i11, i7, i10, i9);
        }
        return Float.NaN;
    }

    private float sizeOfBlackWhiteBlackRunBothWays(int i3, int i4, int i5, int i6) {
        float f2;
        float f3;
        float sizeOfBlackWhiteBlackRun = sizeOfBlackWhiteBlackRun(i3, i4, i5, i6);
        int i7 = i3 - (i5 - i3);
        int i8 = 0;
        if (i7 < 0) {
            f2 = ((float) i3) / ((float) (i3 - i7));
            i7 = 0;
        } else if (i7 >= this.image.getWidth()) {
            f2 = ((float) ((this.image.getWidth() - 1) - i3)) / ((float) (i7 - i3));
            i7 = this.image.getWidth() - 1;
        } else {
            f2 = 1.0f;
        }
        float f4 = (float) i4;
        int i9 = (int) (f4 - (((float) (i6 - i4)) * f2));
        if (i9 < 0) {
            f3 = f4 / ((float) (i4 - i9));
        } else if (i9 >= this.image.getHeight()) {
            f3 = ((float) ((this.image.getHeight() - 1) - i4)) / ((float) (i9 - i4));
            i8 = this.image.getHeight() - 1;
        } else {
            i8 = i9;
            f3 = 1.0f;
        }
        return (sizeOfBlackWhiteBlackRun + sizeOfBlackWhiteBlackRun(i3, i4, (int) ((((float) (i7 - i3)) * f3) + ((float) i3)), i8)) - 1.0f;
    }

    public final float calculateModuleSize(ResultPoint resultPoint, ResultPoint resultPoint2, ResultPoint resultPoint3) {
        return (calculateModuleSizeOneWay(resultPoint, resultPoint2) + calculateModuleSizeOneWay(resultPoint, resultPoint3)) / 2.0f;
    }

    public DetectorResult detect() throws NotFoundException, FormatException {
        return detect((Map<DecodeHintType, ?>) null);
    }

    public final AlignmentPattern findAlignmentInRegion(float f2, int i3, int i4, float f3) throws NotFoundException {
        int i5 = (int) (f3 * f2);
        int max = Math.max(0, i3 - i5);
        int min = Math.min(this.image.getWidth() - 1, i3 + i5) - max;
        float f4 = 3.0f * f2;
        if (((float) min) >= f4) {
            int max2 = Math.max(0, i4 - i5);
            int min2 = Math.min(this.image.getHeight() - 1, i4 + i5) - max2;
            if (((float) min2) >= f4) {
                return new AlignmentPatternFinder(this.image, max, max2, min, min2, f2, this.resultPointCallback).find();
            }
            throw NotFoundException.getNotFoundInstance();
        }
        throw NotFoundException.getNotFoundInstance();
    }

    public final BitMatrix getImage() {
        return this.image;
    }

    public final ResultPointCallback getResultPointCallback() {
        return this.resultPointCallback;
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x0084  */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x008d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.zxing.common.DetectorResult processFinderPatternInfo(com.google.zxing.qrcode.detector.FinderPatternInfo r11) throws com.google.zxing.NotFoundException, com.google.zxing.FormatException {
        /*
            r10 = this;
            com.google.zxing.qrcode.detector.FinderPattern r0 = r11.getTopLeft()
            com.google.zxing.qrcode.detector.FinderPattern r1 = r11.getTopRight()
            com.google.zxing.qrcode.detector.FinderPattern r11 = r11.getBottomLeft()
            float r2 = r10.calculateModuleSize(r0, r1, r11)
            r3 = 1065353216(0x3f800000, float:1.0)
            int r4 = (r2 > r3 ? 1 : (r2 == r3 ? 0 : -1))
            if (r4 < 0) goto L_0x009e
            int r4 = computeDimension(r0, r1, r11, r2)
            com.google.zxing.qrcode.decoder.Version r5 = com.google.zxing.qrcode.decoder.Version.getProvisionalVersionForDimension(r4)
            int r6 = r5.getDimensionForVersion()
            int r6 = r6 + -7
            int[] r5 = r5.getAlignmentPatternCenters()
            int r5 = r5.length
            r7 = 4
            if (r5 <= 0) goto L_0x0073
            float r5 = r1.getX()
            float r8 = r0.getX()
            float r5 = r5 - r8
            float r8 = r11.getX()
            float r8 = r8 + r5
            float r5 = r1.getY()
            float r9 = r0.getY()
            float r5 = r5 - r9
            float r9 = r11.getY()
            float r9 = r9 + r5
            r5 = 1077936128(0x40400000, float:3.0)
            float r6 = (float) r6
            float r5 = r5 / r6
            float r3 = r3 - r5
            float r5 = r0.getX()
            float r6 = r0.getX()
            float r8 = r8 - r6
            float r8 = r8 * r3
            float r8 = r8 + r5
            int r5 = (int) r8
            float r6 = r0.getY()
            float r8 = r0.getY()
            float r9 = r9 - r8
            float r9 = r9 * r3
            float r9 = r9 + r6
            int r3 = (int) r9
            r6 = r7
        L_0x0066:
            r8 = 16
            if (r6 > r8) goto L_0x0073
            float r8 = (float) r6
            com.google.zxing.qrcode.detector.AlignmentPattern r2 = r10.findAlignmentInRegion(r2, r5, r3, r8)     // Catch:{ NotFoundException -> 0x0070 }
            goto L_0x0074
        L_0x0070:
            int r6 = r6 << 1
            goto L_0x0066
        L_0x0073:
            r2 = 0
        L_0x0074:
            com.google.zxing.common.PerspectiveTransform r3 = createTransform(r0, r1, r11, r2, r4)
            com.google.zxing.common.BitMatrix r10 = r10.image
            com.google.zxing.common.BitMatrix r10 = sampleGrid(r10, r3, r4)
            r3 = 2
            r4 = 0
            r5 = 3
            r6 = 1
            if (r2 != 0) goto L_0x008d
            com.google.zxing.ResultPoint[] r2 = new com.google.zxing.ResultPoint[r5]
            r2[r4] = r11
            r2[r6] = r0
            r2[r3] = r1
            goto L_0x0098
        L_0x008d:
            com.google.zxing.ResultPoint[] r7 = new com.google.zxing.ResultPoint[r7]
            r7[r4] = r11
            r7[r6] = r0
            r7[r3] = r1
            r7[r5] = r2
            r2 = r7
        L_0x0098:
            com.google.zxing.common.DetectorResult r11 = new com.google.zxing.common.DetectorResult
            r11.<init>(r10, r2)
            return r11
        L_0x009e:
            com.google.zxing.NotFoundException r10 = com.google.zxing.NotFoundException.getNotFoundInstance()
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.qrcode.detector.Detector.processFinderPatternInfo(com.google.zxing.qrcode.detector.FinderPatternInfo):com.google.zxing.common.DetectorResult");
    }

    public final DetectorResult detect(Map<DecodeHintType, ?> map) throws NotFoundException, FormatException {
        ResultPointCallback resultPointCallback2;
        if (map == null) {
            resultPointCallback2 = null;
        } else {
            resultPointCallback2 = (ResultPointCallback) map.get(DecodeHintType.NEED_RESULT_POINT_CALLBACK);
        }
        this.resultPointCallback = resultPointCallback2;
        return processFinderPatternInfo(new FinderPatternFinder(this.image, this.resultPointCallback).find(map));
    }
}
