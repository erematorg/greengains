package com.google.android.material.carousel;

import android.content.Context;
import android.view.View;
import androidx.annotation.FloatRange;
import androidx.annotation.NonNull;

public abstract class CarouselStrategy {
    private float smallSizeMax;
    private float smallSizeMin;

    public static int[] doubleCounts(int[] iArr) {
        int length = iArr.length;
        int[] iArr2 = new int[length];
        for (int i3 = 0; i3 < length; i3++) {
            iArr2[i3] = iArr[i3] * 2;
        }
        return iArr2;
    }

    @FloatRange(from = 0.0d, to = 1.0d)
    public static float getChildMaskPercentage(float f2, float f3, float f4) {
        return 1.0f - ((f2 - f4) / (f3 - f4));
    }

    public float getSmallItemSizeMax() {
        return this.smallSizeMax;
    }

    public float getSmallItemSizeMin() {
        return this.smallSizeMin;
    }

    public void initialize(Context context) {
        float f2 = this.smallSizeMin;
        if (f2 <= 0.0f) {
            f2 = CarouselStrategyHelper.getSmallSizeMin(context);
        }
        this.smallSizeMin = f2;
        float f3 = this.smallSizeMax;
        if (f3 <= 0.0f) {
            f3 = CarouselStrategyHelper.getSmallSizeMax(context);
        }
        this.smallSizeMax = f3;
    }

    public boolean isContained() {
        return true;
    }

    public abstract KeylineState onFirstChildMeasuredWithMargins(@NonNull Carousel carousel, @NonNull View view);

    public void setSmallItemSizeMax(float f2) {
        this.smallSizeMax = f2;
    }

    public void setSmallItemSizeMin(float f2) {
        this.smallSizeMin = f2;
    }

    public boolean shouldRefreshKeylineState(Carousel carousel, int i3) {
        return false;
    }
}
