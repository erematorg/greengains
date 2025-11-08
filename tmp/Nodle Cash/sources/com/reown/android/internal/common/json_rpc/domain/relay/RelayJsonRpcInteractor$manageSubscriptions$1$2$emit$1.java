package com.reown.android.internal.common.json_rpc.domain.relay;

import com.reown.android.internal.common.json_rpc.domain.relay.RelayJsonRpcInteractor$manageSubscriptions$1;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.reown.android.internal.common.json_rpc.domain.relay.RelayJsonRpcInteractor$manageSubscriptions$1$2", f = "RelayJsonRpcInteractor.kt", i = {0}, l = {455}, m = "emit", n = {"subscription"}, s = {"L$0"})
public final class RelayJsonRpcInteractor$manageSubscriptions$1$2$emit$1 extends ContinuationImpl {
    Object L$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ RelayJsonRpcInteractor$manageSubscriptions$1.AnonymousClass2<T> this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public RelayJsonRpcInteractor$manageSubscriptions$1$2$emit$1(RelayJsonRpcInteractor$manageSubscriptions$1.AnonymousClass2<? super T> r12, Continuation<? super RelayJsonRpcInteractor$manageSubscriptions$1$2$emit$1> continuation) {
        super(continuation);
        this.this$0 = r12;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.emit((Subscription) null, (Continuation<? super Unit>) this);
    }
}
