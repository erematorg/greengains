package com.google.crypto.tink.shaded.protobuf;

import A.a;
import androidx.constraintlayout.core.state.b;
import com.google.crypto.tink.shaded.protobuf.Internal;
import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

final class LongArrayList extends AbstractProtobufList<Long> implements Internal.LongList, RandomAccess, PrimitiveNonBoxingCollection {
    private static final LongArrayList EMPTY_LIST;
    private long[] array;
    private int size;

    static {
        LongArrayList longArrayList = new LongArrayList(new long[0], 0);
        EMPTY_LIST = longArrayList;
        longArrayList.makeImmutable();
    }

    public LongArrayList() {
        this(new long[10], 0);
    }

    public static LongArrayList emptyList() {
        return EMPTY_LIST;
    }

    private void ensureIndexInRange(int i3) {
        if (i3 < 0 || i3 >= this.size) {
            throw new IndexOutOfBoundsException(makeOutOfBoundsExceptionMessage(i3));
        }
    }

    private String makeOutOfBoundsExceptionMessage(int i3) {
        StringBuilder o3 = a.o(i3, "Index:", ", Size:");
        o3.append(this.size);
        return o3.toString();
    }

    public boolean addAll(Collection<? extends Long> collection) {
        ensureIsMutable();
        Internal.checkNotNull(collection);
        if (!(collection instanceof LongArrayList)) {
            return super.addAll(collection);
        }
        LongArrayList longArrayList = (LongArrayList) collection;
        int i3 = longArrayList.size;
        if (i3 == 0) {
            return false;
        }
        int i4 = this.size;
        if (Integer.MAX_VALUE - i4 >= i3) {
            int i5 = i4 + i3;
            long[] jArr = this.array;
            if (i5 > jArr.length) {
                this.array = Arrays.copyOf(jArr, i5);
            }
            System.arraycopy(longArrayList.array, 0, this.array, this.size, longArrayList.size);
            this.size = i5;
            this.modCount++;
            return true;
        }
        throw new OutOfMemoryError();
    }

    public void addLong(long j2) {
        ensureIsMutable();
        int i3 = this.size;
        long[] jArr = this.array;
        if (i3 == jArr.length) {
            long[] jArr2 = new long[b.b(i3, 3, 2, 1)];
            System.arraycopy(jArr, 0, jArr2, 0, i3);
            this.array = jArr2;
        }
        long[] jArr3 = this.array;
        int i4 = this.size;
        this.size = i4 + 1;
        jArr3[i4] = j2;
    }

    public boolean contains(Object obj) {
        return indexOf(obj) != -1;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof LongArrayList)) {
            return super.equals(obj);
        }
        LongArrayList longArrayList = (LongArrayList) obj;
        if (this.size != longArrayList.size) {
            return false;
        }
        long[] jArr = longArrayList.array;
        for (int i3 = 0; i3 < this.size; i3++) {
            if (this.array[i3] != jArr[i3]) {
                return false;
            }
        }
        return true;
    }

    public long getLong(int i3) {
        ensureIndexInRange(i3);
        return this.array[i3];
    }

    public int hashCode() {
        int i3 = 1;
        for (int i4 = 0; i4 < this.size; i4++) {
            i3 = (i3 * 31) + Internal.hashLong(this.array[i4]);
        }
        return i3;
    }

    public int indexOf(Object obj) {
        if (!(obj instanceof Long)) {
            return -1;
        }
        long longValue = ((Long) obj).longValue();
        int size2 = size();
        for (int i3 = 0; i3 < size2; i3++) {
            if (this.array[i3] == longValue) {
                return i3;
            }
        }
        return -1;
    }

    public void removeRange(int i3, int i4) {
        ensureIsMutable();
        if (i4 >= i3) {
            long[] jArr = this.array;
            System.arraycopy(jArr, i4, jArr, i3, this.size - i4);
            this.size -= i4 - i3;
            this.modCount++;
            return;
        }
        throw new IndexOutOfBoundsException("toIndex < fromIndex");
    }

    public long setLong(int i3, long j2) {
        ensureIsMutable();
        ensureIndexInRange(i3);
        long[] jArr = this.array;
        long j3 = jArr[i3];
        jArr[i3] = j2;
        return j3;
    }

    public int size() {
        return this.size;
    }

    private LongArrayList(long[] jArr, int i3) {
        this.array = jArr;
        this.size = i3;
    }

    public Long get(int i3) {
        return Long.valueOf(getLong(i3));
    }

    public Internal.LongList mutableCopyWithCapacity(int i3) {
        if (i3 >= this.size) {
            return new LongArrayList(Arrays.copyOf(this.array, i3), this.size);
        }
        throw new IllegalArgumentException();
    }

    public Long remove(int i3) {
        ensureIsMutable();
        ensureIndexInRange(i3);
        long[] jArr = this.array;
        long j2 = jArr[i3];
        int i4 = this.size;
        if (i3 < i4 - 1) {
            System.arraycopy(jArr, i3 + 1, jArr, i3, (i4 - i3) - 1);
        }
        this.size--;
        this.modCount++;
        return Long.valueOf(j2);
    }

    public Long set(int i3, Long l2) {
        return Long.valueOf(setLong(i3, l2.longValue()));
    }

    public boolean add(Long l2) {
        addLong(l2.longValue());
        return true;
    }

    public void add(int i3, Long l2) {
        addLong(i3, l2.longValue());
    }

    private void addLong(int i3, long j2) {
        int i4;
        ensureIsMutable();
        if (i3 < 0 || i3 > (i4 = this.size)) {
            throw new IndexOutOfBoundsException(makeOutOfBoundsExceptionMessage(i3));
        }
        long[] jArr = this.array;
        if (i4 < jArr.length) {
            System.arraycopy(jArr, i3, jArr, i3 + 1, i4 - i3);
        } else {
            long[] jArr2 = new long[b.b(i4, 3, 2, 1)];
            System.arraycopy(jArr, 0, jArr2, 0, i3);
            System.arraycopy(this.array, i3, jArr2, i3 + 1, this.size - i3);
            this.array = jArr2;
        }
        this.array[i3] = j2;
        this.size++;
        this.modCount++;
    }
}
