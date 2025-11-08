package com.appsamurai.storyly;

import com.appsamurai.storyly.analytics.StorylyEvent;
import com.appsamurai.storyly.data.managers.product.STRCart;
import com.appsamurai.storyly.data.managers.product.STRCartEventResult;
import com.appsamurai.storyly.data.managers.product.STRCartItem;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function5;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;

public final /* synthetic */ class p extends FunctionReferenceImpl implements Function5<StorylyEvent, Function1<? super STRCart, ? extends Unit>, Function1<? super STRCartEventResult, ? extends Unit>, STRCart, STRCartItem, Unit> {
    public p(Object obj) {
        super(5, obj, StorylyView.class, "onStoryProductEvent", "onStoryProductEvent(Lcom/appsamurai/storyly/analytics/StorylyEvent;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lcom/appsamurai/storyly/data/managers/product/STRCart;Lcom/appsamurai/storyly/data/managers/product/STRCartItem;)V", 0);
    }

    public Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
        StorylyEvent storylyEvent = (StorylyEvent) obj;
        Intrinsics.checkNotNullParameter(storylyEvent, "p0");
        StorylyView.access$onStoryProductEvent((StorylyView) this.receiver, storylyEvent, (Function1) obj2, (Function1) obj3, (STRCart) obj4, (STRCartItem) obj5);
        return Unit.INSTANCE;
    }
}
