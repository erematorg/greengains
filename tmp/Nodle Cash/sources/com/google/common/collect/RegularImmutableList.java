package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import java.util.Objects;

@GwtCompatible(emulated = true, serializable = true)
@ElementTypesAreNonnullByDefault
class RegularImmutableList<E> extends ImmutableList<E> {
    static final ImmutableList<Object> EMPTY = new RegularImmutableList(new Object[0], 0);
    @VisibleForTesting
    final transient Object[] array;
    private final transient int size;

    public RegularImmutableList(Object[] objArr, int i3) {
        this.array = objArr;
        this.size = i3;
    }

    public int copyIntoArray(Object[] objArr, int i3) {
        System.arraycopy(this.array, 0, objArr, i3, this.size);
        return i3 + this.size;
    }

    public E get(int i3) {
        Preconditions.checkElementIndex(i3, this.size);
        E e3 = this.array[i3];
        Objects.requireNonNull(e3);
        return e3;
    }

    public Object[] internalArray() {
        return this.array;
    }

    public int internalArrayEnd() {
        return this.size;
    }

    public int internalArrayStart() {
        return 0;
    }

    public boolean isPartialView() {
        return false;
    }

    public int size() {
        return this.size;
    }
}
