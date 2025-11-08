package com.appsamurai.storyly.storylylist;

import androidx.recyclerview.widget.DiffUtil;
import com.appsamurai.storyly.storylylist.StorylyListRecyclerView;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;

public final class e extends DiffUtil.Callback {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ List<MomentsItem> f4706a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ List<MomentsItem> f4707b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ StorylyListRecyclerView.c f4708c;

    public e(List<MomentsItem> list, List<MomentsItem> list2, StorylyListRecyclerView.c cVar) {
        this.f4706a = list;
        this.f4707b = list2;
        this.f4708c = cVar;
    }

    public boolean areContentsTheSame(int i3, int i4) {
        return ((StorylyListRecyclerView.b) this.f4708c).a(this.f4706a.get(i3), this.f4707b.get(i4));
    }

    public boolean areItemsTheSame(int i3, int i4) {
        MomentsItem momentsItem = this.f4706a.get(i3);
        String str = null;
        String tag$storyly_release = momentsItem == null ? null : momentsItem.getTag$storyly_release();
        MomentsItem momentsItem2 = this.f4707b.get(i4);
        if (momentsItem2 != null) {
            str = momentsItem2.getTag$storyly_release();
        }
        return Intrinsics.areEqual((Object) tag$storyly_release, (Object) str);
    }

    public int getNewListSize() {
        return this.f4707b.size();
    }

    public int getOldListSize() {
        return this.f4706a.size();
    }
}
