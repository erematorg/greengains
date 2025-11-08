package com.nodle.wallet.zksync.data;

import com.nodle.wallet.zksync.data.api.NodlePaymasterApi;
import com.nodle.wallet.zksync.data.api.model.ApiNodlePaymasterRequest;
import com.nodle.wallet.zksync.data.api.model.ApiZkPaymasterResponse;
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

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "Lcom/nodle/wallet/zksync/data/ZkPaymasterData;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.nodle.wallet.zksync.data.ZkPaymasterRepository$getNodlePaymasterData$2", f = "ZkPaymasterRepository.kt", i = {}, l = {32}, m = "invokeSuspend", n = {}, s = {})
public final class ZkPaymasterRepository$getNodlePaymasterData$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super ZkPaymasterData>, Object> {
    final /* synthetic */ String $gasLimit;
    final /* synthetic */ ApiNodlePaymasterRequest $request;
    int label;
    final /* synthetic */ ZkPaymasterRepository this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ZkPaymasterRepository$getNodlePaymasterData$2(ZkPaymasterRepository zkPaymasterRepository, String str, ApiNodlePaymasterRequest apiNodlePaymasterRequest, Continuation<? super ZkPaymasterRepository$getNodlePaymasterData$2> continuation) {
        super(2, continuation);
        this.this$0 = zkPaymasterRepository;
        this.$gasLimit = str;
        this.$request = apiNodlePaymasterRequest;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new ZkPaymasterRepository$getNodlePaymasterData$2(this.this$0, this.$gasLimit, this.$request, continuation);
    }

    public final Object invokeSuspend(Object obj) {
        ZkPaymasterData access$toPaymasterData;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i3 = this.label;
        if (i3 == 0) {
            ResultKt.throwOnFailure(obj);
            NodlePaymasterApi access$getNodlePaymasterApi$p = this.this$0.nodlePaymasterApi;
            String str = this.$gasLimit;
            ApiNodlePaymasterRequest apiNodlePaymasterRequest = this.$request;
            this.label = 1;
            obj = access$getNodlePaymasterApi$p.getPaymasterData(str, apiNodlePaymasterRequest, this);
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
            ApiZkPaymasterResponse apiZkPaymasterResponse = (ApiZkPaymasterResponse) response.body();
            if (apiZkPaymasterResponse != null && (access$toPaymasterData = this.this$0.toPaymasterData(apiZkPaymasterResponse)) != null) {
                return access$toPaymasterData;
            }
            throw new NodleHttpException((Throwable) null, "Nodle paymaster error empty response body, response: " + response, 1, (DefaultConstructorMarker) null);
        }
        throw NodleHttpException.Companion.fromResponse(response);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super ZkPaymasterData> continuation) {
        return ((ZkPaymasterRepository$getNodlePaymasterData$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }
}
