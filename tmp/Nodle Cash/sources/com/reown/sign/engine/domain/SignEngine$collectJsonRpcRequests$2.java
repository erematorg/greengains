package com.reown.sign.engine.domain;

import com.reown.android.internal.common.model.WCRequest;
import com.reown.android.internal.common.model.type.ClientParams;
import com.reown.sign.common.model.vo.clientsync.session.params.SignParams;
import com.reown.sign.engine.use_case.requests.OnPingUseCase;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "request", "Lcom/reown/android/internal/common/model/WCRequest;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.reown.sign.engine.domain.SignEngine$collectJsonRpcRequests$2", f = "SignEngine.kt", i = {0, 0, 1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 6, 6, 7, 7, 8, 8}, l = {270, 271, 272, 273, 274, 275, 276, 277, 278}, m = "invokeSuspend", n = {"request", "requestParams", "request", "requestParams", "request", "requestParams", "request", "requestParams", "request", "requestParams", "request", "requestParams", "request", "requestParams", "request", "requestParams", "request", "requestParams"}, s = {"L$0", "L$1", "L$0", "L$1", "L$0", "L$1", "L$0", "L$1", "L$0", "L$1", "L$0", "L$1", "L$0", "L$1", "L$0", "L$1", "L$0", "L$1"})
public final class SignEngine$collectJsonRpcRequests$2 extends SuspendLambda implements Function2<WCRequest, Continuation<? super Unit>, Object> {
    /* synthetic */ Object L$0;
    Object L$1;
    int label;
    final /* synthetic */ SignEngine this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public SignEngine$collectJsonRpcRequests$2(SignEngine signEngine, Continuation<? super SignEngine$collectJsonRpcRequests$2> continuation) {
        super(2, continuation);
        this.this$0 = signEngine;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        SignEngine$collectJsonRpcRequests$2 signEngine$collectJsonRpcRequests$2 = new SignEngine$collectJsonRpcRequests$2(this.this$0, continuation);
        signEngine$collectJsonRpcRequests$2.L$0 = obj;
        return signEngine$collectJsonRpcRequests$2;
    }

    public final Object invoke(WCRequest wCRequest, Continuation<? super Unit> continuation) {
        return ((SignEngine$collectJsonRpcRequests$2) create(wCRequest, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        WCRequest wCRequest = (WCRequest) this.L$0;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure(obj);
                ClientParams params = wCRequest.getParams();
                if (params instanceof SignParams.SessionProposeParams) {
                    this.L$0 = SpillingKt.nullOutSpilledVariable(wCRequest);
                    this.L$1 = SpillingKt.nullOutSpilledVariable(params);
                    this.label = 1;
                    if (this.this$0.onSessionProposeUse.invoke(wCRequest, (SignParams.SessionProposeParams) params, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else if (params instanceof SignParams.SessionAuthenticateParams) {
                    this.L$0 = SpillingKt.nullOutSpilledVariable(wCRequest);
                    this.L$1 = SpillingKt.nullOutSpilledVariable(params);
                    this.label = 2;
                    if (this.this$0.onAuthenticateSessionUseCase.invoke(wCRequest, (SignParams.SessionAuthenticateParams) params, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else if (params instanceof SignParams.SessionSettleParams) {
                    this.L$0 = SpillingKt.nullOutSpilledVariable(wCRequest);
                    this.L$1 = SpillingKt.nullOutSpilledVariable(params);
                    this.label = 3;
                    if (this.this$0.onSessionSettleUseCase.invoke(wCRequest, (SignParams.SessionSettleParams) params, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else if (params instanceof SignParams.SessionRequestParams) {
                    this.L$0 = SpillingKt.nullOutSpilledVariable(wCRequest);
                    this.L$1 = SpillingKt.nullOutSpilledVariable(params);
                    this.label = 4;
                    if (this.this$0.onSessionRequestUseCase.invoke(wCRequest, (SignParams.SessionRequestParams) params, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else if (params instanceof SignParams.DeleteParams) {
                    this.L$0 = SpillingKt.nullOutSpilledVariable(wCRequest);
                    this.L$1 = SpillingKt.nullOutSpilledVariable(params);
                    this.label = 5;
                    if (this.this$0.onSessionDeleteUseCase.invoke(wCRequest, (SignParams.DeleteParams) params, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else if (params instanceof SignParams.EventParams) {
                    this.L$0 = SpillingKt.nullOutSpilledVariable(wCRequest);
                    this.L$1 = SpillingKt.nullOutSpilledVariable(params);
                    this.label = 6;
                    if (this.this$0.onSessionEventUseCase.invoke(wCRequest, (SignParams.EventParams) params, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else if (params instanceof SignParams.UpdateNamespacesParams) {
                    this.L$0 = SpillingKt.nullOutSpilledVariable(wCRequest);
                    this.L$1 = SpillingKt.nullOutSpilledVariable(params);
                    this.label = 7;
                    if (this.this$0.onSessionUpdateUseCase.invoke(wCRequest, (SignParams.UpdateNamespacesParams) params, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else if (params instanceof SignParams.ExtendParams) {
                    this.L$0 = SpillingKt.nullOutSpilledVariable(wCRequest);
                    this.L$1 = SpillingKt.nullOutSpilledVariable(params);
                    this.label = 8;
                    if (this.this$0.onSessionExtendUseCase.invoke(wCRequest, (SignParams.ExtendParams) params, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else if (params instanceof SignParams.PingParams) {
                    OnPingUseCase access$getOnPingUseCase$p = this.this$0.onPingUseCase;
                    this.L$0 = SpillingKt.nullOutSpilledVariable(wCRequest);
                    this.L$1 = SpillingKt.nullOutSpilledVariable(params);
                    this.label = 9;
                    if (access$getOnPingUseCase$p.invoke(wCRequest, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                }
                break;
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
                ClientParams clientParams = (ClientParams) this.L$1;
                ResultKt.throwOnFailure(obj);
                break;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return Unit.INSTANCE;
    }
}
