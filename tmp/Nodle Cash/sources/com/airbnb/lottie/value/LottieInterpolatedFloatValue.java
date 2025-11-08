package com.airbnb.lottie.value;

import android.view.animation.Interpolator;
import com.airbnb.lottie.utils.MiscUtils;

public class LottieInterpolatedFloatValue extends LottieInterpolatedValue<Float> {
    public LottieInterpolatedFloatValue(Float f2, Float f3) {
        super(f2, f3);
    }

    public /* bridge */ /* synthetic */ Object getValue(LottieFrameInfo lottieFrameInfo) {
        return super.getValue(lottieFrameInfo);
    }

    public LottieInterpolatedFloatValue(Float f2, Float f3, Interpolator interpolator) {
        super(f2, f3, interpolator);
    }

    public Float interpolateValue(Float f2, Float f3, float f4) {
        return Float.valueOf(MiscUtils.lerp(f2.floatValue(), f3.floatValue(), f4));
    }
}
