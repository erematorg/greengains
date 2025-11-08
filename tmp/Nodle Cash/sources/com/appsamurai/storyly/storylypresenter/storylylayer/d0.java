package com.appsamurai.storyly.storylypresenter.storylylayer;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

public final class d0 extends Lambda implements Function1<Integer, Unit> {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ x f5695a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ g f5696b;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public d0(x xVar, g gVar) {
        super(1);
        this.f5695a = xVar;
        this.f5696b = gVar;
    }

    public Object invoke(Object obj) {
        this.f5695a.a(this.f5696b, Integer.valueOf(((Number) obj).intValue()), Boolean.TRUE);
        return Unit.INSTANCE;
    }
}
