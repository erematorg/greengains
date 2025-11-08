package com.google.common.primitives;

import A.a;
import androidx.browser.trusted.c;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Comparator;
import org.apache.xerces.impl.xs.SchemaSymbols;

@GwtCompatible
@ElementTypesAreNonnullByDefault
public final class UnsignedLongs {
    public static final long MAX_VALUE = -1;

    public enum LexicographicalComparator implements Comparator<long[]> {
        INSTANCE;

        public String toString() {
            return "UnsignedLongs.lexicographicalComparator()";
        }

        public int compare(long[] jArr, long[] jArr2) {
            int min = Math.min(jArr.length, jArr2.length);
            for (int i3 = 0; i3 < min; i3++) {
                long j2 = jArr[i3];
                long j3 = jArr2[i3];
                if (j2 != j3) {
                    return UnsignedLongs.compare(j2, j3);
                }
            }
            return jArr.length - jArr2.length;
        }
    }

    public static final class ParseOverflowDetection {
        static final int[] maxSafeDigits = new int[37];
        static final long[] maxValueDivs = new long[37];
        static final int[] maxValueMods = new int[37];

        static {
            BigInteger bigInteger = new BigInteger("10000000000000000", 16);
            for (int i3 = 2; i3 <= 36; i3++) {
                long j2 = (long) i3;
                maxValueDivs[i3] = UnsignedLongs.divide(-1, j2);
                maxValueMods[i3] = (int) UnsignedLongs.remainder(-1, j2);
                maxSafeDigits[i3] = bigInteger.toString(i3).length() - 1;
            }
        }

        private ParseOverflowDetection() {
        }

        public static boolean overflowInParse(long j2, int i3, int i4) {
            if (j2 < 0) {
                return true;
            }
            long j3 = maxValueDivs[i4];
            if (j2 < j3) {
                return false;
            }
            return j2 > j3 || i3 > maxValueMods[i4];
        }
    }

    private UnsignedLongs() {
    }

    public static int compare(long j2, long j3) {
        return Longs.compare(flip(j2), flip(j3));
    }

    @CanIgnoreReturnValue
    public static long decode(String str) {
        ParseRequest fromString = ParseRequest.fromString(str);
        try {
            return parseUnsignedLong(fromString.rawValue, fromString.radix);
        } catch (NumberFormatException e3) {
            NumberFormatException numberFormatException = new NumberFormatException(c.a("Error parsing value: ", str));
            numberFormatException.initCause(e3);
            throw numberFormatException;
        }
    }

    public static long divide(long j2, long j3) {
        if (j3 < 0) {
            return compare(j2, j3) < 0 ? 0 : 1;
        }
        if (j2 >= 0) {
            return j2 / j3;
        }
        int i3 = 1;
        long j4 = ((j2 >>> 1) / j3) << 1;
        if (compare(j2 - (j4 * j3), j3) < 0) {
            i3 = 0;
        }
        return j4 + ((long) i3);
    }

    private static long flip(long j2) {
        return j2 ^ Long.MIN_VALUE;
    }

