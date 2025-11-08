package com.nodle.wallet.zksync.data.api;

import com.nodle.wallet.zksync.data.api.model.ApiZkPaymasterResponse;
import com.nodle.wallet.zksync.data.api.model.ApiZyfiPaymasterRequest;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\b\b\u0001\u0010\u0004\u001a\u00020\u0005H§@¢\u0006\u0002\u0010\u0006¨\u0006\u0007À\u0006\u0003"}, d2 = {"Lcom/nodle/wallet/zksync/data/api/ZyfiPaymasterApi;", "", "getPaymasterData", "Lcom/nodle/wallet/zksync/data/api/model/ApiZkPaymasterResponse;", "body", "Lcom/nodle/wallet/zksync/data/api/model/ApiZyfiPaymasterRequest;", "(Lcom/nodle/wallet/zksync/data/api/model/ApiZyfiPaymasterRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "zksync_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface ZyfiPaymasterApi {
    @Nullable
    @POST("erc20_paymaster/v1")
    @Headers({"Content-Type: application/json"})
    Object getPaymasterData(@NotNull @Body ApiZyfiPaymasterRequest apiZyfiPaymasterRequest, @NotNull Continuation<? super ApiZkPaymasterResponse> continuation);
}
