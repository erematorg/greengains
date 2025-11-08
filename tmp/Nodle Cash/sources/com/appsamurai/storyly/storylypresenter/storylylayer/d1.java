package com.appsamurai.storyly.storylypresenter.storylylayer;

import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

public final class d1 extends Lambda implements Function0<Unit> {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ x f5697a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ g2 f5698b;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public d1(x xVar, g2 g2Var) {
        super(0);
        this.f5697a = xVar;
        this.f5698b = g2Var;
    }

    public Object invoke() {
        x.a(this.f5697a, this.f5698b, (Integer) null, (Boolean) null, 6);
        return Unit.INSTANCE;
    }
}
