package com.google.common.primitives;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.google.common.annotations.GwtCompatible;
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
public final class Booleans {

    @GwtCompatible
    public static class BooleanArrayAsList extends AbstractList<Boolean> implements RandomAccess, Serializable {
        private static final long serialVersionUID = 0;
        final boolean[] array;
        final int end;
        final int start;

        public BooleanArrayAsList(boolean[] zArr) {
            this(zArr, 0, zArr.length);
        }

        public boolean contains(@CheckForNull Object obj) {
            return (obj instanceof Boolean) && Booleans.indexOf(this.array, ((Boolean) obj).booleanValue(), this.start, this.end) != -1;
        }

        public boolean equals(@CheckForNull Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof BooleanArrayAsList)) {
                return super.equals(obj);
            }
            BooleanArrayAsList booleanArrayAsList = (BooleanArrayAsList) obj;
            int size = size();
            if (booleanArrayAsList.size() != size) {
                return false;
            }
            for (int i3 = 0; i3 < size; i3++) {
                if (this.array[this.start + i3] != booleanArrayAsList.array[booleanArrayAsList.start + i3]) {
                    return false;
                }
            }
            return true;
        }

        public int hashCode() {
            int i3 = 1;
            for (int i4 = this.start; i4 < this.end; i4++) {
                i3 = (i3 * 31) + Booleans.hashCode(this.array[i4]);
            }
            return i3;
        }

        public int indexOf(@CheckForNull Object obj) {
            int access$000;
            if (!(obj instanceof Boolean) || (access$000 = Booleans.indexOf(this.array, ((Boolean) obj).booleanValue(), this.start, this.end)) < 0) {
                return -1;
            }
            return access$000 - this.start;
        }

        public boolean isEmpty() {
            return false;
        }

        public int lastIndexOf(@CheckForNull Object obj) {
            int access$100;
            if (!(obj instanceof Boolean) || (access$100 = Booleans.lastIndexOf(this.array, ((Boolean) obj).booleanValue(), this.start, this.end)) < 0) {
                return -1;
            }
            return access$100 - this.start;
        }

        public int size() {
            return this.end - this.start;
        }

        public List<Boolean> subList(int i3, int i4) {
            Preconditions.checkPositionIndexes(i3, i4, size());
            if (i3 == i4) {
                return Collections.emptyList();
            }
            boolean[] zArr = this.array;
            int i5 = this.start;
            return new BooleanArrayAsList(zArr, i3 + i5, i5 + i4);
        }

        public boolean[] toBooleanArray() {
            return Arrays.copyOfRange(this.array, this.start, this.end);
        }

        public String toString() {
            StringBuilder sb = new StringBuilder(size() * 7);
            sb.append(this.array[this.start] ? "[true" : "[false");
            int i3 = this.start;
            while (true) {
                i3++;
                if (i3 < this.end) {
                    sb.append(this.array[i3] ? ", true" : ", false");
                } else {
                    sb.append(AbstractJsonLexerKt.END_LIST);
                    return sb.toString();
                }
            }
        }

        public BooleanArrayAsList(boolean[] zArr, int i3, int i4) {
            this.array = zArr;
            this.start = i3;
            this.end = i4;
        }

        public Boolean get(int i3) {
            Preconditions.checkElementIndex(i3, size());
            return Boolean.valueOf(this.array[this.start + i3]);
        }

        public Boolean set(int i3, Boolean bool) {
            Preconditions.checkElementIndex(i3, size());
            boolean[] zArr = this.array;
            int i4 = this.start;
            boolean z2 = zArr[i4 + i3];
            zArr[i4 + i3] = ((Boolean) Preconditions.checkNotNull(bool)).booleanValue();
            return Boolean.valueOf(z2);
        }
    }

    public enum BooleanComparator implements Comparator<Boolean> {
        TRUE_FIRST(1, "Booleans.trueFirst()"),
        FALSE_FIRST(-1, "Booleans.falseFirst()");
        
        private final String toString;
        private final int trueValue;

        private BooleanComparator(int i3, String str) {
            this.trueValue = i3;
            this.toString = str;
        }

        public String toString() {
            return this.toString;
        }

        public int compare(Boolean bool, Boolean bool2) {
            int i3 = 0;
            int i4 = bool.booleanValue() ? this.trueValue : 0;
            if (bool2.booleanValue()) {
                i3 = this.trueValue;
            }
            return i3 - i4;
        }
    }

    public enum LexicographicalComparator implements Comparator<boolean[]> {
        INSTANCE;

        public String toString() {
            return "Booleans.lexicographicalComparator()";
        }

        public int compare(boolean[] zArr, boolean[] zArr2) {
            int min = Math.min(zArr.length, zArr2.length);
            for (int i3 = 0; i3 < min; i3++) {
                int compare = Booleans.compare(zArr[i3], zArr2[i3]);
                if (compare != 0) {
                    return compare;
                }
            }
            return zArr.length - zArr2.length;
        }
    }

    private Booleans() {
    }

    public static List<Boolean> asList(boolean... zArr) {
        return zArr.length == 0 ? Collections.emptyList() : new BooleanArrayAsList(zArr);
    }

    public static int compare(boolean z2, boolean z3) {
        if (z2 == z3) {
            return 0;
        }
        return z2 ? 1 : -1;
    }

    public static boolean[] concat(boolean[]... zArr) {
        int i3 = 0;
        for (boolean[] length : zArr) {
            i3 += length.length;
        }
        boolean[] zArr2 = new boolean[i3];
        int i4 = 0;
        for (boolean[] zArr3 : zArr) {
            System.arraycopy(zArr3, 0, zArr2, i4, zArr3.length);
            i4 += zArr3.length;
        }
        return zArr2;
    }

    public static boolean contains(boolean[] zArr, boolean z2) {
        for (boolean z3 : zArr) {
            if (z3 == z2) {
                return true;
            }
        }
        return false;
    }

    public static int countTrue(boolean... zArr) {
        int i3 = 0;
        for (boolean z2 : zArr) {
            if (z2) {
                i3++;
            }
        }
        return i3;
    }

    public static boolean[] ensureCapacity(boolean[] zArr, int i3, int i4) {
        boolean z2 = false;
        Preconditions.checkArgument(i3 >= 0, "Invalid minLength: %s", i3);
        if (i4 >= 0) {
            z2 = true;
        }
        Preconditions.checkArgument(z2, "Invalid padding: %s", i4);
        return zArr.length < i3 ? Arrays.copyOf(zArr, i3 + i4) : zArr;
    }

    public static Comparator<Boolean> falseFirst() {
        return BooleanComparator.FALSE_FIRST;
    }

    public static int hashCode(boolean z2) {
        return z2 ? 1231 : 1237;
    }

    public static int indexOf(boolean[] zArr, boolean z2) {
        return indexOf(zArr, z2, 0, zArr.length);
    }

    public static String join(String str, boolean... zArr) {
        Preconditions.checkNotNull(str);
        if (zArr.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder(zArr.length * 7);
        sb.append(zArr[0]);
        for (int i3 = 1; i3 < zArr.length; i3++) {
            sb.append(str);
            sb.append(zArr[i3]);
        }
        return sb.toString();
    }

    public static int lastIndexOf(boolean[] zArr, boolean z2) {
        return lastIndexOf(zArr, z2, 0, zArr.length);
    }

    public static Comparator<boolean[]> lexicographicalComparator() {
        return LexicographicalComparator.INSTANCE;
    }

    public static void reverse(boolean[] zArr) {
        Preconditions.checkNotNull(zArr);
        reverse(zArr, 0, zArr.length);
    }

    public static void rotate(boolean[] zArr, int i3) {
        rotate(zArr, i3, 0, zArr.length);
    }

    public static boolean[] toArray(Collection<Boolean> collection) {
        if (collection instanceof BooleanArrayAsList) {
            return ((BooleanArrayAsList) collection).toBooleanArray();
        }
        Object[] array = collection.toArray();
        int length = array.length;
        boolean[] zArr = new boolean[length];
        for (int i3 = 0; i3 < length; i3++) {
            zArr[i3] = ((Boolean) Preconditions.checkNotNull(array[i3])).booleanValue();
        }
        return zArr;
    }

    public static Comparator<Boolean> trueFirst() {
        return BooleanComparator.TRUE_FIRST;
    }

    /* access modifiers changed from: private */
    public static int indexOf(boolean[] zArr, boolean z2, int i3, int i4) {
        while (i3 < i4) {
            if (zArr[i3] == z2) {
                return i3;
            }
            i3++;
        }
        return -1;
    }

    /* access modifiers changed from: private */
    public static int lastIndexOf(boolean[] zArr, boolean z2, int i3, int i4) {
        for (int i5 = i4 - 1; i5 >= i3; i5--) {
            if (zArr[i5] == z2) {
                return i5;
            }
        }
        return -1;
    }

    public static void rotate(boolean[] zArr, int i3, int i4, int i5) {
        Preconditions.checkNotNull(zArr);
        Preconditions.checkPositionIndexes(i4, i5, zArr.length);
        if (zArr.length > 1) {
            int i6 = i5 - i4;
            int i7 = (-i3) % i6;
            if (i7 < 0) {
                i7 += i6;
            }
            int i8 = i7 + i4;
            if (i8 != i4) {
                reverse(zArr, i4, i8);
                reverse(zArr, i8, i5);
                reverse(zArr, i4, i5);
            }
        }
    }

    public static int indexOf(boolean[] zArr, boolean[] zArr2) {
        Preconditions.checkNotNull(zArr, "array");
        Preconditions.checkNotNull(zArr2, TypedValues.AttributesType.S_TARGET);
        if (zArr2.length == 0) {
            return 0;
        }
        int i3 = 0;
        while (i3 < (zArr.length - zArr2.length) + 1) {
            int i4 = 0;
            while (i4 < zArr2.length) {
                if (zArr[i3 + i4] != zArr2[i4]) {
                    i3++;
                } else {
                    i4++;
                }
            }
            return i3;
        }
        return -1;
    }

    public static void reverse(boolean[] zArr, int i3, int i4) {
        Preconditions.checkNotNull(zArr);
        Preconditions.checkPositionIndexes(i3, i4, zArr.length);
        for (int i5 = i4 - 1; i3 < i5; i5--) {
            boolean z2 = zArr[i3];
            zArr[i3] = zArr[i5];
            zArr[i5] = z2;
            i3++;
        }
    }
}
