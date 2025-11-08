package com.appsamurai.storyly;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

public final class n extends Lambda implements Function1<String, Unit> {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ StorylyView f4639a;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public n(StorylyView storylyView) {
        super(1);
        this.f4639a = storylyView;
    }

    public Object invoke(Object obj) {
        String str = (String) obj;
        Intrinsics.checkNotNullParameter(str, "errorMessage");
        StorylyListener storylyListener = this.f4639a.getStorylyListener();
        if (storylyListener != null) {
            storylyListener.storylyLoadFailed(this.f4639a, str);
        }
        return Unit.INSTANCE;
    }
}
