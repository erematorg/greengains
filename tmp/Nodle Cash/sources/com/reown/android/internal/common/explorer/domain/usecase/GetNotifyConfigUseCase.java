package com.reown.android.internal.common.explorer.domain.usecase;

import com.reown.android.internal.common.explorer.ExplorerRepository;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u001e\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u0006\u0010\t\u001a\u00020\nHB¢\u0006\u0004\b\u000b\u0010\fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/reown/android/internal/common/explorer/domain/usecase/GetNotifyConfigUseCase;", "", "explorerRepository", "Lcom/reown/android/internal/common/explorer/ExplorerRepository;", "<init>", "(Lcom/reown/android/internal/common/explorer/ExplorerRepository;)V", "invoke", "Lkotlin/Result;", "Lcom/reown/android/internal/common/explorer/data/model/NotifyConfig;", "appDomain", "", "invoke-gIAlu-s", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nGetNotifyConfigUseCase.kt\nKotlin\n*S Kotlin\n*F\n+ 1 GetNotifyConfigUseCase.kt\ncom/reown/android/internal/common/explorer/domain/usecase/GetNotifyConfigUseCase\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,9:1\n1#2:10\n*E\n"})
public final class GetNotifyConfigUseCase {
    @NotNull
    private final ExplorerRepository explorerRepository;

    public GetNotifyConfigUseCase(@NotNull ExplorerRepository explorerRepository2) {
        Intrinsics.checkNotNullParameter(explorerRepository2, "explorerRepository");
        this.explorerRepository = explorerRepository2;
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x003b  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0023  */
    @org.jetbrains.annotations.Nullable
    /* renamed from: invoke-gIAlu-s  reason: not valid java name */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object m8735invokegIAlus(@org.jetbrains.annotations.NotNull java.lang.String r5, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Result<com.reown.android.internal.common.explorer.data.model.NotifyConfig>> r6) {
        /*
            r4 = this;
            boolean r0 = r6 instanceof com.reown.android.internal.common.explorer.domain.usecase.GetNotifyConfigUseCase$invoke$1
            if (r0 == 0) goto L_0x0013
            r0 = r6
            com.reown.android.internal.common.explorer.domain.usecase.GetNotifyConfigUseCase$invoke$1 r0 = (com.reown.android.internal.common.explorer.domain.usecase.GetNotifyConfigUseCase$invoke$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.label = r1
            goto L_0x0018
        L_0x0013:
            com.reown.android.internal.common.explorer.domain.usecase.GetNotifyConfigUseCase$invoke$1 r0 = new com.reown.android.internal.common.explorer.domain.usecase.GetNotifyConfigUseCase$invoke$1
            r0.<init>(r4, r6)
        L_0x0018:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x003b
            if (r2 != r3) goto L_0x0033
            java.lang.Object r4 = r0.L$1
            com.reown.android.internal.common.explorer.domain.usecase.GetNotifyConfigUseCase r4 = (com.reown.android.internal.common.explorer.domain.usecase.GetNotifyConfigUseCase) r4
            java.lang.Object r4 = r0.L$0
            java.lang.String r4 = (java.lang.String) r4
            kotlin.ResultKt.throwOnFailure(r6)     // Catch:{ all -> 0x0031 }
            goto L_0x005a
        L_0x0031:
            r4 = move-exception
            goto L_0x0061
        L_0x0033:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L_0x003b:
            kotlin.ResultKt.throwOnFailure(r6)
            kotlin.Result$Companion r6 = kotlin.Result.Companion     // Catch:{ all -> 0x0031 }
            com.reown.android.internal.common.explorer.ExplorerRepository r6 = r4.explorerRepository     // Catch:{ all -> 0x0031 }
            java.lang.Object r2 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r5)     // Catch:{ all -> 0x0031 }
            r0.L$0 = r2     // Catch:{ all -> 0x0031 }
            java.lang.Object r4 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r4)     // Catch:{ all -> 0x0031 }
            r0.L$1 = r4     // Catch:{ all -> 0x0031 }
            r4 = 0
            r0.I$0 = r4     // Catch:{ all -> 0x0031 }
            r0.label = r3     // Catch:{ all -> 0x0031 }
            java.lang.Object r6 = r6.getNotifyConfig(r5, r0)     // Catch:{ all -> 0x0031 }
            if (r6 != r1) goto L_0x005a
            return r1
        L_0x005a:
            com.reown.android.internal.common.explorer.data.model.NotifyConfig r6 = (com.reown.android.internal.common.explorer.data.model.NotifyConfig) r6     // Catch:{ all -> 0x0031 }
            java.lang.Object r4 = kotlin.Result.m8979constructorimpl(r6)     // Catch:{ all -> 0x0031 }
            goto L_0x006b
        L_0x0061:
            kotlin.Result$Companion r5 = kotlin.Result.Companion
            java.lang.Object r4 = kotlin.ResultKt.createFailure(r4)
            java.lang.Object r4 = kotlin.Result.m8979constructorimpl(r4)
        L_0x006b:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.reown.android.internal.common.explorer.domain.usecase.GetNotifyConfigUseCase.m8735invokegIAlus(java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
