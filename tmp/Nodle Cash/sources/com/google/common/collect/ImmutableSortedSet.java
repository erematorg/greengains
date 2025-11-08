package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableSet;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.DoNotCall;
import com.google.errorprone.annotations.concurrent.LazyInit;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NavigableSet;
import java.util.SortedSet;
import javax.annotation.CheckForNull;

@GwtCompatible(emulated = true, serializable = true)
@ElementTypesAreNonnullByDefault
public abstract class ImmutableSortedSet<E> extends ImmutableSortedSetFauxverideShim<E> implements NavigableSet<E>, SortedIterable<E> {
    final transient Comparator<? super E> comparator;
    @GwtIncompatible
    @CheckForNull
    @LazyInit
    transient ImmutableSortedSet<E> descendingSet;

    public static final class Builder<E> extends ImmutableSet.Builder<E> {
        private final Comparator<? super E> comparator;

        public Builder(Comparator<? super E> comparator2) {
            this.comparator = (Comparator) Preconditions.checkNotNull(comparator2);
        }

        @CanIgnoreReturnValue
        public Builder<E> combine(ImmutableSet.Builder<E> builder) {
            super.combine(builder);
            return this;
        }

        public ImmutableSortedSet<E> build() {
            ImmutableSortedSet<E> construct = ImmutableSortedSet.construct(this.comparator, this.size, this.contents);
            this.size = construct.size();
            this.forceCopy = true;
            return construct;
        }

        @CanIgnoreReturnValue
        public Builder<E> addAll(Iterable<? extends E> iterable) {
            super.addAll((Iterable) iterable);
            return this;
        }

        @CanIgnoreReturnValue
        public Builder<E> add(E e3) {
            super.add((Object) e3);
            return this;
        }

        @CanIgnoreReturnValue
        public Builder<E> addAll(Iterator<? extends E> it) {
            super.addAll((Iterator) it);
            return this;
        }

        @CanIgnoreReturnValue
        public Builder<E> add(E... eArr) {
            super.add((Object[]) eArr);
            return this;
        }
    }

    @J2ktIncompatible
    public static class SerializedForm<E> implements Serializable {
        private static final long serialVersionUID = 0;
        final Comparator<? super E> comparator;
        final Object[] elements;

        public SerializedForm(Comparator<? super E> comparator2, Object[] objArr) {
            this.comparator = comparator2;
            this.elements = objArr;
        }

        public Object readResolve() {
            return new Builder(this.comparator).add(this.elements).build();
        }
    }

    public ImmutableSortedSet(Comparator<? super E> comparator2) {
        this.comparator = comparator2;
    }

    public static <E> ImmutableSortedSet<E> construct(Comparator<? super E> comparator2, int i3, E... eArr) {
        if (i3 == 0) {
            return emptySet(comparator2);
        }
        ObjectArrays.checkElementsNotNull(eArr, i3);
        Arrays.sort(eArr, 0, i3, comparator2);
        int i4 = 1;
        for (int i5 = 1; i5 < i3; i5++) {
            E e3 = eArr[i5];
            if (comparator2.compare(e3, eArr[i4 - 1]) != 0) {
                eArr[i4] = e3;
                i4++;
            }
        }
        Arrays.fill(eArr, i4, i3, (Object) null);
        if (i4 < eArr.length / 2) {
            eArr = Arrays.copyOf(eArr, i4);
        }
        return new RegularImmutableSortedSet(ImmutableList.asImmutableList(eArr, i4), comparator2);
    }

    public static <E extends Comparable<? super E>> ImmutableSortedSet<E> copyOf(E[] eArr) {
        return construct(Ordering.natural(), eArr.length, (Comparable[]) eArr.clone());
    }

    public static <E> ImmutableSortedSet<E> copyOfSorted(SortedSet<E> sortedSet) {
        Comparator<? super E> comparator2 = SortedIterables.comparator(sortedSet);
        ImmutableList<E> copyOf = ImmutableList.copyOf(sortedSet);
        return copyOf.isEmpty() ? emptySet(comparator2) : new RegularImmutableSortedSet(copyOf, comparator2);
    }

