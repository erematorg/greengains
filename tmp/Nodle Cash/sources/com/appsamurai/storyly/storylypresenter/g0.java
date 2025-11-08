package com.appsamurai.storyly.storylypresenter;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;

public final /* synthetic */ class g0 extends FunctionReferenceImpl implements Function1<Long, Unit> {
    public g0(Object obj) {
        super(1, obj, o.class, "onUserSeek", "onUserSeek(J)V", 0);
    }

    public Object invoke(Object obj) {
        o.b((o) this.receiver, ((Number) obj).longValue());
        return Unit.INSTANCE;
    }
}
