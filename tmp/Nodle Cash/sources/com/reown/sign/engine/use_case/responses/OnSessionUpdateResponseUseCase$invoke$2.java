package com.reown.sign.engine.use_case.responses;

import com.reown.android.internal.common.JsonRpcResponse;
import com.reown.android.internal.common.model.SDKError;
import com.reown.android.internal.common.model.WCResponse;
import com.reown.foundation.common.model.Topic;
import com.reown.foundation.util.Logger;
import com.reown.sign.common.model.vo.sequence.SessionVO;
import com.reown.sign.engine.model.EngineDO;
import com.reown.sign.engine.model.mapper.EngineMapperKt;
import com.reown.utils.UtilFunctionsKt;
import java.util.Map;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.MutableSharedFlow;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.reown.sign.engine.use_case.responses.OnSessionUpdateResponseUseCase$invoke$2", f = "OnSessionUpdateResponseUseCase.kt", i = {0, 0, 0, 0, 0, 1, 1, 1, 2}, l = {43, 48, 53}, m = "invokeSuspend", n = {"sessionTopic", "session", "response", "namespaces", "responseId", "sessionTopic", "session", "response", "e"}, s = {"L$0", "L$1", "L$2", "L$3", "J$0", "L$0", "L$1", "L$2", "L$0"})
public final class OnSessionUpdateResponseUseCase$invoke$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ WCResponse $wcResponse;
    long J$0;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;
    final /* synthetic */ OnSessionUpdateResponseUseCase this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public OnSessionUpdateResponseUseCase$invoke$2(OnSessionUpdateResponseUseCase onSessionUpdateResponseUseCase, WCResponse wCResponse, Continuation<? super OnSessionUpdateResponseUseCase$invoke$2> continuation) {
        super(2, continuation);
        this.this$0 = onSessionUpdateResponseUseCase;
        this.$wcResponse = wCResponse;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new OnSessionUpdateResponseUseCase$invoke$2(this.this$0, this.$wcResponse, continuation);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i3 = this.label;
        if (i3 != 0) {
            if (i3 == 1) {
                Map map = (Map) this.L$3;
                JsonRpcResponse jsonRpcResponse = (JsonRpcResponse) this.L$2;
                SessionVO sessionVO = (SessionVO) this.L$1;
                Topic topic = (Topic) this.L$0;
            } else if (i3 == 2) {
                JsonRpcResponse jsonRpcResponse2 = (JsonRpcResponse) this.L$2;
                SessionVO sessionVO2 = (SessionVO) this.L$1;
                Topic topic2 = (Topic) this.L$0;
            } else if (i3 == 3) {
                Exception exc = (Exception) this.L$0;
                ResultKt.throwOnFailure(obj);
            } else {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            try {
                ResultKt.throwOnFailure(obj);
            } catch (Exception e3) {
                Logger access$getLogger$p = this.this$0.logger;
                access$getLogger$p.error("Peer failed to update session namespaces: " + e3);
                MutableSharedFlow access$get_events$p = this.this$0._events;
                SDKError sDKError = new SDKError(e3);
                this.L$0 = SpillingKt.nullOutSpilledVariable(e3);
                this.L$1 = null;
                this.L$2 = null;
                this.L$3 = null;
                this.label = 3;
                if (access$get_events$p.emit(sDKError, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            }
        } else {
            ResultKt.throwOnFailure(obj);
            Logger access$getLogger$p2 = this.this$0.logger;
            Topic topic3 = this.$wcResponse.getTopic();
            access$getLogger$p2.log("Session update namespaces response received on topic: " + topic3);
            Topic topic4 = this.$wcResponse.getTopic();
            if (!this.this$0.sessionStorageRepository.isSessionValid(topic4)) {
                return Unit.INSTANCE;
            }
            SessionVO sessionWithoutMetadataByTopic = this.this$0.sessionStorageRepository.getSessionWithoutMetadataByTopic(topic4);
            if (!this.this$0.sessionStorageRepository.isUpdatedNamespaceResponseValid(sessionWithoutMetadataByTopic.getTopic().getValue(), UtilFunctionsKt.extractTimestamp(this.$wcResponse.getResponse().getId()))) {
                this.this$0.logger.error("Session update namespaces response error: invalid namespaces");
                return Unit.INSTANCE;
            }
            JsonRpcResponse response = this.$wcResponse.getResponse();
            if (response instanceof JsonRpcResponse.JsonRpcResult) {
                Logger access$getLogger$p3 = this.this$0.logger;
                Topic topic5 = this.$wcResponse.getTopic();
                access$getLogger$p3.log("Session update namespaces response received on topic: " + topic5);
                long id = this.$wcResponse.getResponse().getId();
                Map tempNamespaces = this.this$0.sessionStorageRepository.getTempNamespaces(id);
                this.this$0.sessionStorageRepository.deleteTempNamespacesByTopic(topic4.getValue());
                this.this$0.sessionStorageRepository.deleteNamespaceAndInsertNewNamespace(sessionWithoutMetadataByTopic.getTopic().getValue(), tempNamespaces, id);
                this.this$0.sessionStorageRepository.markUnAckNamespaceAcknowledged(id);
                MutableSharedFlow access$get_events$p2 = this.this$0._events;
                EngineDO.SessionUpdateNamespacesResponse.Result result = new EngineDO.SessionUpdateNamespacesResponse.Result(sessionWithoutMetadataByTopic.getTopic(), EngineMapperKt.toMapOfEngineNamespacesSession(tempNamespaces));
                this.L$0 = SpillingKt.nullOutSpilledVariable(topic4);
                this.L$1 = SpillingKt.nullOutSpilledVariable(sessionWithoutMetadataByTopic);
                this.L$2 = SpillingKt.nullOutSpilledVariable(response);
                this.L$3 = SpillingKt.nullOutSpilledVariable(tempNamespaces);
                this.J$0 = id;
                this.label = 1;
                if (access$get_events$p2.emit(result, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else if (response instanceof JsonRpcResponse.JsonRpcError) {
                Logger access$getLogger$p4 = this.this$0.logger;
                JsonRpcResponse.Error error = ((JsonRpcResponse.JsonRpcError) response).getError();
                Topic topic6 = this.$wcResponse.getTopic();
                access$getLogger$p4.error("Peer failed to update session namespaces: " + error + " on topic: " + topic6);
                MutableSharedFlow access$get_events$p3 = this.this$0._events;
                EngineDO.SessionUpdateNamespacesResponse.Error error2 = new EngineDO.SessionUpdateNamespacesResponse.Error(((JsonRpcResponse.JsonRpcError) response).getErrorMessage());
                this.L$0 = SpillingKt.nullOutSpilledVariable(topic4);
                this.L$1 = SpillingKt.nullOutSpilledVariable(sessionWithoutMetadataByTopic);
                this.L$2 = SpillingKt.nullOutSpilledVariable(response);
                this.label = 2;
                if (access$get_events$p3.emit(error2, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                throw new NoWhenBranchMatchedException();
            }
        }
        return Unit.INSTANCE;
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((OnSessionUpdateResponseUseCase$invoke$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }
}
