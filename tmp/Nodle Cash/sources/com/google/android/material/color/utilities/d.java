package com.google.android.material.color.utilities;

import java.util.function.Function;

public final /* synthetic */ class d implements Function {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f6647a;

    public /* synthetic */ d(int i3) {
        this.f6647a = i3;
    }

    public final Object apply(Object obj) {
        DynamicScheme dynamicScheme = (DynamicScheme) obj;
        switch (this.f6647a) {
            case 0:
                return dynamicScheme.neutralPalette;
            case 1:
                return MaterialDynamicColors.lambda$surfaceBright$20(dynamicScheme);
            case 2:
                return dynamicScheme.primaryPalette;
            case 3:
                return MaterialDynamicColors.lambda$onPrimary$56(dynamicScheme);
            case 4:
                return dynamicScheme.primaryPalette;
            case 5:
                return MaterialDynamicColors.lambda$inversePrimary$65(dynamicScheme);
            case 6:
                return MaterialDynamicColors.lambda$onTertiary$83(dynamicScheme);
            case 7:
                return dynamicScheme.secondaryPalette;
            case 8:
                return MaterialDynamicColors.lambda$secondaryFixed$118(dynamicScheme);
            case 9:
                return dynamicScheme.primaryPalette;
            case 10:
                return MaterialDynamicColors.lambda$onPrimaryFixedVariant$114(dynamicScheme);
            case 11:
                return dynamicScheme.tertiaryPalette;
            case 12:
                return MaterialDynamicColors.lambda$tertiaryFixedDim$135(dynamicScheme);
            case 13:
                return dynamicScheme.errorPalette;
            case 14:
                return MaterialDynamicColors.lambda$onError$95(dynamicScheme);
            case 15:
                return dynamicScheme.neutralPalette;
            case 16:
                return MaterialDynamicColors.lambda$textPrimaryInverseDisableOnly$157(dynamicScheme);
            case 17:
                return dynamicScheme.primaryPalette;
            case 18:
                return dynamicScheme.secondaryPalette;
            case 19:
                return MaterialDynamicColors.lambda$secondaryFixedDim$121(dynamicScheme);
            case 20:
                return dynamicScheme.neutralPalette;
            case 21:
                return dynamicScheme.neutralVariantPalette;
            case 22:
                return MaterialDynamicColors.lambda$surfaceVariant$34(dynamicScheme);
            case 23:
                return dynamicScheme.neutralVariantPalette;
            case 24:
                return Double.valueOf(dynamicScheme.neutralVariantPalette.getKeyColor().getTone());
            case 25:
                return dynamicScheme.neutralPalette;
            case 26:
                return MaterialDynamicColors.lambda$textHintInverse$161(dynamicScheme);
            case 27:
                return dynamicScheme.neutralPalette;
            case 28:
                return Double.valueOf(0.0d);
            default:
                return dynamicScheme.neutralPalette;
        }
    }
}
