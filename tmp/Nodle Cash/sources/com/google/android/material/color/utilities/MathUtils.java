package com.google.android.material.color.utilities;

import androidx.annotation.RestrictTo;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class MathUtils {
    private MathUtils() {
    }

    public static double clampDouble(double d2, double d3, double d4) {
        return d4 < d2 ? d2 : d4 > d3 ? d3 : d4;
    }

    public static int clampInt(int i3, int i4, int i5) {
        return i5 < i3 ? i3 : i5 > i4 ? i4 : i5;
    }

    public static double differenceDegrees(double d2, double d3) {
        return 180.0d - Math.abs(Math.abs(d2 - d3) - 180.0d);
    }

    public static double lerp(double d2, double d3, double d4) {
        return (d4 * d3) + ((1.0d - d4) * d2);
    }

    public static double[] matrixMultiply(double[] dArr, double[][] dArr2) {
        double d2 = dArr[0];
        double[] dArr3 = dArr2[0];
        double d3 = dArr[1];
        double d4 = dArr3[1] * d3;
        double d5 = dArr[2];
        double d6 = (dArr3[2] * d5) + d4 + (dArr3[0] * d2);
        double[] dArr4 = dArr2[1];
        double d7 = (dArr4[2] * d5) + (dArr4[1] * d3) + (dArr4[0] * d2);
        double[] dArr5 = dArr2[2];
        return new double[]{d6, d7, (d5 * dArr5[2]) + (d3 * dArr5[1]) + (d2 * dArr5[0])};
    }

    public static double rotationDirection(double d2, double d3) {
        return sanitizeDegreesDouble(d3 - d2) <= 180.0d ? 1.0d : -1.0d;
    }

    public static double sanitizeDegreesDouble(double d2) {
        double d3 = d2 % 360.0d;
        return d3 < 0.0d ? d3 + 360.0d : d3;
    }

    public static int sanitizeDegreesInt(int i3) {
        int i4 = i3 % 360;
        return i4 < 0 ? i4 + 360 : i4;
    }

    public static int signum(double d2) {
        if (d2 < 0.0d) {
            return -1;
        }
        return d2 == 0.0d ? 0 : 1;
    }
}
