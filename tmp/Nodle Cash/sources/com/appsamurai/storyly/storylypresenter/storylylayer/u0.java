package com.appsamurai.storyly.storylypresenter.storylylayer;

import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

public final class u0 extends Lambda implements Function0<Unit> {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ x f6145a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ s1 f6146b;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public u0(x xVar, s1 s1Var) {
        super(0);
        this.f6145a = xVar;
        this.f6146b = s1Var;
    }

    public Object invoke() {
        x.a(this.f6145a, this.f6146b, (Integer) null, Boolean.FALSE, 2);
        return Unit.INSTANCE;
    }
}
