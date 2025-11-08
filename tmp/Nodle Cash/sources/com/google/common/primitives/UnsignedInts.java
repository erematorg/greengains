package com.google.common.primitives;

import androidx.browser.trusted.c;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Arrays;
import java.util.Comparator;

@GwtCompatible
@ElementTypesAreNonnullByDefault
public final class UnsignedInts {
    static final long INT_MASK = 4294967295L;

    public enum LexicographicalComparator implements Comparator<int[]> {
        INSTANCE;

        public String toString() {
            return "UnsignedInts.lexicographicalComparator()";
        }

        public int compare(int[] iArr, int[] iArr2) {
            int min = Math.min(iArr.length, iArr2.length);
            for (int i3 = 0; i3 < min; i3++) {
                int i4 = iArr[i3];
                int i5 = iArr2[i3];
                if (i4 != i5) {
                    return UnsignedInts.compare(i4, i5);
                }
            }
            return iArr.length - iArr2.length;
        }
    }

    private UnsignedInts() {
    }

    public static int checkedCast(long j2) {
        Preconditions.checkArgument((j2 >> 32) == 0, "out of range: %s", j2);
        return (int) j2;
    }

    public static int compare(int i3, int i4) {
        return Ints.compare(flip(i3), flip(i4));
    }

    @CanIgnoreReturnValue
    public static int decode(String str) {
        ParseRequest fromString = ParseRequest.fromString(str);
        try {
            return parseUnsignedInt(fromString.rawValue, fromString.radix);
        } catch (NumberFormatException e3) {
            NumberFormatException numberFormatException = new NumberFormatException(c.a("Error parsing value: ", str));
            numberFormatException.initCause(e3);
            throw numberFormatException;
        }
    }

    public static int divide(int i3, int i4) {
        return (int) (toLong(i3) / toLong(i4));
    }

    public static int flip(int i3) {
        return i3 ^ Integer.MIN_VALUE;
    }

    public static String join(String str, int... iArr) {
        Preconditions.checkNotNull(str);
        if (iArr.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder(iArr.length * 5);
        sb.append(toString(iArr[0]));
        for (int i3 = 1; i3 < iArr.length; i3++) {
            sb.append(str);
            sb.append(toString(iArr[i3]));
        }
        return sb.toString();
    }

    public static Comparator<int[]> lexicographicalComparator() {
        return LexicographicalComparator.INSTANCE;
    }

    public static int max(int... iArr) {
        Preconditions.checkArgument(iArr.length > 0);
        int flip = flip(iArr[0]);
        for (int i3 = 1; i3 < iArr.length; i3++) {
            int flip2 = flip(iArr[i3]);
            if (flip2 > flip) {
                flip = flip2;
            }
        }
        return flip(flip);
    }

    public static int min(int... iArr) {
        Preconditions.checkArgument(iArr.length > 0);
        int flip = flip(iArr[0]);
        for (int i3 = 1; i3 < iArr.length; i3++) {
            int flip2 = flip(iArr[i3]);
            if (flip2 < flip) {
                flip = flip2;
            }
        }
        return flip(flip);
    }

    @CanIgnoreReturnValue
    public static int parseUnsignedInt(String str) {
        return parseUnsignedInt(str, 10);
    }

    public static int remainder(int i3, int i4) {
        return (int) (toLong(i3) % toLong(i4));
    }

    public static int saturatedCast(long j2) {
        if (j2 <= 0) {
            return 0;
        }
        if (j2 >= 4294967296L) {
            return -1;
        }
        return (int) j2;
    }

    public static void sort(int[] iArr) {
        Preconditions.checkNotNull(iArr);
        sort(iArr, 0, iArr.length);
    }

    public static void sortDescending(int[] iArr) {
        Preconditions.checkNotNull(iArr);
        sortDescending(iArr, 0, iArr.length);
    }

    public static long toLong(int i3) {
        return ((long) i3) & 4294967295L;
    }

    public static String toString(int i3) {
        return toString(i3, 10);
    }

    @CanIgnoreReturnValue
    public static int parseUnsignedInt(String str, int i3) {
        Preconditions.checkNotNull(str);
        long parseLong = Long.parseLong(str, i3);
        if ((4294967295L & parseLong) == parseLong) {
            return (int) parseLong;
        }
        throw new NumberFormatException("Input " + str + " in base " + i3 + " is not in the range of an unsigned integer");
    }

    public static String toString(int i3, int i4) {
        return Long.toString(((long) i3) & 4294967295L, i4);
    }

    public static void sort(int[] iArr, int i3, int i4) {
        Preconditions.checkNotNull(iArr);
        Preconditions.checkPositionIndexes(i3, i4, iArr.length);
        for (int i5 = i3; i5 < i4; i5++) {
            iArr[i5] = flip(iArr[i5]);
        }
        Arrays.sort(iArr, i3, i4);
        while (i3 < i4) {
            iArr[i3] = flip(iArr[i3]);
            i3++;
        }
    }

    public static void sortDescending(int[] iArr, int i3, int i4) {
        Preconditions.checkNotNull(iArr);
        Preconditions.checkPositionIndexes(i3, i4, iArr.length);
        for (int i5 = i3; i5 < i4; i5++) {
            iArr[i5] = Integer.MAX_VALUE ^ iArr[i5];
        }
        Arrays.sort(iArr, i3, i4);
        while (i3 < i4) {
            iArr[i3] = iArr[i3] ^ Integer.MAX_VALUE;
            i3++;
        }
    }
}
