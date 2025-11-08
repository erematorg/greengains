package com.google.zxing.aztec.detector;

import android.support.v4.media.session.a;
import com.google.zxing.NotFoundException;
import com.google.zxing.ResultPoint;
import com.google.zxing.aztec.AztecDetectorResult;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.GridSampler;
import com.google.zxing.common.detector.MathUtils;
import com.google.zxing.common.detector.WhiteRectangleDetector;
import com.google.zxing.common.reedsolomon.GenericGF;
import com.google.zxing.common.reedsolomon.ReedSolomonDecoder;
import com.google.zxing.common.reedsolomon.ReedSolomonException;
import kotlin.text.Typography;

public final class Detector {
    private static final int[] EXPECTED_CORNER_BITS = {3808, 476, 2107, 1799};
    private boolean compact;
    private final BitMatrix image;
    private int nbCenterLayers;
    private int nbDataBlocks;
    private int nbLayers;
    private int shift;

    public static final class CorrectedParameter {
        private final int data;
        private final int errorsCorrected;

        public CorrectedParameter(int i3, int i4) {
            this.data = i3;
            this.errorsCorrected = i4;
        }

        public int getData() {
            return this.data;
        }

        public int getErrorsCorrected() {
            return this.errorsCorrected;
        }
    }

    public static final class Point {

        /* renamed from: x  reason: collision with root package name */
        private final int f7192x;

        /* renamed from: y  reason: collision with root package name */
        private final int f7193y;

        public Point(int i3, int i4) {
            this.f7192x = i3;
            this.f7193y = i4;
        }

        public int getX() {
            return this.f7192x;
        }

        public int getY() {
            return this.f7193y;
        }

        public ResultPoint toResultPoint() {
            return new ResultPoint((float) this.f7192x, (float) this.f7193y);
        }

        public String toString() {
            StringBuilder sb = new StringBuilder("<");
            sb.append(this.f7192x);
            sb.append(' ');
            return a.p(sb, this.f7193y, Typography.greater);
        }
    }

    public Detector(BitMatrix bitMatrix) {
        this.image = bitMatrix;
    }

    private static float distance(Point point, Point point2) {
        return MathUtils.distance(point.getX(), point.getY(), point2.getX(), point2.getY());
    }

    private static ResultPoint[] expandSquare(ResultPoint[] resultPointArr, int i3, int i4) {
        float f2 = ((float) i4) / (((float) i3) * 2.0f);
        float x2 = resultPointArr[0].getX() - resultPointArr[2].getX();
        float y2 = resultPointArr[0].getY() - resultPointArr[2].getY();
        float x3 = (resultPointArr[2].getX() + resultPointArr[0].getX()) / 2.0f;
        float y3 = (resultPointArr[2].getY() + resultPointArr[0].getY()) / 2.0f;
        float f3 = x2 * f2;
        float f4 = y2 * f2;
        ResultPoint resultPoint = new ResultPoint(x3 + f3, y3 + f4);
        ResultPoint resultPoint2 = new ResultPoint(x3 - f3, y3 - f4);
        float x4 = resultPointArr[1].getX() - resultPointArr[3].getX();
        float y4 = resultPointArr[1].getY() - resultPointArr[3].getY();
        float x5 = (resultPointArr[3].getX() + resultPointArr[1].getX()) / 2.0f;
        float y5 = (resultPointArr[3].getY() + resultPointArr[1].getY()) / 2.0f;
        float f5 = x4 * f2;
        float f6 = f2 * y4;
        return new ResultPoint[]{resultPoint, new ResultPoint(x5 + f5, y5 + f6), resultPoint2, new ResultPoint(x5 - f5, y5 - f6)};
    }

    private int extractParameters(ResultPoint[] resultPointArr) throws NotFoundException {
        long j2;
        long j3;
        if (!isValid(resultPointArr[0]) || !isValid(resultPointArr[1]) || !isValid(resultPointArr[2]) || !isValid(resultPointArr[3])) {
            throw NotFoundException.getNotFoundInstance();
        }
        int i3 = this.nbCenterLayers * 2;
        int[] iArr = {sampleLine(resultPointArr[0], resultPointArr[1], i3), sampleLine(resultPointArr[1], resultPointArr[2], i3), sampleLine(resultPointArr[2], resultPointArr[3], i3), sampleLine(resultPointArr[3], resultPointArr[0], i3)};
        this.shift = getRotation(iArr, i3);
        long j4 = 0;
        for (int i4 = 0; i4 < 4; i4++) {
            int i5 = iArr[(this.shift + i4) % 4];
            if (this.compact) {
                j2 = j4 << 7;
                j3 = (long) ((i5 >> 1) & 127);
            } else {
                j2 = j4 << 10;
                j3 = (long) (((i5 >> 2) & 992) + ((i5 >> 1) & 31));
            }
            j4 = j2 + j3;
        }
        CorrectedParameter correctedParameterData = getCorrectedParameterData(j4, this.compact);
        int data = correctedParameterData.getData();
        if (this.compact) {
            this.nbLayers = (data >> 6) + 1;
            this.nbDataBlocks = (data & 63) + 1;
        } else {
            this.nbLayers = (data >> 11) + 1;
            this.nbDataBlocks = (data & 2047) + 1;
        }
        return correctedParameterData.getErrorsCorrected();
    }

