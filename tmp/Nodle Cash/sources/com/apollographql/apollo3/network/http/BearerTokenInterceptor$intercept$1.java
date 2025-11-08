package com.apollographql.apollo3.network.http;

import com.apollographql.apollo3.api.http.HttpRequest;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(k = 3, mv = {1, 5, 1}, xi = 48)
@DebugMetadata(c = "com.apollographql.apollo3.network.http.BearerTokenInterceptor", f = "BearerTokenInterceptor.kt", i = {0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 3, 3, 4, 4, 4, 4}, l = {37, 19, 21, 47, 24, 25}, m = "intercept", n = {"this", "request", "chain", "token", "$this$withLock_u24default$iv", "this", "request", "chain", "token", "$this$withLock_u24default$iv", "this", "request", "chain", "token", "this", "request", "chain", "token", "$this$withLock_u24default$iv", "request", "chain", "token", "$this$withLock_u24default$iv"}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$0", "L$1", "L$2", "L$3", "L$4", "L$0", "L$1", "L$2", "L$3", "L$0", "L$1", "L$2", "L$3", "L$4", "L$0", "L$1", "L$2", "L$3"})
public final class BearerTokenInterceptor$intercept$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ BearerTokenInterceptor this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public BearerTokenInterceptor$intercept$1(BearerTokenInterceptor bearerTokenInterceptor, Continuation<? super BearerTokenInterceptor$intercept$1> continuation) {
        super(continuation);
        this.this$0 = bearerTokenInterceptor;
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.intercept((HttpRequest) null, (HttpInterceptorChain) null, this);
    }
}
