package com.appsamurai.storyly.storylypresenter.storylylayer;

import android.animation.Animator;
import org.jetbrains.annotations.NotNull;

public final class x1 implements Animator.AnimatorListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ u1 f6236a;

    public x1(u1 u1Var) {
        this.f6236a = u1Var;
    }

    public void onAnimationCancel(@NotNull Animator animator) {
    }

    public void onAnimationEnd(@NotNull Animator animator) {
    }

    public void onAnimationRepeat(@NotNull Animator animator) {
    }

    public void onAnimationStart(@NotNull Animator animator) {
        u1 u1Var = this.f6236a;
        u1Var.f6167x = false;
        u1Var.getPointButton().setEnabled(false);
        this.f6236a.getActionButton().setEnabled(false);
    }
}
