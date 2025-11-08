package com.google.common.primitives;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.Immutable;
import java.io.Serializable;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.RandomAccess;
import javax.annotation.CheckForNull;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import okhttp3.internal.url._UrlKt;

@GwtCompatible
@ElementTypesAreNonnullByDefault
@Immutable
public final class ImmutableDoubleArray implements Serializable {
    /* access modifiers changed from: private */
    public static final ImmutableDoubleArray EMPTY = new ImmutableDoubleArray(new double[0]);
    /* access modifiers changed from: private */
    public final double[] array;
    private final int end;
    /* access modifiers changed from: private */
    public final transient int start;

    public static class AsList extends AbstractList<Double> implements RandomAccess, Serializable {
        private final ImmutableDoubleArray parent;

        public boolean contains(@CheckForNull Object obj) {
            return indexOf(obj) >= 0;
        }

        public boolean equals(@CheckForNull Object obj) {
            if (obj instanceof AsList) {
                return this.parent.equals(((AsList) obj).parent);
            }
            if (!(obj instanceof List)) {
                return false;
            }
            List list = (List) obj;
            if (size() != list.size()) {
                return false;
            }
            int access$100 = this.parent.start;
            for (Object next : list) {
                if (next instanceof Double) {
                    int i3 = access$100 + 1;
                    if (ImmutableDoubleArray.areEqual(this.parent.array[access$100], ((Double) next).doubleValue())) {
                        access$100 = i3;
                    }
                }
                return false;
            }
            return true;
        }

        public int hashCode() {
            return this.parent.hashCode();
        }

        public int indexOf(@CheckForNull Object obj) {
            if (obj instanceof Double) {
                return this.parent.indexOf(((Double) obj).doubleValue());
            }
            return -1;
        }

        public int lastIndexOf(@CheckForNull Object obj) {
            if (obj instanceof Double) {
                return this.parent.lastIndexOf(((Double) obj).doubleValue());
            }
            return -1;
        }

        public int size() {
            return this.parent.length();
        }

        public List<Double> subList(int i3, int i4) {
            return this.parent.subArray(i3, i4).asList();
        }

        public String toString() {
            return this.parent.toString();
        }

        private AsList(ImmutableDoubleArray immutableDoubleArray) {
            this.parent = immutableDoubleArray;
        }

        public Double get(int i3) {
            return Double.valueOf(this.parent.get(i3));
        }
    }

    /* access modifiers changed from: private */
    public static boolean areEqual(double d2, double d3) {
        return Double.doubleToLongBits(d2) == Double.doubleToLongBits(d3);
    }

    public static Builder builder(int i3) {
        Preconditions.checkArgument(i3 >= 0, "Invalid initialCapacity: %s", i3);
        return new Builder(i3);
    }

    public static ImmutableDoubleArray copyOf(double[] dArr) {
        if (dArr.length == 0) {
            return EMPTY;
        }
        return new ImmutableDoubleArray(Arrays.copyOf(dArr, dArr.length));
    }

    private boolean isPartialView() {
        return this.start > 0 || this.end < this.array.length;
    }

    public static ImmutableDoubleArray of() {
        return EMPTY;
    }

    public List<Double> asList() {
        return new AsList();
    }

    public boolean contains(double d2) {
        return indexOf(d2) >= 0;
    }

