package com.google.android.material.color.utilities;

import java.util.function.Function;

public final /* synthetic */ class a implements Function {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f6643a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ MaterialDynamicColors f6644b;

    public /* synthetic */ a(MaterialDynamicColors materialDynamicColors, int i3) {
        this.f6643a = i3;
        this.f6644b = materialDynamicColors;
    }

    public final Object apply(Object obj) {
        int i3 = this.f6643a;
        MaterialDynamicColors materialDynamicColors = this.f6644b;
        DynamicScheme dynamicScheme = (DynamicScheme) obj;
        switch (i3) {
            case 0:
                return materialDynamicColors.lambda$onSecondary$72(dynamicScheme);
            case 1:
                return materialDynamicColors.lambda$secondary$69(dynamicScheme);
            case 2:
                return materialDynamicColors.lambda$onSecondaryFixed$125(dynamicScheme);
            case 3:
                return materialDynamicColors.lambda$onSecondaryFixed$126(dynamicScheme);
            case 4:
                return materialDynamicColors.lambda$inverseOnSurface$41(dynamicScheme);
            case 5:
                return materialDynamicColors.lambda$onTertiaryContainer$89(dynamicScheme);
            case 6:
                return materialDynamicColors.lambda$onTertiaryContainer$90(dynamicScheme);
            case 7:
                return materialDynamicColors.lambda$onSecondaryContainer$77(dynamicScheme);
            case 8:
                return materialDynamicColors.lambda$onSecondaryContainer$78(dynamicScheme);
            case 9:
                return materialDynamicColors.lambda$onPrimaryFixed$111(dynamicScheme);
            case 10:
                return materialDynamicColors.lambda$onPrimaryFixed$112(dynamicScheme);
            case 11:
                return materialDynamicColors.lambda$error$93(dynamicScheme);
            case 12:
                return materialDynamicColors.lambda$onErrorContainer$102(dynamicScheme);
            case 13:
                return materialDynamicColors.lambda$errorContainer$99(dynamicScheme);
            case 14:
                return materialDynamicColors.lambda$tertiaryFixed$133(dynamicScheme);
            case 15:
                return materialDynamicColors.lambda$primary$54(dynamicScheme);
            case 16:
                return materialDynamicColors.lambda$onSecondaryFixedVariant$129(dynamicScheme);
            case 17:
                return materialDynamicColors.lambda$onSecondaryFixedVariant$130(dynamicScheme);
            case 18:
                return materialDynamicColors.lambda$tertiary$81(dynamicScheme);
            case 19:
                return materialDynamicColors.lambda$onTertiaryFixedVariant$143(dynamicScheme);
            case 20:
                return materialDynamicColors.lambda$onTertiaryFixedVariant$144(dynamicScheme);
            case 21:
                return materialDynamicColors.lambda$secondaryContainer$75(dynamicScheme);
            case 22:
                return materialDynamicColors.lambda$onTertiaryFixed$139(dynamicScheme);
            case 23:
                return materialDynamicColors.lambda$onTertiaryFixed$140(dynamicScheme);
            case 24:
                return materialDynamicColors.lambda$primaryContainer$60(dynamicScheme);
            case 25:
                return materialDynamicColors.lambda$onBackground$14(dynamicScheme);
            case 26:
                return materialDynamicColors.lambda$onPrimary$57(dynamicScheme);
            case 27:
                return materialDynamicColors.lambda$inversePrimary$66(dynamicScheme);
            case 28:
                return materialDynamicColors.lambda$secondaryFixed$119(dynamicScheme);
            default:
                return materialDynamicColors.lambda$onPrimaryFixedVariant$115(dynamicScheme);
        }
    }
}
