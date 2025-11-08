package com.appsamurai.storyly.data;

import com.appsamurai.storyly.ShareType;
import com.appsamurai.storyly.Story;
import com.appsamurai.storyly.StoryComponent;
import com.appsamurai.storyly.StoryMedia;
import com.appsamurai.storyly.StoryType;
import com.appsamurai.storyly.analytics.b;
import com.appsamurai.storyly.data.d0;
import com.appsamurai.storyly.data.n;
import com.appsamurai.storyly.data.q0;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.descriptors.PrimitiveKind;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.descriptors.SerialDescriptorsKt;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.encoding.Encoder;
import kotlinx.serialization.json.JsonArray;
import kotlinx.serialization.json.JsonDecoder;
import kotlinx.serialization.json.JsonElement;
import kotlinx.serialization.json.JsonElementKt;
import kotlinx.serialization.json.JsonObject;
import kotlinx.serialization.json.JsonPrimitive;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Serializable(with = a.class)
public final class z {
    @NotNull

    /* renamed from: u  reason: collision with root package name */
    public static final a f4300u = new a();
    @NotNull

    /* renamed from: v  reason: collision with root package name */
    public static final SerialDescriptor f4301v = SerialDescriptorsKt.PrimitiveSerialDescriptor("StorylyItem", PrimitiveKind.STRING.INSTANCE);
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public String f4302a;
    @NotNull

    /* renamed from: b  reason: collision with root package name */
    public final d0 f4303b;

    /* renamed from: c  reason: collision with root package name */
    public long f4304c;
    @NotNull

    /* renamed from: d  reason: collision with root package name */
    public String f4305d;

    /* renamed from: e  reason: collision with root package name */
    public int f4306e;
    @NotNull

    /* renamed from: f  reason: collision with root package name */
    public final StoryType f4307f;
    @Nullable

    /* renamed from: g  reason: collision with root package name */
    public final String f4308g;
    @Nullable

    /* renamed from: h  reason: collision with root package name */
    public final String f4309h;
    @Nullable

    /* renamed from: i  reason: collision with root package name */
    public String f4310i;
    @Nullable

    /* renamed from: j  reason: collision with root package name */
    public final String f4311j;
    @Nullable

    /* renamed from: k  reason: collision with root package name */
    public final ShareType f4312k;
    @Nullable

    /* renamed from: l  reason: collision with root package name */
    public final Long f4313l;
    @Nullable

    /* renamed from: m  reason: collision with root package name */
    public b f4314m;
    @Nullable

    /* renamed from: n  reason: collision with root package name */
    public List<? extends List<n>> f4315n;

    /* renamed from: o  reason: collision with root package name */
    public boolean f4316o;

    /* renamed from: p  reason: collision with root package name */
    public long f4317p;

    /* renamed from: q  reason: collision with root package name */
    public boolean f4318q = true;

    /* renamed from: r  reason: collision with root package name */
    public long f4319r;

    /* renamed from: s  reason: collision with root package name */
    public boolean f4320s;
    @Nullable

    /* renamed from: t  reason: collision with root package name */
    public final Long f4321t;

