package com.google.common.collect;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.collect.Sets;
import java.util.Iterator;
import java.util.NavigableSet;
import java.util.SortedSet;
import javax.annotation.CheckForNull;

@GwtIncompatible
@ElementTypesAreNonnullByDefault
public abstract class ForwardingNavigableSet<E> extends ForwardingSortedSet<E> implements NavigableSet<E> {

    public class StandardDescendingSet extends Sets.DescendingSet<E> {
        public StandardDescendingSet(ForwardingNavigableSet forwardingNavigableSet) {
            super(forwardingNavigableSet);
        }
    }

    @CheckForNull
    public E ceiling(@ParametricNullness E e3) {
        return delegate().ceiling(e3);
    }

    public abstract NavigableSet<E> delegate();

    public Iterator<E> descendingIterator() {
        return delegate().descendingIterator();
    }

    public NavigableSet<E> descendingSet() {
        return delegate().descendingSet();
    }

    @CheckForNull
    public E floor(@ParametricNullness E e3) {
        return delegate().floor(e3);
    }

    public NavigableSet<E> headSet(@ParametricNullness E e3, boolean z2) {
        return delegate().headSet(e3, z2);
    }

    @CheckForNull
    public E higher(@ParametricNullness E e3) {
        return delegate().higher(e3);
    }

    @CheckForNull
    public E lower(@ParametricNullness E e3) {
        return delegate().lower(e3);
    }

    @CheckForNull
    public E pollFirst() {
        return delegate().pollFirst();
    }

    @CheckForNull
    public E pollLast() {
        return delegate().pollLast();
    }

    @CheckForNull
    public E standardCeiling(@ParametricNullness E e3) {
        return Iterators.getNext(tailSet(e3, true).iterator(), null);
    }

    @ParametricNullness
    public E standardFirst() {
        return iterator().next();
    }

    @CheckForNull
    public E standardFloor(@ParametricNullness E e3) {
        return Iterators.getNext(headSet(e3, true).descendingIterator(), null);
    }

    public SortedSet<E> standardHeadSet(@ParametricNullness E e3) {
        return headSet(e3, false);
    }

    @CheckForNull
    public E standardHigher(@ParametricNullness E e3) {
        return Iterators.getNext(tailSet(e3, false).iterator(), null);
    }

    @ParametricNullness
    public E standardLast() {
        return descendingIterator().next();
    }

    @CheckForNull
    public E standardLower(@ParametricNullness E e3) {
        return Iterators.getNext(headSet(e3, false).descendingIterator(), null);
    }

    @CheckForNull
    public E standardPollFirst() {
        return Iterators.pollNext(iterator());
    }

    @CheckForNull
    public E standardPollLast() {
        return Iterators.pollNext(descendingIterator());
    }

    public NavigableSet<E> standardSubSet(@ParametricNullness E e3, boolean z2, @ParametricNullness E e4, boolean z3) {
        return tailSet(e3, z2).headSet(e4, z3);
    }

    public SortedSet<E> standardTailSet(@ParametricNullness E e3) {
        return tailSet(e3, true);
    }

    public NavigableSet<E> subSet(@ParametricNullness E e3, boolean z2, @ParametricNullness E e4, boolean z3) {
        return delegate().subSet(e3, z2, e4, z3);
    }

    public NavigableSet<E> tailSet(@ParametricNullness E e3, boolean z2) {
        return delegate().tailSet(e3, z2);
    }

    public SortedSet<E> standardSubSet(@ParametricNullness E e3, @ParametricNullness E e4) {
        return subSet(e3, true, e4, false);
    }
}
