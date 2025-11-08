package com.google.common.math;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.concurrent.LazyInit;
import javax.annotation.CheckForNull;

@GwtIncompatible
@ElementTypesAreNonnullByDefault
@J2ktIncompatible
public abstract class LinearTransformation {

    public static final class LinearTransformationBuilder {
        private final double x1;
        private final double y1;

        public LinearTransformation and(double d2, double d3) {
            boolean z2 = false;
            Preconditions.checkArgument(DoubleUtils.isFinite(d2) && DoubleUtils.isFinite(d3));
            double d4 = this.x1;
            if (d2 != d4) {
                return withSlope((d3 - this.y1) / (d2 - d4));
            }
            if (d3 != this.y1) {
                z2 = true;
            }
            Preconditions.checkArgument(z2);
            return new VerticalLinearTransformation(this.x1);
        }

        public LinearTransformation withSlope(double d2) {
            Preconditions.checkArgument(!Double.isNaN(d2));
            return DoubleUtils.isFinite(d2) ? new RegularLinearTransformation(d2, this.y1 - (this.x1 * d2)) : new VerticalLinearTransformation(this.x1);
        }

        private LinearTransformationBuilder(double d2, double d3) {
            this.x1 = d2;
            this.y1 = d3;
        }
    }

    public static final class NaNLinearTransformation extends LinearTransformation {
        static final NaNLinearTransformation INSTANCE = new NaNLinearTransformation();

        private NaNLinearTransformation() {
        }

        public LinearTransformation inverse() {
            return this;
        }

        public boolean isHorizontal() {
            return false;
        }

        public boolean isVertical() {
            return false;
        }

        public double slope() {
            return Double.NaN;
        }

        public String toString() {
            return "NaN";
        }

        public double transform(double d2) {
            return Double.NaN;
        }
    }

    public static LinearTransformation forNaN() {
        return NaNLinearTransformation.INSTANCE;
    }

    public static LinearTransformation horizontal(double d2) {
        Preconditions.checkArgument(DoubleUtils.isFinite(d2));
        return new RegularLinearTransformation(0.0d, d2);
    }

    public static LinearTransformationBuilder mapping(double d2, double d3) {
        Preconditions.checkArgument(DoubleUtils.isFinite(d2) && DoubleUtils.isFinite(d3));
        return new LinearTransformationBuilder(d2, d3);
    }

    public static LinearTransformation vertical(double d2) {
        Preconditions.checkArgument(DoubleUtils.isFinite(d2));
        return new VerticalLinearTransformation(d2);
    }

    public abstract LinearTransformation inverse();

    public abstract boolean isHorizontal();

    public abstract boolean isVertical();

    public abstract double slope();

    public abstract double transform(double d2);

    public static final class VerticalLinearTransformation extends LinearTransformation {
        @CheckForNull
        @LazyInit
        LinearTransformation inverse;

        /* renamed from: x  reason: collision with root package name */
        final double f6933x;

        public VerticalLinearTransformation(double d2) {
            this.f6933x = d2;
            this.inverse = null;
        }

        private LinearTransformation createInverse() {
            return new RegularLinearTransformation(0.0d, this.f6933x, this);
        }

        public LinearTransformation inverse() {
            LinearTransformation linearTransformation = this.inverse;
            if (linearTransformation != null) {
                return linearTransformation;
            }
            LinearTransformation createInverse = createInverse();
            this.inverse = createInverse;
            return createInverse;
        }

        public boolean isHorizontal() {
            return false;
        }

        public boolean isVertical() {
            return true;
        }

        public double slope() {
            throw new IllegalStateException();
        }

        public String toString() {
            return String.format("x = %g", new Object[]{Double.valueOf(this.f6933x)});
        }

        public double transform(double d2) {
            throw new IllegalStateException();
        }

        public VerticalLinearTransformation(double d2, LinearTransformation linearTransformation) {
            this.f6933x = d2;
            this.inverse = linearTransformation;
        }
    }

    public static final class RegularLinearTransformation extends LinearTransformation {
        @CheckForNull
        @LazyInit
        LinearTransformation inverse;
        final double slope;
        final double yIntercept;

        public RegularLinearTransformation(double d2, double d3) {
            this.slope = d2;
            this.yIntercept = d3;
            this.inverse = null;
        }

        private LinearTransformation createInverse() {
            double d2 = this.slope;
            return d2 != 0.0d ? new RegularLinearTransformation(1.0d / d2, (this.yIntercept * -1.0d) / d2, this) : new VerticalLinearTransformation(this.yIntercept, this);
        }

        public LinearTransformation inverse() {
            LinearTransformation linearTransformation = this.inverse;
            if (linearTransformation != null) {
                return linearTransformation;
            }
            LinearTransformation createInverse = createInverse();
            this.inverse = createInverse;
            return createInverse;
        }

        public boolean isHorizontal() {
            return this.slope == 0.0d;
        }

        public boolean isVertical() {
            return false;
        }

        public double slope() {
            return this.slope;
        }

        public String toString() {
            return String.format("y = %g * x + %g", new Object[]{Double.valueOf(this.slope), Double.valueOf(this.yIntercept)});
        }

        public double transform(double d2) {
            return (d2 * this.slope) + this.yIntercept;
        }

        public RegularLinearTransformation(double d2, double d3, LinearTransformation linearTransformation) {
            this.slope = d2;
            this.yIntercept = d3;
            this.inverse = linearTransformation;
        }
    }
}
