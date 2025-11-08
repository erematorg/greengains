package com.google.android.material.color.utilities;

import B1.C0221a;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import java.util.HashMap;
import java.util.function.Function;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public final class DynamicColor {
    public final Function<DynamicScheme, DynamicColor> background;
    public final ContrastCurve contrastCurve;
    private final HashMap<DynamicScheme, Hct> hctCache = new HashMap<>();
    public final boolean isBackground;
    public final String name;
    public final Function<DynamicScheme, Double> opacity;
    public final Function<DynamicScheme, TonalPalette> palette;
    public final Function<DynamicScheme, DynamicColor> secondBackground;
    public final Function<DynamicScheme, Double> tone;
    public final Function<DynamicScheme, ToneDeltaPair> toneDeltaPair;

    public DynamicColor(@NonNull String str, @NonNull Function<DynamicScheme, TonalPalette> function, @NonNull Function<DynamicScheme, Double> function2, boolean z2, @Nullable Function<DynamicScheme, DynamicColor> function3, @Nullable Function<DynamicScheme, DynamicColor> function4, @Nullable ContrastCurve contrastCurve2, @Nullable Function<DynamicScheme, ToneDeltaPair> function5) {
        this.name = str;
        this.palette = function;
        this.tone = function2;
        this.isBackground = z2;
        this.background = function3;
        this.secondBackground = function4;
        this.contrastCurve = contrastCurve2;
        this.toneDeltaPair = function5;
        this.opacity = null;
    }

    public static double enableLightForeground(double d2) {
        if (!tonePrefersLightForeground(d2) || toneAllowsLightForeground(d2)) {
            return d2;
        }
        return 49.0d;
    }

    public static double foregroundTone(double d2, double d3) {
        double lighterUnsafe = Contrast.lighterUnsafe(d2, d3);
        double darkerUnsafe = Contrast.darkerUnsafe(d2, d3);
        double ratioOfTones = Contrast.ratioOfTones(lighterUnsafe, d2);
        double ratioOfTones2 = Contrast.ratioOfTones(darkerUnsafe, d2);
        if (!tonePrefersLightForeground(d2)) {
            return (ratioOfTones2 >= d3 || ratioOfTones2 >= ratioOfTones) ? darkerUnsafe : lighterUnsafe;
        }
        return (ratioOfTones >= d3 || ratioOfTones >= ratioOfTones2 || ((Math.abs(ratioOfTones - ratioOfTones2) > 0.1d ? 1 : (Math.abs(ratioOfTones - ratioOfTones2) == 0.1d ? 0 : -1)) < 0 && (ratioOfTones > d3 ? 1 : (ratioOfTones == d3 ? 0 : -1)) < 0 && (ratioOfTones2 > d3 ? 1 : (ratioOfTones2 == d3 ? 0 : -1)) < 0)) ? lighterUnsafe : darkerUnsafe;
    }

    @NonNull
    public static DynamicColor fromArgb(@NonNull String str, int i3) {
        return fromPalette(str, new C0221a(TonalPalette.fromInt(i3), 2), new C0221a(Hct.fromInt(i3), 3));
    }

    @NonNull
    public static DynamicColor fromPalette(@NonNull String str, @NonNull Function<DynamicScheme, TonalPalette> function, @NonNull Function<DynamicScheme, Double> function2) {
        return new DynamicColor(str, function, function2, false, (Function<DynamicScheme, DynamicColor>) null, (Function<DynamicScheme, DynamicColor>) null, (ContrastCurve) null, (Function<DynamicScheme, ToneDeltaPair>) null);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ TonalPalette lambda$fromArgb$0(TonalPalette tonalPalette, DynamicScheme dynamicScheme) {
        return tonalPalette;
    }

    public static boolean toneAllowsLightForeground(double d2) {
        return Math.round(d2) <= 49;
    }

    public static boolean tonePrefersLightForeground(double d2) {
        return Math.round(d2) < 60;
    }

    public int getArgb(@NonNull DynamicScheme dynamicScheme) {
        int i3 = getHct(dynamicScheme).toInt();
        Function<DynamicScheme, Double> function = this.opacity;
        if (function == null) {
            return i3;
        }
        return (MathUtils.clampInt(0, 255, (int) Math.round(function.apply(dynamicScheme).doubleValue() * 255.0d)) << 24) | (16777215 & i3);
    }

    @NonNull
    public Hct getHct(@NonNull DynamicScheme dynamicScheme) {
        Hct hct = this.hctCache.get(dynamicScheme);
        if (hct != null) {
            return hct;
        }
        Hct hct2 = this.palette.apply(dynamicScheme).getHct(getTone(dynamicScheme));
        if (this.hctCache.size() > 4) {
            this.hctCache.clear();
        }
        this.hctCache.put(dynamicScheme, hct2);
        return hct2;
    }

    /* JADX WARNING: Removed duplicated region for block: B:113:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x0151  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public double getTone(@androidx.annotation.NonNull com.google.android.material.color.utilities.DynamicScheme r31) {
        /*
            r30 = this;
            r0 = r30
            r1 = r31
            double r2 = r1.contrastLevel
            r4 = 0
            int r2 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r2 >= 0) goto L_0x000e
            r2 = 1
            goto L_0x000f
        L_0x000e:
            r2 = 0
        L_0x000f:
            java.util.function.Function<com.google.android.material.color.utilities.DynamicScheme, com.google.android.material.color.utilities.ToneDeltaPair> r7 = r0.toneDeltaPair
            if (r7 == 0) goto L_0x0153
            java.lang.Object r7 = r7.apply(r1)
            com.google.android.material.color.utilities.ToneDeltaPair r7 = (com.google.android.material.color.utilities.ToneDeltaPair) r7
            com.google.android.material.color.utilities.DynamicColor r16 = r7.getRoleA()
            com.google.android.material.color.utilities.DynamicColor r17 = r7.getRoleB()
            double r18 = r7.getDelta()
            com.google.android.material.color.utilities.TonePolarity r3 = r7.getPolarity()
            boolean r7 = r7.getStayTogether()
            java.util.function.Function<com.google.android.material.color.utilities.DynamicScheme, com.google.android.material.color.utilities.DynamicColor> r6 = r0.background
            java.lang.Object r6 = r6.apply(r1)
            com.google.android.material.color.utilities.DynamicColor r6 = (com.google.android.material.color.utilities.DynamicColor) r6
            double r10 = r6.getTone(r1)
            com.google.android.material.color.utilities.TonePolarity r6 = com.google.android.material.color.utilities.TonePolarity.NEARER
            if (r3 == r6) goto L_0x0050
            com.google.android.material.color.utilities.TonePolarity r6 = com.google.android.material.color.utilities.TonePolarity.LIGHTER
            if (r3 != r6) goto L_0x0045
            boolean r6 = r1.isDark
            if (r6 == 0) goto L_0x0050
        L_0x0045:
            com.google.android.material.color.utilities.TonePolarity r6 = com.google.android.material.color.utilities.TonePolarity.DARKER
            if (r3 != r6) goto L_0x004e
            boolean r3 = r1.isDark
            if (r3 == 0) goto L_0x004e
            goto L_0x0050
        L_0x004e:
            r3 = 0
            goto L_0x0051
        L_0x0050:
            r3 = 1
        L_0x0051:
            if (r3 == 0) goto L_0x0056
            r6 = r16
            goto L_0x0058
        L_0x0056:
            r6 = r17
        L_0x0058:
            if (r3 == 0) goto L_0x005d
            r3 = r17
            goto L_0x005f
        L_0x005d:
            r3 = r16
        L_0x005f:
            java.lang.String r0 = r0.name
            java.lang.String r12 = r6.name
            boolean r0 = r0.equals(r12)
            boolean r12 = r1.isDark
            if (r12 == 0) goto L_0x0070
            r12 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            r22 = r12
            goto L_0x0072
        L_0x0070:
            r22 = -4616189618054758400(0xbff0000000000000, double:-1.0)
        L_0x0072:
            com.google.android.material.color.utilities.ContrastCurve r12 = r6.contrastCurve
            double r4 = r1.contrastLevel
            double r4 = r12.getContrast(r4)
            com.google.android.material.color.utilities.ContrastCurve r12 = r3.contrastCurve
            double r14 = r1.contrastLevel
            double r12 = r12.getContrast(r14)
            java.util.function.Function<com.google.android.material.color.utilities.DynamicScheme, java.lang.Double> r6 = r6.tone
            java.lang.Object r6 = r6.apply(r1)
            java.lang.Double r6 = (java.lang.Double) r6
            double r14 = r6.doubleValue()
            double r20 = com.google.android.material.color.utilities.Contrast.ratioOfTones(r10, r14)
            int r6 = (r20 > r4 ? 1 : (r20 == r4 ? 0 : -1))
            if (r6 < 0) goto L_0x0097
            goto L_0x009b
        L_0x0097:
            double r14 = foregroundTone(r10, r4)
        L_0x009b:
            java.util.function.Function<com.google.android.material.color.utilities.DynamicScheme, java.lang.Double> r3 = r3.tone
            java.lang.Object r1 = r3.apply(r1)
            java.lang.Double r1 = (java.lang.Double) r1
            double r8 = r1.doubleValue()
            double r20 = com.google.android.material.color.utilities.Contrast.ratioOfTones(r10, r8)
            int r1 = (r20 > r12 ? 1 : (r20 == r12 ? 0 : -1))
            if (r1 < 0) goto L_0x00b0
            goto L_0x00b4
        L_0x00b0:
            double r8 = foregroundTone(r10, r12)
        L_0x00b4:
            if (r2 == 0) goto L_0x00be
            double r14 = foregroundTone(r10, r4)
            double r8 = foregroundTone(r10, r12)
        L_0x00be:
            double r1 = r8 - r14
            double r1 = r1 * r22
            int r1 = (r1 > r18 ? 1 : (r1 == r18 ? 0 : -1))
            if (r1 >= 0) goto L_0x00e4
            double r1 = r18 * r22
            double r12 = r14 + r1
            r8 = 0
            r10 = 4636737291354636288(0x4059000000000000, double:100.0)
            double r8 = com.google.android.material.color.utilities.MathUtils.clampDouble(r8, r10, r12)
            double r3 = r8 - r14
            double r3 = r3 * r22
            int r3 = (r3 > r18 ? 1 : (r3 == r18 ? 0 : -1))
            if (r3 >= 0) goto L_0x00e4
            r26 = 4636737291354636288(0x4059000000000000, double:100.0)
            double r28 = r8 - r1
            r24 = 0
            double r14 = com.google.android.material.color.utilities.MathUtils.clampDouble(r24, r26, r28)
        L_0x00e4:
            r1 = 4632233691727265792(0x4049000000000000, double:50.0)
            int r3 = (r1 > r14 ? 1 : (r1 == r14 ? 0 : -1))
            r1 = 4633641066610819072(0x404e000000000000, double:60.0)
            if (r3 > 0) goto L_0x0114
            int r3 = (r14 > r1 ? 1 : (r14 == r1 ? 0 : -1))
            if (r3 >= 0) goto L_0x0114
            r3 = 0
            int r3 = (r22 > r3 ? 1 : (r22 == r3 ? 0 : -1))
            if (r3 <= 0) goto L_0x0100
            double r18 = r18 * r22
            double r3 = r18 + r1
            double r12 = java.lang.Math.max(r8, r3)
            r14 = r1
            goto L_0x014f
        L_0x0100:
            double r18 = r18 * r22
            r1 = 4632092954238910464(0x4048800000000000, double:49.0)
            double r3 = r18 + r1
            double r1 = java.lang.Math.min(r8, r3)
        L_0x010d:
            r12 = r1
            r14 = 4632092954238910464(0x4048800000000000, double:49.0)
            goto L_0x014f
        L_0x0114:
            r3 = 4632233691727265792(0x4049000000000000, double:50.0)
            int r3 = (r3 > r8 ? 1 : (r3 == r8 ? 0 : -1))
            if (r3 > 0) goto L_0x014e
            int r3 = (r8 > r1 ? 1 : (r8 == r1 ? 0 : -1))
            if (r3 >= 0) goto L_0x014e
            if (r7 == 0) goto L_0x013f
            r3 = 0
            int r3 = (r22 > r3 ? 1 : (r22 == r3 ? 0 : -1))
            if (r3 <= 0) goto L_0x0131
            double r18 = r18 * r22
            double r3 = r18 + r1
            double r12 = java.lang.Math.max(r8, r3)
            r14 = 4633641066610819072(0x404e000000000000, double:60.0)
            goto L_0x014f
        L_0x0131:
            double r18 = r18 * r22
            r1 = 4632092954238910464(0x4048800000000000, double:49.0)
            double r3 = r18 + r1
            double r1 = java.lang.Math.min(r8, r3)
            goto L_0x010d
        L_0x013f:
            r3 = 0
            int r1 = (r22 > r3 ? 1 : (r22 == r3 ? 0 : -1))
            if (r1 <= 0) goto L_0x0148
            r12 = 4633641066610819072(0x404e000000000000, double:60.0)
            goto L_0x014f
        L_0x0148:
            r12 = 4632092954238910464(0x4048800000000000, double:49.0)
            goto L_0x014f
        L_0x014e:
            r12 = r8
        L_0x014f:
            if (r0 == 0) goto L_0x0152
            r12 = r14
        L_0x0152:
            return r12
        L_0x0153:
            r3 = r4
            java.util.function.Function<com.google.android.material.color.utilities.DynamicScheme, java.lang.Double> r5 = r0.tone
            java.lang.Object r5 = r5.apply(r1)
            java.lang.Double r5 = (java.lang.Double) r5
            double r5 = r5.doubleValue()
            java.util.function.Function<com.google.android.material.color.utilities.DynamicScheme, com.google.android.material.color.utilities.DynamicColor> r7 = r0.background
            if (r7 != 0) goto L_0x0165
            return r5
        L_0x0165:
            java.lang.Object r7 = r7.apply(r1)
            com.google.android.material.color.utilities.DynamicColor r7 = (com.google.android.material.color.utilities.DynamicColor) r7
            double r7 = r7.getTone(r1)
            com.google.android.material.color.utilities.ContrastCurve r9 = r0.contrastCurve
            double r10 = r1.contrastLevel
            double r9 = r9.getContrast(r10)
            double r11 = com.google.android.material.color.utilities.Contrast.ratioOfTones(r7, r5)
            int r11 = (r11 > r9 ? 1 : (r11 == r9 ? 0 : -1))
            if (r11 < 0) goto L_0x0180
            goto L_0x0184
        L_0x0180:
            double r5 = foregroundTone(r7, r9)
        L_0x0184:
            if (r2 == 0) goto L_0x018a
            double r5 = foregroundTone(r7, r9)
        L_0x018a:
            boolean r2 = r0.isBackground
            if (r2 == 0) goto L_0x01ab
            r11 = 4632233691727265792(0x4049000000000000, double:50.0)
            int r2 = (r11 > r5 ? 1 : (r11 == r5 ? 0 : -1))
            if (r2 > 0) goto L_0x01ab
            r11 = 4633641066610819072(0x404e000000000000, double:60.0)
            int r2 = (r5 > r11 ? 1 : (r5 == r11 ? 0 : -1))
            if (r2 >= 0) goto L_0x01ab
            r13 = 4632092954238910464(0x4048800000000000, double:49.0)
            double r5 = com.google.android.material.color.utilities.Contrast.ratioOfTones(r13, r7)
            int r2 = (r5 > r9 ? 1 : (r5 == r9 ? 0 : -1))
            if (r2 < 0) goto L_0x01a9
            r12 = r13
            goto L_0x01ac
        L_0x01a9:
            r12 = r11
            goto L_0x01ac
        L_0x01ab:
            r12 = r5
        L_0x01ac:
            java.util.function.Function<com.google.android.material.color.utilities.DynamicScheme, com.google.android.material.color.utilities.DynamicColor> r2 = r0.secondBackground
            if (r2 == 0) goto L_0x0231
            java.util.function.Function<com.google.android.material.color.utilities.DynamicScheme, com.google.android.material.color.utilities.DynamicColor> r2 = r0.background
            java.lang.Object r2 = r2.apply(r1)
            com.google.android.material.color.utilities.DynamicColor r2 = (com.google.android.material.color.utilities.DynamicColor) r2
            double r5 = r2.getTone(r1)
            java.util.function.Function<com.google.android.material.color.utilities.DynamicScheme, com.google.android.material.color.utilities.DynamicColor> r0 = r0.secondBackground
            java.lang.Object r0 = r0.apply(r1)
            com.google.android.material.color.utilities.DynamicColor r0 = (com.google.android.material.color.utilities.DynamicColor) r0
            double r0 = r0.getTone(r1)
            double r7 = java.lang.Math.max(r5, r0)
            double r14 = java.lang.Math.min(r5, r0)
            double r16 = com.google.android.material.color.utilities.Contrast.ratioOfTones(r7, r12)
            int r2 = (r16 > r9 ? 1 : (r16 == r9 ? 0 : -1))
            if (r2 < 0) goto L_0x01e1
            double r16 = com.google.android.material.color.utilities.Contrast.ratioOfTones(r14, r12)
            int r2 = (r16 > r9 ? 1 : (r16 == r9 ? 0 : -1))
            if (r2 < 0) goto L_0x01e1
            return r12
        L_0x01e1:
            double r7 = com.google.android.material.color.utilities.Contrast.lighter(r7, r9)
            double r9 = com.google.android.material.color.utilities.Contrast.darker(r14, r9)
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
            r11 = -4616189618054758400(0xbff0000000000000, double:-1.0)
            int r13 = (r7 > r11 ? 1 : (r7 == r11 ? 0 : -1))
            if (r13 == 0) goto L_0x01fb
            java.lang.Double r14 = java.lang.Double.valueOf(r7)
            r2.add(r14)
        L_0x01fb:
            int r11 = (r9 > r11 ? 1 : (r9 == r11 ? 0 : -1))
            if (r11 == 0) goto L_0x0206
            java.lang.Double r12 = java.lang.Double.valueOf(r9)
            r2.add(r12)
        L_0x0206:
            boolean r5 = tonePrefersLightForeground(r5)
            if (r5 != 0) goto L_0x022c
            boolean r0 = tonePrefersLightForeground(r0)
            if (r0 == 0) goto L_0x0213
            goto L_0x022c
        L_0x0213:
            int r0 = r2.size()
            r1 = 1
            if (r0 != r1) goto L_0x0226
            r0 = 0
            java.lang.Object r0 = r2.get(r0)
            java.lang.Double r0 = (java.lang.Double) r0
            double r0 = r0.doubleValue()
            return r0
        L_0x0226:
            if (r11 != 0) goto L_0x022a
            r4 = r3
            goto L_0x022b
        L_0x022a:
            r4 = r9
        L_0x022b:
            return r4
        L_0x022c:
            if (r13 != 0) goto L_0x0230
            r7 = 4636737291354636288(0x4059000000000000, double:100.0)
        L_0x0230:
            return r7
        L_0x0231:
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.color.utilities.DynamicColor.getTone(com.google.android.material.color.utilities.DynamicScheme):double");
    }

    @NonNull
    public static DynamicColor fromPalette(@NonNull String str, @NonNull Function<DynamicScheme, TonalPalette> function, @NonNull Function<DynamicScheme, Double> function2, boolean z2) {
        return new DynamicColor(str, function, function2, z2, (Function<DynamicScheme, DynamicColor>) null, (Function<DynamicScheme, DynamicColor>) null, (ContrastCurve) null, (Function<DynamicScheme, ToneDeltaPair>) null);
    }

    public DynamicColor(@NonNull String str, @NonNull Function<DynamicScheme, TonalPalette> function, @NonNull Function<DynamicScheme, Double> function2, boolean z2, @Nullable Function<DynamicScheme, DynamicColor> function3, @Nullable Function<DynamicScheme, DynamicColor> function4, @Nullable ContrastCurve contrastCurve2, @Nullable Function<DynamicScheme, ToneDeltaPair> function5, @Nullable Function<DynamicScheme, Double> function6) {
        this.name = str;
        this.palette = function;
        this.tone = function2;
        this.isBackground = z2;
        this.background = function3;
        this.secondBackground = function4;
        this.contrastCurve = contrastCurve2;
        this.toneDeltaPair = function5;
        this.opacity = function6;
    }
}
