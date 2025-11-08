package com.appsamurai.storyly.data.managers.network;

import android.content.Context;
import com.appsamurai.storyly.StorylyInit;
import com.appsamurai.storyly.data.e;
import com.google.common.net.HttpHeaders;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlinx.serialization.json.JsonObject;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class i extends e {
    @NotNull

    /* renamed from: g  reason: collision with root package name */
    public final Lazy f3928g;

    public static final class a extends Lambda implements Function0<com.appsamurai.storyly.data.managers.storage.a> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f3929a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public a(Context context) {
            super(0);
            this.f3929a = context;
        }

        public Object invoke() {
            return new com.appsamurai.storyly.data.managers.storage.a(this.f3929a, "stryly-ab-sets");
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public i(@NotNull Context context, @NotNull StorylyInit storylyInit, @Nullable com.appsamurai.storyly.data.managers.processing.a aVar) {
        super(context, storylyInit, 1, StringsKt__StringsJVMKt.replace$default(e.a().d(), "{token}", storylyInit.getStorylyId(), false, 4, (Object) null), com.appsamurai.storyly.data.managers.processing.e.StorylyData, aVar);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(storylyInit, "storylyInit");
        this.f3928g = LazyKt.lazy(new a(context));
    }

    @NotNull
    public Map<String, Object> a() {
        Context context = this.f3915a;
        StorylyInit storylyInit = this.f3916b;
        com.appsamurai.storyly.data.managers.storage.a aVar = (com.appsamurai.storyly.data.managers.storage.a) this.f3928g.getValue();
        String storylyId = this.f3916b.getStorylyId();
        aVar.getClass();
        Intrinsics.checkNotNullParameter(storylyId, "storylyId");
        Object a2 = aVar.a(storylyId);
        List<String> list = null;
        String str = a2 instanceof String ? (String) a2 : null;
        if (str != null) {
            list = StringsKt__StringsKt.split$default((CharSequence) str, new String[]{"/"}, false, 0, 6, (Object) null);
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        if (list != null) {
            for (String Z2 : list) {
                List Z3 = StringsKt__StringsKt.split$default((CharSequence) Z2, new String[]{"-"}, false, 0, 6, (Object) null);
                if (Z3.size() == 2) {
                    linkedHashMap.put(Z3.get(0), Z3.get(1));
                }
            }
        }
        return g.a(context, storylyInit, (String) null, (String) null, (JsonObject) null, linkedHashMap, 28);
    }

    @NotNull
    public Map<String, String> b() {
        String str;
        Pair pair = TuplesKt.to(HttpHeaders.AUTHORIZATION, this.f3916b.getStorylyId());
        com.appsamurai.storyly.data.managers.processing.a aVar = this.f3920f;
        if (aVar == null || (str = aVar.f3948b) == null) {
            str = "";
        }
        return MapsKt.mapOf(pair, TuplesKt.to(HttpHeaders.IF_NONE_MATCH, str));
    }
}
