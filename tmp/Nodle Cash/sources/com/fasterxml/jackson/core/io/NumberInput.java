package com.fasterxml.jackson.core.io;

import com.fasterxml.jackson.core.io.doubleparser.FastDoubleParser;
import com.fasterxml.jackson.core.io.doubleparser.FastFloatParser;
import java.math.BigDecimal;
import java.math.BigInteger;

public final class NumberInput {
    private static final int LARGE_INT_SIZE = 1250;
    static final long L_BILLION = 1000000000;
    static final String MAX_LONG_STR = String.valueOf(Long.MAX_VALUE);
    static final String MIN_LONG_STR_NO_SIGN = String.valueOf(Long.MIN_VALUE).substring(1);
    @Deprecated
    public static final String NASTY_SMALL_DOUBLE = "2.2250738585072012e-308";

    public static boolean inLongRange(char[] cArr, int i3, int i4, boolean z2) {
        String str = z2 ? MIN_LONG_STR_NO_SIGN : MAX_LONG_STR;
        int length = str.length();
        if (i4 < length) {
            return true;
        }
        if (i4 > length) {
            return false;
        }
        int i5 = 0;
        while (i5 < length) {
            int charAt = cArr[i3 + i5] - str.charAt(i5);
            if (charAt == 0) {
                i5++;
            } else if (charAt < 0) {
                return true;
            } else {
                return false;
            }
        }
        return true;
    }

    public static double parseAsDouble(String str, double d2) {
        return parseAsDouble(str, d2, false);
    }

    public static int parseAsInt(String str, int i3) {
        String trim;
        int length;
        if (str == null || (length = trim.length()) == 0) {
            return i3;
        }
        int i4 = 0;
        char charAt = (trim = str.trim()).charAt(0);
        if (charAt == '+') {
            trim = trim.substring(1);
            length = trim.length();
        } else if (charAt == '-') {
            i4 = 1;
        }
        while (i4 < length) {
            char charAt2 = trim.charAt(i4);
            if (charAt2 > '9' || charAt2 < '0') {
                try {
                    return (int) parseDouble(trim, true);
                } catch (NumberFormatException unused) {
                    return i3;
                }
            } else {
                i4++;
            }
        }
        try {
            return Integer.parseInt(trim);
        } catch (NumberFormatException unused2) {
            return i3;
        }
    }

    public static long parseAsLong(String str, long j2) {
        String trim;
        int length;
        if (str == null || (length = trim.length()) == 0) {
            return j2;
        }
        int i3 = 0;
        char charAt = (trim = str.trim()).charAt(0);
        if (charAt == '+') {
            trim = trim.substring(1);
            length = trim.length();
        } else if (charAt == '-') {
            i3 = 1;
        }
        while (i3 < length) {
            char charAt2 = trim.charAt(i3);
            if (charAt2 > '9' || charAt2 < '0') {
                try {
                    return (long) parseDouble(trim, true);
                } catch (NumberFormatException unused) {
                    return j2;
                }
            } else {
                i3++;
            }
        }
        try {
            return Long.parseLong(trim);
        } catch (NumberFormatException unused2) {
            return j2;
        }
    }

    public static BigDecimal parseBigDecimal(String str) throws NumberFormatException {
        return BigDecimalParser.parse(str);
    }

    public static BigInteger parseBigInteger(String str) throws NumberFormatException {
        return str.length() > LARGE_INT_SIZE ? BigDecimalParser.parse(str).toBigInteger() : new BigInteger(str);
    }

    public static double parseDouble(String str) throws NumberFormatException {
        return parseDouble(str, false);
    }

