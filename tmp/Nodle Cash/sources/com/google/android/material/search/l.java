package com.google.android.material.search;

import android.animation.ValueAnimator;
import android.graphics.Rect;

public final /* synthetic */ class l implements ValueAnimator.AnimatorUpdateListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ SearchViewAnimationHelper f6676a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ float f6677b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ float f6678c;

    /* renamed from: d  reason: collision with root package name */
    public final /* synthetic */ Rect f6679d;

    public /* synthetic */ l(SearchViewAnimationHelper searchViewAnimationHelper, float f2, float f3, Rect rect) {
        this.f6676a = searchViewAnimationHelper;
        this.f6677b = f2;
        this.f6678c = f3;
        this.f6679d = rect;
    }

    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        this.f6676a.lambda$getRootViewAnimator$2(this.f6677b, this.f6678c, this.f6679d, valueAnimator);
    }
}