    public static <E> RegularImmutableSortedSet<E> emptySet(Comparator<? super E> comparator2) {
        return Ordering.natural().equals(comparator2) ? RegularImmutableSortedSet.NATURAL_EMPTY_SET : new RegularImmutableSortedSet<>(ImmutableList.of(), comparator2);
    }

    public static <E extends Comparable<?>> Builder<E> naturalOrder() {
        return new Builder<>(Ordering.natural());
    }

    public static <E> ImmutableSortedSet<E> of() {
        return RegularImmutableSortedSet.NATURAL_EMPTY_SET;
    }

    public static <E> Builder<E> orderedBy(Comparator<E> comparator2) {
        return new Builder<>(comparator2);
    }

    @J2ktIncompatible
    private void readObject(ObjectInputStream objectInputStream) throws InvalidObjectException {
        throw new InvalidObjectException("Use SerializedForm");
    }

    public static <E extends Comparable<?>> Builder<E> reverseOrder() {
        return new Builder<>(Collections.reverseOrder());
    }

    @CheckForNull
    public E ceiling(E e3) {
        return Iterables.getFirst(tailSet(e3, true), null);
    }

    public Comparator<? super E> comparator() {
        return this.comparator;
    }

    @GwtIncompatible
    public abstract ImmutableSortedSet<E> createDescendingSet();

    @GwtIncompatible
    public abstract UnmodifiableIterator<E> descendingIterator();

    public E first() {
        return iterator().next();
    }

    @CheckForNull
    public E floor(E e3) {
        return Iterators.getNext(headSet(e3, true).descendingIterator(), null);
    }

    public abstract ImmutableSortedSet<E> headSetImpl(E e3, boolean z2);

    @CheckForNull
    @GwtIncompatible
    public E higher(E e3) {
        return Iterables.getFirst(tailSet(e3, false), null);
    }

    public abstract int indexOf(@CheckForNull Object obj);

    public abstract UnmodifiableIterator<E> iterator();

    public E last() {
        return descendingIterator().next();
    }

    @CheckForNull
    @GwtIncompatible
    public E lower(E e3) {
        return Iterators.getNext(headSet(e3, false).descendingIterator(), null);
    }

    @GwtIncompatible
    @CanIgnoreReturnValue
    @CheckForNull
    @DoNotCall("Always throws UnsupportedOperationException")
    @Deprecated
    public final E pollFirst() {
        throw new UnsupportedOperationException();
    }

    @GwtIncompatible
    @CanIgnoreReturnValue
    @CheckForNull
    @DoNotCall("Always throws UnsupportedOperationException")
    @Deprecated
    public final E pollLast() {
        throw new UnsupportedOperationException();
    }

    public abstract ImmutableSortedSet<E> subSetImpl(E e3, boolean z2, E e4, boolean z3);

    public abstract ImmutableSortedSet<E> tailSetImpl(E e3, boolean z2);

    public int unsafeCompare(Object obj, @CheckForNull Object obj2) {
        return unsafeCompare(this.comparator, obj, obj2);
    }

    @J2ktIncompatible
    public Object writeReplace() {
        return new SerializedForm(this.comparator, toArray());
    }

    public static <E> ImmutableSortedSet<E> copyOf(Iterable<? extends E> iterable) {
        return copyOf(Ordering.natural(), iterable);
    }

    public static <E extends Comparable<? super E>> ImmutableSortedSet<E> of(E e3) {
        return new RegularImmutableSortedSet(ImmutableList.of(e3), Ordering.natural());
    }

    public static int unsafeCompare(Comparator<?> comparator2, Object obj, @CheckForNull Object obj2) {
        return comparator2.compare(obj, obj2);
    }

