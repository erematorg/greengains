package com.apollographql.apollo3;

import com.apollographql.apollo3.api.ApolloResponse;
import com.apollographql.apollo3.api.Operation;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\"'\u0010\u0000\u001a\u0004\u0018\u00010\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003*\b\u0012\u0004\u0012\u0002H\u00020\u00048F¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"autoPersistedQueryInfo", "Lcom/apollographql/apollo3/AutoPersistedQueryInfo;", "D", "Lcom/apollographql/apollo3/api/Operation$Data;", "Lcom/apollographql/apollo3/api/ApolloResponse;", "getAutoPersistedQueryInfo", "(Lcom/apollographql/apollo3/api/ApolloResponse;)Lcom/apollographql/apollo3/AutoPersistedQueryInfo;", "apollo-runtime"}, k = 2, mv = {1, 5, 1}, xi = 48)
public final class AutoPersistedQueryInfoKt {
    @Nullable
    public static final <D extends Operation.Data> AutoPersistedQueryInfo getAutoPersistedQueryInfo(@NotNull ApolloResponse<D> apolloResponse) {
        Intrinsics.checkNotNullParameter(apolloResponse, "<this>");
        return (AutoPersistedQueryInfo) apolloResponse.executionContext.get(AutoPersistedQueryInfo.Key);
    }
}
