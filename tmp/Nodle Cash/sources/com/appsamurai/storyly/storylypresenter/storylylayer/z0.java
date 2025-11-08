package com.appsamurai.storyly.storylypresenter.storylylayer;

import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

public final class z0 extends Lambda implements Function0<Unit> {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ x f6242a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ a2 f6243b;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public z0(x xVar, a2 a2Var) {
        super(0);
        this.f6242a = xVar;
        this.f6243b = a2Var;
    }

    public Object invoke() {
        x.a(this.f6242a, this.f6243b, (Integer) null, (Boolean) null, 6);
        return Unit.INSTANCE;
    }
}
