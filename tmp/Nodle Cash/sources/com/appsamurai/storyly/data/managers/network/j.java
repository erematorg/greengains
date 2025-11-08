package com.appsamurai.storyly.data.managers.network;

import android.content.Context;
import com.amplitude.api.Constants;
import com.appsamurai.storyly.StorylyInit;
import com.appsamurai.storyly.config.StorylyProductConfig;
import com.appsamurai.storyly.data.e;
import com.appsamurai.storyly.data.managers.processing.a;
import com.google.common.net.HttpHeaders;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.json.JsonArray;
import kotlinx.serialization.json.JsonElementBuildersKt;
import kotlinx.serialization.json.JsonObjectBuilder;
import org.jetbrains.annotations.NotNull;

public final class j extends e {
    @NotNull

    /* renamed from: g  reason: collision with root package name */
    public final List<com.appsamurai.storyly.data.j> f3930g;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public j(@NotNull Context context, @NotNull StorylyInit storylyInit, @NotNull List<com.appsamurai.storyly.data.j> list) {
        super(context, storylyInit, 1, StringsKt__StringsJVMKt.replace$default(e.a().e(), "{token}", storylyInit.getStorylyId(), false, 4, (Object) null), com.appsamurai.storyly.data.managers.processing.e.ProductFallbackData, (a) null, 32);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(storylyInit, "storylyInit");
        Intrinsics.checkNotNullParameter(list, FirebaseAnalytics.Param.ITEMS);
        this.f3930g = list;
    }

    @NotNull
    public Map<String, Object> a() {
        List<com.appsamurai.storyly.data.j> list = this.f3930g;
        StorylyProductConfig product$storyly_release = this.f3916b.getConfig().getProduct$storyly_release();
        Intrinsics.checkNotNullParameter(list, FirebaseAnalytics.Param.ITEMS);
        Intrinsics.checkNotNullParameter(product$storyly_release, "config");
        JsonObjectBuilder jsonObjectBuilder = new JsonObjectBuilder();
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(list, 10));
        for (com.appsamurai.storyly.data.j jVar : list) {
            JsonObjectBuilder jsonObjectBuilder2 = new JsonObjectBuilder();
            JsonElementBuildersKt.put(jsonObjectBuilder2, TtmlNode.ATTR_ID, jVar.f3788a);
            JsonElementBuildersKt.put(jsonObjectBuilder2, "product_id", jVar.f3789b);
            JsonElementBuildersKt.put(jsonObjectBuilder2, "product_group_id", jVar.f3790c);
            arrayList.add(jsonObjectBuilder2.build());
        }
        jsonObjectBuilder.put("products", new JsonArray(arrayList));
        JsonElementBuildersKt.put(jsonObjectBuilder, Constants.AMP_TRACKING_OPTION_COUNTRY, product$storyly_release.getCountry$storyly_release());
        JsonElementBuildersKt.put(jsonObjectBuilder, "language", product$storyly_release.getLanguage$storyly_release());
        return jsonObjectBuilder.build();
    }

    @NotNull
    public Map<String, String> b() {
        return MapsKt.mapOf(TuplesKt.to(HttpHeaders.AUTHORIZATION, this.f3916b.getStorylyId()));
    }
}
