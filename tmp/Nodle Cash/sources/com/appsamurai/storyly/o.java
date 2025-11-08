package com.appsamurai.storyly;

import com.appsamurai.storyly.analytics.StorylyEvent;
import kotlin.Unit;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;

public final /* synthetic */ class o extends FunctionReferenceImpl implements Function4<StorylyEvent, StoryGroup, Story, StoryComponent, Unit> {
    public o(Object obj) {
        super(4, obj, StorylyView.class, "onStoryEvent", "onStoryEvent(Lcom/appsamurai/storyly/analytics/StorylyEvent;Lcom/appsamurai/storyly/StoryGroup;Lcom/appsamurai/storyly/Story;Lcom/appsamurai/storyly/StoryComponent;)V", 0);
    }

    public Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
        StorylyEvent storylyEvent = (StorylyEvent) obj;
        Intrinsics.checkNotNullParameter(storylyEvent, "p0");
        StorylyView.access$onStoryEvent((StorylyView) this.receiver, storylyEvent, (StoryGroup) obj2, (Story) obj3, (StoryComponent) obj4);
        return Unit.INSTANCE;
    }
}
