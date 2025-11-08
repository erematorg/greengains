package com.google.zxing.qrcode.detector;

import com.google.zxing.ResultPoint;

public final class FinderPattern extends ResultPoint {
    private final int count;
    private final float estimatedModuleSize;

    public FinderPattern(float f2, float f3, float f4) {
        this(f2, f3, f4, 1);
    }

    public boolean aboutEquals(float f2, float f3, float f4) {
        if (Math.abs(f3 - getY()) > f2 || Math.abs(f4 - getX()) > f2) {
            return false;
        }
        float abs = Math.abs(f2 - this.estimatedModuleSize);
        return abs <= 1.0f || abs <= this.estimatedModuleSize;
    }

    public FinderPattern combineEstimate(float f2, float f3, float f4) {
        int i3 = this.count;
        int i4 = i3 + 1;
        float x2 = (getX() * ((float) i3)) + f3;
        float f5 = (float) i4;
        return new FinderPattern(x2 / f5, ((getY() * ((float) this.count)) + f2) / f5, ((((float) this.count) * this.estimatedModuleSize) + f4) / f5, i4);
    }

    public int getCount() {
        return this.count;
    }

    public float getEstimatedModuleSize() {
        return this.estimatedModuleSize;
    }

    private FinderPattern(float f2, float f3, float f4, int i3) {
        super(f2, f3);
        this.estimatedModuleSize = f4;
        this.count = i3;
    }
}
