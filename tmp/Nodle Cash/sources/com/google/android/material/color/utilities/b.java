package com.google.android.material.color.utilities;

import java.util.function.Function;

public final /* synthetic */ class b implements Function {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f6645a;

    public /* synthetic */ b(int i3) {
        this.f6645a = i3;
    }

    public final Object apply(Object obj) {
        DynamicScheme dynamicScheme = (DynamicScheme) obj;
        switch (this.f6645a) {
            case 0:
                return dynamicScheme.neutralPalette;
            case 1:
                return MaterialDynamicColors.lambda$textPrimaryInverse$153(dynamicScheme);
            case 2:
                return dynamicScheme.neutralPalette;
            case 3:
                return MaterialDynamicColors.lambda$surfaceContainer$26(dynamicScheme);
            case 4:
                return dynamicScheme.neutralVariantPalette;
            case 5:
                return MaterialDynamicColors.lambda$onSurfaceVariant$36(dynamicScheme);
            case 6:
                return dynamicScheme.neutralPalette;
            case 7:
                return MaterialDynamicColors.lambda$inverseSurface$38(dynamicScheme);
            case 8:
                return MaterialDynamicColors.lambda$surfaceDim$18(dynamicScheme);
            case 9:
                return dynamicScheme.errorPalette;
            case 10:
                return MaterialDynamicColors.lambda$onErrorContainer$101(dynamicScheme);
            case 11:
                return dynamicScheme.neutralVariantPalette;
            case 12:
                return MaterialDynamicColors.lambda$textSecondaryAndTertiaryInverse$155(dynamicScheme);
            case 13:
                return dynamicScheme.errorPalette;
            case 14:
                return MaterialDynamicColors.lambda$errorContainer$98(dynamicScheme);
            case 15:
                return dynamicScheme.tertiaryPalette;
            case 16:
                return MaterialDynamicColors.lambda$tertiaryFixed$132(dynamicScheme);
            case 17:
                return dynamicScheme.neutralVariantPalette;
            case 18:
                return dynamicScheme.primaryPalette;
            case 19:
                return MaterialDynamicColors.lambda$primary$53(dynamicScheme);
            case 20:
                return dynamicScheme.neutralPalette;
            case 21:
                return Double.valueOf(0.0d);
            case 22:
                return dynamicScheme.secondaryPalette;
            case 23:
                return MaterialDynamicColors.lambda$onSecondaryFixedVariant$128(dynamicScheme);
            case 24:
                return MaterialDynamicColors.lambda$controlNormal$148(dynamicScheme);
            case 25:
                return dynamicScheme.tertiaryPalette;
            case 26:
                return MaterialDynamicColors.lambda$tertiary$80(dynamicScheme);
            case 27:
                return dynamicScheme.primaryPalette;
            case 28:
                return MaterialDynamicColors.lambda$controlActivated$146(dynamicScheme);
            default:
                return dynamicScheme.primaryPalette;
        }
    }
}
