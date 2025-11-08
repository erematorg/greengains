package com.google.common.collect;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMultiset;
import com.google.common.collect.Multiset;
import com.google.common.math.IntMath;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.DoNotCall;
import com.google.errorprone.annotations.concurrent.LazyInit;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import javax.annotation.CheckForNull;

@GwtIncompatible
@ElementTypesAreNonnullByDefault
public abstract class ImmutableSortedMultiset<E> extends ImmutableSortedMultisetFauxverideShim<E> implements SortedMultiset<E> {
    @CheckForNull
    @LazyInit
    transient ImmutableSortedMultiset<E> descendingMultiset;

    public static class Builder<E> extends ImmutableMultiset.Builder<E> {
        private final Comparator<? super E> comparator;
        private int[] counts = new int[4];
        @VisibleForTesting
        E[] elements = new Object[4];
        private boolean forceCopyElements;
        private int length;

        public Builder(Comparator<? super E> comparator2) {
            super(true);
            this.comparator = (Comparator) Preconditions.checkNotNull(comparator2);
        }

        private void dedupAndCoalesce(boolean z2) {
            int i3 = this.length;
            if (i3 != 0) {
                E[] copyOf = Arrays.copyOf(this.elements, i3);
                Arrays.sort(copyOf, this.comparator);
                int i4 = 1;
                for (int i5 = 1; i5 < copyOf.length; i5++) {
                    if (this.comparator.compare(copyOf[i4 - 1], copyOf[i5]) < 0) {
                        copyOf[i4] = copyOf[i5];
                        i4++;
                    }
                }
                Arrays.fill(copyOf, i4, this.length, (Object) null);
                if (z2) {
                    int i6 = i4 * 4;
                    int i7 = this.length;
                    if (i6 > i7 * 3) {
                        copyOf = Arrays.copyOf(copyOf, IntMath.saturatedAdd(i7, (i7 / 2) + 1));
                    }
                }
                int[] iArr = new int[copyOf.length];
                for (int i8 = 0; i8 < this.length; i8++) {
                    int binarySearch = Arrays.binarySearch(copyOf, 0, i4, this.elements[i8], this.comparator);
                    int i9 = this.counts[i8];
                    if (i9 >= 0) {
                        iArr[binarySearch] = iArr[binarySearch] + i9;
                    } else {
                        iArr[binarySearch] = ~i9;
                    }
                }
                this.elements = copyOf;
                this.counts = iArr;
                this.length = i4;
            }
        }

        private void dedupAndCoalesceAndDeleteEmpty() {
            dedupAndCoalesce(false);
            int i3 = 0;
            int i4 = 0;
            while (true) {
                int i5 = this.length;
                if (i3 < i5) {
                    int[] iArr = this.counts;
                    int i6 = iArr[i3];
                    if (i6 > 0) {
                        E[] eArr = this.elements;
                        eArr[i4] = eArr[i3];
                        iArr[i4] = i6;
                        i4++;
                    }
                    i3++;
                } else {
                    Arrays.fill(this.elements, i4, i5, (Object) null);
                    Arrays.fill(this.counts, i4, this.length, 0);
                    this.length = i4;
                    return;
                }
            }
        }

        private void maintenance() {
            int i3 = this.length;
            E[] eArr = this.elements;
            if (i3 == eArr.length) {
                dedupAndCoalesce(true);
            } else if (this.forceCopyElements) {
                this.elements = Arrays.copyOf(eArr, eArr.length);
            }
            this.forceCopyElements = false;
        }

