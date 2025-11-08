package com.google.common.primitives;

import A.a;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Converter;
import com.google.common.base.Preconditions;
import java.io.Serializable;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.RandomAccess;
import javax.annotation.CheckForNull;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

@GwtCompatible
@ElementTypesAreNonnullByDefault
public final class Longs {
    public static final int BYTES = 8;
    public static final long MAX_POWER_OF_TWO = 4611686018427387904L;

    public static final class AsciiDigits {
        private static final byte[] asciiDigits;

        static {
            byte[] bArr = new byte[128];
            Arrays.fill(bArr, (byte) -1);
            for (int i3 = 0; i3 < 10; i3++) {
                bArr[i3 + 48] = (byte) i3;
            }
            for (int i4 = 0; i4 < 26; i4++) {
                byte b3 = (byte) (i4 + 10);
                bArr[i4 + 65] = b3;
                bArr[i4 + 97] = b3;
            }
            asciiDigits = bArr;
        }

        private AsciiDigits() {
        }

        public static int digit(char c3) {
            if (c3 < 128) {
                return asciiDigits[c3];
            }
            return -1;
        }
    }

    public enum LexicographicalComparator implements Comparator<long[]> {
        INSTANCE;

        public String toString() {
            return "Longs.lexicographicalComparator()";
        }

        public int compare(long[] jArr, long[] jArr2) {
            int min = Math.min(jArr.length, jArr2.length);
            for (int i3 = 0; i3 < min; i3++) {
                int compare = Longs.compare(jArr[i3], jArr2[i3]);
                if (compare != 0) {
                    return compare;
                }
            }
            return jArr.length - jArr2.length;
        }
    }

    @GwtCompatible
    public static class LongArrayAsList extends AbstractList<Long> implements RandomAccess, Serializable {
        private static final long serialVersionUID = 0;
        final long[] array;
        final int end;
        final int start;

        public LongArrayAsList(long[] jArr) {
            this(jArr, 0, jArr.length);
        }

        public boolean contains(@CheckForNull Object obj) {
            return (obj instanceof Long) && Longs.indexOf(this.array, ((Long) obj).longValue(), this.start, this.end) != -1;
        }

