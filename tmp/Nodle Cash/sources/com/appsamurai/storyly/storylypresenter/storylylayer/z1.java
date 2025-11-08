package com.appsamurai.storyly.storylypresenter.storylylayer;

import android.animation.Animator;
import org.jetbrains.annotations.NotNull;

public final class z1 implements Animator.AnimatorListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ u1 f6244a;

    public z1(u1 u1Var) {
        this.f6244a = u1Var;
    }

    public void onAnimationCancel(@NotNull Animator animator) {
    }

    public void onAnimationEnd(@NotNull Animator animator) {
    }

    public void onAnimationRepeat(@NotNull Animator animator) {
    }

    public void onAnimationStart(@NotNull Animator animator) {
        this.f6244a.getActionButton().setEnabled(false);
    }
}
