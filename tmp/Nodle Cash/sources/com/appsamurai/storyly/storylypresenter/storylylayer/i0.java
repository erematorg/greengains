package com.appsamurai.storyly.storylypresenter.storylylayer;

import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

public final class i0 extends Lambda implements Function0<Unit> {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ x f5833a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ p f5834b;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public i0(x xVar, p pVar) {
        super(0);
        this.f5833a = xVar;
        this.f5834b = pVar;
    }

    public Object invoke() {
        x.a(this.f5833a, this.f5834b, (Integer) null, (Boolean) null, 6);
        return Unit.INSTANCE;
    }
}
