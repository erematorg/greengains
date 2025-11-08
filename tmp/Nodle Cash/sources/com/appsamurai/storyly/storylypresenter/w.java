package com.appsamurai.storyly.storylypresenter;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;

public final /* synthetic */ class w extends FunctionReferenceImpl implements Function1<Boolean, Unit> {
    public w(Object obj) {
        super(1, obj, o.class, "onOverlayVisibilityChange", "onOverlayVisibilityChange(Z)V", 0);
    }

    public Object invoke(Object obj) {
        o.a((o) this.receiver, ((Boolean) obj).booleanValue());
        return Unit.INSTANCE;
    }
}
