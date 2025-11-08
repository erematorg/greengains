package com.google.zxing.datamatrix.detector;

import com.google.zxing.NotFoundException;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.DetectorResult;
import com.google.zxing.common.GridSampler;
import com.google.zxing.common.detector.WhiteRectangleDetector;

public final class Detector {
    private final BitMatrix image;
    private final WhiteRectangleDetector rectangleDetector;

    public Detector(BitMatrix bitMatrix) throws NotFoundException {
        this.image = bitMatrix;
        this.rectangleDetector = new WhiteRectangleDetector(bitMatrix);
    }

    private ResultPoint correctTopRight(ResultPoint[] resultPointArr) {
        ResultPoint resultPoint = resultPointArr[0];
        ResultPoint resultPoint2 = resultPointArr[1];
        ResultPoint resultPoint3 = resultPointArr[2];
        ResultPoint resultPoint4 = resultPointArr[3];
        int transitionsBetween = transitionsBetween(resultPoint, resultPoint4);
        ResultPoint shiftPoint = shiftPoint(resultPoint, resultPoint2, (transitionsBetween(resultPoint2, resultPoint4) + 1) * 4);
        ResultPoint shiftPoint2 = shiftPoint(resultPoint3, resultPoint2, (transitionsBetween + 1) * 4);
        int transitionsBetween2 = transitionsBetween(shiftPoint, resultPoint4);
        int transitionsBetween3 = transitionsBetween(shiftPoint2, resultPoint4);
        float f2 = (float) (transitionsBetween2 + 1);
        ResultPoint resultPoint5 = new ResultPoint(((resultPoint3.getX() - resultPoint2.getX()) / f2) + resultPoint4.getX(), ((resultPoint3.getY() - resultPoint2.getY()) / f2) + resultPoint4.getY());
        float f3 = (float) (transitionsBetween3 + 1);
        ResultPoint resultPoint6 = new ResultPoint(((resultPoint.getX() - resultPoint2.getX()) / f3) + resultPoint4.getX(), ((resultPoint.getY() - resultPoint2.getY()) / f3) + resultPoint4.getY());
        if (isValid(resultPoint5)) {
            return (isValid(resultPoint6) && transitionsBetween(shiftPoint, resultPoint5) + transitionsBetween(shiftPoint2, resultPoint5) <= transitionsBetween(shiftPoint, resultPoint6) + transitionsBetween(shiftPoint2, resultPoint6)) ? resultPoint6 : resultPoint5;
        }
        if (isValid(resultPoint6)) {
            return resultPoint6;
        }
        return null;
    }

    private ResultPoint[] detectSolid1(ResultPoint[] resultPointArr) {
        ResultPoint resultPoint = resultPointArr[0];
        ResultPoint resultPoint2 = resultPointArr[1];
        ResultPoint resultPoint3 = resultPointArr[3];
        ResultPoint resultPoint4 = resultPointArr[2];
        int transitionsBetween = transitionsBetween(resultPoint, resultPoint2);
        int transitionsBetween2 = transitionsBetween(resultPoint2, resultPoint3);
        int transitionsBetween3 = transitionsBetween(resultPoint3, resultPoint4);
        int transitionsBetween4 = transitionsBetween(resultPoint4, resultPoint);
        ResultPoint[] resultPointArr2 = {resultPoint4, resultPoint, resultPoint2, resultPoint3};
        if (transitionsBetween > transitionsBetween2) {
            resultPointArr2[0] = resultPoint;
            resultPointArr2[1] = resultPoint2;
            resultPointArr2[2] = resultPoint3;
            resultPointArr2[3] = resultPoint4;
            transitionsBetween = transitionsBetween2;
        }
        if (transitionsBetween > transitionsBetween3) {
            resultPointArr2[0] = resultPoint2;
            resultPointArr2[1] = resultPoint3;
            resultPointArr2[2] = resultPoint4;
            resultPointArr2[3] = resultPoint;
        } else {
            transitionsBetween3 = transitionsBetween;
        }
        if (transitionsBetween3 > transitionsBetween4) {
            resultPointArr2[0] = resultPoint3;
            resultPointArr2[1] = resultPoint4;
            resultPointArr2[2] = resultPoint;
            resultPointArr2[3] = resultPoint2;
        }
        return resultPointArr2;
    }

