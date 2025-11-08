package com.apollographql.apollo3.api;

import com.apollographql.apollo3.annotations.ApolloExperimental;
import com.apollographql.apollo3.api.Operation;
import com.apollographql.apollo3.api.internal.ResponseParser;
import com.apollographql.apollo3.api.json.JsonReader;
import com.apollographql.apollo3.api.json.JsonReaders;
import com.apollographql.apollo3.api.json.JsonWriter;
import com.google.android.gms.actions.SearchIntents;
import kotlin.ExceptionsKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmName;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import okio.Buffer;
import okio.BufferedSource;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u00002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u001a.\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003*\b\u0012\u0004\u0012\u0002H\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\bH\u0007\u001a;\u0010\t\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003*\b\u0012\u0004\u0012\u0002H\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\n\u001a\u0002H\u00022\b\b\u0002\u0010\u0007\u001a\u00020\bH\u0007¢\u0006\u0002\u0010\u000b\u001a4\u0010\f\u001a\b\u0012\u0004\u0012\u0002H\u00020\r\"\b\b\u0000\u0010\u0002*\u00020\u0003*\b\u0012\u0004\u0012\u0002H\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0007\u001a\u00020\bH\u0007\u001a4\u0010\f\u001a\b\u0012\u0004\u0012\u0002H\u00020\r\"\b\b\u0000\u0010\u0002*\u00020\u0003*\b\u0012\u0004\u0012\u0002H\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0007\u001a\u00020\bH\u0007¨\u0006\u0012"}, d2 = {"composeJsonRequest", "", "D", "Lcom/apollographql/apollo3/api/Operation$Data;", "Lcom/apollographql/apollo3/api/Operation;", "jsonWriter", "Lcom/apollographql/apollo3/api/json/JsonWriter;", "customScalarAdapters", "Lcom/apollographql/apollo3/api/CustomScalarAdapters;", "composeJsonResponse", "data", "(Lcom/apollographql/apollo3/api/Operation;Lcom/apollographql/apollo3/api/json/JsonWriter;Lcom/apollographql/apollo3/api/Operation$Data;Lcom/apollographql/apollo3/api/CustomScalarAdapters;)V", "parseJsonResponse", "Lcom/apollographql/apollo3/api/ApolloResponse;", "jsonReader", "Lcom/apollographql/apollo3/api/json/JsonReader;", "json", "", "apollo-api"}, k = 2, mv = {1, 5, 1}, xi = 48)
@SourceDebugExtension({"SMAP\nOperations.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Operations.kt\ncom/apollographql/apollo3/api/Operations\n+ 2 JsonWriters.kt\ncom/apollographql/apollo3/api/json/-JsonWriters\n+ 3 Okio.kt\nokio/Okio__OkioKt\n*L\n1#1,102:1\n46#2,6:103\n46#2,8:109\n52#2,2:117\n46#2,8:124\n52#3,5:119\n57#3,13:132\n*S KotlinDebug\n*F\n+ 1 Operations.kt\ncom/apollographql/apollo3/api/Operations\n*L\n31#1:103,6\n36#1:109,8\n31#1:117,2\n95#1:124,8\n94#1:119,5\n94#1:132,13\n*E\n"})
@JvmName(name = "Operations")
public final class Operations {
    @JvmOverloads
    public static final <D extends Operation.Data> void composeJsonRequest(@NotNull Operation<D> operation, @NotNull JsonWriter jsonWriter) {
        Intrinsics.checkNotNullParameter(operation, "<this>");
        Intrinsics.checkNotNullParameter(jsonWriter, "jsonWriter");
        composeJsonRequest$default(operation, jsonWriter, (CustomScalarAdapters) null, 2, (Object) null);
    }

