package com.reown.sign.engine.use_case.calls;

import com.reown.android.internal.common.storage.verify.VerifyContextStorageRepository;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0018\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\tH@¢\u0006\u0002\u0010\nR\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/reown/sign/engine/use_case/calls/GetVerifyContextByIdUseCase;", "Lcom/reown/sign/engine/use_case/calls/GetVerifyContextByIdUseCaseInterface;", "verifyContextStorageRepository", "Lcom/reown/android/internal/common/storage/verify/VerifyContextStorageRepository;", "<init>", "(Lcom/reown/android/internal/common/storage/verify/VerifyContextStorageRepository;)V", "getVerifyContext", "Lcom/reown/sign/engine/model/EngineDO$VerifyContext;", "id", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "sign_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class GetVerifyContextByIdUseCase implements GetVerifyContextByIdUseCaseInterface {
    @NotNull
    private final VerifyContextStorageRepository verifyContextStorageRepository;

    public GetVerifyContextByIdUseCase(@NotNull VerifyContextStorageRepository verifyContextStorageRepository2) {
        Intrinsics.checkNotNullParameter(verifyContextStorageRepository2, "verifyContextStorageRepository");
        this.verifyContextStorageRepository = verifyContextStorageRepository2;
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0031  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0045  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x004a A[ORIG_RETURN, RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0023  */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object getVerifyContext(long r5, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super com.reown.sign.engine.model.EngineDO.VerifyContext> r7) {
        /*
            r4 = this;
            boolean r0 = r7 instanceof com.reown.sign.engine.use_case.calls.GetVerifyContextByIdUseCase$getVerifyContext$1
            if (r0 == 0) goto L_0x0013
            r0 = r7
            com.reown.sign.engine.use_case.calls.GetVerifyContextByIdUseCase$getVerifyContext$1 r0 = (com.reown.sign.engine.use_case.calls.GetVerifyContextByIdUseCase$getVerifyContext$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.label = r1
            goto L_0x0018
        L_0x0013:
            com.reown.sign.engine.use_case.calls.GetVerifyContextByIdUseCase$getVerifyContext$1 r0 = new com.reown.sign.engine.use_case.calls.GetVerifyContextByIdUseCase$getVerifyContext$1
            r0.<init>(r4, r7)
        L_0x0018:
            java.lang.Object r7 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0031
            if (r2 != r3) goto L_0x0029
            kotlin.ResultKt.throwOnFailure(r7)
            goto L_0x0041
        L_0x0029:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L_0x0031:
            kotlin.ResultKt.throwOnFailure(r7)
            com.reown.android.internal.common.storage.verify.VerifyContextStorageRepository r4 = r4.verifyContextStorageRepository
            r0.J$0 = r5
            r0.label = r3
            java.lang.Object r7 = r4.get(r5, r0)
            if (r7 != r1) goto L_0x0041
            return r1
        L_0x0041:
            com.reown.android.verify.model.VerifyContext r7 = (com.reown.android.verify.model.VerifyContext) r7
            if (r7 == 0) goto L_0x004a
            com.reown.sign.engine.model.EngineDO$VerifyContext r4 = kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r7, "<this>")
            goto L_0x004b
        L_0x004a:
            r4 = 0
        L_0x004b:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.reown.sign.engine.use_case.calls.GetVerifyContextByIdUseCase.getVerifyContext(long, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
