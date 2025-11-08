package com.google.android.material.color.utilities;

import androidx.annotation.RestrictTo;
import androidx.compose.ui.autofill.a;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class Blend {
    private Blend() {
    }

    public static int cam16Ucs(int i3, int i4, double d2) {
        Cam16 fromInt = Cam16.fromInt(i3);
        Cam16 fromInt2 = Cam16.fromInt(i4);
        double jstar = fromInt.getJstar();
        double astar = fromInt.getAstar();
        double bstar = fromInt.getBstar();
        return Cam16.fromUcs(a.a(fromInt2.getJstar(), jstar, d2, jstar), a.a(fromInt2.getAstar(), astar, d2, astar), a.a(fromInt2.getBstar(), bstar, d2, bstar)).toInt();
    }

    public static int harmonize(int i3, int i4) {
        Hct fromInt = Hct.fromInt(i3);
        Hct fromInt2 = Hct.fromInt(i4);
        double min = Math.min(MathUtils.differenceDegrees(fromInt.getHue(), fromInt2.getHue()) * 0.5d, 15.0d);
        return Hct.from(MathUtils.sanitizeDegreesDouble((MathUtils.rotationDirection(fromInt.getHue(), fromInt2.getHue()) * min) + fromInt.getHue()), fromInt.getChroma(), fromInt.getTone()).toInt();
    }

    public static int hctHue(int i3, int i4, double d2) {
        return Hct.from(Cam16.fromInt(cam16Ucs(i3, i4, d2)).getHue(), Cam16.fromInt(i3).getChroma(), ColorUtils.lstarFromArgb(i3)).toInt();
    }
}