    private ResultPoint[] getBullsEyeCorners(Point point) throws NotFoundException {
        boolean z2 = true;
        this.nbCenterLayers = 1;
        Point point2 = point;
        Point point3 = point2;
        Point point4 = point3;
        boolean z3 = true;
        while (this.nbCenterLayers < 9) {
            Point firstDifferent = getFirstDifferent(point, z3, 1, -1);
            Point firstDifferent2 = getFirstDifferent(point2, z3, 1, 1);
            Point firstDifferent3 = getFirstDifferent(point3, z3, -1, 1);
            Point firstDifferent4 = getFirstDifferent(point4, z3, -1, -1);
            if (this.nbCenterLayers > 2) {
                double distance = (double) ((distance(firstDifferent4, firstDifferent) * ((float) this.nbCenterLayers)) / (distance(point4, point) * ((float) (this.nbCenterLayers + 2))));
                if (distance < 0.75d || distance > 1.25d || !isWhiteOrBlackRectangle(firstDifferent, firstDifferent2, firstDifferent3, firstDifferent4)) {
                    break;
                }
            }
            z3 = !z3;
            this.nbCenterLayers++;
            point4 = firstDifferent4;
            point = firstDifferent;
            point2 = firstDifferent2;
            point3 = firstDifferent3;
        }
        int i3 = this.nbCenterLayers;
        if (i3 == 5 || i3 == 7) {
            if (i3 != 5) {
                z2 = false;
            }
            this.compact = z2;
            int i4 = this.nbCenterLayers;
            return expandSquare(new ResultPoint[]{new ResultPoint(((float) point.getX()) + 0.5f, ((float) point.getY()) - 0.5f), new ResultPoint(((float) point2.getX()) + 0.5f, ((float) point2.getY()) + 0.5f), new ResultPoint(((float) point3.getX()) - 0.5f, ((float) point3.getY()) + 0.5f), new ResultPoint(((float) point4.getX()) - 0.5f, ((float) point4.getY()) - 0.5f)}, (i4 * 2) - 3, i4 * 2);
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private int getColor(Point point, Point point2) {
        float distance = distance(point, point2);
        boolean z2 = false;
        if (distance == 0.0f) {
            return 0;
        }
        float x2 = ((float) (point2.getX() - point.getX())) / distance;
        float y2 = ((float) (point2.getY() - point.getY())) / distance;
        float x3 = (float) point.getX();
        float y3 = (float) point.getY();
        boolean z3 = this.image.get(point.getX(), point.getY());
        int floor = (int) Math.floor((double) distance);
        int i3 = 0;
        for (int i4 = 0; i4 < floor; i4++) {
            if (this.image.get(MathUtils.round(x3), MathUtils.round(y3)) != z3) {
                i3++;
            }
            x3 += x2;
            y3 += y2;
        }
        float f2 = ((float) i3) / distance;
        if (f2 > 0.1f && f2 < 0.9f) {
            return 0;
        }
        if (f2 <= 0.1f) {
            z2 = true;
        }
        return z2 == z3 ? 1 : -1;
    }

    private static CorrectedParameter getCorrectedParameterData(long j2, boolean z2) throws NotFoundException {
        int i3;
        int i4;
        if (z2) {
            i3 = 7;
            i4 = 2;
        } else {
            i3 = 10;
            i4 = 4;
        }
        int i5 = i3 - i4;
        int[] iArr = new int[i3];
        for (int i6 = i3 - 1; i6 >= 0; i6--) {
            iArr[i6] = ((int) j2) & 15;
            j2 >>= 4;
        }
        try {
            int decodeWithECCount = new ReedSolomonDecoder(GenericGF.AZTEC_PARAM).decodeWithECCount(iArr, i5);
            int i7 = 0;
            for (int i8 = 0; i8 < i4; i8++) {
                i7 = (i7 << 4) + iArr[i8];
            }
            return new CorrectedParameter(i7, decodeWithECCount);
        } catch (ReedSolomonException unused) {
            throw NotFoundException.getNotFoundInstance();
        }
    }

    private int getDimension() {
        if (this.compact) {
            return (this.nbLayers * 4) + 11;
        }
        int i3 = this.nbLayers;
        return ((((i3 * 2) + 6) / 15) * 2) + (i3 * 4) + 15;
    }

    private Point getFirstDifferent(Point point, boolean z2, int i3, int i4) {
        int x2 = point.getX() + i3;
        int y2 = point.getY();
        while (true) {
            y2 += i4;
            if (!isValid(x2, y2) || this.image.get(x2, y2) != z2) {
                int i5 = x2 - i3;
                int i6 = y2 - i4;
            } else {
                x2 += i3;
            }
        }
        int i52 = x2 - i3;
        int i62 = y2 - i4;
        while (isValid(i52, i62) && this.image.get(i52, i62) == z2) {
            i52 += i3;
        }
        int i7 = i52 - i3;
        while (isValid(i7, i62) && this.image.get(i7, i62) == z2) {
            i62 += i4;
        }
        return new Point(i7, i62 - i4);
    }

    private Point getMatrixCenter() {
        ResultPoint resultPoint;
        ResultPoint resultPoint2;
        ResultPoint resultPoint3;
        ResultPoint resultPoint4;
        ResultPoint resultPoint5;
        ResultPoint resultPoint6;
        ResultPoint resultPoint7;
        ResultPoint resultPoint8;
        try {
            ResultPoint[] detect = new WhiteRectangleDetector(this.image).detect();
            resultPoint3 = detect[0];
            resultPoint2 = detect[1];
            resultPoint = detect[2];
            resultPoint4 = detect[3];
        } catch (NotFoundException unused) {
            int width = this.image.getWidth() / 2;
            int height = this.image.getHeight() / 2;
            int i3 = width + 7;
            int i4 = height - 7;
            ResultPoint resultPoint9 = getFirstDifferent(new Point(i3, i4), false, 1, -1).toResultPoint();
            int i5 = height + 7;
            ResultPoint resultPoint10 = getFirstDifferent(new Point(i3, i5), false, 1, 1).toResultPoint();
            int i6 = width - 7;
            ResultPoint resultPoint11 = getFirstDifferent(new Point(i6, i5), false, -1, 1).toResultPoint();
            resultPoint4 = getFirstDifferent(new Point(i6, i4), false, -1, -1).toResultPoint();
            ResultPoint resultPoint12 = resultPoint10;
            resultPoint = resultPoint11;
            resultPoint3 = resultPoint9;
            resultPoint2 = resultPoint12;
        }
        int round = MathUtils.round((resultPoint.getX() + (resultPoint2.getX() + (resultPoint4.getX() + resultPoint3.getX()))) / 4.0f);
        int round2 = MathUtils.round((resultPoint.getY() + (resultPoint2.getY() + (resultPoint4.getY() + resultPoint3.getY()))) / 4.0f);
        try {
            ResultPoint[] detect2 = new WhiteRectangleDetector(this.image, 15, round, round2).detect();
            resultPoint7 = detect2[0];
            resultPoint6 = detect2[1];
            resultPoint8 = detect2[2];
            resultPoint5 = detect2[3];
        } catch (NotFoundException unused2) {
            int i7 = round + 7;
            int i8 = round2 - 7;
            resultPoint7 = this.getFirstDifferent(new Point(i7, i8), false, 1, -1).toResultPoint();
            int i9 = round2 + 7;
            resultPoint6 = this.getFirstDifferent(new Point(i7, i9), false, 1, 1).toResultPoint();
            int i10 = round - 7;
            resultPoint8 = this.getFirstDifferent(new Point(i10, i9), false, -1, 1).toResultPoint();
            resultPoint5 = this.getFirstDifferent(new Point(i10, i8), false, -1, -1).toResultPoint();
        }
        return new Point(MathUtils.round((resultPoint8.getX() + (resultPoint6.getX() + (resultPoint5.getX() + resultPoint7.getX()))) / 4.0f), MathUtils.round((resultPoint8.getY() + (resultPoint6.getY() + (resultPoint5.getY() + resultPoint7.getY()))) / 4.0f));
    }

    private ResultPoint[] getMatrixCornerPoints(ResultPoint[] resultPointArr) {
        return expandSquare(resultPointArr, this.nbCenterLayers * 2, getDimension());
    }

    private static int getRotation(int[] iArr, int i3) throws NotFoundException {
        int i4 = 0;
        for (int i5 : iArr) {
            i4 = (i4 << 3) + ((i5 >> (i3 - 2)) << 1) + (i5 & 1);
        }
        int i6 = ((i4 & 1) << 11) + (i4 >> 1);
        for (int i7 = 0; i7 < 4; i7++) {
            if (Integer.bitCount(EXPECTED_CORNER_BITS[i7] ^ i6) <= 2) {
                return i7;
            }
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private boolean isValid(int i3, int i4) {
        return i3 >= 0 && i3 < this.image.getWidth() && i4 >= 0 && i4 < this.image.getHeight();
    }

    private boolean isWhiteOrBlackRectangle(Point point, Point point2, Point point3, Point point4) {
        Point point5 = new Point(Math.max(0, point.getX() - 3), Math.min(this.image.getHeight() - 1, point.getY() + 3));
        Point point6 = new Point(Math.max(0, point2.getX() - 3), Math.max(0, point2.getY() - 3));
        Point point7 = new Point(Math.min(this.image.getWidth() - 1, point3.getX() + 3), Math.max(0, Math.min(this.image.getHeight() - 1, point3.getY() - 3)));
        Point point8 = new Point(Math.min(this.image.getWidth() - 1, point4.getX() + 3), Math.min(this.image.getHeight() - 1, point4.getY() + 3));
        int color = getColor(point8, point5);
        return color != 0 && getColor(point5, point6) == color && getColor(point6, point7) == color && getColor(point7, point8) == color;
    }

    private BitMatrix sampleGrid(BitMatrix bitMatrix, ResultPoint resultPoint, ResultPoint resultPoint2, ResultPoint resultPoint3, ResultPoint resultPoint4) throws NotFoundException {
        GridSampler instance = GridSampler.getInstance();
        int dimension = getDimension();
        int i3 = dimension;
        int i4 = dimension;
        float f2 = ((float) dimension) / 2.0f;
        int i5 = this.nbCenterLayers;
        float f3 = f2 - ((float) i5);
        float f4 = f2 + ((float) i5);
        return instance.sampleGrid(bitMatrix, i4, i3, f3, f3, f4, f3, f4, f4, f3, f4, resultPoint.getX(), resultPoint.getY(), resultPoint2.getX(), resultPoint2.getY(), resultPoint3.getX(), resultPoint3.getY(), resultPoint4.getX(), resultPoint4.getY());
    }

    private int sampleLine(ResultPoint resultPoint, ResultPoint resultPoint2, int i3) {
        float distance = distance(resultPoint, resultPoint2);
        float f2 = distance / ((float) i3);
        float x2 = resultPoint.getX();
        float y2 = resultPoint.getY();
        float x3 = ((resultPoint2.getX() - resultPoint.getX()) * f2) / distance;
        float y3 = ((resultPoint2.getY() - resultPoint.getY()) * f2) / distance;
        int i4 = 0;
        for (int i5 = 0; i5 < i3; i5++) {
            float f3 = (float) i5;
            if (this.image.get(MathUtils.round((f3 * x3) + x2), MathUtils.round((f3 * y3) + y2))) {
                i4 |= 1 << ((i3 - i5) - 1);
            }
        }
        return i4;
    }

    public AztecDetectorResult detect() throws NotFoundException {
        return detect(false);
    }

    private static float distance(ResultPoint resultPoint, ResultPoint resultPoint2) {
        return MathUtils.distance(resultPoint.getX(), resultPoint.getY(), resultPoint2.getX(), resultPoint2.getY());
    }

    private boolean isValid(ResultPoint resultPoint) {
        return isValid(MathUtils.round(resultPoint.getX()), MathUtils.round(resultPoint.getY()));
    }

    public AztecDetectorResult detect(boolean z2) throws NotFoundException {
        ResultPoint[] bullsEyeCorners = getBullsEyeCorners(getMatrixCenter());
        if (z2) {
            ResultPoint resultPoint = bullsEyeCorners[0];
            bullsEyeCorners[0] = bullsEyeCorners[2];
            bullsEyeCorners[2] = resultPoint;
        }
        int extractParameters = extractParameters(bullsEyeCorners);
        BitMatrix bitMatrix = this.image;
        int i3 = this.shift;
        return new AztecDetectorResult(sampleGrid(bitMatrix, bullsEyeCorners[i3 % 4], bullsEyeCorners[(i3 + 1) % 4], bullsEyeCorners[(i3 + 2) % 4], bullsEyeCorners[(i3 + 3) % 4]), getMatrixCornerPoints(bullsEyeCorners), this.compact, this.nbDataBlocks, this.nbLayers, extractParameters);
    }
}
