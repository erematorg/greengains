package com.google.android.material.color.utilities;

import androidx.annotation.RestrictTo;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public final class Hct {
    private int argb;
    private double chroma;
    private double hue;
    private double tone;

    private Hct(int i3) {
        setInternalState(i3);
    }

    public static Hct from(double d2, double d3, double d4) {
        return new Hct(HctSolver.solveToInt(d2, d3, d4));
    }

    public static Hct fromInt(int i3) {
        return new Hct(i3);
    }

    private void setInternalState(int i3) {
        this.argb = i3;
        Cam16 fromInt = Cam16.fromInt(i3);
        this.hue = fromInt.getHue();
        this.chroma = fromInt.getChroma();
        this.tone = ColorUtils.lstarFromArgb(i3);
    }

    public double getChroma() {
        return this.chroma;
    }

    public double getHue() {
        return this.hue;
    }

    public double getTone() {
        return this.tone;
    }

    public Hct inViewingConditions(ViewingConditions viewingConditions) {
        double[] xyzInViewingConditions = Cam16.fromInt(toInt()).xyzInViewingConditions(viewingConditions, (double[]) null);
        Cam16 fromXyzInViewingConditions = Cam16.fromXyzInViewingConditions(xyzInViewingConditions[0], xyzInViewingConditions[1], xyzInViewingConditions[2], ViewingConditions.DEFAULT);
        return from(fromXyzInViewingConditions.getHue(), fromXyzInViewingConditions.getChroma(), ColorUtils.lstarFromY(xyzInViewingConditions[1]));
    }

    public void setChroma(double d2) {
        setInternalState(HctSolver.solveToInt(this.hue, d2, this.tone));
    }

    public void setHue(double d2) {
        setInternalState(HctSolver.solveToInt(d2, this.chroma, this.tone));
    }

    public void setTone(double d2) {
        setInternalState(HctSolver.solveToInt(this.hue, this.chroma, d2));
    }

    public int toInt() {
        return this.argb;
    }
}
