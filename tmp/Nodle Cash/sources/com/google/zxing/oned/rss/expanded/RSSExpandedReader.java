package com.google.zxing.oned.rss.expanded;

import androidx.compose.runtime.b;
import androidx.compose.ui.autofill.a;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.DecodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.ResultMetadataType;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitArray;
import com.google.zxing.common.detector.MathUtils;
import com.google.zxing.oned.OneDReader;
import com.google.zxing.oned.rss.AbstractRSSReader;
import com.google.zxing.oned.rss.DataCharacter;
import com.google.zxing.oned.rss.FinderPattern;
import com.google.zxing.oned.rss.RSSUtils;
import com.google.zxing.oned.rss.expanded.decoders.AbstractExpandedDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public final class RSSExpandedReader extends AbstractRSSReader {
    private static final float DATA_CHARACTER_MODULES = 17.0f;
    private static final int[] EVEN_TOTAL_SUBSET = {4, 20, 52, 104, 204};
    private static final int[][] FINDER_PATTERNS = {new int[]{1, 8, 4, 1}, new int[]{3, 6, 4, 1}, new int[]{3, 4, 6, 1}, new int[]{3, 2, 8, 1}, new int[]{2, 6, 5, 1}, new int[]{2, 2, 9, 1}};
    private static final float FINDER_PATTERN_MODULES = 15.0f;
    private static final int[][] FINDER_PATTERN_SEQUENCES = {new int[]{0, 0}, new int[]{0, 1, 1}, new int[]{0, 2, 1, 3}, new int[]{0, 4, 1, 3, 2}, new int[]{0, 4, 1, 3, 3, 5}, new int[]{0, 4, 1, 3, 4, 5, 5}, new int[]{0, 0, 1, 1, 2, 2, 3, 3}, new int[]{0, 0, 1, 1, 2, 2, 3, 4, 4}, new int[]{0, 0, 1, 1, 2, 2, 3, 4, 5, 5}, new int[]{0, 0, 1, 1, 2, 3, 3, 4, 4, 5, 5}};
    private static final int FINDER_PAT_A = 0;
    private static final int FINDER_PAT_B = 1;
    private static final int FINDER_PAT_C = 2;
    private static final int FINDER_PAT_D = 3;
    private static final int FINDER_PAT_E = 4;
    private static final int FINDER_PAT_F = 5;
    private static final int[] GSUM = {0, 348, 1388, 2948, 3988};
    private static final float MAX_FINDER_PATTERN_DISTANCE_VARIANCE = 0.1f;
    private static final int MAX_PAIRS = 11;
    private static final int[] SYMBOL_WIDEST = {7, 5, 4, 3, 1};
    private static final int[][] WEIGHTS;
    private final List<ExpandedPair> pairs = new ArrayList(11);
    private final List<ExpandedRow> rows = new ArrayList();
    private final int[] startEnd = new int[2];
    private boolean startFromEven;

    static {
        int[] iArr = new int[8];
        int[] iArr2 = iArr;
        // fill-array-data instruction
        iArr[0] = 1;
        iArr[1] = 3;
        iArr[2] = 9;
        iArr[3] = 27;
        iArr[4] = 81;
        iArr[5] = 32;
        iArr[6] = 96;
        iArr[7] = 77;
        int[] iArr3 = new int[8];
        int[] iArr4 = iArr3;
        // fill-array-data instruction
        iArr3[0] = 20;
        iArr3[1] = 60;
        iArr3[2] = 180;
        iArr3[3] = 118;
        iArr3[4] = 143;
        iArr3[5] = 7;
        iArr3[6] = 21;
        iArr3[7] = 63;
        int[] iArr5 = new int[8];
        int[] iArr6 = iArr5;
        // fill-array-data instruction
        iArr5[0] = 189;
        iArr5[1] = 145;
        iArr5[2] = 13;
        iArr5[3] = 39;
        iArr5[4] = 117;
        iArr5[5] = 140;
        iArr5[6] = 209;
        iArr5[7] = 205;
        int[] iArr7 = new int[8];
        int[] iArr8 = iArr7;
        // fill-array-data instruction
        iArr7[0] = 193;
        iArr7[1] = 157;
        iArr7[2] = 49;
        iArr7[3] = 147;
        iArr7[4] = 19;
        iArr7[5] = 57;
        iArr7[6] = 171;
        iArr7[7] = 91;
        int[] iArr9 = new int[8];
        int[] iArr10 = iArr9;
        // fill-array-data instruction
        iArr9[0] = 62;
        iArr9[1] = 186;
        iArr9[2] = 136;
        iArr9[3] = 197;
        iArr9[4] = 169;
        iArr9[5] = 85;
        iArr9[6] = 44;
        iArr9[7] = 132;
        int[] iArr11 = new int[8];
        int[] iArr12 = iArr11;
        // fill-array-data instruction
        iArr11[0] = 185;
        iArr11[1] = 133;
        iArr11[2] = 188;
        iArr11[3] = 142;
        iArr11[4] = 4;
        iArr11[5] = 12;
        iArr11[6] = 36;
        iArr11[7] = 108;
        int[] iArr13 = new int[8];
        int[] iArr14 = iArr13;
        // fill-array-data instruction
        iArr13[0] = 113;
        iArr13[1] = 128;
        iArr13[2] = 173;
        iArr13[3] = 97;
        iArr13[4] = 80;
        iArr13[5] = 29;
        iArr13[6] = 87;
        iArr13[7] = 50;
        int[] iArr15 = new int[8];
        int[] iArr16 = iArr15;
        // fill-array-data instruction
        iArr15[0] = 150;
        iArr15[1] = 28;
        iArr15[2] = 84;
        iArr15[3] = 41;
        iArr15[4] = 123;
        iArr15[5] = 158;
        iArr15[6] = 52;
        iArr15[7] = 156;
        int[] iArr17 = new int[8];
        int[] iArr18 = iArr17;
        // fill-array-data instruction
        iArr17[0] = 46;
        iArr17[1] = 138;
        iArr17[2] = 203;
        iArr17[3] = 187;
        iArr17[4] = 139;
        iArr17[5] = 206;
        iArr17[6] = 196;
        iArr17[7] = 166;
        int[] iArr19 = new int[8];
        int[] iArr20 = iArr19;
        // fill-array-data instruction
        iArr19[0] = 76;
        iArr19[1] = 17;
        iArr19[2] = 51;
        iArr19[3] = 153;
        iArr19[4] = 37;
        iArr19[5] = 111;
        iArr19[6] = 122;
        iArr19[7] = 155;
        int[] iArr21 = new int[8];
        // fill-array-data instruction
        iArr21[0] = 43;
        iArr21[1] = 129;
        iArr21[2] = 176;
        iArr21[3] = 106;
        iArr21[4] = 107;
        iArr21[5] = 110;
        iArr21[6] = 119;
        iArr21[7] = 146;
        int[] iArr22 = new int[8];
        int[] iArr23 = iArr22;
        // fill-array-data instruction
        iArr22[0] = 16;
        iArr22[1] = 48;
        iArr22[2] = 144;
        iArr22[3] = 10;
        iArr22[4] = 30;
        iArr22[5] = 90;
        iArr22[6] = 59;
        iArr22[7] = 177;
        int[] iArr24 = new int[8];
        int[] iArr25 = iArr24;
        // fill-array-data instruction
        iArr24[0] = 109;
        iArr24[1] = 116;
        iArr24[2] = 137;
        iArr24[3] = 200;
        iArr24[4] = 178;
        iArr24[5] = 112;
        iArr24[6] = 125;
        iArr24[7] = 164;
        int[] iArr26 = new int[8];
        int[] iArr27 = iArr26;
        // fill-array-data instruction
        iArr26[0] = 70;
        iArr26[1] = 210;
        iArr26[2] = 208;
        iArr26[3] = 202;
        iArr26[4] = 184;
        iArr26[5] = 130;
        iArr26[6] = 179;
        iArr26[7] = 115;
        int[] iArr28 = new int[8];
        int[] iArr29 = iArr28;
        // fill-array-data instruction
        iArr28[0] = 134;
        iArr28[1] = 191;
        iArr28[2] = 151;
        iArr28[3] = 31;
        iArr28[4] = 93;
        iArr28[5] = 68;
        iArr28[6] = 204;
        iArr28[7] = 190;
        int[] iArr30 = new int[8];
        int[] iArr31 = iArr30;
        // fill-array-data instruction
        iArr30[0] = 148;
        iArr30[1] = 22;
        iArr30[2] = 66;
        iArr30[3] = 198;
        iArr30[4] = 172;
        iArr30[5] = 94;
        iArr30[6] = 71;
        iArr30[7] = 2;
        int[] iArr32 = new int[8];
        int[] iArr33 = iArr32;
        // fill-array-data instruction
        iArr32[0] = 6;
        iArr32[1] = 18;
        iArr32[2] = 54;
        iArr32[3] = 162;
        iArr32[4] = 64;
        iArr32[5] = 192;
        iArr32[6] = 154;
        iArr32[7] = 40;
        int[] iArr34 = new int[8];
        int[] iArr35 = iArr34;
        // fill-array-data instruction
        iArr34[0] = 120;
        iArr34[1] = 149;
        iArr34[2] = 25;
        iArr34[3] = 75;
        iArr34[4] = 14;
        iArr34[5] = 42;
        iArr34[6] = 126;
        iArr34[7] = 167;
        int[] iArr36 = new int[8];
        int[] iArr37 = iArr36;
        // fill-array-data instruction
        iArr36[0] = 79;
        iArr36[1] = 26;
        iArr36[2] = 78;
        iArr36[3] = 23;
        iArr36[4] = 69;
        iArr36[5] = 207;
        iArr36[6] = 199;
        iArr36[7] = 175;
        int[] iArr38 = new int[8];
        int[] iArr39 = iArr38;
        // fill-array-data instruction
        iArr38[0] = 103;
        iArr38[1] = 98;
        iArr38[2] = 83;
        iArr38[3] = 38;
        iArr38[4] = 114;
        iArr38[5] = 131;
        iArr38[6] = 182;
        iArr38[7] = 124;
        int[] iArr40 = new int[8];
        int[] iArr41 = iArr40;
        // fill-array-data instruction
        iArr40[0] = 161;
        iArr40[1] = 61;
        iArr40[2] = 183;
        iArr40[3] = 127;
        iArr40[4] = 170;
        iArr40[5] = 88;
        iArr40[6] = 53;
        iArr40[7] = 159;
        int[] iArr42 = new int[8];
        int[] iArr43 = iArr42;
        // fill-array-data instruction
        iArr42[0] = 55;
        iArr42[1] = 165;
        iArr42[2] = 73;
        iArr42[3] = 8;
        iArr42[4] = 24;
        iArr42[5] = 72;
        iArr42[6] = 5;
        iArr42[7] = 15;
        int[] iArr44 = new int[8];
        // fill-array-data instruction
        iArr44[0] = 45;
        iArr44[1] = 135;
        iArr44[2] = 194;
        iArr44[3] = 160;
        iArr44[4] = 58;
        iArr44[5] = 174;
        iArr44[6] = 100;
        iArr44[7] = 89;
        WEIGHTS = new int[][]{iArr2, iArr4, iArr6, iArr8, iArr10, iArr12, iArr14, iArr16, iArr18, iArr20, iArr21, iArr23, iArr25, iArr27, iArr29, iArr31, iArr33, iArr35, iArr37, iArr39, iArr41, iArr43, iArr44};
    }

    /* JADX WARNING: Removed duplicated region for block: B:51:0x0085  */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x009a  */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x00a7  */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x00bc  */
    /* JADX WARNING: Removed duplicated region for block: B:67:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void adjustOddEvenCounts(int r11) throws com.google.zxing.NotFoundException {
        /*
            r10 = this;
            int[] r0 = r10.getOddCounts()
            int r0 = com.google.zxing.common.detector.MathUtils.sum(r0)
            int[] r1 = r10.getEvenCounts()
            int r1 = com.google.zxing.common.detector.MathUtils.sum(r1)
            r2 = 4
            r3 = 1
            r4 = 0
            r5 = 13
            if (r0 <= r5) goto L_0x001a
            r7 = r3
            r6 = r4
            goto L_0x0021
        L_0x001a:
            if (r0 >= r2) goto L_0x001f
            r6 = r3
            r7 = r4
            goto L_0x0021
        L_0x001f:
            r6 = r4
            r7 = r6
        L_0x0021:
            if (r1 <= r5) goto L_0x0026
            r5 = r3
            r2 = r4
            goto L_0x002d
        L_0x0026:
            if (r1 >= r2) goto L_0x002b
            r2 = r3
            r5 = r4
            goto L_0x002d
        L_0x002b:
            r2 = r4
            r5 = r2
        L_0x002d:
            int r8 = r0 + r1
            int r8 = r8 - r11
            r11 = r0 & 1
            if (r11 != r3) goto L_0x0036
            r11 = r3
            goto L_0x0037
        L_0x0036:
            r11 = r4
        L_0x0037:
            r9 = r1 & 1
            if (r9 != 0) goto L_0x003c
            r4 = r3
        L_0x003c:
            r9 = -1
            if (r8 == r9) goto L_0x0075
            if (r8 == 0) goto L_0x005d
            if (r8 != r3) goto L_0x0058
            if (r11 == 0) goto L_0x004f
            if (r4 != 0) goto L_0x004a
            r7 = r3
        L_0x0048:
            r3 = r6
            goto L_0x0083
        L_0x004a:
            com.google.zxing.NotFoundException r10 = com.google.zxing.NotFoundException.getNotFoundInstance()
            throw r10
        L_0x004f:
            if (r4 == 0) goto L_0x0053
            r5 = r3
            goto L_0x0048
        L_0x0053:
            com.google.zxing.NotFoundException r10 = com.google.zxing.NotFoundException.getNotFoundInstance()
            throw r10
        L_0x0058:
            com.google.zxing.NotFoundException r10 = com.google.zxing.NotFoundException.getNotFoundInstance()
            throw r10
        L_0x005d:
            if (r11 == 0) goto L_0x006d
            if (r4 == 0) goto L_0x0068
            if (r0 >= r1) goto L_0x0065
            r5 = r3
            goto L_0x0083
        L_0x0065:
            r2 = r3
            r7 = r2
            goto L_0x0048
        L_0x0068:
            com.google.zxing.NotFoundException r10 = com.google.zxing.NotFoundException.getNotFoundInstance()
            throw r10
        L_0x006d:
            if (r4 != 0) goto L_0x0070
            goto L_0x0048
        L_0x0070:
            com.google.zxing.NotFoundException r10 = com.google.zxing.NotFoundException.getNotFoundInstance()
            throw r10
        L_0x0075:
            if (r11 == 0) goto L_0x007f
            if (r4 != 0) goto L_0x007a
            goto L_0x0083
        L_0x007a:
            com.google.zxing.NotFoundException r10 = com.google.zxing.NotFoundException.getNotFoundInstance()
            throw r10
        L_0x007f:
            if (r4 == 0) goto L_0x00c8
            r2 = r3
            goto L_0x0048
        L_0x0083:
            if (r3 == 0) goto L_0x0098
            if (r7 != 0) goto L_0x0093
            int[] r11 = r10.getOddCounts()
            float[] r0 = r10.getOddRoundingErrors()
            com.google.zxing.oned.rss.AbstractRSSReader.increment(r11, r0)
            goto L_0x0098
        L_0x0093:
            com.google.zxing.NotFoundException r10 = com.google.zxing.NotFoundException.getNotFoundInstance()
            throw r10
        L_0x0098:
            if (r7 == 0) goto L_0x00a5
            int[] r11 = r10.getOddCounts()
            float[] r0 = r10.getOddRoundingErrors()
            com.google.zxing.oned.rss.AbstractRSSReader.decrement(r11, r0)
        L_0x00a5:
            if (r2 == 0) goto L_0x00ba
            if (r5 != 0) goto L_0x00b5
            int[] r11 = r10.getEvenCounts()
            float[] r0 = r10.getOddRoundingErrors()
            com.google.zxing.oned.rss.AbstractRSSReader.increment(r11, r0)
            goto L_0x00ba
        L_0x00b5:
            com.google.zxing.NotFoundException r10 = com.google.zxing.NotFoundException.getNotFoundInstance()
            throw r10
        L_0x00ba:
            if (r5 == 0) goto L_0x00c7
            int[] r11 = r10.getEvenCounts()
            float[] r10 = r10.getEvenRoundingErrors()
            com.google.zxing.oned.rss.AbstractRSSReader.decrement(r11, r10)
        L_0x00c7:
            return
        L_0x00c8:
            com.google.zxing.NotFoundException r10 = com.google.zxing.NotFoundException.getNotFoundInstance()
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.oned.rss.expanded.RSSExpandedReader.adjustOddEvenCounts(int):void");
    }

    private boolean checkChecksum() {
        ExpandedPair expandedPair = this.pairs.get(0);
        DataCharacter leftChar = expandedPair.getLeftChar();
        DataCharacter rightChar = expandedPair.getRightChar();
        if (rightChar == null) {
            return false;
        }
        int checksumPortion = rightChar.getChecksumPortion();
        int i3 = 2;
        for (int i4 = 1; i4 < this.pairs.size(); i4++) {
            ExpandedPair expandedPair2 = this.pairs.get(i4);
            int checksumPortion2 = expandedPair2.getLeftChar().getChecksumPortion() + checksumPortion;
            int i5 = i3 + 1;
            DataCharacter rightChar2 = expandedPair2.getRightChar();
            if (rightChar2 != null) {
                checksumPortion = rightChar2.getChecksumPortion() + checksumPortion2;
                i3 += 2;
            } else {
                i3 = i5;
                checksumPortion = checksumPortion2;
            }
        }
        return a.c(i3, 4, 211, checksumPortion % 211) == leftChar.getValue();
    }

    private List<ExpandedPair> checkRows(boolean z2) {
        List<ExpandedPair> list = null;
        if (this.rows.size() > 25) {
            this.rows.clear();
            return null;
        }
        this.pairs.clear();
        if (z2) {
            Collections.reverse(this.rows);
        }
        try {
            list = checkRows(new ArrayList(), 0);
        } catch (NotFoundException unused) {
        }
        if (z2) {
            Collections.reverse(this.rows);
        }
        return list;
    }

    public static Result constructResult(List<ExpandedPair> list) throws NotFoundException, FormatException {
        String parseInformation = AbstractExpandedDecoder.createDecoder(BitArrayBuilder.buildBitArray(list)).parseInformation();
        ResultPoint[] resultPoints = list.get(0).getFinderPattern().getResultPoints();
        ResultPoint[] resultPoints2 = ((ExpandedPair) android.support.v4.media.session.a.h(list, 1)).getFinderPattern().getResultPoints();
        Result result = new Result(parseInformation, (byte[]) null, new ResultPoint[]{resultPoints[0], resultPoints[1], resultPoints2[0], resultPoints2[1]}, BarcodeFormat.RSS_EXPANDED);
        result.putMetadata(ResultMetadataType.SYMBOLOGY_IDENTIFIER, "]e0");
        return result;
    }

    private void findNextPair(BitArray bitArray, List<ExpandedPair> list, int i3) throws NotFoundException {
        int[] decodeFinderCounters = getDecodeFinderCounters();
        decodeFinderCounters[0] = 0;
        decodeFinderCounters[1] = 0;
        decodeFinderCounters[2] = 0;
        decodeFinderCounters[3] = 0;
        int size = bitArray.getSize();
        if (i3 < 0) {
            i3 = list.isEmpty() ? 0 : ((ExpandedPair) android.support.v4.media.session.a.h(list, 1)).getFinderPattern().getStartEnd()[1];
        }
        boolean z2 = list.size() % 2 != 0;
        if (this.startFromEven) {
            z2 = !z2;
        }
        boolean z3 = false;
        while (true) {
            if (i3 >= size) {
                break;
            }
            boolean z4 = bitArray.get(i3);
            boolean z5 = !z4;
            if (z4) {
                z3 = z5;
                break;
            } else {
                i3++;
                z3 = z5;
            }
        }
        int i4 = 0;
        boolean z6 = z3;
        int i5 = i3;
        while (i3 < size) {
            if (bitArray.get(i3) != z6) {
                decodeFinderCounters[i4] = decodeFinderCounters[i4] + 1;
            } else {
                if (i4 == 3) {
                    if (z2) {
                        reverseCounters(decodeFinderCounters);
                    }
                    if (AbstractRSSReader.isFinderPattern(decodeFinderCounters)) {
                        int[] iArr = this.startEnd;
                        iArr[0] = i5;
                        iArr[1] = i3;
                        return;
                    }
                    if (z2) {
                        reverseCounters(decodeFinderCounters);
                    }
                    i5 += decodeFinderCounters[0] + decodeFinderCounters[1];
                    decodeFinderCounters[0] = decodeFinderCounters[2];
                    decodeFinderCounters[1] = decodeFinderCounters[3];
                    decodeFinderCounters[2] = 0;
                    decodeFinderCounters[3] = 0;
                    i4--;
                } else {
                    i4++;
                }
                decodeFinderCounters[i4] = 1;
                z6 = !z6;
            }
            i3++;
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private static int getNextSecondBar(BitArray bitArray, int i3) {
        return bitArray.get(i3) ? bitArray.getNextSet(bitArray.getNextUnset(i3)) : bitArray.getNextUnset(bitArray.getNextSet(i3));
    }

    private static boolean isNotA1left(FinderPattern finderPattern, boolean z2, boolean z3) {
        return finderPattern.getValue() != 0 || !z2 || !z3;
    }

    private static boolean isPartialRow(Iterable<ExpandedPair> iterable, Iterable<ExpandedRow> iterable2) {
        for (ExpandedRow next : iterable2) {
            Iterator<ExpandedPair> it = iterable.iterator();
            while (true) {
                if (!it.hasNext()) {
                    return true;
                }
                ExpandedPair next2 = it.next();
                Iterator<ExpandedPair> it2 = next.getPairs().iterator();
                while (true) {
                    if (it2.hasNext()) {
                        if (next2.equals(it2.next())) {
                        }
                    }
                }
            }
        }
        return false;
    }

    private static boolean isValidSequence(List<ExpandedPair> list, boolean z2) {
        int[][] iArr = FINDER_PATTERN_SEQUENCES;
        int length = iArr.length;
        int i3 = 0;
        while (i3 < length) {
            int[] iArr2 = iArr[i3];
            int size = list.size();
            int length2 = iArr2.length;
            if (z2) {
                if (size != length2) {
                    continue;
                    i3++;
                }
            } else if (size > length2) {
                continue;
                i3++;
            }
            int i4 = 0;
            while (i4 < list.size()) {
                if (list.get(i4).getFinderPattern().getValue() != iArr2[i4]) {
                    i3++;
                } else {
                    i4++;
                }
            }
            return true;
        }
        return false;
    }

    private static boolean mayFollow(List<ExpandedPair> list, int i3) {
        if (list.isEmpty()) {
            return true;
        }
        for (int[] iArr : FINDER_PATTERN_SEQUENCES) {
            if (list.size() + 1 <= iArr.length) {
                for (int size = list.size(); size < iArr.length; size++) {
                    if (iArr[size] == i3) {
                        int i4 = 0;
                        while (i4 < list.size()) {
                            if (iArr[(size - i4) - 1] == list.get((list.size() - i4) - 1).getFinderPattern().getValue()) {
                                i4++;
                            }
                        }
                        return true;
                    }
                }
                continue;
            }
        }
        return false;
    }

    private FinderPattern parseFoundFinderPattern(BitArray bitArray, int i3, boolean z2, List<ExpandedPair> list) {
        int i4;
        int i5;
        int i6;
        if (z2) {
            int i7 = this.startEnd[0] - 1;
            while (i7 >= 0 && !bitArray.get(i7)) {
                i7--;
            }
            int i8 = i7 + 1;
            int[] iArr = this.startEnd;
            i6 = iArr[0] - i8;
            i4 = iArr[1];
            i5 = i8;
        } else {
            int[] iArr2 = this.startEnd;
            int i9 = iArr2[0];
            int nextUnset = bitArray.getNextUnset(iArr2[1] + 1);
            i4 = nextUnset;
            i5 = i9;
            i6 = nextUnset - this.startEnd[1];
        }
        int[] decodeFinderCounters = getDecodeFinderCounters();
        System.arraycopy(decodeFinderCounters, 0, decodeFinderCounters, 1, decodeFinderCounters.length - 1);
        decodeFinderCounters[0] = i6;
        try {
            int parseFinderValue = AbstractRSSReader.parseFinderValue(decodeFinderCounters, FINDER_PATTERNS);
            if (!mayFollow(list, parseFinderValue)) {
                return null;
            }
            if (!list.isEmpty()) {
                ExpandedPair expandedPair = (ExpandedPair) android.support.v4.media.session.a.h(list, 1);
                int i10 = expandedPair.getFinderPattern().getStartEnd()[0];
                int i11 = expandedPair.getFinderPattern().getStartEnd()[1];
                float f2 = (float) i11;
                float f3 = (((float) (i11 - i10)) / FINDER_PATTERN_MODULES) * DATA_CHARACTER_MODULES * 2.0f;
                float f4 = (0.9f * f3) + f2;
                float f5 = (f3 * 1.1f) + f2;
                float f6 = (float) i5;
                if (f6 < f4 || f6 > f5) {
                    return null;
                }
            }
            return new FinderPattern(parseFinderValue, new int[]{i5, i4}, i5, i4, i3);
        } catch (NotFoundException unused) {
            return null;
        }
    }

    private static void removePartialRows(Collection<ExpandedPair> collection, Collection<ExpandedRow> collection2) {
        Iterator<ExpandedRow> it = collection2.iterator();
        while (it.hasNext()) {
            ExpandedRow next = it.next();
            if (next.getPairs().size() != collection.size()) {
                Iterator<ExpandedPair> it2 = next.getPairs().iterator();
                while (true) {
                    if (it2.hasNext()) {
                        if (!collection.contains(it2.next())) {
                            break;
                        }
                    } else {
                        it.remove();
                        break;
                    }
                }
            }
        }
    }

    private static void reverseCounters(int[] iArr) {
        int length = iArr.length;
        for (int i3 = 0; i3 < length / 2; i3++) {
            int i4 = iArr[i3];
            int i5 = (length - i3) - 1;
            iArr[i3] = iArr[i5];
            iArr[i5] = i4;
        }
    }

    private void storeRow(int i3) {
        boolean z2 = false;
        int i4 = 0;
        boolean z3 = false;
        while (true) {
            if (i4 >= this.rows.size()) {
                break;
            }
            ExpandedRow expandedRow = this.rows.get(i4);
            if (expandedRow.getRowNumber() > i3) {
                z2 = expandedRow.isEquivalent(this.pairs);
                break;
            } else {
                z3 = expandedRow.isEquivalent(this.pairs);
                i4++;
            }
        }
        if (!z2 && !z3 && !isPartialRow(this.pairs, this.rows)) {
            this.rows.add(i4, new ExpandedRow(this.pairs, i3));
            removePartialRows(this.pairs, this.rows);
        }
    }

    public DataCharacter decodeDataCharacter(BitArray bitArray, FinderPattern finderPattern, boolean z2, boolean z3) throws NotFoundException {
        BitArray bitArray2 = bitArray;
        int[] dataCharacterCounters = getDataCharacterCounters();
        Arrays.fill(dataCharacterCounters, 0);
        if (z3) {
            OneDReader.recordPatternInReverse(bitArray2, finderPattern.getStartEnd()[0], dataCharacterCounters);
        } else {
            OneDReader.recordPattern(bitArray2, finderPattern.getStartEnd()[1], dataCharacterCounters);
            int i3 = 0;
            for (int length = dataCharacterCounters.length - 1; i3 < length; length--) {
                int i4 = dataCharacterCounters[i3];
                dataCharacterCounters[i3] = dataCharacterCounters[length];
                dataCharacterCounters[length] = i4;
                i3++;
            }
        }
        float sum = ((float) MathUtils.sum(dataCharacterCounters)) / ((float) 17);
        float f2 = ((float) (finderPattern.getStartEnd()[1] - finderPattern.getStartEnd()[0])) / FINDER_PATTERN_MODULES;
        if (Math.abs(sum - f2) / f2 <= 0.3f) {
            int[] oddCounts = getOddCounts();
            int[] evenCounts = getEvenCounts();
            float[] oddRoundingErrors = getOddRoundingErrors();
            float[] evenRoundingErrors = getEvenRoundingErrors();
            for (int i5 = 0; i5 < dataCharacterCounters.length; i5++) {
                float f3 = (((float) dataCharacterCounters[i5]) * 1.0f) / sum;
                int i6 = (int) (0.5f + f3);
                if (i6 < 1) {
                    if (f3 >= 0.3f) {
                        i6 = 1;
                    } else {
                        throw NotFoundException.getNotFoundInstance();
                    }
                } else if (i6 > 8) {
                    if (f3 <= 8.7f) {
                        i6 = 8;
                    } else {
                        throw NotFoundException.getNotFoundInstance();
                    }
                }
                int i7 = i5 / 2;
                if ((i5 & 1) == 0) {
                    oddCounts[i7] = i6;
                    oddRoundingErrors[i7] = f3 - ((float) i6);
                } else {
                    evenCounts[i7] = i6;
                    evenRoundingErrors[i7] = f3 - ((float) i6);
                }
            }
            adjustOddEvenCounts(17);
            int value = (((finderPattern.getValue() * 4) + (z2 ? 0 : 2)) + (z3 ^ true ? 1 : 0)) - 1;
            int i8 = 0;
            int i9 = 0;
            for (int length2 = oddCounts.length - 1; length2 >= 0; length2--) {
                if (isNotA1left(finderPattern, z2, z3)) {
                    i8 += oddCounts[length2] * WEIGHTS[value][length2 * 2];
                }
                i9 += oddCounts[length2];
            }
            int i10 = 0;
            for (int length3 = evenCounts.length - 1; length3 >= 0; length3--) {
                if (isNotA1left(finderPattern, z2, z3)) {
                    i10 += evenCounts[length3] * WEIGHTS[value][(length3 * 2) + 1];
                }
            }
            int i11 = i8 + i10;
            if ((i9 & 1) != 0 || i9 > 13 || i9 < 4) {
                throw NotFoundException.getNotFoundInstance();
            }
            int i12 = (13 - i9) / 2;
            int i13 = SYMBOL_WIDEST[i12];
            return new DataCharacter(b.a(RSSUtils.getRSSvalue(oddCounts, i13, true), EVEN_TOTAL_SUBSET[i12], RSSUtils.getRSSvalue(evenCounts, 9 - i13, false), GSUM[i12]), i11);
        }
        throw NotFoundException.getNotFoundInstance();
    }

    public Result decodeRow(int i3, BitArray bitArray, Map<DecodeHintType, ?> map) throws NotFoundException, FormatException {
        this.startFromEven = false;
        try {
            return constructResult(decodeRow2pairs(i3, bitArray));
        } catch (NotFoundException unused) {
            this.startFromEven = true;
            return constructResult(decodeRow2pairs(i3, bitArray));
        }
    }

    public List<ExpandedPair> decodeRow2pairs(int i3, BitArray bitArray) throws NotFoundException {
        this.pairs.clear();
        boolean z2 = false;
        while (!z2) {
            try {
                List<ExpandedPair> list = this.pairs;
                list.add(retrieveNextPair(bitArray, list, i3));
            } catch (NotFoundException e3) {
                if (!this.pairs.isEmpty()) {
                    z2 = true;
                } else {
                    throw e3;
                }
            }
        }
        if (checkChecksum() && isValidSequence(this.pairs, true)) {
            return this.pairs;
        }
        boolean isEmpty = this.rows.isEmpty();
        storeRow(i3);
        if (!isEmpty) {
            List<ExpandedPair> checkRows = checkRows(false);
            if (checkRows != null) {
                return checkRows;
            }
            List<ExpandedPair> checkRows2 = checkRows(true);
            if (checkRows2 != null) {
                return checkRows2;
            }
        }
        throw NotFoundException.getNotFoundInstance();
    }

    public List<ExpandedRow> getRows() {
        return this.rows;
    }

    public void reset() {
        this.pairs.clear();
        this.rows.clear();
    }

    public ExpandedPair retrieveNextPair(BitArray bitArray, List<ExpandedPair> list, int i3) throws NotFoundException {
        FinderPattern parseFoundFinderPattern;
        boolean z2 = list.size() % 2 == 0;
        if (this.startFromEven) {
            z2 = !z2;
        }
        DataCharacter dataCharacter = null;
        int i4 = -1;
        boolean z3 = true;
        DataCharacter dataCharacter2 = null;
        do {
            findNextPair(bitArray, list, i4);
            parseFoundFinderPattern = parseFoundFinderPattern(bitArray, i3, z2, list);
            if (parseFoundFinderPattern == null) {
                i4 = getNextSecondBar(bitArray, this.startEnd[0]);
                continue;
            } else {
                try {
                    dataCharacter2 = decodeDataCharacter(bitArray, parseFoundFinderPattern, z2, true);
                    z3 = false;
                    continue;
                } catch (NotFoundException unused) {
                    i4 = getNextSecondBar(bitArray, this.startEnd[0]);
                    continue;
                }
            }
        } while (z3);
        if (list.isEmpty() || !((ExpandedPair) android.support.v4.media.session.a.h(list, 1)).mustBeLast()) {
            try {
                dataCharacter = decodeDataCharacter(bitArray, parseFoundFinderPattern, z2, false);
            } catch (NotFoundException unused2) {
            }
            return new ExpandedPair(dataCharacter2, dataCharacter, parseFoundFinderPattern);
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private List<ExpandedPair> checkRows(List<ExpandedRow> list, int i3) throws NotFoundException {
        while (i3 < this.rows.size()) {
            ExpandedRow expandedRow = this.rows.get(i3);
            this.pairs.clear();
            for (ExpandedRow pairs2 : list) {
                this.pairs.addAll(pairs2.getPairs());
            }
            this.pairs.addAll(expandedRow.getPairs());
            if (!isValidSequence(this.pairs, false)) {
                i3++;
            } else if (checkChecksum()) {
                return this.pairs;
            } else {
                ArrayList arrayList = new ArrayList(list);
                arrayList.add(expandedRow);
                try {
                    return checkRows(arrayList, i3 + 1);
                } catch (NotFoundException unused) {
                }
            }
        }
        throw NotFoundException.getNotFoundInstance();
    }
}
