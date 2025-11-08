package com.appsamurai.storyly.storylypresenter.storylylayer;

import android.animation.Animator;
import android.animation.AnimatorSet;
import org.jetbrains.annotations.NotNull;

public final class v1 implements Animator.AnimatorListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ u1 f6185a;

    public v1(u1 u1Var) {
        this.f6185a = u1Var;
    }

    public void onAnimationCancel(@NotNull Animator animator) {
        AnimatorSet animatorSet = this.f6185a.f6166w;
        if (animatorSet != null) {
            animatorSet.removeAllListeners();
        }
        u1 u1Var = this.f6185a;
        u1Var.f6167x = true;
        u1Var.getActionButton().setEnabled(true);
        this.f6185a.getPointButton().setEnabled(true);
    }

    public void onAnimationEnd(@NotNull Animator animator) {
    }

    public void onAnimationRepeat(@NotNull Animator animator) {
    }

    public void onAnimationStart(@NotNull Animator animator) {
    }
}
