package com.nodle.wallet.zksync;

import com.nodle.wallet.zksync.data.ZkRewardsRepository;
import com.nodle.wallet.zksync.data.ZkSyncConfig;
import com.nodle.wallet.zksync.data.api.model.ApiZkRewardsIssueResponse;
import com.nodle.wallet.zksync.data.api.model.ApiZkRewardsRequest;
import com.nodle.wallet.zksync.data.api.types.RewardsIssue;
import io.nodle.cash.core.android.utils.NodleLogger;
import io.zksync.ZkSyncWallet;
import io.zksync.crypto.eip712.Eip712Domain;
import java.math.BigInteger;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.CoroutineScope;
import timber.log.Timber;

@SourceDebugExtension({"SMAP\nZkWallet.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ZkWallet.kt\ncom/nodle/wallet/zksync/ZkWallet$rewardsIssue$2\n+ 2 NodleLogger.kt\nio/nodle/cash/core/android/utils/NodleLogger\n*L\n1#1,547:1\n20#2,2:548\n20#2,2:550\n*S KotlinDebug\n*F\n+ 1 ZkWallet.kt\ncom/nodle/wallet/zksync/ZkWallet$rewardsIssue$2\n*L\n419#1:548,2\n421#1:550,2\n*E\n"})
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "Lcom/nodle/wallet/zksync/data/api/model/ApiZkRewardsIssueResponse;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.nodle.wallet.zksync.ZkWallet$rewardsIssue$2", f = "ZkWallet.kt", i = {0, 0, 0, 0}, l = {420}, m = "invokeSuspend", n = {"domain", "typedData", "signature", "request"}, s = {"L$0", "L$1", "L$2", "L$3"})
public final class ZkWallet$rewardsIssue$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super ApiZkRewardsIssueResponse>, Object> {
    final /* synthetic */ BigInteger $sequence;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;
    final /* synthetic */ ZkWallet this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ZkWallet$rewardsIssue$2(ZkWallet zkWallet, BigInteger bigInteger, Continuation<? super ZkWallet$rewardsIssue$2> continuation) {
        super(2, continuation);
        this.this$0 = zkWallet;
        this.$sequence = bigInteger;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new ZkWallet$rewardsIssue$2(this.this$0, this.$sequence, continuation);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i3 = this.label;
        if (i3 == 0) {
            ResultKt.throwOnFailure(obj);
            ZkSyncConfig access$getConfig$p = this.this$0.config;
            ZkSyncWallet zkSyncWallet = null;
            if (access$getConfig$p == null) {
                Intrinsics.throwUninitializedPropertyAccessException("config");
                access$getConfig$p = null;
            }
            Eip712Domain eip712Domain = new Eip712Domain("rewards.depin.nodle", "1", Boxing.boxLong(access$getConfig$p.getChainId()));
            RewardsIssue rewardsIssue = new RewardsIssue(this.$sequence);
            ZkSyncWallet access$getWallet$p = this.this$0.wallet;
            if (access$getWallet$p == null) {
                Intrinsics.throwUninitializedPropertyAccessException("wallet");
            } else {
                zkSyncWallet = access$getWallet$p;
            }
            String join = zkSyncWallet.getSigner().signTypedData(eip712Domain, rewardsIssue).join();
            Intrinsics.checkNotNullExpressionValue(join, "join(...)");
            String str = join;
            ApiZkRewardsRequest apiZkRewardsRequest = new ApiZkRewardsRequest(this.this$0.getAddress(), this.$sequence.longValue(), str);
            NodleLogger nodleLogger = NodleLogger.INSTANCE;
            Timber.Forest.d("rewardsIssue request: " + apiZkRewardsRequest, new Object[0]);
            ZkRewardsRepository access$getZkRewardsRepository$p = this.this$0.zkRewardsRepository;
            this.L$0 = SpillingKt.nullOutSpilledVariable(eip712Domain);
            this.L$1 = SpillingKt.nullOutSpilledVariable(rewardsIssue);
            this.L$2 = SpillingKt.nullOutSpilledVariable(str);
            this.L$3 = SpillingKt.nullOutSpilledVariable(apiZkRewardsRequest);
            this.label = 1;
            obj = access$getZkRewardsRepository$p.rewardsIssue(apiZkRewardsRequest, this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i3 == 1) {
            ApiZkRewardsRequest apiZkRewardsRequest2 = (ApiZkRewardsRequest) this.L$3;
            String str2 = (String) this.L$2;
            RewardsIssue rewardsIssue2 = (RewardsIssue) this.L$1;
            Eip712Domain eip712Domain2 = (Eip712Domain) this.L$0;
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ApiZkRewardsIssueResponse apiZkRewardsIssueResponse = (ApiZkRewardsIssueResponse) obj;
        NodleLogger nodleLogger2 = NodleLogger.INSTANCE;
        Timber.Forest.d("rewardsIssue response: " + apiZkRewardsIssueResponse, new Object[0]);
        return apiZkRewardsIssueResponse;
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super ApiZkRewardsIssueResponse> continuation) {
        return ((ZkWallet$rewardsIssue$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }
}
