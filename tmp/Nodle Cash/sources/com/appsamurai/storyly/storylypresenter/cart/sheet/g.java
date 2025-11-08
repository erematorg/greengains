package com.appsamurai.storyly.storylypresenter.cart.sheet;

import com.appsamurai.storyly.data.managers.product.STRCart;
import com.appsamurai.storyly.data.managers.product.STRCartEventResult;
import com.appsamurai.storyly.data.managers.product.STRCartItem;
import com.appsamurai.storyly.storylypresenter.cart.list.f;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

public final class g extends Lambda implements Function3<STRCartItem, Integer, Function0<? extends Unit>, Unit> {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ d f4907a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ f f4908b;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public g(d dVar, f fVar) {
        super(3);
        this.f4907a = dVar;
        this.f4908b = fVar;
    }

    public Object invoke(Object obj, Object obj2, Object obj3) {
        STRCartItem sTRCartItem = (STRCartItem) obj;
        int intValue = ((Number) obj2).intValue();
        Function0 function0 = (Function0) obj3;
        Intrinsics.checkNotNullParameter(function0, "onComplete");
        this.f4907a.getBottomIndicator().setState$storyly_release(a.Loading);
        f fVar = new f(this.f4908b, this.f4907a, function0);
        e eVar = new e(this.f4908b, this.f4907a, function0);
        Function4<STRCartItem, Integer, Function1<? super STRCart, Unit>, Function1<? super STRCartEventResult, Unit>, Unit> onUpdateCart$storyly_release = this.f4907a.getOnUpdateCart$storyly_release();
        if (onUpdateCart$storyly_release != null) {
            onUpdateCart$storyly_release.invoke(sTRCartItem, Integer.valueOf(intValue), fVar, eVar);
        }
        return Unit.INSTANCE;
    }
}
