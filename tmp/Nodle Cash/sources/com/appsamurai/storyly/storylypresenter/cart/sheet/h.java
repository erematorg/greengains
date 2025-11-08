package com.appsamurai.storyly.storylypresenter.cart.sheet;

import com.appsamurai.storyly.data.managers.product.STRCartItem;
import java.util.List;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

public final class h extends Lambda implements Function0<Unit> {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ d f4909a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ List<STRCartItem> f4910b;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public h(d dVar, List<STRCartItem> list) {
        super(0);
        this.f4909a = dVar;
        this.f4910b = list;
    }

    public Object invoke() {
        this.f4909a.getCartRecyclerView().setup(this.f4910b);
        return Unit.INSTANCE;
    }
}
