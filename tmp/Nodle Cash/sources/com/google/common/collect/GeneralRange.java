package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.concurrent.LazyInit;
import java.io.Serializable;
import java.util.Comparator;
import javax.annotation.CheckForNull;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

@GwtCompatible(serializable = true)
@ElementTypesAreNonnullByDefault
final class GeneralRange<T> implements Serializable {
    private final Comparator<? super T> comparator;
    private final boolean hasLowerBound;
    private final boolean hasUpperBound;
    private final BoundType lowerBoundType;
    @CheckForNull
    private final T lowerEndpoint;
    @CheckForNull
    @LazyInit
    private transient GeneralRange<T> reverse;
    private final BoundType upperBoundType;
    @CheckForNull
    private final T upperEndpoint;

    private GeneralRange(Comparator<? super T> comparator2, boolean z2, @CheckForNull T t2, BoundType boundType, boolean z3, @CheckForNull T t3, BoundType boundType2) {
        this.comparator = (Comparator) Preconditions.checkNotNull(comparator2);
        this.hasLowerBound = z2;
        this.hasUpperBound = z3;
        this.lowerEndpoint = t2;
        this.lowerBoundType = (BoundType) Preconditions.checkNotNull(boundType);
        this.upperEndpoint = t3;
        this.upperBoundType = (BoundType) Preconditions.checkNotNull(boundType2);
        if (z2) {
            comparator2.compare(NullnessCasts.uncheckedCastNullableTToT(t2), NullnessCasts.uncheckedCastNullableTToT(t2));
        }
        if (z3) {
            comparator2.compare(NullnessCasts.uncheckedCastNullableTToT(t3), NullnessCasts.uncheckedCastNullableTToT(t3));
        }
        if (z2 && z3) {
            int compare = comparator2.compare(NullnessCasts.uncheckedCastNullableTToT(t2), NullnessCasts.uncheckedCastNullableTToT(t3));
            boolean z4 = false;
            Preconditions.checkArgument(compare <= 0, "lowerEndpoint (%s) > upperEndpoint (%s)", (Object) t2, (Object) t3);
            if (compare == 0) {
                BoundType boundType3 = BoundType.OPEN;
                Preconditions.checkArgument((boundType == boundType3 && boundType2 == boundType3) ? z4 : true);
            }
        }
    }

    public static <T> GeneralRange<T> all(Comparator<? super T> comparator2) {
        BoundType boundType = BoundType.OPEN;
        return new GeneralRange(comparator2, false, (Object) null, boundType, false, (Object) null, boundType);
    }

    public static <T> GeneralRange<T> downTo(Comparator<? super T> comparator2, @ParametricNullness T t2, BoundType boundType) {
        return new GeneralRange(comparator2, true, t2, boundType, false, (T) null, BoundType.OPEN);
    }

    public static <T extends Comparable> GeneralRange<T> from(Range<T> range) {
        T t2 = null;
        T lowerEndpoint2 = range.hasLowerBound() ? range.lowerEndpoint() : null;
        BoundType lowerBoundType2 = range.hasLowerBound() ? range.lowerBoundType() : BoundType.OPEN;
        if (range.hasUpperBound()) {
            t2 = range.upperEndpoint();
        }
        return new GeneralRange(Ordering.natural(), range.hasLowerBound(), lowerEndpoint2, lowerBoundType2, range.hasUpperBound(), t2, range.hasUpperBound() ? range.upperBoundType() : BoundType.OPEN);
    }

    public static <T> GeneralRange<T> range(Comparator<? super T> comparator2, @ParametricNullness T t2, BoundType boundType, @ParametricNullness T t3, BoundType boundType2) {
        return new GeneralRange(comparator2, true, t2, boundType, true, t3, boundType2);
    }

    public static <T> GeneralRange<T> upTo(Comparator<? super T> comparator2, @ParametricNullness T t2, BoundType boundType) {
        return new GeneralRange(comparator2, false, (Object) null, BoundType.OPEN, true, t2, boundType);
    }

    public Comparator<? super T> comparator() {
        return this.comparator;
    }

    public boolean contains(@ParametricNullness T t2) {
        return !tooLow(t2) && !tooHigh(t2);
    }

    public boolean equals(@CheckForNull Object obj) {
        if (!(obj instanceof GeneralRange)) {
            return false;
        }
        GeneralRange generalRange = (GeneralRange) obj;
        return this.comparator.equals(generalRange.comparator) && this.hasLowerBound == generalRange.hasLowerBound && this.hasUpperBound == generalRange.hasUpperBound && getLowerBoundType().equals(generalRange.getLowerBoundType()) && getUpperBoundType().equals(generalRange.getUpperBoundType()) && Objects.equal(getLowerEndpoint(), generalRange.getLowerEndpoint()) && Objects.equal(getUpperEndpoint(), generalRange.getUpperEndpoint());
    }

    public BoundType getLowerBoundType() {
        return this.lowerBoundType;
    }

    @CheckForNull
    public T getLowerEndpoint() {
        return this.lowerEndpoint;
    }

    public BoundType getUpperBoundType() {
        return this.upperBoundType;
    }

    @CheckForNull
    public T getUpperEndpoint() {
        return this.upperEndpoint;
    }

    public boolean hasLowerBound() {
        return this.hasLowerBound;
    }

