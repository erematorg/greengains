package com.google.android.material.color.utilities;

import androidx.annotation.RestrictTo;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public final class ContrastCurve {
    private final double high;
    private final double low;
    private final double medium;
    private final double normal;

    public ContrastCurve(double d2, double d3, double d4, double d5) {
        this.low = d2;
        this.normal = d3;
        this.medium = d4;
        this.high = d5;
    }

    public double getContrast(double d2) {
        return d2 <= -1.0d ? this.low : d2 < 0.0d ? MathUtils.lerp(this.low, this.normal, (d2 - -1.0d) / 1.0d) : d2 < 0.5d ? MathUtils.lerp(this.normal, this.medium, (d2 - 0.0d) / 0.5d) : d2 < 1.0d ? MathUtils.lerp(this.medium, this.high, (d2 - 0.5d) / 0.5d) : this.high;
    }
}
