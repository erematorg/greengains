package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.DecodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.ResultMetadataType;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitArray;
import java.util.Map;

public final class ITFReader extends OneDReader {
    private static final int[] DEFAULT_ALLOWED_LENGTHS = {6, 8, 10, 12, 14};
    private static final int[][] END_PATTERN_REVERSED = {new int[]{1, 1, 2}, new int[]{1, 1, 3}};
    private static final float MAX_AVG_VARIANCE = 0.38f;
    private static final float MAX_INDIVIDUAL_VARIANCE = 0.5f;

    /* renamed from: N  reason: collision with root package name */
    private static final int f7204N = 1;
    private static final int[][] PATTERNS = {new int[]{1, 1, 2, 2, 1}, new int[]{2, 1, 1, 1, 2}, new int[]{1, 2, 1, 1, 2}, new int[]{2, 2, 1, 1, 1}, new int[]{1, 1, 2, 1, 2}, new int[]{2, 1, 2, 1, 1}, new int[]{1, 2, 2, 1, 1}, new int[]{1, 1, 1, 2, 2}, new int[]{2, 1, 1, 2, 1}, new int[]{1, 2, 1, 2, 1}, new int[]{1, 1, 3, 3, 1}, new int[]{3, 1, 1, 1, 3}, new int[]{1, 3, 1, 1, 3}, new int[]{3, 3, 1, 1, 1}, new int[]{1, 1, 3, 1, 3}, new int[]{3, 1, 3, 1, 1}, new int[]{1, 3, 3, 1, 1}, new int[]{1, 1, 1, 3, 3}, new int[]{3, 1, 1, 3, 1}, new int[]{1, 3, 1, 3, 1}};
    private static final int[] START_PATTERN = {1, 1, 1, 1};

    /* renamed from: W  reason: collision with root package name */
    private static final int f7205W = 3;

    /* renamed from: w  reason: collision with root package name */
    private static final int f7206w = 2;
    private int narrowLineWidth = -1;

