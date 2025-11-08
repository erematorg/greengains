package com.reown.android.internal.common.json_rpc.domain.link_mode;

import com.reown.android.internal.common.JsonRpcResponse;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.reown.android.internal.common.json_rpc.domain.link_mode.LinkModeJsonRpcInteractor", f = "LinkModeJsonRpcInteractor.kt", i = {0, 0, 0, 0, 0}, l = {104}, m = "serializeResult", n = {"result", "serializedResult", "jsonRpcRecord", "params", "$i$a$-let-LinkModeJsonRpcInteractor$serializeResult$2"}, s = {"L$0", "L$1", "L$2", "L$3", "I$0"})
public final class LinkModeJsonRpcInteractor$serializeResult$1 extends ContinuationImpl {
    int I$0;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ LinkModeJsonRpcInteractor this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public LinkModeJsonRpcInteractor$serializeResult$1(LinkModeJsonRpcInteractor linkModeJsonRpcInteractor, Continuation<? super LinkModeJsonRpcInteractor$serializeResult$1> continuation) {
        super(continuation);
        this.this$0 = linkModeJsonRpcInteractor;
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.serializeResult((JsonRpcResponse.JsonRpcResult) null, this);
    }
}
