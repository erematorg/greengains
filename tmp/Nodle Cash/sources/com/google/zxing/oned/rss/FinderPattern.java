package com.google.zxing.oned.rss;

import com.google.zxing.ResultPoint;

public final class FinderPattern {
    private final ResultPoint[] resultPoints;
    private final int[] startEnd;
    private final int value;

    public FinderPattern(int i3, int[] iArr, int i4, int i5, int i6) {
        this.value = i3;
        this.startEnd = iArr;
        float f2 = (float) i4;
        float f3 = (float) i6;
        this.resultPoints = new ResultPoint[]{new ResultPoint(f2, f3), new ResultPoint((float) i5, f3)};
    }

    public boolean equals(Object obj) {
        return (obj instanceof FinderPattern) && this.value == ((FinderPattern) obj).value;
    }

    public ResultPoint[] getResultPoints() {
        return this.resultPoints;
    }

    public int[] getStartEnd() {
        return this.startEnd;
    }

    public int getValue() {
        return this.value;
    }

    public int hashCode() {
        return this.value;
    }
}
