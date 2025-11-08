package com.appsamurai.storyly.storylypresenter.product.variant;

import androidx.recyclerview.widget.DiffUtil;
import com.appsamurai.storyly.data.managers.product.STRProductVariant;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;

public final class f extends DiffUtil.Callback {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ List<STRProductVariant> f5287a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ List<STRProductVariant> f5288b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ g f5289c;

    public f(List<STRProductVariant> list, List<STRProductVariant> list2, g gVar) {
        this.f5287a = list;
        this.f5288b = list2;
        this.f5289c = gVar;
    }

    public boolean areContentsTheSame(int i3, int i4) {
        return ((b) this.f5289c).a(this.f5287a.get(i3), this.f5288b.get(i4));
    }

    public boolean areItemsTheSame(int i3, int i4) {
        return Intrinsics.areEqual((Object) this.f5287a.get(i3), (Object) this.f5288b.get(i4));
    }

    public int getNewListSize() {
        return this.f5288b.size();
    }

    public int getOldListSize() {
        return this.f5287a.size();
    }
}
