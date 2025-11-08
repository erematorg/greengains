package com.fasterxml.jackson.core.io;

import A.a;
import androidx.camera.camera2.internal.C0118y;
import androidx.collection.SieveCacheKt;
import java.math.BigDecimal;
import java.util.Arrays;

public final class BigDecimalParser {
    private static final int MAX_CHARS_TO_REPORT = 1000;

    private BigDecimalParser() {
    }

    private static int adjustScale(int i3, long j2) {
        long j3 = ((long) i3) - j2;
        if (j3 <= SieveCacheKt.NodeLinkMask && j3 >= SieveCacheKt.NodeMetaAndPreviousMask) {
            return (int) j3;
        }
        throw new NumberFormatException("Scale out of range: " + j3 + " while adjusting scale " + i3 + " to exponent " + j2);
    }

    public static BigDecimal parse(String str) {
        return parse(str.toCharArray());
    }

    private static BigDecimal parseBigDecimal(char[] cArr, int i3, int i4, int i5) {
        int i6;
        int i7;
        BigDecimal bigDecimal;
        char[] cArr2 = cArr;
        int i8 = i5;
        int i9 = i3 + i4;
        int i10 = i3;
        int i11 = i10;
        int i12 = -1;
        int i13 = -1;
        int i14 = 0;
        boolean z2 = false;
        boolean z3 = false;
        boolean z4 = false;
        while (i10 < i9) {
            char c3 = cArr2[i10];
            if (c3 != '+') {
                if (c3 != 'E' && c3 != 'e') {
                    if (c3 != '-') {
                        if (c3 != '.') {
                            if (i13 >= 0 && i12 == -1) {
                                i14++;
                            }
                        } else if (i13 < 0) {
                            i13 = i10;
                        } else {
                            throw new NumberFormatException("Multiple decimal points");
                        }
                    } else if (i12 >= 0) {
                        if (z3) {
                            throw new NumberFormatException("Multiple signs in exponent");
                        }
                    } else if (!z2) {
                        i11 = i10 + 1;
                        z2 = true;
                        z4 = true;
                    } else {
                        throw new NumberFormatException("Multiple signs in number");
                    }
                    i10++;
                } else if (i12 < 0) {
                    i12 = i10;
                    i10++;
                } else {
                    throw new NumberFormatException("Multiple exponent markers");
                }
            } else if (i12 >= 0) {
                if (z3) {
                    throw new NumberFormatException("Multiple signs in exponent");
                }
            } else if (!z2) {
                i11 = i10 + 1;
                z2 = true;
                i10++;
            } else {
                throw new NumberFormatException("Multiple signs in number");
            }
            z3 = true;
            i10++;
        }
        if (i12 >= 0) {
            i6 = 1;
            i7 = Integer.parseInt(new String(cArr2, i12 + 1, (i9 - i12) - 1));
            i14 = adjustScale(i14, (long) i7);
            i9 = i12;
        } else {
            i6 = 1;
            i7 = 0;
        }
        if (i13 >= 0) {
            int i15 = (i9 - i13) - i6;
            bigDecimal = toBigDecimalRec(cArr2, i11, i13 - i11, i7, i8).add(toBigDecimalRec(cArr2, i13 + i6, i15, i7 - i15, i8));
        } else {
            bigDecimal = toBigDecimalRec(cArr2, i11, i9 - i11, i7, i8);
        }
        if (i14 != 0) {
            bigDecimal = bigDecimal.setScale(i14);
        }
        return z4 ? bigDecimal.negate() : bigDecimal;
    }

    private static BigDecimal toBigDecimalRec(char[] cArr, int i3, int i4, int i5, int i6) {
        if (i4 <= i6) {
            return i4 == 0 ? BigDecimal.ZERO : new BigDecimal(cArr, i3, i4).movePointRight(i5);
        }
        int i7 = i4 / 2;
        return toBigDecimalRec(cArr, i3, i7, (i5 + i4) - i7, i6).add(toBigDecimalRec(cArr, i3 + i7, i4 - i7, i5, i6));
    }

    public static BigDecimal parse(char[] cArr, int i3, int i4) {
        String str;
        if (i4 >= 500) {
            return parseBigDecimal(cArr, i3, i4, i4 / 10);
        }
        try {
            return new BigDecimal(cArr, i3, i4);
        } catch (ArithmeticException | NumberFormatException e3) {
            String message = e3.getMessage();
            if (message == null) {
                message = "Not a valid number representation";
            }
            if (i4 <= 1000) {
                str = new String(cArr, i3, i4);
            } else {
                StringBuilder sb = new StringBuilder();
                sb.append(new String(Arrays.copyOfRange(cArr, i3, 1000)));
                sb.append("(truncated, full length is ");
                str = a.m(sb, " chars)", cArr.length);
            }
            throw new NumberFormatException(C0118y.f("Value \"", str, "\" can not be represented as `java.math.BigDecimal`, reason: ", message));
        }
    }

    public static BigDecimal parse(char[] cArr) {
        return parse(cArr, 0, cArr.length);
    }
}
