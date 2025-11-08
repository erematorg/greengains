package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import java.util.ListIterator;

@GwtCompatible
@ElementTypesAreNonnullByDefault
abstract class TransformedListIterator<F, T> extends TransformedIterator<F, T> implements ListIterator<T> {
    public TransformedListIterator(ListIterator<? extends F> listIterator) {
        super(listIterator);
    }

    private ListIterator<? extends F> backingIterator() {
        return Iterators.cast(this.backingIterator);
    }

    public void add(@ParametricNullness T t2) {
        throw new UnsupportedOperationException();
    }

    public final boolean hasPrevious() {
        return backingIterator().hasPrevious();
    }

    public final int nextIndex() {
        return backingIterator().nextIndex();
    }

    @ParametricNullness
    public final T previous() {
        return transform(backingIterator().previous());
    }

    public final int previousIndex() {
        return backingIterator().previousIndex();
    }

    public void set(@ParametricNullness T t2) {
        throw new UnsupportedOperationException();
    }
}
