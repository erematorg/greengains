package com.appsamurai.storyly.storylypresenter.storylylayer;

import G0.f;
import android.content.Context;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.appsamurai.storyly.data.b0;
import com.appsamurai.storyly.data.z;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Pair;
import kotlin.ReplaceWith;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.Required;
import kotlinx.serialization.SerialName;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.UnknownFieldException;
import kotlinx.serialization.builtins.BuiltinSerializersKt;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeDecoder;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.encoding.Encoder;
import kotlinx.serialization.internal.ArrayListSerializer;
import kotlinx.serialization.internal.GeneratedSerializer;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.PluginGeneratedSerialDescriptor;
import kotlinx.serialization.json.Json;
import kotlinx.serialization.json.JsonBuilder;
import kotlinx.serialization.json.JsonKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONObject;

public final class c {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f5657a;
    @Nullable

    /* renamed from: b  reason: collision with root package name */
    public List<b0> f5658b;
    @NotNull

    /* renamed from: c  reason: collision with root package name */
    public List<b0> f5659c = new ArrayList();

    /* renamed from: d  reason: collision with root package name */
    public Function1<? super List<Pair<Integer, Float>>, Unit> f5660d;

    /* renamed from: e  reason: collision with root package name */
    public Function2<? super List<b0>, ? super List<b0>, Unit> f5661e;

    /* renamed from: f  reason: collision with root package name */
    public boolean f5662f;

    @Serializable
    public static final class a {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public final List<b0> f5663a;

        @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
        /* renamed from: com.appsamurai.storyly.storylypresenter.storylylayer.c$a$a  reason: collision with other inner class name */
        public static final class C0050a implements GeneratedSerializer<a> {
            @NotNull

            /* renamed from: a  reason: collision with root package name */
            public static final C0050a f5664a;

            /* renamed from: b  reason: collision with root package name */
            public static final /* synthetic */ SerialDescriptor f5665b;

            static {
                C0050a aVar = new C0050a();
                f5664a = aVar;
                PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("com.appsamurai.storyly.storylypresenter.storylylayer.LayerMetadataManager.LayerMetadata", aVar, 1);
                pluginGeneratedSerialDescriptor.addElement("layers", false);
                f5665b = pluginGeneratedSerialDescriptor;
            }

            @NotNull
            public KSerializer<?>[] childSerializers() {
                return new KSerializer[]{new ArrayListSerializer(BuiltinSerializersKt.getNullable(b0.f3605o))};
            }

            public Object deserialize(Decoder decoder) {
                Object obj;
                Intrinsics.checkNotNullParameter(decoder, "decoder");
                SerialDescriptor serialDescriptor = f5665b;
                CompositeDecoder beginStructure = decoder.beginStructure(serialDescriptor);
                int i3 = 1;
                Object obj2 = null;
                if (beginStructure.decodeSequentially()) {
                    obj = beginStructure.decodeSerializableElement(serialDescriptor, 0, new ArrayListSerializer(BuiltinSerializersKt.getNullable(b0.f3605o)), null);
                } else {
                    boolean z2 = true;
                    int i4 = 0;
                    while (z2) {
                        int decodeElementIndex = beginStructure.decodeElementIndex(serialDescriptor);
                        if (decodeElementIndex == -1) {
                            z2 = false;
                        } else if (decodeElementIndex == 0) {
                            obj2 = beginStructure.decodeSerializableElement(serialDescriptor, 0, new ArrayListSerializer(BuiltinSerializersKt.getNullable(b0.f3605o)), obj2);
                            i4 = 1;
                        } else {
                            throw new UnknownFieldException(decodeElementIndex);
                        }
                    }
                    obj = obj2;
                    i3 = i4;
                }
                beginStructure.endStructure(serialDescriptor);
                return new a(i3, (List) obj);
            }

            @NotNull
            public SerialDescriptor getDescriptor() {
                return f5665b;
            }

