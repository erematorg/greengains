package com.apollographql.apollo3.api;

import java.util.List;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u000f\b\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00030\u0006¢\u0006\u0002\u0010\u0007R\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00030\u0006¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\n"}, d2 = {"Lcom/apollographql/apollo3/api/EnumType;", "Lcom/apollographql/apollo3/api/CompiledNamedType;", "name", "", "(Ljava/lang/String;)V", "values", "", "(Ljava/lang/String;Ljava/util/List;)V", "getValues", "()Ljava/util/List;", "apollo-api"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class EnumType extends CompiledNamedType {
    @NotNull
    private final List<String> values;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public EnumType(@NotNull String str, @NotNull List<String> list) {
        super(str, (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(str, "name");
        Intrinsics.checkNotNullParameter(list, "values");
        this.values = list;
    }

    @NotNull
    public final List<String> getValues() {
        return this.values;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    @Deprecated(message = "Use the primary constructor instead")
    public EnumType(@NotNull String str) {
        this(str, CollectionsKt.emptyList());
        Intrinsics.checkNotNullParameter(str, "name");
    }
}
