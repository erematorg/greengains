package com.airbnb.lottie.animation.keyframe;

import android.graphics.PointF;
import androidx.annotation.Nullable;
import com.airbnb.lottie.value.Keyframe;
import com.airbnb.lottie.value.LottieValueCallback;
import java.util.Collections;

public class SplitDimensionPathKeyframeAnimation extends BaseKeyframeAnimation<PointF, PointF> {
    private final PointF point = new PointF();
    private final PointF pointWithCallbackValues = new PointF();
    private final BaseKeyframeAnimation<Float, Float> xAnimation;
    @Nullable
    protected LottieValueCallback<Float> xValueCallback;
    private final BaseKeyframeAnimation<Float, Float> yAnimation;
    @Nullable
    protected LottieValueCallback<Float> yValueCallback;

    public SplitDimensionPathKeyframeAnimation(BaseKeyframeAnimation<Float, Float> baseKeyframeAnimation, BaseKeyframeAnimation<Float, Float> baseKeyframeAnimation2) {
        super(Collections.emptyList());
        this.xAnimation = baseKeyframeAnimation;
        this.yAnimation = baseKeyframeAnimation2;
        setProgress(getProgress());
    }

    public void setProgress(float f2) {
        this.xAnimation.setProgress(f2);
        this.yAnimation.setProgress(f2);
        this.point.set(this.xAnimation.getValue().floatValue(), this.yAnimation.getValue().floatValue());
        for (int i3 = 0; i3 < this.listeners.size(); i3++) {
            this.listeners.get(i3).onValueChanged();
        }
    }

    public void setXValueCallback(@Nullable LottieValueCallback<Float> lottieValueCallback) {
        LottieValueCallback<Float> lottieValueCallback2 = this.xValueCallback;
        if (lottieValueCallback2 != null) {
            lottieValueCallback2.setAnimation((BaseKeyframeAnimation<?, ?>) null);
        }
        this.xValueCallback = lottieValueCallback;
        if (lottieValueCallback != null) {
            lottieValueCallback.setAnimation(this);
        }
    }

    public void setYValueCallback(@Nullable LottieValueCallback<Float> lottieValueCallback) {
        LottieValueCallback<Float> lottieValueCallback2 = this.yValueCallback;
        if (lottieValueCallback2 != null) {
            lottieValueCallback2.setAnimation((BaseKeyframeAnimation<?, ?>) null);
        }
        this.yValueCallback = lottieValueCallback;
        if (lottieValueCallback != null) {
            lottieValueCallback.setAnimation(this);
        }
    }

    public PointF getValue() {
        return getValue((Keyframe<PointF>) null, 0.0f);
    }

    public PointF getValue(Keyframe<PointF> keyframe, float f2) {
        Float f3;
        Keyframe<Float> currentKeyframe;
        float f4;
        Keyframe<Float> currentKeyframe2;
        float f5;
        Float f6 = null;
        if (this.xValueCallback == null || (currentKeyframe2 = this.xAnimation.getCurrentKeyframe()) == null) {
            f3 = null;
        } else {
            float interpolatedCurrentKeyframeProgress = this.xAnimation.getInterpolatedCurrentKeyframeProgress();
            Float f7 = currentKeyframe2.endFrame;
            LottieValueCallback<Float> lottieValueCallback = this.xValueCallback;
            float f8 = currentKeyframe2.startFrame;
            if (f7 == null) {
                f5 = f8;
            } else {
                f5 = f7.floatValue();
            }
            f3 = lottieValueCallback.getValueInternal(f8, f5, (Float) currentKeyframe2.startValue, (Float) currentKeyframe2.endValue, f2, f2, interpolatedCurrentKeyframeProgress);
        }
        if (!(this.yValueCallback == null || (currentKeyframe = this.yAnimation.getCurrentKeyframe()) == null)) {
            float interpolatedCurrentKeyframeProgress2 = this.yAnimation.getInterpolatedCurrentKeyframeProgress();
            Float f9 = currentKeyframe.endFrame;
            LottieValueCallback<Float> lottieValueCallback2 = this.yValueCallback;
            float f10 = currentKeyframe.startFrame;
            if (f9 == null) {
                f4 = f10;
            } else {
                f4 = f9.floatValue();
            }
            f6 = lottieValueCallback2.getValueInternal(f10, f4, (Float) currentKeyframe.startValue, (Float) currentKeyframe.endValue, f2, f2, interpolatedCurrentKeyframeProgress2);
        }
        if (f3 == null) {
            this.pointWithCallbackValues.set(this.point.x, 0.0f);
        } else {
            this.pointWithCallbackValues.set(f3.floatValue(), 0.0f);
        }
        if (f6 == null) {
            PointF pointF = this.pointWithCallbackValues;
            pointF.set(pointF.x, this.point.y);
        } else {
            PointF pointF2 = this.pointWithCallbackValues;
            pointF2.set(pointF2.x, f6.floatValue());
        }
        return this.pointWithCallbackValues;
    }
}
