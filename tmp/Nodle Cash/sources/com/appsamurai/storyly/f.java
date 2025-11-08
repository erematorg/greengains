package com.appsamurai.storyly;

import com.appsamurai.storyly.data.managers.conditional.a;
import com.appsamurai.storyly.data.managers.processing.e;
import com.appsamurai.storyly.data.managers.processing.k;
import com.appsamurai.storyly.data.v;
import com.appsamurai.storyly.storylypresenter.b;
import java.util.List;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

public final class f extends Lambda implements Function3<StoryGroup, Story, StoryComponent, Unit> {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ StorylyView f4628a;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public f(StorylyView storylyView) {
        super(3);
        this.f4628a = storylyView;
    }

    public Object invoke(Object obj, Object obj2, Object obj3) {
        StoryGroup storyGroup = (StoryGroup) obj;
        Story story = (Story) obj2;
        StoryComponent storyComponent = (StoryComponent) obj3;
        Intrinsics.checkNotNullParameter(storyGroup, "storyGroup");
        Intrinsics.checkNotNullParameter(story, "story");
        Intrinsics.checkNotNullParameter(storyComponent, "storyComponent");
        StorylyListener storylyListener = this.f4628a.getStorylyListener();
        if (storylyListener != null) {
            storylyListener.storylyUserInteracted(this.f4628a, storyGroup, story, storyComponent);
        }
        com.appsamurai.storyly.data.managers.processing.f access$getStorylyDataManager = this.f4628a.getStorylyDataManager();
        b access$getStorylyDialog$p = this.f4628a.f3426o;
        List<v> a2 = access$getStorylyDialog$p == null ? null : access$getStorylyDialog$p.a();
        access$getStorylyDataManager.getClass();
        Intrinsics.checkNotNullParameter(storyComponent, "storyComponent");
        com.appsamurai.storyly.data.managers.conditional.b b3 = access$getStorylyDataManager.b();
        b3.getClass();
        Intrinsics.checkNotNullParameter(storyComponent, "storyComponent");
        if (b3.f3901d.containsKey(storyComponent.getId())) {
            a aVar = b3.f3901d.get(storyComponent.getId());
            if (aVar != null) {
                aVar.f3896a = storyComponent instanceof StoryPollComponent ? Integer.valueOf(((StoryPollComponent) storyComponent).getSelectedOptionIndex()) : storyComponent instanceof StoryQuizComponent ? Integer.valueOf(((StoryQuizComponent) storyComponent).getSelectedOptionIndex()) : storyComponent instanceof StoryImageQuizComponent ? Integer.valueOf(((StoryImageQuizComponent) storyComponent).getSelectedOptionIndex()) : null;
            }
            com.appsamurai.storyly.data.managers.conditional.b b4 = access$getStorylyDataManager.b();
            if (a2 == null) {
                a2 = CollectionsKt.emptyList();
            }
            b4.a(a2, storyComponent.getId());
            com.appsamurai.storyly.data.managers.processing.f.a(access$getStorylyDataManager, e.ConditionalDataUpdate, (Function0) new k(access$getStorylyDataManager, storyComponent), (Function0) null, 4);
        }
        return Unit.INSTANCE;
    }
}
