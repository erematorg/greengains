package com.nodle.wallet.zksync.utils;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.nodle.wallet.zksync.utils.BridgeProposalProvider", f = "BridgeProposalProvider.kt", i = {0}, l = {19}, m = "getHashForProposal", n = {"id"}, s = {"L$0"})
public final class BridgeProposalProvider$getHashForProposal$1 extends ContinuationImpl {
    Object L$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ BridgeProposalProvider this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public BridgeProposalProvider$getHashForProposal$1(BridgeProposalProvider bridgeProposalProvider, Continuation<? super BridgeProposalProvider$getHashForProposal$1> continuation) {
        super(continuation);
        this.this$0 = bridgeProposalProvider;
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.getHashForProposal((String) null, this);
    }
}
