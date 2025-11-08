package com.google.android.material.shape;

import androidx.annotation.NonNull;

public final class OffsetEdgeTreatment extends EdgeTreatment {
    private final float offset;
    private final EdgeTreatment other;

    public OffsetEdgeTreatment(@NonNull EdgeTreatment edgeTreatment, float f2) {
        this.other = edgeTreatment;
        this.offset = f2;
    }

    public boolean forceIntersection() {
        return this.other.forceIntersection();
    }

    public void getEdgePath(float f2, float f3, float f4, @NonNull ShapePath shapePath) {
        this.other.getEdgePath(f2, f3 - this.offset, f4, shapePath);
    }
}
