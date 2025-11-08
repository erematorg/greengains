package com.nodle.wallet.zksync;

import com.nodle.wallet.token.GenericToken;
import java.math.BigInteger;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.nodle.wallet.zksync.ZkWallet", f = "ZkWallet.kt", i = {0, 0, 0}, l = {97}, m = "transfer", n = {"token", "amount", "to"}, s = {"L$0", "L$1", "L$2"})
public final class ZkWallet$transfer$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ ZkWallet this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ZkWallet$transfer$1(ZkWallet zkWallet, Continuation<? super ZkWallet$transfer$1> continuation) {
        super(continuation);
        this.this$0 = zkWallet;
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.transfer((GenericToken) null, (BigInteger) null, (String) null, this);
    }
}
