package com.google.zxing.common.detector;

public final class MathUtils {
    private MathUtils() {
    }

    public static float distance(float f2, float f3, float f4, float f5) {
        double d2 = (double) (f2 - f4);
        double d3 = (double) (f3 - f5);
        return (float) Math.sqrt((d3 * d3) + (d2 * d2));
    }

    public static int round(float f2) {
        return (int) (f2 + (f2 < 0.0f ? -0.5f : 0.5f));
    }

    public static int sum(int[] iArr) {
        int i3 = 0;
        for (int i4 : iArr) {
            i3 += i4;
        }
        return i3;
    }

    public static float distance(int i3, int i4, int i5, int i6) {
        double d2 = (double) (i3 - i5);
        double d3 = (double) (i4 - i6);
        return (float) Math.sqrt((d3 * d3) + (d2 * d2));
    }
}
