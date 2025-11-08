package com.apollographql.apollo3.network.ws;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(k = 3, mv = {1, 5, 1}, xi = 48)
@DebugMetadata(c = "com.apollographql.apollo3.network.ws.AppSyncWsProtocol", f = "AppSyncWsProtocol.kt", i = {0}, l = {41, 43}, m = "connectionInit", n = {"this"}, s = {"L$0"})
public final class AppSyncWsProtocol$connectionInit$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ AppSyncWsProtocol this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AppSyncWsProtocol$connectionInit$1(AppSyncWsProtocol appSyncWsProtocol, Continuation<? super AppSyncWsProtocol$connectionInit$1> continuation) {
        super(continuation);
        this.this$0 = appSyncWsProtocol;
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.connectionInit(this);
    }
}
