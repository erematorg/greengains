package com.google.zxing.pdf417.decoder;

import androidx.compose.ui.autofill.a;
import com.google.zxing.ChecksumException;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.DecoderResult;
import com.google.zxing.pdf417.PDF417Common;
import com.google.zxing.pdf417.decoder.ec.ErrorCorrection;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Formatter;

public final class PDF417ScanningDecoder {
    private static final int CODEWORD_SKEW_SIZE = 2;
    private static final int MAX_EC_CODEWORDS = 512;
    private static final int MAX_ERRORS = 3;
    private static final ErrorCorrection errorCorrection = new ErrorCorrection();

    private PDF417ScanningDecoder() {
    }

    private static BoundingBox adjustBoundingBox(DetectionResultRowIndicatorColumn detectionResultRowIndicatorColumn) throws NotFoundException {
        int[] rowHeights;
        if (detectionResultRowIndicatorColumn == null || (rowHeights = detectionResultRowIndicatorColumn.getRowHeights()) == null) {
            return null;
        }
        int max = getMax(rowHeights);
        int i3 = 0;
        int i4 = 0;
        for (int i5 : rowHeights) {
            i4 += max - i5;
            if (i5 > 0) {
                break;
            }
        }
        Codeword[] codewords = detectionResultRowIndicatorColumn.getCodewords();
        int i6 = 0;
        while (i4 > 0 && codewords[i6] == null) {
            i4--;
            i6++;
        }
        for (int length = rowHeights.length - 1; length >= 0; length--) {
            int i7 = rowHeights[length];
            i3 += max - i7;
            if (i7 > 0) {
                break;
            }
        }
        int length2 = codewords.length - 1;
        while (i3 > 0 && codewords[length2] == null) {
            i3--;
            length2--;
        }
        return detectionResultRowIndicatorColumn.getBoundingBox().addMissingRows(i4, i3, detectionResultRowIndicatorColumn.isLeft());
    }

    private static void adjustCodewordCount(DetectionResult detectionResult, BarcodeValue[][] barcodeValueArr) throws NotFoundException {
        BarcodeValue barcodeValue = barcodeValueArr[0][1];
        int[] value = barcodeValue.getValue();
        int barcodeColumnCount = (detectionResult.getBarcodeColumnCount() * detectionResult.getBarcodeRowCount()) - getNumberOfECCodeWords(detectionResult.getBarcodeECLevel());
        if (value.length == 0) {
            if (barcodeColumnCount < 1 || barcodeColumnCount > 928) {
                throw NotFoundException.getNotFoundInstance();
            }
            barcodeValue.setValue(barcodeColumnCount);
        } else if (value[0] != barcodeColumnCount && barcodeColumnCount >= 1 && barcodeColumnCount <= 928) {
            barcodeValue.setValue(barcodeColumnCount);
        }
    }

    private static int adjustCodewordStartColumn(BitMatrix bitMatrix, int i3, int i4, boolean z2, int i5, int i6) {
        int i7 = z2 ? -1 : 1;
        int i8 = i5;
        for (int i9 = 0; i9 < 2; i9++) {
            while (true) {
                if (!z2) {
                    if (i8 >= i4) {
                        continue;
                        break;
                    }
                } else if (i8 < i3) {
                    continue;
                    break;
                }
                if (z2 != bitMatrix.get(i8, i6)) {
                    continue;
                    break;
                } else if (Math.abs(i5 - i8) > 2) {
                    return i5;
                } else {
                    i8 += i7;
                }
            }
            i7 = -i7;
            z2 = !z2;
        }
        return i8;
    }

    private static boolean checkCodewordSkew(int i3, int i4, int i5) {
        return i4 + -2 <= i3 && i3 <= i5 + 2;
    }

    private static int correctErrors(int[] iArr, int[] iArr2, int i3) throws ChecksumException {
        if ((iArr2 == null || iArr2.length <= (i3 / 2) + 3) && i3 >= 0 && i3 <= 512) {
            return errorCorrection.decode(iArr, i3, iArr2);
        }
        throw ChecksumException.getChecksumInstance();
    }

