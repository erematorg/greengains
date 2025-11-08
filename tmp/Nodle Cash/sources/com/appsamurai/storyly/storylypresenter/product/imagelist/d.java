package com.appsamurai.storyly.storylypresenter.product.imagelist;

import androidx.recyclerview.widget.DiffUtil;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;

public final class d extends DiffUtil.Callback {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ List<String> f5147a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ List<String> f5148b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ e f5149c;

    public d(List<String> list, List<String> list2, e eVar) {
        this.f5147a = list;
        this.f5148b = list2;
        this.f5149c = eVar;
    }

    public boolean areContentsTheSame(int i3, int i4) {
        b bVar = (b) this.f5149c;
        bVar.getClass();
        Intrinsics.checkNotNullParameter(bVar, "this");
        return Intrinsics.areEqual((Object) this.f5147a.get(i3), (Object) this.f5148b.get(i4));
    }

    public boolean areItemsTheSame(int i3, int i4) {
        return Intrinsics.areEqual((Object) this.f5147a.get(i3), (Object) this.f5148b.get(i4));
    }

    public int getNewListSize() {
        return this.f5148b.size();
    }

    public int getOldListSize() {
        return this.f5147a.size();
    }
}
