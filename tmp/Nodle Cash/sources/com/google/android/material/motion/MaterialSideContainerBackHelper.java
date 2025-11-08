package com.google.android.material.motion;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.res.Resources;
import android.util.Property;
import android.view.View;
import android.view.ViewGroup;
import androidx.activity.BackEventCompat;
import androidx.annotation.GravityInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.annotation.VisibleForTesting;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.interpolator.view.animation.FastOutSlowInInterpolator;
import com.google.android.material.R;
import com.google.android.material.animation.AnimationUtils;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class MaterialSideContainerBackHelper extends MaterialBackAnimationHelper<View> {
    private final float maxScaleXDistanceGrow;
    private final float maxScaleXDistanceShrink;
    private final float maxScaleYDistance;

    public MaterialSideContainerBackHelper(@NonNull View view) {
        super(view);
        Resources resources = view.getResources();
        this.maxScaleXDistanceShrink = resources.getDimension(R.dimen.m3_back_progress_side_container_max_scale_x_distance_shrink);
        this.maxScaleXDistanceGrow = resources.getDimension(R.dimen.m3_back_progress_side_container_max_scale_x_distance_grow);
        this.maxScaleYDistance = resources.getDimension(R.dimen.m3_back_progress_side_container_max_scale_y_distance);
    }

    private boolean checkAbsoluteGravity(@GravityInt int i3, @GravityInt int i4) {
        return (GravityCompat.getAbsoluteGravity(i3, ViewCompat.getLayoutDirection(this.view)) & i4) == i4;
    }

    private int getEdgeMargin(boolean z2) {
        ViewGroup.LayoutParams layoutParams = this.view.getLayoutParams();
        if (!(layoutParams instanceof ViewGroup.MarginLayoutParams)) {
            return 0;
        }
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
        return z2 ? marginLayoutParams.leftMargin : marginLayoutParams.rightMargin;
    }

    public void cancelBackProgress() {
        if (super.onCancelBackProgress() != null) {
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.playTogether(new Animator[]{ObjectAnimator.ofFloat(this.view, View.SCALE_X, new float[]{1.0f}), ObjectAnimator.ofFloat(this.view, View.SCALE_Y, new float[]{1.0f})});
            V v2 = this.view;
            if (v2 instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) v2;
                for (int i3 = 0; i3 < viewGroup.getChildCount(); i3++) {
                    animatorSet.playTogether(new Animator[]{ObjectAnimator.ofFloat(viewGroup.getChildAt(i3), View.SCALE_Y, new float[]{1.0f})});
                }
            }
            animatorSet.setDuration((long) this.cancelDuration);
            animatorSet.start();
        }
    }

    public void finishBackProgress(@NonNull BackEventCompat backEventCompat, @GravityInt final int i3, @Nullable Animator.AnimatorListener animatorListener, @Nullable ValueAnimator.AnimatorUpdateListener animatorUpdateListener) {
        final boolean z2 = backEventCompat.getSwipeEdge() == 0;
        boolean checkAbsoluteGravity = checkAbsoluteGravity(i3, 3);
        float scaleX = (this.view.getScaleX() * ((float) this.view.getWidth())) + ((float) getEdgeMargin(checkAbsoluteGravity));
        V v2 = this.view;
        Property property = View.TRANSLATION_X;
        if (checkAbsoluteGravity) {
            scaleX = -scaleX;
        }
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(v2, property, new float[]{scaleX});
        if (animatorUpdateListener != null) {
            ofFloat.addUpdateListener(animatorUpdateListener);
        }
        ofFloat.setInterpolator(new FastOutSlowInInterpolator());
        ofFloat.setDuration((long) AnimationUtils.lerp(this.hideDurationMax, this.hideDurationMin, backEventCompat.getProgress()));
        ofFloat.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animator) {
                MaterialSideContainerBackHelper.this.view.setTranslationX(0.0f);
                MaterialSideContainerBackHelper.this.updateBackProgress(0.0f, z2, i3);
            }
        });
        if (animatorListener != null) {
            ofFloat.addListener(animatorListener);
        }
        ofFloat.start();
    }

    public void startBackProgress(@NonNull BackEventCompat backEventCompat) {
        super.onStartBackProgress(backEventCompat);
    }

    public void updateBackProgress(@NonNull BackEventCompat backEventCompat, @GravityInt int i3) {
        if (super.onUpdateBackProgress(backEventCompat) != null) {
            updateBackProgress(backEventCompat.getProgress(), backEventCompat.getSwipeEdge() == 0, i3);
        }
    }

    @VisibleForTesting
    public void updateBackProgress(float f2, boolean z2, @GravityInt int i3) {
        float f3;
        float interpolateProgress = interpolateProgress(f2);
        boolean checkAbsoluteGravity = checkAbsoluteGravity(i3, 3);
        boolean z3 = z2 == checkAbsoluteGravity;
        int width = this.view.getWidth();
        int height = this.view.getHeight();
        float f4 = (float) width;
        if (f4 > 0.0f) {
            float f5 = (float) height;
            if (f5 > 0.0f) {
                float f6 = this.maxScaleXDistanceShrink / f4;
                float f7 = this.maxScaleXDistanceGrow / f4;
                float f8 = this.maxScaleYDistance / f5;
                V v2 = this.view;
                if (checkAbsoluteGravity) {
                    f4 = 0.0f;
                }
                v2.setPivotX(f4);
                if (!z3) {
                    f7 = -f6;
                }
                float lerp = AnimationUtils.lerp(0.0f, f7, interpolateProgress);
                float f9 = lerp + 1.0f;
                this.view.setScaleX(f9);
                float lerp2 = 1.0f - AnimationUtils.lerp(0.0f, f8, interpolateProgress);
                this.view.setScaleY(lerp2);
                V v3 = this.view;
                if (v3 instanceof ViewGroup) {
                    ViewGroup viewGroup = (ViewGroup) v3;
                    for (int i4 = 0; i4 < viewGroup.getChildCount(); i4++) {
                        View childAt = viewGroup.getChildAt(i4);
                        if (checkAbsoluteGravity) {
                            f3 = (float) (childAt.getWidth() + (width - childAt.getRight()));
                        } else {
                            f3 = (float) (-childAt.getLeft());
                        }
                        childAt.setPivotX(f3);
                        childAt.setPivotY((float) (-childAt.getTop()));
                        float f10 = z3 ? 1.0f - lerp : 1.0f;
                        float f11 = lerp2 != 0.0f ? (f9 / lerp2) * f10 : 1.0f;
                        childAt.setScaleX(f10);
                        childAt.setScaleY(f11);
                    }
                }
            }
        }
    }
}