            public void serialize(Encoder encoder, Object obj) {
                a aVar = (a) obj;
                Intrinsics.checkNotNullParameter(encoder, "encoder");
                Intrinsics.checkNotNullParameter(aVar, "value");
                SerialDescriptor serialDescriptor = f5665b;
                CompositeEncoder beginStructure = encoder.beginStructure(serialDescriptor);
                Intrinsics.checkNotNullParameter(aVar, "self");
                Intrinsics.checkNotNullParameter(beginStructure, "output");
                Intrinsics.checkNotNullParameter(serialDescriptor, "serialDesc");
                beginStructure.encodeSerializableElement(serialDescriptor, 0, new ArrayListSerializer(BuiltinSerializersKt.getNullable(b0.f3605o)), aVar.f5663a);
                beginStructure.endStructure(serialDescriptor);
            }

            @NotNull
            public KSerializer<?>[] typeParametersSerializers() {
                return GeneratedSerializer.DefaultImpls.typeParametersSerializers(this);
            }
        }

        @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
        public /* synthetic */ a(int i3, @Required @SerialName("layers") List list) {
            if (1 != (i3 & 1)) {
                PluginExceptionsKt.throwMissingFieldException(i3, 1, C0050a.f5664a.getDescriptor());
            }
            this.f5663a = list;
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof a) && Intrinsics.areEqual((Object) this.f5663a, (Object) ((a) obj).f5663a);
        }

        public int hashCode() {
            return this.f5663a.hashCode();
        }

