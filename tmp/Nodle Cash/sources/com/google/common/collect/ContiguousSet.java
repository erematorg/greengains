package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableSortedSet;
import com.google.errorprone.annotations.DoNotCall;
import java.lang.Comparable;
import java.util.NoSuchElementException;
import java.util.Objects;

@GwtCompatible(emulated = true)
@ElementTypesAreNonnullByDefault
public abstract class ContiguousSet<C extends Comparable> extends ImmutableSortedSet<C> {
    final DiscreteDomain<C> domain;

    public ContiguousSet(DiscreteDomain<C> discreteDomain) {
        super(Ordering.natural());
        this.domain = discreteDomain;
    }

    @DoNotCall("Always throws UnsupportedOperationException")
    @Deprecated
    public static <E> ImmutableSortedSet.Builder<E> builder() {
        throw new UnsupportedOperationException();
    }

    public static ContiguousSet<Integer> closed(int i3, int i4) {
        return create(Range.closed(Integer.valueOf(i3), Integer.valueOf(i4)), DiscreteDomain.integers());
    }

    public static ContiguousSet<Integer> closedOpen(int i3, int i4) {
        return create(Range.closedOpen(Integer.valueOf(i3), Integer.valueOf(i4)), DiscreteDomain.integers());
    }

    public static <C extends Comparable> ContiguousSet<C> create(Range<C> range, DiscreteDomain<C> discreteDomain) {
        Preconditions.checkNotNull(range);
        Preconditions.checkNotNull(discreteDomain);
        try {
            Range<C> intersection = !range.hasLowerBound() ? range.intersection(Range.atLeast(discreteDomain.minValue())) : range;
            if (!range.hasUpperBound()) {
                intersection = intersection.intersection(Range.atMost(discreteDomain.maxValue()));
            }
            if (!intersection.isEmpty()) {
                C leastValueAbove = range.lowerBound.leastValueAbove(discreteDomain);
                Objects.requireNonNull(leastValueAbove);
                C greatestValueBelow = range.upperBound.greatestValueBelow(discreteDomain);
                Objects.requireNonNull(greatestValueBelow);
                if (Range.compareOrThrow((Comparable) leastValueAbove, (Comparable) greatestValueBelow) <= 0) {
                    return new RegularContiguousSet(intersection, discreteDomain);
                }
            }
            return new EmptyContiguousSet(discreteDomain);
        } catch (NoSuchElementException e3) {
            throw new IllegalArgumentException(e3);
        }
    }

    @GwtIncompatible
    public ImmutableSortedSet<C> createDescendingSet() {
        return new DescendingImmutableSortedSet(this);
    }

    public abstract ContiguousSet<C> headSetImpl(C c3, boolean z2);

    public abstract ContiguousSet<C> intersection(ContiguousSet<C> contiguousSet);

    public abstract Range<C> range();

    public abstract Range<C> range(BoundType boundType, BoundType boundType2);

    public abstract ContiguousSet<C> subSetImpl(C c3, boolean z2, C c4, boolean z3);

    public abstract ContiguousSet<C> tailSetImpl(C c3, boolean z2);

    public String toString() {
        return range().toString();
    }

    public static ContiguousSet<Long> closed(long j2, long j3) {
        return create(Range.closed(Long.valueOf(j2), Long.valueOf(j3)), DiscreteDomain.longs());
    }

    public static ContiguousSet<Long> closedOpen(long j2, long j3) {
        return create(Range.closedOpen(Long.valueOf(j2), Long.valueOf(j3)), DiscreteDomain.longs());
    }

    public ContiguousSet<C> headSet(C c3) {
        return headSetImpl((Comparable) Preconditions.checkNotNull(c3), false);
    }

    public ContiguousSet<C> subSet(C c3, C c4) {
        Preconditions.checkNotNull(c3);
        Preconditions.checkNotNull(c4);
        Preconditions.checkArgument(comparator().compare(c3, c4) <= 0);
        return subSetImpl(c3, true, c4, false);
    }

    public ContiguousSet<C> tailSet(C c3) {
        return tailSetImpl((Comparable) Preconditions.checkNotNull(c3), true);
    }

    @GwtIncompatible
    public ContiguousSet<C> headSet(C c3, boolean z2) {
        return headSetImpl((Comparable) Preconditions.checkNotNull(c3), z2);
    }

    @GwtIncompatible
    public ContiguousSet<C> tailSet(C c3, boolean z2) {
        return tailSetImpl((Comparable) Preconditions.checkNotNull(c3), z2);
    }

    @GwtIncompatible
    public ContiguousSet<C> subSet(C c3, boolean z2, C c4, boolean z3) {
        Preconditions.checkNotNull(c3);
        Preconditions.checkNotNull(c4);
        Preconditions.checkArgument(comparator().compare(c3, c4) <= 0);
        return subSetImpl(c3, z2, c4, z3);
    }
}
