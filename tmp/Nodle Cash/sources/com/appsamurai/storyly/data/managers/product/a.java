package com.appsamurai.storyly.data.managers.product;

import java.util.List;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import org.jetbrains.annotations.Nullable;

public final class a {

    /* renamed from: a  reason: collision with root package name */
    public boolean f4038a;
    @Nullable

    /* renamed from: b  reason: collision with root package name */
    public STRCart f4039b;
    @Nullable

    /* renamed from: c  reason: collision with root package name */
    public Function1<? super STRCart, Unit> f4040c;

    @Nullable
    public final Integer a() {
        List<STRCartItem> items;
        STRCart sTRCart = this.f4039b;
        if (sTRCart == null || (items = sTRCart.getItems()) == null) {
            return null;
        }
        return Integer.valueOf(items.size());
    }
}
