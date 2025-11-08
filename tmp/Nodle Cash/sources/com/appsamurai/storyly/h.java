package com.appsamurai.storyly;

import com.appsamurai.storyly.data.managers.conditional.b;
import com.appsamurai.storyly.data.z;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;

public final /* synthetic */ class h extends FunctionReferenceImpl implements Function1<z, Boolean> {
    public h(Object obj) {
        super(1, obj, b.class, "updateStoryCondition", "updateStoryCondition(Lcom/appsamurai/storyly/data/StorylyItem;)Z", 0);
    }

    public Object invoke(Object obj) {
        z zVar = (z) obj;
        Intrinsics.checkNotNullParameter(zVar, "p0");
        return Boolean.valueOf(((b) this.receiver).a(zVar));
    }
}
