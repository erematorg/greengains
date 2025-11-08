package com.apollographql.apollo3.api;

import com.apollographql.apollo3.annotations.ApolloExperimental;
import com.apollographql.apollo3.api.Operation;
import com.apollographql.apollo3.api.json.BufferedSinkJsonWriter;
import com.apollographql.apollo3.api.json.JsonWriter;
import com.reown.foundation.util.jwt.JwtUtilsKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import okio.Buffer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000&\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u001a\u0012\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0002H\u0002\u001a\u001c\u0010\u0003\u001a\u00020\u0004*\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b\u001a\"\u0010\t\u001a\u00020\n*\u00020\u00022\b\b\u0002\u0010\u0007\u001a\u00020\b2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\nH\u0007¨\u0006\f"}, d2 = {"adapter", "Lcom/apollographql/apollo3/api/Adapter;", "Lcom/apollographql/apollo3/api/Operation$Data;", "toJson", "", "jsonWriter", "Lcom/apollographql/apollo3/api/json/JsonWriter;", "customScalarAdapters", "Lcom/apollographql/apollo3/api/CustomScalarAdapters;", "toJsonString", "", "indent", "apollo-api"}, k = 2, mv = {1, 5, 1}, xi = 48)
@SourceDebugExtension({"SMAP\n-Data.kt\nKotlin\n*S Kotlin\n*F\n+ 1 -Data.kt\ncom/apollographql/apollo3/api/_DataKt\n+ 2 JsonWriters.kt\ncom/apollographql/apollo3/api/json/-JsonWriters\n*L\n1#1,37:1\n68#2,7:38\n*S KotlinDebug\n*F\n+ 1 -Data.kt\ncom/apollographql/apollo3/api/_DataKt\n*L\n32#1:38,7\n*E\n"})
public final class _DataKt {
    private static final Adapter<Operation.Data> adapter(Operation.Data data) {
        String name = data.getClass().getName();
        Intrinsics.checkNotNullExpressionValue(name, "name");
        String O = StringsKt__StringsKt.removeSuffix(name, (CharSequence) "$Data");
        String k02 = StringsKt__StringsKt.substringAfterLast$default(O, (String) JwtUtilsKt.JWT_DELIMITER, (String) null, 2, (Object) null);
        String p02 = StringsKt__StringsKt.substringBeforeLast$default(O, (String) JwtUtilsKt.JWT_DELIMITER, (String) null, 2, (Object) null);
        Object obj = Class.forName(p02 + ".adapter." + k02 + "_ResponseAdapter$Data").getDeclaredField("INSTANCE").get((Object) null);
        Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type com.apollographql.apollo3.api.Adapter<com.apollographql.apollo3.api.Operation.Data>");
        return Adapters.m8203obj$default((Adapter) obj, false, 1, (Object) null);
    }

    public static final void toJson(@NotNull Operation.Data data, @NotNull JsonWriter jsonWriter, @NotNull CustomScalarAdapters customScalarAdapters) {
        Intrinsics.checkNotNullParameter(data, "<this>");
        Intrinsics.checkNotNullParameter(jsonWriter, "jsonWriter");
        Intrinsics.checkNotNullParameter(customScalarAdapters, "customScalarAdapters");
        adapter(data).toJson(jsonWriter, customScalarAdapters, data);
    }

    public static /* synthetic */ void toJson$default(Operation.Data data, JsonWriter jsonWriter, CustomScalarAdapters customScalarAdapters, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            customScalarAdapters = CustomScalarAdapters.Empty;
        }
        toJson(data, jsonWriter, customScalarAdapters);
    }

    @ApolloExperimental
    @NotNull
    public static final String toJsonString(@NotNull Operation.Data data, @NotNull CustomScalarAdapters customScalarAdapters, @Nullable String str) {
        Intrinsics.checkNotNullParameter(data, "<this>");
        Intrinsics.checkNotNullParameter(customScalarAdapters, "customScalarAdapters");
        Buffer buffer = new Buffer();
        toJson(data, new BufferedSinkJsonWriter(buffer, str), customScalarAdapters);
        return buffer.readUtf8();
    }

    public static /* synthetic */ String toJsonString$default(Operation.Data data, CustomScalarAdapters customScalarAdapters, String str, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            customScalarAdapters = CustomScalarAdapters.Empty;
        }
        if ((i3 & 2) != 0) {
            str = null;
        }
        return toJsonString(data, customScalarAdapters, str);
    }
}
