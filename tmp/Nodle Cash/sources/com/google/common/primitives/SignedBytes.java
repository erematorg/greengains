package com.google.common.primitives;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import java.util.Arrays;
import java.util.Comparator;

@GwtCompatible
@ElementTypesAreNonnullByDefault
public final class SignedBytes {
    public static final byte MAX_POWER_OF_TWO = 64;

    public enum LexicographicalComparator implements Comparator<byte[]> {
        INSTANCE;

        public String toString() {
            return "SignedBytes.lexicographicalComparator()";
        }

        public int compare(byte[] bArr, byte[] bArr2) {
            int min = Math.min(bArr.length, bArr2.length);
            for (int i3 = 0; i3 < min; i3++) {
                int compare = SignedBytes.compare(bArr[i3], bArr2[i3]);
                if (compare != 0) {
                    return compare;
                }
            }
            return bArr.length - bArr2.length;
        }
    }

    private SignedBytes() {
    }

    public static byte checkedCast(long j2) {
        byte b3 = (byte) ((int) j2);
        Preconditions.checkArgument(((long) b3) == j2, "Out of range: %s", j2);
        return b3;
    }

    public static int compare(byte b3, byte b4) {
        return b3 - b4;
    }

    public static String join(String str, byte... bArr) {
        Preconditions.checkNotNull(str);
        if (bArr.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder(bArr.length * 5);
        sb.append(bArr[0]);
        for (int i3 = 1; i3 < bArr.length; i3++) {
            sb.append(str);
            sb.append(bArr[i3]);
        }
        return sb.toString();
    }

    public static Comparator<byte[]> lexicographicalComparator() {
        return LexicographicalComparator.INSTANCE;
    }

    public static byte max(byte... bArr) {
        Preconditions.checkArgument(bArr.length > 0);
        byte b3 = bArr[0];
        for (int i3 = 1; i3 < bArr.length; i3++) {
            byte b4 = bArr[i3];
            if (b4 > b3) {
                b3 = b4;
            }
        }
        return b3;
    }

    public static byte min(byte... bArr) {
        Preconditions.checkArgument(bArr.length > 0);
        byte b3 = bArr[0];
        for (int i3 = 1; i3 < bArr.length; i3++) {
            byte b4 = bArr[i3];
            if (b4 < b3) {
                b3 = b4;
            }
        }
        return b3;
    }

    public static byte saturatedCast(long j2) {
        if (j2 > 127) {
            return Byte.MAX_VALUE;
        }
        if (j2 < -128) {
            return Byte.MIN_VALUE;
        }
        return (byte) ((int) j2);
    }

    public static void sortDescending(byte[] bArr) {
        Preconditions.checkNotNull(bArr);
        sortDescending(bArr, 0, bArr.length);
    }

    public static void sortDescending(byte[] bArr, int i3, int i4) {
        Preconditions.checkNotNull(bArr);
        Preconditions.checkPositionIndexes(i3, i4, bArr.length);
        Arrays.sort(bArr, i3, i4);
        Bytes.reverse(bArr, i3, i4);
    }
}
