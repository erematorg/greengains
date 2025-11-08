package com.google.android.material.internal;

import I0.b;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.graphics.Rect;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import com.google.android.material.animation.AnimationUtils;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class ExpandCollapseAnimationHelper {
    @Nullable
    private ValueAnimator.AnimatorUpdateListener additionalUpdateListener;
    private final View collapsedView;
    private int collapsedViewOffsetY;
    private long duration;
    private final List<View> endAnchoredViews = new ArrayList();
    /* access modifiers changed from: private */
    public final View expandedView;
    private int expandedViewOffsetY;
    private final List<AnimatorListenerAdapter> listeners = new ArrayList();

    public ExpandCollapseAnimationHelper(@NonNull View view, @NonNull View view2) {
        this.collapsedView = view;
        this.expandedView = view2;
    }

    private void addListeners(Animator animator, List<AnimatorListenerAdapter> list) {
        for (AnimatorListenerAdapter addListener : list) {
            animator.addListener(addListener);
        }
    }

    private AnimatorSet getAnimatorSet(boolean z2) {
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(new Animator[]{getExpandCollapseAnimator(z2), getExpandedViewChildrenAlphaAnimator(z2), getEndAnchoredViewsTranslateAnimator(z2)});
        return animatorSet;
    }

    private Animator getEndAnchoredViewsTranslateAnimator(boolean z2) {
        ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{(float) ((this.collapsedView.getRight() - this.expandedView.getRight()) + (this.expandedView.getLeft() - this.collapsedView.getLeft())), 0.0f});
        ofFloat.addUpdateListener(MultiViewUpdateListener.translationXListener((Collection<View>) this.endAnchoredViews));
        ofFloat.setDuration(this.duration);
        ofFloat.setInterpolator(ReversableAnimatedValueInterpolator.of(z2, AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR));
        return ofFloat;
    }

    private Animator getExpandCollapseAnimator(boolean z2) {
        Rect calculateRectFromBounds = ViewUtils.calculateRectFromBounds(this.collapsedView, this.collapsedViewOffsetY);
        Rect calculateRectFromBounds2 = ViewUtils.calculateRectFromBounds(this.expandedView, this.expandedViewOffsetY);
        Rect rect = new Rect(calculateRectFromBounds);
        ValueAnimator ofObject = ValueAnimator.ofObject(new RectEvaluator(rect), new Object[]{calculateRectFromBounds, calculateRectFromBounds2});
        ofObject.addUpdateListener(new b(this, rect, 2));
        ValueAnimator.AnimatorUpdateListener animatorUpdateListener = this.additionalUpdateListener;
        if (animatorUpdateListener != null) {
            ofObject.addUpdateListener(animatorUpdateListener);
        }
        ofObject.setDuration(this.duration);
        ofObject.setInterpolator(ReversableAnimatedValueInterpolator.of(z2, AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR));
        return ofObject;
    }

    private Animator getExpandedViewChildrenAlphaAnimator(boolean z2) {
        List<View> children = ViewUtils.getChildren(this.expandedView);
        ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        ofFloat.addUpdateListener(MultiViewUpdateListener.alphaListener((Collection<View>) children));
        ofFloat.setDuration(this.duration);
        ofFloat.setInterpolator(ReversableAnimatedValueInterpolator.of(z2, AnimationUtils.LINEAR_INTERPOLATOR));
        return ofFloat;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$getExpandCollapseAnimator$0(Rect rect, ValueAnimator valueAnimator) {
        ViewUtils.setBoundsFromRect(this.expandedView, rect);
    }

    @NonNull
    @CanIgnoreReturnValue
    public ExpandCollapseAnimationHelper addEndAnchoredViews(@NonNull View... viewArr) {
        Collections.addAll(this.endAnchoredViews, viewArr);
        return this;
    }

    @NonNull
    @CanIgnoreReturnValue
    public ExpandCollapseAnimationHelper addListener(@NonNull AnimatorListenerAdapter animatorListenerAdapter) {
        this.listeners.add(animatorListenerAdapter);
        return this;
    }

    @NonNull
    public Animator getCollapseAnimator() {
        AnimatorSet animatorSet = getAnimatorSet(false);
        animatorSet.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animator) {
                ExpandCollapseAnimationHelper.this.expandedView.setVisibility(8);
            }
        });
        addListeners(animatorSet, this.listeners);
        return animatorSet;
    }

    @NonNull
    public Animator getExpandAnimator() {
        AnimatorSet animatorSet = getAnimatorSet(true);
        animatorSet.addListener(new AnimatorListenerAdapter() {
            public void onAnimationStart(Animator animator) {
                ExpandCollapseAnimationHelper.this.expandedView.setVisibility(0);
            }
        });
        addListeners(animatorSet, this.listeners);
        return animatorSet;
    }

    @NonNull
    @CanIgnoreReturnValue
    public ExpandCollapseAnimationHelper setAdditionalUpdateListener(@Nullable ValueAnimator.AnimatorUpdateListener animatorUpdateListener) {
        this.additionalUpdateListener = animatorUpdateListener;
        return this;
    }

    @NonNull
    @CanIgnoreReturnValue
    public ExpandCollapseAnimationHelper setCollapsedViewOffsetY(int i3) {
        this.collapsedViewOffsetY = i3;
        return this;
    }

    @NonNull
    @CanIgnoreReturnValue
    public ExpandCollapseAnimationHelper setDuration(long j2) {
        this.duration = j2;
        return this;
    }

    @NonNull
    @CanIgnoreReturnValue
    public ExpandCollapseAnimationHelper setExpandedViewOffsetY(int i3) {
        this.expandedViewOffsetY = i3;
        return this;
    }

    @NonNull
    @CanIgnoreReturnValue
    public ExpandCollapseAnimationHelper addEndAnchoredViews(@NonNull Collection<View> collection) {
        this.endAnchoredViews.addAll(collection);
        return this;
    }
}
