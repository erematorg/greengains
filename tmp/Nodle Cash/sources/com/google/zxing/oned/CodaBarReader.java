package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.DecodeHintType;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.ResultMetadataType;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitArray;
import java.util.Arrays;
import java.util.Map;

public final class CodaBarReader extends OneDReader {
    static final char[] ALPHABET = ALPHABET_STRING.toCharArray();
    private static final String ALPHABET_STRING = "0123456789-$:/.+ABCD";
    static final int[] CHARACTER_ENCODINGS = {3, 6, 9, 96, 18, 66, 33, 36, 48, 72, 12, 24, 69, 81, 84, 21, 26, 41, 11, 14};
    private static final float MAX_ACCEPTABLE = 2.0f;
    private static final int MIN_CHARACTER_LENGTH = 3;
    private static final float PADDING = 1.5f;
    private static final char[] STARTEND_ENCODING = {'A', 'B', 'C', 'D'};
    private int counterLength = 0;
    private int[] counters = new int[80];
    private final StringBuilder decodeRowResult = new StringBuilder(20);

    public static boolean arrayContains(char[] cArr, char c3) {
        if (cArr != null) {
            for (char c4 : cArr) {
                if (c4 == c3) {
                    return true;
                }
            }
        }
        return false;
    }

    private void counterAppend(int i3) {
        int[] iArr = this.counters;
        int i4 = this.counterLength;
        iArr[i4] = i3;
        int i5 = i4 + 1;
        this.counterLength = i5;
        if (i5 >= iArr.length) {
            int[] iArr2 = new int[(i5 * 2)];
            System.arraycopy(iArr, 0, iArr2, 0, i5);
            this.counters = iArr2;
        }
    }

