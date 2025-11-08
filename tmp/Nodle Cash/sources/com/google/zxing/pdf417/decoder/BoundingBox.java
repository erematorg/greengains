package com.google.zxing.pdf417.decoder;

import com.google.zxing.NotFoundException;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitMatrix;

final class BoundingBox {
    private final ResultPoint bottomLeft;
    private final ResultPoint bottomRight;
    private final BitMatrix image;
    private final int maxX;
    private final int maxY;
    private final int minX;
    private final int minY;
    private final ResultPoint topLeft;
    private final ResultPoint topRight;

    public BoundingBox(BitMatrix bitMatrix, ResultPoint resultPoint, ResultPoint resultPoint2, ResultPoint resultPoint3, ResultPoint resultPoint4) throws NotFoundException {
        boolean z2 = false;
        boolean z3 = resultPoint == null || resultPoint2 == null;
        z2 = (resultPoint3 == null || resultPoint4 == null) ? true : z2;
        if (!z3 || !z2) {
            if (z3) {
                resultPoint = new ResultPoint(0.0f, resultPoint3.getY());
                resultPoint2 = new ResultPoint(0.0f, resultPoint4.getY());
            } else if (z2) {
                resultPoint3 = new ResultPoint((float) (bitMatrix.getWidth() - 1), resultPoint.getY());
                resultPoint4 = new ResultPoint((float) (bitMatrix.getWidth() - 1), resultPoint2.getY());
            }
            this.image = bitMatrix;
            this.topLeft = resultPoint;
            this.bottomLeft = resultPoint2;
            this.topRight = resultPoint3;
            this.bottomRight = resultPoint4;
            this.minX = (int) Math.min(resultPoint.getX(), resultPoint2.getX());
            this.maxX = (int) Math.max(resultPoint3.getX(), resultPoint4.getX());
            this.minY = (int) Math.min(resultPoint.getY(), resultPoint3.getY());
            this.maxY = (int) Math.max(resultPoint2.getY(), resultPoint4.getY());
            return;
        }
        throw NotFoundException.getNotFoundInstance();
    }

    public static BoundingBox merge(BoundingBox boundingBox, BoundingBox boundingBox2) throws NotFoundException {
        return boundingBox == null ? boundingBox2 : boundingBox2 == null ? boundingBox : new BoundingBox(boundingBox.image, boundingBox.topLeft, boundingBox.bottomLeft, boundingBox2.topRight, boundingBox2.bottomRight);
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x002e  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x005d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.google.zxing.pdf417.decoder.BoundingBox addMissingRows(int r13, int r14, boolean r15) throws com.google.zxing.NotFoundException {
        /*
            r12 = this;
            com.google.zxing.ResultPoint r0 = r12.topLeft
            com.google.zxing.ResultPoint r1 = r12.bottomLeft
            com.google.zxing.ResultPoint r2 = r12.topRight
            com.google.zxing.ResultPoint r3 = r12.bottomRight
            if (r13 <= 0) goto L_0x002a
            if (r15 == 0) goto L_0x000e
            r4 = r0
            goto L_0x000f
        L_0x000e:
            r4 = r2
        L_0x000f:
            float r5 = r4.getY()
            int r5 = (int) r5
            int r5 = r5 - r13
            if (r5 >= 0) goto L_0x0018
            r5 = 0
        L_0x0018:
            com.google.zxing.ResultPoint r13 = new com.google.zxing.ResultPoint
            float r4 = r4.getX()
            float r5 = (float) r5
            r13.<init>(r4, r5)
            if (r15 == 0) goto L_0x0027
            r8 = r13
        L_0x0025:
            r10 = r2
            goto L_0x002c
        L_0x0027:
            r10 = r13
            r8 = r0
            goto L_0x002c
        L_0x002a:
            r8 = r0
            goto L_0x0025
        L_0x002c:
            if (r14 <= 0) goto L_0x005d
            if (r15 == 0) goto L_0x0033
            com.google.zxing.ResultPoint r13 = r12.bottomLeft
            goto L_0x0035
        L_0x0033:
            com.google.zxing.ResultPoint r13 = r12.bottomRight
        L_0x0035:
            float r0 = r13.getY()
            int r0 = (int) r0
            int r0 = r0 + r14
            com.google.zxing.common.BitMatrix r14 = r12.image
            int r14 = r14.getHeight()
            if (r0 < r14) goto L_0x004b
            com.google.zxing.common.BitMatrix r14 = r12.image
            int r14 = r14.getHeight()
            int r0 = r14 + -1
        L_0x004b:
            com.google.zxing.ResultPoint r14 = new com.google.zxing.ResultPoint
            float r13 = r13.getX()
            float r0 = (float) r0
            r14.<init>(r13, r0)
            if (r15 == 0) goto L_0x005a
            r9 = r14
        L_0x0058:
            r11 = r3
            goto L_0x005f
        L_0x005a:
            r11 = r14
            r9 = r1
            goto L_0x005f
        L_0x005d:
            r9 = r1
            goto L_0x0058
        L_0x005f:
            com.google.zxing.pdf417.decoder.BoundingBox r13 = new com.google.zxing.pdf417.decoder.BoundingBox
            com.google.zxing.common.BitMatrix r7 = r12.image
            r6 = r13
            r6.<init>(r7, r8, r9, r10, r11)
            return r13
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.pdf417.decoder.BoundingBox.addMissingRows(int, int, boolean):com.google.zxing.pdf417.decoder.BoundingBox");
    }

    public ResultPoint getBottomLeft() {
        return this.bottomLeft;
    }

    public ResultPoint getBottomRight() {
        return this.bottomRight;
    }

    public int getMaxX() {
        return this.maxX;
    }

    public int getMaxY() {
        return this.maxY;
    }

    public int getMinX() {
        return this.minX;
    }

    public int getMinY() {
        return this.minY;
    }

    public ResultPoint getTopLeft() {
        return this.topLeft;
    }

    public ResultPoint getTopRight() {
        return this.topRight;
    }

    public BoundingBox(BoundingBox boundingBox) {
        this.image = boundingBox.image;
        this.topLeft = boundingBox.topLeft;
        this.bottomLeft = boundingBox.bottomLeft;
        this.topRight = boundingBox.topRight;
        this.bottomRight = boundingBox.bottomRight;
        this.minX = boundingBox.minX;
        this.maxX = boundingBox.maxX;
        this.minY = boundingBox.minY;
        this.maxY = boundingBox.maxY;
    }
}
