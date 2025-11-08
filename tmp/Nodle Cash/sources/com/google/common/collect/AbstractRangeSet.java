package com.google.common.collect;

import com.google.common.annotations.GwtIncompatible;
import java.lang.Comparable;
import javax.annotation.CheckForNull;

@GwtIncompatible
@ElementTypesAreNonnullByDefault
abstract class AbstractRangeSet<C extends Comparable> implements RangeSet<C> {
    public void add(Range<C> range) {
        throw new UnsupportedOperationException();
    }

    public void addAll(RangeSet<C> rangeSet) {
        addAll(rangeSet.asRanges());
    }

    public void clear() {
        remove(Range.all());
    }

    public boolean contains(C c3) {
        return rangeContaining(c3) != null;
    }

    public abstract boolean encloses(Range<C> range);

    public boolean enclosesAll(RangeSet<C> rangeSet) {
        return enclosesAll(rangeSet.asRanges());
    }

    public boolean equals(@CheckForNull Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof RangeSet) {
            return asRanges().equals(((RangeSet) obj).asRanges());
        }
        return false;
    }

    public final int hashCode() {
        return asRanges().hashCode();
    }

    public boolean intersects(Range<C> range) {
        return !subRangeSet(range).isEmpty();
    }

    public boolean isEmpty() {
        return asRanges().isEmpty();
    }

    @CheckForNull
    public abstract Range<C> rangeContaining(C c3);

    public void remove(Range<C> range) {
        throw new UnsupportedOperationException();
    }

    public void removeAll(RangeSet<C> rangeSet) {
        removeAll(rangeSet.asRanges());
    }

    public final String toString() {
        return asRanges().toString();
    }

    public void addAll(Iterable<Range<C>> iterable) {
        for (Range<C> add : iterable) {
            add(add);
        }
    }

    public boolean enclosesAll(Iterable<Range<C>> iterable) {
        for (Range<C> encloses : iterable) {
            if (!encloses(encloses)) {
                return false;
            }
        }
        return true;
    }

    public void removeAll(Iterable<Range<C>> iterable) {
        for (Range<C> remove : iterable) {
            remove(remove);
        }
    }
}