    private static BarcodeValue[][] createBarcodeMatrix(DetectionResult detectionResult) {
        int rowNumber;
        int barcodeRowCount = detectionResult.getBarcodeRowCount();
        int[] iArr = new int[2];
        iArr[1] = detectionResult.getBarcodeColumnCount() + 2;
        iArr[0] = barcodeRowCount;
        BarcodeValue[][] barcodeValueArr = (BarcodeValue[][]) Array.newInstance(BarcodeValue.class, iArr);
        for (BarcodeValue[] barcodeValueArr2 : barcodeValueArr) {
            int i3 = 0;
            while (true) {
                if (i3 >= barcodeValueArr2.length) {
                    break;
                }
                barcodeValueArr2[i3] = new BarcodeValue();
                i3++;
            }
        }
        int i4 = 0;
        for (DetectionResultColumn detectionResultColumn : detectionResult.getDetectionResultColumns()) {
            if (detectionResultColumn != null) {
                for (Codeword codeword : detectionResultColumn.getCodewords()) {
                    if (codeword != null && (rowNumber = codeword.getRowNumber()) >= 0 && rowNumber < barcodeValueArr.length) {
                        barcodeValueArr[rowNumber][i4].setValue(codeword.getValue());
                    }
                }
            }
            i4++;
        }
        return barcodeValueArr;
    }

    private static DecoderResult createDecoderResult(DetectionResult detectionResult) throws FormatException, ChecksumException, NotFoundException {
        BarcodeValue[][] createBarcodeMatrix = createBarcodeMatrix(detectionResult);
        adjustCodewordCount(detectionResult, createBarcodeMatrix);
        ArrayList arrayList = new ArrayList();
        int[] iArr = new int[(detectionResult.getBarcodeRowCount() * detectionResult.getBarcodeColumnCount())];
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        for (int i3 = 0; i3 < detectionResult.getBarcodeRowCount(); i3++) {
            int i4 = 0;
            while (i4 < detectionResult.getBarcodeColumnCount()) {
                int i5 = i4 + 1;
                int[] value = createBarcodeMatrix[i3][i5].getValue();
                int barcodeColumnCount = (detectionResult.getBarcodeColumnCount() * i3) + i4;
                if (value.length == 0) {
                    arrayList.add(Integer.valueOf(barcodeColumnCount));
                } else if (value.length == 1) {
                    iArr[barcodeColumnCount] = value[0];
                } else {
                    arrayList3.add(Integer.valueOf(barcodeColumnCount));
                    arrayList2.add(value);
                }
                i4 = i5;
            }
        }
        int size = arrayList2.size();
        int[][] iArr2 = new int[size][];
        for (int i6 = 0; i6 < size; i6++) {
            iArr2[i6] = (int[]) arrayList2.get(i6);
        }
        return createDecoderResultFromAmbiguousValues(detectionResult.getBarcodeECLevel(), iArr, PDF417Common.toIntArray(arrayList), PDF417Common.toIntArray(arrayList3), iArr2);
    }

    private static DecoderResult createDecoderResultFromAmbiguousValues(int i3, int[] iArr, int[] iArr2, int[] iArr3, int[][] iArr4) throws FormatException, ChecksumException {
        int length = iArr3.length;
        int[] iArr5 = new int[length];
        int i4 = 100;
        while (true) {
            int i5 = i4 - 1;
            if (i4 > 0) {
                for (int i6 = 0; i6 < length; i6++) {
                    iArr[iArr3[i6]] = iArr4[i6][iArr5[i6]];
                }
                try {
                    return decodeCodewords(iArr, i3, iArr2);
                } catch (ChecksumException unused) {
                    if (length != 0) {
                        int i7 = 0;
                        while (true) {
                            if (i7 >= length) {
                                break;
                            }
                            int i8 = iArr5[i7];
                            if (i8 < iArr4[i7].length - 1) {
                                iArr5[i7] = i8 + 1;
                                break;
                            }
                            iArr5[i7] = 0;
                            if (i7 != length - 1) {
                                i7++;
                            } else {
                                throw ChecksumException.getChecksumInstance();
                            }
                        }
                        i4 = i5;
                    } else {
                        throw ChecksumException.getChecksumInstance();
                    }
                }
            } else {
                throw ChecksumException.getChecksumInstance();
            }
        }
    }

