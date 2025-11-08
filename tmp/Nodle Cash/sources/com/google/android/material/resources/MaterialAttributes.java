package com.google.android.material.resources;

import android.content.Context;
import android.util.TypedValue;
import android.view.View;
import androidx.annotation.AttrRes;
import androidx.annotation.DimenRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.Px;
import androidx.annotation.RestrictTo;
import com.google.android.material.R;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class MaterialAttributes {
    @Nullable
    public static TypedValue resolve(@NonNull Context context, @AttrRes int i3) {
        TypedValue typedValue = new TypedValue();
        if (context.getTheme().resolveAttribute(i3, typedValue, true)) {
            return typedValue;
        }
        return null;
    }

    public static boolean resolveBoolean(@NonNull Context context, @AttrRes int i3, boolean z2) {
        TypedValue resolve = resolve(context, i3);
        return (resolve == null || resolve.type != 18) ? z2 : resolve.data != 0;
    }

    public static boolean resolveBooleanOrThrow(@NonNull Context context, @AttrRes int i3, @NonNull String str) {
        return resolveOrThrow(context, i3, str) != 0;
    }

    @Px
    public static int resolveDimension(@NonNull Context context, @AttrRes int i3, @DimenRes int i4) {
        TypedValue resolve = resolve(context, i3);
        return (int) ((resolve == null || resolve.type != 5) ? context.getResources().getDimension(i4) : resolve.getDimension(context.getResources().getDisplayMetrics()));
    }

    public static int resolveInteger(@NonNull Context context, @AttrRes int i3, int i4) {
        TypedValue resolve = resolve(context, i3);
        return (resolve == null || resolve.type != 16) ? i4 : resolve.data;
    }

    @Px
    public static int resolveMinimumAccessibleTouchTarget(@NonNull Context context) {
        return resolveDimension(context, R.attr.minTouchTargetSize, R.dimen.mtrl_min_touch_target_size);
    }

    public static int resolveOrThrow(@NonNull Context context, @AttrRes int i3, @NonNull String str) {
        return resolveTypedValueOrThrow(context, i3, str).data;
    }

    @NonNull
    public static TypedValue resolveTypedValueOrThrow(@NonNull View view, @AttrRes int i3) {
        return resolveTypedValueOrThrow(view.getContext(), i3, view.getClass().getCanonicalName());
    }

    public static int resolveOrThrow(@NonNull View view, @AttrRes int i3) {
        return resolveTypedValueOrThrow(view, i3).data;
    }

    @NonNull
    public static TypedValue resolveTypedValueOrThrow(@NonNull Context context, @AttrRes int i3, @NonNull String str) {
        TypedValue resolve = resolve(context, i3);
        if (resolve != null) {
            return resolve;
        }
        throw new IllegalArgumentException(String.format("%1$s requires a value for the %2$s attribute to be set in your app theme. You can either set the attribute in your theme or update your theme to inherit from Theme.MaterialComponents (or a descendant).", new Object[]{str, context.getResources().getResourceName(i3)}));
    }
}
