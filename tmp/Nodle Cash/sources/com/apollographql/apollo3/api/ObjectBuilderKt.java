package com.apollographql.apollo3.api;

import com.apollographql.apollo3.api.json.MapJsonWriter;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u000e\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0006\u001a)\u0010\u0007\u001a\u0004\u0018\u00010\b\"\u0004\b\u0000\u0010\t2\f\u0010\n\u001a\b\u0012\u0004\u0012\u0002H\t0\u000b2\u0006\u0010\f\u001a\u0002H\t¢\u0006\u0002\u0010\r\"\u0011\u0010\u0000\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0003¨\u0006\u000e"}, d2 = {"GlobalBuilder", "Lcom/apollographql/apollo3/api/BuilderScope;", "getGlobalBuilder", "()Lcom/apollographql/apollo3/api/BuilderScope;", "Builder", "customScalarAdapters", "Lcom/apollographql/apollo3/api/CustomScalarAdapters;", "adaptValue", "", "T", "adapter", "Lcom/apollographql/apollo3/api/Adapter;", "value", "(Lcom/apollographql/apollo3/api/Adapter;Ljava/lang/Object;)Ljava/lang/Object;", "apollo-api"}, k = 2, mv = {1, 5, 1}, xi = 48)
public final class ObjectBuilderKt {
    @NotNull
    private static final BuilderScope GlobalBuilder = new ObjectBuilderKt$GlobalBuilder$1();

    @NotNull
    public static final BuilderScope Builder(@NotNull CustomScalarAdapters customScalarAdapters) {
        Intrinsics.checkNotNullParameter(customScalarAdapters, "customScalarAdapters");
        return new ObjectBuilderKt$Builder$1(customScalarAdapters);
    }

    @Nullable
    public static final <T> Object adaptValue(@NotNull Adapter<T> adapter, T t2) {
        Intrinsics.checkNotNullParameter(adapter, "adapter");
        MapJsonWriter mapJsonWriter = new MapJsonWriter();
        adapter.toJson(mapJsonWriter, CustomScalarAdapters.Empty, t2);
        return mapJsonWriter.root();
    }

    @NotNull
    public static final BuilderScope getGlobalBuilder() {
        return GlobalBuilder;
    }
}
