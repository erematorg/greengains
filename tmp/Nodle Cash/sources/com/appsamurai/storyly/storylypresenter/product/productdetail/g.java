package com.appsamurai.storyly.storylypresenter.product.productdetail;

import com.appsamurai.storyly.data.managers.product.STRProductItem;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

public final class g extends Lambda implements Function0<Unit> {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ h f5206a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ b f5207b;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public g(h hVar, b bVar) {
        super(0);
        this.f5206a = hVar;
        this.f5207b = bVar;
    }

    public Object invoke() {
        this.f5206a.getVariantStackView().setSelectionState(false);
        f fVar = new f(this.f5207b, this.f5206a);
        e eVar = new e(this.f5207b, this.f5206a);
        h hVar = this.f5206a;
        STRProductItem sTRProductItem = hVar.f5220j;
        if (sTRProductItem != null) {
            hVar.getOnBuyNowClick$storyly_release().invoke(sTRProductItem, Integer.valueOf(hVar.getBottomIndicator().getQuantity$storyly_release()), fVar, eVar);
        }
        if (this.f5206a.getMessageContainer().getVisibility() == 0) {
            h hVar2 = this.f5206a;
            h.a(hVar2, (Function0) new d(hVar2));
        }
        return Unit.INSTANCE;
    }
}