    public boolean hasUpperBound() {
        return this.hasUpperBound;
    }

    public int hashCode() {
        return Objects.hashCode(this.comparator, getLowerEndpoint(), getLowerBoundType(), getUpperEndpoint(), getUpperBoundType());
    }

    public GeneralRange<T> intersect(GeneralRange<T> generalRange) {
        int compare;
        int compare2;
        BoundType boundType;
        BoundType boundType2;
        T t2;
        int compare3;
        BoundType boundType3;
        Preconditions.checkNotNull(generalRange);
        Preconditions.checkArgument(this.comparator.equals(generalRange.comparator));
        boolean z2 = this.hasLowerBound;
        T lowerEndpoint2 = getLowerEndpoint();
        BoundType lowerBoundType2 = getLowerBoundType();
        if (!hasLowerBound()) {
            z2 = generalRange.hasLowerBound;
            lowerEndpoint2 = generalRange.getLowerEndpoint();
            lowerBoundType2 = generalRange.getLowerBoundType();
        } else if (generalRange.hasLowerBound() && ((compare = this.comparator.compare(getLowerEndpoint(), generalRange.getLowerEndpoint())) < 0 || (compare == 0 && generalRange.getLowerBoundType() == BoundType.OPEN))) {
            lowerEndpoint2 = generalRange.getLowerEndpoint();
            lowerBoundType2 = generalRange.getLowerBoundType();
        }
        boolean z3 = z2;
        boolean z4 = this.hasUpperBound;
        T upperEndpoint2 = getUpperEndpoint();
        BoundType upperBoundType2 = getUpperBoundType();
        if (!hasUpperBound()) {
            z4 = generalRange.hasUpperBound;
            upperEndpoint2 = generalRange.getUpperEndpoint();
            upperBoundType2 = generalRange.getUpperBoundType();
        } else if (generalRange.hasUpperBound() && ((compare2 = this.comparator.compare(getUpperEndpoint(), generalRange.getUpperEndpoint())) > 0 || (compare2 == 0 && generalRange.getUpperBoundType() == BoundType.OPEN))) {
            upperEndpoint2 = generalRange.getUpperEndpoint();
            upperBoundType2 = generalRange.getUpperBoundType();
        }
        boolean z5 = z4;
        T t3 = upperEndpoint2;
        if (!z3 || !z5 || ((compare3 = this.comparator.compare(lowerEndpoint2, t3)) <= 0 && !(compare3 == 0 && lowerBoundType2 == (boundType3 = BoundType.OPEN) && upperBoundType2 == boundType3))) {
            t2 = lowerEndpoint2;
            boundType2 = lowerBoundType2;
            boundType = upperBoundType2;
        } else {
            boundType2 = BoundType.OPEN;
            boundType = BoundType.CLOSED;
            t2 = t3;
        }
        return new GeneralRange(this.comparator, z3, t2, boundType2, z5, t3, boundType);
    }

    public boolean isEmpty() {
        return (hasUpperBound() && tooLow(NullnessCasts.uncheckedCastNullableTToT(getUpperEndpoint()))) || (hasLowerBound() && tooHigh(NullnessCasts.uncheckedCastNullableTToT(getLowerEndpoint())));
    }

    public GeneralRange<T> reverse() {
        GeneralRange<T> generalRange = this.reverse;
        if (generalRange != null) {
            return generalRange;
        }
        GeneralRange generalRange2 = new GeneralRange(Ordering.from(this.comparator).reverse(), this.hasUpperBound, getUpperEndpoint(), getUpperBoundType(), this.hasLowerBound, getLowerEndpoint(), getLowerBoundType());
        generalRange2.reverse = this;
        this.reverse = generalRange2;
        return generalRange2;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.comparator);
        sb.append(":");
        BoundType boundType = this.lowerBoundType;
        BoundType boundType2 = BoundType.CLOSED;
        sb.append(boundType == boundType2 ? AbstractJsonLexerKt.BEGIN_LIST : '(');
        sb.append(this.hasLowerBound ? this.lowerEndpoint : "-∞");
        sb.append(AbstractJsonLexerKt.COMMA);
        sb.append(this.hasUpperBound ? this.upperEndpoint : "∞");
        sb.append(this.upperBoundType == boundType2 ? AbstractJsonLexerKt.END_LIST : ')');
        return sb.toString();
    }

    public boolean tooHigh(@ParametricNullness T t2) {
        boolean z2 = false;
        if (!hasUpperBound()) {
            return false;
        }
        int compare = this.comparator.compare(t2, NullnessCasts.uncheckedCastNullableTToT(getUpperEndpoint()));
        boolean z3 = compare > 0;
        boolean z4 = compare == 0;
        if (getUpperBoundType() == BoundType.OPEN) {
            z2 = true;
        }
        return (z4 & z2) | z3;
    }

    public boolean tooLow(@ParametricNullness T t2) {
        boolean z2 = false;
        if (!hasLowerBound()) {
            return false;
        }
        int compare = this.comparator.compare(t2, NullnessCasts.uncheckedCastNullableTToT(getLowerEndpoint()));
        boolean z3 = compare < 0;
        boolean z4 = compare == 0;
        if (getLowerBoundType() == BoundType.OPEN) {
            z2 = true;
        }
        return (z4 & z2) | z3;
    }
}
