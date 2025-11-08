package com.reown.sign.client;

import com.reown.android.internal.common.model.SDKError;
import com.reown.android.internal.common.model.type.EngineEvent;
import com.reown.sign.client.SignInterface;
import com.reown.sign.client.mapper.ClientMapperKt;
import com.reown.sign.engine.model.EngineDO;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "event", "Lcom/reown/android/internal/common/model/type/EngineEvent;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.reown.sign.client.SignProtocol$setDappDelegate$2", f = "SignProtocol.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
public final class SignProtocol$setDappDelegate$2 extends SuspendLambda implements Function2<EngineEvent, Continuation<? super Unit>, Object> {
    final /* synthetic */ SignInterface.DappDelegate $delegate;
    /* synthetic */ Object L$0;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public SignProtocol$setDappDelegate$2(SignInterface.DappDelegate dappDelegate, Continuation<? super SignProtocol$setDappDelegate$2> continuation) {
        super(2, continuation);
        this.$delegate = dappDelegate;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        SignProtocol$setDappDelegate$2 signProtocol$setDappDelegate$2 = new SignProtocol$setDappDelegate$2(this.$delegate, continuation);
        signProtocol$setDappDelegate$2.L$0 = obj;
        return signProtocol$setDappDelegate$2;
    }

    public final Object invoke(EngineEvent engineEvent, Continuation<? super Unit> continuation) {
        return ((SignProtocol$setDappDelegate$2) create(engineEvent, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        EngineEvent engineEvent = (EngineEvent) this.L$0;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            if (engineEvent instanceof EngineDO.SessionRejected) {
                this.$delegate.onSessionRejected(Intrinsics.checkNotNullParameter((EngineDO.SessionRejected) engineEvent, "<this>"));
            } else if (engineEvent instanceof EngineDO.SessionApproved) {
                this.$delegate.onSessionApproved(ClientMapperKt.toClientSessionApproved((EngineDO.SessionApproved) engineEvent));
            } else if (engineEvent instanceof EngineDO.SessionUpdateNamespaces) {
                this.$delegate.onSessionUpdate(Intrinsics.checkNotNullParameter((EngineDO.SessionUpdateNamespaces) engineEvent, "<this>"));
            } else if (engineEvent instanceof EngineDO.SessionDelete) {
                this.$delegate.onSessionDelete(Intrinsics.checkNotNullParameter((EngineDO.SessionDelete) engineEvent, "<this>"));
            } else if (engineEvent instanceof EngineDO.SessionEvent) {
                EngineDO.SessionEvent sessionEvent = (EngineDO.SessionEvent) engineEvent;
                this.$delegate.onSessionEvent(Intrinsics.checkNotNullParameter(sessionEvent, "<this>"));
                this.$delegate.onSessionEvent(Intrinsics.checkNotNullParameter(sessionEvent, "<this>"));
            } else if (engineEvent instanceof EngineDO.SessionExtend) {
                this.$delegate.onSessionExtend(ClientMapperKt.toClientActiveSession((EngineDO.SessionExtend) engineEvent));
            } else if (engineEvent instanceof EngineDO.SessionPayloadResponse) {
                this.$delegate.onSessionRequestResponse(Intrinsics.checkNotNullParameter((EngineDO.SessionPayloadResponse) engineEvent, "<this>"));
            } else if (engineEvent instanceof EngineDO.SessionAuthenticateResponse) {
                this.$delegate.onSessionAuthenticateResponse(ClientMapperKt.toClientSessionAuthenticateResponse((EngineDO.SessionAuthenticateResponse) engineEvent));
            } else if (engineEvent instanceof EngineDO.ExpiredProposal) {
                this.$delegate.onProposalExpired(Intrinsics.checkNotNullParameter((EngineDO.ExpiredProposal) engineEvent, "<this>"));
            } else if (engineEvent instanceof EngineDO.ExpiredRequest) {
                this.$delegate.onRequestExpired(Intrinsics.checkNotNullParameter((EngineDO.ExpiredRequest) engineEvent, "<this>"));
            } else if (engineEvent instanceof SDKError) {
                this.$delegate.onError(Intrinsics.checkNotNullParameter((SDKError) engineEvent, "<this>"));
            }
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
