package com.appsamurai.storyly.storylypresenter;

import kotlin.Pair;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;

public final /* synthetic */ class a0 extends FunctionReferenceImpl implements Function1<Pair<? extends Float, ? extends Float>, Unit> {
    public a0(Object obj) {
        super(1, obj, o.class, "onSwipeUp", "onSwipeUp(Lkotlin/Pair;)V", 0);
    }

    public Object invoke(Object obj) {
        Pair pair = (Pair) obj;
        Intrinsics.checkNotNullParameter(pair, "p0");
        o.a((o) this.receiver, pair);
        return Unit.INSTANCE;
    }
}
