package com.nodle.wallet.zksync.utils;

import io.nodle.cash.core.android.utils.CoroutineDispatchers;
import io.nodle.cash.substrate.SubstrateEnvironmentRepository;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0019\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0016\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tH@¢\u0006\u0002\u0010\u000bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/nodle/wallet/zksync/utils/BridgeProposalProvider;", "", "substrateEnvironmentRepository", "Lio/nodle/cash/substrate/SubstrateEnvironmentRepository;", "coroutineDispatchers", "Lio/nodle/cash/core/android/utils/CoroutineDispatchers;", "<init>", "(Lio/nodle/cash/substrate/SubstrateEnvironmentRepository;Lio/nodle/cash/core/android/utils/CoroutineDispatchers;)V", "getHashForProposal", "", "id", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "zksync_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class BridgeProposalProvider {
    @NotNull
    private final CoroutineDispatchers coroutineDispatchers;
    /* access modifiers changed from: private */
    @NotNull
    public final SubstrateEnvironmentRepository substrateEnvironmentRepository;

    @Inject
    public BridgeProposalProvider(@NotNull SubstrateEnvironmentRepository substrateEnvironmentRepository2, @NotNull CoroutineDispatchers coroutineDispatchers2) {
        Intrinsics.checkNotNullParameter(substrateEnvironmentRepository2, "substrateEnvironmentRepository");
        Intrinsics.checkNotNullParameter(coroutineDispatchers2, "coroutineDispatchers");
        this.substrateEnvironmentRepository = substrateEnvironmentRepository2;
        this.coroutineDispatchers = coroutineDispatchers2;
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0035  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0023  */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object getHashForProposal(@org.jetbrains.annotations.NotNull java.lang.String r6, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.lang.String> r7) {
        /*
            r5 = this;
            boolean r0 = r7 instanceof com.nodle.wallet.zksync.utils.BridgeProposalProvider$getHashForProposal$1
            if (r0 == 0) goto L_0x0013
            r0 = r7
            com.nodle.wallet.zksync.utils.BridgeProposalProvider$getHashForProposal$1 r0 = (com.nodle.wallet.zksync.utils.BridgeProposalProvider$getHashForProposal$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.label = r1
            goto L_0x0018
        L_0x0013:
            com.nodle.wallet.zksync.utils.BridgeProposalProvider$getHashForProposal$1 r0 = new com.nodle.wallet.zksync.utils.BridgeProposalProvider$getHashForProposal$1
            r0.<init>(r5, r7)
        L_0x0018:
            java.lang.Object r7 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0035
            if (r2 != r3) goto L_0x002d
            java.lang.Object r5 = r0.L$0
            java.lang.String r5 = (java.lang.String) r5
            kotlin.ResultKt.throwOnFailure(r7)
            goto L_0x0053
        L_0x002d:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L_0x0035:
            kotlin.ResultKt.throwOnFailure(r7)
            io.nodle.cash.core.android.utils.CoroutineDispatchers r7 = r5.coroutineDispatchers
            kotlinx.coroutines.CoroutineDispatcher r7 = r7.getIo()
            com.nodle.wallet.zksync.utils.BridgeProposalProvider$getHashForProposal$2 r2 = new com.nodle.wallet.zksync.utils.BridgeProposalProvider$getHashForProposal$2
            r4 = 0
            r2.<init>(r6, r5, r4)
            java.lang.Object r5 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r6)
            r0.L$0 = r5
            r0.label = r3
            java.lang.Object r7 = kotlinx.coroutines.BuildersKt.withContext(r7, r2, r0)
            if (r7 != r1) goto L_0x0053
            return r1
        L_0x0053:
            java.lang.String r5 = "withContext(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r7, r5)
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.nodle.wallet.zksync.utils.BridgeProposalProvider.getHashForProposal(java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
