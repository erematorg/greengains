package com.appsamurai.storyly.analytics;

import com.appsamurai.storyly.config.StorylyConfig;
import com.appsamurai.storyly.util.f;
import java.util.Locale;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlinx.serialization.json.JsonElementBuildersKt;
import kotlinx.serialization.json.JsonObjectBuilder;

public final class i extends Lambda implements Function1<JsonObjectBuilder, Unit> {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ StorylyConfig f3542a;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public i(StorylyConfig storylyConfig) {
        super(1);
        this.f3542a = storylyConfig;
    }

    public Object invoke(Object obj) {
        JsonObjectBuilder jsonObjectBuilder = (JsonObjectBuilder) obj;
        Intrinsics.checkNotNullParameter(jsonObjectBuilder, "$this$putJsonObject");
        String name = this.f3542a.getGroup$storyly_release().getSize$storyly_release().name();
        Locale locale = Locale.ENGLISH;
        Intrinsics.checkNotNullExpressionValue(locale, "ENGLISH");
        if (name != null) {
            String lowerCase = name.toLowerCase(locale);
            Intrinsics.checkNotNullExpressionValue(lowerCase, "(this as java.lang.String).toLowerCase(locale)");
            JsonElementBuildersKt.put(jsonObjectBuilder, "story_group_size", lowerCase);
            JsonElementBuildersKt.putJsonArray(jsonObjectBuilder, "story_group_icon_border_color_not_seen", new g(this.f3542a));
            JsonElementBuildersKt.putJsonArray(jsonObjectBuilder, "story_group_icon_border_color_seen", new h(this.f3542a));
            JsonElementBuildersKt.put(jsonObjectBuilder, "story_group_icon_background_color", f.a(this.f3542a.getGroup$storyly_release().getIconBackgroundColor$storyly_release()));
            JsonElementBuildersKt.put(jsonObjectBuilder, "story_group_pin_color", f.a(this.f3542a.getGroup$storyly_release().getPinIconColor$storyly_release()));
            JsonElementBuildersKt.put(jsonObjectBuilder, "corner_radius", (Number) Integer.valueOf(this.f3542a.getGroup$storyly_release().getIconCornerRadius$storyly_release()));
            JsonElementBuildersKt.put(jsonObjectBuilder, "height", (Number) Integer.valueOf(this.f3542a.getGroup$storyly_release().getIconHeight$storyly_release()));
            JsonElementBuildersKt.put(jsonObjectBuilder, "width", (Number) Integer.valueOf(this.f3542a.getGroup$storyly_release().getIconWidth$storyly_release()));
            return Unit.INSTANCE;
        }
        throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
    }
}
