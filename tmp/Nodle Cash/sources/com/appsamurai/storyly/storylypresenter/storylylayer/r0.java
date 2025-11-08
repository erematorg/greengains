package com.appsamurai.storyly.storylypresenter.storylylayer;

import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

public final class r0 extends Lambda implements Function0<Unit> {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ x f5983a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ r1 f5984b;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public r0(x xVar, r1 r1Var) {
        super(0);
        this.f5983a = xVar;
        this.f5984b = r1Var;
    }

    public Object invoke() {
        x.a(this.f5983a, this.f5984b, (Integer) null, (Boolean) null, 6);
        return Unit.INSTANCE;
    }
}
