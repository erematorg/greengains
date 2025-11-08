package com.appsamurai.storyly;

import com.appsamurai.storyly.data.v;
import java.util.List;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

public final class j extends Lambda implements Function1<List<? extends v>, Unit> {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ StorylyView f4632a;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public j(StorylyView storylyView) {
        super(1);
        this.f4632a = storylyView;
    }

    public Object invoke(Object obj) {
        List list = (List) obj;
        Intrinsics.checkNotNullParameter(list, "groupItems");
        StorylyView.access$updateListGroups(this.f4632a, list);
        return Unit.INSTANCE;
    }
}