        public boolean equals(@CheckForNull Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof LongArrayAsList)) {
                return super.equals(obj);
            }
            LongArrayAsList longArrayAsList = (LongArrayAsList) obj;
            int size = size();
            if (longArrayAsList.size() != size) {
                return false;
            }
            for (int i3 = 0; i3 < size; i3++) {
                if (this.array[this.start + i3] != longArrayAsList.array[longArrayAsList.start + i3]) {
                    return false;
                }
            }
            return true;
        }

        public int hashCode() {
            int i3 = 1;
            for (int i4 = this.start; i4 < this.end; i4++) {
                i3 = (i3 * 31) + Longs.hashCode(this.array[i4]);
            }
            return i3;
        }

        public int indexOf(@CheckForNull Object obj) {
            int access$000;
            if (!(obj instanceof Long) || (access$000 = Longs.indexOf(this.array, ((Long) obj).longValue(), this.start, this.end)) < 0) {
                return -1;
            }
            return access$000 - this.start;
        }

        public boolean isEmpty() {
            return false;
        }

        public int lastIndexOf(@CheckForNull Object obj) {
            int access$100;
            if (!(obj instanceof Long) || (access$100 = Longs.lastIndexOf(this.array, ((Long) obj).longValue(), this.start, this.end)) < 0) {
                return -1;
            }
            return access$100 - this.start;
        }

        public int size() {
            return this.end - this.start;
        }

        public List<Long> subList(int i3, int i4) {
            Preconditions.checkPositionIndexes(i3, i4, size());
            if (i3 == i4) {
                return Collections.emptyList();
            }
            long[] jArr = this.array;
            int i5 = this.start;
            return new LongArrayAsList(jArr, i3 + i5, i5 + i4);
        }

        public long[] toLongArray() {
            return Arrays.copyOfRange(this.array, this.start, this.end);
        }

        public String toString() {
            StringBuilder sb = new StringBuilder(size() * 10);
            sb.append(AbstractJsonLexerKt.BEGIN_LIST);
            sb.append(this.array[this.start]);
            int i3 = this.start;
            while (true) {
                i3++;
                if (i3 < this.end) {
                    sb.append(", ");
                    sb.append(this.array[i3]);
                } else {
                    sb.append(AbstractJsonLexerKt.END_LIST);
                    return sb.toString();
                }
            }
        }

        public LongArrayAsList(long[] jArr, int i3, int i4) {
            this.array = jArr;
            this.start = i3;
            this.end = i4;
        }

        public Long get(int i3) {
            Preconditions.checkElementIndex(i3, size());
            return Long.valueOf(this.array[this.start + i3]);
        }

        public Long set(int i3, Long l2) {
            Preconditions.checkElementIndex(i3, size());
            long[] jArr = this.array;
            int i4 = this.start;
            long j2 = jArr[i4 + i3];
            jArr[i4 + i3] = ((Long) Preconditions.checkNotNull(l2)).longValue();
            return Long.valueOf(j2);
        }
    }

    public static final class LongConverter extends Converter<String, Long> implements Serializable {
        static final LongConverter INSTANCE = new LongConverter();
        private static final long serialVersionUID = 1;

        private LongConverter() {
        }

        private Object readResolve() {
            return INSTANCE;
        }

        public String toString() {
            return "Longs.stringConverter()";
        }

        public String doBackward(Long l2) {
            return l2.toString();
        }

        public Long doForward(String str) {
            return Long.decode(str);
        }
    }

    private Longs() {
    }

    public static List<Long> asList(long... jArr) {
        return jArr.length == 0 ? Collections.emptyList() : new LongArrayAsList(jArr);
    }

    private static int checkNoOverflow(long j2) {
        int i3 = (int) j2;
        Preconditions.checkArgument(j2 == ((long) i3), "the total number of elements (%s) in the arrays must fit in an int", j2);
        return i3;
    }

    public static int compare(long j2, long j3) {
        int i3 = (j2 > j3 ? 1 : (j2 == j3 ? 0 : -1));
        if (i3 < 0) {
            return -1;
        }
        return i3 > 0 ? 1 : 0;
    }

    public static long[] concat(long[]... jArr) {
        long j2 = 0;
        for (long[] length : jArr) {
            j2 += (long) length.length;
        }
        long[] jArr2 = new long[checkNoOverflow(j2)];
        int i3 = 0;
        for (long[] jArr3 : jArr) {
            System.arraycopy(jArr3, 0, jArr2, i3, jArr3.length);
            i3 += jArr3.length;
        }
        return jArr2;
    }

    public static long constrainToRange(long j2, long j3, long j4) {
        Preconditions.checkArgument(j3 <= j4, "min (%s) must be less than or equal to max (%s)", j3, j4);
        return Math.min(Math.max(j2, j3), j4);
    }

    public static boolean contains(long[] jArr, long j2) {
        for (long j3 : jArr) {
            if (j3 == j2) {
                return true;
            }
        }
        return false;
    }

    public static long[] ensureCapacity(long[] jArr, int i3, int i4) {
        boolean z2 = false;
        Preconditions.checkArgument(i3 >= 0, "Invalid minLength: %s", i3);
        if (i4 >= 0) {
            z2 = true;
        }
        Preconditions.checkArgument(z2, "Invalid padding: %s", i4);
        return jArr.length < i3 ? Arrays.copyOf(jArr, i3 + i4) : jArr;
    }

    public static long fromByteArray(byte[] bArr) {
        Preconditions.checkArgument(bArr.length >= 8, "array too small: %s < %s", bArr.length, 8);
        return fromBytes(bArr[0], bArr[1], bArr[2], bArr[3], bArr[4], bArr[5], bArr[6], bArr[7]);
    }

    public static long fromBytes(byte b3, byte b4, byte b5, byte b6, byte b7, byte b8, byte b9, byte b10) {
        return ((((long) b4) & 255) << 48) | ((((long) b3) & 255) << 56) | ((((long) b5) & 255) << 40) | ((((long) b6) & 255) << 32) | ((((long) b7) & 255) << 24) | ((((long) b8) & 255) << 16) | ((((long) b9) & 255) << 8) | (((long) b10) & 255);
    }

    public static int hashCode(long j2) {
        return (int) (j2 ^ (j2 >>> 32));
    }

    public static int indexOf(long[] jArr, long j2) {
        return indexOf(jArr, j2, 0, jArr.length);
    }

    public static String join(String str, long... jArr) {
        Preconditions.checkNotNull(str);
        if (jArr.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder(jArr.length * 10);
        sb.append(jArr[0]);
        for (int i3 = 1; i3 < jArr.length; i3++) {
            sb.append(str);
            sb.append(jArr[i3]);
        }
        return sb.toString();
    }

    public static int lastIndexOf(long[] jArr, long j2) {
        return lastIndexOf(jArr, j2, 0, jArr.length);
    }

    public static Comparator<long[]> lexicographicalComparator() {
        return LexicographicalComparator.INSTANCE;
    }

    public static long max(long... jArr) {
        Preconditions.checkArgument(jArr.length > 0);
        long j2 = jArr[0];
        for (int i3 = 1; i3 < jArr.length; i3++) {
            long j3 = jArr[i3];
            if (j3 > j2) {
                j2 = j3;
            }
        }
        return j2;
    }

    public static long min(long... jArr) {
        Preconditions.checkArgument(jArr.length > 0);
        long j2 = jArr[0];
        for (int i3 = 1; i3 < jArr.length; i3++) {
            long j3 = jArr[i3];
            if (j3 < j2) {
                j2 = j3;
            }
        }
        return j2;
    }

    public static void reverse(long[] jArr) {
        Preconditions.checkNotNull(jArr);
        reverse(jArr, 0, jArr.length);
    }

    public static void rotate(long[] jArr, int i3) {
        rotate(jArr, i3, 0, jArr.length);
    }

    public static void sortDescending(long[] jArr) {
        Preconditions.checkNotNull(jArr);
        sortDescending(jArr, 0, jArr.length);
    }

    public static Converter<String, Long> stringConverter() {
        return LongConverter.INSTANCE;
    }

    public static long[] toArray(Collection<? extends Number> collection) {
        if (collection instanceof LongArrayAsList) {
            return ((LongArrayAsList) collection).toLongArray();
        }
        Object[] array = collection.toArray();
        int length = array.length;
        long[] jArr = new long[length];
        for (int i3 = 0; i3 < length; i3++) {
            jArr[i3] = ((Number) Preconditions.checkNotNull(array[i3])).longValue();
        }
        return jArr;
    }

    public static byte[] toByteArray(long j2) {
        byte[] bArr = new byte[8];
        for (int i3 = 7; i3 >= 0; i3--) {
            bArr[i3] = (byte) ((int) (255 & j2));
            j2 >>= 8;
        }
        return bArr;
    }

    @CheckForNull
    public static Long tryParse(String str) {
        return tryParse(str, 10);
    }

    /* access modifiers changed from: private */
    public static int indexOf(long[] jArr, long j2, int i3, int i4) {
        while (i3 < i4) {
            if (jArr[i3] == j2) {
                return i3;
            }
            i3++;
        }
        return -1;
    }

    /* access modifiers changed from: private */
    public static int lastIndexOf(long[] jArr, long j2, int i3, int i4) {
        for (int i5 = i4 - 1; i5 >= i3; i5--) {
            if (jArr[i5] == j2) {
                return i5;
            }
        }
        return -1;
    }

    public static void rotate(long[] jArr, int i3, int i4, int i5) {
        Preconditions.checkNotNull(jArr);
        Preconditions.checkPositionIndexes(i4, i5, jArr.length);
        if (jArr.length > 1) {
            int i6 = i5 - i4;
            int i7 = (-i3) % i6;
            if (i7 < 0) {
                i7 += i6;
            }
            int i8 = i7 + i4;
            if (i8 != i4) {
                reverse(jArr, i4, i8);
                reverse(jArr, i8, i5);
                reverse(jArr, i4, i5);
            }
        }
    }

    @CheckForNull
    public static Long tryParse(String str, int i3) {
        String str2 = str;
        int i4 = i3;
        if (((String) Preconditions.checkNotNull(str)).isEmpty()) {
            return null;
        }
        if (i4 < 2 || i4 > 36) {
            throw new IllegalArgumentException(a.k("radix must be between MIN_RADIX and MAX_RADIX but was ", i4));
        }
        int i5 = 0;
        if (str2.charAt(0) == '-') {
            i5 = 1;
        }
        if (i5 == str.length()) {
            return null;
        }
        int i6 = i5 + 1;
        int digit = AsciiDigits.digit(str2.charAt(i5));
        if (digit < 0 || digit >= i4) {
            return null;
        }
        long j2 = (long) (-digit);
        long j3 = (long) i4;
        long j4 = Long.MIN_VALUE / j3;
        while (i6 < str.length()) {
            int i7 = i6 + 1;
            int digit2 = AsciiDigits.digit(str2.charAt(i6));
            if (digit2 < 0 || digit2 >= i4 || j2 < j4) {
                return null;
            }
            long j5 = j2 * j3;
            long j6 = (long) digit2;
            if (j5 < j6 - Long.MIN_VALUE) {
                return null;
            }
            j2 = j5 - j6;
            i6 = i7;
        }
        if (i5 != 0) {
            return Long.valueOf(j2);
        }
        if (j2 == Long.MIN_VALUE) {
            return null;
        }
        return Long.valueOf(-j2);
    }

    public static int indexOf(long[] jArr, long[] jArr2) {
        Preconditions.checkNotNull(jArr, "array");
        Preconditions.checkNotNull(jArr2, TypedValues.AttributesType.S_TARGET);
        if (jArr2.length == 0) {
            return 0;
        }
        int i3 = 0;
        while (i3 < (jArr.length - jArr2.length) + 1) {
            int i4 = 0;
            while (i4 < jArr2.length) {
                if (jArr[i3 + i4] != jArr2[i4]) {
                    i3++;
                } else {
                    i4++;
                }
            }
            return i3;
        }
        return -1;
    }

    public static void reverse(long[] jArr, int i3, int i4) {
        Preconditions.checkNotNull(jArr);
        Preconditions.checkPositionIndexes(i3, i4, jArr.length);
        for (int i5 = i4 - 1; i3 < i5; i5--) {
            long j2 = jArr[i3];
            jArr[i3] = jArr[i5];
            jArr[i5] = j2;
            i3++;
        }
    }

    public static void sortDescending(long[] jArr, int i3, int i4) {
        Preconditions.checkNotNull(jArr);
        Preconditions.checkPositionIndexes(i3, i4, jArr.length);
        Arrays.sort(jArr, i3, i4);
        reverse(jArr, i3, i4);
    }
}
