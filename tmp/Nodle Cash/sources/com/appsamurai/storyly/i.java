package com.appsamurai.storyly;

import com.appsamurai.storyly.data.managers.processing.e;
import com.appsamurai.storyly.storylypresenter.b;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

public final class i extends Lambda implements Function1<Function0<? extends Unit>, Unit> {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ b f4630a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ StorylyView f4631b;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public i(b bVar, StorylyView storylyView) {
        super(1);
        this.f4630a = bVar;
        this.f4631b = storylyView;
    }

    public Object invoke(Object obj) {
        Function0 function0 = (Function0) obj;
        Intrinsics.checkNotNullParameter(function0, "onMomentsPageLoadEnd");
        if (Intrinsics.areEqual((Object) this.f4630a.f4751f, (Object) this.f4631b.getStorylyDataManager().d().f3942a)) {
            this.f4631b.a(e.PageData, (Function0<Unit>) null, function0);
        } else {
            function0.invoke();
        }
        return Unit.INSTANCE;
    }
}
