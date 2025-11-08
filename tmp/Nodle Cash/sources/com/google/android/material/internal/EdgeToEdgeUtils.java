package com.google.android.material.internal;

import android.annotation.TargetApi;
import android.content.Context;
import android.view.Window;
import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import com.google.android.material.color.MaterialColors;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class EdgeToEdgeUtils {
    private static final int EDGE_TO_EDGE_BAR_ALPHA = 128;

    private EdgeToEdgeUtils() {
    }

    public static void applyEdgeToEdge(@NonNull Window window, boolean z2) {
        applyEdgeToEdge(window, z2, (Integer) null, (Integer) null);
    }

    @TargetApi(21)
    private static int getNavigationBarColor(Context context, boolean z2) {
        if (z2) {
            return 0;
        }
        return MaterialColors.getColor(context, 16843858, (int) ViewCompat.MEASURED_STATE_MASK);
    }

    @TargetApi(21)
    private static int getStatusBarColor(Context context, boolean z2) {
        if (z2) {
            return 0;
        }
        return MaterialColors.getColor(context, 16843857, (int) ViewCompat.MEASURED_STATE_MASK);
    }

    private static boolean isUsingLightSystemBar(int i3, boolean z2) {
        return MaterialColors.isColorLight(i3) || (i3 == 0 && z2);
    }

    public static void setLightNavigationBar(@NonNull Window window, boolean z2) {
        WindowCompat.getInsetsController(window, window.getDecorView()).setAppearanceLightNavigationBars(z2);
    }

    public static void setLightStatusBar(@NonNull Window window, boolean z2) {
        WindowCompat.getInsetsController(window, window.getDecorView()).setAppearanceLightStatusBars(z2);
    }

    public static void applyEdgeToEdge(@NonNull Window window, boolean z2, @ColorInt @Nullable Integer num, @ColorInt @Nullable Integer num2) {
        boolean z3 = false;
        boolean z4 = num == null || num.intValue() == 0;
        if (num2 == null || num2.intValue() == 0) {
            z3 = true;
        }
        if (z4 || z3) {
            int color = MaterialColors.getColor(window.getContext(), 16842801, (int) ViewCompat.MEASURED_STATE_MASK);
            if (z4) {
                num = Integer.valueOf(color);
            }
            if (z3) {
                num2 = Integer.valueOf(color);
            }
        }
        WindowCompat.setDecorFitsSystemWindows(window, !z2);
        int statusBarColor = getStatusBarColor(window.getContext(), z2);
        int navigationBarColor = getNavigationBarColor(window.getContext(), z2);
        window.setStatusBarColor(statusBarColor);
        window.setNavigationBarColor(navigationBarColor);
        setLightStatusBar(window, isUsingLightSystemBar(statusBarColor, MaterialColors.isColorLight(num.intValue())));
        setLightNavigationBar(window, isUsingLightSystemBar(navigationBarColor, MaterialColors.isColorLight(num2.intValue())));
    }
}
