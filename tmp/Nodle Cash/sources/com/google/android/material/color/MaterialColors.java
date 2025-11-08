package com.google.android.material.color;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.View;
import androidx.annotation.AttrRes;
import androidx.annotation.ColorInt;
import androidx.annotation.FloatRange;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.ColorUtils;
import com.google.android.material.R;
import com.google.android.material.color.utilities.Blend;
import com.google.android.material.color.utilities.Hct;
import com.google.android.material.resources.MaterialAttributes;

public class MaterialColors {
    public static final float ALPHA_DISABLED = 0.38f;
    public static final float ALPHA_DISABLED_LOW = 0.12f;
    public static final float ALPHA_FULL = 1.0f;
    public static final float ALPHA_LOW = 0.32f;
    public static final float ALPHA_MEDIUM = 0.54f;
    private static final int CHROMA_NEUTRAL = 6;
    private static final int TONE_ACCENT_CONTAINER_DARK = 30;
    private static final int TONE_ACCENT_CONTAINER_LIGHT = 90;
    private static final int TONE_ACCENT_DARK = 80;
    private static final int TONE_ACCENT_LIGHT = 40;
    private static final int TONE_ON_ACCENT_CONTAINER_DARK = 90;
    private static final int TONE_ON_ACCENT_CONTAINER_LIGHT = 10;
    private static final int TONE_ON_ACCENT_DARK = 20;
    private static final int TONE_ON_ACCENT_LIGHT = 100;
    private static final int TONE_SURFACE_CONTAINER_DARK = 12;
    private static final int TONE_SURFACE_CONTAINER_HIGH_DARK = 17;
    private static final int TONE_SURFACE_CONTAINER_HIGH_LIGHT = 92;
    private static final int TONE_SURFACE_CONTAINER_LIGHT = 94;

    private MaterialColors() {
    }

    @ColorInt
    public static int compositeARGBWithAlpha(@ColorInt int i3, @IntRange(from = 0, to = 255) int i4) {
        return ColorUtils.setAlphaComponent(i3, (Color.alpha(i3) * i4) / 255);
    }

    @ColorInt
    public static int getColor(@NonNull View view, @AttrRes int i3) {
        return resolveColor(view.getContext(), MaterialAttributes.resolveTypedValueOrThrow(view, i3));
    }

    @ColorInt
    @Nullable
    public static Integer getColorOrNull(@NonNull Context context, @AttrRes int i3) {
        TypedValue resolve = MaterialAttributes.resolve(context, i3);
        if (resolve != null) {
            return Integer.valueOf(resolveColor(context, resolve));
        }
        return null;
    }

    @ColorInt
    private static int getColorRole(@ColorInt int i3, @IntRange(from = 0, to = 100) int i4) {
        Hct fromInt = Hct.fromInt(i3);
        fromInt.setTone((double) i4);
        return fromInt.toInt();
    }

    @NonNull
    public static ColorRoles getColorRoles(@NonNull Context context, @ColorInt int i3) {
        return getColorRoles(i3, isLightTheme(context));
    }

    @NonNull
    public static ColorStateList getColorStateList(@NonNull Context context, @AttrRes int i3, @NonNull ColorStateList colorStateList) {
        TypedValue resolve = MaterialAttributes.resolve(context, i3);
        ColorStateList resolveColorStateList = resolve != null ? resolveColorStateList(context, resolve) : null;
        return resolveColorStateList == null ? colorStateList : resolveColorStateList;
    }

    @Nullable
    public static ColorStateList getColorStateListOrNull(@NonNull Context context, @AttrRes int i3) {
        TypedValue resolve = MaterialAttributes.resolve(context, i3);
        if (resolve == null) {
            return null;
        }
        int i4 = resolve.resourceId;
        if (i4 != 0) {
            return ContextCompat.getColorStateList(context, i4);
        }
        int i5 = resolve.data;
        if (i5 != 0) {
            return ColorStateList.valueOf(i5);
        }
        return null;
    }

