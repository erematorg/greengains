package com.appsamurai.storyly.storylypresenter;

import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

public final class h extends Lambda implements Function0<Unit> {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ d f4999a;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public h(d dVar) {
        super(0);
        this.f4999a = dVar;
    }

    public Object invoke() {
        this.f4999a.f4973r = true;
        return Unit.INSTANCE;
    }
}
