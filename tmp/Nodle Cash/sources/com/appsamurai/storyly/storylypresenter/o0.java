package com.appsamurai.storyly.storylypresenter;

import com.appsamurai.storyly.storylypresenter.share.c;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;

public final /* synthetic */ class o0 extends FunctionReferenceImpl implements Function1<c, Unit> {
    public o0(Object obj) {
        super(1, obj, o.class, "shareStory", "shareStory(Lcom/appsamurai/storyly/storylypresenter/share/StoryShareType;)V", 0);
    }

    public Object invoke(Object obj) {
        c cVar = (c) obj;
        Intrinsics.checkNotNullParameter(cVar, "p0");
        o.a((o) this.receiver, cVar);
        return Unit.INSTANCE;
    }
}
