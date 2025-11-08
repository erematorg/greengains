package com.appsamurai.storyly.storylypresenter.storylylayer;

import android.animation.Animator;
import org.jetbrains.annotations.NotNull;

public final class l implements Animator.AnimatorListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ i f5843a;

    public l(i iVar) {
        this.f5843a = iVar;
    }

    public void onAnimationCancel(@NotNull Animator animator) {
    }

    public void onAnimationEnd(@NotNull Animator animator) {
    }

    public void onAnimationRepeat(@NotNull Animator animator) {
    }

    public void onAnimationStart(@NotNull Animator animator) {
        i.j(this.f5843a);
        this.f5843a.getPopupView().setVisibility(0);
        this.f5843a.getPopupBackgroundView().setVisibility(0);
        this.f5843a.getPopupBackgroundView().setEnabled(false);
        this.f5843a.getCommentHandler().post(new j(this.f5843a));
    }
}
