package com.nodle.wallet.zksync;

import androidx.browser.trusted.c;
import com.nodle.wallet.fee.FeeInToken;
import com.nodle.wallet.token.EthToken;
import com.nodle.wallet.token.GenericToken;
import io.nodle.cash.core.android.utils.NodleLogger;
import io.zksync.ZkSyncWallet;
import io.zksync.methods.request.Transaction;
import io.zksync.methods.response.ZksEstimateFee;
import io.zksync.transaction.fee.Fee;
import io.zksync.wrappers.ERC20;
import io.zksync.wrappers.ZkSyncContract;
import java.math.BigInteger;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.CoroutineScope;
import timber.log.Timber;

@SourceDebugExtension({"SMAP\nZkWallet.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ZkWallet.kt\ncom/nodle/wallet/zksync/ZkWallet$getTransferFees$2\n+ 2 NodleLogger.kt\nio/nodle/cash/core/android/utils/NodleLogger\n*L\n1#1,547:1\n20#2,2:548\n20#2,2:550\n20#2,2:552\n*S KotlinDebug\n*F\n+ 1 ZkWallet.kt\ncom/nodle/wallet/zksync/ZkWallet$getTransferFees$2\n*L\n123#1:548,2\n134#1:550,2\n135#1:552,2\n*E\n"})
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "Lcom/nodle/wallet/fee/FeeInToken;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.nodle.wallet.zksync.ZkWallet$getTransferFees$2", f = "ZkWallet.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
public final class ZkWallet$getTransferFees$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super FeeInToken>, Object> {
    final /* synthetic */ BigInteger $amount;
    final /* synthetic */ String $to;
    final /* synthetic */ GenericToken $token;
    int label;
    final /* synthetic */ ZkWallet this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ZkWallet$getTransferFees$2(GenericToken genericToken, ZkWallet zkWallet, String str, BigInteger bigInteger, Continuation<? super ZkWallet$getTransferFees$2> continuation) {
        super(2, continuation);
        this.$token = genericToken;
        this.this$0 = zkWallet;
        this.$to = str;
        this.$amount = bigInteger;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new ZkWallet$getTransferFees$2(this.$token, this.this$0, this.$to, this.$amount, continuation);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            boolean z2 = this.$token instanceof EthToken;
            String address = this.this$0.getAddress();
            String address2 = z2 ? this.$to : this.$token.getAddress();
            String encodeTransfer = z2 ? ZkSyncContract.BINARY : ERC20.encodeTransfer(address2, this.$amount);
            BigInteger bigInteger = BigInteger.ZERO;
            Transaction createFunctionCallTransaction = Transaction.createFunctionCallTransaction(address, address2, bigInteger, bigInteger, encodeTransfer);
            ZkSyncWallet access$getWallet$p = this.this$0.wallet;
            if (access$getWallet$p == null) {
                Intrinsics.throwUninitializedPropertyAccessException("wallet");
                access$getWallet$p = null;
            }
            ZksEstimateFee send = access$getWallet$p.getZksync().zksEstimateFee(createFunctionCallTransaction).send();
            if (send.hasError()) {
                NodleLogger nodleLogger = NodleLogger.INSTANCE;
                Timber.Forest.d(c.a("[ZkWallet] estimateResult has error msg: ", send.getError().getMessage()), new Object[0]);
            }
            Fee fee = (Fee) send.getResult();
            BigInteger value = fee.getGasLimit().getValue();
            BigInteger value2 = fee.getGasPerPubdataLimit().getValue();
            BigInteger value3 = fee.getMaxFeePerGas().getValue();
            BigInteger value4 = fee.getMaxPriorityFeePerGas().getValue();
            Intrinsics.checkNotNull(value);
            Intrinsics.checkNotNull(value3);
            BigInteger multiply = value.multiply(value3);
            Intrinsics.checkNotNullExpressionValue(multiply, "multiply(...)");
            BigInteger valueOf = BigInteger.valueOf((long) 2);
            Intrinsics.checkNotNullExpressionValue(valueOf, "valueOf(...)");
            BigInteger multiply2 = multiply.multiply(valueOf);
            Intrinsics.checkNotNullExpressionValue(multiply2, "multiply(...)");
            Intrinsics.checkNotNull(value4);
            BigInteger add = value3.add(value4);
            Intrinsics.checkNotNullExpressionValue(add, "add(...)");
            BigInteger multiply3 = value.multiply(add);
            Intrinsics.checkNotNullExpressionValue(multiply3, "multiply(...)");
            Intrinsics.checkNotNull(value2);
            BigInteger multiply4 = value2.multiply(value3);
            Intrinsics.checkNotNullExpressionValue(multiply4, "multiply(...)");
            BigInteger add2 = multiply3.add(multiply4);
            Intrinsics.checkNotNullExpressionValue(add2, "add(...)");
            NodleLogger nodleLogger2 = NodleLogger.INSTANCE;
            Timber.Forest forest = Timber.Forest;
            forest.d("[ZkWallet] Transfer fee for " + this.$token.getSymbol() + ": " + add2, new Object[0]);
            forest.d("[ZkWallet] Transfer fee in ETH maxFee: " + multiply2 + ", estimatedGasPrice: " + add + ", estimatedFeeTokenAmount: " + add2, new Object[0]);
            return new FeeInToken(this.$token, multiply2, add2);
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super FeeInToken> continuation) {
        return ((ZkWallet$getTransferFees$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }
}
