package com.appsamurai.storyly.storylypresenter.storylylayer;

import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

public final class w0 extends Lambda implements Function0<Unit> {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ x f6199a;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public w0(x xVar) {
        super(0);
        this.f6199a = xVar;
    }

    public Object invoke() {
        x xVar = this.f6199a;
        xVar.f6220s = true;
        xVar.b().invoke();
        return Unit.INSTANCE;
    }
}
