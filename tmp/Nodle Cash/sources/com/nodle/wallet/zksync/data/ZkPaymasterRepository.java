package com.nodle.wallet.zksync.data;

import com.nodle.wallet.zksync.data.api.NodlePaymasterApi;
import com.nodle.wallet.zksync.data.api.ZyfiPaymasterApi;
import com.nodle.wallet.zksync.data.api.model.ApiNodlePaymasterRequest;
import com.nodle.wallet.zksync.data.api.model.ApiZkPaymasterResponse;
import com.nodle.wallet.zksync.data.api.model.ApiZyfiPaymasterRequest;
import io.nodle.cash.core.android.utils.CoroutineDispatchers;
import java.math.BigInteger;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B!\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\u0016\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH@¢\u0006\u0002\u0010\u000eJ\u001e\u0010\u000f\u001a\u00020\u000b2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\f\u001a\u00020\u0012H@¢\u0006\u0002\u0010\u0013J\f\u0010\u0014\u001a\u00020\u000b*\u00020\u0015H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lcom/nodle/wallet/zksync/data/ZkPaymasterRepository;", "", "zyfiPaymasterApi", "Lcom/nodle/wallet/zksync/data/api/ZyfiPaymasterApi;", "nodlePaymasterApi", "Lcom/nodle/wallet/zksync/data/api/NodlePaymasterApi;", "coroutineDispatchers", "Lio/nodle/cash/core/android/utils/CoroutineDispatchers;", "<init>", "(Lcom/nodle/wallet/zksync/data/api/ZyfiPaymasterApi;Lcom/nodle/wallet/zksync/data/api/NodlePaymasterApi;Lio/nodle/cash/core/android/utils/CoroutineDispatchers;)V", "getZyfiPaymasterData", "Lcom/nodle/wallet/zksync/data/ZkPaymasterData;", "request", "Lcom/nodle/wallet/zksync/data/api/model/ApiZyfiPaymasterRequest;", "(Lcom/nodle/wallet/zksync/data/api/model/ApiZyfiPaymasterRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getNodlePaymasterData", "gasLimit", "", "Lcom/nodle/wallet/zksync/data/api/model/ApiNodlePaymasterRequest;", "(Ljava/lang/String;Lcom/nodle/wallet/zksync/data/api/model/ApiNodlePaymasterRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "toPaymasterData", "Lcom/nodle/wallet/zksync/data/api/model/ApiZkPaymasterResponse;", "zksync_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class ZkPaymasterRepository {
    @NotNull
    private final CoroutineDispatchers coroutineDispatchers;
    /* access modifiers changed from: private */
    @NotNull
    public final NodlePaymasterApi nodlePaymasterApi;
    /* access modifiers changed from: private */
    @NotNull
    public final ZyfiPaymasterApi zyfiPaymasterApi;

    @Inject
    public ZkPaymasterRepository(@NotNull ZyfiPaymasterApi zyfiPaymasterApi2, @NotNull NodlePaymasterApi nodlePaymasterApi2, @NotNull CoroutineDispatchers coroutineDispatchers2) {
        Intrinsics.checkNotNullParameter(zyfiPaymasterApi2, "zyfiPaymasterApi");
        Intrinsics.checkNotNullParameter(nodlePaymasterApi2, "nodlePaymasterApi");
        Intrinsics.checkNotNullParameter(coroutineDispatchers2, "coroutineDispatchers");
        this.zyfiPaymasterApi = zyfiPaymasterApi2;
        this.nodlePaymasterApi = nodlePaymasterApi2;
        this.coroutineDispatchers = coroutineDispatchers2;
    }

    /* access modifiers changed from: private */
    public final ZkPaymasterData toPaymasterData(ApiZkPaymasterResponse apiZkPaymasterResponse) {
        String paymaster = apiZkPaymasterResponse.getTxData().getCustomData().getPaymasterParams().getPaymaster();
        String paymasterInput = apiZkPaymasterResponse.getTxData().getCustomData().getPaymasterParams().getPaymasterInput();
        BigInteger bigInteger = new BigInteger(apiZkPaymasterResponse.getGasLimit());
        BigInteger bigInteger2 = new BigInteger(apiZkPaymasterResponse.getTxData().getMaxFeePerGas());
        BigInteger valueOf = BigInteger.valueOf(apiZkPaymasterResponse.getTxData().getCustomData().getGasPerPubdata());
        Intrinsics.checkNotNullExpressionValue(valueOf, "valueOf(...)");
        String feeTokenAmount = apiZkPaymasterResponse.getFeeTokenAmount();
        BigInteger bigInteger3 = feeTokenAmount != null ? new BigInteger(feeTokenAmount) : BigInteger.ZERO;
        Intrinsics.checkNotNull(bigInteger3);
        String estimatedFinalFeeTokenAmount = apiZkPaymasterResponse.getEstimatedFinalFeeTokenAmount();
        BigInteger bigInteger4 = estimatedFinalFeeTokenAmount != null ? new BigInteger(estimatedFinalFeeTokenAmount) : BigInteger.ZERO;
        Intrinsics.checkNotNull(bigInteger4);
        return new ZkPaymasterData(paymaster, paymasterInput, bigInteger, bigInteger2, valueOf, bigInteger3, bigInteger4);
    }

    @Nullable
    public final Object getNodlePaymasterData(@NotNull String str, @NotNull ApiNodlePaymasterRequest apiNodlePaymasterRequest, @NotNull Continuation<? super ZkPaymasterData> continuation) {
        return BuildersKt.withContext(this.coroutineDispatchers.getIo(), new ZkPaymasterRepository$getNodlePaymasterData$2(this, str, apiNodlePaymasterRequest, (Continuation<? super ZkPaymasterRepository$getNodlePaymasterData$2>) null), continuation);
    }

    @Nullable
    public final Object getZyfiPaymasterData(@NotNull ApiZyfiPaymasterRequest apiZyfiPaymasterRequest, @NotNull Continuation<? super ZkPaymasterData> continuation) {
        return BuildersKt.withContext(this.coroutineDispatchers.getIo(), new ZkPaymasterRepository$getZyfiPaymasterData$2(this, apiZyfiPaymasterRequest, (Continuation<? super ZkPaymasterRepository$getZyfiPaymasterData$2>) null), continuation);
    }
}
