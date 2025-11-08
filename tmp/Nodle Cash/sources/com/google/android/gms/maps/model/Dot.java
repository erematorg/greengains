package com.google.android.gms.maps.model;

import androidx.annotation.NonNull;

public final class Dot extends PatternItem {
    public Dot() {
        super(1, (Float) null);
    }

    @NonNull
    public String toString() {
        return "[Dot]";
    }
}
