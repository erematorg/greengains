package com.reown.sign.engine.use_case.calls;

import androidx.compose.animation.core.a;
import com.reown.android.internal.common.JsonRpcResponse;
import com.reown.android.internal.common.WalletConnectScopeKt;
import com.reown.android.internal.common.exception.RequestExpiredException;
import com.reown.android.internal.common.json_rpc.domain.link_mode.LinkModeJsonRpcInteractorInterface;
import com.reown.android.internal.common.model.AppMetaData;
import com.reown.android.internal.common.model.EnvelopeType;
import com.reown.android.internal.common.model.Expiry;
import com.reown.android.internal.common.model.IrnParams;
import com.reown.android.internal.common.model.Participants;
import com.reown.android.internal.common.model.Redirect;
import com.reown.android.internal.common.model.SymmetricKey;
import com.reown.android.internal.common.model.Tags;
import com.reown.android.internal.common.model.TransportType;
import com.reown.android.internal.common.model.type.RelayJsonRpcInteractorInterface;
import com.reown.android.internal.utils.CoreValidator;
import com.reown.android.internal.utils.Time;
import com.reown.android.pulse.domain.InsertEventUseCase;
import com.reown.android.pulse.model.Direction;
import com.reown.android.pulse.model.properties.Properties;
import com.reown.android.pulse.model.properties.Props;
import com.reown.foundation.common.model.PublicKey;
import com.reown.foundation.common.model.Topic;
import com.reown.foundation.common.model.Ttl;
import com.reown.foundation.util.Logger;
import com.reown.sign.common.exceptions.MissingSessionAuthenticateRequest;
import com.reown.sign.common.model.Request;
import com.reown.sign.common.model.vo.clientsync.session.params.SignParams;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
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
@DebugMetadata(c = "com.reown.sign.engine.use_case.calls.RejectSessionAuthenticateUseCase$rejectSessionAuthenticate$2", f = "RejectSessionAuthenticateUseCase.kt", i = {0, 0, 0, 0, 0, 0, 0, 0}, l = {79}, m = "invokeSuspend", n = {"jsonRpcHistoryEntry", "response", "sessionAuthenticateParams", "receiverMetadata", "receiverPublicKey", "senderPublicKey", "symmetricKey", "responseTopic"}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7"})
public final class RejectSessionAuthenticateUseCase$rejectSessionAuthenticate$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ long $id;
    final /* synthetic */ Function1<Throwable, Unit> $onFailure;
    final /* synthetic */ Function0<Unit> $onSuccess;
    final /* synthetic */ String $reason;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    Object L$6;
    Object L$7;
    int label;
    final /* synthetic */ RejectSessionAuthenticateUseCase this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public RejectSessionAuthenticateUseCase$rejectSessionAuthenticate$2(RejectSessionAuthenticateUseCase rejectSessionAuthenticateUseCase, long j2, Function1<? super Throwable, Unit> function1, String str, Function0<Unit> function0, Continuation<? super RejectSessionAuthenticateUseCase$rejectSessionAuthenticate$2> continuation) {
        super(2, continuation);
        this.this$0 = rejectSessionAuthenticateUseCase;
        this.$id = j2;
        this.$onFailure = function1;
        this.$reason = str;
        this.$onSuccess = function0;
    }

    /* access modifiers changed from: private */
    public static final Unit invokeSuspend$lambda$1(RejectSessionAuthenticateUseCase rejectSessionAuthenticateUseCase, Topic topic, Function0 function0, long j2) {
        Logger access$getLogger$p = rejectSessionAuthenticateUseCase.logger;
        access$getLogger$p.log("Session Authenticate Reject Responded on topic: " + topic);
        Job unused = BuildersKt__Builders_commonKt.launch$default(WalletConnectScopeKt.getScope(), (CoroutineContext) null, (CoroutineStart) null, new RejectSessionAuthenticateUseCase$rejectSessionAuthenticate$2$2$1(rejectSessionAuthenticateUseCase, j2, (Continuation<? super RejectSessionAuthenticateUseCase$rejectSessionAuthenticate$2$2$1>) null), 3, (Object) null);
        function0.invoke();
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final Unit invokeSuspend$lambda$2(RejectSessionAuthenticateUseCase rejectSessionAuthenticateUseCase, Topic topic, Function1 function1, long j2, Throwable th) {
        Logger access$getLogger$p = rejectSessionAuthenticateUseCase.logger;
        access$getLogger$p.error("Session Authenticate Error Responded on topic: " + topic);
        Job unused = BuildersKt__Builders_commonKt.launch$default(WalletConnectScopeKt.getScope(), (CoroutineContext) null, (CoroutineStart) null, new RejectSessionAuthenticateUseCase$rejectSessionAuthenticate$2$3$1(rejectSessionAuthenticateUseCase, j2, (Continuation<? super RejectSessionAuthenticateUseCase$rejectSessionAuthenticate$2$3$1>) null), 3, (Object) null);
        function1.invoke(th);
        return Unit.INSTANCE;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new RejectSessionAuthenticateUseCase$rejectSessionAuthenticate$2(this.this$0, this.$id, this.$onFailure, this.$reason, this.$onSuccess, continuation);
    }

    public final Object invokeSuspend(Object obj) {
        Redirect redirect;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i3 = this.label;
        if (i3 == 0) {
            ResultKt.throwOnFailure(obj);
            Request<SignParams.SessionAuthenticateParams> invoke$sign_release = this.this$0.getPendingSessionAuthenticateRequest.invoke$sign_release(this.$id);
            if (invoke$sign_release == null) {
                this.this$0.logger.error(new MissingSessionAuthenticateRequest().getMessage());
                this.$onFailure.invoke(new MissingSessionAuthenticateRequest());
                return Unit.INSTANCE;
            }
            Expiry expiry = invoke$sign_release.getExpiry();
            if (expiry != null) {
                RejectSessionAuthenticateUseCase rejectSessionAuthenticateUseCase = this.this$0;
                if (CoreValidator.INSTANCE.isExpired(expiry)) {
                    Logger access$getLogger$p = rejectSessionAuthenticateUseCase.logger;
                    Topic topic = invoke$sign_release.getTopic();
                    long id = invoke$sign_release.getId();
                    access$getLogger$p.error("Session Authenticate Request Expired: " + topic + ", id: " + id);
                    throw new RequestExpiredException(a.s("This request has expired, id: ", invoke$sign_release.getId()));
                }
            }
            JsonRpcResponse.JsonRpcError jsonRpcError = new JsonRpcResponse.JsonRpcError(this.$id, (String) null, new JsonRpcResponse.Error(12001, this.$reason), 2, (DefaultConstructorMarker) null);
            SignParams.SessionAuthenticateParams params = invoke$sign_release.getParams();
            AppMetaData metadata = params.getRequester().getMetadata();
            String r13 = PublicKey.m8856constructorimpl(params.getRequester().getPublicKey());
            String r14 = this.this$0.crypto.m8724generateAndStoreX25519KeyPairuN_RPug();
            String r15 = this.this$0.crypto.m8725generateSymmetricKeyFromKeyAgreementrMsFr_I(r14, r13);
            Topic topicFromKey = this.this$0.crypto.getTopicFromKey(PublicKey.m8855boximpl(r13));
            this.this$0.crypto.setKey(SymmetricKey.m8777boximpl(r15), topicFromKey.getValue());
            if (invoke$sign_release.getTransportType() == TransportType.LINK_MODE && (redirect = metadata.getRedirect()) != null && redirect.getLinkMode()) {
                Redirect redirect2 = metadata.getRedirect();
                String universal = redirect2 != null ? redirect2.getUniversal() : null;
                if (universal == null || universal.length() == 0) {
                    this.$onFailure.invoke(new IllegalStateException("App link is missing"));
                    return Unit.INSTANCE;
                }
                LinkModeJsonRpcInteractorInterface access$getLinkModeJsonRpcInteractor$p = this.this$0.linkModeJsonRpcInteractor;
                Redirect redirect3 = metadata.getRedirect();
                String universal2 = redirect3 != null ? redirect3.getUniversal() : null;
                Intrinsics.checkNotNull(universal2);
                access$getLinkModeJsonRpcInteractor$p.triggerResponse(topicFromKey, jsonRpcError, universal2, new Participants(r14, r13, (DefaultConstructorMarker) null), EnvelopeType.ONE);
                InsertEventUseCase access$getInsertEventUseCase$p = this.this$0.insertEventUseCase;
                Props props = new Props("SUCCESS", String.valueOf(Tags.SESSION_AUTHENTICATE_LINK_MODE_RESPONSE_REJECT.getId()), new Properties((String) null, (String) null, (String) null, (Boolean) null, (String) null, (String) null, (List) null, (String) null, Boxing.boxLong(this.$id), this.this$0.clientId, Direction.SENT.getState(), (String) null, 2303, (DefaultConstructorMarker) null));
                this.L$0 = SpillingKt.nullOutSpilledVariable(invoke$sign_release);
                this.L$1 = SpillingKt.nullOutSpilledVariable(jsonRpcError);
                this.L$2 = SpillingKt.nullOutSpilledVariable(params);
                this.L$3 = SpillingKt.nullOutSpilledVariable(metadata);
                this.L$4 = SpillingKt.nullOutSpilledVariable(r13);
                this.L$5 = SpillingKt.nullOutSpilledVariable(r14);
                this.L$6 = SpillingKt.nullOutSpilledVariable(r15);
                this.L$7 = SpillingKt.nullOutSpilledVariable(topicFromKey);
                this.label = 1;
                if (access$getInsertEventUseCase$p.invoke(props, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                IrnParams irnParams = new IrnParams(Tags.SESSION_AUTHENTICATE_RESPONSE_REJECT, new Ttl(Time.getDayInSeconds()), Boxing.boxLong(jsonRpcError.getId()), (List) null, (String) null, (List) null, (List) null, false, 120, (DefaultConstructorMarker) null);
                Logger access$getLogger$p2 = this.this$0.logger;
                StringBuilder sb = new StringBuilder("Sending Session Authenticate Reject on topic: ");
                Topic topic2 = topicFromKey;
                sb.append(topic2);
                access$getLogger$p2.log(sb.toString());
                RelayJsonRpcInteractorInterface access$getJsonRpcInteractor$p = this.this$0.jsonRpcInteractor;
                EnvelopeType envelopeType = EnvelopeType.ONE;
                Participants participants = new Participants(r14, r13, (DefaultConstructorMarker) null);
                RejectSessionAuthenticateUseCase rejectSessionAuthenticateUseCase2 = this.this$0;
                Function0<Unit> function0 = this.$onSuccess;
                RejectSessionAuthenticateUseCase rejectSessionAuthenticateUseCase3 = rejectSessionAuthenticateUseCase2;
                Topic topic3 = topic2;
                long j2 = this.$id;
                access$getJsonRpcInteractor$p.publishJsonRpcResponse(topic2, irnParams, jsonRpcError, new l(rejectSessionAuthenticateUseCase3, topic3, function0, j2), new m(rejectSessionAuthenticateUseCase3, topic3, this.$onFailure, j2), participants, envelopeType);
            }
        } else if (i3 == 1) {
            Topic topic4 = (Topic) this.L$7;
            String str = (String) this.L$6;
            String str2 = (String) this.L$5;
            String str3 = (String) this.L$4;
            AppMetaData appMetaData = (AppMetaData) this.L$3;
            SignParams.SessionAuthenticateParams sessionAuthenticateParams = (SignParams.SessionAuthenticateParams) this.L$2;
            JsonRpcResponse.JsonRpcError jsonRpcError2 = (JsonRpcResponse.JsonRpcError) this.L$1;
            Request request = (Request) this.L$0;
            try {
                ResultKt.throwOnFailure(obj);
            } catch (Exception e3) {
                this.$onFailure.invoke(e3);
            }
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return Unit.INSTANCE;
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((RejectSessionAuthenticateUseCase$rejectSessionAuthenticate$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }
}
