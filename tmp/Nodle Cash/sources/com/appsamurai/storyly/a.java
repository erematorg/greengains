package com.appsamurai.storyly;

import com.appsamurai.storyly.ad.StorylyAdViewListener;
import com.appsamurai.storyly.ad.StorylyAdViewProvider;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

public final class a extends Lambda implements Function1<StorylyAdViewListener, Unit> {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ StorylyView f3457a;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public a(StorylyView storylyView) {
        super(1);
        this.f3457a = storylyView;
    }

    public Object invoke(Object obj) {
        StorylyAdViewListener storylyAdViewListener = (StorylyAdViewListener) obj;
        Intrinsics.checkNotNullParameter(storylyAdViewListener, "storylyAdListener");
        StorylyAdViewProvider storylyAdViewProvider = this.f3457a.getStorylyAdViewProvider();
        if (storylyAdViewProvider != null) {
            storylyAdViewProvider.onRequest(storylyAdViewListener);
        }
        return Unit.INSTANCE;
    }
}
