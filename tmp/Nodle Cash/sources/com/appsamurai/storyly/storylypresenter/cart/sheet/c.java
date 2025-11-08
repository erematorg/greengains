package com.appsamurai.storyly.storylypresenter.cart.sheet;

import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

public final class c extends Lambda implements Function0<Unit> {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ d f4858a;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public c(d dVar) {
        super(0);
        this.f4858a = dVar;
    }

    public Object invoke() {
        d dVar = this.f4858a;
        b bVar = b.WithGoToCheckout;
        int i3 = d.f4859w;
        dVar.a(bVar);
        return Unit.INSTANCE;
    }
}
