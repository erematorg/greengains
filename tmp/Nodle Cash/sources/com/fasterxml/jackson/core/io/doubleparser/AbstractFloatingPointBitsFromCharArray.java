package com.fasterxml.jackson.core.io.doubleparser;

import org.apache.commons.lang3.ClassUtils;

abstract class AbstractFloatingPointBitsFromCharArray extends AbstractFloatValueParser {
    private static boolean isDigit(char c3) {
        return '0' <= c3 && c3 <= '9';
    }

    private long parseDecFloatLiteral(char[] cArr, int i3, int i4, int i5, boolean z2, boolean z3) {
        char c3;
        boolean z4;
        char c4;
        int i6;
        int i7;
        int i8;
        int i9;
        int i10;
        boolean z5;
        long j2;
        int tryToParseEightDigits;
        char[] cArr2 = cArr;
        int i11 = i5;
        int i12 = -1;
        int i13 = i3;
        long j3 = 0;
        char c5 = 0;
        boolean z6 = false;
        while (true) {
            c3 = ClassUtils.PACKAGE_SEPARATOR_CHAR;
            z4 = true;
            if (i13 >= i11) {
                break;
            }
            c5 = cArr2[i13];
            if (!isDigit(c5)) {
                if (c5 != '.') {
                    break;
                }
                z6 |= i12 >= 0;
                int i14 = i13;
                while (i14 < i11 - 8 && (tryToParseEightDigits = tryToParseEightDigits(cArr2, i14 + 1)) >= 0) {
                    j3 = (j3 * 100000000) + ((long) tryToParseEightDigits);
                    i14 += 8;
                }
                int i15 = i13;
                i13 = i14;
                i12 = i15;
            } else {
                j3 = ((j3 * 10) + ((long) c5)) - 48;
            }
            i13++;
        }
        if (i12 < 0) {
            i7 = i13 - i3;
            i12 = i13;
            i6 = 0;
        } else {
            i7 = (i13 - i3) - 1;
            i6 = (i12 - i13) + 1;
        }
        if (c4 == 'e' || c4 == 'E') {
            i8 = i13 + 1;
            c4 = i8 < i11 ? cArr2[i8] : 0;
            boolean z7 = c4 == '-';
            if (z7 || c4 == '+') {
                i8 = i13 + 2;
                c4 = i8 < i11 ? cArr2[i8] : 0;
            }
            z6 |= !isDigit(c4);
            i9 = 0;
            while (true) {
                if (i9 < 1024) {
                    i9 = ((i9 * 10) + c4) - 48;
                }
                i8++;
                c4 = i8 < i11 ? cArr2[i8] : 0;
                if (!isDigit(c4)) {
                    break;
                }
            }
            if (z7) {
                i9 = -i9;
            }
            i6 += i9;
        } else {
            i8 = i13;
            i9 = 0;
        }
        char c6 = c4;
        int i16 = i6;
        char c7 = c6;
        if (i8 < i11 && (c7 == 'd' || c7 == 'D' || c7 == 'f' || c7 == 'F')) {
            i8++;
        }
        int skipWhitespace = skipWhitespace(cArr2, i8, i11);
        if (z6 || skipWhitespace < i11) {
            return -1;
        }
        if (!z3 && i7 == 0) {
            return -1;
        }
        if (i7 > 19) {
            int i17 = i3;
            j2 = 0;
            int i18 = 0;
            while (i17 < i13) {
                char c8 = cArr2[i17];
                if (c8 != c3) {
                    if (Long.compareUnsigned(j2, 1000000000000000000L) >= 0) {
                        break;
                    }
                    j2 = ((j2 * 10) + ((long) c8)) - 48;
                } else {
                    i18++;
                }
                i17++;
                c3 = ClassUtils.PACKAGE_SEPARATOR_CHAR;
            }
            if (i17 >= i13) {
                z4 = false;
            }
            i10 = (i12 - i17) + i18 + i9;
            z5 = z4;
        } else {
            j2 = j3;
            z5 = false;
            i10 = 0;
        }
        return valueOfFloatLiteral(cArr, i4, i5, z2, j2, i16, z5, i10);
    }

