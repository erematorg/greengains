package com.appsamurai.storyly.storylypresenter.storylylayer;

import android.animation.Animator;
import org.jetbrains.annotations.NotNull;

public final class k implements Animator.AnimatorListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ i f5839a;

    public k(i iVar) {
        this.f5839a = iVar;
    }

    public void onAnimationCancel(@NotNull Animator animator) {
    }

    public void onAnimationEnd(@NotNull Animator animator) {
        i iVar = this.f5839a;
        iVar.f5771H = false;
        iVar.getPopupBackgroundView().setEnabled(true);
        this.f5839a.getPopupSendButton().setEnabled(true);
    }

    public void onAnimationRepeat(@NotNull Animator animator) {
    }

    public void onAnimationStart(@NotNull Animator animator) {
    }
}
