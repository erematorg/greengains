package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.Multiset;
import com.google.common.collect.Multisets;
import com.google.common.collect.SortedMultisets;
import com.google.errorprone.annotations.concurrent.LazyInit;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NavigableSet;
import java.util.Set;
import javax.annotation.CheckForNull;

@GwtCompatible(emulated = true)
@ElementTypesAreNonnullByDefault
abstract class DescendingMultiset<E> extends ForwardingMultiset<E> implements SortedMultiset<E> {
    @CheckForNull
    @LazyInit
    private transient Comparator<? super E> comparator;
    @CheckForNull
    @LazyInit
    private transient NavigableSet<E> elementSet;
    @CheckForNull
    @LazyInit
    private transient Set<Multiset.Entry<E>> entrySet;

    public Comparator<? super E> comparator() {
        Comparator<? super E> comparator2 = this.comparator;
        if (comparator2 != null) {
            return comparator2;
        }
        Ordering reverse = Ordering.from(forwardMultiset().comparator()).reverse();
        this.comparator = reverse;
        return reverse;
    }

    public Set<Multiset.Entry<E>> createEntrySet() {
        return new Multisets.EntrySet<E>() {
            public Iterator<Multiset.Entry<E>> iterator() {
                return DescendingMultiset.this.entryIterator();
            }

            public Multiset<E> multiset() {
                return DescendingMultiset.this;
            }

            public int size() {
                return DescendingMultiset.this.forwardMultiset().entrySet().size();
            }
        };
    }

    public SortedMultiset<E> descendingMultiset() {
        return forwardMultiset();
    }

    public abstract Iterator<Multiset.Entry<E>> entryIterator();

    public Set<Multiset.Entry<E>> entrySet() {
        Set<Multiset.Entry<E>> set = this.entrySet;
        if (set != null) {
            return set;
        }
        Set<Multiset.Entry<E>> createEntrySet = createEntrySet();
        this.entrySet = createEntrySet;
        return createEntrySet;
    }

    @CheckForNull
    public Multiset.Entry<E> firstEntry() {
        return forwardMultiset().lastEntry();
    }

    public abstract SortedMultiset<E> forwardMultiset();

    public SortedMultiset<E> headMultiset(@ParametricNullness E e3, BoundType boundType) {
        return forwardMultiset().tailMultiset(e3, boundType).descendingMultiset();
    }

    public Iterator<E> iterator() {
        return Multisets.iteratorImpl(this);
    }

    @CheckForNull
    public Multiset.Entry<E> lastEntry() {
        return forwardMultiset().firstEntry();
    }

    @CheckForNull
    public Multiset.Entry<E> pollFirstEntry() {
        return forwardMultiset().pollLastEntry();
    }

    @CheckForNull
    public Multiset.Entry<E> pollLastEntry() {
        return forwardMultiset().pollFirstEntry();
    }

    public SortedMultiset<E> subMultiset(@ParametricNullness E e3, BoundType boundType, @ParametricNullness E e4, BoundType boundType2) {
        return forwardMultiset().subMultiset(e4, boundType2, e3, boundType).descendingMultiset();
    }

    public SortedMultiset<E> tailMultiset(@ParametricNullness E e3, BoundType boundType) {
        return forwardMultiset().headMultiset(e3, boundType).descendingMultiset();
    }

    public Object[] toArray() {
        return standardToArray();
    }

    public String toString() {
        return entrySet().toString();
    }

    public <T> T[] toArray(T[] tArr) {
        return standardToArray(tArr);
    }

    public Multiset<E> delegate() {
        return forwardMultiset();
    }

    public NavigableSet<E> elementSet() {
        NavigableSet<E> navigableSet = this.elementSet;
        if (navigableSet != null) {
            return navigableSet;
        }
        SortedMultisets.NavigableElementSet navigableElementSet = new SortedMultisets.NavigableElementSet(this);
        this.elementSet = navigableElementSet;
        return navigableElementSet;
    }
}
