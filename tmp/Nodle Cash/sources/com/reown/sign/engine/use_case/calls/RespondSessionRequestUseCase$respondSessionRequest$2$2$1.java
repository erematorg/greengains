package com.reown.sign.engine.use_case.calls;

import com.reown.android.internal.common.JsonRpcResponse;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.SupervisorKt;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.reown.sign.engine.use_case.calls.RespondSessionRequestUseCase$respondSessionRequest$2$2$1", f = "RespondSessionRequestUseCase.kt", i = {}, l = {121}, m = "invokeSuspend", n = {}, s = {})
public final class RespondSessionRequestUseCase$respondSessionRequest$2$2$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ JsonRpcResponse $jsonRpcResponse;
    int label;
    final /* synthetic */ RespondSessionRequestUseCase this$0;

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.reown.sign.engine.use_case.calls.RespondSessionRequestUseCase$respondSessionRequest$2$2$1$1", f = "RespondSessionRequestUseCase.kt", i = {}, l = {122}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.reown.sign.engine.use_case.calls.RespondSessionRequestUseCase$respondSessionRequest$2$2$1$1  reason: invalid class name */
    public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(respondSessionRequestUseCase, jsonRpcResponse, continuation);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i3 = this.label;
            if (i3 == 0) {
                ResultKt.throwOnFailure(obj);
                RespondSessionRequestUseCase respondSessionRequestUseCase = respondSessionRequestUseCase;
                long id = jsonRpcResponse.getId();
                this.label = 1;
                if (respondSessionRequestUseCase.removePendingSessionRequestAndEmit(id, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else if (i3 == 1) {
                ResultKt.throwOnFailure(obj);
            } else {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            return Unit.INSTANCE;
        }

        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public RespondSessionRequestUseCase$respondSessionRequest$2$2$1(RespondSessionRequestUseCase respondSessionRequestUseCase, JsonRpcResponse jsonRpcResponse, Continuation<? super RespondSessionRequestUseCase$respondSessionRequest$2$2$1> continuation) {
        super(2, continuation);
        this.this$0 = respondSessionRequestUseCase;
        this.$jsonRpcResponse = jsonRpcResponse;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new RespondSessionRequestUseCase$respondSessionRequest$2$2$1(this.this$0, this.$jsonRpcResponse, continuation);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i3 = this.label;
        if (i3 == 0) {
            ResultKt.throwOnFailure(obj);
            final RespondSessionRequestUseCase respondSessionRequestUseCase = this.this$0;
            final JsonRpcResponse jsonRpcResponse = this.$jsonRpcResponse;
            AnonymousClass1 r6 = new AnonymousClass1((Continuation<? super AnonymousClass1>) null);
            this.label = 1;
            if (SupervisorKt.supervisorScope(r6, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i3 == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return Unit.INSTANCE;
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((RespondSessionRequestUseCase$respondSessionRequest$2$2$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }
}
