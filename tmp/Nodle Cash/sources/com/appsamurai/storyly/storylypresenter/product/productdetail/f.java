package com.appsamurai.storyly.storylypresenter.product.productdetail;

import A0.C0202c;
import com.appsamurai.storyly.data.managers.product.STRCart;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

public final class f extends Lambda implements Function1<STRCart, Unit> {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ b f5204a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ h f5205b;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public f(b bVar, h hVar) {
        super(1);
        this.f5204a = bVar;
        this.f5205b = hVar;
    }

    public final void a() {
        this.f5204a.post(new C0202c(this.f5205b, 0));
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        STRCart sTRCart = (STRCart) obj;
        a();
        return Unit.INSTANCE;
    }

    public static final void a(h hVar) {
        Intrinsics.checkNotNullParameter(hVar, "this$0");
        hVar.getVariantStackView().setSelectionState(true);
        hVar.getBottomIndicator().setState$storyly_release(c.Default);
        hVar.a(a.WithSuccess);
    }
}