    private ResultPoint[] detectSolid2(ResultPoint[] resultPointArr) {
        ResultPoint resultPoint = resultPointArr[0];
        ResultPoint resultPoint2 = resultPointArr[1];
        ResultPoint resultPoint3 = resultPointArr[2];
        ResultPoint resultPoint4 = resultPointArr[3];
        int transitionsBetween = (transitionsBetween(resultPoint, resultPoint4) + 1) * 4;
        if (transitionsBetween(shiftPoint(resultPoint2, resultPoint3, transitionsBetween), resultPoint) < transitionsBetween(shiftPoint(resultPoint3, resultPoint2, transitionsBetween), resultPoint4)) {
            resultPointArr[0] = resultPoint;
            resultPointArr[1] = resultPoint2;
            resultPointArr[2] = resultPoint3;
            resultPointArr[3] = resultPoint4;
        } else {
            resultPointArr[0] = resultPoint2;
            resultPointArr[1] = resultPoint3;
            resultPointArr[2] = resultPoint4;
            resultPointArr[3] = resultPoint;
        }
        return resultPointArr;
    }

    private boolean isValid(ResultPoint resultPoint) {
        return resultPoint.getX() >= 0.0f && resultPoint.getX() <= ((float) (this.image.getWidth() - 1)) && resultPoint.getY() > 0.0f && resultPoint.getY() <= ((float) (this.image.getHeight() - 1));
    }

    private static ResultPoint moveAway(ResultPoint resultPoint, float f2, float f3) {
        float x2 = resultPoint.getX();
        float y2 = resultPoint.getY();
        return new ResultPoint(x2 < f2 ? x2 - 1.0f : x2 + 1.0f, y2 < f3 ? y2 - 1.0f : y2 + 1.0f);
    }

    private static BitMatrix sampleGrid(BitMatrix bitMatrix, ResultPoint resultPoint, ResultPoint resultPoint2, ResultPoint resultPoint3, ResultPoint resultPoint4, int i3, int i4) throws NotFoundException {
        float f2 = ((float) i3) - 0.5f;
        float f3 = ((float) i4) - 0.5f;
        return GridSampler.getInstance().sampleGrid(bitMatrix, i3, i4, 0.5f, 0.5f, f2, 0.5f, f2, f3, 0.5f, f3, resultPoint.getX(), resultPoint.getY(), resultPoint4.getX(), resultPoint4.getY(), resultPoint3.getX(), resultPoint3.getY(), resultPoint2.getX(), resultPoint2.getY());
    }

    private static ResultPoint shiftPoint(ResultPoint resultPoint, ResultPoint resultPoint2, int i3) {
        float f2 = (float) (i3 + 1);
        return new ResultPoint(resultPoint.getX() + ((resultPoint2.getX() - resultPoint.getX()) / f2), resultPoint.getY() + ((resultPoint2.getY() - resultPoint.getY()) / f2));
    }

    private ResultPoint[] shiftToModuleCenter(ResultPoint[] resultPointArr) {
        ResultPoint resultPoint = resultPointArr[0];
        ResultPoint resultPoint2 = resultPointArr[1];
        ResultPoint resultPoint3 = resultPointArr[2];
        ResultPoint resultPoint4 = resultPointArr[3];
        ResultPoint shiftPoint = shiftPoint(resultPoint, resultPoint2, (transitionsBetween(resultPoint3, resultPoint4) + 1) * 4);
        ResultPoint shiftPoint2 = shiftPoint(resultPoint3, resultPoint2, (transitionsBetween(resultPoint, resultPoint4) + 1) * 4);
        int transitionsBetween = transitionsBetween(shiftPoint, resultPoint4);
        int i3 = transitionsBetween + 1;
        int transitionsBetween2 = transitionsBetween(shiftPoint2, resultPoint4);
        int i4 = transitionsBetween2 + 1;
        if ((i3 & 1) == 1) {
            i3 = transitionsBetween + 2;
        }
        if ((i4 & 1) == 1) {
            i4 = transitionsBetween2 + 2;
        }
        float x2 = (resultPoint4.getX() + (resultPoint3.getX() + (resultPoint2.getX() + resultPoint.getX()))) / 4.0f;
        float y2 = (resultPoint4.getY() + (resultPoint3.getY() + (resultPoint2.getY() + resultPoint.getY()))) / 4.0f;
        ResultPoint moveAway = moveAway(resultPoint, x2, y2);
        ResultPoint moveAway2 = moveAway(resultPoint2, x2, y2);
        ResultPoint moveAway3 = moveAway(resultPoint3, x2, y2);
        ResultPoint moveAway4 = moveAway(resultPoint4, x2, y2);
        int i5 = i4 * 4;
        int i6 = i3 * 4;
        return new ResultPoint[]{shiftPoint(shiftPoint(moveAway, moveAway2, i5), moveAway4, i6), shiftPoint(shiftPoint(moveAway2, moveAway, i5), moveAway3, i6), shiftPoint(shiftPoint(moveAway3, moveAway4, i5), moveAway2, i6), shiftPoint(shiftPoint(moveAway4, moveAway3, i5), moveAway, i6)};
    }

