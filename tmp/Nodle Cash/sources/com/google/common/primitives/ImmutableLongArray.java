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
public final class ImmutableLongArray implements Serializable {
    /* access modifiers changed from: private */
    public static final ImmutableLongArray EMPTY = new ImmutableLongArray(new long[0]);
    /* access modifiers changed from: private */
    public final long[] array;
    private final int end;
    /* access modifiers changed from: private */
    public final transient int start;

    public static class AsList extends AbstractList<Long> implements RandomAccess, Serializable {
        private final ImmutableLongArray parent;

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
                if (next instanceof Long) {
                    int i3 = access$100 + 1;
                    if (this.parent.array[access$100] == ((Long) next).longValue()) {
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
            if (obj instanceof Long) {
                return this.parent.indexOf(((Long) obj).longValue());
            }
            return -1;
        }

        public int lastIndexOf(@CheckForNull Object obj) {
            if (obj instanceof Long) {
                return this.parent.lastIndexOf(((Long) obj).longValue());
            }
            return -1;
        }

        public int size() {
            return this.parent.length();
        }

        public List<Long> subList(int i3, int i4) {
            return this.parent.subArray(i3, i4).asList();
        }

        public String toString() {
            return this.parent.toString();
        }

        private AsList(ImmutableLongArray immutableLongArray) {
            this.parent = immutableLongArray;
        }

        public Long get(int i3) {
            return Long.valueOf(this.parent.get(i3));
        }
    }

    public static Builder builder(int i3) {
        Preconditions.checkArgument(i3 >= 0, "Invalid initialCapacity: %s", i3);
        return new Builder(i3);
    }

    public static ImmutableLongArray copyOf(long[] jArr) {
        if (jArr.length == 0) {
            return EMPTY;
        }
        return new ImmutableLongArray(Arrays.copyOf(jArr, jArr.length));
    }

    private boolean isPartialView() {
        return this.start > 0 || this.end < this.array.length;
    }

    public static ImmutableLongArray of() {
        return EMPTY;
    }

    public List<Long> asList() {
        return new AsList();
    }

    public boolean contains(long j2) {
        return indexOf(j2) >= 0;
    }

