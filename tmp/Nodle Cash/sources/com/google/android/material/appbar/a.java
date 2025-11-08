package com.google.android.material.appbar;

import android.animation.ValueAnimator;
import android.content.res.ColorStateList;
import com.google.android.material.shape.MaterialShapeDrawable;

public final /* synthetic */ class a implements ValueAnimator.AnimatorUpdateListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ AppBarLayout f6619a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ ColorStateList f6620b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ ColorStateList f6621c;

    /* renamed from: d  reason: collision with root package name */
    public final /* synthetic */ MaterialShapeDrawable f6622d;

    /* renamed from: e  reason: collision with root package name */
    public final /* synthetic */ Integer f6623e;

    public /* synthetic */ a(AppBarLayout appBarLayout, ColorStateList colorStateList, ColorStateList colorStateList2, MaterialShapeDrawable materialShapeDrawable, Integer num) {
        this.f6619a = appBarLayout;
        this.f6620b = colorStateList;
        this.f6621c = colorStateList2;
        this.f6622d = materialShapeDrawable;
        this.f6623e = num;
    }

    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        this.f6619a.lambda$initializeLiftOnScrollWithColor$0(this.f6620b, this.f6621c, this.f6622d, this.f6623e, valueAnimator);
    }
}
