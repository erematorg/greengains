package com.reown.sign.engine.domain;

import com.reown.android.internal.common.model.WCResponse;
import com.reown.android.internal.common.model.type.ClientParams;
import com.reown.sign.common.model.vo.clientsync.session.params.SignParams;
import com.reown.sign.engine.use_case.responses.OnSessionSettleResponseUseCase;
import com.reown.sign.engine.use_case.responses.OnSessionUpdateResponseUseCase;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "response", "Lcom/reown/android/internal/common/model/WCResponse;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.reown.sign.engine.domain.SignEngine$collectJsonRpcResponses$2", f = "SignEngine.kt", i = {0, 0, 1, 1, 2, 2, 3, 3, 4, 4}, l = {287, 288, 289, 290, 291}, m = "invokeSuspend", n = {"response", "params", "response", "params", "response", "params", "response", "params", "response", "params"}, s = {"L$0", "L$1", "L$0", "L$1", "L$0", "L$1", "L$0", "L$1", "L$0", "L$1"})
public final class SignEngine$collectJsonRpcResponses$2 extends SuspendLambda implements Function2<WCResponse, Continuation<? super Unit>, Object> {
    /* synthetic */ Object L$0;
    Object L$1;
    int label;
    final /* synthetic */ SignEngine this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public SignEngine$collectJsonRpcResponses$2(SignEngine signEngine, Continuation<? super SignEngine$collectJsonRpcResponses$2> continuation) {
        super(2, continuation);
        this.this$0 = signEngine;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        SignEngine$collectJsonRpcResponses$2 signEngine$collectJsonRpcResponses$2 = new SignEngine$collectJsonRpcResponses$2(this.this$0, continuation);
        signEngine$collectJsonRpcResponses$2.L$0 = obj;
        return signEngine$collectJsonRpcResponses$2;
    }

    public final Object invoke(WCResponse wCResponse, Continuation<? super Unit> continuation) {
        return ((SignEngine$collectJsonRpcResponses$2) create(wCResponse, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        WCResponse wCResponse = (WCResponse) this.L$0;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i3 = this.label;
        if (i3 == 0) {
            ResultKt.throwOnFailure(obj);
            ClientParams params = wCResponse.getParams();
            if (params instanceof SignParams.SessionProposeParams) {
                this.L$0 = SpillingKt.nullOutSpilledVariable(wCResponse);
                this.L$1 = SpillingKt.nullOutSpilledVariable(params);
                this.label = 1;
                if (this.this$0.onSessionProposalResponseUseCase.invoke(wCResponse, (SignParams.SessionProposeParams) params, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else if (params instanceof SignParams.SessionAuthenticateParams) {
                this.L$0 = SpillingKt.nullOutSpilledVariable(wCResponse);
                this.L$1 = SpillingKt.nullOutSpilledVariable(params);
                this.label = 2;
                if (this.this$0.onSessionAuthenticateResponseUseCase.invoke(wCResponse, (SignParams.SessionAuthenticateParams) params, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else if (params instanceof SignParams.SessionSettleParams) {
                OnSessionSettleResponseUseCase access$getOnSessionSettleResponseUseCase$p = this.this$0.onSessionSettleResponseUseCase;
                this.L$0 = SpillingKt.nullOutSpilledVariable(wCResponse);
                this.L$1 = SpillingKt.nullOutSpilledVariable(params);
                this.label = 3;
                if (access$getOnSessionSettleResponseUseCase$p.invoke(wCResponse, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else if (params instanceof SignParams.UpdateNamespacesParams) {
                OnSessionUpdateResponseUseCase access$getOnSessionUpdateResponseUseCase$p = this.this$0.onSessionUpdateResponseUseCase;
                this.L$0 = SpillingKt.nullOutSpilledVariable(wCResponse);
                this.L$1 = SpillingKt.nullOutSpilledVariable(params);
                this.label = 4;
                if (access$getOnSessionUpdateResponseUseCase$p.invoke(wCResponse, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else if (params instanceof SignParams.SessionRequestParams) {
                this.L$0 = SpillingKt.nullOutSpilledVariable(wCResponse);
                this.L$1 = SpillingKt.nullOutSpilledVariable(params);
                this.label = 5;
                if (this.this$0.onSessionRequestResponseUseCase.invoke(wCResponse, (SignParams.SessionRequestParams) params, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            }
        } else if (i3 == 1 || i3 == 2 || i3 == 3 || i3 == 4 || i3 == 5) {
            ClientParams clientParams = (ClientParams) this.L$1;
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return Unit.INSTANCE;
    }
}
