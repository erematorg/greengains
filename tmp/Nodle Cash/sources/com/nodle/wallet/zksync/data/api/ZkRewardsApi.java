package com.nodle.wallet.zksync.data.api;

import com.nodle.wallet.zksync.data.api.model.ApiRewardsEstimateFeeRequest;
import com.nodle.wallet.zksync.data.api.model.ApiRewardsEstimateFeeResponse;
import com.nodle.wallet.zksync.data.api.model.ApiZkRewardsIssueResponse;
import com.nodle.wallet.zksync.data.api.model.ApiZkRewardsRequest;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u001e\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u0006H§@¢\u0006\u0002\u0010\u0007J\u001e\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u00032\b\b\u0001\u0010\u0005\u001a\u00020\nH§@¢\u0006\u0002\u0010\u000b¨\u0006\fÀ\u0006\u0003"}, d2 = {"Lcom/nodle/wallet/zksync/data/api/ZkRewardsApi;", "", "issue", "Lretrofit2/Response;", "Lcom/nodle/wallet/zksync/data/api/model/ApiZkRewardsIssueResponse;", "body", "Lcom/nodle/wallet/zksync/data/api/model/ApiZkRewardsRequest;", "(Lcom/nodle/wallet/zksync/data/api/model/ApiZkRewardsRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "estimateFee", "Lcom/nodle/wallet/zksync/data/api/model/ApiRewardsEstimateFeeResponse;", "Lcom/nodle/wallet/zksync/data/api/model/ApiRewardsEstimateFeeRequest;", "(Lcom/nodle/wallet/zksync/data/api/model/ApiRewardsEstimateFeeRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "zksync_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface ZkRewardsApi {
    @Nullable
    @POST("estimate-fee")
    @Headers({"Content-Type: application/json"})
    Object estimateFee(@NotNull @Body ApiRewardsEstimateFeeRequest apiRewardsEstimateFeeRequest, @NotNull Continuation<? super Response<ApiRewardsEstimateFeeResponse>> continuation);

    @Nullable
    @POST("issue")
    @Headers({"Content-Type: application/json"})
    Object issue(@NotNull @Body ApiZkRewardsRequest apiZkRewardsRequest, @NotNull Continuation<? super Response<ApiZkRewardsIssueResponse>> continuation);
}
