package com.apollographql.apollo3.api;

import com.apollographql.apollo3.api.Operation;
import com.apollographql.apollo3.api.json.BufferedSinkJsonWriter;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import okio.Buffer;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0014\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\b\b\u0002\u0010\u0003\u001a\u00020\u0004\u001a\u0014\u0010\u0005\u001a\u00020\u0001*\u00020\u00022\b\b\u0002\u0010\u0003\u001a\u00020\u0004¨\u0006\u0006"}, d2 = {"toJson", "", "Lcom/apollographql/apollo3/api/Operation$Data;", "customScalarAdapters", "Lcom/apollographql/apollo3/api/CustomScalarAdapters;", "toResponseJson", "apollo-api"}, k = 2, mv = {1, 5, 1}, xi = 48)
@SourceDebugExtension({"SMAP\ntoJson.kt\nKotlin\n*S Kotlin\n*F\n+ 1 toJson.kt\ncom/apollographql/apollo3/api/ToJsonKt\n+ 2 JsonWriters.kt\ncom/apollographql/apollo3/api/json/-JsonWriters\n*L\n1#1,20:1\n67#2,8:21\n67#2,8:29\n*S KotlinDebug\n*F\n+ 1 toJson.kt\ncom/apollographql/apollo3/api/ToJsonKt\n*L\n11#1:21,8\n15#1:29,8\n*E\n"})
public final class ToJsonKt {
    @NotNull
    public static final String toJson(@NotNull Operation.Data data, @NotNull CustomScalarAdapters customScalarAdapters) {
        Intrinsics.checkNotNullParameter(data, "<this>");
        Intrinsics.checkNotNullParameter(customScalarAdapters, "customScalarAdapters");
        Buffer buffer = new Buffer();
        _DataKt.toJson(data, new BufferedSinkJsonWriter(buffer, (String) null), customScalarAdapters);
        return buffer.readUtf8();
    }

    public static /* synthetic */ String toJson$default(Operation.Data data, CustomScalarAdapters customScalarAdapters, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            customScalarAdapters = CustomScalarAdapters.Empty;
        }
        return toJson(data, customScalarAdapters);
    }

    @NotNull
    public static final String toResponseJson(@NotNull Operation.Data data, @NotNull CustomScalarAdapters customScalarAdapters) {
        Intrinsics.checkNotNullParameter(data, "<this>");
        Intrinsics.checkNotNullParameter(customScalarAdapters, "customScalarAdapters");
        Buffer buffer = new Buffer();
        BufferedSinkJsonWriter bufferedSinkJsonWriter = new BufferedSinkJsonWriter(buffer, (String) null);
        bufferedSinkJsonWriter.beginObject();
        bufferedSinkJsonWriter.name("data");
        _DataKt.toJson(data, bufferedSinkJsonWriter, customScalarAdapters);
        bufferedSinkJsonWriter.endObject();
        return buffer.readUtf8();
    }

    public static /* synthetic */ String toResponseJson$default(Operation.Data data, CustomScalarAdapters customScalarAdapters, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            customScalarAdapters = CustomScalarAdapters.Empty;
        }
        return toResponseJson(data, customScalarAdapters);
    }
}
