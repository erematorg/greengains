package com.nodle.wallet.zksync.data;

import com.nodle.wallet.zksync.data.api.ZkRewardsApi;
import com.nodle.wallet.zksync.data.api.model.ApiRewardsEstimateFeeRequest;
import com.nodle.wallet.zksync.data.api.model.ApiRewardsEstimateFeeResponse;
import io.nodle.cash.data.error.NodleHttpException;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlinx.coroutines.CoroutineScope;
import retrofit2.Response;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.nodle.wallet.zksync.data.ZkRewardsRepository$rewardsEstimateGasLimit$2", f = "ZkRewardsRepository.kt", i = {}, l = {31}, m = "invokeSuspend", n = {}, s = {})
public final class ZkRewardsRepository$rewardsEstimateGasLimit$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super String>, Object> {
    final /* synthetic */ String $address;
    int label;
    final /* synthetic */ ZkRewardsRepository this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ZkRewardsRepository$rewardsEstimateGasLimit$2(ZkRewardsRepository zkRewardsRepository, String str, Continuation<? super ZkRewardsRepository$rewardsEstimateGasLimit$2> continuation) {
        super(2, continuation);
        this.this$0 = zkRewardsRepository;
        this.$address = str;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new ZkRewardsRepository$rewardsEstimateGasLimit$2(this.this$0, this.$address, continuation);
    }

    public final Object invokeSuspend(Object obj) {
        String gasLimit;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i3 = this.label;
        if (i3 == 0) {
            ResultKt.throwOnFailure(obj);
            ZkRewardsApi access$getZkRewardsApi$p = this.this$0.zkRewardsApi;
            ApiRewardsEstimateFeeRequest apiRewardsEstimateFeeRequest = new ApiRewardsEstimateFeeRequest(this.$address);
            this.label = 1;
            obj = access$getZkRewardsApi$p.estimateFee(apiRewardsEstimateFeeRequest, this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i3 == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        Response response = (Response) obj;
        if (response.isSuccessful()) {
            ApiRewardsEstimateFeeResponse apiRewardsEstimateFeeResponse = (ApiRewardsEstimateFeeResponse) response.body();
            if (apiRewardsEstimateFeeResponse != null && (gasLimit = apiRewardsEstimateFeeResponse.getGasLimit()) != null) {
                return gasLimit;
            }
            throw new NodleHttpException((Throwable) null, "Rewards estimate fee error no gasLimit, response: " + response, 1, (DefaultConstructorMarker) null);
        }
        throw NodleHttpException.Companion.fromResponse(response);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super String> continuation) {
        return ((ZkRewardsRepository$rewardsEstimateGasLimit$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }
}
