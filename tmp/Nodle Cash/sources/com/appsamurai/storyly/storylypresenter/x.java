package com.appsamurai.storyly.storylypresenter;

import android.view.animation.ScaleAnimation;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.reflect.KProperty;

public final /* synthetic */ class x extends FunctionReferenceImpl implements Function0<Unit> {
    public x(Object obj) {
        super(0, obj, o.class, "onSwipeDownComplete", "onSwipeDownComplete()V", 0);
    }

    public Object invoke() {
        o oVar = (o) this.receiver;
        KProperty<Object>[] kPropertyArr = o.f5037L;
        oVar.getOnPullDown$storyly_release().invoke(Boolean.TRUE);
        ScaleAnimation scaleAnimation = new ScaleAnimation(1.0f, 0.0f, 1.0f, 0.0f, 0, (float) (oVar.getWidth() / 2), 0, (float) oVar.getHeight());
        scaleAnimation.setAnimationListener(new c0(oVar));
        scaleAnimation.setDuration(200);
        Unit unit = Unit.INSTANCE;
        oVar.startAnimation(scaleAnimation);
        return Unit.INSTANCE;
    }
}
