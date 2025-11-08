package com.google.android.material.color.utilities;

import androidx.annotation.RestrictTo;
import java.util.HashMap;
import java.util.Map;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public final class TonalPalette {
    Map<Integer, Integer> cache = new HashMap();
    double chroma;
    double hue;
    Hct keyColor;

    private TonalPalette(double d2, double d3, Hct hct) {
        this.hue = d2;
        this.chroma = d3;
        this.keyColor = hct;
    }

    private static Hct createKeyColor(double d2, double d3) {
        Hct from = Hct.from(d2, d3, 50.0d);
        Hct hct = from;
        double abs = Math.abs(from.getChroma() - d3);
        for (double d4 = 1.0d; d4 < 50.0d && Math.round(d3) != Math.round(hct.getChroma()); d4 += 1.0d) {
            Hct from2 = Hct.from(d2, d3, 50.0d + d4);
            double abs2 = Math.abs(from2.getChroma() - d3);
            if (abs2 < abs) {
                hct = from2;
                abs = abs2;
            }
            Hct from3 = Hct.from(d2, d3, 50.0d - d4);
            double abs3 = Math.abs(from3.getChroma() - d3);
            if (abs3 < abs) {
                hct = from3;
                abs = abs3;
            }
        }
        return hct;
    }

    public static TonalPalette fromHct(Hct hct) {
        return new TonalPalette(hct.getHue(), hct.getChroma(), hct);
    }

    public static TonalPalette fromHueAndChroma(double d2, double d3) {
        return new TonalPalette(d2, d3, createKeyColor(d2, d3));
    }

    public static TonalPalette fromInt(int i3) {
        return fromHct(Hct.fromInt(i3));
    }

    public double getChroma() {
        return this.chroma;
    }

    public Hct getHct(double d2) {
        return Hct.from(this.hue, this.chroma, d2);
    }

    public double getHue() {
        return this.hue;
    }

    public Hct getKeyColor() {
        return this.keyColor;
    }

    public int tone(int i3) {
        Integer num = this.cache.get(Integer.valueOf(i3));
        if (num == null) {
            num = Integer.valueOf(Hct.from(this.hue, this.chroma, (double) i3).toInt());
            this.cache.put(Integer.valueOf(i3), num);
        }
        return num.intValue();
    }
}
