package com.google.android.gms.maps.model;

import androidx.annotation.NonNull;

public final class RoundCap extends Cap {
    public RoundCap() {
        super(2);
    }

    @NonNull
    public String toString() {
        return "[RoundCap]";
    }
}
