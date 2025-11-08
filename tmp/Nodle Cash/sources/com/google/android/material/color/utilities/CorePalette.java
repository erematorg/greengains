package com.google.android.material.color.utilities;

import androidx.annotation.RestrictTo;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public final class CorePalette {

    /* renamed from: a1  reason: collision with root package name */
    public TonalPalette f6632a1;
    public TonalPalette a2;
    public TonalPalette a3;
    public TonalPalette error;

    /* renamed from: n1  reason: collision with root package name */
    public TonalPalette f6633n1;
    public TonalPalette n2;

    private CorePalette(int i3, boolean z2) {
        Hct fromInt = Hct.fromInt(i3);
        double hue = fromInt.getHue();
        double chroma = fromInt.getChroma();
        if (z2) {
            this.f6632a1 = TonalPalette.fromHueAndChroma(hue, chroma);
            this.a2 = TonalPalette.fromHueAndChroma(hue, chroma / 3.0d);
            this.a3 = TonalPalette.fromHueAndChroma(60.0d + hue, chroma / 2.0d);
            this.f6633n1 = TonalPalette.fromHueAndChroma(hue, Math.min(chroma / 12.0d, 4.0d));
            this.n2 = TonalPalette.fromHueAndChroma(hue, Math.min(chroma / 6.0d, 8.0d));
        } else {
            this.f6632a1 = TonalPalette.fromHueAndChroma(hue, Math.max(48.0d, chroma));
            this.a2 = TonalPalette.fromHueAndChroma(hue, 16.0d);
            this.a3 = TonalPalette.fromHueAndChroma(60.0d + hue, 24.0d);
            this.f6633n1 = TonalPalette.fromHueAndChroma(hue, 4.0d);
            this.n2 = TonalPalette.fromHueAndChroma(hue, 8.0d);
        }
        this.error = TonalPalette.fromHueAndChroma(25.0d, 84.0d);
    }

    public static CorePalette contentOf(int i3) {
        return new CorePalette(i3, true);
    }

    public static CorePalette of(int i3) {
        return new CorePalette(i3, false);
    }
}
