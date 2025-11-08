package com.appsamurai.storyly.storylypresenter.storylylayer;

import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

public final class o0 extends Lambda implements Function0<Unit> {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ x f5891a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ u f5892b;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public o0(x xVar, u uVar) {
        super(0);
        this.f5891a = xVar;
        this.f5892b = uVar;
    }

    public Object invoke() {
        x.a(this.f5891a, this.f5892b, (Integer) null, Boolean.TRUE, 2);
        return Unit.INSTANCE;
    }
}
