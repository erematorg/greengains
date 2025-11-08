package com.google.mlkit.vision.common;

import androidx.annotation.NonNull;

public abstract class PointF3D {
    @NonNull
    public static PointF3D from(float f2, float f3, float f4) {
        return new zza(f2, f3, f4);
    }

    public abstract float getX();

    public abstract float getY();

    public abstract float getZ();
}
