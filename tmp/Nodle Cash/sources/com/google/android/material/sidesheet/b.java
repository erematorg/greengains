package com.google.android.material.sidesheet;

import android.animation.ValueAnimator;
import android.view.View;
import android.view.ViewGroup;

public final /* synthetic */ class b implements ValueAnimator.AnimatorUpdateListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ SideSheetBehavior f6685a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ ViewGroup.MarginLayoutParams f6686b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ int f6687c;

    /* renamed from: d  reason: collision with root package name */
    public final /* synthetic */ View f6688d;

    public /* synthetic */ b(SideSheetBehavior sideSheetBehavior, ViewGroup.MarginLayoutParams marginLayoutParams, int i3, View view) {
        this.f6685a = sideSheetBehavior;
        this.f6686b = marginLayoutParams;
        this.f6687c = i3;
        this.f6688d = view;
    }

    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        View view = this.f6688d;
        this.f6685a.lambda$getCoplanarFinishAnimatorUpdateListener$1(this.f6686b, this.f6687c, view, valueAnimator);
    }
}
