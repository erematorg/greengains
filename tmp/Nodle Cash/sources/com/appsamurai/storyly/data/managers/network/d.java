package com.appsamurai.storyly.data.managers.network;

import android.content.Context;
import com.appsamurai.storyly.StorylyInit;
import com.appsamurai.storyly.data.e;
import com.google.common.net.HttpHeaders;
import java.util.List;
import java.util.Map;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlinx.serialization.json.JsonArrayBuilder;
import kotlinx.serialization.json.JsonElementBuildersKt;
import kotlinx.serialization.json.JsonElementKt;
import kotlinx.serialization.json.JsonObjectBuilder;
import org.jetbrains.annotations.NotNull;

public final class d extends e {
    @NotNull

    /* renamed from: g  reason: collision with root package name */
    public final String f3912g;
    @NotNull

    /* renamed from: h  reason: collision with root package name */
    public final List<String> f3913h;

    public static final class a extends Lambda implements Function1<JsonArrayBuilder, Unit> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ d f3914a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public a(d dVar) {
            super(1);
            this.f3914a = dVar;
        }

        public Object invoke(Object obj) {
            JsonArrayBuilder jsonArrayBuilder = (JsonArrayBuilder) obj;
            Intrinsics.checkNotNullParameter(jsonArrayBuilder, "$this$putJsonArray");
            for (String JsonPrimitive : this.f3914a.f3913h) {
                jsonArrayBuilder.add(JsonElementKt.JsonPrimitive(JsonPrimitive));
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public d(@NotNull Context context, @NotNull StorylyInit storylyInit, @NotNull String str, @NotNull List<String> list) {
        super(context, storylyInit, 1, e.a().b(), com.appsamurai.storyly.data.managers.processing.e.PageData, (com.appsamurai.storyly.data.managers.processing.a) null, 32);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(storylyInit, "storylyInit");
        Intrinsics.checkNotNullParameter(str, "momentsToken");
        Intrinsics.checkNotNullParameter(list, "idList");
        this.f3912g = str;
        this.f3913h = list;
    }

    @NotNull
    public Map<String, Object> a() {
        Context context = this.f3915a;
        StorylyInit storylyInit = this.f3916b;
        JsonObjectBuilder jsonObjectBuilder = new JsonObjectBuilder();
        JsonElementBuildersKt.put(jsonObjectBuilder, "user_payload", this.f3916b.getConfig().getStorylyPayload());
        JsonElementBuildersKt.put(jsonObjectBuilder, "stories_filter", (Number) 2);
        JsonElementBuildersKt.putJsonArray(jsonObjectBuilder, "story_group_ids", new a(this));
        Unit unit = Unit.INSTANCE;
        return g.a(context, storylyInit, (String) null, (String) null, jsonObjectBuilder.build(), (Map) null, 44);
    }

    @NotNull
    public Map<String, String> b() {
        return MapsKt.mapOf(TuplesKt.to(HttpHeaders.AUTHORIZATION, this.f3912g));
    }

    @NotNull
    public String c() {
        return this.f3912g;
    }
}
