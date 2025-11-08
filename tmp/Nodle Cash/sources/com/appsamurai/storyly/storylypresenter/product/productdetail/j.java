package com.appsamurai.storyly.storylypresenter.product.productdetail;

import com.appsamurai.storyly.data.managers.product.STRProductVariant;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

public final class j extends Lambda implements Function1<STRProductVariant, Unit> {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ h f5260a;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public j(h hVar) {
        super(1);
        this.f5260a = hVar;
    }

    public Object invoke(Object obj) {
        h.a(this.f5260a, (STRProductVariant) obj);
        this.f5260a.getOnProductSelected$storyly_release().invoke(this.f5260a.f5220j);
        return Unit.INSTANCE;
    }
}
