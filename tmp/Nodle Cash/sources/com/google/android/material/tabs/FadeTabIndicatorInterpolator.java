package com.google.android.material.tabs;

import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.view.View;
import androidx.annotation.NonNull;
import com.google.android.material.animation.AnimationUtils;

class FadeTabIndicatorInterpolator extends TabIndicatorInterpolator {
    private static final float FADE_THRESHOLD = 0.5f;

    public void updateIndicatorForOffset(TabLayout tabLayout, View view, View view2, float f2, @NonNull Drawable drawable) {
        int i3 = (f2 > 0.5f ? 1 : (f2 == 0.5f ? 0 : -1));
        if (i3 >= 0) {
            view = view2;
        }
        RectF calculateIndicatorWidthForTab = TabIndicatorInterpolator.calculateIndicatorWidthForTab(tabLayout, view);
        float lerp = i3 < 0 ? AnimationUtils.lerp(1.0f, 0.0f, 0.0f, 0.5f, f2) : AnimationUtils.lerp(0.0f, 1.0f, 0.5f, 1.0f, f2);
        drawable.setBounds((int) calculateIndicatorWidthForTab.left, drawable.getBounds().top, (int) calculateIndicatorWidthForTab.right, drawable.getBounds().bottom);
        drawable.setAlpha((int) (lerp * 255.0f));
    }
}
