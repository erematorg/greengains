package com.google.crypto.tink.shaded.protobuf;

import A.a;
import androidx.constraintlayout.core.state.b;
import com.google.crypto.tink.shaded.protobuf.Internal;
import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

final class DoubleArrayList extends AbstractProtobufList<Double> implements Internal.DoubleList, RandomAccess, PrimitiveNonBoxingCollection {
    private static final DoubleArrayList EMPTY_LIST;
    private double[] array;
    private int size;

    static {
        DoubleArrayList doubleArrayList = new DoubleArrayList(new double[0], 0);
        EMPTY_LIST = doubleArrayList;
        doubleArrayList.makeImmutable();
    }

    public DoubleArrayList() {
        this(new double[10], 0);
    }

    public static DoubleArrayList emptyList() {
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

    public boolean addAll(Collection<? extends Double> collection) {
        ensureIsMutable();
        Internal.checkNotNull(collection);
        if (!(collection instanceof DoubleArrayList)) {
            return super.addAll(collection);
        }
        DoubleArrayList doubleArrayList = (DoubleArrayList) collection;
        int i3 = doubleArrayList.size;
        if (i3 == 0) {
            return false;
        }
        int i4 = this.size;
        if (Integer.MAX_VALUE - i4 >= i3) {
            int i5 = i4 + i3;
            double[] dArr = this.array;
            if (i5 > dArr.length) {
                this.array = Arrays.copyOf(dArr, i5);
            }
            System.arraycopy(doubleArrayList.array, 0, this.array, this.size, doubleArrayList.size);
            this.size = i5;
            this.modCount++;
            return true;
        }
        throw new OutOfMemoryError();
    }

    public void addDouble(double d2) {
        ensureIsMutable();
        int i3 = this.size;
        double[] dArr = this.array;
        if (i3 == dArr.length) {
            double[] dArr2 = new double[b.b(i3, 3, 2, 1)];
            System.arraycopy(dArr, 0, dArr2, 0, i3);
            this.array = dArr2;
        }
        double[] dArr3 = this.array;
        int i4 = this.size;
        this.size = i4 + 1;
        dArr3[i4] = d2;
    }

    public boolean contains(Object obj) {
        return indexOf(obj) != -1;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof DoubleArrayList)) {
            return super.equals(obj);
        }
        DoubleArrayList doubleArrayList = (DoubleArrayList) obj;
        if (this.size != doubleArrayList.size) {
            return false;
        }
        double[] dArr = doubleArrayList.array;
        for (int i3 = 0; i3 < this.size; i3++) {
            if (Double.doubleToLongBits(this.array[i3]) != Double.doubleToLongBits(dArr[i3])) {
                return false;
            }
        }
        return true;
    }

    public double getDouble(int i3) {
        ensureIndexInRange(i3);
        return this.array[i3];
    }

    public int hashCode() {
        int i3 = 1;
        for (int i4 = 0; i4 < this.size; i4++) {
            i3 = (i3 * 31) + Internal.hashLong(Double.doubleToLongBits(this.array[i4]));
        }
        return i3;
    }

    public int indexOf(Object obj) {
        if (!(obj instanceof Double)) {
            return -1;
        }
        double doubleValue = ((Double) obj).doubleValue();
        int size2 = size();
        for (int i3 = 0; i3 < size2; i3++) {
            if (this.array[i3] == doubleValue) {
                return i3;
            }
        }
        return -1;
    }

    public void removeRange(int i3, int i4) {
        ensureIsMutable();
        if (i4 >= i3) {
            double[] dArr = this.array;
            System.arraycopy(dArr, i4, dArr, i3, this.size - i4);
            this.size -= i4 - i3;
            this.modCount++;
            return;
        }
        throw new IndexOutOfBoundsException("toIndex < fromIndex");
    }

    public double setDouble(int i3, double d2) {
        ensureIsMutable();
        ensureIndexInRange(i3);
        double[] dArr = this.array;
        double d3 = dArr[i3];
        dArr[i3] = d2;
        return d3;
    }

    public int size() {
        return this.size;
    }

    private DoubleArrayList(double[] dArr, int i3) {
        this.array = dArr;
        this.size = i3;
    }

    public Double get(int i3) {
        return Double.valueOf(getDouble(i3));
    }

    public Internal.DoubleList mutableCopyWithCapacity(int i3) {
        if (i3 >= this.size) {
            return new DoubleArrayList(Arrays.copyOf(this.array, i3), this.size);
        }
        throw new IllegalArgumentException();
    }

    public Double remove(int i3) {
        ensureIsMutable();
        ensureIndexInRange(i3);
        double[] dArr = this.array;
        double d2 = dArr[i3];
        int i4 = this.size;
        if (i3 < i4 - 1) {
            System.arraycopy(dArr, i3 + 1, dArr, i3, (i4 - i3) - 1);
        }
        this.size--;
        this.modCount++;
        return Double.valueOf(d2);
    }

    public Double set(int i3, Double d2) {
        return Double.valueOf(setDouble(i3, d2.doubleValue()));
    }

    public boolean add(Double d2) {
        addDouble(d2.doubleValue());
        return true;
    }

    public void add(int i3, Double d2) {
        addDouble(i3, d2.doubleValue());
    }

    private void addDouble(int i3, double d2) {
        int i4;
        ensureIsMutable();
        if (i3 < 0 || i3 > (i4 = this.size)) {
            throw new IndexOutOfBoundsException(makeOutOfBoundsExceptionMessage(i3));
        }
        double[] dArr = this.array;
        if (i4 < dArr.length) {
            System.arraycopy(dArr, i3, dArr, i3 + 1, i4 - i3);
        } else {
            double[] dArr2 = new double[b.b(i4, 3, 2, 1)];
            System.arraycopy(dArr, 0, dArr2, 0, i3);
            System.arraycopy(this.array, i3, dArr2, i3 + 1, this.size - i3);
            this.array = dArr2;
        }
        this.array[i3] = d2;
        this.size++;
        this.modCount++;
    }
}
