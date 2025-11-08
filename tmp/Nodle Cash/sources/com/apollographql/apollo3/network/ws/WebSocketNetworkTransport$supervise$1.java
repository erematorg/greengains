package com.apollographql.apollo3.network.ws;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(k = 3, mv = {1, 5, 1}, xi = 48)
@DebugMetadata(c = "com.apollographql.apollo3.network.ws.WebSocketNetworkTransport", f = "WebSocketNetworkTransport.kt", i = {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 4, 4, 4, 5, 5, 5, 5, 5, 5, 5, 6, 6, 6, 6, 6, 6, 6, 6, 7, 7, 7, 7, 7, 7, 7, 8, 8, 8, 8, 8, 8, 8}, l = {154, 159, 161, 195, 194, 204, 214, 218, 244}, m = "supervise", n = {"this", "scope", "idleJob", "connectionJob", "protocol", "activeMessages", "reopenAttemptCount", "this", "scope", "idleJob", "connectionJob", "protocol", "activeMessages", "message", "reopenAttemptCount", "this", "scope", "idleJob", "connectionJob", "protocol", "activeMessages", "reopenAttemptCount", "this", "scope", "idleJob", "connectionJob", "protocol", "activeMessages", "message", "reopenAttemptCount", "this", "scope", "idleJob", "connectionJob", "protocol", "activeMessages", "message", "reopenAttemptCount", "this", "scope", "idleJob", "connectionJob", "protocol", "activeMessages", "reopenAttemptCount", "this", "scope", "idleJob", "connectionJob", "protocol", "activeMessages", "message", "reopenAttemptCount", "this", "scope", "idleJob", "connectionJob", "protocol", "activeMessages", "reopenAttemptCount", "this", "scope", "idleJob", "connectionJob", "protocol", "activeMessages", "reopenAttemptCount"}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "J$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "J$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "J$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "J$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "J$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "J$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "J$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "J$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "J$0"})
public final class WebSocketNetworkTransport$supervise$1 extends ContinuationImpl {
    long J$0;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    Object L$6;
    Object L$7;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ WebSocketNetworkTransport this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public WebSocketNetworkTransport$supervise$1(WebSocketNetworkTransport webSocketNetworkTransport, Continuation<? super WebSocketNetworkTransport$supervise$1> continuation) {
        super(continuation);
        this.this$0 = webSocketNetworkTransport;
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.supervise((CoroutineScope) null, this);
    }
}
