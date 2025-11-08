package com.appsamurai.storyly.storylypresenter.storylylayer;

import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

public final class e1 extends Lambda implements Function0<Unit> {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ x f5724a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ r f5725b;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public e1(x xVar, r rVar) {
        super(0);
        this.f5724a = xVar;
        this.f5725b = rVar;
    }

    public Object invoke() {
        x.a(this.f5724a, this.f5725b, (Integer) null, Boolean.FALSE, 2);
        return Unit.INSTANCE;
    }
}
