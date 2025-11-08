package com.fasterxml.jackson.core.io.schubfach;

import java.io.IOException;

public final class DoubleToDecimal {
    private static final int BQ_MASK = 2047;
    private static final long C_MIN = 4503599627370496L;
    static final long C_TINY = 3;
    static final int E_MAX = 309;
    static final int E_MIN = -323;

    /* renamed from: H  reason: collision with root package name */
    static final int f6552H = 17;
    static final int K_MAX = 292;
    static final int K_MIN = -324;
    private static final int MASK_28 = 268435455;
    private static final long MASK_63 = Long.MAX_VALUE;
    public static final int MAX_CHARS = 24;
    private static final int MINUS_INF = 4;
    private static final int MINUS_ZERO = 2;
    private static final int NAN = 5;
    private static final int NON_SPECIAL = 0;
    static final int P = 53;
    private static final int PLUS_INF = 3;
    private static final int PLUS_ZERO = 1;
    static final int Q_MAX = 971;
    static final int Q_MIN = -1074;
    private static final long T_MASK = 4503599627370495L;

    /* renamed from: W  reason: collision with root package name */
    private static final int f6553W = 11;
    private final byte[] bytes = new byte[24];
    private int index;

    private DoubleToDecimal() {
    }

    private void append(int i3) {
        byte[] bArr = this.bytes;
        int i4 = this.index + 1;
        this.index = i4;
        bArr[i4] = (byte) i3;
    }

    private void append8Digits(int i3) {
        int y2 = y(i3);
        for (int i4 = 0; i4 < 8; i4++) {
            int i5 = y2 * 10;
            appendDigit(i5 >>> 28);
            y2 = i5 & MASK_28;
        }
    }

    private Appendable appendDecimalTo(double d2, Appendable appendable) throws IOException {
        int decimal = toDecimal(d2);
        if (decimal != 0) {
            return decimal != 1 ? decimal != 2 ? decimal != 3 ? decimal != 4 ? appendable.append("NaN") : appendable.append("-Infinity") : appendable.append("Infinity") : appendable.append("-0.0") : appendable.append("0.0");
        }
        int i3 = this.index + 1;
        char[] cArr = new char[i3];
        for (int i4 = 0; i4 < i3; i4++) {
            cArr[i4] = (char) this.bytes[i4];
        }
        if (appendable instanceof StringBuilder) {
            StringBuilder sb = (StringBuilder) appendable;
            sb.append(cArr);
            return sb;
        } else if (appendable instanceof StringBuffer) {
            StringBuffer stringBuffer = (StringBuffer) appendable;
            stringBuffer.append(cArr);
            return stringBuffer;
        } else {
            for (int i5 = 0; i5 < i3; i5++) {
                appendable.append(cArr[i5]);
            }
            return appendable;
        }
    }

    private void appendDigit(int i3) {
        byte[] bArr = this.bytes;
        int i4 = this.index + 1;
        this.index = i4;
        bArr[i4] = (byte) (i3 + 48);
    }

    public static Appendable appendTo(double d2, Appendable appendable) throws IOException {
        return new DoubleToDecimal().appendDecimalTo(d2, appendable);
    }

    private String charsToString() {
        return new String(this.bytes, 0, 0, this.index + 1);
    }

    private void exponent(int i3) {
        append(69);
        if (i3 < 0) {
            append(45);
            i3 = -i3;
        }
        if (i3 < 10) {
            appendDigit(i3);
            return;
        }
        if (i3 >= 100) {
            int i4 = (i3 * 1311) >>> 17;
            appendDigit(i4);
            i3 -= i4 * 100;
        }
        int i5 = (i3 * 103) >>> 10;
        appendDigit(i5);
        appendDigit(i3 - (i5 * 10));
    }

    private void lowDigits(int i3) {
        if (i3 != 0) {
            append8Digits(i3);
        }
        removeTrailingZeroes();
    }

    private void removeTrailingZeroes() {
        int i3;
        byte b3;
        while (true) {
            byte[] bArr = this.bytes;
            i3 = this.index;
            b3 = bArr[i3];
            if (b3 != 48) {
                break;
            }
            this.index = i3 - 1;
        }
        if (b3 == 46) {
            this.index = i3 + 1;
        }
    }

    private static long rop(long j2, long j3, long j4) {
        long multiplyHigh = MathUtils.multiplyHigh(j3, j4);
        long j5 = j2 * j4;
        long multiplyHigh2 = MathUtils.multiplyHigh(j2, j4);
        long j6 = (j5 >>> 1) + multiplyHigh;
        return (multiplyHigh2 + (j6 >>> 63)) | (((j6 & Long.MAX_VALUE) + Long.MAX_VALUE) >>> 63);
    }

    private int toChars(long j2, int i3) {
        int flog10pow2 = MathUtils.flog10pow2(64 - Long.numberOfLeadingZeros(j2));
        if (j2 >= MathUtils.pow10(flog10pow2)) {
            flog10pow2++;
        }
        long pow10 = j2 * MathUtils.pow10(17 - flog10pow2);
        int i4 = i3 + flog10pow2;
        long multiplyHigh = MathUtils.multiplyHigh(pow10, 193428131138340668L) >>> 20;
        int i5 = (int) (pow10 - (100000000 * multiplyHigh));
        int i6 = (int) ((1441151881 * multiplyHigh) >>> 57);
        int i7 = (int) (multiplyHigh - ((long) (100000000 * i6)));
        return (i4 <= 0 || i4 > 7) ? (-3 >= i4 || i4 > 0) ? toChars3(i6, i7, i5, i4) : toChars2(i6, i7, i5, i4) : toChars1(i6, i7, i5, i4);
    }

