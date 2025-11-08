package com.google.protobuf;

import A.a;
import androidx.constraintlayout.core.state.b;
import com.google.protobuf.Internal;
import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

final class IntArrayList extends AbstractProtobufList<Integer> implements Internal.IntList, RandomAccess, PrimitiveNonBoxingCollection {
    private static final IntArrayList EMPTY_LIST = new IntArrayList(new int[0], 0, false);
    private int[] array;
    private int size;

    public IntArrayList() {
        this(new int[10], 0, true);
    }

    public static IntArrayList emptyList() {
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

    public boolean addAll(Collection<? extends Integer> collection) {
        ensureIsMutable();
        Internal.checkNotNull(collection);
        if (!(collection instanceof IntArrayList)) {
            return super.addAll(collection);
        }
        IntArrayList intArrayList = (IntArrayList) collection;
        int i3 = intArrayList.size;
        if (i3 == 0) {
            return false;
        }
        int i4 = this.size;
        if (Integer.MAX_VALUE - i4 >= i3) {
            int i5 = i4 + i3;
            int[] iArr = this.array;
            if (i5 > iArr.length) {
                this.array = Arrays.copyOf(iArr, i5);
            }
            System.arraycopy(intArrayList.array, 0, this.array, this.size, intArrayList.size);
            this.size = i5;
            this.modCount++;
            return true;
        }
        throw new OutOfMemoryError();
    }

    public void addInt(int i3) {
        ensureIsMutable();
        int i4 = this.size;
        int[] iArr = this.array;
        if (i4 == iArr.length) {
            int[] iArr2 = new int[b.b(i4, 3, 2, 1)];
            System.arraycopy(iArr, 0, iArr2, 0, i4);
            this.array = iArr2;
        }
        int[] iArr3 = this.array;
        int i5 = this.size;
        this.size = i5 + 1;
        iArr3[i5] = i3;
    }

    public boolean contains(Object obj) {
        return indexOf(obj) != -1;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof IntArrayList)) {
            return super.equals(obj);
        }
        IntArrayList intArrayList = (IntArrayList) obj;
        if (this.size != intArrayList.size) {
            return false;
        }
        int[] iArr = intArrayList.array;
        for (int i3 = 0; i3 < this.size; i3++) {
            if (this.array[i3] != iArr[i3]) {
                return false;
            }
        }
        return true;
    }

    public int getInt(int i3) {
        ensureIndexInRange(i3);
        return this.array[i3];
    }

    public int hashCode() {
        int i3 = 1;
        for (int i4 = 0; i4 < this.size; i4++) {
            i3 = (i3 * 31) + this.array[i4];
        }
        return i3;
    }

    public int indexOf(Object obj) {
        if (!(obj instanceof Integer)) {
            return -1;
        }
        int intValue = ((Integer) obj).intValue();
        int size2 = size();
        for (int i3 = 0; i3 < size2; i3++) {
            if (this.array[i3] == intValue) {
                return i3;
            }
        }
        return -1;
    }

    public void removeRange(int i3, int i4) {
        ensureIsMutable();
        if (i4 >= i3) {
            int[] iArr = this.array;
            System.arraycopy(iArr, i4, iArr, i3, this.size - i4);
            this.size -= i4 - i3;
            this.modCount++;
            return;
        }
        throw new IndexOutOfBoundsException("toIndex < fromIndex");
    }

    public int setInt(int i3, int i4) {
        ensureIsMutable();
        ensureIndexInRange(i3);
        int[] iArr = this.array;
        int i5 = iArr[i3];
        iArr[i3] = i4;
        return i5;
    }

    public int size() {
        return this.size;
    }

    private IntArrayList(int[] iArr, int i3, boolean z2) {
        super(z2);
        this.array = iArr;
        this.size = i3;
    }

    public Integer get(int i3) {
        return Integer.valueOf(getInt(i3));
    }

    public Internal.IntList mutableCopyWithCapacity(int i3) {
        if (i3 >= this.size) {
            return new IntArrayList(Arrays.copyOf(this.array, i3), this.size, true);
        }
        throw new IllegalArgumentException();
    }

    public Integer remove(int i3) {
        ensureIsMutable();
        ensureIndexInRange(i3);
        int[] iArr = this.array;
        int i4 = iArr[i3];
        int i5 = this.size;
        if (i3 < i5 - 1) {
            System.arraycopy(iArr, i3 + 1, iArr, i3, (i5 - i3) - 1);
        }
        this.size--;
        this.modCount++;
        return Integer.valueOf(i4);
    }

    public Integer set(int i3, Integer num) {
        return Integer.valueOf(setInt(i3, num.intValue()));
    }

    public boolean add(Integer num) {
        addInt(num.intValue());
        return true;
    }

    public void add(int i3, Integer num) {
        addInt(i3, num.intValue());
    }

    private void addInt(int i3, int i4) {
        int i5;
        ensureIsMutable();
        if (i3 < 0 || i3 > (i5 = this.size)) {
            throw new IndexOutOfBoundsException(makeOutOfBoundsExceptionMessage(i3));
        }
        int[] iArr = this.array;
        if (i5 < iArr.length) {
            System.arraycopy(iArr, i3, iArr, i3 + 1, i5 - i3);
        } else {
            int[] iArr2 = new int[b.b(i5, 3, 2, 1)];
            System.arraycopy(iArr, 0, iArr2, 0, i3);
            System.arraycopy(this.array, i3, iArr2, i3 + 1, this.size - i3);
            this.array = iArr2;
        }
        this.array[i3] = i4;
        this.size++;
        this.modCount++;
    }
}
