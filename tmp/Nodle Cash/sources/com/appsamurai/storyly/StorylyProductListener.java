package com.appsamurai.storyly;

import androidx.annotation.Keep;
import com.appsamurai.storyly.analytics.StorylyEvent;
import com.appsamurai.storyly.data.managers.product.STRCart;
import com.appsamurai.storyly.data.managers.product.STRCartEventResult;
import com.appsamurai.storyly.data.managers.product.STRCartItem;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bg\u0018\u00002\u00020\u0001J\u001e\u0010\b\u001a\u00020\u00072\u0006\u0010\u0003\u001a\u00020\u00022\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H&J\u0018\u0010\u000b\u001a\u00020\u00072\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\n\u001a\u00020\tH&JZ\u0010\u0014\u001a\u00020\u00072\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\n\u001a\u00020\t2\b\u0010\r\u001a\u0004\u0018\u00010\f2\b\u0010\u000f\u001a\u0004\u0018\u00010\u000e2\u0016\u0010\u0011\u001a\u0012\u0012\u0006\u0012\u0004\u0018\u00010\f\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u00102\u0014\u0010\u0013\u001a\u0010\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0010H&¨\u0006\u0015"}, d2 = {"Lcom/appsamurai/storyly/StorylyProductListener;", "", "Lcom/appsamurai/storyly/StorylyView;", "storylyView", "", "", "productIds", "", "storylyHydration", "Lcom/appsamurai/storyly/analytics/StorylyEvent;", "event", "storylyEvent", "Lcom/appsamurai/storyly/data/managers/product/STRCart;", "cart", "Lcom/appsamurai/storyly/data/managers/product/STRCartItem;", "change", "Lkotlin/Function1;", "onSuccess", "Lcom/appsamurai/storyly/data/managers/product/STRCartEventResult;", "onFail", "storylyUpdateCartEvent", "storyly_release"}, k = 1, mv = {1, 5, 1})
@Keep
public interface StorylyProductListener {
    void storylyEvent(@NotNull StorylyView storylyView, @NotNull StorylyEvent storylyEvent);

    void storylyHydration(@NotNull StorylyView storylyView, @NotNull List<String> list);

    void storylyUpdateCartEvent(@NotNull StorylyView storylyView, @NotNull StorylyEvent storylyEvent, @Nullable STRCart sTRCart, @Nullable STRCartItem sTRCartItem, @Nullable Function1<? super STRCart, Unit> function1, @Nullable Function1<? super STRCartEventResult, Unit> function12);
}
