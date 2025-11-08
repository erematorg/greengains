package com.reown.android.keyserver.domain.use_case;

import kotlin.Metadata;
import kotlin.Result;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.reown.android.keyserver.domain.use_case.ResolveIdentityUseCase", f = "ResolveIdentityUseCase.kt", i = {0, 0, 0}, l = {10}, m = "invoke-gIAlu-s", n = {"identityKey", "$this$invoke_gIAlu_s_u24lambda_u240", "$i$a$-runCatching-ResolveIdentityUseCase$invoke$2"}, s = {"L$0", "L$1", "I$0"})
public final class ResolveIdentityUseCase$invoke$1 extends ContinuationImpl {
    int I$0;
    Object L$0;
    Object L$1;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ ResolveIdentityUseCase this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ResolveIdentityUseCase$invoke$1(ResolveIdentityUseCase resolveIdentityUseCase, Continuation<? super ResolveIdentityUseCase$invoke$1> continuation) {
        super(continuation);
        this.this$0 = resolveIdentityUseCase;
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        Object r12 = this.this$0.m8839invokegIAlus((String) null, this);
        return r12 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? r12 : Result.m8978boximpl(r12);
    }
}
