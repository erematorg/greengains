package com.appsamurai.storyly.data;

import com.appsamurai.storyly.StoryGroupAnimation;
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
import kotlinx.serialization.json.JsonNull;
import kotlinx.serialization.json.JsonObject;
import kotlinx.serialization.json.JsonPrimitive;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Serializable(with = a.class)
public final class m0 {
    @NotNull

    /* renamed from: l  reason: collision with root package name */
    public static final a f3860l = new a();
    @NotNull

    /* renamed from: m  reason: collision with root package name */
    public static final SerialDescriptor f3861m = SerialDescriptorsKt.PrimitiveSerialDescriptor("StorylyStyle", PrimitiveKind.STRING.INSTANCE);
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public final List<Integer> f3862a;
    @Nullable

    /* renamed from: b  reason: collision with root package name */
    public final List<Integer> f3863b;
    @Nullable

    /* renamed from: c  reason: collision with root package name */
    public final Integer f3864c;
    @Nullable

    /* renamed from: d  reason: collision with root package name */
    public final Integer f3865d;
    @Nullable

    /* renamed from: e  reason: collision with root package name */
    public final Integer f3866e;
    @Nullable

    /* renamed from: f  reason: collision with root package name */
    public final StoryGroupAnimation f3867f;
    @Nullable

    /* renamed from: g  reason: collision with root package name */
    public final Integer f3868g;
    @Nullable

    /* renamed from: h  reason: collision with root package name */
    public final Integer f3869h;
    @Nullable

    /* renamed from: i  reason: collision with root package name */
    public final Boolean f3870i;
    @Nullable

    /* renamed from: j  reason: collision with root package name */
    public final Boolean f3871j;
    @Nullable

    /* renamed from: k  reason: collision with root package name */
    public final Boolean f3872k;

