package com.google.android.material.math;

public final class MathUtils {
    public static final float DEFAULT_EPSILON = 1.0E-4f;

    private MathUtils() {
    }

    public static float dist(float f2, float f3, float f4, float f5) {
        return (float) Math.hypot((double) (f4 - f2), (double) (f5 - f3));
    }

    public static float distanceToFurthestCorner(float f2, float f3, float f4, float f5, float f6, float f7) {
        return max(dist(f2, f3, f4, f5), dist(f2, f3, f6, f5), dist(f2, f3, f6, f7), dist(f2, f3, f4, f7));
    }

    public static float floorMod(float f2, int i3) {
        float f3 = (float) i3;
        int i4 = (int) (f2 / f3);
        if (Math.signum(f2) * f3 < 0.0f && ((float) (i4 * i3)) != f2) {
            i4--;
        }
        return f2 - ((float) (i4 * i3));
    }

    public static boolean geq(float f2, float f3, float f4) {
        return f2 + f4 >= f3;
    }

    public static float lerp(float f2, float f3, float f4) {
        return (f4 * f3) + ((1.0f - f4) * f2);
    }

    private static float max(float f2, float f3, float f4, float f5) {
        return (f2 <= f3 || f2 <= f4 || f2 <= f5) ? (f3 <= f4 || f3 <= f5) ? f4 > f5 ? f4 : f5 : f3 : f2;
    }

    public static int floorMod(int i3, int i4) {
        int i5 = i3 / i4;
        if ((i3 ^ i4) < 0 && i5 * i4 != i3) {
            i5--;
        }
        return i3 - (i5 * i4);
    }
}
