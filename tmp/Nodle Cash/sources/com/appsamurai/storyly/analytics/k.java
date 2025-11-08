package com.appsamurai.storyly.analytics;

import com.appsamurai.storyly.config.StorylyConfig;
import java.util.Locale;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlinx.serialization.json.JsonElementBuildersKt;
import kotlinx.serialization.json.JsonObjectBuilder;

public final class k extends Lambda implements Function1<JsonObjectBuilder, Unit> {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ StorylyConfig f3544a;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public k(StorylyConfig storylyConfig) {
        super(1);
        this.f3544a = storylyConfig;
    }

    public Object invoke(Object obj) {
        JsonObjectBuilder jsonObjectBuilder = (JsonObjectBuilder) obj;
        Intrinsics.checkNotNullParameter(jsonObjectBuilder, "$this$putJsonObject");
        String name = this.f3544a.getBar$storyly_release().getOrientation$storyly_release().name();
        Locale locale = Locale.ENGLISH;
        Intrinsics.checkNotNullExpressionValue(locale, "ENGLISH");
        if (name != null) {
            String lowerCase = name.toLowerCase(locale);
            Intrinsics.checkNotNullExpressionValue(lowerCase, "(this as java.lang.String).toLowerCase(locale)");
            JsonElementBuildersKt.put(jsonObjectBuilder, "orientation", lowerCase);
            JsonElementBuildersKt.put(jsonObjectBuilder, "sections", (Number) Integer.valueOf(this.f3544a.getBar$storyly_release().getSection$storyly_release()));
            JsonElementBuildersKt.put(jsonObjectBuilder, "horizontal_edge_padding", (Number) Integer.valueOf(this.f3544a.getBar$storyly_release().getHorizontalEdgePadding$storyly_release()));
            JsonElementBuildersKt.put(jsonObjectBuilder, "vertical_edge_padding", (Number) Integer.valueOf(this.f3544a.getBar$storyly_release().getVerticalEdgePadding$storyly_release()));
            JsonElementBuildersKt.put(jsonObjectBuilder, "horizontal_padding_between_items", (Number) Integer.valueOf(this.f3544a.getBar$storyly_release().getHorizontalPaddingBetweenItems$storyly_release(this.f3544a.getGroup$storyly_release().getSize$storyly_release())));
            JsonElementBuildersKt.put(jsonObjectBuilder, "vertical_padding_between_items", (Number) Integer.valueOf(this.f3544a.getBar$storyly_release().getVerticalPaddingBetweenItems$storyly_release(this.f3544a.getGroup$storyly_release().getSize$storyly_release())));
            return Unit.INSTANCE;
        }
        throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
    }
}
