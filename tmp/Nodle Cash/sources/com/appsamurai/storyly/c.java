package com.appsamurai.storyly;

import com.appsamurai.storyly.data.managers.product.STRCart;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

public final class c extends Lambda implements Function1<STRCart, Unit> {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ StorylyView f3546a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ Function1<STRCart, Unit> f3547b;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public c(StorylyView storylyView, Function1<? super STRCart, Unit> function1) {
        super(1);
        this.f3546a = storylyView;
        this.f3547b = function1;
    }

    public Object invoke(Object obj) {
        STRCart sTRCart = (STRCart) obj;
        if (sTRCart != null) {
            this.f3546a.updateCart(sTRCart);
        }
        Function1<STRCart, Unit> function1 = this.f3547b;
        if (function1 != null) {
            function1.invoke(sTRCart);
        }
        return Unit.INSTANCE;
    }
}
