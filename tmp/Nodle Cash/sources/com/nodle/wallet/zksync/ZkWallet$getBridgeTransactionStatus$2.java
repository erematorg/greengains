package com.nodle.wallet.zksync;

import com.nodle.wallet.zksync.data.api.types.BridgeTransactionStatus;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.CoroutineScope;

@SourceDebugExtension({"SMAP\nZkWallet.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ZkWallet.kt\ncom/nodle/wallet/zksync/ZkWallet$getBridgeTransactionStatus$2\n+ 2 NodleLogger.kt\nio/nodle/cash/core/android/utils/NodleLogger\n*L\n1#1,547:1\n20#2,2:548\n*S KotlinDebug\n*F\n+ 1 ZkWallet.kt\ncom/nodle/wallet/zksync/ZkWallet$getBridgeTransactionStatus$2\n*L\n311#1:548,2\n*E\n"})
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "Lcom/nodle/wallet/zksync/data/api/types/BridgeTransactionStatus;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.nodle.wallet.zksync.ZkWallet$getBridgeTransactionStatus$2", f = "ZkWallet.kt", i = {1, 2, 2, 2}, l = {306, 307, 309}, m = "invokeSuspend", n = {"hash", "hash", "proposal", "currentBlock"}, s = {"L$0", "L$0", "L$1", "L$2"})
public final class ZkWallet$getBridgeTransactionStatus$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super BridgeTransactionStatus>, Object> {
    final /* synthetic */ String $transactionId;
    Object L$0;
    Object L$1;
    Object L$2;
    int label;
    final /* synthetic */ ZkWallet this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ZkWallet$getBridgeTransactionStatus$2(ZkWallet zkWallet, String str, Continuation<? super ZkWallet$getBridgeTransactionStatus$2> continuation) {
        super(2, continuation);
        this.this$0 = zkWallet;
        this.$transactionId = str;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new ZkWallet$getBridgeTransactionStatus$2(this.this$0, this.$transactionId, continuation);
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x0068  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0094 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0095  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r6) {
        /*
            r5 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r5.label
            r2 = 3
            r3 = 2
            r4 = 1
            if (r1 == 0) goto L_0x0036
            if (r1 == r4) goto L_0x0032
            if (r1 == r3) goto L_0x002a
            if (r1 != r2) goto L_0x0022
            java.lang.Object r0 = r5.L$2
            java.math.BigInteger r0 = (java.math.BigInteger) r0
            java.lang.Object r1 = r5.L$1
            com.nodle.wallet.zksync.data.api.types.Proposal r1 = (com.nodle.wallet.zksync.data.api.types.Proposal) r1
            java.lang.Object r5 = r5.L$0
            java.lang.String r5 = (java.lang.String) r5
            kotlin.ResultKt.throwOnFailure(r6)
            goto L_0x0098
        L_0x0022:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L_0x002a:
            java.lang.Object r1 = r5.L$0
            java.lang.String r1 = (java.lang.String) r1
            kotlin.ResultKt.throwOnFailure(r6)
            goto L_0x005e
        L_0x0032:
            kotlin.ResultKt.throwOnFailure(r6)
            goto L_0x004a
        L_0x0036:
            kotlin.ResultKt.throwOnFailure(r6)
            com.nodle.wallet.zksync.ZkWallet r6 = r5.this$0
            com.nodle.wallet.zksync.utils.BridgeProposalProvider r6 = r6.bridgeProposalProvider
            java.lang.String r1 = r5.$transactionId
            r5.label = r4
            java.lang.Object r6 = r6.getHashForProposal(r1, r5)
            if (r6 != r0) goto L_0x004a
            return r0
        L_0x004a:
            r1 = r6
            java.lang.String r1 = (java.lang.String) r1
            com.nodle.wallet.zksync.ZkWallet r6 = r5.this$0
            java.lang.Object r4 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r1)
            r5.L$0 = r4
            r5.label = r3
            java.lang.Object r6 = r6.queryProposalByHash(r1, r5)
            if (r6 != r0) goto L_0x005e
            return r0
        L_0x005e:
            com.nodle.wallet.zksync.data.api.types.Proposal r6 = (com.nodle.wallet.zksync.data.api.types.Proposal) r6
            com.nodle.wallet.zksync.ZkWallet r3 = r5.this$0
            io.zksync.ZkSyncWallet r3 = r3.wallet
            if (r3 != 0) goto L_0x006e
            java.lang.String r3 = "wallet"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r3)
            r3 = 0
        L_0x006e:
            io.zksync.protocol.ZkSync r3 = r3.getZksync()
            org.web3j.protocol.core.Request r3 = r3.ethBlockNumber()
            org.web3j.protocol.core.Response r3 = r3.send()
            org.web3j.protocol.core.methods.response.EthBlockNumber r3 = (org.web3j.protocol.core.methods.response.EthBlockNumber) r3
            java.math.BigInteger r3 = r3.getBlockNumber()
            com.nodle.wallet.zksync.ZkWallet r4 = r5.this$0
            java.lang.Object r1 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r1)
            r5.L$0 = r1
            r5.L$1 = r6
            r5.L$2 = r3
            r5.label = r2
            java.lang.Object r5 = r4.getBridgeTransactionDelay(r5)
            if (r5 != r0) goto L_0x0095
            return r0
        L_0x0095:
            r1 = r6
            r0 = r3
            r6 = r5
        L_0x0098:
            java.math.BigInteger r6 = (java.math.BigInteger) r6
            com.nodle.wallet.zksync.data.api.types.BridgeTransactionStatus r5 = new com.nodle.wallet.zksync.data.api.types.BridgeTransactionStatus
            kotlin.jvm.internal.Intrinsics.checkNotNull(r0)
            r5.<init>(r1, r6, r0)
            io.nodle.cash.core.android.utils.NodleLogger r6 = io.nodle.cash.core.android.utils.NodleLogger.INSTANCE
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            java.lang.String r0 = "[ZkWallet] BridgeTransactionStatus: "
            r6.<init>(r0)
            r6.append(r1)
            java.lang.String r6 = r6.toString()
            timber.log.Timber$Forest r0 = timber.log.Timber.Forest
            r1 = 0
            java.lang.Object[] r1 = new java.lang.Object[r1]
            r0.d(r6, r1)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.nodle.wallet.zksync.ZkWallet$getBridgeTransactionStatus$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super BridgeTransactionStatus> continuation) {
        return ((ZkWallet$getBridgeTransactionStatus$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }
}
