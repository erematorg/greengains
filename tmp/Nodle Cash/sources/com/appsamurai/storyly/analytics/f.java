package com.appsamurai.storyly.analytics;

import com.appsamurai.storyly.StoryGroupType;
import com.appsamurai.storyly.config.StorylyConfig;
import com.appsamurai.storyly.data.v;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.text.StringsKt;
import kotlinx.serialization.json.JsonArray;
import kotlinx.serialization.json.JsonArrayBuilder;
import kotlinx.serialization.json.JsonElementBuildersKt;
import kotlinx.serialization.json.JsonObject;
import kotlinx.serialization.json.JsonObjectBuilder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class f {

    public static final class a extends Lambda implements Function1<JsonArrayBuilder, Unit> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ v f3537a;

        /* renamed from: b  reason: collision with root package name */
        public final /* synthetic */ List<v> f3538b;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public a(v vVar, List<v> list) {
            super(1);
            this.f3537a = vVar;
            this.f3538b = list;
        }

        public Object invoke(Object obj) {
            Intrinsics.checkNotNullParameter((JsonArrayBuilder) obj, "$this$putJsonArray");
            StoryGroupType storyGroupType = this.f3537a.f4228h;
            List<v> list = this.f3538b;
            List<T> filterNotNull = list == null ? null : CollectionsKt.filterNotNull(list);
            if (filterNotNull == null) {
                filterNotNull = CollectionsKt.emptyList();
            }
            f.a(storyGroupType, filterNotNull);
            return Unit.INSTANCE;
        }
    }

    public static final class b extends Lambda implements Function1<JsonObjectBuilder, Unit> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ StorylyConfig f3539a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public b(StorylyConfig storylyConfig) {
            super(1);
            this.f3539a = storylyConfig;
        }

        public Object invoke(Object obj) {
            JsonObjectBuilder jsonObjectBuilder = (JsonObjectBuilder) obj;
            Intrinsics.checkNotNullParameter(jsonObjectBuilder, "$this$putJsonObject");
            JsonElementBuildersKt.putJsonObject(jsonObjectBuilder, "story_group_icon_styling", new i(this.f3539a));
            JsonElementBuildersKt.putJsonObject(jsonObjectBuilder, "story_group_text_styling", new j(this.f3539a));
            JsonElementBuildersKt.putJsonObject(jsonObjectBuilder, "story_group_list_styling", new k(this.f3539a));
            return Unit.INSTANCE;
        }
    }

    @NotNull
    public static final JsonArray a(@NotNull StoryGroupType storyGroupType, @NotNull List<v> list) {
        Intrinsics.checkNotNullParameter(storyGroupType, "storyGroupType");
        Intrinsics.checkNotNullParameter(list, "groupItems");
        if (storyGroupType == StoryGroupType.MomentsDefault) {
            JsonArrayBuilder jsonArrayBuilder = new JsonArrayBuilder();
            ArrayList arrayList = new ArrayList();
            for (T next : list) {
                if (((v) next).f4228h == StoryGroupType.MomentsDefault) {
                    arrayList.add(next);
                }
            }
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                JsonElementBuildersKt.add(jsonArrayBuilder, ((v) it.next()).f4221a);
            }
            return jsonArrayBuilder.build();
        }
        JsonArrayBuilder jsonArrayBuilder2 = new JsonArrayBuilder();
        ArrayList arrayList2 = new ArrayList();
        for (T next2 : list) {
            if (((v) next2).f4228h != StoryGroupType.MomentsDefault) {
                arrayList2.add(next2);
            }
        }
        Iterator it2 = arrayList2.iterator();
        while (it2.hasNext()) {
            Integer intOrNull = StringsKt.toIntOrNull(((v) it2.next()).f4221a);
            if (intOrNull != null) {
                JsonElementBuildersKt.add(jsonArrayBuilder2, (Number) intOrNull);
            }
        }
        return jsonArrayBuilder2.build();
    }

    @NotNull
    public static final JsonObject a(@Nullable List<v> list, @NotNull v vVar, @NotNull StorylyConfig storylyConfig) {
        Intrinsics.checkNotNullParameter(vVar, "storylyGroupItem");
        Intrinsics.checkNotNullParameter(storylyConfig, "config");
        JsonObjectBuilder jsonObjectBuilder = new JsonObjectBuilder();
        JsonElementBuildersKt.put(jsonObjectBuilder, "story_group_pinned", Boolean.valueOf(vVar.f4230j));
        JsonElementBuildersKt.put(jsonObjectBuilder, "story_group_seen", Boolean.valueOf(vVar.f4237q));
        JsonElementBuildersKt.putJsonArray(jsonObjectBuilder, "sg_ids", new a(vVar, list));
        JsonElementBuildersKt.putJsonObject(jsonObjectBuilder, "story_group_theme", new b(storylyConfig));
        return jsonObjectBuilder.build();
    }
}
