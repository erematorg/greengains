package com.nodle.wallet.zksync.data;

import com.nodle.wallet.zksync.data.api.ZkRewardsApi;
import com.nodle.wallet.zksync.data.api.model.ApiZkRewardsIssueResponse;
import com.nodle.wallet.zksync.data.api.model.ApiZkRewardsRequest;
import io.nodle.cash.core.android.utils.CoroutineDispatchers;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0019\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0016\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH@¢\u0006\u0002\u0010\fJ\u0016\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000eH@¢\u0006\u0002\u0010\u0010R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lcom/nodle/wallet/zksync/data/ZkRewardsRepository;", "", "zkRewardsApi", "Lcom/nodle/wallet/zksync/data/api/ZkRewardsApi;", "coroutineDispatchers", "Lio/nodle/cash/core/android/utils/CoroutineDispatchers;", "<init>", "(Lcom/nodle/wallet/zksync/data/api/ZkRewardsApi;Lio/nodle/cash/core/android/utils/CoroutineDispatchers;)V", "rewardsIssue", "Lcom/nodle/wallet/zksync/data/api/model/ApiZkRewardsIssueResponse;", "request", "Lcom/nodle/wallet/zksync/data/api/model/ApiZkRewardsRequest;", "(Lcom/nodle/wallet/zksync/data/api/model/ApiZkRewardsRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "rewardsEstimateGasLimit", "", "address", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "zksync_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class ZkRewardsRepository {
    @NotNull
    private final CoroutineDispatchers coroutineDispatchers;
    /* access modifiers changed from: private */
    @NotNull
    public final ZkRewardsApi zkRewardsApi;

    @Inject
    public ZkRewardsRepository(@NotNull ZkRewardsApi zkRewardsApi2, @NotNull CoroutineDispatchers coroutineDispatchers2) {
        Intrinsics.checkNotNullParameter(zkRewardsApi2, "zkRewardsApi");
        Intrinsics.checkNotNullParameter(coroutineDispatchers2, "coroutineDispatchers");
        this.zkRewardsApi = zkRewardsApi2;
        this.coroutineDispatchers = coroutineDispatchers2;
    }

    @Nullable
    public final Object rewardsEstimateGasLimit(@NotNull String str, @NotNull Continuation<? super String> continuation) {
        return BuildersKt.withContext(this.coroutineDispatchers.getIo(), new ZkRewardsRepository$rewardsEstimateGasLimit$2(this, str, (Continuation<? super ZkRewardsRepository$rewardsEstimateGasLimit$2>) null), continuation);
    }

    @Nullable
    public final Object rewardsIssue(@NotNull ApiZkRewardsRequest apiZkRewardsRequest, @NotNull Continuation<? super ApiZkRewardsIssueResponse> continuation) {
        return BuildersKt.withContext(this.coroutineDispatchers.getIo(), new ZkRewardsRepository$rewardsIssue$2(this, apiZkRewardsRequest, (Continuation<? super ZkRewardsRepository$rewardsIssue$2>) null), continuation);
    }
}
