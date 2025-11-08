package com.appsamurai.storyly.data.managers.processing;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

public final class h extends Lambda implements Function1<a, Unit> {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ f f4011a;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public h(f fVar) {
        super(1);
        this.f4011a = fVar;
    }

    public Object invoke(Object obj) {
        Unit unit;
        a aVar = (a) obj;
        if (aVar == null) {
            unit = null;
        } else {
            this.f4011a.a(aVar);
            unit = Unit.INSTANCE;
        }
        if (unit == null) {
            f.a(this.f4011a, (String) null, false, false, 7);
        }
        return Unit.INSTANCE;
    }
}
