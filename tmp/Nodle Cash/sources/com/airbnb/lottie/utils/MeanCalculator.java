package com.airbnb.lottie.utils;

public class MeanCalculator {

    /* renamed from: n  reason: collision with root package name */
    private int f3405n;
    private float sum;

    public void add(float f2) {
        float f3 = this.sum + f2;
        this.sum = f3;
        int i3 = this.f3405n + 1;
        this.f3405n = i3;
        if (i3 == Integer.MAX_VALUE) {
            this.sum = f3 / 2.0f;
            this.f3405n = i3 / 2;
        }
    }

    public float getMean() {
        int i3 = this.f3405n;
        if (i3 == 0) {
            return 0.0f;
        }
        return this.sum / ((float) i3);
    }
}
