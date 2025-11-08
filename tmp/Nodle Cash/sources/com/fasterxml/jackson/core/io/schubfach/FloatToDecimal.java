package com.fasterxml.jackson.core.io.schubfach;

import java.io.IOException;

public final class FloatToDecimal {
    private static final int BQ_MASK = 255;
    private static final int C_MIN = 8388608;
    static final int C_TINY = 8;
    static final int E_MAX = 39;
    static final int E_MIN = -44;

    /* renamed from: H  reason: collision with root package name */
    static final int f6554H = 9;
    static final int K_MAX = 31;
    static final int K_MIN = -45;
    private static final int MASK_28 = 268435455;
    private static final long MASK_32 = 4294967295L;
    public static final int MAX_CHARS = 15;
    private static final int MINUS_INF = 4;
    private static final int MINUS_ZERO = 2;
    private static final int NAN = 5;
    private static final int NON_SPECIAL = 0;
    static final int P = 24;
    private static final int PLUS_INF = 3;
    private static final int PLUS_ZERO = 1;
    static final int Q_MAX = 104;
    static final int Q_MIN = -149;
    private static final int T_MASK = 8388607;

    /* renamed from: W  reason: collision with root package name */
    private static final int f6555W = 8;
    private final byte[] bytes = new byte[15];
    private int index;

    private FloatToDecimal() {
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

    private Appendable appendDecimalTo(float f2, Appendable appendable) throws IOException {
        int decimal = toDecimal(f2);
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

    public static Appendable appendTo(float f2, Appendable appendable) throws IOException {
        return new FloatToDecimal().appendDecimalTo(f2, appendable);
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
        int i4 = (i3 * 103) >>> 10;
        appendDigit(i4);
        appendDigit(i3 - (i4 * 10));
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

    private static int rop(long j2, long j3) {
        long multiplyHigh = MathUtils.multiplyHigh(j2, j3);
        return (int) ((((multiplyHigh & 4294967295L) + 4294967295L) >>> 32) | (multiplyHigh >>> 31));
    }

    private int toChars(int i3, int i4) {
        int flog10pow2 = MathUtils.flog10pow2(32 - Integer.numberOfLeadingZeros(i3));
        if (((long) i3) >= MathUtils.pow10(flog10pow2)) {
            flog10pow2++;
        }
        int pow10 = i3 * ((int) MathUtils.pow10(9 - flog10pow2));
        int i5 = i4 + flog10pow2;
        int i6 = (int) ((((long) pow10) * 1441151881) >>> 57);
        int i7 = pow10 - (100000000 * i6);
        return (i5 <= 0 || i5 > 7) ? (-3 >= i5 || i5 > 0) ? toChars3(i6, i7, i5) : toChars2(i6, i7, i5) : toChars1(i6, i7, i5);
    }

    private int toChars1(int i3, int i4, int i5) {
        appendDigit(i3);
        int y2 = y(i4);
        int i6 = 1;
        while (i6 < i5) {
            int i7 = y2 * 10;
            appendDigit(i7 >>> 28);
            y2 = i7 & MASK_28;
            i6++;
        }
        append(46);
        while (i6 <= 8) {
            int i8 = y2 * 10;
            appendDigit(i8 >>> 28);
            y2 = i8 & MASK_28;
            i6++;
        }
        removeTrailingZeroes();
        return 0;
    }

    private int toChars2(int i3, int i4, int i5) {
        appendDigit(0);
        append(46);
        while (i5 < 0) {
            appendDigit(0);
            i5++;
        }
        appendDigit(i3);
        append8Digits(i4);
        removeTrailingZeroes();
        return 0;
    }

    private int toChars3(int i3, int i4, int i5) {
        appendDigit(i3);
        append(46);
        append8Digits(i4);
        removeTrailingZeroes();
        exponent(i5 - 1);
        return 0;
    }

    private int toDecimal(float f2) {
        int floatToRawIntBits = Float.floatToRawIntBits(f2);
        int i3 = T_MASK & floatToRawIntBits;
        int i4 = (floatToRawIntBits >>> 23) & 255;
        if (i4 < 255) {
            this.index = -1;
            if (floatToRawIntBits < 0) {
                append(45);
            }
            boolean z2 = true;
            if (i4 != 0) {
                int i5 = 150 - i4;
                int i6 = i3 | 8388608;
                boolean z3 = i5 > 0;
                if (i5 >= 24) {
                    z2 = false;
                }
                if (z3 && z2) {
                    int i7 = i6 >> i5;
                    if ((i7 << i5) == i6) {
                        return toChars(i7, 0);
                    }
                }
                return toDecimal(-i5, i6, 0);
            } else if (i3 != 0) {
                if (i3 < 8) {
                    return toDecimal(Q_MIN, i3 * 10, -1);
                }
                return toDecimal(Q_MIN, i3, 0);
            } else if (floatToRawIntBits == 0) {
                return 1;
            } else {
                return 2;
            }
        } else if (i3 != 0) {
            return 5;
        } else {
            return floatToRawIntBits > 0 ? 3 : 4;
        }
    }

    private String toDecimalString(float f2) {
        int decimal = toDecimal(f2);
        return decimal != 0 ? decimal != 1 ? decimal != 2 ? decimal != 3 ? decimal != 4 ? "NaN" : "-Infinity" : "Infinity" : "-0.0" : "0.0" : charsToString();
    }

    public static String toString(float f2) {
        return new FloatToDecimal().toDecimalString(f2);
    }

    private int y(int i3) {
        return ((int) (MathUtils.multiplyHigh(((long) (i3 + 1)) << 28, 193428131138340668L) >>> 20)) - 1;
    }

    private int toDecimal(int i3, int i4, int i5) {
        long j2;
        int i6;
        int i7 = i3;
        int i8 = i4;
        int i9 = i8 & 1;
        long j3 = (long) (i8 << 2);
        long j4 = j3 + 2;
        boolean z2 = false;
        if ((i8 != 8388608) || (i7 == Q_MIN)) {
            j2 = j3 - 2;
            i6 = MathUtils.flog10pow2(i3);
        } else {
            j2 = j3 - 1;
            i6 = MathUtils.flog10threeQuartersPow2(i3);
        }
        int flog2pow10 = i7 + MathUtils.flog2pow10(-i6) + 33;
        long g12 = 1 + MathUtils.g1(i6);
        int rop = rop(g12, j3 << flog2pow10);
        int rop2 = rop(g12, j2 << flog2pow10);
        int rop3 = rop(g12, j4 << flog2pow10);
        int i10 = rop >> 2;
        if (i10 >= 100) {
            int i11 = ((int) ((((long) i10) * 1717986919) >>> 34)) * 10;
            int i12 = i11 + 10;
            boolean z3 = rop2 + i9 <= (i11 << 2);
            if (z3 != ((i12 << 2) + i9 <= rop3)) {
                if (!z3) {
                    i11 = i12;
                }
                return toChars(i11, i6);
            }
        }
        int i13 = i10 + 1;
        boolean z4 = rop2 + i9 <= (i10 << 2);
        if ((i13 << 2) + i9 <= rop3) {
            z2 = true;
        }
        if (z4 != z2) {
            if (!z4) {
                i10 = i13;
            }
            return toChars(i10, i6 + i5);
        }
        int i14 = rop - ((i10 + i13) << 1);
        if (i14 >= 0 && !(i14 == 0 && (i10 & 1) == 0)) {
            i10 = i13;
        }
        return toChars(i10, i6 + i5);
    }
}
