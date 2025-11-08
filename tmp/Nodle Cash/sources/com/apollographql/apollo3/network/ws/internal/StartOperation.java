package com.apollographql.apollo3.network.ws.internal;

import com.apollographql.apollo3.api.ApolloRequest;
import com.apollographql.apollo3.api.Operation;
import com.apollographql.apollo3.api.Operation.Data;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0003B\u0013\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005¢\u0006\u0002\u0010\u0006R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lcom/apollographql/apollo3/network/ws/internal/StartOperation;", "D", "Lcom/apollographql/apollo3/api/Operation$Data;", "Lcom/apollographql/apollo3/network/ws/internal/Command;", "request", "Lcom/apollographql/apollo3/api/ApolloRequest;", "(Lcom/apollographql/apollo3/api/ApolloRequest;)V", "getRequest", "()Lcom/apollographql/apollo3/api/ApolloRequest;", "apollo-runtime"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class StartOperation<D extends Operation.Data> implements Command {
    @NotNull
    private final ApolloRequest<D> request;

    public StartOperation(@NotNull ApolloRequest<D> apolloRequest) {
        Intrinsics.checkNotNullParameter(apolloRequest, "request");
        this.request = apolloRequest;
    }

    @NotNull
    public final ApolloRequest<D> getRequest() {
        return this.request;
    }
}
