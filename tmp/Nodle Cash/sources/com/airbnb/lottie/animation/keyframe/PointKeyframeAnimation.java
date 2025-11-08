package com.airbnb.lottie.animation.keyframe;

import android.graphics.PointF;
import android.support.v4.media.session.a;
import com.airbnb.lottie.value.Keyframe;
import com.airbnb.lottie.value.LottieValueCallback;
import java.util.List;

public class PointKeyframeAnimation extends KeyframeAnimation<PointF> {
    private final PointF point = new PointF();

    public PointKeyframeAnimation(List<Keyframe<PointF>> list) {
        super(list);
    }

    public PointF getValue(Keyframe<PointF> keyframe, float f2) {
        return getValue(keyframe, f2, f2, f2);
    }

    public PointF getValue(Keyframe<PointF> keyframe, float f2, float f3, float f4) {
        T t2;
        T t3 = keyframe.startValue;
        if (t3 == null || (t2 = keyframe.endValue) == null) {
            throw new IllegalStateException("Missing values for keyframe.");
        }
        PointF pointF = (PointF) t3;
        PointF pointF2 = (PointF) t2;
        LottieValueCallback<A> lottieValueCallback = this.valueCallback;
        if (lottieValueCallback != null) {
            PointF pointF3 = (PointF) lottieValueCallback.getValueInternal(keyframe.startFrame, keyframe.endFrame.floatValue(), pointF, pointF2, f2, getLinearCurrentKeyframeProgress(), getProgress());
            if (pointF3 != null) {
                return pointF3;
            }
        }
        PointF pointF4 = this.point;
        float f5 = pointF.x;
        float b3 = a.b(pointF2.x, f5, f3, f5);
        float f6 = pointF.y;
        pointF4.set(b3, a.b(pointF2.y, f6, f4, f6));
        return this.point;
    }
}