    private int findStartPattern() throws NotFoundException {
        for (int i3 = 1; i3 < this.counterLength; i3 += 2) {
            int narrowWidePattern = toNarrowWidePattern(i3);
            if (narrowWidePattern != -1 && arrayContains(STARTEND_ENCODING, ALPHABET[narrowWidePattern])) {
                int i4 = 0;
                for (int i5 = i3; i5 < i3 + 7; i5++) {
                    i4 += this.counters[i5];
                }
                if (i3 == 1 || this.counters[i3 - 1] >= i4 / 2) {
                    return i3;
                }
            }
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private void setCounters(BitArray bitArray) throws NotFoundException {
        int i3 = 0;
        this.counterLength = 0;
        int nextUnset = bitArray.getNextUnset(0);
        int size = bitArray.getSize();
        if (nextUnset < size) {
            boolean z2 = true;
            while (nextUnset < size) {
                if (bitArray.get(nextUnset) != z2) {
                    i3++;
                } else {
                    counterAppend(i3);
                    z2 = !z2;
                    i3 = 1;
                }
                nextUnset++;
            }
            counterAppend(i3);
            return;
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private int toNarrowWidePattern(int i3) {
        int i4 = i3 + 7;
        if (i4 >= this.counterLength) {
            return -1;
        }
        int[] iArr = this.counters;
        int i5 = Integer.MAX_VALUE;
        int i6 = 0;
        int i7 = Integer.MAX_VALUE;
        int i8 = 0;
        for (int i9 = i3; i9 < i4; i9 += 2) {
            int i10 = iArr[i9];
            if (i10 < i7) {
                i7 = i10;
            }
            if (i10 > i8) {
                i8 = i10;
            }
        }
        int i11 = (i7 + i8) / 2;
        int i12 = 0;
        for (int i13 = i3 + 1; i13 < i4; i13 += 2) {
            int i14 = iArr[i13];
            if (i14 < i5) {
                i5 = i14;
            }
            if (i14 > i12) {
                i12 = i14;
            }
        }
        int i15 = (i5 + i12) / 2;
        int i16 = 128;
        int i17 = 0;
        for (int i18 = 0; i18 < 7; i18++) {
            i16 >>= 1;
            if (iArr[i3 + i18] > ((i18 & 1) == 0 ? i11 : i15)) {
                i17 |= i16;
            }
        }
        while (true) {
            int[] iArr2 = CHARACTER_ENCODINGS;
            if (i6 >= iArr2.length) {
                return -1;
            }
            if (iArr2[i6] == i17) {
                return i6;
            }
            i6++;
        }
    }

    private void validatePattern(int i3) throws NotFoundException {
        int[] iArr = {0, 0, 0, 0};
        int[] iArr2 = {0, 0, 0, 0};
        int length = this.decodeRowResult.length() - 1;
        int i4 = i3;
        int i5 = 0;
        while (true) {
            if (i5 > length) {
                break;
            }
            int i6 = CHARACTER_ENCODINGS[this.decodeRowResult.charAt(i5)];
            for (int i7 = 6; i7 >= 0; i7--) {
                int i8 = ((i6 & 1) * 2) + (i7 & 1);
                iArr[i8] = iArr[i8] + this.counters[i4 + i7];
                iArr2[i8] = iArr2[i8] + 1;
                i6 >>= 1;
            }
            i4 += 8;
            i5++;
        }
        float[] fArr = new float[4];
        float[] fArr2 = new float[4];
        for (int i9 = 0; i9 < 2; i9++) {
            fArr2[i9] = 0.0f;
            int i10 = i9 + 2;
            float f2 = ((float) iArr[i9]) / ((float) iArr2[i9]);
            int i11 = iArr[i10];
            int i12 = iArr2[i10];
            float f3 = ((((float) i11) / ((float) i12)) + f2) / 2.0f;
            fArr2[i10] = f3;
            fArr[i9] = f3;
            fArr[i10] = ((((float) i11) * 2.0f) + PADDING) / ((float) i12);
        }
        int i13 = i3;
        for (int i14 = 0; i14 <= length; i14++) {
            int i15 = CHARACTER_ENCODINGS[this.decodeRowResult.charAt(i14)];
            for (int i16 = 6; i16 >= 0; i16--) {
                int i17 = ((i15 & 1) * 2) + (i16 & 1);
                float f4 = (float) this.counters[i13 + i16];
                if (f4 < fArr2[i17] || f4 > fArr[i17]) {
                    throw NotFoundException.getNotFoundInstance();
                }
                i15 >>= 1;
            }
            i13 += 8;
        }
    }

    public Result decodeRow(int i3, BitArray bitArray, Map<DecodeHintType, ?> map) throws NotFoundException {
        int i4;
        Arrays.fill(this.counters, 0);
        setCounters(bitArray);
        int findStartPattern = findStartPattern();
        this.decodeRowResult.setLength(0);
        int i5 = findStartPattern;
        while (true) {
            int narrowWidePattern = toNarrowWidePattern(i5);
            if (narrowWidePattern != -1) {
                this.decodeRowResult.append((char) narrowWidePattern);
                i4 = i5 + 8;
                if ((this.decodeRowResult.length() <= 1 || !arrayContains(STARTEND_ENCODING, ALPHABET[narrowWidePattern])) && i4 < this.counterLength) {
                    i5 = i4;
                }
            } else {
                throw NotFoundException.getNotFoundInstance();
            }
        }
        int i6 = i5 + 7;
        int i7 = this.counters[i6];
        int i8 = 0;
        for (int i9 = -8; i9 < -1; i9++) {
            i8 += this.counters[i4 + i9];
        }
        if (i4 >= this.counterLength || i7 >= i8 / 2) {
            validatePattern(findStartPattern);
            for (int i10 = 0; i10 < this.decodeRowResult.length(); i10++) {
                StringBuilder sb = this.decodeRowResult;
                sb.setCharAt(i10, ALPHABET[sb.charAt(i10)]);
            }
            char charAt = this.decodeRowResult.charAt(0);
            char[] cArr = STARTEND_ENCODING;
            if (arrayContains(cArr, charAt)) {
                StringBuilder sb2 = this.decodeRowResult;
                if (!arrayContains(cArr, sb2.charAt(sb2.length() - 1))) {
                    throw NotFoundException.getNotFoundInstance();
                } else if (this.decodeRowResult.length() > 3) {
                    if (map == null || !map.containsKey(DecodeHintType.RETURN_CODABAR_START_END)) {
                        StringBuilder sb3 = this.decodeRowResult;
                        sb3.deleteCharAt(sb3.length() - 1);
                        this.decodeRowResult.deleteCharAt(0);
                    }
                    int i11 = 0;
                    for (int i12 = 0; i12 < findStartPattern; i12++) {
                        i11 += this.counters[i12];
                    }
                    float f2 = (float) i11;
                    while (findStartPattern < i6) {
                        i11 += this.counters[findStartPattern];
                        findStartPattern++;
                    }
                    float f3 = (float) i3;
                    Result result = new Result(this.decodeRowResult.toString(), (byte[]) null, new ResultPoint[]{new ResultPoint(f2, f3), new ResultPoint((float) i11, f3)}, BarcodeFormat.CODABAR);
                    result.putMetadata(ResultMetadataType.SYMBOLOGY_IDENTIFIER, "]F0");
                    return result;
                } else {
                    throw NotFoundException.getNotFoundInstance();
                }
            } else {
                throw NotFoundException.getNotFoundInstance();
            }
        } else {
            throw NotFoundException.getNotFoundInstance();
        }
    }
}
