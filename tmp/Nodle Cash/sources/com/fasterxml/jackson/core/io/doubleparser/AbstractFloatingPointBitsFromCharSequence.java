package com.fasterxml.jackson.core.io.doubleparser;

import org.apache.commons.lang3.ClassUtils;

abstract class AbstractFloatingPointBitsFromCharSequence extends AbstractFloatValueParser {
    private boolean isDigit(char c3) {
        return '0' <= c3 && c3 <= '9';
    }

    private long parseDecFloatLiteral(CharSequence charSequence, int i3, int i4, int i5, boolean z2, boolean z3) {
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
        CharSequence charSequence2 = charSequence;
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
            c5 = charSequence2.charAt(i13);
            if (!isDigit(c5)) {
                if (c5 != '.') {
                    break;
                }
                z6 |= i12 >= 0;
                int i14 = i13;
                while (i14 < i11 - 8 && (tryToParseEightDigits = tryToParseEightDigits(charSequence2, i14 + 1)) >= 0) {
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
            c4 = i8 < i11 ? charSequence2.charAt(i8) : 0;
            boolean z7 = c4 == '-';
            if (z7 || c4 == '+') {
                i8 = i13 + 2;
                c4 = i8 < i11 ? charSequence2.charAt(i8) : 0;
            }
            z6 |= !isDigit(c4);
            i9 = 0;
            while (true) {
                if (i9 < 1024) {
                    i9 = ((i9 * 10) + c4) - 48;
                }
                i8++;
                c4 = i8 < i11 ? charSequence2.charAt(i8) : 0;
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
        int skipWhitespace = skipWhitespace(charSequence2, i8, i11);
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
                char charAt = charSequence2.charAt(i17);
                if (charAt != c3) {
                    if (Long.compareUnsigned(j2, 1000000000000000000L) >= 0) {
                        break;
                    }
                    j2 = ((j2 * 10) + ((long) charAt)) - 48;
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
        return valueOfFloatLiteral(charSequence, i4, i5, z2, j2, i16, z5, i10);
    }

    private long parseHexFloatLiteral(CharSequence charSequence, int i3, int i4, int i5, boolean z2) {
        int i6;
        int i7;
        int i8;
        boolean z3;
        char c3;
        int i9;
        boolean z4;
        int i10;
        int i11;
        boolean z5;
        long j2;
        CharSequence charSequence2 = charSequence;
        int i12 = i5;
        int i13 = i3;
        long j3 = 0;
        int i14 = -1;
        char c4 = 0;
        boolean z6 = false;
        while (true) {
            boolean z7 = true;
            if (i13 >= i12) {
                break;
            }
            c4 = charSequence2.charAt(i13);
            byte b3 = c4 > 127 ? -1 : AbstractFloatValueParser.CHAR_TO_HEX_MAP[c4];
            if (b3 < 0) {
                if (b3 != -4) {
                    break;
                }
                if (i14 < 0) {
                    z7 = false;
                }
                z6 |= z7;
                i14 = i13;
            } else {
                j3 = (j3 << 4) | ((long) b3);
            }
            i13++;
        }
        if (i14 < 0) {
            i7 = i13 - i3;
            i14 = i13;
            i6 = 0;
        } else {
            i7 = (i13 - i3) - 1;
            i6 = Math.min((i14 - i13) + 1, 1024) * 4;
        }
        boolean z8 = c4 == 'p' || c4 == 'P';
        if (z8) {
            int i15 = i13 + 1;
            char charAt = i15 < i12 ? charSequence2.charAt(i15) : 0;
            boolean z9 = charAt == '-';
            if (z9 || charAt == '+') {
                i15 = i13 + 2;
                charAt = i15 < i12 ? charSequence2.charAt(i15) : 0;
            }
            boolean z10 = (!isDigit(charAt)) | z6;
            int i16 = 1024;
            i8 = 0;
            while (true) {
                if (i8 < i16) {
                    i8 = ((i8 * 10) + charAt) - 48;
                }
                z3 = true;
                i15++;
                c3 = i15 < i12 ? charSequence2.charAt(i15) : 0;
                if (!isDigit(c3)) {
                    break;
                }
                charAt = c3;
                i16 = 1024;
            }
            if (z9) {
                i8 = -i8;
            }
            int i17 = i6 + i8;
            z4 = z10;
            i10 = i15;
            i9 = i17;
        } else {
            z3 = true;
            i10 = i13;
            i8 = 0;
            char c5 = c4;
            i9 = i6;
            z4 = z6;
            c3 = c5;
        }
        if (i10 < i12 && (c3 == 'd' || c3 == 'D' || c3 == 'f' || c3 == 'F')) {
            i10++;
        }
        int skipWhitespace = skipWhitespace(charSequence2, i10, i12);
        if (z4 || skipWhitespace < i12 || i7 == 0 || !z8) {
            return -1;
        }
        if (i7 > 16) {
            skipWhitespace = i3;
            long j4 = 0;
            int i18 = 0;
            while (skipWhitespace < i13) {
                char charAt2 = charSequence2.charAt(skipWhitespace);
                byte b4 = charAt2 > 127 ? -1 : AbstractFloatValueParser.CHAR_TO_HEX_MAP[charAt2];
                if (b4 >= 0) {
                    if (Long.compareUnsigned(j4, 1000000000000000000L) >= 0) {
                        break;
                    }
                    j4 = (j4 << 4) | ((long) b4);
                } else {
                    i18++;
                }
                skipWhitespace++;
            }
            if (skipWhitespace >= i13) {
                z3 = false;
            }
            i11 = i18;
            z5 = z3;
            j2 = j4;
        } else {
            j2 = j3;
            z5 = false;
            i11 = 0;
        }
        return valueOfHexLiteral(charSequence, i4, i5, z2, j2, i9, z5, (i14 - skipWhitespace) + i11 + i8);
    }

    private long parseInfinity(CharSequence charSequence, int i3, int i4, boolean z2) {
        int i5 = i3 + 7;
        if (i5 < i4 && charSequence.charAt(i3) == 'I' && charSequence.charAt(i3 + 1) == 'n' && charSequence.charAt(i3 + 2) == 'f' && charSequence.charAt(i3 + 3) == 'i' && charSequence.charAt(i3 + 4) == 'n' && charSequence.charAt(i3 + 5) == 'i' && charSequence.charAt(i3 + 6) == 't' && charSequence.charAt(i5) == 'y' && skipWhitespace(charSequence, i3 + 8, i4) == i4) {
            return z2 ? negativeInfinity() : positiveInfinity();
        }
        return -1;
    }

    private long parseNaN(CharSequence charSequence, int i3, int i4) {
        int i5 = i3 + 2;
        if (i5 < i4 && charSequence.charAt(i3 + 1) == 'a' && charSequence.charAt(i5) == 'N' && skipWhitespace(charSequence, i3 + 3, i4) == i4) {
            return nan();
        }
        return -1;
    }

    private int skipWhitespace(CharSequence charSequence, int i3, int i4) {
        while (i3 < i4 && charSequence.charAt(i3) <= ' ') {
            i3++;
        }
        return i3;
    }

    private int tryToParseEightDigits(CharSequence charSequence, int i3) {
        return FastDoubleSwar.tryToParseEightDigitsUtf16(((long) charSequence.charAt(i3)) | (((long) charSequence.charAt(i3 + 1)) << 16) | (((long) charSequence.charAt(i3 + 2)) << 32) | (((long) charSequence.charAt(i3 + 3)) << 48), (((long) charSequence.charAt(i3 + 7)) << 48) | ((long) charSequence.charAt(i3 + 4)) | (((long) charSequence.charAt(i3 + 5)) << 16) | (((long) charSequence.charAt(i3 + 6)) << 32));
    }

    public abstract long nan();

    public abstract long negativeInfinity();

    public long parseFloatingPointLiteral(CharSequence charSequence, int i3, int i4) {
        int skipWhitespace;
        int i5;
        int i6 = i3 + i4;
        if (i3 < 0 || i6 > charSequence.length() || (skipWhitespace = skipWhitespace(charSequence, i3, i6)) == i6) {
            return -1;
        }
        char charAt = charSequence.charAt(skipWhitespace);
        char c3 = 0;
        boolean z2 = true;
        boolean z3 = charAt == '-';
        if (z3 || charAt == '+') {
            skipWhitespace++;
            charAt = skipWhitespace < i6 ? charSequence.charAt(skipWhitespace) : 0;
            if (charAt == 0) {
                return -1;
            }
        }
        if (charAt >= 'I') {
            return charAt == 'N' ? parseNaN(charSequence, skipWhitespace, i6) : parseInfinity(charSequence, skipWhitespace, i6, z3);
        }
        if (charAt != '0') {
            z2 = false;
        }
        if (z2) {
            int i7 = skipWhitespace + 1;
            if (i7 < i6) {
                c3 = charSequence.charAt(i7);
            }
            if (c3 == 'x' || c3 == 'X') {
                return parseHexFloatLiteral(charSequence, skipWhitespace + 2, i3, i6, z3);
            }
            i5 = i7;
        } else {
            i5 = skipWhitespace;
        }
        return parseDecFloatLiteral(charSequence, i5, i3, i6, z3, z2);
    }

    public abstract long positiveInfinity();

    public abstract long valueOfFloatLiteral(CharSequence charSequence, int i3, int i4, boolean z2, long j2, int i5, boolean z3, int i6);

    public abstract long valueOfHexLiteral(CharSequence charSequence, int i3, int i4, boolean z2, long j2, int i5, boolean z3, int i6);
}
