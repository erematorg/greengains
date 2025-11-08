package com.appsamurai.storyly.storylypresenter.share;

import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

public final class e extends Lambda implements Function0<Unit> {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ Function0<Unit> f5324a;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public e(Function0<Unit> function0) {
        super(0);
        this.f5324a = function0;
    }

    public Object invoke() {
        Function0<Unit> function0 = this.f5324a;
        if (function0 != null) {
            function0.invoke();
        }
        return Unit.INSTANCE;
    }
}
