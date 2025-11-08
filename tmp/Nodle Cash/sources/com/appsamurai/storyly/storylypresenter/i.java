package com.appsamurai.storyly.storylypresenter;

import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

public final class i extends Lambda implements Function0<Unit> {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ d f5002a;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public i(d dVar) {
        super(0);
        this.f5002a = dVar;
    }

    public Object invoke() {
        this.f5002a.f4973r = false;
        return Unit.INSTANCE;
    }
}
