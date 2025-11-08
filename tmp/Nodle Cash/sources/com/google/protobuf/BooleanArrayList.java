package com.google.protobuf;

import A.a;
import androidx.constraintlayout.core.state.b;
import com.google.protobuf.Internal;
import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

final class BooleanArrayList extends AbstractProtobufList<Boolean> implements Internal.BooleanList, RandomAccess, PrimitiveNonBoxingCollection {
    private static final BooleanArrayList EMPTY_LIST = new BooleanArrayList(new boolean[0], 0, false);
    private boolean[] array;
    private int size;

    public BooleanArrayList() {
        this(new boolean[10], 0, true);
    }

    public static BooleanArrayList emptyList() {
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

    public boolean addAll(Collection<? extends Boolean> collection) {
        ensureIsMutable();
        Internal.checkNotNull(collection);
        if (!(collection instanceof BooleanArrayList)) {
            return super.addAll(collection);
        }
        BooleanArrayList booleanArrayList = (BooleanArrayList) collection;
        int i3 = booleanArrayList.size;
        if (i3 == 0) {
            return false;
        }
        int i4 = this.size;
        if (Integer.MAX_VALUE - i4 >= i3) {
            int i5 = i4 + i3;
            boolean[] zArr = this.array;
            if (i5 > zArr.length) {
                this.array = Arrays.copyOf(zArr, i5);
            }
            System.arraycopy(booleanArrayList.array, 0, this.array, this.size, booleanArrayList.size);
            this.size = i5;
            this.modCount++;
            return true;
        }
        throw new OutOfMemoryError();
    }

    public void addBoolean(boolean z2) {
        ensureIsMutable();
        int i3 = this.size;
        boolean[] zArr = this.array;
        if (i3 == zArr.length) {
            boolean[] zArr2 = new boolean[b.b(i3, 3, 2, 1)];
            System.arraycopy(zArr, 0, zArr2, 0, i3);
            this.array = zArr2;
        }
        boolean[] zArr3 = this.array;
        int i4 = this.size;
        this.size = i4 + 1;
        zArr3[i4] = z2;
    }

    public boolean contains(Object obj) {
        return indexOf(obj) != -1;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof BooleanArrayList)) {
            return super.equals(obj);
        }
        BooleanArrayList booleanArrayList = (BooleanArrayList) obj;
        if (this.size != booleanArrayList.size) {
            return false;
        }
        boolean[] zArr = booleanArrayList.array;
        for (int i3 = 0; i3 < this.size; i3++) {
            if (this.array[i3] != zArr[i3]) {
                return false;
            }
        }
        return true;
    }

    public boolean getBoolean(int i3) {
        ensureIndexInRange(i3);
        return this.array[i3];
    }

    public int hashCode() {
        int i3 = 1;
        for (int i4 = 0; i4 < this.size; i4++) {
            i3 = (i3 * 31) + Internal.hashBoolean(this.array[i4]);
        }
        return i3;
    }

    public int indexOf(Object obj) {
        if (!(obj instanceof Boolean)) {
            return -1;
        }
        boolean booleanValue = ((Boolean) obj).booleanValue();
        int size2 = size();
        for (int i3 = 0; i3 < size2; i3++) {
            if (this.array[i3] == booleanValue) {
                return i3;
            }
        }
        return -1;
    }

    public void removeRange(int i3, int i4) {
        ensureIsMutable();
        if (i4 >= i3) {
            boolean[] zArr = this.array;
            System.arraycopy(zArr, i4, zArr, i3, this.size - i4);
            this.size -= i4 - i3;
            this.modCount++;
            return;
        }
        throw new IndexOutOfBoundsException("toIndex < fromIndex");
    }

    public boolean setBoolean(int i3, boolean z2) {
        ensureIsMutable();
        ensureIndexInRange(i3);
        boolean[] zArr = this.array;
        boolean z3 = zArr[i3];
        zArr[i3] = z2;
        return z3;
    }

    public int size() {
        return this.size;
    }

    private BooleanArrayList(boolean[] zArr, int i3, boolean z2) {
        super(z2);
        this.array = zArr;
        this.size = i3;
    }

    public Boolean get(int i3) {
        return Boolean.valueOf(getBoolean(i3));
    }

    public Internal.BooleanList mutableCopyWithCapacity(int i3) {
        if (i3 >= this.size) {
            return new BooleanArrayList(Arrays.copyOf(this.array, i3), this.size, true);
        }
        throw new IllegalArgumentException();
    }

    public Boolean remove(int i3) {
        ensureIsMutable();
        ensureIndexInRange(i3);
        boolean[] zArr = this.array;
        boolean z2 = zArr[i3];
        int i4 = this.size;
        if (i3 < i4 - 1) {
            System.arraycopy(zArr, i3 + 1, zArr, i3, (i4 - i3) - 1);
        }
        this.size--;
        this.modCount++;
        return Boolean.valueOf(z2);
    }

    public Boolean set(int i3, Boolean bool) {
        return Boolean.valueOf(setBoolean(i3, bool.booleanValue()));
    }

    public boolean add(Boolean bool) {
        addBoolean(bool.booleanValue());
        return true;
    }

    public void add(int i3, Boolean bool) {
        addBoolean(i3, bool.booleanValue());
    }

    private void addBoolean(int i3, boolean z2) {
        int i4;
        ensureIsMutable();
        if (i3 < 0 || i3 > (i4 = this.size)) {
            throw new IndexOutOfBoundsException(makeOutOfBoundsExceptionMessage(i3));
        }
        boolean[] zArr = this.array;
        if (i4 < zArr.length) {
            System.arraycopy(zArr, i3, zArr, i3 + 1, i4 - i3);
        } else {
            boolean[] zArr2 = new boolean[b.b(i4, 3, 2, 1)];
            System.arraycopy(zArr, 0, zArr2, 0, i3);
            System.arraycopy(this.array, i3, zArr2, i3 + 1, this.size - i3);
            this.array = zArr2;
        }
        this.array[i3] = z2;
        this.size++;
        this.modCount++;
    }
}
