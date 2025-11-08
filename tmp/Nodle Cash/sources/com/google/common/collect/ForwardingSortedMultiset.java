package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.Multiset;
import com.google.common.collect.SortedMultisets;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NavigableSet;
import javax.annotation.CheckForNull;

@GwtCompatible(emulated = true)
@ElementTypesAreNonnullByDefault
public abstract class ForwardingSortedMultiset<E> extends ForwardingMultiset<E> implements SortedMultiset<E> {

    public abstract class StandardDescendingMultiset extends DescendingMultiset<E> {
        public StandardDescendingMultiset() {
        }

        public SortedMultiset<E> forwardMultiset() {
            return ForwardingSortedMultiset.this;
        }
    }

    public class StandardElementSet extends SortedMultisets.NavigableElementSet<E> {
        public StandardElementSet(ForwardingSortedMultiset forwardingSortedMultiset) {
            super(forwardingSortedMultiset);
        }
    }

    public Comparator<? super E> comparator() {
        return delegate().comparator();
    }

    public abstract SortedMultiset<E> delegate();

    public SortedMultiset<E> descendingMultiset() {
        return delegate().descendingMultiset();
    }

    @CheckForNull
    public Multiset.Entry<E> firstEntry() {
        return delegate().firstEntry();
    }

    public SortedMultiset<E> headMultiset(@ParametricNullness E e3, BoundType boundType) {
        return delegate().headMultiset(e3, boundType);
    }

    @CheckForNull
    public Multiset.Entry<E> lastEntry() {
        return delegate().lastEntry();
    }

    @CheckForNull
    public Multiset.Entry<E> pollFirstEntry() {
        return delegate().pollFirstEntry();
    }

    @CheckForNull
    public Multiset.Entry<E> pollLastEntry() {
        return delegate().pollLastEntry();
    }

    @CheckForNull
    public Multiset.Entry<E> standardFirstEntry() {
        Iterator it = entrySet().iterator();
        if (!it.hasNext()) {
            return null;
        }
        Multiset.Entry entry = (Multiset.Entry) it.next();
        return Multisets.immutableEntry(entry.getElement(), entry.getCount());
    }

    @CheckForNull
    public Multiset.Entry<E> standardLastEntry() {
        Iterator it = descendingMultiset().entrySet().iterator();
        if (!it.hasNext()) {
            return null;
        }
        Multiset.Entry entry = (Multiset.Entry) it.next();
        return Multisets.immutableEntry(entry.getElement(), entry.getCount());
    }

    @CheckForNull
    public Multiset.Entry<E> standardPollFirstEntry() {
        Iterator it = entrySet().iterator();
        if (!it.hasNext()) {
            return null;
        }
        Multiset.Entry entry = (Multiset.Entry) it.next();
        Multiset.Entry<E> immutableEntry = Multisets.immutableEntry(entry.getElement(), entry.getCount());
        it.remove();
        return immutableEntry;
    }

    @CheckForNull
    public Multiset.Entry<E> standardPollLastEntry() {
        Iterator it = descendingMultiset().entrySet().iterator();
        if (!it.hasNext()) {
            return null;
        }
        Multiset.Entry entry = (Multiset.Entry) it.next();
        Multiset.Entry<E> immutableEntry = Multisets.immutableEntry(entry.getElement(), entry.getCount());
        it.remove();
        return immutableEntry;
    }

    public SortedMultiset<E> standardSubMultiset(@ParametricNullness E e3, BoundType boundType, @ParametricNullness E e4, BoundType boundType2) {
        return tailMultiset(e3, boundType).headMultiset(e4, boundType2);
    }

    public SortedMultiset<E> subMultiset(@ParametricNullness E e3, BoundType boundType, @ParametricNullness E e4, BoundType boundType2) {
        return delegate().subMultiset(e3, boundType, e4, boundType2);
    }

    public SortedMultiset<E> tailMultiset(@ParametricNullness E e3, BoundType boundType) {
        return delegate().tailMultiset(e3, boundType);
    }

    public NavigableSet<E> elementSet() {
        return delegate().elementSet();
    }
}
