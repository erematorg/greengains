package com.google.android.material.color.utilities;

import androidx.annotation.RestrictTo;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public final class Cam16 {
    static final double[][] CAM16RGB_TO_XYZ = {new double[]{1.8620678d, -1.0112547d, 0.14918678d}, new double[]{0.38752654d, 0.62144744d, -0.00897398d}, new double[]{-0.0158415d, -0.03412294d, 1.0499644d}};
    static final double[][] XYZ_TO_CAM16RGB = {new double[]{0.401288d, 0.650173d, -0.051461d}, new double[]{-0.250268d, 1.204414d, 0.045854d}, new double[]{-0.002079d, 0.048952d, 0.953127d}};
    private final double astar;
    private final double bstar;
    private final double chroma;
    private final double hue;

    /* renamed from: j  reason: collision with root package name */
    private final double f6628j;
    private final double jstar;

    /* renamed from: m  reason: collision with root package name */
    private final double f6629m;

    /* renamed from: q  reason: collision with root package name */
    private final double f6630q;

    /* renamed from: s  reason: collision with root package name */
    private final double f6631s;
    private final double[] tempArray = {0.0d, 0.0d, 0.0d};

    private Cam16(double d2, double d3, double d4, double d5, double d6, double d7, double d8, double d9, double d10) {
        this.hue = d2;
        this.chroma = d3;
        this.f6628j = d4;
        this.f6630q = d5;
        this.f6629m = d6;
        this.f6631s = d7;
        this.jstar = d8;
        this.astar = d9;
        this.bstar = d10;
    }

    public static Cam16 fromInt(int i3) {
        return fromIntInViewingConditions(i3, ViewingConditions.DEFAULT);
    }

    public static Cam16 fromIntInViewingConditions(int i3, ViewingConditions viewingConditions) {
        int i4 = i3;
        double linearized = ColorUtils.linearized((16711680 & i4) >> 16);
        double linearized2 = ColorUtils.linearized((65280 & i4) >> 8);
        double linearized3 = ColorUtils.linearized(i4 & 255);
        return fromXyzInViewingConditions((0.18051042d * linearized3) + (0.35762064d * linearized2) + (0.41233895d * linearized), (0.0722d * linearized3) + (0.7152d * linearized2) + (0.2126d * linearized), (linearized3 * 0.95034478d) + (linearized2 * 0.11916382d) + (linearized * 0.01932141d), viewingConditions);
    }

    public static Cam16 fromJch(double d2, double d3, double d4) {
        return fromJchInViewingConditions(d2, d3, d4, ViewingConditions.DEFAULT);
    }

    private static Cam16 fromJchInViewingConditions(double d2, double d3, double d4, ViewingConditions viewingConditions) {
        double d5 = d3;
        double d6 = d4;
        double d7 = d2 / 100.0d;
        double aw = (viewingConditions.getAw() + 4.0d) * Math.sqrt(d7) * (4.0d / viewingConditions.getC()) * viewingConditions.getFlRoot();
        double flRoot = viewingConditions.getFlRoot() * d3;
        double c3 = viewingConditions.getC();
        double radians = Math.toRadians(d4);
        double d8 = (1.7000000000000002d * d2) / ((0.007d * d2) + 1.0d);
        double log1p = 43.859649122807014d * Math.log1p(flRoot * 0.0228d);
        return new Cam16(d6, d5, d2, aw, flRoot, Math.sqrt((c3 * (d3 / Math.sqrt(d7))) / (viewingConditions.getAw() + 4.0d)) * 50.0d, d8, Math.cos(radians) * log1p, Math.sin(radians) * log1p);
    }

    public static Cam16 fromUcs(double d2, double d3, double d4) {
        return fromUcsInViewingConditions(d2, d3, d4, ViewingConditions.DEFAULT);
    }

    public static Cam16 fromUcsInViewingConditions(double d2, double d3, double d4, ViewingConditions viewingConditions) {
        double expm1 = (Math.expm1(Math.hypot(d3, d4) * 0.0228d) / 0.0228d) / viewingConditions.getFlRoot();
        double d5 = d3;
        double atan2 = Math.atan2(d4, d3) * 57.29577951308232d;
        if (atan2 < 0.0d) {
            atan2 += 360.0d;
        }
        return fromJchInViewingConditions(d2 / (1.0d - ((d2 - 100.0d) * 0.007d)), expm1, atan2, viewingConditions);
    }

    public static Cam16 fromXyzInViewingConditions(double d2, double d3, double d4, ViewingConditions viewingConditions) {
        double[][] dArr = XYZ_TO_CAM16RGB;
        double[] dArr2 = dArr[0];
        double d5 = (dArr2[2] * d4) + (dArr2[1] * d3) + (dArr2[0] * d2);
        double[] dArr3 = dArr[1];
        double d6 = (dArr3[2] * d4) + (dArr3[1] * d3) + (dArr3[0] * d2);
        double[] dArr4 = dArr[2];
        double d7 = (dArr4[1] * d3) + (dArr4[0] * d2);
        double d8 = viewingConditions.getRgbD()[0] * d5;
        double d9 = viewingConditions.getRgbD()[1] * d6;
        double d10 = viewingConditions.getRgbD()[2] * ((dArr4[2] * d4) + d7);
        double pow = Math.pow((Math.abs(d8) * viewingConditions.getFl()) / 100.0d, 0.42d);
        double pow2 = Math.pow((Math.abs(d9) * viewingConditions.getFl()) / 100.0d, 0.42d);
        double pow3 = Math.pow((Math.abs(d10) * viewingConditions.getFl()) / 100.0d, 0.42d);
        double signum = ((Math.signum(d8) * 400.0d) * pow) / (pow + 27.13d);
        double signum2 = ((Math.signum(d9) * 400.0d) * pow2) / (pow2 + 27.13d);
        double signum3 = ((Math.signum(d10) * 400.0d) * pow3) / (pow3 + 27.13d);
        double d11 = (((-12.0d * signum2) + (signum * 11.0d)) + signum3) / 11.0d;
        double d12 = ((signum + signum2) - (signum3 * 2.0d)) / 9.0d;
        double d13 = signum2 * 20.0d;
        double d14 = ((21.0d * signum3) + ((signum * 20.0d) + d13)) / 20.0d;
        double d15 = (((signum * 40.0d) + d13) + signum3) / 20.0d;
        double degrees = Math.toDegrees(Math.atan2(d12, d11));
        if (degrees < 0.0d) {
            degrees += 360.0d;
        } else if (degrees >= 360.0d) {
            degrees -= 360.0d;
        }
        double d16 = degrees;
        double radians = Math.toRadians(d16);
        double pow4 = Math.pow((viewingConditions.getNbb() * d15) / viewingConditions.getAw(), viewingConditions.getC() * viewingConditions.getZ()) * 100.0d;
        double d17 = pow4 / 100.0d;
        double flRoot = viewingConditions.getFlRoot() * (viewingConditions.getAw() + 4.0d) * Math.sqrt(d17) * (4.0d / viewingConditions.getC());
        double nc = viewingConditions.getNc() * (Math.cos(Math.toRadians(d16 < 20.14d ? d16 + 360.0d : d16) + 2.0d) + 3.8d) * 0.25d * 3846.153846153846d;
        double pow5 = Math.pow((Math.hypot(d11, d12) * (viewingConditions.getNcb() * nc)) / (d14 + 0.305d), 0.9d) * Math.pow(1.64d - Math.pow(0.29d, viewingConditions.getN()), 0.73d);
        double sqrt = Math.sqrt(d17) * pow5;
        double d18 = sqrt;
        double flRoot2 = viewingConditions.getFlRoot() * sqrt;
        double d19 = flRoot2;
        double sqrt2 = Math.sqrt((viewingConditions.getC() * pow5) / (viewingConditions.getAw() + 4.0d)) * 50.0d;
        double d20 = (1.7000000000000002d * pow4) / ((0.007d * pow4) + 1.0d);
        double log1p = Math.log1p(flRoot2 * 0.0228d) * 43.859649122807014d;
        return new Cam16(d16, d18, pow4, flRoot, d19, sqrt2, d20, Math.cos(radians) * log1p, Math.sin(radians) * log1p);
    }

    public double distance(Cam16 cam16) {
        double jstar2 = getJstar() - cam16.getJstar();
        double astar2 = getAstar() - cam16.getAstar();
        double bstar2 = getBstar() - cam16.getBstar();
        return Math.pow(Math.sqrt((bstar2 * bstar2) + (astar2 * astar2) + (jstar2 * jstar2)), 0.63d) * 1.41d;
    }

    public double getAstar() {
        return this.astar;
    }

    public double getBstar() {
        return this.bstar;
    }

    public double getChroma() {
        return this.chroma;
    }

    public double getHue() {
        return this.hue;
    }

    public double getJ() {
        return this.f6628j;
    }

    public double getJstar() {
        return this.jstar;
    }

    public double getM() {
        return this.f6629m;
    }

    public double getQ() {
        return this.f6630q;
    }

    public double getS() {
        return this.f6631s;
    }

    public int toInt() {
        return viewed(ViewingConditions.DEFAULT);
    }

    public int viewed(ViewingConditions viewingConditions) {
        double[] xyzInViewingConditions = xyzInViewingConditions(viewingConditions, this.tempArray);
        return ColorUtils.argbFromXyz(xyzInViewingConditions[0], xyzInViewingConditions[1], xyzInViewingConditions[2]);
    }

    public double[] xyzInViewingConditions(ViewingConditions viewingConditions, double[] dArr) {
        double pow = Math.pow(((getChroma() == 0.0d || getJ() == 0.0d) ? 0.0d : getChroma() / Math.sqrt(getJ() / 100.0d)) / Math.pow(1.64d - Math.pow(0.29d, viewingConditions.getN()), 0.73d), 1.1111111111111112d);
        double radians = Math.toRadians(getHue());
        double aw = viewingConditions.getAw();
        double ncb = viewingConditions.getNcb() * viewingConditions.getNc() * (Math.cos(2.0d + radians) + 3.8d) * 0.25d * 3846.153846153846d;
        double pow2 = (Math.pow(getJ() / 100.0d, (1.0d / viewingConditions.getC()) / viewingConditions.getZ()) * aw) / viewingConditions.getNbb();
        double sin = Math.sin(radians);
        double cos = Math.cos(radians);
        double d2 = (((0.305d + pow2) * 23.0d) * pow) / (((pow * 108.0d) * sin) + (((11.0d * pow) * cos) + (ncb * 23.0d)));
        double d3 = cos * d2;
        double d4 = d2 * sin;
        double d5 = pow2 * 460.0d;
        double d6 = ((288.0d * d4) + ((451.0d * d3) + d5)) / 1403.0d;
        double d7 = ((d5 - (891.0d * d3)) - (261.0d * d4)) / 1403.0d;
        double d8 = ((d5 - (d3 * 220.0d)) - (d4 * 6300.0d)) / 1403.0d;
        double pow3 = Math.pow(Math.max(0.0d, (Math.abs(d6) * 27.13d) / (400.0d - Math.abs(d6))), 2.380952380952381d) * (100.0d / viewingConditions.getFl()) * Math.signum(d6);
        double max = Math.max(0.0d, (Math.abs(d7) * 27.13d) / (400.0d - Math.abs(d7)));
        double fl = (100.0d / viewingConditions.getFl()) * Math.signum(d7);
        double max2 = Math.max(0.0d, (Math.abs(d8) * 27.13d) / (400.0d - Math.abs(d8)));
        double fl2 = (100.0d / viewingConditions.getFl()) * Math.signum(d8);
        double d9 = pow3 / viewingConditions.getRgbD()[0];
        double pow4 = (Math.pow(max, 2.380952380952381d) * fl) / viewingConditions.getRgbD()[1];
        double pow5 = (Math.pow(max2, 2.380952380952381d) * fl2) / viewingConditions.getRgbD()[2];
        double[][] dArr2 = CAM16RGB_TO_XYZ;
        double[] dArr3 = dArr2[0];
        double d10 = (dArr3[2] * pow5) + (dArr3[1] * pow4) + (dArr3[0] * d9);
        double[] dArr4 = dArr2[1];
        double d11 = (dArr4[2] * pow5) + (dArr4[1] * pow4) + (dArr4[0] * d9);
        double[] dArr5 = dArr2[2];
        double d12 = (pow5 * dArr5[2]) + (pow4 * dArr5[1]) + (d9 * dArr5[0]);
        if (dArr != null) {
            dArr[0] = d10;
            dArr[1] = d11;
            dArr[2] = d12;
            return dArr;
        }
        return new double[]{d10, d11, d12};
    }
}
