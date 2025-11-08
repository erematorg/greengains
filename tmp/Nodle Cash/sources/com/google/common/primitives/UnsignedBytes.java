package com.google.common.primitives;

import A.a;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.nio.ByteOrder;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;
import sun.misc.Unsafe;

@GwtIncompatible
@ElementTypesAreNonnullByDefault
@J2ktIncompatible
public final class UnsignedBytes {
    public static final byte MAX_POWER_OF_TWO = Byte.MIN_VALUE;
    public static final byte MAX_VALUE = -1;
    private static final int UNSIGNED_MASK = 255;

    @VisibleForTesting
    public static class LexicographicalComparatorHolder {
        static final Comparator<byte[]> BEST_COMPARATOR = getBestComparator();
        static final String UNSAFE_COMPARATOR_NAME = LexicographicalComparatorHolder.class.getName().concat("$UnsafeComparator");

        public enum PureJavaComparator implements Comparator<byte[]> {
            INSTANCE;

            public String toString() {
                return "UnsignedBytes.lexicographicalComparator() (pure Java version)";
            }

            public int compare(byte[] bArr, byte[] bArr2) {
                int min = Math.min(bArr.length, bArr2.length);
                for (int i3 = 0; i3 < min; i3++) {
                    int compare = UnsignedBytes.compare(bArr[i3], bArr2[i3]);
                    if (compare != 0) {
                        return compare;
                    }
                }
                return bArr.length - bArr2.length;
            }
        }

        @VisibleForTesting
        public enum UnsafeComparator implements Comparator<byte[]> {
            INSTANCE;
            
            static final boolean BIG_ENDIAN = false;
            static final int BYTE_ARRAY_BASE_OFFSET = 0;
            static final Unsafe theUnsafe = null;

            static {
                BIG_ENDIAN = ByteOrder.nativeOrder().equals(ByteOrder.BIG_ENDIAN);
                Unsafe unsafe = getUnsafe();
                theUnsafe = unsafe;
                Class<byte[]> cls = byte[].class;
                int arrayBaseOffset = unsafe.arrayBaseOffset(cls);
                BYTE_ARRAY_BASE_OFFSET = arrayBaseOffset;
                if (!"64".equals(System.getProperty("sun.arch.data.model")) || arrayBaseOffset % 8 != 0 || unsafe.arrayIndexScale(cls) != 1) {
                    throw new Error();
                }
            }

            /* JADX WARNING: Code restructure failed: missing block: B:5:0x0010, code lost:
                return (sun.misc.Unsafe) java.security.AccessController.doPrivileged(new com.google.common.primitives.UnsignedBytes.LexicographicalComparatorHolder.UnsafeComparator.AnonymousClass1());
             */
            /* JADX WARNING: Code restructure failed: missing block: B:6:0x0011, code lost:
                r0 = move-exception;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:8:0x001d, code lost:
                throw new java.lang.RuntimeException("Could not initialize intrinsics", r0.getCause());
             */
            /* JADX WARNING: Failed to process nested try/catch */
            /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0005 */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            private static sun.misc.Unsafe getUnsafe() {
                /*
                    sun.misc.Unsafe r0 = sun.misc.Unsafe.getUnsafe()     // Catch:{ SecurityException -> 0x0005 }
                    return r0
                L_0x0005:
                    com.google.common.primitives.UnsignedBytes$LexicographicalComparatorHolder$UnsafeComparator$1 r0 = new com.google.common.primitives.UnsignedBytes$LexicographicalComparatorHolder$UnsafeComparator$1     // Catch:{ PrivilegedActionException -> 0x0011 }
                    r0.<init>()     // Catch:{ PrivilegedActionException -> 0x0011 }
                    java.lang.Object r0 = java.security.AccessController.doPrivileged(r0)     // Catch:{ PrivilegedActionException -> 0x0011 }
                    sun.misc.Unsafe r0 = (sun.misc.Unsafe) r0     // Catch:{ PrivilegedActionException -> 0x0011 }
                    return r0
                L_0x0011:
                    r0 = move-exception
                    java.lang.RuntimeException r1 = new java.lang.RuntimeException
                    java.lang.String r2 = "Could not initialize intrinsics"
                    java.lang.Throwable r0 = r0.getCause()
                    r1.<init>(r2, r0)
                    throw r1
                */
                throw new UnsupportedOperationException("Method not decompiled: com.google.common.primitives.UnsignedBytes.LexicographicalComparatorHolder.UnsafeComparator.getUnsafe():sun.misc.Unsafe");
            }

            public String toString() {
                return "UnsignedBytes.lexicographicalComparator() (sun.misc.Unsafe version)";
            }

            public int compare(byte[] bArr, byte[] bArr2) {
                int min = Math.min(bArr.length, bArr2.length);
                int i3 = min & -8;
                int i4 = 0;
                while (i4 < i3) {
                    Unsafe unsafe = theUnsafe;
                    int i5 = BYTE_ARRAY_BASE_OFFSET;
                    long j2 = (long) i4;
                    long j3 = unsafe.getLong(bArr, ((long) i5) + j2);
                    long j4 = unsafe.getLong(bArr2, ((long) i5) + j2);
                    if (j3 == j4) {
                        i4 += 8;
                    } else if (BIG_ENDIAN) {
                        return UnsignedLongs.compare(j3, j4);
                    } else {
                        int numberOfTrailingZeros = Long.numberOfTrailingZeros(j3 ^ j4) & -8;
                        return ((int) ((j3 >>> numberOfTrailingZeros) & 255)) - ((int) (255 & (j4 >>> numberOfTrailingZeros)));
                    }
                }
                while (i4 < min) {
                    int compare = UnsignedBytes.compare(bArr[i4], bArr2[i4]);
                    if (compare != 0) {
                        return compare;
                    }
                    i4++;
                }
                return bArr.length - bArr2.length;
            }
        }

