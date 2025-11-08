package com.apollographql.apollo3.api;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001B%\b\u0000\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u000f"}, d2 = {"Lcom/apollographql/apollo3/api/FakeResolverContext;", "", "path", "", "id", "", "mergedField", "Lcom/apollographql/apollo3/api/CompiledField;", "(Ljava/util/List;Ljava/lang/String;Lcom/apollographql/apollo3/api/CompiledField;)V", "getId", "()Ljava/lang/String;", "getMergedField", "()Lcom/apollographql/apollo3/api/CompiledField;", "getPath", "()Ljava/util/List;", "apollo-api"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class FakeResolverContext {
    @NotNull
    private final String id;
    @NotNull
    private final CompiledField mergedField;
    @NotNull
    private final List<Object> path;

    public FakeResolverContext(@NotNull List<? extends Object> list, @NotNull String str, @NotNull CompiledField compiledField) {
        Intrinsics.checkNotNullParameter(list, "path");
        Intrinsics.checkNotNullParameter(str, TtmlNode.ATTR_ID);
        Intrinsics.checkNotNullParameter(compiledField, "mergedField");
        this.path = list;
        this.id = str;
        this.mergedField = compiledField;
    }

    @NotNull
    public final String getId() {
        return this.id;
    }

    @NotNull
    public final CompiledField getMergedField() {
        return this.mergedField;
    }

    @NotNull
    public final List<Object> getPath() {
        return this.path;
    }
}
