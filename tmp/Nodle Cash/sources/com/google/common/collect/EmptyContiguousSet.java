package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.lang.Comparable;
import java.util.NoSuchElementException;
import java.util.Set;
import javax.annotation.CheckForNull;
import okhttp3.internal.url._UrlKt;

@GwtCompatible(emulated = true)
@ElementTypesAreNonnullByDefault
final class EmptyContiguousSet<C extends Comparable> extends ContiguousSet<C> {

    @GwtIncompatible
    @J2ktIncompatible
    public static final class SerializedForm<C extends Comparable> implements Serializable {
        private static final long serialVersionUID = 0;
        private final DiscreteDomain<C> domain;

        private Object readResolve() {
            return new EmptyContiguousSet(this.domain);
        }

        private SerializedForm(DiscreteDomain<C> discreteDomain) {
            this.domain = discreteDomain;
        }
    }

    public EmptyContiguousSet(DiscreteDomain<C> discreteDomain) {
        super(discreteDomain);
    }

    @GwtIncompatible
    @J2ktIncompatible
    private void readObject(ObjectInputStream objectInputStream) throws InvalidObjectException {
        throw new InvalidObjectException("Use SerializedForm");
    }

    public ImmutableList<C> asList() {
        return ImmutableList.of();
    }

    public boolean contains(@CheckForNull Object obj) {
        return false;
    }

    @GwtIncompatible
    public ImmutableSortedSet<C> createDescendingSet() {
        return ImmutableSortedSet.emptySet(Ordering.natural().reverse());
    }

    public boolean equals(@CheckForNull Object obj) {
        if (obj instanceof Set) {
            return ((Set) obj).isEmpty();
        }
        return false;
    }

    public int hashCode() {
        return 0;
    }

    public ContiguousSet<C> headSetImpl(C c3, boolean z2) {
        return this;
    }

    @GwtIncompatible
    public int indexOf(@CheckForNull Object obj) {
        return -1;
    }

    public ContiguousSet<C> intersection(ContiguousSet<C> contiguousSet) {
        return this;
    }

    public boolean isEmpty() {
        return true;
    }

    @GwtIncompatible
    public boolean isHashCodeFast() {
        return true;
    }

    public boolean isPartialView() {
        return false;
    }

    public Range<C> range() {
        throw new NoSuchElementException();
    }

    public int size() {
        return 0;
    }

    public ContiguousSet<C> subSetImpl(C c3, boolean z2, C c4, boolean z3) {
        return this;
    }

    public ContiguousSet<C> tailSetImpl(C c3, boolean z2) {
        return this;
    }

    public String toString() {
        return _UrlKt.PATH_SEGMENT_ENCODE_SET_URI;
    }

    @GwtIncompatible
    @J2ktIncompatible
    public Object writeReplace() {
        return new SerializedForm(this.domain);
    }

    @GwtIncompatible
    public UnmodifiableIterator<C> descendingIterator() {
        return Iterators.emptyIterator();
    }

    public C first() {
        throw new NoSuchElementException();
    }

    public UnmodifiableIterator<C> iterator() {
        return Iterators.emptyIterator();
    }

    public C last() {
        throw new NoSuchElementException();
    }

    public Range<C> range(BoundType boundType, BoundType boundType2) {
        throw new NoSuchElementException();
    }
}
