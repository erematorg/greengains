package com.google.protobuf;

import A.a;
import androidx.constraintlayout.core.state.b;
import java.util.Arrays;
import java.util.RandomAccess;

final class ProtobufArrayList<E> extends AbstractProtobufList<E> implements RandomAccess {
    private static final ProtobufArrayList<Object> EMPTY_LIST = new ProtobufArrayList<>(new Object[0], 0, false);
    private E[] array;
    private int size;

    public ProtobufArrayList() {
        this(new Object[10], 0, true);
    }

    private static <E> E[] createArray(int i3) {
        return new Object[i3];
    }

    public static <E> ProtobufArrayList<E> emptyList() {
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

    public boolean add(E e3) {
        ensureIsMutable();
        int i3 = this.size;
        E[] eArr = this.array;
        if (i3 == eArr.length) {
            this.array = Arrays.copyOf(eArr, ((i3 * 3) / 2) + 1);
        }
        E[] eArr2 = this.array;
        int i4 = this.size;
        this.size = i4 + 1;
        eArr2[i4] = e3;
        this.modCount++;
        return true;
    }

    public E get(int i3) {
        ensureIndexInRange(i3);
        return this.array[i3];
    }

    public E remove(int i3) {
        ensureIsMutable();
        ensureIndexInRange(i3);
        E[] eArr = this.array;
        E e3 = eArr[i3];
        int i4 = this.size;
        if (i3 < i4 - 1) {
            System.arraycopy(eArr, i3 + 1, eArr, i3, (i4 - i3) - 1);
        }
        this.size--;
        this.modCount++;
        return e3;
    }

    public E set(int i3, E e3) {
        ensureIsMutable();
        ensureIndexInRange(i3);
        E[] eArr = this.array;
        E e4 = eArr[i3];
        eArr[i3] = e3;
        this.modCount++;
        return e4;
    }

    public int size() {
        return this.size;
    }

    private ProtobufArrayList(E[] eArr, int i3, boolean z2) {
        super(z2);
        this.array = eArr;
        this.size = i3;
    }

    public ProtobufArrayList<E> mutableCopyWithCapacity(int i3) {
        if (i3 >= this.size) {
            return new ProtobufArrayList<>(Arrays.copyOf(this.array, i3), this.size, true);
        }
        throw new IllegalArgumentException();
    }

    public void add(int i3, E e3) {
        int i4;
        ensureIsMutable();
        if (i3 < 0 || i3 > (i4 = this.size)) {
            throw new IndexOutOfBoundsException(makeOutOfBoundsExceptionMessage(i3));
        }
        E[] eArr = this.array;
        if (i4 < eArr.length) {
            System.arraycopy(eArr, i3, eArr, i3 + 1, i4 - i3);
        } else {
            E[] createArray = createArray(b.b(i4, 3, 2, 1));
            System.arraycopy(this.array, 0, createArray, 0, i3);
            System.arraycopy(this.array, i3, createArray, i3 + 1, this.size - i3);
            this.array = createArray;
        }
        this.array[i3] = e3;
        this.size++;
        this.modCount++;
    }
}
