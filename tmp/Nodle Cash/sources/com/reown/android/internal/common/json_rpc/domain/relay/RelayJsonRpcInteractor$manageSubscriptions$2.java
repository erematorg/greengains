package com.reown.android.internal.common.json_rpc.domain.relay;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.reown.android.internal.common.json_rpc.domain.relay.RelayJsonRpcInteractor", f = "RelayJsonRpcInteractor.kt", i = {0, 0, 0, 1, 1, 1, 2, 2, 2}, l = {491, 493, 495}, m = "manageSubscriptions", n = {"subscription", "clientJsonRpc", "$i$a$-let-RelayJsonRpcInteractor$manageSubscriptions$3", "subscription", "result", "$i$a$-let-RelayJsonRpcInteractor$manageSubscriptions$4", "subscription", "error", "$i$a$-let-RelayJsonRpcInteractor$manageSubscriptions$5"}, s = {"L$0", "L$1", "I$0", "L$0", "L$1", "I$0", "L$0", "L$1", "I$0"})
public final class RelayJsonRpcInteractor$manageSubscriptions$2 extends ContinuationImpl {
    int I$0;
    Object L$0;
    Object L$1;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ RelayJsonRpcInteractor this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public RelayJsonRpcInteractor$manageSubscriptions$2(RelayJsonRpcInteractor relayJsonRpcInteractor, Continuation<? super RelayJsonRpcInteractor$manageSubscriptions$2> continuation) {
        super(continuation);
        this.this$0 = relayJsonRpcInteractor;
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.manageSubscriptions((Subscription) null, this);
    }
}