    public boolean equals(@CheckForNull Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ImmutableLongArray)) {
            return false;
        }
        ImmutableLongArray immutableLongArray = (ImmutableLongArray) obj;
        if (length() != immutableLongArray.length()) {
            return false;
        }
        for (int i3 = 0; i3 < length(); i3++) {
            if (get(i3) != immutableLongArray.get(i3)) {
                return false;
            }
        }
        return true;
    }

    public long get(int i3) {
        Preconditions.checkElementIndex(i3, length());
        return this.array[this.start + i3];
    }

    public int hashCode() {
        int i3 = 1;
        for (int i4 = this.start; i4 < this.end; i4++) {
            i3 = (i3 * 31) + Longs.hashCode(this.array[i4]);
        }
        return i3;
    }

    public int indexOf(long j2) {
        for (int i3 = this.start; i3 < this.end; i3++) {
            if (this.array[i3] == j2) {
                return i3 - this.start;
            }
        }
        return -1;
    }

    public boolean isEmpty() {
        return this.end == this.start;
    }

    public int lastIndexOf(long j2) {
        int i3 = this.end;
        while (true) {
            i3--;
            int i4 = this.start;
            if (i3 < i4) {
                return -1;
            }
            if (this.array[i3] == j2) {
                return i3 - i4;
            }
        }
    }

    public int length() {
        return this.end - this.start;
    }

    public Object readResolve() {
        return isEmpty() ? EMPTY : this;
    }

    public ImmutableLongArray subArray(int i3, int i4) {
        Preconditions.checkPositionIndexes(i3, i4, length());
        if (i3 == i4) {
            return EMPTY;
        }
        long[] jArr = this.array;
        int i5 = this.start;
        return new ImmutableLongArray(jArr, i3 + i5, i5 + i4);
    }

    public long[] toArray() {
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

    public ImmutableLongArray trimmed() {
        return isPartialView() ? new ImmutableLongArray(toArray()) : this;
    }

    public Object writeReplace() {
        return trimmed();
    }

    private ImmutableLongArray(long[] jArr) {
        this(jArr, 0, jArr.length);
    }

    public static ImmutableLongArray of(long j2) {
        return new ImmutableLongArray(new long[]{j2});
    }

    public static final class Builder {
        private long[] array;
        private int count = 0;

        public Builder(int i3) {
            this.array = new long[i3];
        }

        private void ensureRoomFor(int i3) {
            int i4 = this.count + i3;
            long[] jArr = this.array;
            if (i4 > jArr.length) {
                this.array = Arrays.copyOf(jArr, expandedCapacity(jArr.length, i4));
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
        public Builder add(long j2) {
            ensureRoomFor(1);
            long[] jArr = this.array;
            int i3 = this.count;
            jArr[i3] = j2;
            this.count = i3 + 1;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder addAll(long[] jArr) {
            ensureRoomFor(jArr.length);
            System.arraycopy(jArr, 0, this.array, this.count, jArr.length);
            this.count += jArr.length;
            return this;
        }

        public ImmutableLongArray build() {
            return this.count == 0 ? ImmutableLongArray.EMPTY : new ImmutableLongArray(this.array, 0, this.count);
        }

        @CanIgnoreReturnValue
        public Builder addAll(Iterable<Long> iterable) {
            if (iterable instanceof Collection) {
                return addAll((Collection<Long>) (Collection) iterable);
            }
            for (Long longValue : iterable) {
                add(longValue.longValue());
            }
            return this;
        }

        @CanIgnoreReturnValue
        public Builder addAll(Collection<Long> collection) {
            ensureRoomFor(collection.size());
            for (Long longValue : collection) {
                long[] jArr = this.array;
                int i3 = this.count;
                this.count = i3 + 1;
                jArr[i3] = longValue.longValue();
            }
            return this;
        }

        @CanIgnoreReturnValue
        public Builder addAll(ImmutableLongArray immutableLongArray) {
            ensureRoomFor(immutableLongArray.length());
            System.arraycopy(immutableLongArray.array, immutableLongArray.start, this.array, this.count, immutableLongArray.length());
            this.count = immutableLongArray.length() + this.count;
            return this;
        }
    }

    private ImmutableLongArray(long[] jArr, int i3, int i4) {
        this.array = jArr;
        this.start = i3;
        this.end = i4;
    }

    public static Builder builder() {
        return new Builder(10);
    }

    public static ImmutableLongArray of(long j2, long j3) {
        return new ImmutableLongArray(new long[]{j2, j3});
    }

    public static ImmutableLongArray copyOf(Collection<Long> collection) {
        return collection.isEmpty() ? EMPTY : new ImmutableLongArray(Longs.toArray(collection));
    }

    public static ImmutableLongArray of(long j2, long j3, long j4) {
        return new ImmutableLongArray(new long[]{j2, j3, j4});
    }

    public static ImmutableLongArray copyOf(Iterable<Long> iterable) {
        if (iterable instanceof Collection) {
            return copyOf((Collection<Long>) (Collection) iterable);
        }
        return builder().addAll(iterable).build();
    }

    public static ImmutableLongArray of(long j2, long j3, long j4, long j5) {
        return new ImmutableLongArray(new long[]{j2, j3, j4, j5});
    }

    public static ImmutableLongArray of(long j2, long j3, long j4, long j5, long j6) {
        return new ImmutableLongArray(new long[]{j2, j3, j4, j5, j6});
    }

    public static ImmutableLongArray of(long j2, long j3, long j4, long j5, long j6, long j7) {
        return new ImmutableLongArray(new long[]{j2, j3, j4, j5, j6, j7});
    }

    public static ImmutableLongArray of(long j2, long... jArr) {
        Preconditions.checkArgument(jArr.length <= 2147483646, "the total number of elements must fit in an int");
        long[] jArr2 = new long[(jArr.length + 1)];
        jArr2[0] = j2;
        System.arraycopy(jArr, 0, jArr2, 1, jArr.length);
        return new ImmutableLongArray(jArr2);
    }
}
