package com.google.android.material.color.utilities;

import androidx.annotation.RestrictTo;
import androidx.core.view.ViewCompat;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class ColorUtils {
    static final double[][] SRGB_TO_XYZ = {new double[]{0.41233895d, 0.35762064d, 0.18051042d}, new double[]{0.2126d, 0.7152d, 0.0722d}, new double[]{0.01932141d, 0.11916382d, 0.95034478d}};
    static final double[] WHITE_POINT_D65 = {95.047d, 100.0d, 108.883d};
    static final double[][] XYZ_TO_SRGB = {new double[]{3.2413774792388685d, -1.5376652402851851d, -0.49885366846268053d}, new double[]{-0.9691452513005321d, 1.8758853451067872d, 0.04156585616912061d}, new double[]{0.05562093689691305d, -0.20395524564742123d, 1.0571799111220335d}};

    private ColorUtils() {
    }

    public static int alphaFromArgb(int i3) {
        return (i3 >> 24) & 255;
    }

    public static int argbFromLab(double d2, double d3, double d4) {
        double[] dArr = WHITE_POINT_D65;
        double d5 = (d2 + 16.0d) / 116.0d;
        double d6 = d5 - (d4 / 200.0d);
        return argbFromXyz(labInvf((d3 / 500.0d) + d5) * dArr[0], labInvf(d5) * dArr[1], labInvf(d6) * dArr[2]);
    }

    public static int argbFromLinrgb(double[] dArr) {
        return argbFromRgb(delinearized(dArr[0]), delinearized(dArr[1]), delinearized(dArr[2]));
    }

    public static int argbFromLstar(double d2) {
        int delinearized = delinearized(yFromLstar(d2));
        return argbFromRgb(delinearized, delinearized, delinearized);
    }

    public static int argbFromRgb(int i3, int i4, int i5) {
        return ((i3 & 255) << 16) | ViewCompat.MEASURED_STATE_MASK | ((i4 & 255) << 8) | (i5 & 255);
    }

    public static int argbFromXyz(double d2, double d3, double d4) {
        double[][] dArr = XYZ_TO_SRGB;
        double[] dArr2 = dArr[0];
        double d5 = dArr2[1] * d3;
        double d6 = (dArr2[2] * d4) + d5 + (dArr2[0] * d2);
        double[] dArr3 = dArr[1];
        double d7 = (dArr3[1] * d3) + (dArr3[0] * d2);
        double[] dArr4 = dArr[2];
        double d8 = dArr4[1] * d3;
        return argbFromRgb(delinearized(d6), delinearized((dArr3[2] * d4) + d7), delinearized((dArr4[2] * d4) + d8 + (dArr4[0] * d2)));
    }

    public static int blueFromArgb(int i3) {
        return i3 & 255;
    }

    public static int delinearized(double d2) {
        double d3 = d2 / 100.0d;
        return MathUtils.clampInt(0, 255, (int) Math.round((d3 <= 0.0031308d ? d3 * 12.92d : (Math.pow(d3, 0.4166666666666667d) * 1.055d) - 0.055d) * 255.0d));
    }

    public static int greenFromArgb(int i3) {
        return (i3 >> 8) & 255;
    }

    public static boolean isOpaque(int i3) {
        return alphaFromArgb(i3) >= 255;
    }

    public static double labF(double d2) {
        return d2 > 0.008856451679035631d ? Math.pow(d2, 0.3333333333333333d) : ((d2 * 903.2962962962963d) + 16.0d) / 116.0d;
    }

    public static double[] labFromArgb(int i3) {
        double linearized = linearized(redFromArgb(i3));
        double linearized2 = linearized(greenFromArgb(i3));
        double linearized3 = linearized(blueFromArgb(i3));
        double[][] dArr = SRGB_TO_XYZ;
        double[] dArr2 = dArr[0];
        double d2 = dArr2[1] * linearized2;
        double d3 = (dArr2[2] * linearized3) + d2 + (dArr2[0] * linearized);
        double[] dArr3 = dArr[1];
        double d4 = (dArr3[2] * linearized3) + (dArr3[1] * linearized2) + (dArr3[0] * linearized);
        double[] dArr4 = dArr[2];
        double d5 = dArr4[0] * linearized;
        double d6 = (dArr4[2] * linearized3) + (dArr4[1] * linearized2) + d5;
        double[] dArr5 = WHITE_POINT_D65;
        double d7 = d3 / dArr5[0];
        double d8 = d6 / dArr5[2];
        double labF = labF(d7);
        double labF2 = labF(d4 / dArr5[1]);
        return new double[]{(116.0d * labF2) - 16.0d, (labF - labF2) * 500.0d, (labF2 - labF(d8)) * 200.0d};
    }

    public static double labInvf(double d2) {
        double d3 = d2 * d2 * d2;
        return d3 > 0.008856451679035631d ? d3 : ((d2 * 116.0d) - 16.0d) / 903.2962962962963d;
    }

    public static double linearized(int i3) {
        double d2 = ((double) i3) / 255.0d;
        return (d2 <= 0.040449936d ? d2 / 12.92d : Math.pow((d2 + 0.055d) / 1.055d, 2.4d)) * 100.0d;
    }

    public static double lstarFromArgb(int i3) {
        return (labF(xyzFromArgb(i3)[1] / 100.0d) * 116.0d) - 16.0d;
    }

    public static double lstarFromY(double d2) {
        return (labF(d2 / 100.0d) * 116.0d) - 16.0d;
    }

    public static int redFromArgb(int i3) {
        return (i3 >> 16) & 255;
    }

    public static double[] whitePointD65() {
        return WHITE_POINT_D65;
    }

    public static double[] xyzFromArgb(int i3) {
        return MathUtils.matrixMultiply(new double[]{linearized(redFromArgb(i3)), linearized(greenFromArgb(i3)), linearized(blueFromArgb(i3))}, SRGB_TO_XYZ);
    }

    public static double yFromLstar(double d2) {
        return labInvf((d2 + 16.0d) / 116.0d) * 100.0d;
    }
}