    public static /* synthetic */ void composeJsonRequest$default(Operation operation, JsonWriter jsonWriter, CustomScalarAdapters customScalarAdapters, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            customScalarAdapters = CustomScalarAdapters.Empty;
        }
        composeJsonRequest(operation, jsonWriter, customScalarAdapters);
    }

    @JvmOverloads
    public static final <D extends Operation.Data> void composeJsonResponse(@NotNull Operation<D> operation, @NotNull JsonWriter jsonWriter, @NotNull D d2) {
        Intrinsics.checkNotNullParameter(operation, "<this>");
        Intrinsics.checkNotNullParameter(jsonWriter, "jsonWriter");
        Intrinsics.checkNotNullParameter(d2, "data");
        composeJsonResponse$default(operation, jsonWriter, d2, (CustomScalarAdapters) null, 4, (Object) null);
    }

    public static /* synthetic */ void composeJsonResponse$default(Operation operation, JsonWriter jsonWriter, Operation.Data data, CustomScalarAdapters customScalarAdapters, int i3, Object obj) {
        if ((i3 & 4) != 0) {
            customScalarAdapters = CustomScalarAdapters.Empty;
        }
        composeJsonResponse(operation, jsonWriter, data, customScalarAdapters);
    }

    @NotNull
    @JvmOverloads
    public static final <D extends Operation.Data> ApolloResponse<D> parseJsonResponse(@NotNull Operation<D> operation, @NotNull JsonReader jsonReader) {
        Intrinsics.checkNotNullParameter(operation, "<this>");
        Intrinsics.checkNotNullParameter(jsonReader, "jsonReader");
        return parseJsonResponse$default((Operation) operation, jsonReader, (CustomScalarAdapters) null, 2, (Object) null);
    }

    public static /* synthetic */ ApolloResponse parseJsonResponse$default(Operation operation, JsonReader jsonReader, CustomScalarAdapters customScalarAdapters, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            customScalarAdapters = CustomScalarAdapters.Empty;
        }
        return parseJsonResponse(operation, jsonReader, customScalarAdapters);
    }

    @JvmOverloads
    public static final <D extends Operation.Data> void composeJsonRequest(@NotNull Operation<D> operation, @NotNull JsonWriter jsonWriter, @NotNull CustomScalarAdapters customScalarAdapters) {
        Intrinsics.checkNotNullParameter(operation, "<this>");
        Intrinsics.checkNotNullParameter(jsonWriter, "jsonWriter");
        Intrinsics.checkNotNullParameter(customScalarAdapters, "customScalarAdapters");
        jsonWriter.beginObject();
        jsonWriter.name("operationName");
        jsonWriter.value(operation.name());
        jsonWriter.name("variables");
        jsonWriter.beginObject();
        operation.serializeVariables(jsonWriter, customScalarAdapters);
        jsonWriter.endObject();
        jsonWriter.name(SearchIntents.EXTRA_QUERY);
        jsonWriter.value(operation.document());
        jsonWriter.endObject();
    }

    @JvmOverloads
    public static final <D extends Operation.Data> void composeJsonResponse(@NotNull Operation<D> operation, @NotNull JsonWriter jsonWriter, @NotNull D d2, @NotNull CustomScalarAdapters customScalarAdapters) {
        Unit unit;
        Intrinsics.checkNotNullParameter(operation, "<this>");
        Intrinsics.checkNotNullParameter(jsonWriter, "jsonWriter");
        Intrinsics.checkNotNullParameter(d2, "data");
        Intrinsics.checkNotNullParameter(customScalarAdapters, "customScalarAdapters");
        Throwable th = null;
        try {
            jsonWriter.beginObject();
            jsonWriter.name("data");
            operation.adapter().toJson(jsonWriter, customScalarAdapters, d2);
            jsonWriter.endObject();
            unit = Unit.INSTANCE;
        } catch (Throwable th2) {
            th = th2;
            unit = null;
        }
        try {
            jsonWriter.close();
        } catch (Throwable th3) {
            if (th == null) {
                th = th3;
            } else {
                ExceptionsKt.addSuppressed(th, th3);
            }
        }
        if (th == null) {
            Intrinsics.checkNotNull(unit);
            return;
        }
        throw th;
    }

    @ApolloExperimental
    @NotNull
    @JvmOverloads
    public static final <D extends Operation.Data> ApolloResponse<D> parseJsonResponse(@NotNull Operation<D> operation, @NotNull String str) {
        Intrinsics.checkNotNullParameter(operation, "<this>");
        Intrinsics.checkNotNullParameter(str, "json");
        return parseJsonResponse$default((Operation) operation, str, (CustomScalarAdapters) null, 2, (Object) null);
    }

    @NotNull
    @JvmOverloads
    public static final <D extends Operation.Data> ApolloResponse<D> parseJsonResponse(@NotNull Operation<D> operation, @NotNull JsonReader jsonReader, @NotNull CustomScalarAdapters customScalarAdapters) {
        Intrinsics.checkNotNullParameter(operation, "<this>");
        Intrinsics.checkNotNullParameter(jsonReader, "jsonReader");
        Intrinsics.checkNotNullParameter(customScalarAdapters, "customScalarAdapters");
        return ResponseParser.INSTANCE.parse(jsonReader, operation, customScalarAdapters.newBuilder().adapterContext(customScalarAdapters.getAdapterContext().newBuilder().variables(Executables.variables(operation, customScalarAdapters, true)).build()).build());
    }

    public static /* synthetic */ ApolloResponse parseJsonResponse$default(Operation operation, String str, CustomScalarAdapters customScalarAdapters, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            customScalarAdapters = CustomScalarAdapters.Empty;
        }
        return parseJsonResponse(operation, str, customScalarAdapters);
    }

    @ApolloExperimental
    @NotNull
    @JvmOverloads
    public static final <D extends Operation.Data> ApolloResponse<D> parseJsonResponse(@NotNull Operation<D> operation, @NotNull String str, @NotNull CustomScalarAdapters customScalarAdapters) {
        Intrinsics.checkNotNullParameter(operation, "<this>");
        Intrinsics.checkNotNullParameter(str, "json");
        Intrinsics.checkNotNullParameter(customScalarAdapters, "customScalarAdapters");
        return parseJsonResponse(operation, JsonReaders.jsonReader((BufferedSource) new Buffer().writeUtf8(str)), customScalarAdapters);
    }
}
