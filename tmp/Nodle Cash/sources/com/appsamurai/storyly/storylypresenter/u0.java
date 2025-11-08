package com.appsamurai.storyly.storylypresenter;

import com.appsamurai.storyly.external.a;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

public final class u0 extends Lambda implements Function0<Unit> {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ o f6245a;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public u0(o oVar) {
        super(0);
        this.f6245a = oVar;
    }

    public Object invoke() {
        a aVar = this.f6245a.f5038A;
        if (aVar == null) {
            Intrinsics.throwUninitializedPropertyAccessException("loadingView");
            aVar = null;
        }
        aVar.b();
        return Unit.INSTANCE;
    }
}
