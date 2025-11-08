package com.airbnb.lottie.animation.keyframe;

import com.airbnb.lottie.utils.MiscUtils;
import com.airbnb.lottie.value.Keyframe;
import com.airbnb.lottie.value.LottieValueCallback;
import com.airbnb.lottie.value.ScaleXY;
import java.util.List;

public class ScaleKeyframeAnimation extends KeyframeAnimation<ScaleXY> {
    private final ScaleXY scaleXY = new ScaleXY();

    public ScaleKeyframeAnimation(List<Keyframe<ScaleXY>> list) {
        super(list);
    }

    public ScaleXY getValue(Keyframe<ScaleXY> keyframe, float f2) {
        T t2;
        T t3 = keyframe.startValue;
        if (t3 == null || (t2 = keyframe.endValue) == null) {
            throw new IllegalStateException("Missing values for keyframe.");
        }
        ScaleXY scaleXY2 = (ScaleXY) t3;
        ScaleXY scaleXY3 = (ScaleXY) t2;
        LottieValueCallback<A> lottieValueCallback = this.valueCallback;
        if (lottieValueCallback != null) {
            ScaleXY scaleXY4 = (ScaleXY) lottieValueCallback.getValueInternal(keyframe.startFrame, keyframe.endFrame.floatValue(), scaleXY2, scaleXY3, f2, getLinearCurrentKeyframeProgress(), getProgress());
            if (scaleXY4 != null) {
                return scaleXY4;
            }
        }
        this.scaleXY.set(MiscUtils.lerp(scaleXY2.getScaleX(), scaleXY3.getScaleX(), f2), MiscUtils.lerp(scaleXY2.getScaleY(), scaleXY3.getScaleY(), f2));
        return this.scaleXY;
    }
}
