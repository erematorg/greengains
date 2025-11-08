package com.google.android.material.color.utilities;

import android.support.v4.media.session.a;
import androidx.annotation.RestrictTo;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.CheckReturnValue;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

@CheckReturnValue
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class Scheme {
    private int background;
    private int error;
    private int errorContainer;
    private int inverseOnSurface;
    private int inversePrimary;
    private int inverseSurface;
    private int onBackground;
    private int onError;
    private int onErrorContainer;
    private int onPrimary;
    private int onPrimaryContainer;
    private int onSecondary;
    private int onSecondaryContainer;
    private int onSurface;
    private int onSurfaceVariant;
    private int onTertiary;
    private int onTertiaryContainer;
    private int outline;
    private int outlineVariant;
    private int primary;
    private int primaryContainer;
    private int scrim;
    private int secondary;
    private int secondaryContainer;
    private int shadow;
    private int surface;
    private int surfaceVariant;
    private int tertiary;
    private int tertiaryContainer;

    public Scheme() {
    }

    public static Scheme dark(int i3) {
        return darkFromCorePalette(CorePalette.of(i3));
    }

    public static Scheme darkContent(int i3) {
        return darkFromCorePalette(CorePalette.contentOf(i3));
    }

    private static Scheme darkFromCorePalette(CorePalette corePalette) {
        return new Scheme().withPrimary(corePalette.f6632a1.tone(80)).withOnPrimary(corePalette.f6632a1.tone(20)).withPrimaryContainer(corePalette.f6632a1.tone(30)).withOnPrimaryContainer(corePalette.f6632a1.tone(90)).withSecondary(corePalette.a2.tone(80)).withOnSecondary(corePalette.a2.tone(20)).withSecondaryContainer(corePalette.a2.tone(30)).withOnSecondaryContainer(corePalette.a2.tone(90)).withTertiary(corePalette.a3.tone(80)).withOnTertiary(corePalette.a3.tone(20)).withTertiaryContainer(corePalette.a3.tone(30)).withOnTertiaryContainer(corePalette.a3.tone(90)).withError(corePalette.error.tone(80)).withOnError(corePalette.error.tone(20)).withErrorContainer(corePalette.error.tone(30)).withOnErrorContainer(corePalette.error.tone(80)).withBackground(corePalette.f6633n1.tone(10)).withOnBackground(corePalette.f6633n1.tone(90)).withSurface(corePalette.f6633n1.tone(10)).withOnSurface(corePalette.f6633n1.tone(90)).withSurfaceVariant(corePalette.n2.tone(30)).withOnSurfaceVariant(corePalette.n2.tone(80)).withOutline(corePalette.n2.tone(60)).withOutlineVariant(corePalette.n2.tone(30)).withShadow(corePalette.f6633n1.tone(0)).withScrim(corePalette.f6633n1.tone(0)).withInverseSurface(corePalette.f6633n1.tone(90)).withInverseOnSurface(corePalette.f6633n1.tone(20)).withInversePrimary(corePalette.f6632a1.tone(40));
    }

    public static Scheme light(int i3) {
        return lightFromCorePalette(CorePalette.of(i3));
    }

    public static Scheme lightContent(int i3) {
        return lightFromCorePalette(CorePalette.contentOf(i3));
    }

    private static Scheme lightFromCorePalette(CorePalette corePalette) {
        return new Scheme().withPrimary(corePalette.f6632a1.tone(40)).withOnPrimary(corePalette.f6632a1.tone(100)).withPrimaryContainer(corePalette.f6632a1.tone(90)).withOnPrimaryContainer(corePalette.f6632a1.tone(10)).withSecondary(corePalette.a2.tone(40)).withOnSecondary(corePalette.a2.tone(100)).withSecondaryContainer(corePalette.a2.tone(90)).withOnSecondaryContainer(corePalette.a2.tone(10)).withTertiary(corePalette.a3.tone(40)).withOnTertiary(corePalette.a3.tone(100)).withTertiaryContainer(corePalette.a3.tone(90)).withOnTertiaryContainer(corePalette.a3.tone(10)).withError(corePalette.error.tone(40)).withOnError(corePalette.error.tone(100)).withErrorContainer(corePalette.error.tone(90)).withOnErrorContainer(corePalette.error.tone(10)).withBackground(corePalette.f6633n1.tone(99)).withOnBackground(corePalette.f6633n1.tone(10)).withSurface(corePalette.f6633n1.tone(99)).withOnSurface(corePalette.f6633n1.tone(10)).withSurfaceVariant(corePalette.n2.tone(90)).withOnSurfaceVariant(corePalette.n2.tone(30)).withOutline(corePalette.n2.tone(50)).withOutlineVariant(corePalette.n2.tone(80)).withShadow(corePalette.f6633n1.tone(0)).withScrim(corePalette.f6633n1.tone(0)).withInverseSurface(corePalette.f6633n1.tone(20)).withInverseOnSurface(corePalette.f6633n1.tone(95)).withInversePrimary(corePalette.f6632a1.tone(80));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Scheme) || !super.equals(obj)) {
            return false;
        }
        Scheme scheme = (Scheme) obj;
        return this.primary == scheme.primary && this.onPrimary == scheme.onPrimary && this.primaryContainer == scheme.primaryContainer && this.onPrimaryContainer == scheme.onPrimaryContainer && this.secondary == scheme.secondary && this.onSecondary == scheme.onSecondary && this.secondaryContainer == scheme.secondaryContainer && this.onSecondaryContainer == scheme.onSecondaryContainer && this.tertiary == scheme.tertiary && this.onTertiary == scheme.onTertiary && this.tertiaryContainer == scheme.tertiaryContainer && this.onTertiaryContainer == scheme.onTertiaryContainer && this.error == scheme.error && this.onError == scheme.onError && this.errorContainer == scheme.errorContainer && this.onErrorContainer == scheme.onErrorContainer && this.background == scheme.background && this.onBackground == scheme.onBackground && this.surface == scheme.surface && this.onSurface == scheme.onSurface && this.surfaceVariant == scheme.surfaceVariant && this.onSurfaceVariant == scheme.onSurfaceVariant && this.outline == scheme.outline && this.outlineVariant == scheme.outlineVariant && this.shadow == scheme.shadow && this.scrim == scheme.scrim && this.inverseSurface == scheme.inverseSurface && this.inverseOnSurface == scheme.inverseOnSurface && this.inversePrimary == scheme.inversePrimary;
    }

    public int getBackground() {
        return this.background;
    }

    public int getError() {
        return this.error;
    }

    public int getErrorContainer() {
        return this.errorContainer;
    }

    public int getInverseOnSurface() {
        return this.inverseOnSurface;
    }

    public int getInversePrimary() {
        return this.inversePrimary;
    }

    public int getInverseSurface() {
        return this.inverseSurface;
    }

    public int getOnBackground() {
        return this.onBackground;
    }

    public int getOnError() {
        return this.onError;
    }

    public int getOnErrorContainer() {
        return this.onErrorContainer;
    }

    public int getOnPrimary() {
        return this.onPrimary;
    }

    public int getOnPrimaryContainer() {
        return this.onPrimaryContainer;
    }

    public int getOnSecondary() {
        return this.onSecondary;
    }

    public int getOnSecondaryContainer() {
        return this.onSecondaryContainer;
    }

    public int getOnSurface() {
        return this.onSurface;
    }

    public int getOnSurfaceVariant() {
        return this.onSurfaceVariant;
    }

    public int getOnTertiary() {
        return this.onTertiary;
    }

    public int getOnTertiaryContainer() {
        return this.onTertiaryContainer;
    }

    public int getOutline() {
        return this.outline;
    }

    public int getOutlineVariant() {
        return this.outlineVariant;
    }

    public int getPrimary() {
        return this.primary;
    }

    public int getPrimaryContainer() {
        return this.primaryContainer;
    }

    public int getScrim() {
        return this.scrim;
    }

    public int getSecondary() {
        return this.secondary;
    }

    public int getSecondaryContainer() {
        return this.secondaryContainer;
    }

    public int getShadow() {
        return this.shadow;
    }

    public int getSurface() {
        return this.surface;
    }

    public int getSurfaceVariant() {
        return this.surfaceVariant;
    }

    public int getTertiary() {
        return this.tertiary;
    }

    public int getTertiaryContainer() {
        return this.tertiaryContainer;
    }

    public int hashCode() {
        return (((((((((((((((((((((((((((((((((((((((((((((((((((((((((super.hashCode() * 31) + this.primary) * 31) + this.onPrimary) * 31) + this.primaryContainer) * 31) + this.onPrimaryContainer) * 31) + this.secondary) * 31) + this.onSecondary) * 31) + this.secondaryContainer) * 31) + this.onSecondaryContainer) * 31) + this.tertiary) * 31) + this.onTertiary) * 31) + this.tertiaryContainer) * 31) + this.onTertiaryContainer) * 31) + this.error) * 31) + this.onError) * 31) + this.errorContainer) * 31) + this.onErrorContainer) * 31) + this.background) * 31) + this.onBackground) * 31) + this.surface) * 31) + this.onSurface) * 31) + this.surfaceVariant) * 31) + this.onSurfaceVariant) * 31) + this.outline) * 31) + this.outlineVariant) * 31) + this.shadow) * 31) + this.scrim) * 31) + this.inverseSurface) * 31) + this.inverseOnSurface) * 31) + this.inversePrimary;
    }

    public void setBackground(int i3) {
        this.background = i3;
    }

    public void setError(int i3) {
        this.error = i3;
    }

    public void setErrorContainer(int i3) {
        this.errorContainer = i3;
    }

    public void setInverseOnSurface(int i3) {
        this.inverseOnSurface = i3;
    }

    public void setInversePrimary(int i3) {
        this.inversePrimary = i3;
    }

    public void setInverseSurface(int i3) {
        this.inverseSurface = i3;
    }

    public void setOnBackground(int i3) {
        this.onBackground = i3;
    }

    public void setOnError(int i3) {
        this.onError = i3;
    }

    public void setOnErrorContainer(int i3) {
        this.onErrorContainer = i3;
    }

    public void setOnPrimary(int i3) {
        this.onPrimary = i3;
    }

    public void setOnPrimaryContainer(int i3) {
        this.onPrimaryContainer = i3;
    }

    public void setOnSecondary(int i3) {
        this.onSecondary = i3;
    }

    public void setOnSecondaryContainer(int i3) {
        this.onSecondaryContainer = i3;
    }

    public void setOnSurface(int i3) {
        this.onSurface = i3;
    }

    public void setOnSurfaceVariant(int i3) {
        this.onSurfaceVariant = i3;
    }

    public void setOnTertiary(int i3) {
        this.onTertiary = i3;
    }

    public void setOnTertiaryContainer(int i3) {
        this.onTertiaryContainer = i3;
    }

    public void setOutline(int i3) {
        this.outline = i3;
    }

    public void setOutlineVariant(int i3) {
        this.outlineVariant = i3;
    }

    public void setPrimary(int i3) {
        this.primary = i3;
    }

    public void setPrimaryContainer(int i3) {
        this.primaryContainer = i3;
    }

    public void setScrim(int i3) {
        this.scrim = i3;
    }

    public void setSecondary(int i3) {
        this.secondary = i3;
    }

    public void setSecondaryContainer(int i3) {
        this.secondaryContainer = i3;
    }

    public void setShadow(int i3) {
        this.shadow = i3;
    }

    public void setSurface(int i3) {
        this.surface = i3;
    }

    public void setSurfaceVariant(int i3) {
        this.surfaceVariant = i3;
    }

    public void setTertiary(int i3) {
        this.tertiary = i3;
    }

    public void setTertiaryContainer(int i3) {
        this.tertiaryContainer = i3;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("Scheme{primary=");
        sb.append(this.primary);
        sb.append(", onPrimary=");
        sb.append(this.onPrimary);
        sb.append(", primaryContainer=");
        sb.append(this.primaryContainer);
        sb.append(", onPrimaryContainer=");
        sb.append(this.onPrimaryContainer);
        sb.append(", secondary=");
        sb.append(this.secondary);
        sb.append(", onSecondary=");
        sb.append(this.onSecondary);
        sb.append(", secondaryContainer=");
        sb.append(this.secondaryContainer);
        sb.append(", onSecondaryContainer=");
        sb.append(this.onSecondaryContainer);
        sb.append(", tertiary=");
        sb.append(this.tertiary);
        sb.append(", onTertiary=");
        sb.append(this.onTertiary);
        sb.append(", tertiaryContainer=");
        sb.append(this.tertiaryContainer);
        sb.append(", onTertiaryContainer=");
        sb.append(this.onTertiaryContainer);
        sb.append(", error=");
        sb.append(this.error);
        sb.append(", onError=");
        sb.append(this.onError);
        sb.append(", errorContainer=");
        sb.append(this.errorContainer);
        sb.append(", onErrorContainer=");
        sb.append(this.onErrorContainer);
        sb.append(", background=");
        sb.append(this.background);
        sb.append(", onBackground=");
        sb.append(this.onBackground);
        sb.append(", surface=");
        sb.append(this.surface);
        sb.append(", onSurface=");
        sb.append(this.onSurface);
        sb.append(", surfaceVariant=");
        sb.append(this.surfaceVariant);
        sb.append(", onSurfaceVariant=");
        sb.append(this.onSurfaceVariant);
        sb.append(", outline=");
        sb.append(this.outline);
        sb.append(", outlineVariant=");
        sb.append(this.outlineVariant);
        sb.append(", shadow=");
        sb.append(this.shadow);
        sb.append(", scrim=");
        sb.append(this.scrim);
        sb.append(", inverseSurface=");
        sb.append(this.inverseSurface);
        sb.append(", inverseOnSurface=");
        sb.append(this.inverseOnSurface);
        sb.append(", inversePrimary=");
        return a.p(sb, this.inversePrimary, AbstractJsonLexerKt.END_OBJ);
    }

    @CanIgnoreReturnValue
    public Scheme withBackground(int i3) {
        this.background = i3;
        return this;
    }

    @CanIgnoreReturnValue
    public Scheme withError(int i3) {
        this.error = i3;
        return this;
    }

    @CanIgnoreReturnValue
    public Scheme withErrorContainer(int i3) {
        this.errorContainer = i3;
        return this;
    }

    @CanIgnoreReturnValue
    public Scheme withInverseOnSurface(int i3) {
        this.inverseOnSurface = i3;
        return this;
    }

    @CanIgnoreReturnValue
    public Scheme withInversePrimary(int i3) {
        this.inversePrimary = i3;
        return this;
    }

    @CanIgnoreReturnValue
    public Scheme withInverseSurface(int i3) {
        this.inverseSurface = i3;
        return this;
    }

    @CanIgnoreReturnValue
    public Scheme withOnBackground(int i3) {
        this.onBackground = i3;
        return this;
    }

    @CanIgnoreReturnValue
    public Scheme withOnError(int i3) {
        this.onError = i3;
        return this;
    }

    @CanIgnoreReturnValue
    public Scheme withOnErrorContainer(int i3) {
        this.onErrorContainer = i3;
        return this;
    }

    @CanIgnoreReturnValue
    public Scheme withOnPrimary(int i3) {
        this.onPrimary = i3;
        return this;
    }

    @CanIgnoreReturnValue
    public Scheme withOnPrimaryContainer(int i3) {
        this.onPrimaryContainer = i3;
        return this;
    }

    @CanIgnoreReturnValue
    public Scheme withOnSecondary(int i3) {
        this.onSecondary = i3;
        return this;
    }

    @CanIgnoreReturnValue
    public Scheme withOnSecondaryContainer(int i3) {
        this.onSecondaryContainer = i3;
        return this;
    }

    @CanIgnoreReturnValue
    public Scheme withOnSurface(int i3) {
        this.onSurface = i3;
        return this;
    }

    @CanIgnoreReturnValue
    public Scheme withOnSurfaceVariant(int i3) {
        this.onSurfaceVariant = i3;
        return this;
    }

    @CanIgnoreReturnValue
    public Scheme withOnTertiary(int i3) {
        this.onTertiary = i3;
        return this;
    }

    @CanIgnoreReturnValue
    public Scheme withOnTertiaryContainer(int i3) {
        this.onTertiaryContainer = i3;
        return this;
    }

    @CanIgnoreReturnValue
    public Scheme withOutline(int i3) {
        this.outline = i3;
        return this;
    }

    @CanIgnoreReturnValue
    public Scheme withOutlineVariant(int i3) {
        this.outlineVariant = i3;
        return this;
    }

    @CanIgnoreReturnValue
    public Scheme withPrimary(int i3) {
        this.primary = i3;
        return this;
    }

    @CanIgnoreReturnValue
    public Scheme withPrimaryContainer(int i3) {
        this.primaryContainer = i3;
        return this;
    }

    @CanIgnoreReturnValue
    public Scheme withScrim(int i3) {
        this.scrim = i3;
        return this;
    }

    @CanIgnoreReturnValue
    public Scheme withSecondary(int i3) {
        this.secondary = i3;
        return this;
    }

    @CanIgnoreReturnValue
    public Scheme withSecondaryContainer(int i3) {
        this.secondaryContainer = i3;
        return this;
    }

    @CanIgnoreReturnValue
    public Scheme withShadow(int i3) {
        this.shadow = i3;
        return this;
    }

    @CanIgnoreReturnValue
    public Scheme withSurface(int i3) {
        this.surface = i3;
        return this;
    }

    @CanIgnoreReturnValue
    public Scheme withSurfaceVariant(int i3) {
        this.surfaceVariant = i3;
        return this;
    }

    @CanIgnoreReturnValue
    public Scheme withTertiary(int i3) {
        this.tertiary = i3;
        return this;
    }

    @CanIgnoreReturnValue
    public Scheme withTertiaryContainer(int i3) {
        this.tertiaryContainer = i3;
        return this;
    }

    public Scheme(int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11, int i12, int i13, int i14, int i15, int i16, int i17, int i18, int i19, int i20, int i21, int i22, int i23, int i24, int i25, int i26, int i27, int i28, int i29, int i30, int i31) {
        this.primary = i3;
        this.onPrimary = i4;
        this.primaryContainer = i5;
        this.onPrimaryContainer = i6;
        this.secondary = i7;
        this.onSecondary = i8;
        this.secondaryContainer = i9;
        this.onSecondaryContainer = i10;
        this.tertiary = i11;
        this.onTertiary = i12;
        this.tertiaryContainer = i13;
        this.onTertiaryContainer = i14;
        this.error = i15;
        this.onError = i16;
        this.errorContainer = i17;
        this.onErrorContainer = i18;
        this.background = i19;
        this.onBackground = i20;
        this.surface = i21;
        this.onSurface = i22;
        this.surfaceVariant = i23;
        this.onSurfaceVariant = i24;
        this.outline = i25;
        this.outlineVariant = i26;
        this.shadow = i27;
        this.scrim = i28;
        this.inverseSurface = i29;
        this.inverseOnSurface = i30;
        this.inversePrimary = i31;
    }
}
