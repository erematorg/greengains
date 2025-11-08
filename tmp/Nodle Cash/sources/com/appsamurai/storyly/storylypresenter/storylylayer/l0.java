package com.appsamurai.storyly.storylypresenter.storylylayer;

import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

public final class l0 extends Lambda implements Function0<Unit> {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ x f5844a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ w f5845b;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public l0(x xVar, w wVar) {
        super(0);
        this.f5844a = xVar;
        this.f5845b = wVar;
    }

    public Object invoke() {
        x.a(this.f5844a, this.f5845b, (Integer) null, Boolean.TRUE, 2);
        return Unit.INSTANCE;
    }
}
