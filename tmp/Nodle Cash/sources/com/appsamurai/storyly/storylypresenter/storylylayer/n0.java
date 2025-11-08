package com.appsamurai.storyly.storylypresenter.storylylayer;

import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

public final class n0 extends Lambda implements Function0<Unit> {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ x f5887a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ u f5888b;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public n0(x xVar, u uVar) {
        super(0);
        this.f5887a = xVar;
        this.f5888b = uVar;
    }

    public Object invoke() {
        x.a(this.f5887a, this.f5888b, (Integer) null, Boolean.FALSE, 2);
        return Unit.INSTANCE;
    }
}
