package com.apollographql.apollo3.network.http;

import com.apollographql.apollo3.api.http.HttpRequest;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(k = 3, mv = {1, 5, 1}, xi = 48)
@DebugMetadata(c = "com.apollographql.apollo3.network.http.LoggingInterceptor", f = "LoggingInterceptor.kt", i = {1, 1, 1}, l = {82, 110}, m = "intercept", n = {"this", "logHeaders", "logBody"}, s = {"L$0", "I$0", "I$1"})
public final class LoggingInterceptor$intercept$1 extends ContinuationImpl {
    int I$0;
    int I$1;
    Object L$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ LoggingInterceptor this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public LoggingInterceptor$intercept$1(LoggingInterceptor loggingInterceptor, Continuation<? super LoggingInterceptor$intercept$1> continuation) {
        super(continuation);
        this.this$0 = loggingInterceptor;
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.intercept((HttpRequest) null, (HttpInterceptorChain) null, this);
    }
}
