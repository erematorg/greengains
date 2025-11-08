package com.nodle.wallet.zksync;

import A.a;
import com.nodle.wallet.token.GenericToken;
import io.nodle.cash.core.android.utils.NodleLogger;
import io.zksync.ZkSyncWallet;
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
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import timber.log.Timber;

@SourceDebugExtension({"SMAP\nZkWallet.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ZkWallet.kt\ncom/nodle/wallet/zksync/ZkWallet$transfer$2\n+ 2 NodleLogger.kt\nio/nodle/cash/core/android/utils/NodleLogger\n*L\n1#1,547:1\n20#2,2:548\n20#2,2:550\n*S KotlinDebug\n*F\n+ 1 ZkWallet.kt\ncom/nodle/wallet/zksync/ZkWallet$transfer$2\n*L\n99#1:548,2\n100#1:550,2\n*E\n"})
@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\n \u0002*\u0004\u0018\u00010\u00010\u0001*\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "kotlin.jvm.PlatformType", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.nodle.wallet.zksync.ZkWallet$transfer$2", f = "ZkWallet.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
public final class ZkWallet$transfer$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super String>, Object> {
    final /* synthetic */ BigInteger $amount;
    final /* synthetic */ String $to;
    final /* synthetic */ GenericToken $token;
    int label;
    final /* synthetic */ ZkWallet this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ZkWallet$transfer$2(ZkWallet zkWallet, String str, BigInteger bigInteger, GenericToken genericToken, Continuation<? super ZkWallet$transfer$2> continuation) {
        super(2, continuation);
        this.this$0 = zkWallet;
        this.$to = str;
        this.$amount = bigInteger;
        this.$token = genericToken;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new ZkWallet$transfer$2(this.this$0, this.$to, this.$amount, this.$token, continuation);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            ZkSyncWallet access$getWallet$p = this.this$0.wallet;
            if (access$getWallet$p == null) {
                Intrinsics.throwUninitializedPropertyAccessException("wallet");
                access$getWallet$p = null;
            }
            TransactionReceipt send = access$getWallet$p.transfer(this.$to, this.$amount, this.this$0.toZkToken(this.$token)).send();
            NodleLogger nodleLogger = NodleLogger.INSTANCE;
            Timber.Forest forest = Timber.Forest;
            forest.d("[ZkWallet] Transfer receipt: " + send, new Object[0]);
            forest.d(a.l("[ZkWallet] Transfer transactions: ", this.this$0.ZKSYNC_MAINNET_EXPLORER, send.getTransactionHash()), new Object[0]);
            return send.getTransactionHash();
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super String> continuation) {
        return ((ZkWallet$transfer$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }
}
