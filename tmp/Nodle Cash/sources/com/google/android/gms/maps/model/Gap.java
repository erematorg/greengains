package com.google.android.gms.maps.model;

import androidx.annotation.NonNull;
import androidx.camera.camera2.internal.C0118y;

public final class Gap extends PatternItem {
    public final float length;

    public Gap(float f2) {
        super(2, Float.valueOf(Math.max(f2, 0.0f)));
        this.length = Math.max(f2, 0.0f);
    }

    @NonNull
    public String toString() {
        return C0118y.i(new StringBuilder("[Gap: length="), "]", this.length);
    }
}
