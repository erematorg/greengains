package com.appsamurai.storyly.storylypresenter.storylylayer;

import com.appsamurai.storyly.data.b0;
import java.util.Map;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

public final class m1 extends Lambda implements Function1<Map<String, o1>, Unit> {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ b0 f5884a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ x f5885b;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public m1(b0 b0Var, x xVar) {
        super(1);
        this.f5884a = b0Var;
        this.f5885b = xVar;
    }

    public Object invoke(Object obj) {
        Map map = (Map) obj;
        Intrinsics.checkNotNullParameter(map, "it");
        o1 o1Var = (o1) map.get(this.f5884a.f3614i);
        if (o1Var != null) {
            x xVar = this.f5885b;
            xVar.getClass();
            o1Var.animate().alpha(0.0f).setDuration(400).setListener(new z(o1Var, xVar));
        }
        map.remove(this.f5884a.f3614i);
        return Unit.INSTANCE;
    }
}
