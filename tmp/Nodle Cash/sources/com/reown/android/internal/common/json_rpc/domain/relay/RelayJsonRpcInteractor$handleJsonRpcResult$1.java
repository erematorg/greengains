package com.reown.android.internal.common.json_rpc.domain.relay;

import com.reown.android.internal.common.JsonRpcResponse;
import com.reown.foundation.common.model.Topic;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.reown.android.internal.common.json_rpc.domain.relay.RelayJsonRpcInteractor", f = "RelayJsonRpcInteractor.kt", i = {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1}, l = {521, 524}, m = "handleJsonRpcResult", n = {"jsonRpcResult", "topic", "serializedResult", "jsonRpcRecord", "params", "responseVO", "$i$a$-let-RelayJsonRpcInteractor$handleJsonRpcResult$2", "jsonRpcResult", "topic", "serializedResult", "jsonRpcRecord"}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "I$0", "L$0", "L$1", "L$2", "L$3"})
public final class RelayJsonRpcInteractor$handleJsonRpcResult$1 extends ContinuationImpl {
    int I$0;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ RelayJsonRpcInteractor this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public RelayJsonRpcInteractor$handleJsonRpcResult$1(RelayJsonRpcInteractor relayJsonRpcInteractor, Continuation<? super RelayJsonRpcInteractor$handleJsonRpcResult$1> continuation) {
        super(continuation);
        this.this$0 = relayJsonRpcInteractor;
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.handleJsonRpcResult((JsonRpcResponse.JsonRpcResult) null, (Topic) null, this);
    }
}
