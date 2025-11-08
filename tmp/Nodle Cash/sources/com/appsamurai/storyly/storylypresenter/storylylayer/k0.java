package com.appsamurai.storyly.storylypresenter.storylylayer;

import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

public final class k0 extends Lambda implements Function0<Unit> {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ x f5840a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ w f5841b;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public k0(x xVar, w wVar) {
        super(0);
        this.f5840a = xVar;
        this.f5841b = wVar;
    }

    public Object invoke() {
        x.a(this.f5840a, this.f5841b, (Integer) null, Boolean.FALSE, 2);
        return Unit.INSTANCE;
    }
}
