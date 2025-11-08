package com.apollographql.apollo3.api.http;

import com.apollographql.apollo3.api.ApolloRequest;
import com.apollographql.apollo3.api.Operation;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J \u0010\u0002\u001a\u00020\u0003\"\b\b\u0000\u0010\u0004*\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u0002H\u00040\u0007H&¨\u0006\b"}, d2 = {"Lcom/apollographql/apollo3/api/http/HttpRequestComposer;", "", "compose", "Lcom/apollographql/apollo3/api/http/HttpRequest;", "D", "Lcom/apollographql/apollo3/api/Operation$Data;", "apolloRequest", "Lcom/apollographql/apollo3/api/ApolloRequest;", "apollo-api"}, k = 1, mv = {1, 5, 1}, xi = 48)
public interface HttpRequestComposer {
    @NotNull
    <D extends Operation.Data> HttpRequest compose(@NotNull ApolloRequest<D> apolloRequest);
}
