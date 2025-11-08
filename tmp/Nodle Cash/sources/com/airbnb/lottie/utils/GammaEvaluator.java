package com.airbnb.lottie.utils;

import android.support.v4.media.session.a;

public class GammaEvaluator {
    private static float EOCF_sRGB(float f2) {
        return f2 <= 0.04045f ? f2 / 12.92f : (float) Math.pow((double) ((f2 + 0.055f) / 1.055f), 2.4000000953674316d);
    }

    private static float OECF_sRGB(float f2) {
        return f2 <= 0.0031308f ? f2 * 12.92f : (float) ((Math.pow((double) f2, 0.4166666567325592d) * 1.0549999475479126d) - 0.054999999701976776d);
    }

    public static int evaluate(float f2, int i3, int i4) {
        if (i3 == i4) {
            return i3;
        }
        float f3 = ((float) ((i3 >> 24) & 255)) / 255.0f;
        float EOCF_sRGB = EOCF_sRGB(((float) ((i3 >> 16) & 255)) / 255.0f);
        float EOCF_sRGB2 = EOCF_sRGB(((float) ((i3 >> 8) & 255)) / 255.0f);
        float EOCF_sRGB3 = EOCF_sRGB(((float) (i3 & 255)) / 255.0f);
        float EOCF_sRGB4 = EOCF_sRGB(((float) ((i4 >> 16) & 255)) / 255.0f);
        float EOCF_sRGB5 = EOCF_sRGB(((float) ((i4 >> 8) & 255)) / 255.0f);
        float EOCF_sRGB6 = EOCF_sRGB(((float) (i4 & 255)) / 255.0f);
        float b3 = a.b(((float) ((i4 >> 24) & 255)) / 255.0f, f3, f2, f3);
        float b4 = a.b(EOCF_sRGB4, EOCF_sRGB, f2, EOCF_sRGB);
        float b5 = a.b(EOCF_sRGB5, EOCF_sRGB2, f2, EOCF_sRGB2);
        float b6 = a.b(EOCF_sRGB6, EOCF_sRGB3, f2, EOCF_sRGB3);
        int round = Math.round(OECF_sRGB(b4) * 255.0f) << 16;
        return Math.round(OECF_sRGB(b6) * 255.0f) | round | (Math.round(b3 * 255.0f) << 24) | (Math.round(OECF_sRGB(b5) * 255.0f) << 8);
    }
}
