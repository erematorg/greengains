package com.google.common.collect;

import com.google.common.annotations.GwtIncompatible;
import javax.annotation.CheckForNull;

@GwtIncompatible
@ElementTypesAreNonnullByDefault
final class DescendingImmutableSortedSet<E> extends ImmutableSortedSet<E> {
    private final ImmutableSortedSet<E> forward;

    public DescendingImmutableSortedSet(ImmutableSortedSet<E> immutableSortedSet) {
        super(Ordering.from(immutableSortedSet.comparator()).reverse());
        this.forward = immutableSortedSet;
    }

    @CheckForNull
    public E ceiling(E e3) {
        return this.forward.floor(e3);
    }

    public boolean contains(@CheckForNull Object obj) {
        return this.forward.contains(obj);
    }

    @GwtIncompatible("NavigableSet")
    public ImmutableSortedSet<E> createDescendingSet() {
        throw new AssertionError("should never be called");
    }

    @CheckForNull
    public E floor(E e3) {
        return this.forward.ceiling(e3);
    }

    public ImmutableSortedSet<E> headSetImpl(E e3, boolean z2) {
        return this.forward.tailSet(e3, z2).descendingSet();
    }

    @CheckForNull
    public E higher(E e3) {
        return this.forward.lower(e3);
    }

    public int indexOf(@CheckForNull Object obj) {
        int indexOf = this.forward.indexOf(obj);
        return indexOf == -1 ? indexOf : (size() - 1) - indexOf;
    }

    public boolean isPartialView() {
        return this.forward.isPartialView();
    }

    @CheckForNull
    public E lower(E e3) {
        return this.forward.higher(e3);
    }

    public int size() {
        return this.forward.size();
    }

    public ImmutableSortedSet<E> subSetImpl(E e3, boolean z2, E e4, boolean z3) {
        return this.forward.subSet(e4, z3, e3, z2).descendingSet();
    }

    public ImmutableSortedSet<E> tailSetImpl(E e3, boolean z2) {
        return this.forward.headSet(e3, z2).descendingSet();
    }

    @GwtIncompatible("NavigableSet")
    public UnmodifiableIterator<E> descendingIterator() {
        return this.forward.iterator();
    }

    @GwtIncompatible("NavigableSet")
    public ImmutableSortedSet<E> descendingSet() {
        return this.forward;
    }

    public UnmodifiableIterator<E> iterator() {
        return this.forward.descendingIterator();
    }
}
