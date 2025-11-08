package com.appsamurai.storyly.storylypresenter;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.reflect.KProperty;

public final /* synthetic */ class s extends FunctionReferenceImpl implements Function1<Boolean, Unit> {
    public s(Object obj) {
        super(1, obj, o.class, "onNextClick", "onNextClick(Z)V", 0);
    }

    public Object invoke(Object obj) {
        boolean booleanValue = ((Boolean) obj).booleanValue();
        KProperty<Object>[] kPropertyArr = o.f5037L;
        ((o) this.receiver).a(booleanValue);
        return Unit.INSTANCE;
    }
}
