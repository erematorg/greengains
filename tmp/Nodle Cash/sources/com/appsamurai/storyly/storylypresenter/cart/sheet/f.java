package com.appsamurai.storyly.storylypresenter.cart.sheet;

import T1.a;
import com.appsamurai.storyly.data.managers.product.STRCart;
import com.appsamurai.storyly.data.managers.product.STRCartItem;
import java.util.List;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.Nullable;

public final class f extends Lambda implements Function1<STRCart, Unit> {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ com.appsamurai.storyly.storylypresenter.cart.list.f f4904a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ d f4905b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ Function0<Unit> f4906c;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public f(com.appsamurai.storyly.storylypresenter.cart.list.f fVar, d dVar, Function0<Unit> function0) {
        super(1);
        this.f4904a = fVar;
        this.f4905b = dVar;
        this.f4906c = function0;
    }

    public final void a(@Nullable STRCart sTRCart) {
        this.f4904a.post(new a(sTRCart, this.f4905b, (Function0) this.f4906c));
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        a((STRCart) obj);
        return Unit.INSTANCE;
    }

    public static final void a(STRCart sTRCart, d dVar, Function0 function0) {
        List<STRCartItem> items;
        List<STRCartItem> items2;
        Intrinsics.checkNotNullParameter(dVar, "this$0");
        Intrinsics.checkNotNullParameter(function0, "$onComplete");
        if (!(sTRCart == null || (items2 = sTRCart.getItems()) == null || !items2.isEmpty())) {
            int i3 = d.f4859w;
            dVar.a(b.Default);
        }
        dVar.getBottomIndicator().setState$storyly_release(a.Default);
        function0.invoke();
        dVar.getBottomIndicator().a(sTRCart);
        if (sTRCart != null && (items = sTRCart.getItems()) != null) {
            dVar.a((Function0<Unit>) new h(dVar, items));
        }
    }
}
