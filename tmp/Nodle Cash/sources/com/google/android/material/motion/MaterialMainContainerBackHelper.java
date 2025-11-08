package com.google.android.material.motion;

import G0.e;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Build;
import android.view.RoundedCorner;
import android.view.View;
import android.view.WindowInsets;
import androidx.activity.BackEventCompat;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.annotation.VisibleForTesting;
import androidx.compose.animation.core.a;
import com.google.android.material.R;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.internal.ClippableRoundedCornerLayout;
import com.google.android.material.internal.ViewUtils;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class MaterialMainContainerBackHelper extends MaterialBackAnimationHelper<View> {
    private static final float MIN_SCALE = 0.9f;
    @Nullable
    private Integer expandedCornerSize;
    @Nullable
    private Rect initialHideFromClipBounds;
    @Nullable
    private Rect initialHideToClipBounds;
    private float initialTouchY;
    private final float maxTranslationY;
    private final float minEdgeGap;

    public MaterialMainContainerBackHelper(@NonNull View view) {
        super(view);
        Resources resources = view.getResources();
        this.minEdgeGap = resources.getDimension(R.dimen.m3_back_progress_main_container_min_edge_gap);
        this.maxTranslationY = resources.getDimension(R.dimen.m3_back_progress_main_container_max_translation_y);
    }

    @NonNull
    private ValueAnimator createCornerAnimator(ClippableRoundedCornerLayout clippableRoundedCornerLayout) {
        ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{clippableRoundedCornerLayout.getCornerRadius(), (float) getExpandedCornerSize()});
        ofFloat.addUpdateListener(new e(clippableRoundedCornerLayout, 2));
        return ofFloat;
    }

    @NonNull
    private AnimatorSet createResetScaleAndTranslationAnimator(@Nullable final View view) {
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(new Animator[]{ObjectAnimator.ofFloat(this.view, View.SCALE_X, new float[]{1.0f}), ObjectAnimator.ofFloat(this.view, View.SCALE_Y, new float[]{1.0f}), ObjectAnimator.ofFloat(this.view, View.TRANSLATION_X, new float[]{0.0f}), ObjectAnimator.ofFloat(this.view, View.TRANSLATION_Y, new float[]{0.0f})});
        animatorSet.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animator) {
                View view = view;
                if (view != null) {
                    view.setVisibility(0);
                }
            }
        });
        return animatorSet;
    }

    private int getMaxDeviceCornerRadius() {
        WindowInsets rootWindowInsets;
        if (Build.VERSION.SDK_INT < 31 || (rootWindowInsets = this.view.getRootWindowInsets()) == null) {
            return 0;
        }
        return Math.max(Math.max(getRoundedCornerRadius(rootWindowInsets, 0), getRoundedCornerRadius(rootWindowInsets, 1)), Math.max(getRoundedCornerRadius(rootWindowInsets, 3), getRoundedCornerRadius(rootWindowInsets, 2)));
    }

    @RequiresApi(31)
    private int getRoundedCornerRadius(WindowInsets windowInsets, int i3) {
        RoundedCorner g2 = windowInsets.getRoundedCorner(i3);
        if (g2 != null) {
            return g2.getRadius();
        }
        return 0;
    }

    private boolean isAtTopOfScreen() {
        int[] iArr = new int[2];
        this.view.getLocationOnScreen(iArr);
        return iArr[1] == 0;
    }

    private void resetInitialValues() {
        this.initialTouchY = 0.0f;
        this.initialHideToClipBounds = null;
        this.initialHideFromClipBounds = null;
    }

    public void cancelBackProgress(@Nullable View view) {
        if (super.onCancelBackProgress() != null) {
            AnimatorSet createResetScaleAndTranslationAnimator = createResetScaleAndTranslationAnimator(view);
            V v2 = this.view;
            if (v2 instanceof ClippableRoundedCornerLayout) {
                createResetScaleAndTranslationAnimator.playTogether(new Animator[]{createCornerAnimator((ClippableRoundedCornerLayout) v2)});
            }
            createResetScaleAndTranslationAnimator.setDuration((long) this.cancelDuration);
            createResetScaleAndTranslationAnimator.start();
            resetInitialValues();
        }
    }

    public void finishBackProgress(long j2, @Nullable View view) {
        AnimatorSet createResetScaleAndTranslationAnimator = createResetScaleAndTranslationAnimator(view);
        createResetScaleAndTranslationAnimator.setDuration(j2);
        createResetScaleAndTranslationAnimator.start();
        resetInitialValues();
    }

    public int getExpandedCornerSize() {
        if (this.expandedCornerSize == null) {
            this.expandedCornerSize = Integer.valueOf(isAtTopOfScreen() ? getMaxDeviceCornerRadius() : 0);
        }
        return this.expandedCornerSize.intValue();
    }

    @Nullable
    public Rect getInitialHideFromClipBounds() {
        return this.initialHideFromClipBounds;
    }

    @Nullable
    public Rect getInitialHideToClipBounds() {
        return this.initialHideToClipBounds;
    }

    public void startBackProgress(@NonNull BackEventCompat backEventCompat, @Nullable View view) {
        super.onStartBackProgress(backEventCompat);
        startBackProgress(backEventCompat.getTouchY(), view);
    }

    public void updateBackProgress(@NonNull BackEventCompat backEventCompat, @Nullable View view, float f2) {
        if (super.onUpdateBackProgress(backEventCompat) != null) {
            if (!(view == null || view.getVisibility() == 4)) {
                view.setVisibility(4);
            }
            updateBackProgress(backEventCompat.getProgress(), backEventCompat.getSwipeEdge() == 0, backEventCompat.getTouchY(), f2);
        }
    }

    @VisibleForTesting
    public void startBackProgress(float f2, @Nullable View view) {
        this.initialHideToClipBounds = ViewUtils.calculateRectFromBounds(this.view);
        if (view != null) {
            this.initialHideFromClipBounds = ViewUtils.calculateOffsetRectFromBounds(this.view, view);
        }
        this.initialTouchY = f2;
    }

    @VisibleForTesting
    public void updateBackProgress(float f2, boolean z2, float f3, float f4) {
        float interpolateProgress = interpolateProgress(f2);
        float width = (float) this.view.getWidth();
        float height = (float) this.view.getHeight();
        if (width > 0.0f && height > 0.0f) {
            float lerp = AnimationUtils.lerp(1.0f, (float) MIN_SCALE, interpolateProgress);
            float lerp2 = AnimationUtils.lerp(0.0f, Math.max(0.0f, a.a(width, MIN_SCALE, width, 2.0f) - this.minEdgeGap), interpolateProgress) * ((float) (z2 ? 1 : -1));
            float min = Math.min(Math.max(0.0f, a.a(lerp, height, height, 2.0f) - this.minEdgeGap), this.maxTranslationY);
            float f5 = f3 - this.initialTouchY;
            float lerp3 = AnimationUtils.lerp(0.0f, min, Math.abs(f5) / height) * Math.signum(f5);
            this.view.setScaleX(lerp);
            this.view.setScaleY(lerp);
            this.view.setTranslationX(lerp2);
            this.view.setTranslationY(lerp3);
            V v2 = this.view;
            if (v2 instanceof ClippableRoundedCornerLayout) {
                ((ClippableRoundedCornerLayout) v2).updateCornerRadius(AnimationUtils.lerp((float) getExpandedCornerSize(), f4, interpolateProgress));
            }
        }
    }
}
