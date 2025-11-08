package com.nodle.wallet.zksync.data.api;

import com.nodle.wallet.zksync.data.api.model.ApiNodlePaymasterRequest;
import com.nodle.wallet.zksync.data.api.model.ApiZkPaymasterResponse;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J(\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u00062\b\b\u0001\u0010\u0007\u001a\u00020\bH§@¢\u0006\u0002\u0010\t¨\u0006\nÀ\u0006\u0003"}, d2 = {"Lcom/nodle/wallet/zksync/data/api/NodlePaymasterApi;", "", "getPaymasterData", "Lretrofit2/Response;", "Lcom/nodle/wallet/zksync/data/api/model/ApiZkPaymasterResponse;", "gasLimit", "", "body", "Lcom/nodle/wallet/zksync/data/api/model/ApiNodlePaymasterRequest;", "(Ljava/lang/String;Lcom/nodle/wallet/zksync/data/api/model/ApiNodlePaymasterRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "zksync_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface NodlePaymasterApi {
    @Nullable
    @POST("paymaster")
    @Headers({"Content-Type: application/json"})
    Object getPaymasterData(@NotNull @Query("gas_limit") String str, @NotNull @Body ApiNodlePaymasterRequest apiNodlePaymasterRequest, @NotNull Continuation<? super Response<ApiZkPaymasterResponse>> continuation);
}
