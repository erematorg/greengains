package com.reown.sign.common.adapters;

import A.a;
import com.reown.sign.common.model.vo.clientsync.session.payload.SessionEventVO;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.JsonReader;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.internal.Util;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONObject;

@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\b\u0010\r\u001a\u00020\nH\u0016J\u0010\u0010\u000e\u001a\u00020\u00022\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J \u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00122\u000e\u0010\u0014\u001a\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u0015H\u0002J\u001c\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00172\n\u0010\u0019\u001a\u0006\u0012\u0002\b\u00030\u001aH\u0002J\u001a\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010\u0002H\u0016R\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0001X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u0001X\u0004¢\u0006\u0002\n\u0000¨\u0006 "}, d2 = {"Lcom/reown/sign/common/adapters/SessionEventVOJsonAdapter;", "Lcom/squareup/moshi/JsonAdapter;", "Lcom/reown/sign/common/model/vo/clientsync/session/payload/SessionEventVO;", "moshi", "Lcom/squareup/moshi/Moshi;", "<init>", "(Lcom/squareup/moshi/Moshi;)V", "options", "Lcom/squareup/moshi/JsonReader$Options;", "stringAdapter", "", "anyAdapter", "", "toString", "fromJson", "reader", "Lcom/squareup/moshi/JsonReader;", "upsertObject", "Lorg/json/JSONObject;", "rootObject", "paramsMap", "", "upsertArray", "Lorg/json/JSONArray;", "rootArray", "paramsList", "", "toJson", "", "writer", "Lcom/squareup/moshi/JsonWriter;", "value_", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nSessionEventVOJsonAdapter.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SessionEventVOJsonAdapter.kt\ncom/reown/sign/common/adapters/SessionEventVOJsonAdapter\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,143:1\n1869#2,2:144\n1869#2,2:146\n*S KotlinDebug\n*F\n+ 1 SessionEventVOJsonAdapter.kt\ncom/reown/sign/common/adapters/SessionEventVOJsonAdapter\n*L\n68#1:144,2\n90#1:146,2\n*E\n"})
public final class SessionEventVOJsonAdapter extends JsonAdapter<SessionEventVO> {
    @NotNull
    private final JsonAdapter<Object> anyAdapter;
    @NotNull
    private final JsonReader.Options options;
    @NotNull
    private final JsonAdapter<String> stringAdapter;

    public SessionEventVOJsonAdapter(@NotNull Moshi moshi) {
        Intrinsics.checkNotNullParameter(moshi, "moshi");
        JsonReader.Options of = JsonReader.Options.of("name", "data");
        Intrinsics.checkNotNullExpressionValue(of, "of(...)");
        this.options = of;
        this.stringAdapter = a.h(moshi, String.class, "name", "adapter(...)");
        this.anyAdapter = a.h(moshi, Object.class, "data", "adapter(...)");
    }

    private final JSONArray upsertArray(JSONArray jSONArray, List<?> list) {
        for (Object next : list) {
            if (next instanceof List) {
                jSONArray.put(upsertArray(new JSONArray(), (List) next));
            } else if (next instanceof Map) {
                jSONArray.put(upsertObject(new JSONObject(), (Map) next));
            } else if (next instanceof String) {
                try {
                    Object fromJson = this.anyAdapter.fromJson((String) next);
                    if (fromJson instanceof List) {
                        jSONArray.put(upsertArray(new JSONArray(), (List) fromJson));
                    } else if (fromJson instanceof Map) {
                        jSONArray.put(upsertObject(new JSONObject(), (Map) fromJson));
                    } else if (fromJson instanceof Number) {
                        jSONArray.put(((String) next).toString());
                    } else {
                        throw new IllegalArgumentException("Failed Deserializing Unknown Type " + next);
                    }
                } catch (Exception unused) {
                    jSONArray.put(next);
                }
            } else if (next instanceof Number) {
                Number number = (Number) next;
                jSONArray.put(number.doubleValue() % ((double) 1) == 0.0d ? Long.valueOf(number.longValue()) : Double.valueOf(number.doubleValue()));
            } else {
                if (next == null) {
                    next = JSONObject.NULL;
                }
                jSONArray.put(next);
            }
        }
        return jSONArray;
    }

    private final JSONObject upsertObject(JSONObject jSONObject, Map<?, ?> map) {
        Intrinsics.checkNotNull(map, "null cannot be cast to non-null type kotlin.collections.Map<kotlin.String, kotlin.Any?>");
        for (Map.Entry entry : map.entrySet()) {
            String str = (String) entry.getKey();
            Object value = entry.getValue();
            if (value instanceof List) {
                jSONObject.putOpt(str, upsertArray(new JSONArray(), (List) value));
            } else if (value instanceof Map) {
                jSONObject.putOpt(str, upsertObject(new JSONObject(), (Map) value));
            } else if (value instanceof Number) {
                Number number = (Number) value;
                jSONObject.put(str, number.doubleValue() % ((double) 1) == 0.0d ? Long.valueOf(number.longValue()) : Double.valueOf(number.doubleValue()));
            } else {
                if (value == null) {
                    value = JSONObject.NULL;
                }
                jSONObject.putOpt(str, value);
            }
        }
        return jSONObject;
    }

