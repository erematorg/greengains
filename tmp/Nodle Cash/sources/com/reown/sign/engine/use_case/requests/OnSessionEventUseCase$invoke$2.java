package com.reown.sign.engine.use_case.requests;

import com.reown.android.internal.common.exception.Uncategorized;
import com.reown.android.internal.common.model.EnvelopeType;
import com.reown.android.internal.common.model.IrnParams;
import com.reown.android.internal.common.model.Namespace;
import com.reown.android.internal.common.model.Participants;
import com.reown.android.internal.common.model.SDKError;
import com.reown.android.internal.common.model.Tags;
import com.reown.android.internal.common.model.WCRequest;
import com.reown.android.internal.common.model.type.RelayJsonRpcInteractorInterface;
import com.reown.android.internal.utils.CoreValidator;
import com.reown.android.internal.utils.Time;
import com.reown.foundation.common.model.Topic;
import com.reown.foundation.common.model.Ttl;
import com.reown.foundation.util.Logger;
import com.reown.sign.common.exceptions.PeerError;
import com.reown.sign.common.model.vo.clientsync.session.params.SignParams;
import com.reown.sign.common.model.vo.clientsync.session.payload.SessionEventVO;
import com.reown.sign.common.model.vo.sequence.SessionVO;
import com.reown.sign.common.validator.SignValidator;
import com.reown.sign.engine.model.EngineDO;
import com.reown.sign.engine.model.ValidationError;
import com.reown.sign.engine.model.mapper.EngineMapperKt;
import java.util.List;
import java.util.Map;
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
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.MutableSharedFlow;

