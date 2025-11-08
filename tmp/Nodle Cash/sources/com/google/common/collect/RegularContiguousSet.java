package com.google.common.collect;

import androidx.collection.SieveCacheKt;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.common.base.Preconditions;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.lang.Comparable;
import java.util.Collection;
import java.util.Objects;
import javax.annotation.CheckForNull;

@GwtCompatible(emulated = true)
@ElementTypesAreNonnullByDefault
final class RegularContiguousSet<C extends Comparable> extends ContiguousSet<C> {
    private static final long serialVersionUID = 0;
    private final Range<C> range;

    @GwtIncompatible
    @J2ktIncompatible
    public static final class SerializedForm<C extends Comparable> implements Serializable {
        final DiscreteDomain<C> domain;
        final Range<C> range;

        private Object readResolve() {
            return new RegularContiguousSet(this.range, this.domain);
        }

        private SerializedForm(Range<C> range2, DiscreteDomain<C> discreteDomain) {
            this.range = range2;
            this.domain = discreteDomain;
        }
    }

    public RegularContiguousSet(Range<C> range2, DiscreteDomain<C> discreteDomain) {
        super(discreteDomain);
        this.range = range2;
    }

    /* access modifiers changed from: private */
    public static boolean equalsOrThrow(Comparable<?> comparable, @CheckForNull Comparable<?> comparable2) {
        return comparable2 != null && Range.compareOrThrow(comparable, comparable2) == 0;
    }

    private ContiguousSet<C> intersectionInCurrentDomain(Range<C> range2) {
        return this.range.isConnected(range2) ? ContiguousSet.create(this.range.intersection(range2), this.domain) : new EmptyContiguousSet(this.domain);
    }

    @GwtIncompatible
    @J2ktIncompatible
    private void readObject(ObjectInputStream objectInputStream) throws InvalidObjectException {
        throw new InvalidObjectException("Use SerializedForm");
    }

    public boolean contains(@CheckForNull Object obj) {
        if (obj == null) {
            return false;
        }
        try {
            return this.range.contains((Comparable) obj);
        } catch (ClassCastException unused) {
            return false;
        }
    }

    public boolean containsAll(Collection<?> collection) {
        return Collections2.containsAllImpl(this, collection);
    }

    public ImmutableList<C> createAsList() {
        return this.domain.supportsFastOffset ? new ImmutableAsList<C>() {
            public ImmutableSortedSet<C> delegateCollection() {
                return RegularContiguousSet.this;
            }

            public C get(int i3) {
                Preconditions.checkElementIndex(i3, size());
                RegularContiguousSet regularContiguousSet = RegularContiguousSet.this;
                return regularContiguousSet.domain.offset(regularContiguousSet.first(), (long) i3);
            }
        } : super.createAsList();
    }

    public boolean equals(@CheckForNull Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof RegularContiguousSet) {
            RegularContiguousSet regularContiguousSet = (RegularContiguousSet) obj;
            if (this.domain.equals(regularContiguousSet.domain)) {
                return first().equals(regularContiguousSet.first()) && last().equals(regularContiguousSet.last());
            }
        }
        return super.equals(obj);
    }

    public int hashCode() {
        return Sets.hashCodeImpl(this);
    }

    @GwtIncompatible
    public int indexOf(@CheckForNull Object obj) {
        if (!contains(obj)) {
            return -1;
        }
        DiscreteDomain<C> discreteDomain = this.domain;
        Comparable first = first();
        Objects.requireNonNull(obj);
        return (int) discreteDomain.distance(first, (Comparable) obj);
    }

    public ContiguousSet<C> intersection(ContiguousSet<C> contiguousSet) {
        Preconditions.checkNotNull(contiguousSet);
        Preconditions.checkArgument(this.domain.equals(contiguousSet.domain));
        if (contiguousSet.isEmpty()) {
            return contiguousSet;
        }
        Comparable comparable = (Comparable) Ordering.natural().max(first(), (Comparable) contiguousSet.first());
        Comparable comparable2 = (Comparable) Ordering.natural().min(last(), (Comparable) contiguousSet.last());
        return comparable.compareTo(comparable2) <= 0 ? ContiguousSet.create(Range.closed(comparable, comparable2), this.domain) : new EmptyContiguousSet(this.domain);
    }

    public boolean isEmpty() {
        return false;
    }

    public boolean isPartialView() {
        return false;
    }

    public Range<C> range() {
        BoundType boundType = BoundType.CLOSED;
        return range(boundType, boundType);
    }

    public int size() {
        long distance = this.domain.distance(first(), last());
        if (distance >= SieveCacheKt.NodeLinkMask) {
            return Integer.MAX_VALUE;
        }
        return ((int) distance) + 1;
    }

    @GwtIncompatible
    @J2ktIncompatible
    public Object writeReplace() {
        return new SerializedForm(this.range, this.domain);
    }

    @GwtIncompatible
    public UnmodifiableIterator<C> descendingIterator() {
        return new AbstractSequentialIterator<C>(last()) {
            final C first;

            {
                this.first = RegularContiguousSet.this.first();
            }

            @CheckForNull
            public C computeNext(C c3) {
                if (RegularContiguousSet.equalsOrThrow(c3, this.first)) {
                    return null;
                }
                return RegularContiguousSet.this.domain.previous(c3);
            }
        };
    }

    public C first() {
        C leastValueAbove = this.range.lowerBound.leastValueAbove(this.domain);
        Objects.requireNonNull(leastValueAbove);
        return (Comparable) leastValueAbove;
    }

    public ContiguousSet<C> headSetImpl(C c3, boolean z2) {
        return intersectionInCurrentDomain(Range.upTo(c3, BoundType.forBoolean(z2)));
    }

    public UnmodifiableIterator<C> iterator() {
        return new AbstractSequentialIterator<C>(first()) {
            final C last;

            {
                this.last = RegularContiguousSet.this.last();
            }

            @CheckForNull
            public C computeNext(C c3) {
                if (RegularContiguousSet.equalsOrThrow(c3, this.last)) {
                    return null;
                }
                return RegularContiguousSet.this.domain.next(c3);
            }
        };
    }

    public C last() {
        C greatestValueBelow = this.range.upperBound.greatestValueBelow(this.domain);
        Objects.requireNonNull(greatestValueBelow);
        return (Comparable) greatestValueBelow;
    }

    public Range<C> range(BoundType boundType, BoundType boundType2) {
        return Range.create(this.range.lowerBound.withLowerBoundType(boundType, this.domain), this.range.upperBound.withUpperBoundType(boundType2, this.domain));
    }

    public ContiguousSet<C> subSetImpl(C c3, boolean z2, C c4, boolean z3) {
        if (c3.compareTo(c4) != 0 || z2 || z3) {
            return intersectionInCurrentDomain(Range.range(c3, BoundType.forBoolean(z2), c4, BoundType.forBoolean(z3)));
        }
        return new EmptyContiguousSet(this.domain);
    }

    public ContiguousSet<C> tailSetImpl(C c3, boolean z2) {
        return intersectionInCurrentDomain(Range.downTo(c3, BoundType.forBoolean(z2)));
    }
}
