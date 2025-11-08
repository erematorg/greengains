package com.appsamurai.storyly.analytics;

import com.appsamurai.storyly.config.StorylyConfig;
import com.appsamurai.storyly.util.f;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlinx.serialization.json.JsonArrayBuilder;
import kotlinx.serialization.json.JsonElementBuildersKt;

public final class g extends Lambda implements Function1<JsonArrayBuilder, Unit> {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ StorylyConfig f3540a;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public g(StorylyConfig storylyConfig) {
        super(1);
        this.f3540a = storylyConfig;
    }

    public Object invoke(Object obj) {
        JsonArrayBuilder jsonArrayBuilder = (JsonArrayBuilder) obj;
        Intrinsics.checkNotNullParameter(jsonArrayBuilder, "$this$putJsonArray");
        for (Number intValue : this.f3540a.getGroup$storyly_release().getIconBorderColorNotSeen$storyly_release()) {
            JsonElementBuildersKt.add(jsonArrayBuilder, f.a(intValue.intValue()));
        }
        return Unit.INSTANCE;
    }
}