    @NotNull
    public String toString() {
        return a.j(38, "GeneratedJsonAdapter(SessionEventVO)");
    }

    @NotNull
    public SessionEventVO fromJson(@NotNull JsonReader jsonReader) {
        Object obj;
        Intrinsics.checkNotNullParameter(jsonReader, "reader");
        jsonReader.beginObject();
        String str = null;
        String str2 = null;
        while (jsonReader.hasNext()) {
            int selectName = jsonReader.selectName(this.options);
            if (selectName == -1) {
                jsonReader.skipName();
                jsonReader.skipValue();
            } else if (selectName == 0) {
                str = this.stringAdapter.fromJson(jsonReader);
                if (str == null) {
                    throw Util.unexpectedNull("name", "name", jsonReader);
                }
            } else if (selectName == 1) {
                Object fromJson = this.anyAdapter.fromJson(jsonReader);
                if (fromJson == null) {
                    throw Util.unexpectedNull("data", "data", jsonReader);
                } else if (fromJson instanceof List) {
                    str2 = upsertArray(new JSONArray(), (List) fromJson).toString();
                } else if (fromJson instanceof Map) {
                    str2 = upsertObject(new JSONObject(), (Map) fromJson).toString();
                } else if (fromJson instanceof Number) {
                    Number number = (Number) fromJson;
                    if (number.doubleValue() % ((double) 1) == 0.0d) {
                        obj = Long.valueOf(number.longValue());
                    } else {
                        obj = Double.valueOf(number.doubleValue());
                    }
                    str2 = obj.toString();
                } else {
                    str2 = fromJson.toString();
                }
            } else {
                continue;
            }
        }
        jsonReader.endObject();
        if (str == null) {
            throw Util.missingProperty("name", "name", jsonReader);
        } else if (str2 != null) {
            return new SessionEventVO(str, str2);
        } else {
            throw Util.missingProperty("data", "data", jsonReader);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x004f, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0050, code lost:
        kotlin.io.CloseableKt.closeFinally(r1, r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0053, code lost:
        throw r4;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void toJson(@org.jetbrains.annotations.NotNull com.squareup.moshi.JsonWriter r4, @org.jetbrains.annotations.Nullable com.reown.sign.common.model.vo.clientsync.session.payload.SessionEventVO r5) {
        /*
            r3 = this;
            java.lang.String r0 = "\\\""
            java.lang.String r1 = "writer"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r1)
            if (r5 == 0) goto L_0x0054
            r4.beginObject()
            java.lang.String r1 = "name"
            r4.name(r1)
            com.squareup.moshi.JsonAdapter<java.lang.String> r1 = r3.stringAdapter
            java.lang.String r2 = r5.getName()
            r1.toJson((com.squareup.moshi.JsonWriter) r4, r2)
            java.lang.String r1 = "data"
            r4.name(r1)
            okio.BufferedSink r1 = r4.valueSink()
            com.squareup.moshi.JsonAdapter<java.lang.Object> r3 = r3.anyAdapter     // Catch:{ all -> 0x004d }
            java.lang.Object r5 = r5.getData()     // Catch:{ all -> 0x004d }
            java.lang.String r3 = r3.toJson(r5)     // Catch:{ all -> 0x004d }
            java.lang.String r5 = "toJson(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r3, r5)     // Catch:{ all -> 0x004d }
            java.lang.String r3 = kotlin.text.StringsKt__StringsKt.removeSurrounding((java.lang.String) r3, (java.lang.CharSequence) "\"")     // Catch:{ all -> 0x004d }
            java.lang.String r5 = "\""
            java.lang.String r3 = kotlin.text.StringsKt__StringsJVMKt.replace$default((java.lang.String) r3, (java.lang.String) r0, (java.lang.String) r5, false, 4, (java.lang.Object) null)     // Catch:{ all -> 0x004d }
            java.lang.String r5 = "\\\\\""
            java.lang.String r3 = kotlin.text.StringsKt__StringsJVMKt.replace$default((java.lang.String) r3, (java.lang.String) r5, (java.lang.String) r0, false, 4, (java.lang.Object) null)     // Catch:{ all -> 0x004d }
            r1.writeUtf8(r3)     // Catch:{ all -> 0x004d }
            r3 = 0
            kotlin.io.CloseableKt.closeFinally(r1, r3)
            r4.endObject()
            return
        L_0x004d:
            r3 = move-exception
            throw r3     // Catch:{ all -> 0x004f }
        L_0x004f:
            r4 = move-exception
            kotlin.io.CloseableKt.closeFinally(r1, r3)
            throw r4
        L_0x0054:
            java.lang.NullPointerException r3 = new java.lang.NullPointerException
            java.lang.String r4 = "value_ was null! Wrap in .nullSafe() to write nullable values."
            r3.<init>(r4)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.reown.sign.common.adapters.SessionEventVOJsonAdapter.toJson(com.squareup.moshi.JsonWriter, com.reown.sign.common.model.vo.clientsync.session.payload.SessionEventVO):void");
    }
}
