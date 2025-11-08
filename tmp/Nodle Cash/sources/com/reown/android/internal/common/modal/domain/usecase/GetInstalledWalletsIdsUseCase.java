package com.reown.android.internal.common.modal.domain.usecase;

import com.reown.android.internal.common.modal.AppKitApiRepository;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u001c\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u0006\u0010\t\u001a\u00020\bHB¢\u0006\u0002\u0010\nR\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/reown/android/internal/common/modal/domain/usecase/GetInstalledWalletsIdsUseCase;", "Lcom/reown/android/internal/common/modal/domain/usecase/GetInstalledWalletsIdsUseCaseInterface;", "appKitApiRepository", "Lcom/reown/android/internal/common/modal/AppKitApiRepository;", "<init>", "(Lcom/reown/android/internal/common/modal/AppKitApiRepository;)V", "invoke", "", "", "sdkType", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nGetAndroidDataUseCaseInterface.kt\nKotlin\n*S Kotlin\n*F\n+ 1 GetAndroidDataUseCaseInterface.kt\ncom/reown/android/internal/common/modal/domain/usecase/GetInstalledWalletsIdsUseCase\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,16:1\n1#2:17\n1563#3:18\n1634#3,3:19\n*S KotlinDebug\n*F\n+ 1 GetAndroidDataUseCaseInterface.kt\ncom/reown/android/internal/common/modal/domain/usecase/GetInstalledWalletsIdsUseCase\n*L\n14#1:18\n14#1:19,3\n*E\n"})
public final class GetInstalledWalletsIdsUseCase implements GetInstalledWalletsIdsUseCaseInterface {
    @NotNull
    private final AppKitApiRepository appKitApiRepository;

    public GetInstalledWalletsIdsUseCase(@NotNull AppKitApiRepository appKitApiRepository2) {
        Intrinsics.checkNotNullParameter(appKitApiRepository2, "appKitApiRepository");
        this.appKitApiRepository = appKitApiRepository2;
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x003b  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0055  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x007f  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0023  */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object invoke(@org.jetbrains.annotations.NotNull java.lang.String r5, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.util.List<java.lang.String>> r6) {
        /*
            r4 = this;
            boolean r0 = r6 instanceof com.reown.android.internal.common.modal.domain.usecase.GetInstalledWalletsIdsUseCase$invoke$1
            if (r0 == 0) goto L_0x0013
            r0 = r6
            com.reown.android.internal.common.modal.domain.usecase.GetInstalledWalletsIdsUseCase$invoke$1 r0 = (com.reown.android.internal.common.modal.domain.usecase.GetInstalledWalletsIdsUseCase$invoke$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.label = r1
            goto L_0x0018
        L_0x0013:
            com.reown.android.internal.common.modal.domain.usecase.GetInstalledWalletsIdsUseCase$invoke$1 r0 = new com.reown.android.internal.common.modal.domain.usecase.GetInstalledWalletsIdsUseCase$invoke$1
            r0.<init>(r4, r6)
        L_0x0018:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x003b
            if (r2 != r3) goto L_0x0033
            java.lang.Object r4 = r0.L$0
            java.lang.String r4 = (java.lang.String) r4
            kotlin.ResultKt.throwOnFailure(r6)
            kotlin.Result r6 = (kotlin.Result) r6
            java.lang.Object r4 = r6.m8988unboximpl()
            goto L_0x004f
        L_0x0033:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L_0x003b:
            kotlin.ResultKt.throwOnFailure(r6)
            com.reown.android.internal.common.modal.AppKitApiRepository r4 = r4.appKitApiRepository
            java.lang.Object r6 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r5)
            r0.L$0 = r6
            r0.label = r3
            java.lang.Object r4 = r4.m8746getAndroidWalletsDatagIAlus(r5, r0)
            if (r4 != r1) goto L_0x004f
            return r1
        L_0x004f:
            boolean r5 = kotlin.Result.m8986isSuccessimpl(r4)
            if (r5 == 0) goto L_0x007f
            java.util.List r4 = (java.util.List) r4
            java.lang.Iterable r4 = (java.lang.Iterable) r4
            java.util.ArrayList r5 = new java.util.ArrayList
            int r6 = kotlin.collections.CollectionsKt__IterablesKt.collectionSizeOrDefault(r4, 10)
            r5.<init>(r6)
            java.util.Iterator r4 = r4.iterator()
        L_0x0066:
            boolean r6 = r4.hasNext()
            if (r6 == 0) goto L_0x007a
            java.lang.Object r6 = r4.next()
            com.reown.android.internal.common.modal.data.model.WalletAppData r6 = (com.reown.android.internal.common.modal.data.model.WalletAppData) r6
            java.lang.String r6 = r6.getId()
            r5.add(r6)
            goto L_0x0066
        L_0x007a:
            java.lang.Object r4 = kotlin.Result.m8979constructorimpl(r5)
            goto L_0x0083
        L_0x007f:
            java.lang.Object r4 = kotlin.Result.m8979constructorimpl(r4)
        L_0x0083:
            kotlin.ResultKt.throwOnFailure(r4)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.reown.android.internal.common.modal.domain.usecase.GetInstalledWalletsIdsUseCase.invoke(java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