    public static float parseFloat(String str) throws NumberFormatException {
        return parseFloat(str, false);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0049, code lost:
        r0 = androidx.compose.ui.autofill.a.c(r3[r4], 48, 10000, r0);
        r4 = r4 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0054, code lost:
        r0 = androidx.compose.ui.autofill.a.c(r3[r4], 48, 1000, r0);
        r4 = r4 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x005f, code lost:
        r0 = androidx.compose.ui.autofill.a.c(r3[r4], 48, 100, r0);
        r4 = r4 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:?, code lost:
        return androidx.compose.ui.autofill.a.c(r3[r4], 48, 10, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0025, code lost:
        r0 = androidx.compose.ui.autofill.a.c(r3[r4], 48, 10000000, r0);
        r4 = r4 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0031, code lost:
        r0 = androidx.compose.ui.autofill.a.c(r3[r4], 48, 1000000, r0);
        r4 = r4 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x003d, code lost:
        r0 = androidx.compose.ui.autofill.a.c(r3[r4], 48, 100000, r0);
        r4 = r4 + 1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int parseInt(char[] r3, int r4, int r5) {
        /*
            if (r5 <= 0) goto L_0x000c
            char r0 = r3[r4]
            r1 = 43
            if (r0 != r1) goto L_0x000c
            int r4 = r4 + 1
            int r5 = r5 + -1
        L_0x000c:
            int r0 = r4 + r5
            int r0 = r0 + -1
            char r0 = r3[r0]
            r1 = 48
            int r0 = r0 - r1
            switch(r5) {
                case 2: goto L_0x006a;
                case 3: goto L_0x005f;
                case 4: goto L_0x0054;
                case 5: goto L_0x0049;
                case 6: goto L_0x003d;
                case 7: goto L_0x0031;
                case 8: goto L_0x0025;
                case 9: goto L_0x0019;
                default: goto L_0x0018;
            }
        L_0x0018:
            goto L_0x0072
        L_0x0019:
            int r5 = r4 + 1
            char r4 = r3[r4]
            r2 = 100000000(0x5f5e100, float:2.3122341E-35)
            int r0 = androidx.compose.ui.autofill.a.c(r4, r1, r2, r0)
            r4 = r5
        L_0x0025:
            int r5 = r4 + 1
            char r4 = r3[r4]
            r2 = 10000000(0x989680, float:1.4012985E-38)
            int r0 = androidx.compose.ui.autofill.a.c(r4, r1, r2, r0)
            r4 = r5
        L_0x0031:
            int r5 = r4 + 1
            char r4 = r3[r4]
            r2 = 1000000(0xf4240, float:1.401298E-39)
            int r0 = androidx.compose.ui.autofill.a.c(r4, r1, r2, r0)
            r4 = r5
        L_0x003d:
            int r5 = r4 + 1
            char r4 = r3[r4]
            r2 = 100000(0x186a0, float:1.4013E-40)
            int r0 = androidx.compose.ui.autofill.a.c(r4, r1, r2, r0)
            r4 = r5
        L_0x0049:
            int r5 = r4 + 1
            char r4 = r3[r4]
            r2 = 10000(0x2710, float:1.4013E-41)
            int r0 = androidx.compose.ui.autofill.a.c(r4, r1, r2, r0)
            r4 = r5
        L_0x0054:
            int r5 = r4 + 1
            char r4 = r3[r4]
            r2 = 1000(0x3e8, float:1.401E-42)
            int r0 = androidx.compose.ui.autofill.a.c(r4, r1, r2, r0)
            r4 = r5
        L_0x005f:
            int r5 = r4 + 1
            char r4 = r3[r4]
            r2 = 100
            int r0 = androidx.compose.ui.autofill.a.c(r4, r1, r2, r0)
            r4 = r5
        L_0x006a:
            char r3 = r3[r4]
            r4 = 10
            int r0 = androidx.compose.ui.autofill.a.c(r3, r1, r4, r0)
        L_0x0072:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.io.NumberInput.parseInt(char[], int, int):int");
    }

    public static long parseLong(char[] cArr, int i3, int i4) {
        int i5 = i4 - 9;
        return (((long) parseInt(cArr, i3, i5)) * 1000000000) + ((long) parseInt(cArr, i3 + i5, 9));
    }

    public static double parseAsDouble(String str, double d2, boolean z2) {
        if (str == null) {
            return d2;
        }
        String trim = str.trim();
        if (trim.length() == 0) {
            return d2;
        }
        try {
            return parseDouble(trim, z2);
        } catch (NumberFormatException unused) {
            return d2;
        }
    }

    public static BigDecimal parseBigDecimal(char[] cArr, int i3, int i4) throws NumberFormatException {
        return BigDecimalParser.parse(cArr, i3, i4);
    }

    public static double parseDouble(String str, boolean z2) throws NumberFormatException {
        return z2 ? FastDoubleParser.parseDouble((CharSequence) str) : Double.parseDouble(str);
    }

    public static float parseFloat(String str, boolean z2) throws NumberFormatException {
        return z2 ? FastFloatParser.parseFloat((CharSequence) str) : Float.parseFloat(str);
    }

    public static BigDecimal parseBigDecimal(char[] cArr) throws NumberFormatException {
        return BigDecimalParser.parse(cArr);
    }

    public static long parseLong(String str) {
        if (str.length() <= 9) {
            return (long) parseInt(str);
        }
        return Long.parseLong(str);
    }

    public static boolean inLongRange(String str, boolean z2) {
        String str2 = z2 ? MIN_LONG_STR_NO_SIGN : MAX_LONG_STR;
        int length = str2.length();
        int length2 = str.length();
        if (length2 < length) {
            return true;
        }
        if (length2 > length) {
            return false;
        }
        int i3 = 0;
        while (i3 < length) {
            int charAt = str.charAt(i3) - str2.charAt(i3);
            if (charAt == 0) {
                i3++;
            } else if (charAt < 0) {
                return true;
            } else {
                return false;
            }
        }
        return true;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:37:0x0072, code lost:
        return java.lang.Integer.parseInt(r10);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int parseInt(java.lang.String r10) {
        /*
            r0 = 0
            char r1 = r10.charAt(r0)
            int r2 = r10.length()
            r3 = 45
            r4 = 1
            if (r1 != r3) goto L_0x000f
            r0 = r4
        L_0x000f:
            r3 = 2
            r5 = 10
            if (r0 == 0) goto L_0x0024
            if (r2 == r4) goto L_0x001f
            if (r2 <= r5) goto L_0x0019
            goto L_0x001f
        L_0x0019:
            char r1 = r10.charAt(r4)
            r4 = r3
            goto L_0x002d
        L_0x001f:
            int r10 = java.lang.Integer.parseInt(r10)
            return r10
        L_0x0024:
            r6 = 9
            if (r2 <= r6) goto L_0x002d
            int r10 = java.lang.Integer.parseInt(r10)
            return r10
        L_0x002d:
            r6 = 57
            if (r1 > r6) goto L_0x0081
            r7 = 48
            if (r1 >= r7) goto L_0x0036
            goto L_0x0081
        L_0x0036:
            int r1 = r1 - r7
            if (r4 >= r2) goto L_0x007d
            int r8 = r4 + 1
            char r9 = r10.charAt(r4)
            if (r9 > r6) goto L_0x0078
            if (r9 >= r7) goto L_0x0044
            goto L_0x0078
        L_0x0044:
            int r1 = r1 * 10
            int r9 = r9 - r7
            int r1 = r1 + r9
            if (r8 >= r2) goto L_0x007d
            int r4 = r4 + r3
            char r3 = r10.charAt(r8)
            if (r3 > r6) goto L_0x0073
            if (r3 >= r7) goto L_0x0054
            goto L_0x0073
        L_0x0054:
            int r1 = r1 * 10
            int r3 = r3 - r7
            int r1 = r1 + r3
            if (r4 >= r2) goto L_0x007d
        L_0x005a:
            int r3 = r4 + 1
            char r4 = r10.charAt(r4)
            if (r4 > r6) goto L_0x006e
            if (r4 >= r7) goto L_0x0065
            goto L_0x006e
        L_0x0065:
            int r1 = r1 * r5
            int r4 = r4 + -48
            int r1 = r1 + r4
            if (r3 < r2) goto L_0x006c
            goto L_0x007d
        L_0x006c:
            r4 = r3
            goto L_0x005a
        L_0x006e:
            int r10 = java.lang.Integer.parseInt(r10)
            return r10
        L_0x0073:
            int r10 = java.lang.Integer.parseInt(r10)
            return r10
        L_0x0078:
            int r10 = java.lang.Integer.parseInt(r10)
            return r10
        L_0x007d:
            if (r0 == 0) goto L_0x0080
            int r1 = -r1
        L_0x0080:
            return r1
        L_0x0081:
            int r10 = java.lang.Integer.parseInt(r10)
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.io.NumberInput.parseInt(java.lang.String):int");
    }
}
