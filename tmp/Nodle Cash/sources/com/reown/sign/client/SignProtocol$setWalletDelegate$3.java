package com.reown.sign.client;

import com.reown.android.internal.common.model.SDKError;
import com.reown.android.internal.common.model.type.EngineEvent;
import com.reown.sign.client.Sign;
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
@DebugMetadata(c = "com.reown.sign.client.SignProtocol$setWalletDelegate$3", f = "SignProtocol.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
public final class SignProtocol$setWalletDelegate$3 extends SuspendLambda implements Function2<EngineEvent, Continuation<? super Unit>, Object> {
    final /* synthetic */ SignInterface.WalletDelegate $delegate;
    /* synthetic */ Object L$0;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public SignProtocol$setWalletDelegate$3(SignInterface.WalletDelegate walletDelegate, Continuation<? super SignProtocol$setWalletDelegate$3> continuation) {
        super(2, continuation);
        this.$delegate = walletDelegate;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        SignProtocol$setWalletDelegate$3 signProtocol$setWalletDelegate$3 = new SignProtocol$setWalletDelegate$3(this.$delegate, continuation);
        signProtocol$setWalletDelegate$3.L$0 = obj;
        return signProtocol$setWalletDelegate$3;
    }

    public final Object invoke(EngineEvent engineEvent, Continuation<? super Unit> continuation) {
        return ((SignProtocol$setWalletDelegate$3) create(engineEvent, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        EngineEvent engineEvent = (EngineEvent) this.L$0;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            if (engineEvent instanceof EngineDO.SessionProposalEvent) {
                EngineDO.SessionProposalEvent sessionProposalEvent = (EngineDO.SessionProposalEvent) engineEvent;
                this.$delegate.onSessionProposal(Intrinsics.checkNotNullParameter(sessionProposalEvent.getProposal(), "<this>"), Intrinsics.checkNotNullParameter(sessionProposalEvent.getContext(), "<this>"));
            } else if (engineEvent instanceof EngineDO.SessionAuthenticateEvent) {
                Function2<Sign.Model.SessionAuthenticate, Sign.Model.VerifyContext, Unit> onSessionAuthenticate = this.$delegate.getOnSessionAuthenticate();
                if (onSessionAuthenticate != null) {
                    EngineDO.SessionAuthenticateEvent sessionAuthenticateEvent = (EngineDO.SessionAuthenticateEvent) engineEvent;
                    onSessionAuthenticate.invoke(ClientMapperKt.toClientSessionAuthenticate(sessionAuthenticateEvent), Intrinsics.checkNotNullParameter(sessionAuthenticateEvent.getVerifyContext(), "<this>"));
                }
            } else if (engineEvent instanceof EngineDO.SessionRequestEvent) {
                EngineDO.SessionRequestEvent sessionRequestEvent = (EngineDO.SessionRequestEvent) engineEvent;
                this.$delegate.onSessionRequest(ClientMapperKt.toClientSessionRequest(sessionRequestEvent.getRequest()), Intrinsics.checkNotNullParameter(sessionRequestEvent.getContext(), "<this>"));
            } else if (engineEvent instanceof EngineDO.SessionDelete) {
                this.$delegate.onSessionDelete(Intrinsics.checkNotNullParameter((EngineDO.SessionDelete) engineEvent, "<this>"));
            } else if (engineEvent instanceof EngineDO.SessionExtend) {
                this.$delegate.onSessionExtend(ClientMapperKt.toClientActiveSession((EngineDO.SessionExtend) engineEvent));
            } else if (engineEvent instanceof EngineDO.SettledSessionResponse) {
                this.$delegate.onSessionSettleResponse(ClientMapperKt.toClientSettledSessionResponse((EngineDO.SettledSessionResponse) engineEvent));
            } else if (engineEvent instanceof EngineDO.SessionUpdateNamespacesResponse) {
                this.$delegate.onSessionUpdateResponse(ClientMapperKt.toClientUpdateSessionNamespacesResponse((EngineDO.SessionUpdateNamespacesResponse) engineEvent));
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
