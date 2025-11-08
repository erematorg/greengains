package com.google.android.material.color.utilities;

import java.util.function.Function;

public final /* synthetic */ class e implements Function {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f6648a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ MaterialDynamicColors f6649b;

    public /* synthetic */ e(MaterialDynamicColors materialDynamicColors, int i3) {
        this.f6648a = i3;
        this.f6649b = materialDynamicColors;
    }

    public final Object apply(Object obj) {
        int i3 = this.f6648a;
        MaterialDynamicColors materialDynamicColors = this.f6649b;
        DynamicScheme dynamicScheme = (DynamicScheme) obj;
        switch (i3) {
            case 0:
                return materialDynamicColors.lambda$onPrimaryFixedVariant$116(dynamicScheme);
            case 1:
                return materialDynamicColors.lambda$tertiaryFixedDim$136(dynamicScheme);
            case 2:
                return materialDynamicColors.lambda$onTertiary$84(dynamicScheme);
            case 3:
                return materialDynamicColors.lambda$onError$96(dynamicScheme);
            case 4:
                return materialDynamicColors.lambda$onPrimaryContainer$62(dynamicScheme);
            case 5:
                return materialDynamicColors.lambda$onPrimaryContainer$63(dynamicScheme);
            case 6:
                return materialDynamicColors.lambda$secondaryFixedDim$122(dynamicScheme);
            case 7:
                return materialDynamicColors.lambda$primaryFixed$105(dynamicScheme);
            case 8:
                return materialDynamicColors.lambda$primaryFixedDim$108(dynamicScheme);
            case 9:
                return materialDynamicColors.lambda$tertiaryContainer$87(dynamicScheme);
            default:
                return materialDynamicColors.highestSurface(dynamicScheme);
        }
    }
}
