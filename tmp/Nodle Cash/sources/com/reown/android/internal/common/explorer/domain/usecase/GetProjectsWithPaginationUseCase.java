package com.reown.android.internal.common.explorer.domain.usecase;

import com.reown.android.internal.common.explorer.ExplorerRepository;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J<\u0010\u0006\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b0\u00072\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000eHB¢\u0006\u0004\b\u0010\u0010\u0011R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/reown/android/internal/common/explorer/domain/usecase/GetProjectsWithPaginationUseCase;", "", "explorerRepository", "Lcom/reown/android/internal/common/explorer/ExplorerRepository;", "<init>", "(Lcom/reown/android/internal/common/explorer/ExplorerRepository;)V", "invoke", "Lkotlin/Result;", "", "Lcom/reown/android/internal/common/explorer/data/model/Project;", "page", "", "entries", "isVerified", "", "isFeatured", "invoke-yxL6bBk", "(IIZZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nGetProjectsWithPaginationUseCase.kt\nKotlin\n*S Kotlin\n*F\n+ 1 GetProjectsWithPaginationUseCase.kt\ncom/reown/android/internal/common/explorer/domain/usecase/GetProjectsWithPaginationUseCase\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,12:1\n1#2:13\n1056#3:14\n*S KotlinDebug\n*F\n+ 1 GetProjectsWithPaginationUseCase.kt\ncom/reown/android/internal/common/explorer/domain/usecase/GetProjectsWithPaginationUseCase\n*L\n10#1:14\n*E\n"})
public final class GetProjectsWithPaginationUseCase {
    @NotNull
    private final ExplorerRepository explorerRepository;

    public GetProjectsWithPaginationUseCase(@NotNull ExplorerRepository explorerRepository2) {
        Intrinsics.checkNotNullParameter(explorerRepository2, "explorerRepository");
        this.explorerRepository = explorerRepository2;
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x0039  */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x0025  */
    @org.jetbrains.annotations.Nullable
    /* renamed from: invoke-yxL6bBk  reason: not valid java name */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object m8736invokeyxL6bBk(int r8, int r9, boolean r10, boolean r11, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Result<? extends java.util.List<com.reown.android.internal.common.explorer.data.model.Project>>> r12) {
        /*
            r7 = this;
            boolean r0 = r12 instanceof com.reown.android.internal.common.explorer.domain.usecase.GetProjectsWithPaginationUseCase$invoke$1
            if (r0 == 0) goto L_0x0014
            r0 = r12
            com.reown.android.internal.common.explorer.domain.usecase.GetProjectsWithPaginationUseCase$invoke$1 r0 = (com.reown.android.internal.common.explorer.domain.usecase.GetProjectsWithPaginationUseCase$invoke$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0014
            int r1 = r1 - r2
            r0.label = r1
        L_0x0012:
            r6 = r0
            goto L_0x001a
        L_0x0014:
            com.reown.android.internal.common.explorer.domain.usecase.GetProjectsWithPaginationUseCase$invoke$1 r0 = new com.reown.android.internal.common.explorer.domain.usecase.GetProjectsWithPaginationUseCase$invoke$1
            r0.<init>(r7, r12)
            goto L_0x0012
        L_0x001a:
            java.lang.Object r12 = r6.result
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r6.label
            r2 = 1
            if (r1 == 0) goto L_0x0039
            if (r1 != r2) goto L_0x0031
            java.lang.Object r7 = r6.L$0
            com.reown.android.internal.common.explorer.domain.usecase.GetProjectsWithPaginationUseCase r7 = (com.reown.android.internal.common.explorer.domain.usecase.GetProjectsWithPaginationUseCase) r7
            kotlin.ResultKt.throwOnFailure(r12)     // Catch:{ all -> 0x002f }
            goto L_0x005e
        L_0x002f:
            r7 = move-exception
            goto L_0x0074
        L_0x0031:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L_0x0039:
            kotlin.ResultKt.throwOnFailure(r12)
            kotlin.Result$Companion r12 = kotlin.Result.Companion     // Catch:{ all -> 0x002f }
            com.reown.android.internal.common.explorer.ExplorerRepository r1 = r7.explorerRepository     // Catch:{ all -> 0x002f }
            r12 = 0
            java.lang.Object r7 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r7)     // Catch:{ all -> 0x002f }
            r6.L$0 = r7     // Catch:{ all -> 0x002f }
            r6.I$0 = r8     // Catch:{ all -> 0x002f }
            r6.I$1 = r9     // Catch:{ all -> 0x002f }
            r6.Z$0 = r10     // Catch:{ all -> 0x002f }
            r6.Z$1 = r11     // Catch:{ all -> 0x002f }
            r6.I$2 = r12     // Catch:{ all -> 0x002f }
            r6.label = r2     // Catch:{ all -> 0x002f }
            r2 = r8
            r3 = r9
            r4 = r10
            r5 = r11
            java.lang.Object r12 = r1.getProjects(r2, r3, r4, r5, r6)     // Catch:{ all -> 0x002f }
            if (r12 != r0) goto L_0x005e
            return r0
        L_0x005e:
            com.reown.android.internal.common.explorer.data.model.ProjectListing r12 = (com.reown.android.internal.common.explorer.data.model.ProjectListing) r12     // Catch:{ all -> 0x002f }
            java.util.List r7 = r12.getProjects()     // Catch:{ all -> 0x002f }
            java.lang.Iterable r7 = (java.lang.Iterable) r7     // Catch:{ all -> 0x002f }
            com.reown.android.internal.common.explorer.domain.usecase.GetProjectsWithPaginationUseCase$invoke_yxL6bBk$lambda$1$$inlined$sortedBy$1 r8 = new com.reown.android.internal.common.explorer.domain.usecase.GetProjectsWithPaginationUseCase$invoke_yxL6bBk$lambda$1$$inlined$sortedBy$1     // Catch:{ all -> 0x002f }
            r8.<init>()     // Catch:{ all -> 0x002f }
            java.util.List r7 = kotlin.collections.CollectionsKt.sortedWith(r7, r8)     // Catch:{ all -> 0x002f }
            java.lang.Object r7 = kotlin.Result.m8979constructorimpl(r7)     // Catch:{ all -> 0x002f }
            goto L_0x007e
        L_0x0074:
            kotlin.Result$Companion r8 = kotlin.Result.Companion
            java.lang.Object r7 = kotlin.ResultKt.createFailure(r7)
            java.lang.Object r7 = kotlin.Result.m8979constructorimpl(r7)
        L_0x007e:
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.reown.android.internal.common.explorer.domain.usecase.GetProjectsWithPaginationUseCase.m8736invokeyxL6bBk(int, int, boolean, boolean, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
