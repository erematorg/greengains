package com.appsamurai.storyly.storylypresenter.product.imagelist;

import android.content.Context;
import android.util.AttributeSet;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.appsamurai.storyly.util.m;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.List;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class c extends RecyclerView {

    /* renamed from: a  reason: collision with root package name */
    public Function0<Unit> f5145a;
    @NotNull

    /* renamed from: b  reason: collision with root package name */
    public final b f5146b;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public c(@NotNull Context context, @Nullable AttributeSet attributeSet, int i3) {
        super(context, attributeSet, i3);
        Intrinsics.checkNotNullParameter(context, "context");
        b bVar = new b();
        this.f5146b = bVar;
        setLayoutManager(new LinearLayoutManager(context, 0, false));
        setAdapter(bVar);
        setNestedScrollingEnabled(false);
        a();
    }

    public final void a() {
        addItemDecoration(new a((int) (((double) m.b().width()) * 0.0335d)));
    }

    @NotNull
    public final Function0<Unit> getOnUserInteractionStarted$storyly_release() {
        Function0<Unit> function0 = this.f5145a;
        if (function0 != null) {
            return function0;
        }
        Intrinsics.throwUninitializedPropertyAccessException("onUserInteractionStarted");
        return null;
    }

    public final void setOnUserInteractionStarted$storyly_release(@NotNull Function0<Unit> function0) {
        Intrinsics.checkNotNullParameter(function0, "<set-?>");
        this.f5145a = function0;
    }

    public final void setup(@NotNull List<String> list) {
        Intrinsics.checkNotNullParameter(list, FirebaseAnalytics.Param.ITEMS);
        b bVar = this.f5146b;
        List<T> list2 = CollectionsKt.toList(list);
        bVar.getClass();
        Intrinsics.checkNotNullParameter(list2, FirebaseAnalytics.Param.ITEMS);
        bVar.f5141a.setValue(bVar, b.f5140b[0], list2);
    }
}
