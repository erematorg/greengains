package com.appsamurai.storyly.storylypresenter;

import com.appsamurai.storyly.storylypresenter.o;
import com.appsamurai.storyly.storylypresenter.storylyheader.a;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

public final class c1 extends Lambda implements Function0<Unit> {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ o f4771a;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public c1(o oVar) {
        super(0);
        this.f4771a = oVar;
    }

    public Object invoke() {
        o oVar = this.f4771a;
        if (oVar.f5051f == o.a.Started) {
            a.c cVar = oVar.getStorylyHeaderView().f5461c;
            if (cVar == null) {
                Intrinsics.throwUninitializedPropertyAccessException("headerView");
                cVar = null;
            }
            cVar.k();
        }
        return Unit.INSTANCE;
    }
}