    @GwtIncompatible
    public ImmutableSortedSet<E> descendingSet() {
        ImmutableSortedSet<E> immutableSortedSet = this.descendingSet;
        if (immutableSortedSet != null) {
            return immutableSortedSet;
        }
        ImmutableSortedSet<E> createDescendingSet = createDescendingSet();
        this.descendingSet = createDescendingSet;
        createDescendingSet.descendingSet = this;
        return createDescendingSet;
    }

    public static <E extends Comparable<? super E>> ImmutableSortedSet<E> of(E e3, E e4) {
        return construct(Ordering.natural(), 2, e3, e4);
    }

    public ImmutableSortedSet<E> headSet(E e3) {
        return headSet(e3, false);
    }

    public ImmutableSortedSet<E> subSet(E e3, E e4) {
        return subSet(e3, true, e4, false);
    }

    public ImmutableSortedSet<E> tailSet(E e3) {
        return tailSet(e3, true);
    }

    public static <E> ImmutableSortedSet<E> copyOf(Collection<? extends E> collection) {
        return copyOf(Ordering.natural(), collection);
    }

    public static <E extends Comparable<? super E>> ImmutableSortedSet<E> of(E e3, E e4, E e5) {
        return construct(Ordering.natural(), 3, e3, e4, e5);
    }

    public ImmutableSortedSet<E> headSet(E e3, boolean z2) {
        return headSetImpl(Preconditions.checkNotNull(e3), z2);
    }

    @GwtIncompatible
    public ImmutableSortedSet<E> subSet(E e3, boolean z2, E e4, boolean z3) {
        Preconditions.checkNotNull(e3);
        Preconditions.checkNotNull(e4);
        Preconditions.checkArgument(this.comparator.compare(e3, e4) <= 0);
        return subSetImpl(e3, z2, e4, z3);
    }

    public ImmutableSortedSet<E> tailSet(E e3, boolean z2) {
        return tailSetImpl(Preconditions.checkNotNull(e3), z2);
    }

    public static <E extends Comparable<? super E>> ImmutableSortedSet<E> of(E e3, E e4, E e5, E e6) {
        return construct(Ordering.natural(), 4, e3, e4, e5, e6);
    }

    public static <E> ImmutableSortedSet<E> copyOf(Iterator<? extends E> it) {
        return copyOf(Ordering.natural(), it);
    }

    public static <E extends Comparable<? super E>> ImmutableSortedSet<E> of(E e3, E e4, E e5, E e6, E e7) {
        return construct(Ordering.natural(), 5, e3, e4, e5, e6, e7);
    }

    public static <E extends Comparable<? super E>> ImmutableSortedSet<E> of(E e3, E e4, E e5, E e6, E e7, E e8, E... eArr) {
        int length = eArr.length + 6;
        Comparable[] comparableArr = new Comparable[length];
        comparableArr[0] = e3;
        comparableArr[1] = e4;
        comparableArr[2] = e5;
        comparableArr[3] = e6;
        comparableArr[4] = e7;
        comparableArr[5] = e8;
        System.arraycopy(eArr, 0, comparableArr, 6, eArr.length);
        return construct(Ordering.natural(), length, comparableArr);
    }

    public static <E> ImmutableSortedSet<E> copyOf(Comparator<? super E> comparator2, Iterator<? extends E> it) {
        return new Builder(comparator2).addAll((Iterator) it).build();
    }

    public static <E> ImmutableSortedSet<E> copyOf(Comparator<? super E> comparator2, Iterable<? extends E> iterable) {
        Preconditions.checkNotNull(comparator2);
        if (SortedIterables.hasSameComparator(comparator2, iterable) && (iterable instanceof ImmutableSortedSet)) {
            ImmutableSortedSet<E> immutableSortedSet = (ImmutableSortedSet) iterable;
            if (!immutableSortedSet.isPartialView()) {
                return immutableSortedSet;
            }
        }
        Object[] array = Iterables.toArray(iterable);
        return construct(comparator2, array.length, array);
    }

    public static <E> ImmutableSortedSet<E> copyOf(Comparator<? super E> comparator2, Collection<? extends E> collection) {
        return copyOf(comparator2, collection);
    }
}
