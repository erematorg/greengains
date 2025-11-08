package com.airbnb.lottie.model.animatable;

import com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation;
import com.airbnb.lottie.animation.keyframe.GradientColorKeyframeAnimation;
import com.airbnb.lottie.model.content.GradientColor;
import com.airbnb.lottie.value.Keyframe;
import java.util.Arrays;
import java.util.List;

public class AnimatableGradientColorValue extends BaseAnimatableValue<GradientColor, GradientColor> {
    public AnimatableGradientColorValue(List<Keyframe<GradientColor>> list) {
        super(ensureInterpolatableKeyframes(list));
    }

    private static Keyframe<GradientColor> ensureInterpolatableKeyframe(Keyframe<GradientColor> keyframe) {
        GradientColor gradientColor = (GradientColor) keyframe.startValue;
        GradientColor gradientColor2 = (GradientColor) keyframe.endValue;
        if (gradientColor == null || gradientColor2 == null || gradientColor.getPositions().length == gradientColor2.getPositions().length) {
            return keyframe;
        }
        float[] mergePositions = mergePositions(gradientColor.getPositions(), gradientColor2.getPositions());
        return keyframe.copyWith(gradientColor.copyWithPositions(mergePositions), gradientColor2.copyWithPositions(mergePositions));
    }

    private static List<Keyframe<GradientColor>> ensureInterpolatableKeyframes(List<Keyframe<GradientColor>> list) {
        for (int i3 = 0; i3 < list.size(); i3++) {
            list.set(i3, ensureInterpolatableKeyframe(list.get(i3)));
        }
        return list;
    }

    public static float[] mergePositions(float[] fArr, float[] fArr2) {
        int length = fArr.length + fArr2.length;
        float[] fArr3 = new float[length];
        System.arraycopy(fArr, 0, fArr3, 0, fArr.length);
        System.arraycopy(fArr2, 0, fArr3, fArr.length, fArr2.length);
        Arrays.sort(fArr3);
        float f2 = Float.NaN;
        int i3 = 0;
        for (int i4 = 0; i4 < length; i4++) {
            float f3 = fArr3[i4];
            if (f3 != f2) {
                fArr3[i3] = f3;
                i3++;
                f2 = fArr3[i4];
            }
        }
        return Arrays.copyOfRange(fArr3, 0, i3);
    }

    public BaseKeyframeAnimation<GradientColor, GradientColor> createAnimation() {
        return new GradientColorKeyframeAnimation(this.keyframes);
    }

    public /* bridge */ /* synthetic */ List getKeyframes() {
        return super.getKeyframes();
    }

    public /* bridge */ /* synthetic */ boolean isStatic() {
        return super.isStatic();
    }

    public /* bridge */ /* synthetic */ String toString() {
        return super.toString();
    }
}
