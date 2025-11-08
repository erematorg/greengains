package com.reown.sign.common.adapters;

import A.a;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.reown.sign.common.model.vo.clientsync.session.payload.SessionRequestVO;
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

@Metadata(d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\b\u0010\u000f\u001a\u00020\nH\u0016J\u0010\u0010\u0010\u001a\u00020\u00022\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J \u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00142\u000e\u0010\u0016\u001a\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u0017H\u0002J\u001c\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u00192\n\u0010\u001b\u001a\u0006\u0012\u0002\b\u00030\u001cH\u0002J\u001a\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 2\b\u0010!\u001a\u0004\u0018\u00010\u0002H\u0016R\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0001X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u0001X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0001X\u0004¢\u0006\u0002\n\u0000¨\u0006\""}, d2 = {"Lcom/reown/sign/common/adapters/SessionRequestVOJsonAdapter;", "Lcom/squareup/moshi/JsonAdapter;", "Lcom/reown/sign/common/model/vo/clientsync/session/payload/SessionRequestVO;", "moshi", "Lcom/squareup/moshi/Moshi;", "<init>", "(Lcom/squareup/moshi/Moshi;)V", "options", "Lcom/squareup/moshi/JsonReader$Options;", "stringAdapter", "", "anyAdapter", "", "longAdapter", "", "toString", "fromJson", "reader", "Lcom/squareup/moshi/JsonReader;", "processObject", "Lorg/json/JSONObject;", "rootObject", "paramsMap", "", "upsertArray", "Lorg/json/JSONArray;", "rootArray", "paramsList", "", "toJson", "", "writer", "Lcom/squareup/moshi/JsonWriter;", "value_", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nSessionRequestVOJsonAdapter.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SessionRequestVOJsonAdapter.kt\ncom/reown/sign/common/adapters/SessionRequestVOJsonAdapter\n+ 2 _Maps.kt\nkotlin/collections/MapsKt___MapsKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,140:1\n216#2,2:141\n1869#3,2:143\n*S KotlinDebug\n*F\n+ 1 SessionRequestVOJsonAdapter.kt\ncom/reown/sign/common/adapters/SessionRequestVOJsonAdapter\n*L\n65#1:141,2\n83#1:143,2\n*E\n"})
public final class SessionRequestVOJsonAdapter extends JsonAdapter<SessionRequestVO> {
    @NotNull
    private final JsonAdapter<Object> anyAdapter;
    @NotNull
    private final JsonAdapter<Long> longAdapter;
    @NotNull
    private final JsonReader.Options options;
    @NotNull
    private final JsonAdapter<String> stringAdapter;

    public SessionRequestVOJsonAdapter(@NotNull Moshi moshi) {
        Intrinsics.checkNotNullParameter(moshi, "moshi");
        JsonReader.Options of = JsonReader.Options.of(FirebaseAnalytics.Param.METHOD, "params", "expiryTimestamp");
        Intrinsics.checkNotNullExpressionValue(of, "of(...)");
        this.options = of;
        this.stringAdapter = a.h(moshi, String.class, FirebaseAnalytics.Param.METHOD, "adapter(...)");
        this.anyAdapter = a.h(moshi, Object.class, "params", "adapter(...)");
        this.longAdapter = a.h(moshi, Long.TYPE, "expiryTimestamp", "adapter(...)");
    }

    private final JSONObject processObject(JSONObject jSONObject, Map<?, ?> map) {
        Intrinsics.checkNotNull(map, "null cannot be cast to non-null type kotlin.collections.Map<kotlin.String, kotlin.Any?>");
        for (Map.Entry next : map.entrySet()) {
            String str = (String) next.getKey();
            Object value = next.getValue();
            if (value instanceof List) {
                value = upsertArray(new JSONArray(), (List) value);
            } else if (value instanceof Map) {
                value = processObject(new JSONObject(), (Map) value);
            } else if (value instanceof Number) {
                Number number = (Number) value;
                value = number.doubleValue() % ((double) 1) == 0.0d ? Long.valueOf(number.longValue()) : Double.valueOf(number.doubleValue());
            } else if (value == null) {
                value = JSONObject.NULL;
            }
            jSONObject.putOpt(str, value);
        }
        return jSONObject;
    }

    private final JSONArray upsertArray(JSONArray jSONArray, List<?> list) {
        for (Object next : list) {
            if (next instanceof List) {
                jSONArray.put(upsertArray(new JSONArray(), (List) next));
            } else if (next instanceof Map) {
                jSONArray.put(processObject(new JSONObject(), (Map) next));
            } else if (next instanceof String) {
                try {
                    Object fromJson = this.anyAdapter.fromJson((String) next);
                    if (fromJson instanceof List) {
                        jSONArray.put(upsertArray(new JSONArray(), (List) fromJson));
                    } else if (fromJson instanceof Map) {
                        jSONArray.put(processObject(new JSONObject(), (Map) fromJson));
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

    @NotNull
    public String toString() {
        return a.j(38, "GeneratedJsonAdapter(SessionRequestVO)");
    }

    @NotNull
    public SessionRequestVO fromJson(@NotNull JsonReader jsonReader) {
        Intrinsics.checkNotNullParameter(jsonReader, "reader");
        jsonReader.beginObject();
        String str = null;
        String str2 = null;
        Long l2 = null;
        while (jsonReader.hasNext()) {
            int selectName = jsonReader.selectName(this.options);
            if (selectName == -1) {
                jsonReader.skipName();
                jsonReader.skipValue();
            } else if (selectName == 0) {
                str = this.stringAdapter.fromJson(jsonReader);
                if (str == null) {
                    throw Util.unexpectedNull(FirebaseAnalytics.Param.METHOD, FirebaseAnalytics.Param.METHOD, jsonReader);
                }
            } else if (selectName == 1) {
                Object fromJson = this.anyAdapter.fromJson(jsonReader);
                if (fromJson == null) {
                    throw Util.unexpectedNull("params", "params", jsonReader);
                } else if (fromJson instanceof List) {
                    str2 = upsertArray(new JSONArray(), (List) fromJson).toString();
                } else {
                    str2 = processObject(new JSONObject(), (Map) fromJson).toString();
                }
            } else if (selectName == 2 && (l2 = this.longAdapter.fromJson(jsonReader)) == null) {
                throw Util.unexpectedNull("expiryTimestamp", "expiryTimestamp", jsonReader);
            }
        }
        jsonReader.endObject();
        if (str == null) {
            throw Util.missingProperty(FirebaseAnalytics.Param.METHOD, FirebaseAnalytics.Param.METHOD, jsonReader);
        } else if (str2 != null) {
            return new SessionRequestVO(str, str2, l2);
        } else {
            throw Util.missingProperty("params", "params", jsonReader);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0063, code lost:
        r5 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0064, code lost:
        kotlin.io.CloseableKt.closeFinally(r1, r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0067, code lost:
        throw r5;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void toJson(@org.jetbrains.annotations.NotNull com.squareup.moshi.JsonWriter r5, @org.jetbrains.annotations.Nullable com.reown.sign.common.model.vo.clientsync.session.payload.SessionRequestVO r6) {
        /*
            r4 = this;
            java.lang.String r0 = "\\\""
            java.lang.String r1 = "writer"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r1)
            if (r6 == 0) goto L_0x0068
            r5.beginObject()
            java.lang.String r1 = "method"
            r5.name(r1)
            com.squareup.moshi.JsonAdapter<java.lang.String> r1 = r4.stringAdapter
            java.lang.String r2 = r6.getMethod()
            r1.toJson((com.squareup.moshi.JsonWriter) r5, r2)
            java.lang.String r1 = "params"
            r5.name(r1)
            okio.BufferedSink r1 = r5.valueSink()
            com.squareup.moshi.JsonAdapter<java.lang.Object> r2 = r4.anyAdapter     // Catch:{ all -> 0x0061 }
            java.lang.String r3 = r6.getParams()     // Catch:{ all -> 0x0061 }
            java.lang.String r2 = r2.toJson(r3)     // Catch:{ all -> 0x0061 }
            java.lang.String r3 = "toJson(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r3)     // Catch:{ all -> 0x0061 }
            java.lang.String r2 = kotlin.text.StringsKt__StringsKt.removeSurrounding((java.lang.String) r2, (java.lang.CharSequence) "\"")     // Catch:{ all -> 0x0061 }
            java.lang.String r3 = "\""
            java.lang.String r2 = kotlin.text.StringsKt__StringsJVMKt.replace$default((java.lang.String) r2, (java.lang.String) r0, (java.lang.String) r3, false, 4, (java.lang.Object) null)     // Catch:{ all -> 0x0061 }
            java.lang.String r3 = "\\\\\""
            java.lang.String r0 = kotlin.text.StringsKt__StringsJVMKt.replace$default((java.lang.String) r2, (java.lang.String) r3, (java.lang.String) r0, false, 4, (java.lang.Object) null)     // Catch:{ all -> 0x0061 }
            r1.writeUtf8(r0)     // Catch:{ all -> 0x0061 }
            r0 = 0
            kotlin.io.CloseableKt.closeFinally(r1, r0)
            java.lang.Long r0 = r6.getExpiryTimestamp()
            if (r0 == 0) goto L_0x005d
            java.lang.String r0 = "expiryTimestamp"
            r5.name(r0)
            com.squareup.moshi.JsonAdapter<java.lang.Long> r4 = r4.longAdapter
            java.lang.Long r6 = r6.getExpiryTimestamp()
            r4.toJson((com.squareup.moshi.JsonWriter) r5, r6)
        L_0x005d:
            r5.endObject()
            return
        L_0x0061:
            r4 = move-exception
            throw r4     // Catch:{ all -> 0x0063 }
        L_0x0063:
            r5 = move-exception
            kotlin.io.CloseableKt.closeFinally(r1, r4)
            throw r5
        L_0x0068:
            java.lang.NullPointerException r4 = new java.lang.NullPointerException
            java.lang.String r5 = "value_ was null! Wrap in .nullSafe() to write nullable values."
            r4.<init>(r5)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.reown.sign.common.adapters.SessionRequestVOJsonAdapter.toJson(com.squareup.moshi.JsonWriter, com.reown.sign.common.model.vo.clientsync.session.payload.SessionRequestVO):void");
    }
}
