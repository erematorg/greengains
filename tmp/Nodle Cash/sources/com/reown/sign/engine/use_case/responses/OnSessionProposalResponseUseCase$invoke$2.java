package com.reown.sign.engine.use_case.responses;

import com.reown.android.Core;
import com.reown.android.internal.common.JsonRpcResponse;
import com.reown.android.internal.common.WalletConnectScopeKt;
import com.reown.android.internal.common.model.SDKError;
import com.reown.android.internal.common.model.WCResponse;
import com.reown.android.internal.common.model.params.CoreSignParams;
import com.reown.android.internal.common.model.type.RelayJsonRpcInteractorInterface;
import com.reown.android.pairing.handler.PairingControllerInterface;
import com.reown.foundation.common.model.PublicKey;
import com.reown.foundation.common.model.Topic;
import com.reown.foundation.util.Logger;
import com.reown.sign.common.model.vo.clientsync.session.params.SignParams;
import com.reown.sign.engine.model.EngineDO;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.flow.MutableSharedFlow;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.reown.sign.engine.use_case.responses.OnSessionProposalResponseUseCase$invoke$2", f = "OnSessionProposalResponseUseCase.kt", i = {0, 0, 1}, l = {59, 64}, m = "invokeSuspend", n = {"pairingTopic", "response", "e"}, s = {"L$0", "L$1", "L$0"})
public final class OnSessionProposalResponseUseCase$invoke$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ SignParams.SessionProposeParams $params;
    final /* synthetic */ WCResponse $wcResponse;
    Object L$0;
    Object L$1;
    int label;
    final /* synthetic */ OnSessionProposalResponseUseCase this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public OnSessionProposalResponseUseCase$invoke$2(OnSessionProposalResponseUseCase onSessionProposalResponseUseCase, WCResponse wCResponse, SignParams.SessionProposeParams sessionProposeParams, Continuation<? super OnSessionProposalResponseUseCase$invoke$2> continuation) {
        super(2, continuation);
        this.this$0 = onSessionProposalResponseUseCase;
        this.$wcResponse = wCResponse;
        this.$params = sessionProposeParams;
    }

    /* access modifiers changed from: private */
    public static final Unit invokeSuspend$lambda$0(OnSessionProposalResponseUseCase onSessionProposalResponseUseCase, Topic topic, Topic topic2) {
        Logger access$getLogger$p = onSessionProposalResponseUseCase.logger;
        access$getLogger$p.log("Session proposal approval subscribed on session topic: " + topic);
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final Unit invokeSuspend$lambda$1(OnSessionProposalResponseUseCase onSessionProposalResponseUseCase, Topic topic, Throwable th) {
        Logger access$getLogger$p = onSessionProposalResponseUseCase.logger;
        access$getLogger$p.error("Session proposal approval subscribe error on session topic: " + topic + " - " + th);
        Job unused = BuildersKt__Builders_commonKt.launch$default(WalletConnectScopeKt.getScope(), (CoroutineContext) null, (CoroutineStart) null, new OnSessionProposalResponseUseCase$invoke$2$2$1(onSessionProposalResponseUseCase, th, (Continuation<? super OnSessionProposalResponseUseCase$invoke$2$2$1>) null), 3, (Object) null);
        return Unit.INSTANCE;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new OnSessionProposalResponseUseCase$invoke$2(this.this$0, this.$wcResponse, this.$params, continuation);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i3 = this.label;
        if (i3 == 0) {
            ResultKt.throwOnFailure(obj);
            Logger access$getLogger$p = this.this$0.logger;
            Topic topic = this.$wcResponse.getTopic();
            access$getLogger$p.log("Session proposal response received on topic: " + topic);
            Topic topic2 = this.$wcResponse.getTopic();
            PairingControllerInterface.deleteAndUnsubscribePairing$default(this.this$0.pairingController, new Core.Params.Delete(topic2.getValue()), (Function1) null, 2, (Object) null);
            JsonRpcResponse response = this.$wcResponse.getResponse();
            if (response instanceof JsonRpcResponse.JsonRpcResult) {
                Logger access$getLogger$p2 = this.this$0.logger;
                Topic topic3 = this.$wcResponse.getTopic();
                access$getLogger$p2.log("Session proposal approval received on topic: " + topic3);
                String r10 = PublicKey.m8856constructorimpl(this.$params.getProposer().getPublicKey());
                Object result = ((JsonRpcResponse.JsonRpcResult) response).getResult();
                Intrinsics.checkNotNull(result, "null cannot be cast to non-null type com.reown.android.internal.common.model.params.CoreSignParams.ApprovalParams");
                Topic r102 = this.this$0.crypto.m8726generateTopicFromKeyAgreementV_lFtQw(r10, PublicKey.m8856constructorimpl(((CoreSignParams.ApprovalParams) result).getResponderPublicKey()));
                RelayJsonRpcInteractorInterface access$getJsonRpcInteractor$p = this.this$0.jsonRpcInteractor;
                OnSessionProposalResponseUseCase onSessionProposalResponseUseCase = this.this$0;
                access$getJsonRpcInteractor$p.subscribe(r102, new b(onSessionProposalResponseUseCase, r102, 0), new b(onSessionProposalResponseUseCase, r102, 1));
            } else if (response instanceof JsonRpcResponse.JsonRpcError) {
                this.this$0.proposalStorageRepository.deleteProposal$sign_release(this.$params.getProposer().getPublicKey());
                Logger access$getLogger$p3 = this.this$0.logger;
                Topic topic4 = this.$wcResponse.getTopic();
                access$getLogger$p3.log("Session proposal rejection received on topic: " + topic4);
                MutableSharedFlow access$get_events$p = this.this$0._events;
                EngineDO.SessionRejected sessionRejected = new EngineDO.SessionRejected(topic2.getValue(), ((JsonRpcResponse.JsonRpcError) response).getErrorMessage());
                this.L$0 = SpillingKt.nullOutSpilledVariable(topic2);
                this.L$1 = SpillingKt.nullOutSpilledVariable(response);
                this.label = 1;
                if (access$get_events$p.emit(sessionRejected, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                throw new NoWhenBranchMatchedException();
            }
        } else if (i3 == 1) {
            JsonRpcResponse jsonRpcResponse = (JsonRpcResponse) this.L$1;
            Topic topic5 = (Topic) this.L$0;
            try {
                ResultKt.throwOnFailure(obj);
            } catch (Exception e3) {
                Logger access$getLogger$p4 = this.this$0.logger;
                Topic topic6 = this.$wcResponse.getTopic();
                access$getLogger$p4.error("Session proposal response received failure on topic: " + topic6 + ": " + e3);
                MutableSharedFlow access$get_events$p2 = this.this$0._events;
                SDKError sDKError = new SDKError(e3);
                this.L$0 = SpillingKt.nullOutSpilledVariable(e3);
                this.L$1 = null;
                this.label = 2;
                if (access$get_events$p2.emit(sDKError, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            }
        } else if (i3 == 2) {
            Exception exc = (Exception) this.L$0;
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return Unit.INSTANCE;
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((OnSessionProposalResponseUseCase$invoke$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }
}
