package com.reown.sign.engine.use_case.calls;

import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0003H\n"}, d2 = {"<anonymous>", "Lkotlin/Result;", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.reown.sign.engine.use_case.calls.SessionAuthenticateUseCase$authenticate$4$1$sessionProposeResult$1", f = "SessionAuthenticateUseCase.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
public final class SessionAuthenticateUseCase$authenticate$4$1$sessionProposeResult$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Result<? extends Unit>>, Object> {
    final /* synthetic */ Object $sessionProposeDeferred;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public SessionAuthenticateUseCase$authenticate$4$1$sessionProposeResult$1(Object obj, Continuation<? super SessionAuthenticateUseCase$authenticate$4$1$sessionProposeResult$1> continuation) {
        super(2, continuation);
        this.$sessionProposeDeferred = obj;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new SessionAuthenticateUseCase$authenticate$4$1$sessionProposeResult$1(this.$sessionProposeDeferred, continuation);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            return Result.m8978boximpl(this.$sessionProposeDeferred);
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Result<Unit>> continuation) {
        return ((SessionAuthenticateUseCase$authenticate$4$1$sessionProposeResult$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }
}
