package com.reown.sign.engine.use_case.calls;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.reown.sign.engine.use_case.calls.GetListOfVerifyContextsUseCase", f = "GetListOfVerifyContextsUseCase.kt", i = {}, l = {8}, m = "getListOfVerifyContexts", n = {}, s = {})
public final class GetListOfVerifyContextsUseCase$getListOfVerifyContexts$1 extends ContinuationImpl {
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ GetListOfVerifyContextsUseCase this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public GetListOfVerifyContextsUseCase$getListOfVerifyContexts$1(GetListOfVerifyContextsUseCase getListOfVerifyContextsUseCase, Continuation<? super GetListOfVerifyContextsUseCase$getListOfVerifyContexts$1> continuation) {
        super(continuation);
        this.this$0 = getListOfVerifyContextsUseCase;
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.getListOfVerifyContexts(this);
    }
}
