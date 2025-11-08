package com.appsamurai.storyly.analytics;

import android.content.Context;
import androidx.core.app.NotificationCompat;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.appsamurai.storyly.Story;
import com.appsamurai.storyly.StoryComponent;
import com.appsamurai.storyly.StoryGroup;
import com.appsamurai.storyly.StoryGroupType;
import com.appsamurai.storyly.StoryType;
import com.appsamurai.storyly.StorylyInit;
import com.appsamurai.storyly.config.StorylyConfig;
import com.appsamurai.storyly.data.b0;
import com.appsamurai.storyly.data.managers.product.STRCart;
import com.appsamurai.storyly.data.managers.product.STRCartEventResult;
import com.appsamurai.storyly.data.managers.product.STRCartItem;
import com.appsamurai.storyly.data.v;
import com.appsamurai.storyly.data.z;
import com.appsamurai.storyly.storylypresenter.g1;
import com.appsamurai.storyly.util.a;
import com.google.common.net.HttpHeaders;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.functions.Function5;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import kotlinx.serialization.json.JsonArrayBuilder;
import kotlinx.serialization.json.JsonElement;
import kotlinx.serialization.json.JsonElementBuildersKt;
import kotlinx.serialization.json.JsonElementKt;
import kotlinx.serialization.json.JsonNull;
import kotlinx.serialization.json.JsonObject;
import kotlinx.serialization.json.JsonObjectBuilder;
import kotlinx.serialization.json.JsonPrimitive;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class e {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f3514a;
    @NotNull

    /* renamed from: b  reason: collision with root package name */
    public final Function4<StorylyEvent, StoryGroup, Story, StoryComponent, Unit> f3515b;
    @NotNull

    /* renamed from: c  reason: collision with root package name */
    public Function5<? super StorylyEvent, ? super Function1<? super STRCart, Unit>, ? super Function1<? super STRCartEventResult, Unit>, ? super STRCart, ? super STRCartItem, Unit> f3516c;
    @NotNull

    /* renamed from: d  reason: collision with root package name */
    public final RequestQueue f3517d;
    @Nullable

    /* renamed from: e  reason: collision with root package name */
    public StorylyInit f3518e;
    @NotNull

    /* renamed from: f  reason: collision with root package name */
    public final Lazy f3519f = LazyKt.lazy(f.f3529a);
    @Nullable

    /* renamed from: g  reason: collision with root package name */
    public String f3520g;
    @NotNull

    /* renamed from: h  reason: collision with root package name */
    public final Lazy f3521h = LazyKt.lazy(c.f3524a);
    @NotNull

    /* renamed from: i  reason: collision with root package name */
    public final Lazy f3522i = LazyKt.lazy(b.f3523a);

    public /* synthetic */ class a {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[StorylyEvent.values().length];
            iArr[StorylyEvent.StoryProductAdded.ordinal()] = 1;
            iArr[StorylyEvent.StoryProductUpdated.ordinal()] = 2;
            iArr[StorylyEvent.StoryProductRemoved.ordinal()] = 3;
            iArr[StorylyEvent.StoryCheckoutButtonClicked.ordinal()] = 4;
            iArr[StorylyEvent.StoryProductSelected.ordinal()] = 5;
            iArr[StorylyEvent.StoryCartButtonClicked.ordinal()] = 6;
            iArr[StorylyEvent.StoryCartViewClicked.ordinal()] = 7;
            iArr[StorylyEvent.StoryProductCatalogClicked.ordinal()] = 8;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public static final class b extends Lambda implements Function0<List<? extends a>> {

        /* renamed from: a  reason: collision with root package name */
        public static final b f3523a = new b();

        public b() {
            super(0);
        }

        public Object invoke() {
            return CollectionsKt.listOf(a.Dismissed, a.LastGroupCompleted);
        }
    }

    public static final class c extends Lambda implements Function0<List<? extends a>> {

        /* renamed from: a  reason: collision with root package name */
        public static final c f3524a = new c();

        public c() {
            super(0);
        }

        public Object invoke() {
            return CollectionsKt.listOf(a.Selected, a.ProgrammaticallySelected, a.DeepLinkOpened);
        }
    }

    public static final class d extends Lambda implements Function1<JsonArrayBuilder, Unit> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ v f3525a;

        /* renamed from: b  reason: collision with root package name */
        public final /* synthetic */ z f3526b;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public d(v vVar, z zVar) {
            super(1);
            this.f3525a = vVar;
            this.f3526b = zVar;
        }

        public Object invoke(Object obj) {
            JsonArrayBuilder jsonArrayBuilder = (JsonArrayBuilder) obj;
            Intrinsics.checkNotNullParameter(jsonArrayBuilder, "$this$putJsonArray");
            v vVar = this.f3525a;
            z zVar = this.f3526b;
            JsonObjectBuilder jsonObjectBuilder = new JsonObjectBuilder();
            JsonElementBuildersKt.put(jsonObjectBuilder, "story_group_id", vVar.f4221a);
            JsonElementBuildersKt.put(jsonObjectBuilder, "story_id", zVar == null ? null : zVar.f4302a);
            Unit unit = Unit.INSTANCE;
            jsonArrayBuilder.add(jsonObjectBuilder.build());
            return Unit.INSTANCE;
        }
    }

    /* renamed from: com.appsamurai.storyly.analytics.e$e  reason: collision with other inner class name */
    public static final class C0006e extends StringRequest {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ String f3527a;

        /* renamed from: b  reason: collision with root package name */
        public final /* synthetic */ JsonObject f3528b;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public C0006e(String str, JsonObject jsonObject, String str2, Response.Listener<String> listener, Response.ErrorListener errorListener) {
            super(1, str2, listener, errorListener);
            this.f3527a = str;
            this.f3528b = jsonObject;
        }

        @NotNull
        public byte[] getBody() {
            String jsonObject = this.f3528b.toString();
            Charset charset = Charsets.UTF_8;
            if (jsonObject != null) {
                byte[] bytes = jsonObject.getBytes(charset);
                Intrinsics.checkNotNullExpressionValue(bytes, "(this as java.lang.String).getBytes(charset)");
                return bytes;
            }
            throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
        }

        @NotNull
        public Map<String, String> getHeaders() {
            return MapsKt.mutableMapOf(TuplesKt.to("Content-Type", "application/json"), TuplesKt.to("Accept", "application/json"), TuplesKt.to(HttpHeaders.AUTHORIZATION, this.f3527a));
        }
    }

    public static final class f extends Lambda implements Function0<String> {

        /* renamed from: a  reason: collision with root package name */
        public static final f f3529a = new f();

        public f() {
            super(0);
        }

        public Object invoke() {
            String uuid = UUID.randomUUID().toString();
            Intrinsics.checkNotNullExpressionValue(uuid, "randomUUID().toString()");
            Locale locale = Locale.ENGLISH;
            Intrinsics.checkNotNullExpressionValue(locale, "ENGLISH");
            String upperCase = uuid.toUpperCase(locale);
            Intrinsics.checkNotNullExpressionValue(upperCase, "(this as java.lang.String).toUpperCase(locale)");
            return upperCase;
        }
    }

    public static final class g extends Lambda implements Function1<JsonArrayBuilder, Unit> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ e f3530a;

        /* renamed from: b  reason: collision with root package name */
        public final /* synthetic */ v f3531b;

        /* renamed from: c  reason: collision with root package name */
        public final /* synthetic */ z f3532c;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public g(e eVar, v vVar, z zVar) {
            super(1);
            this.f3530a = eVar;
            this.f3531b = vVar;
            this.f3532c = zVar;
        }

        public Object invoke(Object obj) {
            JsonArrayBuilder jsonArrayBuilder = (JsonArrayBuilder) obj;
            Intrinsics.checkNotNullParameter(jsonArrayBuilder, "$this$putJsonArray");
            e eVar = this.f3530a;
            v vVar = this.f3531b;
            JsonElement a2 = eVar.a(vVar == null ? null : vVar.f4228h, this.f3532c);
            if (a2 == null) {
                a2 = JsonNull.INSTANCE;
            }
            jsonArrayBuilder.add(a2);
            return Unit.INSTANCE;
        }
    }

    public static final class h extends StringRequest {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ String f3533a;

        /* renamed from: b  reason: collision with root package name */
        public final /* synthetic */ v f3534b;

        /* renamed from: c  reason: collision with root package name */
        public final /* synthetic */ StorylyInit f3535c;

        /* renamed from: d  reason: collision with root package name */
        public final /* synthetic */ JsonObject f3536d;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public h(String str, v vVar, StorylyInit storylyInit, JsonObject jsonObject, String str2, Response.Listener<String> listener, Response.ErrorListener errorListener) {
            super(1, str2, listener, errorListener);
            this.f3533a = str;
            this.f3534b = vVar;
            this.f3535c = storylyInit;
            this.f3536d = jsonObject;
        }

        @NotNull
        public byte[] getBody() {
            String jsonObject = this.f3536d.toString();
            Charset charset = Charsets.UTF_8;
            if (jsonObject != null) {
                byte[] bytes = jsonObject.getBytes(charset);
                Intrinsics.checkNotNullExpressionValue(bytes, "(this as java.lang.String).getBytes(charset)");
                return bytes;
            }
            throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
        }

        @NotNull
        public Map<String, String> getHeaders() {
            Pair pair = TuplesKt.to("Content-Type", "application/json");
            Pair pair2 = TuplesKt.to("Accept", "application/json");
            String str = this.f3533a;
            if (str == null) {
                v vVar = this.f3534b;
                str = vVar == null ? null : vVar.f4233m;
                if (str == null) {
                    str = this.f3535c.getStorylyId();
                }
            }
            return MapsKt.mutableMapOf(pair, pair2, TuplesKt.to(HttpHeaders.AUTHORIZATION, str));
        }
    }

    public e(@NotNull Context context, @NotNull Function4<? super StorylyEvent, ? super StoryGroup, ? super Story, ? super StoryComponent, Unit> function4, @NotNull Function5<? super StorylyEvent, ? super Function1<? super STRCart, Unit>, ? super Function1<? super STRCartEventResult, Unit>, ? super STRCart, ? super STRCartItem, Unit> function5) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(function4, "onTrackEvent");
        Intrinsics.checkNotNullParameter(function5, "onTrackProductEvent");
        this.f3514a = context;
        this.f3515b = function4;
        this.f3516c = function5;
        RequestQueue newRequestQueue = Volley.newRequestQueue(context);
        Intrinsics.checkNotNullExpressionValue(newRequestQueue, "newRequestQueue(context)");
        this.f3517d = newRequestQueue;
    }

    public static final void a(VolleyError volleyError) {
    }

    public static final void a(String str) {
    }

    public final void a(@Nullable v vVar, @Nullable z zVar, @NotNull Function0<Unit> function0) {
        String str;
        Intrinsics.checkNotNullParameter(function0, "onReportCompleted");
        if (zVar != null && zVar.f4316o) {
            ((g1) function0).invoke();
            return;
        }
        StorylyInit storylyInit = this.f3518e;
        if (storylyInit != null && !StringsKt.isBlank(storylyInit.getStorylyId())) {
            if (vVar == null) {
                str = null;
            } else {
                str = vVar.f4233m;
            }
            String str2 = str;
            if (str2 != null) {
                String str3 = com.appsamurai.storyly.data.e.f3644a.f3635e;
                JsonObjectBuilder jsonObjectBuilder = new JsonObjectBuilder();
                JsonElementBuildersKt.put(jsonObjectBuilder, "user_payload", storylyInit.getConfig().getStorylyPayload());
                JsonElementBuildersKt.putJsonArray(jsonObjectBuilder, "stories", new d(vVar, zVar));
                C0006e eVar = new C0006e(str2, jsonObjectBuilder.build(), str3, new A.a(19), new A.a(20));
                eVar.setRetryPolicy(new DefaultRetryPolicy(10000, 3, 1.0f));
                eVar.setShouldCache(false);
                this.f3517d.add(eVar);
                ((g1) function0).invoke();
            }
        }
    }

    public static /* synthetic */ boolean a(e eVar, a aVar, v vVar, z zVar, b0 b0Var, StoryComponent storyComponent, JsonObject jsonObject, Function1 function1, String str, Function1 function12, Function1 function13, STRCart sTRCart, STRCartItem sTRCartItem, int i3) {
        int i4 = i3;
        return eVar.a(aVar, vVar, zVar, (i4 & 8) != 0 ? null : b0Var, (i4 & 16) != 0 ? null : storyComponent, (i4 & 32) != 0 ? null : jsonObject, (i4 & 64) != 0 ? null : function1, (i4 & 128) != 0 ? null : str, (i4 & 256) != 0 ? null : function12, (i4 & 512) != 0 ? null : function13, (i4 & 1024) != 0 ? null : sTRCart, (i4 & 2048) != 0 ? null : sTRCartItem);
    }

    public final boolean a(@NotNull a aVar, @Nullable v vVar, @Nullable z zVar, @Nullable b0 b0Var, @Nullable StoryComponent storyComponent, @Nullable JsonObject jsonObject, @Nullable Function1<? super Boolean, Unit> function1, @Nullable String str, @Nullable Function1<? super STRCart, Unit> function12, @Nullable Function1<? super STRCartEventResult, Unit> function13, @Nullable STRCart sTRCart, @Nullable STRCartItem sTRCartItem) {
        String str2;
        String str3;
        StoryGroupType storyGroupType;
        StoryGroupType storyGroupType2;
        Integer num;
        Integer num2;
        String str4;
        String str5;
        String str6;
        Float f2;
        Float f3;
        Long l2;
        Long l3;
        StoryType storyType;
        String str7;
        JsonObject build;
        StoryGroup storyGroup;
        StoryComponent storyComponent2;
        Story story;
        StorylyConfig config;
        Set<Map.Entry<String, JsonElement>> entrySet;
        StoryGroupType storyGroupType3;
        List<z> list;
        a aVar2 = aVar;
        v vVar2 = vVar;
        z zVar2 = zVar;
        b0 b0Var2 = b0Var;
        Function1<? super Boolean, Unit> function14 = function1;
        Intrinsics.checkNotNullParameter(aVar2, NotificationCompat.CATEGORY_EVENT);
        StorylyInit storylyInit = this.f3518e;
        if (storylyInit == null || StringsKt.isBlank(storylyInit.getStorylyId())) {
            return false;
        }
        if (this.f3520g == null && ((List) this.f3521h.getValue()).contains(aVar2)) {
            String uuid = UUID.randomUUID().toString();
            Intrinsics.checkNotNullExpressionValue(uuid, "randomUUID().toString()");
            Locale locale = Locale.ENGLISH;
            Intrinsics.checkNotNullExpressionValue(locale, "ENGLISH");
            String upperCase = uuid.toUpperCase(locale);
            Intrinsics.checkNotNullExpressionValue(upperCase, "(this as java.lang.String).toUpperCase(locale)");
            this.f3520g = upperCase;
        }
        String storylyId = storylyInit.getStorylyId();
        if (str == null) {
            str2 = vVar2 == null ? null : vVar2.f4233m;
        } else {
            str2 = str;
        }
        if (str2 == null) {
            str3 = StringsKt__StringsJVMKt.replace$default(com.appsamurai.storyly.data.e.f3644a.f3632b, "{token}", storylyId, false, 4, (Object) null);
        } else {
            str3 = com.appsamurai.storyly.data.e.f3644a.f3636f;
        }
        String str8 = str3;
        if (str8 == null) {
            return false;
        }
        JsonObjectBuilder jsonObjectBuilder = new JsonObjectBuilder();
        JsonElementBuildersKt.put(jsonObjectBuilder, "event_type", aVar.name());
        if (vVar2 == null) {
            storyGroupType = null;
        } else {
            storyGroupType = vVar2.f4228h;
        }
        JsonElement a2 = a(storyGroupType, vVar2);
        if (a2 == null) {
            a2 = JsonNull.INSTANCE;
        }
        jsonObjectBuilder.put("story_group_id", a2);
        if (vVar2 == null) {
            storyGroupType2 = null;
        } else {
            storyGroupType2 = vVar2.f4228h;
        }
        JsonElement a3 = a(storyGroupType2, zVar2);
        if (a3 == null) {
            a3 = JsonNull.INSTANCE;
        }
        jsonObjectBuilder.put("story_id", a3);
        JsonElementBuildersKt.putJsonArray(jsonObjectBuilder, "story_ids", new g(this, vVar2, zVar2));
        if (vVar2 == null) {
            num = null;
        } else {
            num = vVar2.f4241u;
        }
        JsonElementBuildersKt.put(jsonObjectBuilder, "story_group_index", (Number) num);
        if (zVar2 == null || vVar2 == null || (list = vVar2.f4226f) == null) {
            num2 = null;
        } else {
            num2 = Integer.valueOf(list.indexOf(zVar2));
        }
        JsonElementBuildersKt.put(jsonObjectBuilder, "story_index", (Number) num2);
        if (vVar2 == null || (storyGroupType3 = vVar2.f4228h) == null) {
            str4 = null;
        } else {
            str4 = storyGroupType3.getCustomName();
        }
        JsonElementBuildersKt.put(jsonObjectBuilder, "story_group_type", str4);
        if (b0Var2 == null) {
            str5 = null;
        } else {
            str5 = b0Var2.f3614i;
        }
        JsonElementBuildersKt.put(jsonObjectBuilder, "uid", str5);
        if (b0Var2 == null) {
            str6 = null;
        } else {
            str6 = b0Var2.f3606a;
        }
        JsonElementBuildersKt.put(jsonObjectBuilder, "story_interactive_type", str6);
        if (b0Var2 == null) {
            f2 = null;
        } else {
            f2 = Float.valueOf(b0Var2.f3607b);
        }
        JsonElementBuildersKt.put(jsonObjectBuilder, "story_interactive_x", (Number) f2);
        if (b0Var2 == null) {
            f3 = null;
        } else {
            f3 = Float.valueOf(b0Var2.f3608c);
        }
        JsonElementBuildersKt.put(jsonObjectBuilder, "story_interactive_y", (Number) f3);
        if (zVar2 == null) {
            l2 = null;
        } else {
            l2 = Long.valueOf(zVar2.f4304c);
        }
        JsonElementBuildersKt.put(jsonObjectBuilder, "duration", (Number) l2);
        if (zVar2 == null) {
            l3 = null;
        } else {
            l3 = Long.valueOf(zVar2.f4317p);
        }
        JsonElementBuildersKt.put(jsonObjectBuilder, "watch_length", (Number) l3);
        if (zVar2 == null) {
            storyType = null;
        } else {
            storyType = zVar2.f4307f;
        }
        if (storyType == StoryType.LongVideo) {
            JsonElementBuildersKt.put(jsonObjectBuilder, "story_session_time", (Number) Long.valueOf(zVar2.f4319r));
        }
        JsonElementBuildersKt.put(jsonObjectBuilder, "event_time", (Number) Long.valueOf(System.currentTimeMillis()));
        if (!(jsonObject == null || (entrySet = jsonObject.entrySet()) == null)) {
            for (Map.Entry entry : entrySet) {
                jsonObjectBuilder.put((String) entry.getKey(), (JsonElement) entry.getValue());
            }
        }
        JsonObject build2 = jsonObjectBuilder.build();
        Context context = this.f3514a;
        String str9 = (String) this.f3519f.getValue();
        String str10 = this.f3520g;
        if (str == null) {
            str7 = vVar2 == null ? null : vVar2.f4233m;
        } else {
            str7 = str;
        }
        if (str7 != null) {
            JsonObjectBuilder jsonObjectBuilder2 = new JsonObjectBuilder();
            jsonObjectBuilder2.put("payload", build2);
            StorylyInit storylyInit2 = this.f3518e;
            JsonElementBuildersKt.put(jsonObjectBuilder2, "user_payload", (storylyInit2 == null || (config = storylyInit2.getConfig()) == null) ? null : config.getStorylyPayload());
            build = jsonObjectBuilder2.build();
        } else {
            JsonObjectBuilder jsonObjectBuilder3 = new JsonObjectBuilder();
            jsonObjectBuilder3.put("payload", build2);
            build = jsonObjectBuilder3.build();
        }
        h hVar = new h(str, vVar, storylyInit, com.appsamurai.storyly.data.managers.network.g.a(context, storylyInit, str9, str10, build, (Map) null, 32), str8, new T.a(function14, 0), new T.a(function14, 1));
        hVar.setRetryPolicy(new DefaultRetryPolicy(10000, 3, 1.0f));
        hVar.setShouldCache(false);
        this.f3517d.add(hVar);
        if (this.f3520g != null && ((List) this.f3522i.getValue()).contains(aVar2)) {
            this.f3520g = null;
        }
        List<StorylyEvent> list2 = aVar2.f3501a;
        if (list2 == null) {
            return true;
        }
        for (StorylyEvent storylyEvent : list2) {
            switch (a.$EnumSwitchMapping$0[storylyEvent.ordinal()]) {
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                case 8:
                    StoryComponent storyComponent3 = storyComponent;
                    this.f3516c.invoke(storylyEvent, function12, function13, sTRCart, sTRCartItem);
                    break;
                default:
                    Function4<StorylyEvent, StoryGroup, Story, StoryComponent, Unit> function4 = this.f3515b;
                    if (vVar2 == null) {
                        storyGroup = null;
                    } else {
                        storyGroup = vVar.d();
                    }
                    if (zVar2 == null) {
                        storyComponent2 = storyComponent;
                        story = null;
                    } else {
                        story = zVar.a();
                        storyComponent2 = storyComponent;
                    }
                    function4.invoke(storylyEvent, storyGroup, story, storyComponent2);
                    break;
            }
        }
        return true;
    }

    public static final void a(Function1 function1, String str) {
        if (function1 != null) {
            function1.invoke(Boolean.TRUE);
        }
    }

    public static final void a(Function1 function1, VolleyError volleyError) {
        a.C0055a aVar = com.appsamurai.storyly.util.a.f6249a;
        StringBuilder sb = new StringBuilder("Track event sent failed:");
        sb.append(volleyError);
        sb.append(AbstractJsonLexerKt.COLON);
        NetworkResponse networkResponse = volleyError.networkResponse;
        sb.append(networkResponse == null ? 500 : networkResponse.statusCode);
        a.C0055a.a(aVar, sb.toString(), (String) null, 2);
        if (function1 != null) {
            function1.invoke(Boolean.FALSE);
        }
    }

    @Nullable
    public final JsonPrimitive a(@Nullable StoryGroupType storyGroupType, @Nullable v vVar) {
        if (storyGroupType == null || vVar == null) {
            return null;
        }
        if (storyGroupType == StoryGroupType.MomentsDefault) {
            return JsonElementKt.JsonPrimitive(vVar.f4221a);
        }
        return JsonElementKt.JsonPrimitive((Number) StringsKt.toIntOrNull(vVar.f4221a));
    }

    @Nullable
    public final JsonPrimitive a(@Nullable StoryGroupType storyGroupType, @Nullable z zVar) {
        if (storyGroupType == null || zVar == null) {
            return null;
        }
        if (storyGroupType == StoryGroupType.MomentsDefault) {
            return JsonElementKt.JsonPrimitive(zVar.f4302a);
        }
        return JsonElementKt.JsonPrimitive((Number) StringsKt.toIntOrNull(zVar.f4302a));
    }
}
