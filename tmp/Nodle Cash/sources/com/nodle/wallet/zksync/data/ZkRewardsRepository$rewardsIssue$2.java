package com.nodle.wallet.zksync.data;

import com.nodle.wallet.zksync.data.api.ZkRewardsApi;
import com.nodle.wallet.zksync.data.api.model.ApiZkRewardsIssueResponse;
import com.nodle.wallet.zksync.data.api.model.ApiZkRewardsRequest;
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

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "Lcom/nodle/wallet/zksync/data/api/model/ApiZkRewardsIssueResponse;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.nodle.wallet.zksync.data.ZkRewardsRepository$rewardsIssue$2", f = "ZkRewardsRepository.kt", i = {}, l = {21}, m = "invokeSuspend", n = {}, s = {})
public final class ZkRewardsRepository$rewardsIssue$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super ApiZkRewardsIssueResponse>, Object> {
    final /* synthetic */ ApiZkRewardsRequest $request;
    int label;
    final /* synthetic */ ZkRewardsRepository this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ZkRewardsRepository$rewardsIssue$2(ZkRewardsRepository zkRewardsRepository, ApiZkRewardsRequest apiZkRewardsRequest, Continuation<? super ZkRewardsRepository$rewardsIssue$2> continuation) {
        super(2, continuation);
        this.this$0 = zkRewardsRepository;
        this.$request = apiZkRewardsRequest;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new ZkRewardsRepository$rewardsIssue$2(this.this$0, this.$request, continuation);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i3 = this.label;
        if (i3 == 0) {
            ResultKt.throwOnFailure(obj);
            ZkRewardsApi access$getZkRewardsApi$p = this.this$0.zkRewardsApi;
            ApiZkRewardsRequest apiZkRewardsRequest = this.$request;
            this.label = 1;
            obj = access$getZkRewardsApi$p.issue(apiZkRewardsRequest, this);
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
            ApiZkRewardsIssueResponse apiZkRewardsIssueResponse = (ApiZkRewardsIssueResponse) response.body();
            if (apiZkRewardsIssueResponse != null) {
                return apiZkRewardsIssueResponse;
            }
            throw new NodleHttpException((Throwable) null, "Rewards issue error empty response body, response: " + response, 1, (DefaultConstructorMarker) null);
        }
        throw NodleHttpException.Companion.fromResponse(response);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super ApiZkRewardsIssueResponse> continuation) {
        return ((ZkRewardsRepository$rewardsIssue$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }
}