    public static final class a implements KSerializer<m0> {
        public Object deserialize(Decoder decoder) {
            ArrayList arrayList;
            ArrayList arrayList2;
            StoryGroupAnimation storyGroupAnimation;
            JsonPrimitive jsonPrimitive;
            JsonPrimitive jsonPrimitive2;
            JsonPrimitive jsonPrimitive3;
            JsonPrimitive jsonPrimitive4;
            JsonPrimitive jsonPrimitive5;
            JsonPrimitive jsonPrimitive6;
            Boolean booleanOrNull;
            JsonPrimitive jsonPrimitive7;
            JsonPrimitive jsonPrimitive8;
            JsonPrimitive jsonPrimitive9;
            JsonArray jsonArray;
            JsonArray jsonArray2;
            Decoder decoder2 = decoder;
            Intrinsics.checkNotNullParameter(decoder2, "decoder");
            Boolean bool = null;
            JsonDecoder jsonDecoder = decoder2 instanceof JsonDecoder ? (JsonDecoder) decoder2 : null;
            if (jsonDecoder != null) {
                Intrinsics.checkNotNullParameter(jsonDecoder, "<this>");
                JsonElement decodeJsonElement = jsonDecoder.decodeJsonElement();
                JsonObject jsonObject = decodeJsonElement instanceof JsonNull ? null : JsonElementKt.getJsonObject(decodeJsonElement);
                if (jsonObject == null) {
                    return null;
                }
                JsonElement jsonElement = (JsonElement) jsonObject.get((Object) "sg_border_unseen");
                if (jsonElement == null || (jsonArray2 = JsonElementKt.getJsonArray(jsonElement)) == null) {
                    arrayList = null;
                } else {
                    ArrayList arrayList3 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(jsonArray2, 10));
                    Iterator<JsonElement> it = jsonArray2.iterator();
                    while (it.hasNext()) {
                        arrayList3.add(Integer.valueOf(((c) ((JsonDecoder) decoder2).getJson().decodeFromJsonElement(c.f3622b, it.next())).f3624a));
                    }
                    arrayList = arrayList3;
                }
                JsonElement jsonElement2 = (JsonElement) jsonObject.get((Object) "sg_border_seen");
                if (jsonElement2 == null || (jsonArray = JsonElementKt.getJsonArray(jsonElement2)) == null) {
                    arrayList2 = null;
                } else {
                    ArrayList arrayList4 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(jsonArray, 10));
                    Iterator<JsonElement> it2 = jsonArray.iterator();
                    while (it2.hasNext()) {
                        arrayList4.add(Integer.valueOf(((c) ((JsonDecoder) decoder2).getJson().decodeFromJsonElement(c.f3622b, it2.next())).f3624a));
                    }
                    arrayList2 = arrayList4;
                }
                JsonElement jsonElement3 = (JsonElement) jsonObject.get((Object) "sg_text_unseen");
                Integer valueOf = (jsonElement3 == null || (jsonPrimitive9 = JsonElementKt.getJsonPrimitive(jsonElement3)) == null) ? null : Integer.valueOf(((c) ((JsonDecoder) decoder2).getJson().decodeFromJsonElement(c.f3622b, jsonPrimitive9)).f3624a);
                JsonElement jsonElement4 = (JsonElement) jsonObject.get((Object) "sg_text_seen");
                Integer valueOf2 = (jsonElement4 == null || (jsonPrimitive8 = JsonElementKt.getJsonPrimitive(jsonElement4)) == null) ? null : Integer.valueOf(((c) ((JsonDecoder) decoder2).getJson().decodeFromJsonElement(c.f3622b, jsonPrimitive8)).f3624a);
                JsonElement jsonElement5 = (JsonElement) jsonObject.get((Object) "pin_bg");
                Integer valueOf3 = (jsonElement5 == null || (jsonPrimitive7 = JsonElementKt.getJsonPrimitive(jsonElement5)) == null) ? null : Integer.valueOf(((c) ((JsonDecoder) decoder2).getJson().decodeFromJsonElement(c.f3622b, jsonPrimitive7)).f3624a);
                JsonElement jsonElement6 = (JsonElement) jsonObject.get((Object) "animation");
                if (jsonElement6 == null || (jsonPrimitive6 = JsonElementKt.getJsonPrimitive(jsonElement6)) == null || (booleanOrNull = JsonElementKt.getBooleanOrNull(jsonPrimitive6)) == null) {
                    storyGroupAnimation = null;
                } else {
                    storyGroupAnimation = booleanOrNull.booleanValue() ? StoryGroupAnimation.BorderRotation : StoryGroupAnimation.Disabled;
                }
                JsonElement jsonElement7 = (JsonElement) jsonObject.get((Object) "progress_bg");
                Integer valueOf4 = (jsonElement7 == null || (jsonPrimitive5 = JsonElementKt.getJsonPrimitive(jsonElement7)) == null) ? null : Integer.valueOf(((c) ((JsonDecoder) decoder2).getJson().decodeFromJsonElement(c.f3622b, jsonPrimitive5)).f3624a);
                JsonElement jsonElement8 = (JsonElement) jsonObject.get((Object) "progress_fill");
                Integer valueOf5 = (jsonElement8 == null || (jsonPrimitive4 = JsonElementKt.getJsonPrimitive(jsonElement8)) == null) ? null : Integer.valueOf(((c) ((JsonDecoder) decoder2).getJson().decodeFromJsonElement(c.f3622b, jsonPrimitive4)).f3624a);
                JsonElement jsonElement9 = (JsonElement) jsonObject.get((Object) "story_title_visible");
                Boolean booleanOrNull2 = (jsonElement9 == null || (jsonPrimitive3 = JsonElementKt.getJsonPrimitive(jsonElement9)) == null) ? null : JsonElementKt.getBooleanOrNull(jsonPrimitive3);
                JsonElement jsonElement10 = (JsonElement) jsonObject.get((Object) "story_cover_visible");
                Boolean booleanOrNull3 = (jsonElement10 == null || (jsonPrimitive2 = JsonElementKt.getJsonPrimitive(jsonElement10)) == null) ? null : JsonElementKt.getBooleanOrNull(jsonPrimitive2);
                JsonElement jsonElement11 = (JsonElement) jsonObject.get((Object) "story_close_visible");
                if (!(jsonElement11 == null || (jsonPrimitive = JsonElementKt.getJsonPrimitive(jsonElement11)) == null)) {
                    bool = JsonElementKt.getBooleanOrNull(jsonPrimitive);
                }
                return new m0(arrayList, arrayList2, valueOf, valueOf2, valueOf3, storyGroupAnimation, valueOf4, valueOf5, booleanOrNull2, booleanOrNull3, bool);
            }
            throw new Exception("StorylyStyle Deserialize Exception: No JsonDecoder found");
        }

        @NotNull
        public SerialDescriptor getDescriptor() {
            return m0.f3861m;
        }

        public void serialize(Encoder encoder, Object obj) {
            m0 m0Var = (m0) obj;
            Intrinsics.checkNotNullParameter(encoder, "encoder");
        }
    }

    public m0() {
        this((List) null, (List) null, (Integer) null, (Integer) null, (Integer) null, (StoryGroupAnimation) null, (Integer) null, (Integer) null, (Boolean) null, (Boolean) null, (Boolean) null, 2047);
    }

    @Nullable
    public final StoryGroupAnimation a() {
        return this.f3867f;
    }

    @Nullable
    public final Boolean b() {
        return this.f3872k;
    }

    @Nullable
    public final Boolean c() {
        return this.f3871j;
    }

    @Nullable
    public final Boolean d() {
        return this.f3870i;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof m0)) {
            return false;
        }
        m0 m0Var = (m0) obj;
        return Intrinsics.areEqual((Object) this.f3862a, (Object) m0Var.f3862a) && Intrinsics.areEqual((Object) this.f3863b, (Object) m0Var.f3863b) && Intrinsics.areEqual((Object) this.f3864c, (Object) m0Var.f3864c) && Intrinsics.areEqual((Object) this.f3865d, (Object) m0Var.f3865d) && Intrinsics.areEqual((Object) this.f3866e, (Object) m0Var.f3866e) && this.f3867f == m0Var.f3867f && Intrinsics.areEqual((Object) this.f3868g, (Object) m0Var.f3868g) && Intrinsics.areEqual((Object) this.f3869h, (Object) m0Var.f3869h) && Intrinsics.areEqual((Object) this.f3870i, (Object) m0Var.f3870i) && Intrinsics.areEqual((Object) this.f3871j, (Object) m0Var.f3871j) && Intrinsics.areEqual((Object) this.f3872k, (Object) m0Var.f3872k);
    }

    public int hashCode() {
        List<Integer> list = this.f3862a;
        int i3 = 0;
        int hashCode = (list == null ? 0 : list.hashCode()) * 31;
        List<Integer> list2 = this.f3863b;
        int hashCode2 = (hashCode + (list2 == null ? 0 : list2.hashCode())) * 31;
        Integer num = this.f3864c;
        int hashCode3 = (hashCode2 + (num == null ? 0 : num.hashCode())) * 31;
        Integer num2 = this.f3865d;
        int hashCode4 = (hashCode3 + (num2 == null ? 0 : num2.hashCode())) * 31;
        Integer num3 = this.f3866e;
        int hashCode5 = (hashCode4 + (num3 == null ? 0 : num3.hashCode())) * 31;
        StoryGroupAnimation storyGroupAnimation = this.f3867f;
        int hashCode6 = (hashCode5 + (storyGroupAnimation == null ? 0 : storyGroupAnimation.hashCode())) * 31;
        Integer num4 = this.f3868g;
        int hashCode7 = (hashCode6 + (num4 == null ? 0 : num4.hashCode())) * 31;
        Integer num5 = this.f3869h;
        int hashCode8 = (hashCode7 + (num5 == null ? 0 : num5.hashCode())) * 31;
        Boolean bool = this.f3870i;
        int hashCode9 = (hashCode8 + (bool == null ? 0 : bool.hashCode())) * 31;
        Boolean bool2 = this.f3871j;
        int hashCode10 = (hashCode9 + (bool2 == null ? 0 : bool2.hashCode())) * 31;
        Boolean bool3 = this.f3872k;
        if (bool3 != null) {
            i3 = bool3.hashCode();
        }
        return hashCode10 + i3;
    }

    @NotNull
    public String toString() {
        return "StorylyStyle(borderUnseenColors=" + this.f3862a + ", borderSeenColors=" + this.f3863b + ", textUnseenColor=" + this.f3864c + ", textSeenColor=" + this.f3865d + ", pinBackgroundColor=" + this.f3866e + ", animation=" + this.f3867f + ", progressBackgroundColor=" + this.f3868g + ", progressFillColor=" + this.f3869h + ", storyTitleIsVisible=" + this.f3870i + ", storyCoverIsVisible=" + this.f3871j + ", storyCloseIsVisible=" + this.f3872k + ')';
    }

    public m0(@Nullable List<Integer> list, @Nullable List<Integer> list2, @Nullable Integer num, @Nullable Integer num2, @Nullable Integer num3, @Nullable StoryGroupAnimation storyGroupAnimation, @Nullable Integer num4, @Nullable Integer num5, @Nullable Boolean bool, @Nullable Boolean bool2, @Nullable Boolean bool3) {
        this.f3862a = list;
        this.f3863b = list2;
        this.f3864c = num;
        this.f3865d = num2;
        this.f3866e = num3;
        this.f3867f = storyGroupAnimation;
        this.f3868g = num4;
        this.f3869h = num5;
        this.f3870i = bool;
        this.f3871j = bool2;
        this.f3872k = bool3;
    }

    public /* synthetic */ m0(List list, List list2, Integer num, Integer num2, Integer num3, StoryGroupAnimation storyGroupAnimation, Integer num4, Integer num5, Boolean bool, Boolean bool2, Boolean bool3, int i3) {
        this((List<Integer>) null, (List<Integer>) null, (Integer) null, (Integer) null, (Integer) null, (StoryGroupAnimation) null, (Integer) null, (Integer) null, (Boolean) null, (Boolean) null, (Boolean) null);
    }
}
