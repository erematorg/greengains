package com.reown.android.internal.common.modal.domain.usecase;

import com.reown.android.internal.common.modal.AppKitApiRepository;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005JH\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\t2\u000e\u0010\r\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\u000e2\u000e\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\u000eHB¢\u0006\u0002\u0010\u0010R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lcom/reown/android/internal/common/modal/domain/usecase/GetWalletsUseCase;", "Lcom/reown/android/internal/common/modal/domain/usecase/GetWalletsUseCaseInterface;", "appKitApiRepository", "Lcom/reown/android/internal/common/modal/AppKitApiRepository;", "<init>", "(Lcom/reown/android/internal/common/modal/AppKitApiRepository;)V", "invoke", "Lcom/reown/android/internal/common/modal/data/model/WalletListing;", "sdkType", "", "page", "", "search", "excludeIds", "", "includeIds", "(Ljava/lang/String;ILjava/lang/String;Ljava/util/List;Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class GetWalletsUseCase implements GetWalletsUseCaseInterface {
    @NotNull
    private final AppKitApiRepository appKitApiRepository;

    public GetWalletsUseCase(@NotNull AppKitApiRepository appKitApiRepository2) {
        Intrinsics.checkNotNullParameter(appKitApiRepository2, "appKitApiRepository");
        this.appKitApiRepository = appKitApiRepository2;
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x0049  */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x0025  */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object invoke(@org.jetbrains.annotations.NotNull java.lang.String r9, int r10, @org.jetbrains.annotations.Nullable java.lang.String r11, @org.jetbrains.annotations.Nullable java.util.List<java.lang.String> r12, @org.jetbrains.annotations.Nullable java.util.List<java.lang.String> r13, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super com.reown.android.internal.common.modal.data.model.WalletListing> r14) {
        /*
            r8 = this;
            boolean r0 = r14 instanceof com.reown.android.internal.common.modal.domain.usecase.GetWalletsUseCase$invoke$1
            if (r0 == 0) goto L_0x0014
            r0 = r14
            com.reown.android.internal.common.modal.domain.usecase.GetWalletsUseCase$invoke$1 r0 = (com.reown.android.internal.common.modal.domain.usecase.GetWalletsUseCase$invoke$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0014
            int r1 = r1 - r2
            r0.label = r1
        L_0x0012:
            r7 = r0
            goto L_0x001a
        L_0x0014:
            com.reown.android.internal.common.modal.domain.usecase.GetWalletsUseCase$invoke$1 r0 = new com.reown.android.internal.common.modal.domain.usecase.GetWalletsUseCase$invoke$1
            r0.<init>(r8, r14)
            goto L_0x0012
        L_0x001a:
            java.lang.Object r14 = r7.result
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r7.label
            r2 = 1
            if (r1 == 0) goto L_0x0049
            if (r1 != r2) goto L_0x0041
            java.lang.Object r8 = r7.L$3
            java.util.List r8 = (java.util.List) r8
            java.lang.Object r8 = r7.L$2
            java.util.List r8 = (java.util.List) r8
            java.lang.Object r8 = r7.L$1
            java.lang.String r8 = (java.lang.String) r8
            java.lang.Object r8 = r7.L$0
            java.lang.String r8 = (java.lang.String) r8
            kotlin.ResultKt.throwOnFailure(r14)
            kotlin.Result r14 = (kotlin.Result) r14
            java.lang.Object r8 = r14.m8988unboximpl()
            goto L_0x0076
        L_0x0041:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L_0x0049:
            kotlin.ResultKt.throwOnFailure(r14)
            com.reown.android.internal.common.modal.AppKitApiRepository r1 = r8.appKitApiRepository
            java.lang.Object r8 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r9)
            r7.L$0 = r8
            java.lang.Object r8 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r11)
            r7.L$1 = r8
            java.lang.Object r8 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r12)
            r7.L$2 = r8
            java.lang.Object r8 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r13)
            r7.L$3 = r8
            r7.I$0 = r10
            r7.label = r2
            r2 = r9
            r3 = r10
            r4 = r11
            r5 = r12
            r6 = r13
            java.lang.Object r8 = r1.m8747getWalletshUnOzRk(r2, r3, r4, r5, r6, r7)
            if (r8 != r0) goto L_0x0076
            return r0
        L_0x0076:
            kotlin.ResultKt.throwOnFailure(r8)
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.reown.android.internal.common.modal.domain.usecase.GetWalletsUseCase.invoke(java.lang.String, int, java.lang.String, java.util.List, java.util.List, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
