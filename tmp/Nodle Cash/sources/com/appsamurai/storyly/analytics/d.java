package com.appsamurai.storyly.analytics;

import com.appsamurai.storyly.StoryComponent;
import com.appsamurai.storyly.StoryGroupType;
import com.appsamurai.storyly.data.b0;
import com.appsamurai.storyly.data.managers.product.STRCart;
import com.appsamurai.storyly.data.managers.product.STRCartItem;
import com.appsamurai.storyly.data.v;
import com.appsamurai.storyly.data.z;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlinx.serialization.json.JsonArrayBuilder;
import kotlinx.serialization.json.JsonElement;
import kotlinx.serialization.json.JsonElementBuildersKt;
import kotlinx.serialization.json.JsonObjectBuilder;
import org.jetbrains.annotations.NotNull;

public final class d {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final e f3511a;
    @NotNull

    /* renamed from: b  reason: collision with root package name */
    public Set<String> f3512b = new LinkedHashSet();

    public static final class a extends Lambda implements Function1<JsonArrayBuilder, Unit> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ List<v> f3513a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public a(List<v> list) {
            super(1);
            this.f3513a = list;
        }

        public Object invoke(Object obj) {
            JsonArrayBuilder jsonArrayBuilder = (JsonArrayBuilder) obj;
            Intrinsics.checkNotNullParameter(jsonArrayBuilder, "$this$putJsonArray");
            Iterator<JsonElement> it = f.a(StoryGroupType.Default, this.f3513a).iterator();
            while (it.hasNext()) {
                jsonArrayBuilder.add(it.next());
            }
            return Unit.INSTANCE;
        }
    }

    public d(@NotNull e eVar) {
        Intrinsics.checkNotNullParameter(eVar, "storylyTracker");
        this.f3511a = eVar;
    }

    public final void a(@NotNull List<v> list) {
        Intrinsics.checkNotNullParameter(list, FirebaseAnalytics.Param.ITEMS);
        ArrayList arrayList = new ArrayList();
        for (T next : list) {
            if (!this.f3512b.contains(((v) next).f4221a)) {
                arrayList.add(next);
            }
        }
        if (!arrayList.isEmpty()) {
            e eVar = this.f3511a;
            a aVar = a.BarViewed;
            JsonObjectBuilder jsonObjectBuilder = new JsonObjectBuilder();
            JsonElementBuildersKt.putJsonArray(jsonObjectBuilder, "sg_ids", new a(arrayList));
            Unit unit = Unit.INSTANCE;
            e.a(eVar, aVar, (v) null, (z) null, (b0) null, (StoryComponent) null, jsonObjectBuilder.build(), (Function1) null, (String) null, (Function1) null, (Function1) null, (STRCart) null, (STRCartItem) null, 4056);
            Set<String> set = this.f3512b;
            ArrayList arrayList2 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(arrayList, 10));
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                arrayList2.add(((v) it.next()).f4221a);
            }
            set.addAll(arrayList2);
        }
    }
}
