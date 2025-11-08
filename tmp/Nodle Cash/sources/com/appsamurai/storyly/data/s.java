package com.appsamurai.storyly.data;

import com.appsamurai.storyly.data.a;
import com.appsamurai.storyly.data.p0;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.collections.CollectionsKt;
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
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Serializable(with = a.class)
public final class s {
    @NotNull

    /* renamed from: f  reason: collision with root package name */
    public static final a f4195f = new a();
    @NotNull

    /* renamed from: g  reason: collision with root package name */
    public static final SerialDescriptor f4196g = SerialDescriptorsKt.PrimitiveSerialDescriptor("StorylyData", PrimitiveKind.STRING.INSTANCE);
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public List<v> f4197a;
    @Nullable

    /* renamed from: b  reason: collision with root package name */
    public final a f4198b;
    @Nullable

    /* renamed from: c  reason: collision with root package name */
    public final p0 f4199c;
    @Nullable

    /* renamed from: d  reason: collision with root package name */
    public final Map<Integer, Exception> f4200d;
    @Nullable

    /* renamed from: e  reason: collision with root package name */
    public final m0 f4201e;

    public static final class a implements KSerializer<s> {
        public Object deserialize(Decoder decoder) {
            ArrayList arrayList;
            JsonArray jsonArray;
            v vVar;
            Intrinsics.checkNotNullParameter(decoder, "decoder");
            JsonDecoder jsonDecoder = decoder instanceof JsonDecoder ? (JsonDecoder) decoder : null;
            if (jsonDecoder != null) {
                JsonObject jsonObject = JsonElementKt.getJsonObject(jsonDecoder.decodeJsonElement());
                if (jsonObject == null) {
                    jsonObject = null;
                }
                if (jsonObject != null) {
                    LinkedHashMap linkedHashMap = new LinkedHashMap();
                    JsonElement jsonElement = (JsonElement) jsonObject.get((Object) "story_groups");
                    if (jsonElement == null || (jsonArray = JsonElementKt.getJsonArray(jsonElement)) == null) {
                        arrayList = null;
                    } else {
                        ArrayList arrayList2 = new ArrayList();
                        Iterator<JsonElement> it = jsonArray.iterator();
                        int i3 = 0;
                        while (it.hasNext()) {
                            JsonElement next = it.next();
                            int i4 = i3 + 1;
                            if (i3 < 0) {
                                CollectionsKt.throwIndexOverflow();
                            }
                            try {
                                vVar = (v) ((JsonDecoder) decoder).getJson().decodeFromJsonElement(v.f4220z, next);
                            } catch (Exception e3) {
                                linkedHashMap.put(Integer.valueOf(i3), e3);
                                vVar = null;
                            }
                            if (vVar != null) {
                                arrayList2.add(vVar);
                            }
                            i3 = i4;
                        }
                        arrayList = arrayList2;
                    }
                    if (arrayList != null) {
                        JsonElement jsonElement2 = (JsonElement) jsonObject.get((Object) TtmlNode.TAG_STYLE);
                        m0 m0Var = jsonElement2 == null ? null : (m0) ((JsonDecoder) decoder).getJson().decodeFromJsonElement(m0.f3860l, jsonElement2);
                        JsonElement jsonElement3 = (JsonElement) jsonObject.get((Object) "ad");
                        a aVar = jsonElement3 == null ? null : (a) ((JsonDecoder) decoder).getJson().decodeFromJsonElement(a.C0007a.f3595a, jsonElement3);
                        JsonElement jsonElement4 = (JsonElement) jsonObject.get((Object) "user");
                        return new s(arrayList, aVar, jsonElement4 == null ? null : (p0) ((JsonDecoder) decoder).getJson().decodeFromJsonElement(p0.a.f4118a, jsonElement4), linkedHashMap.isEmpty() ? null : linkedHashMap, m0Var);
                    }
                    throw new Exception("No Story Group");
                }
                throw new Exception("No jsonObject found");
            }
            throw new Exception("No JsonDecoder found");
        }

        @NotNull
        public SerialDescriptor getDescriptor() {
            return s.f4196g;
        }

        public void serialize(Encoder encoder, Object obj) {
            Intrinsics.checkNotNullParameter(encoder, "encoder");
            Intrinsics.checkNotNullParameter((s) obj, "value");
        }
    }

    public s(@NotNull List<v> list, @Nullable a aVar, @Nullable p0 p0Var, @Nullable Map<Integer, ? extends Exception> map, @Nullable m0 m0Var) {
        Intrinsics.checkNotNullParameter(list, "groupItems");
        this.f4197a = list;
        this.f4198b = aVar;
        this.f4199c = p0Var;
        this.f4200d = map;
        this.f4201e = m0Var;
    }

    public final void a(@NotNull List<v> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.f4197a = list;
    }
}
