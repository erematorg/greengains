package com.google.zxing.oned;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.ChecksumException;
import com.google.zxing.DecodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.ResultMetadataType;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitArray;
import java.util.Arrays;
import java.util.Map;
import jnr.posix.FileStat;

public final class Code93Reader extends OneDReader {
    private static final char[] ALPHABET = ALPHABET_STRING.toCharArray();
    static final String ALPHABET_STRING = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. $/+%abcd*";
    static final int ASTERISK_ENCODING;
    static final int[] CHARACTER_ENCODINGS;
    private final int[] counters = new int[6];
    private final StringBuilder decodeRowResult = new StringBuilder(20);

    static {
        int[] iArr = {276, 328, 324, 322, 296, FileStat.ALL_READ, 290, 336, 274, 266, TypedValues.CycleType.TYPE_WAVE_OFFSET, TypedValues.CycleType.TYPE_EASING, 418, 404, TypedValues.CycleType.TYPE_VISIBILITY, 394, 360, 356, 354, 308, 282, 344, 332, 326, 300, 278, 436, 434, 428, TypedValues.CycleType.TYPE_CUSTOM_WAVE_SHAPE, 406, 410, 364, 358, 310, 314, 302, 468, 466, 458, 366, 374, 430, 294, 474, 470, 306, 350};
        CHARACTER_ENCODINGS = iArr;
        ASTERISK_ENCODING = iArr[47];
    }

    private static void checkChecksums(CharSequence charSequence) throws ChecksumException {
        int length = charSequence.length();
        checkOneChecksum(charSequence, length - 2, 20);
        checkOneChecksum(charSequence, length - 1, 15);
    }

