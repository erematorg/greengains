package com.appsamurai.storyly.storylypresenter;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.reflect.KProperty;

public final /* synthetic */ class z extends FunctionReferenceImpl implements Function1<Float, Unit> {
    public z(Object obj) {
        super(1, obj, o.class, "onSwipeDownMove", "onSwipeDownMove(F)V", 0);
    }

    public Object invoke(Object obj) {
        float floatValue = ((Number) obj).floatValue();
        o oVar = (o) this.receiver;
        KProperty<Object>[] kPropertyArr = o.f5037L;
        oVar.h();
        oVar.getOnSwipeDown$storyly_release().invoke(Float.valueOf(floatValue));
        oVar.setY(floatValue);
        return Unit.INSTANCE;
    }
}
