package com.apollographql.apollo3.api;

import com.apollographql.apollo3.annotations.ApolloInternal;
import com.apollographql.apollo3.api.Executable;
import com.apollographql.apollo3.api.json.BufferedSinkJsonWriter;
import com.apollographql.apollo3.api.json.MapJsonWriter;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import okio.Buffer;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000&\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0001H\u0002\u001a\"\u0010\u0002\u001a\u00020\u0003\"\b\b\u0000\u0010\u0004*\u00020\u0005*\b\u0012\u0004\u0012\u0002H\u00040\u00062\u0006\u0010\u0007\u001a\u00020\u0001\u001a,\u0010\u0002\u001a\u00020\u0003\"\b\b\u0000\u0010\u0004*\u00020\u0005*\b\u0012\u0004\u0012\u0002H\u00040\u00062\u0006\u0010\u0007\u001a\u00020\u00012\u0006\u0010\b\u001a\u00020\tH\u0007\u001a\"\u0010\n\u001a\u00020\u000b\"\b\b\u0000\u0010\u0004*\u00020\u0005*\b\u0012\u0004\u0012\u0002H\u00040\u00062\u0006\u0010\u0007\u001a\u00020\u0001¨\u0006\f"}, d2 = {"serializeVariablesWithDefaultBooleanValues", "Lcom/apollographql/apollo3/api/CustomScalarAdapters;", "variables", "Lcom/apollographql/apollo3/api/Executable$Variables;", "D", "Lcom/apollographql/apollo3/api/Executable$Data;", "Lcom/apollographql/apollo3/api/Executable;", "customScalarAdapters", "withDefaultBooleanValues", "", "variablesJson", "", "apollo-api"}, k = 2, mv = {1, 5, 1}, xi = 48)
@SourceDebugExtension({"SMAP\nExecutables.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Executables.kt\ncom/apollographql/apollo3/api/Executables\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,46:1\n1#2:47\n*E\n"})
@JvmName(name = "Executables")
public final class Executables {
    private static final CustomScalarAdapters serializeVariablesWithDefaultBooleanValues(CustomScalarAdapters customScalarAdapters) {
        return customScalarAdapters.newBuilder().adapterContext(customScalarAdapters.getAdapterContext().newBuilder().serializeVariablesWithDefaultBooleanValues(Boolean.TRUE).build()).build();
    }

    @NotNull
    public static final <D extends Executable.Data> Executable.Variables variables(@NotNull Executable<D> executable, @NotNull CustomScalarAdapters customScalarAdapters) {
        Intrinsics.checkNotNullParameter(executable, "<this>");
        Intrinsics.checkNotNullParameter(customScalarAdapters, "customScalarAdapters");
        return variables(executable, customScalarAdapters, false);
    }

    @NotNull
    public static final <D extends Executable.Data> String variablesJson(@NotNull Executable<D> executable, @NotNull CustomScalarAdapters customScalarAdapters) {
        Intrinsics.checkNotNullParameter(executable, "<this>");
        Intrinsics.checkNotNullParameter(customScalarAdapters, "customScalarAdapters");
        Buffer buffer = new Buffer();
        BufferedSinkJsonWriter bufferedSinkJsonWriter = new BufferedSinkJsonWriter(buffer, (String) null, 2, (DefaultConstructorMarker) null);
        bufferedSinkJsonWriter.beginObject();
        executable.serializeVariables(bufferedSinkJsonWriter, serializeVariablesWithDefaultBooleanValues(customScalarAdapters));
        bufferedSinkJsonWriter.endObject();
        return buffer.readUtf8();
    }

    @NotNull
    @ApolloInternal
    public static final <D extends Executable.Data> Executable.Variables variables(@NotNull Executable<D> executable, @NotNull CustomScalarAdapters customScalarAdapters, boolean z2) {
        Intrinsics.checkNotNullParameter(executable, "<this>");
        Intrinsics.checkNotNullParameter(customScalarAdapters, "customScalarAdapters");
        MapJsonWriter mapJsonWriter = new MapJsonWriter();
        mapJsonWriter.beginObject();
        if (z2) {
            customScalarAdapters = serializeVariablesWithDefaultBooleanValues(customScalarAdapters);
        }
        executable.serializeVariables(mapJsonWriter, customScalarAdapters);
        mapJsonWriter.endObject();
        Object root = mapJsonWriter.root();
        Intrinsics.checkNotNull(root, "null cannot be cast to non-null type kotlin.collections.Map<kotlin.String, kotlin.Any?>");
        return new Executable.Variables((Map) root);
    }
}
