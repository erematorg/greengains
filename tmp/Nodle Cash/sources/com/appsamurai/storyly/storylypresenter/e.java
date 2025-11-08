package com.appsamurai.storyly.storylypresenter;

import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

public final class e extends Lambda implements Function0<Unit> {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ d f4987a;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public e(d dVar) {
        super(0);
        this.f4987a = dVar;
    }

    public Object invoke() {
        this.f4987a.getOnClosed$storyly_release().invoke();
        return Unit.INSTANCE;
    }
}
