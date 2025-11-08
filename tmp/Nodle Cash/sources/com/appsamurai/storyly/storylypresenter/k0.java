package com.appsamurai.storyly.storylypresenter;

import com.appsamurai.storyly.Story;
import com.appsamurai.storyly.StoryGroup;
import com.appsamurai.storyly.data.v;
import com.appsamurai.storyly.data.z;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Lambda;

public final class k0 extends Lambda implements Function0<Unit> {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ o f5027a;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public k0(o oVar) {
        super(0);
        this.f5027a = oVar;
    }

    public Object invoke() {
        Function2<StoryGroup, Story, Unit> onStorylyHeaderClicked$storyly_release = this.f5027a.getOnStorylyHeaderClicked$storyly_release();
        v storylyGroupItem$storyly_release = this.f5027a.getStorylyGroupItem$storyly_release();
        Story story = null;
        StoryGroup d2 = storylyGroupItem$storyly_release == null ? null : storylyGroupItem$storyly_release.d();
        z h3 = this.f5027a.getStorylyItem();
        if (h3 != null) {
            story = h3.a();
        }
        onStorylyHeaderClicked$storyly_release.invoke(d2, story);
        return Unit.INSTANCE;
    }
}