        @NotNull
        public String toString() {
            return androidx.compose.ui.autofill.a.k(new StringBuilder("LayerMetadata(layers="), this.f5663a, ')');
        }
    }

    public static final class b extends JsonObjectRequest {
        public b(String str, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
            super(0, str, (JSONObject) null, listener, errorListener);
        }

        @NotNull
        public Map<String, String> getHeaders() {
            return MapsKt.mutableMapOf(TuplesKt.to("Content-Type", "application/json"), TuplesKt.to("Accept", "application/json"));
        }
    }

    /* renamed from: com.appsamurai.storyly.storylypresenter.storylylayer.c$c  reason: collision with other inner class name */
    public static final class C0051c extends Lambda implements Function1<JsonBuilder, Unit> {

        /* renamed from: a  reason: collision with root package name */
        public static final C0051c f5666a = new C0051c();

        public C0051c() {
            super(1);
        }

        public Object invoke(Object obj) {
            JsonBuilder jsonBuilder = (JsonBuilder) obj;
            Intrinsics.checkNotNullParameter(jsonBuilder, "$this$Json");
            jsonBuilder.setIgnoreUnknownKeys(true);
            return Unit.INSTANCE;
        }
    }

    public static final class d<T> implements Comparator {
        public final int compare(T t2, T t3) {
            return ComparisonsKt.compareValues(((b0) t2).f3616k, ((b0) t3).f3616k);
        }
    }

    public c(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f5657a = context;
    }

    public static final void a(VolleyError volleyError) {
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0037, code lost:
        r3 = kotlin.collections.CollectionsKt.filterNotNull(r3);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void b(@org.jetbrains.annotations.NotNull com.appsamurai.storyly.data.z r3, @org.jetbrains.annotations.Nullable java.lang.String r4) {
        /*
            r2 = this;
            java.lang.String r0 = "storylyItem"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r0)
            r0 = 0
            r2.f5662f = r0
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            r2.f5659c = r0
            com.appsamurai.storyly.data.d0 r0 = r3.f4303b
            java.util.List<com.appsamurai.storyly.data.b0> r0 = r0.f3639a
            if (r0 != 0) goto L_0x0017
            goto L_0x0050
        L_0x0017:
            boolean r1 = r0.isEmpty()
            if (r1 == 0) goto L_0x001e
            goto L_0x0050
        L_0x001e:
            java.util.Iterator r0 = r0.iterator()
        L_0x0022:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x0050
            java.lang.Object r1 = r0.next()
            com.appsamurai.storyly.data.b0 r1 = (com.appsamurai.storyly.data.b0) r1
            if (r1 == 0) goto L_0x0022
            com.appsamurai.storyly.data.d0 r3 = r3.f4303b
            java.util.List<com.appsamurai.storyly.data.b0> r3 = r3.f3639a
            if (r3 != 0) goto L_0x0037
            goto L_0x003d
        L_0x0037:
            java.util.List r3 = kotlin.collections.CollectionsKt.filterNotNull(r3)
            if (r3 != 0) goto L_0x003f
        L_0x003d:
            r3 = 0
            goto L_0x0048
        L_0x003f:
            com.appsamurai.storyly.storylypresenter.storylylayer.c$d r4 = new com.appsamurai.storyly.storylypresenter.storylylayer.c$d
            r4.<init>()
            java.util.List r3 = kotlin.collections.CollectionsKt.sortedWith(r3, r4)
        L_0x0048:
            r2.f5658b = r3
            r3 = 0
            r2.a((long) r3)
            goto L_0x0053
        L_0x0050:
            r2.a(r3, r4)
        L_0x0053:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.storylypresenter.storylylayer.c.b(com.appsamurai.storyly.data.z, java.lang.String):void");
    }

    public final void a(long j2) {
        long j3;
        long j4;
        ArrayList arrayList = new ArrayList();
        List<b0> list = this.f5658b;
        if (list != null) {
            for (b0 b0Var : list) {
                Long l2 = b0Var.f3616k;
                if (l2 == null) {
                    j3 = Long.MIN_VALUE;
                } else {
                    j3 = l2.longValue();
                }
                if (j3 < j2) {
                    Long l3 = b0Var.f3617l;
                    if (l3 == null) {
                        j4 = Long.MAX_VALUE;
                    } else {
                        j4 = l3.longValue();
                    }
                    if (j4 > j2) {
                        arrayList.add(b0Var);
                    }
                }
            }
        }
        List<b0> list2 = this.f5659c;
        ArrayList arrayList2 = new ArrayList();
        for (T next : list2) {
            if (!arrayList.contains((b0) next)) {
                arrayList2.add(next);
            }
        }
        ArrayList arrayList3 = new ArrayList();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            Object next2 = it.next();
            if (!this.f5659c.contains((b0) next2)) {
                arrayList3.add(next2);
            }
        }
        this.f5659c = arrayList;
        if (!arrayList3.isEmpty() || !arrayList2.isEmpty()) {
            Function2<? super List<b0>, ? super List<b0>, Unit> function2 = this.f5661e;
            if (function2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("onMetadataUpdated");
                function2 = null;
            }
            function2.invoke(arrayList3, arrayList2);
        }
    }

    public final void a(z zVar, String str) {
        if (str != null) {
            b bVar = new b(str, new f(zVar, 0, this, str), new A.a(10));
            bVar.setRetryPolicy(new DefaultRetryPolicy(10000, 3, 1.0f));
            bVar.setShouldCache(false);
            Volley.newRequestQueue(this.f5657a).add(bVar);
        }
    }

    public static final void a(z zVar, c cVar, String str, JSONObject jSONObject) {
        Intrinsics.checkNotNullParameter(zVar, "$storylyItem");
        Intrinsics.checkNotNullParameter(cVar, "this$0");
        Json Json$default = JsonKt.Json$default((Json) null, C0051c.f5666a, 1, (Object) null);
        a.C0050a aVar = a.C0050a.f5664a;
        String jSONObject2 = jSONObject.toString();
        Intrinsics.checkNotNullExpressionValue(jSONObject2, "response.toString()");
        zVar.f4303b.f3639a = ((a) Json$default.decodeFromString(aVar, jSONObject2)).f5663a;
        cVar.b(zVar, str);
    }
}
