package com.reown.sign.engine.use_case.requests;

import com.reown.android.internal.common.exception.Uncategorized;
import com.reown.android.internal.common.model.EnvelopeType;
import com.reown.android.internal.common.model.IrnParams;
import com.reown.android.internal.common.model.Participants;
import com.reown.android.internal.common.model.SDKError;
import com.reown.android.internal.common.model.Tags;
import com.reown.android.internal.common.model.WCRequest;
import com.reown.android.internal.common.model.type.RelayJsonRpcInteractorInterface;
import com.reown.android.internal.utils.Time;
import com.reown.foundation.common.model.Topic;
import com.reown.foundation.common.model.Ttl;
import com.reown.foundation.util.Logger;
import com.reown.sign.common.model.vo.clientsync.session.params.SignParams;
import com.reown.sign.engine.model.EngineDO;
import com.reown.sign.engine.model.mapper.EngineMapperKt;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.MutableSharedFlow;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.reown.sign.engine.use_case.requests.OnSessionDeleteUseCase$invoke$2", f = "OnSessionDeleteUseCase.kt", i = {0, 1, 1}, l = {53, 57}, m = "invokeSuspend", n = {"irnParams", "irnParams", "e"}, s = {"L$0", "L$0", "L$1"})
public final class OnSessionDeleteUseCase$invoke$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ SignParams.DeleteParams $params;
    final /* synthetic */ WCRequest $request;
    Object L$0;
    Object L$1;
    int label;
    final /* synthetic */ OnSessionDeleteUseCase this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public OnSessionDeleteUseCase$invoke$2(OnSessionDeleteUseCase onSessionDeleteUseCase, WCRequest wCRequest, SignParams.DeleteParams deleteParams, Continuation<? super OnSessionDeleteUseCase$invoke$2> continuation) {
        super(2, continuation);
        this.this$0 = onSessionDeleteUseCase;
        this.$request = wCRequest;
        this.$params = deleteParams;
    }

    /* access modifiers changed from: private */
    public static final Unit invokeSuspend$lambda$0(OnSessionDeleteUseCase onSessionDeleteUseCase, WCRequest wCRequest) {
        Logger access$getLogger$p = onSessionDeleteUseCase.logger;
        Topic topic = wCRequest.getTopic();
        access$getLogger$p.log("Session delete received on topic: " + topic + " - unsubscribe success");
        try {
            onSessionDeleteUseCase.crypto.removeKeys(wCRequest.getTopic().getValue());
        } catch (Exception e3) {
            Logger access$getLogger$p2 = onSessionDeleteUseCase.logger;
            access$getLogger$p2.error("Remove keys exception:" + e3);
        }
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final Unit invokeSuspend$lambda$1(OnSessionDeleteUseCase onSessionDeleteUseCase, WCRequest wCRequest, Throwable th) {
        Logger access$getLogger$p = onSessionDeleteUseCase.logger;
        Topic topic = wCRequest.getTopic();
        access$getLogger$p.error("Session delete received on topic: " + topic + " - unsubscribe error " + th);
        return Unit.INSTANCE;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new OnSessionDeleteUseCase$invoke$2(this.this$0, this.$request, this.$params, continuation);
    }

    public final Object invokeSuspend(Object obj) {
        Exception exc;
        IrnParams irnParams;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i3 = this.label;
        if (i3 == 0) {
            ResultKt.throwOnFailure(obj);
            Logger access$getLogger$p = this.this$0.logger;
            Topic topic = this.$request.getTopic();
            access$getLogger$p.log("Session delete received on topic: " + topic);
            IrnParams irnParams2 = new IrnParams(Tags.SESSION_DELETE_RESPONSE, new Ttl(Time.getDayInSeconds()), Boxing.boxLong(this.$request.getId()), (List) null, (String) null, (List) null, (List) null, false, 248, (DefaultConstructorMarker) null);
            try {
                if (!this.this$0.sessionStorageRepository.isSessionValid(this.$request.getTopic())) {
                    Logger access$getLogger$p2 = this.this$0.logger;
                    Topic topic2 = this.$request.getTopic();
                    access$getLogger$p2.error("Session delete received failure on topic: " + topic2 + " - invalid session");
                    RelayJsonRpcInteractorInterface access$getJsonRpcInteractor$p = this.this$0.jsonRpcInteractor;
                    WCRequest wCRequest = this.$request;
                    RelayJsonRpcInteractorInterface.respondWithError$default(access$getJsonRpcInteractor$p, wCRequest, new Uncategorized.NoMatchingTopic("SESSION", wCRequest.getTopic().getValue()), irnParams2, (EnvelopeType) null, (Participants) null, (Function1) null, (Function1) null, 120, (Object) null);
                    return Unit.INSTANCE;
                }
                RelayJsonRpcInteractorInterface access$getJsonRpcInteractor$p2 = this.this$0.jsonRpcInteractor;
                Topic topic3 = this.$request.getTopic();
                OnSessionDeleteUseCase onSessionDeleteUseCase = this.this$0;
                WCRequest wCRequest2 = this.$request;
                access$getJsonRpcInteractor$p2.unsubscribe(topic3, new b(onSessionDeleteUseCase, wCRequest2), new c(onSessionDeleteUseCase, wCRequest2));
                this.this$0.sessionStorageRepository.deleteSession(this.$request.getTopic());
                Logger access$getLogger$p3 = this.this$0.logger;
                Topic topic4 = this.$request.getTopic();
                access$getLogger$p3.log("Session delete received on topic: " + topic4 + " - emitting");
                MutableSharedFlow access$get_events$p = this.this$0._events;
                EngineDO.SessionDelete engineDO = EngineMapperKt.toEngineDO(this.$params, this.$request.getTopic());
                this.L$0 = irnParams2;
                this.label = 1;
                if (access$get_events$p.emit(engineDO, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } catch (Exception e3) {
                exc = e3;
                irnParams = irnParams2;
                Logger access$getLogger$p4 = this.this$0.logger;
                Topic topic5 = this.$request.getTopic();
                access$getLogger$p4.error("Session delete received failure on topic: " + topic5 + " - " + exc);
                RelayJsonRpcInteractorInterface access$getJsonRpcInteractor$p3 = this.this$0.jsonRpcInteractor;
                WCRequest wCRequest3 = this.$request;
                String message = exc.getMessage();
                Topic topic6 = this.$request.getTopic();
                RelayJsonRpcInteractorInterface.respondWithError$default(access$getJsonRpcInteractor$p3, wCRequest3, new Uncategorized.GenericError("Cannot delete a session: " + message + ", topic: " + topic6), irnParams, (EnvelopeType) null, (Participants) null, (Function1) null, (Function1) null, 120, (Object) null);
                MutableSharedFlow access$get_events$p2 = this.this$0._events;
                SDKError sDKError = new SDKError(exc);
                this.L$0 = SpillingKt.nullOutSpilledVariable(irnParams);
                this.L$1 = SpillingKt.nullOutSpilledVariable(exc);
                this.label = 2;
                if (access$get_events$p2.emit(sDKError, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                return Unit.INSTANCE;
            }
        } else if (i3 == 1) {
            IrnParams irnParams3 = (IrnParams) this.L$0;
            try {
                ResultKt.throwOnFailure(obj);
            } catch (Exception e4) {
                exc = e4;
                irnParams = irnParams3;
            }
        } else if (i3 == 2) {
            Exception exc2 = (Exception) this.L$1;
            IrnParams irnParams4 = (IrnParams) this.L$0;
            ResultKt.throwOnFailure(obj);
            return Unit.INSTANCE;
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return Unit.INSTANCE;
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((OnSessionDeleteUseCase$invoke$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }
}
