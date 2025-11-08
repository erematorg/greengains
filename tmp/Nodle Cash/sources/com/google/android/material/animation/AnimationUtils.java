package com.google.android.material.animation;

import android.animation.TimeInterpolator;
import android.support.v4.media.session.a;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import androidx.annotation.RestrictTo;
import androidx.interpolator.view.animation.FastOutLinearInInterpolator;
import androidx.interpolator.view.animation.FastOutSlowInInterpolator;
import androidx.interpolator.view.animation.LinearOutSlowInInterpolator;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class AnimationUtils {
    public static final TimeInterpolator DECELERATE_INTERPOLATOR = new DecelerateInterpolator();
    public static final TimeInterpolator FAST_OUT_LINEAR_IN_INTERPOLATOR = new FastOutLinearInInterpolator();
    public static final TimeInterpolator FAST_OUT_SLOW_IN_INTERPOLATOR = new FastOutSlowInInterpolator();
    public static final TimeInterpolator LINEAR_INTERPOLATOR = new LinearInterpolator();
    public static final TimeInterpolator LINEAR_OUT_SLOW_IN_INTERPOLATOR = new LinearOutSlowInInterpolator();

    public static float lerp(float f2, float f3, float f4) {
        return a.b(f3, f2, f4, f2);
    }

    public static int lerp(int i3, int i4, float f2) {
        return Math.round(f2 * ((float) (i4 - i3))) + i3;
    }

    public static float lerp(float f2, float f3, float f4, float f5, float f6) {
        if (f6 <= f4) {
            return f2;
        }
        return f6 >= f5 ? f3 : lerp(f2, f3, (f6 - f4) / (f5 - f4));
    }
}
