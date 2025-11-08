package com.appsamurai.storyly.storylypresenter.storylylayer;

import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

public final class c1 extends Lambda implements Function0<Unit> {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ x f5669a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ d2 f5670b;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public c1(x xVar, d2 d2Var) {
        super(0);
        this.f5669a = xVar;
        this.f5670b = d2Var;
    }

    public Object invoke() {
        x.a(this.f5669a, this.f5670b, (Integer) null, (Boolean) null, 6);
        return Unit.INSTANCE;
    }
}
