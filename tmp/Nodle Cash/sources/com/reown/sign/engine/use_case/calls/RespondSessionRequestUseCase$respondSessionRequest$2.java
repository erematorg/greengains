package com.reown.sign.engine.use_case.calls;

import androidx.browser.trusted.c;
import androidx.compose.animation.core.a;
import com.reown.android.internal.common.JsonRpcResponse;
import com.reown.android.internal.common.WalletConnectScopeKt;
import com.reown.android.internal.common.exception.CannotFindSequenceForTopic;
import com.reown.android.internal.common.exception.RequestExpiredException;
import com.reown.android.internal.common.json_rpc.domain.link_mode.LinkModeJsonRpcInteractorInterface;
import com.reown.android.internal.common.model.AppMetaData;
import com.reown.android.internal.common.model.AppMetaDataType;
import com.reown.android.internal.common.model.EnvelopeType;
import com.reown.android.internal.common.model.Expiry;
import com.reown.android.internal.common.model.IrnParams;
import com.reown.android.internal.common.model.Participants;
import com.reown.android.internal.common.model.Tags;
import com.reown.android.internal.common.model.TransportType;
import com.reown.android.internal.common.model.type.RelayJsonRpcInteractorInterface;
import com.reown.android.internal.utils.CoreValidator;
import com.reown.android.internal.utils.Time;
import com.reown.android.pulse.domain.InsertEventUseCase;
import com.reown.android.pulse.model.Direction;
import com.reown.android.pulse.model.properties.Properties;
import com.reown.android.pulse.model.properties.Props;
import com.reown.foundation.common.model.Topic;
import com.reown.foundation.common.model.Ttl;
import com.reown.foundation.util.Logger;
import com.reown.sign.common.model.Request;
import com.reown.sign.common.model.vo.clientsync.session.params.SignParams;
import com.reown.sign.common.model.vo.sequence.SessionVO;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Triple;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.reown.sign.engine.use_case.calls.RespondSessionRequestUseCase$respondSessionRequest$2", f = "RespondSessionRequestUseCase.kt", i = {0, 0, 0, 1, 1, 1}, l = {84, 86}, m = "invokeSuspend", n = {"topicWrapper", "session", "pendingRequest", "topicWrapper", "session", "pendingRequest"}, s = {"L$0", "L$1", "L$2", "L$0", "L$1", "L$2"})
public final class RespondSessionRequestUseCase$respondSessionRequest$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ JsonRpcResponse $jsonRpcResponse;
    final /* synthetic */ Function1<Throwable, Unit> $onFailure;
    final /* synthetic */ Function0<Unit> $onSuccess;
    final /* synthetic */ String $topic;
    Object L$0;
    Object L$1;
    Object L$2;
    int label;
    final /* synthetic */ RespondSessionRequestUseCase this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public RespondSessionRequestUseCase$respondSessionRequest$2(String str, RespondSessionRequestUseCase respondSessionRequestUseCase, JsonRpcResponse jsonRpcResponse, Function1<? super Throwable, Unit> function1, Function0<Unit> function0, Continuation<? super RespondSessionRequestUseCase$respondSessionRequest$2> continuation) {
        super(2, continuation);
        this.$topic = str;
        this.this$0 = respondSessionRequestUseCase;
        this.$jsonRpcResponse = jsonRpcResponse;
        this.$onFailure = function1;
        this.$onSuccess = function0;
    }

    /* access modifiers changed from: private */
    public static final Unit invokeSuspend$lambda$3(Function0 function0, RespondSessionRequestUseCase respondSessionRequestUseCase, String str, JsonRpcResponse jsonRpcResponse) {
        function0.invoke();
        Logger access$getLogger$p = respondSessionRequestUseCase.logger;
        long id = jsonRpcResponse.getId();
        access$getLogger$p.log("Session request response sent successfully on topic: " + str + ", id: " + id);
        Job unused = BuildersKt__Builders_commonKt.launch$default(WalletConnectScopeKt.getScope(), (CoroutineContext) null, (CoroutineStart) null, new RespondSessionRequestUseCase$respondSessionRequest$2$2$1(respondSessionRequestUseCase, jsonRpcResponse, (Continuation<? super RespondSessionRequestUseCase$respondSessionRequest$2$2$1>) null), 3, (Object) null);
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final Unit invokeSuspend$lambda$4(RespondSessionRequestUseCase respondSessionRequestUseCase, JsonRpcResponse jsonRpcResponse, Function1 function1, Throwable th) {
        Logger access$getLogger$p = respondSessionRequestUseCase.logger;
        long id = jsonRpcResponse.getId();
        access$getLogger$p.error("Sending session response error: " + th + ", id: " + id);
        function1.invoke(th);
        return Unit.INSTANCE;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new RespondSessionRequestUseCase$respondSessionRequest$2(this.$topic, this.this$0, this.$jsonRpcResponse, this.$onFailure, this.$onSuccess, continuation);
    }

    public final Object invokeSuspend(Object obj) {
        Topic topic;
        SessionVO sessionVO;
        Request<SignParams.SessionRequestParams> request;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i3 = this.label;
        if (i3 == 0) {
            ResultKt.throwOnFailure(obj);
            topic = new Topic(this.$topic);
            if (!this.this$0.sessionStorageRepository.isSessionValid(topic)) {
                Logger access$getLogger$p = this.this$0.logger;
                String str = this.$topic;
                long id = this.$jsonRpcResponse.getId();
                access$getLogger$p.error("Request response -  invalid session: " + str + ", id: " + id);
                this.$onFailure.invoke(new CannotFindSequenceForTopic(c.a("Cannot find sequence for given topic: ", this.$topic)));
                return Unit.INSTANCE;
            }
            SessionVO sessionWithoutMetadataByTopic = this.this$0.sessionStorageRepository.getSessionWithoutMetadataByTopic(topic);
            SessionVO r2 = SessionVO.m8871copypMwxKLQ$default(sessionWithoutMetadataByTopic, (Topic) null, (Expiry) null, (String) null, (String) null, (String) null, (String) null, (AppMetaData) null, (String) null, this.this$0.metadataStorageRepository.getByTopicAndType(sessionWithoutMetadataByTopic.getTopic(), AppMetaDataType.PEER), (Map) null, (Map) null, (Map) null, (Map) null, (Map) null, false, (String) null, (TransportType) null, 130815, (Object) null);
            Request<SignParams.SessionRequestParams> invoke = this.this$0.getPendingJsonRpcHistoryEntryByIdUseCase.invoke(this.$jsonRpcResponse.getId());
            if (invoke == null) {
                Logger access$getLogger$p2 = this.this$0.logger;
                String str2 = this.$topic;
                long id2 = this.$jsonRpcResponse.getId();
                access$getLogger$p2.error("Request doesn't exist: " + str2 + ", id: " + id2);
                this.$onFailure.invoke(new RequestExpiredException(a.s("This request has expired, id: ", this.$jsonRpcResponse.getId())));
                return Unit.INSTANCE;
            }
            Long expiry = invoke.getParams().getExpiry();
            if (expiry != null) {
                RespondSessionRequestUseCase respondSessionRequestUseCase = this.this$0;
                String str3 = this.$topic;
                JsonRpcResponse jsonRpcResponse = this.$jsonRpcResponse;
                Function1<Throwable, Unit> function1 = this.$onFailure;
                if (CoreValidator.INSTANCE.isExpired(new Expiry(expiry.longValue()))) {
                    Logger access$getLogger$p3 = respondSessionRequestUseCase.logger;
                    long id3 = jsonRpcResponse.getId();
                    access$getLogger$p3.error("Request Expired: " + str3 + ", id: " + id3);
                    function1.invoke(new RequestExpiredException(a.s("This request has expired, id: ", jsonRpcResponse.getId())));
                    return Unit.INSTANCE;
                }
            }
            if (r2.getTransportType() != TransportType.LINK_MODE || !Intrinsics.areEqual((Object) r2.getPeerLinkMode(), (Object) Boxing.boxBoolean(true))) {
                Triple<List<String>, List<String>, String> collect = this.this$0.tvf.collect(invoke.getParams().getRpcMethod(), invoke.getParams().getRpcParams(), invoke.getParams().getChainId());
                JsonRpcResponse jsonRpcResponse2 = this.$jsonRpcResponse;
                List<String> list = null;
                JsonRpcResponse.JsonRpcResult jsonRpcResult = jsonRpcResponse2 instanceof JsonRpcResponse.JsonRpcResult ? (JsonRpcResponse.JsonRpcResult) jsonRpcResponse2 : null;
                if (jsonRpcResult != null) {
                    list = this.this$0.tvf.collectTxHashes(invoke.getParams().getRpcMethod(), String.valueOf(jsonRpcResult.getResult()), invoke.getParams().getRpcParams());
                }
                Tags tags = Tags.SESSION_REQUEST_RESPONSE;
                Ttl ttl = new Ttl(Time.getFiveMinutesInSeconds());
                long id4 = this.$jsonRpcResponse.getId();
                String third = collect.getThird();
                IrnParams irnParams = new IrnParams(tags, ttl, Boxing.boxLong(id4), collect.getFirst(), third, list, collect.getSecond(), false, 128, (DefaultConstructorMarker) null);
                Logger access$getLogger$p4 = this.this$0.logger;
                String str4 = this.$topic;
                long id5 = this.$jsonRpcResponse.getId();
                access$getLogger$p4.log("Sending session request response on topic: " + str4 + ", id: " + id5);
                RelayJsonRpcInteractorInterface access$getJsonRpcInteractor$p = this.this$0.jsonRpcInteractor;
                Topic topic2 = new Topic(this.$topic);
                JsonRpcResponse jsonRpcResponse3 = this.$jsonRpcResponse;
                Function0<Unit> function0 = this.$onSuccess;
                RespondSessionRequestUseCase respondSessionRequestUseCase2 = this.this$0;
                RelayJsonRpcInteractorInterface.publishJsonRpcResponse$default(access$getJsonRpcInteractor$p, topic2, irnParams, jsonRpcResponse3, new n(function0, respondSessionRequestUseCase2, this.$topic, jsonRpcResponse3), new a((Object) respondSessionRequestUseCase2, 6, (Object) jsonRpcResponse3, (Object) this.$onFailure), (Participants) null, (EnvelopeType) null, 96, (Object) null);
                return Unit.INSTANCE;
            }
            String peerAppLink = r2.getPeerAppLink();
            if (peerAppLink == null || peerAppLink.length() == 0) {
                this.$onFailure.invoke(new IllegalStateException("App link is missing"));
                return Unit.INSTANCE;
            }
            RespondSessionRequestUseCase respondSessionRequestUseCase3 = this.this$0;
            long id6 = this.$jsonRpcResponse.getId();
            this.L$0 = SpillingKt.nullOutSpilledVariable(topic);
            this.L$1 = r2;
            this.L$2 = SpillingKt.nullOutSpilledVariable(invoke);
            this.label = 1;
            if (respondSessionRequestUseCase3.removePendingSessionRequestAndEmit(id6, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            sessionVO = r2;
            request = invoke;
        } else if (i3 == 1) {
            request = (Request) this.L$2;
            sessionVO = (SessionVO) this.L$1;
            topic = (Topic) this.L$0;
            ResultKt.throwOnFailure(obj);
        } else if (i3 == 2) {
            Request request2 = (Request) this.L$2;
            SessionVO sessionVO2 = (SessionVO) this.L$1;
            Topic topic3 = (Topic) this.L$0;
            try {
                ResultKt.throwOnFailure(obj);
            } catch (Exception e3) {
                this.$onFailure.invoke(e3);
            }
            return Unit.INSTANCE;
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        LinkModeJsonRpcInteractorInterface.triggerResponse$default(this.this$0.linkModeJsonRpcInteractor, new Topic(this.$topic), this.$jsonRpcResponse, sessionVO.getPeerAppLink(), (Participants) null, (EnvelopeType) null, 24, (Object) null);
        InsertEventUseCase access$getInsertEventUseCase$p = this.this$0.insertEventUseCase;
        Props props = new Props("SUCCESS", String.valueOf(Tags.SESSION_REQUEST_LINK_MODE_RESPONSE.getId()), new Properties((String) null, (String) null, (String) null, (Boolean) null, (String) null, (String) null, (List) null, (String) null, Boxing.boxLong(this.$jsonRpcResponse.getId()), this.this$0.clientId, Direction.SENT.getState(), (String) null, 2303, (DefaultConstructorMarker) null));
        this.L$0 = SpillingKt.nullOutSpilledVariable(topic);
        this.L$1 = SpillingKt.nullOutSpilledVariable(sessionVO);
        this.L$2 = SpillingKt.nullOutSpilledVariable(request);
        this.label = 2;
        if (access$getInsertEventUseCase$p.invoke(props, this) == coroutine_suspended) {
            return coroutine_suspended;
        }
        return Unit.INSTANCE;
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((RespondSessionRequestUseCase$respondSessionRequest$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }
}
