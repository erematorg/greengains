package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;

@GwtCompatible(emulated = true)
@ElementTypesAreNonnullByDefault
abstract class IndexedImmutableSet<E> extends ImmutableSet<E> {
    @GwtIncompatible
    public int copyIntoArray(Object[] objArr, int i3) {
        return asList().copyIntoArray(objArr, i3);
    }

    public ImmutableList<E> createAsList() {
        return new ImmutableList<E>() {
            public E get(int i3) {
                return IndexedImmutableSet.this.get(i3);
            }

            public boolean isPartialView() {
                return IndexedImmutableSet.this.isPartialView();
            }

            public int size() {
                return IndexedImmutableSet.this.size();
            }
        };
    }

    public abstract E get(int i3);

    public UnmodifiableIterator<E> iterator() {
        return asList().iterator();
    }
}
