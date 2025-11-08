package com.appsamurai.storyly.storylypresenter.storylylayer;

import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

public final class c0 extends Lambda implements Function0<Unit> {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ x f5667a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ g f5668b;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public c0(x xVar, g gVar) {
        super(0);
        this.f5667a = xVar;
        this.f5668b = gVar;
    }

    public Object invoke() {
        x.a(this.f5667a, this.f5668b, (Integer) null, Boolean.FALSE, 2);
        return Unit.INSTANCE;
    }
}
