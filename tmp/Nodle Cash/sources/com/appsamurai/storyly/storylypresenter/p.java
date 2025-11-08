package com.appsamurai.storyly.storylypresenter;

import com.appsamurai.storyly.storylypresenter.o;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.FunctionReferenceImpl;

public final /* synthetic */ class p extends FunctionReferenceImpl implements Function0<Unit> {
    public p(Object obj) {
        super(0, obj, o.class, "onPause", "onPause()V", 0);
    }

    public Object invoke() {
        o oVar = (o) this.receiver;
        if (oVar.f5051f == o.a.Started) {
            oVar.h();
        }
        return Unit.INSTANCE;
    }
}
