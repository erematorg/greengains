package com.reown.sign.engine.use_case.responses;

import com.reown.android.internal.common.JsonRpcResponse;
import com.reown.android.internal.common.model.AppMetaDataType;
import com.reown.android.internal.common.model.Expiry;
import com.reown.android.internal.common.model.SDKError;
import com.reown.android.internal.common.model.TransportType;
import com.reown.android.internal.common.model.WCResponse;
import com.reown.android.internal.common.model.type.RelayJsonRpcInteractorInterface;
import com.reown.foundation.common.model.Topic;
import com.reown.foundation.util.Logger;
import com.reown.sign.common.model.vo.sequence.SessionVO;
import com.reown.sign.engine.model.EngineDO;
import com.reown.sign.engine.model.mapper.EngineMapperKt;
import java.util.Map;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.MutableSharedFlow;

@SourceDebugExtension({"SMAP\nOnSessionSettleResponseUseCase.kt\nKotlin\n*S Kotlin\n*F\n+ 1 OnSessionSettleResponseUseCase.kt\ncom/reown/sign/engine/use_case/responses/OnSessionSettleResponseUseCase$invoke$2\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,65:1\n1#2:66\n*E\n"})
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.reown.sign.engine.use_case.responses.OnSessionSettleResponseUseCase$invoke$2", f = "OnSessionSettleResponseUseCase.kt", i = {0, 0, 0, 1, 1}, l = {47, 62}, m = "invokeSuspend", n = {"$this$supervisorScope", "sessionTopic", "session", "$this$supervisorScope", "e"}, s = {"L$0", "L$1", "L$2", "L$0", "L$1"})
public final class OnSessionSettleResponseUseCase$invoke$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ WCResponse $wcResponse;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    int label;
    final /* synthetic */ OnSessionSettleResponseUseCase this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public OnSessionSettleResponseUseCase$invoke$2(OnSessionSettleResponseUseCase onSessionSettleResponseUseCase, WCResponse wCResponse, Continuation<? super OnSessionSettleResponseUseCase$invoke$2> continuation) {
        super(2, continuation);
        this.this$0 = onSessionSettleResponseUseCase;
        this.$wcResponse = wCResponse;
    }

    /* access modifiers changed from: private */
    public static final Unit invokeSuspend$lambda$3(CoroutineScope coroutineScope, OnSessionSettleResponseUseCase onSessionSettleResponseUseCase, Topic topic) {
        Object obj;
        try {
            Result.Companion companion = Result.Companion;
            onSessionSettleResponseUseCase.sessionStorageRepository.deleteSession(topic);
            onSessionSettleResponseUseCase.crypto.removeKeys(topic.getValue());
            obj = Result.m8979constructorimpl(Unit.INSTANCE);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.Companion;
            obj = Result.m8979constructorimpl(ResultKt.createFailure(th));
        }
        Throwable r02 = Result.m8982exceptionOrNullimpl(obj);
        if (r02 != null) {
            onSessionSettleResponseUseCase.logger.error(r02);
        }
        return Unit.INSTANCE;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        OnSessionSettleResponseUseCase$invoke$2 onSessionSettleResponseUseCase$invoke$2 = new OnSessionSettleResponseUseCase$invoke$2(this.this$0, this.$wcResponse, continuation);
        onSessionSettleResponseUseCase$invoke$2.L$0 = obj;
        return onSessionSettleResponseUseCase$invoke$2;
    }

    public final Object invokeSuspend(Object obj) {
        CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i3 = this.label;
        if (i3 == 0) {
            ResultKt.throwOnFailure(obj);
            Logger access$getLogger$p = this.this$0.logger;
            Topic topic = this.$wcResponse.getTopic();
            access$getLogger$p.log("Session settle response received on topic: " + topic);
            Topic topic2 = this.$wcResponse.getTopic();
            if (!this.this$0.sessionStorageRepository.isSessionValid(topic2)) {
                this.this$0.logger.error("Peer failed to settle session: invalid session");
                return Unit.INSTANCE;
            }
            SessionVO sessionWithoutMetadataByTopic = this.this$0.sessionStorageRepository.getSessionWithoutMetadataByTopic(topic2);
            SessionVO r02 = SessionVO.m8871copypMwxKLQ$default(sessionWithoutMetadataByTopic, (Topic) null, (Expiry) null, (String) null, (String) null, (String) null, (String) null, sessionWithoutMetadataByTopic.getSelfAppMetaData(), (String) null, this.this$0.metadataStorageRepository.getByTopicAndType(sessionWithoutMetadataByTopic.getTopic(), AppMetaDataType.PEER), (Map) null, (Map) null, (Map) null, (Map) null, (Map) null, false, (String) null, (TransportType) null, 130751, (Object) null);
            JsonRpcResponse response = this.$wcResponse.getResponse();
            if (response instanceof JsonRpcResponse.JsonRpcResult) {
                this.this$0.logger.log("Session settle success received");
                this.this$0.sessionStorageRepository.acknowledgeSession(topic2);
                MutableSharedFlow access$get_events$p = this.this$0._events;
                EngineDO.SettledSessionResponse.Result result = new EngineDO.SettledSessionResponse.Result(EngineMapperKt.toEngineDO(r02));
                this.L$0 = SpillingKt.nullOutSpilledVariable(coroutineScope);
                this.L$1 = SpillingKt.nullOutSpilledVariable(topic2);
                this.L$2 = SpillingKt.nullOutSpilledVariable(r02);
                this.label = 1;
                if (access$get_events$p.emit(result, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else if (response instanceof JsonRpcResponse.JsonRpcError) {
                Logger access$getLogger$p2 = this.this$0.logger;
                JsonRpcResponse response2 = this.$wcResponse.getResponse();
                Intrinsics.checkNotNull(response2, "null cannot be cast to non-null type com.reown.android.internal.common.JsonRpcResponse.JsonRpcError");
                String errorMessage = ((JsonRpcResponse.JsonRpcError) response2).getErrorMessage();
                access$getLogger$p2.error("Peer failed to settle session: " + errorMessage);
                RelayJsonRpcInteractorInterface.unsubscribe$default(this.this$0.jsonRpcInteractor, topic2, new c(coroutineScope, this.this$0, topic2), (Function1) null, 4, (Object) null);
            } else {
                throw new NoWhenBranchMatchedException();
            }
        } else if (i3 == 1) {
            SessionVO sessionVO = (SessionVO) this.L$2;
            Topic topic3 = (Topic) this.L$1;
            try {
                ResultKt.throwOnFailure(obj);
            } catch (Exception e3) {
                Logger access$getLogger$p3 = this.this$0.logger;
                access$getLogger$p3.error("Peer failed to settle session: " + e3);
                MutableSharedFlow access$get_events$p2 = this.this$0._events;
                SDKError sDKError = new SDKError(e3);
                this.L$0 = SpillingKt.nullOutSpilledVariable(coroutineScope);
                this.L$1 = SpillingKt.nullOutSpilledVariable(e3);
                this.L$2 = null;
                this.label = 2;
                if (access$get_events$p2.emit(sDKError, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            }
        } else if (i3 == 2) {
            Exception exc = (Exception) this.L$1;
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return Unit.INSTANCE;
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((OnSessionSettleResponseUseCase$invoke$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }
}
