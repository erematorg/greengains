package com.nodle.wallet.zksync.data;

import com.nodle.wallet.zksync.data.api.ZyfiPaymasterApi;
import com.nodle.wallet.zksync.data.api.model.ApiZkPaymasterResponse;
import com.nodle.wallet.zksync.data.api.model.ApiZyfiPaymasterRequest;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "Lcom/nodle/wallet/zksync/data/ZkPaymasterData;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.nodle.wallet.zksync.data.ZkPaymasterRepository$getZyfiPaymasterData$2", f = "ZkPaymasterRepository.kt", i = {}, l = {24}, m = "invokeSuspend", n = {}, s = {})
public final class ZkPaymasterRepository$getZyfiPaymasterData$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super ZkPaymasterData>, Object> {
    final /* synthetic */ ApiZyfiPaymasterRequest $request;
    Object L$0;
    int label;
    final /* synthetic */ ZkPaymasterRepository this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ZkPaymasterRepository$getZyfiPaymasterData$2(ZkPaymasterRepository zkPaymasterRepository, ApiZyfiPaymasterRequest apiZyfiPaymasterRequest, Continuation<? super ZkPaymasterRepository$getZyfiPaymasterData$2> continuation) {
        super(2, continuation);
        this.this$0 = zkPaymasterRepository;
        this.$request = apiZyfiPaymasterRequest;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new ZkPaymasterRepository$getZyfiPaymasterData$2(this.this$0, this.$request, continuation);
    }

    public final Object invokeSuspend(Object obj) {
        ZkPaymasterRepository zkPaymasterRepository;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i3 = this.label;
        if (i3 == 0) {
            ResultKt.throwOnFailure(obj);
            ZkPaymasterRepository zkPaymasterRepository2 = this.this$0;
            ZyfiPaymasterApi access$getZyfiPaymasterApi$p = zkPaymasterRepository2.zyfiPaymasterApi;
            ApiZyfiPaymasterRequest apiZyfiPaymasterRequest = this.$request;
            this.L$0 = zkPaymasterRepository2;
            this.label = 1;
            Object paymasterData = access$getZyfiPaymasterApi$p.getPaymasterData(apiZyfiPaymasterRequest, this);
            if (paymasterData == coroutine_suspended) {
                return coroutine_suspended;
            }
            ZkPaymasterRepository zkPaymasterRepository3 = zkPaymasterRepository2;
            obj = paymasterData;
            zkPaymasterRepository = zkPaymasterRepository3;
        } else if (i3 == 1) {
            zkPaymasterRepository = (ZkPaymasterRepository) this.L$0;
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return zkPaymasterRepository.toPaymasterData((ApiZkPaymasterResponse) obj);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super ZkPaymasterData> continuation) {
        return ((ZkPaymasterRepository$getZyfiPaymasterData$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }
}
