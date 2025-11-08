package com.appsamurai.storyly.storylypresenter.product.variant;

import android.content.Context;
import android.util.AttributeSet;
import androidx.recyclerview.widget.ConcatAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.appsamurai.storyly.config.StorylyConfig;
import com.appsamurai.storyly.data.managers.product.STRProductVariant;
import com.appsamurai.storyly.util.m;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.List;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class d extends RecyclerView {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final b f5279a;
    @NotNull

    /* renamed from: b  reason: collision with root package name */
    public final c f5280b;

    /* renamed from: c  reason: collision with root package name */
    public boolean f5281c = true;
    @Nullable

    /* renamed from: d  reason: collision with root package name */
    public Function1<? super STRProductVariant, Unit> f5282d;

    /* renamed from: e  reason: collision with root package name */
    public int f5283e;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public d(@NotNull Context context, @Nullable AttributeSet attributeSet, int i3, @NotNull StorylyConfig storylyConfig) {
        super(context, attributeSet, i3);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(storylyConfig, "config");
        b bVar = new b(storylyConfig);
        this.f5279a = bVar;
        c cVar = new c(storylyConfig);
        this.f5280b = cVar;
        setLayoutManager(new LinearLayoutManager(context, 0, false));
        setAdapter(new ConcatAdapter((RecyclerView.Adapter<? extends RecyclerView.ViewHolder>[]) new RecyclerView.Adapter[]{cVar, bVar}));
        setNestedScrollingEnabled(false);
        a();
    }

    public final void a() {
        addItemDecoration(new a((int) (((double) m.b().width()) * 0.0335d)));
    }

    public final boolean getClickEnabled$storyly_release() {
        return this.f5281c;
    }

    public final int getComponentHeight$storyly_release() {
        return this.f5283e;
    }

    @NotNull
    public final c getHeaderAdapter$storyly_release() {
        return this.f5280b;
    }

    @Nullable
    public final Function1<STRProductVariant, Unit> getOnVariantSelection$storyly_release() {
        return this.f5282d;
    }

    public final void setClickEnabled$storyly_release(boolean z2) {
        this.f5281c = z2;
        this.f5279a.f5266d = z2;
    }

    public final void setComponentHeight$storyly_release(int i3) {
        this.f5279a.f5265c = i3;
        this.f5280b.f5277c = i3;
        this.f5283e = i3;
    }

    public final void setOnVariantSelection$storyly_release(@Nullable Function1<? super STRProductVariant, Unit> function1) {
        this.f5282d = function1;
        this.f5279a.f5267e = function1;
    }

    public final void setSelectedItem(@Nullable STRProductVariant sTRProductVariant) {
        b bVar = this.f5279a;
        if (sTRProductVariant == null) {
            bVar.a(-1);
        }
        int indexOf = CollectionsKt.indexOf(bVar.a(), sTRProductVariant);
        if (indexOf != -1) {
            bVar.a(indexOf);
        }
    }

    public final void setup(@NotNull List<STRProductVariant> list) {
        String str;
        Intrinsics.checkNotNullParameter(list, FirebaseAnalytics.Param.ITEMS);
        b bVar = this.f5279a;
        List<T> mutableList = CollectionsKt.toMutableList(list);
        bVar.getClass();
        Intrinsics.checkNotNullParameter(mutableList, FirebaseAnalytics.Param.ITEMS);
        bVar.f5264b.setValue(bVar, b.f5262g[0], mutableList);
        c cVar = this.f5280b;
        STRProductVariant sTRProductVariant = (STRProductVariant) CollectionsKt.firstOrNull(list);
        if (sTRProductVariant == null || (str = sTRProductVariant.getName()) == null) {
            str = "";
        }
        cVar.getClass();
        Intrinsics.checkNotNullParameter(str, "headerText");
        cVar.f5276b = str;
        cVar.notifyDataSetChanged();
    }
}
