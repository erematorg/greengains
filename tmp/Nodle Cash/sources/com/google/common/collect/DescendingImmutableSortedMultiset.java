package com.google.common.collect;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.collect.Multiset;
import javax.annotation.CheckForNull;

@GwtIncompatible
@ElementTypesAreNonnullByDefault
final class DescendingImmutableSortedMultiset<E> extends ImmutableSortedMultiset<E> {
    private final transient ImmutableSortedMultiset<E> forward;

    public DescendingImmutableSortedMultiset(ImmutableSortedMultiset<E> immutableSortedMultiset) {
        this.forward = immutableSortedMultiset;
    }

    public int count(@CheckForNull Object obj) {
        return this.forward.count(obj);
    }

    @CheckForNull
    public Multiset.Entry<E> firstEntry() {
        return this.forward.lastEntry();
    }

    public Multiset.Entry<E> getEntry(int i3) {
        return this.forward.entrySet().asList().reverse().get(i3);
    }

    public boolean isPartialView() {
        return this.forward.isPartialView();
    }

    @CheckForNull
    public Multiset.Entry<E> lastEntry() {
        return this.forward.firstEntry();
    }

    public int size() {
        return this.forward.size();
    }

    public ImmutableSortedMultiset<E> descendingMultiset() {
        return this.forward;
    }

    public ImmutableSortedMultiset<E> headMultiset(E e3, BoundType boundType) {
        return this.forward.tailMultiset(e3, boundType).descendingMultiset();
    }

    public ImmutableSortedMultiset<E> tailMultiset(E e3, BoundType boundType) {
        return this.forward.headMultiset(e3, boundType).descendingMultiset();
    }

    public ImmutableSortedSet<E> elementSet() {
        return this.forward.elementSet().descendingSet();
    }
}
