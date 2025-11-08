package com.google.android.material.transition.platform;

import android.support.v4.media.session.a;
import androidx.annotation.RequiresApi;

@RequiresApi(21)
class FadeModeEvaluators {
    private static final FadeModeEvaluator CROSS = new FadeModeEvaluator() {
        public FadeModeResult evaluate(float f2, float f3, float f4, float f5) {
            return FadeModeResult.startOnTop(TransitionUtils.lerp(255, 0, f3, f4, f2), TransitionUtils.lerp(0, 255, f3, f4, f2));
        }
    };
    private static final FadeModeEvaluator IN = new FadeModeEvaluator() {
        public FadeModeResult evaluate(float f2, float f3, float f4, float f5) {
            return FadeModeResult.endOnTop(255, TransitionUtils.lerp(0, 255, f3, f4, f2));
        }
    };
    private static final FadeModeEvaluator OUT = new FadeModeEvaluator() {
        public FadeModeResult evaluate(float f2, float f3, float f4, float f5) {
            return FadeModeResult.startOnTop(TransitionUtils.lerp(255, 0, f3, f4, f2), 255);
        }
    };
    private static final FadeModeEvaluator THROUGH = new FadeModeEvaluator() {
        public FadeModeResult evaluate(float f2, float f3, float f4, float f5) {
            float b3 = a.b(f4, f3, f5, f3);
            return FadeModeResult.startOnTop(TransitionUtils.lerp(255, 0, f3, b3, f2), TransitionUtils.lerp(0, 255, b3, f4, f2));
        }
    };

    private FadeModeEvaluators() {
    }

    public static FadeModeEvaluator get(int i3, boolean z2) {
        if (i3 == 0) {
            return z2 ? IN : OUT;
        }
        if (i3 == 1) {
            return z2 ? OUT : IN;
        }
        if (i3 == 2) {
            return CROSS;
        }
        if (i3 == 3) {
            return THROUGH;
        }
        throw new IllegalArgumentException(A.a.k("Invalid fade mode: ", i3));
    }
}
