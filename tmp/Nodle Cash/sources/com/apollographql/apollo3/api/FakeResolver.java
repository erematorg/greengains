package com.apollographql.apollo3.api;

import java.util.Map;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010$\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H&J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u0004H&J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\u0004H&J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u0003\u001a\u00020\u0004H&J(\u0010\u000b\u001a\u0004\u0018\u00010\n2\u0014\u0010\f\u001a\u0010\u0012\u0004\u0012\u00020\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010\r2\u0006\u0010\u000e\u001a\u00020\u000fH&¨\u0006\u0010"}, d2 = {"Lcom/apollographql/apollo3/api/FakeResolver;", "", "resolveLeaf", "context", "Lcom/apollographql/apollo3/api/FakeResolverContext;", "resolveListSize", "", "resolveMaybeNull", "", "resolveTypename", "", "stableIdForObject", "obj", "", "mergedField", "Lcom/apollographql/apollo3/api/CompiledField;", "apollo-api"}, k = 1, mv = {1, 5, 1}, xi = 48)
public interface FakeResolver {
    @NotNull
    Object resolveLeaf(@NotNull FakeResolverContext fakeResolverContext);

    int resolveListSize(@NotNull FakeResolverContext fakeResolverContext);

    boolean resolveMaybeNull(@NotNull FakeResolverContext fakeResolverContext);

    @NotNull
    String resolveTypename(@NotNull FakeResolverContext fakeResolverContext);

    @Nullable
    String stableIdForObject(@NotNull Map<String, ? extends Object> map, @NotNull CompiledField compiledField);
}
