package com.github.mikephil.charting.data;

import android.annotation.SuppressLint;

@SuppressLint({"ParcelCreator"})
public class RadarEntry extends Entry {
    public RadarEntry(float f2) {
        super(0.0f, f2);
    }

    public float getValue() {
        return getY();
    }

    @Deprecated
    public float getX() {
        return super.getX();
    }

    @Deprecated
    public void setX(float f2) {
        super.setX(f2);
    }

    public RadarEntry(float f2, Object obj) {
        super(0.0f, f2, obj);
    }

    public RadarEntry copy() {
        return new RadarEntry(getY(), getData());
    }
}
