package com.appsamurai.storyly.storylypresenter.storylylayer;

import android.animation.Animator;
import org.jetbrains.annotations.NotNull;

public final class w1 implements Animator.AnimatorListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ u1 f6200a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ float f6201b;

    public w1(u1 u1Var, float f2) {
        this.f6200a = u1Var;
        this.f6201b = f2;
    }

    public void onAnimationCancel(@NotNull Animator animator) {
    }

    public void onAnimationEnd(@NotNull Animator animator) {
        u1 u1Var = this.f6200a;
        u1Var.f6167x = true;
        u1Var.getActionButton().setEnabled(true);
        this.f6200a.getPointButton().setEnabled(true);
        this.f6200a.getToolTip().setVisibility(8);
        this.f6200a.getToolTip().setTranslationY(this.f6200a.getToolTip().getTranslationY() + this.f6201b);
        this.f6200a.f();
    }

    public void onAnimationRepeat(@NotNull Animator animator) {
    }

    public void onAnimationStart(@NotNull Animator animator) {
    }
}
