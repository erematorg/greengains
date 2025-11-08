package com.appsamurai.storyly.storylypresenter;

import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.KProperty;

public final class i1 extends Lambda implements Function0<Unit> {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ o f5003a;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public i1(o oVar) {
        super(0);
        this.f5003a = oVar;
    }

    public Object invoke() {
        o oVar = this.f5003a;
        KProperty<Object>[] kPropertyArr = o.f5037L;
        oVar.d();
        return Unit.INSTANCE;
    }
}
