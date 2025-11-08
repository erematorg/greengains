package com.google.android.material.color.utilities;

import androidx.annotation.RestrictTo;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public final class ViewingConditions {
    public static final ViewingConditions DEFAULT = defaultWithBackgroundLstar(50.0d);
    private final double aw;

    /* renamed from: c  reason: collision with root package name */
    private final double f6640c;
    private final double fl;
    private final double flRoot;

    /* renamed from: n  reason: collision with root package name */
    private final double f6641n;
    private final double nbb;
    private final double nc;
    private final double ncb;
    private final double[] rgbD;

    /* renamed from: z  reason: collision with root package name */
    private final double f6642z;

    private ViewingConditions(double d2, double d3, double d4, double d5, double d6, double d7, double[] dArr, double d8, double d9, double d10) {
        this.f6641n = d2;
        this.aw = d3;
        this.nbb = d4;
        this.ncb = d5;
        this.f6640c = d6;
        this.nc = d7;
        this.rgbD = dArr;
        this.fl = d8;
        this.flRoot = d9;
        this.f6642z = d10;
    }

    public static ViewingConditions defaultWithBackgroundLstar(double d2) {
        return make(ColorUtils.whitePointD65(), (ColorUtils.yFromLstar(50.0d) * 63.66197723675813d) / 100.0d, d2, 2.0d, false);
    }

    public static ViewingConditions make(double[] dArr, double d2, double d3, double d4, boolean z2) {
        double d5 = d2;
        double max = Math.max(0.1d, d3);
        double[][] dArr2 = Cam16.XYZ_TO_CAM16RGB;
        double d6 = dArr[0];
        double[] dArr3 = dArr2[0];
        double d7 = dArr[1];
        double d8 = dArr3[1] * d7;
        double d9 = dArr[2];
        double d10 = (dArr3[2] * d9) + d8 + (dArr3[0] * d6);
        double[] dArr4 = dArr2[1];
        double d11 = (dArr4[2] * d9) + (dArr4[1] * d7) + (dArr4[0] * d6);
        double[] dArr5 = dArr2[2];
        double d12 = (d9 * dArr5[2]) + (d7 * dArr5[1]) + (d6 * dArr5[0]);
        double d13 = (d4 / 10.0d) + 0.8d;
        double lerp = d13 >= 0.9d ? MathUtils.lerp(0.59d, 0.69d, (d13 - 0.9d) * 10.0d) : MathUtils.lerp(0.525d, 0.59d, (d13 - 0.8d) * 10.0d);
        double clampDouble = MathUtils.clampDouble(0.0d, 1.0d, z2 ? 1.0d : (1.0d - (Math.exp(((-d5) - 42.0d) / 92.0d) * 0.2777777777777778d)) * d13);
        double[] dArr6 = {(((100.0d / d10) * clampDouble) + 1.0d) - clampDouble, (((100.0d / d11) * clampDouble) + 1.0d) - clampDouble, (((100.0d / d12) * clampDouble) + 1.0d) - clampDouble};
        double d14 = 5.0d * d5;
        double d15 = 1.0d / (d14 + 1.0d);
        double d16 = d15 * d15 * d15 * d15;
        double d17 = 1.0d - d16;
        double cbrt = (Math.cbrt(d14) * 0.1d * d17 * d17) + (d16 * d5);
        double yFromLstar = ColorUtils.yFromLstar(max) / dArr[1];
        double d18 = yFromLstar;
        double sqrt = Math.sqrt(yFromLstar) + 1.48d;
        double pow = 0.725d / Math.pow(yFromLstar, 0.2d);
        double d19 = pow;
        double d20 = pow;
        double[] dArr7 = {Math.pow(((dArr6[0] * cbrt) * d10) / 100.0d, 0.42d), Math.pow(((dArr6[1] * cbrt) * d11) / 100.0d, 0.42d), Math.pow(((dArr6[2] * cbrt) * d12) / 100.0d, 0.42d)};
        double d21 = dArr7[0];
        double d22 = (d21 * 400.0d) / (d21 + 27.13d);
        double d23 = dArr7[1];
        double d24 = (d23 * 400.0d) / (d23 + 27.13d);
        double d25 = dArr7[2];
        double[] dArr8 = {d22, d24, (400.0d * d25) / (d25 + 27.13d)};
        return new ViewingConditions(d18, ((dArr8[2] * 0.05d) + (dArr8[0] * 2.0d) + dArr8[1]) * pow, d19, d20, lerp, d13, dArr6, cbrt, Math.pow(cbrt, 0.25d), sqrt);
    }

    public double getAw() {
        return this.aw;
    }

    public double getC() {
        return this.f6640c;
    }

    public double getFl() {
        return this.fl;
    }

    public double getFlRoot() {
        return this.flRoot;
    }

    public double getN() {
        return this.f6641n;
    }

    public double getNbb() {
        return this.nbb;
    }

    public double getNc() {
        return this.nc;
    }

    public double getNcb() {
        return this.ncb;
    }

    public double[] getRgbD() {
        return this.rgbD;
    }

    public double getZ() {
        return this.f6642z;
    }
}
