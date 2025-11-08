package com.reown.sign.engine.use_case.calls;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.reown.sign.engine.use_case.calls.RespondSessionRequestUseCase", f = "RespondSessionRequestUseCase.kt", i = {0, 1, 1, 1}, l = {135, 141}, m = "removePendingSessionRequestAndEmit", n = {"id", "event", "id", "$i$a$-let-RespondSessionRequestUseCase$removePendingSessionRequestAndEmit$5"}, s = {"J$0", "L$0", "J$0", "I$0"})
public final class RespondSessionRequestUseCase$removePendingSessionRequestAndEmit$1 extends ContinuationImpl {
    int I$0;
    long J$0;
    Object L$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ RespondSessionRequestUseCase this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public RespondSessionRequestUseCase$removePendingSessionRequestAndEmit$1(RespondSessionRequestUseCase respondSessionRequestUseCase, Continuation<? super RespondSessionRequestUseCase$removePendingSessionRequestAndEmit$1> continuation) {
        super(continuation);
        this.this$0 = respondSessionRequestUseCase;
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.removePendingSessionRequestAndEmit(0, this);
    }
}