    private static int decodeDigit(int[] iArr) throws NotFoundException {
        int length = PATTERNS.length;
        float f2 = 0.38f;
        int i3 = -1;
        for (int i4 = 0; i4 < length; i4++) {
            float patternMatchVariance = OneDReader.patternMatchVariance(iArr, PATTERNS[i4], 0.5f);
            if (patternMatchVariance < f2) {
                i3 = i4;
                f2 = patternMatchVariance;
            } else if (patternMatchVariance == f2) {
                i3 = -1;
            }
        }
        if (i3 >= 0) {
            return i3 % 10;
        }
        throw NotFoundException.getNotFoundInstance();
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(2:7|8) */
    /* JADX WARNING: Code restructure failed: missing block: B:8:?, code lost:
        r0 = findGuardPattern(r6, r0, END_PATTERN_REVERSED[1]);
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0014 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int[] decodeEnd(com.google.zxing.common.BitArray r6) throws com.google.zxing.NotFoundException {
        /*
            r5 = this;
            r6.reverse()
            int r0 = skipWhiteSpace(r6)     // Catch:{ all -> 0x0012 }
            r1 = 1
            r2 = 0
            int[][] r3 = END_PATTERN_REVERSED     // Catch:{ NotFoundException -> 0x0014 }
            r3 = r3[r2]     // Catch:{ NotFoundException -> 0x0014 }
            int[] r0 = findGuardPattern(r6, r0, r3)     // Catch:{ NotFoundException -> 0x0014 }
            goto L_0x001c
        L_0x0012:
            r5 = move-exception
            goto L_0x0037
        L_0x0014:
            int[][] r3 = END_PATTERN_REVERSED     // Catch:{ all -> 0x0012 }
            r3 = r3[r1]     // Catch:{ all -> 0x0012 }
            int[] r0 = findGuardPattern(r6, r0, r3)     // Catch:{ all -> 0x0012 }
        L_0x001c:
            r3 = r0[r2]     // Catch:{ all -> 0x0012 }
            r5.validateQuietZone(r6, r3)     // Catch:{ all -> 0x0012 }
            r5 = r0[r2]     // Catch:{ all -> 0x0012 }
            int r3 = r6.getSize()     // Catch:{ all -> 0x0012 }
            r4 = r0[r1]     // Catch:{ all -> 0x0012 }
            int r3 = r3 - r4
            r0[r2] = r3     // Catch:{ all -> 0x0012 }
            int r2 = r6.getSize()     // Catch:{ all -> 0x0012 }
            int r2 = r2 - r5
            r0[r1] = r2     // Catch:{ all -> 0x0012 }
            r6.reverse()
            return r0
        L_0x0037:
            r6.reverse()
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.oned.ITFReader.decodeEnd(com.google.zxing.common.BitArray):int[]");
    }

    private static void decodeMiddle(BitArray bitArray, int i3, int i4, StringBuilder sb) throws NotFoundException {
        int[] iArr = new int[10];
        int[] iArr2 = new int[5];
        int[] iArr3 = new int[5];
        while (i3 < i4) {
            OneDReader.recordPattern(bitArray, i3, iArr);
            for (int i5 = 0; i5 < 5; i5++) {
                int i6 = i5 * 2;
                iArr2[i5] = iArr[i6];
                iArr3[i5] = iArr[i6 + 1];
            }
            sb.append((char) (decodeDigit(iArr2) + 48));
            sb.append((char) (decodeDigit(iArr3) + 48));
            for (int i7 = 0; i7 < 10; i7++) {
                i3 += iArr[i7];
            }
        }
    }

    private int[] decodeStart(BitArray bitArray) throws NotFoundException {
        int[] findGuardPattern = findGuardPattern(bitArray, skipWhiteSpace(bitArray), START_PATTERN);
        int i3 = findGuardPattern[1];
        int i4 = findGuardPattern[0];
        this.narrowLineWidth = (i3 - i4) / 4;
        validateQuietZone(bitArray, i4);
        return findGuardPattern;
    }

    private static int[] findGuardPattern(BitArray bitArray, int i3, int[] iArr) throws NotFoundException {
        int length = iArr.length;
        int[] iArr2 = new int[length];
        int size = bitArray.getSize();
        int i4 = i3;
        boolean z2 = false;
        int i5 = 0;
        while (i3 < size) {
            if (bitArray.get(i3) != z2) {
                iArr2[i5] = iArr2[i5] + 1;
            } else {
                if (i5 != length - 1) {
                    i5++;
                } else if (OneDReader.patternMatchVariance(iArr2, iArr, 0.5f) < 0.38f) {
                    return new int[]{i4, i3};
                } else {
                    i4 += iArr2[0] + iArr2[1];
                    int i6 = i5 - 1;
                    System.arraycopy(iArr2, 2, iArr2, 0, i6);
                    iArr2[i6] = 0;
                    iArr2[i5] = 0;
                    i5--;
                }
                iArr2[i5] = 1;
                z2 = !z2;
            }
            i3++;
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private static int skipWhiteSpace(BitArray bitArray) throws NotFoundException {
        int size = bitArray.getSize();
        int nextSet = bitArray.getNextSet(0);
        if (nextSet != size) {
            return nextSet;
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private void validateQuietZone(BitArray bitArray, int i3) throws NotFoundException {
        int min = Math.min(this.narrowLineWidth * 10, i3);
        int i4 = i3 - 1;
        while (min > 0 && i4 >= 0 && !bitArray.get(i4)) {
            min--;
            i4--;
        }
        if (min != 0) {
            throw NotFoundException.getNotFoundInstance();
        }
    }

    public Result decodeRow(int i3, BitArray bitArray, Map<DecodeHintType, ?> map) throws FormatException, NotFoundException {
        boolean z2;
        int[] decodeStart = decodeStart(bitArray);
        int[] decodeEnd = decodeEnd(bitArray);
        StringBuilder sb = new StringBuilder(20);
        decodeMiddle(bitArray, decodeStart[1], decodeEnd[0], sb);
        String sb2 = sb.toString();
        int[] iArr = map != null ? (int[]) map.get(DecodeHintType.ALLOWED_LENGTHS) : null;
        if (iArr == null) {
            iArr = DEFAULT_ALLOWED_LENGTHS;
        }
        int length = sb2.length();
        int length2 = iArr.length;
        int i4 = 0;
        int i5 = 0;
        while (true) {
            if (i4 >= length2) {
                z2 = false;
                break;
            }
            int i6 = iArr[i4];
            if (length == i6) {
                z2 = true;
                break;
            }
            if (i6 > i5) {
                i5 = i6;
            }
            i4++;
        }
        if (!z2 && length > i5) {
            z2 = true;
        }
        if (z2) {
            float f2 = (float) i3;
            Result result = new Result(sb2, (byte[]) null, new ResultPoint[]{new ResultPoint((float) decodeStart[1], f2), new ResultPoint((float) decodeEnd[0], f2)}, BarcodeFormat.ITF);
            result.putMetadata(ResultMetadataType.SYMBOLOGY_IDENTIFIER, "]I0");
            return result;
        }
        throw FormatException.getFormatInstance();
    }
}
