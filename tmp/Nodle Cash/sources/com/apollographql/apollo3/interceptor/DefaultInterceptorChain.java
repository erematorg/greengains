package com.apollographql.apollo3.interceptor;

import com.apollographql.apollo3.api.ApolloRequest;
import com.apollographql.apollo3.api.ApolloResponse;
import com.apollographql.apollo3.api.Operation;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.Flow;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u001b\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J,\u0010\b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u000b0\n0\t\"\b\b\u0000\u0010\u000b*\u00020\f2\f\u0010\r\u001a\b\u0012\u0004\u0012\u0002H\u000b0\u000eH\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/apollographql/apollo3/interceptor/DefaultInterceptorChain;", "Lcom/apollographql/apollo3/interceptor/ApolloInterceptorChain;", "interceptors", "", "Lcom/apollographql/apollo3/interceptor/ApolloInterceptor;", "index", "", "(Ljava/util/List;I)V", "proceed", "Lkotlinx/coroutines/flow/Flow;", "Lcom/apollographql/apollo3/api/ApolloResponse;", "D", "Lcom/apollographql/apollo3/api/Operation$Data;", "request", "Lcom/apollographql/apollo3/api/ApolloRequest;", "apollo-runtime"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class DefaultInterceptorChain implements ApolloInterceptorChain {
    private final int index;
    @NotNull
    private final List<ApolloInterceptor> interceptors;

    public DefaultInterceptorChain(@NotNull List<? extends ApolloInterceptor> list, int i3) {
        Intrinsics.checkNotNullParameter(list, "interceptors");
        this.interceptors = list;
        this.index = i3;
    }

    @NotNull
    public <D extends Operation.Data> Flow<ApolloResponse<D>> proceed(@NotNull ApolloRequest<D> apolloRequest) {
        Intrinsics.checkNotNullParameter(apolloRequest, "request");
        if (this.index < this.interceptors.size()) {
            return this.interceptors.get(this.index).intercept(apolloRequest, new DefaultInterceptorChain(this.interceptors, this.index + 1));
        }
        throw new IllegalStateException("Check failed.");
    }
}
