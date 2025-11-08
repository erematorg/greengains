package com.appsamurai.storyly.storylypresenter.storylylayer.storylyProductList;

import androidx.recyclerview.widget.DiffUtil;
import com.appsamurai.storyly.data.managers.product.STRProductItem;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;

public final class b extends DiffUtil.Callback {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ List<List<STRProductItem>> f6056a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ List<List<STRProductItem>> f6057b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ c f6058c;

    public b(List<? extends List<STRProductItem>> list, List<? extends List<STRProductItem>> list2, c cVar) {
        this.f6056a = list;
        this.f6057b = list2;
        this.f6058c = cVar;
    }

    public boolean areContentsTheSame(int i3, int i4) {
        return ((e) this.f6058c).a(this.f6056a.get(i3), this.f6057b.get(i4));
    }

    public boolean areItemsTheSame(int i3, int i4) {
        return Intrinsics.areEqual((Object) this.f6056a.get(i3), (Object) this.f6057b.get(i4));
    }

    public int getNewListSize() {
        return this.f6057b.size();
    }

    public int getOldListSize() {
        return this.f6056a.size();
    }
}
