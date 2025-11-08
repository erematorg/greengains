package com.apollographql.apollo3.api;

import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001B\u000f\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0007\u001a\u00020\u0000H\u0017J\b\u0010\b\u001a\u00020\u0000H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u0001\u0007\t\n\u000b\f\r\u000e\u000f¨\u0006\u0010"}, d2 = {"Lcom/apollographql/apollo3/api/CompiledNamedType;", "Lcom/apollographql/apollo3/api/CompiledType;", "name", "", "(Ljava/lang/String;)V", "getName", "()Ljava/lang/String;", "leafType", "rawType", "Lcom/apollographql/apollo3/api/CustomScalarType;", "Lcom/apollographql/apollo3/api/EnumType;", "Lcom/apollographql/apollo3/api/InputObjectType;", "Lcom/apollographql/apollo3/api/InterfaceType;", "Lcom/apollographql/apollo3/api/ObjectType;", "Lcom/apollographql/apollo3/api/ScalarType;", "Lcom/apollographql/apollo3/api/UnionType;", "apollo-api"}, k = 1, mv = {1, 5, 1}, xi = 48)
public abstract class CompiledNamedType extends CompiledType {
    @NotNull
    private final String name;

    public /* synthetic */ CompiledNamedType(String str, DefaultConstructorMarker defaultConstructorMarker) {
        this(str);
    }

    @NotNull
    public final String getName() {
        return this.name;
    }

    @NotNull
    @Deprecated(message = "Use rawType instead", replaceWith = @ReplaceWith(expression = "rawType()", imports = {}))
    public CompiledNamedType leafType() {
        return this;
    }

    @NotNull
    public CompiledNamedType rawType() {
        return this;
    }

    private CompiledNamedType(String str) {
        super((DefaultConstructorMarker) null);
        this.name = str;
    }
}
