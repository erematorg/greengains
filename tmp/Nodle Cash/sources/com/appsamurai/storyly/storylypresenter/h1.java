package com.appsamurai.storyly.storylypresenter;

import com.appsamurai.storyly.storylypresenter.storylyfooter.d;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

public final class h1 extends Lambda implements Function0<Unit> {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ o f5000a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ d f5001b;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public h1(o oVar, d dVar) {
        super(0);
        this.f5000a = oVar;
        this.f5001b = dVar;
    }

    public Object invoke() {
        o oVar = this.f5000a;
        oVar.f5046a.a(oVar.getStorylyGroupItem$storyly_release(), this.f5000a.getStorylyItem(), new g1(this.f5000a, this.f5001b));
        return Unit.INSTANCE;
    }
}
