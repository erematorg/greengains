package com.appsamurai.storyly.storylypresenter;

import com.appsamurai.storyly.storylypresenter.o;
import com.appsamurai.storyly.storylypresenter.storylyheader.a;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

public final class d1 extends Lambda implements Function0<Unit> {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ o f4986a;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public d1(o oVar) {
        super(0);
        this.f4986a = oVar;
    }

    public Object invoke() {
        o oVar = this.f4986a;
        if (oVar.f5051f == o.a.Started) {
            a.c cVar = oVar.getStorylyHeaderView().f5461c;
            if (cVar == null) {
                Intrinsics.throwUninitializedPropertyAccessException("headerView");
                cVar = null;
            }
            cVar.m();
        }
        return Unit.INSTANCE;
    }
}
