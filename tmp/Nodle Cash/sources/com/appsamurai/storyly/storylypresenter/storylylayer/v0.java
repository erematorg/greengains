package com.appsamurai.storyly.storylypresenter.storylylayer;

import com.appsamurai.storyly.storylypresenter.storylylayer.storylyProductList.i;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

public final class v0 extends Lambda implements Function0<Unit> {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ x f6183a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ i f6184b;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public v0(x xVar, i iVar) {
        super(0);
        this.f6183a = xVar;
        this.f6184b = iVar;
    }

    public Object invoke() {
        x.a(this.f6183a, this.f6184b, (Integer) null, (Boolean) null, 6);
        return Unit.INSTANCE;
    }
}
