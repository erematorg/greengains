package com.apollographql.apollo3.api.http;

import com.apollographql.apollo3.annotations.ApolloExperimental;
import com.apollographql.apollo3.api.ApolloRequest;
import com.apollographql.apollo3.api.Operation;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@ApolloExperimental
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J.\u0010\u0003\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0004\"\b\b\u0000\u0010\u0007*\u00020\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\u00070\nH\u0016¨\u0006\u000b"}, d2 = {"Lcom/apollographql/apollo3/api/http/DefaultWebSocketPayloadComposer;", "Lcom/apollographql/apollo3/api/http/WebSocketPayloadComposer;", "()V", "compose", "", "", "", "D", "Lcom/apollographql/apollo3/api/Operation$Data;", "apolloRequest", "Lcom/apollographql/apollo3/api/ApolloRequest;", "apollo-api"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class DefaultWebSocketPayloadComposer implements WebSocketPayloadComposer {
    @NotNull
    public <D extends Operation.Data> Map<String, Object> compose(@NotNull ApolloRequest<D> apolloRequest) {
        Intrinsics.checkNotNullParameter(apolloRequest, "apolloRequest");
        return DefaultHttpRequestComposer.Companion.composePayload(apolloRequest);
    }
}
