package com.reown.sign.engine.use_case.calls;

import com.reown.android.internal.common.storage.verify.VerifyContextStorageRepository;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H@¢\u0006\u0002\u0010\tR\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/reown/sign/engine/use_case/calls/GetListOfVerifyContextsUseCase;", "Lcom/reown/sign/engine/use_case/calls/GetListOfVerifyContextsUseCaseInterface;", "verifyContextStorageRepository", "Lcom/reown/android/internal/common/storage/verify/VerifyContextStorageRepository;", "<init>", "(Lcom/reown/android/internal/common/storage/verify/VerifyContextStorageRepository;)V", "getListOfVerifyContexts", "", "Lcom/reown/sign/engine/model/EngineDO$VerifyContext;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nGetListOfVerifyContextsUseCase.kt\nKotlin\n*S Kotlin\n*F\n+ 1 GetListOfVerifyContextsUseCase.kt\ncom/reown/sign/engine/use_case/calls/GetListOfVerifyContextsUseCase\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,13:1\n1563#2:14\n1634#2,3:15\n*S KotlinDebug\n*F\n+ 1 GetListOfVerifyContextsUseCase.kt\ncom/reown/sign/engine/use_case/calls/GetListOfVerifyContextsUseCase\n*L\n8#1:14\n8#1:15,3\n*E\n"})
public final class GetListOfVerifyContextsUseCase implements GetListOfVerifyContextsUseCaseInterface {
    @NotNull
    private final VerifyContextStorageRepository verifyContextStorageRepository;

    public GetListOfVerifyContextsUseCase(@NotNull VerifyContextStorageRepository verifyContextStorageRepository2) {
        Intrinsics.checkNotNullParameter(verifyContextStorageRepository2, "verifyContextStorageRepository");
        this.verifyContextStorageRepository = verifyContextStorageRepository2;
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0031  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0054 A[LOOP:0: B:16:0x004e->B:18:0x0054, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0023  */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object getListOfVerifyContexts(@org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.util.List<com.reown.sign.engine.model.EngineDO.VerifyContext>> r5) {
        /*
            r4 = this;
            boolean r0 = r5 instanceof com.reown.sign.engine.use_case.calls.GetListOfVerifyContextsUseCase$getListOfVerifyContexts$1
            if (r0 == 0) goto L_0x0013
            r0 = r5
            com.reown.sign.engine.use_case.calls.GetListOfVerifyContextsUseCase$getListOfVerifyContexts$1 r0 = (com.reown.sign.engine.use_case.calls.GetListOfVerifyContextsUseCase$getListOfVerifyContexts$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.label = r1
            goto L_0x0018
        L_0x0013:
            com.reown.sign.engine.use_case.calls.GetListOfVerifyContextsUseCase$getListOfVerifyContexts$1 r0 = new com.reown.sign.engine.use_case.calls.GetListOfVerifyContextsUseCase$getListOfVerifyContexts$1
            r0.<init>(r4, r5)
        L_0x0018:
            java.lang.Object r5 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0031
            if (r2 != r3) goto L_0x0029
            kotlin.ResultKt.throwOnFailure(r5)
            goto L_0x003f
        L_0x0029:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L_0x0031:
            kotlin.ResultKt.throwOnFailure(r5)
            com.reown.android.internal.common.storage.verify.VerifyContextStorageRepository r4 = r4.verifyContextStorageRepository
            r0.label = r3
            java.lang.Object r5 = r4.getAll(r0)
            if (r5 != r1) goto L_0x003f
            return r1
        L_0x003f:
            java.lang.Iterable r5 = (java.lang.Iterable) r5
            java.util.ArrayList r4 = new java.util.ArrayList
            int r0 = kotlin.collections.CollectionsKt__IterablesKt.collectionSizeOrDefault(r5, 10)
            r4.<init>(r0)
            java.util.Iterator r5 = r5.iterator()
        L_0x004e:
            boolean r0 = r5.hasNext()
            if (r0 == 0) goto L_0x0062
            java.lang.Object r0 = r5.next()
            com.reown.android.verify.model.VerifyContext r0 = (com.reown.android.verify.model.VerifyContext) r0
            com.reown.sign.engine.model.EngineDO$VerifyContext r0 = kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r0, "<this>")
            r4.add(r0)
            goto L_0x004e
        L_0x0062:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.reown.sign.engine.use_case.calls.GetListOfVerifyContextsUseCase.getListOfVerifyContexts(kotlin.coroutines.Continuation):java.lang.Object");
    }
}
