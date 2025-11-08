package com.appsamurai.storyly.data.managers.network;

import android.content.Context;
import com.appsamurai.storyly.StorylyInit;
import com.appsamurai.storyly.data.e;
import com.appsamurai.storyly.data.managers.processing.a;
import com.google.common.net.HttpHeaders;
import java.util.Map;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.json.JsonElementBuildersKt;
import kotlinx.serialization.json.JsonObjectBuilder;
import org.jetbrains.annotations.NotNull;

public final class c extends e {
    @NotNull

    /* renamed from: g  reason: collision with root package name */
    public final String f3911g;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public c(@NotNull Context context, @NotNull StorylyInit storylyInit, @NotNull String str) {
        super(context, storylyInit, 1, e.a().a(), com.appsamurai.storyly.data.managers.processing.e.MomentsIDsData, (a) null, 32);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(storylyInit, "storylyInit");
        Intrinsics.checkNotNullParameter(str, "momentsToken");
        this.f3911g = str;
    }

    @NotNull
    public Map<String, Object> a() {
        Context context = this.f3915a;
        StorylyInit storylyInit = this.f3916b;
        JsonObjectBuilder jsonObjectBuilder = new JsonObjectBuilder();
        JsonElementBuildersKt.put(jsonObjectBuilder, "user_payload", this.f3916b.getConfig().getStorylyPayload());
        Unit unit = Unit.INSTANCE;
        return g.a(context, storylyInit, (String) null, (String) null, jsonObjectBuilder.build(), (Map) null, 44);
    }

    @NotNull
    public Map<String, String> b() {
        return MapsKt.mapOf(TuplesKt.to(HttpHeaders.AUTHORIZATION, this.f3911g));
    }

    @NotNull
    public String c() {
        return this.f3911g;
    }
}
