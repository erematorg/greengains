package com.appsamurai.storyly.storylypresenter.storylyfooter;

import android.animation.Animator;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public final class e implements Animator.AnimatorListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ d f5456a;

    public e(d dVar) {
        this.f5456a = dVar;
    }

    public void onAnimationCancel(@NotNull Animator animator) {
    }

    public void onAnimationEnd(@NotNull Animator animator) {
        this.f5456a.b().setVisibility(4);
        Function0<Unit> function0 = this.f5456a.f5434e;
        if (function0 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("onFeedbackSheetDismiss");
            function0 = null;
        }
        function0.invoke();
    }

    public void onAnimationRepeat(@NotNull Animator animator) {
    }

    public void onAnimationStart(@NotNull Animator animator) {
    }
}
