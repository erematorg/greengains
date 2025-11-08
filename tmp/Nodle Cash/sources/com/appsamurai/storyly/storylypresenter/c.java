package com.appsamurai.storyly.storylypresenter;

import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

public final class c extends Lambda implements Function1<Function0<? extends Unit>, Unit> {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ b f4769a;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public c(b bVar) {
        super(1);
        this.f4769a = bVar;
    }

    public Object invoke(Object obj) {
        Function0 function0 = (Function0) obj;
        Intrinsics.checkNotNullParameter(function0, "it");
        Function1<? super Function0<Unit>, Unit> function1 = this.f4769a.f4748c;
        if (function1 != null) {
            function1.invoke(function0);
        }
        return Unit.INSTANCE;
    }
}