    public static DecoderResult decode(BitMatrix bitMatrix, ResultPoint resultPoint, ResultPoint resultPoint2, ResultPoint resultPoint3, ResultPoint resultPoint4, int i3, int i4) throws NotFoundException, FormatException, ChecksumException {
        BoundingBox boundingBox;
        DetectionResult merge;
        DetectionResultColumn detectionResultRowIndicatorColumn;
        int i5;
        int i6;
        DetectionResultColumn detectionResultColumn;
        int i7;
        int i8;
        BoundingBox boundingBox2 = new BoundingBox(bitMatrix, resultPoint, resultPoint2, resultPoint3, resultPoint4);
        DetectionResultRowIndicatorColumn detectionResultRowIndicatorColumn2 = null;
        boolean z2 = true;
        DetectionResultRowIndicatorColumn detectionResultRowIndicatorColumn3 = null;
        boolean z3 = true;
        while (true) {
            boundingBox = boundingBox2;
            if (resultPoint != null) {
                detectionResultRowIndicatorColumn2 = getRowIndicatorColumn(bitMatrix, boundingBox, resultPoint, true, i3, i4);
            }
            if (resultPoint3 != null) {
                detectionResultRowIndicatorColumn3 = getRowIndicatorColumn(bitMatrix, boundingBox, resultPoint3, false, i3, i4);
            }
            merge = merge(detectionResultRowIndicatorColumn2, detectionResultRowIndicatorColumn3);
            if (merge != null) {
                boundingBox2 = merge.getBoundingBox();
                if (!z3 || boundingBox2 == null || (boundingBox2.getMinY() >= boundingBox.getMinY() && boundingBox2.getMaxY() <= boundingBox.getMaxY())) {
                    merge.setBoundingBox(boundingBox);
                    int barcodeColumnCount = merge.getBarcodeColumnCount() + 1;
                    merge.setDetectionResultColumn(0, detectionResultRowIndicatorColumn2);
                    merge.setDetectionResultColumn(barcodeColumnCount, detectionResultRowIndicatorColumn3);
                } else {
                    z3 = false;
                }
            } else {
                throw NotFoundException.getNotFoundInstance();
            }
        }
        merge.setBoundingBox(boundingBox);
        int barcodeColumnCount2 = merge.getBarcodeColumnCount() + 1;
        merge.setDetectionResultColumn(0, detectionResultRowIndicatorColumn2);
        merge.setDetectionResultColumn(barcodeColumnCount2, detectionResultRowIndicatorColumn3);
        boolean z4 = detectionResultRowIndicatorColumn2 != null;
        int i9 = i3;
        int i10 = i4;
        int i11 = 1;
        while (i11 <= barcodeColumnCount2) {
            int i12 = z4 ? i11 : barcodeColumnCount2 - i11;
            if (merge.getDetectionResultColumn(i12) == null) {
                if (i12 == 0 || i12 == barcodeColumnCount2) {
                    detectionResultRowIndicatorColumn = new DetectionResultRowIndicatorColumn(boundingBox, i12 == 0 ? z2 : false);
                } else {
                    detectionResultRowIndicatorColumn = new DetectionResultColumn(boundingBox);
                }
                DetectionResultColumn detectionResultColumn2 = detectionResultRowIndicatorColumn;
                merge.setDetectionResultColumn(i12, detectionResultColumn2);
                int i13 = -1;
                int minY = boundingBox.getMinY();
                int i14 = -1;
                while (minY <= boundingBox.getMaxY()) {
                    int startColumn = getStartColumn(merge, i12, minY, z4);
                    if (startColumn >= 0 && startColumn <= boundingBox.getMaxX()) {
                        i8 = startColumn;
                    } else if (i14 == i13) {
                        i7 = i14;
                        i6 = minY;
                        i5 = i13;
                        detectionResultColumn = detectionResultColumn2;
                        i14 = i7;
                        minY = i6 + 1;
                        detectionResultColumn2 = detectionResultColumn;
                        i13 = i5;
                    } else {
                        i8 = i14;
                    }
                    i7 = i14;
                    int i15 = minY;
                    i5 = i13;
                    detectionResultColumn = detectionResultColumn2;
                    Codeword detectCodeword = detectCodeword(bitMatrix, boundingBox.getMinX(), boundingBox.getMaxX(), z4, i8, i15, i9, i10);
                    i6 = i15;
                    if (detectCodeword != null) {
                        detectionResultColumn.setCodeword(i6, detectCodeword);
                        i9 = Math.min(i9, detectCodeword.getWidth());
                        i10 = Math.max(i10, detectCodeword.getWidth());
                        i14 = i8;
                        minY = i6 + 1;
                        detectionResultColumn2 = detectionResultColumn;
                        i13 = i5;
                    }
                    i14 = i7;
                    minY = i6 + 1;
                    detectionResultColumn2 = detectionResultColumn;
                    i13 = i5;
                }
            }
            i11++;
            z2 = true;
        }
        return createDecoderResult(merge);
    }

