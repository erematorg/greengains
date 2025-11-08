package com.appsamurai.storyly.analytics;

import com.appsamurai.storyly.config.StorylyConfig;
import com.appsamurai.storyly.util.f;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlinx.serialization.json.JsonElementBuildersKt;
import kotlinx.serialization.json.JsonObjectBuilder;

public final class j extends Lambda implements Function1<JsonObjectBuilder, Unit> {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ StorylyConfig f3543a;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public j(StorylyConfig storylyConfig) {
        super(1);
        this.f3543a = storylyConfig;
    }

    public Object invoke(Object obj) {
        JsonObjectBuilder jsonObjectBuilder = (JsonObjectBuilder) obj;
        Intrinsics.checkNotNullParameter(jsonObjectBuilder, "$this$putJsonObject");
        JsonElementBuildersKt.put(jsonObjectBuilder, "story_group_text_color_seen", f.a(this.f3543a.getGroup$storyly_release().getTitleSeenColor$storyly_release()));
        JsonElementBuildersKt.put(jsonObjectBuilder, "story_group_text_color_not_seen", f.a(this.f3543a.getGroup$storyly_release().getTitleNotSeenColor$storyly_release()));
        JsonElementBuildersKt.put(jsonObjectBuilder, "is_visible", Boolean.valueOf(this.f3543a.getGroup$storyly_release().isTitleVisible$storyly_release()));
        return Unit.INSTANCE;
    }
}