    private int toChars1(int i3, int i4, int i5, int i6) {
        appendDigit(i3);
        int y2 = y(i4);
        int i7 = 1;
        while (i7 < i6) {
            int i8 = y2 * 10;
            appendDigit(i8 >>> 28);
            y2 = i8 & MASK_28;
            i7++;
        }
        append(46);
        while (i7 <= 8) {
            int i9 = y2 * 10;
            appendDigit(i9 >>> 28);
            y2 = i9 & MASK_28;
            i7++;
        }
        lowDigits(i5);
        return 0;
    }

    private int toChars2(int i3, int i4, int i5, int i6) {
        appendDigit(0);
        append(46);
        while (i6 < 0) {
            appendDigit(0);
            i6++;
        }
        appendDigit(i3);
        append8Digits(i4);
        lowDigits(i5);
        return 0;
    }

    private int toChars3(int i3, int i4, int i5, int i6) {
        appendDigit(i3);
        append(46);
        append8Digits(i4);
        lowDigits(i5);
        exponent(i6 - 1);
        return 0;
    }

    private int toDecimal(double d2) {
        long doubleToRawLongBits = Double.doubleToRawLongBits(d2);
        long j2 = T_MASK & doubleToRawLongBits;
        int i3 = ((int) (doubleToRawLongBits >>> 52)) & BQ_MASK;
        if (i3 < BQ_MASK) {
            this.index = -1;
            int i4 = (doubleToRawLongBits > 0 ? 1 : (doubleToRawLongBits == 0 ? 0 : -1));
            if (i4 < 0) {
                append(45);
            }
            boolean z2 = true;
            if (i3 != 0) {
                int i5 = 1075 - i3;
                long j3 = j2 | C_MIN;
                boolean z3 = i5 > 0;
                if (i5 >= 53) {
                    z2 = false;
                }
                if (z2 && z3) {
                    long j4 = j3 >> i5;
                    if ((j4 << i5) == j3) {
                        return toChars(j4, 0);
                    }
                }
                return toDecimal(-i5, j3, 0);
            } else if (j2 != 0) {
                if (j2 < 3) {
                    return toDecimal(Q_MIN, j2 * 10, -1);
                }
                return toDecimal(Q_MIN, j2, 0);
            } else if (i4 == 0) {
                return 1;
            } else {
                return 2;
            }
        } else if (j2 != 0) {
            return 5;
        } else {
            return doubleToRawLongBits > 0 ? 3 : 4;
        }
    }

    private String toDecimalString(double d2) {
        int decimal = toDecimal(d2);
        return decimal != 0 ? decimal != 1 ? decimal != 2 ? decimal != 3 ? decimal != 4 ? "NaN" : "-Infinity" : "Infinity" : "-0.0" : "0.0" : charsToString();
    }

    public static String toString(double d2) {
        return new DoubleToDecimal().toDecimalString(d2);
    }

    private int y(int i3) {
        return ((int) (MathUtils.multiplyHigh(((long) (i3 + 1)) << 28, 193428131138340668L) >>> 20)) - 1;
    }

    private int toDecimal(int i3, long j2, int i4) {
        long j3;
        int i5;
        int i6 = i3;
        long j4 = j2;
        int i7 = ((int) j4) & 1;
        long j5 = j4 << 2;
        long j6 = j5 + 2;
        if ((j4 != C_MIN) || (i6 == Q_MIN)) {
            j3 = j5 - 2;
            i5 = MathUtils.flog10pow2(i3);
        } else {
            j3 = j5 - 1;
            i5 = MathUtils.flog10threeQuartersPow2(i3);
        }
        int flog2pow10 = i6 + MathUtils.flog2pow10(-i5) + 2;
        long g12 = MathUtils.g1(i5);
        long g02 = MathUtils.g0(i5);
        long rop = rop(g12, g02, j5 << flog2pow10);
        long rop2 = rop(g12, g02, j3 << flog2pow10);
        long rop3 = rop(g12, g02, j6 << flog2pow10);
        long j7 = rop >> 2;
        if (j7 >= 100) {
            long multiplyHigh = MathUtils.multiplyHigh(j7, 1844674407370955168L) * 10;
            long j8 = multiplyHigh + 10;
            int i8 = i5;
            long j9 = (long) i7;
            boolean z2 = rop2 + j9 <= (multiplyHigh << 2);
            if (z2 != ((j8 << 2) + j9 <= rop3)) {
                if (!z2) {
                    multiplyHigh = j8;
                }
                return toChars(multiplyHigh, i8);
            }
            i5 = i8;
        }
        long j10 = j7 + 1;
        long j11 = (long) i7;
        boolean z3 = rop2 + j11 <= (j7 << 2);
        if (z3 != ((j10 << 2) + j11 <= rop3)) {
            if (!z3) {
                j7 = j10;
            }
            return toChars(j7, i5 + i4);
        }
        int i9 = ((rop - ((j7 + j10) << 1)) > 0 ? 1 : ((rop - ((j7 + j10) << 1)) == 0 ? 0 : -1));
        if (i9 >= 0 && !(i9 == 0 && (1 & j7) == 0)) {
            j7 = j10;
        }
        return toChars(j7, i5 + i4);
    }
}
