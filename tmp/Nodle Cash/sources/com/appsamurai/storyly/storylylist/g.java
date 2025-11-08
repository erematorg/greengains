package com.appsamurai.storyly.storylylist;

import androidx.recyclerview.widget.DiffUtil;
import com.appsamurai.storyly.data.v;
import com.appsamurai.storyly.storylylist.StorylyListRecyclerView;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;

public final class g extends DiffUtil.Callback {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ List<v> f4710a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ List<v> f4711b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ StorylyListRecyclerView.f f4712c;

    public g(List<v> list, List<v> list2, StorylyListRecyclerView.f fVar) {
        this.f4710a = list;
        this.f4711b = list2;
        this.f4712c = fVar;
    }

    public boolean areContentsTheSame(int i3, int i4) {
        return ((StorylyListRecyclerView.e) this.f4712c).a(this.f4710a.get(i3), this.f4711b.get(i4));
    }

    public boolean areItemsTheSame(int i3, int i4) {
        v vVar = this.f4710a.get(i3);
        String str = null;
        String str2 = vVar == null ? null : vVar.f4221a;
        v vVar2 = this.f4711b.get(i4);
        if (vVar2 != null) {
            str = vVar2.f4221a;
        }
        return Intrinsics.areEqual((Object) str2, (Object) str);
    }

    public int getNewListSize() {
        return this.f4711b.size();
    }

    public int getOldListSize() {
        return this.f4710a.size();
    }
}
