package com.appsamurai.storyly.storylypresenter.storylylayer.storylyProductList;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.appsamurai.storyly.config.StorylyConfig;
import com.appsamurai.storyly.data.managers.product.STRProductItem;
import com.appsamurai.storyly.util.m;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.List;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@SuppressLint({"ViewConstructor"})
public final class g extends RecyclerView {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final e f6089a;

    /* renamed from: b  reason: collision with root package name */
    public Function0<Unit> f6090b;

    /* renamed from: c  reason: collision with root package name */
    public Function0<Unit> f6091c;
    @NotNull

    /* renamed from: d  reason: collision with root package name */
    public Function1<? super List<STRProductItem>, Unit> f6092d = a.f6094a;

    /* renamed from: e  reason: collision with root package name */
    public int f6093e;

    public static final class a extends Lambda implements Function1<List<? extends STRProductItem>, Unit> {

        /* renamed from: a  reason: collision with root package name */
        public static final a f6094a = new a();

        public a() {
            super(1);
        }

        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            List list = (List) obj;
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public g(@NotNull Context context, @Nullable AttributeSet attributeSet, int i3, @NotNull StorylyConfig storylyConfig) {
        super(context, attributeSet, i3);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(storylyConfig, "config");
        e eVar = new e(storylyConfig);
        this.f6089a = eVar;
        setLayoutManager(new LinearLayoutManager(context, 0, false));
        setAdapter(eVar);
        a();
    }

    public final void a() {
        addItemDecoration(new d((int) (((double) m.b().width()) * 0.044d)));
    }

    public final int getComponentHeight$storyly_release() {
        return this.f6093e;
    }

    @NotNull
    public final Function1<List<STRProductItem>, Unit> getOnProductClick$storyly_release() {
        return this.f6092d;
    }

    @NotNull
    public final Function0<Unit> getOnUserInteractionEnded$storyly_release() {
        Function0<Unit> function0 = this.f6091c;
        if (function0 != null) {
            return function0;
        }
        Intrinsics.throwUninitializedPropertyAccessException("onUserInteractionEnded");
        return null;
    }

    @NotNull
    public final Function0<Unit> getOnUserInteractionStarted$storyly_release() {
        Function0<Unit> function0 = this.f6090b;
        if (function0 != null) {
            return function0;
        }
        Intrinsics.throwUninitializedPropertyAccessException("onUserInteractionStarted");
        return null;
    }

    public void onScrollStateChanged(int i3) {
        if (i3 == 1) {
            getOnUserInteractionStarted$storyly_release().invoke();
        } else if (i3 == 2) {
            getOnUserInteractionEnded$storyly_release().invoke();
        }
        super.onScrollStateChanged(i3);
    }

    public final void setComponentHeight$storyly_release(int i3) {
        this.f6089a.f6065e = i3;
        this.f6093e = i3;
    }

    public final void setOnProductClick$storyly_release(@NotNull Function1<? super List<STRProductItem>, Unit> function1) {
        Intrinsics.checkNotNullParameter(function1, "value");
        this.f6092d = function1;
        e eVar = this.f6089a;
        eVar.getClass();
        Intrinsics.checkNotNullParameter(function1, "<set-?>");
        eVar.f6062b = function1;
    }

    public final void setOnUserInteractionEnded$storyly_release(@NotNull Function0<Unit> function0) {
        Intrinsics.checkNotNullParameter(function0, "<set-?>");
        this.f6091c = function0;
    }

    public final void setOnUserInteractionStarted$storyly_release(@NotNull Function0<Unit> function0) {
        Intrinsics.checkNotNullParameter(function0, "<set-?>");
        this.f6090b = function0;
    }

    public final void setup(@NotNull List<? extends List<STRProductItem>> list) {
        Intrinsics.checkNotNullParameter(list, FirebaseAnalytics.Param.ITEMS);
        e eVar = this.f6089a;
        List<T> mutableList = CollectionsKt.toMutableList(list);
        eVar.getClass();
        Intrinsics.checkNotNullParameter(mutableList, FirebaseAnalytics.Param.ITEMS);
        eVar.f6063c.setValue(eVar, e.f6060f[0], mutableList);
    }

    public final void setupEntity(@NotNull a aVar) {
        Intrinsics.checkNotNullParameter(aVar, "productListItemEntity");
        e eVar = this.f6089a;
        eVar.getClass();
        Intrinsics.checkNotNullParameter(aVar, "<set-?>");
        eVar.f6064d = aVar;
    }
}
