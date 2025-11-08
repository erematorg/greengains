package com.airbnb.lottie.value;

public class ScaleXY {
    private float scaleX;
    private float scaleY;

    public ScaleXY(float f2, float f3) {
        this.scaleX = f2;
        this.scaleY = f3;
    }

    public boolean equals(float f2, float f3) {
        return this.scaleX == f2 && this.scaleY == f3;
    }

    public float getScaleX() {
        return this.scaleX;
    }

    public float getScaleY() {
        return this.scaleY;
    }

    public void set(float f2, float f3) {
        this.scaleX = f2;
        this.scaleY = f3;
    }

    public String toString() {
        return getScaleX() + "x" + getScaleY();
    }

    public ScaleXY() {
        this(1.0f, 1.0f);
    }
}
