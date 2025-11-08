package com.google.common.math;

import androidx.compose.ui.autofill.a;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.common.base.Preconditions;
import com.google.common.primitives.Doubles;
import java.util.Iterator;

@GwtIncompatible
@ElementTypesAreNonnullByDefault
@J2ktIncompatible
public final class StatsAccumulator {
    private long count = 0;
    private double max = Double.NaN;
    private double mean = 0.0d;
    private double min = Double.NaN;
    private double sumOfSquaresOfDeltas = 0.0d;

    public static double calculateNewMeanNonFinite(double d2, double d3) {
        if (Doubles.isFinite(d2)) {
            return d3;
        }
        if (Doubles.isFinite(d3) || d2 == d3) {
            return d2;
        }
        return Double.NaN;
    }

    private void merge(long j2, double d2, double d3, double d4, double d5) {
        long j3 = j2;
        double d6 = d2;
        double d7 = d3;
        double d8 = d4;
        double d9 = d5;
        long j4 = this.count;
        if (j4 == 0) {
            this.count = j3;
            this.mean = d6;
            this.sumOfSquaresOfDeltas = d7;
            this.min = d8;
            this.max = d9;
            return;
        }
        this.count = j4 + j3;
        if (!Doubles.isFinite(this.mean) || !Doubles.isFinite(d2)) {
            this.mean = calculateNewMeanNonFinite(this.mean, d6);
            this.sumOfSquaresOfDeltas = Double.NaN;
        } else {
            double d10 = this.mean;
            double d11 = d6 - d10;
            double d12 = (double) j3;
            double d13 = ((d11 * d12) / ((double) this.count)) + d10;
            this.mean = d13;
            this.sumOfSquaresOfDeltas = ((d6 - d13) * d11 * d12) + d7 + this.sumOfSquaresOfDeltas;
        }
        this.min = Math.min(this.min, d8);
        this.max = Math.max(this.max, d5);
    }

    public void add(double d2) {
        long j2 = this.count;
        if (j2 == 0) {
            this.count = 1;
            this.mean = d2;
            this.min = d2;
            this.max = d2;
            if (!Doubles.isFinite(d2)) {
                this.sumOfSquaresOfDeltas = Double.NaN;
                return;
            }
            return;
        }
        this.count = j2 + 1;
        if (!Doubles.isFinite(d2) || !Doubles.isFinite(this.mean)) {
            this.mean = calculateNewMeanNonFinite(this.mean, d2);
            this.sumOfSquaresOfDeltas = Double.NaN;
        } else {
            double d3 = this.mean;
            double d4 = d2 - d3;
            double d5 = (d4 / ((double) this.count)) + d3;
            this.mean = d5;
            this.sumOfSquaresOfDeltas = a.a(d2, d5, d4, this.sumOfSquaresOfDeltas);
        }
        this.min = Math.min(this.min, d2);
        this.max = Math.max(this.max, d2);
    }

    public void addAll(Iterable<? extends Number> iterable) {
        for (Number doubleValue : iterable) {
            add(doubleValue.doubleValue());
        }
    }

    public long count() {
        return this.count;
    }

    public double max() {
        Preconditions.checkState(this.count != 0);
        return this.max;
    }

    public double mean() {
        Preconditions.checkState(this.count != 0);
        return this.mean;
    }

    public double min() {
        Preconditions.checkState(this.count != 0);
        return this.min;
    }

    public final double populationStandardDeviation() {
        return Math.sqrt(populationVariance());
    }

    public final double populationVariance() {
        Preconditions.checkState(this.count != 0);
        if (Double.isNaN(this.sumOfSquaresOfDeltas)) {
            return Double.NaN;
        }
        if (this.count == 1) {
            return 0.0d;
        }
        return DoubleUtils.ensureNonNegative(this.sumOfSquaresOfDeltas) / ((double) this.count);
    }

    public final double sampleStandardDeviation() {
        return Math.sqrt(sampleVariance());
    }

    public final double sampleVariance() {
        Preconditions.checkState(this.count > 1);
        if (Double.isNaN(this.sumOfSquaresOfDeltas)) {
            return Double.NaN;
        }
        return DoubleUtils.ensureNonNegative(this.sumOfSquaresOfDeltas) / ((double) (this.count - 1));
    }

    public Stats snapshot() {
        return new Stats(this.count, this.mean, this.sumOfSquaresOfDeltas, this.min, this.max);
    }

    public final double sum() {
        return this.mean * ((double) this.count);
    }

    public double sumOfSquaresOfDeltas() {
        return this.sumOfSquaresOfDeltas;
    }

    public void addAll(Iterator<? extends Number> it) {
        while (it.hasNext()) {
            add(((Number) it.next()).doubleValue());
        }
    }

    public void addAll(double... dArr) {
        for (double add : dArr) {
            add(add);
        }
    }

    public void addAll(int... iArr) {
        for (int i3 : iArr) {
            add((double) i3);
        }
    }

    public void addAll(long... jArr) {
        for (long j2 : jArr) {
            add((double) j2);
        }
    }

    public void addAll(Stats stats) {
        if (stats.count() != 0) {
            merge(stats.count(), stats.mean(), stats.sumOfSquaresOfDeltas(), stats.min(), stats.max());
        }
    }

    public void addAll(StatsAccumulator statsAccumulator) {
        if (statsAccumulator.count() != 0) {
            merge(statsAccumulator.count(), statsAccumulator.mean(), statsAccumulator.sumOfSquaresOfDeltas(), statsAccumulator.min(), statsAccumulator.max());
        }
    }
}
