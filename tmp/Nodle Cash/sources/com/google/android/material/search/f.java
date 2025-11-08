package com.google.android.material.search;

import android.animation.ValueAnimator;
import android.view.View;
import com.google.android.material.shape.MaterialShapeDrawable;

public final /* synthetic */ class f implements ValueAnimator.AnimatorUpdateListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ MaterialShapeDrawable f6668a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ View f6669b;

    public /* synthetic */ f(View view, MaterialShapeDrawable materialShapeDrawable) {
        this.f6668a = materialShapeDrawable;
        this.f6669b = view;
    }

    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        SearchBarAnimationHelper.lambda$getExpandedViewBackgroundUpdateListener$1(this.f6668a, this.f6669b, valueAnimator);
    }
}
