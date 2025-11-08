package com.google.android.material.color.utilities;

import java.util.function.Function;

public final /* synthetic */ class c implements Function {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f6646a;

    public /* synthetic */ c(int i3) {
        this.f6646a = i3;
    }

    public final Object apply(Object obj) {
        DynamicScheme dynamicScheme = (DynamicScheme) obj;
        switch (this.f6646a) {
            case 0:
                return Double.valueOf(dynamicScheme.primaryPalette.getKeyColor().getTone());
            case 1:
                return MaterialDynamicColors.lambda$surface$16(dynamicScheme);
            case 2:
                return dynamicScheme.tertiaryPalette;
            case 3:
                return MaterialDynamicColors.lambda$onTertiaryFixedVariant$142(dynamicScheme);
            case 4:
                return dynamicScheme.secondaryPalette;
            case 5:
                return Double.valueOf(dynamicScheme.secondaryPalette.getKeyColor().getTone());
            case 6:
                return dynamicScheme.neutralPalette;
            case 7:
                return MaterialDynamicColors.lambda$surfaceContainerLow$24(dynamicScheme);
            case 8:
                return dynamicScheme.secondaryPalette;
            case 9:
                return MaterialDynamicColors.lambda$secondaryContainer$74(dynamicScheme);
            case 10:
                return dynamicScheme.neutralPalette;
            case 11:
                return dynamicScheme.tertiaryPalette;
            case 12:
                return MaterialDynamicColors.lambda$onTertiaryFixed$138(dynamicScheme);
            case 13:
                return dynamicScheme.tertiaryPalette;
            case 14:
                return Double.valueOf(dynamicScheme.tertiaryPalette.getKeyColor().getTone());
            case 15:
                return dynamicScheme.neutralPalette;
            case 16:
                return MaterialDynamicColors.lambda$controlHighlight$150(dynamicScheme);
            case 17:
                return MaterialDynamicColors.lambda$controlHighlight$151(dynamicScheme);
            case 18:
                return MaterialDynamicColors.lambda$textSecondaryAndTertiaryInverseDisabled$159(dynamicScheme);
            case 19:
                return dynamicScheme.primaryPalette;
            case 20:
                return MaterialDynamicColors.lambda$primaryContainer$59(dynamicScheme);
            case 21:
                return dynamicScheme.neutralPalette;
            case 22:
                return MaterialDynamicColors.lambda$background$11(dynamicScheme);
            case 23:
                return dynamicScheme.neutralPalette;
            case 24:
                return MaterialDynamicColors.lambda$surfaceContainerHigh$28(dynamicScheme);
            case 25:
                return dynamicScheme.neutralPalette;
            case 26:
                return MaterialDynamicColors.lambda$onBackground$13(dynamicScheme);
            case 27:
                return dynamicScheme.tertiaryPalette;
            case 28:
                return dynamicScheme.neutralVariantPalette;
            default:
                return MaterialDynamicColors.lambda$outlineVariant$45(dynamicScheme);
        }
    }
}
