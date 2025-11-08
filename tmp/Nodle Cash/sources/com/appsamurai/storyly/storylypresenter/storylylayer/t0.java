package com.appsamurai.storyly.storylypresenter.storylylayer;

import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

public final class t0 extends Lambda implements Function0<Unit> {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ x f6105a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ s1 f6106b;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public t0(x xVar, s1 s1Var) {
        super(0);
        this.f6105a = xVar;
        this.f6106b = s1Var;
    }

    public Object invoke() {
        x.a(this.f6105a, this.f6106b, (Integer) null, Boolean.TRUE, 2);
        return Unit.INSTANCE;
    }
}
