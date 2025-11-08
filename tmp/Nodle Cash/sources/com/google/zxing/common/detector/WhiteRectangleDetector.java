package com.google.zxing.common.detector;

import com.google.zxing.NotFoundException;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitMatrix;

public final class WhiteRectangleDetector {
    private static final int CORR = 1;
    private static final int INIT_SIZE = 10;
    private final int downInit;
    private final int height;
    private final BitMatrix image;
    private final int leftInit;
    private final int rightInit;
    private final int upInit;
    private final int width;

    public WhiteRectangleDetector(BitMatrix bitMatrix) throws NotFoundException {
        this(bitMatrix, 10, bitMatrix.getWidth() / 2, bitMatrix.getHeight() / 2);
    }

    private ResultPoint[] centerEdges(ResultPoint resultPoint, ResultPoint resultPoint2, ResultPoint resultPoint3, ResultPoint resultPoint4) {
        float x2 = resultPoint.getX();
        float y2 = resultPoint.getY();
        float x3 = resultPoint2.getX();
        float y3 = resultPoint2.getY();
        float x4 = resultPoint3.getX();
        float y4 = resultPoint3.getY();
        float x5 = resultPoint4.getX();
        float y5 = resultPoint4.getY();
        return x2 < ((float) this.width) / 2.0f ? new ResultPoint[]{new ResultPoint(x5 - 1.0f, y5 + 1.0f), new ResultPoint(x3 + 1.0f, y3 + 1.0f), new ResultPoint(x4 - 1.0f, y4 - 1.0f), new ResultPoint(x2 + 1.0f, y2 - 1.0f)} : new ResultPoint[]{new ResultPoint(x5 + 1.0f, y5 + 1.0f), new ResultPoint(x3 + 1.0f, y3 - 1.0f), new ResultPoint(x4 - 1.0f, y4 + 1.0f), new ResultPoint(x2 - 1.0f, y2 - 1.0f)};
    }

    private boolean containsBlackPoint(int i3, int i4, int i5, boolean z2) {
        if (z2) {
            while (i3 <= i4) {
                if (this.image.get(i3, i5)) {
                    return true;
                }
                i3++;
            }
            return false;
        }
        while (i3 <= i4) {
            if (this.image.get(i5, i3)) {
                return true;
            }
            i3++;
        }
        return false;
    }

    private ResultPoint getBlackPointOnSegment(float f2, float f3, float f4, float f5) {
        int round = MathUtils.round(MathUtils.distance(f2, f3, f4, f5));
        float f6 = (float) round;
        float f7 = (f4 - f2) / f6;
        float f8 = (f5 - f3) / f6;
        for (int i3 = 0; i3 < round; i3++) {
            float f9 = (float) i3;
            int round2 = MathUtils.round((f9 * f7) + f2);
            int round3 = MathUtils.round((f9 * f8) + f3);
            if (this.image.get(round2, round3)) {
                return new ResultPoint((float) round2, (float) round3);
            }
        }
        return null;
    }

    public ResultPoint[] detect() throws NotFoundException {
        int i3 = this.leftInit;
        int i4 = this.rightInit;
        int i5 = this.upInit;
        int i6 = this.downInit;
        boolean z2 = false;
        int i7 = 1;
        boolean z3 = false;
        boolean z4 = false;
        boolean z5 = false;
        boolean z6 = false;
        boolean z7 = true;
        while (true) {
            if (!z7) {
                break;
            }
            boolean z8 = false;
            boolean z9 = true;
            while (true) {
                if ((z9 || !z3) && i4 < this.width) {
                    z9 = containsBlackPoint(i5, i6, i4, false);
                    if (z9) {
                        i4++;
                        z3 = true;
                        z8 = true;
                    } else if (!z3) {
                        i4++;
                    }
                }
            }
            if (i4 < this.width) {
                boolean z10 = true;
                while (true) {
                    if ((z10 || !z4) && i6 < this.height) {
                        z10 = containsBlackPoint(i3, i4, i6, true);
                        if (z10) {
                            i6++;
                            z4 = true;
                            z8 = true;
                        } else if (!z4) {
                            i6++;
                        }
                    }
                }
                if (i6 < this.height) {
                    boolean z11 = true;
                    while (true) {
                        if ((z11 || !z5) && i3 >= 0) {
                            z11 = containsBlackPoint(i5, i6, i3, false);
                            if (z11) {
                                i3--;
                                z5 = true;
                                z8 = true;
                            } else if (!z5) {
                                i3--;
                            }
                        }
                    }
                    if (i3 >= 0) {
                        z7 = z8;
                        boolean z12 = true;
                        while (true) {
                            if ((z12 || !z6) && i5 >= 0) {
                                z12 = containsBlackPoint(i3, i4, i5, true);
                                if (z12) {
                                    i5--;
                                    z7 = true;
                                    z6 = true;
                                } else if (!z6) {
                                    i5--;
                                }
                            }
                        }
                        if (i5 < 0) {
                            break;
                        }
                    } else {
                        break;
                    }
                } else {
                    break;
                }
            } else {
                break;
            }
        }
        z2 = true;
        if (!z2) {
            int i8 = i4 - i3;
            ResultPoint resultPoint = null;
            int i9 = 1;
            ResultPoint resultPoint2 = null;
            while (resultPoint2 == null && i9 < i8) {
                resultPoint2 = getBlackPointOnSegment((float) i3, (float) (i6 - i9), (float) (i3 + i9), (float) i6);
                i9++;
            }
            if (resultPoint2 != null) {
                int i10 = 1;
                ResultPoint resultPoint3 = null;
                while (resultPoint3 == null && i10 < i8) {
                    resultPoint3 = getBlackPointOnSegment((float) i3, (float) (i5 + i10), (float) (i3 + i10), (float) i5);
                    i10++;
                }
                if (resultPoint3 != null) {
                    int i11 = 1;
                    ResultPoint resultPoint4 = null;
                    while (resultPoint4 == null && i11 < i8) {
                        resultPoint4 = getBlackPointOnSegment((float) i4, (float) (i5 + i11), (float) (i4 - i11), (float) i5);
                        i11++;
                    }
                    if (resultPoint4 != null) {
                        while (resultPoint == null && i7 < i8) {
                            resultPoint = getBlackPointOnSegment((float) i4, (float) (i6 - i7), (float) (i4 - i7), (float) i6);
                            i7++;
                        }
                        if (resultPoint != null) {
                            return centerEdges(resultPoint, resultPoint2, resultPoint4, resultPoint3);
                        }
                        throw NotFoundException.getNotFoundInstance();
                    }
                    throw NotFoundException.getNotFoundInstance();
                }
                throw NotFoundException.getNotFoundInstance();
            }
            throw NotFoundException.getNotFoundInstance();
        }
        throw NotFoundException.getNotFoundInstance();
    }

    public WhiteRectangleDetector(BitMatrix bitMatrix, int i3, int i4, int i5) throws NotFoundException {
        this.image = bitMatrix;
        int height2 = bitMatrix.getHeight();
        this.height = height2;
        int width2 = bitMatrix.getWidth();
        this.width = width2;
        int i6 = i3 / 2;
        int i7 = i4 - i6;
        this.leftInit = i7;
        int i8 = i4 + i6;
        this.rightInit = i8;
        int i9 = i5 - i6;
        this.upInit = i9;
        int i10 = i5 + i6;
        this.downInit = i10;
        if (i9 < 0 || i7 < 0 || i10 >= height2 || i8 >= width2) {
            throw NotFoundException.getNotFoundInstance();
        }
    }
}
