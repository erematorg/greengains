package com.reown.android.internal.common.explorer.domain.usecase;

import kotlin.Metadata;
import kotlin.Result;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.reown.android.internal.common.explorer.domain.usecase.GetNotifyConfigUseCase", f = "GetNotifyConfigUseCase.kt", i = {0, 0, 0}, l = {7}, m = "invoke-gIAlu-s", n = {"appDomain", "$this$invoke_gIAlu_s_u24lambda_u240", "$i$a$-runCatching-GetNotifyConfigUseCase$invoke$2"}, s = {"L$0", "L$1", "I$0"})
public final class GetNotifyConfigUseCase$invoke$1 extends ContinuationImpl {
    int I$0;
    Object L$0;
    Object L$1;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ GetNotifyConfigUseCase this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public GetNotifyConfigUseCase$invoke$1(GetNotifyConfigUseCase getNotifyConfigUseCase, Continuation<? super GetNotifyConfigUseCase$invoke$1> continuation) {
        super(continuation);
        this.this$0 = getNotifyConfigUseCase;
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        Object r12 = this.this$0.m8735invokegIAlus((String) null, this);
        return r12 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? r12 : Result.m8978boximpl(r12);
    }
}
