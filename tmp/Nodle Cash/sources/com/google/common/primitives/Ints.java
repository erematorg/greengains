package com.google.common.primitives;

import androidx.collection.SieveCacheKt;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Ascii;
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

@GwtCompatible(emulated = true)
@ElementTypesAreNonnullByDefault
public final class Ints extends IntsMethodsForWeb {
    public static final int BYTES = 4;
    public static final int MAX_POWER_OF_TWO = 1073741824;

    @GwtCompatible
    public static class IntArrayAsList extends AbstractList<Integer> implements RandomAccess, Serializable {
        private static final long serialVersionUID = 0;
        final int[] array;
        final int end;
        final int start;

        public IntArrayAsList(int[] iArr) {
            this(iArr, 0, iArr.length);
        }

        public boolean contains(@CheckForNull Object obj) {
            return (obj instanceof Integer) && Ints.indexOf(this.array, ((Integer) obj).intValue(), this.start, this.end) != -1;
        }

        public boolean equals(@CheckForNull Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof IntArrayAsList)) {
                return super.equals(obj);
            }
            IntArrayAsList intArrayAsList = (IntArrayAsList) obj;
            int size = size();
            if (intArrayAsList.size() != size) {
                return false;
            }
            for (int i3 = 0; i3 < size; i3++) {
                if (this.array[this.start + i3] != intArrayAsList.array[intArrayAsList.start + i3]) {
                    return false;
                }
            }
            return true;
        }

        public int hashCode() {
            int i3 = 1;
            for (int i4 = this.start; i4 < this.end; i4++) {
                i3 = (i3 * 31) + Ints.hashCode(this.array[i4]);
            }
            return i3;
        }

        public int indexOf(@CheckForNull Object obj) {
            int access$000;
            if (!(obj instanceof Integer) || (access$000 = Ints.indexOf(this.array, ((Integer) obj).intValue(), this.start, this.end)) < 0) {
                return -1;
            }
            return access$000 - this.start;
        }

        public boolean isEmpty() {
            return false;
        }

        public int lastIndexOf(@CheckForNull Object obj) {
            int access$100;
            if (!(obj instanceof Integer) || (access$100 = Ints.lastIndexOf(this.array, ((Integer) obj).intValue(), this.start, this.end)) < 0) {
                return -1;
            }
            return access$100 - this.start;
        }

        public int size() {
            return this.end - this.start;
        }

        public List<Integer> subList(int i3, int i4) {
            Preconditions.checkPositionIndexes(i3, i4, size());
            if (i3 == i4) {
                return Collections.emptyList();
            }
            int[] iArr = this.array;
            int i5 = this.start;
            return new IntArrayAsList(iArr, i3 + i5, i5 + i4);
        }

        public int[] toIntArray() {
            return Arrays.copyOfRange(this.array, this.start, this.end);
        }

        public String toString() {
            StringBuilder sb = new StringBuilder(size() * 5);
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

        public IntArrayAsList(int[] iArr, int i3, int i4) {
            this.array = iArr;
            this.start = i3;
            this.end = i4;
        }

        public Integer get(int i3) {
            Preconditions.checkElementIndex(i3, size());
            return Integer.valueOf(this.array[this.start + i3]);
        }

        public Integer set(int i3, Integer num) {
            Preconditions.checkElementIndex(i3, size());
            int[] iArr = this.array;
            int i4 = this.start;
            int i5 = iArr[i4 + i3];
            iArr[i4 + i3] = ((Integer) Preconditions.checkNotNull(num)).intValue();
            return Integer.valueOf(i5);
        }
    }

    public static final class IntConverter extends Converter<String, Integer> implements Serializable {
        static final IntConverter INSTANCE = new IntConverter();
        private static final long serialVersionUID = 1;

        private IntConverter() {
        }

        private Object readResolve() {
            return INSTANCE;
        }

        public String toString() {
            return "Ints.stringConverter()";
        }

        public String doBackward(Integer num) {
            return num.toString();
        }

        public Integer doForward(String str) {
            return Integer.decode(str);
        }
    }

    public enum LexicographicalComparator implements Comparator<int[]> {
        INSTANCE;

        public String toString() {
            return "Ints.lexicographicalComparator()";
        }

        public int compare(int[] iArr, int[] iArr2) {
            int min = Math.min(iArr.length, iArr2.length);
            for (int i3 = 0; i3 < min; i3++) {
                int compare = Ints.compare(iArr[i3], iArr2[i3]);
                if (compare != 0) {
                    return compare;
                }
            }
            return iArr.length - iArr2.length;
        }
    }

    private Ints() {
    }

    public static List<Integer> asList(int... iArr) {
        return iArr.length == 0 ? Collections.emptyList() : new IntArrayAsList(iArr);
    }

    public static int checkedCast(long j2) {
        int i3 = (int) j2;
        Preconditions.checkArgument(((long) i3) == j2, "Out of range: %s", j2);
        return i3;
    }

    public static int compare(int i3, int i4) {
        if (i3 < i4) {
            return -1;
        }
        return i3 > i4 ? 1 : 0;
    }

    public static int[] concat(int[]... iArr) {
        int i3 = 0;
        for (int[] length : iArr) {
            i3 += length.length;
        }
        int[] iArr2 = new int[i3];
        int i4 = 0;
        for (int[] iArr3 : iArr) {
            System.arraycopy(iArr3, 0, iArr2, i4, iArr3.length);
            i4 += iArr3.length;
        }
        return iArr2;
    }

    public static int constrainToRange(int i3, int i4, int i5) {
        Preconditions.checkArgument(i4 <= i5, "min (%s) must be less than or equal to max (%s)", i4, i5);
        return Math.min(Math.max(i3, i4), i5);
    }

    public static boolean contains(int[] iArr, int i3) {
        for (int i4 : iArr) {
            if (i4 == i3) {
                return true;
            }
        }
        return false;
    }

    public static int[] ensureCapacity(int[] iArr, int i3, int i4) {
        boolean z2 = false;
        Preconditions.checkArgument(i3 >= 0, "Invalid minLength: %s", i3);
        if (i4 >= 0) {
            z2 = true;
        }
        Preconditions.checkArgument(z2, "Invalid padding: %s", i4);
        return iArr.length < i3 ? Arrays.copyOf(iArr, i3 + i4) : iArr;
    }

    public static int fromByteArray(byte[] bArr) {
        Preconditions.checkArgument(bArr.length >= 4, "array too small: %s < %s", bArr.length, 4);
        return fromBytes(bArr[0], bArr[1], bArr[2], bArr[3]);
    }

    public static int fromBytes(byte b3, byte b4, byte b5, byte b6) {
        return (b3 << Ascii.CAN) | ((b4 & 255) << 16) | ((b5 & 255) << 8) | (b6 & 255);
    }

    public static int hashCode(int i3) {
        return i3;
    }

    public static int indexOf(int[] iArr, int i3) {
        return indexOf(iArr, i3, 0, iArr.length);
    }

    public static String join(String str, int... iArr) {
        Preconditions.checkNotNull(str);
        if (iArr.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder(iArr.length * 5);
        sb.append(iArr[0]);
        for (int i3 = 1; i3 < iArr.length; i3++) {
            sb.append(str);
            sb.append(iArr[i3]);
        }
        return sb.toString();
    }

    public static int lastIndexOf(int[] iArr, int i3) {
        return lastIndexOf(iArr, i3, 0, iArr.length);
    }

    public static Comparator<int[]> lexicographicalComparator() {
        return LexicographicalComparator.INSTANCE;
    }

    @GwtIncompatible("Available in GWT! Annotation is to avoid conflict with GWT specialization of base class.")
    public static int max(int... iArr) {
        Preconditions.checkArgument(iArr.length > 0);
        int i3 = iArr[0];
        for (int i4 = 1; i4 < iArr.length; i4++) {
            int i5 = iArr[i4];
            if (i5 > i3) {
                i3 = i5;
            }
        }
        return i3;
    }

    @GwtIncompatible("Available in GWT! Annotation is to avoid conflict with GWT specialization of base class.")
    public static int min(int... iArr) {
        Preconditions.checkArgument(iArr.length > 0);
        int i3 = iArr[0];
        for (int i4 = 1; i4 < iArr.length; i4++) {
            int i5 = iArr[i4];
            if (i5 < i3) {
                i3 = i5;
            }
        }
        return i3;
    }

    public static void reverse(int[] iArr) {
        Preconditions.checkNotNull(iArr);
        reverse(iArr, 0, iArr.length);
    }

    public static void rotate(int[] iArr, int i3) {
        rotate(iArr, i3, 0, iArr.length);
    }

    public static int saturatedCast(long j2) {
        if (j2 > SieveCacheKt.NodeLinkMask) {
            return Integer.MAX_VALUE;
        }
        if (j2 < SieveCacheKt.NodeMetaAndPreviousMask) {
            return Integer.MIN_VALUE;
        }
        return (int) j2;
    }

    public static void sortDescending(int[] iArr) {
        Preconditions.checkNotNull(iArr);
        sortDescending(iArr, 0, iArr.length);
    }

    public static Converter<String, Integer> stringConverter() {
        return IntConverter.INSTANCE;
    }

    public static int[] toArray(Collection<? extends Number> collection) {
        if (collection instanceof IntArrayAsList) {
            return ((IntArrayAsList) collection).toIntArray();
        }
        Object[] array = collection.toArray();
        int length = array.length;
        int[] iArr = new int[length];
        for (int i3 = 0; i3 < length; i3++) {
            iArr[i3] = ((Number) Preconditions.checkNotNull(array[i3])).intValue();
        }
        return iArr;
    }

    public static byte[] toByteArray(int i3) {
        return new byte[]{(byte) (i3 >> 24), (byte) (i3 >> 16), (byte) (i3 >> 8), (byte) i3};
    }

    @CheckForNull
    public static Integer tryParse(String str) {
        return tryParse(str, 10);
    }

    /* access modifiers changed from: private */
    public static int indexOf(int[] iArr, int i3, int i4, int i5) {
        while (i4 < i5) {
            if (iArr[i4] == i3) {
                return i4;
            }
            i4++;
        }
        return -1;
    }

    /* access modifiers changed from: private */
    public static int lastIndexOf(int[] iArr, int i3, int i4, int i5) {
        for (int i6 = i5 - 1; i6 >= i4; i6--) {
            if (iArr[i6] == i3) {
                return i6;
            }
        }
        return -1;
    }

    public static void rotate(int[] iArr, int i3, int i4, int i5) {
        Preconditions.checkNotNull(iArr);
        Preconditions.checkPositionIndexes(i4, i5, iArr.length);
        if (iArr.length > 1) {
            int i6 = i5 - i4;
            int i7 = (-i3) % i6;
            if (i7 < 0) {
                i7 += i6;
            }
            int i8 = i7 + i4;
            if (i8 != i4) {
                reverse(iArr, i4, i8);
                reverse(iArr, i8, i5);
                reverse(iArr, i4, i5);
            }
        }
    }

    @CheckForNull
    public static Integer tryParse(String str, int i3) {
        Long tryParse = Longs.tryParse(str, i3);
        if (tryParse == null || tryParse.longValue() != ((long) tryParse.intValue())) {
            return null;
        }
        return Integer.valueOf(tryParse.intValue());
    }

    public static int indexOf(int[] iArr, int[] iArr2) {
        Preconditions.checkNotNull(iArr, "array");
        Preconditions.checkNotNull(iArr2, TypedValues.AttributesType.S_TARGET);
        if (iArr2.length == 0) {
            return 0;
        }
        int i3 = 0;
        while (i3 < (iArr.length - iArr2.length) + 1) {
            int i4 = 0;
            while (i4 < iArr2.length) {
                if (iArr[i3 + i4] != iArr2[i4]) {
                    i3++;
                } else {
                    i4++;
                }
            }
            return i3;
        }
        return -1;
    }

    public static void reverse(int[] iArr, int i3, int i4) {
        Preconditions.checkNotNull(iArr);
        Preconditions.checkPositionIndexes(i3, i4, iArr.length);
        for (int i5 = i4 - 1; i3 < i5; i5--) {
            int i6 = iArr[i3];
            iArr[i3] = iArr[i5];
            iArr[i5] = i6;
            i3++;
        }
    }

    public static void sortDescending(int[] iArr, int i3, int i4) {
        Preconditions.checkNotNull(iArr);
        Preconditions.checkPositionIndexes(i3, i4, iArr.length);
        Arrays.sort(iArr, i3, i4);
        reverse(iArr, i3, i4);
    }
}
