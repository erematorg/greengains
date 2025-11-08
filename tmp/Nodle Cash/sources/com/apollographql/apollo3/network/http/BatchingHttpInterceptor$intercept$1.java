package com.apollographql.apollo3.network.http;

import com.apollographql.apollo3.api.http.HttpRequest;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(k = 3, mv = {1, 5, 1}, xi = 48)
@DebugMetadata(c = "com.apollographql.apollo3.network.http.BatchingHttpInterceptor", f = "BatchingHttpInterceptor.kt", i = {1, 1, 1, 2}, l = {89, 254, 103, 111}, m = "intercept", n = {"this", "pendingRequest", "$this$withLock_u24default$iv", "pendingRequest"}, s = {"L$0", "L$1", "L$2", "L$0"})
public final class BatchingHttpInterceptor$intercept$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ BatchingHttpInterceptor this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public BatchingHttpInterceptor$intercept$1(BatchingHttpInterceptor batchingHttpInterceptor, Continuation<? super BatchingHttpInterceptor$intercept$1> continuation) {
        super(continuation);
        this.this$0 = batchingHttpInterceptor;
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.intercept((HttpRequest) null, (HttpInterceptorChain) null, this);
    }
}
