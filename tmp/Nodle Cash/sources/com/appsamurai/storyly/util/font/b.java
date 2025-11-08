package com.appsamurai.storyly.util.font;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

public final class b extends Lambda implements Function1<Throwable, Unit> {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ a f6312a;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public b(a aVar) {
        super(1);
        this.f6312a = aVar;
    }

    public Object invoke(Object obj) {
        Throwable th = (Throwable) obj;
        this.f6312a.cancel();
        return Unit.INSTANCE;
    }
}
