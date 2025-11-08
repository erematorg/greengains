package com.nodle.wallet.zksync;

import androidx.camera.camera2.internal.C0118y;
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
import timber.log.Timber;

@SourceDebugExtension({"SMAP\nZkWallet.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ZkWallet.kt\ncom/nodle/wallet/zksync/ZkWallet$getBalance$3\n+ 2 NodleLogger.kt\nio/nodle/cash/core/android/utils/NodleLogger\n*L\n1#1,547:1\n20#2,2:548\n*S KotlinDebug\n*F\n+ 1 ZkWallet.kt\ncom/nodle/wallet/zksync/ZkWallet$getBalance$3\n*L\n91#1:548,2\n*E\n"})
@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\n \u0002*\u0004\u0018\u00010\u00010\u0001*\u00020\u0003H\n"}, d2 = {"<anonymous>", "Ljava/math/BigInteger;", "kotlin.jvm.PlatformType", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.nodle.wallet.zksync.ZkWallet$getBalance$3", f = "ZkWallet.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
public final class ZkWallet$getBalance$3 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super BigInteger>, Object> {
    final /* synthetic */ String $address;
    final /* synthetic */ GenericToken $token;
    int label;
    final /* synthetic */ ZkWallet this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ZkWallet$getBalance$3(ZkWallet zkWallet, String str, GenericToken genericToken, Continuation<? super ZkWallet$getBalance$3> continuation) {
        super(2, continuation);
        this.this$0 = zkWallet;
        this.$address = str;
        this.$token = genericToken;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new ZkWallet$getBalance$3(this.this$0, this.$address, this.$token, continuation);
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
            BigInteger send = access$getWallet$p.getBalance(this.$address, this.this$0.toZkToken(this.$token)).send();
            NodleLogger nodleLogger = NodleLogger.INSTANCE;
            StringBuilder l2 = C0118y.l("[ZkWallet] Balance for address=", this.$address, " and token=", this.$token.getSymbol(), ": ");
            l2.append(send);
            Timber.Forest.d(l2.toString(), new Object[0]);
            return send;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super BigInteger> continuation) {
        return ((ZkWallet$getBalance$3) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }
}
