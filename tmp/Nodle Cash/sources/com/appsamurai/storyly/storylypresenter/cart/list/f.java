package com.appsamurai.storyly.storylypresenter.cart.list;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.appsamurai.storyly.config.StorylyConfig;
import com.appsamurai.storyly.data.managers.product.STRCartItem;
import com.appsamurai.storyly.util.m;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.List;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@SuppressLint({"ViewConstructor"})
public final class f extends RecyclerView {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final c f4847a;
    @Nullable

    /* renamed from: b  reason: collision with root package name */
    public Function3<? super STRCartItem, ? super Integer, ? super Function0<Unit>, Unit> f4848b;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public f(@NotNull Context context, @Nullable AttributeSet attributeSet, int i3, @NotNull StorylyConfig storylyConfig) {
        super(context, attributeSet, i3);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(storylyConfig, "config");
        c cVar = new c(storylyConfig);
        this.f4847a = cVar;
        setLayoutManager(new LinearLayoutManager(context, 1, false));
        setAdapter(cVar);
        setNestedScrollingEnabled(false);
        a();
    }

    public final void a() {
        addItemDecoration(new b((int) (((double) m.b().height()) * 0.01875d)));
    }

    @Nullable
    public final Function3<STRCartItem, Integer, Function0<Unit>, Unit> getOnUpdateCart$storyly_release() {
        return this.f4848b;
    }

    public final void setOnUpdateCart$storyly_release(@Nullable Function3<? super STRCartItem, ? super Integer, ? super Function0<Unit>, Unit> function3) {
        this.f4848b = function3;
        this.f4847a.f4797c = function3;
    }

    public final void setup(@NotNull List<STRCartItem> list) {
        Intrinsics.checkNotNullParameter(list, FirebaseAnalytics.Param.ITEMS);
        c cVar = this.f4847a;
        List<T> list2 = CollectionsKt.toList(list);
        cVar.getClass();
        Intrinsics.checkNotNullParameter(list2, FirebaseAnalytics.Param.ITEMS);
        cVar.f4796b.setValue(cVar, c.f4794d[0], list2);
    }
}
