package com.appsamurai.storyly.storylypresenter.storylylayer;

import android.animation.Animator;
import android.view.View;
import org.jetbrains.annotations.NotNull;

public final class q implements Animator.AnimatorListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ p f5954a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ View f5955b;

    public q(p pVar, View view) {
        this.f5954a = pVar;
        this.f5955b = view;
    }

    public void onAnimationCancel(@NotNull Animator animator) {
    }

    public void onAnimationEnd(@NotNull Animator animator) {
        this.f5954a.a(this.f5955b).start();
    }

    public void onAnimationRepeat(@NotNull Animator animator) {
    }

    public void onAnimationStart(@NotNull Animator animator) {
    }
}