        @CanIgnoreReturnValue
        public Builder<E> addCopies(E e3, int i3) {
            Preconditions.checkNotNull(e3);
            CollectPreconditions.checkNonnegative(i3, "occurrences");
            if (i3 == 0) {
                return this;
            }
            maintenance();
            E[] eArr = this.elements;
            int i4 = this.length;
            eArr[i4] = e3;
            this.counts[i4] = i3;
            this.length = i4 + 1;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder<E> setCount(E e3, int i3) {
            Preconditions.checkNotNull(e3);
            CollectPreconditions.checkNonnegative(i3, "count");
            maintenance();
            E[] eArr = this.elements;
            int i4 = this.length;
            eArr[i4] = e3;
            this.counts[i4] = ~i3;
            this.length = i4 + 1;
            return this;
        }

        public ImmutableSortedMultiset<E> build() {
            dedupAndCoalesceAndDeleteEmpty();
            int i3 = this.length;
            if (i3 == 0) {
                return ImmutableSortedMultiset.emptyMultiset(this.comparator);
            }
            RegularImmutableSortedSet regularImmutableSortedSet = (RegularImmutableSortedSet) ImmutableSortedSet.construct(this.comparator, i3, this.elements);
            long[] jArr = new long[(this.length + 1)];
            int i4 = 0;
            while (i4 < this.length) {
                int i5 = i4 + 1;
                jArr[i5] = jArr[i4] + ((long) this.counts[i4]);
                i4 = i5;
            }
            this.forceCopyElements = true;
            return new RegularImmutableSortedMultiset(regularImmutableSortedSet, jArr, 0, this.length);
        }

        @CanIgnoreReturnValue
        public Builder<E> add(E e3) {
            return addCopies((Object) e3, 1);
        }

        @CanIgnoreReturnValue
        public Builder<E> addAll(Iterable<? extends E> iterable) {
            if (iterable instanceof Multiset) {
                for (Multiset.Entry entry : ((Multiset) iterable).entrySet()) {
                    addCopies(entry.getElement(), entry.getCount());
                }
            } else {
                for (Object add : iterable) {
                    add((Object) add);
                }
            }
            return this;
        }

        @CanIgnoreReturnValue
        public Builder<E> add(E... eArr) {
            for (E add : eArr) {
                add((Object) add);
            }
            return this;
        }

        @CanIgnoreReturnValue
        public Builder<E> addAll(Iterator<? extends E> it) {
            while (it.hasNext()) {
                add((Object) it.next());
            }
            return this;
        }
    }

    @J2ktIncompatible
    public static final class SerializedForm<E> implements Serializable {
        final Comparator<? super E> comparator;
        final int[] counts;
        final E[] elements;

        public SerializedForm(SortedMultiset<E> sortedMultiset) {
            this.comparator = sortedMultiset.comparator();
            int size = sortedMultiset.entrySet().size();
            this.elements = new Object[size];
            this.counts = new int[size];
            int i3 = 0;
            for (Multiset.Entry next : sortedMultiset.entrySet()) {
                this.elements[i3] = next.getElement();
                this.counts[i3] = next.getCount();
                i3++;
            }
        }

        public Object readResolve() {
            int length = this.elements.length;
            Builder builder = new Builder(this.comparator);
            for (int i3 = 0; i3 < length; i3++) {
                builder.addCopies((Object) this.elements[i3], this.counts[i3]);
            }
            return builder.build();
        }
    }

    public static <E extends Comparable<? super E>> ImmutableSortedMultiset<E> copyOf(E[] eArr) {
        return copyOf(Ordering.natural(), Arrays.asList(eArr));
    }

    public static <E> ImmutableSortedMultiset<E> copyOfSorted(SortedMultiset<E> sortedMultiset) {
        return copyOfSortedEntries(sortedMultiset.comparator(), Lists.newArrayList(sortedMultiset.entrySet()));
    }

    private static <E> ImmutableSortedMultiset<E> copyOfSortedEntries(Comparator<? super E> comparator, Collection<Multiset.Entry<E>> collection) {
        if (collection.isEmpty()) {
            return emptyMultiset(comparator);
        }
        ImmutableList.Builder builder = new ImmutableList.Builder(collection.size());
        long[] jArr = new long[(collection.size() + 1)];
        int i3 = 0;
        for (Multiset.Entry next : collection) {
            builder.add(next.getElement());
            int i4 = i3 + 1;
            jArr[i4] = jArr[i3] + ((long) next.getCount());
            i3 = i4;
        }
        return new RegularImmutableSortedMultiset(new RegularImmutableSortedSet(builder.build(), comparator), jArr, 0, collection.size());
    }

    public static <E> ImmutableSortedMultiset<E> emptyMultiset(Comparator<? super E> comparator) {
        return Ordering.natural().equals(comparator) ? RegularImmutableSortedMultiset.NATURAL_EMPTY_MULTISET : new RegularImmutableSortedMultiset(comparator);
    }

    public static <E extends Comparable<?>> Builder<E> naturalOrder() {
        return new Builder<>(Ordering.natural());
    }

    public static <E> ImmutableSortedMultiset<E> of() {
        return RegularImmutableSortedMultiset.NATURAL_EMPTY_MULTISET;
    }

    public static <E> Builder<E> orderedBy(Comparator<E> comparator) {
        return new Builder<>(comparator);
    }

    @J2ktIncompatible
    private void readObject(ObjectInputStream objectInputStream) throws InvalidObjectException {
        throw new InvalidObjectException("Use SerializedForm");
    }

    public static <E extends Comparable<?>> Builder<E> reverseOrder() {
        return new Builder<>(Ordering.natural().reverse());
    }

    public final Comparator<? super E> comparator() {
        return elementSet().comparator();
    }

    public abstract ImmutableSortedSet<E> elementSet();

    public abstract ImmutableSortedMultiset<E> headMultiset(E e3, BoundType boundType);

