package com.appsamurai.storyly.storylylist;

import android.view.View;
import com.appsamurai.storyly.R;
import com.appsamurai.storyly.data.v;
import com.appsamurai.storyly.storylylist.StorylyListRecyclerView;
import java.util.Iterator;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

public final class f extends Lambda implements Function1<View, String> {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ StorylyListRecyclerView.e f4709a;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public f(StorylyListRecyclerView.e eVar) {
        super(1);
        this.f4709a = eVar;
    }

    public Object invoke(Object obj) {
        View view = (View) obj;
        Intrinsics.checkNotNullParameter(view, "view");
        h hVar = view instanceof h ? (h) view : null;
        v storylyGroupItem = hVar == null ? null : hVar.getStorylyGroupItem();
        if (storylyGroupItem == null) {
            return "";
        }
        Iterator<v> it = this.f4709a.a().iterator();
        int i3 = 0;
        while (true) {
            if (!it.hasNext()) {
                i3 = -1;
                break;
            }
            v next = it.next();
            if (Intrinsics.areEqual((Object) storylyGroupItem.f4221a, (Object) next == null ? null : next.f4221a)) {
                break;
            }
            i3++;
        }
        StorylyListRecyclerView.e eVar = this.f4709a;
        String string = eVar.f4666b.getResources().getString(storylyGroupItem.f4237q ? R.string.st_desc_seen : R.string.st_desc_unseen);
        Intrinsics.checkNotNullExpressionValue(string, "resources.getString(if (… R.string.st_desc_unseen)");
        String string2 = eVar.f4666b.getResources().getString(storylyGroupItem.f4230j ? R.string.st_desc_story_group_pinned : R.string.st_desc_story_group_unpinned, new Object[]{Integer.valueOf(i3 + 1), Integer.valueOf(eVar.a().size()), storylyGroupItem.f4222b, string});
        Intrinsics.checkNotNullExpressionValue(string2, "resources.getString(\n   …xtSeenState\n            )");
        return string2;
    }
}
