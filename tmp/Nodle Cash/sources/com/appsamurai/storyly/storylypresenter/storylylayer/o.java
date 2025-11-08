package com.appsamurai.storyly.storylypresenter.storylylayer;

import android.animation.Animator;
import org.jetbrains.annotations.NotNull;

public final class o implements Animator.AnimatorListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ p f5890a;

    public o(p pVar) {
        this.f5890a = pVar;
    }

    public void onAnimationCancel(@NotNull Animator animator) {
    }

    public void onAnimationEnd(@NotNull Animator animator) {
    }

    public void onAnimationRepeat(@NotNull Animator animator) {
    }

    public void onAnimationStart(@NotNull Animator animator) {
        this.f5890a.setEmojisClickable(false);
    }
}
