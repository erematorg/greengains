package com.reown.sign.engine.use_case.requests;

import com.reown.sign.engine.model.EngineDO;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.MutableSharedFlow;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.reown.sign.engine.use_case.requests.OnSessionRequestUseCase$emitSessionRequest$1", f = "OnSessionRequestUseCase.kt", i = {}, l = {140}, m = "invokeSuspend", n = {}, s = {})
public final class OnSessionRequestUseCase$emitSessionRequest$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ EngineDO.SessionRequestEvent $event;
    int label;
    final /* synthetic */ OnSessionRequestUseCase this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public OnSessionRequestUseCase$emitSessionRequest$1(OnSessionRequestUseCase onSessionRequestUseCase, EngineDO.SessionRequestEvent sessionRequestEvent, Continuation<? super OnSessionRequestUseCase$emitSessionRequest$1> continuation) {
        super(2, continuation);
        this.this$0 = onSessionRequestUseCase;
        this.$event = sessionRequestEvent;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new OnSessionRequestUseCase$emitSessionRequest$1(this.this$0, this.$event, continuation);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i3 = this.label;
        if (i3 == 0) {
            ResultKt.throwOnFailure(obj);
            MutableSharedFlow access$get_events$p = this.this$0._events;
            EngineDO.SessionRequestEvent sessionRequestEvent = this.$event;
            this.label = 1;
            if (access$get_events$p.emit(sessionRequestEvent, this) == coroutine_suspended) {
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
        return ((OnSessionRequestUseCase$emitSessionRequest$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }
}
