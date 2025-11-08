package com.nodle.wallet.zksync;

import com.nodle.wallet.fee.FeeInToken;
import com.nodle.wallet.token.GenericToken;
import com.nodle.wallet.zksync.data.ZkPaymasterData;
import java.math.BigInteger;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "Lcom/nodle/wallet/fee/FeeInToken;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.nodle.wallet.zksync.ZkWallet$getTransferWithPaymasterFee$2", f = "ZkWallet.kt", i = {}, l = {164}, m = "invokeSuspend", n = {}, s = {})
public final class ZkWallet$getTransferWithPaymasterFee$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super FeeInToken>, Object> {
    final /* synthetic */ BigInteger $amount;
    final /* synthetic */ GenericToken $feeToken;
    final /* synthetic */ String $to;
    final /* synthetic */ GenericToken $token;
    int label;
    final /* synthetic */ ZkWallet this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ZkWallet$getTransferWithPaymasterFee$2(ZkWallet zkWallet, String str, BigInteger bigInteger, GenericToken genericToken, GenericToken genericToken2, Continuation<? super ZkWallet$getTransferWithPaymasterFee$2> continuation) {
        super(2, continuation);
        this.this$0 = zkWallet;
        this.$to = str;
        this.$amount = bigInteger;
        this.$token = genericToken;
        this.$feeToken = genericToken2;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new ZkWallet$getTransferWithPaymasterFee$2(this.this$0, this.$to, this.$amount, this.$token, this.$feeToken, continuation);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i3 = this.label;
        if (i3 == 0) {
            ResultKt.throwOnFailure(obj);
            ZkWallet zkWallet = this.this$0;
            String str = this.$to;
            BigInteger bigInteger = this.$amount;
            GenericToken genericToken = this.$token;
            GenericToken genericToken2 = this.$feeToken;
            this.label = 1;
            obj = zkWallet.getPaymasterTransactionData(str, bigInteger, genericToken, genericToken2, this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i3 == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ZkPaymasterData zkPaymasterData = (ZkPaymasterData) ((Pair) obj).component2();
        return new FeeInToken(this.$feeToken, zkPaymasterData.getMaxFeeInToken(), zkPaymasterData.getEstimatedFeeInToken());
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super FeeInToken> continuation) {
        return ((ZkWallet$getTransferWithPaymasterFee$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }
}
