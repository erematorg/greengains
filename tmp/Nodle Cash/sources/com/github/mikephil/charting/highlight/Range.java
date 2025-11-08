package com.github.mikephil.charting.highlight;

public final class Range {
    public float from;
    public float to;

    public Range(float f2, float f3) {
        this.from = f2;
        this.to = f3;
    }

    public boolean contains(float f2) {
        return f2 > this.from && f2 <= this.to;
    }

    public boolean isLarger(float f2) {
        return f2 > this.to;
    }

    public boolean isSmaller(float f2) {
        return f2 < this.from;
    }
}
