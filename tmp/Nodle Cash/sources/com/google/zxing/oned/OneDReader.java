package com.google.zxing.oned;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.ChecksumException;
import com.google.zxing.DecodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.Reader;
import com.google.zxing.Result;
import com.google.zxing.ResultMetadataType;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitArray;
import java.util.Arrays;
import java.util.Map;

public abstract class OneDReader implements Reader {
    /* JADX WARNING: Removed duplicated region for block: B:37:0x0077 A[Catch:{ ReaderException -> 0x00c8 }] */
    /* JADX WARNING: Removed duplicated region for block: B:72:0x00ce A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.google.zxing.Result doDecode(com.google.zxing.BinaryBitmap r22, java.util.Map<com.google.zxing.DecodeHintType, ?> r23) throws com.google.zxing.NotFoundException {
        /*
            r21 = this;
            r0 = r23
            int r1 = r22.getWidth()
            int r2 = r22.getHeight()
            com.google.zxing.common.BitArray r3 = new com.google.zxing.common.BitArray
            r3.<init>(r1)
            r4 = 0
            r5 = 1
            if (r0 == 0) goto L_0x001d
            com.google.zxing.DecodeHintType r6 = com.google.zxing.DecodeHintType.TRY_HARDER
            boolean r6 = r0.containsKey(r6)
            if (r6 == 0) goto L_0x001d
            r6 = r5
            goto L_0x001e
        L_0x001d:
            r6 = r4
        L_0x001e:
            if (r6 == 0) goto L_0x0023
            r7 = 8
            goto L_0x0024
        L_0x0023:
            r7 = 5
        L_0x0024:
            int r7 = r2 >> r7
            int r7 = java.lang.Math.max(r5, r7)
            if (r6 == 0) goto L_0x002e
            r6 = r2
            goto L_0x0030
        L_0x002e:
            r6 = 15
        L_0x0030:
            int r8 = r2 / 2
            r9 = r4
        L_0x0033:
            if (r9 >= r6) goto L_0x00e5
            int r10 = r9 + 1
            int r11 = r10 / 2
            r9 = r9 & 1
            if (r9 != 0) goto L_0x003e
            goto L_0x003f
        L_0x003e:
            int r11 = -r11
        L_0x003f:
            int r11 = r11 * r7
            int r11 = r11 + r8
            if (r11 < 0) goto L_0x00e5
            if (r11 >= r2) goto L_0x00e5
            r9 = r22
            com.google.zxing.common.BitArray r3 = r9.getBlackRow(r11, r3)     // Catch:{ NotFoundException -> 0x00d9 }
            r12 = r4
        L_0x004c:
            r13 = 2
            if (r12 >= r13) goto L_0x00d9
            if (r12 != r5) goto L_0x006f
            r3.reverse()
            if (r0 == 0) goto L_0x006f
            com.google.zxing.DecodeHintType r13 = com.google.zxing.DecodeHintType.NEED_RESULT_POINT_CALLBACK
            boolean r14 = r0.containsKey(r13)
            if (r14 == 0) goto L_0x006f
            java.util.EnumMap r14 = new java.util.EnumMap
            java.lang.Class<com.google.zxing.DecodeHintType> r15 = com.google.zxing.DecodeHintType.class
            r14.<init>(r15)
            r14.putAll(r0)
            r14.remove(r13)
            r13 = r21
            r0 = r14
            goto L_0x0071
        L_0x006f:
            r13 = r21
        L_0x0071:
            com.google.zxing.Result r14 = r13.decodeRow(r11, r3, r0)     // Catch:{ ReaderException -> 0x00c8 }
            if (r12 != r5) goto L_0x00ce
            com.google.zxing.ResultMetadataType r15 = com.google.zxing.ResultMetadataType.ORIENTATION     // Catch:{ ReaderException -> 0x00c8 }
            r16 = 180(0xb4, float:2.52E-43)
            java.lang.Integer r5 = java.lang.Integer.valueOf(r16)     // Catch:{ ReaderException -> 0x00c5 }
            r14.putMetadata(r15, r5)     // Catch:{ ReaderException -> 0x00c5 }
            com.google.zxing.ResultPoint[] r5 = r14.getResultPoints()     // Catch:{ ReaderException -> 0x00c5 }
            if (r5 == 0) goto L_0x00ce
            com.google.zxing.ResultPoint r15 = new com.google.zxing.ResultPoint     // Catch:{ ReaderException -> 0x00c5 }
            r16 = r0
            float r0 = (float) r1
            r18 = r5[r4]     // Catch:{ ReaderException -> 0x00c2 }
            float r18 = r18.getX()     // Catch:{ ReaderException -> 0x00c2 }
            float r18 = r0 - r18
            r19 = 1065353216(0x3f800000, float:1.0)
            r20 = r1
            float r1 = r18 - r19
            r18 = r5[r4]     // Catch:{ ReaderException -> 0x00c0 }
            float r4 = r18.getY()     // Catch:{ ReaderException -> 0x00c0 }
            r15.<init>(r1, r4)     // Catch:{ ReaderException -> 0x00c0 }
            r1 = 0
            r5[r1] = r15     // Catch:{ ReaderException -> 0x00c0 }
            com.google.zxing.ResultPoint r4 = new com.google.zxing.ResultPoint     // Catch:{ ReaderException -> 0x00c0 }
            r15 = 1
            r17 = r5[r15]     // Catch:{ ReaderException -> 0x00cf }
            float r17 = r17.getX()     // Catch:{ ReaderException -> 0x00cf }
            float r0 = r0 - r17
            float r0 = r0 - r19
            r17 = r5[r15]     // Catch:{ ReaderException -> 0x00cf }
            float r1 = r17.getY()     // Catch:{ ReaderException -> 0x00cf }
            r4.<init>(r0, r1)     // Catch:{ ReaderException -> 0x00cf }
            r5[r15] = r4     // Catch:{ ReaderException -> 0x00cf }
            goto L_0x00ce
        L_0x00c0:
            r15 = 1
            goto L_0x00cf
        L_0x00c2:
            r20 = r1
            goto L_0x00c0
        L_0x00c5:
            r16 = r0
            goto L_0x00c2
        L_0x00c8:
            r16 = r0
            r20 = r1
            r15 = r5
            goto L_0x00cf
        L_0x00ce:
            return r14
        L_0x00cf:
            int r12 = r12 + 1
            r5 = r15
            r0 = r16
            r1 = r20
            r4 = 0
            goto L_0x004c
        L_0x00d9:
            r13 = r21
            r20 = r1
            r15 = r5
            r9 = r10
            r5 = r15
            r1 = r20
            r4 = 0
            goto L_0x0033
        L_0x00e5:
            com.google.zxing.NotFoundException r0 = com.google.zxing.NotFoundException.getNotFoundInstance()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.oned.OneDReader.doDecode(com.google.zxing.BinaryBitmap, java.util.Map):com.google.zxing.Result");
    }

    public static float patternMatchVariance(int[] iArr, int[] iArr2, float f2) {
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
        float f3 = (float) i3;
        float f4 = f3 / ((float) i4);
        float f5 = f2 * f4;
        float f6 = 0.0f;
        for (int i6 = 0; i6 < length; i6++) {
            int i7 = iArr[i6];
            float f7 = ((float) iArr2[i6]) * f4;
            float f8 = (float) i7;
            float f9 = f8 > f7 ? f8 - f7 : f7 - f8;
            if (f9 > f5) {
                return Float.POSITIVE_INFINITY;
            }
            f6 += f9;
        }
        return f6 / f3;
    }

    public static void recordPattern(BitArray bitArray, int i3, int[] iArr) throws NotFoundException {
        int length = iArr.length;
        int i4 = 0;
        Arrays.fill(iArr, 0, length, 0);
        int size = bitArray.getSize();
        if (i3 < size) {
            boolean z2 = !bitArray.get(i3);
            while (i3 < size) {
                if (bitArray.get(i3) != z2) {
                    iArr[i4] = iArr[i4] + 1;
                } else {
                    i4++;
                    if (i4 == length) {
                        break;
                    }
                    iArr[i4] = 1;
                    z2 = !z2;
                }
                i3++;
            }
            if (i4 == length) {
                return;
            }
            if (i4 != length - 1 || i3 != size) {
                throw NotFoundException.getNotFoundInstance();
            }
            return;
        }
        throw NotFoundException.getNotFoundInstance();
    }

    public static void recordPatternInReverse(BitArray bitArray, int i3, int[] iArr) throws NotFoundException {
        int length = iArr.length;
        boolean z2 = bitArray.get(i3);
        while (i3 > 0 && length >= 0) {
            i3--;
            if (bitArray.get(i3) != z2) {
                length--;
                z2 = !z2;
            }
        }
        if (length < 0) {
            recordPattern(bitArray, i3 + 1, iArr);
            return;
        }
        throw NotFoundException.getNotFoundInstance();
    }

    public Result decode(BinaryBitmap binaryBitmap) throws NotFoundException, FormatException {
        return decode(binaryBitmap, (Map<DecodeHintType, ?>) null);
    }

    public abstract Result decodeRow(int i3, BitArray bitArray, Map<DecodeHintType, ?> map) throws NotFoundException, ChecksumException, FormatException;

    public void reset() {
    }

    public Result decode(BinaryBitmap binaryBitmap, Map<DecodeHintType, ?> map) throws NotFoundException, FormatException {
        try {
            return doDecode(binaryBitmap, map);
        } catch (NotFoundException e3) {
            if (map == null || !map.containsKey(DecodeHintType.TRY_HARDER) || !binaryBitmap.isRotateSupported()) {
                throw e3;
            }
            BinaryBitmap rotateCounterClockwise = binaryBitmap.rotateCounterClockwise();
            Result doDecode = doDecode(rotateCounterClockwise, map);
            Map<ResultMetadataType, Object> resultMetadata = doDecode.getResultMetadata();
            int i3 = 270;
            if (resultMetadata != null) {
                ResultMetadataType resultMetadataType = ResultMetadataType.ORIENTATION;
                if (resultMetadata.containsKey(resultMetadataType)) {
                    i3 = (((Integer) resultMetadata.get(resultMetadataType)).intValue() + 270) % 360;
                }
            }
            doDecode.putMetadata(ResultMetadataType.ORIENTATION, Integer.valueOf(i3));
            ResultPoint[] resultPoints = doDecode.getResultPoints();
            if (resultPoints != null) {
                int height = rotateCounterClockwise.getHeight();
                for (int i4 = 0; i4 < resultPoints.length; i4++) {
                    resultPoints[i4] = new ResultPoint((((float) height) - resultPoints[i4].getY()) - 1.0f, resultPoints[i4].getX());
                }
            }
            return doDecode;
        }
    }
}
