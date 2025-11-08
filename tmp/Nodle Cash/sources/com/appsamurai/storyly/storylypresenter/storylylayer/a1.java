package com.appsamurai.storyly.storylypresenter.storylylayer;

import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

public final class a1 extends Lambda implements Function0<Unit> {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ x f5574a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ b2 f5575b;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public a1(x xVar, b2 b2Var) {
        super(0);
        this.f5574a = xVar;
        this.f5575b = b2Var;
    }

    public Object invoke() {
        x.a(this.f5574a, this.f5575b, (Integer) null, (Boolean) null, 6);
        return Unit.INSTANCE;
    }
}
