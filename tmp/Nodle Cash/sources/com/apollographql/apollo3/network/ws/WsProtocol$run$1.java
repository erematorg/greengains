package com.apollographql.apollo3.network.ws;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(k = 3, mv = {1, 5, 1}, xi = 48)
@DebugMetadata(c = "com.apollographql.apollo3.network.ws.WsProtocol", f = "WsProtocol.kt", i = {0}, l = {144}, m = "run$suspendImpl", n = {"$this"}, s = {"L$0"})
public final class WsProtocol$run$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ WsProtocol this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public WsProtocol$run$1(WsProtocol wsProtocol, Continuation<? super WsProtocol$run$1> continuation) {
        super(continuation);
        this.this$0 = wsProtocol;
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return WsProtocol.run$suspendImpl(this.this$0, this);
    }
}
