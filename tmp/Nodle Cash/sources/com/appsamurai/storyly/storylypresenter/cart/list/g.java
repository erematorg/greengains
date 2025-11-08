package com.appsamurai.storyly.storylypresenter.cart.list;

import androidx.recyclerview.widget.DiffUtil;
import com.appsamurai.storyly.data.managers.product.STRCartItem;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;

public final class g extends DiffUtil.Callback {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ List<STRCartItem> f4849a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ List<STRCartItem> f4850b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ h f4851c;

    public g(List<STRCartItem> list, List<STRCartItem> list2, h hVar) {
        this.f4849a = list;
        this.f4850b = list2;
        this.f4851c = hVar;
    }

    public boolean areContentsTheSame(int i3, int i4) {
        return ((c) this.f4851c).a(this.f4849a.get(i3), this.f4850b.get(i4));
    }

    public boolean areItemsTheSame(int i3, int i4) {
        return Intrinsics.areEqual((Object) this.f4849a.get(i3), (Object) this.f4850b.get(i4));
    }

    public int getNewListSize() {
        return this.f4850b.size();
    }

    public int getOldListSize() {
        return this.f4849a.size();
    }
}
