package com.nodle.wallet.zksync;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.nodle.wallet.zksync.ZkWallet", f = "ZkWallet.kt", i = {0}, l = {345}, m = "queryProposalByHash", n = {"proposalHash"}, s = {"L$0"})
public final class ZkWallet$queryProposalByHash$1 extends ContinuationImpl {
    Object L$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ ZkWallet this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ZkWallet$queryProposalByHash$1(ZkWallet zkWallet, Continuation<? super ZkWallet$queryProposalByHash$1> continuation) {
        super(continuation);
        this.this$0 = zkWallet;
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.queryProposalByHash((String) null, this);
    }
}
