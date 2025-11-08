package com.github.mikephil.charting.model;

public class GradientColor {
    private int endColor;
    private int startColor;

    public GradientColor(int i3, int i4) {
        this.startColor = i3;
        this.endColor = i4;
    }

    public int getEndColor() {
        return this.endColor;
    }

    public int getStartColor() {
        return this.startColor;
    }

    public void setEndColor(int i3) {
        this.endColor = i3;
    }

    public void setStartColor(int i3) {
        this.startColor = i3;
    }
}
