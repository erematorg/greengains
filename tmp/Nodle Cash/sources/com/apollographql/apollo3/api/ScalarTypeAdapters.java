package com.apollographql.apollo3.api;

import java.util.Map;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.NotImplementedError;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001B\u001d\u0012\u0016\u0010\u0002\u001a\u0012\u0012\u0004\u0012\u00020\u0004\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00050\u0003¢\u0006\u0002\u0010\u0006R!\u0010\u0002\u001a\u0012\u0012\u0004\u0012\u00020\u0004\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00050\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lcom/apollographql/apollo3/api/ScalarTypeAdapters;", "", "customAdapters", "", "Lcom/apollographql/apollo3/api/CustomScalarType;", "Lcom/apollographql/apollo3/api/CustomTypeAdapter;", "(Ljava/util/Map;)V", "getCustomAdapters", "()Ljava/util/Map;", "apollo-api"}, k = 1, mv = {1, 5, 1}, xi = 48)
@Deprecated(level = DeprecationLevel.ERROR, message = "Used for backward compatibility with 2.x, use Adapter instead")
public final class ScalarTypeAdapters {
    @NotNull
    private final Map<CustomScalarType, CustomTypeAdapter<?>> customAdapters;

    public ScalarTypeAdapters(@NotNull Map<CustomScalarType, ? extends CustomTypeAdapter<?>> map) {
        Intrinsics.checkNotNullParameter(map, "customAdapters");
        this.customAdapters = map;
        throw new NotImplementedError("Use CustomScalarAdapters instead");
    }

    @NotNull
    public final Map<CustomScalarType, CustomTypeAdapter<?>> getCustomAdapters() {
        return this.customAdapters;
    }
}
