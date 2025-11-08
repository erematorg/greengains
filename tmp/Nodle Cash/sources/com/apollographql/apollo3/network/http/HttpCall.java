package com.apollographql.apollo3.network.http;

import com.apollographql.apollo3.api.http.HttpBody;
import com.apollographql.apollo3.api.http.HttpHeader;
import com.apollographql.apollo3.api.http.HttpMethod;
import com.apollographql.apollo3.api.http.HttpRequest;
import com.apollographql.apollo3.api.http.HttpResponse;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.List;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0016\u0010\u000b\u001a\u00020\u00002\u0006\u0010\f\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u0007J\u0014\u0010\u000e\u001a\u00020\u00002\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010J\u000e\u0010\u0012\u001a\u00020\u00002\u0006\u0010\u0012\u001a\u00020\u0013J\u000e\u0010\u0014\u001a\u00020\u0015H@¢\u0006\u0002\u0010\u0016J\u0014\u0010\u000f\u001a\u00020\u00002\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0004¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lcom/apollographql/apollo3/network/http/HttpCall;", "", "engine", "Lcom/apollographql/apollo3/network/http/HttpEngine;", "method", "Lcom/apollographql/apollo3/api/http/HttpMethod;", "url", "", "(Lcom/apollographql/apollo3/network/http/HttpEngine;Lcom/apollographql/apollo3/api/http/HttpMethod;Ljava/lang/String;)V", "requestBuilder", "Lcom/apollographql/apollo3/api/http/HttpRequest$Builder;", "addHeader", "name", "value", "addHeaders", "headers", "", "Lcom/apollographql/apollo3/api/http/HttpHeader;", "body", "Lcom/apollographql/apollo3/api/http/HttpBody;", "execute", "Lcom/apollographql/apollo3/api/http/HttpResponse;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "apollo-runtime"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class HttpCall {
    @NotNull
    private final HttpEngine engine;
    @NotNull
    private final HttpRequest.Builder requestBuilder;

    public HttpCall(@NotNull HttpEngine httpEngine, @NotNull HttpMethod httpMethod, @NotNull String str) {
        Intrinsics.checkNotNullParameter(httpEngine, "engine");
        Intrinsics.checkNotNullParameter(httpMethod, FirebaseAnalytics.Param.METHOD);
        Intrinsics.checkNotNullParameter(str, "url");
        this.engine = httpEngine;
        this.requestBuilder = new HttpRequest.Builder(httpMethod, str);
    }

    @NotNull
    public final HttpCall addHeader(@NotNull String str, @NotNull String str2) {
        Intrinsics.checkNotNullParameter(str, "name");
        Intrinsics.checkNotNullParameter(str2, "value");
        this.requestBuilder.addHeader(str, str2);
        return this;
    }

    @NotNull
    public final HttpCall addHeaders(@NotNull List<HttpHeader> list) {
        Intrinsics.checkNotNullParameter(list, "headers");
        this.requestBuilder.addHeaders(list);
        return this;
    }

    @NotNull
    public final HttpCall body(@NotNull HttpBody httpBody) {
        Intrinsics.checkNotNullParameter(httpBody, "body");
        this.requestBuilder.body(httpBody);
        return this;
    }

    @Nullable
    public final Object execute(@NotNull Continuation<? super HttpResponse> continuation) {
        return this.engine.execute(this.requestBuilder.build(), continuation);
    }

    @NotNull
    public final HttpCall headers(@NotNull List<HttpHeader> list) {
        Intrinsics.checkNotNullParameter(list, "headers");
        this.requestBuilder.headers(list);
        return this;
    }
}
