package com.google.android.material.navigation;

import G0.e;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.drawerlayout.widget.DrawerLayout;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class DrawerLayoutUtils {
    private static final int DEFAULT_SCRIM_ALPHA = Color.alpha(DEFAULT_SCRIM_COLOR);
    private static final int DEFAULT_SCRIM_COLOR = -1728053248;

    private DrawerLayoutUtils() {
    }

    @NonNull
    public static Animator.AnimatorListener getScrimCloseAnimatorListener(@NonNull final DrawerLayout drawerLayout, @NonNull final View view) {
        return new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animator) {
                DrawerLayout.this.closeDrawer(view, false);
                DrawerLayout.this.setScrimColor(DrawerLayoutUtils.DEFAULT_SCRIM_COLOR);
            }
        };
    }

    @NonNull
    public static ValueAnimator.AnimatorUpdateListener getScrimCloseAnimatorUpdateListener(@NonNull DrawerLayout drawerLayout) {
        return new e(drawerLayout, 3);
    }
}
