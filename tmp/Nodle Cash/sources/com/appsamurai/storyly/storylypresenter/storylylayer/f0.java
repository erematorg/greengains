package com.appsamurai.storyly.storylypresenter.storylylayer;

import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

public final class f0 extends Lambda implements Function0<Unit> {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ x f5729a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ i f5730b;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public f0(x xVar, i iVar) {
        super(0);
        this.f5729a = xVar;
        this.f5730b = iVar;
    }

    public Object invoke() {
        x.a(this.f5729a, this.f5730b, (Integer) null, (Boolean) null, 6);
        return Unit.INSTANCE;
    }
}