    @ColorInt
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static int getSurfaceContainerFromSeed(@NonNull Context context, @ColorInt int i3) {
        return getColorRole(i3, isLightTheme(context) ? 94 : 12, 6);
    }

    @ColorInt
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static int getSurfaceContainerHighFromSeed(@NonNull Context context, @ColorInt int i3) {
        return getColorRole(i3, isLightTheme(context) ? 92 : 17, 6);
    }

    @ColorInt
    public static int harmonize(@ColorInt int i3, @ColorInt int i4) {
        return Blend.harmonize(i3, i4);
    }

    @ColorInt
    public static int harmonizeWithPrimary(@NonNull Context context, @ColorInt int i3) {
        return harmonize(i3, getColor(context, R.attr.colorPrimary, MaterialColors.class.getCanonicalName()));
    }

    public static boolean isColorLight(@ColorInt int i3) {
        return i3 != 0 && ColorUtils.calculateLuminance(i3) > 0.5d;
    }

    public static boolean isLightTheme(@NonNull Context context) {
        return MaterialAttributes.resolveBoolean(context, R.attr.isLightTheme, true);
    }

    @ColorInt
    public static int layer(@NonNull View view, @AttrRes int i3, @AttrRes int i4) {
        return layer(view, i3, i4, 1.0f);
    }

    private static int resolveColor(@NonNull Context context, @NonNull TypedValue typedValue) {
        int i3 = typedValue.resourceId;
        return i3 != 0 ? ContextCompat.getColor(context, i3) : typedValue.data;
    }

    private static ColorStateList resolveColorStateList(@NonNull Context context, @NonNull TypedValue typedValue) {
        int i3 = typedValue.resourceId;
        return i3 != 0 ? ContextCompat.getColorStateList(context, i3) : ColorStateList.valueOf(typedValue.data);
    }

    @NonNull
    public static ColorRoles getColorRoles(@ColorInt int i3, boolean z2) {
        if (z2) {
            return new ColorRoles(getColorRole(i3, 40), getColorRole(i3, 100), getColorRole(i3, 90), getColorRole(i3, 10));
        }
        return new ColorRoles(getColorRole(i3, 80), getColorRole(i3, 20), getColorRole(i3, 30), getColorRole(i3, 90));
    }

    @ColorInt
    public static int layer(@NonNull View view, @AttrRes int i3, @AttrRes int i4, @FloatRange(from = 0.0d, to = 1.0d) float f2) {
        return layer(getColor(view, i3), getColor(view, i4), f2);
    }

    @ColorInt
    public static int getColor(Context context, @AttrRes int i3, String str) {
        return resolveColor(context, MaterialAttributes.resolveTypedValueOrThrow(context, i3, str));
    }

    @ColorInt
    private static int getColorRole(@ColorInt int i3, @IntRange(from = 0, to = 100) int i4, int i5) {
        Hct fromInt = Hct.fromInt(getColorRole(i3, i4));
        fromInt.setChroma((double) i5);
        return fromInt.toInt();
    }

    @ColorInt
    public static int layer(@ColorInt int i3, @ColorInt int i4, @FloatRange(from = 0.0d, to = 1.0d) float f2) {
        return layer(i3, ColorUtils.setAlphaComponent(i4, Math.round(((float) Color.alpha(i4)) * f2)));
    }

    @ColorInt
    public static int getColor(@NonNull View view, @AttrRes int i3, @ColorInt int i4) {
        return getColor(view.getContext(), i3, i4);
    }

    @ColorInt
    public static int getColor(@NonNull Context context, @AttrRes int i3, @ColorInt int i4) {
        Integer colorOrNull = getColorOrNull(context, i3);
        return colorOrNull != null ? colorOrNull.intValue() : i4;
    }

    @ColorInt
    public static int layer(@ColorInt int i3, @ColorInt int i4) {
        return ColorUtils.compositeColors(i4, i3);
    }
}
