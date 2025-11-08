package com.google.protobuf;

import A.a;
import androidx.constraintlayout.core.state.b;
import com.google.protobuf.Internal;
import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

final class FloatArrayList extends AbstractProtobufList<Float> implements Internal.FloatList, RandomAccess, PrimitiveNonBoxingCollection {
    private static final FloatArrayList EMPTY_LIST = new FloatArrayList(new float[0], 0, false);
    private float[] array;
    private int size;

    public FloatArrayList() {
        this(new float[10], 0, true);
    }

    public static FloatArrayList emptyList() {
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

    public boolean addAll(Collection<? extends Float> collection) {
        ensureIsMutable();
        Internal.checkNotNull(collection);
        if (!(collection instanceof FloatArrayList)) {
            return super.addAll(collection);
        }
        FloatArrayList floatArrayList = (FloatArrayList) collection;
        int i3 = floatArrayList.size;
        if (i3 == 0) {
            return false;
        }
        int i4 = this.size;
        if (Integer.MAX_VALUE - i4 >= i3) {
            int i5 = i4 + i3;
            float[] fArr = this.array;
            if (i5 > fArr.length) {
                this.array = Arrays.copyOf(fArr, i5);
            }
            System.arraycopy(floatArrayList.array, 0, this.array, this.size, floatArrayList.size);
            this.size = i5;
            this.modCount++;
            return true;
        }
        throw new OutOfMemoryError();
    }

    public void addFloat(float f2) {
        ensureIsMutable();
        int i3 = this.size;
        float[] fArr = this.array;
        if (i3 == fArr.length) {
            float[] fArr2 = new float[b.b(i3, 3, 2, 1)];
            System.arraycopy(fArr, 0, fArr2, 0, i3);
            this.array = fArr2;
        }
        float[] fArr3 = this.array;
        int i4 = this.size;
        this.size = i4 + 1;
        fArr3[i4] = f2;
    }

    public boolean contains(Object obj) {
        return indexOf(obj) != -1;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof FloatArrayList)) {
            return super.equals(obj);
        }
        FloatArrayList floatArrayList = (FloatArrayList) obj;
        if (this.size != floatArrayList.size) {
            return false;
        }
        float[] fArr = floatArrayList.array;
        for (int i3 = 0; i3 < this.size; i3++) {
            if (Float.floatToIntBits(this.array[i3]) != Float.floatToIntBits(fArr[i3])) {
                return false;
            }
        }
        return true;
    }

    public float getFloat(int i3) {
        ensureIndexInRange(i3);
        return this.array[i3];
    }

    public int hashCode() {
        int i3 = 1;
        for (int i4 = 0; i4 < this.size; i4++) {
            i3 = (i3 * 31) + Float.floatToIntBits(this.array[i4]);
        }
        return i3;
    }

    public int indexOf(Object obj) {
        if (!(obj instanceof Float)) {
            return -1;
        }
        float floatValue = ((Float) obj).floatValue();
        int size2 = size();
        for (int i3 = 0; i3 < size2; i3++) {
            if (this.array[i3] == floatValue) {
                return i3;
            }
        }
        return -1;
    }

    public void removeRange(int i3, int i4) {
        ensureIsMutable();
        if (i4 >= i3) {
            float[] fArr = this.array;
            System.arraycopy(fArr, i4, fArr, i3, this.size - i4);
            this.size -= i4 - i3;
            this.modCount++;
            return;
        }
        throw new IndexOutOfBoundsException("toIndex < fromIndex");
    }

    public float setFloat(int i3, float f2) {
        ensureIsMutable();
        ensureIndexInRange(i3);
        float[] fArr = this.array;
        float f3 = fArr[i3];
        fArr[i3] = f2;
        return f3;
    }

    public int size() {
        return this.size;
    }

    private FloatArrayList(float[] fArr, int i3, boolean z2) {
        super(z2);
        this.array = fArr;
        this.size = i3;
    }

    public Float get(int i3) {
        return Float.valueOf(getFloat(i3));
    }

    public Internal.FloatList mutableCopyWithCapacity(int i3) {
        if (i3 >= this.size) {
            return new FloatArrayList(Arrays.copyOf(this.array, i3), this.size, true);
        }
        throw new IllegalArgumentException();
    }

    public Float remove(int i3) {
        ensureIsMutable();
        ensureIndexInRange(i3);
        float[] fArr = this.array;
        float f2 = fArr[i3];
        int i4 = this.size;
        if (i3 < i4 - 1) {
            System.arraycopy(fArr, i3 + 1, fArr, i3, (i4 - i3) - 1);
        }
        this.size--;
        this.modCount++;
        return Float.valueOf(f2);
    }

    public Float set(int i3, Float f2) {
        return Float.valueOf(setFloat(i3, f2.floatValue()));
    }

    public boolean add(Float f2) {
        addFloat(f2.floatValue());
        return true;
    }

    public void add(int i3, Float f2) {
        addFloat(i3, f2.floatValue());
    }

    private void addFloat(int i3, float f2) {
        int i4;
        ensureIsMutable();
        if (i3 < 0 || i3 > (i4 = this.size)) {
            throw new IndexOutOfBoundsException(makeOutOfBoundsExceptionMessage(i3));
        }
        float[] fArr = this.array;
        if (i4 < fArr.length) {
            System.arraycopy(fArr, i3, fArr, i3 + 1, i4 - i3);
        } else {
            float[] fArr2 = new float[b.b(i4, 3, 2, 1)];
            System.arraycopy(fArr, 0, fArr2, 0, i3);
            System.arraycopy(this.array, i3, fArr2, i3 + 1, this.size - i3);
            this.array = fArr2;
        }
        this.array[i3] = f2;
        this.size++;
        this.modCount++;
    }
}