    private int transitionsBetween(ResultPoint resultPoint, ResultPoint resultPoint2) {
        int x2 = (int) resultPoint.getX();
        int y2 = (int) resultPoint.getY();
        int x3 = (int) resultPoint2.getX();
        int i3 = 1;
        int min = Math.min(this.image.getHeight() - 1, (int) resultPoint2.getY());
        int i4 = 0;
        boolean z2 = Math.abs(min - y2) > Math.abs(x3 - x2);
        if (z2) {
            int i5 = y2;
            y2 = x2;
            x2 = i5;
            int i6 = min;
            min = x3;
            x3 = i6;
        }
        int abs = Math.abs(x3 - x2);
        int abs2 = Math.abs(min - y2);
        int i7 = (-abs) / 2;
        int i8 = y2 < min ? 1 : -1;
        if (x2 >= x3) {
            i3 = -1;
        }
        boolean z3 = this.image.get(z2 ? y2 : x2, z2 ? x2 : y2);
        while (x2 != x3) {
            boolean z4 = this.image.get(z2 ? y2 : x2, z2 ? x2 : y2);
            if (z4 != z3) {
                i4++;
                z3 = z4;
            }
            i7 += abs2;
            if (i7 > 0) {
                if (y2 == min) {
                    break;
                }
                y2 += i8;
                i7 -= abs;
            }
            x2 += i3;
        }
        return i4;
    }

    public DetectorResult detect() throws NotFoundException {
        int i3;
        int i4;
        ResultPoint[] detectSolid2 = detectSolid2(detectSolid1(this.rectangleDetector.detect()));
        ResultPoint correctTopRight = correctTopRight(detectSolid2);
        detectSolid2[3] = correctTopRight;
        if (correctTopRight != null) {
            ResultPoint[] shiftToModuleCenter = shiftToModuleCenter(detectSolid2);
            ResultPoint resultPoint = shiftToModuleCenter[0];
            ResultPoint resultPoint2 = shiftToModuleCenter[1];
            ResultPoint resultPoint3 = shiftToModuleCenter[2];
            ResultPoint resultPoint4 = shiftToModuleCenter[3];
            int transitionsBetween = transitionsBetween(resultPoint, resultPoint4);
            int i5 = transitionsBetween + 1;
            int transitionsBetween2 = transitionsBetween(resultPoint3, resultPoint4);
            int i6 = transitionsBetween2 + 1;
            if ((i5 & 1) == 1) {
                i5 = transitionsBetween + 2;
            }
            if ((i6 & 1) == 1) {
                i6 = transitionsBetween2 + 2;
            }
            if (i5 * 4 >= i6 * 6 || i6 * 4 >= i5 * 6) {
                i4 = i5;
                i3 = i6;
            } else {
                i4 = Math.max(i5, i6);
                i3 = i4;
            }
            return new DetectorResult(sampleGrid(this.image, resultPoint, resultPoint2, resultPoint3, resultPoint4, i4, i3), new ResultPoint[]{resultPoint, resultPoint2, resultPoint3, resultPoint4});
        }
        throw NotFoundException.getNotFoundInstance();
    }
}
