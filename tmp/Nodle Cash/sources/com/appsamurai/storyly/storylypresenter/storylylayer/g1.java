package com.appsamurai.storyly.storylypresenter.storylylayer;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

public final class g1 extends Lambda implements Function1<Integer, Unit> {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ x f5739a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ r f5740b;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public g1(x xVar, r rVar) {
        super(1);
        this.f5739a = xVar;
        this.f5740b = rVar;
    }

    public Object invoke(Object obj) {
        this.f5739a.a(this.f5740b, (Integer) obj, Boolean.TRUE);
        return Unit.INSTANCE;
    }
}
