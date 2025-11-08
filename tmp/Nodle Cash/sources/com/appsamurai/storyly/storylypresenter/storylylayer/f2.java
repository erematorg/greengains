package com.appsamurai.storyly.storylypresenter.storylylayer;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.graphics.drawable.TransitionDrawable;
import org.jetbrains.annotations.Nullable;

public final class f2 extends AnimatorListenerAdapter {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ TransitionDrawable f5732a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ d2 f5733b;

    public f2(TransitionDrawable transitionDrawable, d2 d2Var) {
        this.f5732a = transitionDrawable;
        this.f5733b = d2Var;
    }

    public void onAnimationStart(@Nullable Animator animator) {
        this.f5732a.startTransition(600);
        this.f5733b.getImageView().setVisibility(0);
    }
}
