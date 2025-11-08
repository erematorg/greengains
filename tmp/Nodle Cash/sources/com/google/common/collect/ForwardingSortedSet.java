package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.SortedSet;
import javax.annotation.CheckForNull;

@GwtCompatible
@ElementTypesAreNonnullByDefault
public abstract class ForwardingSortedSet<E> extends ForwardingSet<E> implements SortedSet<E> {
    @CheckForNull
    public Comparator<? super E> comparator() {
        return delegate().comparator();
    }

    public abstract SortedSet<E> delegate();

    @ParametricNullness
    public E first() {
        return delegate().first();
    }

    public SortedSet<E> headSet(@ParametricNullness E e3) {
        return delegate().headSet(e3);
    }

    @ParametricNullness
    public E last() {
        return delegate().last();
    }

    public boolean standardContains(@CheckForNull Object obj) {
        try {
            return ForwardingSortedMap.unsafeCompare(comparator(), tailSet(obj).first(), obj) == 0;
        } catch (ClassCastException | NullPointerException | NoSuchElementException unused) {
            return false;
        }
    }

    public boolean standardRemove(@CheckForNull Object obj) {
        try {
            Iterator it = tailSet(obj).iterator();
            if (it.hasNext()) {
                if (ForwardingSortedMap.unsafeCompare(comparator(), it.next(), obj) == 0) {
                    it.remove();
                    return true;
                }
            }
        } catch (ClassCastException | NullPointerException unused) {
        }
        return false;
    }

    public SortedSet<E> standardSubSet(@ParametricNullness E e3, @ParametricNullness E e4) {
        return tailSet(e3).headSet(e4);
    }

    public SortedSet<E> subSet(@ParametricNullness E e3, @ParametricNullness E e4) {
        return delegate().subSet(e3, e4);
    }

    public SortedSet<E> tailSet(@ParametricNullness E e3) {
        return delegate().tailSet(e3);
    }
}
