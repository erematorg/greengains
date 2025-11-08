package com.appsamurai.storyly.storylypresenter;

import android.content.Context;
import androidx.recyclerview.widget.LinearLayoutManager;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001¨\u0006\u0002"}, d2 = {"com/appsamurai/storyly/storylypresenter/StorylyGroupRecyclerView$linearLayoutManager$2$1", "Landroidx/recyclerview/widget/LinearLayoutManager;", "storyly_release"}, k = 1, mv = {1, 5, 1})
public final class StorylyGroupRecyclerView$linearLayoutManager$2$1 extends LinearLayoutManager {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ d f4718a;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public StorylyGroupRecyclerView$linearLayoutManager$2$1(d dVar, Context context) {
        super(context, 0, false);
        this.f4718a = dVar;
    }

    public boolean canScrollHorizontally() {
        return this.f4718a.f4973r;
    }
}
