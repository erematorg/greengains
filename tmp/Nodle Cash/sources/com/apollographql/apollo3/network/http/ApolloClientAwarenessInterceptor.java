package com.apollographql.apollo3.network.http;

import com.apollographql.apollo3.api.http.HttpHeader;
import com.apollographql.apollo3.api.http.HttpMethod;
import com.apollographql.apollo3.api.http.HttpRequest;
import com.apollographql.apollo3.api.http.HttpResponse;
import com.apollographql.apollo3.network.http.HttpInterceptor;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\u001e\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH@¢\u0006\u0002\u0010\u000fR\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/apollographql/apollo3/network/http/ApolloClientAwarenessInterceptor;", "Lcom/apollographql/apollo3/network/http/HttpInterceptor;", "clientName", "", "clientVersion", "(Ljava/lang/String;Ljava/lang/String;)V", "extraHeaders", "", "Lcom/apollographql/apollo3/api/http/HttpHeader;", "intercept", "Lcom/apollographql/apollo3/api/http/HttpResponse;", "request", "Lcom/apollographql/apollo3/api/http/HttpRequest;", "chain", "Lcom/apollographql/apollo3/network/http/HttpInterceptorChain;", "(Lcom/apollographql/apollo3/api/http/HttpRequest;Lcom/apollographql/apollo3/network/http/HttpInterceptorChain;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "apollo-runtime"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class ApolloClientAwarenessInterceptor implements HttpInterceptor {
    @NotNull
    private final List<HttpHeader> extraHeaders;

    public ApolloClientAwarenessInterceptor(@NotNull String str, @NotNull String str2) {
        Intrinsics.checkNotNullParameter(str, "clientName");
        Intrinsics.checkNotNullParameter(str2, "clientVersion");
        this.extraHeaders = CollectionsKt.listOf(new HttpHeader("apollographql-client-name", str), new HttpHeader("apollographql-client-version", str2));
    }

    public void dispose() {
        HttpInterceptor.DefaultImpls.dispose(this);
    }

    @Nullable
    public Object intercept(@NotNull HttpRequest httpRequest, @NotNull HttpInterceptorChain httpInterceptorChain, @NotNull Continuation<? super HttpResponse> continuation) {
        return httpInterceptorChain.proceed(HttpRequest.newBuilder$default(httpRequest, (HttpMethod) null, (String) null, 3, (Object) null).addHeaders(this.extraHeaders).build(), continuation);
    }
}