    private static DecoderResult decodeCodewords(int[] iArr, int i3, int[] iArr2) throws FormatException, ChecksumException {
        if (iArr.length != 0) {
            int i4 = 1 << (i3 + 1);
            int correctErrors = correctErrors(iArr, iArr2, i4);
            verifyCodewordCount(iArr, i4);
            DecoderResult decode = DecodedBitStreamParser.decode(iArr, String.valueOf(i3));
            decode.setErrorsCorrected(Integer.valueOf(correctErrors));
            decode.setErasures(Integer.valueOf(iArr2.length));
            return decode;
        }
        throw FormatException.getFormatInstance();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0040, code lost:
        r7 = com.google.zxing.pdf417.decoder.PDF417CodewordDecoder.getDecodedValue(r7);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static com.google.zxing.pdf417.decoder.Codeword detectCodeword(com.google.zxing.common.BitMatrix r7, int r8, int r9, boolean r10, int r11, int r12, int r13, int r14) {
        /*
            int r11 = adjustCodewordStartColumn(r7, r8, r9, r10, r11, r12)
            r0 = r7
            r1 = r8
            r2 = r9
            r3 = r10
            r4 = r11
            r5 = r12
            int[] r7 = getModuleBitCount(r0, r1, r2, r3, r4, r5)
            r8 = 0
            if (r7 != 0) goto L_0x0012
            return r8
        L_0x0012:
            int r9 = com.google.zxing.common.detector.MathUtils.sum(r7)
            if (r10 == 0) goto L_0x001b
            int r10 = r11 + r9
            goto L_0x0039
        L_0x001b:
            r10 = 0
        L_0x001c:
            int r12 = r7.length
            int r12 = r12 / 2
            if (r10 >= r12) goto L_0x0034
            r12 = r7[r10]
            int r0 = r7.length
            int r0 = r0 + -1
            int r0 = r0 - r10
            r0 = r7[r0]
            r7[r10] = r0
            int r0 = r7.length
            int r0 = r0 + -1
            int r0 = r0 - r10
            r7[r0] = r12
            int r10 = r10 + 1
            goto L_0x001c
        L_0x0034:
            int r10 = r11 - r9
            r6 = r11
            r11 = r10
            r10 = r6
        L_0x0039:
            boolean r9 = checkCodewordSkew(r9, r13, r14)
            if (r9 != 0) goto L_0x0040
            return r8
        L_0x0040:
            int r7 = com.google.zxing.pdf417.decoder.PDF417CodewordDecoder.getDecodedValue(r7)
            int r9 = com.google.zxing.pdf417.PDF417Common.getCodeword(r7)
            r12 = -1
            if (r9 != r12) goto L_0x004c
            return r8
        L_0x004c:
            com.google.zxing.pdf417.decoder.Codeword r8 = new com.google.zxing.pdf417.decoder.Codeword
            int r7 = getCodewordBucketNumber((int) r7)
            r8.<init>(r11, r10, r7, r9)
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.pdf417.decoder.PDF417ScanningDecoder.detectCodeword(com.google.zxing.common.BitMatrix, int, int, boolean, int, int, int, int):com.google.zxing.pdf417.decoder.Codeword");
    }

    private static BarcodeMetadata getBarcodeMetadata(DetectionResultRowIndicatorColumn detectionResultRowIndicatorColumn, DetectionResultRowIndicatorColumn detectionResultRowIndicatorColumn2) {
        BarcodeMetadata barcodeMetadata;
        BarcodeMetadata barcodeMetadata2;
        if (detectionResultRowIndicatorColumn == null || (barcodeMetadata = detectionResultRowIndicatorColumn.getBarcodeMetadata()) == null) {
            if (detectionResultRowIndicatorColumn2 == null) {
                return null;
            }
            return detectionResultRowIndicatorColumn2.getBarcodeMetadata();
        } else if (detectionResultRowIndicatorColumn2 == null || (barcodeMetadata2 = detectionResultRowIndicatorColumn2.getBarcodeMetadata()) == null || barcodeMetadata.getColumnCount() == barcodeMetadata2.getColumnCount() || barcodeMetadata.getErrorCorrectionLevel() == barcodeMetadata2.getErrorCorrectionLevel() || barcodeMetadata.getRowCount() == barcodeMetadata2.getRowCount()) {
            return barcodeMetadata;
        } else {
            return null;
        }
    }

    private static int[] getBitCountForCodeword(int i3) {
        int[] iArr = new int[8];
        int i4 = 0;
        int i5 = 7;
        while (true) {
            int i6 = i3 & 1;
            if (i6 != i4) {
                i5--;
                if (i5 < 0) {
                    return iArr;
                }
                i4 = i6;
            }
            iArr[i5] = iArr[i5] + 1;
            i3 >>= 1;
        }
    }

    private static int getCodewordBucketNumber(int i3) {
        return getCodewordBucketNumber(getBitCountForCodeword(i3));
    }

    private static int getMax(int[] iArr) {
        int i3 = -1;
        for (int max : iArr) {
            i3 = Math.max(i3, max);
        }
        return i3;
    }

    private static int[] getModuleBitCount(BitMatrix bitMatrix, int i3, int i4, boolean z2, int i5, int i6) {
        int[] iArr = new int[8];
        int i7 = z2 ? 1 : -1;
        int i8 = 0;
        boolean z3 = z2;
        while (true) {
            if (!z2) {
                if (i5 < i3) {
                    break;
                }
            } else if (i5 >= i4) {
                break;
            }
            if (i8 >= 8) {
                break;
            } else if (bitMatrix.get(i5, i6) == z3) {
                iArr[i8] = iArr[i8] + 1;
                i5 += i7;
            } else {
                i8++;
                z3 = !z3;
            }
        }
        if (i8 != 8) {
            if (z2) {
                i3 = i4;
            }
            if (!(i5 == i3 && i8 == 7)) {
                return null;
            }
        }
        return iArr;
    }

    private static int getNumberOfECCodeWords(int i3) {
        return 2 << i3;
    }

    private static DetectionResultRowIndicatorColumn getRowIndicatorColumn(BitMatrix bitMatrix, BoundingBox boundingBox, ResultPoint resultPoint, boolean z2, int i3, int i4) {
        boolean z3 = z2;
        DetectionResultRowIndicatorColumn detectionResultRowIndicatorColumn = new DetectionResultRowIndicatorColumn(boundingBox, z3);
        int i5 = 0;
        while (i5 < 2) {
            int i6 = i5 == 0 ? 1 : -1;
            int x2 = (int) resultPoint.getX();
            int y2 = (int) resultPoint.getY();
            while (y2 <= boundingBox.getMaxY() && y2 >= boundingBox.getMinY()) {
                Codeword detectCodeword = detectCodeword(bitMatrix, 0, bitMatrix.getWidth(), z2, x2, y2, i3, i4);
                if (detectCodeword != null) {
                    detectionResultRowIndicatorColumn.setCodeword(y2, detectCodeword);
                    x2 = z3 ? detectCodeword.getStartX() : detectCodeword.getEndX();
                }
                y2 += i6;
            }
            i5++;
        }
        return detectionResultRowIndicatorColumn;
    }

    private static int getStartColumn(DetectionResult detectionResult, int i3, int i4, boolean z2) {
        int i5 = z2 ? 1 : -1;
        int i6 = i3 - i5;
        Codeword codeword = isValidBarcodeColumn(detectionResult, i6) ? detectionResult.getDetectionResultColumn(i6).getCodeword(i4) : null;
        if (codeword != null) {
            return z2 ? codeword.getEndX() : codeword.getStartX();
        }
        Codeword codewordNearby = detectionResult.getDetectionResultColumn(i3).getCodewordNearby(i4);
        if (codewordNearby != null) {
            return z2 ? codewordNearby.getStartX() : codewordNearby.getEndX();
        }
        if (isValidBarcodeColumn(detectionResult, i6)) {
            codewordNearby = detectionResult.getDetectionResultColumn(i6).getCodewordNearby(i4);
        }
        if (codewordNearby != null) {
            return z2 ? codewordNearby.getEndX() : codewordNearby.getStartX();
        }
        int i7 = 0;
        while (true) {
            i3 -= i5;
            if (isValidBarcodeColumn(detectionResult, i3)) {
                for (Codeword codeword2 : detectionResult.getDetectionResultColumn(i3).getCodewords()) {
                    if (codeword2 != null) {
                        return a.c(codeword2.getEndX(), codeword2.getStartX(), i5 * i7, z2 ? codeword2.getEndX() : codeword2.getStartX());
                    }
                }
                i7++;
            } else {
                BoundingBox boundingBox = detectionResult.getBoundingBox();
                return z2 ? boundingBox.getMinX() : boundingBox.getMaxX();
            }
        }
    }

    private static boolean isValidBarcodeColumn(DetectionResult detectionResult, int i3) {
        return i3 >= 0 && i3 <= detectionResult.getBarcodeColumnCount() + 1;
    }

    private static DetectionResult merge(DetectionResultRowIndicatorColumn detectionResultRowIndicatorColumn, DetectionResultRowIndicatorColumn detectionResultRowIndicatorColumn2) throws NotFoundException {
        BarcodeMetadata barcodeMetadata;
        if ((detectionResultRowIndicatorColumn == null && detectionResultRowIndicatorColumn2 == null) || (barcodeMetadata = getBarcodeMetadata(detectionResultRowIndicatorColumn, detectionResultRowIndicatorColumn2)) == null) {
            return null;
        }
        return new DetectionResult(barcodeMetadata, BoundingBox.merge(adjustBoundingBox(detectionResultRowIndicatorColumn), adjustBoundingBox(detectionResultRowIndicatorColumn2)));
    }

    public static String toString(BarcodeValue[][] barcodeValueArr) {
        Formatter formatter = new Formatter();
        int i3 = 0;
        while (i3 < barcodeValueArr.length) {
            try {
                formatter.format("Row %2d: ", new Object[]{Integer.valueOf(i3)});
                int i4 = 0;
                while (true) {
                    BarcodeValue[] barcodeValueArr2 = barcodeValueArr[i3];
                    if (i4 >= barcodeValueArr2.length) {
                        break;
                    }
                    BarcodeValue barcodeValue = barcodeValueArr2[i4];
                    if (barcodeValue.getValue().length == 0) {
                        formatter.format("        ", (Object[]) null);
                    } else {
                        formatter.format("%4d(%2d)", new Object[]{Integer.valueOf(barcodeValue.getValue()[0]), barcodeValue.getConfidence(barcodeValue.getValue()[0])});
                    }
                    i4++;
                }
                formatter.format("%n", new Object[0]);
                i3++;
            } catch (Throwable th) {
                try {
                    formatter.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        }
        String formatter2 = formatter.toString();
        formatter.close();
        return formatter2;
    }

    private static void verifyCodewordCount(int[] iArr, int i3) throws FormatException {
        if (iArr.length >= 4) {
            int i4 = iArr[0];
            if (i4 > iArr.length) {
                throw FormatException.getFormatInstance();
            } else if (i4 != 0) {
            } else {
                if (i3 < iArr.length) {
                    iArr[0] = iArr.length - i3;
                    return;
                }
                throw FormatException.getFormatInstance();
            }
        } else {
            throw FormatException.getFormatInstance();
        }
    }

    private static int getCodewordBucketNumber(int[] iArr) {
        return ((((iArr[0] - iArr[2]) + iArr[4]) - iArr[6]) + 9) % 9;
    }
}
