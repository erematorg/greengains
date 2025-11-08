package com.nodle.wallet.zksync;

import com.nodle.wallet.zksync.data.ZkSyncConfig;
import io.zksync.ZkSyncWallet;
import io.zksync.protocol.ZkSync;
import java.math.BigInteger;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.FunctionReturnDecoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.request.Transaction;
import org.web3j.protocol.core.methods.response.EthCall;

@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\n \u0002*\u0004\u0018\u00010\u00010\u0001*\u00020\u0003H\n"}, d2 = {"<anonymous>", "Ljava/math/BigInteger;", "kotlin.jvm.PlatformType", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.nodle.wallet.zksync.ZkWallet$getBridgeTransactionDelay$2", f = "ZkWallet.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
public final class ZkWallet$getBridgeTransactionDelay$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super BigInteger>, Object> {
    int label;
    final /* synthetic */ ZkWallet this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ZkWallet$getBridgeTransactionDelay$2(ZkWallet zkWallet, Continuation<? super ZkWallet$getBridgeTransactionDelay$2> continuation) {
        super(2, continuation);
        this.this$0 = zkWallet;
    }

    /* access modifiers changed from: private */
    public static final BigInteger invokeSuspend$lambda$0(Function function, EthCall ethCall) {
        Object value = FunctionReturnDecoder.decode((String) ethCall.getResult(), function.getOutputParameters()).get(0).getValue();
        Intrinsics.checkNotNull(value, "null cannot be cast to non-null type java.math.BigInteger");
        return (BigInteger) value;
    }

    /* access modifiers changed from: private */
    public static final BigInteger invokeSuspend$lambda$1(Function1 function1, Object obj) {
        return (BigInteger) function1.invoke(obj);
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new ZkWallet$getBridgeTransactionDelay$2(this.this$0, continuation);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            Function function = new Function("delay", CollectionsKt.emptyList(), CollectionsKt.listOf(TypeReference.create(Uint256.class)));
            String encode = FunctionEncoder.encode(function);
            ZkSyncWallet access$getWallet$p = this.this$0.wallet;
            ZkSyncConfig zkSyncConfig = null;
            if (access$getWallet$p == null) {
                Intrinsics.throwUninitializedPropertyAccessException("wallet");
                access$getWallet$p = null;
            }
            ZkSync zksync = access$getWallet$p.getZksync();
            String address = this.this$0.getAddress();
            ZkSyncConfig access$getConfig$p = this.this$0.config;
            if (access$getConfig$p == null) {
                Intrinsics.throwUninitializedPropertyAccessException("config");
            } else {
                zkSyncConfig = access$getConfig$p;
            }
            return zksync.ethCall(Transaction.createEthCallTransaction(address, zkSyncConfig.getBridgeContract(), encode), DefaultBlockParameterName.LATEST).sendAsync().thenApply(new c(new b(function, 0), 0)).join();
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super BigInteger> continuation) {
        return ((ZkWallet$getBridgeTransactionDelay$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }
}