    public static final class a implements KSerializer<z> {
        public Object deserialize(Decoder decoder) {
            String str;
            String str2;
            ArrayList arrayList;
            StoryType storyType;
            List<b0> list;
            JsonArray jsonArray;
            JsonPrimitive jsonPrimitive;
            JsonPrimitive jsonPrimitive2;
            JsonPrimitive jsonPrimitive3;
            JsonPrimitive jsonPrimitive4;
            JsonPrimitive jsonPrimitive5;
            JsonPrimitive jsonPrimitive6;
            JsonPrimitive jsonPrimitive7;
            JsonPrimitive jsonPrimitive8;
            JsonObject jsonObject;
            JsonPrimitive jsonPrimitive9;
            Decoder decoder2 = decoder;
            Intrinsics.checkNotNullParameter(decoder2, "decoder");
            JsonDecoder jsonDecoder = decoder2 instanceof JsonDecoder ? (JsonDecoder) decoder2 : null;
            if (jsonDecoder != null) {
                JsonObject jsonObject2 = JsonElementKt.getJsonObject(jsonDecoder.decodeJsonElement());
                if (jsonObject2 == null) {
                    jsonObject2 = null;
                }
                if (jsonObject2 != null) {
                    JsonElement jsonElement = (JsonElement) jsonObject2.get((Object) "story_id");
                    String contentOrNull = (jsonElement == null || (jsonPrimitive9 = JsonElementKt.getJsonPrimitive(jsonElement)) == null) ? null : JsonElementKt.getContentOrNull(jsonPrimitive9);
                    if (contentOrNull != null) {
                        JsonElement jsonElement2 = (JsonElement) jsonObject2.get((Object) SVGParser.XML_STYLESHEET_ATTR_MEDIA);
                        d0 d0Var = (jsonElement2 == null || (jsonObject = JsonElementKt.getJsonObject(jsonElement2)) == null) ? null : (d0) ((JsonDecoder) decoder2).getJson().decodeFromJsonElement(d0.a.f3642a, jsonObject);
                        if (d0Var != null) {
                            JsonElement jsonElement3 = (JsonElement) jsonObject2.get((Object) "duration");
                            Long longOrNull = (jsonElement3 == null || (jsonPrimitive8 = JsonElementKt.getJsonPrimitive(jsonElement3)) == null) ? null : JsonElementKt.getLongOrNull(jsonPrimitive8);
                            if (longOrNull != null) {
                                long longValue = longOrNull.longValue();
                                JsonElement jsonElement4 = (JsonElement) jsonObject2.get((Object) "title");
                                String contentOrNull2 = (jsonElement4 == null || (jsonPrimitive7 = JsonElementKt.getJsonPrimitive(jsonElement4)) == null) ? null : JsonElementKt.getContentOrNull(jsonPrimitive7);
                                if (contentOrNull2 != null) {
                                    JsonElement jsonElement5 = (JsonElement) jsonObject2.get((Object) "order");
                                    Integer intOrNull = (jsonElement5 == null || (jsonPrimitive6 = JsonElementKt.getJsonPrimitive(jsonElement5)) == null) ? null : JsonElementKt.getIntOrNull(jsonPrimitive6);
                                    if (intOrNull != null) {
                                        int intValue = intOrNull.intValue();
                                        JsonElement jsonElement6 = (JsonElement) jsonObject2.get((Object) "type");
                                        StoryType storyType2 = jsonElement6 == null ? null : (StoryType) ((JsonDecoder) decoder2).getJson().decodeFromJsonElement(StoryType.StoryTypeDeserializer.serializer(), jsonElement6);
                                        if (storyType2 == null) {
                                            storyType2 = StoryType.Image;
                                        }
                                        JsonElement jsonElement7 = (JsonElement) jsonObject2.get((Object) "name");
                                        String contentOrNull3 = (jsonElement7 == null || (jsonPrimitive5 = JsonElementKt.getJsonPrimitive(jsonElement7)) == null) ? null : JsonElementKt.getContentOrNull(jsonPrimitive5);
                                        JsonElement jsonElement8 = (JsonElement) jsonObject2.get((Object) "alt_text");
                                        String contentOrNull4 = (jsonElement8 == null || (jsonPrimitive4 = JsonElementKt.getJsonPrimitive(jsonElement8)) == null) ? null : JsonElementKt.getContentOrNull(jsonPrimitive4);
                                        JsonElement jsonElement9 = (JsonElement) jsonObject2.get((Object) "preview_path");
                                        String contentOrNull5 = (jsonElement9 == null || (jsonPrimitive3 = JsonElementKt.getJsonPrimitive(jsonElement9)) == null) ? null : JsonElementKt.getContentOrNull(jsonPrimitive3);
                                        JsonElement jsonElement10 = (JsonElement) jsonObject2.get((Object) FirebaseAnalytics.Param.END_DATE);
                                        String contentOrNull6 = (jsonElement10 == null || (jsonPrimitive2 = JsonElementKt.getJsonPrimitive(jsonElement10)) == null) ? null : JsonElementKt.getContentOrNull(jsonPrimitive2);
                                        JsonElement jsonElement11 = (JsonElement) jsonObject2.get((Object) "create_date");
                                        Long longOrNull2 = (jsonElement11 == null || (jsonPrimitive = JsonElementKt.getJsonPrimitive(jsonElement11)) == null) ? null : JsonElementKt.getLongOrNull(jsonPrimitive);
                                        JsonElement jsonElement12 = (JsonElement) jsonObject2.get((Object) "conditions");
                                        if (jsonElement12 == null || (jsonArray = JsonElementKt.getJsonArray(jsonElement12)) == null) {
                                            str = contentOrNull5;
                                            str2 = contentOrNull6;
                                            arrayList = null;
                                        } else {
                                            ArrayList arrayList2 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(jsonArray, 10));
                                            Iterator<JsonElement> it = jsonArray.iterator();
                                            while (it.hasNext()) {
                                                JsonArray jsonArray2 = JsonElementKt.getJsonArray(it.next());
                                                Iterator<JsonElement> it2 = it;
                                                String str3 = contentOrNull6;
                                                ArrayList arrayList3 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(jsonArray2, 10));
                                                for (Iterator<JsonElement> it3 = jsonArray2.iterator(); it3.hasNext(); it3 = it3) {
                                                    arrayList3.add((n) ((JsonDecoder) decoder2).getJson().decodeFromJsonElement(n.a.f4057a, it3.next()));
                                                    contentOrNull5 = contentOrNull5;
                                                }
                                                String str4 = contentOrNull5;
                                                arrayList2.add(arrayList3);
                                                it = it2;
                                                contentOrNull6 = str3;
                                            }
                                            str = contentOrNull5;
                                            str2 = contentOrNull6;
                                            arrayList = arrayList2;
                                        }
                                        JsonElement jsonElement13 = (JsonElement) jsonObject2.get((Object) "moments_stats");
                                        b bVar = jsonElement13 == null ? null : (b) ((JsonDecoder) decoder2).getJson().decodeFromJsonElement(b.a.f3505a, jsonElement13);
                                        JsonElement jsonElement14 = (JsonElement) jsonObject2.get((Object) FirebaseAnalytics.Event.SHARE);
                                        ShareType shareType = jsonElement14 == null ? null : (ShareType) ((JsonDecoder) decoder2).getJson().decodeFromJsonElement(ShareType.ShareTypeDeserializer.serializer(), jsonElement14);
                                        if (storyType2 == StoryType.Video && (list = d0Var.f3639a) != null && !list.isEmpty()) {
                                            Iterator<T> it4 = list.iterator();
                                            while (true) {
                                                if (!it4.hasNext()) {
                                                    break;
                                                }
                                                b0 b0Var = (b0) it4.next();
                                                a0 a0Var = b0Var == null ? null : b0Var.f3615j;
                                                q0 q0Var = a0Var instanceof q0 ? (q0) a0Var : null;
                                                if ((q0Var == null ? null : q0Var.f4150e) == q0.d.Long) {
                                                    storyType = StoryType.LongVideo;
                                                    break;
                                                }
                                            }
                                        }
                                        storyType = storyType2;
                                        return new z(contentOrNull, d0Var, longValue, contentOrNull2, intValue, storyType, contentOrNull3, contentOrNull4, str, str2, shareType, longOrNull2, bVar, arrayList);
                                    }
                                    throw new Exception("No order found");
                                }
                                throw new Exception("No title found");
                            }
                            throw new Exception("No duration found");
                        }
                        throw new Exception("No media found");
                    }
                    throw new Exception("No story_id found");
                }
                throw new Exception("No jsonObject found");
            }
            throw new Exception("No JsonDecoder found");
        }

        @NotNull
        public SerialDescriptor getDescriptor() {
            return z.f4301v;
        }

        public void serialize(Encoder encoder, Object obj) {
            Intrinsics.checkNotNullParameter(encoder, "encoder");
            Intrinsics.checkNotNullParameter((z) obj, "value");
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0052, code lost:
        r2 = com.appsamurai.storyly.util.h.a().parse(r5);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public z(@org.jetbrains.annotations.NotNull java.lang.String r8, @org.jetbrains.annotations.NotNull com.appsamurai.storyly.data.d0 r9, long r10, @org.jetbrains.annotations.NotNull java.lang.String r12, int r13, @org.jetbrains.annotations.NotNull com.appsamurai.storyly.StoryType r14, @org.jetbrains.annotations.Nullable java.lang.String r15, @org.jetbrains.annotations.Nullable java.lang.String r16, @org.jetbrains.annotations.Nullable java.lang.String r17, @org.jetbrains.annotations.Nullable java.lang.String r18, @org.jetbrains.annotations.Nullable com.appsamurai.storyly.ShareType r19, @org.jetbrains.annotations.Nullable java.lang.Long r20, @org.jetbrains.annotations.Nullable com.appsamurai.storyly.analytics.b r21, @org.jetbrains.annotations.Nullable java.util.List<? extends java.util.List<com.appsamurai.storyly.data.n>> r22) {
        /*
            r7 = this;
            r0 = r7
            r1 = r8
            r2 = r9
            r3 = r12
            r4 = r14
            r5 = r18
            java.lang.String r6 = "storyId"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r8, r6)
            java.lang.String r6 = "media"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r9, r6)
            java.lang.String r6 = "title"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r12, r6)
            java.lang.String r6 = "type"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r14, r6)
            r7.<init>()
            r0.f4302a = r1
            r0.f4303b = r2
            r1 = r10
            r0.f4304c = r1
            r0.f4305d = r3
            r1 = r13
            r0.f4306e = r1
            r0.f4307f = r4
            r1 = r15
            r0.f4308g = r1
            r1 = r16
            r0.f4309h = r1
            r1 = r17
            r0.f4310i = r1
            r0.f4311j = r5
            r1 = r19
            r0.f4312k = r1
            r1 = r20
            r0.f4313l = r1
            r1 = r21
            r0.f4314m = r1
            r1 = r22
            r0.f4315n = r1
            r1 = 1
            r0.f4318q = r1
            r1 = 0
            if (r5 != 0) goto L_0x0052
            goto L_0x005c
        L_0x0052:
            java.text.SimpleDateFormat r2 = com.appsamurai.storyly.util.h.a()
            java.util.Date r2 = r2.parse(r5)
            if (r2 != 0) goto L_0x005e
        L_0x005c:
            r2 = r1
            goto L_0x0066
        L_0x005e:
            long r2 = r2.getTime()
            java.lang.Long r2 = java.lang.Long.valueOf(r2)
        L_0x0066:
            if (r2 != 0) goto L_0x0069
            goto L_0x006a
        L_0x0069:
            r1 = r2
        L_0x006a:
            r0.f4321t = r1
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.data.z.<init>(java.lang.String, com.appsamurai.storyly.data.d0, long, java.lang.String, int, com.appsamurai.storyly.StoryType, java.lang.String, java.lang.String, java.lang.String, java.lang.String, com.appsamurai.storyly.ShareType, java.lang.Long, com.appsamurai.storyly.analytics.b, java.util.List):void");
    }

    @NotNull
    public final Story a() {
        ArrayList arrayList;
        ArrayList arrayList2;
        List<b0> list = this.f4303b.f3639a;
        if (list == null) {
            arrayList = null;
        } else {
            ArrayList arrayList3 = new ArrayList();
            for (b0 b0Var : list) {
                a0 a0Var = b0Var == null ? null : b0Var.f3615j;
                n0 n0Var = a0Var instanceof n0 ? (n0) a0Var : null;
                String str = n0Var == null ? null : n0Var.f4063e;
                if (str == null) {
                    a0 a0Var2 = b0Var == null ? null : b0Var.f3615j;
                    p pVar = a0Var2 instanceof p ? (p) a0Var2 : null;
                    str = pVar == null ? null : pVar.f4102i;
                }
                if (str != null) {
                    arrayList3.add(str);
                }
            }
            arrayList = arrayList3;
        }
        String str2 = this.f4302a;
        String str3 = this.f4305d;
        String str4 = this.f4308g;
        int i3 = this.f4306e;
        boolean z2 = this.f4320s;
        long j2 = this.f4317p;
        StoryType storyType = this.f4307f;
        List<b0> list2 = this.f4303b.f3639a;
        if (list2 == null) {
            arrayList2 = null;
        } else {
            ArrayList arrayList4 = new ArrayList();
            for (b0 b0Var2 : list2) {
                StoryComponent a2 = b0Var2 == null ? null : b0Var2.f3615j.a(b0Var2);
                if (a2 != null) {
                    arrayList4.add(a2);
                }
            }
            arrayList2 = arrayList4;
        }
        return new Story(str2, str3, str4, i3, z2, j2, new StoryMedia(storyType, arrayList2, arrayList, this.f4303b.f3641c, this.f4310i));
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof z)) {
            return false;
        }
        z zVar = (z) obj;
        return Intrinsics.areEqual((Object) this.f4302a, (Object) zVar.f4302a) && Intrinsics.areEqual((Object) this.f4303b, (Object) zVar.f4303b) && this.f4304c == zVar.f4304c && Intrinsics.areEqual((Object) this.f4305d, (Object) zVar.f4305d) && this.f4306e == zVar.f4306e && this.f4307f == zVar.f4307f && Intrinsics.areEqual((Object) this.f4308g, (Object) zVar.f4308g) && Intrinsics.areEqual((Object) this.f4309h, (Object) zVar.f4309h) && Intrinsics.areEqual((Object) this.f4310i, (Object) zVar.f4310i) && Intrinsics.areEqual((Object) this.f4311j, (Object) zVar.f4311j) && this.f4312k == zVar.f4312k && Intrinsics.areEqual((Object) this.f4313l, (Object) zVar.f4313l) && Intrinsics.areEqual((Object) this.f4314m, (Object) zVar.f4314m) && Intrinsics.areEqual((Object) this.f4315n, (Object) zVar.f4315n);
    }

    public int hashCode() {
        int hashCode = (this.f4307f.hashCode() + androidx.compose.animation.core.a.c(this.f4306e, androidx.compose.animation.core.a.i(this.f4305d, androidx.compose.animation.core.a.D(this.f4304c, (this.f4303b.hashCode() + (this.f4302a.hashCode() * 31)) * 31, 31), 31), 31)) * 31;
        String str = this.f4308g;
        int i3 = 0;
        int hashCode2 = (hashCode + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.f4309h;
        int hashCode3 = (hashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.f4310i;
        int hashCode4 = (hashCode3 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.f4311j;
        int hashCode5 = (hashCode4 + (str4 == null ? 0 : str4.hashCode())) * 31;
        ShareType shareType = this.f4312k;
        int hashCode6 = (hashCode5 + (shareType == null ? 0 : shareType.hashCode())) * 31;
        Long l2 = this.f4313l;
        int hashCode7 = (hashCode6 + (l2 == null ? 0 : l2.hashCode())) * 31;
        b bVar = this.f4314m;
        int hashCode8 = (hashCode7 + (bVar == null ? 0 : bVar.hashCode())) * 31;
        List<? extends List<n>> list = this.f4315n;
        if (list != null) {
            i3 = list.hashCode();
        }
        return hashCode8 + i3;
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder("StorylyItem(storyId=");
        sb.append(this.f4302a);
        sb.append(", media=");
        sb.append(this.f4303b);
        sb.append(", duration=");
        sb.append(this.f4304c);
        sb.append(", title=");
        sb.append(this.f4305d);
        sb.append(", order=");
        sb.append(this.f4306e);
        sb.append(", type=");
        sb.append(this.f4307f);
        sb.append(", name=");
        sb.append(this.f4308g);
        sb.append(", altText=");
        sb.append(this.f4309h);
        sb.append(", previewPath=");
        sb.append(this.f4310i);
        sb.append(", endDate=");
        sb.append(this.f4311j);
        sb.append(", shareType=");
        sb.append(this.f4312k);
        sb.append(", createDate=");
        sb.append(this.f4313l);
        sb.append(", momentsStats=");
        sb.append(this.f4314m);
        sb.append(", conditions=");
        return androidx.compose.ui.autofill.a.k(sb, this.f4315n, ')');
    }
}
