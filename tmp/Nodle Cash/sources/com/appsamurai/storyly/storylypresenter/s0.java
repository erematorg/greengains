package com.appsamurai.storyly.storylypresenter;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;

public final /* synthetic */ class s0 extends FunctionReferenceImpl implements Function1<Long, Unit> {
    public s0(Object obj) {
        super(1, obj, o.class, "onItemSessionTimeUpdated", "onItemSessionTimeUpdated(Ljava/lang/Long;)V", 0);
    }

    public Object invoke(Object obj) {
        o.a((o) this.receiver, (Long) obj);
        return Unit.INSTANCE;
    }
}
