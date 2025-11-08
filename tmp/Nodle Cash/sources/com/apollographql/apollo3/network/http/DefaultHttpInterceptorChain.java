package com.apollographql.apollo3.network.http;

import com.apollographql.apollo3.api.http.HttpRequest;
import com.apollographql.apollo3.api.http.HttpResponse;
import java.util.List;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\u001b\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\u0016\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH@¢\u0006\u0002\u0010\fR\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/apollographql/apollo3/network/http/DefaultHttpInterceptorChain;", "Lcom/apollographql/apollo3/network/http/HttpInterceptorChain;", "interceptors", "", "Lcom/apollographql/apollo3/network/http/HttpInterceptor;", "index", "", "(Ljava/util/List;I)V", "proceed", "Lcom/apollographql/apollo3/api/http/HttpResponse;", "request", "Lcom/apollographql/apollo3/api/http/HttpRequest;", "(Lcom/apollographql/apollo3/api/http/HttpRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "apollo-runtime"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class DefaultHttpInterceptorChain implements HttpInterceptorChain {
    private final int index;
    @NotNull
    private final List<HttpInterceptor> interceptors;

    public DefaultHttpInterceptorChain(@NotNull List<? extends HttpInterceptor> list, int i3) {
        Intrinsics.checkNotNullParameter(list, "interceptors");
        this.interceptors = list;
        this.index = i3;
    }

    @Nullable
    public Object proceed(@NotNull HttpRequest httpRequest, @NotNull Continuation<? super HttpResponse> continuation) {
        if (this.index < this.interceptors.size()) {
            return this.interceptors.get(this.index).intercept(httpRequest, new DefaultHttpInterceptorChain(this.interceptors, this.index + 1), continuation);
        }
        throw new IllegalStateException("Check failed.");
    }
}
