package com.airbnb.lottie.animation.keyframe;

import com.airbnb.lottie.model.content.GradientColor;
import com.airbnb.lottie.value.Keyframe;
import java.util.List;

public class GradientColorKeyframeAnimation extends KeyframeAnimation<GradientColor> {
    private final GradientColor gradientColor;

    public GradientColorKeyframeAnimation(List<Keyframe<GradientColor>> list) {
        super(list);
        int i3 = 0;
        for (int i4 = 0; i4 < list.size(); i4++) {
            GradientColor gradientColor2 = (GradientColor) list.get(i4).startValue;
            if (gradientColor2 != null) {
                i3 = Math.max(i3, gradientColor2.getSize());
            }
        }
        this.gradientColor = new GradientColor(new float[i3], new int[i3]);
    }

    public GradientColor getValue(Keyframe<GradientColor> keyframe, float f2) {
        this.gradientColor.lerp((GradientColor) keyframe.startValue, (GradientColor) keyframe.endValue, f2);
        return this.gradientColor;
    }
}
