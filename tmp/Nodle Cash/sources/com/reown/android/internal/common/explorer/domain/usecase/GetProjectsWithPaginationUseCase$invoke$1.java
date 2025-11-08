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
@DebugMetadata(c = "com.reown.android.internal.common.explorer.domain.usecase.GetProjectsWithPaginationUseCase", f = "GetProjectsWithPaginationUseCase.kt", i = {0, 0, 0, 0, 0, 0}, l = {10}, m = "invoke-yxL6bBk", n = {"$this$invoke_yxL6bBk_u24lambda_u241", "page", "entries", "isVerified", "isFeatured", "$i$a$-runCatching-GetProjectsWithPaginationUseCase$invoke$2"}, s = {"L$0", "I$0", "I$1", "Z$0", "Z$1", "I$2"})
public final class GetProjectsWithPaginationUseCase$invoke$1 extends ContinuationImpl {
    int I$0;
    int I$1;
    int I$2;
    Object L$0;
    boolean Z$0;
    boolean Z$1;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ GetProjectsWithPaginationUseCase this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public GetProjectsWithPaginationUseCase$invoke$1(GetProjectsWithPaginationUseCase getProjectsWithPaginationUseCase, Continuation<? super GetProjectsWithPaginationUseCase$invoke$1> continuation) {
        super(continuation);
        this.this$0 = getProjectsWithPaginationUseCase;
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        Object r6 = this.this$0.m8736invokeyxL6bBk(0, 0, false, false, this);
        return r6 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? r6 : Result.m8978boximpl(r6);
    }
}
