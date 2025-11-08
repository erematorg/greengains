package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.common.collect.Multiset;
import com.google.common.collect.SortedMultisets;
import com.google.errorprone.annotations.concurrent.LazyInit;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NavigableSet;
import javax.annotation.CheckForNull;

@GwtCompatible(emulated = true)
@ElementTypesAreNonnullByDefault
abstract class AbstractSortedMultiset<E> extends AbstractMultiset<E> implements SortedMultiset<E> {
    @GwtTransient
    final Comparator<? super E> comparator;
    @CheckForNull
    @LazyInit
    private transient SortedMultiset<E> descendingMultiset;

    public AbstractSortedMultiset() {
        this(Ordering.natural());
    }

    public Comparator<? super E> comparator() {
        return this.comparator;
    }

    public SortedMultiset<E> createDescendingMultiset() {
        return new DescendingMultiset<E>() {
            public Iterator<Multiset.Entry<E>> entryIterator() {
                return AbstractSortedMultiset.this.descendingEntryIterator();
            }

            public SortedMultiset<E> forwardMultiset() {
                return AbstractSortedMultiset.this;
            }

            public Iterator<E> iterator() {
                return AbstractSortedMultiset.this.descendingIterator();
            }
        };
    }

    public abstract Iterator<Multiset.Entry<E>> descendingEntryIterator();

    public Iterator<E> descendingIterator() {
        return Multisets.iteratorImpl(descendingMultiset());
    }

    public SortedMultiset<E> descendingMultiset() {
        SortedMultiset<E> sortedMultiset = this.descendingMultiset;
        if (sortedMultiset != null) {
            return sortedMultiset;
        }
        SortedMultiset<E> createDescendingMultiset = createDescendingMultiset();
        this.descendingMultiset = createDescendingMultiset;
        return createDescendingMultiset;
    }

    @CheckForNull
    public Multiset.Entry<E> firstEntry() {
        Iterator entryIterator = entryIterator();
        if (entryIterator.hasNext()) {
            return (Multiset.Entry) entryIterator.next();
        }
        return null;
    }

    @CheckForNull
    public Multiset.Entry<E> lastEntry() {
        Iterator descendingEntryIterator = descendingEntryIterator();
        if (descendingEntryIterator.hasNext()) {
            return (Multiset.Entry) descendingEntryIterator.next();
        }
        return null;
    }

    @CheckForNull
    public Multiset.Entry<E> pollFirstEntry() {
        Iterator entryIterator = entryIterator();
        if (!entryIterator.hasNext()) {
            return null;
        }
        Multiset.Entry entry = (Multiset.Entry) entryIterator.next();
        Multiset.Entry<E> immutableEntry = Multisets.immutableEntry(entry.getElement(), entry.getCount());
        entryIterator.remove();
        return immutableEntry;
    }

    @CheckForNull
    public Multiset.Entry<E> pollLastEntry() {
        Iterator descendingEntryIterator = descendingEntryIterator();
        if (!descendingEntryIterator.hasNext()) {
            return null;
        }
        Multiset.Entry entry = (Multiset.Entry) descendingEntryIterator.next();
        Multiset.Entry<E> immutableEntry = Multisets.immutableEntry(entry.getElement(), entry.getCount());
        descendingEntryIterator.remove();
        return immutableEntry;
    }

    public SortedMultiset<E> subMultiset(@ParametricNullness E e3, BoundType boundType, @ParametricNullness E e4, BoundType boundType2) {
        Preconditions.checkNotNull(boundType);
        Preconditions.checkNotNull(boundType2);
        return tailMultiset(e3, boundType).headMultiset(e4, boundType2);
    }

    public AbstractSortedMultiset(Comparator<? super E> comparator2) {
        this.comparator = (Comparator) Preconditions.checkNotNull(comparator2);
    }

    public NavigableSet<E> createElementSet() {
        return new SortedMultisets.NavigableElementSet(this);
    }

    public NavigableSet<E> elementSet() {
        return (NavigableSet) super.elementSet();
    }
}
