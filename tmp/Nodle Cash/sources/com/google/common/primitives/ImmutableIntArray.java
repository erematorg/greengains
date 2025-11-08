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
public final class ImmutableIntArray implements Serializable {
    /* access modifiers changed from: private */
    public static final ImmutableIntArray EMPTY = new ImmutableIntArray(new int[0]);
    /* access modifiers changed from: private */
    public final int[] array;
    private final int end;
    /* access modifiers changed from: private */
    public final transient int start;

    public static class AsList extends AbstractList<Integer> implements RandomAccess, Serializable {
        private final ImmutableIntArray parent;

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
                if (next instanceof Integer) {
                    int i3 = access$100 + 1;
                    if (this.parent.array[access$100] == ((Integer) next).intValue()) {
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
            if (obj instanceof Integer) {
                return this.parent.indexOf(((Integer) obj).intValue());
            }
            return -1;
        }

        public int lastIndexOf(@CheckForNull Object obj) {
            if (obj instanceof Integer) {
                return this.parent.lastIndexOf(((Integer) obj).intValue());
            }
            return -1;
        }

        public int size() {
            return this.parent.length();
        }

        public List<Integer> subList(int i3, int i4) {
            return this.parent.subArray(i3, i4).asList();
        }

        public String toString() {
            return this.parent.toString();
        }

        private AsList(ImmutableIntArray immutableIntArray) {
            this.parent = immutableIntArray;
        }

        public Integer get(int i3) {
            return Integer.valueOf(this.parent.get(i3));
        }
    }

    public static Builder builder(int i3) {
        Preconditions.checkArgument(i3 >= 0, "Invalid initialCapacity: %s", i3);
        return new Builder(i3);
    }

    public static ImmutableIntArray copyOf(int[] iArr) {
        return iArr.length == 0 ? EMPTY : new ImmutableIntArray(Arrays.copyOf(iArr, iArr.length));
    }

    private boolean isPartialView() {
        return this.start > 0 || this.end < this.array.length;
    }

    public static ImmutableIntArray of() {
        return EMPTY;
    }

    public List<Integer> asList() {
        return new AsList();
    }

    public boolean contains(int i3) {
        return indexOf(i3) >= 0;
    }

