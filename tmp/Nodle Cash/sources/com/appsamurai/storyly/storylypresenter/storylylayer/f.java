package com.appsamurai.storyly.storylypresenter.storylylayer;

import com.appsamurai.storyly.data.b0;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

public final class f extends Lambda implements Function0<Unit> {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ g f5727a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ b0 f5728b;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public f(g gVar, b0 b0Var) {
        super(0);
        this.f5727a = gVar;
        this.f5728b = b0Var;
    }

    public Object invoke() {
        this.f5727a.getOnUserActionClick$storyly_release().invoke(this.f5728b);
        return Unit.INSTANCE;
    }
}