    private long parseHexFloatLiteral(char[] cArr, int i3, int i4, int i5, boolean z2) {
        boolean z3;
        int i6;
        int i7;
        int i8;
        int i9;
        int i10;
        char c3;
        int i11;
        boolean z4;
        long j2;
        int i12;
        char[] cArr2 = cArr;
        int i13 = i5;
        int i14 = i3;
        long j3 = 0;
        int i15 = -1;
        char c4 = 0;
        boolean z5 = false;
        while (true) {
            z3 = true;
            if (i14 >= i13) {
                break;
            }
            c4 = cArr2[i14];
            byte b3 = c4 > 127 ? -1 : AbstractFloatValueParser.CHAR_TO_HEX_MAP[c4];
            if (b3 < 0) {
                if (b3 != -4) {
                    break;
                }
                if (i15 < 0) {
                    z3 = false;
                }
                z5 |= z3;
                i15 = i14;
            } else {
                j3 = (j3 << 4) | ((long) b3);
            }
            i14++;
        }
        if (i15 < 0) {
            i6 = i14 - i3;
            i7 = 0;
            i15 = i14;
        } else {
            i6 = (i14 - i3) - 1;
            i7 = Math.min((i15 - i14) + 1, 1024) * 4;
        }
        boolean z6 = c4 == 'p' || c4 == 'P';
        if (z6) {
            i9 = i14 + 1;
            char c5 = i9 < i13 ? cArr2[i9] : 0;
            boolean z7 = c5 == '-';
            if (z7 || c5 == '+') {
                i9 = i14 + 2;
                c5 = i9 < i13 ? cArr2[i9] : 0;
            }
            boolean z8 = (!isDigit(c5)) | z5;
            int i16 = 0;
            do {
                if (i16 < 1024) {
                    i16 = ((i16 * 10) + c5) - 48;
                }
                i9++;
                c5 = i9 < i13 ? cArr2[i9] : 0;
            } while (isDigit(c5));
            if (z7) {
                i16 = -i16;
            }
            c3 = c5;
            i8 = i7 + i16;
            i10 = i16;
            z5 = z8;
        } else {
            i9 = i14;
            c3 = c4;
            i8 = i7;
            i10 = 0;
        }
        if (i9 < i13 && (c3 == 'd' || c3 == 'D' || c3 == 'f' || c3 == 'F')) {
            i9++;
        }
        int skipWhitespace = skipWhitespace(cArr2, i9, i13);
        if (z5 || skipWhitespace < i13 || i6 == 0 || !z6) {
            return -1;
        }
        if (i6 > 16) {
            i12 = i3;
            int i17 = 0;
            long j4 = 0;
            while (i12 < i14) {
                char c6 = cArr2[i12];
                byte b4 = c6 > 127 ? -1 : AbstractFloatValueParser.CHAR_TO_HEX_MAP[c6];
                if (b4 >= 0) {
                    if (Long.compareUnsigned(j4, 1000000000000000000L) >= 0) {
                        break;
                    }
                    j4 = (j4 << 4) | ((long) b4);
                } else {
                    i17++;
                }
                i12++;
            }
            if (i12 >= i14) {
                z3 = false;
            }
            i11 = i17;
            j2 = j4;
            z4 = z3;
        } else {
            i12 = skipWhitespace;
            j2 = j3;
            z4 = false;
            i11 = 0;
        }
        return valueOfHexLiteral(cArr, i4, i5, z2, j2, i8, z4, (i15 - i12) + i11 + i10);
    }

    private long parseInfinity(char[] cArr, int i3, int i4, boolean z2) {
        int i5 = i3 + 7;
        if (i5 < i4 && cArr[i3] == 'I' && cArr[i3 + 1] == 'n' && cArr[i3 + 2] == 'f' && cArr[i3 + 3] == 'i' && cArr[i3 + 4] == 'n' && cArr[i3 + 5] == 'i' && cArr[i3 + 6] == 't' && cArr[i5] == 'y' && skipWhitespace(cArr, i3 + 8, i4) == i4) {
            return z2 ? negativeInfinity() : positiveInfinity();
        }
        return -1;
    }

    private long parseNaN(char[] cArr, int i3, int i4) {
        int i5 = i3 + 2;
        if (i5 < i4 && cArr[i3 + 1] == 'a' && cArr[i5] == 'N' && skipWhitespace(cArr, i3 + 3, i4) == i4) {
            return nan();
        }
        return -1;
    }

    private int skipWhitespace(char[] cArr, int i3, int i4) {
        while (i3 < i4 && (cArr[i3] & 255) <= ' ') {
            i3++;
        }
        return i3;
    }

    private int tryToParseEightDigits(char[] cArr, int i3) {
        return FastDoubleSwar.tryToParseEightDigitsUtf16(cArr, i3);
    }

    public abstract long nan();

    public abstract long negativeInfinity();

    public long parseFloatingPointLiteral(char[] cArr, int i3, int i4) {
        int skipWhitespace;
        int i5;
        int i6 = i3 + i4;
        if (i3 < 0 || i6 > cArr.length || (skipWhitespace = skipWhitespace(cArr, i3, i6)) == i6) {
            return -1;
        }
        char c3 = cArr[skipWhitespace];
        char c4 = 0;
        boolean z2 = true;
        boolean z3 = c3 == '-';
        if (z3 || c3 == '+') {
            skipWhitespace++;
            c3 = skipWhitespace < i6 ? cArr[skipWhitespace] : 0;
            if (c3 == 0) {
                return -1;
            }
        }
        if (c3 >= 'I') {
            return c3 == 'N' ? parseNaN(cArr, skipWhitespace, i6) : parseInfinity(cArr, skipWhitespace, i6, z3);
        }
        if (c3 != '0') {
            z2 = false;
        }
        if (z2) {
            int i7 = skipWhitespace + 1;
            if (i7 < i6) {
                c4 = cArr[i7];
            }
            if (c4 == 'x' || c4 == 'X') {
                return parseHexFloatLiteral(cArr, skipWhitespace + 2, i3, i6, z3);
            }
            i5 = i7;
        } else {
            i5 = skipWhitespace;
        }
        return parseDecFloatLiteral(cArr, i5, i3, i6, z3, z2);
    }

    public abstract long positiveInfinity();

    public abstract long valueOfFloatLiteral(char[] cArr, int i3, int i4, boolean z2, long j2, int i5, boolean z3, int i6);

    public abstract long valueOfHexLiteral(char[] cArr, int i3, int i4, boolean z2, long j2, int i5, boolean z3, int i6);
}
