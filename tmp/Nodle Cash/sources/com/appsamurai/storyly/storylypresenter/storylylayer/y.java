package com.appsamurai.storyly.storylypresenter.storylylayer;

import java.util.List;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

public final class y extends Lambda implements Function1<List<o1>, Unit> {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ x f6237a;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public y(x xVar) {
        super(1);
        this.f6237a = xVar;
    }

    public Object invoke(Object obj) {
        List<o1> list = (List) obj;
        Intrinsics.checkNotNullParameter(list, "it");
        x xVar = this.f6237a;
        for (o1 a2 : list) {
            xVar.a(a2);
        }
        return Unit.INSTANCE;
    }
}