    @CanIgnoreReturnValue
    @CheckForNull
    @DoNotCall("Always throws UnsupportedOperationException")
    @Deprecated
    public final Multiset.Entry<E> pollFirstEntry() {
        throw new UnsupportedOperationException();
    }

    @CanIgnoreReturnValue
    @CheckForNull
    @DoNotCall("Always throws UnsupportedOperationException")
    @Deprecated
    public final Multiset.Entry<E> pollLastEntry() {
        throw new UnsupportedOperationException();
    }

    public abstract ImmutableSortedMultiset<E> tailMultiset(E e3, BoundType boundType);

    @J2ktIncompatible
    public Object writeReplace() {
        return new SerializedForm(this);
    }

    public static <E> ImmutableSortedMultiset<E> copyOf(Iterable<? extends E> iterable) {
        return copyOf(Ordering.natural(), iterable);
    }

    public static <E extends Comparable<? super E>> ImmutableSortedMultiset<E> of(E e3) {
        return new RegularImmutableSortedMultiset((RegularImmutableSortedSet) ImmutableSortedSet.of(e3), new long[]{0, 1}, 0, 1);
    }

    public ImmutableSortedMultiset<E> descendingMultiset() {
        ImmutableSortedMultiset<E> immutableSortedMultiset = this.descendingMultiset;
        if (immutableSortedMultiset == null) {
            if (isEmpty()) {
                immutableSortedMultiset = emptyMultiset(Ordering.from(comparator()).reverse());
            } else {
                immutableSortedMultiset = new DescendingImmutableSortedMultiset<>(this);
            }
            this.descendingMultiset = immutableSortedMultiset;
        }
        return immutableSortedMultiset;
    }

    public ImmutableSortedMultiset<E> subMultiset(E e3, BoundType boundType, E e4, BoundType boundType2) {
        Preconditions.checkArgument(comparator().compare(e3, e4) <= 0, "Expected lowerBound <= upperBound but %s > %s", (Object) e3, (Object) e4);
        return tailMultiset(e3, boundType).headMultiset(e4, boundType2);
    }

    public static <E> ImmutableSortedMultiset<E> copyOf(Iterator<? extends E> it) {
        return copyOf(Ordering.natural(), it);
    }

    public static <E extends Comparable<? super E>> ImmutableSortedMultiset<E> of(E e3, E e4) {
        return copyOf(Ordering.natural(), Arrays.asList(new Comparable[]{e3, e4}));
    }

    public static <E> ImmutableSortedMultiset<E> copyOf(Comparator<? super E> comparator, Iterator<? extends E> it) {
        Preconditions.checkNotNull(comparator);
        return new Builder(comparator).addAll((Iterator) it).build();
    }

    public static <E extends Comparable<? super E>> ImmutableSortedMultiset<E> of(E e3, E e4, E e5) {
        return copyOf(Ordering.natural(), Arrays.asList(new Comparable[]{e3, e4, e5}));
    }

    public static <E extends Comparable<? super E>> ImmutableSortedMultiset<E> of(E e3, E e4, E e5, E e6) {
        return copyOf(Ordering.natural(), Arrays.asList(new Comparable[]{e3, e4, e5, e6}));
    }

    public static <E> ImmutableSortedMultiset<E> copyOf(Comparator<? super E> comparator, Iterable<? extends E> iterable) {
        if (iterable instanceof ImmutableSortedMultiset) {
            ImmutableSortedMultiset<E> immutableSortedMultiset = (ImmutableSortedMultiset) iterable;
            if (comparator.equals(immutableSortedMultiset.comparator())) {
                return immutableSortedMultiset.isPartialView() ? copyOfSortedEntries(comparator, immutableSortedMultiset.entrySet().asList()) : immutableSortedMultiset;
            }
        }
        return new Builder(comparator).addAll((Iterable) iterable).build();
    }

    public static <E extends Comparable<? super E>> ImmutableSortedMultiset<E> of(E e3, E e4, E e5, E e6, E e7) {
        return copyOf(Ordering.natural(), Arrays.asList(new Comparable[]{e3, e4, e5, e6, e7}));
    }

    public static <E extends Comparable<? super E>> ImmutableSortedMultiset<E> of(E e3, E e4, E e5, E e6, E e7, E e8, E... eArr) {
        ArrayList newArrayListWithCapacity = Lists.newArrayListWithCapacity(eArr.length + 6);
        Collections.addAll(newArrayListWithCapacity, new Comparable[]{e3, e4, e5, e6, e7, e8});
        Collections.addAll(newArrayListWithCapacity, eArr);
        return copyOf(Ordering.natural(), newArrayListWithCapacity);
    }
}
