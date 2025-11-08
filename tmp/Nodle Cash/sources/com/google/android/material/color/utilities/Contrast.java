package com.google.android.material.color.utilities;

import androidx.annotation.RestrictTo;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public final class Contrast {
    private static final double CONTRAST_RATIO_EPSILON = 0.04d;
    private static final double LUMINANCE_GAMUT_MAP_TOLERANCE = 0.4d;
    public static final double RATIO_30 = 3.0d;
    public static final double RATIO_45 = 4.5d;
    public static final double RATIO_70 = 7.0d;
    public static final double RATIO_MAX = 21.0d;
    public static final double RATIO_MIN = 1.0d;

    private Contrast() {
    }

    public static double darker(double d2, double d3) {
        if (d2 >= 0.0d && d2 <= 100.0d) {
            double yFromLstar = ColorUtils.yFromLstar(d2);
            double d4 = ((yFromLstar + 5.0d) / d3) - 5.0d;
            if (d4 >= 0.0d && d4 <= 100.0d) {
                double ratioOfYs = ratioOfYs(yFromLstar, d4);
                double abs = Math.abs(ratioOfYs - d3);
                if (ratioOfYs < d3 && abs > CONTRAST_RATIO_EPSILON) {
                    return -1.0d;
                }
                double lstarFromY = ColorUtils.lstarFromY(d4) - LUMINANCE_GAMUT_MAP_TOLERANCE;
                if (lstarFromY < 0.0d || lstarFromY > 100.0d) {
                    return -1.0d;
                }
                return lstarFromY;
            }
        }
        return -1.0d;
    }

    public static double darkerUnsafe(double d2, double d3) {
        return Math.max(0.0d, darker(d2, d3));
    }

    public static double lighter(double d2, double d3) {
        if (d2 >= 0.0d && d2 <= 100.0d) {
            double yFromLstar = ColorUtils.yFromLstar(d2);
            double d4 = ((yFromLstar + 5.0d) * d3) - 5.0d;
            if (d4 >= 0.0d && d4 <= 100.0d) {
                double ratioOfYs = ratioOfYs(d4, yFromLstar);
                double abs = Math.abs(ratioOfYs - d3);
                if (ratioOfYs < d3 && abs > CONTRAST_RATIO_EPSILON) {
                    return -1.0d;
                }
                double lstarFromY = ColorUtils.lstarFromY(d4) + LUMINANCE_GAMUT_MAP_TOLERANCE;
                if (lstarFromY < 0.0d || lstarFromY > 100.0d) {
                    return -1.0d;
                }
                return lstarFromY;
            }
        }
        return -1.0d;
    }

    public static double lighterUnsafe(double d2, double d3) {
        double lighter = lighter(d2, d3);
        if (lighter < 0.0d) {
            return 100.0d;
        }
        return lighter;
    }

    public static double ratioOfTones(double d2, double d3) {
        return ratioOfYs(ColorUtils.yFromLstar(d2), ColorUtils.yFromLstar(d3));
    }

    public static double ratioOfYs(double d2, double d3) {
        double max = Math.max(d2, d3);
        if (max != d3) {
            d2 = d3;
        }
        return (max + 5.0d) / (d2 + 5.0d);
    }
}
