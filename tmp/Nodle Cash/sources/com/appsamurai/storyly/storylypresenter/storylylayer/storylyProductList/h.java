package com.appsamurai.storyly.storylypresenter.storylylayer.storylyProductList;

import com.appsamurai.storyly.data.h0;
import com.appsamurai.storyly.data.managers.product.STRProductItem;
import java.util.List;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

public final class h extends Lambda implements Function1<List<? extends STRProductItem>, Unit> {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ i f6095a;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public h(i iVar) {
        super(1);
        this.f6095a = iVar;
    }

    public Object invoke(Object obj) {
        List<STRProductItem> list = (List) obj;
        h0 h0Var = this.f6095a.f6097h;
        if (h0Var == null) {
            Intrinsics.throwUninitializedPropertyAccessException("storylyLayer");
            h0Var = null;
        }
        h0Var.f3743r = list;
        this.f6095a.getOnProductClick$storyly_release().invoke(this.f6095a.getStorylyLayerItem$storyly_release());
        return Unit.INSTANCE;
    }
}
