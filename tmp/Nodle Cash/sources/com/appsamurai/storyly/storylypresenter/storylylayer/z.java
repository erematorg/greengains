package com.appsamurai.storyly.storylypresenter.storylylayer;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class z extends AnimatorListenerAdapter {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ o1 f6240a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ x f6241b;

    public z(o1 o1Var, x xVar) {
        this.f6240a = o1Var;
        this.f6241b = xVar;
    }

    public void onAnimationEnd(@NotNull Animator animator) {
        Intrinsics.checkNotNullParameter(animator, "animation");
        this.f6241b.f6203b.removeView(this.f6240a);
    }

    public void onAnimationStart(@Nullable Animator animator) {
        this.f6240a.d();
    }
}