@SourceDebugExtension({"SMAP\nOnSessionEventUseCase.kt\nKotlin\n*S Kotlin\n*F\n+ 1 OnSessionEventUseCase.kt\ncom/reown/sign/engine/use_case/requests/OnSessionEventUseCase$invoke$2\n+ 2 SignValidator.kt\ncom/reown/sign/common/validator/SignValidator\n*L\n1#1,79:1\n118#2,4:80\n99#2,6:84\n*S KotlinDebug\n*F\n+ 1 OnSessionEventUseCase.kt\ncom/reown/sign/engine/use_case/requests/OnSessionEventUseCase$invoke$2\n*L\n38#1:80,4\n63#1:84,6\n*E\n"})
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.reown.sign.engine.use_case.requests.OnSessionEventUseCase$invoke$2", f = "OnSessionEventUseCase.kt", i = {0, 0, 0, 1, 1}, l = {71, 75}, m = "invokeSuspend", n = {"irnParams", "session", "event", "irnParams", "e"}, s = {"L$0", "L$1", "L$2", "L$0", "L$1"})
public final class OnSessionEventUseCase$invoke$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ SignParams.EventParams $params;
    final /* synthetic */ WCRequest $request;
    Object L$0;
    Object L$1;
    Object L$2;
    int label;
    final /* synthetic */ OnSessionEventUseCase this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public OnSessionEventUseCase$invoke$2(OnSessionEventUseCase onSessionEventUseCase, WCRequest wCRequest, SignParams.EventParams eventParams, Continuation<? super OnSessionEventUseCase$invoke$2> continuation) {
        super(2, continuation);
        this.this$0 = onSessionEventUseCase;
        this.$request = wCRequest;
        this.$params = eventParams;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new OnSessionEventUseCase$invoke$2(this.this$0, this.$request, this.$params, continuation);
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
            access$getLogger$p.log("Session event received on topic: " + topic);
            IrnParams irnParams2 = new IrnParams(Tags.SESSION_EVENT_RESPONSE, new Ttl(Time.getFiveMinutesInSeconds()), Boxing.boxLong(this.$request.getId()), (List) null, (String) null, (List) null, (List) null, false, 248, (DefaultConstructorMarker) null);
            try {
                SignValidator signValidator = SignValidator.INSTANCE;
                EngineDO.Event engineDOEvent = Intrinsics.checkNotNullParameter(this.$params, "<this>");
                OnSessionEventUseCase onSessionEventUseCase = this.this$0;
                WCRequest wCRequest = this.$request;
                if (engineDOEvent.getData().length() != 0) {
                    if (engineDOEvent.getName().length() != 0) {
                        if (engineDOEvent.getChainId().length() != 0) {
                            if (CoreValidator.INSTANCE.isChainIdCAIP2Compliant(engineDOEvent.getChainId())) {
                                if (!this.this$0.sessionStorageRepository.isSessionValid(this.$request.getTopic())) {
                                    Logger access$getLogger$p2 = this.this$0.logger;
                                    Topic topic2 = this.$request.getTopic();
                                    access$getLogger$p2.error("Session event received failure on topic: " + topic2 + " - invalid session");
                                    RelayJsonRpcInteractorInterface access$getJsonRpcInteractor$p = this.this$0.jsonRpcInteractor;
                                    WCRequest wCRequest2 = this.$request;
                                    RelayJsonRpcInteractorInterface.respondWithError$default(access$getJsonRpcInteractor$p, wCRequest2, new Uncategorized.NoMatchingTopic("SESSION", wCRequest2.getTopic().getValue()), irnParams2, (EnvelopeType) null, (Participants) null, (Function1) null, (Function1) null, 120, (Object) null);
                                    return Unit.INSTANCE;
                                }
                                SessionVO sessionWithoutMetadataByTopic = this.this$0.sessionStorageRepository.getSessionWithoutMetadataByTopic(this.$request.getTopic());
                                if (!sessionWithoutMetadataByTopic.isPeerController()) {
                                    Logger access$getLogger$p3 = this.this$0.logger;
                                    Topic topic3 = this.$request.getTopic();
                                    access$getLogger$p3.error("Session event received failure on topic: " + topic3 + " - unauthorized peer");
                                    RelayJsonRpcInteractorInterface.respondWithError$default(this.this$0.jsonRpcInteractor, this.$request, new PeerError.Unauthorized.Event("SESSION"), irnParams2, (EnvelopeType) null, (Participants) null, (Function1) null, (Function1) null, 120, (Object) null);
                                    return Unit.INSTANCE;
                                } else if (!sessionWithoutMetadataByTopic.isAcknowledged()) {
                                    Logger access$getLogger$p4 = this.this$0.logger;
                                    Topic topic4 = this.$request.getTopic();
                                    access$getLogger$p4.error("Session event received failure on topic: " + topic4 + " - no matching topic");
                                    RelayJsonRpcInteractorInterface access$getJsonRpcInteractor$p2 = this.this$0.jsonRpcInteractor;
                                    WCRequest wCRequest3 = this.$request;
                                    RelayJsonRpcInteractorInterface.respondWithError$default(access$getJsonRpcInteractor$p2, wCRequest3, new Uncategorized.NoMatchingTopic("SESSION", wCRequest3.getTopic().getValue()), irnParams2, (EnvelopeType) null, (Participants) null, (Function1) null, (Function1) null, 120, (Object) null);
                                    return Unit.INSTANCE;
                                } else {
                                    SessionEventVO event = this.$params.getEvent();
                                    SignValidator signValidator2 = SignValidator.INSTANCE;
                                    String chainId = this.$params.getChainId();
                                    String name = event.getName();
                                    Map<String, Namespace.Session> sessionNamespaces = sessionWithoutMetadataByTopic.getSessionNamespaces();
                                    OnSessionEventUseCase onSessionEventUseCase2 = this.this$0;
                                    WCRequest wCRequest4 = this.$request;
                                    Map access$allEventsWithChains = signValidator2.allEventsWithChains(sessionNamespaces);
                                    if (access$allEventsWithChains.get(name) != null) {
                                        Object obj2 = access$allEventsWithChains.get(name);
                                        Intrinsics.checkNotNull(obj2);
                                        if (((List) obj2).contains(chainId)) {
                                            RelayJsonRpcInteractorInterface.respondWithSuccess$default(this.this$0.jsonRpcInteractor, this.$request, irnParams2, (EnvelopeType) null, (Participants) null, 12, (Object) null);
                                            Logger access$getLogger$p5 = this.this$0.logger;
                                            Topic topic5 = this.$request.getTopic();
                                            access$getLogger$p5.log("Session event received on topic: " + topic5 + " - emitting");
                                            MutableSharedFlow access$get_events$p = this.this$0._events;
                                            EngineDO.SessionEvent engineDO = EngineMapperKt.toEngineDO(this.$params, this.$request.getTopic());
                                            this.L$0 = irnParams2;
                                            this.L$1 = SpillingKt.nullOutSpilledVariable(sessionWithoutMetadataByTopic);
                                            this.L$2 = SpillingKt.nullOutSpilledVariable(event);
                                            this.label = 1;
                                            if (access$get_events$p.emit(engineDO, this) == coroutine_suspended) {
                                                return coroutine_suspended;
                                            }
                                        }
                                    }
                                    ValidationError.UnauthorizedEvent unauthorizedEvent = ValidationError.UnauthorizedEvent.INSTANCE;
                                    Logger access$getLogger$p6 = onSessionEventUseCase2.logger;
                                    Topic topic6 = wCRequest4.getTopic();
                                    access$getLogger$p6.error("Session event received failure on topic: " + topic6 + " - " + unauthorizedEvent);
                                    RelayJsonRpcInteractorInterface.respondWithError$default(onSessionEventUseCase2.jsonRpcInteractor, wCRequest4, EngineMapperKt.toPeerError(unauthorizedEvent), irnParams2, (EnvelopeType) null, (Participants) null, (Function1) null, (Function1) null, 120, (Object) null);
                                    return Unit.INSTANCE;
                                }
                            }
                        }
                    }
                }
                ValidationError.InvalidEvent invalidEvent = ValidationError.InvalidEvent.INSTANCE;
                Logger access$getLogger$p7 = onSessionEventUseCase.logger;
                Topic topic7 = wCRequest.getTopic();
                access$getLogger$p7.error("Session event received failure on topic: " + topic7 + " - " + invalidEvent);
                RelayJsonRpcInteractorInterface.respondWithError$default(onSessionEventUseCase.jsonRpcInteractor, wCRequest, EngineMapperKt.toPeerError(invalidEvent), irnParams2, (EnvelopeType) null, (Participants) null, (Function1) null, (Function1) null, 120, (Object) null);
                return Unit.INSTANCE;
            } catch (Exception e3) {
                exc = e3;
                irnParams = irnParams2;
                Logger access$getLogger$p8 = this.this$0.logger;
                Topic topic8 = this.$request.getTopic();
                access$getLogger$p8.error("Session event received failure on topic: " + topic8 + " - " + exc);
                RelayJsonRpcInteractorInterface access$getJsonRpcInteractor$p3 = this.this$0.jsonRpcInteractor;
                WCRequest wCRequest5 = this.$request;
                String message = exc.getMessage();
                Topic topic9 = this.$request.getTopic();
                RelayJsonRpcInteractorInterface.respondWithError$default(access$getJsonRpcInteractor$p3, wCRequest5, new Uncategorized.GenericError("Cannot emit an event: " + message + ", topic: " + topic9), irnParams, (EnvelopeType) null, (Participants) null, (Function1) null, (Function1) null, 120, (Object) null);
                MutableSharedFlow access$get_events$p2 = this.this$0._events;
                SDKError sDKError = new SDKError(exc);
                this.L$0 = SpillingKt.nullOutSpilledVariable(irnParams);
                this.L$1 = SpillingKt.nullOutSpilledVariable(exc);
                this.L$2 = null;
                this.label = 2;
                if (access$get_events$p2.emit(sDKError, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                return Unit.INSTANCE;
            }
        } else if (i3 == 1) {
            SessionEventVO sessionEventVO = (SessionEventVO) this.L$2;
            SessionVO sessionVO = (SessionVO) this.L$1;
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
        return ((OnSessionEventUseCase$invoke$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }
}
