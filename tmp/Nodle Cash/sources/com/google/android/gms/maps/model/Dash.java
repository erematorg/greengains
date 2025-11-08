package com.google.android.gms.maps.model;

import androidx.annotation.NonNull;
import androidx.camera.camera2.internal.C0118y;

public final class Dash extends PatternItem {
    public final float length;

    public Dash(float f2) {
        super(0, Float.valueOf(Math.max(f2, 0.0f)));
        this.length = Math.max(f2, 0.0f);
    }

    @NonNull
    public String toString() {
        return C0118y.i(new StringBuilder("[Dash: length="), "]", this.length);
    }
}
