package com.google.common.collect;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.common.collect.Multiset;
import com.google.common.primitives.Ints;
import java.util.Comparator;
import javax.annotation.CheckForNull;

@GwtIncompatible
@ElementTypesAreNonnullByDefault
final class RegularImmutableSortedMultiset<E> extends ImmutableSortedMultiset<E> {
    static final ImmutableSortedMultiset<Comparable> NATURAL_EMPTY_MULTISET = new RegularImmutableSortedMultiset(Ordering.natural());
    private static final long[] ZERO_CUMULATIVE_COUNTS = {0};
    private final transient long[] cumulativeCounts;
    @VisibleForTesting
    final transient RegularImmutableSortedSet<E> elementSet;
    private final transient int length;
    private final transient int offset;

    public RegularImmutableSortedMultiset(Comparator<? super E> comparator) {
        this.elementSet = ImmutableSortedSet.emptySet(comparator);
        this.cumulativeCounts = ZERO_CUMULATIVE_COUNTS;
        this.offset = 0;
        this.length = 0;
    }

    private int getCount(int i3) {
        long[] jArr = this.cumulativeCounts;
        int i4 = this.offset;
        return (int) (jArr[(i4 + i3) + 1] - jArr[i4 + i3]);
    }

    public int count(@CheckForNull Object obj) {
        int indexOf = this.elementSet.indexOf(obj);
        if (indexOf >= 0) {
            return getCount(indexOf);
        }
        return 0;
    }

    @CheckForNull
    public Multiset.Entry<E> firstEntry() {
        if (isEmpty()) {
            return null;
        }
        return getEntry(0);
    }

    public Multiset.Entry<E> getEntry(int i3) {
        return Multisets.immutableEntry(this.elementSet.asList().get(i3), getCount(i3));
    }

    public ImmutableSortedMultiset<E> getSubMultiset(int i3, int i4) {
        Preconditions.checkPositionIndexes(i3, i4, this.length);
        return i3 == i4 ? ImmutableSortedMultiset.emptyMultiset(comparator()) : (i3 == 0 && i4 == this.length) ? this : new RegularImmutableSortedMultiset(this.elementSet.getSubSet(i3, i4), this.cumulativeCounts, this.offset + i3, i4 - i3);
    }

    public boolean isPartialView() {
        return this.offset > 0 || this.length < this.cumulativeCounts.length - 1;
    }

    @CheckForNull
    public Multiset.Entry<E> lastEntry() {
        if (isEmpty()) {
            return null;
        }
        return getEntry(this.length - 1);
    }

    public int size() {
        long[] jArr = this.cumulativeCounts;
        int i3 = this.offset;
        return Ints.saturatedCast(jArr[this.length + i3] - jArr[i3]);
    }

    public ImmutableSortedMultiset<E> headMultiset(E e3, BoundType boundType) {
        return getSubMultiset(0, this.elementSet.headIndex(e3, Preconditions.checkNotNull(boundType) == BoundType.CLOSED));
    }

    public ImmutableSortedMultiset<E> tailMultiset(E e3, BoundType boundType) {
        return getSubMultiset(this.elementSet.tailIndex(e3, Preconditions.checkNotNull(boundType) == BoundType.CLOSED), this.length);
    }

    public ImmutableSortedSet<E> elementSet() {
        return this.elementSet;
    }

    public RegularImmutableSortedMultiset(RegularImmutableSortedSet<E> regularImmutableSortedSet, long[] jArr, int i3, int i4) {
        this.elementSet = regularImmutableSortedSet;
        this.cumulativeCounts = jArr;
        this.offset = i3;
        this.length = i4;
    }
}
