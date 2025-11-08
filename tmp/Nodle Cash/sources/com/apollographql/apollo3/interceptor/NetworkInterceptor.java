package com.apollographql.apollo3.interceptor;

import com.apollographql.apollo3.api.ApolloRequest;
import com.apollographql.apollo3.api.ApolloResponse;
import com.apollographql.apollo3.api.Mutation;
import com.apollographql.apollo3.api.Operation;
import com.apollographql.apollo3.api.Query;
import com.apollographql.apollo3.api.Subscription;
import com.apollographql.apollo3.network.NetworkTransport;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J4\u0010\b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u000b0\n0\t\"\b\b\u0000\u0010\u000b*\u00020\f2\f\u0010\r\u001a\b\u0012\u0004\u0012\u0002H\u000b0\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lcom/apollographql/apollo3/interceptor/NetworkInterceptor;", "Lcom/apollographql/apollo3/interceptor/ApolloInterceptor;", "networkTransport", "Lcom/apollographql/apollo3/network/NetworkTransport;", "subscriptionNetworkTransport", "dispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "(Lcom/apollographql/apollo3/network/NetworkTransport;Lcom/apollographql/apollo3/network/NetworkTransport;Lkotlinx/coroutines/CoroutineDispatcher;)V", "intercept", "Lkotlinx/coroutines/flow/Flow;", "Lcom/apollographql/apollo3/api/ApolloResponse;", "D", "Lcom/apollographql/apollo3/api/Operation$Data;", "request", "Lcom/apollographql/apollo3/api/ApolloRequest;", "chain", "Lcom/apollographql/apollo3/interceptor/ApolloInterceptorChain;", "apollo-runtime"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class NetworkInterceptor implements ApolloInterceptor {
    @NotNull
    private final CoroutineDispatcher dispatcher;
    @NotNull
    private final NetworkTransport networkTransport;
    @NotNull
    private final NetworkTransport subscriptionNetworkTransport;

    public NetworkInterceptor(@NotNull NetworkTransport networkTransport2, @NotNull NetworkTransport networkTransport3, @NotNull CoroutineDispatcher coroutineDispatcher) {
        Intrinsics.checkNotNullParameter(networkTransport2, "networkTransport");
        Intrinsics.checkNotNullParameter(networkTransport3, "subscriptionNetworkTransport");
        Intrinsics.checkNotNullParameter(coroutineDispatcher, "dispatcher");
        this.networkTransport = networkTransport2;
        this.subscriptionNetworkTransport = networkTransport3;
        this.dispatcher = coroutineDispatcher;
    }

    @NotNull
    public <D extends Operation.Data> Flow<ApolloResponse<D>> intercept(@NotNull ApolloRequest<D> apolloRequest, @NotNull ApolloInterceptorChain apolloInterceptorChain) {
        Flow<ApolloResponse<D>> flow;
        Intrinsics.checkNotNullParameter(apolloRequest, "request");
        Intrinsics.checkNotNullParameter(apolloInterceptorChain, "chain");
        Operation<D> operation = apolloRequest.getOperation();
        if (operation instanceof Query) {
            flow = this.networkTransport.execute(apolloRequest);
        } else if (operation instanceof Mutation) {
            flow = this.networkTransport.execute(apolloRequest);
        } else if (operation instanceof Subscription) {
            flow = this.subscriptionNetworkTransport.execute(apolloRequest);
        } else {
            throw new IllegalStateException("");
        }
        return FlowKt.flowOn(flow, this.dispatcher);
    }
}
