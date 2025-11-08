package com.appsamurai.storyly.storylypresenter;

import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.FunctionReferenceImpl;

public final /* synthetic */ class q extends FunctionReferenceImpl implements Function0<Unit> {
    public q(Object obj) {
        super(0, obj, o.class, "resume", "resume()V", 0);
    }

    public Object invoke() {
        ((o) this.receiver).j();
        return Unit.INSTANCE;
    }
}
