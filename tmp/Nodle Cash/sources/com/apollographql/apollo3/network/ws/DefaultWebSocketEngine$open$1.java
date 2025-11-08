package com.apollographql.apollo3.network.ws;

import com.apollographql.apollo3.api.http.HttpHeader;
import java.util.List;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(k = 3, mv = {1, 5, 1}, xi = 48)
@DebugMetadata(c = "com.apollographql.apollo3.network.ws.DefaultWebSocketEngine", f = "OkHttpWebSocketEngine.kt", i = {0, 0}, l = {74}, m = "open", n = {"messageChannel", "webSocket"}, s = {"L$0", "L$1"})
public final class DefaultWebSocketEngine$open$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ DefaultWebSocketEngine this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public DefaultWebSocketEngine$open$1(DefaultWebSocketEngine defaultWebSocketEngine, Continuation<? super DefaultWebSocketEngine$open$1> continuation) {
        super(continuation);
        this.this$0 = defaultWebSocketEngine;
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.open((String) null, (List<HttpHeader>) null, (Continuation<? super WebSocketConnection>) this);
    }
}