    public boolean equals(@CheckForNull Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ImmutableIntArray)) {
            return false;
        }
        ImmutableIntArray immutableIntArray = (ImmutableIntArray) obj;
        if (length() != immutableIntArray.length()) {
            return false;
        }
        for (int i3 = 0; i3 < length(); i3++) {
            if (get(i3) != immutableIntArray.get(i3)) {
                return false;
            }
        }
        return true;
    }

    public int get(int i3) {
        Preconditions.checkElementIndex(i3, length());
        return this.array[this.start + i3];
    }

    public int hashCode() {
        int i3 = 1;
        for (int i4 = this.start; i4 < this.end; i4++) {
            i3 = (i3 * 31) + Ints.hashCode(this.array[i4]);
        }
        return i3;
    }

    public int indexOf(int i3) {
        for (int i4 = this.start; i4 < this.end; i4++) {
            if (this.array[i4] == i3) {
                return i4 - this.start;
            }
        }
        return -1;
    }

    public boolean isEmpty() {
        return this.end == this.start;
    }

    public int lastIndexOf(int i3) {
        int i4 = this.end;
        while (true) {
            i4--;
            int i5 = this.start;
            if (i4 < i5) {
                return -1;
            }
            if (this.array[i4] == i3) {
                return i4 - i5;
            }
        }
    }

    public int length() {
        return this.end - this.start;
    }

    public Object readResolve() {
        return isEmpty() ? EMPTY : this;
    }

    public ImmutableIntArray subArray(int i3, int i4) {
        Preconditions.checkPositionIndexes(i3, i4, length());
        if (i3 == i4) {
            return EMPTY;
        }
        int[] iArr = this.array;
        int i5 = this.start;
        return new ImmutableIntArray(iArr, i3 + i5, i5 + i4);
    }

    public int[] toArray() {
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

    public ImmutableIntArray trimmed() {
        return isPartialView() ? new ImmutableIntArray(toArray()) : this;
    }

    public Object writeReplace() {
        return trimmed();
    }

    private ImmutableIntArray(int[] iArr) {
        this(iArr, 0, iArr.length);
    }

    public static ImmutableIntArray copyOf(Collection<Integer> collection) {
        return collection.isEmpty() ? EMPTY : new ImmutableIntArray(Ints.toArray(collection));
    }

    public static ImmutableIntArray of(int i3) {
        return new ImmutableIntArray(new int[]{i3});
    }

    public static final class Builder {
        private int[] array;
        private int count = 0;

        public Builder(int i3) {
            this.array = new int[i3];
        }

        private void ensureRoomFor(int i3) {
            int i4 = this.count + i3;
            int[] iArr = this.array;
            if (i4 > iArr.length) {
                this.array = Arrays.copyOf(iArr, expandedCapacity(iArr.length, i4));
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
        public Builder add(int i3) {
            ensureRoomFor(1);
            int[] iArr = this.array;
            int i4 = this.count;
            iArr[i4] = i3;
            this.count = i4 + 1;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder addAll(int[] iArr) {
            ensureRoomFor(iArr.length);
            System.arraycopy(iArr, 0, this.array, this.count, iArr.length);
            this.count += iArr.length;
            return this;
        }

        public ImmutableIntArray build() {
            return this.count == 0 ? ImmutableIntArray.EMPTY : new ImmutableIntArray(this.array, 0, this.count);
        }

        @CanIgnoreReturnValue
        public Builder addAll(Iterable<Integer> iterable) {
            if (iterable instanceof Collection) {
                return addAll((Collection<Integer>) (Collection) iterable);
            }
            for (Integer intValue : iterable) {
                add(intValue.intValue());
            }
            return this;
        }

        @CanIgnoreReturnValue
        public Builder addAll(Collection<Integer> collection) {
            ensureRoomFor(collection.size());
            for (Integer intValue : collection) {
                int[] iArr = this.array;
                int i3 = this.count;
                this.count = i3 + 1;
                iArr[i3] = intValue.intValue();
            }
            return this;
        }

        @CanIgnoreReturnValue
        public Builder addAll(ImmutableIntArray immutableIntArray) {
            ensureRoomFor(immutableIntArray.length());
            System.arraycopy(immutableIntArray.array, immutableIntArray.start, this.array, this.count, immutableIntArray.length());
            this.count = immutableIntArray.length() + this.count;
            return this;
        }
    }

    private ImmutableIntArray(int[] iArr, int i3, int i4) {
        this.array = iArr;
        this.start = i3;
        this.end = i4;
    }

    public static Builder builder() {
        return new Builder(10);
    }

    public static ImmutableIntArray copyOf(Iterable<Integer> iterable) {
        if (iterable instanceof Collection) {
            return copyOf((Collection<Integer>) (Collection) iterable);
        }
        return builder().addAll(iterable).build();
    }

    public static ImmutableIntArray of(int i3, int i4) {
        return new ImmutableIntArray(new int[]{i3, i4});
    }

    public static ImmutableIntArray of(int i3, int i4, int i5) {
        return new ImmutableIntArray(new int[]{i3, i4, i5});
    }

    public static ImmutableIntArray of(int i3, int i4, int i5, int i6) {
        return new ImmutableIntArray(new int[]{i3, i4, i5, i6});
    }

    public static ImmutableIntArray of(int i3, int i4, int i5, int i6, int i7) {
        return new ImmutableIntArray(new int[]{i3, i4, i5, i6, i7});
    }

    public static ImmutableIntArray of(int i3, int i4, int i5, int i6, int i7, int i8) {
        return new ImmutableIntArray(new int[]{i3, i4, i5, i6, i7, i8});
    }

    public static ImmutableIntArray of(int i3, int... iArr) {
        Preconditions.checkArgument(iArr.length <= 2147483646, "the total number of elements must fit in an int");
        int[] iArr2 = new int[(iArr.length + 1)];
        iArr2[0] = i3;
        System.arraycopy(iArr, 0, iArr2, 1, iArr.length);
        return new ImmutableIntArray(iArr2);
    }
}
