package com.google.android.material.search;

import android.animation.ValueAnimator;
import android.view.View;
import androidx.appcompat.graphics.drawable.DrawerArrowDrawable;
import com.google.android.material.internal.FadeThroughDrawable;

public final /* synthetic */ class c implements ValueAnimator.AnimatorUpdateListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f6660a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ Object f6661b;

    public /* synthetic */ c(Object obj, int i3) {
        this.f6660a = i3;
        this.f6661b = obj;
    }

    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        int i3 = this.f6660a;
        Object obj = this.f6661b;
        switch (i3) {
            case 0:
                ((View) obj).setAlpha(0.0f);
                return;
            case 1:
                ((DrawerArrowDrawable) obj).setProgress(((Float) valueAnimator.getAnimatedValue()).floatValue());
                return;
            default:
                ((FadeThroughDrawable) obj).setProgress(((Float) valueAnimator.getAnimatedValue()).floatValue());
                return;
        }
    }
}
