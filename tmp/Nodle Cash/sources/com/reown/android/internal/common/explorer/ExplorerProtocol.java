package com.reown.android.internal.common.explorer;

import com.reown.android.internal.common.KoinApplicationKt;
import com.reown.android.internal.common.di.AndroidCommonDITags;
import com.reown.android.internal.common.explorer.domain.usecase.GetProjectsWithPaginationUseCase;
import com.reown.foundation.util.Logger;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.koin.core.Koin;
import org.koin.core.KoinApplication;
import org.koin.core.parameter.ParametersHolder;
import org.koin.core.qualifier.Qualifier;
import org.koin.core.qualifier.QualifierKt;

@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0011\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J<\u0010\u0011\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00140\u00130\u00122\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u0019H@¢\u0006\u0004\b\u001b\u0010\u001cR\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\u0006\u001a\u00020\u00078BX\u0002¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\b\u0010\tR\u001b\u0010\f\u001a\u00020\r8BX\u0002¢\u0006\f\n\u0004\b\u0010\u0010\u000b\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001d"}, d2 = {"Lcom/reown/android/internal/common/explorer/ExplorerProtocol;", "Lcom/reown/android/internal/common/explorer/ExplorerInterface;", "koinApp", "Lorg/koin/core/KoinApplication;", "<init>", "(Lorg/koin/core/KoinApplication;)V", "getProjectsWithPaginationUseCase", "Lcom/reown/android/internal/common/explorer/domain/usecase/GetProjectsWithPaginationUseCase;", "getGetProjectsWithPaginationUseCase", "()Lcom/reown/android/internal/common/explorer/domain/usecase/GetProjectsWithPaginationUseCase;", "getProjectsWithPaginationUseCase$delegate", "Lkotlin/Lazy;", "logger", "Lcom/reown/foundation/util/Logger;", "getLogger", "()Lcom/reown/foundation/util/Logger;", "logger$delegate", "getProjects", "Lkotlin/Result;", "", "Lcom/reown/android/internal/common/explorer/data/model/Project;", "page", "", "entries", "isVerified", "", "isFeatured", "getProjects-yxL6bBk", "(IIZZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nExplorerProtocol.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ExplorerProtocol.kt\ncom/reown/android/internal/common/explorer/ExplorerProtocol\n+ 2 Koin.kt\norg/koin/core/Koin\n+ 3 Scope.kt\norg/koin/core/scope/Scope\n*L\n1#1,23:1\n124#2,4:24\n124#2,4:29\n142#3:28\n142#3:33\n*S KotlinDebug\n*F\n+ 1 ExplorerProtocol.kt\ncom/reown/android/internal/common/explorer/ExplorerProtocol\n*L\n16#1:24,4\n17#1:29,4\n16#1:28\n17#1:33\n*E\n"})
public final class ExplorerProtocol implements ExplorerInterface {
    @NotNull
    private final Lazy getProjectsWithPaginationUseCase$delegate;
    @NotNull
    private final KoinApplication koinApp;
    @NotNull
    private final Lazy logger$delegate;

    public ExplorerProtocol() {
        this((KoinApplication) null, 1, (DefaultConstructorMarker) null);
    }

    private final GetProjectsWithPaginationUseCase getGetProjectsWithPaginationUseCase() {
        return (GetProjectsWithPaginationUseCase) this.getProjectsWithPaginationUseCase$delegate.getValue();
    }

    private final Logger getLogger() {
        return (Logger) this.logger$delegate.getValue();
    }

    /* access modifiers changed from: private */
    public static final GetProjectsWithPaginationUseCase getProjectsWithPaginationUseCase_delegate$lambda$0(ExplorerProtocol explorerProtocol) {
        return (GetProjectsWithPaginationUseCase) explorerProtocol.koinApp.getKoin().getScopeRegistry().getRootScope().get(Reflection.getOrCreateKotlinClass(GetProjectsWithPaginationUseCase.class), (Qualifier) null, (Function0<? extends ParametersHolder>) null);
    }

    /* access modifiers changed from: private */
    public static final Logger logger_delegate$lambda$1(ExplorerProtocol explorerProtocol) {
        Koin koin = explorerProtocol.koinApp.getKoin();
        return (Logger) koin.getScopeRegistry().getRootScope().get(Reflection.getOrCreateKotlinClass(Logger.class), QualifierKt.named(AndroidCommonDITags.LOGGER), (Function0<? extends ParametersHolder>) null);
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x0039  */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x0025  */
    @org.jetbrains.annotations.Nullable
    /* renamed from: getProjects-yxL6bBk  reason: not valid java name */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object m8734getProjectsyxL6bBk(int r8, int r9, boolean r10, boolean r11, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Result<? extends java.util.List<com.reown.android.internal.common.explorer.data.model.Project>>> r12) {
        /*
            r7 = this;
            boolean r0 = r12 instanceof com.reown.android.internal.common.explorer.ExplorerProtocol$getProjects$1
            if (r0 == 0) goto L_0x0014
            r0 = r12
            com.reown.android.internal.common.explorer.ExplorerProtocol$getProjects$1 r0 = (com.reown.android.internal.common.explorer.ExplorerProtocol$getProjects$1) r0
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
            com.reown.android.internal.common.explorer.ExplorerProtocol$getProjects$1 r0 = new com.reown.android.internal.common.explorer.ExplorerProtocol$getProjects$1
            r0.<init>(r7, r12)
            goto L_0x0012
        L_0x001a:
            java.lang.Object r12 = r6.result
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r6.label
            r2 = 1
            if (r1 == 0) goto L_0x0039
            if (r1 != r2) goto L_0x0031
            kotlin.ResultKt.throwOnFailure(r12)
            kotlin.Result r12 = (kotlin.Result) r12
            java.lang.Object r7 = r12.m8988unboximpl()
            goto L_0x0055
        L_0x0031:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L_0x0039:
            kotlin.ResultKt.throwOnFailure(r12)
            com.reown.android.internal.common.explorer.domain.usecase.GetProjectsWithPaginationUseCase r1 = r7.getGetProjectsWithPaginationUseCase()
            r6.I$0 = r8
            r6.I$1 = r9
            r6.Z$0 = r10
            r6.Z$1 = r11
            r6.label = r2
            r2 = r8
            r3 = r9
            r4 = r10
            r5 = r11
            java.lang.Object r7 = r1.m8736invokeyxL6bBk(r2, r3, r4, r5, r6)
            if (r7 != r0) goto L_0x0055
            return r0
        L_0x0055:
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.reown.android.internal.common.explorer.ExplorerProtocol.m8734getProjectsyxL6bBk(int, int, boolean, boolean, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public ExplorerProtocol(@NotNull KoinApplication koinApplication) {
        Intrinsics.checkNotNullParameter(koinApplication, "koinApp");
        this.koinApp = koinApplication;
        this.getProjectsWithPaginationUseCase$delegate = LazyKt.lazy(new a(this, 0));
        this.logger$delegate = LazyKt.lazy(new a(this, 1));
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ ExplorerProtocol(KoinApplication koinApplication, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this((i3 & 1) != 0 ? KoinApplicationKt.getWcKoinApp() : koinApplication);
    }
}
