package com.reown.android.internal.common.json_rpc.domain.relay;

import com.reown.android.internal.common.model.sync.ClientJsonRpc;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.reown.android.internal.common.json_rpc.domain.relay.RelayJsonRpcInteractor", f = "RelayJsonRpcInteractor.kt", i = {0, 0, 0, 0}, l = {509}, m = "handleRequest", n = {"clientJsonRpc", "subscription", "params", "$i$a$-let-RelayJsonRpcInteractor$handleRequest$2"}, s = {"L$0", "L$1", "L$2", "I$0"})
public final class RelayJsonRpcInteractor$handleRequest$1 extends ContinuationImpl {
    int I$0;
    Object L$0;
    Object L$1;
    Object L$2;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ RelayJsonRpcInteractor this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public RelayJsonRpcInteractor$handleRequest$1(RelayJsonRpcInteractor relayJsonRpcInteractor, Continuation<? super RelayJsonRpcInteractor$handleRequest$1> continuation) {
        super(continuation);
        this.this$0 = relayJsonRpcInteractor;
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.handleRequest((ClientJsonRpc) null, (Subscription) null, this);
    }
}