    public static String join(String str, long... jArr) {
        Preconditions.checkNotNull(str);
        if (jArr.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder(jArr.length * 5);
        sb.append(toString(jArr[0]));
        for (int i3 = 1; i3 < jArr.length; i3++) {
            sb.append(str);
            sb.append(toString(jArr[i3]));
        }
        return sb.toString();
    }

    public static Comparator<long[]> lexicographicalComparator() {
        return LexicographicalComparator.INSTANCE;
    }

    public static long max(long... jArr) {
        Preconditions.checkArgument(jArr.length > 0);
        long flip = flip(jArr[0]);
        for (int i3 = 1; i3 < jArr.length; i3++) {
            long flip2 = flip(jArr[i3]);
            if (flip2 > flip) {
                flip = flip2;
            }
        }
        return flip(flip);
    }

    public static long min(long... jArr) {
        Preconditions.checkArgument(jArr.length > 0);
        long flip = flip(jArr[0]);
        for (int i3 = 1; i3 < jArr.length; i3++) {
            long flip2 = flip(jArr[i3]);
            if (flip2 < flip) {
                flip = flip2;
            }
        }
        return flip(flip);
    }

    @CanIgnoreReturnValue
    public static long parseUnsignedLong(String str) {
        return parseUnsignedLong(str, 10);
    }

    public static long remainder(long j2, long j3) {
        if (j3 < 0) {
            return compare(j2, j3) < 0 ? j2 : j2 - j3;
        }
        if (j2 >= 0) {
            return j2 % j3;
        }
        long j4 = j2 - ((((j2 >>> 1) / j3) << 1) * j3);
        if (compare(j4, j3) < 0) {
            j3 = 0;
        }
        return j4 - j3;
    }

    public static void sort(long[] jArr) {
        Preconditions.checkNotNull(jArr);
        sort(jArr, 0, jArr.length);
    }

    public static void sortDescending(long[] jArr) {
        Preconditions.checkNotNull(jArr);
        sortDescending(jArr, 0, jArr.length);
    }

    public static String toString(long j2) {
        return toString(j2, 10);
    }

    @CanIgnoreReturnValue
    public static long parseUnsignedLong(String str, int i3) {
        Preconditions.checkNotNull(str);
        if (str.length() == 0) {
            throw new NumberFormatException("empty string");
        } else if (i3 < 2 || i3 > 36) {
            throw new NumberFormatException(a.k("illegal radix: ", i3));
        } else {
            int i4 = ParseOverflowDetection.maxSafeDigits[i3] - 1;
            long j2 = 0;
            int i5 = 0;
            while (i5 < str.length()) {
                int digit = Character.digit(str.charAt(i5), i3);
                if (digit == -1) {
                    throw new NumberFormatException(str);
                } else if (i5 <= i4 || !ParseOverflowDetection.overflowInParse(j2, digit, i3)) {
                    j2 = (j2 * ((long) i3)) + ((long) digit);
                    i5++;
                } else {
                    throw new NumberFormatException("Too large for unsigned long: ".concat(str));
                }
            }
            return j2;
        }
    }

    public static String toString(long j2, int i3) {
        long j3;
        Preconditions.checkArgument(i3 >= 2 && i3 <= 36, "radix (%s) must be between Character.MIN_RADIX and Character.MAX_RADIX", i3);
        int i4 = (j2 > 0 ? 1 : (j2 == 0 ? 0 : -1));
        if (i4 == 0) {
            return SchemaSymbols.ATTVAL_FALSE_0;
        }
        if (i4 > 0) {
            return Long.toString(j2, i3);
        }
        int i5 = 64;
        char[] cArr = new char[64];
        int i6 = i3 - 1;
        if ((i3 & i6) == 0) {
            int numberOfTrailingZeros = Integer.numberOfTrailingZeros(i3);
            do {
                i5--;
                cArr[i5] = Character.forDigit(((int) j2) & i6, i3);
                j2 >>>= numberOfTrailingZeros;
            } while (j2 != 0);
        } else {
            if ((i3 & 1) == 0) {
                j3 = (j2 >>> 1) / ((long) (i3 >>> 1));
            } else {
                j3 = divide(j2, (long) i3);
            }
            long j4 = (long) i3;
            int i7 = 63;
            cArr[63] = Character.forDigit((int) (j2 - (j3 * j4)), i3);
            while (j3 > 0) {
                i7--;
                cArr[i7] = Character.forDigit((int) (j3 % j4), i3);
                j3 /= j4;
            }
            i5 = i7;
        }
        return new String(cArr, i5, 64 - i5);
    }

    public static void sort(long[] jArr, int i3, int i4) {
        Preconditions.checkNotNull(jArr);
        Preconditions.checkPositionIndexes(i3, i4, jArr.length);
        for (int i5 = i3; i5 < i4; i5++) {
            jArr[i5] = flip(jArr[i5]);
        }
        Arrays.sort(jArr, i3, i4);
        while (i3 < i4) {
            jArr[i3] = flip(jArr[i3]);
            i3++;
        }
    }

    public static void sortDescending(long[] jArr, int i3, int i4) {
        Preconditions.checkNotNull(jArr);
        Preconditions.checkPositionIndexes(i3, i4, jArr.length);
        for (int i5 = i3; i5 < i4; i5++) {
            jArr[i5] = Long.MAX_VALUE ^ jArr[i5];
        }
        Arrays.sort(jArr, i3, i4);
        while (i3 < i4) {
            jArr[i3] = jArr[i3] ^ Long.MAX_VALUE;
            i3++;
        }
    }
}
