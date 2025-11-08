package com.appsamurai.storyly.storylypresenter;

import com.appsamurai.storyly.storylypresenter.storylyfooter.a;
import java.util.List;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

public final class z0 extends Lambda implements Function1<List<? extends Pair<? extends Integer, ? extends Float>>, Unit> {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ o f6248a;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public z0(o oVar) {
        super(1);
        this.f6248a = oVar;
    }

    public Object invoke(Object obj) {
        List list = (List) obj;
        Intrinsics.checkNotNullParameter(list, "parts");
        a f2 = this.f6248a.getStorylyFooterView();
        f2.getClass();
        Intrinsics.checkNotNullParameter(list, "parts");
        a.C0032a aVar = f2.f5346c;
        if (aVar != null) {
            aVar.a((List<Pair<Integer, Float>>) list);
            Unit unit = Unit.INSTANCE;
        }
        return Unit.INSTANCE;
    }
}
