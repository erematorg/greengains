package com.nodle.wallet.zksync;

import A.a;
import androidx.browser.trusted.c;
import com.nodle.wallet.token.GenericToken;
import com.nodle.wallet.zksync.data.ZkPaymasterData;
import io.nodle.cash.core.android.utils.NodleLogger;
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
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.CoroutineScope;
import timber.log.Timber;

@SourceDebugExtension({"SMAP\nZkWallet.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ZkWallet.kt\ncom/nodle/wallet/zksync/ZkWallet$transferWithPaymaster$2\n+ 2 NodleLogger.kt\nio/nodle/cash/core/android/utils/NodleLogger\n*L\n1#1,547:1\n20#2,2:548\n20#2,2:550\n20#2,2:552\n*S KotlinDebug\n*F\n+ 1 ZkWallet.kt\ncom/nodle/wallet/zksync/ZkWallet$transferWithPaymaster$2\n*L\n146#1:548,2\n155#1:550,2\n156#1:552,2\n*E\n"})
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.nodle.wallet.zksync.ZkWallet$transferWithPaymaster$2", f = "ZkWallet.kt", i = {}, l = {147}, m = "invokeSuspend", n = {}, s = {})
public final class ZkWallet$transferWithPaymaster$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super String>, Object> {
    final /* synthetic */ BigInteger $amount;
    final /* synthetic */ GenericToken $feeToken;
    final /* synthetic */ String $to;
    final /* synthetic */ GenericToken $token;
    int label;
    final /* synthetic */ ZkWallet this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ZkWallet$transferWithPaymaster$2(GenericToken genericToken, BigInteger bigInteger, String str, ZkWallet zkWallet, GenericToken genericToken2, Continuation<? super ZkWallet$transferWithPaymaster$2> continuation) {
        super(2, continuation);
        this.$token = genericToken;
        this.$amount = bigInteger;
        this.$to = str;
        this.this$0 = zkWallet;
        this.$feeToken = genericToken2;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new ZkWallet$transferWithPaymaster$2(this.$token, this.$amount, this.$to, this.this$0, this.$feeToken, continuation);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i3 = this.label;
        if (i3 == 0) {
            ResultKt.throwOnFailure(obj);
            NodleLogger nodleLogger = NodleLogger.INSTANCE;
            String symbol = this.$token.getSymbol();
            BigInteger bigInteger = this.$amount;
            String str = this.$to;
            Timber.Forest.d("[ZkWallet] Transfer with paymaster for " + symbol + ": " + bigInteger + " to " + str, new Object[0]);
            ZkWallet zkWallet = this.this$0;
            String str2 = this.$to;
            BigInteger bigInteger2 = this.$amount;
            GenericToken genericToken = this.$token;
            GenericToken genericToken2 = this.$feeToken;
            this.label = 1;
            obj = zkWallet.getPaymasterTransactionData(str2, bigInteger2, genericToken, genericToken2, this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i3 == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        Pair pair = (Pair) obj;
        ZkWallet zkWallet2 = this.this$0;
        String address = zkWallet2.getAddress();
        String address2 = this.$token.getAddress();
        BigInteger bigInteger3 = BigInteger.ZERO;
        Intrinsics.checkNotNullExpressionValue(bigInteger3, "ZERO");
        String executeWithPaymaster = zkWallet2.executeWithPaymaster(address, address2, bigInteger3, (String) pair.component1(), (ZkPaymasterData) pair.component2());
        NodleLogger nodleLogger2 = NodleLogger.INSTANCE;
        String a2 = c.a("[ZkWallet] Transfer receipt: ", executeWithPaymaster);
        Timber.Forest forest = Timber.Forest;
        forest.d(a2, new Object[0]);
        forest.d(a.l("[ZkWallet] Transfer transactions: ", this.this$0.ZKSYNC_MAINNET_EXPLORER, executeWithPaymaster), new Object[0]);
        return executeWithPaymaster;
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super String> continuation) {
        return ((ZkWallet$transferWithPaymaster$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }
}
