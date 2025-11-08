package com.reown.sign.engine.use_case.requests;

import com.reown.android.internal.common.model.WCRequest;
import com.reown.android.verify.model.VerifyContext;
import com.reown.foundation.common.model.Topic;
import com.reown.foundation.util.Logger;
import com.reown.sign.common.model.vo.clientsync.session.params.SignParams;
import com.reown.sign.engine.model.EngineDO;
import com.reown.sign.engine.model.mapper.EngineMapperKt;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.MutableSharedFlow;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.reown.sign.engine.use_case.requests.OnSessionAuthenticateUseCase$emitSessionAuthenticate$1", f = "OnSessionAuthenticateUseCase.kt", i = {}, l = {89}, m = "invokeSuspend", n = {}, s = {})
public final class OnSessionAuthenticateUseCase$emitSessionAuthenticate$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ SignParams.SessionAuthenticateParams $authenticateSessionParams;
    final /* synthetic */ WCRequest $request;
    final /* synthetic */ VerifyContext $verifyContext;
    int label;
    final /* synthetic */ OnSessionAuthenticateUseCase this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public OnSessionAuthenticateUseCase$emitSessionAuthenticate$1(OnSessionAuthenticateUseCase onSessionAuthenticateUseCase, WCRequest wCRequest, SignParams.SessionAuthenticateParams sessionAuthenticateParams, VerifyContext verifyContext, Continuation<? super OnSessionAuthenticateUseCase$emitSessionAuthenticate$1> continuation) {
        super(2, continuation);
        this.this$0 = onSessionAuthenticateUseCase;
        this.$request = wCRequest;
        this.$authenticateSessionParams = sessionAuthenticateParams;
        this.$verifyContext = verifyContext;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new OnSessionAuthenticateUseCase$emitSessionAuthenticate$1(this.this$0, this.$request, this.$authenticateSessionParams, this.$verifyContext, continuation);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i3 = this.label;
        if (i3 == 0) {
            ResultKt.throwOnFailure(obj);
            Logger access$getLogger$p = this.this$0.logger;
            Topic topic = this.$request.getTopic();
            access$getLogger$p.log("Received session authenticate - emitting: " + topic);
            MutableSharedFlow access$get_events$p = this.this$0._events;
            EngineDO.SessionAuthenticateEvent sessionAuthenticateEvent = new EngineDO.SessionAuthenticateEvent(this.$request.getId(), this.$request.getTopic().getValue(), EngineMapperKt.toEngineDO(this.$authenticateSessionParams.getAuthPayload()), Intrinsics.checkNotNullParameter(this.$authenticateSessionParams.getRequester(), "<this>"), this.$authenticateSessionParams.getExpiryTimestamp(), Intrinsics.checkNotNullParameter(this.$verifyContext, "<this>"));
            this.label = 1;
            if (access$get_events$p.emit(sessionAuthenticateEvent, this) == coroutine_suspended) {
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
        return ((OnSessionAuthenticateUseCase$emitSessionAuthenticate$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }
}
