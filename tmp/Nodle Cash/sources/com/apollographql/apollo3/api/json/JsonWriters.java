package com.apollographql.apollo3.api.json;

import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import okio.Buffer;
import okio.ByteString;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000(\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0006\u001a@\u0010\u0000\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0019\b\u0004\u0010\u0004\u001a\u0013\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005¢\u0006\u0002\b\bH\bø\u0001\u0000\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0002 \u0001\u001a6\u0010\t\u001a\u0004\u0018\u00010\n2\u0019\b\u0004\u0010\u0004\u001a\u0013\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005¢\u0006\u0002\b\bH\bø\u0001\u0000\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001\u001a@\u0010\u000b\u001a\u00020\u00032\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0019\b\u0004\u0010\u0004\u001a\u0013\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005¢\u0006\u0002\b\bH\bø\u0001\u0000\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0002 \u0001\u001a\u0014\u0010\f\u001a\u00020\u0007*\u00020\u00062\b\u0010\r\u001a\u0004\u0018\u00010\n\u001a8\u0010\u000e\u001a\u00020\u0007*\u00020\u00062\u0019\b\u0004\u0010\u0004\u001a\u0013\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005¢\u0006\u0002\b\bH\bø\u0001\u0000\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001\u001a8\u0010\u000f\u001a\u00020\u0007*\u00020\u00062\u0019\b\u0004\u0010\u0004\u001a\u0013\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005¢\u0006\u0002\b\bH\bø\u0001\u0000\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001\u0002\u0007\n\u0005\b20\u0001¨\u0006\u0010"}, d2 = {"buildJsonByteString", "Lokio/ByteString;", "indent", "", "block", "Lkotlin/Function1;", "Lcom/apollographql/apollo3/api/json/JsonWriter;", "", "Lkotlin/ExtensionFunctionType;", "buildJsonMap", "", "buildJsonString", "writeAny", "value", "writeArray", "writeObject", "apollo-api"}, k = 2, mv = {1, 5, 1}, xi = 48)
@SourceDebugExtension({"SMAP\nJsonWriters.kt\nKotlin\n*S Kotlin\n*F\n+ 1 JsonWriters.kt\ncom/apollographql/apollo3/api/json/-JsonWriters\n+ 2 _Maps.kt\nkotlin/collections/MapsKt___MapsKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,97:1\n46#1,6:98\n52#1,2:106\n57#1,6:108\n63#1,2:116\n215#2,2:104\n1855#3,2:114\n*S KotlinDebug\n*F\n+ 1 JsonWriters.kt\ncom/apollographql/apollo3/api/json/-JsonWriters\n*L\n18#1:98,6\n18#1:106,2\n27#1:108,6\n27#1:116,2\n19#1:104,2\n28#1:114,2\n*E\n"})
@JvmName(name = "-JsonWriters")
/* renamed from: com.apollographql.apollo3.api.json.-JsonWriters  reason: invalid class name */
public final class JsonWriters {
    @NotNull
    public static final ByteString buildJsonByteString(@Nullable String str, @NotNull Function1<? super JsonWriter, Unit> function1) {
        Intrinsics.checkNotNullParameter(function1, "block");
        Buffer buffer = new Buffer();
        function1.invoke(new BufferedSinkJsonWriter(buffer, str));
        return buffer.readByteString();
    }

    public static /* synthetic */ ByteString buildJsonByteString$default(String str, Function1 function1, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            str = null;
        }
        Intrinsics.checkNotNullParameter(function1, "block");
        Buffer buffer = new Buffer();
        function1.invoke(new BufferedSinkJsonWriter(buffer, str));
        return buffer.readByteString();
    }

    @Nullable
    public static final Object buildJsonMap(@NotNull Function1<? super JsonWriter, Unit> function1) {
        Intrinsics.checkNotNullParameter(function1, "block");
        MapJsonWriter mapJsonWriter = new MapJsonWriter();
        function1.invoke(mapJsonWriter);
        return mapJsonWriter.root();
    }

    @NotNull
    public static final String buildJsonString(@Nullable String str, @NotNull Function1<? super JsonWriter, Unit> function1) {
        Intrinsics.checkNotNullParameter(function1, "block");
        Buffer buffer = new Buffer();
        function1.invoke(new BufferedSinkJsonWriter(buffer, str));
        return buffer.readUtf8();
    }

    public static /* synthetic */ String buildJsonString$default(String str, Function1 function1, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            str = null;
        }
        Intrinsics.checkNotNullParameter(function1, "block");
        Buffer buffer = new Buffer();
        function1.invoke(new BufferedSinkJsonWriter(buffer, str));
        return buffer.readUtf8();
    }

    public static final void writeAny(@NotNull JsonWriter jsonWriter, @Nullable Object obj) {
        Intrinsics.checkNotNullParameter(jsonWriter, "<this>");
        if (obj == null) {
            jsonWriter.nullValue();
        } else if (obj instanceof Map) {
            jsonWriter.beginObject();
            for (Map.Entry entry : ((Map) obj).entrySet()) {
                Object key = entry.getKey();
                Object value = entry.getValue();
                jsonWriter.name(String.valueOf(key));
                writeAny(jsonWriter, value);
            }
            jsonWriter.endObject();
        } else if (obj instanceof List) {
            jsonWriter.beginArray();
            for (Object writeAny : (Iterable) obj) {
                writeAny(jsonWriter, writeAny);
            }
            jsonWriter.endArray();
        } else if (obj instanceof Boolean) {
            jsonWriter.value(((Boolean) obj).booleanValue());
        } else if (obj instanceof Integer) {
            jsonWriter.value(((Number) obj).intValue());
        } else if (obj instanceof Long) {
            jsonWriter.value(((Number) obj).longValue());
        } else if (obj instanceof Double) {
            jsonWriter.value(((Number) obj).doubleValue());
        } else if (obj instanceof JsonNumber) {
            jsonWriter.value((JsonNumber) obj);
        } else if (obj instanceof String) {
            jsonWriter.value((String) obj);
        } else {
            throw new IllegalStateException(("Cannot write " + obj + " to Json").toString());
        }
    }

    public static final void writeArray(@NotNull JsonWriter jsonWriter, @NotNull Function1<? super JsonWriter, Unit> function1) {
        Intrinsics.checkNotNullParameter(jsonWriter, "<this>");
        Intrinsics.checkNotNullParameter(function1, "block");
        jsonWriter.beginArray();
        function1.invoke(jsonWriter);
        jsonWriter.endArray();
    }

    public static final void writeObject(@NotNull JsonWriter jsonWriter, @NotNull Function1<? super JsonWriter, Unit> function1) {
        Intrinsics.checkNotNullParameter(jsonWriter, "<this>");
        Intrinsics.checkNotNullParameter(function1, "block");
        jsonWriter.beginObject();
        function1.invoke(jsonWriter);
        jsonWriter.endObject();
    }
}
