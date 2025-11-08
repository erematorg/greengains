package com.appsamurai.storyly;

import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Lambda;

public final class g extends Lambda implements Function2<StoryGroup, Story, Unit> {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ StorylyView f4629a;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public g(StorylyView storylyView) {
        super(2);
        this.f4629a = storylyView;
    }

    public Object invoke(Object obj, Object obj2) {
        StoryGroup storyGroup = (StoryGroup) obj;
        Story story = (Story) obj2;
        StorylyMomentsListener storylyMomentsListener = this.f4629a.getStorylyMomentsListener();
        if (storylyMomentsListener != null) {
            storylyMomentsListener.storyHeaderClicked(this.f4629a, storyGroup, story);
        }
        return Unit.INSTANCE;
    }
}