        public static Comparator<byte[]> getBestComparator() {
            try {
                Object[] enumConstants = Class.forName(UNSAFE_COMPARATOR_NAME).getEnumConstants();
                Objects.requireNonNull(enumConstants);
                return (Comparator) enumConstants[0];
            } catch (Throwable unused) {
                return UnsignedBytes.lexicographicalComparatorJavaImpl();
            }
        }
    }

    private UnsignedBytes() {
    }

    @CanIgnoreReturnValue
    public static byte checkedCast(long j2) {
        Preconditions.checkArgument((j2 >> 8) == 0, "out of range: %s", j2);
        return (byte) ((int) j2);
    }

    public static int compare(byte b3, byte b4) {
        return toInt(b3) - toInt(b4);
    }

    private static byte flip(byte b3) {
        return (byte) (b3 ^ 128);
    }

    public static String join(String str, byte... bArr) {
        Preconditions.checkNotNull(str);
        if (bArr.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder((str.length() + 3) * bArr.length);
        sb.append(toInt(bArr[0]));
        for (int i3 = 1; i3 < bArr.length; i3++) {
            sb.append(str);
            sb.append(toString(bArr[i3]));
        }
        return sb.toString();
    }

    public static Comparator<byte[]> lexicographicalComparator() {
        return LexicographicalComparatorHolder.BEST_COMPARATOR;
    }

    @VisibleForTesting
    public static Comparator<byte[]> lexicographicalComparatorJavaImpl() {
        return LexicographicalComparatorHolder.PureJavaComparator.INSTANCE;
    }

    public static byte max(byte... bArr) {
        Preconditions.checkArgument(bArr.length > 0);
        int i3 = toInt(bArr[0]);
        for (int i4 = 1; i4 < bArr.length; i4++) {
            int i5 = toInt(bArr[i4]);
            if (i5 > i3) {
                i3 = i5;
            }
        }
        return (byte) i3;
    }

    public static byte min(byte... bArr) {
        Preconditions.checkArgument(bArr.length > 0);
        int i3 = toInt(bArr[0]);
        for (int i4 = 1; i4 < bArr.length; i4++) {
            int i5 = toInt(bArr[i4]);
            if (i5 < i3) {
                i3 = i5;
            }
        }
        return (byte) i3;
    }

    @CanIgnoreReturnValue
    public static byte parseUnsignedByte(String str) {
        return parseUnsignedByte(str, 10);
    }

    public static byte saturatedCast(long j2) {
        if (j2 > ((long) toInt((byte) -1))) {
            return -1;
        }
        if (j2 < 0) {
            return 0;
        }
        return (byte) ((int) j2);
    }

    public static void sort(byte[] bArr) {
        Preconditions.checkNotNull(bArr);
        sort(bArr, 0, bArr.length);
    }

    public static void sortDescending(byte[] bArr) {
        Preconditions.checkNotNull(bArr);
        sortDescending(bArr, 0, bArr.length);
    }

    public static int toInt(byte b3) {
        return b3 & 255;
    }

    public static String toString(byte b3) {
        return toString(b3, 10);
    }

    @CanIgnoreReturnValue
    public static byte parseUnsignedByte(String str, int i3) {
        int parseInt = Integer.parseInt((String) Preconditions.checkNotNull(str), i3);
        if ((parseInt >> 8) == 0) {
            return (byte) parseInt;
        }
        throw new NumberFormatException(a.k("out of range: ", parseInt));
    }

    public static String toString(byte b3, int i3) {
        Preconditions.checkArgument(i3 >= 2 && i3 <= 36, "radix (%s) must be between Character.MIN_RADIX and Character.MAX_RADIX", i3);
        return Integer.toString(toInt(b3), i3);
    }

    public static void sort(byte[] bArr, int i3, int i4) {
        Preconditions.checkNotNull(bArr);
        Preconditions.checkPositionIndexes(i3, i4, bArr.length);
        for (int i5 = i3; i5 < i4; i5++) {
            bArr[i5] = flip(bArr[i5]);
        }
        Arrays.sort(bArr, i3, i4);
        while (i3 < i4) {
            bArr[i3] = flip(bArr[i3]);
            i3++;
        }
    }

    public static void sortDescending(byte[] bArr, int i3, int i4) {
        Preconditions.checkNotNull(bArr);
        Preconditions.checkPositionIndexes(i3, i4, bArr.length);
        for (int i5 = i3; i5 < i4; i5++) {
            bArr[i5] = (byte) (bArr[i5] ^ Byte.MAX_VALUE);
        }
        Arrays.sort(bArr, i3, i4);
        while (i3 < i4) {
            bArr[i3] = (byte) (bArr[i3] ^ Byte.MAX_VALUE);
            i3++;
        }
    }
}
