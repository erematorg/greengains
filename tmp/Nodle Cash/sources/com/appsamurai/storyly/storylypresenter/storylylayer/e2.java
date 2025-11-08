package com.appsamurai.storyly.storylypresenter.storylylayer;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import org.jetbrains.annotations.Nullable;

public final class e2 extends AnimatorListenerAdapter {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ d2 f5726a;

    public e2(d2 d2Var) {
        this.f5726a = d2Var;
    }

    public void onAnimationStart(@Nullable Animator animator) {
        this.f5726a.getImageView().setVisibility(0);
    }
}
