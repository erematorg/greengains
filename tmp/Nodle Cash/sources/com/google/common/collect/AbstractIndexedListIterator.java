package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import java.util.NoSuchElementException;

@GwtCompatible
@ElementTypesAreNonnullByDefault
abstract class AbstractIndexedListIterator<E> extends UnmodifiableListIterator<E> {
    private int position;
    private final int size;

    public AbstractIndexedListIterator(int i3) {
        this(i3, 0);
    }

    @ParametricNullness
    public abstract E get(int i3);

    public final boolean hasNext() {
        return this.position < this.size;
    }

    public final boolean hasPrevious() {
        return this.position > 0;
    }

    @ParametricNullness
    public final E next() {
        if (hasNext()) {
            int i3 = this.position;
            this.position = i3 + 1;
            return get(i3);
        }
        throw new NoSuchElementException();
    }

    public final int nextIndex() {
        return this.position;
    }

    @ParametricNullness
    public final E previous() {
        if (hasPrevious()) {
            int i3 = this.position - 1;
            this.position = i3;
            return get(i3);
        }
        throw new NoSuchElementException();
    }

    public final int previousIndex() {
        return this.position - 1;
    }

    public AbstractIndexedListIterator(int i3, int i4) {
        Preconditions.checkPositionIndex(i4, i3);
        this.size = i3;
        this.position = i4;
    }
}
