package com.appsamurai.storyly;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

public final class e extends Lambda implements Function1<Story, Unit> {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ StorylyView f4386a;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public e(StorylyView storylyView) {
        super(1);
        this.f4386a = storylyView;
    }

    public Object invoke(Object obj) {
        Story story = (Story) obj;
        Intrinsics.checkNotNullParameter(story, "story");
        StorylyListener storylyListener = this.f4386a.getStorylyListener();
        if (storylyListener != null) {
            storylyListener.storylyActionClicked(this.f4386a, story);
        }
        return Unit.INSTANCE;
    }
}
