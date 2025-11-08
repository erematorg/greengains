package com.appsamurai.storyly.storylypresenter.storylylayer;

import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

public final class b1 extends Lambda implements Function0<Unit> {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ x f5624a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ c2 f5625b;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public b1(x xVar, c2 c2Var) {
        super(0);
        this.f5624a = xVar;
        this.f5625b = c2Var;
    }

    public Object invoke() {
        x.a(this.f5624a, this.f5625b, (Integer) null, (Boolean) null, 6);
        return Unit.INSTANCE;
    }
}
