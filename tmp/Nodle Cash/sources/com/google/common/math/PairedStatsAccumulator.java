package com.google.common.math;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.common.base.Preconditions;
import com.google.common.primitives.Doubles;

@GwtIncompatible
@ElementTypesAreNonnullByDefault
@J2ktIncompatible
public final class PairedStatsAccumulator {
    private double sumOfProductsOfDeltas = 0.0d;
    private final StatsAccumulator xStats = new StatsAccumulator();
    private final StatsAccumulator yStats = new StatsAccumulator();

    private static double ensureInUnitRange(double d2) {
        return Doubles.constrainToRange(d2, -1.0d, 1.0d);
    }

    private double ensurePositive(double d2) {
        if (d2 > 0.0d) {
            return d2;
        }
        return Double.MIN_VALUE;
    }

    public void add(double d2, double d3) {
        this.xStats.add(d2);
        if (!Doubles.isFinite(d2) || !Doubles.isFinite(d3)) {
            this.sumOfProductsOfDeltas = Double.NaN;
        } else if (this.xStats.count() > 1) {
            this.sumOfProductsOfDeltas = ((d3 - this.yStats.mean()) * (d2 - this.xStats.mean())) + this.sumOfProductsOfDeltas;
        }
        this.yStats.add(d3);
    }

    public void addAll(PairedStats pairedStats) {
        if (pairedStats.count() != 0) {
            this.xStats.addAll(pairedStats.xStats());
            if (this.yStats.count() == 0) {
                this.sumOfProductsOfDeltas = pairedStats.sumOfProductsOfDeltas();
            } else {
                this.sumOfProductsOfDeltas = ((pairedStats.yStats().mean() - this.yStats.mean()) * (pairedStats.xStats().mean() - this.xStats.mean()) * ((double) pairedStats.count())) + pairedStats.sumOfProductsOfDeltas() + this.sumOfProductsOfDeltas;
            }
            this.yStats.addAll(pairedStats.yStats());
        }
    }

    public long count() {
        return this.xStats.count();
    }

    public final LinearTransformation leastSquaresFit() {
        boolean z2 = false;
        Preconditions.checkState(count() > 1);
        if (Double.isNaN(this.sumOfProductsOfDeltas)) {
            return LinearTransformation.forNaN();
        }
        double sumOfSquaresOfDeltas = this.xStats.sumOfSquaresOfDeltas();
        if (sumOfSquaresOfDeltas > 0.0d) {
            return this.yStats.sumOfSquaresOfDeltas() > 0.0d ? LinearTransformation.mapping(this.xStats.mean(), this.yStats.mean()).withSlope(this.sumOfProductsOfDeltas / sumOfSquaresOfDeltas) : LinearTransformation.horizontal(this.yStats.mean());
        }
        if (this.yStats.sumOfSquaresOfDeltas() > 0.0d) {
            z2 = true;
        }
        Preconditions.checkState(z2);
        return LinearTransformation.vertical(this.xStats.mean());
    }

    public final double pearsonsCorrelationCoefficient() {
        boolean z2 = false;
        Preconditions.checkState(count() > 1);
        if (Double.isNaN(this.sumOfProductsOfDeltas)) {
            return Double.NaN;
        }
        double sumOfSquaresOfDeltas = this.xStats.sumOfSquaresOfDeltas();
        double sumOfSquaresOfDeltas2 = this.yStats.sumOfSquaresOfDeltas();
        Preconditions.checkState(sumOfSquaresOfDeltas > 0.0d);
        if (sumOfSquaresOfDeltas2 > 0.0d) {
            z2 = true;
        }
        Preconditions.checkState(z2);
        return ensureInUnitRange(this.sumOfProductsOfDeltas / Math.sqrt(ensurePositive(sumOfSquaresOfDeltas * sumOfSquaresOfDeltas2)));
    }

    public double populationCovariance() {
        Preconditions.checkState(count() != 0);
        return this.sumOfProductsOfDeltas / ((double) count());
    }

    public final double sampleCovariance() {
        Preconditions.checkState(count() > 1);
        return this.sumOfProductsOfDeltas / ((double) (count() - 1));
    }

    public PairedStats snapshot() {
        return new PairedStats(this.xStats.snapshot(), this.yStats.snapshot(), this.sumOfProductsOfDeltas);
    }

    public Stats xStats() {
        return this.xStats.snapshot();
    }

    public Stats yStats() {
        return this.yStats.snapshot();
    }
}
