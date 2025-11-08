package com.airbnb.lottie.value;

import androidx.annotation.RestrictTo;

public class LottieFrameInfo<T> {
    private float endFrame;
    private T endValue;
    private float interpolatedKeyframeProgress;
    private float linearKeyframeProgress;
    private float overallProgress;
    private float startFrame;
    private T startValue;

    public float getEndFrame() {
        return this.endFrame;
    }

    public T getEndValue() {
        return this.endValue;
    }

    public float getInterpolatedKeyframeProgress() {
        return this.interpolatedKeyframeProgress;
    }

    public float getLinearKeyframeProgress() {
        return this.linearKeyframeProgress;
    }

    public float getOverallProgress() {
        return this.overallProgress;
    }

    public float getStartFrame() {
        return this.startFrame;
    }

    public T getStartValue() {
        return this.startValue;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public LottieFrameInfo<T> set(float f2, float f3, T t2, T t3, float f4, float f5, float f6) {
        this.startFrame = f2;
        this.endFrame = f3;
        this.startValue = t2;
        this.endValue = t3;
        this.linearKeyframeProgress = f4;
        this.interpolatedKeyframeProgress = f5;
        this.overallProgress = f6;
        return this;
    }
}
