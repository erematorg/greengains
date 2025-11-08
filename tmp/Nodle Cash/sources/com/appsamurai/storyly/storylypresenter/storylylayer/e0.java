package com.appsamurai.storyly.storylypresenter.storylylayer;

import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

public final class e0 extends Lambda implements Function0<Unit> {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ x f5722a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ h f5723b;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public e0(x xVar, h hVar) {
        super(0);
        this.f5722a = xVar;
        this.f5723b = hVar;
    }

    public Object invoke() {
        x.a(this.f5722a, this.f5723b, (Integer) null, (Boolean) null, 6);
        return Unit.INSTANCE;
    }
}
