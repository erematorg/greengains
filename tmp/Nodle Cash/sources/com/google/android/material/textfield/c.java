package com.google.android.material.textfield;

import android.animation.ValueAnimator;

public final /* synthetic */ class c implements ValueAnimator.AnimatorUpdateListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f6695a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ EndIconDelegate f6696b;

    public /* synthetic */ c(EndIconDelegate endIconDelegate, int i3) {
        this.f6695a = i3;
        this.f6696b = endIconDelegate;
    }

    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        int i3 = this.f6695a;
        EndIconDelegate endIconDelegate = this.f6696b;
        switch (i3) {
            case 0:
                ((ClearTextEndIconDelegate) endIconDelegate).lambda$getAlphaAnimator$3(valueAnimator);
                return;
            case 1:
                ((ClearTextEndIconDelegate) endIconDelegate).lambda$getScaleAnimator$4(valueAnimator);
                return;
            default:
                ((DropdownMenuEndIconDelegate) endIconDelegate).lambda$getAlphaAnimator$6(valueAnimator);
                return;
        }
    }
}
