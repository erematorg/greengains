package com.apollographql.apollo3.network.http;

import com.apollographql.apollo3.api.http.HttpRequest;
import com.apollographql.apollo3.api.http.HttpResponse;
import com.apollographql.apollo3.network.http.HttpInterceptor;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000\u001f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u001e\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H@¢\u0006\u0002\u0010\b¨\u0006\t"}, d2 = {"com/apollographql/apollo3/network/http/BatchingHttpEngine$engineInterceptor$1", "Lcom/apollographql/apollo3/network/http/HttpInterceptor;", "intercept", "Lcom/apollographql/apollo3/api/http/HttpResponse;", "request", "Lcom/apollographql/apollo3/api/http/HttpRequest;", "chain", "Lcom/apollographql/apollo3/network/http/HttpInterceptorChain;", "(Lcom/apollographql/apollo3/api/http/HttpRequest;Lcom/apollographql/apollo3/network/http/HttpInterceptorChain;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "apollo-runtime"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class BatchingHttpEngine$engineInterceptor$1 implements HttpInterceptor {
    final /* synthetic */ BatchingHttpEngine this$0;

    public BatchingHttpEngine$engineInterceptor$1(BatchingHttpEngine batchingHttpEngine) {
        this.this$0 = batchingHttpEngine;
    }

    public void dispose() {
        HttpInterceptor.DefaultImpls.dispose(this);
    }

    @Nullable
    public Object intercept(@NotNull HttpRequest httpRequest, @NotNull HttpInterceptorChain httpInterceptorChain, @NotNull Continuation<? super HttpResponse> continuation) {
        return this.this$0.getDelegate().execute(httpRequest, continuation);
    }
}
