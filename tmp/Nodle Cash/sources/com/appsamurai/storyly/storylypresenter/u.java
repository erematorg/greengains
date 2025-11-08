package com.appsamurai.storyly.storylypresenter;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;

public final /* synthetic */ class u extends FunctionReferenceImpl implements Function1<Long, Unit> {
    public u(Object obj) {
        super(1, obj, o.class, "onSeek", "onSeek(J)V", 0);
    }

    public Object invoke(Object obj) {
        o.a((o) this.receiver, ((Number) obj).longValue());
        return Unit.INSTANCE;
    }
}
