package com.google.zxing.oned.rss;

import androidx.compose.runtime.b;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.DecodeHintType;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.ResultMetadataType;
import com.google.zxing.ResultPoint;
import com.google.zxing.ResultPointCallback;
import com.google.zxing.common.BitArray;
import com.google.zxing.common.detector.MathUtils;
import com.google.zxing.oned.OneDReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public final class RSS14Reader extends AbstractRSSReader {
    private static final int[][] FINDER_PATTERNS = {new int[]{3, 8, 2, 1}, new int[]{3, 5, 5, 1}, new int[]{3, 3, 7, 1}, new int[]{3, 1, 9, 1}, new int[]{2, 7, 4, 1}, new int[]{2, 5, 6, 1}, new int[]{2, 3, 8, 1}, new int[]{1, 5, 7, 1}, new int[]{1, 3, 9, 1}};
    private static final int[] INSIDE_GSUM = {0, 336, 1036, 1516};
    private static final int[] INSIDE_ODD_TOTAL_SUBSET = {4, 20, 48, 81};
    private static final int[] INSIDE_ODD_WIDEST = {2, 4, 6, 8};
    private static final int[] OUTSIDE_EVEN_TOTAL_SUBSET = {1, 10, 34, 70, 126};
    private static final int[] OUTSIDE_GSUM = {0, 161, 961, 2015, 2715};
    private static final int[] OUTSIDE_ODD_WIDEST = {8, 6, 4, 3, 1};
    private final List<Pair> possibleLeftPairs = new ArrayList();
    private final List<Pair> possibleRightPairs = new ArrayList();

    private static void addOrTally(Collection<Pair> collection, Pair pair) {
        if (pair != null) {
            for (Pair next : collection) {
                if (next.getValue() == pair.getValue()) {
                    next.incrementCount();
                    return;
                }
            }
            collection.add(pair);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0028, code lost:
        if (r1 < 4) goto L_0x002a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x002a, code lost:
        r2 = true;
        r5 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x002d, code lost:
        r2 = false;
        r5 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0044, code lost:
        if (r1 < 4) goto L_0x002a;
     */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x009f  */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x00b4  */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x00c1  */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x00d6  */
    /* JADX WARNING: Removed duplicated region for block: B:79:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void adjustOddEvenCounts(boolean r10, int r11) throws com.google.zxing.NotFoundException {
        /*
            r9 = this;
            int[] r0 = r9.getOddCounts()
            int r0 = com.google.zxing.common.detector.MathUtils.sum(r0)
            int[] r1 = r9.getEvenCounts()
            int r1 = com.google.zxing.common.detector.MathUtils.sum(r1)
            r2 = 4
            r3 = 1
            r4 = 0
            if (r10 == 0) goto L_0x0030
            r5 = 12
            if (r0 <= r5) goto L_0x001c
            r7 = r3
            r6 = r4
            goto L_0x0023
        L_0x001c:
            if (r0 >= r2) goto L_0x0021
            r6 = r3
            r7 = r4
            goto L_0x0023
        L_0x0021:
            r6 = r4
            r7 = r6
        L_0x0023:
            if (r1 <= r5) goto L_0x0028
        L_0x0025:
            r5 = r3
            r2 = r4
            goto L_0x0047
        L_0x0028:
            if (r1 >= r2) goto L_0x002d
        L_0x002a:
            r2 = r3
            r5 = r4
            goto L_0x0047
        L_0x002d:
            r2 = r4
            r5 = r2
            goto L_0x0047
        L_0x0030:
            r5 = 11
            if (r0 <= r5) goto L_0x0037
            r7 = r3
            r6 = r4
            goto L_0x003f
        L_0x0037:
            r5 = 5
            if (r0 >= r5) goto L_0x003d
            r6 = r3
            r7 = r4
            goto L_0x003f
        L_0x003d:
            r6 = r4
            r7 = r6
        L_0x003f:
            r5 = 10
            if (r1 <= r5) goto L_0x0044
            goto L_0x0025
        L_0x0044:
            if (r1 >= r2) goto L_0x002d
            goto L_0x002a
        L_0x0047:
            int r8 = r0 + r1
            int r8 = r8 - r11
            r11 = r0 & 1
            if (r11 != r10) goto L_0x0050
            r10 = r3
            goto L_0x0051
        L_0x0050:
            r10 = r4
        L_0x0051:
            r11 = r1 & 1
            if (r11 != r3) goto L_0x0056
            r4 = r3
        L_0x0056:
            r11 = -1
            if (r8 == r11) goto L_0x008f
            if (r8 == 0) goto L_0x0077
            if (r8 != r3) goto L_0x0072
            if (r10 == 0) goto L_0x0069
            if (r4 != 0) goto L_0x0064
            r7 = r3
        L_0x0062:
            r3 = r6
            goto L_0x009d
        L_0x0064:
            com.google.zxing.NotFoundException r9 = com.google.zxing.NotFoundException.getNotFoundInstance()
            throw r9
        L_0x0069:
            if (r4 == 0) goto L_0x006d
            r5 = r3
            goto L_0x0062
        L_0x006d:
            com.google.zxing.NotFoundException r9 = com.google.zxing.NotFoundException.getNotFoundInstance()
            throw r9
        L_0x0072:
            com.google.zxing.NotFoundException r9 = com.google.zxing.NotFoundException.getNotFoundInstance()
            throw r9
        L_0x0077:
            if (r10 == 0) goto L_0x0087
            if (r4 == 0) goto L_0x0082
            if (r0 >= r1) goto L_0x007f
            r5 = r3
            goto L_0x009d
        L_0x007f:
            r2 = r3
            r7 = r2
            goto L_0x0062
        L_0x0082:
            com.google.zxing.NotFoundException r9 = com.google.zxing.NotFoundException.getNotFoundInstance()
            throw r9
        L_0x0087:
            if (r4 != 0) goto L_0x008a
            goto L_0x0062
        L_0x008a:
            com.google.zxing.NotFoundException r9 = com.google.zxing.NotFoundException.getNotFoundInstance()
            throw r9
        L_0x008f:
            if (r10 == 0) goto L_0x0099
            if (r4 != 0) goto L_0x0094
            goto L_0x009d
        L_0x0094:
            com.google.zxing.NotFoundException r9 = com.google.zxing.NotFoundException.getNotFoundInstance()
            throw r9
        L_0x0099:
            if (r4 == 0) goto L_0x00e2
            r2 = r3
            goto L_0x0062
        L_0x009d:
            if (r3 == 0) goto L_0x00b2
            if (r7 != 0) goto L_0x00ad
            int[] r10 = r9.getOddCounts()
            float[] r11 = r9.getOddRoundingErrors()
            com.google.zxing.oned.rss.AbstractRSSReader.increment(r10, r11)
            goto L_0x00b2
        L_0x00ad:
            com.google.zxing.NotFoundException r9 = com.google.zxing.NotFoundException.getNotFoundInstance()
            throw r9
        L_0x00b2:
            if (r7 == 0) goto L_0x00bf
            int[] r10 = r9.getOddCounts()
            float[] r11 = r9.getOddRoundingErrors()
            com.google.zxing.oned.rss.AbstractRSSReader.decrement(r10, r11)
        L_0x00bf:
            if (r2 == 0) goto L_0x00d4
            if (r5 != 0) goto L_0x00cf
            int[] r10 = r9.getEvenCounts()
            float[] r11 = r9.getOddRoundingErrors()
            com.google.zxing.oned.rss.AbstractRSSReader.increment(r10, r11)
            goto L_0x00d4
        L_0x00cf:
            com.google.zxing.NotFoundException r9 = com.google.zxing.NotFoundException.getNotFoundInstance()
            throw r9
        L_0x00d4:
            if (r5 == 0) goto L_0x00e1
            int[] r10 = r9.getEvenCounts()
            float[] r9 = r9.getEvenRoundingErrors()
            com.google.zxing.oned.rss.AbstractRSSReader.decrement(r10, r9)
        L_0x00e1:
            return
        L_0x00e2:
            com.google.zxing.NotFoundException r9 = com.google.zxing.NotFoundException.getNotFoundInstance()
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.oned.rss.RSS14Reader.adjustOddEvenCounts(boolean, int):void");
    }

    private static boolean checkChecksum(Pair pair, Pair pair2) {
        int checksumPortion = ((pair2.getChecksumPortion() * 16) + pair.getChecksumPortion()) % 79;
        int value = pair2.getFinderPattern().getValue() + (pair.getFinderPattern().getValue() * 9);
        if (value > 72) {
            value--;
        }
        if (value > 8) {
            value--;
        }
        return checksumPortion == value;
    }

    private static Result constructResult(Pair pair, Pair pair2) {
        String valueOf = String.valueOf((((long) pair.getValue()) * 4537077) + ((long) pair2.getValue()));
        StringBuilder sb = new StringBuilder(14);
        for (int length = 13 - valueOf.length(); length > 0; length--) {
            sb.append('0');
        }
        sb.append(valueOf);
        int i3 = 0;
        for (int i4 = 0; i4 < 13; i4++) {
            int charAt = sb.charAt(i4) - '0';
            if ((i4 & 1) == 0) {
                charAt *= 3;
            }
            i3 += charAt;
        }
        int i5 = 10 - (i3 % 10);
        if (i5 == 10) {
            i5 = 0;
        }
        sb.append(i5);
        ResultPoint[] resultPoints = pair.getFinderPattern().getResultPoints();
        ResultPoint[] resultPoints2 = pair2.getFinderPattern().getResultPoints();
        Result result = new Result(sb.toString(), (byte[]) null, new ResultPoint[]{resultPoints[0], resultPoints[1], resultPoints2[0], resultPoints2[1]}, BarcodeFormat.RSS_14);
        result.putMetadata(ResultMetadataType.SYMBOLOGY_IDENTIFIER, "]e0");
        return result;
    }

    private DataCharacter decodeDataCharacter(BitArray bitArray, FinderPattern finderPattern, boolean z2) throws NotFoundException {
        int[] dataCharacterCounters = getDataCharacterCounters();
        Arrays.fill(dataCharacterCounters, 0);
        if (z2) {
            OneDReader.recordPatternInReverse(bitArray, finderPattern.getStartEnd()[0], dataCharacterCounters);
        } else {
            OneDReader.recordPattern(bitArray, finderPattern.getStartEnd()[1], dataCharacterCounters);
            int i3 = 0;
            for (int length = dataCharacterCounters.length - 1; i3 < length; length--) {
                int i4 = dataCharacterCounters[i3];
                dataCharacterCounters[i3] = dataCharacterCounters[length];
                dataCharacterCounters[length] = i4;
                i3++;
            }
        }
        int i5 = z2 ? 16 : 15;
        float sum = ((float) MathUtils.sum(dataCharacterCounters)) / ((float) i5);
        int[] oddCounts = getOddCounts();
        int[] evenCounts = getEvenCounts();
        float[] oddRoundingErrors = getOddRoundingErrors();
        float[] evenRoundingErrors = getEvenRoundingErrors();
        for (int i6 = 0; i6 < dataCharacterCounters.length; i6++) {
            float f2 = ((float) dataCharacterCounters[i6]) / sum;
            int i7 = (int) (0.5f + f2);
            if (i7 < 1) {
                i7 = 1;
            } else if (i7 > 8) {
                i7 = 8;
            }
            int i8 = i6 / 2;
            if ((i6 & 1) == 0) {
                oddCounts[i8] = i7;
                oddRoundingErrors[i8] = f2 - ((float) i7);
            } else {
                evenCounts[i8] = i7;
                evenRoundingErrors[i8] = f2 - ((float) i7);
            }
        }
        adjustOddEvenCounts(z2, i5);
        int i9 = 0;
        int i10 = 0;
        for (int length2 = oddCounts.length - 1; length2 >= 0; length2--) {
            int i11 = oddCounts[length2];
            i9 = (i9 * 9) + i11;
            i10 += i11;
        }
        int i12 = 0;
        int i13 = 0;
        for (int length3 = evenCounts.length - 1; length3 >= 0; length3--) {
            int i14 = evenCounts[length3];
            i12 = (i12 * 9) + i14;
            i13 += i14;
        }
        int i15 = (i12 * 3) + i9;
        if (z2) {
            if ((i10 & 1) != 0 || i10 > 12 || i10 < 4) {
                throw NotFoundException.getNotFoundInstance();
            }
            int i16 = (12 - i10) / 2;
            int i17 = OUTSIDE_ODD_WIDEST[i16];
            return new DataCharacter(b.a(RSSUtils.getRSSvalue(oddCounts, i17, false), OUTSIDE_EVEN_TOTAL_SUBSET[i16], RSSUtils.getRSSvalue(evenCounts, 9 - i17, true), OUTSIDE_GSUM[i16]), i15);
        } else if ((i13 & 1) != 0 || i13 > 10 || i13 < 4) {
            throw NotFoundException.getNotFoundInstance();
        } else {
            int i18 = (10 - i13) / 2;
            int i19 = INSIDE_ODD_WIDEST[i18];
            return new DataCharacter(b.a(RSSUtils.getRSSvalue(evenCounts, 9 - i19, false), INSIDE_ODD_TOTAL_SUBSET[i18], RSSUtils.getRSSvalue(oddCounts, i19, true), INSIDE_GSUM[i18]), i15);
        }
    }

    private Pair decodePair(BitArray bitArray, boolean z2, int i3, Map<DecodeHintType, ?> map) {
        try {
            FinderPattern parseFoundFinderPattern = parseFoundFinderPattern(bitArray, i3, z2, findFinderPattern(bitArray, z2));
            ResultPointCallback resultPointCallback = map == null ? null : (ResultPointCallback) map.get(DecodeHintType.NEED_RESULT_POINT_CALLBACK);
            if (resultPointCallback != null) {
                int[] startEnd = parseFoundFinderPattern.getStartEnd();
                float f2 = ((float) ((startEnd[0] + startEnd[1]) - 1)) / 2.0f;
                if (z2) {
                    f2 = ((float) (bitArray.getSize() - 1)) - f2;
                }
                resultPointCallback.foundPossibleResultPoint(new ResultPoint(f2, (float) i3));
            }
            DataCharacter decodeDataCharacter = decodeDataCharacter(bitArray, parseFoundFinderPattern, true);
            DataCharacter decodeDataCharacter2 = decodeDataCharacter(bitArray, parseFoundFinderPattern, false);
            return new Pair((decodeDataCharacter.getValue() * 1597) + decodeDataCharacter2.getValue(), (decodeDataCharacter2.getChecksumPortion() * 4) + decodeDataCharacter.getChecksumPortion(), parseFoundFinderPattern);
        } catch (NotFoundException unused) {
            return null;
        }
    }

    private int[] findFinderPattern(BitArray bitArray, boolean z2) throws NotFoundException {
        int[] decodeFinderCounters = getDecodeFinderCounters();
        decodeFinderCounters[0] = 0;
        decodeFinderCounters[1] = 0;
        decodeFinderCounters[2] = 0;
        decodeFinderCounters[3] = 0;
        int size = bitArray.getSize();
        int i3 = 0;
        boolean z3 = false;
        while (i3 < size) {
            z3 = !bitArray.get(i3);
            if (z2 == z3) {
                break;
            }
            i3++;
        }
        int i4 = 0;
        int i5 = i3;
        while (i3 < size) {
            if (bitArray.get(i3) != z3) {
                decodeFinderCounters[i4] = decodeFinderCounters[i4] + 1;
            } else {
                if (i4 != 3) {
                    i4++;
                } else if (AbstractRSSReader.isFinderPattern(decodeFinderCounters)) {
                    return new int[]{i5, i3};
                } else {
                    i5 += decodeFinderCounters[0] + decodeFinderCounters[1];
                    decodeFinderCounters[0] = decodeFinderCounters[2];
                    decodeFinderCounters[1] = decodeFinderCounters[3];
                    decodeFinderCounters[2] = 0;
                    decodeFinderCounters[3] = 0;
                    i4--;
                }
                decodeFinderCounters[i4] = 1;
                z3 = !z3;
            }
            i3++;
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private FinderPattern parseFoundFinderPattern(BitArray bitArray, int i3, boolean z2, int[] iArr) throws NotFoundException {
        int i4;
        int i5;
        boolean z3 = bitArray.get(iArr[0]);
        int i6 = iArr[0] - 1;
        while (i6 >= 0 && z3 != bitArray.get(i6)) {
            i6--;
        }
        int i7 = i6 + 1;
        int[] decodeFinderCounters = getDecodeFinderCounters();
        System.arraycopy(decodeFinderCounters, 0, decodeFinderCounters, 1, decodeFinderCounters.length - 1);
        decodeFinderCounters[0] = iArr[0] - i7;
        int parseFinderValue = AbstractRSSReader.parseFinderValue(decodeFinderCounters, FINDER_PATTERNS);
        int i8 = iArr[1];
        if (z2) {
            i4 = (bitArray.getSize() - 1) - i8;
            i5 = (bitArray.getSize() - 1) - i7;
        } else {
            i4 = i8;
            i5 = i7;
        }
        return new FinderPattern(parseFinderValue, new int[]{i7, iArr[1]}, i5, i4, i3);
    }

    public Result decodeRow(int i3, BitArray bitArray, Map<DecodeHintType, ?> map) throws NotFoundException {
        addOrTally(this.possibleLeftPairs, decodePair(bitArray, false, i3, map));
        bitArray.reverse();
        addOrTally(this.possibleRightPairs, decodePair(bitArray, true, i3, map));
        bitArray.reverse();
        for (Pair next : this.possibleLeftPairs) {
            if (next.getCount() > 1) {
                for (Pair next2 : this.possibleRightPairs) {
                    if (next2.getCount() > 1 && checkChecksum(next, next2)) {
                        return constructResult(next, next2);
                    }
                }
                continue;
            }
        }
        throw NotFoundException.getNotFoundInstance();
    }

    public void reset() {
        this.possibleLeftPairs.clear();
        this.possibleRightPairs.clear();
    }
}
