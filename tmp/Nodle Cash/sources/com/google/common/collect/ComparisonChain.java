package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.primitives.Booleans;
import com.google.common.primitives.Ints;
import com.google.common.primitives.Longs;
import java.util.Comparator;

@GwtCompatible
@ElementTypesAreNonnullByDefault
public abstract class ComparisonChain {
    /* access modifiers changed from: private */
    public static final ComparisonChain ACTIVE = new ComparisonChain() {
        public ComparisonChain classify(int i3) {
            return i3 < 0 ? ComparisonChain.LESS : i3 > 0 ? ComparisonChain.GREATER : ComparisonChain.ACTIVE;
        }

        public ComparisonChain compare(Comparable<?> comparable, Comparable<?> comparable2) {
            return classify(comparable.compareTo(comparable2));
        }

        public ComparisonChain compareFalseFirst(boolean z2, boolean z3) {
            return classify(Booleans.compare(z2, z3));
        }

        public ComparisonChain compareTrueFirst(boolean z2, boolean z3) {
            return classify(Booleans.compare(z3, z2));
        }

        public int result() {
            return 0;
        }

        public <T> ComparisonChain compare(@ParametricNullness T t2, @ParametricNullness T t3, Comparator<T> comparator) {
            return classify(comparator.compare(t2, t3));
        }

        public ComparisonChain compare(int i3, int i4) {
            return classify(Ints.compare(i3, i4));
        }

        public ComparisonChain compare(long j2, long j3) {
            return classify(Longs.compare(j2, j3));
        }

        public ComparisonChain compare(float f2, float f3) {
            return classify(Float.compare(f2, f3));
        }

        public ComparisonChain compare(double d2, double d3) {
            return classify(Double.compare(d2, d3));
        }
    };
    /* access modifiers changed from: private */
    public static final ComparisonChain GREATER = new InactiveComparisonChain(1);
    /* access modifiers changed from: private */
    public static final ComparisonChain LESS = new InactiveComparisonChain(-1);

    public static final class InactiveComparisonChain extends ComparisonChain {
        final int result;

        public InactiveComparisonChain(int i3) {
            super();
            this.result = i3;
        }

        public ComparisonChain compare(double d2, double d3) {
            return this;
        }

        public ComparisonChain compareFalseFirst(boolean z2, boolean z3) {
            return this;
        }

        public ComparisonChain compareTrueFirst(boolean z2, boolean z3) {
            return this;
        }

        public int result() {
            return this.result;
        }

        public ComparisonChain compare(float f2, float f3) {
            return this;
        }

        public ComparisonChain compare(int i3, int i4) {
            return this;
        }

        public ComparisonChain compare(long j2, long j3) {
            return this;
        }

        public ComparisonChain compare(Comparable<?> comparable, Comparable<?> comparable2) {
            return this;
        }

        public <T> ComparisonChain compare(@ParametricNullness T t2, @ParametricNullness T t3, Comparator<T> comparator) {
            return this;
        }
    }

    public static ComparisonChain start() {
        return ACTIVE;
    }

    public abstract ComparisonChain compare(double d2, double d3);

    public abstract ComparisonChain compare(float f2, float f3);

    public abstract ComparisonChain compare(int i3, int i4);

    public abstract ComparisonChain compare(long j2, long j3);

    @Deprecated
    public final ComparisonChain compare(Boolean bool, Boolean bool2) {
        return compareFalseFirst(bool.booleanValue(), bool2.booleanValue());
    }

    public abstract ComparisonChain compare(Comparable<?> comparable, Comparable<?> comparable2);

    public abstract <T> ComparisonChain compare(@ParametricNullness T t2, @ParametricNullness T t3, Comparator<T> comparator);

    public abstract ComparisonChain compareFalseFirst(boolean z2, boolean z3);

    public abstract ComparisonChain compareTrueFirst(boolean z2, boolean z3);

    public abstract int result();

    private ComparisonChain() {
    }
}