    public boolean equals(@CheckForNull Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ImmutableDoubleArray)) {
            return false;
        }
        ImmutableDoubleArray immutableDoubleArray = (ImmutableDoubleArray) obj;
        if (length() != immutableDoubleArray.length()) {
            return false;
        }
        for (int i3 = 0; i3 < length(); i3++) {
            if (!areEqual(get(i3), immutableDoubleArray.get(i3))) {
                return false;
            }
        }
        return true;
    }

    public double get(int i3) {
        Preconditions.checkElementIndex(i3, length());
        return this.array[this.start + i3];
    }

    public int hashCode() {
        int i3 = 1;
        for (int i4 = this.start; i4 < this.end; i4++) {
            i3 = (i3 * 31) + Doubles.hashCode(this.array[i4]);
        }
        return i3;
    }

    public int indexOf(double d2) {
        for (int i3 = this.start; i3 < this.end; i3++) {
            if (areEqual(this.array[i3], d2)) {
                return i3 - this.start;
            }
        }
        return -1;
    }

    public boolean isEmpty() {
        return this.end == this.start;
    }

    public int lastIndexOf(double d2) {
        int i3 = this.end;
        while (true) {
            i3--;
            if (i3 < this.start) {
                return -1;
            }
            if (areEqual(this.array[i3], d2)) {
                return i3 - this.start;
            }
        }
    }

    public int length() {
        return this.end - this.start;
    }

    public Object readResolve() {
        return isEmpty() ? EMPTY : this;
    }

    public ImmutableDoubleArray subArray(int i3, int i4) {
        Preconditions.checkPositionIndexes(i3, i4, length());
        if (i3 == i4) {
            return EMPTY;
        }
        double[] dArr = this.array;
        int i5 = this.start;
        return new ImmutableDoubleArray(dArr, i3 + i5, i5 + i4);
    }

    public double[] toArray() {
        return Arrays.copyOfRange(this.array, this.start, this.end);
    }

    public String toString() {
        if (isEmpty()) {
            return _UrlKt.PATH_SEGMENT_ENCODE_SET_URI;
        }
        StringBuilder sb = new StringBuilder(length() * 5);
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

    public ImmutableDoubleArray trimmed() {
        return isPartialView() ? new ImmutableDoubleArray(toArray()) : this;
    }

    public Object writeReplace() {
        return trimmed();
    }

    private ImmutableDoubleArray(double[] dArr) {
        this(dArr, 0, dArr.length);
    }

    public static ImmutableDoubleArray of(double d2) {
        return new ImmutableDoubleArray(new double[]{d2});
    }

    public static final class Builder {
        private double[] array;
        private int count = 0;

        public Builder(int i3) {
            this.array = new double[i3];
        }

        private void ensureRoomFor(int i3) {
            int i4 = this.count + i3;
            double[] dArr = this.array;
            if (i4 > dArr.length) {
                this.array = Arrays.copyOf(dArr, expandedCapacity(dArr.length, i4));
            }
        }

        private static int expandedCapacity(int i3, int i4) {
            if (i4 >= 0) {
                int i5 = i3 + (i3 >> 1) + 1;
                if (i5 < i4) {
                    i5 = Integer.highestOneBit(i4 - 1) << 1;
                }
                if (i5 < 0) {
                    return Integer.MAX_VALUE;
                }
                return i5;
            }
            throw new AssertionError("cannot store more than MAX_VALUE elements");
        }

        @CanIgnoreReturnValue
        public Builder add(double d2) {
            ensureRoomFor(1);
            double[] dArr = this.array;
            int i3 = this.count;
            dArr[i3] = d2;
            this.count = i3 + 1;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder addAll(double[] dArr) {
            ensureRoomFor(dArr.length);
            System.arraycopy(dArr, 0, this.array, this.count, dArr.length);
            this.count += dArr.length;
            return this;
        }

        public ImmutableDoubleArray build() {
            return this.count == 0 ? ImmutableDoubleArray.EMPTY : new ImmutableDoubleArray(this.array, 0, this.count);
        }

        @CanIgnoreReturnValue
        public Builder addAll(Iterable<Double> iterable) {
            if (iterable instanceof Collection) {
                return addAll((Collection<Double>) (Collection) iterable);
            }
            for (Double doubleValue : iterable) {
                add(doubleValue.doubleValue());
            }
            return this;
        }

        @CanIgnoreReturnValue
        public Builder addAll(Collection<Double> collection) {
            ensureRoomFor(collection.size());
            for (Double doubleValue : collection) {
                double[] dArr = this.array;
                int i3 = this.count;
                this.count = i3 + 1;
                dArr[i3] = doubleValue.doubleValue();
            }
            return this;
        }

        @CanIgnoreReturnValue
        public Builder addAll(ImmutableDoubleArray immutableDoubleArray) {
            ensureRoomFor(immutableDoubleArray.length());
            System.arraycopy(immutableDoubleArray.array, immutableDoubleArray.start, this.array, this.count, immutableDoubleArray.length());
            this.count = immutableDoubleArray.length() + this.count;
            return this;
        }
    }

    private ImmutableDoubleArray(double[] dArr, int i3, int i4) {
        this.array = dArr;
        this.start = i3;
        this.end = i4;
    }

    public static Builder builder() {
        return new Builder(10);
    }

    public static ImmutableDoubleArray of(double d2, double d3) {
        return new ImmutableDoubleArray(new double[]{d2, d3});
    }

    public static ImmutableDoubleArray copyOf(Collection<Double> collection) {
        return collection.isEmpty() ? EMPTY : new ImmutableDoubleArray(Doubles.toArray(collection));
    }

    public static ImmutableDoubleArray of(double d2, double d3, double d4) {
        return new ImmutableDoubleArray(new double[]{d2, d3, d4});
    }

    public static ImmutableDoubleArray copyOf(Iterable<Double> iterable) {
        if (iterable instanceof Collection) {
            return copyOf((Collection<Double>) (Collection) iterable);
        }
        return builder().addAll(iterable).build();
    }

    public static ImmutableDoubleArray of(double d2, double d3, double d4, double d5) {
        return new ImmutableDoubleArray(new double[]{d2, d3, d4, d5});
    }

    public static ImmutableDoubleArray of(double d2, double d3, double d4, double d5, double d6) {
        return new ImmutableDoubleArray(new double[]{d2, d3, d4, d5, d6});
    }

    public static ImmutableDoubleArray of(double d2, double d3, double d4, double d5, double d6, double d7) {
        return new ImmutableDoubleArray(new double[]{d2, d3, d4, d5, d6, d7});
    }

    public static ImmutableDoubleArray of(double d2, double... dArr) {
        Preconditions.checkArgument(dArr.length <= 2147483646, "the total number of elements must fit in an int");
        double[] dArr2 = new double[(dArr.length + 1)];
        dArr2[0] = d2;
        System.arraycopy(dArr, 0, dArr2, 1, dArr.length);
        return new ImmutableDoubleArray(dArr2);
    }
}
