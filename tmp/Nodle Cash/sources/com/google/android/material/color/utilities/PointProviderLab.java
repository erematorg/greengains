package com.google.android.material.color.utilities;

import androidx.annotation.RestrictTo;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public final class PointProviderLab implements PointProvider {
    public double distance(double[] dArr, double[] dArr2) {
        double d2 = dArr[0] - dArr2[0];
        double d3 = dArr[1] - dArr2[1];
        double d4 = dArr[2] - dArr2[2];
        return (d4 * d4) + (d3 * d3) + (d2 * d2);
    }

    public double[] fromInt(int i3) {
        double[] labFromArgb = ColorUtils.labFromArgb(i3);
        return new double[]{labFromArgb[0], labFromArgb[1], labFromArgb[2]};
    }

    public int toInt(double[] dArr) {
        return ColorUtils.argbFromLab(dArr[0], dArr[1], dArr[2]);
    }
}