    private static void checkOneChecksum(CharSequence charSequence, int i3, int i4) throws ChecksumException {
        int i5 = 0;
        int i6 = 1;
        for (int i7 = i3 - 1; i7 >= 0; i7--) {
            i5 += ALPHABET_STRING.indexOf(charSequence.charAt(i7)) * i6;
            i6++;
            if (i6 > i4) {
                i6 = 1;
            }
        }
        if (charSequence.charAt(i3) != ALPHABET[i5 % 47]) {
            throw ChecksumException.getChecksumInstance();
        }
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:10:0x002c, code lost:
        r4 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x009e, code lost:
        r1.append(r4);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String decodeExtended(java.lang.CharSequence r9) throws com.google.zxing.FormatException {
        /*
            int r0 = r9.length()
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>(r0)
            r2 = 0
            r3 = r2
        L_0x000b:
            if (r3 >= r0) goto L_0x00b3
            char r4 = r9.charAt(r3)
            r5 = 97
            if (r4 < r5) goto L_0x00ac
            r5 = 100
            if (r4 > r5) goto L_0x00ac
            int r5 = r0 + -1
            if (r3 >= r5) goto L_0x00a7
            int r3 = r3 + 1
            char r5 = r9.charAt(r3)
            r6 = 79
            r7 = 90
            r8 = 65
            switch(r4) {
                case 97: goto L_0x0097;
                case 98: goto L_0x004e;
                case 99: goto L_0x003d;
                case 100: goto L_0x002f;
                default: goto L_0x002c;
            }
        L_0x002c:
            r4 = r2
            goto L_0x009e
        L_0x002f:
            if (r5 < r8) goto L_0x0038
            if (r5 > r7) goto L_0x0038
            int r5 = r5 + 32
        L_0x0035:
            char r4 = (char) r5
            goto L_0x009e
        L_0x0038:
            com.google.zxing.FormatException r9 = com.google.zxing.FormatException.getFormatInstance()
            throw r9
        L_0x003d:
            if (r5 < r8) goto L_0x0044
            if (r5 > r6) goto L_0x0044
            int r5 = r5 + -32
            goto L_0x0035
        L_0x0044:
            if (r5 != r7) goto L_0x0049
            r4 = 58
            goto L_0x009e
        L_0x0049:
            com.google.zxing.FormatException r9 = com.google.zxing.FormatException.getFormatInstance()
            throw r9
        L_0x004e:
            if (r5 < r8) goto L_0x0057
            r4 = 69
            if (r5 > r4) goto L_0x0057
            int r5 = r5 + -38
            goto L_0x0035
        L_0x0057:
            r4 = 70
            if (r5 < r4) goto L_0x0062
            r4 = 74
            if (r5 > r4) goto L_0x0062
            int r5 = r5 + -11
            goto L_0x0035
        L_0x0062:
            r4 = 75
            if (r5 < r4) goto L_0x006b
            if (r5 > r6) goto L_0x006b
            int r5 = r5 + 16
            goto L_0x0035
        L_0x006b:
            r4 = 80
            if (r5 < r4) goto L_0x0076
            r4 = 84
            if (r5 > r4) goto L_0x0076
            int r5 = r5 + 43
            goto L_0x0035
        L_0x0076:
            r4 = 85
            if (r5 != r4) goto L_0x007b
            goto L_0x002c
        L_0x007b:
            r4 = 86
            if (r5 != r4) goto L_0x0082
            r4 = 64
            goto L_0x009e
        L_0x0082:
            r4 = 87
            if (r5 != r4) goto L_0x0089
            r4 = 96
            goto L_0x009e
        L_0x0089:
            r4 = 88
            if (r5 < r4) goto L_0x0092
            if (r5 > r7) goto L_0x0092
            r4 = 127(0x7f, float:1.78E-43)
            goto L_0x009e
        L_0x0092:
            com.google.zxing.FormatException r9 = com.google.zxing.FormatException.getFormatInstance()
            throw r9
        L_0x0097:
            if (r5 < r8) goto L_0x00a2
            if (r5 > r7) goto L_0x00a2
            int r5 = r5 + -64
            goto L_0x0035
        L_0x009e:
            r1.append(r4)
            goto L_0x00af
        L_0x00a2:
            com.google.zxing.FormatException r9 = com.google.zxing.FormatException.getFormatInstance()
            throw r9
        L_0x00a7:
            com.google.zxing.FormatException r9 = com.google.zxing.FormatException.getFormatInstance()
            throw r9
        L_0x00ac:
            r1.append(r4)
        L_0x00af:
            int r3 = r3 + 1
            goto L_0x000b
        L_0x00b3:
            java.lang.String r9 = r1.toString()
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.oned.Code93Reader.decodeExtended(java.lang.CharSequence):java.lang.String");
    }

    private int[] findAsteriskPattern(BitArray bitArray) throws NotFoundException {
        int size = bitArray.getSize();
        int nextSet = bitArray.getNextSet(0);
        Arrays.fill(this.counters, 0);
        int[] iArr = this.counters;
        int length = iArr.length;
        boolean z2 = false;
        int i3 = 0;
        int i4 = nextSet;
        while (nextSet < size) {
            if (bitArray.get(nextSet) != z2) {
                iArr[i3] = iArr[i3] + 1;
            } else {
                if (i3 != length - 1) {
                    i3++;
                } else if (toPattern(iArr) == ASTERISK_ENCODING) {
                    return new int[]{i4, nextSet};
                } else {
                    i4 += iArr[0] + iArr[1];
                    int i5 = i3 - 1;
                    System.arraycopy(iArr, 2, iArr, 0, i5);
                    iArr[i5] = 0;
                    iArr[i3] = 0;
                    i3--;
                }
                iArr[i3] = 1;
                z2 = !z2;
            }
            nextSet++;
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private static char patternToChar(int i3) throws NotFoundException {
        int i4 = 0;
        while (true) {
            int[] iArr = CHARACTER_ENCODINGS;
            if (i4 >= iArr.length) {
                throw NotFoundException.getNotFoundInstance();
            } else if (iArr[i4] == i3) {
                return ALPHABET[i4];
            } else {
                i4++;
            }
        }
    }

    private static int toPattern(int[] iArr) {
        int i3 = 0;
        for (int i4 : iArr) {
            i3 += i4;
        }
        int length = iArr.length;
        int i5 = 0;
        for (int i6 = 0; i6 < length; i6++) {
            int round = Math.round((((float) iArr[i6]) * 9.0f) / ((float) i3));
            if (round < 1 || round > 4) {
                return -1;
            }
            if ((i6 & 1) == 0) {
                for (int i7 = 0; i7 < round; i7++) {
                    i5 = (i5 << 1) | 1;
                }
            } else {
                i5 <<= round;
            }
        }
        return i5;
    }

    public Result decodeRow(int i3, BitArray bitArray, Map<DecodeHintType, ?> map) throws NotFoundException, ChecksumException, FormatException {
        int[] findAsteriskPattern = findAsteriskPattern(bitArray);
        int nextSet = bitArray.getNextSet(findAsteriskPattern[1]);
        int size = bitArray.getSize();
        int[] iArr = this.counters;
        Arrays.fill(iArr, 0);
        StringBuilder sb = this.decodeRowResult;
        sb.setLength(0);
        while (true) {
            OneDReader.recordPattern(bitArray, nextSet, iArr);
            int pattern = toPattern(iArr);
            if (pattern >= 0) {
                char patternToChar = patternToChar(pattern);
                sb.append(patternToChar);
                int i4 = nextSet;
                for (int i5 : iArr) {
                    i4 += i5;
                }
                int nextSet2 = bitArray.getNextSet(i4);
                if (patternToChar == '*') {
                    sb.deleteCharAt(sb.length() - 1);
                    int i6 = 0;
                    for (int i7 : iArr) {
                        i6 += i7;
                    }
                    if (nextSet2 == size || !bitArray.get(nextSet2)) {
                        throw NotFoundException.getNotFoundInstance();
                    } else if (sb.length() >= 2) {
                        checkChecksums(sb);
                        sb.setLength(sb.length() - 2);
                        float f2 = (float) i3;
                        Result result = new Result(decodeExtended(sb), (byte[]) null, new ResultPoint[]{new ResultPoint(((float) (findAsteriskPattern[1] + findAsteriskPattern[0])) / 2.0f, f2), new ResultPoint((((float) i6) / 2.0f) + ((float) nextSet), f2)}, BarcodeFormat.CODE_93);
                        result.putMetadata(ResultMetadataType.SYMBOLOGY_IDENTIFIER, "]G0");
                        return result;
                    } else {
                        throw NotFoundException.getNotFoundInstance();
                    }
                } else {
                    nextSet = nextSet2;
                }
            } else {
                throw NotFoundException.getNotFoundInstance();
            }
        }
    }
}
