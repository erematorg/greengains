package com.apollographql.apollo3.api;

import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001B\u0007\b\u0004¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H'J\b\u0010\u0005\u001a\u00020\u0004H&\u0001\u0003\u0006\u0004\u0007¨\u0006\b"}, d2 = {"Lcom/apollographql/apollo3/api/CompiledType;", "", "()V", "leafType", "Lcom/apollographql/apollo3/api/CompiledNamedType;", "rawType", "Lcom/apollographql/apollo3/api/CompiledListType;", "Lcom/apollographql/apollo3/api/CompiledNotNullType;", "apollo-api"}, k = 1, mv = {1, 5, 1}, xi = 48)
public abstract class CompiledType {
    public /* synthetic */ CompiledType(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    @NotNull
    @Deprecated(message = "Use rawType instead", replaceWith = @ReplaceWith(expression = "rawType()", imports = {}))
    public abstract CompiledNamedType leafType();

    @NotNull
    public abstract CompiledNamedType rawType();

    private CompiledType() {
    }
}
