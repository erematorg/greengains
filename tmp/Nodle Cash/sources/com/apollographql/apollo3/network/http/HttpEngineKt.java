package com.apollographql.apollo3.network.http;

import com.apollographql.apollo3.api.http.HttpMethod;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u001a\u0012\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004\u001a\u0012\u0010\u0005\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004¨\u0006\u0006"}, d2 = {"get", "Lcom/apollographql/apollo3/network/http/HttpCall;", "Lcom/apollographql/apollo3/network/http/HttpEngine;", "url", "", "post", "apollo-runtime"}, k = 2, mv = {1, 5, 1}, xi = 48)
public final class HttpEngineKt {
    @NotNull
    public static final HttpCall get(@NotNull HttpEngine httpEngine, @NotNull String str) {
        Intrinsics.checkNotNullParameter(httpEngine, "<this>");
        Intrinsics.checkNotNullParameter(str, "url");
        return new HttpCall(httpEngine, HttpMethod.Get, str);
    }

    @NotNull
    public static final HttpCall post(@NotNull HttpEngine httpEngine, @NotNull String str) {
        Intrinsics.checkNotNullParameter(httpEngine, "<this>");
        Intrinsics.checkNotNullParameter(str, "url");
        return new HttpCall(httpEngine, HttpMethod.Post, str);
    }
}
